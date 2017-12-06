import java.io.IOException;

/**
 * CSCMatchTester tests the entire CSC Match project and is the interface that the user interacts with.
 * 
 * @author Team 1 - Jake Tran, Levi Sullivan, Pedro Mendoza, Sang Hoo Lee, Boyan Xu
 */


public class CSCMatchDriver
{

	public static void main(String[] args)
	{
		Menu menu = new Menu();
		
		System.out.println("Welcome to CSC Match!");
		System.out.println();
		try
		{
			menu.displayMenu();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
