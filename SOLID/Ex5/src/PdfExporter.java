import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    private static final int MAX_BODY_LENGTH = 20;

    @Override
    protected ExportResult doExport(ExportRequest req) {
        // Documented limitation — returns error result instead of throwing.
        if (req.body.length() > MAX_BODY_LENGTH) {
            return ExportResult.error(
                    "PDF cannot handle content > " + MAX_BODY_LENGTH + " chars");
        }
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf",
                fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
