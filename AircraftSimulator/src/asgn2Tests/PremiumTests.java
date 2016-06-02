/**
 * This is the Premium Class Passenger test class which tests the upgrade method from the inherited abstract class Passenger.
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;
import asgn2Passengers.Business;

/**
 * @author Elliott Moore
 *
 */
public class PremiumTests {

	private Passenger premium;
	private Passenger business;
	private int bookingTime;
	private int departureTime;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bookingTime = 1000;
		departureTime = 2000;
		premium = new Premium(bookingTime, departureTime);
		business = new Business(bookingTime, departureTime);
	}

	@Test
	public void testUpgradePremium() throws PassengerException {
		Passenger upgraded = premium.upgrade();

		assertSame(upgraded.getPassID().charAt(0), business.getPassID().charAt(0));
	}

}
