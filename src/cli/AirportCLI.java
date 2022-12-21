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
		Flight f1 = new Flight(service.getMaxFlightId() +1,12,13,new Plane("747",300),"argentina");
		Flight f2 = new Flight(service.getMaxFlightId() +1,11,12,new Plane("757",200),"argentina");
		Flight f3 = new Flight(service.getMaxFlightId() +1,10,11,new Plane("767",100),"argentina");
		Flight f4 = new Flight(service.getMaxFlightId() +1,15,18,new Plane("777",300),"mexico");
			service.save(f1);
			service.save(f2);
			service.save(f3);
			service.save(f4);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		int decision = sc.nextInt();// Read user input
		while(printAllDestinations(service))
		{
			decision = sc.nextInt();
		}
		context.close();
	}

	public static boolean actions(int decision,TravelerService service,Flight dest) {
		Scanner sc = new Scanner(System.in);
		switch(decision) {
		case 1:
			printAllDestinations(service);
			break;
		case 2:
			printFlight(service,sc);
			break;
		case 3:
			addTraveler(service,dest,sc);
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
	public static void removeTraveler(TravelerService service,Scanner sc) {
		
	}
	public static void deleteTraveler(TravelerService service,Scanner sc) {
		
	}

	public static boolean printAllDestinations(TravelerService service)
	{
		try {
			service.showDestinations();
			Scanner sc = new Scanner(System.in);
			String dest = sc.nextLine();
			List<Flight> flights = service.showFlightsToDestinations(dest);
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
