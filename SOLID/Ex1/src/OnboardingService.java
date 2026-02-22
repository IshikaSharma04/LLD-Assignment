import java.util.*;

public class OnboardingService {
    private final FakeDb db;
    private final StudentInputParser parser = new StudentInputParser();
    private final StudentValidator validator = new StudentValidator();
    private final RegistrationPrinter printer = new RegistrationPrinter();

    public OnboardingService(FakeDb db) { this.db = db; }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        ParsedStudentData data = parser.parse(raw);

        // validation inline, printing inline
        List<String> errors = validator.validate(data);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(db.count());

        StudentRecord rec = new StudentRecord(
            id,
            data.name,
            data.email,
            data.phone,
            data.program
        );

        db.save(rec);
        printer.printSuccess(rec, db.count());
    }
}
