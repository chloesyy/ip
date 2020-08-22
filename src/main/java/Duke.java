import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(line);
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
