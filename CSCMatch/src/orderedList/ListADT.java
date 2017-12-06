package orderedList;


import java.io.Serializable;
import java.util.Iterator;

/**
 * ListADT defines the interface to a general list collection. Specific types of lists will extend
 * this interface to complete the set of operations.
 * @author Jake Tran
 *
 */

public interface ListADT<T> extends Iterable<T>
{

	/**
	 * Removes and returns the first element from the list.
	 * @return element at the beginning of the list.
	 * @throws EmptyCollectionException 
	 */
	public T removeFirst() throws EmptyCollectionException;
	
	/**
	 * Removes and returns the last element from the list.
	 * @return
	 * @throws EmptyCollectionException 
	 */
	public T removeLast() throws EmptyCollectionException;
	
	/**
	 * Removes and returns a particular element from the list.
	 * @param element the element to be removed.
	 * @return
	 * @throws ElementNotFoundException 
	 * @throws EmptyCollectionException 
	 */
	public T remove(T element) throws ElementNotFoundException, EmptyCollectionException;
	
	/**
	 * Examines the element at the front of the list.
	 * @return
	 */
	public T first();
	
	/**
	 * Examines the element at the back of the list.
	 * @return
	 */
	public T last();
	
	/**
	 * Determines if the list contains a particular element.
	 * @param target
	 * @return
	 */ 
	public boolean contains(T target);
	
	/**
	 * Determines if list is empty.
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * Determines number of elements in the list.
	 * @return
	 */
	public int size();
	
	/**
	 * Returns an iterator for the elements in this list.
	 * 
	 * @return an iterator over the elements in this list
	 */
	public Iterator<T> iterator();
	
	/**
	 * Returns a string representation of this list.
	 * @return
	 */
	public String toString();
	
}
