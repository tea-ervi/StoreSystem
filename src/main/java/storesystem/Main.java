package storesystem;

import storesystem.model.*;
import storesystem.service.StoreManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StoreManager manager = new StoreManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== WELCOME TO STORE SYSTEM ===");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Log in to the system");
            System.out.println("2. View the product catalog");
            System.out.println("0. Exit");
            System.out.print("Select an action: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    User currentUser = manager.loginUser(email, password);
                    if (currentUser == null) {
                        System.out.println("Login error: incorrect email or password.");
                        break;
                    }

                    System.out.println("Successful login! Welcome, " + currentUser.getName());

                    if (currentUser instanceof Customer) {
                        customerMenu(manager, (Customer) currentUser, scanner);
                    } else if (currentUser instanceof Employee) {
                        employeeMenu(manager, (Employee) currentUser, scanner);
                    }
                    break;

                case "2":
                    manager.displayAllProducts();
                    break;

                case "0":
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Incorrect choice. Try again.");
            }
        }
        scanner.close();
    }

    // Customer menu
    private static void customerMenu(StoreManager manager, Customer customer, Scanner scanner) {
        boolean active = true;
        ArrayList<Product> cart = new ArrayList<>();

        while (active) {
            System.out.println("\n--- CUSTOMER MENU (" + customer.getName() + ") ---");
            System.out.println("1. Browse the product catalog");
            System.out.println("2. Add the item to your cart");
            System.out.println("3. Place an order from the cart");
            System.out.println("4. View my order history");
            System.out.println("0. Go to the main menu");
            System.out.print("Select an action: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manager.displayAllProducts();
                    break;
                case "2":
                    manager.displayAllProducts();
                    System.out.print("Enter the item number to add to your cart: ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        ArrayList<Product> allProducts = manager.getProducts();
                        if (index >= 0 && index < allProducts.size()) {
                            Product selected = allProducts.get(index);
                            cart.add(selected);
                            System.out.println("Product \"" + selected.getName() + "\" added to cart.");
                        } else {
                            System.out.println("Incorrect product number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number.");
                    }
                    break;
                case "3":
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty!");
                    } else {
                        manager.createOrder(customer, cart);
                        cart.clear();
                    }
                    break;
                case "4":
                    customer.viewOrderHistory();
                    manager.displayAllOrders();
                    break;
                case "0":
                    active = false;
                    System.out.println("Returning to the main menu...");
                    break;
                default:
                    System.out.println("Wrong choice.");
            }
        }
    }

    // Employee menu
    private static void employeeMenu(StoreManager manager, Employee employee, Scanner scanner) {
        boolean active = true;

        while (active) {
            System.out.println("\n--- EMPLOYEE MENU (" + employee.getName() + ") ---");
            System.out.println("1. Browse the product catalog");
            System.out.println("2. Add a new product to the catalog");
            System.out.println("3. View all orders in the system");
            System.out.println("0. Go to the main menu");
            System.out.print("Select an action: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manager.displayAllProducts();
                    break;
                case "2":
                    employee.addNewProduct();
                    System.out.print("Enter the product type (1 - Book, 2 - Electronics, 3 - Clothing): ");
                    String type = scanner.nextLine();

                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter the name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter the quantity in stock: ");
                    int stock = Integer.parseInt(scanner.nextLine());

                    if (type.equals("1")) {
                        System.out.print("Enter the author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter the number of pages: ");
                        int pages = Integer.parseInt(scanner.nextLine());
                        manager.addProduct(new Book(id, name, price, stock, author, pages));
                    } else if (type.equals("2")) {
                        System.out.print("Enter brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("Enter warranty (months): ");
                        int warranty = Integer.parseInt(scanner.nextLine());
                        manager.addProduct(new Electronics(id, name, price, stock, brand, warranty));
                    } else if (type.equals("3")) {
                        System.out.print("Enter size: ");
                        String size = scanner.nextLine();
                        System.out.print("Enter the material: ");
                        String material = scanner.nextLine();
                        manager.addProduct(new Clothing(id, name, price, stock, size, material));
                    } else {
                        System.out.println("Invalid product type.");
                    }
                    break;
                case "3":
                    employee.updateStock();
                    manager.displayAllOrders();
                    break;
                case "0":
                    active = false;
                    System.out.println("Returning to the main menu...");
                    break;
                default:
                    System.out.println("Wrong choice.");
            }
        }
    }
}