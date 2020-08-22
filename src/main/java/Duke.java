import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        String[] tasks = new String[100];
        int count = 0;

        System.out.println("Welcome! :D This is TaskThomas.");
        System.out.println("What are you doing today?");
        line = in.nextLine();

        while (!line.equals("bye")) {
            // If user inputs "list", print out the list of tasks
            if (line.equals("list")) {
                for (int i = 0; i<count; i++) {
                    int taskNum = i + 1;
                    System.out.println(taskNum + ": " + tasks[i]);
                }
            } else {
                tasks[count++] = line;
                System.out.println("added: " + line);
            }
            line = in.nextLine();
        }
        System.out.println("Bye :( Hope to see you again soon!");
    }
}
