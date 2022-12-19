package entitys;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Airport {
	private Airport airport;
	private String name;
	private int flightcount;
	private final int MAX_FLIGHT_COUNT = 30;
	private ArrayList<Flight> flights;

	
	private Airport() {
	}
	public Airport getInstance() {
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
