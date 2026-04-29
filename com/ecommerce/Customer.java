package com.ecommerce;

import java.util.ArrayList;

public class Customer {
    private int customerID;
    private String name;
    private ArrayList<Product> cart;

    public Customer(int customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.cart = new ArrayList<>();
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    // Add product to cart
    public void addToCart(Product product) {
        cart.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    // Remove product
    public void removeFromCart(Product product) {
        if (cart.remove(product)) {
            System.out.println(product.getName() + " removed from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    // Calculate total
    public double calculateTotal() {
        double total = 0;
        for (Product p : cart) {
            total += p.getPrice();
        }
        return total;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void viewCart() {
        System.out.println("\nCart Items:");
        for (Product p : cart) {
            p.displayProduct();
        }
        System.out.println("Total: $" + calculateTotal());
    }
}