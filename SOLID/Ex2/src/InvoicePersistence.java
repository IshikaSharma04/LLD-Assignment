public interface InvoicePersistence {
    void save(String invoiceId, String content);
    int countLines(String invoiceId);
}
