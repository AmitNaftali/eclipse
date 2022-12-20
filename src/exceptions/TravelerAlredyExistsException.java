package exceptions;

public class TravelerAlredyExistsException extends Exception{
	//thrown when trying to add an exists traveler to a flight 

	public TravelerAlredyExistsException(String str)
	{
		super(str);
	}
}
