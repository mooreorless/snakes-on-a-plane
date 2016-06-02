/**
 * This is the Economy Class Passenger test class which tests the upgrade method from the inherited abstract class Passenger.
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Economy;
import asgn2Passengers.Premium;

/**
 * @author Elliott Moore
 *
 */
public class EconomyTests {

	private Passenger economy;
	private Passenger premium;
	private int bookingTime;
	private int departureTime;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bookingTime = 1000;
		departureTime = 2000;
		economy = new Economy(bookingTime, departureTime);
		premium = new Premium(bookingTime, departureTime);
	}

	@Test
	public void testUpgradeEconomy() throws PassengerException {
		Passenger upgraded = economy.upgrade();

		assertSame(upgraded.getPassID().charAt(0), premium.getPassID().charAt(0));
	}
}
