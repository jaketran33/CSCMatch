package orderedList;


public class ElementNotFoundException extends Exception
{
	public ElementNotFoundException() { super(); }
	
	public ElementNotFoundException(String message)
	{
		super();
		System.out.println("ERROR: ElementNotFoundException " + message);
	}
}
