package exceptions;

//thrown when trying to delete an unfounded flight in the airport
public class FlightNotFoundException extends Exception{
	public FlightNotFoundException(String str)
	{
		super(str);
	}
}
