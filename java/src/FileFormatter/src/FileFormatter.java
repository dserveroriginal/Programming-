package FileFormatter.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileFormatter {
    public static void main(String[] arguments) {
        System.out.println("221RDB047 Lukas Pahomovs");
        System.out.println("Enter file name:");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        scanner.close();
        System.out.println(fileName);
        try {
            fileFormatting(fileName);
        } catch (Exception e) {
            System.out.println(e);  
        }
    }

    private static void fileFormatting(String fileName) throws IOException {
        String[] file = readFile(fileName);
        String[] formattedFile = new String[file.length];
        int length=0;
        for (int index = 0; index < file.length; index++) {
            length=file[index].length()>length?file[index].length():length;
        }
        for (int index = 0; index < file.length; index++) {
            formattedFile[index] = formatLine(file[index],length);
        }
        writeFile(fileName, formattedFile);
    }

    private static void writeFile(String fileName, String[] formattedFile) throws IOException {
        FileWriter file = new FileWriter(fileName);
        int lenght = formattedFile.length;
        for (int index = 0; index < lenght; index++) {
            String line = formattedFile[index];
            file.write(line);
            file.write(System.lineSeparator());
            if ((index % 2 != 0)&&(index!=lenght-1)) file.write(System.lineSeparator());
        }
        file.close();
    }

    private static String[] readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);
        ArrayList<String> fileContent = new ArrayList<String>();
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            if (!line.trim().isEmpty()) fileContent.add(line);
        }
        fileScanner.close();
        return fileContent.toArray(new String[fileContent.size()]);
    }

    private static String formatLine(String string, int length) {
        string = string.trim();
        string=Character.toUpperCase(string.charAt(0))+string.substring(1);
        int spaces = length - string.length();
        return  " ".repeat(spaces/2) + string + " ".repeat(spaces/2) + " ".repeat(spaces%2);
    }
    
}
