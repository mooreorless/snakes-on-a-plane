/**
 * This A380 test class tests all the methods from the inherited abstract class of Aircraft.
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import asgn2Aircraft.*;
import asgn2Passengers.*;

import java.util.ArrayList;
import java.util.List;
import java.lang.*;

/**
 * @author Andrew O'Rourke
 *
 */

public class A380Tests {

	private A380 testA380;
	private String testFlightCode;
	private int normalDepatureTime, normalConfirmationTime, normalCancellationTime, negativeDepartureTime, departureTimeZero;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Setup default ints here
		testFlightCode = "testFlightCode";

		// Use for normal test case
		testA380 = new A380(testFlightCode, normalDepatureTime);
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
	
	// Test methods for confirmBooking
	
	@Test
	public void ConfirmBookingForFirstClassPassenger() throws PassengerException, AircraftException{
		First testFirstPassenger = new First(1200, 2200);
		A380 testA380 = new A380("Flight code 117", 2200);
		int expectedNumOfFirstPassengers = 1;
		
		testA380.confirmBooking(testFirstPassenger, 1300);
		
		assertEquals(expectedNumOfFirstPassengers, testA380.getNumFirst());
	}
	
	@Test
	public void ConfirmBookingForBusinessClassPassenger() throws PassengerException, AircraftException{
		Business testFirstPassenger = new Business(1200, 2200);
		A380 testA380 = new A380("Flight code 117", 2200);
		int expectedNumOfBusinessPassengers = 1;
		
		testA380.confirmBooking(testFirstPassenger, 1300);
		
		assertEquals(expectedNumOfBusinessPassengers, testA380.getNumBusiness());
	}
	
	@Test
	public void ConfirmBookingForPremiumClassPassenger() throws PassengerException, AircraftException{
		Premium testFirstPassenger = new Premium(1200, 2200);
		A380 testA380 = new A380("Flight code 117", 2200);
		int expectedNumOfPremiumPassengers = 1;
		
		testA380.confirmBooking(testFirstPassenger, 1300);
		
		assertEquals(expectedNumOfPremiumPassengers, testA380.getNumPremium());
	}
	
	@Test
	public void ConfirmBookingForEconomyClassPassenger() throws PassengerException, AircraftException{
		Economy testFirstPassenger = new Economy(1200, 2200);
		A380 testA380 = new A380("Flight code 117", 2200);
		int expectedNumOfEconomyPassengers = 1;
		
		testA380.confirmBooking(testFirstPassenger, 1300);
		
		assertEquals(expectedNumOfEconomyPassengers, testA380.getNumEconomy());
	}
	
