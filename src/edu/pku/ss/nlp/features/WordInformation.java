package edu.pku.ss.nlp.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pku.ss.nlp.corpus.Text;

/**
 * This class will compute the word information of input text. "incidence score"
 * means the number of occurrence per 1000 words.
 * 
 * This defines the personal pronouns arrays in English. All the classification
 * are from 1)http://en.wikipedia.org/wiki/English_personal_pronouns
 * 2)http://www.englishclub.com/grammar/pronouns-personal.htm
 * 3)http://www.esldesk.com/grammar/practice/personal-pronouns. And in this
 * class, all the arrays of personal pronouns are in standard modern English,
 * that means that there are still some non-standard forms of personal pronouns.
 * So, this class may be rewritten in the future, as the pronouns will increase
 * or decrease.
 * 
 * NOTE:All the personal pronouns are from the standard modern English.
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class WordInformation extends AbstractFeature implements BaseExtractor {

	/**
	 * The array of all the personal pronouns in English.
	 */
	public static final String[] PERSONAL_PRONOUNS = { "I", "me", "my", "mine",
			"myself", "we", "us", "our", "ours", "ourselves", "you", "your",
			"yours", "yourself", "yourselves", "he", "him", "his", "himself",
			"she", "her", "hers", "herself", "it", "its", "itself", "they",
			"them", "their", "theirs", "themself", "themselves" };
	/**
	 * The array of pronouns, first person, single form.
	 */
	public static final String[] FIRST_PERSON_SINGLE_FORM_PRONOUNS = { "I",
			"me", "myself", "mine", "my" };
	/**
	 * The array of pronouns, first person, plural form.
	 */
	public static final String[] FIRST_PERSON_PLURAL_FORM_PRONOUNS = { "we",
			"us", "ourself", "ourselves", "ours", "our" };
	/**
	 * The array of pronouns, second person.
	 */
	public static final String[] SECOND_PERSON_PRONOUNS = { "you", "yourself",
			"yours", "your", "yourselves" };
	/**
	 * The array of pronouns, third person, single form.
	 */
	public static final String[] THIRD_PERSON_SINGLE_FORM_PRONOUNS = { "he",
			"she", "it", "him", "her", "it", "his", "her", "its", "himself",
			"herself", "itself" };
	/**
	 * The array of pronouns, first person, plural form.
	 */
	public static final String[] THIRD_PERSON_PLURAL_FORM_PRONOUNS = { "they",
			"them", "theirs", "their", "themselves" };

	public static final String[] NOUNS_TAGS = { "NN", "NNS", "NNP", "NNPS" };
	public static final String[] VERBS_TAGS = { "VB", "VBD", "VBG", "VBP",
			"VBZ" };
	public static final String[] ADJECTIVES_TAGS = { "JJ", "JJR", "JJS" };
	public static final String[] ADVERBS_TAGS = { "RB", "RBR", "RBS" };
	public static final String[] PRONOUNS_TAGS = { "PRP", "PRP$" };

	/**
	 * This is the number of nouns per 1000 words. These nouns' tags of Penn
	 * Treebank Project are "NN", "NNS", "NNP" and "NNPS".
	 */
	private double nounIncidence;
	/**
	 * This is the number of verbs per 1000 words. These verbs' tags of Penn
	 * Treebank Project are "VB", "VBD", "VBG", "VBP" and "VBZ".
	 */
	private double verbIncidence;
	/**
	 * This is the number of adjectives per 1000 words. These adjectives' tags
	 * of Penn Treebank Project are "JJ", "JJR" and "JJS".
	 */
	private double adjectiveIncidence;
	/**
	 * This is the number of adverbs per 1000 words. These adverbs' tags of Penn
	 * Treebank Project are "RB", "RBR" and "RBS".
	 */
	private double adverbIncidence;
	/**
	 * This is the number of personal pronouns per 1000 words. These tags of
	 * Penn Treebank Project are "PRP" and "PRP$".
	 */
	private double personalPronounIncidence;

	/**
	 * This is the number of "first person, single form" pronouns per 1000
	 * words.
	 */
	private double firstPersonSingularPronounIncidence;
	/**
	 * This is the number of "first person, plural form" pronouns per 1000
	 * words.
	 */
	private double firstPersonPluralPronounIncidence;

	/**
	 * This is the number of "second person" pronouns per 1000 words.
	 */
	private double secondPersonPronounIncidence;

	/**
	 * This is the number of "third person, single form" pronouns per words.
	 */
	private double thirdPersonSingularPronounIncidence;

	/**
	 * This is the number of "third person, plural form" pronouns per words.
	 */
	private double thirdPersonPluralPronounIncidence;

	public WordInformation(Text text) {
		super(text);
		this.doInitialize();
	}

	private void doInitialize() {
		Map<String, List<String>> tagWordListMap = this.getText()
				.getTagWordListMap();
		int wordCount = this.getText().getWordCount();

		this.nounIncidence = this.computeGeneralIncidenceScore(NOUNS_TAGS,
				tagWordListMap, wordCount);
		this.verbIncidence = this.computeGeneralIncidenceScore(VERBS_TAGS,
				tagWordListMap, wordCount);
		this.adjectiveIncidence = this.computeGeneralIncidenceScore(
				ADJECTIVES_TAGS, tagWordListMap, wordCount);
		this.adverbIncidence = this.computeGeneralIncidenceScore(ADVERBS_TAGS,
				tagWordListMap, wordCount);

		this.personalPronounIncidence = this.computeSpecialIncidenceScore(
				PERSONAL_PRONOUNS, tagWordListMap, wordCount);
		this.firstPersonSingularPronounIncidence = this
				.computeSpecialIncidenceScore(
						FIRST_PERSON_SINGLE_FORM_PRONOUNS, tagWordListMap,
						wordCount);
		this.firstPersonPluralPronounIncidence = this
				.computeSpecialIncidenceScore(
						FIRST_PERSON_PLURAL_FORM_PRONOUNS, tagWordListMap,
						wordCount);
		this.secondPersonPronounIncidence = this.computeSpecialIncidenceScore(
				SECOND_PERSON_PRONOUNS, tagWordListMap, wordCount);
		this.thirdPersonSingularPronounIncidence = this
				.computeSpecialIncidenceScore(
						THIRD_PERSON_SINGLE_FORM_PRONOUNS, tagWordListMap,
						wordCount);
		this.thirdPersonPluralPronounIncidence = this
				.computeSpecialIncidenceScore(
						THIRD_PERSON_PLURAL_FORM_PRONOUNS, tagWordListMap,
						wordCount);

		this.initFeatureValueMap();
	}

	private double computeGeneralIncidenceScore(String[] tags,
			Map<String, List<String>> tagWordListMap, int wordCount) {

		int sum = 0;
		for (String tag : tags) {
			if (tagWordListMap.containsKey(tag)) {
				sum += tagWordListMap.get(tag).size();
			}
		}

		return sum * 1.0 / wordCount * 1000;
	}

	private double computeSpecialIncidenceScore(String[] pronouns,
			Map<String, List<String>> tagWordListMap, int wordCount) {

		// Construct the <pronoun, count> hashmap.
		Map<String, Integer> pronounsCountMap = new HashMap<String, Integer>();
		for (String p : pronouns) {
			pronounsCountMap.put(p, 0);
		}

		// Get the list of all the possible pronouns of text.
		List<String> cadidatePronounsList = new ArrayList<String>();
		for (String tag : PRONOUNS_TAGS) {
			if (tagWordListMap.containsKey(tag)) {
				cadidatePronounsList.addAll(tagWordListMap.get(tag));
			}
		}

		// Count the number of pronouns.
		int sum = 0;
		for (String p : cadidatePronounsList) {
			if (pronounsCountMap.containsKey(p)) {
				sum += 1;
			}
		}

		// Compute the incidence score.
		return sum * 1.0 / wordCount * 1000;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(this.nounIncidence).append(",").append(this.verbIncidence)
				.append(",").append(this.adjectiveIncidence).append(",")
				.append(this.adverbIncidence).append(",")
				.append(this.personalPronounIncidence).append(",")
				.append(this.firstPersonSingularPronounIncidence).append(",")
				.append(this.firstPersonPluralPronounIncidence).append(",")
				.append(this.secondPersonPronounIncidence).append(",")
				.append(this.thirdPersonSingularPronounIncidence).append(",")
				.append(this.thirdPersonPluralPronounIncidence).append(",");
		return sb.toString();
	}

	@Override
	protected void initFeatureValueMap() {
		this.checkAndAdd("nounIncidence", this.nounIncidence);
		this.checkAndAdd("verbIncidence", this.verbIncidence);
		this.checkAndAdd("adjectiveIncidence", this.adjectiveIncidence);
		this.checkAndAdd("adverbIncidence", this.adverbIncidence);
		this.checkAndAdd("personalPronounIncidence",
				this.personalPronounIncidence);
		this.checkAndAdd("firstPersonSingularPronounIncidence",
				this.firstPersonSingularPronounIncidence);
		this.checkAndAdd("firstPersonPluralPronounIncidence",
				this.firstPersonPluralPronounIncidence);
		this.checkAndAdd("secondPersonPronounIncidence",
				this.secondPersonPronounIncidence);
		this.checkAndAdd("thirdPersonSingularPronounIncidence",
				this.thirdPersonSingularPronounIncidence);
		this.checkAndAdd("thirdPersonPluralPronounIncidence",
				this.thirdPersonPluralPronounIncidence);
	}

	@Override
	public String extractFeatureByString() {
		// TODO Auto-generated method stub
		return null;
	}
}
