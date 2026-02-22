// It has the single responsibility of creating the record of the student
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
