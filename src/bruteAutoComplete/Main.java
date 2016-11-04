package bruteAutoComplete;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	private Scanner stringInput;
	private BruteAutoComplete bruteAutoComplete;

	public static void main(String[] args) {
		new Main();
	}

	/**
	 * Sets up the scanner. Read in a string to the file. Create a new
	 * bruteAutoComplete instance print the menu and allows users to interact
	 * with bruteforce methods
	 */
	public Main() {
		stringInput = new Scanner(System.in);
		System.out.print("Enter path to file: ");
		try {
			bruteAutoComplete = new BruteAutoComplete(getStringInput());
			menu();
		} catch (IllegalArgumentException | FileNotFoundException | NullPointerException e) {
			e.printStackTrace();
		}
		stringInput.close();
	}

	/**
	 * prints the menu to the console. Uses a switch statement to call the method the user chooses.
	 */
	private void menu() {

		int choice;

		do {

			System.out.println("\nBrute Force Auto Complete");
			System.out.println("-------------------------\n");

			System.out.println("1) Get the Weight of a word.");
			System.out.println("2) Search for highest weight of a prefix.");
			System.out.println("3) Search for a list of words with a prefix.");
			System.out.println("0) Exit.");
			System.out.print("==> ");
			choice = getInput();

			switch (choice) {
			case 1:
				getWeight();
				break;
			case 2:
				searchBestMatch();
				break;
			case 3:
				searchListMatch();
				break;
			case 0:
				System.out.println("Closing");
				break;
			default:
				break;
			}
		} while (choice != 0);

	}

	/**
	 * Returns the users choice at the main menu.
	 * @return (Int) users choice
	 */
	private int getInput() {
		int temp = stringInput.nextInt();
		stringInput.nextLine();
		return temp;
	}

	/**
	 * 
	 * @return (String) path to file, or prefix of a word
	 */
	private String getStringInput() {
		return stringInput.nextLine();
	}

	/**
	 * Gets the weight of a prefix.
	 */
	private void getWeight() {
		try {
			System.out.print("\nEnter prefix or word to be searched: ");
			String temp = getStringInput();
			System.out.println("String: " + temp +", Weight: "+ bruteAutoComplete.weightOf(temp));
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Allows the user to find the best matching string of a prefix.
	 */
	private void searchBestMatch() {
		try {
			System.out.print("\nEnter prefix or word to be searched: ");
			System.out.println(bruteAutoComplete.bestMatch(getStringInput()));
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Allows the user to find a list of strings with a prefix of their choice.
	 * Also allows the users to specify the size of the list
	 */
	private void searchListMatch() {
		try {
			System.out.print("\nEnter prefix or word to be searched: ");
			String prefix = getStringInput();
			System.out.print("\nEnter the number (Int) of words to print: ");
			System.out.println(bruteAutoComplete.matches(prefix, getInput()));
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
}
