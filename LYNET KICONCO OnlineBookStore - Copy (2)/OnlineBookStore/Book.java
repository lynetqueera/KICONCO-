package OnlineBookStore;

public class Book extends Product {
    private String isbn;
    private String genre;
    private int pages;

    public Book(String id, String title, String author, double price, int stock,
            String isbn, String genre, int pages) {
        super(id, title, author, price, stock);
        this.isbn = isbn;
        this.genre = genre;
        this.pages = pages;
    }

    @Override
    public String getType() {
        return "Book";
    }

    @Override
    public String getDescription() {
        return String.format("Book: %s by %s (%s, %d pages)", title, author, genre, pages);
    }

    // Getters
    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}