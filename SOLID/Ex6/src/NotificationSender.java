public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    public final void send(Notification n) {
        validate(n);
        doSend(n);
    }

    // Base contract validation (no subtype may tighten)
    protected void validate(Notification n) {
        if (n == null) throw new IllegalArgumentException("notification cannot be null");
    }

    protected abstract void doSend(Notification n);
}
