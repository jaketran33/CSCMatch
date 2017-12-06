package orderedList;


import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayList represents an array implementation of a list. The front of
 * the list is kept at array index 0. This abstract class will be extended to
 * create a specific kind of list.
 * @author Jake Tran
 *
 * @param <T>
 */

public abstract class ArrayList<T> implements ListADT<T>, Iterable<T>, Serializable
{

	private final static int DEFAULT_CAPACITY = 100;
	private final static int NOT_FOUND = -1;
	protected int rear;
	protected T[] list;
	protected int modCount;
	protected int count;
	
	/**
	 * Creates an empty list using the default capacity.
	 */
	public ArrayList()
	{
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Creates an empty list using the specified capacity.
	 * @param initialCapacity
	 */
	public ArrayList(int initialCapacity)
	{
		rear = 0;
		list = (T[]) (new Object[initialCapacity]);
		modCount = 0;
		count = 0;
	}
	
	/**
	 * Removes and returns the specified element.
	 * 
	 * 
	 * @throws ElementNotFoundException 
	 */
	public T remove(T element) throws ElementNotFoundException
	{
		T result;
		int index = find(element);
		
		if (index == NOT_FOUND)
			throw new ElementNotFoundException("ArrayList");
		
		result = list[index];
		rear--;
		
		// shift appropriate elements
		shiftElementsBack(index);
		list[rear] = null;
		modCount++;
		count--;
		return result;
	}
	
	/**
	 * Removes and returns the first element from the list.
	 * @return element at the beginning of the list.
	 */
	public T removeFirst()
	{
		T result = list[0];
		shiftElementsBack(0);
		rear--;
		modCount++;
		count--;
		return result;
	}

	/**
	 * Removes and returns the last element from the list.
	 * @return
	 */
	public T removeLast()
	{
		T result = list[rear - 1];
		list[rear - 1] = null;
		rear--;
		modCount++;
		count--;
		return result;
	}

	/**
	 * Examines the element at the front of the list.
	 * @return
	 */
	public T first()
	{
		return list[0];
	}

	/**
	 * Examines the element at the back of the list.
	 * @return
	 */
	public T last()
	{
		return list[rear - 1];
	}

	/**
	 * Returns true if this list contains the specified element.
	 */
	public boolean contains(T target)
	{
		return (find(target) != NOT_FOUND);
	}

	/**
	 * Determines if list is empty.
	 * @return
	 */
	public boolean isEmpty()
	{
		return (rear == 0);
	}

	/**
	 * Determines number of elements in the list.
	 * @return
	 */
	public int size()
	{
		return count;
	}

	/**
	 * Returns an iterator for the elements in this list.
	 * 
	 * @return an iterator over the elements in this list
	 */
	public Iterator<T> iterator() // TODO: probably wrong
	{
		ArrayListIterator iterator = new ArrayListIterator();
		return iterator;
	}
	
	/**
	 * Expands the capacity of the list by increasing the size by two.
	 */
	public void expandCapacity()
	{
		list = Arrays.copyOf(list, list.length * 2);
	}

	/**
	 * Returns the array index of the specified element
	 * or the constant NOT_FOUND if it is not found.
	 * @param target
	 * @return the index of the target element, or the NOT_FOUND constant
	 */
	private int find(T target)
	{
		int scan = 0;
		int result = NOT_FOUND;
		
		// Scans through list[] until element matches the target
		// If no match is found, the result equals NOT_FOUND
		if (!isEmpty())
			while (result == NOT_FOUND && scan < rear)
				if (target.equals(list[scan]))
					result = scan;
				else
					scan++;
		
		return result;
	}
	
	/**
	 * Shift appropriate elements
	 * @param index
	 */
	private void shiftElementsBack(int index)
	{
		for (int scan = index; scan < rear; scan++)
			list[scan] = list[scan + 1];
	}

	/**
	 * ArrayListIterator iterates over the elements of an ArrayList.
	 * @author Jake Tran
	 */
	private class ArrayListIterator implements Iterator
	{
		int iteratorModCount;
		int current;
		
		/**
		 * Sets up this iterator using the specified modCount.
		 */
		public ArrayListIterator()
		{
			iteratorModCount = modCount;
			current = 0;
		}
		
		/**
		 * Returns true of this iterator has at least one more element to deliver in the iteration.
		 */
		@Override
		public boolean hasNext() throws ConcurrentModificationException
		{
			if (iteratorModCount != modCount)
				throw new ConcurrentModificationException();
			
			return (current < rear);
		}

		/**
		 * Returns the next element in the iteration. If there are no more elements in this iteration,
		 * a NoSuchElementException is thrown.
		 */
		@Override
		public T next() throws ConcurrentModificationException
		{	
			if (!hasNext())
				throw new NoSuchElementException();
			
			current++;
			
			return list[current - 1];
		}
	}
}




