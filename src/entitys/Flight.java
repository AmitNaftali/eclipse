package entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Flight implements Comparable<Flight>,Serializable{
	private int id; // unique id
	private ArrayList<Traveler> t;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		if(seatCount==0 && !t.remove(tr)) {
			System.out.println("unable to remove traveler. no such traveler in this flight");
			return false;
		}
		else {
			seatCount--;
			System.out.println("traveler removed");
			return true;
		}
	}
	public void changeFlight(Flight other)
	{
		this.departureTime=other.departureTime;
		this.landingTime=other.landingTime;
		this.p=other.p;
		this.t=other.t;
		this.seatCount=other.seatCount;
		this.destination=other.destination;
	}
	
	//compare by departure time
	@Override
	public int compareTo(Flight f) {
		return Double.compare(this.departureTime, f.departureTime);
	}

	@Override
	public String toString() {
		return "Flight [travelers=" + t + ",\nseatCount=" + seatCount + ",\ndepartureTime=" + departureTime + ",\nlandingTime="
				+ landingTime + ",\np=" + p + ",\ndestination=" + destination + "]";
	}

}