import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, TaskNotFoundException {
        System.out.println("Daily planner");

        ArrayList<Task> AllTasks = new ArrayList<>();
        System.out.println("Some Tasks added to Planner (for example):");
        AllTasks.add(new Task("New Year!!", Task.Type.personal, LocalDate.of(2023, 01, 01), RepeatPeriod.YearlyTask, "Santa comes soon!"));
        AllTasks.add(new Task("Weekends", Task.Type.personal, LocalDate.of(2023, 01, 04), RepeatPeriod.Weekends, "Some Descriptions for Weekends"));
        AllTasks.add(new Task("SomeWorkDay", Task.Type.work, LocalDate.of(2023, 02, 28), RepeatPeriod.WeeklyTask));
        AllTasks.add(new Task("Happy Birthday", Task.Type.personal, LocalDate.of(2023, 11, 10), RepeatPeriod.YearlyTask, ""));
        AllTasks.add(new Task("Some Work Day 3 after 3", Task.Type.work, LocalDate.of(2023, 02, 28), RepeatPeriod.schedule3after3, "Work time"));
        AllTasks.forEach(System.out::println);

        do {} while (TaskService.startMenu() != 0);
    }
}
