import java.time.LocalDate;
import java.util.Scanner;

public class InputValues {
    static Scanner scanner = new Scanner(System.in);
    public LocalDate currentDate = LocalDate.of(2023, 1, 31);

    public static int scanYear() throws IncorrectArgumentException {
        try {
            return scanInt(LocalDate.now().getYear(), 3000, "year (0 for exit)");
        } catch (Exception e) {
            throw new IncorrectArgumentException("Year number incorrect");
        }
    }

    ;

    public static int scanMonth() throws IncorrectArgumentException {
        try {
            return scanInt(1, 12, "month (0 for exit)");
        } catch (Exception e) {
            throw new IncorrectArgumentException("Month number incorrect");
        }
    }

    ;

    public static int scanDay(int year, int month) throws IncorrectArgumentException {
        try {
            int maxDaysInMonth = LocalDate.of(year, month, 1).lengthOfMonth();
            return scanInt(1, maxDaysInMonth, "day (0 for exit)");
        } catch (Exception e) {
            throw new IncorrectArgumentException("Day number incorrect");
        }
    }

    ;

    public static int scanID(String message) {
        int maxID = 9999;
        System.out.println();
        return scanInt(0, maxID, message);
    }

    ;

    public static int scanInt(int minValue, int maxValue, String message) {
        while (true) {
            System.out.println(message);
            while (!scanner.hasNextInt()) {
                System.out.println("incorrect input, number must be integer");
                scanner.nextLine();
            }
            int value = scanner.nextInt();
            if (value == 0 || value <= maxValue && value >= minValue) {
                return value;
            }
            System.out.println("Enter a number in the range from " + minValue + " to " + maxValue);
        }
    }
}
