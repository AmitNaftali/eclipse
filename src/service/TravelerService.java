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


//service
public class TravelerService {
	
	@Qualifier()//TravelerFileDao
	@Autowired
	private FileDao dependency;
	
	public List<Flight> getAll() throws Exception {
		return dependency.getAll();
	}

	public void save(Flight f) throws Exception {
		if(((ArrayList<Flight>)dependency.getAll()).contains(f))
			throw new FlightAlreadyExistException(f.toString());
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
