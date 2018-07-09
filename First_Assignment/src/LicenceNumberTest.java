import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class LicenceNumberTest {

	ArrayList<LicenceNumber> numbers;
	Name name;
	Date date;
	
	//this method generates 99 instances of the same driver with the exact same date of issue to check for uniqueness with each number.
	@Before
	public void setUp() {
		numbers = new ArrayList<LicenceNumber>();
		name = new Name("Matthew","Garfitt");
		date = new Date();
		for(int i= 0; i < 99; i++) {
			numbers.add(LicenceNumber.getInstance(name, date));
		}
	}
	
	@Test
	public void test_instance() {
		int count = 0;
		for(LicenceNumber licence : numbers) {
			for(int i=0; i < numbers.size(); i++) {
				if(count != i)
					assertNotEquals(licence, numbers.get(i));
			}
			count++;
		}
	}
}
