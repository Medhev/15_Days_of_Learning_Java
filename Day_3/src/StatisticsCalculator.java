import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class StatisticsCalculator {

    private static double calcMin(double[] arr) throws IllegalArgumentException {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        double minimum = arr[0];
        for (double v : arr) {
            // Example validation: reject NaN
            if (Double.isNaN(v)) {
                throw new IllegalArgumentException("Invalid input: NaN");
            }
            if (v < minimum) {
                minimum = v;
            }
        }
        return minimum;
    }

    private static double calcMax(double[] arr) throws IllegalArgumentException {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        double maximum = arr[0];
        for (double v : arr) {
            if (Double.isNaN(v)) {
                throw new IllegalArgumentException("Invalid input: NaN");
            }
            if (v > maximum) {
                maximum = v;
            }
        }
        return maximum;
    }

    private static double calcMean(double[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        double sum = 0;
        for (double v : arr) {
            if (Double.isNaN(v)) {
                throw new IllegalArgumentException("Invalid input: NaN");
            }
            sum += v;
        }
        return sum / arr.length;
    }

    private static double calcMedian(double[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        for (double v : arr) {
            if (Double.isNaN(v)) {
                throw new IllegalArgumentException("Invalid input: NaN");
            }
        }
        double[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        int mid = sorted.length / 2;
        if (sorted.length % 2 == 0) {
            return (sorted[mid - 1] + sorted[mid]) / 2;
        } else {
            return sorted[mid];
        }
    }

    private static double findFirstMode(double[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        Map<Double, Integer> freq = new LinkedHashMap<>();
        for (double v : arr) {
            freq.put(v, freq.getOrDefault(v, 0) + 1);
        }
        double mode = arr[0];
        int maxCount = 0;
        for (Map.Entry<Double, Integer> e : freq.entrySet()) {
            if (e.getValue() > maxCount) {
                maxCount = e.getValue();
                mode = e.getKey();
            }
        }
        return mode;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to My Statistic Calculator app");
        Scanner input = new Scanner(System.in);
        boolean valid = true;
        while (valid) {
            System.out.println("Enter operation: Min, Max, Mean, Median, Mode");
            String choice = input.nextLine().trim().toLowerCase();

            System.out.print("Enter the number of values: ");
            if (!input.hasNextInt()) {
                System.out.println("Invalid number. Exiting.");
                break;
            }
            int n = input.nextInt();
            double[] arr = new double[n];
            System.out.println("Enter the numbers:");
            for (int i = 0; i < n; i++) {
                if (!input.hasNextDouble()) {
                    System.out.println("Invalid input. Exiting.");
                    return;
                }
                arr[i] = input.nextDouble();
            }
            input.nextLine(); // consume newline

            double result;
            try {
                switch (choice) {
                    case "min":
                        result = calcMin(arr);
                        break;
                    case "max":
                        result = calcMax(arr);
                        break;
                    case "mean":
                        result = calcMean(arr);
                        break;
                    case "median":
                        result = calcMedian(arr);
                        break;
                    case "mode":
                        result = findFirstMode(arr);
                        break;
                    default:
                        System.out.println("Unknown operation. Try again.");
                        continue;
                }
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("Do you want to continue? (Y/N): ");
            String cont = input.nextLine().trim().toUpperCase();
            if (cont.equals("N")) {
                valid = false;
            }
        }
        input.close();
        System.out.println("OK!");
    }
}
