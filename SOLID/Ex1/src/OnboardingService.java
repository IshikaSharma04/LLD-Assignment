import java.util.*;

public class OnboardingService {
    private final FakeDb db;
    private final StudentInputParser parser = new StudentInputParser();
    private final StudentValidator validator = new StudentValidator();

    public OnboardingService(FakeDb db) { this.db = db; }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        ParsedStudentData data = parser.parse(raw);
        
        String name = data.name;
        String email = data.email;
        String phone = data.phone;
        String program = data.program;

        // validation inline, printing inline
        List<String> errors = validator.validate(data);

        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            for (String e : errors) System.out.println("- " + e);
            return;
        }

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        db.save(rec);

        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + db.count());
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}
