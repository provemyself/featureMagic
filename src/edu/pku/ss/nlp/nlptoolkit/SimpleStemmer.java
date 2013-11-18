package edu.pku.ss.nlp.nlptoolkit;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of this class follows the singleton pattern. In this
 * software, Stemming algorithm is often used, so we should have a global and
 * unique Stemmer instance for performance.
 * 
 * NOTE: In this class, the case of words to be stemmed does not matter, as all
 * the input will be changed to lower case first before stemming.
 * 
 * @author 王志伟
 * @version 0.0.1
 * @datetime 2013/9/15 20:27
 * 
 */
public class SimpleStemmer {

	/**
	 * Stemming the given word and return the stem of word. NOTE: all the input
	 * must be changed to lower case first.
	 * 
	 * @param word
	 *            an object of String, represents a word in English.
	 * @return the stem of word.
	 */
	public static String stem(String word) {
		return stem(word.toLowerCase().toCharArray());
	}

	/**
	 * This function is an auxilliary function, which provides the fundamental
	 * implementation of stemming.
	 * 
	 * @param word
	 *            a object of char[], represents a word in English.For example,
	 *            a word "test", its "char[]" form is "['t', 'e', 's', 't']".
	 * @return the stem of given word.
	 */
	private static String stem(char[] word) {
		PorterStemmer stemmer = new PorterStemmer();
		for (char c : word) {
			stemmer.add(c);
		}
		stemmer.stem();
		return stemmer.toString();
	}

	/**
	 * 
	 * @param wordList
	 *            a list of words to be stemmed.
	 * @return a list of stems of wordList.
	 */
	public static List<String> stem(List<String> wordList) {
		List<String> stemList = new ArrayList<String>();
		for (String word : wordList) {
			stemList.add(stem(word));
		}
		return stemList;
	}

	/**
	 * 
	 * @param wordArray
	 *            an array of words to be stemmed.
	 * @return an array of stems of wordArray.
	 */
	public static String[] stem(String[] wordArray) {
		String[] stemArray = new String[wordArray.length];

		for (int i = 0; i < wordArray.length; ++i) {
			stemArray[i] = stem(wordArray[i]);
		}
		return stemArray;
	}
}
