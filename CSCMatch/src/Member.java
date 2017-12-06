import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

import javax.naming.spi.DirStateFactory.Result;

import orderedList.ArrayOrderedList;
import orderedList.ElementNotFoundException;
import orderedList.NonComparableElementException;

/**
 * Represents a Member in the CSC Match program.
 * 
 * @author Team 1 - Jake Tran, Levi Sullivan, Pedro Mendoza, Sang Hoo Lee, Boyan Xu
 *
 */

public class Member implements Serializable, Comparable<Member>
{
	private String name;
	private int year;
	private int compatibilityScore = 0;
	
	ArrayOrderedList<Member> favoritesList;
	LinkedList<Interest> interestList;
	
	
	// Constructor
	public Member(String name, int year)
	{
		this.name = name;
		this.year = year;
		this.compatibilityScore = 0;
		
		favoritesList = new ArrayOrderedList<>();
		interestList = new LinkedList<Interest>();

	}
	
	
	// *************************** //
	//							   //
	// --------- METHODS --------- //
	//							   //
	// *************************** //
	
	public void addInterest(String topic, int level) throws ElementNotFoundException, NonComparableElementException
	{
		boolean copy = false;
		Interest oldInterest = null;
		Interest newInterest = new Interest(topic, level);
		
		// Checks if the new interest is already in the interestList
		for (Interest interest : interestList)
		{
			if (topic.toLowerCase().equals(interest.getTopic()))
			{
				copy = true;
				oldInterest = interest;
			}
		}
		
		// If new interest is already in interestList, remove the old and insert the new interest
		if (copy == true)
		{
			interestList.remove(oldInterest);
		}
		
		interestList.add(newInterest);
	}

	private String printTopMatches(MemberList memberList)
	{
		int count = 1;
		String result = "";
		
		computeTopFiveMatches(memberList);
		
		result += "\nTop five matches: \n";
		
		// Iterates through this member's favoritesList and prints out the top five members in it
		for (Member member : favoritesList)
		{
			result += count + ". " + member.getName() + " " + member.getCompatibilityScore() + "\n";
			count++;
			
			if (count > 5)
				break;
		}
		
		// If favoritesList is null, prints out message
		if (result == "")
		{
			result = "There are no other members in CSC Match to match with " + getName() + "\n";
		}
		
		return result;
	}
	
	private void computeTopFiveMatches(MemberList memberList)
	{
		// Iterates through the other members and computes each member's compatibility to this member,
		// then adds the member to this member's topFiveList
		for (Member member : memberList)
		{
			if (!member.equals(this))
			{
				member.setCompatibilityScore(computeCompatibility(member));
				try
				{
					favoritesList.add(member);
				} catch (NonComparableElementException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	private int computeCompatibility(Member otherMember)
	{
		compatibilityScore = 0;
		boolean found = false;
		
		// Iterate through other member's interests
		for (Interest intB : otherMember.getInterestList())
		{
			// Iterate through this member's interests
			for (Interest intA : this.interestList)
			{
				if (intB.getTopic().toLowerCase().equals(intA.getTopic().toLowerCase()))
				{
					compatibilityScore += intB.getLevel() * intA.getLevel();
					found = true;
				}
			}
			
			// If the other member has an interest this member doesn't
			if (found == false)
				compatibilityScore += intB.getLevel() / 2;
			
			found = false;
		}
		
		return compatibilityScore;
	}

	public String toString(MemberList memberList)
	{
		String result =
				"\n" +
				"Name: " + name + "\n" +
				"Year: " + year + "\n";
		
		if (interestList.isEmpty())
			result += "\nThis user has no interests.\n";
		else {
			result += "\nInterests: " + "\n";
			result += printInterests();
/*			for (Interest interest : interestList)
				result += interest.toString() + "\n";*/
		}
		
		result += printTopMatches(memberList);
		
		return result;
	}
	
	@Override
	public int compareTo(Member otherMember)
	{
		if (compatibilityScore < otherMember.getCompatibilityScore())
			return 1;
		else if (compatibilityScore > otherMember.getCompatibilityScore())
			return -1;
		else
			return 0;
	}
	
	// If otherMember's name matches this member's name, return true.
	// Return false otherwise
	public boolean equals(Member otherMember)
	{
		if (this.getName().toLowerCase().equals(otherMember.getName().toLowerCase()))
			return true;
		else
			return false;
	}
	
	public String printInterests()
	{
		String result = "";
		int n;
		int count = 1;
		
		for (n = 10; n >= 0; n--)
		{
			for (Interest interest : interestList)
			{
				if (interest.getLevel() == n)
				{
					result += count + ". " + interest.getTopic() + " " + n + "\n";
					count++;
				}
			}
		}
		
		if (result == "")
			result = "This user has no interests";
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// *************************** //
	//							   //
	// -------- GETS/SETS -------- //
	//							   //
	// *************************** //
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public int getCompatibilityScore()
	{
		return compatibilityScore;
	}
	public void setCompatibilityScore(int compatibilityScore)
	{
		this.compatibilityScore = compatibilityScore;
	}
	public LinkedList<Interest> getInterestList()
	{
		return interestList;
	}
	public void setInterestList(LinkedList<Interest> interestList)
	{
		this.interestList = interestList;
	}
	
}