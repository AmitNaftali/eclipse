package entitys;

import java.util.ArrayList;
import java.util.Collections;

public class Airport {
	private String name;
	private int flightcount;
	private ArrayList<Flight> f;

	public Airport(String name, int flightcount) {
		this.name = name;
		this.flightcount = 0;
		this.f = new ArrayList<Flight>();
	}

	public boolean addFlight(Flight fl) {
		if (flightcount == 30) {
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
