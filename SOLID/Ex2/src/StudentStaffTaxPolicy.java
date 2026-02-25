/*
 * ─── SRP: StudentStaffTaxPolicy — Concrete Tax Implementation ───
 *
 * WHY THIS CLASS EXISTS:
 *   This is the concrete implementation of TaxPolicy that knows the actual
 *   tax rules for students, staff, and others.
 *
 * HOW IT WORKS:
 *   - "student" → 5% tax
 *   - "staff"   → 2% tax
 *   - others    → 8% tax (default)
 *
 * KEY DESIGN POINT:
 *   The rules here are specific to the student/staff scenario. If a new
 *   scenario arises (e.g., "GST-compliant tax policy"), we create a NEW
 *   class implementing TaxPolicy — we don't edit this one. This is OCP.
 *
 * NOTE: The "hard-coded policy (smell)" comment reminds us that in a
 * production system, these values might come from a config file or database.
 */
public class StudentStaffTaxPolicy implements TaxPolicy {
    @Override
     public double taxPercent(String customerType) {
        // hard-coded policy (smell)
        if ("student".equalsIgnoreCase(customerType)) return 5.0;
        if ("staff".equalsIgnoreCase(customerType)) return 2.0;
        return 8.0;
    }
}
