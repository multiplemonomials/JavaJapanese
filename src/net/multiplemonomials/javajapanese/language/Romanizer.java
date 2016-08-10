package net.multiplemonomials.javajapanese.language;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.stackoverflow.ManyMap;

import net.multiplemonomials.javajapanese.util.Log;
import net.multiplemonomials.javajapanese.util.Pair;
import net.multiplemonomials.javajapanese.util.Utils;


/**
 * Created by Jamie on 3/6/2016.
 */
public class Romanizer
{
	public enum RomanizerMode
	{
		HEPBURN,
		KUNREISHIKI;

		ManyMap<Character, String> hira2Romaji;
		ManyMap<Character, String> yoonFirstChar;
		ManyMap<Character, String> yoonSecondChar;

		ManyMap<Character, String> exYoonFirstChar;

		//these are second-chars used in extended katakana yoon combinations.  If one of these is in a word, it combines with the previous character in an extended yoon sound
		ManyMap<Character, String> exYoonSecondChar;

		ManyMap<String, String> extendedYoon; //special exceptions to the other extended katakana rules.  These take priority over other yoon combinations.  These may be any number of characters.

		static
		{
			HEPBURN.hira2Romaji = new ManyMap<Character, String>();
			HEPBURN.yoonFirstChar = new ManyMap<Character, String>();
			HEPBURN.yoonSecondChar = new ManyMap<Character, String>();
			HEPBURN.exYoonFirstChar = new ManyMap<Character, String>();
			HEPBURN.exYoonSecondChar = new ManyMap<Character, String>();
			HEPBURN.extendedYoon = new ManyMap<String, String>();


			HEPBURN.hira2Romaji.put('あ', "a");
			HEPBURN.hira2Romaji.put('い', "i");
			HEPBURN.hira2Romaji.put('う', "u");
			HEPBURN.hira2Romaji.put('え', "e");
			HEPBURN.hira2Romaji.put('お', "o");
			HEPBURN.hira2Romaji.put('か', "ka");
			HEPBURN.hira2Romaji.put('き', "ki");
			HEPBURN.hira2Romaji.put('く', "ku");
			HEPBURN.hira2Romaji.put('け', "ke");
			HEPBURN.hira2Romaji.put('こ', "ko");
			HEPBURN.hira2Romaji.put('が', "ga");
			HEPBURN.hira2Romaji.put('ぎ', "gi");
			HEPBURN.hira2Romaji.put('ぐ', "gu");
			HEPBURN.hira2Romaji.put('げ', "ge");
			HEPBURN.hira2Romaji.put('ご', "go");
			HEPBURN.hira2Romaji.put('さ', "sa");
			HEPBURN.hira2Romaji.put('し', "shi");
			HEPBURN.hira2Romaji.put('す', "su");
			HEPBURN.hira2Romaji.put('せ', "se");
			HEPBURN.hira2Romaji.put('そ', "so");
			HEPBURN.hira2Romaji.put('ざ', "za");
			HEPBURN.hira2Romaji.put('じ', "zi");
			HEPBURN.hira2Romaji.put('ず', "zu");
			HEPBURN.hira2Romaji.put('ぜ', "ze");
			HEPBURN.hira2Romaji.put('ぞ', "zo");
			HEPBURN.hira2Romaji.put('た', "ta");
			HEPBURN.hira2Romaji.put('ち', "chi");
			HEPBURN.hira2Romaji.put('つ', "tsu");
			HEPBURN.hira2Romaji.put('て', "te");
			HEPBURN.hira2Romaji.put('と', "to");
			HEPBURN.hira2Romaji.put('だ', "da");
			HEPBURN.hira2Romaji.put('ぢ', "ji");
			HEPBURN.hira2Romaji.put('づ', "zu");
			HEPBURN.hira2Romaji.put('で', "de");
			HEPBURN.hira2Romaji.put('ど', "do");
			HEPBURN.hira2Romaji.put('な', "na");
			HEPBURN.hira2Romaji.put('に', "ni");
			HEPBURN.hira2Romaji.put('ぬ', "nu");
			HEPBURN.hira2Romaji.put('ね', "ne");
			HEPBURN.hira2Romaji.put('の', "no");
			HEPBURN.hira2Romaji.put('は', "ha");
			HEPBURN.hira2Romaji.put('ひ', "hi");
			HEPBURN.hira2Romaji.put('ふ', "fu");
			HEPBURN.hira2Romaji.put('へ', "he");
			HEPBURN.hira2Romaji.put('ほ', "ho");
			HEPBURN.hira2Romaji.put('ば', "ba");
			HEPBURN.hira2Romaji.put('び', "bi");
			HEPBURN.hira2Romaji.put('ぶ', "bu");
			HEPBURN.hira2Romaji.put('べ', "be");
			HEPBURN.hira2Romaji.put('ぼ', "bo");
			HEPBURN.hira2Romaji.put('ぱ', "pa");
			HEPBURN.hira2Romaji.put('ぴ', "pi");
			HEPBURN.hira2Romaji.put('ぷ', "pu");
			HEPBURN.hira2Romaji.put('ぺ', "pe");
			HEPBURN.hira2Romaji.put('ぽ', "po");
			HEPBURN.hira2Romaji.put('ま', "ma");
			HEPBURN.hira2Romaji.put('み', "mi");
			HEPBURN.hira2Romaji.put('む', "mu");
			HEPBURN.hira2Romaji.put('め', "me");
			HEPBURN.hira2Romaji.put('も', "mo");
			HEPBURN.hira2Romaji.put('や', "ya");
			HEPBURN.hira2Romaji.put('ゆ', "yu");
			HEPBURN.hira2Romaji.put('よ', "yo");
			HEPBURN.hira2Romaji.put('ら', "ra");
			HEPBURN.hira2Romaji.put('り', "ri");
			HEPBURN.hira2Romaji.put('る', "ru");
			HEPBURN.hira2Romaji.put('れ', "re");
			HEPBURN.hira2Romaji.put('ろ', "ro");

			HEPBURN.hira2Romaji.put('を', "o");
			HEPBURN.hira2Romaji.put('ん', "n");

			HEPBURN.yoonFirstChar.put('き', "k");
			HEPBURN.yoonFirstChar.put('し', "s");
			HEPBURN.yoonFirstChar.put('ち', "ch");
			HEPBURN.yoonFirstChar.put('に', "n");
			HEPBURN.yoonFirstChar.put('ひ', "h");
			HEPBURN.yoonFirstChar.put('み', "m");
			HEPBURN.yoonFirstChar.put('り', "r");
			HEPBURN.yoonFirstChar.put('ぎ', "g");
			HEPBURN.yoonFirstChar.put('じ', "j");
			HEPBURN.yoonFirstChar.put('ぢ', "j");
			HEPBURN.yoonFirstChar.put('び', "b");
			HEPBURN.yoonFirstChar.put('ぴ', "p");

			//extended katakana sounds (used to represent foreign words, don't hava hiragana equivalents)
			//some yoon first chars make different sounds when used with extended katakana second chars
			//however, some of them will still get erroneously converted to hiragana

			HEPBURN.yoonFirstChar.put('ふ', "f"); //only set of extended kana sounds which use small y characters

			HEPBURN.exYoonFirstChar.put('う', "w");
			HEPBURN.exYoonFirstChar.put('ヴ', "v");
			HEPBURN.exYoonFirstChar.put('し', "sh");
			HEPBURN.exYoonFirstChar.put('じ', "j");
			HEPBURN.exYoonFirstChar.put('ち', "ch");
			HEPBURN.exYoonFirstChar.put('つ', "ts");
			HEPBURN.exYoonFirstChar.put('て', "t");
			HEPBURN.exYoonFirstChar.put('と', "t");
			HEPBURN.exYoonFirstChar.put('で', "d");
			HEPBURN.exYoonFirstChar.put('ど', "d");
			HEPBURN.exYoonFirstChar.put('ふ', "f");

			//regular kana
			HEPBURN.yoonSecondChar.put('ゃ', "ya");
			HEPBURN.yoonSecondChar.put('ゅ', "yu");
			HEPBURN.yoonSecondChar.put('ょ', "yo");

			//extended katakana
			HEPBURN.exYoonSecondChar.put('ぁ', "a");
			HEPBURN.exYoonSecondChar.put('ゎ', "wa");
			HEPBURN.exYoonSecondChar.put('ぃ', "i");
			HEPBURN.exYoonSecondChar.put('ぅ', "u");
			HEPBURN.exYoonSecondChar.put('ぇ', "e");
			HEPBURN.exYoonSecondChar.put('ぉ', "o");

			HEPBURN.extendedYoon.put("くぁ", "kwa");
			HEPBURN.extendedYoon.put("くぃ", "kwi");
			HEPBURN.extendedYoon.put("くぇ", "kwe");
			HEPBURN.extendedYoon.put("くぉ", "kwo");

			HEPBURN.extendedYoon.put("ぐぁ", "gwa");
			HEPBURN.extendedYoon.put("ぐぃ", "gwi");
			HEPBURN.extendedYoon.put("ぐぇ", "gwe");
			HEPBURN.extendedYoon.put("ぐぉ", "gwo");

			HEPBURN.extendedYoon.put("ヴぃぇ", "vye");

			HEPBURN.extendedYoon.put("いぃ", "yi");
			HEPBURN.extendedYoon.put("いぇ", "ye");

			HEPBURN.extendedYoon.put("ほぅ", "hu");


			//there aren't too many differences between the two
			KUNREISHIKI.hira2Romaji = new ManyMap<Character, String>(HEPBURN.hira2Romaji);
			KUNREISHIKI.yoonFirstChar = new ManyMap<Character, String>(HEPBURN.yoonFirstChar);
			KUNREISHIKI.yoonSecondChar = new ManyMap<Character, String>(HEPBURN.yoonSecondChar);

			KUNREISHIKI.exYoonFirstChar = new ManyMap<Character, String>(HEPBURN.exYoonFirstChar);
			KUNREISHIKI.exYoonSecondChar = new ManyMap<Character, String>(HEPBURN.exYoonSecondChar);
			KUNREISHIKI.extendedYoon = new ManyMap<String, String>(HEPBURN.extendedYoon);

			KUNREISHIKI.hira2Romaji.removeAllValues('し');
			KUNREISHIKI.hira2Romaji.put('し', "si");

			KUNREISHIKI.hira2Romaji.removeAllValues('ち');
			KUNREISHIKI.hira2Romaji.put('ち', "ti");

			KUNREISHIKI.hira2Romaji.removeAllValues('つ');
			KUNREISHIKI.hira2Romaji.put('つ', "tu");

			KUNREISHIKI.hira2Romaji.removeAllValues('ふ');
			KUNREISHIKI.hira2Romaji.put('ふ', "hu");

			KUNREISHIKI.yoonFirstChar.removeAllValues('ち');
			KUNREISHIKI.yoonFirstChar.removeAllValues('じ');
			KUNREISHIKI.yoonFirstChar.removeAllValues('ぢ');

			KUNREISHIKI.yoonFirstChar.put('ち', "t");
			KUNREISHIKI.yoonFirstChar.put('じ', "z");
			KUNREISHIKI.yoonFirstChar.put('ぢ', "z");
		}
	}


