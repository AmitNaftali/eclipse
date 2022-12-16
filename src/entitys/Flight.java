package entitys;

import java.util.ArrayList;
import java.util.Collections;

public class Flight implements Comparable<Flight> {
	private ArrayList<Traveler> t;
	private int seatCount;
	private double departureTime;
	private double landingTime;
	private Plane p;
	private String destination;
	
	public Flight(double departureTime,double landingTime,Plane p,String destination) {
		this.departureTime=departureTime;
		this.landingTime=landingTime;
		this.p=p;
		this.t=new ArrayList<Traveler>();
		this.seatCount=0;
		this.destination=destination;
	}

	public double getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(double departureTime) {
		this.departureTime = departureTime;
	}

	public double getLandingTime() {
		return landingTime;
	}

	public void setLandingTime(double landingTime) {
		this.landingTime = landingTime;
	}
	
	public boolean addTraveler(Traveler tr) {
		if(seatCount == p.getSeatsNum()) {
			System.out.println("unable to add traveler. plane is full");
			return false;
		}
		else {
			t.add(tr);
			seatCount++;
			Collections.sort(t);
			System.out.println("traveler added");
			return true;
		}
	}
	
	public boolean removeTraveler(Traveler tr) {
		if(seatCount==0) {
			System.out.println("unable to remove traveler. no such traveler in this flight");
			return false;
		}
		else {
			if(!t.remove(tr))
				return false;
			seatCount--;
			Collections.sort(t);
			System.out.println("traveler removed");
			return true;
		}
	}

	@Override
	public int compareTo(Flight f) {
		return (int) (this.departureTime-f.getDepartureTime());
	}

}