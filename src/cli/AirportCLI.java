package cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import entitys.Flight;
import entitys.Plane;
import entitys.Traveler;
import exceptions.FlightNotFoundException;
import exceptions.FullFlightException;
import exceptions.TravelerAlreadyExistsException;
import exceptions.TravelerNotFoundException;
import service.TravelerService;

public class AirportCLI {
	public static Scanner sc = new Scanner(System.in);  // Create a Scanner object
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//show flight
		//show traveler
		//add traveler
		//update traveler
		//delete traveler
		//exit
		// Load the Spring configuration file

		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		TravelerService service = (TravelerService)context.getBean("travelerService");
		//start
		
		while(printAllDestinations(service)){}
		
		//end
		context.close();
	}

	public static boolean actions(int decision,TravelerService service,Flight dest) {
		switch(decision) {
		case 1:
			addTraveler(service,dest);
			break;
		case 2:
			removeTraveler(service,dest);
			break;
		case 3:
			getAllTravelerFlights(service,dest);
			break;
		case 4:
			sc.nextLine();
			printAllDestinations(service);
			break;
		case 0:
			System.out.println("Exited.");
			return false;
		}	
		return true;
	}
	

	public static void printFlight(TravelerService service) {
		try {
			int id = sc.nextInt();
			System.out.println(service.get(id).toString());
		}
		catch(FlightNotFoundException fnfe)
		{
			System.out.println(fnfe.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void addTraveler(TravelerService service,Flight dest) {
		try {
			sc.nextLine();
			System.out.println("Enter full name:");
			String name = sc.nextLine();
			System.out.println("Enter passport id:");
			int passportId = sc.nextInt();
			sc.nextLine();
			Traveler newTraveler = new Traveler(passportId,name);
			service.addTravelerToFlight(dest.getId(), newTraveler);
		}catch(FlightNotFoundException fnfe)
		{
			System.out.println(fnfe.getMessage());
		}
		catch(TravelerAlreadyExistsException taee)
		{
			System.out.println(taee.getMessage());
		}
		catch(FullFlightException ffe)
		{
			System.out.println(ffe.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void removeTraveler(TravelerService service,Flight dest) {
		try {
			
			System.out.println("Enter passport id:");
			int passportId = sc.nextInt();
			sc.nextLine();
			Traveler newTraveler = new Traveler(passportId,""); //empty name for removing
			service.removeTravelerFromFlight(dest.getId(),newTraveler);
		}catch(FlightNotFoundException fnfe)
		{
			System.out.println(fnfe.getMessage());
		}
		catch(TravelerAlreadyExistsException taee)
		{
			System.out.println(taee.getMessage());
		}
		catch(FullFlightException ffe)
		{
			System.out.println(ffe.getMessage());
		}	catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void getAllTravelerFlights(TravelerService service,Flight dest) {
		try {
			System.out.println("Enter passport id:");
			int passportId = sc.nextInt();
			sc.nextLine();
			Traveler traveler = new Traveler(passportId,"");
			List<Flight> travelerDests = service.getTravelerFlights(traveler,dest.getDestination());
			System.out.println("your flights to " + dest.getDestination() +" are: " + travelerDests);
		}catch(TravelerNotFoundException tnfe) // traveler does not exist
		{
			System.out.println(tnfe.getMessage());
		}
		catch(Exception e) //error
		{
			e.printStackTrace();
		}
	}

	public static boolean printAllDestinations(TravelerService service)
	{
		try {
			System.out.println("Destinations: " + service.getDestinations());
			System.out.println("Enter destination:");
			String dest = sc.nextLine();
			List<Flight> flights = service.showFlightsToDestinations(dest);
			System.out.println("Enter flight number:");
			int flightNum = sc.nextInt();
			Flight chosenFlight = flights.get(flightNum - 1);
			System.out.println("actions: 1 - add to flight, 2 - remove from flight, 3 - show your flights, 4 - show all flights, 0 - exit");
			int decision = sc.nextInt();
			return actions(decision,service,chosenFlight);
		}catch(FlightNotFoundException fnfe) // destination does not exist
		{
			System.out.println(fnfe.getMessage());
			return true;
		}
		catch(Exception e) // error
		{
			e.printStackTrace();
			return false;
		}
	}
	

}
