package OnlineBookStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



// ProductRepository
public class ProductRepository {
    private final Map<String, Product> products;
    private final Map<String, PriceObservable> priceObservables;

    public ProductRepository() {
        this.products = new HashMap<>();
        this.priceObservables = new HashMap<>();
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Add sample books
        addProduct(ProductFactory.createProduct("book", "B001", "HOW TO GET AWAY WITH MURDER",
                "KICONCO LYNET", 12.99, 10, "978-0743273565", "Fiction", 180));

        addProduct(ProductFactory.createProduct("ebook", "E005", "OBJECT ORIENTED PROGRAMMING",
                "LYN C. LYNET", 29.99, 100, "PDF", 2.5, "http://downloads/clean-code.pdf"));

                addProduct(ProductFactory.createProduct("ebook", "E002", "JAVA PROGRAMMING",
                "JOHN C KARIM", 29.98, 109, "PDF", 1.9, "https://books.google.co.ug/books?id=IIoOAAAAQAAJ"));

        addProduct(ProductFactory.createProduct("book", "B002", "To Kill a Mockingbird",
                "Harper LYNET", 14.99, 5, "978-0061120084", "Fiction", 281));

                       addProduct(ProductFactory.createProduct("book", "B003", "database progaramming with mysql",
                "Harriet Twinamatsiko", 12.99, 8, "978-0061320084", "academic", 441));

                       addProduct(ProductFactory.createProduct("book", "B006", "The great gatsgy",
                "larry hassan", 14.99, 9, "978-0061220084", "adventure", 581));
            }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
        priceObservables.put(product.getId(), new PriceObservable(product));
    }

    public Product findById(String id) {
        return products.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public List<Product> findByType(String type) {
        return products.values().stream()
                .filter(p -> p.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public PriceObservable getPriceObservable(String productId) {
        return priceObservables.get(productId);
    }

    public void updateProduct(Product product) {
        products.put(product.getId(), product);
    }
}