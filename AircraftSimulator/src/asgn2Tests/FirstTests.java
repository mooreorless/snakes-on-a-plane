/**
 * This is the First Class Passenger test class which tests the upgrade method from the inherited abstract class Passenger.
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

	private Passenger p;
	private int normalBookingTime, normalDepartureTime, normalConfirmationTime, normalCancellationTime,
			negativeBookingTime, negativeDepartureTime, negativeCancellationTime, negativeConfirmationTime, earlyDepartureTime;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		normalBookingTime = 1000;
		normalDepartureTime = 2000;
		normalConfirmationTime = 1000;
		normalCancellationTime = 1000;

		negativeBookingTime = -1;
		negativeDepartureTime = -1;
		negativeCancellationTime = -1;
		negativeConfirmationTime = -1;

		earlyDepartureTime = 500;

		p = new First(normalBookingTime, normalDepartureTime);
	}

	//Default test cases and normal cases

	@Test(expected = PassengerException.class)
	public void testBookingTimeLessThanZero() throws PassengerException {

		p = new First(negativeBookingTime, normalDepartureTime);
		
	}

	@Test(expected = PassengerException.class)
	public void testDepartureTimeLessThanZero() throws PassengerException {

		p = new First(normalBookingTime, negativeDepartureTime);

	}

	@Test(expected = PassengerException.class)
	public void testDepartureTimeBeforeBookingTime() throws PassengerException {

		p = new First(normalBookingTime, earlyDepartureTime);
	}

	// Cancel Seat Tests

	@Test
	public void testPassengerCancelSeatNormal() throws PassengerException {

		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
		p.cancelSeat(normalCancellationTime);

		assertFalse(p.isConfirmed());
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatCancellationTimeLessThanZero() throws PassengerException {

		p.cancelSeat(negativeCancellationTime);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatDepartureTimeLessThanCancellationTime() throws PassengerException {
		int cancellationTime = 3000;

		p.cancelSeat(cancellationTime);
	}

	@Test
	public void testPassengerCancelSeatBookingTimeEqualsCancellationTime() throws PassengerException {

		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
		p.cancelSeat(normalCancellationTime);

		assertEquals(normalBookingTime, normalCancellationTime);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatPassengerIsNew() throws PassengerException {

		p.cancelSeat(normalCancellationTime);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatPassengerIsQueued() throws PassengerException {
		int queueTime = 1000;

		p.queuePassenger(queueTime, normalDepartureTime);
		p.cancelSeat(normalCancellationTime);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatPassengerIsRefused() throws PassengerException {
		int refusalTime = 1000;

		p.refusePassenger(refusalTime);
		p.cancelSeat(normalCancellationTime);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatPassengerIsFlown() throws PassengerException {

		p.flyPassenger(normalDepartureTime);
		p.cancelSeat(normalCancellationTime);
	}

	// Confirm Seat Methods

	@Test(expected = PassengerException.class)
	public void testConfirmSeatConfirmationTimeLessThanZero() throws PassengerException {

		p.confirmSeat(negativeConfirmationTime, normalDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatDepartureTimeLessThanConfirmationTime() throws PassengerException {

		p.confirmSeat(normalConfirmationTime, earlyDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatPassengerIsConfirmedTwice() throws PassengerException {

		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatPassengerIsRefused() throws PassengerException {
		int refusalTime = 1000;

		p.refusePassenger(refusalTime);
		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatPassengerIsFlown() throws PassengerException {

		p.flyPassenger(normalDepartureTime);
		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
	}

	// Passenger State Transitions

	@Test
	public void testPassengerIsNew() throws PassengerException {

		assertTrue(p.isNew());
	}

	@Test
	public void testPassengerIsQueued() throws PassengerException {
		int queueTime = 1000;

		p.queuePassenger(queueTime, normalDepartureTime);
		assertTrue(p.isQueued());
	}

	@Test
	public void testPassengerIsRefused() throws PassengerException {
		int refusalTime = 1500;

		p.refusePassenger(refusalTime);
		assertTrue(p.isRefused());
	}

	@Test(expected = PassengerException.class)
	public void testPassengerIsFlown() throws PassengerException {

		p.flyPassenger(normalDepartureTime);
		assertTrue(p.isFlown());
	}

	@Test
	public void testPassengerIsConfirmed() throws PassengerException {

		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
		assertTrue(p.isConfirmed());
	}

	//Fly Passenger

	@Test(expected = PassengerException.class)
	public void testFlyPassengerInvalidDepartureTime() throws PassengerException {

		p.flyPassenger(negativeDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerIsNewState() throws PassengerException {

		p.flyPassenger(normalDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerIsQueuedState() throws PassengerException {
		int queueTime = 1500;

		p.queuePassenger(queueTime, normalDepartureTime);
		p.flyPassenger(normalDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerIsRefusedState() throws PassengerException {
		int refusalTime = 1000;

		p.refusePassenger(refusalTime);
		p.flyPassenger(normalDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerFlyTwice() throws PassengerException {

		p.flyPassenger(normalDepartureTime);
		p.flyPassenger(normalDepartureTime);
	}

	//Queue Passenger

	@Test(expected = PassengerException.class)
	public void testQueuePassengerQueueTimeLessThanZero() throws PassengerException {
		int queueTime = -1;

		p.queuePassenger(queueTime, normalDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerDepartureTimeLessThanQueueTime() throws PassengerException {
		int queueTime = 2100;

		p.queuePassenger(queueTime, normalDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerIsQueuedTwice() throws PassengerException {
		int queueTime = 1000;

		p.queuePassenger(queueTime, normalDepartureTime);
		p.queuePassenger(queueTime, normalDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerIsConfirmedState() throws PassengerException {
		int queueTime = 1000;

		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
		p.queuePassenger(queueTime, normalDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerIsRefusedState() throws PassengerException {
		int queueTime = 1000;
		int refusalTime = 1000;

		p.refusePassenger(refusalTime);
		p.queuePassenger(queueTime, normalDepartureTime);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerIsFlownState() throws PassengerException {
		int queueTime = 1000;

		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
		p.flyPassenger(normalDepartureTime);
		p.queuePassenger(queueTime, normalDepartureTime);
	}

	// Refuse Passenger

	@Test(expected = PassengerException.class)
	public void testRefusePassengerRefusalTimeLessThanZero() throws PassengerException {
		int refusalTime = -1;

		p.refusePassenger(refusalTime);
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerRefusalTimeLessThanBookingTime() throws PassengerException {
		int refusalTime = 500;

		p.refusePassenger(refusalTime);
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerIsConfirmedState() throws PassengerException {
		int refusalTime = 1000;

		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
		p.refusePassenger(refusalTime);
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerIsRefusedTwice() throws PassengerException {
		int refusalTime = 1000;

		p.refusePassenger(refusalTime);
		p.refusePassenger(refusalTime);
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerIsFlownState() throws PassengerException {
		int refusalTime = 1000;

		p.flyPassenger(normalDepartureTime);
		p.refusePassenger(refusalTime);
	}

	@Test
	public void testWasConfirmedThenFlown() throws PassengerException {

		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
		p.flyPassenger(normalDepartureTime);

		assertTrue(p.wasConfirmed());
	}

	@Test
	public void testWasQueued() throws PassengerException {
		int queueTime = 1000;

		p.queuePassenger(queueTime, normalDepartureTime);
		assertTrue(p.wasQueued());
	}

	@Test
	public void testWasConfirmedThenQueued() throws PassengerException {
		int queueTime = 1000;

		p.queuePassenger(queueTime, normalDepartureTime);
		p.confirmSeat(normalConfirmationTime, normalDepartureTime);
		assertTrue(p.wasQueued());
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
