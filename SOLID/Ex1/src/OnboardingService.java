import java.util.*;

// It has the single responsibility of managing all the services
public class OnboardingService {
    private final StudentRepository repository;
    private final StudentParser parser;
    private final StudentValidator validator;
    private final IdGenerator idGenerator;
    private OnboardingPrinter printer;

    public OnboardingService(StudentRepository repository, StudentParser parser, StudentValidator validator, IdGenerator idGenerator, OnboardingPrinter printer) {
        this.repository = repository;
        this.parser = parser;
        this.validator = validator;
        this.idGenerator = idGenerator;
        this.printer = printer;
    }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        
        printer.printHeader();
        printer.printInput(raw);

        // 1. Parse
        Map<String, String> fields = parser.parse(raw);

        // 2. Validate
        List<String> errors = validator.validate(fields);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }
        
        //3. Generate ID
        String id = idGenerator.nextId();

        // 4. Build record
        StudentRecord record = new StudentRecord(
            id, 
            fields.get("name"), 
            fields.get("email"), 
            fields.get("phone"), 
            fields.get("program"));

        // 5. Persist
        repository.save(record);
        printer.printCreated(id);
        printer.printSavedCount(repository.count());

        // 6. Confirm
        printer.printConfirmation(record);
        printer.printDbDump(repository.findAll());
        
        
       
    }
}
