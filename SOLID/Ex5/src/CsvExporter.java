import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    protected ExportResult doExport(ExportRequest req) {
        // Proper RFC 4180 quoting — no data loss.
        String csv = "title,body\n" + quoteCsv(req.title) + "," + quoteCsv(req.body) + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    /** Quotes a field if it contains comma, newline, or double-quote. */
    private String quoteCsv(String s) {
        if (s.contains(",") || s.contains("\n") || s.contains("\"")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }
}
