package pack1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FIFO {
	public static void main(String[] args) {

		// Variables
		int pageFrame = 0;
		int pageFaultCounter = 0;
		int pageHitCounter = 0;
		int counter = 1;
		byte lengthOfReferenceString = 0;
		int hit = -99;

		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> referenceStringList = new ArrayList<Integer>();

		System.out.println("Enter length of Reference String: ");
		lengthOfReferenceString = scan.nextByte();

		/*
		 * condition: length of reference string must be 5 - 15 error kapag ang length
		 * of reference string ay < 5 || > 15
		 */
		if (lengthOfReferenceString < 5 || lengthOfReferenceString > 15) { // yung part na to pang check lang kung tama
																			// ba yung length ng reference string.
			System.out.println("\n\n\n");
			System.out.println("Error - Length of Reference String must be 5-15");
			restartProgram();
		}

		/*
		 * yung part na to yung kumukuha ng reference string sa user input
		 */
		byte a = 0;
		while (true) {
			System.out.println("Enter Reference String: ");
			referenceStringList.add(scan.nextInt());
			System.out.println(referenceStringList);
			a++;

			if (a == lengthOfReferenceString) {
				// c = false;
				break;
			}
		}

		/*
		 * input para sa Page Frame
		 */
		System.out.println("Enter number of Page Frame/s: ");
		pageFrame = scan.nextInt();
		if (pageFrame <= 0) {
			System.out.println("Error - Page Frame must be greater than 0");
			restartProgram();
		}

		HashMap<Integer, ArrayList<Integer>> row = new HashMap<>();
		// initialize HashMap
		for (int i = 0; i < pageFrame; i++) {
			row.put(i + 1, new ArrayList<>());
		}
		ArrayList<Integer> currentQ = new ArrayList<Integer>();
		ArrayList<Integer> previousQ = new ArrayList<Integer>();
		ArrayList<Integer> currentQTemplate = new ArrayList<Integer>();
		ArrayList<Integer> pageFaultList = new ArrayList<Integer>();
		/*
		 * Process
		 */

		int mLoop = referenceStringList.size();

		for (int i = 0; i < mLoop; i++) {
			Object currentRefString = referenceStringList.get(i);
			/*
			 * 
			 */
			if (currentQ.size() < pageFrame) { 
				if (currentQ.contains(currentRefString)) {
					// page hit - may parehas na int
					pageHitCounter++;
					for (int j = 0; j < currentQ.size(); j++) {
						currentQTemplate.add(hit);
					}

					for (int j = 0; j < currentQ.size(); j++) {
						row.get(j + 1).add(currentQ.get(j));
						previousQ.add((int) currentRefString);
						pageFaultList.add(-99);
					}
				} else {
					//page hit pero ang technique i add lng natin wala paring scanning
					pageFaultCounter++;
					currentQ.add((int) currentRefString);
					previousQ.add((int) currentRefString);
					for (int j = 0; j < currentQ.size(); j++) {
						row.get(j + 1).add(currentQ.get(j));
					}
				}
			} else {
				if (currentQ.contains(currentRefString)) {
					// page hit - may parehas na int
					pageHitCounter++;
					for (int j = 0; j < currentQ.size(); j++) {
						currentQTemplate.add(hit);
					}

					for (int j = 0; j < currentQ.size(); j++) {
						row.get(j + 1).add(currentQ.get(j));
						previousQ.add((int) currentRefString);
						pageFaultList.add(-99);
					}
				} else {
					// page fault
					pageFaultCounter++;
					pageFaultList.add(counter);
					counter++;

					int forIndex = previousQ.size() - previousQ.size();
					Object remove = previousQ.get(forIndex);
					int indexUpdate = currentQ.indexOf(remove);
					currentQ.remove(remove);
					currentQ.add(indexUpdate, (int) currentRefString);

					previousQ.remove(forIndex);
					previousQ.add((int) currentRefString);
					// pageFaultCounter++;

					for (int j = 0; j < currentQ.size(); j++) {
						row.get(j + 1).add(currentQ.get(j));
					}

				}

			}

		}
		
		// For Printing
		for (Integer item : referenceStringList) {
			System.out.print(item + " ");
		}
		System.out.println();

		// for printing rows
		for (int i = 0; i < pageFrame; i++) {
			ArrayList<String> spaceContainer = new ArrayList<String>();
			String space = " ";
			ArrayList<String> Converted = toStringArray(row.get(i + 1));
			int numberOfSpace = referenceStringList.size() - Converted.size();
			for (int j = 0; j < numberOfSpace; j++) {
				spaceContainer.add(space);
			}
			spaceContainer.addAll(Converted);

			for (String toPrint : spaceContainer) {
				System.out.print(toPrint + " ");
			}

			System.out.println();

		}
		System.out.println("Number of Page Faults :" + pageFaultCounter);
	}

	public static ArrayList<String> toStringArray(ArrayList<Integer> currentRow) {
		ArrayList<String> container = new ArrayList<>();
		for (Integer item : currentRow) {
			if (item < 0) {
				container.add(" ");
			} else {
				container.add(String.valueOf(item));
			}
		}
		return container;
	}
	public static void restartProgram() { // this method restarts the program, restart kung nag error.
		main(null);
	}

}
