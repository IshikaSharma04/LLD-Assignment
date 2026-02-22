import java.util.*;

public class OnboardingService {
    private final StudentRepository db;
    private final InputParser parser;
    private final StudentValidator validator;
    private final MessageLogger logger;

    public OnboardingService(StudentRepository db) {
        this.db = db;
        this.parser = new InputParser();
        this.validator = new StudentValidator();
        this.logger = new MessageLogger();
    }

    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        Map<String,String> kv = parser.parse(raw);

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        List<String> errors = validator.validate(name, email, phone, program);

        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            for (String e : errors) System.out.println("- " + e);
            return;
        }

        String id = IdUtil.nextStudentId();
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        db.save(rec);

        logger.printConfirmation(id, rec, db.count());
    }
}
