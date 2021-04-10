/**
 ** Program Name: SearchTimeComparison
 ** Author: Zulhelmi bin Mohamad
 ** Date: April 9th, 2021
 ** Course: CPSC 1150
 ** Compiler:JDK 15.0.1
 **/

import java.util.Arrays;
import java.lang.Character;
import java.util.Scanner;

public class SearchTimeComparison{
	/**
	 ** SearchTimeComparison: Searches the average number of comparisons between linear and
     **                       binary search
	 **/
    public static void  main (String[] args) {

		//Asks user if they want key to be in list or not
		boolean keyInlist = isKeyInList();

        System.out.printf("\n%10s%-25s\n", " ", "Average Number of Comparisons");
        System.out.printf("%-10s%12s%20s\n", "n", "Linear Search", "Binary Search");
        System.out.println("-------------------------------------------");

        for (int size=10; size <1000000; size*=10) {
	
			//How many test cases for each instance
			final int TEST_CASE_NUM = 100;

			//Records the amount of comparisons for linear and binary
			double[] linearSearchTimeList = new double[TEST_CASE_NUM];
			double[] binSearchTimeList = new double[TEST_CASE_NUM];
			
			for(int i = 0; i < TEST_CASE_NUM; ++i){
				//Generates random integer of given size
				int [] list = genArray(size);

				int key;

				//Overloads the methods depending if user chooses if the key must be in array
				if (keyInlist)
					key = genKey(size, list);
				else
					key = genKey(size);

				
				Arrays.sort(list);

				linearSearchTimeList[i] = linearSearch(list, key);
				binSearchTimeList[i] = binSearch(list, key);
			}
			double avgLnS = avgSearch(TEST_CASE_NUM, linearSearchTimeList);
			double avgBS = avgSearch(TEST_CASE_NUM, binSearchTimeList);

            //Tabulates results
            System.out.printf("%-10d%-20.2f%-20.2f\n", size, avgLnS, avgBS);
        }
    }

    public static int linearSearch(int[] list, int key){
		
        for(int i = 0; i < list.length; ++i){
            if (key == list[i]){
				//The number of searches is the same as the ith + 1 iteration
                return i+1; 
            }
        }

		//If key is not in list, then returns the list length since 
		//it would search the whole thing
		return list.length;
    }
    
	public static int binSearch(int [] list, int key){
		//Used Binary Search code in D2L
		int first = 0; 
		int last = list.length-1;
		int mid;
		
		//counter
		int numComparison = 0;

		while (first <= last){
			//Increments at the start since any search must at least search 1
			numComparison += 1;

			mid = (first +last )/2;
			if (list[mid] == key){
				return numComparison;
			}
			else if (list[mid] < key) {
				first = mid + 1; 
			}
			else {
				last = mid - 1;
			}
		}
		return numComparison; 
	}

    public static int[] genArray(int size){
        int[] randomArray = new int[size];
        for(int i = 0; i < randomArray.length; ++i){
            //Generates an integer between [0,size]
            randomArray[i] = (int) (Math.random() * size + 1);
        }
        return randomArray;
    } 

	public static int genKey(int size){
		//genKey: Generates random key between 0~size
		return (int) ((Math.random() * size) + 1);
        }

	public static int genKey(int size, int[] list){
		//genKey: Overloaded function when a key must be in the list

		//Generates random index, checks it in the list, then assign the value to key
		int i = (int) (Math.random() * size);
		int key = list[i];
		return key;
	}

	public static double avgSearch(int testCaseNum, double[] searchTimeList){
		//avgSearch: Finds average search time given a searchTimeList		
		double sum = 0;
		for (int i = 0; i < testCaseNum; ++i){
			sum += searchTimeList[i];
		}
		return (sum/testCaseNum);
	}
	
	public static boolean isKeyInList(){
		//avgSearch: Asking user if they want the key in list or not
		Scanner input = new Scanner(System.in);
		String option = "";
		System.out.println("Do you want the key to definitely be in the list?");
			do{
				System.out.printf("Enter Y or N: ");
				option = input.nextLine();
				option = option.toUpperCase();
				if (!isInputValid(option))
					System.out.println("Invalid option, please try again");
			} while (!isInputValid(option));
		input.close();

		if (option.equals("Y"))
			return true;
		else
			return false;
	}

	public static boolean isInputValid(String input){
		
		//isInputValid: Checks if a user inputs "Y" or "N"
		//Copied from my StudentScoreFile java program

		if (input.length() != 1){
            return false;
        }
        Character inputChar= input.charAt(0);
        if (Character.isLetter(inputChar) == true){
			if(input.equals("Y") || input.equals("N"))
            	return true;
			else
				return false;
        }
        else{
            return false;
        }
	}
}