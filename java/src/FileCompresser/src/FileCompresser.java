package FileCompresser.src;

import java.util.Scanner;

public class FileCompresser {
    public static void main(String[] args) {
        while (true) {
            menu();
        }
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.nextLine();
        if (next.equals("comp")) compress();
        else if (next.equals("decomp")) decompress();
        else if (next.equals("size")) size();
        else if (next.equals("equal")) equality();
        else if (next.equals("about")) about();
        else if (next.equals("exit")) exit();
        else System.out.println("invalid command");  
        scanner.close();      
    }

    private static void exit() {
        saveFile();
        System.exit(0);
    }

    private static void saveFile() {
        // :TODO saveFile
    }

    private static void size() {
        // :TODO size
    }

    private static void equality() {
        // :TODO equality
    }

    private static void about() {
        System.out.println("221RDB047 Lukas Pahomovs 14. grupa");
        System.out.println("");
        // :TODO complete about
    }

    private static void decompress() {
        // :TODO decompress
    }

    private static void compress() {
        // :TODO compress
    }
}
