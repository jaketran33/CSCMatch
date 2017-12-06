import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
// TODO import other stuff

/**
 * Represents a menu class that the controls the user interaction with the program.
 * 
 * @author Team 1 - Jake Tran, Levi Sullivan, Pedro Mendoza, Sang Hoo Lee, Boyan Xu 
 *
 */

// TODO: Take out all log messages when done

public class Menu implements Serializable
{
	private boolean saved; 			// changes to false every time a member is added, an interest is added, etc.
	private boolean initialLaunch;  // displays new or load menu on first launch.
	private boolean again = true; 	// loops menu again if user choice is invalid
	private Scanner kb;
	private MemberList memberList;
	
	// Constructor
	public Menu() 
	{
		saved = true; 
		initialLaunch = true;
		kb = new Scanner(System.in);
		memberList = new MemberList();
	}
	
	
	
	// *************************** //
	//							   //
	// --------- METHODS --------- //
	//							   //
	// *************************** //

	public void displayMenu() throws IOException
	{
		do {
			String choice;
			
			if(initialLaunch == true)
				displayNewLoadMenu();
			
			// Prints the menu options
			System.out.println();
			System.out.println("Choose from one of the following options by entering in the number:");
			System.out.println("1. Load");
			System.out.println("2. Save");
			System.out.println("3. Quit");
			System.out.println("4. List all members");
			System.out.println("5. List a member");
			System.out.println("6. Add a member");
			System.out.println("7. Remove a member");
			System.out.println("8. Add an interest to a member");
			
			choice = kb.nextLine();
			
			// Calls the corresponding method based on the user's choice
			switch (choice)
			{
				case "1":
					load();
					again = false;
					break;
				case "2":
					save(true);
					again = false;
					break;
				case "3":
					quit();
					again = false;
					break;
				case "4":
					listAllMembers();
					again = false;
					break;
				case "5":
					listMember();
					again = false;
					break;
				case "6":
					addMember();
					again = false;
					break;
				case "7":
					removeMember();
					again = false;
					break;
				case "8":
					addInterestToMember();
					again = false;
					break;
				default:
					System.out.println();
					again = true;
					displayMenu();
		}
		} while (again == true);

	}

	// Displays option to start a new file or load a saved one on the initial launch of the program
	private void displayNewLoadMenu() throws IOException
	{
		do {
			String choice;
			
			System.out.println("Choose from one of the following options by entering in the number:");
			System.out.println("1. Load a previously saved CSC Match file");
			System.out.println("2. Start a new CSC Match file");
			
			choice = kb.nextLine();
			
			switch (choice)
			{
				case "1":
					load();
					again = false;
					break;
				case "2":
					again = false;
					break;
				default:
					System.out.println();
					System.out.println("ERROR: Please choose a valid choice \n");
					again = true;
			}
		} while (again == true);
		
		initialLaunch = false;
	}

	private void quit() throws IOException
	{
		String choice;
		System.out.println("Are you sure you want to quit? y or n?");
		
		choice = kb.nextLine();
		
		if (choice.toLowerCase().equals("n"))
			displayMenu();
		else {
			do {
				if (saved == false)
					askSaveChanges();
			} while (again == true);
			
			System.out.println();
			System.out.println("Goodbye.");
			System.exit(0);
		}
	}
	
