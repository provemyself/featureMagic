package edu.pku.ss.nlp.features;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public final class FeatureAlgorithms {

	/**
	 * Don't let anyone instantiate this class.
	 */
	private FeatureAlgorithms() {
	}

	/**
	 * the array of vowels.
	 */
	private static final char[] vowels = { 'a', 'e', 'i', 'o', 'u' };

	/**
	 * 
	 * @param word
	 *            the word to be computed for syllable number.
	 * @return the number of syllable in the argument.
	 */
	public static int computeSyllableNumberOfWord(String word) {
		int length = word.length();
		int syllableNumber = 0;
		boolean lastVowel = false; // if the last char is vowel,the "lastVowel"
									// is true.
									// else is false.
		for (int i = 0; i < length; ++i) {
			if (FeatureAlgorithms.isVowel(word.charAt(i)) && (!lastVowel)) {
				syllableNumber++;
				lastVowel = true;
			} else if (!(FeatureAlgorithms.isVowel(word.charAt(i)))) {
				lastVowel = false;
			}
		}
		return syllableNumber;
	}

	/**
	 * This function compute the Flesch Reading Ease scores.In general, a text
	 * should generally have more than 200 words before the Flesch Reading Ease
	 * scores can be successfully used.The output of the Flesch Reading Ease
	 * formula is a number from 0 to 100, with a higher score indicating easier
	 * reading. The average document has a Flesch Reading Ease score between 6
	 * and 70.
	 * 
	 * @param asl
	 *            the average sentence length, that means the number of words
	 *            divided by the number of sentences.
	 * @param asw
	 *            average number of syllables per word, that means the number of
	 *            syllables divided by the number of words.
	 * @return the value of Flesch Reading Ease.
	 */
	public static double computeFleschReadingEase(double asl, double asw) {
		return 206.835 - (1.015 * asl) - (84.6 * asw);
	}

	/**
	 * This function compute the Flesch-Kincaid Grade Level scores.In general, a
	 * text should generally have more than 200 words before the Flesch-Kincaid
	 * Grade Level scores can be successfully used.
	 * 
	 * @param asl
	 *            the average sentence length, that means the number of words
	 *            divided by the number of sentences.
	 * @param asw
	 *            average number of syllables per word, that means the number of
	 *            syllables divided by the number of words.
	 * @return the value of Flesch-Kincaid Grade Level scores.
	 */
	public static double computeFleschKincaidGradeLevel(double asl, double asw) {
		return (0.39 * asl) + (11.8 * asw) - 15.59;
	}

	/**
	 * 
	 * @param c
	 *            a character of word.
	 * @return true if the argument is vowel, else return false.
	 */
	private static boolean isVowel(char c) {
		int length = vowels.length;
		for (int i = 0; i < length; ++i) {
			if (c == vowels[i])
				return true;
		}
		return false;
	}

	public static double getIncidenceScore(double numerator, double denominator) {
		if (Double.compare(denominator, 0) == 0) {
			return 0;
		} else {
			return numerator / denominator * 1000;
		}
	}

}
