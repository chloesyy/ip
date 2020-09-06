import java.util.Scanner;

public class Duke {
    public static final int MAX_TASKS = 100;
    public static Task[] tasks = new Task[MAX_TASKS];
    public static int count = 0;

    /*
    MAIN METHOD
     */
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        printWelcomeMessage();
        line = in.nextLine();

        while (!line.equals("bye")) {
            handleLine(line);
            line = in.nextLine();
        }

        printExitMessage();
    }

    public static void printWelcomeMessage() {
        System.out.println("Welcome! :D This is TaskThomas.");
        System.out.println("What are you doing today?");
    }

    public static void printExitMessage() {
        System.out.println("Bye bye :( Hope to see you again soon!");
    }

    public static void handleLine(String line) {
        if (line.equals("list")) {
            printList();
        } else if (line.startsWith("done")) {
            markAsDone(line);
        } else {
            addTask(line);
        }
    }

    /*
    Prints the list of tasks and indicates whether they are done.
    */
    public static void printList() {
        System.out.println("These are the tasks you have now!");
        for (int i = 0; i<count; i++) {
            int taskNum = i + 1;
            System.out.print(taskNum + ".");
            tasks[i].printTask();
        }
    }

    /*
    Marks a task as done.
     */
    public static void markAsDone(String line) {
        int taskNum = Integer.parseInt(line.substring(5));
        if (taskNum > count) {
            System.out.println("Oh no! This is an invalid task number. :(");
            return;
        }
        tasks[taskNum-1].isDone = true;

        System.out.println("Good job! You've completed: ");
        tasks[taskNum-1].printTask();
    }

    /*
    Adds a task to the list.
     */
    public static void addTask(String line) {
        if (line.startsWith("todo")) {
            try {
                addTodo(line.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Ohno! The todo description cannot be empty :(");
                return;
            }
        } else if (line.startsWith("deadline")) {
            try {
                addDeadline(line.substring(9));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Ohno! The deadline needs a description and a /by :(");
                return;
            }
        } else if (line.startsWith("event")) {
            try {
                addEvent(line.substring(6));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Ohno! The event needs a description and an /at :(");
                return;
            }
        }

        System.out.println("Okay! I added:");
        tasks[count-1].printTask();
        System.out.println("Now you have " + count + " tasks in the list!");
    }

    /*
    Adds either a to-do task, deadline task or event task to the list.
     */
    public static void addTodo(String line) {
        tasks[count++] = new Todo(line);
    }

    public static void addDeadline(String line) {
        String[] descriptionAndBy = line.split(" /by ");
        tasks[count++] = new Deadline(descriptionAndBy[0], descriptionAndBy[1]);
    }

    public static void addEvent(String line) {
        String[] descriptionAndAt = line.split(" /at ");
        tasks[count++] = new Event(descriptionAndAt[0], descriptionAndAt[1]);
    }
}
