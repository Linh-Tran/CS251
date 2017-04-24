import java.util.*;
import java.io.*;

/**
 * @author Linh Tran
 * CS251.004 
 * March 5, 2014
 * AnagramSolver contains two classes an AnagramSolver as the outer class and Letter Inventory 
 * as the nested class. The outer class does the recursive call to find all the possible anagrams
 * combinations uses all the letters in the given phrase to form anagrams. The inventory builds inventory 
 * using the given phrase and the words from the dictionary. 
 */


/**
* This class calls contains 2 methods called void print and void findAnagram and a constructor.
* it does the recursive using the findAnagram method that looks through the dictionary.
* It finds words that can be created from the given phrase and make a temp inventory that store that 
* the substracted letters inside the given phrase. Then recursively adds the word that match with the given 
* into array string called Anagram as long as letters inside the phrase can be substracted and that 
* the number of anagram found is less than or equal to maxWords or equal to zero. For every anagram
* it findAnagram finds it prints.  
*/
public class AnagramSolver{

	List <String> dict; //makses a list called dict

	/**
	* The AnagramSolver constructor pass through an list of string (dictionary) and
	* and stores it into a local member variable of list of string called dict. 
	* @param List of String called x
	*/
	public AnagramSolver(List <String> x){
		dict = x;
	}

	/**
	* method coincide with findAnagram method if maxWords is greater or equal to zero than it will call
	* the findAnagram method passing in the same maxWords, the phrase given as an LetterInventory
	* and a empty string arrayList. if maxWords is less than 0 then it throw a IllegalArgumentException  
	* @param String phraseString, and int maxWords
	*/
	void print(String phraseString, int maxWords){
		
		if (maxWords >= 0){
			findAnagram( maxWords, new LetterInventory(phraseString), new ArrayList<String>());
		}
		else {
			System.out.println("hello");
			throw new IllegalArgumentException(
				"maxWords can't be less than zero: " + maxWords);
		}
	}


	/**
	* method does the recusive call to find all the possible anagram from a given phrase
	* with the restratic that the anagram must be less than or equal to maxwords. If done
	* finding an anagram it will print it. If an anagram is not form, meeaning that the 
	* inventory of the phase in which the dictionary substracts is not zero then the letter
	* inside the phrase inventory is restored. Then void print will call findAnagram 
	* to test a different words next on the dictionary to search anagrams from.
	* @param int maxWords: limit on the amount of anagrams solution for a phrase,
	* 		 letter invertory of the phrase pass through and a List of Strings called anagram.
	* @print prints anagram.
	*/
	public void findAnagram ( int maxWords, LetterInventory phrase, List<String> anagram){
		
		
		if (phrase.isEmpty()){ //base case when there's no more letters inside the phrase inventory

			if (anagram.size()<= maxWords || maxWords==0){ //if the anagram size is <= maxWords or ==0
				System.out.println(anagram); //prints the anagram string list
			}
			return; //exit recursive call when find anagram
		}

		//loop through the dictionary using for each loop and create a temporary invertory that 
		// stores the invertory of the phrase substract from 


		/**loop through the dictionary using for each loop and create a temporary invertory that 
		   stores the invertory of the phrase substract from */
		for (String x : dict){



			/**Uses the substract method from LetterInventory class */
			LetterInventory tmp = phrase.substract(new LetterInventory(x));
			
			/**while letters can be remove from the phrase inventory then add the 
				dictionary word that can form into the angram list and try to add more
				words that matches util you can't anymore. If cant substract anymore letters then 
				backtrack by removing the word that can't from anagram from the anagram solution list */ 
			if(tmp != null){ 
				anagram.add(x);
				findAnagram(maxWords, tmp, anagram);
				anagram.remove(x);
			}
		}
	}

	
	/**
	* This class creates LetterInventory that stores the letter words in alphabetical order 
	* and avoiding other puntuations and non letters input. Class contains three methods 
	* called isEmpty(), LetterInverntory substract, String toString(). IsEmpty checks if
	* inventory isEmpty, substract method substracts the dictionary words from the given phrase, 
	* and toString transforms the invetory arrayLIst into a string in the format "[" aabbc "}"
	*/

	public static class LetterInventory {
		private String inventoryString = ""; //invetoryString 

		//creates a ArrayList of type Character;
		ArrayList<Character> inventoryList = new ArrayList<>();

		/**
		* LetterInventory constructor that creates a inventory of alphabetic letters
		* in given string, ignoring the case and non-alphabetic characters using string
		* pass through.
		* @param String from the phrase phase through
		*/
		public LetterInventory (String s) {

			//turns the String s into a char array called inventoryChars
			char [] inventoryChars = s.toCharArray();
			
			/**iterate through inventoryChars and add characters to the 
			   arrayList if the char is an alphabetic letter */
			for ( char x: inventoryChars){
				if (Character.isLetter(x)){
					x = Character.toLowerCase(x);
					inventoryList.add(x);
				}
			}
			Collections.sort(inventoryList);//sort the arraylist alphabetically.
		}

		/**
		* method checks of the inventory arrayList is empty using
		* collections methods of the phrase pass through is emty.
		* @return boolean true 
		*/
		boolean isEmpty(){ 
		
			return inventoryList.isEmpty();//return true if the inventoryList is empty.
		}
		
		/**
		* transform the arrayList of typ character into a string using stringBuilder
		* collections methods of the phrase pass through is emty.
		* @return inventory of letters as a string called inventoryString.
		*/
		public String toString(){ 
			//create StringBuilder the size of arrayList
			StringBuilder sb = new StringBuilder(inventoryList.size());
			//iterate through the inventoryList and append char to the stringBuilder sb
			for ( Character x : inventoryList){ 
				sb.append(x);
			}
			inventoryString = sb.toString(); //turn stringBuilder into string

			return  "[" + inventoryString +"]"; //return string in the format "[" aaaablm "]"
		}
		/**
		* loop through each Character or letter inside the dictionary words inventory 
		* and substract Characters from the inventoryList which is inventory of the
		* phrase user pass through, if no more Character can be remove from the list
		* then reutrn null
		* @param takes inventory of the dictionary words
		* @return return null or new inventory
		*/ 
		LetterInventory substract (LetterInventory other){
			//create a new LetterInventory that contains the letters in the pass in inventory
			LetterInventory inventory = new LetterInventory(this.toString());
			
			//loop through each Character or letter inside the dictionary words inventory 
			// and substract Characters from the inventoryList which is inventory of the
			// phrase user pass through, if no more Character can be remove from the list
			// then reutrn null 
			for ( Character y: other.inventoryList){
				if ( inventory.inventoryList.contains(y)){
					inventory.inventoryList.remove(y);
				}
				else{
					return null ;
				}	
			}
			//otherwise return the new inventory.
			return inventory;
	    }
	}
}