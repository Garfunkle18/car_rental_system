/**
 * This class is the licence number class that ensures uniqueness for each licence number.
 * The values are padded using a string representation to make sure the licence number is formatted correctly.
 * The class uses a random generator to select a two digit number, if the same number is generated, and the initials and the year of issue are also identical,
 * then a new instance is generated to ensure uniqueness.
 * 
 * @author Matthew Garfitt 
 * @version 10/03/2018
 */
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

public final class LicenceNumber {
	private final String initials;
	private final int dateOfIssue;
	private final String stringRep;
	private final int serialNumber;
	private static final Map<String, LicenceNumber> LICENCE_NUMBER = new HashMap<>();
	
	
	private LicenceNumber(String initials, int dateOfIssue, int serialNumber){
		this.initials = initials;
		this.dateOfIssue = dateOfIssue;
		this.serialNumber = serialNumber;
		stringRep = String.format("%2s%c%04d%c%02d",initials, '-', dateOfIssue, '-', serialNumber); //formats the string to accept two letters, followed by a character (representing the '-'), a 4 digit number for the year, another '-', and two digits for the serial number.
	}
	
	/**
	 * An object factory method to initialise the values before finalising them in a private constructor.
	 * Generates a unique licence number based on the client's name and the date of issue.
	 * @param A name object to represent the name of the client.
	 * @param A date object to represent the date of issue.
	 * @return An instance of the licence number. 
	 */
	public static LicenceNumber getInstance(Name name, Date date) {
		Random random = new Random();
		final String initials = name.getInitials();
		String stringDate = date.toString().substring(date.toString().length() - 4);//;
		int yearOfIssue = Integer.parseInt(stringDate);
		final int serialNumber = random.nextInt(100);
		final String stringRep = initials + "-" + yearOfIssue + "-" + serialNumber;
		
		//ensures a unique licence number is generated.
		if(!(LICENCE_NUMBER.containsKey(stringRep)))
			LICENCE_NUMBER.putIfAbsent(stringRep, new LicenceNumber(initials, yearOfIssue, serialNumber));
		else if(LICENCE_NUMBER.containsKey(stringRep))
			return LicenceNumber.getInstance(name, date);
		
		return LICENCE_NUMBER.get(stringRep);
	}
	
	/**
	 * Retrieve's the client's initials.
	 * @return A string that is the first letter of the client's first name and last name.
	 */
	public String getInitials(){
		return initials;
	}
	
	/**
	 * Retrieve's the serial number that represents the two digits.
	 * @return An integer that is two digits long.
	 */
	public int getSerialNumber(){
		return serialNumber;
	}
	
	/**
	 * Retrieves the date the licence was issued.
	 * @return A date object.
	 */
	public int getDateOfIssue() {
		return dateOfIssue;
	}
	
	/**
	 * Overrides the to string method to display the serial number in a string format.
	 * @return A string representation of the serial number.
	 */
	@Override
	public String toString(){
		return stringRep;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dateOfIssue;
		result = prime * result + ((initials == null) ? 0 : initials.hashCode());
		result = prime * result + serialNumber;
		result = prime * result + ((stringRep == null) ? 0 : stringRep.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LicenceNumber other = (LicenceNumber) obj;
		if (dateOfIssue != other.dateOfIssue)
			return false;
		if (initials == null) {
			if (other.initials != null)
				return false;
		} else if (!initials.equals(other.initials))
			return false;
		if (serialNumber != other.serialNumber)
			return false;
		if (stringRep == null) {
			if (other.stringRep != null)
				return false;
		} else if (!stringRep.equals(other.stringRep))
			return false;
		return true;
	}	
}
