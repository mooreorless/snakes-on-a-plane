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
import java.lang.*;

/**
 * @author Andrew O'Rourke
 *
 */

public class A380Tests {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	// Test methods for A380/Aircraft constructor
	
//	@Test(expected = AircraftException.class)
//	public void InvalidFlightCode() throws AircraftException {
//		A380 testA380 = new A380(null, 2200);
//	}
	
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
	
//	@Test(expected = AircraftException.class)
//	public void CancelBookingPassengerNotInSeating() throws PassengerException, AircraftException {
//		
//		A380 testA380 = new A380("testFlightCode", 2200);
//		Economy testPassenger = new Economy(2, 4);
//		testA380.cancelBooking(testPassenger, 6);
//	}
	
//	@Test
//	public void CancelFirstClassBooking() throws AircraftException, PassengerException{
//		First testFirstPassenger = new First(1200, 2200);
//		A380 testA380 = new A380("Flight code 117", 2200);
//	}
	
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
	
	
	
	// Test methods for getNumPassengers
	
	@Test
	public void GetNumPassengersTest() throws AircraftException, PassengerException {
		A380 testA380 = new A380("Flight 117", 2200);
		First testPassenger = new First(1200, 2200);
		testA380.confirmBooking(testPassenger, 1500);
		assertEquals(1, testA380.getNumPassengers());
	}
	
	//Test methods for upgrades
	
	@Test
	public void testUpgrade() throws AircraftException, PassengerException{
		Economy testPassenger = new Economy(2, 4);
		testPassenger.upgrade();
		Premium testPassenger2 = new Premium(2, 4);
		assertEquals(testPassenger, testPassenger2);
	}
}
