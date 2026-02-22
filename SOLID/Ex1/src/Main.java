public class Main {
    public static void main(String[] args) {
       
        StudentParser parser = new StudentParser();
        StudentValidator validator = new StudentValidator();
        IdGenerator idGenerator = new IdGenerator();
        StudentRepository repository = new FakeDb();
        OnboardingPrinter printer = new OnboardingPrinter();
        OnboardingService service = new OnboardingService(
            repository, parser, validator, idGenerator, printer
        );

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        service.registerFromRawInput(raw);
        
    }
}
