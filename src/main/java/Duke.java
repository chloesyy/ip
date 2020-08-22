import java.util.Scanner;

public class Duke {
    public static Task[] tasks = new Task[100];
    public static int count = 0;

    public static void printList() {
        for (int i = 0; i<count; i++) {
            int taskNum = i + 1;
            System.out.println(taskNum + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
        }
    }

    public static void markAsDone(String line) {
        int taskNum = Integer.parseInt(line.substring(5));
        if (taskNum > count) {
            System.out.println("Oh no! This is an invalid task number. :(");
            return;
        }
        tasks[taskNum-1].isDone = true;

        System.out.println("Good job! You've completed: ");
        System.out.println("    [" + tasks[taskNum-1].getStatusIcon() + "] " + tasks[taskNum-1].description);
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome! :D This is TaskThomas.");
        System.out.println("What are you doing today?");
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList();
            } else if (line.startsWith("done")) {
                markAsDone(line);
            } else {
                // Add task to tasks array
                tasks[count++] = new Task(line);
                System.out.println("added: " + tasks[count - 1].description);
            }
            line = in.nextLine();
        }
        System.out.println("Bye bye :( Hope to see you again soon!");
    }
}