	static final char littleTsu='っ';
	static final char vowelRepeatDash='ー';
	static final int longestHiraganaRomaji = 3; //length of the longest romaji entry in the "hira2Romaji" ManyMap

	static final String TAG = "Romanizer";
	


	RomanizerMode mode;
	/**
	 * Takes a string containing hiragana and/or katakana, and returns a string of roman characters according to the selected romanization mode
	 * @param kana
	 * @return
	 */
	public String romanize(String kana)
	{
		//first, let's get it into hiragana
		kana = Kana.kata2Hira(kana);

		//I guess we can leave any non-hiragana characters (like alphanumerics and punctuation) for now.

		StringBuilder output = new StringBuilder();

		char currentChar;
		boolean prevCharWasYoon = false;
		boolean prevCharWasExYoon = false;

		Set<String> specialYoon = mode.extendedYoon.keySet();

		
		//going backwards makes things a little more straightforward
		for (int index = kana.length() - 1; index >= 0; --index)
		{
			currentChar = kana.charAt(index);

			boolean specialYoonFound = false;
			//check for special yoon
			for(String yoon : specialYoon)
			{
				if(index >= yoon.length())
				{
					String candidate = kana.substring(index - yoon.length() + 1, index + 1);

					if (candidate.equals(yoon))
					{
						//found one!
						output.insert(0, mode.extendedYoon.getFirstValue(yoon));

						//advance extra distance, because the special yoon may be more than 1 character
						index -= (yoon.length() - 1);

						specialYoonFound = true;
						break;
					}
				}
			}

			//if we found one, skip the rest of the loop
			if(specialYoonFound)
			{
				continue;
			}

			if(prevCharWasYoon)
			{

				if(!prevCharWasExYoon && mode.yoonFirstChar.containsKey(currentChar))
				{
					output.insert(0, mode.yoonFirstChar.getFirstValue(currentChar));
					prevCharWasYoon = false;
				}
				else
				{
					throw new IllegalArgumentException("Invalid hiragana!  Unknown yoon combination \"" + kana.substring(index, index + 2) + '\"');
				}
			}
			else if(prevCharWasExYoon)
			{

				if(!prevCharWasYoon && mode.exYoonFirstChar.containsKey(currentChar))
				{
					output.insert(0, mode.exYoonFirstChar.getFirstValue(currentChar));
					prevCharWasExYoon = false;
				}
				else
				{
					throw new IllegalArgumentException("Invalid hiragana!  Unknown extended yoon combination \"" + kana.substring(index, index + 2) + '\"');
				}
			}
			else if(mode.hira2Romaji.containsKey(currentChar))
			{
				output.insert(0, mode.hira2Romaji.getFirstValue(currentChar));

			}
			else if(mode.yoonSecondChar.containsKey(currentChar))
			{
				if(prevCharWasYoon || prevCharWasExYoon)
				{
					throw new IllegalArgumentException("Invalid hiragana!  Two yoon second chars in a row!");
				}
				else
				{
					output.insert(0, mode.yoonSecondChar.getFirstValue(currentChar));
					prevCharWasYoon = true;
				}
			}
			else if(mode.exYoonSecondChar.containsKey(currentChar))
			{
				if(prevCharWasYoon || prevCharWasExYoon)
				{
					throw new IllegalArgumentException("Invalid hiragana!  Two yoon second chars in a row!");
				}
				else
				{
					output.insert(0, mode.exYoonSecondChar.getFirstValue(currentChar));
					prevCharWasExYoon = true;
				}
			}
			else if(currentChar == littleTsu)
			{
				if(index < kana.length() - 1)
				{
					//append the first letter of the consonant sound of the nexc character
					output.insert(0, getConsonantPart(kana.charAt(index + 1)).charAt(0));

				}
				else
				{
					throw new IllegalArgumentException("Invalid hiragana!  There's a っ at the end!");
				}
			}
			else if(currentChar == vowelRepeatDash)
			{
				if(index > 0)
				{
					//append the first letter of the consonant sound of the previous character
					output.insert(0, getVowelPart(kana.charAt(index - 1)));

				}
				else
				{
					throw new IllegalArgumentException("Invalid hiragana!  There's a ー at the start!");
				}
			}
			else
			{
				//unknown character
				Log.w(TAG, "Unknown character in text to romanize: " + currentChar);
			}
		}

		return output.toString();

	}

