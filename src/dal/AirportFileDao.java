package dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


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
	public void save(Flight flight) throws Exception {
		airport.addFlight(flight);
		serialize();
	}

	@Override
	public void update(Flight flight) throws Exception {
		fillAirport();
		airport.updateFlight(flight);
		serialize();
	}

	@Override
	public void delete(int flightId) throws Exception {
		fillAirport();
		airport.deleteFlight(flightId);
		serialize();
	}

	@Override
	public Flight get(int flightId) throws Exception {
		fillAirport();
		return airport.getFlight(flightId);
	}
	
	private void fillAirport() throws Exception
	{
		try {
			deserialize(Airport.getInstance());
			if(Airport.getInstance() == null)
				throw new Exception();
		}catch(Exception e)//file empty
		{
			airport = Airport.getInstance();
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
	private void deserialize(Airport airport) throws Exception
	{
		// Add the student object as a model attribute
		// Deserialization
		String filename = "./airport.dat"; 
        // Reading the object from a file
        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file);
        // Method for deserialization of object
        airport = (Airport)in.readObject();
        in.close();
        file.close();
	}

	@Override
	public void saveAirport() throws Exception {
		serialize();
	}

	
	public String printAirport() throws Exception {	
		fillAirport();
		return airport.toString();
	}
	
}
