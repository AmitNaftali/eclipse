package entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Flight implements Comparable<Flight>, Serializable {
	private int id; // unique id
	private ArrayList<Traveler> t;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private ArrayList<Traveler> travelers;
	private int seatCount;
	private double departureTime;
	private double landingTime;
	private Plane plane;
	private String destination;

	public Flight(double departureTime, double landingTime, Plane p, String destination) {
		this.departureTime = departureTime;
		this.landingTime = landingTime;
		this.plane = p;
		this.travelers = new ArrayList<Traveler>();
		this.seatCount = 0;
		this.destination = destination;
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
		if (seatCount == plane.getSeatsNum()) {
			System.out.println("unable to add traveler. plane is full");
			return false;
		}
		travelers.add(tr);
		seatCount++;
		Collections.sort(travelers);
		System.out.println("traveler added");
		return true;

	}

	public boolean removeTraveler(Traveler tr) {
		if (seatCount == 0 && !travelers.remove(tr)) {
			System.out.println("unable to remove traveler. no such traveler in this flight");
			return false;
		} 
		seatCount--;
		System.out.println("traveler removed");
		return true;
	}
	public void changeFlight(Flight other)
	{
		this.departureTime=other.departureTime;
		this.landingTime=other.landingTime;
		this.plane=other.plane;
		this.t=other.t;
		this.seatCount=other.seatCount;
		this.destination=other.destination;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == this)
			return true;
		if(obj == null || obj.getClass() != this.getClass())
			return false;
		Flight f = (Flight)obj;
		return f.id == this.id;
	}

	@Override
	public int compareTo(Flight f) {
		return Double.compare(this.id, f.id);
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", travelers=" + travelers + ", seatCount=" + seatCount + ", departureTime="
				+ departureTime + ", landingTime=" + landingTime + ", plane=" + plane + ", destination=" + destination
				+ "]";
	}

	

}