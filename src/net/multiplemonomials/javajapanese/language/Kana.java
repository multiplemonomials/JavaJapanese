package net.multiplemonomials.javajapanese.language;

import com.stackoverflow.ManyMap;

/**
 * Created by Jamie on 3/6/2016.
 */
public class Kana
{

	static class Hiragana
	{
		public static final ManyMap<Integer, Character> seion; //non-voiced, "regular" characters

		public static final ManyMap<Integer, Character> dakuon; //voiced, "quoted" characters

		public static final ManyMap<Integer, Character> handakuon; //voiced, "dotted" characters

		public static final ManyMap<Integer, Character> smallcharacters; //small y-characters and little tsu

		static
		{
			//hiragana characters hand-copied from the ordering in JED's kana table
			seion = ManyMap.fromArray(new Character[]{'あ', 'い', 'う', 'え', 'お', 'か', 'き', 'く', 'け', 'こ', 'さ', 'し', 'す', 'せ', 'そ', 'た', 'ち', 'つ', 'て', 'と',
					'な', 'に', 'ぬ', 'ね', 'の', 'は', 'ひ', 'ふ', 'へ', 'ほ', 'ま', 'み', 'む', 'め', 'も', 'や', 'ゆ', 'よ', 'ら', 'り', 'る', 'れ', 'ろ', 'わ', 'ん', 'を'});

			dakuon = ManyMap.fromArray(new Character[]{'が', 'ぎ', 'ぐ', 'げ', 'ご', 'ざ', 'じ', 'ず', 'ぜ', 'ぞ', 'だ', 'ぢ', 'づ', 'で', 'ど',
					'ば', 'び', 'ぶ', 'べ', 'ぼ'});

			handakuon=ManyMap.fromArray(new Character[]{'ぱ', 'ぴ', 'ぷ', 'ぺ', 'ぽ'});

			smallcharacters =ManyMap.fromArray(new Character[]{'ゃ', 'ゅ', 'ょ', 'っ', 'ぁ', 'ぃ', 'ぅ', 'ぇ', 'ぉ'});

		}

	}

	static class Katakana
	{
		public static final ManyMap<Integer, Character> seion; //non-voiced, "regular" characters

		public static final ManyMap<Integer, Character> dakuon; //voiced, "quoted" characters

		public static final ManyMap<Integer, Character> handakuon; //voiced, "dotted" characters

		public static final ManyMap<Integer, Character> smallcharacters; //small y-characters and little tsu

		static
		{
			//katakana characters hand-copied from the ordering in JED's kana table
			seion = ManyMap.fromArray(new Character[]{'ア', 'イ', 'ウ', 'エ', 'オ', 'カ', 'キ', 'ク', 'ケ', 'コ', 'サ', 'シ', 'ス', 'セ', 'ソ', 'タ', 'チ', 'ツ', 'テ', 'ト',
					'ナ', 'ニ', 'ヌ', 'ネ', 'ノ', 'ハ', 'ヒ', 'フ', 'ヘ', 'ホ', 'マ', 'ミ', 'ム', 'メ', 'モ', 'ヤ', 'ユ', 'ヨ', 'ラ', 'リ', 'ル', 'レ', 'ロ', 'ワ', 'ン', 'ヲ'});

			dakuon = ManyMap.fromArray(new Character[]{'ガ', 'ギ', 'グ', 'ゲ', 'ゴ', 'ザ', 'ジ', 'ズ', 'ゼ', 'ゾ', 'タ', 'ヂ', 'ヅ', 'デ', 'ド',
					'バ', 'ビ', 'ブ', 'ベ', 'ボ'});

			handakuon=ManyMap.fromArray(new Character[]{'パ', 'ピ', 'プ', 'ペ', 'ポ'});

			smallcharacters =ManyMap.fromArray(new Character[]{'ャ', 'ュ', 'ョ', 'ッ', 'ァ', 'ィ', 'ゥ', 'ェ', 'ォ'});

		}

	}

