package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dal.FileDao;
import entitys.Flight;
import entitys.Traveler;

//service
@Component
public class TravelerService {
	
	@Qualifier()//TravelerFileDao
	@Autowired
	private FileDao dependency;
	
	public List<Flight> getAll() throws Exception {
		return dependency.getAll();
	}

	public void save(Flight t) throws Exception {
		dependency.save(t);
	}

	public void update(Traveler t, int flightId) throws Exception {
	}

	public void delete(int id) throws Exception {
		
	}

	public Traveler get(int id) throws Exception {
		return null;
	}
}