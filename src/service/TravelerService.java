package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import dal.FileDao;
import entitys.Airport;
import entitys.Flight;
import entitys.Traveler;
import exceptions.FlightAlreadyExistException;
import exceptions.FlightNotFoundException;
import exceptions.FullAirportException;
import exceptions.FullFlightException;
import exceptions.TravelerAlreadyExistsException;
import exceptions.TravelerNotFoundException;


//service
public class TravelerService {
	@Autowired
	private FileDao dependency;
	private int maxTravelers;
    private int maxFlights;
    private int maxDestinations;
	
	public List<Flight> getAll() throws Exception {
		return dependency.getAll();
	}

	public void save(Flight flight) throws Exception {
		if((dependency.getAll()).contains(flight))
			throw new FlightAlreadyExistException("flight: " + flight + " already exist");
		if(maxFlights <= dependency.getAll().size())
			throw new FullAirportException("cannot add flight. airport has reached max flights:" + maxFlights);
		if(maxDestinations <= getDestinations().size())
			throw new FullAirportException("cannot add flight. airport has reached max destinations:" + maxDestinations);
		
		dependency.save(flight);
	}

	public void update(Flight flightChange) throws Exception {
		for(Flight flight : dependency.getAll())
			if(flight.getId() == flightChange.getId()) {
				
				 dependency.update(flightChange);
				 return;
			}
		throw new FlightNotFoundException("flight: " + flightChange + "could not found");
	}

	public void delete(int flightId) throws Exception {
		for(Flight flight : dependency.getAll())
			if(flight.getId() == flightId) {
				 dependency.delete(flightId);
				 return;
			}
		throw new FlightNotFoundException("flight: with id:" + flightId + "could not found");
	}

	public Flight get(int flightId) throws Exception {
		for(Flight flight : dependency.getAll())
			if(flight.getId() == flightId) {
				
				 return dependency.get(flightId);
			}
		throw new FlightNotFoundException("flight: with id:" + flightId + "could not found");
	}
	public void addTravelerToFlight(int flightId,Traveler traveler) throws Exception {
		//update flight
		for(Flight flight : dependency.getAll())
			if(flight.getId() == flightId) {
				if (flight.getTravelers().size() == maxTravelers) {
					throw new FullFlightException("cannot add traveler:" + traveler +" flight is full");
				}
				if(flight.getTravelers().contains(traveler))
					throw new TravelerAlreadyExistsException("traveler:" + traveler + " already exist in flight");
				
				flight.addTraveler(traveler);
				dependency.update(flight);
				System.out.println("traveler added");
				return;
			}
		throw new FlightNotFoundException("flight: with id:" + flightId + "could not found");
	}
	public void removeTravelerFromFlight(int flightId,Traveler traveler) throws Exception {
		for(Flight flight : dependency.getAll())
			if(flight.getId() == flightId) {
				if (flight.getTravelers().size() == 0 || !flight.removeTraveler(traveler)) {
					throw new TravelerNotFoundException("could not found traveler" + traveler + " in flight");
				} 
				
				dependency.update(flight);
				System.out.println("traveler removed");
				return;
			}
		throw new FlightNotFoundException("flight: with id:" + flightId + "could not found");
	}
	
	
	public List<String> getDestinations() throws Exception
	{
		ArrayList<String> list = new ArrayList<String>();
		for(Flight f : dependency.getAll())
			if(!list.contains(f.getDestination()))
				list.add(f.getDestination());
		return list;
	}
	
	public List<Flight> showFlightsToDestinations(String destination) throws Exception {
		String printFlights = "";
		int count = 1;
		ArrayList<Flight> flights = new ArrayList<Flight>();
		for(Flight flight : dependency.getAll())
			if(flight.getDestination().equals(destination)) {
				printFlights += count++ + ".";
				printFlights += flight.toString();
				printFlights += "\n";
				flights.add(flight);
			}
		if(flights.size() == 0)
			throw new FlightNotFoundException("flight with destination:" + destination + " does not exist");
		System.out.println(printFlights);
		return flights;	
	}
	
	@PreDestroy
	@PostConstruct
	public void containerStartUp()  throws Exception{
		getAll();
		System.out.println(Airport.getInstance());
	}

	public int getMaxTravelers() {
		return maxTravelers;
	}

	public void setMaxTravelers(int maxTravelers) {
		this.maxTravelers = maxTravelers;
	}

	public int getMaxFlights() {
		return maxFlights;
	}

	public void setMaxFlights(int maxFlights) {
		this.maxFlights = maxFlights;
	}
	
	public int getMaxDestinations() {
		return maxDestinations;
	}

	public void setMaxDestinations(int maxDestinations) {
		this.maxDestinations = maxDestinations;
	}
	public List<Flight> getTravelerFlights(Traveler traveler,String dest) throws Exception{
		ArrayList<Flight> flights = new ArrayList<Flight>();
		for(Flight flight : dependency.getAll()) {
			if(flight.getDestination().equals(dest) && flight.getTravelers().contains(traveler))
				flights.add(flight);
		}
		if(flights.isEmpty())
			throw new TravelerNotFoundException("could not found traveler" + traveler + " in flights to " + dest + "!");
		return flights;
	}
}
