import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NameTest {

   Name name;
//
	@Before
	public void setUp() throws Exception {
		name = new Name("Tom", "Smith");
	}

	@Test
	public void test_getfirstName() {
		// TODO Auto-generated method stub
		assertEquals("Tom", name.getFirstName());
	}
	
	@Test
	public void test_emptyString() throws Exception{
		try {
			Name name3 = new Name("  ", "Smith");
			fail("Name cannot be empty");
		} 
		catch (IllegalArgumentException E) {
			assertEquals("Empty first name", E.getMessage());
		}
	}


	@Test
	public void test_LastName() {
		assertEquals("Smith", name.getLastName());
	}

	@Test
	public void test_getInitials() {
		assertEquals("TS", name.getInitials());
	}

}
