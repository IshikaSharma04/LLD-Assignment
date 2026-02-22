import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        validateRequest(req);
    
        String body = safeString(req.body).replace("\n", " ").replace(",", " ");
        String title = safeString(req.title);
        
        String csv = "title,body\n" + title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
