# Java JDBC – Employee Database App

## 📌 Objective
A Java console application that connects to a **MySQL** or **PostgreSQL** database using JDBC and performs **CRUD** operations (Create, Read, Update, Delete) on an `employees` table.

---

## 🚀 Features
- Connect to **MySQL** or **PostgreSQL** database
- **Add** new employees
- **View** all employees
- **Update** employee details
- **Delete** employees by ID
- Uses **PreparedStatement** and **ResultSet** for safe and efficient database access

---

## 🗄️ Database Setup

### MySQL
```sql
CREATE DATABASE companydb;

USE companydb;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50),
    salary DOUBLE
);
⚙️ Configuration

In EmployeeDBApp.java, update the database connection details:

private static final String URL = "jdbc:mysql://localhost:3306/companydb";
// For PostgreSQL: "jdbc:postgresql://localhost:5432/companydb"
private static final String USER = "root"; // Change if needed
private static final String PASSWORD = "password"; // Change to your DB password

📜 Usage

When you run the program, you’ll see the menu:
--- Employee Database Menu ---
1. Add Employee
2. View Employees
3. Update Employee
4. Delete Employee
5. Exit
Choose an option and follow the prompts.

📝 Notes

Use PreparedStatement to prevent SQL injection

Ensure database service is running before connecting

Adjust port and credentials as needed for your DB
