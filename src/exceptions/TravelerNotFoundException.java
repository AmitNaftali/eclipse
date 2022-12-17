package exceptions;

//thrown when trying to remove an unfounded traveler from a flight 
public class TravelerNotFoundException extends Exception{
	public TravelerNotFoundException(String str)
	{
		super(str);
	}
}
