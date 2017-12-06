package orderedList;

public class ArrayOrderedListTester
{
	public static void main (String[] args)
	{
		ArrayOrderedList<Character> list = new ArrayOrderedList<>();
		
		Character i1 = 'a';
		Character i2 = 'b';
		Character i3 = 'c';
		Character i4 = 'd';
		
		try
		{
			list.add(i3);
			list.add(i1);
			list.add(i2);
			list.add(i4);
			list.removeLast();
		} catch (NonComparableElementException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Character element : list)
			System.out.println(element);
	}
}
