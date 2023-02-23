
// 221RDB047 Lukas Pahomovs 14
import java.util.HashMap;
import java.util.Scanner;

public class DNA {
    static HashMap<Character, Byte> numberMap = new HashMap<Character, Byte>();
    static HashMap<Byte, Character> nukleotideMap = new HashMap<Byte, Character>();
    
    static void decompile(String[] arguments) {
        if (arguments.length - 2 != Byte.parseByte(arguments[1])) {
            System.out.print("wrong command format");
            return;
        }
        String output = String.format("%X", Byte.parseByte(arguments[2]));
        Byte[] values = new Byte[arguments.length - 2];
        values[0] = Byte.parseByte(arguments[2]);
        try {
            for (int index = 3; index < arguments.length; index++) {
                values[index - 2] = Byte.parseByte(arguments[index]);
                output = output + String.format(" %X", values[index - 2]);
            }
        } catch (Exception e) {
            System.out.print("wrong command format");
            return;
        }
        System.out.println(output);
        output = "";
        String part = "";
        Byte value = 0, number = 0, edge = 4;

        for (Byte index = 1; index < values.length; index++) {
            number = values[index];
            if (values[0] < output.length() + 4) {
                edge = (byte) (values[0] - output.length());
                number = (byte) (number >> 2 * edge);
            }
            while (part.length() < edge) {
                if (number < 0 && (edge % 2 != 0)) {
                    value = (byte) (number & 0b11);
                    value = (byte) (3 + value);
                } else {
                    value = (byte) (number & 0b11);
                }

                part = nukleotideMap.get(value) + part;
                number = (byte) (number >> 2);
            }
            output = output + part;
            part = "";
        }
        System.out.println(output);
        return;
}

    static void compile(String arguments) {
        for (char character : arguments.toCharArray()) {
            if (!numberMap.containsKey(Character.toUpperCase(character))) {
                System.out.print("wrong command format");
                return;
            }
        }
        Byte[] output = new Byte[(byte) Math.ceil(arguments.length() / 4.0)];
        for (int index = 0; index < output.length; index++) {
            output[index] = 0;
        }
        Byte bitIndex = 1, outputIndex = 0;
        output[outputIndex] = (byte) (output[outputIndex] + numberMap.get(arguments.charAt(0)));
        for (Byte charIndex = 1; charIndex < arguments.length(); charIndex++) {
            if (bitIndex > 3) {
                bitIndex = 0;
                outputIndex++;
            }
            output[outputIndex] = (byte) (output[outputIndex] << 2);
            output[outputIndex] = (byte) (output[outputIndex] + numberMap.get(arguments.charAt(charIndex)));
            bitIndex++;
        }
        while (bitIndex < 4) {
            output[outputIndex] = (byte) (output[outputIndex] << 2);
            bitIndex++;
        }

        String outputString = String.format("%X", arguments.length());
        for (int index = 0; index < output.length; index++) {
            outputString = outputString + String.format(" %X", output[index]);
        }
        System.out.println(outputString);
    }

    static void about () {
        System.out.println("This program is a DNA compiler/decompiler.");
        System.out.println("It can compile DNA sequences into hexodecimal format and decompile binary format into DNA sequences.");
        System.out.println("It was created by Lukas Pahomovs for the course of Computer Science at the Riga Tehnical University.");
    }

    public static void main(String[] args) {

        numberMap.put('A', (byte) 0b00);
        numberMap.put('C', (byte) 0b01);
        numberMap.put('G', (byte) 0b10);
        numberMap.put('T', (byte) 0b11);
        nukleotideMap.put((byte) 0b00, 'A');
        nukleotideMap.put((byte) 0b01, 'C');
        nukleotideMap.put((byte) 0b10, 'G');
        nukleotideMap.put((byte) 0b11, 'T');

        Scanner consoleInput = new Scanner(System.in);
        while (consoleInput.hasNext()) {

            String line = consoleInput.nextLine();
            String[] command = line.split(" ", -1);

            if (command[0].contains("decomp")) {
                decompile(command);
            } else if (command[0].contains("comp")) {
                compile(command[1]);
            } else if (command[0].contains("about")) {
                about();
            }else if (command[0].contains("exit")) {
                break;
            }
        }

        consoleInput.close();
    }
}