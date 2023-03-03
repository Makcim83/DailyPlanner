import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TaskService {
    static Set<Task> taskSet = new HashSet<>();
    static Set<Task> removedTasks = new HashSet<>();

    public static void addTasksForExample() {
        System.out.println("Some Tasks added to Planner (for example):");
        taskSet.add(new Task("New Year!!", Task.Type.personal, LocalDate.of(2023, 01, 01), RepeatPeriod.YearlyTask, "Santa comes soon!"));
        taskSet.add(new Task("Weekends", Task.Type.personal, LocalDate.of(2023, 01, 04), RepeatPeriod.Weekends, "Some Descriptions for Weekends"));
        taskSet.add(new Task("SomeWorkDay", Task.Type.work, LocalDate.of(2023, 02, 28), RepeatPeriod.WeeklyTask));
        taskSet.add(new Task("Happy Birthday", Task.Type.personal, LocalDate.of(2023, 11, 10), RepeatPeriod.YearlyTask, ""));
        taskSet.add(new Task("Some Work Day 3 after 3", Task.Type.work, LocalDate.of(2023, 02, 28), RepeatPeriod.schedule3after3, "Work time"));
        taskSet.forEach(System.out::println);
    }

    private static void showMainMenu() {
        System.out.println("\nDaily planner menu" +
                "\n1 - show tasks at date" +
                "\n2 - add task" +
                "\n3 - delete task" +
                "\n4 - show list of tasks at date" +
                "\n5 - add tasks in file" +
                "\n6 - show all tasks" +
                "\n7 - restore removed tasks" +
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
                break;
            }
            case 2: {
                System.err.println("Selected 2 - add task");
                break;
            }
            case 3: {
                System.out.println("Selected 3 - delete task by ID");
                inputIdForRemove();
                break;
            }
            case 4: {
                System.err.println("Selected 4 - show tasks at date");
                int year = InputValues.scanYear();
                int month = InputValues.scanMonth();
                int day = InputValues.scanDay(year, month);
                getAllByDate(year, month, day);
                break;
            }
            case 5: {
                System.err.println("Selected 5 - add tasks in file");
                break;
            }
            case 6: {
                System.out.println("Selected 6 - show all tasks");
                taskSet.forEach(System.out::println);
                break;
            }
            case 7: {
                System.out.println("Selected 7 - restore removed tasks by ID");
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

    private static void getAllByDate(int y, int m, int d) {
        System.out.println("Tasks list at date " + y + "-" + m + "-" + d);
        LocalDate showDate = LocalDate.of(y, m, d);
        System.err.println("List...." + showDate.toString());
        for (Task task : taskSet) {
            LocalDate taskDate = task.getDateTime();
            if (task.repeatPeriod.repeatD == 1) {
                System.out.println(task);
            } else if (task.repeatPeriod.repeatM == 1) {
                do {
                    taskDate.plusMonths(1);
                    if (taskDate == showDate) {
                        System.out.println(task);
                    }
                } while (taskDate.isBefore(showDate));
            } else if (task.repeatPeriod.repeatY == 1) {
                do {
                    taskDate.plusYears(1);
                    if (taskDate == showDate) {
                        System.out.println(task);
                    }
                } while (taskDate.isBefore(showDate));
            }
        }
    }
}

