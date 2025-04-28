import java.util.Scanner;

// Use Runnable interface instead of extending Thread
class CalculatorTask implements Runnable {
    private int a;
    private int b;
    private char operation; // To specify the operation

    public CalculatorTask(int a, int b, char operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    @Override
    public void run() {
        try {
            switch (operation) {
                case '+':
                    System.out.println(Thread.currentThread().getName() + ": Sum of " + a + " and " + b + " is " + (a + b));
                    break;
                case '-':
                    System.out.println(Thread.currentThread().getName() + ": Difference of " + a + " and " + b + " is " + (a - b));
                    break;
                case '*':
                    System.out.println(Thread.currentThread().getName() + ": Product of " + a + " and " + b + " is " + (a * b));
                    break;
                case '/':
                    if (b == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    System.out.println(Thread.currentThread().getName() + ": Division of " + a + " and " + b + " is " + ((double) a / b));
                    break;
                default:
                    System.out.println(Thread.currentThread().getName() + ": Invalid operation: " + operation);
            }
        } catch (ArithmeticException e) {
            System.err.println(Thread.currentThread().getName() + ": Error: " + e.getMessage());
        }
    }
}

public class lab5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter second number: ");
        int num2 = scanner.nextInt();

        System.out.print("Enter operation (+, -, *, /): ");
        char op = scanner.next().charAt(0);

        // Create a Runnable task
        CalculatorTask task = new CalculatorTask(num1, num2, op);

        // Create a Thread and pass the task to it.
        Thread calculatorThread = new Thread(task);

        // Set a meaningful name to the thread.
        calculatorThread.setName("CalculatorThread");

        // Start the thread.
        calculatorThread.start();

        // Close the scanner
        scanner.close();
    }
}