	/**
	 * Converts all hiragana in the string into katakana.
	 *
	 * Non-hiragana characters will be ignored.
	 * @param hiragana
	 * @return
	 */
	public static String hira2Kata(String hiragana)
	{
		StringBuilder katakana = new StringBuilder();

		char currentChar;

		for(int index = 0; index < hiragana.length(); ++index)
		{
			currentChar = hiragana.charAt(index);

			if(Hiragana.seion.containsValue(currentChar))
			{
				katakana.append(Katakana.seion.getFirstValue(Hiragana.seion.getFirstKey(currentChar)));
			}
			else if(Hiragana.dakuon.containsValue(currentChar))
			{
				katakana.append(Katakana.dakuon.getFirstValue(Hiragana.dakuon.getFirstKey(currentChar)));
			}
			else if(Hiragana.handakuon.containsValue(currentChar))
			{
				katakana.append(Katakana.handakuon.getFirstValue(Hiragana.handakuon.getFirstKey(currentChar)));
			}
			else if(Hiragana.smallcharacters.containsValue(currentChar))
			{
				katakana.append(Katakana.smallcharacters.getFirstValue(Hiragana.smallcharacters.getFirstKey(currentChar)));
			}
			else
			{
				//currentChar is not hiragana
				katakana.append(currentChar);
			}

		}

		return katakana.toString();
	}

	/**
	 * Converts all katakana in the string into hiragana.
	 *
	 * Non-katakana characters will be ignored.
	 * @param katakana
	 * @return
	 */
	public static String kata2Hira(String katakana)
	{
		StringBuilder hiragana = new StringBuilder();

		char currentChar;

		for(int index = 0; index < katakana.length(); ++index)
		{
			currentChar = katakana.charAt(index);

			if(Katakana.seion.containsValue(currentChar))
			{
				hiragana.append(Hiragana.seion.getFirstValue(Katakana.seion.getFirstKey(currentChar)));
			}
			else if(Katakana.dakuon.containsValue(currentChar))
			{
				hiragana.append(Hiragana.dakuon.getFirstValue(Katakana.dakuon.getFirstKey(currentChar)));
			}
			else if(Katakana.handakuon.containsValue(currentChar))
			{
				hiragana.append(Hiragana.handakuon.getFirstValue(Katakana.handakuon.getFirstKey(currentChar)));
			}
			else if(Katakana.smallcharacters.containsValue(currentChar))
			{
				hiragana.append(Hiragana.smallcharacters.getFirstValue(Katakana.smallcharacters.getFirstKey(currentChar)));
			}
			else
			{
				//currentChar is not hiragana
				hiragana.append(currentChar);
			}

		}

		return hiragana.toString();
	}

	/**
	 * 
	 * @param string
	 * @return  True if the string consists of only hiragana character, false otherwise
	 */
	public static boolean isOnlyHiragana(String string)
	{
		for(int index = 0; index < string.length(); ++index)
		{
			if(!isHiragana(string.charAt(index)))
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	 *
	 * @param string
	 * @return  True if the string has any hiragana characters
	 */
	public static boolean containsHiragana(String string)
	{
		for(int index = 0; index < string.length(); ++index)
		{
			if(isHiragana(string.charAt(index)))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 *
	 * @param string
	 * @return  True if the string has any hiragana characters
	 */
	public static boolean containsKatakana(String string)
	{
		for(int index = 0; index < string.length(); ++index)
		{
			if(isKatakana(string.charAt(index)))
			{
				return true;
			}
		}

		return false;
	}


	/**
	 * 
	 * @param c
	 * @return True if the character is hiragana
	 */
	public static boolean isHiragana(Character c)
	{
		return (Hiragana.seion.containsValue(c) || Hiragana.dakuon.containsValue(c) || Hiragana.handakuon.containsValue(c) || Hiragana.smallcharacters.containsValue(c));
	}

	/**
	 *
	 * @param string
	 * @return  True if the string consists of only katakana characters, false otherwise
	 */
	public static boolean isOnlyKatakana(String string)
	{
		for(int index = 0; index < string.length(); ++index)
		{
			if(!isKatakana(string.charAt(index)))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 *
	 * @param c
	 * @return True if the character is katakana
	 */
	public static boolean isKatakana(Character c)
	{
		return (Katakana.seion.containsValue(c) || Katakana.dakuon.containsValue(c) || Katakana.handakuon.containsValue(c) || Katakana.smallcharacters.containsValue(c));
	}
}
