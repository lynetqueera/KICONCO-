package OnlineBookStore;

public class ProductFactory {
    public static Product createProduct(String type, String id, String title, String author,
            double price, int stock, Object... additionalParams) {
        
        // Input validation
        validateCommonParameters(id, title, author, price, stock);
        
        switch (type.toLowerCase()) {
            case "book":
                validateBookParameters(additionalParams);
                String isbn = (String) additionalParams[0];
                String genre = (String) additionalParams[1];
                Integer pages = (Integer) additionalParams[2];
                validateISBN(isbn);
                validatePages(pages);
                
                return new Book(id, title, author, price, stock, isbn, genre, pages);

            case "ebook":
                validateEbookParameters(additionalParams);
                String format = (String) additionalParams[0];
                Double fileSize = (Double) additionalParams[1];
                String downloadLink = (String) additionalParams[2];
                validateFileSize(fileSize);
                validateDownloadLink(downloadLink);
                
                return new EBook(id, title, author, price, stock, format, fileSize, downloadLink);

            default:
                throw new IllegalArgumentException("Unknown product type: " + type);
        }
    }
    
    private static void validateCommonParameters(String id, String title, String author, 
                                               double price, int stock) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative: " + price);
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative: " + stock);
        }
    }
    
    private static void validateBookParameters(Object[] additionalParams) {
        if (additionalParams.length < 3) {
            throw new IllegalArgumentException("Book requires 3 additional parameters: ISBN, genre, pages");
        }
        if (!(additionalParams[0] instanceof String)) {
            throw new IllegalArgumentException("ISBN must be a string");
        }
        if (!(additionalParams[1] instanceof String)) {
            throw new IllegalArgumentException("Genre must be a string");
        }
        if (!(additionalParams[2] instanceof Integer)) {
            throw new IllegalArgumentException("Pages must be an integer");
        }
    }
    
    private static void validateEbookParameters(Object[] additionalParams) {
        if (additionalParams.length < 3) {
            throw new IllegalArgumentException("EBook requires 3 additional parameters: format, fileSize, downloadLink");
        }
        // checks for similar type checks for ebook...
    }
    
    private static void validateISBN(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        // Add ISBN format validation if needed
    }
    
    private static void validatePages(Integer pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be positive: " + pages);
        }
        if (pages > 10000) {
            throw new IllegalArgumentException("Unreasonable number of pages: " + pages);
        }
    }
    
    private static void validateFileSize(Double fileSize) {
        if (fileSize <= 0) {
            throw new IllegalArgumentException("File size must be positive: " + fileSize);
        }
        if (fileSize > 1000) { // 1000 MB = 1GB
            throw new IllegalArgumentException("File size too large: " + fileSize + " MB");
        }
    }
    
    private static void validateDownloadLink(String downloadLink) {
        if (downloadLink == null || downloadLink.trim().isEmpty()) {
            throw new IllegalArgumentException("Download link cannot be empty");
        }
        if (!downloadLink.startsWith("http")) {
            throw new IllegalArgumentException("Download link must be a valid URL");
        }
    }
}