package entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.FullFlightException;
import exceptions.TravelerAlreadyExistsException;
import exceptions.TravelerNotFoundException;

public class Flight implements Comparable<Flight>, Serializable {
	private int id; // unique id
	private ArrayList<Traveler> travelers;
	private double departureTime;
	private double landingTime;
	private Plane plane;
	private String destination;

	public Flight(int id,double departureTime, double landingTime, Plane p, String destination) {
		this.id = id;
		this.departureTime = departureTime;
		this.landingTime = landingTime;
		this.plane = p;
		this.travelers = new ArrayList<Traveler>();
		this.destination = destination;
	}
	
	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public ArrayList<Traveler> getTravelers() {
		return travelers;
	}
	public void setLandingTime(double landingTime) {
		this.landingTime = landingTime;
	}

	public boolean addTraveler(Traveler traveler){
		travelers.add(traveler);
		return true;

	}

	public boolean removeTraveler(Traveler traveler) {
		return travelers.remove(traveler);
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
	public int compareTo(Flight flight) {
		return Double.compare(this.id, flight.id);
	}

	@Override
	public String toString() {
		return "Flight : id=" + id + ", destination = " + destination
				+", departureTime = "
				+ String.valueOf(departureTime).replace('.', ':') + ", landingTime = " + String.valueOf(landingTime).replace('.', ':')
				+ "\n" + "travelers:" + travelers;
	}

	

}