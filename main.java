
// 221RDB047 Lukas Pahomovs 14
import java.util.HashMap;
import java.util.Scanner;

class Main {
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
            if (values[0]<output.length()+4) {
                edge = (byte) (values[0] - output.length());
                number = (byte) (number >> 2 * edge);
            }
            while (part.length() < edge) {
                if (number<0&&(edge %2!=0)) {
                    value = (byte) (number & 0b11);
                    value=(byte) (3+value);
                }else{
                    value = (byte) (number & 0b11);
                }
                
                part = nukleotideMap.get(value) + part;
                number = (byte) (number >> 2);
            }
            output = output + part;
            part = "";
        }
        System.out.println(output);
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
            } else if (command[0].contains("exit")) {
                break;
            }
        }

        consoleInput.close();
    }
}