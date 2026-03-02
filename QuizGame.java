import java.util.Scanner;
public class QuizGame {

    public static void main(String[] args) {

        // Scanner object for user input
        Scanner input = new Scanner(System.in);

        int score = 0;              
        int totalQuestions = 5;     
        char answer;               

        System.out.println("Welcome to the Java Quiz Game!");
        System.out.println("Choose the correct option: A, B, C, or D\n");

        // ---------------- Question 1 ----------------
        System.out.println("1. Which data type is used to store whole numbers in Java?");
        System.out.println("A. double");
        System.out.println("B. int");
        System.out.println("C. char");
        System.out.println("D. boolean");
        System.out.print("Your answer: ");
        answer = input.next().toUpperCase().charAt(0);

        if (answer == 'A' || answer == 'B' || answer == 'C' || answer == 'D') {
            switch (answer) {
                case 'B':
                    score++;
                    break;
            }
        } else {
            System.out.println("Invalid input. Question skipped.");
        }

        // ---------------- Question 2 ----------------
        System.out.println("\n2. Which keyword is used to create an object in Java?");
        System.out.println("A. class");
        System.out.println("B. void");
        System.out.println("C. new");
        System.out.println("D. static");
        System.out.print("Your answer: ");
        answer = input.next().toUpperCase().charAt(0);

        if (answer == 'A' || answer == 'B' || answer == 'C' || answer == 'D') {
            switch (answer) {
                case 'C':
                    score++;
                    break;
            }
        } else {
            System.out.println("Invalid input. Question skipped.");
        }

        // ---------------- Question 3 ----------------
        System.out.println("\n3. Which symbol is used for single-line comments in Java?");
        System.out.println("A. /* */");
        System.out.println("B. //");
        System.out.println("C. #");
        System.out.println("D. <!-- -->");
        System.out.print("Your answer: ");
        answer = input.next().toUpperCase().charAt(0);

        if (answer == 'A' || answer == 'B' || answer == 'C' || answer == 'D') {
            switch (answer) {
                case 'B':
                    score++;
                    break;
            }
        } else {
            System.out.println("Invalid input. Question skipped.");
        }

        // ---------------- Question 4 ----------------
        System.out.println("\n4. Which method is the entry point of a Java program?");
        System.out.println("A. start()");
        System.out.println("B. run()");
        System.out.println("C. main()");
        System.out.println("D. execute()");
        System.out.print("Your answer: ");
        answer = input.next().toUpperCase().charAt(0);

        if (answer == 'A' || answer == 'B' || answer == 'C' || answer == 'D') {
            switch (answer) {
                case 'C':
                    score++;
                    break;
            }
        } else {
            System.out.println("Invalid input. Question skipped.");
        }

        // ---------------- Question 5 ----------------
        System.out.println("\n5. Which operator is used to compare two values?");
        System.out.println("A. =");
        System.out.println("B. !=");
        System.out.println("C. ==");
        System.out.println("D. +");
        System.out.print("Your answer: ");
        answer = input.next().toUpperCase().charAt(0);

        if (answer == 'A' || answer == 'B' || answer == 'C' || answer == 'D') {
            switch (answer) {
                case 'C':
                    score++;
                    break;
            }
        } else {
            System.out.println("Invalid input. Question skipped.");
        }

        // ---------------- Final Score ----------------
        double percentage = (score / (double) totalQuestions) * 100;

        System.out.println("\nQuiz Completed!");
        System.out.println("Correct Answers: " + score + " out of " + totalQuestions);
        System.out.println("Final Score: " + percentage + "%");

        // Close scanner
        input.close();
    }
}
