package FileCompresser.src;

import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;

public class FileCompresser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String next = scanner.nextLine();
            switch (next) {
                case "comp":
                    compress(scanner);
                    break;
                case "decomp":
                    decompress(scanner);
                    break;
                case "size":
                    size(scanner);
                    break;
                case "equal":
                    equality(scanner);
                    break;
                case "about":
                    about();
                    break;
                case "exit":
                    exit(scanner);
                    break;

                default:
                    break;
            }
            // if (next.equals("comp")) compress();
            // else if (next.equals("decomp")) decompress();
            // else if (next.equals("size")) size();
            // else if (next.equals("equal")) equality();
            // else if (next.equals("about")) about();
            // else if (next.equals("exit")) {
            // scanner.close();
            // exit();
            // }
            // else System.out.println("invalid command");

        }

    }

    private static void exit(Scanner scanner) {
        scanner.close();
        System.exit(0);
    }

    private static void size(Scanner scanner) {
        System.out.println("file name:");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        System.out.println("size: " + file.length());
    }

    private static void equality(Scanner scanner) {
        System.out.println("first file name:");
        String firstFileName = scanner.nextLine();
        System.out.println("second file name:");
        String secondFileName = scanner.nextLine();

        try{

            File firstFile = new File(firstFileName);
            File secondFile = new File(secondFileName);
            if (firstFile.length() != secondFile.length()) {
                System.out.println("false");
                return;
            }

            String firstFileString = firstFile.toString();
            String secondFileString = secondFile.toString();
            if (firstFileString.equals(secondFileString)) {
                System.out.println("false");
                return;
            }

        } catch (Exception e) {
            System.out.println("an error occurred");
        }

        System.out.println("true");
    }

    private static void about() {
        System.out.println("221RDB047 Lukas Pahomovs 14. grupa");
        System.out.println("");
        // :TODO complete about
    }

    private static void decompress(Scanner scanner) {

        System.out.println("archive name:");
        String archiveName = scanner.nextLine();
        System.out.println("file name:");
        String fileName = scanner.nextLine();

        String archive = readFile(archiveName);
        if (archive.equals(""))
            return;

        // :TODO decompress

        String file = "";
        saveFile(file, fileName);

    }

    private static void compress(Scanner scanner) {
        System.out.println("source file name:");
        String fileName = scanner.nextLine();
        System.out.println("archive name:");
        String archiveName = scanner.nextLine();
        
        String file = readFile(fileName);
        if (file.equals(""))
            return;

        // :TODO compress

        String archive = "";
        saveFile(archive, archiveName);

    }

    private static String readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String contents = "";
            while (scanner.hasNextLine()) {
                contents += scanner.nextLine();
            }
            scanner.close();
            return contents;
        } catch (Exception e) {
            System.out.println("file not found");
            return "";
        }
    }

    private static void saveFile(String contents, String filename) {
        try {
            File file = new File(filename);
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file));
            writer.write(contents);
            writer.close();
        } catch (Exception e) {
            System.out.println("an error occurred");
        }
    }
}
