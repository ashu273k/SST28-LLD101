/**
 * Base exporter contract (Template Method).
 *
 * Preconditions  – enforced here, subtypes must NOT tighten them:
 *   • req != null
 *   • req.title != null
 *   • req.body  != null
 *
 * Postconditions – every subtype must honour:
 *   • Never throws; returns ExportResult.error(...) on failure.
 *   • On success, result.bytes faithfully represents the original data
 *     (no silent data loss or semantic change).
 */
public abstract class Exporter {

    public final ExportResult export(ExportRequest req) {
        // ── common precondition checks (subtypes cannot tighten) ──
        if (req == null) {
            return ExportResult.error("Request must not be null");
        }
        if (req.title == null || req.body == null) {
            return ExportResult.error("Title and body must not be null");
        }
        return doExport(req);
    }

    /** Format-specific logic. Called only after preconditions pass. */
    protected abstract ExportResult doExport(ExportRequest req);
}
