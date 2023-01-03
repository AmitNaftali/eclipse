package entitys;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exceptions.FlightAlreadyExistException;
import exceptions.FullAirportException;

public final class Airport implements Serializable{
	private static Airport airport;
	private String name;
	private ArrayList<Flight> flights;

	
	private Airport() {
		flights = new ArrayList<Flight>(); 
	}
	
	public static Airport getInstance() {
		if(airport == null)
			airport = new Airport();
		return airport;	
	}
	public static void setAirport(Airport airport2)
	{
		airport = airport2;
	}
	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}

	public void addFlight(Flight flight) {
		flights.add(flight);
	}
	
	public void updateFlight(Flight flight)
	{
		for (int i = 0; i < flights.size(); i++) {
			if(flights.get(i).getId() == flight.getId())
			{
				flights.set(i,flight);
			}
		}
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
