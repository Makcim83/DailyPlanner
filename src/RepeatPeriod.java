import java.time.LocalDate;

public enum RepeatPeriod {

    OneTimeTask(false, false, false, false, 1),
    DailyTask(false, false, false, true, 1),
    WeeklyTask(false, false, true, false, 1),
    MonthlyTask(false, true, false, false, 1),
    YearlyTask(true, false, false, false, 1),
    Weekends(false, false, true, false, 2),
    Vocation(false, false, false, false, 14);

    private boolean repeatY;
    private boolean repeatM;
    private boolean repeatW;
    private boolean repeatD;
    private int durationDays;

    RepeatPeriod(boolean Year, boolean Month, boolean Week, boolean Day, int DurationDays) {
        this.repeatY = Year;
        this.repeatM = Month;
        this.repeatW = Week;
        this.repeatD = Day;
        this.durationDays = DurationDays;
    }

    public static boolean isEntry(LocalDate dateShow, LocalDate dateTask, RepeatPeriod repeatPeriod) {
        LocalDate d;
        if (dateShow.isBefore(dateTask)) {
            System.out.println("    Test: match 0 - the task hasn't started yet " + dateTask + " is before than " + dateShow + " (duration " + repeatPeriod.durationDays + " days)");
            return false;
        }

        if (isEntryInDuration(dateShow, dateTask, repeatPeriod)) {
            System.out.println("    Test: match 1 - range");
            return true;
        }

        if (repeatPeriod.repeatD) {
            System.out.println("    Test: match 2 - everyday");
            return true;
        }
        d = dateTask;
        if (repeatPeriod.repeatW) {
            if (dateShow.getDayOfWeek() == dateTask.getDayOfWeek()) {
                System.out.println("    Test: match  3 every week first day");
                return true;
            }
            if (repeatPeriod.durationDays == 2 && dateShow.getDayOfWeek() == d.plusDays(1).getDayOfWeek()) {
                System.out.println("    Test: match  4 every week second day");
                return true;
            }
        }


        if (repeatPeriod.repeatM) {
            d = dateTask;
            while (d.isBefore(dateShow)) {
                d = d.plusMonths(1);
                if (isEntryInDuration(dateShow, d, repeatPeriod)) {
                    System.out.println("    Test: match  5 - every month");
                    return true;
                }
            }
        }

        if (repeatPeriod.repeatY) {
            d = dateTask;
            while (d.isBefore(dateShow)) {
                d = d.plusYears(1);
                if (isEntryInDuration(dateShow, d, repeatPeriod)) {
                    System.out.println("    Test: match  6 - every year");
                    return true;
                }
            }
        }

        System.out.println("    Test: no matches " + dateTask);
        return false;
    }

    private static boolean isEntryInDuration(LocalDate date, LocalDate dateTask, RepeatPeriod repeatPeriod) {
        LocalDate d = dateTask.plusDays(repeatPeriod.durationDays - 1);
        System.out.println("    Test: next range " + repeatPeriod + " " + d);
        if (date.isEqual(dateTask)) {
            return true;
        }
        ;
        if (repeatPeriod.durationDays > 1) {
            if (date.isEqual(d) || (date.isAfter(dateTask) && date.isBefore(d))) {
                return true;
            }
        }
        return false;
    }
}
