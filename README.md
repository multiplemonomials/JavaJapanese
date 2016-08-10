# JavaJapanese -- utilities for handling Japanese text
This library has two classes that you might be interested in -- Kana and Romanizer

### Kana
Kana has functions for dealing with hiragana and katakana.


`hira2Kata()` and `kata2Hira()` -- Convert text in one kana into the other

`containsHiragana()` and `containsKatakana()` -- Return true if there are characters of the kana in a string

`isOnlyKatakana()` and `isOnlyHiragana()` -- Return true of the string contains only characters in the kana

### Romanizer
Romanizer is for converting between romaji and kana.

First, create a Romanizer, specifying either Hepburn or Kunrei-shiki (Council-ordered) romanization. 

```java
Romanizer romanizer = new Romanizer(RomanizerMode.HEPBURN)
```

It has two main functions:

`romanize()`

Returns a string containing the romaji for any kana contained in the string.  Logs a warning if it encounters a non-kana character.  May also throw an IllegalArgumentException if the provided string has no valid pronunciation, for example if it starts with a ー.

`unromanize()`

Returns a `Set<String>` containing all possible hiragana spellings of a romaji word. If there aren't any romanizations, returns an empty set. 

It is possible and common for there to be multiple possible un-romanizations of a word.  For example, for "nani", valid unromanizations are "なに" and "なんい".  One of those might be more likely, but both are possible, and this function doesn't have or provide any information about which is correct.  In many cases, romanization is a loss of information.  Because of this, I'd recommend using this for searching, not for displaying text to the user.

## Download
[Source and binary jar](https://dl.dropboxusercontent.com/u/27566023/Software%20Host/JavaJapanese-0.1.jar)

## Notes

- The kana data in this library comes from Wikipedia and the JED app for Android.
- Most, but not all, of the "extended Katakana" on [the Wikipedia chart](https://en.wikipedia.org/wiki/Hepburn_romanization#For_extended_katakana) are supported in the romanizer
- This library makes heavy use of its own collection, `com.stackoverflow.ManyMap`, a many-to-many map class supporting forward and reverse lookup.  It's very useful, feel free to use it in your own stuff.
- This library depends on Google Guava.
- Long お sounds are romanized and romanized how they're spelled, with an う.  So, "がっこう" would be romanized as "gakkou"