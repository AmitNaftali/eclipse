package dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


import entitys.Airport;
import entitys.Flight;




public class AirportFileDao implements FileDao{

	@Override
	public List<Flight> getAll() throws Exception {
		fillAirport();
		return Airport.getInstance().getFlights();
	}

	@Override
	public void save(Flight flight) throws Exception {
		Airport.getInstance().addFlight(flight);
		serialize();
	}

	@Override
	public void update(Flight flight) throws Exception {
		fillAirport();
		Airport.getInstance().updateFlight(flight);
		serialize();
	}

	@Override
	public void delete(int flightId) throws Exception {
		fillAirport();
		Airport.getInstance().deleteFlight(flightId);
		serialize();
	}

	@Override
	public Flight get(int flightId) throws Exception {
		fillAirport();
		return Airport.getInstance().getFlight(flightId);
	}
	
	private void fillAirport() throws Exception
	{
		try {
			deserialize();
		}catch(Exception e)//file empty
		{
			serialize();
		}
	}

	private void serialize() throws Exception
	{
		 String filename = "./airport.dat";  
        //Saving of object in a file
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        // Method for serialization of object
        out.writeObject(Airport.getInstance());
        out.close();
        file.close();   
	}
	private void deserialize() throws Exception
	{
		// Add the student object as a model attribute
		// Deserialization
		String filename = "./airport.dat"; 
        // Reading the object from a file
        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file);
        // Method for deserialization of object
        Airport.setAirport((Airport)in.readObject());
        in.close();
        file.close();
	}	
}
