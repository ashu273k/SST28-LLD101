/*
 * ─── SRP REFACTOR: IdGenerator ───
 *
 * PROBLEM SOLVED:
 *   ID generation logic (counter + formatting "SST-2026-XXXX") was inlined
 *   in OnboardingService. If the ID format changed (e.g., adding department
 *   prefix, switching to UUID), the entire service method had to be edited.
 *
 * WHY WE CREATED THIS CLASS:
 *   Separated ID generation into its own class with ONE responsibility:
 *   producing unique student IDs. Follows SRP.
 *   - Maintains an internal counter for sequential IDs.
 *   - nextId() returns the next formatted ID string.
 *
 * HOW IT WORKS:
 *   Increments an internal counter and formats it as "SST-2026-0001", etc.
 *   The format is the only thing this class cares about.
 *
 * BENEFIT:
 *   - Changing ID format (e.g., UUID, database sequence) = edit here only.
 *   - OnboardingService just calls nextId() — doesn't know the format.
 *   - Easy to mock/stub in tests.
 */
public class IdGenerator {

    private int counter = 0;
    public String nextId() {
        counter++;
        return String.format("SST-2026-%04d", counter);
    }
}
