// Merge codes here

import java.util.LinkedList;
import java.lang.StringBuilder;

public class MasterNode {

	// Name
	// Typo Type 1
	

	// Name
	// Typo Type 2


	// Jay Moon
	// Typo Type 3
	public void getTypo3(String input) {

		// Store generated typos here
		LinkedList<String> typos = new LinkedList<String>();

		// Iterate through input string and generate typos
		for(int i = 0; i < input.length()-1; i++) {
			char currChar = input.charAt(i);
			char nextChar = input.charAt(i+1);

			// If two characters are not same
			if(currChar != nextChar) {
				// Initialize new string to swap characters
				StringBuilder sb = new StringBuilder(input);
				sb.setCharAt(i, nextChar);
				sb.setCharAt(i+1, currChar);

				// Add new typo to the list
				typos.add(sb.toString());
			}

			// Else do not do anything and continue loop

		}

		// Name
		// Typo Type 4


		// Name
		// Typo Type 5

	
}