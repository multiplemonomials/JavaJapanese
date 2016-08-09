package com.stackoverflow;



import com.google.common.collect.HashMultimap;

import java.util.Set;

/**
 * Many-to-many map supporting reverse lockup
 * 
 * Modified from http://stackoverflow.com/questions/20390923/do-we-have-a-multibimap
 * @param <Key>
 * @param <Value>
 */
public class ManyMap<Key, Value>  {
	private final HashMultimap<Key, Value> keysToValues;

	private final HashMultimap<Value, Key> valuesToKeys;

	public Set<Value> getValues(Key key) {
		return keysToValues.get(key);
	}

	/**
	 * Get the first Value in the ManyMap for this Key. As ordering is random, only use this function when you know that there will only be one value for this key.
	 * @param key
	 * @return
	 */
	public Value getFirstValue(Key key) {
		return keysToValues.get(key).iterator().next();
	}

	public Set<Key> getKeys(Value value) {
		return valuesToKeys.get(value);
	}

	/**
	 * Get the first Key in the ManyMap for this Value. As ordering is random, only use this function when you know that there will only be one key for this value.
	 * @param value
	 * @return
	 */
	public Key getFirstKey(Value value) {
		return valuesToKeys.get(value).iterator().next();
	}


	/**
	 * Adds the key-value pair to the map.
	 *
	 * If it already exists, nothing is changed.
	 * @param key
	 * @param value
	 * @return true if the ManyMap changed.
	 */
	public boolean put(Key key, Value value) {

		//things get a little too confusing if we allow multiple identical pairs
		if(keysToValues.containsEntry(key, value))
		{
			return false;
		}

		return keysToValues.put(key, value) && valuesToKeys.put(value, key);
	}

	/**
	 * Remove this key and all values it is used for
	 * @param key
	 * @return true if elements were removed, false otherwise
	 */
	public boolean removeAllValues(Key key)
	{
		Set<Value> values = getValues(key);

		if(values.isEmpty())
		{
			//no values for this key
			return false;
		}

		for(Value val : values)
		{
			valuesToKeys.remove(val, key);
		}
		keysToValues.removeAll(key);

		return true;
	}

	/**
	 * Remove this value and all keys it is used for
	 * @param val
	 * @return true if elements were removed, false otherwise
	 */
	public boolean removeAllKeys(Value val)
	{
		Set<Key> keys = getKeys(val);

		if(keys.isEmpty())
		{
			//no keys for this value
			return false;
		}

		for(Key key : keys)
		{
			keysToValues.remove(key, val);
		}
		valuesToKeys.removeAll(val);

		return true;
	}

	/**
	 * Remove this key-value pairing
	 * @param key
	 * @param value
	 * @return true if elements were removed, false otherwise
	 */
	public boolean remove(Key key, Value value)
	{
		keysToValues.remove(key, value);
		return valuesToKeys.remove(value, key);

	}

	/**
	 * Create a ManyMap between the index and value of elements in an array
	 * @param array
	 * @param <Value>
	 * @return
	 */
	public static <Value> ManyMap<Integer, Value> fromArray(Value[] array)
	{
		ManyMap<Integer, Value> map = new ManyMap<Integer, Value>();
		for(int index = 0; index < array.length; ++index)
		{
			map.put(index, array[index]);
		}
		return map;
	}

	public boolean putAll(Key key, Iterable<? extends Value> values) {
		boolean changed = false;
		for (Value value : values) {
			changed = put(key, value) || changed;
		}
		return changed;
	}

	/**
	 * Copies the other multimap. Elements will still reference the same objects, but additions to or removals from the new map will not affect the old one.
	 * @param other
	 */
	public ManyMap(ManyMap<Key, Value> other)
	{
		keysToValues = HashMultimap.create(other.keysToValues);
		valuesToKeys = HashMultimap.create(other.valuesToKeys);

	}

	/**
	 * Creates an empty ManyMap.
	 *
	 * Gee, a normal constructor on a collection.  What an oddity!
	 */
	public ManyMap()
	{
		keysToValues = HashMultimap.create();
		valuesToKeys = HashMultimap.create();
	}

	public boolean containsKey(Key key)
	{
		return keysToValues.containsKey(key);
	}

	public boolean containsValue(Value value)
	{
		return valuesToKeys.containsKey(value);
	}

	public Set<Key> keySet()
	{
		return keysToValues.keySet();
	}

	public Set<Value> valueSet()
	{
		return valuesToKeys.keySet();
	}
}

