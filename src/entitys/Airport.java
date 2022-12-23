package entitys;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.FlightAlreadyExistException;
import exceptions.FullAirportException;

public final class Airport implements Serializable{
	private static Airport airport;
	private String name;
	private final int MAX_FLIGHT = 30;
	private ArrayList<Flight> flights;

	
	private Airport() {
		flights = new ArrayList<Flight>();
		name = "jfk";
	}
	
	public static Airport getInstance() {
		if(airport == null)
			airport = new Airport();
		return airport;	
	}
	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}

	public void addFlight(Flight flight) throws Exception {
		if (flights.size() == MAX_FLIGHT) {
			throw new FullAirportException("cannot add flight. airport is full of flights:" + MAX_FLIGHT);
		}
		if(flights.contains(flight))
			throw new FlightAlreadyExistException("flight:" + flight + " already exist in airport");
		flights.add(flight);
		Collections.sort(flights);
	}
	
	public void updateFlight(int flightId, Flight flight)
	{
		for (int i = 0; i < flights.size(); i++) {
			if(flights.get(i).getId() == flightId)
			{
				flights.get(i).changeFlight(flight);
			}
		}
		Collections.sort(flights);
	}
	
	public void deleteFlight(int flightId)
	{
		for (int i = 0; i < flights.size(); i++) {
			if(flights.get(i).getId() == flightId)
			{
				flights.remove(flights.get(i));
			}
		}
	}
	public Flight getFlight(int flightId)
	{
		for (int i = 0; i < flights.size(); i++) {
			if(flights.get(i).getId() == flightId)
			{
				return flights.get(i);
			}
		}
		return null; // didnt found
	}

	@Override
	public String toString() {
		return "Airport : name=" + name  + ", flights=" + flights.toString();
	}
	
	

}
