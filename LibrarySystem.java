import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibrarySystem {

    static class Book {
        private String title;
        private String author;
        private int quantity;

        public Book(String title, String author, int quantity) {
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "Title: " + title + ", Author: " + author + ", Quantity: " + quantity;
        }
    }

    private static Map<String, Book> library = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Library System ---");
            System.out.println("1. Add Books");
            System.out.println("2. Borrow Books");
            System.out.println("3. Return Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    addBooks();
                    break;
                case 2:
                    borrowBooks();
                    break;
                case 3:
                    returnBooks();
                    break; // Fixed: was missing break
                case 4:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBooks() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter author: ");
        String author = scanner.nextLine().trim();
        System.out.print("Enter quantity: ");
        
        int quantity = 0;
        if (scanner.hasNextInt()) {
            quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } else {
            System.out.println("Invalid quantity. Operation cancelled.");
            scanner.next();
            return;
        }

        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        String key = title.toLowerCase();
        if (library.containsKey(key)) {
            Book existingBook = library.get(key);
            existingBook.setQuantity(existingBook.getQuantity() + quantity);
            System.out.println("Book updated! New quantity: " + existingBook.getQuantity());
        } else {
            library.put(key, new Book(title, author, quantity));
            System.out.println("New book added successfully!");
        }
    }

    private static void borrowBooks() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter quantity to borrow: ");
        
        int quantity = 0;
        if (scanner.hasNextInt()) {
            quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } else {
            System.out.println("Invalid quantity. Operation cancelled.");
            scanner.next();
            return;
        }

        if (quantity <= 0) {
            System.out.println("Please enter a valid positive quantity.");
            return;
        }

        String key = title.toLowerCase();
        if (library.containsKey(key)) {
            Book book = library.get(key);
            if (book.getQuantity() >= quantity) {
                book.setQuantity(book.getQuantity() - quantity);
                System.out.println("You have successfully borrowed " + quantity + " copies of '" + book.getTitle() + "'.");
            } else {
                System.out.println("Error: Not enough copies available. Current quantity: " + book.getQuantity());
            }
        } else {
            System.out.println("Error: Book not found in the library.");
        }
    }

    private static void returnBooks() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter quantity to return: ");
        
        int quantity = 0;
        if (scanner.hasNextInt()) {
            quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } else {
            System.out.println("Invalid quantity. Operation cancelled.");
            scanner.next();
            return;
        }

        if (quantity <= 0) {
            System.out.println("Please enter a valid positive quantity.");
            return;
        }

        String key = title.toLowerCase();
        if (library.containsKey(key)) {
            Book book = library.get(key);
            book.setQuantity(book.getQuantity() + quantity);
            System.out.println("You have successfully returned " + quantity + " copies of '" + book.getTitle() + "'.");
        } else {
             System.out.println("Error: This book does not belong to our library system.");
        }
    }
}
