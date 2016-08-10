package net.multiplemonomials.javajapanese.util;

/**
 * Created by Jamie on 3/6/2016.
 */
public class Utils
{
	/**
	 * Returns true if the provided character is a vowel.
	 * Ignores case.
	 * For the purposes of this function, Y is not a vowel.
	 *
	 * @param toCheck
	 * @return
	 */
	public static boolean isVowel(char toCheck)
	{
		toCheck = Character.toLowerCase(toCheck);

		return toCheck == 'a'  || toCheck == 'e' || toCheck == 'i' || toCheck == 'o' || toCheck == 'u';
	}

	/**
	 * Returns true if the provided character is a consonant.
	 * Ignores case.
	 * For the purposes of this function, Y is a consonant.
	 * @param toCheck
	 * @return
	 */
	public static boolean isConsonant(char toCheck)
	{
		return !isVowel(toCheck);
	}

}