	@Test(expected = AircraftException.class)
	public void ConfirmBookingForFullClass() throws PassengerException, AircraftException{
		
		int FIRST = 14;
		
		A380 testA380 = new A380("Flight 117", 2200);

		// Populate entire First class passengers
		for(int i = 0; i < FIRST; i++){
			First testPassenger = new First(1200, 2200);
			testA380.confirmBooking(testPassenger, 1500);
		}
		// Attempt to add another first class passenger
		First testPassenger = new First(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
	}
	
	// Test methods for cancelBooking
	
	@Test(expected = AircraftException.class)
	public void CancelBookingPassengerNotConfirmed() throws PassengerException, AircraftException {
		
		A380 testA380 = new A380("testFlightCode", 2200);
		Economy testPassenger = new Economy(2, 4);
		testPassenger.queuePassenger(2, 4);
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
	
	@Test(expected = AircraftException.class)
	public void CancelBookingPassengerNotInSeating() throws PassengerException, AircraftException {
		
		A380 testA380 = new A380("testFlightCode", 2200);
		Economy testPassenger = new Economy(2, 4);
		testA380.cancelBooking(testPassenger, 6);
	}
	
	@Test
	public void CancelFirstClassBooking() throws AircraftException, PassengerException{
		First testFirstPassenger = new First(1200, 2200);
		A380 testA380 = new A380("Flight code 117", 2200);
	}
	
	// Test methods for flightEmpty
	
	@Test
	public void FlightEmptyTest() throws AircraftException {
		A380 testA380 = new A380("Flight 117", 2200);
		assertTrue(testA380.flightEmpty());
	}
	
	// Test methods for flightFull
	
	@Test
	public void FullFlightTest() throws AircraftException, PassengerException {
		int FIRST = 14;
		int BUSINESS = 64;
		int PREMIUM = 35;
		int ECONOMY = 371;
		
		A380 testA380 = new A380("Flight 117", 2200);
		
		//Populate entire flight
			// Populate entire First class
			for(int i = 0; i < FIRST; i++){
				First testPassenger = new First(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate entire Business class
			for(int i = 0; i < BUSINESS; i++){
				Business testPassenger = new Business(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate entire Premium class
			for(int i = 0; i < PREMIUM; i++){
				Premium testPassenger = new Premium(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate entire Economy class
			for(int i = 0; i < ECONOMY; i++){
				Economy testPassenger = new Economy(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			
		assertTrue(testA380.flightFull());
	}
	
	@Test
	public void EmptyFlightFullFlightTest() throws AircraftException {
		A380 testA380 = new A380("Flight 117", 2200);
		assertFalse(testA380.flightFull());
	}
	
	@Test
	public void FlightFullBoundaryCaseTest() throws AircraftException, PassengerException {
		
		int FIRST = 14;
		int BUSINESS = 64;
		int PREMIUM = 35;
		int ECONOMY = 371;
		
		A380 testA380 = new A380("Flight 117", 2200);
		
		//Populate entire flight
			// Populate entire First class
			for(int i = 0; i < FIRST; i++){
				First testPassenger = new First(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate entire Business class
			for(int i = 0; i < BUSINESS; i++){
				Business testPassenger = new Business(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate entire Premium class
			for(int i = 0; i < PREMIUM; i++){
				Premium testPassenger = new Premium(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate all but one seat of the Economy class
			for(int i = 0; i < (ECONOMY) - 1; i++){
				Economy testPassenger = new Economy(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			
			assertFalse(testA380.flightFull());
	}
	
	// Test methdods for flyPassengers
	
	@Test
	public void FlyPassengersStateTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		First testPassenger = new First(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		testA380.flyPassengers(2200);
		assertTrue(testPassenger.isFlown());
	}
	
	@Test
	public void FlyPassengersDepartureTimeTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		First testPassenger = new First(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		testA380.flyPassengers(2200);
		assertEquals(2200, testPassenger.getDepartureTime());
	}
	
	
	
	// Test methods for simple get methods
	
	@Test
	public void GetNumBusinessTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		Business testPassenger = new Business(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		assertEquals(1, testA380.getNumBusiness());
	}
	
	@Test
	public void GetNumEconomyTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		Economy testPassenger = new Economy(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		assertEquals(1, testA380.getNumEconomy());
	}
	
	@Test
	public void GetNumFirstTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		First testPassenger = new First(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		assertEquals(1, testA380.getNumFirst());
	}
	
	@Test
	public void GetNumPremiumTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		Premium testPassenger = new Premium(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		assertEquals(1, testA380.getNumPremium());
	}
	
	@Test
	public void GetNumPassengersTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		First testPassenger = new First(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		assertEquals(1, testA380.getNumPassengers());
	}
	
	// Test methods for GetPassengers
	
	@Test
	public void getPassengersTest() throws AircraftException, PassengerException {
		List<Passenger> arrList1 = new ArrayList<Passenger>();
		A380 testA380 = new A380("Flight 117", 2200);
		First testPassenger = new First(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		arrList1 = testA380.getPassengers();
		assertEquals(testPassenger, arrList1.get(0));
	}
	
	// Test methods for hasPassengers
	
	@Test
	public void HasPassengerTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		First testPassenger = new First(1200, 2200);
		testA380.confirmBooking(testPassenger, 1300);
		assertTrue(testA380.hasPassenger(testPassenger));
	}
	
	// Test methods for seatsAvailable
	
	@Test
	public void SeatsAvailableInFirstClassTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		First testPassenger = new First(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		assertTrue(testA380.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsUnavailableInFirstClassTest() throws AircraftException, PassengerException {
		
		int numOfFirstSeats = 14;
		
		A380 testA380 = new A380("Flight 117", 2200);
		for(int i = 0; i < numOfFirstSeats; i++){
			First testPassenger = new First(1200, 2200);
			testA380.confirmBooking(testPassenger, 1500);
		}
		First testPassenger = new First(1200, 2200);
		assertFalse(testA380.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableInFirstClassBoundaryTest() throws AircraftException, PassengerException {
		
		int numOfFirstSeats = 14;
		
		A380 testA380 = new A380("Flight 117", 2200);
		for(int i = 0; i < (numOfFirstSeats) - 1; i++){
			First testPassenger = new First(1200, 2200);
			testA380.confirmBooking(testPassenger, 1500);
		}
		First testPassenger = new First(1200, 2200);
		assertTrue(testA380.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableInBusinessClassTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		Business testPassenger = new Business(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		assertTrue(testA380.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsUnavailableInBusinessClassTest() throws AircraftException, PassengerException {
		
		int numOfBusinessSeats = 64;
		
		A380 testA380 = new A380("Flight 117", 2200);
		for(int i = 0; i < numOfBusinessSeats; i++){
			Business testPassenger = new Business(1200, 2200);
			testA380.confirmBooking(testPassenger, 1500);
		}
		Business testPassenger = new Business(1200, 2200);
		assertFalse(testA380.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableInBusinessClassBoundaryTest() throws AircraftException, PassengerException {
		
		int numOfBusinessSeats = 64;
		
		A380 testA380 = new A380("Flight 117", 2200);
		for(int i = 0; i < (numOfBusinessSeats) - 1; i++){
			Business testPassenger = new Business(1200, 2200);
			testA380.confirmBooking(testPassenger, 1500);
		}
		Business testPassenger = new Business(1200, 2200);
		assertTrue(testA380.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableInPremiumClassTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		Premium testPassenger = new Premium(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		assertTrue(testA380.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsUnavailableInPremiumClassTest() throws AircraftException, PassengerException {
		
		int numOfPremiumSeats = 35;
		
		A380 testA380 = new A380("Flight 117", 2200);
		for(int i = 0; i < numOfPremiumSeats; i++){
			Premium testPassenger = new Premium(1200, 2200);
			testA380.confirmBooking(testPassenger, 1500);
		}
		Premium testPassenger = new Premium(1200, 2200);
		assertFalse(testA380.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableInPremiumClassBoundaryTest() throws AircraftException, PassengerException {
		
		int numOfPremiumSeats = 35;
		
		A380 testA380 = new A380("Flight 117", 2200);
		for(int i = 0; i < (numOfPremiumSeats) - 1; i++){
			Premium testPassenger = new Premium(1200, 2200);
			testA380.confirmBooking(testPassenger, 1500);
		}
		Premium testPassenger = new Premium(1200, 2200);
		assertTrue(testA380.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableInEconomyClassTest() throws AircraftException, PassengerException {
		
		A380 testA380 = new A380("Flight 117", 2200);
		Economy testPassenger = new Economy(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		assertTrue(testA380.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsUnavailableInEconomyClassTest() throws AircraftException, PassengerException {
		
		int numOfEconomySeats = 371;
		
		A380 testA380 = new A380("Flight 117", 2200);
		for(int i = 0; i < numOfEconomySeats; i++){
			Economy testPassenger = new Economy(1200, 2200);
			testA380.confirmBooking(testPassenger, 1500);
		}
		Economy testPassenger = new Economy(1200, 2200);
		assertFalse(testA380.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableInEconomyClassBoundaryTest() throws AircraftException, PassengerException {
		
		int numOfEconomySeats = 370;
		
		A380 testA380 = new A380("Flight 117", 2200);
		for(int i = 0; i < numOfEconomySeats; i++){
			Economy testPassenger = new Economy(1200, 2200);
			testA380.confirmBooking(testPassenger, 1500);
		}
		Economy testPassenger = new Economy(1200, 2200);
		assertTrue(testA380.seatsAvailable(testPassenger));
	}
	
	// Test methods for upgradeBooking
	
	@Test
	public void BusinessToFirstUpgradeTest() throws AircraftException, PassengerException {
		
		int first = 10;
		int business = 64;
		
		A380 testA380 = new A380("Flight 117", 2200);
		
		//Populate flight classes with some values
			// Populate First class
			for(int i = 0; i < first; i++){
				First testPassenger = new First(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate entire Business class
			for(int i = 0; i < business; i++){
				Business testPassenger = new Business(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			
		testA380.upgradeBookings();
		assertEquals(14, testA380.getNumFirst());
		assertEquals(60, testA380.getNumBusiness());
	}
	
	@Test
	public void PremiumToBusinessUpgradeTest() throws AircraftException, PassengerException {
		
		int first = 14;
		int business = 60;
		int premium = 35;
		
		A380 testA380 = new A380("Flight 117", 2200);
		
		//Populate flight classes with some values
		// Populate entire First class
			for(int i = 0; i < first; i++){
				First testPassenger = new First(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate Business class
			for(int i = 0; i < business; i++){
				Business testPassenger = new Business(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate Entire Premium class
			for(int i = 0; i < premium; i++){
				Premium testPassenger = new Premium(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			
		testA380.upgradeBookings();
		assertEquals(64, testA380.getNumBusiness());
		assertEquals(31, testA380.getNumPremium());
	}
	
	@Test
	public void EconomyToPremiumUpgradeTest() throws AircraftException, PassengerException {
		
		int first = 14;
		int business = 64;
		int premium = 31;
		int economy = 371; 
		
		A380 testA380 = new A380("Flight 117", 2200);
		
		//Populate flight classes with some values
			// Populate entire First class
			for(int i = 0; i < first; i++){
				First testPassenger = new First(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate entire Business class
			for(int i = 0; i < business; i++){
				Business testPassenger = new Business(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate Premium class
			for(int i = 0; i < premium; i++){
				Premium testPassenger = new Premium(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate Entire Economy class
			for(int i = 0; i < economy; i++){
				Economy testPassenger = new Economy(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			
		testA380.upgradeBookings();
		assertEquals(35, testA380.getNumPremium());
		assertEquals(367, testA380.getNumEconomy());
	}
	
	@Test
	public void UpgradeAllFareClassesTest() throws AircraftException, PassengerException {
		
		int first = 10;
		int business = 60;
		int premium = 31;
		int economy = 371;
		
		A380 testA380 = new A380("Flight 117", 2200);
		
		//Populate flight classes with some values
			// Populate First class
			for(int i = 0; i < first; i++){
				First testPassenger = new First(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate Business class
			for(int i = 0; i < business; i++){
				Business testPassenger = new Business(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate Premium class
			for(int i = 0; i < premium; i++){
				Premium testPassenger = new Premium(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate Economy class
			for(int i = 0; i < economy; i++){
				Economy testPassenger = new Economy(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			
		testA380.upgradeBookings();
		assertEquals(14, testA380.getNumFirst());
		assertEquals(64, testA380.getNumBusiness());
		assertEquals(35, testA380.getNumPremium());
	}
//	
	@Test
	public void UpgradeOnEmptyFlightTest() throws AircraftException, PassengerException {
		
		A380 testA380 = new A380("Flight 117", 2200);
			
		testA380.upgradeBookings();
		assertEquals(0, testA380.getNumFirst());
		assertEquals(0, testA380.getNumEconomy());
		assertEquals(0, testA380.getNumPremium());
	}
	
	@Test
	public void UpgradeOnFullFlightTest() throws AircraftException, PassengerException {
		
		int first = 14;
		int business = 64;
		int premium = 35;
		int economy = 371; 
		
		A380 testA380 = new A380("Flight 117", 2200);
		
		//Populate all flight classes to maximum capacity
			
		// Populate First class
			for(int i = 0; i < first; i++){
				First testPassenger = new First(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate Business class
			for(int i = 0; i < business; i++){
				Business testPassenger = new Business(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate Premium class
			for(int i = 0; i < premium; i++){
				Premium testPassenger = new Premium(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			// Populate Economy class
			for(int i = 0; i < economy; i++){
				Economy testPassenger = new Economy(1200, 2200);
				testA380.confirmBooking(testPassenger, 1500);
			}
			
		testA380.upgradeBookings();
		assertEquals(14, testA380.getNumFirst());
		assertEquals(64, testA380.getNumBusiness());
		assertEquals(35, testA380.getNumPremium());
		assertEquals(371, testA380.getNumEconomy());
	}
	
	@Test
	public void UpgradeOneEachTest() throws AircraftException, PassengerException {
		
		A380 testA380 = new A380("Flight 117", 2200);
		
		Passenger testFirst = new First(1500, 2200);
		Passenger testBusiness = new Business(1500, 2200);
		Passenger testPremium = new Premium(1500, 2200);
		Passenger testEconomy = new Economy(1500, 2200);
		
		testA380.confirmBooking(testFirst, 1600);
		testA380.confirmBooking(testBusiness, 1600);
		testA380.confirmBooking(testPremium, 1600);
		testA380.confirmBooking(testEconomy, 1600);
		
			
		testA380.upgradeBookings();
		
		assertEquals(2, testA380.getNumFirst());
		assertEquals(1, testA380.getNumBusiness());
		assertEquals(1, testA380.getNumPremium());
		assertEquals(0, testA380.getNumEconomy());
	}
}
