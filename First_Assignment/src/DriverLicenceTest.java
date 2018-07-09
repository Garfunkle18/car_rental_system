//This class does not test the licence's getter methods as there is no behaviour to examine, meaning that these methods would just be redundant testing.
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DriverLicenceTest {

	DriverLicence licence;
	Name name;
	Date date;
	
	@Before
	public void setUp() {
		name = new Name("Matthew", "Garfitt");
		date = new Date();
		DriverLicence licence = new DriverLicence(name, date, date, true);
	}
	
	@Test
	public void test_nullValues() {
		try{
		DriverLicence licence2 = new DriverLicence(null, null, null, true);
		fail("Null values");
		} catch (IllegalArgumentException e) {
			assertEquals("Name, date of birth or date of issue cannot be null.", e.getMessage());
		}
	}

}
