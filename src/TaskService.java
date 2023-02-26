import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public class TaskService {
    Map<Integer, Task> taskMap;
    Collection<Task> removedTasks;

    public static int startMenu() {
        showMainMenu();
        int menuNumber = InputValues.scanInt(1, 5, "select a menu number (0 for exit planner)");
        switch (menuNumber) {
            case 1: {
                System.out.println("Selected 1 - show tasks at date");

                break;
            }
            case 2: {
                System.out.println("Selected 2 - show tasks at date");
                break;
            }
            case 3: {
                System.out.println("Selected 3 - show list of tasks");
                break;
            }
            case 4: {
                System.out.println("Selected 4 - show tasks at date");
                getAllByDate(LocalDate.now());
                break;
            }
            case 5: {
                System.out.println("Selected 5 - add tasks in file");
                break;
            }
            case 0: {
                System.out.println("Selected 0 - exit planner");
                break;
            }
        }
        return menuNumber;
    }

    private static void showMainMenu() {
        System.out.println(
                "\nDaily planner menu" +
                        "\n1 - show tasks at date" +
                        "\n2 - add task" +
                        "\n3 - remove task" +
                        "\n4 - show list of tasks" +
                        "\n5 - add tasks in file" +
                        "\n0 - exit planner");
        System.out.println();
    }


    private static void add(Task task) {
        System.out.println("Task addition ....");
        System.out.println();
    }

    private static void inputIdForRemove() throws TaskNotFoundException {
        int id = InputValues.scanID("Input ID for delete task (0 for exit)");
        if (id == 0) {
            System.out.println("Deleted is cancelled");
        } else {
            try {
                remove(id);
            } catch (Exception e) {
                throw new TaskNotFoundException("task not found or incorrect ID");
            }
        }
    }

    private static void remove(int id) {
        System.out.println("Task ID " + id + " deleted successfully");
    }

    private static void getAllByDate(LocalDate date) {
        System.out.println("Tasks list at date " + date);
        System.out.println("List....");
    }
}
