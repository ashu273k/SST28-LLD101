import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {
    @Override
    protected ExportResult doExport(ExportRequest req) {
        String json = "{\"title\":\"" + escapeJson(req.title)
                    + "\",\"body\":\"" + escapeJson(req.body) + "\"}";
        return new ExportResult("application/json",
                json.getBytes(StandardCharsets.UTF_8));
    }

    /** Escapes characters that are special inside a JSON string value. */
    private String escapeJson(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
