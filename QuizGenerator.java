import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class QuizGenerator {
    private static HashMap<String, ArrayList<Question>> quizzes = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nQuiz Generator");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add question to a quiz");
            System.out.println("3. Take a quiz");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createQuiz(scanner);
                    break;
                case 2:
                    addQuestion(scanner);
                    break;
                case 3:
                    takeQuiz(scanner);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void createQuiz(Scanner scanner) {
        System.out.print("Enter the name of the new quiz: ");
        String quizName = scanner.nextLine();
        quizzes.put(quizName, new ArrayList<>());
        System.out.println("Quiz '" + quizName + "' created.");
    }

    private static void addQuestion(Scanner scanner) {
        System.out.print("Enter the name of the quiz to add a question to: ");
        String quizName = scanner.nextLine();
        if (quizzes.containsKey(quizName)) {
            System.out.print("Enter the question: ");
            String questionText = scanner.nextLine();
            ArrayList<String> options = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                System.out.print("Enter option " + (i + 1) + ": ");
                options.add(scanner.nextLine());
            }
            System.out.print("Enter the correct option number (1-4): ");
            int correctAnswer = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            quizzes.get(quizName).add(new Question(questionText, options, correctAnswer));
            System.out.println("Question added.");
        } else {
            System.out.println("Quiz not found.");
        }
    }

    private static void takeQuiz(Scanner scanner) {
        System.out.print("Enter the name of the quiz you want to take: ");
        String quizName = scanner.nextLine();
        if (quizzes.containsKey(quizName)) {
            int score = 0;
            ArrayList<Question> questions = quizzes.get(quizName);
            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);
                System.out.println("\nQuestion " + (i + 1) + ": " + q.getQuestionText());
                ArrayList<String> options = q.getOptions();
                for (int j = 0; j < options.size(); j++) {
                    System.out.println((j + 1) + ". " + options.get(j));
                }
                System.out.print("Enter your answer (1-4): ");
                int answer = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (answer == q.getCorrectAnswer()) {
                    score++;
                }
            }
            System.out.println("\nYou scored " + score + "/" + questions.size());
        } else {
            System.out.println("Quiz not found.");
        }
    }
}

class Question {
    private String questionText;
    private ArrayList<String> options;
    private int correctAnswer;

    public Question(String questionText, ArrayList<String> options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

