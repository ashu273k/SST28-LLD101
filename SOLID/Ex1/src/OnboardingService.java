import java.util.*;

/*
 * ─── SRP REFACTOR: OnboardingService (Orchestrator) ───
 *
 * PROBLEM SOLVED:
 *   Before the refactor, this class was a "god class" — it did EVERYTHING:
 *   parsing raw input, validating fields, generating IDs, building records,
 *   saving to the database, and printing output. This violated SRP because
 *   it had 5+ reasons to change.
 *
 * WHY WE REFACTORED IT:
 *   After applying SRP, this class now has ONLY ONE responsibility:
 *   ORCHESTRATING the onboarding workflow. It coordinates the steps but
 *   DELEGATES the actual work to specialized classes:
 *     - StudentParser     → handles parsing
 *     - StudentValidator   → handles validation
 *     - IdGenerator        → handles ID creation
 *     - StudentRepository  → handles persistence (via interface)
 *     - OnboardingPrinter  → handles all console output
 *
 * HOW IT WORKS:
 *   The constructor receives all dependencies via DEPENDENCY INJECTION.
 *   registerFromRawInput() simply calls each collaborator in sequence:
 *     1. Print header/input   (printer)
 *     2. Parse raw string     (parser)
 *     3. Validate fields      (validator) — return early on errors
 *     4. Generate ID          (idGenerator)
 *     5. Build StudentRecord
 *     6. Save to repository   (repository)
 *     7. Print confirmation   (printer)
 *
 * KEY DESIGN DECISIONS:
 *   - Dependencies are injected via constructor (not created internally).
 *     This means the service doesn't know about FakeDb, specific formats, etc.
 *   - Each step is a single method call to a collaborator.
 *   - Adding a new step (e.g., sending a welcome email) = inject a new
 *     collaborator, add one line here. No existing code changes.
 *
 * BENEFIT:
 *   - Each class can be tested, modified, and replaced independently.
 *   - The service reads like a checklist of steps, easy to understand.
 *   - Follows SRP: this class changes ONLY if the workflow itself changes.
 */
public class OnboardingService {
    private final StudentRepository repository;
    private final StudentParser parser;
    private final StudentValidator validator;
    private final IdGenerator idGenerator;
    private OnboardingPrinter printer;

    // ─── Dependency Injection via Constructor ───
    // All collaborators are passed in from outside. The service does NOT
    // create them with "new". This enables swapping implementations
    // (e.g., FakeDb → RealDb) without changing this class.
    public OnboardingService(StudentRepository repository, StudentParser parser, StudentValidator validator, IdGenerator idGenerator, OnboardingPrinter printer) {
        this.repository = repository;
        this.parser = parser;
        this.validator = validator;
        this.idGenerator = idGenerator;
        this.printer = printer;
    }

    // ─── The Orchestration Method ───
    // This method ONLY coordinates the workflow steps. It does NOT contain
    // any business logic (parsing rules, validation rules, ID format, etc.).
    // Each step is delegated to a specialized collaborator.
    public void registerFromRawInput(String raw) {
        
        printer.printHeader();
        printer.printInput(raw);

        // Step 1: DELEGATE parsing to StudentParser
        Map<String, String> fields = parser.parse(raw);

        // Step 2: DELEGATE validation to StudentValidator
        List<String> errors = validator.validate(fields);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }
        
        // Step 3: DELEGATE ID generation to IdGenerator
        String id = idGenerator.nextId();

        // Step 4: Build the data record (simple object construction)
        StudentRecord record = new StudentRecord(
            id, 
            fields.get("name"), 
            fields.get("email"), 
            fields.get("phone"), 
            fields.get("program"));

        // Step 5: DELEGATE persistence to StudentRepository (interface)
        repository.save(record);
        printer.printCreated(id);
        printer.printSavedCount(repository.count());

        // Step 6: DELEGATE printing to OnboardingPrinter
        printer.printConfirmation(record);
        printer.printDbDump(repository.findAll());
        
        
       
    }
}
