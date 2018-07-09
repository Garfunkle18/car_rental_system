/**
 * This class generates instances of unique registration numbers and assigns them to car objects to identify each car.
 * The class makes use of an object factory to generate the values before finalising them in the
 * private constructor. 
 * 
 * @author Matthew Garfitt 
 * @version 10/03/2018
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public final class RegistrationNumber {
	private static final Map<String, RegistrationNumber> REG_NUM = new HashMap<String, RegistrationNumber>();
	private final char letter;
	private final int regNumber;
	private final String stringRep;

	private RegistrationNumber(char letter, int regNumber) {
		this.letter = letter;
		this.regNumber = regNumber;
		stringRep = String.format("%c%04d", letter, regNumber); // formats the registration number to accept one character and a 4 digit number, so the digits can be 0000.
	}

	public static RegistrationNumber getInstance() {
		Random random = new Random();
		final char letter = (char) (random.nextInt(26) + 'a'); // generates a random letter in the alphabet.
		final int regNumber = random.nextInt(10000); //generates a random four digit number.
		final String stringRep = String.format("%c%04d", letter, regNumber); 

		
		
		if (!REG_NUM.containsKey(stringRep)) {
			REG_NUM.putIfAbsent(stringRep, new RegistrationNumber(letter, regNumber));
			return REG_NUM.get(stringRep);
		}else if (REG_NUM.containsKey(stringRep)){
			 return RegistrationNumber.getInstance(); //generates a new registration number if it already exists, generating a new unique number should not be a problem since there are only 50 cars in the rental system.
		}
		
		return REG_NUM.get(stringRep);
	}

	/**
	 * Returns the character that is associated with the licence number.
	 * @return A character that is a letter.
	 */
	public char getChar() {
		return letter;
	}
	
	/**
	 * Returns the registration number from 0 to 9999
	 * @return An integer that is the registration number.
	 */
	public int getRegNumber() {
		return regNumber;
	}

	/**
	 * Overrides the to string method to represent the registration number in a string format.
	 * @return A string representation of the licence number.
	 */
	@Override
	public String toString() {
		return stringRep;
	}
}
