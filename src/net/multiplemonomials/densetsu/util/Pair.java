package net.multiplemonomials.densetsu.util;

/**
 * This class is useful when one wants to return two values from one function. 
 * I can't understand why it is not in the Java Standard Library .
 * 
 * 
 * Supports equals() and hashCode() comparison, so can be used in HashMap's.
 * @author Jamie
 *
 */
public class Pair<L, R>
{
	public L first;
	
	public R second;
	
	public Pair(L left, R right)
	{
		this.first = left;
		this.second = right;
	}

	public Pair()
	{

	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Pair<?, ?>)
		{
			Pair<?, ?> other = ((Pair<?, ?>)obj);
			return other.first.equals(first) && other.second.equals(second);
		}
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return first.hashCode() * 41 + second.hashCode();
	}
}
