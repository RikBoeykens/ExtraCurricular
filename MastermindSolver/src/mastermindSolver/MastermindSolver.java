package mastermindSolver;
//***************************************************************************************
//	Lists.java			Author: Rik Boeykens
//
//	To check what lists can do
//***************************************************************************************

import java.util.ArrayList;
import java.util.Scanner;

public class MastermindSolver 
{

	public static void main(String[] args) 
	{
		//variables
		
		ArrayList<String[]> listS = new ArrayList<String[]>();
		
		Scanner scan = new Scanner (System.in);
		
		String [] strColours = {"Blue", "Red", "Yellow", "Green", "Purple", "Brown"};
		
		int place0, place1, place2, place3;
		place0=place1=place2=place3 = 0;
		
		String [] strGuess = {"Yellow", "Yellow", "Blue", "Blue"};
		
		int intOrange, intWhite;
		int intOrangeCheck, intWhiteCheck;
		
		int intCount = 0;
		
		int intCheck = 0;
		int intGuess = 0;
		
		//creating all the possible combinations
		
		while (place3<6)
		{
			//creates arrays of combinations of the 6 colours
			String[] strInput = new String[4];
			strInput[0] = strColours[place0];
			strInput[1] = strColours[place1];
			strInput[2] = strColours[place2];
			strInput[3] = strColours[place3];
			
			//adds them to the list
			listS.add(strInput);
			
			//adds up place0 (we're counting in 'hex')
			place0++;
			
			if (place0==6)
			{
				place0 = 0;
				place1++;
			}
			
			if (place1==6)
			{
				place1 = 0;
				place2++;
			}
			
			if (place2==6)
			{
				place2 = 0;
				place3++;
			}
		}
		
		//introduction
		System.out.println("This program will solve the game Mastermind by eliminating the impossible solutions."); 
		System.out.println("\nPlease start with input 'Yellow'-'Yellow'-'Blue'-'Blue'");

		//ask for input
		System.out.print("\nHow many orange pegs did you get? ");
		intOrange = scan.nextInt();
		
		System.out.print("How many white pegs did you get? ");
		intWhite = scan.nextInt();
		
		//start of the loop that calculates the next guess, asks to play it and takes input
		while (intOrange<4)
		{
			intCount = 0;
			//start of the loop that calculates the next guess, the loop goes through every array in listS (every possible option in Mastmind)
			while (intCount<listS.size())
			{
				intOrangeCheck = intWhiteCheck = 0;
				//checking orange pegs
				//to loop through strGuess and the current array from listS
				for (int x = 0; x<4 ; x++)
				{
					//checks for every place if the letter matches the letter in the same place on the solution
					if(strGuess[x].equals(listS.get(intCount)[x]))
					{
						intOrangeCheck++;		//adds one to the variable intRightPlace
					}
				}
			
				//checking white pegs
				for (int x = 0 ; x<6 ; x++)
				{			
					//to loop through strGuess and the current array to record the amount of times this colour occurs
					for(int y = 0 ; y<4 ; y++)
					{
						if (strColours[x].equals(strGuess[y]))
							intGuess++;								//intGuess increases every time one of the elements is equal to the colour being cycled through
						if (strColours[x].equals(listS.get(intCount)[y]))
							intCheck++;								//same for intCheck					
					}

					//minimum value between intGuess and intCheck (if there's 2 Blue's in the solution but 1 Blue in the guess you only get 1. 
					//if there's 1 Blue in the solution but 2 in the guess you still only get 1)
					intWhiteCheck+= Math.min(intGuess,intCheck);		

					intGuess = intCheck = 0; //puts intGuess and intGuess back to zero for the next colour
				}
				
				intWhiteCheck-=intOrangeCheck; //otherwise this would also count the characters that are on the right place

				//Now we know how many orange pegs and how many white pegs this combination has compared to the guess.
				//If this doesn't match the amount of orange and white pegs our guess has compared to the solution the current combination can't be the solution
				
				if(intOrangeCheck==intOrange&&intWhiteCheck==intWhite)
				{
					//if orange and white match we can check the next one. 
					intCount++;
					//This would also be the place to put extra calculations to refine the algorithm
				}
				else
				{
					//if they don't match we don't need it any more. We don't add up intCount because the next time in the loop we'll be on the same spot
					listS.remove(intCount);
				}
			}
			
			//To keep this first algorithm simple we'll just take the first solution found as the next guess
			for (int x = 0; x<4 ; x++)
			{
				strGuess[x] = listS.get(0)[x];
			}
			
			//output to show the next guess
			if (listS.size()==1)
			{
				System.out.print("You're nearly there, this should do the trick:");
			}
			else
			{
				System.out.println("There are " + listS.size() + " solutions left. ");
				System.out.print("Please try the following combination:");
			}
			for (int x=0; x<4;x++)
			{
				System.out.print(" '" + strGuess[x] + "'");
			}
			
			//ask for input
			System.out.print("\nHow many orange pegs did you get? ");
			intOrange = scan.nextInt();
			
			System.out.print("How many white pegs did you get? ");
			intWhite = scan.nextInt();
			
			//and it starts again...
		}
		System.out.println("Glad to be of service!");
	}

}