	private void askSaveChanges() throws IOException
	{
		System.out.println("Do you want to save changes to CSC Match?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		
		String choice = kb.nextLine();
		
		switch (choice)
		{
			case "1":
				save(false);
				again = false;
				break;
			case "2":
				again = false;
				break;
			default:
				System.out.println();
				System.out.println("ERROR: Please choose a valid choice \n");
				again = true;
		}
	}
	
	/**
	 * Saves current state of program into a file name specified by user
	 * @param displayMenu if true, displays menu after saving
	 * @throws IOException
	 */
	private void save(boolean displayMenu) throws IOException
	{
		String file;
		
		do {
			System.out.println("Input the file name, enter 0 to cancel");
			
			file = kb.nextLine();
			if (file.equals("0"))
				again = false;
			else
			{
				try
				{
					memberList.save(file);
					again = false;
				} catch (IOException e)
				{
					System.out.println("ERROR: Invalid file name");
					again = true;
				}
			}
		} while (again == true);
		

		saved = true;
		
		if (displayMenu == true)
			displayMenu();
	}

	private void load() throws IOException
	{
		String file;
		
		do {
			if (saved == false) {
				System.out.println("");
				askSaveChanges();
			}
			
			System.out.println();
			System.out.println("Input file a to load, enter 0 to cancel");
			
			file = kb.nextLine();
			if (file.equals("0"))
				again = false;
			else
			{
			try{
				memberList = MemberList.load(file);
				again = false;
			}
			catch(ClassNotFoundException | IOException cnf){
				System.out.println("ERROR: File Not Found");
				again = true;
			}
			}
		} while (again == true);
		
		initialLaunch = false;
		
		displayMenu();
	}
	
	private void listAllMembers() throws IOException
	{
		System.out.println();
		System.out.println("Current Members: ");
		System.out.println();
		if (memberList.getCount() == 0)
			System.out.println("There are zero members.");
		else 
		{
			for (Member member : memberList.getMemberList())
				System.out.println(member.getName());
		}
		
		displayMenu();
	}
	
	private void listMember() throws IOException
	{
		
		String name;
		again = false;
		boolean found = false;
		Member current = null;
		
		do {
			System.out.print("Enter the name of the member: ");
			name = kb.nextLine();
			
			for (Member member : memberList.getMemberList())
			{
				if (name.toLowerCase().equals(member.getName().toLowerCase()))
				{
					found = true;
					current = member;
					again = false;
					break;
				}
				else
				{
					found = false;
					again = true;
				}
			}
			
			if (found == false)
			{
				System.out.println();
				System.out.println("ERROR: Member does not exist");
			}
			
		} while (again == true);

		if (found == true)
			System.out.println(current.toString(memberList));
		
		displayMenu();
	}
	
	private void addMember() throws IOException
	{
		String name = askForName();
		int iYear = askForYear();
		
		memberList.addMember(name, iYear);
		
		System.out.println("Member successfully added!");
		System.out.println("Name:  " + name);
		System.out.println("Year:  " + iYear);
		
		setSaved(false);
		displayMenu();
	}

	// Asks for name of new member and loops if name isn't unique
	private String askForName()
	{
		String name;

		do {
			System.out.print("Enter the name of the new member: ");
			name = kb.nextLine();
			
			if (memberList.isNameUnique(name) == false)
			{
				System.out.println();
				System.out.println("ERROR: Name is not unique, please try again.");
				again = true;
			} else
				again = false;
		} while (again == true);
		
		return name;
	}
	
	// Asks for the year of the member and loops if it's an invalid year
	private int askForYear()
	{
		String year;
		int iYear = 0;
		
		do {
			System.out.println();
			System.out.println("Enter the year of the member: ");
			System.out.println("1. Freshman");
			System.out.println("2. Sophmore");
			System.out.println("3. Junior");
			System.out.println("4. Senior");
			System.out.println("5. CS Graduate");

			
			year = kb.nextLine();
			
			switch (year) {
				case "1":
					iYear = 1;
					again = false;
					break;
				case "2":
					iYear = 2;
					again = false;
					break;
				case "3":
					iYear = 3;
					again = false;
					break;
				case "4":
					iYear = 4;
					again = false;
					break;
				case "5":
					iYear = 5;
					again = false;
					break;
				default:
					System.out.println();
					System.out.println("ERROR: Please enter a year between 1-5");
					again = true;
					break;
			}
		} while (again == true);
		
		return iYear;
	}
	
	private void removeMember() throws IOException
	{
		String name;
		System.out.print("Enter the name of the member to be removed: ");
		name = kb.nextLine();
		memberList.removeMember(name);
		
		setSaved(false);
		displayMenu();
	}
	
	// TODO: doesn't check if member exists initially
	private void addInterestToMember() throws IOException
	{
		String name;
		String topic;
		String level;
		int iLevel = 0;
		
		System.out.print("Enter the name of the member: ");
		name = kb.nextLine();
		System.out.print("Enter the name of the interest: ");
		topic = kb.nextLine();
		
		do {
			System.out.println("Enter the interest level from 0-10 (least to most):");
			level = kb.nextLine();
			
			switch (level)
			{
				case "0":
					iLevel = 0;
					again = false;
					break;
				case "1":
					iLevel = 1;
					again = false;
					break;
				case "2":
					iLevel = 2;
					again = false;
					break;
				case "3":
					iLevel = 3;
					again = false;
					break;
				case "4":
					iLevel = 4;
					again = false;
					break;
				case "5":
					iLevel = 5;
					again = false;
					break;
				case "6":
					iLevel = 6;
					again = false;
					break;
				case "7":
					iLevel = 7;
					again = false;
					break;
				case "8":
					iLevel = 8;
					again = false;
					break;
				case "9":
					iLevel = 9;
					again = false;
					break;
				case "10":
					iLevel = 10;
					again = false;
					break;
				default:
					System.out.println();
					System.out.println("ERROR: Please enter an interest level between 0-10");
					again = true;
			}
		} while (again == true);
		
		memberList.addInterestToMember(name, topic, iLevel);
		
		setSaved(false);
		displayMenu();
	}

	public boolean isSaved()
	{
		return saved;
	}

	private void setSaved(boolean saved)
	{
		this.saved = saved;
	}
	
	
}