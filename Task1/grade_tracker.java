import java.util.Scanner;

public class grade_tracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Student Grade Tracker");

        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();

        String[] names = new String[numStudents];
        double[] averages = new double[numStudents];
        double totalClass = 0;

        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;
        String topper = "", last = "";

        for (int i = 0; i < numStudents; i++) {
            scanner.nextLine(); // consume newline
            System.out.print("\nEnter name of student " + (i + 1) + ": ");
            names[i] = scanner.nextLine();

            System.out.print("Enter number of subjects for " + names[i] + ": ");
            int subjects = scanner.nextInt();

            double total = 0;
            for (int j = 0; j < subjects; j++) {
                System.out.print("Enter marks for subject " + (j + 1) + ": ");
                total += scanner.nextDouble();
            }

            double avg = total / subjects;
            averages[i] = avg;
            totalClass += avg;

            if (avg > highest) {
                highest = avg;
                topper = names[i];
            }

            if (avg < lowest) {
                lowest = avg;
                last = names[i];
            }
        }

        System.out.println("\n--- Student Report ---");
        for (int i = 0; i < numStudents; i++) {
            System.out.printf("%s : Average = %.2f\n", names[i], averages[i]);
        }

        double classAverage = totalClass / numStudents;

        System.out.printf("\nClass Average: %.2f\n", classAverage);
        System.out.printf("Highest Scorer: %s (%.2f)\n", topper, highest);
        System.out.printf("Lowest Scorer: %s (%.2f)\n", last, lowest);

        scanner.close();
    }
}
