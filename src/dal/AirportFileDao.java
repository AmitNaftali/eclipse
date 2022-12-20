package dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import entitys.Airport;
import entitys.Flight;




public class AirportFileDao implements FileDao{
	private Airport airport;

	@Override
	public List<Flight> getAll() throws Exception {
		fillAirport();
		return airport.getFlights();
	}

	@Override
	public void save(Flight f) throws Exception {
		airport.addFlight(f);
		serialize();
	}

	@Override
	public void update(int id,Flight f) throws Exception {
		fillAirport();
		for (int i = 0; i < airport.getFlights().size(); i++) {
			if(airport.getFlights().get(i).getId() == id)
			{
				airport.getFlights().get(i).changeFlight(f);
			}
		}
		serialize();
	}

	@Override
	public void delete(int id) throws Exception {
		fillAirport();
		for (int i = 0; i < airport.getFlights().size(); i++) {
			if(airport.getFlights().get(i).getId() == id)
			{
				airport.getFlights().remove(airport.getFlights().get(i));
			}
		}
		serialize();
	}

	@Override
	public Flight get(int id) throws Exception {
		fillAirport();
		for (int i = 0; i < airport.getFlights().size(); i++) {
			if(airport.getFlights().get(i).getId() == id)
			{
				return airport.getFlights().get(i);
			}
		}
		return null; // didnt found
	}
	
	private void fillAirport() throws Exception
	{
		try {
			deserialize();
		}catch(Exception e)//file empty
		{
			Airport airport = Airport.getInstance();
			serialize();
		}
	}

	private void serialize() throws Exception
	{
		 String filename = "airport.dat";  
        //Saving of object in a file
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        // Method for serialization of object
        out.writeObject(airport);
        out.close();
        file.close();   
        System.out.println("Object has been serialized");
	}
	private void deserialize() throws Exception
	{
		if(airport == null) {
			// Add the student object as a model attribute
			// Deserialization
			String filename = "airport.dat"; 
	        // Reading the object from a file
	        FileInputStream file = new FileInputStream(filename);
	        ObjectInputStream in = new ObjectInputStream(file);
	        // Method for deserialization of object
	        airport = (Airport)in.readObject();
	        in.close();
	        file.close();
		}		
	}
	
}
