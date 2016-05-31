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


	//Default test cases and normal cases

	@Test(expected = PassengerException.class)
	public void testBookingTimeLessThanZero() throws PassengerException {
		Passenger p = new First(-1, 2);
		
	}

	//Cancel Seat Tests

	@Test(expected = PassengerException.class)
	public void testCancelSeatCancellationTimeLessThanZero() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatDepartureTimeLessThanCancelTime() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatWhenPassengerIsNew() throws PassengerException {
		//TODO
	}


	@Test(expected = PassengerException.class)
	public void testCancelSeatWhenPassengerIsQueued() throws PassengerException {
		//TODO
	}


	@Test(expected = PassengerException.class)
	public void testCancelSeatWhenPassengerIsRefused() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatWhenPassengerIsFlown() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatPassengerNewState() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatPassengerConfirmedState() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatBookingTimeEqualsCancellationTime() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatConfirmTimeLessThanZero() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatDepartureTimeLessThanConfirmationTime() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatWhenPassengerIsConfirmed() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatWhenPassengerIsRefused() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatWhenPassengerIsFlown() throws PassengerException {
		//TODO
	}

	//Fly Passenger

	@Test(expected = PassengerException.class)
	public void testFlyPassengerInvalidDepartureTime() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerIsNewState() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerIsQueuedState() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerIsRefusedState() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerIsFlownState() throws PassengerException {
		//TODO
	}

	//Queue Passenger

	@Test(expected = PassengerException.class)
	public void testQueuePassengerInvalidQueueTime() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerIsQueuedState() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerIsConfirmedState() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerIsRefusedState() throws PassengerException {
		//TODO
	}

	//State variables which are set in the fly,refuse,queue etc
//
//	@Test(expected = PassengerException.class)
//	public void testQueuePassengerIfInQueue() throws PassengerException {
//		//TODO
//	}

//	@Test(expected = PassengerException.class)
//	public void testQueuePassengerEnterQueueTime() throws PassengerException {
//		//TODO
//	}


	//Refuse Passenger

	@Test(expected = PassengerException.class)
	public void testRefusePassengerRefusalTimeLessThanZero() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerIsConfirmedState() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerIsRefusedState() throws PassengerException {
		//TODO
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerIsFlownState() throws PassengerException {
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
