public class CgrRule implements EligibilityRule {
    private final double minCgr;

    public CgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public RuleResult check(StudentProfile student) {
        if (student.cgr < minCgr) {
            return RuleResult.fail("CGR below " + minCgr);
        }
        return RuleResult.pass();
    }
}
