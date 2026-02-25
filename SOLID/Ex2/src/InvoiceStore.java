/*
 * ─── SRP REFACTOR: InvoiceStore (Interface / Abstraction) ───
 *
 * PROBLEM SOLVED:
 *   Persistence was using a concrete class directly. CafeteriaSystem
 *   was tightly coupled to a specific file/storage mechanism. Changing
 *   how invoices are saved (e.g., to a real database or cloud) would
 *   require editing the billing system.
 *
 * WHY WE CREATED THIS INTERFACE:
 *   - SRP: Defines WHAT persistence does (save, countLines), not HOW.
 *   - Dependency Inversion: CafeteriaSystem depends on this abstraction.
 *   - FileStore is the in-memory implementation; a real DatabaseStore or
 *     CloudStore can be created later without touching CafeteriaSystem.
 *   - Testability: Inject a mock store in tests to verify save behavior.
 */
public interface InvoiceStore {
    void save(String name, String content);
    int countLines(String name);
}
