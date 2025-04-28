import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// User-defined exception for age validation
class AgeNotWithinRangeException extends Exception {
    public AgeNotWithinRangeException(String message) {
        super(message);
    }
}

// User-defined exception for name validation
class NameNotValidException extends Exception {
    public NameNotValidException(String message) {
        super(message);
    }
}

// Employee class
class Employee {
    int eid;
    String name;
    int age;
    String department;

    public Employee(int eid, String name, int age, String department) throws AgeNotWithinRangeException, NameNotValidException {
        this.eid = eid;

        // Name validation: Check for numbers or special symbols
        Pattern pattern = Pattern.compile("[^a-zA-Z\\s]"); // Allows only letters and spaces
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            throw new NameNotValidException("Name contains invalid characters (numbers or special symbols).");
        }
        this.name = name;

        // Age validation: Check if age is within the valid range
        if (age < 25 || age > 60) {
            throw new AgeNotWithinRangeException("Age is not within the valid range (25-60).");
        }
        this.age = age;
        this.department = department;
    }

    public void displayDetails() {
        System.out.println("Employee ID: " + eid);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Department: " + department);
    }
}

public class lab6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Part A: Arithmetic Exception and InputMismatchException
        System.out.println("----- Part A: Arithmetic Exception and InputMismatchException -----");
        try {
            System.out.print("Enter the first number (n1): ");
            int n1 = scanner.nextInt();
            System.out.print("Enter the second number (n2): ");
            int n2 = scanner.nextInt();
            double result = (double) n1 / n2; // Cast to double for floating-point division
            System.out.println("Division of n1 by n2: " + result);
        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage()); // Print to standard error
        } catch (InputMismatchException e) {
            System.err.println("Error: Invalid input. Please enter integers only.");
            scanner.next(); // Consume the invalid input to prevent an infinite loop
        }

        // Part B: Custom Exception for Employee Validation
        System.out.println("\n----- Part B: Custom Exception for Employee Validation -----");
        try {
            System.out.print("Enter employee ID: ");
            int eid = scanner.nextInt();
            scanner.nextLine(); // Consume the newline after reading the integer

            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();

            System.out.print("Enter employee age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter employee department: ");
            String department = scanner.nextLine();

            Employee employee = new Employee(eid, name, age, department);
            System.out.println("Employee record is valid.");
            employee.displayDetails(); // Display the employee details

        } catch (AgeNotWithinRangeException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NameNotValidException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Error: Invalid input.  Please enter data in the correct format.");
            scanner.next(); // consume the wrong input
        } finally {
            scanner.close(); // Close the scanner to prevent resource leaks
        }
    }
}

