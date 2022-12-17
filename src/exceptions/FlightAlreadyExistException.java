package exceptions;

//thrown when trying to add flight with same destination and time to the airport
public class FlightAlreadyExistException extends Exception{
	public FlightAlreadyExistException(String str)
	{
		super(str);
	}
	
	
}
