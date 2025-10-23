package OnlineBookStore;

// EBook
public class EBook extends Product {
    private String format;
    private double fileSize;
    private String downloadLink;

    public EBook(String id, String title, String author, double price, int stock,
            String format, double fileSize, String downloadLink) {
        super(id, title, author, price, stock);
        this.format = format;
        this.fileSize = fileSize;
        this.downloadLink = downloadLink;
    }

    @Override
    public String getType() {
        return "E-Book";
    }

    @Override
    public String getDescription() {
        return String.format("E-Book: %s by %s (%s, %.1f MB)", title, author, format, fileSize);
    }

    // Getters
    public String getFormat() {
        return format;
    }

    public double getFileSize() {
        return fileSize;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }
}