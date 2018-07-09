import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LargeCarTest {
	
	LargeCar car;
	
	@Before
	public void setUp() {
		car = new LargeCar(RegistrationNumber.getInstance());
		//so that it can be driven.
		car.setRented(true);
	}
	
	@Test
	public void test_instance() {
		assertTrue(car instanceof LargeCar);
	}
	
	@Test
	public void test_getFuelCapacity() {
		car.drive(1);
		assertEquals(60, car.getFuelCapacity());
		assertFalse(car.isFull());
		car.topUpFuel(1);
		assertTrue(car.isFull());
	}
	
	@Test
	public void test_getFuelAmount() {
		assertEquals(60, car.getFuelAmount());
		car.drive(1);
		assertEquals(59, car.getFuelAmount());
	}
	
	@Test
	public void test_drive() {
		assertEquals(0, car.drive(-1));
		assertEquals(0, car.drive(0));
		assertEquals(60, car.drive(870));
		assertEquals(0, car.drive(100));
		assertTrue(car.isEmpty());
		car.topUpFuel(60);
		assertTrue(car.isFull());
	}

}
