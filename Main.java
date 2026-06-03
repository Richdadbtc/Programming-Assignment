/**
 * Main class to run the clock application
 */
public class Main {
    public static void main(String[] args) {

        Clock clock = new Clock();

        // Thread for updating time
        Thread updateThread = new Thread(() -> {
            clock.updateTime();
        });

        // Thread for displaying time
        Thread displayThread = new Thread(() -> {
            clock.displayTime();
        });

        // Set thread priorities
        updateThread.setPriority(Thread.MIN_PRIORITY);   // Background task
        displayThread.setPriority(Thread.MAX_PRIORITY);  // High priority for display

        // Start threads
        updateThread.start();
        displayThread.start();
    }
}