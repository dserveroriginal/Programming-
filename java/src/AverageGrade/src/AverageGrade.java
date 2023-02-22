package AverageGrade.src;
// 221RDB047 Lukas Pahomovs 14

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AverageGrade {

    static List<String> students = new ArrayList<String>();

    static Scanner scanner(File file) {
        try {
            Scanner scanner = new Scanner(file);
            return scanner;
        } catch (Exception e) {
            System.out.println("File not found!");
            return null;
        }
    }

    static void check(String[] student) {

        int average = 0, low = 0;
        for (int index = 2; index < student.length; index++) {
            average += Integer.parseInt(student[index]);
        }
        average /= student.length - 2;
        if (average <= 5) {
            for (int index = 2; index < student.length; index++) {
                if (Integer.parseInt(student[index]) < 4) {
                    low++;
                }
            }
            students.add(student[0] + " " + student[1] + " " + low);
        }
    }

    public static void main(String[] args) {
        System.out.println("221RDB047 Lukas Pahomovs 14. grupa");
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("input file name: ");
        String fileName = consoleScanner.nextLine();
        consoleScanner.close();
        if (fileName == "") {
            fileName = "java/src/AverageGrade/src/tests/Students.txt";
        }

        File file = new File(fileName);
        Scanner fileScanner = scanner(file);
        if (fileScanner == null)
            return;

        while (fileScanner.hasNextLine()) {
            String[] student = fileScanner.nextLine().split(" ");
            check(student);
        }

        System.out.println("result:");
        for (String student : students) {
            System.out.println(student);
        }

        fileScanner.close();
    }
}
