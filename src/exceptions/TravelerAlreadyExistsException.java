package exceptions;

public class TravelerAlreadyExistsException extends Exception{
	//thrown when trying to add an exists traveler to a flight 

	public TravelerAlreadyExistsException(String str)
	{
		super(str);
	}
}
