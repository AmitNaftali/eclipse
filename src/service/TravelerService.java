package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import dal.FileDao;
import entitys.Flight;
import entitys.Traveler;
import exceptions.FlightAlreadyExistException;
import exceptions.FlightNotFoundException;
import exceptions.FullFlightException;


//service
public class TravelerService {
	@Autowired
	private FileDao dependency;
	private final int MAX_TRAVELERS = 100;
    private final int MAX_FLIGHTS = 30;
	
	public List<Flight> getAll() throws Exception {
		return dependency.getAll();
	}

	public void save(Flight f) throws Exception {
		if(((ArrayList<Flight>)dependency.getAll()).contains(f))
			throw new FlightAlreadyExistException(f.toString());
		if(MAX_FLIGHTS <= dependency.getAll().size())
			throw new FullFlightException(MAX_FLIGHTS + "");
		dependency.save(f);
	}

	public void update(int id,Flight flight) throws Exception {
		for(Flight f : (ArrayList<Flight>)dependency.getAll())
			if(f.getId() == id) {
				 dependency.update(id, flight);
				 return;
			}
		throw new FlightNotFoundException(flight.toString());
	}

	public void delete(int id) throws Exception {
		for(Flight f : (ArrayList<Flight>)dependency.getAll())
			if(f.getId() == id) {
				 dependency.delete(id);
				 return;
			}
		throw new FlightNotFoundException("id = " + id);
	}

	public Flight get(int id) throws Exception {
		for(Flight f : (ArrayList<Flight>)dependency.getAll())
			if(f.getId() == id) {
				 return dependency.get(id);
			}
		throw new FlightNotFoundException("id = " + id);
	}
	public boolean addTravelerToFlight(int flightid,Traveler t) throws Exception {
		for(Flight f : (ArrayList<Flight>)dependency.getAll())
			if(f.getId() == flightid) {
				return f.addTraveler(t);
				
			}
		throw new FlightNotFoundException("flight id = " + flightid);
	}
	public boolean removeTravelerFromFlight(int flightid,Traveler t) throws Exception {
		for(Flight f : (ArrayList<Flight>)dependency.getAll())
			if(f.getId() == flightid) {
				return f.removeTraveler(t);
				
			}
		throw new FlightNotFoundException("flight id = " + flightid);
	}
	@PreDestroy
	@PostConstruct
	public void containerStartUp()  throws Exception{ 
		System.out.println(dependency.getAll().toString());

	}
	
	public int getMaxFlightId() throws Exception{
		return Collections.max(dependency.getAll()).getId();
	}
	public List<String> showDestinations() throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		for(Flight f : (ArrayList<Flight>)dependency.getAll())
			if(!list.contains(f.getDestination()))
				list.add(f.getDestination());
		System.out.println(list.toString());
		return list;	
	}
	public List<Flight> showFlightsToDestinations(String destination) throws Exception {
		String printFlights = "";
		ArrayList<Flight> flights = new ArrayList<Flight>();
		for(Flight f : (ArrayList<Flight>)dependency.getAll())
			if(f.getDestination().equals(destination)) {
				printFlights += f.toString();
				printFlights += "\n";
				flights.add(f);
			}
		System.out.println(printFlights);
		return flights;	
	}
}
