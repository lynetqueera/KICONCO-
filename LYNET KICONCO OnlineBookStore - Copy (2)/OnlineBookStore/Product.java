package OnlineBookStore;

// Product
public abstract class Product {
    protected String id;
    protected String title;
    protected String author;
    protected double price;
    protected int stock;

    public Product(String id, String title, String author, double price, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public abstract String getType();

    public abstract String getDescription();

    @Override
    public String toString() {
        return String.format("%s [ID: %s, Author: %s, Price: $%.2f, Stock: %d]",
                title, id, author, price, stock);
    }
}