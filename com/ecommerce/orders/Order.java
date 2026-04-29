package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private Customer customer;
    private ArrayList<Product> products;
    private double total;
    private String status;

    public Order(int orderID, Customer customer) {
        this.orderID = orderID;
        this.customer = customer;
        this.products = new ArrayList<>(customer.getCart());
        this.total = customer.calculateTotal();
        this.status = "Pending";
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public void displayOrder() {
        System.out.println("\nOrder ID: " + orderID);
        System.out.println("Customer: " + customer.getName());

        System.out.println("Products:");
        for (Product p : products) {
            p.displayProduct();
        }

        System.out.println("Total: $" + total);
        System.out.println("Status: " + status);
    }
}