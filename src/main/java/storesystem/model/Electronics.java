package storesystem.model;

public class Electronics extends Product {
    private String brand;
    private int warrantyMonths;

    public Electronics(String id, String name, double price, int stockQuantity, String brand, int warrantyMonths) {
        super(id, name, price, stockQuantity);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public void displayInfo() {
        System.out.println("[Electronic] ID: " + getId() + ", Name: " + getName() +
                ", Brand: " + brand + ", Warranty months: " + warrantyMonths + " months" +
                ", Price: " + getPrice() + " USD, The stock quantity: " + getStockQuantity());
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public int getWarrantyMonths() { return warrantyMonths; }
    public void setWarrantyMonths(int warrantyMonths) { this.warrantyMonths = warrantyMonths; }
}