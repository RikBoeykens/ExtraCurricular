package mastermind;
//**********************************************************************
//	MasterMind.java			Author: Rik Boeykens
//
//	A game made with a combination of skills learned so far
//**********************************************************************

import java.util.*;

public class MasterMind 
{
	
	public static void main(String[] args) 
	{
		//variables
		Scanner scan = new Scanner (System.in);
		Random generator = new Random();
		
		String strABCDEF[] = {"A", "B", "C", "D", "E", "F"};
		String strSolution[] = new String[4];
		String strGuess[] = new String[4];
		String strInput = "";
		
		int intRound = 1;
		int intRightPlace = 0;
		int intRightCharacter = 0;
		int intGuess = 0;
		int intCheck = 0;
		
		boolean boolWrongInput = false;
		boolean boolWon = false;
		
		//create the solution we'll look for
		
		for (int x = 0; x<4 ; x++)
		{
			strSolution[x]=strABCDEF[generator.nextInt(6)];
		}
				
		//introduction
		
		System.out.println("Welcome to Mastermind! Try to find the secret code in 10 rounds."
				+ "\nThe code consists of 4 characters between A-F. Characters can repeat."
				+ "\nAfter every try an output will tell you how many letters are on the right"
				+ "\nplace and how many letters are correct, but on the wrong place.");
		
		//start of the loop that goes on for 10 rounds or terminates when the correct code is given
		
		while ((intRound <=10)&&(!boolWon))
		{
			//start of round
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("ROUND " + intRound);
			
			//loop that asks for input, processes it and asks again if input is incorrect
			do
			{
				//asks for input
				System.out.println("Please enter your guess. Guesses need to be 4 characters and A-F");
				//takes input
				strInput = scan.next();
				//checks if length is 4
				if (strInput.length()!=4)
					boolWrongInput = true;	//to keep repeating the input stage in case of wrong input
				else
				{
					//boolWrongInput is set to false because otherwise the program would never know if the input is right (trust me, it makes sense)
					boolWrongInput = false;
					//loops through the string to put it in an array, checks if it matches A-F
					for(int x=0 ; x<4 ; x++)
					{
						strGuess[x] = strInput.substring(x, x+1).toUpperCase();
						//checks if the input matches A-F
						if (!((strGuess[x].equals("A"))||(strGuess[x].equals("B"))||(strGuess[x].equals("C"))||
								(strGuess[x].equals("D"))||(strGuess[x].equals("E"))||(strGuess[x].equals("F"))))
							boolWrongInput=true;	//to keep repeating the input stage in case of wrong input
					}
				}
				//inform the user something is wrong
				if (boolWrongInput)
					System.out.println("Something went wrong with your guess");

			}while(boolWrongInput);
			
			//checking phase
						
			//checking how many letters are on the right place
			
			//to loop through strGuess and strCheck
			for (int x = 0; x<4 ; x++)
			{
				//checks for every place if the letter matches the letter in the same place on the solution
				if(strGuess[x].equals(strSolution[x]))
				{
					intRightPlace++;		//adds one to the variable intRightPlace
				}
			}
			
			//checking how many correct letters there are
			
			//to loop through strABCDEF and take info per letter
			for (int x = 0 ; x<6 ; x++)
			{			
				//to loop through strGuess and strCheck to record the amount of times this letter occurs
				for(int y = 0 ; y<4 ; y++)
				{
					if (strABCDEF[x].equals(strGuess[y]))
						intGuess++;								//intGuess increases every time one of the elements is equal to the letter being cycled through
					if (strABCDEF[x].equals(strSolution[y]))
						intCheck++;								//same for intCheck					
				}

			//minimum value between intGuess and intCheck (if there's 2 A's in the solution but 1 A in the guess you only get 1. 
			//if there's 1 A in the solution but 2 in the guess you still only get 1)
			intRightCharacter+= Math.min(intGuess,intCheck);		

			intGuess = intCheck = 0; //puts intGuess and intGuess back to zero for the next letter
			}
			
			intRightCharacter-=intRightPlace; //otherwise this would also count the characters that are on the right place
			
			//show results
			
			if (intRightPlace == 4) //this means the game has been won and also quits the loop
				boolWon = true;
			else
			{
				System.out.println("Right characters on the right place:\t" + intRightPlace);
				System.out.println("Right characters on the wrong place:\t" + intRightCharacter);
			}
			
			//put intRightPlace and intRightCharacter back to 0
			
			intRightPlace = intRightCharacter = 0;
			
			intRound++;
		}
		if (boolWon)
			System.out.println("Congratulations! You won in " + (intRound -1) + " rounds!");
		else
		{
			System.out.print("I'm sorry, you were not able to find the solution in 10 rounds."
					+ "\nThe correct solution: ");
			//prints out the 4 characters of the solution
			for (int x = 0; x<4; x++)
			{
				System.out.print(strSolution[x]);
			}			
		}			
	}

}
