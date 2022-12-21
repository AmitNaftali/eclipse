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
import exceptions.TravelerAlredyExistsException;
import service.TravelerService;

public class AirportCLI {
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
		Scanner sc = new Scanner(System.in);  // Create a Scanner object
		TravelerService service = (TravelerService)context.getBean("travelerService");
		
		
		
		try {
			System.out.println(service.getAll().get(1).getTravelers().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(printAllDestinations(service))
		{
		}
		System.out.println("end");
		context.close();
	}

	public static boolean actions(int decision,TravelerService service,Flight dest) {
		Scanner sc = new Scanner(System.in);
		switch(decision) {
		case 1:
			addTraveler(service,dest,sc);
			break;
		case 2:
			removeTraveler(service,dest,sc);
			break;
		case 3:
			printAllDestinations(service);
			break;
		case 4:
			System.out.println();
			break;
		case 5:
			System.out.println();
			break;
		case 0:
			System.out.println("Exited.");
			return false;
		}	
		return true;
	}
	

	public static void printFlight(TravelerService service,Scanner sc) {
		try {
			int id = sc.nextInt();
			System.out.println(service.get(id).toString());
		}
		catch(FlightNotFoundException fnfe)
		{
			System.out.println("Flight not found.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void addTraveler(TravelerService service,Flight dest,Scanner sc) {
		try {
			System.out.println("Enter full name:");
			String name = sc.nextLine();
			System.out.println("Enter passport id:");
			int passportId = sc.nextInt();
			Traveler newTraveler = new Traveler(passportId,name);
			service.addTravelerToFlight(dest.getId(), newTraveler);
		}catch(FlightNotFoundException fnfe)
		{
			System.out.println("flight not found");
		}
		catch(TravelerAlredyExistsException taee)
		{
			System.out.println("traveler already exist not found");
		}
		catch(FullFlightException ffe)
		{
			System.out.println("flight is full of travelers");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void removeTraveler(TravelerService service,Flight dest,Scanner sc) {
		try {
			System.out.println("Enter full name:");
			String name = sc.nextLine();
			System.out.println("Enter passport id:");
			int passportId = sc.nextInt();
			Traveler newTraveler = new Traveler(passportId,name);
			service.removeTravelerFromFlight(dest.getId(),newTraveler);
			System.out.println(dest.getTravelers().toString());
		}catch(FlightNotFoundException fnfe)
		{
			System.out.println("flight not found");
		}
		catch(TravelerAlredyExistsException taee)
		{
			System.out.println("traveler already exist");
		}
		catch(FullFlightException ffe)
		{
			System.out.println("flight is full of travelers");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void deleteTraveler(TravelerService service,Scanner sc) {
		
	}

	public static boolean printAllDestinations(TravelerService service)
	{
		try {
			service.showDestinations();
			System.out.println("Enter destination:");
			Scanner sc = new Scanner(System.in);
			String dest = sc.nextLine();
			List<Flight> flights = service.showFlightsToDestinations(dest);
			System.out.println("Enter flight number:");
			int flightNum = sc.nextInt();
			Flight chosenFlight = flights.get(flightNum -1);
			System.out.println("actions: 1 - add to flight, 2 - remove from flight, 3 - show all flights, 0 - exit");
			int decision = sc.nextInt();
			return actions(decision,service,chosenFlight);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	

}
