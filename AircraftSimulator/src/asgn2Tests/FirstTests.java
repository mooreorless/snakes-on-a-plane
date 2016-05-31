/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Passengers.First;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;

/**
 * @author Elliott Moore
 *
 */
public class FirstTests {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	@Test(expected = PassengerException.class)
	public void testBookingTimeLessThanZero() throws PassengerException {
		Passenger p = new First(-1, 2);
		
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeat() throws PassengerException {
		//TODO
	}
	
	@Test
	public void testNoSeatsMsg() {
		assertTrue(true);
	}
	
	@Test
	public void testGetBookingTimeDontTest() {
		assertTrue(true);
	}
	
	@Test
	public void testGetConfirmationTimeDontTest() {
		assertTrue(true);
	}
	
	@Test
	public void testGetDepartureTimeDontTest() {
		assertTrue(true);
	}
	
	@Test
	public void testGetEnterQueueTimeDontTest() {
		assertTrue(true);
	}
	
	@Test
	public void testGetExitQueueTimeDontTest() {
		assertTrue(true);
	}
	
	@Test
	public void testGetPassIdDontTest() {
		assertTrue(true);
	}
}
