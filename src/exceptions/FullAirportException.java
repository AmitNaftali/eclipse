package exceptions;

//thrown when the airport is full of flights and trying to add a new one
public class FullAirportException extends Exception{
	public FullAirportException(String str)
	{
		super(str);
	}
}
