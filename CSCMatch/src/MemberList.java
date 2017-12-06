import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import orderedList.ElementNotFoundException;
import orderedList.NonComparableElementException;


/**
 * Represents the MemberList that stores all the members in the CSC Match program.
 * 
 * @author Team 1 - Jake Tran, Levi Sullivan, Pedro Mendoza, Sang Hoo Lee, Boyan Xu
 *
 */

public class MemberList implements Iterable<Member>, Serializable
{
	private LinkedList<Member> memberList;
	private int count;
		
	// Constructor
	public MemberList()
	{
		memberList = new LinkedList<Member>();
		count = 0;
	}
	
	
	
	// *************************** //
	//							   //
	// --------- METHODS --------- //
	//							   //
	// *************************** //
	
	public void addMember(String name, int year)
	{
		Member member = new Member(name, year);
		memberList.add(member);
		count++;
	}
	
	public boolean isNameUnique(String newName)
	{
		boolean unique = true;
		for (Member member : memberList)
		{
			if (member.getName().toLowerCase().equals(newName.toLowerCase()))
				unique = false;
		}
		return unique;
	}
	
	public void removeMember(String name)
	{	
		boolean exists = false;
		for(Member m : memberList)
		{
		    if (m.getName().equals(name))
		    {
		    	exists = true;
		    	memberList.remove(m);
		    	System.out.println(name + " has been removed.");
		    }
		}
		
		if (!exists)
			System.out.println(name + " does not exist.");
		
		count--;
	}
	
	public void addInterestToMember(String name, String topic, int level)
	{
		boolean exists = false;
		for(Member m : memberList)
		{
		    if (m.getName().equals(name))
		    {
		    	exists = true;
		    	try
				{
					m.addInterest(topic, level);
				} catch (ElementNotFoundException | NonComparableElementException e)
				{
					e.printStackTrace();
				}
		    	System.out.println("This interest was added to " + name + ":");
		    	System.out.println("Topic: " + topic);
		    	System.out.println("Interest Level: " + level);
		    }
		}
		
		if (!exists)
			System.out.println(name + " does not exist.");
	}

	@Override
	public Iterator<Member> iterator() {
		return memberList.iterator();
	}
	
	
	public void save(String fileName) throws IOException
	{
		if(!memberList.isEmpty()){
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.flush();
			oos.close();
			System.out.println();
			System.out.println("CSC Match successfully saved!");
		}
		else {
			System.out.println();
			System.out.println("There are no data to save");
		}
	}
	public static MemberList load(String fileName) throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		MemberList memberlist = (MemberList) ois.readObject();
		memberlist.toString();
		ois.close();
		return memberlist;
		
	}
	
	
	
	
	
	
	
	// *************************** //
	//							   //
	// -------- GETS/SETS -------- //
	//							   //
	// *************************** //
	
	public LinkedList<Member> getMemberList()
	{
		return memberList;
	}

	public int getCount()
	{
		return count;
	}
	
	
}