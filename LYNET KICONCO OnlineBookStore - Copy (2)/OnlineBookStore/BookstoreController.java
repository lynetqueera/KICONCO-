package OnlineBookStore;

// BookstoreController
public class BookstoreController {
    private BookstoreService bookstoreService;
    private BookstoreView view;

    public BookstoreController(BookstoreService bookstoreService, BookstoreView view) {
        this.bookstoreService = bookstoreService;
        this.view = view;
    }

    public void displayAllProducts() {
        view.displayProducts(bookstoreService.getAllProducts());
    }

    public void displayBooks() {
        view.displayProducts(bookstoreService.getProductsByType("Book"));
    }

    public void displayEBooks() {
        view.displayProducts(bookstoreService.getProductsByType("E-Book"));
    }

    public void addCustomer(String name, String email) {
        Customer customer = new Customer(name, email);
        bookstoreService.addCustomer(customer);
        view.displayMessage("Customer added: " + name);
    }

    public void subscribeCustomerToProduct(String customerName, String productId) {
        Customer customer = findCustomerByName(customerName);
        if (customer != null) {
            bookstoreService.subscribeToPriceUpdates(productId, customer);
            view.displayMessage(customerName + " subscribed to price updates for product " + productId);
        } else {
            view.displayMessage("Customer not found: " + customerName);
        }
    }

    public void updateProductPrice(String productId, double newPrice) {
        bookstoreService.updateProductPrice(productId, newPrice);
        view.displayMessage("Price updated for product " + productId);
    }

    public void addNewProduct(String type, String id, String title, String author,
            double price, int stock, Object... additionalParams) {
        bookstoreService.addNewProduct(type, id, title, author, price, stock, additionalParams);
        view.displayMessage("Product added: " + title);
    }

    private Customer findCustomerByName(String name) {
        return bookstoreService.getCustomers().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void setBookstoreService(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public void setView(BookstoreView view) {
        this.view = view;
    }
}