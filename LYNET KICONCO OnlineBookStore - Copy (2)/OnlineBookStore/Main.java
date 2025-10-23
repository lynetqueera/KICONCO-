package OnlineBookStore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize layers
        ProductRepository repository = new ProductRepository();
        BookstoreService service = new BookstoreService(repository);
        BookstoreView view = new BookstoreView();
        BookstoreController controller = new BookstoreController(service, view);

        try (Scanner scanner = new Scanner(System.in)) {
            view.displayWelcomeMessage();

            boolean running = true;
            while (running) {
                view.displayMenu();
                
                // Validate menu input
                int choice = 0;
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    System.out.println("Invalid input. Please enter a number between 1-8.");
                    scanner.nextLine(); // clear invalid input
                    continue;
                }
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        controller.displayAllProducts();
                        break;

                    case 2:
                        controller.displayBooks();
                        break;

                    case 3:
                        controller.displayEBooks();
                        break;

                    case 4:
                        addCustomerInteractive(controller, scanner);
                        break;

                    case 5:
                        subscribeCustomerInteractive(controller, scanner);
                        break;

                    case 6:
                        updatePriceInteractive(controller, scanner);
                        break;

                    case 7:
                        addNewProductInteractive(controller, scanner);
                        break;

                    case 8:
                        running = false;
                        view.displayMessage("Thank you for using Online Bookstore!");
                        break;

                    default:
                        view.displayMessage("Invalid option. Please choose 1-8.");
                }
            }
        }
    }

    private static void addCustomerInteractive(BookstoreController controller, Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine().trim();
        
        // Basic validation
        if (name.isEmpty()) {
            System.out.println("Error: Customer name cannot be empty.");
            return;
        }
        if (email.isEmpty() || !email.contains("@")) {
            System.out.println("Error: Please enter a valid email address.");
            return;
        }
        
        controller.addCustomer(name, email);
    }

    private static void subscribeCustomerInteractive(BookstoreController controller, Scanner scanner) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();
        
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine().trim();
        
        if (customerName.isEmpty() || productId.isEmpty()) {
            System.out.println("Error: Customer name and product ID cannot be empty.");
            return;
        }
        
        controller.subscribeCustomerToProduct(customerName, productId);
    }

    private static void updatePriceInteractive(BookstoreController controller, Scanner scanner) {
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine().trim();
        
        if (productId.isEmpty()) {
            System.out.println("Error: Product ID cannot be empty.");
            return;
        }
        
        System.out.print("Enter new price: ");
        double newPrice = 0;
        if (scanner.hasNextDouble()) {
            newPrice = scanner.nextDouble();
            scanner.nextLine(); // consume newline
        } else {
            System.out.println("Error: Please enter a valid price.");
            scanner.nextLine(); // clear invalid input
            return;
        }
        
        if (newPrice < 0) {
            System.out.println("Error: Price cannot be negative.");
            return;
        }
        
        controller.updateProductPrice(productId, newPrice);
    }

    private static void addNewProductInteractive(BookstoreController controller, Scanner scanner) {
        try {
            // Get and validate product type
            String type = getValidatedProductType(scanner);
            if (type == null) return;

            // Get and validate common product fields
            String id = getValidatedProductId(scanner);
            if (id == null) return;

            String title = getValidatedStringInput(scanner, "Enter title: ");
            if (title == null) return;

            String author = getValidatedStringInput(scanner, "Enter author: ");
            if (author == null) return;

            Double price = getValidatedDoubleInput(scanner, "Enter price: ", 0.0, 10000.0);
            if (price == null) return;

            Integer stock = getValidatedIntInput(scanner, "Enter stock: ", 0, 10000);
            if (stock == null) return;

            // Handle type-specific parameters
            if (type.equalsIgnoreCase("book")) {
                addBookProduct(controller, scanner, id, title, author, price, stock);
            } else if (type.equalsIgnoreCase("ebook")) {
                addEbookProduct(controller, scanner, id, title, author, price, stock);
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static String getValidatedProductType(Scanner scanner) {
        while (true) {
            System.out.print("Enter product type (book/ebook): ");
            String type = scanner.nextLine().trim().toLowerCase();
            
            if (type.isEmpty()) {
                System.out.println("Product type cannot be empty.");
                continue;
            }
            
            if (type.equals("book") || type.equals("ebook")) {
                return type;
            } else {
                System.out.println("Invalid product type. Please enter 'book' or 'ebook'.");
                System.out.print("Do you want to try again? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("yes") && !response.equals("y")) {
                    return null;
                }
            }
        }
    }

    private static String getValidatedProductId(Scanner scanner) {
        while (true) {
            System.out.print("Enter product ID: ");
            String id = scanner.nextLine().trim();
            
            if (id.isEmpty()) {
                System.out.println("Product ID cannot be empty.");
                continue;
            }
            
            // Check for basic ID format (at least one letter and one number)
            if (!id.matches(".*[A-Za-z].*") || !id.matches(".*[0-9].*")) {
                System.out.println("Product ID should contain both letters and numbers for better identification.");
                System.out.print("Do you want to use this ID anyway? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("yes") || response.equals("y")) {
                    return id;
                }
            } else {
                return id;
            }
        }
    }

    private static String getValidatedStringInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty.");
                continue;
            }
            
            if (input.length() < 2) {
                System.out.println("Input is too short. Please enter at least 2 characters.");
                continue;
            }
            
            return input;
        }
    }

    private static Double getValidatedDoubleInput(Scanner scanner, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                
                if (value < min) {
                    System.out.printf("Error: Value cannot be less than %.2f.\n", min);
                } else if (value > max) {
                    System.out.printf("Error: Value cannot be greater than %.2f.\n", max);
                } else {
                    return value;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
            }
            
            System.out.print("Do you want to try again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                return null;
            }
        }
    }

    private static Integer getValidatedIntInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                if (value < min) {
                    System.out.printf("Error: Value cannot be less than %d.\n", min);
                } else if (value > max) {
                    System.out.printf("Error: Value cannot be greater than %d.\n", max);
                } else {
                    return value;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid whole number.");
                scanner.nextLine(); // clear invalid input
            }
            
            System.out.print("Do you want to try again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                return null;
            }
        }
    }

    private static void addBookProduct(BookstoreController controller, Scanner scanner, String id, String title, String author, 
                                     double price, int stock) {
        System.out.println("\n--- Book Specific Details ---");
        
        String isbn = getValidatedStringInput(scanner, "Enter ISBN: ");
        if (isbn == null) return;
        
        String genre = getValidatedStringInput(scanner, "Enter genre: ");
        if (genre == null) return;
        
        Integer pages = getValidatedIntInput(scanner, "Enter number of pages: ", 1, 10000);
        if (pages == null) return;
        
        // Confirm before adding
        System.out.println("\n--- Product Summary ---");
        System.out.println("Type: Book");
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.printf("Price: $%.2f\n", price);
        System.out.println("Stock: " + stock);
        System.out.println("ISBN: " + isbn);
        System.out.println("Genre: " + genre);
        System.out.println("Pages: " + pages);
        
        System.out.print("\n ADD PRODUCT (Y/N): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (confirmation.equals("yes") || confirmation.equals("y")) {
            controller.addNewProduct("book", id, title, author, price, stock, isbn, genre, pages);
        } else {
            System.out.println("Product addition cancelled.");
        }
    }
    private static void addEbookProduct(BookstoreController controller, Scanner scanner,
                                       String id, String title, String author,
                                      double price, int stock) {
        System.out.println("\n--- E-Book Specific Details ---");
        
        String format = getValidatedStringInput(scanner, "Enter format (PDF, EPUB, etc.): ");
        if (format == null) return;
        
        Double fileSize = getValidatedDoubleInput(scanner, "Enter file size (MB): ", 0.1, 1000.0);
        if (fileSize == null) return;
        
        String downloadLink = getValidatedDownloadLink(scanner);
        if (downloadLink == null) return;
        
        // Confirm before adding
        System.out.println("\n--- Product Summary ---");
        System.out.println("Type: E-Book");
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.printf("Price: $%.2f\n", price);
        System.out.println("Stock: " + stock);
        System.out.println("Format: " + format);
        System.out.printf("File Size: %.1f MB\n", fileSize);
        System.out.println("Download Link: " + downloadLink);
        
        System.out.print("\n ADD PRODUCT: ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (confirmation.equals("yes") || confirmation.equals("y")) {
            controller.addNewProduct("ebook", id, title, author, price, stock, format, fileSize, downloadLink);
        } else {
            System.out.println("Product addition cancelled.");
        }
    }

    private static String getValidatedDownloadLink(Scanner scanner) {
        while (true) {
            System.out.print("Enter download link: ");
            String link = scanner.nextLine().trim();
            
            if (link.isEmpty()) {
                System.out.println("Download link cannot be empty.");
                continue;
            }
            
            // Basic URL validation
            if (!link.startsWith("http://") && !link.startsWith("https://")) {
                System.out.println("Warning: Download link should start with http:// or https://");
                System.out.print("Do you want to use this link anyway? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("yes") || response.equals("y")) {
                    return link;
                }
            } else {
                return link;
            }
        }
    }
}