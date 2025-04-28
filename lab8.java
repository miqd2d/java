import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lab8 implements ActionListener {

    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[8];
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton;
    private JPanel panel;

    private Font myFont = new Font("SansSerif", Font.BOLD, 30);
    private double firstNumber = 0;
    private double secondNumber = 0;
    private char operator;

    public lab8() {
        // Set up the main frame
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null); // Use absolute layout for simplicity
        frame.setLocationRelativeTo(null); // Center the frame

        // Set up the text field
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false); // Prevent user from typing directly
        textField.setHorizontalAlignment(JTextField.RIGHT); // Align text to the right
        frame.add(textField);

        // Set up the buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("C");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        // Set up the panel for number buttons
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10)); // 4 rows, 4 columns, gaps
        frame.add(panel);

        // Add number and function buttons to the panel in the desired order
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        // Add the clear and delete buttons outside the panel
        delButton.setBounds(50, 420, 100, 50);
        clrButton.setBounds(250, 420, 100, 50);
        frame.add(delButton);
        frame.add(clrButton);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new lab8();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle number button clicks
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText() + i);
                return;
            }
        }

        // Handle decimal button click
        if (e.getSource() == decButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText() + ".");
            }
            return;
        }

        // Handle clear button click
        if (e.getSource() == clrButton) {
            textField.setText("");
            firstNumber = 0;
            secondNumber = 0;
            operator = '\0';
            return;
        }

        // Handle delete button click
        if (e.getSource() == delButton) {
            String currentText = textField.getText();
            if (currentText.length() > 0) {
                textField.setText(currentText.substring(0, currentText.length() - 1));
            }
            return;
        }

        // Handle operator buttons (+, -, *, /)
        if (e.getSource() == addButton || e.getSource() == subButton || e.getSource() == mulButton || e.getSource() == divButton) {
            if (textField.getText().isEmpty()) return; //prevent error
            firstNumber = Double.parseDouble(textField.getText());
            operator = ((JButton) e.getSource()).getText().charAt(0); // Get the operator
            textField.setText(""); // Clear the text field for the next number
            return;
        }

        // Handle equals button click
        if (e.getSource() == equButton) {
            if (textField.getText().isEmpty()) return; // prevent error
            secondNumber = Double.parseDouble(textField.getText());
            double result = 0;
            try {
                switch (operator) {
                    case '+':
                        result = firstNumber + secondNumber;
                        break;
                    case '-':
                        result = firstNumber - secondNumber;
                        break;
                    case '*':
                        result = firstNumber * secondNumber;
                        break;
                    case '/':
                        if (secondNumber == 0) {
                            textField.setText("Error: Division by zero");
                            return;
                        }
                        result = firstNumber / secondNumber;
                        break;
                    default:
                        textField.setText("Error");
                        return;
                }
                textField.setText(String.valueOf(result));
                firstNumber = result; // Store the result for chaining operations
                operator = '\0';       // Reset the operator
            } catch (NumberFormatException ex) {
                textField.setText("Error");
                return;
            }
        }
    }
}

