package orderedList;

/**
 * OrderedListADT defines an interface for an ordered list collection.
 * Only comparable elements are stored, kept in the order determined by the
 * inherent relationship among the elements.
 * 
 * @author Jake Tran
 */

public interface OrderedListADT<T> extends ListADT<T>
{

	/**
	 * Adds the specified element to this list at the proper location.
	 * @param element the element to be added to this list.
	 * @throws NonComparableElementException 
	 */
	public void add(T element) throws NonComparableElementException;
	
}
