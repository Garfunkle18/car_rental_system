/**
 * This class tests 5000 instances of registration Numbers to ensure uniqueness with each instance.
 */

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class RegistrationNumberTester {

	ArrayList<RegistrationNumber> regs;
	RegistrationNumber reg;

	@Before
	public void setUp() {
		regs = new ArrayList<>();
		for (int i = 0; i < 5000; i++) {
			RegistrationNumber regNum = RegistrationNumber.getInstance();
			regs.add(regNum);
		}
	}

	@Test
	public void test_getInstance() throws Exception {
		int count = 0;
		for (RegistrationNumber regNum1 : regs) {
			for (int i = 0; i < regs.size(); i++) {
				if (count != i)
					assertNotEquals(regNum1, regs.get(i));
			}
			count++;
		}
	}

	@Test
	public void test_getChar() {
		for (RegistrationNumber regNum : regs) {
			assertTrue(Pattern.matches("[a-z]", Character.toString(regNum.getChar())));
		}
	}

	@Test
	public void test_getRegNum() {
		for (RegistrationNumber regNum : regs) {
			assertTrue(Pattern.matches("[0-9]{1,4}", Integer.toString(regNum.getRegNumber()))); //random number is not yet padded out, but is once it's finalised as a registration number to accept four digits only. If for exampl the random number is 1, once padded, this is actually 0001.
		}
	}

}