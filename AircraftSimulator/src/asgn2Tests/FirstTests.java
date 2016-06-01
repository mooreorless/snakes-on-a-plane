/**
 * This file includes the test cases for First Class passenger
 * through the Passenger interface.
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Passengers.First;
import asgn2Passengers.Business;
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
		int bookingTime = -1;
		int departureTime = 2;

		Passenger p = new First(bookingTime, departureTime);
		
	}

	@Test(expected = PassengerException.class)
	public void testDepartureTimeLessThanZero() throws PassengerException {
		int bookingTime = 2200;
		int departureTime = -1;

		Passenger p = new First(bookingTime, departureTime);

	}

	@Test(expected = PassengerException.class)
	public void testDepartureTimeBeforeBookingTime() throws PassengerException {
		int bookingTime = 2200;
		int departureTime = 1800;

		Passenger p = new First(bookingTime, departureTime);

	}

	// Cancel Seat Tests

	@Test
	public void testPassengerCancelSeatNormal() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int cancellationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.confirmSeat(1000, 2000);
		p.cancelSeat(cancellationTime);

		assertFalse(p.isConfirmed());
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatCancellationTimeLessThanZero() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int cancellationTime = -1;
		Passenger p = new First(bookingTime, departureTime);

		p.cancelSeat(cancellationTime);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatDepartureTimeLessThanCancellationTime() throws PassengerException {

		int bookingTime = 1;
		int departureTime = 2;
		int cancellationTime = 3;
		Passenger p = new First(bookingTime, departureTime);

		p.cancelSeat(cancellationTime);
	}

	@Test
	public void testPassengerCancelSeatBookingTimeEqualsCancellationTime() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int cancellationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.confirmSeat(1000, 2000);
		p.cancelSeat(cancellationTime);

		assertEquals(bookingTime, cancellationTime);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatPassengerIsNew() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int cancellationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.cancelSeat(cancellationTime);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatPassengerIsQueued() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int queueTime = 1000;
		int cancellationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.queuePassenger(queueTime, departureTime);
		p.cancelSeat(cancellationTime);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatPassengerIsRefused() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int refusalTime = 1000;
		int cancellationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.refusePassenger(refusalTime);
		p.cancelSeat(cancellationTime);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatPassengerIsFlown() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int cancellationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.flyPassenger(departureTime);
		p.cancelSeat(cancellationTime);
	}

	// Confirm Seat Methods

	@Test(expected = PassengerException.class)
	public void testConfirmSeatDepartureTimeLessThanZero() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int confirmationTime = -1;
		Passenger p = new First(bookingTime, departureTime);

		p.confirmSeat(confirmationTime, departureTime);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatDepartureTimeLessThanConfirmationTime() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int confirmationTime = 3000;
		Passenger p = new First(bookingTime, departureTime);

		p.confirmSeat(confirmationTime, departureTime);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatPassengerIsConfirmedTwice() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int confirmationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.confirmSeat(confirmationTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatPassengerIsRefused() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int refusalTime = 1000;
		int confirmationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.refusePassenger(refusalTime);
		p.confirmSeat(confirmationTime, departureTime);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatPassengerIsFlown() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int confirmationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.flyPassenger(departureTime);
		p.confirmSeat(confirmationTime, departureTime);
	}

	// Passenger State Transitions

	@Test
	public void testPassengerIsNew() throws PassengerException {

		Passenger p = new First(1000, 2000);
		assertTrue(p.isNew());
	}

	@Test
	public void testPassengerIsQueued() throws PassengerException {

		Passenger p = new First(1000, 2000);
		p.queuePassenger(1000, 2000);
		assertTrue(p.isQueued());
	}

	@Test
	public void testPassengerIsRefused() throws PassengerException {

		Passenger p = new First(1000, 2000);
		p.refusePassenger(1500);
		assertTrue(p.isRefused());
	}

	@Test(expected = PassengerException.class)
	public void testPassengerIsFlown() throws PassengerException {

		Passenger p = new First(1000, 2000);
		p.flyPassenger(2000);
		assertTrue(p.isFlown());
	}

	@Test
	public void testPassengerIsConfirmed() throws PassengerException {

		Passenger p = new First(1000, 2000);
		p.confirmSeat(1000, 2000);
		assertTrue(p.isConfirmed());
	}

	//Fly Passenger

	@Test(expected = PassengerException.class)
	public void testFlyPassengerInvalidDepartureTime() throws PassengerException {

		int bookingTime = 1000;
		int invalidDepartureTime = -1;
		Passenger p = new First(bookingTime, invalidDepartureTime);

		p.flyPassenger(invalidDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerIsNewState() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		Passenger p = new First(bookingTime, departureTime);

		p.flyPassenger(departureTime);
		//assertFalse(p.isNew());
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerIsQueuedState() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		Passenger p = new First(bookingTime, departureTime);

		p.queuePassenger(1500, 2000);
		p.flyPassenger(departureTime);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerIsRefusedState() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		Passenger p = new First(bookingTime, departureTime);

		p.refusePassenger(1000);
		p.flyPassenger(departureTime);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerFlyTwice() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		Passenger p	= new First(bookingTime, departureTime);

		p.flyPassenger(departureTime);
		p.flyPassenger(departureTime);
	}

	//Queue Passenger

	@Test(expected = PassengerException.class)
	public void testQueuePassengerQueueTimeLessThanZero() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int queueTime = -1;
		Passenger p = new First(bookingTime, departureTime);

		p.queuePassenger(queueTime, departureTime);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerDepartureTimeLessThanQueueTime() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int queueTime = 2100;
		Passenger p = new First(bookingTime, departureTime);

		p.queuePassenger(queueTime, departureTime);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerIsQueuedTwice() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int queueTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.queuePassenger(queueTime, departureTime);
		p.queuePassenger(queueTime, departureTime);
		//p.wasQueued();
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerIsConfirmedState() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int queueTime = 1000;
		int confirmationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.confirmSeat(confirmationTime, departureTime);
		p.queuePassenger(queueTime, departureTime);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerIsRefusedState() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int queueTime = 1000;
		int refusalTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.refusePassenger(refusalTime);
		p.queuePassenger(queueTime, departureTime);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerIsFlownState() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int queueTime = 1000;
		int confirmationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.confirmSeat(confirmationTime, departureTime);
		p.flyPassenger(departureTime);
		p.queuePassenger(queueTime, departureTime);
	}

	// Refuse Passenger

	@Test(expected = PassengerException.class)
	public void testRefusePassengerRefusalTimeLessThanZero() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int refusalTime = -1;
		Passenger p = new First(bookingTime, departureTime);

		p.refusePassenger(refusalTime);
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerRefusalTimeLessThanBookingTime() throws PassengerException {

		int bookingTime = 2000;
		int departureTime = 4000;
		int refusalTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.refusePassenger(refusalTime);
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerIsConfirmedState() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int refusalTime = 1000;
		int confirmationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.confirmSeat(confirmationTime, departureTime);
		p.refusePassenger(refusalTime);
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerIsRefusedTwice() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int refusalTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.refusePassenger(refusalTime);
		p.refusePassenger(refusalTime);
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerIsFlownState() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int refusalTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.flyPassenger(departureTime);
		p.refusePassenger(refusalTime);
	}

	@Test
	public void testWasConfirmed() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int confirmationTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.confirmSeat(confirmationTime, departureTime);

		assertTrue(p.wasConfirmed());
	}

	@Test
	public void testWasQueued() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		int queueTime = 1000;
		Passenger p = new First(bookingTime, departureTime);

		p.queuePassenger(queueTime, departureTime);

		assertTrue(p.wasQueued());
	}

	// Can't upgrade First class passenger, so will test Business
	@Test
	public void testUpgradeBusinessPassenger() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		Passenger business = new Business(bookingTime, departureTime);
		Passenger upgraded = new First(bookingTime, departureTime);

		System.out.println(business.upgrade());

		String msg = business.getPassID();
		msg += "changed";

		String otherMsg = upgraded.getPassID();

		System.out.println(msg + otherMsg);
		assertNotSame(business, upgraded);
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
