package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class BruteAutoComplete implements AutoComplete {

	private HashMap<String, Integer> dictionary;

	public BruteAutoComplete(String file) throws IllegalArgumentException, FileNotFoundException {
		File usersFile = new File(file);
		Scanner inUsers = new Scanner(usersFile);
		String delims = "[ ]";// each field in the file is separated(delimited)
								// by a space.
		while (inUsers.hasNextLine()) {
			// get user and rating from data source
			String userDetails = inUsers.nextLine().trim();
			// parse user details string
			String[] userTokens = userDetails.split(delims);
			if (!userTokens[0].equals("﻿")) {
				// System.out.println("UserID: " + userTokens[0] + ",First Name:" + userTokens[1]);
				for (String string: userTokens) {
					System.out.println(string);
				}
			} else {
				continue;
			}
			// output user data to console.
			/*	if (userTokens.length == 2) {

			} else {
				inUsers.close();
				// throw new Exception("Invalid member length: " + userTokens.length);
			} */
		}
		inUsers.close();
		// this.dictionary = ;
	}

	@Override
	public double weightOf(String term) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String bestMatch(String prefix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<String> matches(String prefix, int k) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String args[]) {
		try {
			new BruteAutoComplete(".\\TextFile\\wiktionary.txt");
		} catch (IllegalArgumentException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
