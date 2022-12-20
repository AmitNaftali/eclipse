package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dal.FileDao;
import entitys.Flight;
import entitys.Traveler;
import exceptions.FlightAlreadyExistException;
import exceptions.FlightNotFoundException;
import exceptions.FullFlightException;


//service
public class TravelerService {
	@Autowired
	private FileDao dependency;
	private final int MAX_TRAVELERS = 100;
    private final int MAX_FLIGHTS = 30;
	
	public List<Flight> getAll() throws Exception {
		return dependency.getAll();
	}

	public void save(Flight f) throws Exception {
		if(((ArrayList<Flight>)dependency.getAll()).contains(f))
			throw new FlightAlreadyExistException(f.toString());
		if(MAX_FLIGHTS <= dependency.getAll().size())
			throw new FullFlightException(MAX_FLIGHTS + "");
		dependency.save(f);
	}

	public void update(int id,Flight flight) throws Exception {
		for(Flight f : (ArrayList<Flight>)dependency.getAll())
			if(f.getId() == id) {
				 dependency.update(id, flight);
				 return;
			}
		throw new FlightNotFoundException(flight.toString());
	}

	public void delete(int id) throws Exception {
		for(Flight f : (ArrayList<Flight>)dependency.getAll())
			if(f.getId() == id) {
				 dependency.delete(id);
				 return;
			}
		throw new FlightNotFoundException("id = " + id);
	}

	public Flight get(int id) throws Exception {
		for(Flight f : (ArrayList<Flight>)dependency.getAll())
			if(f.getId() == id) {
				 return dependency.get(id);
			}
		throw new FlightNotFoundException("id = " + id);
	}
}
