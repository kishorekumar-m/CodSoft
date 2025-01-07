import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Quiz(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

public class JavaQuizGame {
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static boolean answered = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Quiz[] questions = {
            new Quiz("What is the size of an int in Java?", 
                     new String[]{"1. 2 bytes", "2. 4 bytes", "3. 8 bytes", "4. Depends on the platform"}, 
                     2),
            new Quiz("Which keyword is used to inherit a class in Java?", 
                     new String[]{"1. implement", "2. extends", "3. inherits", "4. super"}, 
                     2),
            new Quiz("Which method is the entry point of a Java program?", 
                     new String[]{"1. main()", "2. start()", "3. run()", "4. init()"}, 
                     1),
            new Quiz("Which of these is a valid declaration of a char in Java?", 
                     new String[]{"1. char ch = 'A';", "2. char ch = \"A\";", "3. char ch = A;", "4. char ch = 'AB';"}, 
                     1),
            new Quiz("What is the default value of a boolean variable in Java?", 
                     new String[]{"1. true", "2. false", "3. null", "4. 0"}, 
                     2)
        };

        System.out.println("Welcome to the Java Quiz Game!");
        System.out.println("You have 10 seconds to answer each question. Let's begin!");

        for (Quiz quiz : questions) {
            askQuestion(quiz, scanner);
        }

        System.out.println("\nQuiz Over!");
        System.out.println("Your final score: " + score + "/" + questions.length);
        scanner.close();
    }

    private static void askQuestion(Quiz quiz, Scanner scanner) {
        System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + quiz.getQuestion());
        for (String option : quiz.getOptions()) {
            System.out.println(option);
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up! Moving to the next question.");
                    answered = true; // Mark question as answered
                }
                timer.cancel();
            }
        }, 10000); // 10 seconds timer

        answered = false;
        int userAnswer = -1;

        while (!answered) {
            if (scanner.hasNextInt()) {
                userAnswer = scanner.nextInt();
                if (userAnswer >= 1 && userAnswer <= quiz.getOptions().length) {
                    answered = true;
                    if (userAnswer == quiz.getCorrectAnswer()) {
                        System.out.println("Correct!");
                        score++;
                    } else {
                        System.out.println("Wrong! The correct answer was option " + quiz.getCorrectAnswer() + ".");
                    }
                    timer.cancel();
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            } else {
                scanner.next(); // Clear invalid input
                System.out.println("Invalid input. Please enter a number corresponding to your choice.");
            }
        }

        currentQuestionIndex++;
    }
}