	/**
	 * Get the one or two characters of the consonant part of this hiragana character's romanization.
	 *
	 * This takes only one character, so it does not always work with yoon.  It does fall back to them if it sees an extended katakana character, though.
	 * @param hiragana a hiragana character
	 * @return
	 */
	public String getConsonantPart(char hiragana)
	{
		if(mode.hira2Romaji.containsKey(hiragana))
		{
			String fullRomanization = mode.hira2Romaji.getFirstValue(hiragana);

			//this will work as long as none of the romanizations has a two-letter vowel sound.  Hopefully, this will never happen.
			return fullRomanization.substring(0, fullRomanization.length() - 1);
		}
		//let's fall back to the yoon
		else if(mode.yoonFirstChar.containsKey(hiragana))
		{
			return mode.yoonFirstChar.getFirstValue(hiragana);
		}
		else if(mode.exYoonFirstChar.containsKey(hiragana))
		{
			return mode.exYoonFirstChar.getFirstValue(hiragana);
		}
		throw new IllegalArgumentException("Character \"" + hiragana + "\"  is not in the romanization map.");

	}

	/**
	 * Get the one character vowel part of a hiragana
	 *
	 * This tries to work if you give it the second character of a yoon.  It doesn't work wth special yoon, though.
	 * @param hiragana a hiragana character
	 * @return
	 */
	public char getVowelPart(char hiragana)
	{
		if(mode.hira2Romaji.containsKey(hiragana))
		{
			String fullRomanization = mode.hira2Romaji.getFirstValue(hiragana);

			//this will work as long as none of the romanizations has a two-letter vowel sound.  Hopefully, this will never happen.
			return fullRomanization.charAt(fullRomanization.length() - 1);
		}
		//let's fall back to the yoon

		String fullYoonVowel;

		if(mode.yoonSecondChar.containsKey(hiragana))
		{
			fullYoonVowel = mode.yoonSecondChar.getFirstValue(hiragana);
		}
		else if(mode.exYoonSecondChar.containsKey(hiragana))
		{
			fullYoonVowel = mode.exYoonSecondChar.getFirstValue(hiragana);
		}
		else
		{
			throw new IllegalArgumentException("Character \"" + hiragana + "\"  is not in the romanization map.");
		}

		return fullYoonVowel.charAt(fullYoonVowel.length() - 1);
	}

