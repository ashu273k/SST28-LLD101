import java.util.*;

/*
 * ─── SRP: FileStore — Concrete Implementation of InvoiceStore ───
 *
 * WHY THIS CLASS EXISTS:
 *   This is the in-memory implementation of InvoiceStore. It simulates
 *   file storage using a HashMap — useful for demos and testing.
 *
 * HOW IT WORKS:
 *   - save() stores the invoice content keyed by invoice name.
 *   - countLines() counts newline characters in the stored content.
 *
 * KEY DESIGN POINT:
 *   Because it implements InvoiceStore, it can be replaced with a real
 *   file-based or database-based implementation without changing
 *   CafeteriaSystem.
 */
public class FileStore implements InvoiceStore {
    private final Map<String, String> files = new HashMap<>();

    public void save(String name, String content) {
        files.put(name, content);
    }

    public int countLines(String name) {
        String c = files.getOrDefault(name, "");
        if (c.isEmpty())
            return 0;
        return c.split("\n").length;
    }
}
