import java.util.Scanner;

class Calculator {
    public static void main (String[] args) {
        try {
            Scanner receivingInput = new Scanner(System.in);
            System.out.println("Welcome to My Calc App");
            boolean exit = true;
            while (exit) {
                System.out.print("Enter the number 1: ");
                int a = receivingInput.nextInt();
                System.out.print("Enter the number 2: ");
                int b = receivingInput.nextInt();
                System.out.print("Enter the operation to perform (+,-,*,/,%): ");
                char operator = receivingInput.next().charAt(0);
                switch (operator) {
                    case '+':
                        System.out.printf("The addition of this operation is %d .\n", (a + b));
                        break;
                    case '-':
                        System.out.printf("The Subtraction of this operation is %d. \n", (a - b));
                        break;
                    case '*':
                        System.out.printf("The Multiplication of this operation is %d. \n", (a * b));
                        break;
                    case '/':
                        if (b == 0) {
                            System.out.println("ERROR: Division by Zero");
                        } else {
                            System.out.printf("The Division of this operation is %d. \n", (a / b));
                        }
                        break;
                    case '%':
                        System.out.printf("The Modulo of this operation is %d. \n", (a % b));
                        break;
                    default:
                        System.out.println("Invalid Operator");
                        exit = false;
                }
            }
        } catch(Exception e) {System.out.println((e.getMessage()));}
    }
}