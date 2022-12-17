package exceptions;

//thrown when a flight is full of travelers and trying to add a new one
public class FullFlightException extends Exception{
	public FullFlightException(String str)
	{
		super(str);
	}
}
