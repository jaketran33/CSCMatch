import java.io.Serializable;

/**
 * Represents an Interest in the CSC Match program.
 * 
 * @author Team 1 - Jake Tran, Levi Sullivan, Pedro Mendoza, Sang Hoo Lee, Boyan Xu
 *
 */

public class Interest implements Comparable<Interest>, Serializable
{
	String topic;
	int level;
	
	// Constructor
	public Interest(String topic, int level)
	{
		this.topic = topic;
		this.level = level;
	}

	
	// *************************** //
	//							   //
	// --------- METHODS --------- //
	//							   //
	// *************************** //
	
	
	public String toString()
	{
		return topic + " " + level;
	}
	
	public int compareTo(Interest otherInterest)
	{	
		if (level < otherInterest.getLevel())
			return 1;
		else if (level > otherInterest.getLevel())
			return -1;
		else
			return 0;
	}
	
	
	
	
	
	
	
	
	// *************************** //
	//							   //
	// -------- GETS/SETS -------- //
	//							   //
	// *************************** //
	
	public String getTopic()
	{
		return topic;
	}

	public void setTopic(String topic)
	{
		this.topic = topic;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}
	
	
}
