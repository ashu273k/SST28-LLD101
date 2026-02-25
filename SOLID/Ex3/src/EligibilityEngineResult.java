import java.util.*;

/*
 * ─── EligibilityEngineResult (Data Transfer Object) ───
 *
 * WHY THIS CLASS EXISTS:
 *   Carries the evaluation result (status + reasons) from the engine to
 *   the printer. Cleanly separates "computing the result" from
 *   "formatting/displaying the result" (SRP).
 *
 * DESIGN:
 *   - status: "ELIGIBLE" or "NOT_ELIGIBLE"
 *   - reasons: list of failure reason strings (empty if eligible)
 */
public class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
