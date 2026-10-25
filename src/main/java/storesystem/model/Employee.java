package storesystem.model;

public class Employee extends User {
    private String employeeId;

    public Employee(String name, String email, String password, String employeeId) {
        super(name, email, password, "EMPLOYEE");
        this.employeeId = employeeId;
    }

    public void addNewProduct() {
        System.out.println("Employee " + getName() + " added new product in catalog.");
    }

    public void updateStock() {
        System.out.println("Employee " + getName() + " updates the stock of the ыproduct.");
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
}