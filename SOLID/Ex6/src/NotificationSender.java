/**
 * Base type for all notification channels.
 *
 * Contract for send(Notification n):
 *   Pre : n is non-null with a non-null body.
 *   Post: the body is delivered as-is (no silent truncation or alteration).
 *         If the channel cannot deliver (e.g. missing/invalid address),
 *         it prints a diagnostic message and logs the failure to audit —
 *         it never throws a runtime exception.
 *         Exactly one audit entry is added per call.
 */
public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }
    public abstract void send(Notification n);
}
