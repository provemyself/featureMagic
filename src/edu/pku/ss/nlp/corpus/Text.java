package edu.pku.ss.nlp.corpus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.pku.ss.nlp.features.FeatureAlgorithms;
import edu.pku.ss.nlp.lm.Bigram;
import edu.pku.ss.nlp.lm.Trigram;
import edu.pku.ss.nlp.lm.Unigram;
import edu.pku.ss.nlp.nlptoolkit.NLPToolkitSingleton;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class Text {

	/**
	 * the total number of paragraph in the text.
	 */
	private int paragraphCount;

	/**
	 * the total number of sentence in the text.
	 */
	private int sentenceCount;

	/**
	 * the total number of word in the text.
	 */
	private int wordCount;

	/**
	 * the total number of word syllable in the text.
	 */
	private int syllableCount;

	/**
	 * the total number of word letter in the text.
	 */
	private int letterCount;

	/**
	 * Each Text object consists of several Paragraphs.
	 */
	private List<Paragraph> paragraphList = new ArrayList<Paragraph>();
	private List<Sentence> sentenceList = new ArrayList<Sentence>();
	private List<Word> wordList = new ArrayList<Word>();
	private List<String> wordContentList = new ArrayList<String>();
	private List<String> posList = new ArrayList<String>();
	private List<String> stemList = new ArrayList<String>();

	/**
	 * The untagged text content.
	 */
	private String textContent;

	/**
	 * the average number of sentences in each paragraph within the text
	 */
	private double paragraphMeanLength;

	/**
	 * the standard deviation of the measure of the mean length of paragraphs
	 * within the text
	 */
	private double paragraphLengthDeviation;

	/**
	 * the average number of words in each sentence within the text
	 */
	private double sentenceMeanLength;

	/**
	 * the standard deviation of the measure for the mean length of sentences
	 * within the text
	 */
	private double sentenceLengthDeviation;

	/**
	 * the average number of syllables in all of the words in the text
	 */
	private double wordSyllableMeanLength;

	/**
	 * the standard deviation of the measure for the mean number of syllables in
	 * the words within the text
	 */
	private double wordSyllableDeviation;

	/**
	 * the average number of letters for all of the words in the text
	 */
	private double wordLetterMeanLength;

	/**
	 * the standard deviation of the measure for the mean number of letters in
	 * the words within the text
	 */
	private double wordLetterDeviation;

	private double fleschReadingEase;

	private double fleschKincaidGradeLevel;

	private Map<String, List<String>> tagWordListMap = new HashMap<String, List<String>>();

	/**
	 * This vocabulary is the set of tokens of this text, that's the set
	 * includes all the possible tokens that occur in this text.
	 */
	private Set<String> vocabulary = new HashSet<String>();

	private Map<Unigram, Integer> unigramMap = new HashMap<Unigram, Integer>();
	private Map<Bigram, Integer> bigramMap = new HashMap<Bigram, Integer>();
	private Map<Trigram, Integer> trigramMap = new HashMap<Trigram, Integer>();

	private Map<Unigram, Integer> posUnigramMap = new HashMap<Unigram, Integer>();
	private Map<Bigram, Integer> posBigramMap = new HashMap<Bigram, Integer>();
	private Map<Trigram, Integer> posTrigramMap = new HashMap<Trigram, Integer>();

	private Map<Unigram, Integer> stemUnigramMap = new HashMap<Unigram, Integer>();
	private Map<Bigram, Integer> stemBigramMap = new HashMap<Bigram, Integer>();
	private Map<Trigram, Integer> stemTrigramMap = new HashMap<Trigram, Integer>();

	// Lexical Diversity.
	private double contentWordsTTR;
	private double allWordsTTR;
	private double allWordsMTLD;
	private double allWordsVOC;// wait to initialize.

	public Text(String text) {
		this.textContent = text;

		this.initialize();
		this.vocabulary = new HashSet<String>(this.wordContentList);

		this.ComputeFleschReadingEase();
		this.ComputeFleschKincaidGradeLevel();
	}

	/**
	 * init the paragraphList of the Text.
	 */
	private void initialize() {
		String[] paragraphs = NLPToolkitSingleton.getInstance()
				.getParagraphSegmentor().segment(this.textContent);

		// this sum for computing the deviation of paragraph length.
		double sum = 0.0;
		Paragraph p = null;
		for (String paragraph : paragraphs) {
			p = new Paragraph(paragraph);

			sum += Math.pow(p.getSentenceCount() - this.paragraphMeanLength,
					2.0);

			this.paragraphList.add(p);
			this.wordCount += p.getWordCount();
			this.letterCount += p.getLetterCount();
			this.syllableCount += p.getSyllableCount();
			this.sentenceCount += p.getSentenceCount();
			this.tagWordListMap.putAll(p.getTagWordListMap());
			this.sentenceList.addAll(p.getSentenceList());
			this.wordList.addAll(p.getWordList());
			this.wordContentList.addAll(p.getWordContentList());
			this.posList.addAll(p.getPOSList());
			this.stemList.addAll(p.getStemList());
			this.vocabulary.addAll(p.getVocabulary());

			this.unigramMap.putAll(p.getUnigramMap());
			this.posUnigramMap.putAll(p.getPOSUnigramMap());
			this.stemUnigramMap.putAll(p.getStemUnigramMap());
		}
		// Initialize the paragraphCount.
		this.paragraphCount = this.paragraphList.size();

		// Compute the mean length of paragraph.
		this.paragraphMeanLength = this.sentenceCount * 1.0
				/ this.paragraphCount;
		// Initialize the paragraphLengthDeviation of the Text.
		this.paragraphLengthDeviation = Math.sqrt(sum / this.paragraphCount);

		// Initialize the sentenceMeanLength.
		this.sentenceMeanLength = this.wordCount * 1.0 / this.sentenceCount;
		this.sentenceLengthDeviation = this.ComputeSentenceLengthDeviation();

		this.wordSyllableMeanLength = this.syllableCount * 1.0 / this.wordCount;
		this.wordLetterMeanLength = this.letterCount * 1.0 / this.wordCount;

		this.ComputeWordDeviation();
		this.initPartialNgram();

		this.initContentWordTTR();
		this.initAllWordsTTR();
		this.initAllWordsMTLD();
		this.initAllWordsVOC();
	}

	/**
	 * Compute the SentenceLengthDeviation of the Text.
	 */
	private double ComputeSentenceLengthDeviation() {
		double sum = 0.0;
		for (Sentence sentence : this.sentenceList) {
			sum += Math.pow(sentence.getWordCount() - this.sentenceMeanLength,
					2.0);
		}
		return this.sentenceLengthDeviation = Math.sqrt(sum
				/ this.sentenceCount);
	}

	/**
	 * Computes the wordSyllableDeviation and wordLetterDeviation of the text.
	 */
	private void ComputeWordDeviation() {
		double sum1 = 0.0;
		double sum2 = 0.0;
		for (Sentence sentence : this.sentenceList) {
			for (Word word : sentence.getWordList()) {
				sum1 += Math.pow(word.getSyllableCount()
						- this.wordSyllableMeanLength, 2);
				sum2 += Math.pow(word.getLetterCount()
						- this.wordLetterMeanLength, 2);
			}
		}
		this.wordSyllableDeviation = Math.sqrt(sum1 / this.syllableCount);
		this.wordLetterDeviation = Math.sqrt(sum2 / this.letterCount);
	}

	/**
	 * Compute the FleschReadingEase of the Text.
	 */
	private void ComputeFleschReadingEase() {
		this.fleschReadingEase = FeatureAlgorithms.computeFleschReadingEase(
				this.sentenceMeanLength, this.wordSyllableMeanLength);
	}

	/**
	 * Compute the fleschKincaidGradeLevel of the Text.
	 */
	private void ComputeFleschKincaidGradeLevel() {
		this.fleschKincaidGradeLevel = FeatureAlgorithms
				.computeFleschKincaidGradeLevel(this.sentenceMeanLength,
						this.wordSyllableMeanLength);
	}

	/**
	 * Initialize the bigram, posBigram, stemBigram, trigram, posTrigram and
	 * stemTrigram.
	 */
	private void initPartialNgram() {
		int size = this.wordList.size();
		if (size < 2) {// if size < 2, no bigram or trigram, so this function
						// should return.
			return;
		}
		int i = 0;
		for (; i < size - 2; ++i) {
			this.initBigram(i);
			this.initTrigram(i);
		}
		this.initBigram(i);// As the upper bound is "size - 2", when the for
							// loop finished, the i will be "size - 2", so we
							// should invoke the function "initBigram(i)" again
							// to finish the counting for all the words in
							// wordList.
	}

	/**
	 * This function is just for initializing the bigrams, which are bigram,
	 * posBigram and stemBigram. It count the number of occurrence of three
	 * successive words.
	 * 
	 * @param i
	 *            the index of wordList, which is increased by the iteration of
	 *            "initPartialNgram()".
	 */
	private void initBigram(int i) {
		checkoutMap(new Bigram(this.wordList.get(i).getWordContent(),
				this.wordList.get(i + 1).getWordContent()), this.bigramMap);
		checkoutMap(new Bigram(this.wordList.get(i).getPOS(), this.wordList
				.get(i + 1).getPOS()), this.posBigramMap);
		checkoutMap(new Bigram(this.wordList.get(i).getStem(), this.wordList
				.get(i + 1).getStem()), this.stemBigramMap);
	}

	/**
	 * This function is just for initializing the trigrams, which are trigram,
	 * posTrigram and stemTrigram. It count the number of occurrence of three
	 * successive words.
	 * 
	 * @param i
	 *            the index of wordList, which is increased by the iteration of
	 *            "initPartialNgram()".
	 */
	private void initTrigram(int i) {
		checkoutMap(
				new Trigram(this.wordList.get(i).getWordContent(),
						this.wordList.get(i + 1).getWordContent(),
						this.wordList.get(i + 2).getWordContent()),
				this.trigramMap);
		checkoutMap(new Trigram(this.wordList.get(i).getPOS(), this.wordList
				.get(i + 1).getPOS(), this.wordList.get(i + 2).getPOS()),
				this.posTrigramMap);
		checkoutMap(new Trigram(this.wordList.get(i).getStem(), this.wordList
				.get(i + 1).getStem(), this.wordList.get(i + 2).getStem()),
				this.stemTrigramMap);
	}

	/**
	 * This function is a generic function.
	 * 
	 * @param key
	 *            the object of Gram or its subclasses.
	 * @param gramMap
	 *            the map of gram and count.
	 */
	private <T> void checkoutMap(T key, Map<T, Integer> gramMap) {
		if (gramMap.containsKey(key)) {
			gramMap.put(key, gramMap.get(key) + 1);
		} else {
			gramMap.put(key, 1);
		}
	}

	private void initContentWordTTR() {
		Set<String> tmpVocabulary = new HashSet<String>(this.vocabulary);
		tmpVocabulary.removeAll(StopWord.getInstance().getPunctuationSet());
		tmpVocabulary.removeAll(StopWord.getInstance().getFunctionSet());
		int typeSize = tmpVocabulary.size();

		List<String> stopList = StopWord.getInstance().getFunctionList();
		stopList.addAll(StopWord.getInstance().getPunctuationList());
		int wordSize = this.wordContentList.size();
		for (String word : wordContentList) {
			if (stopList.contains(word)) {
				wordSize--;
			}
		}
		this.contentWordsTTR = typeSize * 1.0 / wordSize;
	}

	private void initAllWordsTTR() {
		Set<String> tmpVocabulary = new HashSet<String>(this.vocabulary);
		tmpVocabulary.removeAll(StopWord.getInstance().getPunctuationSet());
		int typeSize = tmpVocabulary.size();

		List<String> stopList = StopWord.getInstance().getPunctuationList();
		int wordSize = this.wordContentList.size();
		for (String word : wordContentList) {
			if (stopList.contains(word)) {
				wordSize--;
			}
		}

		this.allWordsTTR = typeSize * 1.0 / wordSize;
	}

	private void initAllWordsMTLD() {
		List<Double> ttrForwardList = new ArrayList<Double>();
		List<Double> ttrBackwardList = new ArrayList<Double>();
		Set<String> typeForwardSet = new HashSet<String>();
		Set<String> typeBackwardSet = new HashSet<String>();
		int sumForward = 0;
		int sumBackward = 0;
		double ttrForward = 0.0;
		double ttrBackward = 0.0;
		double factorsForward = 0.0;
		double factorsBackward = 0.0;
		int size = this.wordContentList.size();
		for (int i = 0; i < size; ++i) {
			sumForward++;
			sumBackward++;

			typeForwardSet.add(this.wordContentList.get(i));
			typeBackwardSet.add(this.wordContentList.get(size - i - 1));

			ttrForward = typeForwardSet.size() * 1.0 / sumForward;
			ttrBackward = typeBackwardSet.size() * 1.0 / sumBackward;

			ttrForwardList.add(ttrForward);
			ttrBackwardList.add(ttrBackward);

			if (ttrForward <= 0.720) {
				sumForward = 0;
				typeForwardSet.clear();
				factorsForward++;
			}

			if (ttrBackward <= 0.720) {
				sumBackward = 0;
				typeBackwardSet.clear();
				factorsBackward++;
			}
		}
		double range = 1 - 0.720;
		double forwardLast = ttrForwardList.get(ttrForwardList.size() - 1);
		if (forwardLast > 0.720) {
			factorsForward += (1 - forwardLast) / range;
		}
		double backwardLast = ttrBackwardList.get(ttrBackwardList.size() - 1);
		if (backwardLast > 0.720) {
			factorsBackward += (1 - backwardLast) / range;
		}
		this.allWordsMTLD = ((size / factorsForward) + (size / factorsBackward)) / 2;
	}

	private void initAllWordsVOC() {

	}

	public int getParagraphCount() {
		return paragraphCount;
	}

	public int getSentenceCount() {
		return sentenceCount;
	}

	public int getWordCount() {
		return wordCount;
	}

	public String getTextContent() {
		return textContent;
	}

	public List<Paragraph> getParagraphList() {
		return Collections.unmodifiableList(this.paragraphList);
	}

	public List<Sentence> getSentenceList() {
		return Collections.unmodifiableList(this.sentenceList);
	}

	public List<Word> getWordList() {
		return Collections.unmodifiableList(this.wordList);
	}

	public List<String> getWordContentList() {
		return Collections.unmodifiableList(this.wordContentList);
	}

	public List<String> getPOSList() {
		return Collections.unmodifiableList(this.posList);
	}

	public List<String> getStemList() {
		return Collections.unmodifiableList(this.stemList);
	}

	public double getParagraphMeanLength() {
		return paragraphMeanLength;
	}

	public double getParagraphLengthDeviation() {
		return paragraphLengthDeviation;
	}

	public double getSentenceMeanLength() {
		return sentenceMeanLength;
	}

	public double getSentenceLengthDeviation() {
		return sentenceLengthDeviation;
	}

	public double getWordSyllableMeanLength() {
		return wordSyllableMeanLength;
	}

	public double getWordSyllableDeviation() {
		return wordSyllableDeviation;
	}

	public double getWordLetterMeanLength() {
		return wordLetterMeanLength;
	}

	public double getWordLetterDeviation() {
		return wordLetterDeviation;
	}

	public int getSyllableCount() {
		return syllableCount;
	}

	public int getLetterCount() {
		return letterCount;
	}

	public double getFleschReadingEase() {
		return fleschReadingEase;
	}

	public double getFleschKincaidGradeLevel() {
		return fleschKincaidGradeLevel;
	}

	public Map<String, List<String>> getTagWordListMap() {
		return Collections.unmodifiableMap(this.tagWordListMap);
	}

	/**
	 * Return a list of word according to the given pos. If contains the pos,
	 * return the corresponding list, or return a empty list.
	 * 
	 * @param pos
	 *            part of speech.
	 * @return
	 */
	public List<String> getListByPOS(String pos) {
		List<String> wordList = new ArrayList<String>();
		if (this.tagWordListMap.containsKey(pos)) {
			return Collections.unmodifiableList(this.tagWordListMap.get(pos));
		} else {
			return Collections.unmodifiableList(wordList);
		}
	}

	public Set<String> getVocabulary() {
		return Collections.unmodifiableSet(this.vocabulary);
	}

	public Map<Unigram, Integer> getUnigram() {
		return Collections.unmodifiableMap(this.unigramMap);
	}

	public Map<Bigram, Integer> getBigram() {
		return Collections.unmodifiableMap(this.bigramMap);
	}

	public Map<Trigram, Integer> getTrigram() {
		return Collections.unmodifiableMap(this.trigramMap);
	}

	public Map<Unigram, Integer> getPosUnigram() {
		return Collections.unmodifiableMap(this.posUnigramMap);
	}

	public Map<Bigram, Integer> getPosBigram() {
		return Collections.unmodifiableMap(this.posBigramMap);
	}

	public Map<Trigram, Integer> getPosTrigram() {
		return Collections.unmodifiableMap(posTrigramMap);
	}

	public Map<Unigram, Integer> getStemUnigram() {
		return Collections.unmodifiableMap(stemUnigramMap);
	}

	public Map<Bigram, Integer> getStemBigram() {
		return Collections.unmodifiableMap(stemBigramMap);
	}

	public Map<Trigram, Integer> getStemTrigram() {
		return Collections.unmodifiableMap(stemTrigramMap);
	}

	public double getContentWordsTTR() {
		return contentWordsTTR;
	}

	public double getAllWordsTTR() {
		return allWordsTTR;
	}

	public double getAllWordsMTLD() {
		return allWordsMTLD;
	}

	public double getAllWordsVOC() {
		return allWordsVOC;
	}
}
