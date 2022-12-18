package entitys;

import java.util.ArrayList;
import java.util.Collections;

public final class Airport {
	private Airport airport;
	private String name;
	private int flightcount;
	private final int MAX_FLIGHT_COUNT = 30;
	private ArrayList<Flight> f;

	private Airport() {
	}
	public Airport getInstance() {
		if(airport == null)
			airport = new Airport();
		return airport;	
	}
	public boolean addFlight(Flight fl) {
		if (flightcount == MAX_FLIGHT_COUNT) {
			System.out.println("unable to add Flight. airport is full");
			return false;
		}
		else {
			f.add(fl);
			flightcount++;
			Collections.sort(f);
			System.out.println("flight added");
			return true;
		}
	}
	
	

}
