package storesystem.model;

public class Customer extends User {
    private String deliveryAddress;

    public Customer(String name, String email, String password, String deliveryAddress) {
        super(name, email, password, "CUSTOMER");
        this.deliveryAddress = deliveryAddress;
    }

    public void placeOrder() {
        System.out.println(getName() + "places an order for delivery to an address: " + deliveryAddress);
    }

    public void viewOrderHistory() {
        System.out.println("The history of customer's orders: " + getName());
    }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
}