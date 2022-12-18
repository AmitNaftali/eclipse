package entitys;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Airport {
	private String name;
	private int flightcount;
	private final int MAX_FLIGHT_COUNT = 30;
	private List<Flight> flights;

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Airport(String name, int flightcount) {
		this.name = name;
		this.flightcount = 0;
		this.flights = new ArrayList<Flight>();
	}

	public boolean addFlight(Flight fl) {
		if (flightcount == MAX_FLIGHT_COUNT) {
			System.out.println("unable to add Flight. airport is full");
			return false;
		}
		else {
			flights.add(fl);
			flightcount++;
			Collections.sort(flights);
			System.out.println("flight added");
			return true;
		}
	}
	
	

}
