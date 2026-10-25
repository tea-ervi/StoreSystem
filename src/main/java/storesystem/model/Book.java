package storesystem.model;

public class Book extends Product {
    private String author;
    private int pages;

    public Book(String id, String name, double price, int stockQuantity, String author, int pages) {
        super(id, name, price, stockQuantity);
        this.author = author;
        this.pages = pages;
    }

    @Override
    public void displayInfo() {
        System.out.println("[Book] ID: " + getId() + ", Name: " + getName() +
                ", Author: " + author + ", Pages: "  + pages +
                ", Price: " + getPrice() + " USD, The quantity stock: " + getStockQuantity());
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }
}