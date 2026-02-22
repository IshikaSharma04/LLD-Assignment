import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    private static final int MAX_CONTENT_LENGTH = 20;

    @Override
    public ExportResult export(ExportRequest req) {
        validateRequest(req);
        
        String body = safeString(req.body);
        
        if (body.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException("PDF cannot handle content > " + MAX_CONTENT_LENGTH + " chars");
        }
        
        String fakePdf = "PDF(" + safeString(req.title) + "):" + body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
