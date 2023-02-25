package Turism.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Turism {
    enum Vehicle {
        BUS, TRAIN, PLANE, BOAT
    }

    static class Ride {
        private int id;
        private String destination;
        private String date;
        private int period;
        private double price;
        private Vehicle type;

        public Ride(int id, String destination, String date, int period, double price, String type) {
            this.id = id;
            this.destination = destination;
            this.date = date;
            this.period = period;
            this.price = price;
            this.type = Vehicle.valueOf(type.toUpperCase());
        }

        @Override
        public String toString() {
            return id + ";" + destination + ";" + date + ";" + period
                    + ";" + price + ";" + type;
        }

        public String toFormattedString() {
            return String.format("%-4d%-21s %-11s %-6d %-8.2f %-7s", id, destination, date, period, price, type);
        }

    }

    public static void main(String[] args) {
        ArrayList<Ride> rides = new ArrayList<Ride>();
        rides = readFile(rides, "java/src/Turism/src/test/db.csv");
        Scanner consoleScanner = new Scanner(System.in);
        String input;
        String[] inputArray;
        int id;
        while (true) {
            input = consoleScanner.nextLine();
            if (input.equals("exit"))
                break;
            inputArray = input.split(" ");
            switch (inputArray[0]) {
                case "add":
                    rides = add(rides, inputArray[1]);
                    break;
                case "del":
                    id = Integer.parseInt(inputArray[1]);
                    if (del(rides, id) != null)
                        rides = del(rides, id);
                    break;
                case "print":
                    System.out.println("------------------------------------------------------------");
                    System.out.println("ID  City                 Date         Days     Price Vehicle");
                    System.out.println("------------------------------------------------------------");
                    for (Ride ride : rides) {
                        System.out.println(ride.toFormattedString());
                    }
                    break;
                case "edit":
                    rides = edit(rides, inputArray);
                    break;
                default:
                    System.out.println("wrong command");
                    break;
            }

        }
        consoleScanner.close();
    }

    private static ArrayList<Ride> edit(ArrayList<Ride> rides, String[] inputArray) {
        return null;
    }

    private static ArrayList<Ride> readFile(ArrayList<Ride> rides, String fileName) {
        File file = new File(fileName);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                rides = add(rides, line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        return rides;
    }

    private static ArrayList<Ride> del(ArrayList<Ride> rides, int id) {
        boolean found = false;
        for (int index = 0; index < rides.size(); index++) {
            if (rides.get(index).id == id) {
                rides.remove(index);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("wrong id");
            return null;
        }
        return rides;
    }

    private static ArrayList<Ride> add(ArrayList<Ride> rides, String inputArray) {
        String[] arguments = inputArray.split(";");
        if (!check(arguments))
            return rides;
        int id = Integer.parseInt(arguments[0]);
        String destination = arguments[1];
        String date = arguments[2];
        int period = Integer.parseInt(arguments[3]);
        double price = Double.parseDouble(arguments[4]);
        String type = arguments[5];
        Ride ride = new Ride(id, destination, date, period, price, type);
        rides.add(ride);
        return rides;
    }

    private static boolean check(String[] arguments) {
        if (arguments.length != 6) {
            System.out.println("wrong field count");
            return false;
        }
        if (!(arguments[0].matches("\\d{3}?"))) {
            System.out.println("wrong id");
            return false;
        }
        if (!(arguments[2].matches("\\d{2}+/\\d{2}+/\\d{4}+")||arguments[2].matches(""))) {
            System.out.println("wrong date");
            return false;
        } 
        if (!((arguments[3].matches("\\d++")&&Integer.parseInt(arguments[3])>0)||arguments[3].matches(""))) {
            System.out.println("wrong day count");
            return false;
        }
        try {
            Double.parseDouble(arguments[4]);
        } catch (Exception e) {
            System.out.println("wrong price");
            return false;
        }
        if (!(arguments[5].toUpperCase().matches("BUS|TRAIN|PLANE|BOAT"))) {
            System.out.println("wrong vehicle");
            return false;
        }

        return true;
    }
}
