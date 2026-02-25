/*
 * ─── Demo01 — Wiring / Composition Root ───
 *
 * WHY THIS CLASS EXISTS:
 *   This is where all the pieces are ASSEMBLED together. It acts as the
 *   "composition root" — the one place where concrete implementations
 *   are chosen and injected into the service.
 *
 * KEY DESIGN POINT:
 *   Notice how we create concrete instances (StudentParser, FakeDb, etc.)
 *   and inject them into OnboardingService via its CONSTRUCTOR. This is
 *   Dependency Injection in action. If we wanted to swap FakeDb for a
 *   real MySQL database, we'd change ONLY this file — not the service.
 *
 * HOW THE SRP REFACTOR IS VISIBLE HERE:
 *   - 5 separate collaborators are created, each with ONE responsibility.
 *   - The service receives all of them and just orchestrates.
 *   - StudentRepository is declared as the INTERFACE type — FakeDb is
 *     the implementation. The service never knows which implementation.
 */
public class Demo01 {
    public static void main(String[] args) {
       
        // ─── Create each SRP-separated collaborator ───
        StudentParser parser = new StudentParser();         // Responsibility: parsing
        StudentValidator validator = new StudentValidator(); // Responsibility: validation
        IdGenerator idGenerator = new IdGenerator();         // Responsibility: ID generation
        StudentRepository repository = new FakeDb();         // Responsibility: persistence (via interface)
        OnboardingPrinter printer = new OnboardingPrinter(); // Responsibility: console output

        // ─── Inject all collaborators into the orchestrator ───
        OnboardingService service = new OnboardingService(
            repository, parser, validator, idGenerator, printer
        );

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        service.registerFromRawInput(raw);
        
    }
}
