import java.util.*;

/*
 * ─── OCP REFACTOR: EligibilityEngine (Rule Executor / Orchestrator) ───
 *
 * PROBLEM SOLVED:
 *   Before the refactor, this class had a MASSIVE if/else chain evaluating
 *   all eligibility rules inline. Adding any new rule meant editing this
 *   class (OCP violation). The method also mixed evaluation with printing
 *   and persistence (SRP violation).
 *
 * WHY WE REFACTORED IT:
 *   Now it follows BOTH SRP and OCP:
 *   - SRP: The engine ONLY evaluates rules and coordinates. Printing is
 *     delegated to ReportPrinter. Persistence to FakeEligibilityStore.
 *   - OCP: The engine takes a List<EligibilityRule> and ITERATES over them.
 *     It doesn't know which rules exist or what they check. Adding a new
 *     rule = add a new class to the list in Demo03. Engine stays UNCHANGED.
 *
 * HOW IT WORKS:
 *   1. evaluate(StudentProfile) iterates through the rule list.
 *   2. Each rule returns null (pass) or a reason string (fail).
 *   3. On first failure, it short-circuits (preserves original behavior).
 *   4. Returns EligibilityEngineResult with status and reasons.
 *   5. runAndPrint() calls evaluate(), prints via ReportPrinter, saves.
 *
 * KEY DESIGN PATTERN:
 *   This is the STRATEGY pattern applied as a rules engine:
 *   - Each rule is a "strategy" that knows how to check one condition.
 *   - The engine is the "context" that executes strategies in order.
 *   - New strategies can be added without modifying the context.
 *
 * BENEFIT:
 *   - Adding "MinorGPARule" = create class + add to list. Zero edits to engine.
 *   - Rules are independently testable.
 *   - Rule order is configurable (just change the list order).
 */
public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store, List<EligibilityRule> rules) {
        this.store = store;
        this.rules = rules;
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        for (EligibilityRule rule : rules) {
            String reason = rule.check(s);
            if (reason != null) {
                status = "NOT_ELIGIBLE";
                reasons.add(reason);
                break;  // preserves original short-circuit behavior
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}

