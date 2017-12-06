package orderedList;

import java.io.Serializable;
import java.util.Iterator;

import javax.swing.table.TableColumn;

public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T>, Serializable
{
	
	/**
	 * Adds the specified element to this list at the proper location.
	 * @param element the element to be added to this list.
	 * @throws NonComparableElementException 
	 */
	public void add(T element) throws NonComparableElementException
	{
		
		if (!(element instanceof Comparable))
			throw new NonComparableElementException("OrderedList");
		
		Comparable<T> comparableElement = (Comparable<T>) element;
		
//		if (size() == list.length)
//			expandCapacity(); // TODO: add method to interface and ArrayList.java
		int scan = 0;
		
		// Find the insertion location by going through list
		// until element at list is > element to be added and < the following element
		while (scan < rear && comparableElement.compareTo(list[scan]) > 0)
			scan++;
		
		// Shift existing elements up one
		for (int shift = rear; shift > scan; shift--)
			list[shift] = list[shift - 1];
		
		// Insert new element
		list[scan] = element; // TODO null pointer exception here
		rear++;
		modCount++;
		count++;
		
	}

}
