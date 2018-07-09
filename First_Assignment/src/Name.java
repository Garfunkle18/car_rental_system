/**
 * Name object class that represents the name of the client, it accepts two string inputs as parameters,
 * the first string for the clients first name, and the second string for the client's last name.
 * 
 * @author Matthew Garfitt 
 * @version 10/03/2018
 */
public final class Name {
	private String firstName; 
	private String lastName;
	
	public Name(String firstName, String lastName) {
		if ((firstName.trim().length() == 0) || (firstName == null))
            throw new IllegalArgumentException("Empty first name"); //The first name should not accept null values.
		if (lastName.trim().length() == 0 || lastName == null)
            throw new IllegalArgumentException("Empty last name"); // The last name should not accept null values.
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * Retrieves the first name of the client.
	 * @return A string that is the client's first name.
	 */
	public String getFirstName() { return firstName; }
	
	/**
	 * Retrieves the surname of the client.
	 * @return A string that is the client's last name.
	 */
	public String getLastName() { return lastName; }
	
	/**
	 * Retrieves the initials of the client using the substring method.
	 * @return A string that is the client's initials.
	 */
	public String getInitials() { return firstName.substring(0,1).toUpperCase() + "" + lastName.substring(0,1).toUpperCase(); }
	
	/**
	 * Overrides the to string method to display the client's name.
	 * @return The client's first name and last name in a string format.
	 */
	@Override
	public String toString(){
		return firstName + " " + lastName;
	}

}

