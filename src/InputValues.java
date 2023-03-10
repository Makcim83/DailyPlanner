import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class InputValues {
    static Scanner scanner = new Scanner(System.in);
    public LocalDate currentDate = LocalDate.of(2023, 1, 31);

    public static int scanYear() throws IncorrectArgumentException {
        try {
            return scanInt(LocalDate.now().getYear(), 3000, "year");
        } catch (Exception e) {
            throw new IncorrectArgumentException("Year number incorrect");
        }
    }

    ;

    public static int scanMonth() throws IncorrectArgumentException {
        try {
            return scanInt(1, 12, "month");
        } catch (Exception e) {
            throw new IncorrectArgumentException("Month number incorrect");
        }
    }

    ;

    public static int scanDay(int year, int month) throws IncorrectArgumentException {
        try {
            int maxDaysInMonth = LocalDate.of(year, month, 1).lengthOfMonth();
            return scanInt(1, maxDaysInMonth, "day");
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
                scanner.nextLine();
            }
            int value = scanner.nextInt();
            if (value == 0 || value <= maxValue && value >= minValue) {
                return value;
            }
            System.out.println("Enter a number in the range from " + minValue + " to " + maxValue);
        }
    }

    public static String scanString(int minLength, int maxLength, String message) {
        while (true) {
            System.out.println(message);
            String str = scanner.nextLine();
            if (str.isEmpty() && minLength == 0) return "";
            if (minLength <= str.length() && str.length() <= maxLength) return str;
            System.out.println("incorrect input, need length " + minLength + ".." + maxLength);
        }
    }

    public static LocalDate scanDate(String message) throws IncorrectArgumentException {
        while (true) {
            Date dateInput = null;
            System.out.print(message);
            System.out.println("Input date in format dd.mm.yyyy");
            Scanner input = new Scanner(System.in);
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String cinput = input.nextLine();
            try {
                if (cinput != null && cinput.trim().length() > 0) {
                    dateInput = format.parse(cinput);
                }
                System.out.println("Input date is : " + dateInput);
                LocalDate localDate = dateInput.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                return localDate;
            } catch (ParseException e) {
                System.err.println("Date must be in format dd.mm.yyyy");
            }
        }
    }
}
