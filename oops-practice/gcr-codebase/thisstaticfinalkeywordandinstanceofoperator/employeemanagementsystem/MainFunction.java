package employeemanagementsystem;

public class MainFunction {
    public static void main(String[] args) {
        // Creating Employee objects
        Employees emp1 = new Employees(101, "Alice", "Developer");
        Employees emp2 = new Employees(102, "Bob", "Manager");

        // Display static company name and total employee count
        Employees.displayCompanyName();
        Employees.displayTotalEmployees();

        System.out.println("\n--- Employee 1 Details ---");
        Employees.showIfEmployee(emp1);

        System.out.println("\n--- Employee 2 Details ---");
        Employees.showIfEmployee(emp2);

        System.out.println("\n--- Invalid Object Check ---");
        Employees.showIfEmployee("NotAnEmployee");
    }
}
