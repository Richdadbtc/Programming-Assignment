import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Sample Products
        Product p1 = new Product(1, "Laptop", 800);
        Product p2 = new Product(2, "Phone", 500);
        Product p3 = new Product(3, "Headphones", 100);

        // Create Customer
        Customer customer = new Customer(101, "Chris");

        int choice;

        do {
            System.out.println("\n=== E-Commerce System ===");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Place Order");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    p1.displayProduct();
                    p2.displayProduct();
                    p3.displayProduct();
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    int id = scanner.nextInt();

                    if (id == 1) customer.addToCart(p1);
                    else if (id == 2) customer.addToCart(p2);
                    else if (id == 3) customer.addToCart(p3);
                    else System.out.println("Invalid Product ID");

                    break;

                case 3:
                    customer.viewCart();
                    break;

                case 4:
                    if (customer.getCart().isEmpty()) {
                        System.out.println("Cart is empty!");
                    } else {
                        Order order = new Order(1001, customer);
                        order.updateStatus("Completed");
                        order.displayOrder();
                    }
                    break;

                case 5:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        scanner.close();
    }
}