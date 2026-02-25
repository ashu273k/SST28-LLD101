/*
 * ─── SRP: StudentRecord (Data Transfer Object) ───
 *
 * WHY THIS CLASS EXISTS:
 *   This is a simple data holder (DTO) that carries student information
 *   between layers — from the service to the repository to the printer.
 *   It has ONE responsibility: hold student data in a structured form.
 *
 * PROBLEM IT SOLVES:
 *   Without this, we'd pass raw strings or Maps around, making the code
 *   fragile and hard to read. By having a typed record with named fields
 *   (id, name, email, phone, program), every layer gets clear, type-safe data.
 *
 * DESIGN DECISIONS:
 *   - Fields are public final (immutable after creation) — once a record
 *     is built, it cannot be accidentally modified.
 *   - Getters are provided for print formatting compatibility.
 *   - toString() is overridden for easy debugging / confirmation output.
 */
public class StudentRecord {
    public final String id;
    public final String name;
    public final String email;
    public final String phone;
    public final String program;

    public StudentRecord(String id, String name, String email, String phone, String program) {
        this.id = id; this.name = name; this.email = email; this.phone = phone; this.program = program;
    }

    
    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    
    public String getEmail() {
        return this.email;
    }
    
    
    public String getPhone() {
        return this.phone;
    }
    
    public String getProgram() {
        return this.program;
    }

    @Override
    public String toString() {
        return "StudentRecord{id='" + id + "', name='" + name + "', email='" + email + "', phone='" + phone + "', program='" + program + "'}";
    }
}
