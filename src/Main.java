public class Main {
    public static void main(String[] args) throws TaskNotFoundException, IncorrectArgumentException {
        System.out.println("Daily planner");

        TaskService.addTasksForExample();

        do {
        } while (TaskService.startMenu() != 0);
    }
}