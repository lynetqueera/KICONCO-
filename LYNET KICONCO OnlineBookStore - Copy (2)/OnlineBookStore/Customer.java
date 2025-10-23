package OnlineBookStore;

import java.util.List;
import java.util.ArrayList;

// Customer.java (Concrete Observer)
public class Customer implements PriceObserver {
    private String name;
    private String email;
    private List<Product> watchedProducts;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.watchedProducts = new ArrayList<>();
    }

    @Override
    public void update(Product product, double oldPrice, double newPrice) {
        System.out.printf("Notification to %s (%s): %s price changed from $%.2f to $%.2f%n",
                name, email, product.getTitle(), oldPrice, newPrice);
        // In real application, send email notification here
    }

    public void watchProduct(Product product) {
        if (!watchedProducts.contains(product)) {
            watchedProducts.add(product);
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Product> getWatchedProducts() {
        return watchedProducts;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWatchedProducts(List<Product> watchedProducts) {
        this.watchedProducts = watchedProducts;
    }
}