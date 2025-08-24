import java.sql.*;
import java.util.Scanner;

public class EmployeeDBApp {
    //the URL, USER, PASSWORD is for example only 
    //Create your own database and put your own user and password 
    
    private static final String URL = "jdbc:mysql://localhost:3306/com_employ";
    private static final String USER = "root";
    private static final String PASSWORD = "Admin@123";


    private Connection conn;

    public EmployeeDBApp() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to Database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add Employee
    public void addEmployee(String name, String department, double salary) {
        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setDouble(3, salary);
            int rows = stmt.executeUpdate();
            System.out.println("Employee added! Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View All Employees
    public void viewEmployees() {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Employee List ---");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Employee
    public void updateEmployee(int id, String name, String department, double salary) {
        String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setDouble(3, salary);
            stmt.setInt(4, id);
            int rows = stmt.executeUpdate();
            System.out.println("Employee updated! Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Employee
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println("Employee deleted! Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main Menu
    public static void main(String[] args) {
        EmployeeDBApp app = new EmployeeDBApp();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Employee Database Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    app.addEmployee(name, dept, salary);
                    break;

                case 2:
                    app.viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter Employee ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter New Department: ");
                    String udept = sc.nextLine();
                    System.out.print("Enter New Salary: ");
                    double usalary = sc.nextDouble();
                    app.updateEmployee(uid, uname, udept, usalary);
                    break;

                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    int did = sc.nextInt();
                    app.deleteEmployee(did);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
