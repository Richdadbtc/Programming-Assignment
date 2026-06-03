import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clock class responsible for getting current date and time
 */
public class Clock {

    private volatile LocalDateTime currentTime;

    // Formatter for output
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

    // Method to update time continuously
    public void updateTime() {
        while (true) {
            currentTime = LocalDateTime.now();
            try {
                Thread.sleep(1000); // update every second
            } catch (InterruptedException e) {
                System.out.println("Update thread interrupted.");
            }
        }
    }

    // Method to display time
    public void displayTime() {
        while (true) {
            if (currentTime != null) {
                System.out.println("Current Time: " + formatter.format(currentTime));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Display thread interrupted.");
            }
        }
    }
}