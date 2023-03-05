public enum RepeatPeriod {

    YearlyTask(true, false, false, false, 1),
    MonthlyTask(false, true, false, false, 1),
    WeeklyTask(false, false, true, false, 1),
    DailyTask(false, false, false, true, 1),
    OneTimeTask(false, false, false, false, 0),
    Weekends(false, false, true, false, 2),
    Vocation(false, false, false, false, 14);

    protected boolean repeatY;
    protected boolean repeatM;
    protected boolean repeatW;
    protected boolean repeatD;
    protected int durationDays;

    RepeatPeriod(boolean Year, boolean Month, boolean Week, boolean Day, int DurationDays) {
        this.repeatY = Year;
        this.repeatM = Month;
        this.repeatW = Week;
        this.repeatD = Day;
        this.durationDays = DurationDays;
    }
}
