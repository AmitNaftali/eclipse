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

	public boolean addFlight(Flight fl) throws Exception {
		if (flights.size() == MAX_FLIGHT) {
			System.out.println("unable to add Flight. airport is full");
			throw new FullAirportException(MAX_FLIGHT + "");
		}
		if(flights.contains(fl))
			throw new FlightAlreadyExistException(fl.toString());
		
		flights.add(fl);
		Collections.sort(flights);
		System.out.println("flight added");
		return true;
	}

	@Override
	public String toString() {
		return "Airport : name=" + name  + ", flights=" + flights.toString();
	}
	
	

}
