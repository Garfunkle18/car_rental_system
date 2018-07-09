/**
 * Driver licence class, this class uses a normal public constructor, and returns new objects for defensive copying, this makes all the values
 * a read only format. The paramaters accept date objects only, but these are tested using the calendar class where the dates can be manipulated.
 * An instance of the licence number is generated and then stored in the constructor. The driver licence is used to retrieve a car, so the values
 * that are stored in this class determine whether the client is eligible to drive the selected vehicle.
 * 
 * @author Matthew Garfitt 
 * @version 10/03/2018
 */
import java.util.Date;


public final class DriverLicence {
	private final Name name;
	private final Date dob;
	private final LicenceNumber licenceNumber;
	private final Date dateOfIssue;
	private boolean fullLicence;
	
	
	public DriverLicence(Name name, Date dob, Date dateOfIssue, boolean fullLicence){
		if(name == null || dob == null || dateOfIssue == null)
			throw new IllegalArgumentException("Name, date of birth or date of issue cannot be null.");
		this.name = new Name(name.getFirstName(), name.getLastName());
		this.dob = new Date(dob.getTime());
		this.licenceNumber = LicenceNumber.getInstance(name, dateOfIssue);
		this.dateOfIssue = new Date(dateOfIssue.getTime());
		this.fullLicence = fullLicence;
	}

	/**
	 * Retrieves the name of the driver.
	 * @return A name object.
	 */
	public Name getName() {
		return new Name(name.getFirstName(), name.getLastName());
	}
	
	/**
	 * Retrieves the driver's date of birth.
	 * @return A date object.
	 */
	public Date getDob() {
		return new Date(dob.getTime());
	}
	
	/**
	 * Retrieves the date the licence was issued.
	 * @return A date object.
	 */
	public Date getDateOfIssue() {
		return new Date(dateOfIssue.getTime());
	}
	
	/**
	 * Retrieves the unique licence number of the driver licence.
	 * @return A licence number object that was generated in the constructor.
	 */
	public LicenceNumber getLicenceNumber() {
		return licenceNumber;
	}
	
	/**
	 * Checks whether the licence is provisional or not. 
	 * @return A boolean value that checks whether the licence is full.
	 */
	public boolean isFullLicence() {
		return fullLicence;
	}
	
	/**
	 * A method that overrides the toString to provide meaningful details about the licence.
	 * @return A string representing the licence details
	 */
	@Override
	public String toString(){
		return name + ", " + " DOB: " + dob + ", DOI: " + dateOfIssue + ", fullLicence: " + fullLicence + "\n" +
	licenceNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfIssue == null) ? 0 : dateOfIssue.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + (fullLicence ? 1231 : 1237);
		result = prime * result + ((licenceNumber == null) ? 0 : licenceNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		DriverLicence other = (DriverLicence) obj;
		if (dateOfIssue == null) {
			if (other.dateOfIssue != null)
				return false;
		} else if (!dateOfIssue.equals(other.dateOfIssue))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (fullLicence != other.fullLicence)
			return false;
		if (licenceNumber == null) {
			if (other.licenceNumber != null)
				return false;
		} else if (!licenceNumber.equals(other.licenceNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
