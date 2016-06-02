/**
 * This is the Business Class Passenger test class which tests the upgrade method from the inherited abstract class Passenger.
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Business;
import asgn2Passengers.First;

/**
 * @author Elliott Moore
 *
 */
public class BusinessTests {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUpgradeBusiness() throws PassengerException {

		int bookingTime = 1000;
		int departureTime = 2000;
		Passenger business = new Business(bookingTime, departureTime);
		Passenger first = new First(bookingTime, departureTime);

		Passenger upgraded = business.upgrade();

		assertSame(upgraded.getPassID().charAt(0), first.getPassID().charAt(0));
	}
}