	class Branch
	{
		StringBuilder text;

		int index;

		boolean done = false;

		public Branch()
		{
			text = new StringBuilder();

			index = 0;
		}

		public Branch(StringBuilder text, int index)
		{
			this.text = text;
			this.index = index;
		}

		// copy constructor
		public Branch(Branch otherBranch)
		{
			text = new StringBuilder(otherBranch.text);
			index = otherBranch.index;
			done = otherBranch.done;
		}
	}

	public Set<String> unromanize(String romaji)
	{
		HashSet<Branch> branches = new HashSet<Branch>();

		branches.add(new Branch());

		boolean moreWorkToDo;

		do
		{

			HashSet<Branch> branchesToAdd = new HashSet<Branch>();

			moreWorkToDo = false;

			for(Branch branch : branches)
			{
				if(!branch.done)
				{
					HashSet<Pair<String, Integer>> nextSteps = unromanize_getNextSteps(romaji, branch);

					if(nextSteps.isEmpty())
					{
						branch.done = true;
					}
					else
					{
						int stepsLeft = nextSteps.size();

						for (Pair<String, Integer> step : nextSteps)
						{
							Branch stepBranch;
							if (stepsLeft == 1)
							{
								//continue the current branch
								stepBranch = branch;
							}
							else //fork a new one
							{
								--stepsLeft;
								stepBranch = new Branch(branch);
								branchesToAdd.add(stepBranch);
							}

							//process the branch
							stepBranch.text.append(step.first);
							stepBranch.index += step.second;

							if(stepBranch.index >= romaji.length())
							{
								stepBranch.done = true;
							}
							else
							{
								moreWorkToDo = true;
							}

						}

					}


				}



			}

			//now that we're done iterating, add the new branches
			branches.addAll(branchesToAdd);


			//check for branches which still need processing
//			for(Branch branch : branches)
//			{
//				if(!branch.done)
//				{
//					moreWorkToDo = true;
//				}
//			}
		}
		while(moreWorkToDo);

		HashSet<String> possibleKana = new HashSet<String>();

		for(Branch branch : branches)
		{
			//if the only branch is the initial one and it is empty, we want to return an empty set, so filter out empty branches.
			if(!branch.text.toString().isEmpty())
			{
				possibleKana.add(branch.text.toString());
			}
		}

		return possibleKana;
	}

