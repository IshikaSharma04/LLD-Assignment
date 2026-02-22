public class AttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public AttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    @Override
    public RuleResult check(StudentProfile student) {
        if (student.attendancePct < minAttendance) {
            return RuleResult.fail("attendance below " + minAttendance);
        }
        return RuleResult.pass();
    }
}
