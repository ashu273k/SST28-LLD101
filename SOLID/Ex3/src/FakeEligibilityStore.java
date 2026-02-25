/*
 * ─── FakeEligibilityStore — Persistence Stub ───
 *
 * WHY THIS CLASS EXISTS:
 *   Simulates saving evaluation results. In a real system, this would
 *   write to a database. Kept simple for demo purposes.
 *   Could be abstracted behind an interface for full Dependency Inversion.
 */
public class FakeEligibilityStore {
    public void save(String roll, String status) {
        System.out.println("Saved evaluation for roll=" + roll);
    }
}
