import java.sql.*;
import java.util.Scanner;

public class lab9 {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  // Corrected driver class name
    static final String DB_URL = "jdbc:mysql://localhost/testdb"; // Replace with your database URL

    //  Database credentials
    static final String USER = "localhost";  // Replace with your database username
    static final String PASS = "6622";  // Replace with your database password

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create a table if it doesn't exist
            System.out.println("Creating table if it doesn't exist...");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS employees " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully.");


            // Main loop for CRUD operations
            while (true) {
                System.out.println("\nChoose an operation:");
                System.out.println("1: Create (Insert) Record");
                System.out.println("2: Read (Select) Records");
                System.out.println("3: Update Record");
                System.out.println("4: Delete Record");
                System.out.println("5: Exit");
                System.out.print("Enter your choice: ");
                int choice = 0;
                try{
                    choice = scanner.nextInt();
                }catch(Exception e){
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    scanner.next(); // Consume the invalid input
                    continue; // Restart the loop
                }
                scanner.nextLine(); // Consume the newline character after reading the integer input.


                switch (choice) {
                    case 1:
                        // Create (Insert)
                        System.out.print("Enter employee ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter employee name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter employee age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        try {
                            sql = "INSERT INTO employees (id, name, age) " +
                                    "VALUES (?, ?, ?)";
                            PreparedStatement pstmt = conn.prepareStatement(sql); // Use PreparedStatement to prevent SQL injection
                            pstmt.setInt(1, id);
                            pstmt.setString(2, name);
                            pstmt.setInt(3, age);
                            pstmt.executeUpdate();
                            System.out.println("Record inserted successfully.");
                        } catch (SQLException e) {
                            System.err.println("Error inserting record: " + e.getMessage());
                        }
                        break;

                    case 2:
                        // Read (Select)
                        System.out.println("Reading records...");
                        sql = "SELECT id, name, age FROM employees";
                        try{
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(sql);

                            // Extract data from result set
                            while (rs.next()) {
                                //Retrieve by column name
                                id = rs.getInt("id");
                                name = rs.getString("name");
                                age = rs.getInt("age");

                                //Display values
                                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
                            }
                        } catch (SQLException e){
                            System.err.println("Error reading records: " + e.getMessage());
                        }
                        break;

                    case 3:
                        // Update
                        System.out.print("Enter employee ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Enter new employee name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new employee age: ");
                        int newAge = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        try {
                            sql = "UPDATE employees SET name=?, age=? WHERE id=?";
                            PreparedStatement pstmt = conn.prepareStatement(sql); // Use PreparedStatement
                            pstmt.setString(1, newName);
                            pstmt.setInt(2, newAge);
                            pstmt.setInt(3, updateId);
                            int rowsUpdated = pstmt.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Record updated successfully.");
                            } else {
                                System.out.println("No record found with ID " + updateId);
                            }
                        } catch (SQLException e) {
                            System.err.println("Error updating record: " + e.getMessage());
                        }
                        break;

                    case 4:
                        // Delete
                        System.out.print("Enter employee ID to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        try {
                            sql = "DELETE FROM employees WHERE id=?";
                            PreparedStatement pstmt = conn.prepareStatement(sql); // Use PreparedStatement
                            pstmt.setInt(1, deleteId);
                            int rowsDeleted = pstmt.executeUpdate();
                            if (rowsDeleted > 0) {
                                System.out.println("Record deleted successfully.");
                            } else {
                                System.out.println("No record found with ID " + deleteId);
                            }
                        } catch (SQLException e) {
                            System.err.println("Error deleting record: " + e.getMessage());
                        }
                        break;
                    case 5:
                        // Exit
                        System.out.println("Exiting application...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } // End of main loop

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (rs != null) rs.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

