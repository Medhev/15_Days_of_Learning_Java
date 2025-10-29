import java.util.Scanner;

public class GuessingGame {
    private static boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static int validatingNumber(String number) {
        int numb = Integer.MIN_VALUE;
        try {
            boolean isNum = isNumber(number);
            if (isNum) numb = Integer.parseInt(number);
            else throw new NumberFormatException("Invalid Input");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return numb;
    }

    public static void main(String[] args) throws Exception {

        Scanner receiveInput = new Scanner(System.in);
        int count = 0;

        System.out.println("Welcome to My Number Guessing App");

        System.out.print("\nEnter the limit of Guessing: ");
        String limit = receiveInput.nextLine();
        int sameLimit = validatingNumber(limit);

        int target = (int) (Math.random() * sameLimit) + 1;

        boolean isCorrect = true;
        while (isCorrect) {

            System.out.print("\nEnter the value to guess: ");
            String guess = receiveInput.nextLine();
            int guessNumber = validatingNumber(guess);

            if (guessNumber == target) {
                System.out.println("Congratulations! You guessed the number correctly!");
                isCorrect = false;

            } else if (guessNumber > target) {
                System.out.println("Your guess is too high. Try again.");
                count++;

            } else {
                System.out.println("Your guess is too low. Try again.");
                count++;

            }
        }
        System.out.println("The no. of attempts is: "+ count);
    }
}