	/**
	 * Given a romaji string and a branch, get the possible next hiragana chars, and how much the index in the romaji string increased by.
	 *
	 * If nothing is returned, it means that the branch is done.
	 * @param romaji
	 * @param branch
	 * @return
	 */
	private HashSet<Pair<String, Integer>> unromanize_getNextSteps(String romaji, Branch branch)
	{
		HashSet<Pair<String, Integer>> possibleNextSteps = new HashSet<Pair<String, Integer>>();


		int romajiLength = romaji.length();


		//check for "regular" kana

		//find possible sequences of characters which could be a hiragana character from the current index
		ArrayList<String> possibleHiraCharsList = new ArrayList<String>();

		for(int hiraCharsLength = 1; branch.index + hiraCharsLength <= romajiLength && hiraCharsLength <= longestHiraganaRomaji; ++hiraCharsLength)
		{
			possibleHiraCharsList.add(romaji.substring(branch.index, branch.index + hiraCharsLength));
		}

		for(String possibleHiraChars : possibleHiraCharsList)
		{
			if (mode.hira2Romaji.containsValue(possibleHiraChars))
			{
				//found one!

				Set<Character> possibleHira = mode.hira2Romaji.getKeys(possibleHiraChars);

				for(Character hiraChar: possibleHira)
				{
					possibleNextSteps.add(new Pair<String, Integer>(hiraChar.toString(), possibleHiraChars.length()));
				}

			}
		}


		//check for possible yoon
		for(String firstChars : mode.yoonFirstChar.valueSet())
		{
			if(branch.index + firstChars.length() <= romajiLength && firstChars.equals(romaji.substring(branch.index, branch.index + firstChars.length())))
			{
				//found one
				for(String secondChars : mode.yoonSecondChar.valueSet())
				{
					int secondEndLocation = branch.index + firstChars.length() + secondChars.length();  //1-index, for substring
					if(secondEndLocation <= romajiLength && secondChars.equals(romaji.substring(branch.index + firstChars.length(), secondEndLocation)))
					{
						//aaand, we've got a match!

						Set<Character> yoonFirstCharHiragana = mode.yoonFirstChar.getKeys(firstChars);
						Set<Character> yoonSecondCharHiragana = mode.yoonSecondChar.getKeys(secondChars);

						for(Character firstHira : yoonFirstCharHiragana)
						{
							for(Character secondHira : yoonSecondCharHiragana)
							{
								possibleNextSteps.add(new Pair<String, Integer>(firstHira.toString() + secondHira.toString(), firstChars.length() + secondChars.length()));
							}
						}
					}
				}
			}
		}

		//Now, let's do it again for extended yoon
		for(String firstChars : mode.exYoonFirstChar.valueSet())
		{
			if(branch.index + firstChars.length() <= romajiLength && firstChars.equals(romaji.substring(branch.index, branch.index + firstChars.length())))
			{
				//found one
				for(String secondChars : mode.exYoonSecondChar.valueSet())
				{
					//aaand, we've got a match!
					int secondEndLocation = branch.index + firstChars.length() + secondChars.length();  //1-index, for substring
					if(secondEndLocation <= romajiLength && secondChars.equals(romaji.substring(branch.index + firstChars.length(), secondEndLocation)))
					{

						Set<Character> yoonFirstCharHiragana = mode.exYoonFirstChar.getKeys(firstChars);
						Set<Character> yoonSecondCharHiragana = mode.exYoonSecondChar.getKeys(secondChars);

						for(Character firstHira : yoonFirstCharHiragana)
						{
							for(Character secondHira : yoonSecondCharHiragana)
							{
								possibleNextSteps.add(new Pair<String, Integer>(firstHira.toString() + secondHira.toString(), firstChars.length() + secondChars.length()));
							}
						}
					}
				}
			}
		}

		//check for little tsu (sokuon)
		if(romajiLength > branch.index + 1 && romaji.charAt(branch.index) == romaji.charAt(branch.index + 1) && Utils.isConsonant(romaji.charAt(branch.index)))
		{
			//note: according to wikipedia, こっち is romanized "kotchi"  However, I've always learned it as "kocchi". so I'm going with that.  This is also how JED does it.
			possibleNextSteps.add(new Pair<String, Integer>(Character.toString(littleTsu), 1));
		}

		//check for exended-vowel dash
		if(branch.index > 0) //make sure we're not on the first loop
		{
			char currentChar = romaji.charAt(branch.index);
			char prevChar = romaji.charAt(branch.index - 1);

			if(prevChar == currentChar && Utils.isVowel(currentChar))
			{
				possibleNextSteps.add(new Pair<String, Integer>(Character.toString(vowelRepeatDash), 1));
			}
		}

		//finally, check for special-case yoon
		for(String yoonRomaji : mode.extendedYoon.valueSet())
		{
			if(romajiLength >= branch.index + yoonRomaji.length() && yoonRomaji.equals(romaji.substring(branch.index, branch.index + yoonRomaji.length())))
			{

				Set<String> possibleYoonKana = mode.extendedYoon.getKeys(yoonRomaji);

				for(String yoonKana : possibleYoonKana)
				{
					possibleNextSteps.add(new Pair<String, Integer>(yoonKana, yoonRomaji.length()));
				}

			}
		}


		return possibleNextSteps;

	}


	public void setMode(RomanizerMode mode)
	{
		this.mode = mode;
	}

	public RomanizerMode getMode()
	{
		return mode;
	}

	public Romanizer(RomanizerMode mode)
	{
		this.mode = mode;
	}
}
