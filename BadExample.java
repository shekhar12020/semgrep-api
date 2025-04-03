import java.sql.Statement;
import java.security.MessageDigest;

public class BadExample {

    // Triggers: large-method (too many lines)
    public void bigMethod(String a, String b, String c, String d, String e, String f) {
        // Triggers: TODO comment
        // TODO: fix this loop later
        for (int i = 0; i < 5; i++) {
            // Triggers: magic number
            if (i == 42) {
                System.out.println("Magic number detected!"); // Triggers: System.out.println
            }
        }

        try {
            int x = 10 / 0;
        } catch (ArithmeticException e) {
            // Triggers: empty catch
        }

        try {
            Thread.sleep(1000); // Triggers: Thread.sleep
        } catch (InterruptedException e) {
            e.printStackTrace(); // Triggers: error-without-logger
        }

        if (true) {
            if (true) {
                if (true) {
                    System.out.println("Deep nesting!"); // Triggers: deep nesting
                }
            }
        }
    }

    // Triggers: missing @Override (assuming it overrides from parent class)
    public String toString() {
        return "BadExample";
    }

    // Triggers: long parameter list
    public void tooManyParams(String a, String b, String c, String d, String e, String f, String g) {
        System.out.println("Too many params!");
    }

    // Triggers: static access to instance method
    public void printMsg() {
        System.out.println("Message");
    }
    public static void callInstance() {
        BadExample obj = new BadExample();
        BadExample.printMsg(); // Should be obj.printMsg()
    }

    // Triggers: architecture violation (controller -> repo)
    public void controllerAction() {
        Controller controller = new Controller();
        controller.repo.saveSomething(); // Triggers: controller-direct-repo
    }

    // Triggers: weak hash usage
    public void hashData() throws Exception {
        MessageDigest.getInstance("MD5");
    }
}

class Controller {
    Repository repo = new Repository();
}

class Repository {
    void saveSomething() {
        System.out.println("Saving...");
    }
}
