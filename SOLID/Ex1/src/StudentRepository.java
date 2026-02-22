import java.util.List;


// It is created to abstract the persistence implementation 
// So that we can Add any database inplace of Fake Db for future scope
public interface StudentRepository {
    void save(StudentRecord record);
    List<StudentRecord> findAll();
    int count();
}
