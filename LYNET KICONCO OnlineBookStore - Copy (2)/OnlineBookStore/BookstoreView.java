package OnlineBookStore;

import java.util.List;

// BookstoreView
public class BookstoreView {
    public void displayWelcomeMessage() {
        System.out.println("=== Welcome to Online Bookstore ===");
        System.out.println();
    }

    public void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.println("=== Products ===");
        for (Product product : products) {
            System.out.println(product);
            System.out.println("  Type: " + product.getType());
            System.out.println("  Description: " + product.getDescription());
            System.out.println();
        }
    }

    public void displayMessage(String message) {
        System.out.println(">>> " + message);
    }

    public void displayMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Display all products");
        System.out.println("2. Display books only");
        System.out.println("3. Display e-books only");
        System.out.println("4. Add customer");
        System.out.println("5. Subscribe to price updates");
        System.out.println("6. Update product price");
        System.out.println("7. Add new product");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
    }
}