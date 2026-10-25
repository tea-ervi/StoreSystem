package storesystem.model;

import java.util.ArrayList;

public class Order {
    private final String orderId;
    private final Customer customer;
    private final ArrayList<Product> products;
    private final double totalAmount;

    public Order(String orderId, Customer customer, ArrayList<Product> products) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.totalAmount = calculateTotal();
    }

    public double calculateTotal() {
        double sum = 0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void printReceipt() {
        System.out.println("\n========= Receipt № " + orderId + " =========");
        System.out.println("Customer: " + customer.getName() + " (" + customer.getEmail() + ")");
        System.out.println("Address: " + customer.getDeliveryAddress());
        System.out.println("----------------------------------------------");
        System.out.println("Products in order:");
        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". ");
            products.get(i).displayInfo();
        }
        System.out.println("----------------------------------------------");
        System.out.println("Total amount: " + totalAmount + " руб.");
        System.out.println("==============================================\n");
    }


    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public ArrayList<Product> getProducts() { return products; }
    public double getTotalAmount() { return totalAmount; }
}