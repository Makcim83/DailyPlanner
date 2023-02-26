public enum RepeatPeriod {

    YearlyTask(1, 0, 0, 0, 1),
    MonthlyTask(0, 1, 0, 0, 1),
    WeeklyTask(0, 0, 1, 0, 1),
    DailyTask(0, 0, 0, 1, 1),
    OneTimeTask(0, 0, 0, 0, 0),
    schedule3after3(0, 0, 0, 3, 3),
    Weekends(0, 0, 1, 0, 2),
    Vocation(0, 0, 0, 0, 14);

    protected int repeatY;
    protected int repeatM;
    protected int repeatW;
    protected int repeatD;
    protected int durationDays;

    RepeatPeriod(int Year, int Month, int Week, int Day, int DurationDays) {
        this.repeatY = Year;
        this.repeatM = Month;
        this.repeatW = Week;
        this.repeatD = Day;
        this.durationDays = DurationDays;
    }
}
