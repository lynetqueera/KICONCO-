package OnlineBookStore;

import java.util.ArrayList;
import java.util.List;

// BookstoreService
public class BookstoreService {
    private ProductRepository productRepository;
    private List<Customer> customers;

    public BookstoreService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.customers = new ArrayList<>();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByType(String type) {
        return productRepository.findByType(type);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void subscribeToPriceUpdates(String productId, Customer customer) {
        PriceObservable observable = productRepository.getPriceObservable(productId);
        if (observable != null) {
            observable.addObserver(customer);
            customer.watchProduct(productRepository.findById(productId));
        }
    }

    public void updateProductPrice(String productId, double newPrice) {
        PriceObservable observable = productRepository.getPriceObservable(productId);
        if (observable != null) {
            observable.setPrice(newPrice);
        }
    }

    public void addNewProduct(String type, String id, String title, String author,
            double price, int stock, Object... additionalParams) {
        Product product = ProductFactory.createProduct(type, id, title, author, price, stock, additionalParams);
        productRepository.addProduct(product);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    
}