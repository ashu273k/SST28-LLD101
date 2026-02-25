import java.util.*;

/*
 * ─── SRP: FakeDb — Concrete Implementation of StudentRepository ───
 *
 * WHY THIS CLASS EXISTS:
 *   This is the in-memory implementation of the StudentRepository interface.
 *   It simulates a database using an ArrayList — useful for development,
 *   testing, and demos without needing a real database.
 *
 * HOW IT WORKS:
 *   - save() adds a StudentRecord to an in-memory list.
 *   - findAll() returns an unmodifiable view of all saved records.
 *   - count() returns the current number of saved records.
 *
 * KEY DESIGN POINT:
 *   Because it implements StudentRepository (the interface), FakeDb can be
 *   swapped with any other implementation (e.g., MySqlRepository) without
 *   changing OnboardingService. The service never knows it's using FakeDb —
 *   it only sees StudentRepository.
 */
public class FakeDb implements StudentRepository {
    private final List<StudentRecord> rows = new ArrayList<>();

    public void save(StudentRecord r) {
        rows.add(r);
    }

    public int count() {
        return rows.size();
    }

    public List<StudentRecord> findAll() {
        return Collections.unmodifiableList(rows);
    }
}
