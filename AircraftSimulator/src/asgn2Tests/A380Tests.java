/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import asgn2Aircraft.*;
import asgn2Passengers.*;
import java.util.List;

/**
 * @author Andrew O'Rourke
 *
 */

public class A380Tests {

	private A380 populatedA380;
	private List<Passenger> seats;
	private Economy testPassenger1;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		populatedA380 = new A380("Test Object", 2000, 10, 10, 10, 10);
		seats.add(testPassenger1);
		System.out.println(populatedA380.getNumEconomy());
	}
	
	@Test
	public void test() throws AircraftException{
		assertNotNull(populatedA380);
	}

	// Test methods for A380/Aircraft constructor
	
	@Test(expected = AircraftException.class)
	public void InvalidFlightCode() throws AircraftException {
		A380 testA380 = new A380(null, 2200);
	}
	
	@Test(expected = AircraftException.class)
	public void DepartureTimeLessThanZero() throws AircraftException {
		A380 testA380 = new A380("testFlightCode", -1);
	}
	
	@Test(expected = AircraftException.class)
	public void DepartureTimeZero() throws AircraftException {
		A380 testA380 = new A380("testFlightCode", 0);
	}
	
	// Test methods for CancelBooking
	
	@Test(expected = AircraftException.class)
	public void CancelBookingPassengerNotConfirmed() throws PassengerException, AircraftException {
		
		A380 testA380 = new A380("testFlightCode", 2200);
		Economy testPassenger = new Economy(2, 4);
		testPassenger.queuePassenger(2, 4);
		//System.out.println(testPassenger.isQueued());
		testA380.cancelBooking(testPassenger, 2);
	}
	
	@Test(expected = AircraftException.class)
	public void CancelBookingPassengerCancellationTimeZero() throws PassengerException, AircraftException {
		
		A380 testA380 = new A380("testFlightCode", 2200);
		Economy testPassenger = new Economy(2, 4);
		testA380.cancelBooking(testPassenger, 0);
	}
	
	@Test(expected = AircraftException.class)
	public void CancelBookingPassengerDepTimeLessThanCancTime() throws PassengerException, AircraftException {
		
		A380 testA380 = new A380("testFlightCode", 2200);
		Economy testPassenger = new Economy(2, 4);
		testA380.cancelBooking(testPassenger, 6);
	}
	
//	@Test(expected = AircraftException.class)
//	public void CancelBookingPassengerNotInSeating() throws PassengerException, AircraftException {
//		
//		A380 testA380 = new A380("testFlightCode", 2200);
//		Economy testPassenger = new Economy(2, 4);
//		testA380.cancelBooking(testPassenger, 6);
//	}
	
//	@Test
//	public void testUpgrade() throws AircraftException, PassengerException{
//		
//		A380 testA380 = new A380("testFlightCode", 2200);
//		Economy testPassenger = new Economy(2, 4);
//		int beforeValue = testA380.getNumPremium();
//		testPassenger.upgrade();
//		int afterValue = testA380.getNumPremium();
//		assertEquals(beforeValue, afterValue);
//		System.out.println(beforeValue);
//		System.out.println(afterValue);
//	}
	
}
