package edu.pku.ss.nlp.corpus;

import edu.pku.ss.nlp.features.FeatureAlgorithms;
import edu.pku.ss.nlp.nlptoolkit.SimpleStemmer;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class Word {

	/**
	 * the total number of letter in the word.
	 */
	private int letterCount;

	/**
	 * the total number of syllable in the word.
	 */
	private int syllableCount;

	/**
	 * a word of the Text.
	 */
	private String wordContent;

	/**
	 * the tagged word content. For example, if there is a word "name", then its
	 * wordContent is also "name".
	 */
	private String taggedWordContent;

	/**
	 * the part-of-speech of word.
	 */
	private String POS;

	/**
	 * the stem of word.
	 */
	private String stem;

	/**
	 * 
	 * @param word
	 */
	public Word(String word) {
		this.wordContent = word;
		this.letterCount = word.length();
		this.syllableCount = FeatureAlgorithms.computeSyllableNumberOfWord(word);
	}

	public Word(String word, String pos) {
		this(word);
		this.taggedWordContent = word + "/" + pos;
		this.POS = pos;
		this.stem = SimpleStemmer.stem(word);
	}

	/**
	 * @return the letterCount
	 */
	public int getLetterCount() {
		return letterCount;
	}

	/**
	 * @return the syllableCount
	 */
	public int getSyllableCount() {
		return syllableCount;
	}

	/**
	 * @return the word
	 */
	public String getWordContent() {
		return wordContent;
	}

	public String getTaggedWordContent() {
		return taggedWordContent;
	}

	public String getPOS() {
		return POS;
	}

	public String getStem() {
		return this.stem;
	}
}
