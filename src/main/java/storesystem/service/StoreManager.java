package storesystem.service;

import storesystem.model.*;
import java.util.ArrayList;

public class StoreManager {
    private ArrayList<User> users;
    private ArrayList<Product> products;
    private ArrayList<Order> orders;
    private int orderCounter = 1;

    public StoreManager() {
        users = new ArrayList<>();
        products = new ArrayList<>();
        orders = new ArrayList<>();

        initDefaultData();
    }

    private void initDefaultData() {
        // test employee and customer
        users.add(new Employee("The best manager", "emp@store.com", "123", "001"));
        users.add(new Customer("The first customer", "cust@store.com", "123", "Pushkin st. 10"));

        // test products
        products.add(new Book("01", "Java Programming", 10.0, 5, "Kate The Best Writer Ever", 500));
        products.add(new Electronics("02", "Smartphone XL", 450.0, 3, "TechBrand", 12));
        products.add(new Clothing("03", "Oversized hoodie", 15.0, 10, "L", "Cotton"));
    }


    public User loginUser(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }


    public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("The product catalog is empty.");
            return;
        }
        System.out.println("\n--- PRODUCT CATALOG ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". ");
            products.get(i).displayInfo();
        }
    }

    // Добавление нового товара (для сотрудника)
    public void addProduct(Product product) {
        products.add(product);
        System.out.println("The product has been successfully added to the system!");
    }


    public ArrayList<Product> getProducts() {
        return products;
    }


    public void createOrder(Customer customer, ArrayList<Product> selectedProducts) {
        if (selectedProducts.isEmpty()) {
            System.out.println("Error: cannot place an empty order.");
            return;
        }


        for (Product p : selectedProducts) {
            if (p.getStockQuantity() > 0) {
                p.setStockQuantity(p.getStockQuantity() - 1);
            } else {
                System.out.println("Attention: product " + p.getName() + " out of stock!");
            }
        }

        String orderId = "ORD-" + String.format("%03d", orderCounter++);
        Order newOrder = new Order(orderId, customer, new ArrayList<>(selectedProducts));
        orders.add(newOrder);

        System.out.println("Order successfully placed!");
        newOrder.printReceipt();
    }


    public void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("There are no orders in the system yet.");
            return;
        }
        System.out.println("\n--- ORDER HISTORY ---");
        for (Order o : orders) {
            o.printReceipt();
        }
    }
}