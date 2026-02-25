/*
 * ─── StudentProfile (Value Object / DTO) ───
 *
 * WHY THIS CLASS EXISTS:
 *   Holds all the data about a student that the eligibility rules need:
 *   rollNo, name, cgr, attendancePct, earnedCredits, disciplinaryFlag.
 *   Public final fields make it immutable — rules can read but not modify.
 *
 * DESIGN CONSTRAINT:
 *   "Keep StudentProfile fields unchanged" per exercise requirements.
 */
public class StudentProfile {
    public final String rollNo;
    public final String name;
    public final double cgr;
    public final int attendancePct;
    public final int earnedCredits;
    public final int disciplinaryFlag;

    public StudentProfile(String rollNo, String name, double cgr, int attendancePct, int earnedCredits, int disciplinaryFlag) {
        this.rollNo = rollNo; this.name = name; this.cgr = cgr;
        this.attendancePct = attendancePct; this.earnedCredits = earnedCredits;
        this.disciplinaryFlag = disciplinaryFlag;
    }
}
