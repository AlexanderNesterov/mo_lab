package first;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserInput {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static int getInt(String description) {
        int number = 4;

        System.out.print("Enter " + description + ": ");
        while (number < 5 || number % 2 == 0) {
            try {
                number = Integer.parseInt(reader.readLine());
            } catch(Exception ignored) {
                System.out.print("Incorrect number. " + description + ": ");
            }
        }

        System.out.println();
        return number;
    }

    public static double getDouble(String description) {
        double number;

        System.out.print("Enter " + description + ": ");
        while (true) {
            try {
                number = Double.parseDouble(reader.readLine());
                System.out.println();
                return number;
            } catch(Exception ignored) {
                System.out.print("Incorrect number. " + description + ": ");
            }
        }
    }

    public static String getString() {
        System.out.print("Enter the statement: ");

        while (true) {
            try {
                return reader.readLine();
            } catch(Exception ignored) {
            }
        }
    }
}
