import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        validateRequest(req);
        
        String title = escape(safeString(req.title));
        String body = escape(safeString(req.body));
        
        String json = "{\"title\":\"" + title + "\",\"body\":\"" + body + "\"}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String s) {
        return s.replace("\"", "\\\"");
    }
}
