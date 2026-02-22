public abstract class Exporter {
    public abstract ExportResult export(ExportRequest req);
    protected void validateRequest(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("ExportRequest cannot be null");
        }
    }
    
    protected String safeString(String s) {
        return s == null ? "" : s;
    }
}
