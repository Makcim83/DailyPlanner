import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TaskService {
    static Set<Task> taskSet = new HashSet<>();
    static Set<Task> removedTasks = new HashSet<>();

    public static void addTasksForExample() {
        System.out.println("Some Tasks added to Planner (for example):");
        taskSet.add(new Task("New Year!!", Task.Type.personal, LocalDate.of(2023, 01, 01), RepeatPeriod.YearlyTask, "Santa comes soon!"));
        taskSet.add(new Task("Weekends", Task.Type.personal, LocalDate.of(2023, 01, 07), RepeatPeriod.Weekends, "Some Descriptions for Weekends"));
        taskSet.add(new Task("SomeWorkDay", Task.Type.work, LocalDate.of(2023, 02, 28), RepeatPeriod.WeeklyTask));
        taskSet.add(new Task("Happy Birthday", Task.Type.personal, LocalDate.of(2023, 11, 10), RepeatPeriod.YearlyTask, ""));
        taskSet.add(new Task("Vocation", Task.Type.work, LocalDate.of(2023, 02, 28), RepeatPeriod.Vocation, "chill out"));
        taskSet.add(new Task("EveryDayTask", Task.Type.personal, LocalDate.of(2023, 02, 02), RepeatPeriod.DailyTask, ""));
        taskSet.add(new Task("EveryMonthTask", Task.Type.personal, LocalDate.of(2023, 03, 01), RepeatPeriod.DailyTask, "03 day of every month"));
        taskSet.forEach(System.out::println);
    }

    private static void showMainMenu() {
        System.out.println("\nDaily planner menu" +
                "\n1 - show tasks at date" +
                "\n2 - add task" +
                "\n3 - delete task" +
                "\n4 - show all tasks" +
                "\n5 - restore removed tasks" +
                "\n0 - exit planner");
        System.out.println();
    }

    public static int startMenu() throws IncorrectArgumentException, TaskNotFoundException {
        showMainMenu();
        int menuNumber = InputValues.scanInt(1, 7, "select a menu number (0 for exit planner)");
        switch (menuNumber) {
            case 0: {
                System.err.println("Selected 0 - exit planner");
                break;
            }
            case 1: {
                System.err.println("Selected 1 - show tasks at date");
                LocalDate showAtDate = InputValues.scanDate("");
                System.out.println(showAtDate);
                showAllAtDate(showAtDate);
                break;
            }
            case 2: {
                System.err.println("Selected 2 - add task");
                break;
            }
            case 3: {
                System.out.println("Selected 3 - delete task by ID");
                if (removedTasks.isEmpty()) {
                    System.err.println("no tasks");
                } else {
                    inputIdForRemove();
                }
                break;
            }
            case 4: {
                System.out.println("Selected 4 - show all tasks");
                taskSet.forEach(System.out::println);
                break;
            }
            case 5: {
                System.out.println("Selected 5 - restore removed tasks by ID");
                if (removedTasks.isEmpty()) {
                    System.err.println("no removed tasks");
                } else {
                    inputIdForRestore();
                }
                ;
                break;
            }
        }
        return menuNumber;
    }

    private static void add(Task task) {
        System.out.println("Task addition ....");
        System.out.println();
    }

    private static void inputIdForRemove() throws TaskNotFoundException {
        taskSet.forEach(task -> System.out.println(task.toStringWithID()));
        int id = InputValues.scanID("Input ID for delete task (0 for exit)");
        if (id == 0) {
            System.out.println("Remove is cancelled");
        } else {
            try {
                int taskCounts = (int) taskSet
                        .stream()
                        .filter(t -> t.getId() == id)
                        .count();
                if (taskCounts > 0) {
                    Task taskForRemove = taskSet
                            .stream()
                            .filter(t -> t.getId() == id)
                            .findFirst()
                            .get();
                    taskSet.remove(taskForRemove);
                    removedTasks.add(taskForRemove);
                    System.out.println("Task ID " + id + " removed successfully");
                } else {
                    System.out.println("incorrect ID");
                }
            } catch (Exception e) {
                throw new TaskNotFoundException("task not found or incorrect ID");
            }
        }
    }

    private static void inputIdForRestore() throws TaskNotFoundException {
        removedTasks.forEach(task -> System.out.println(task.toStringWithID()));
        int id = InputValues.scanID("Input ID for restore task (0 for exit)");
        if (id == 0) {
            System.out.println("Restore is cancelled");
        } else {
            try {
                int taskCounts = (int) removedTasks
                        .stream()
                        .filter(t -> t.getId() == id)
                        .count();
                if (taskCounts > 0) {
                    Task taskForRestore = removedTasks
                            .stream()
                            .filter(t -> t.getId() == id)
                            .findFirst()
                            .get();
                    removedTasks.remove(taskForRestore);
                    taskSet.add(taskForRestore);
                    System.out.println("Task ID " + id + " restored successfully");
                } else {
                    System.out.println("incorrect ID");
                }
            } catch (Exception e) {
                throw new TaskNotFoundException("task not found or incorrect ID");
            }
        }
    }

    private static void showAllAtDate(LocalDate showDate) {
        System.out.println("Tasks list at date " + showDate);
        for (Task task : taskSet) {
            LocalDate dateTemp = task.getDateTime();
            if (dateTemp.isEqual(showDate)) {
                System.out.println("Today is planned task : " + task);
            } else if (dateTemp.isAfter(showDate)) {
//                System.out.println("Дата этой задачи еще не наступила, позже" + task);
            } else if (task.repeatPeriod == RepeatPeriod.DailyTask
                    || (task.repeatPeriod == RepeatPeriod.WeeklyTask && dateTemp.getDayOfWeek() == showDate.getDayOfWeek())
                    || (task.repeatPeriod == RepeatPeriod.Weekends && (dateTemp.getDayOfWeek() == showDate.getDayOfWeek() || dateTemp.plusDays(1).getDayOfWeek() == showDate.getDayOfWeek()))
                    || (task.repeatPeriod == RepeatPeriod.Vocation && (dateTemp.isAfter(showDate) && dateTemp.isBefore(showDate.plusDays(RepeatPeriod.Vocation.durationDays))))
            ) {
                System.out.println("Today is planned task : " + task);
            } else if (task.repeatPeriod == RepeatPeriod.YearlyTask
                    || task.repeatPeriod == RepeatPeriod.MonthlyTask) {
                while (dateTemp.isAfter(showDate)) {
                    if (dateTemp.isEqual(showDate)) {
                        System.out.println("Today is planned task : " + task);
                    } else if (task.repeatPeriod == RepeatPeriod.YearlyTask) {
                        dateTemp = dateTemp.plusYears(1);
                    } else if (task.repeatPeriod == RepeatPeriod.MonthlyTask) {
                        dateTemp = dateTemp.plusMonths(1);
                    } else if (dateTemp.isAfter(showDate)) {
                        System.out.println("Дата этой задачи еще не наступила, позже++++" + task);
                    }
                    ;
                }
                if (dateTemp.isEqual(showDate)) {
                    System.out.println("Today is planned task : " + task);
                }
            }
        }
    }
}


