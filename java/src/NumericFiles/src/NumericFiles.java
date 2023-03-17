package NumericFiles.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NumericFiles {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choise;
		String choiseStr;

		loop: while (true) {

			System.out.println("\n1) Create");
			System.out.println("2) Calculate");
			System.out.println("3) View");
			System.out.println("4) About");
			System.out.println("5) Exit");
			System.out.println("\nInput number from 1 till 5: ");
			
			choiseStr = sc.nextLine();
			
			try {
				choise = Integer.parseInt(choiseStr);
				if (choise < 1 || choise > 5) {
					throw new Exception();
				}
			}
			catch (Exception ex) {
				System.out.println("please, input number from 1 till 5");
				continue;
			}
			
            
			switch (choise) {
			case 1:
				create();
				break;
			case 2:
				calculate();
				break;
			case 3:
				view();
				break;
			case 4:
				about();
				break;
			case 5:
				break loop;
			}
		}

		sc.close();

	}

	public static void create() {
		
		String filename;
		System.out.print("\nInput file name: ");
		filename = sc.nextLine();
		
		int count;
		String inputStr;
		
		System.out.print("input count of numbers: ");
		try {
			inputStr = sc.nextLine();
			count = Integer.parseInt(inputStr);
		}
		catch (Exception ex){
			System.out.println("input-output error");
			return;
		}

		try {
			DataOutputStream out = new DataOutputStream(
					new FileOutputStream(filename));

			for (int i = 1; i <= count; i++) {
				System.out.print("input number: ");
				try {
					inputStr = sc.nextLine();
					int number = Integer.parseInt(inputStr);
					out.writeInt(number);
				}
				catch (Exception e) {
					System.out.println("input-output error");
					break;
				}
			}
			
			out.close();
		} 
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static void calculate() {
		String filename;
        DataInputStream in = null;
        int i = 0;
        int wipe = 0;
        ArrayList<Integer> file = new ArrayList<Integer>();

        System.out.print("\nInput file name: ");
        filename = sc.nextLine();

        System.out.print("\nInput number to wipe: ");
        try {
            wipe = Integer.parseInt(sc.nextLine());
        }
        catch (Exception ex) {
            System.out.println("input-output error");
            return;
        }

        try{
            in= new DataInputStream(new FileInputStream(filename));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        boolean EOF = false;
		while (!EOF) {
			try {
				i = in.readInt();
                if (i != wipe) {
                    file.add(i);
                }

			} 
			catch (EOFException e) {
				EOF = true;
			} 
			catch (IOException e) {
				System.out.println("bad file format, not all numbers has been read succesfully");
				break;
			}
		}
		
		try {
			in.close();
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}

		try {
			DataOutputStream out = new DataOutputStream(
					new FileOutputStream(filename));

			for (int index = 0; index <= file.size(); index++) {
				try {
                    int number = file.get(index);
					out.writeInt(number);
				}
				catch (Exception e) {
					System.out.println("input-output error");
					break;
				}
			}
			
			out.close();
		} 
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
        
		//System.out.println("\nhello from calculate\n");
	}

	public static void view() {
		String filename;
		DataInputStream in = null;
		int i = 0;

		System.out.print("\nInput file name: ");
		filename = sc.nextLine();
		
		try {
			in = new DataInputStream(new FileInputStream(filename));
		} 
		catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			return;
		}

		System.out.print("\nContent of file: ");
		boolean EOF = false;
		while (!EOF) {
			try {
				i = in.readInt();
				System.out.print(i + " ");
			} 
			catch (EOFException e) {
				EOF = true;
			} 
			catch (IOException e) {
				System.out.println("bad file format, not all numbers has been read succesfully");
				break;
			}
		}
		
		try {
			in.close();
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}

		System.out.println();
	}

	public static void about() {
		System.out.println("221RDB047 Lukas Pahomovs 14");
	}

}
