package storesystem.model;

public class Clothing extends Product {
    private String size;
    private String material;

    public Clothing(String id, String name, double price, int stockQuantity, String size, String material) {
        super(id, name, price, stockQuantity);
        this.size = size;
        this.material = material;
    }

    @Override
    public void displayInfo() {
        System.out.println("[Clothing] ID: " + getId() + ", Name: " + getName() +
                ", Size: " + size + ", Material: " + material +
                ", Price: " + getPrice() + " USD, The stock quantity: " + getStockQuantity());
    }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
}