package edu.pku.ss.nlp.corpus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.pku.ss.nlp.lm.Unigram;
import edu.pku.ss.nlp.nlptoolkit.NLPToolkitSingleton;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class Sentence {

	/**
	 * the total number of word in the sentence.
	 */
	private int wordCount;

	/**
	 * the total number of word syllable in the sentence.
	 */
	private int syllableCount;

	/**
	 * the total number of word letter in the sentence.
	 */
	private int letterCount;
	private Set<String> vocabulary = new HashSet<String>();

	/**
	 * Each Sentence consists of several words.
	 */
	private List<Word> wordList = new ArrayList<Word>();
	private List<String> wordContentList = new ArrayList<String>();
	private List<String> posList = new ArrayList<String>();
	private List<String> stemList = new ArrayList<String>();
	/**
	 * the untagged sentence content.
	 */
	private String senteceContent;

	/**
	 * The key is the tag, and the value is the list of words that belongs to
	 * the tag.
	 */
	private Map<String, List<String>> tagWordListMap = new HashMap<String, List<String>>();

	private Map<Unigram, Integer> unigramMap = new HashMap<Unigram, Integer>();
	private Map<Unigram, Integer> posUnigramMap = new HashMap<Unigram, Integer>();
	private Map<Unigram, Integer> stemUnigramMap = new HashMap<Unigram, Integer>();

	public Sentence(String sentence) {
		this.senteceContent = sentence;
		this.initialize();
	}

	/**
	 * Do initialize the wordList with words of sentence.
	 */
	private void initialize() {

		String[] words = NLPToolkitSingleton.getInstance().getTokenizer()
				.tokenize(senteceContent);
		String[] tags = NLPToolkitSingleton.getInstance().getTagger()
				.tagTokenizedSentence(words);

		Word word = null;
		for (int i = 0; i < tags.length; ++i) {
			word = new Word(words[i], tags[i]);
			this.letterCount += word.getLetterCount();
			this.syllableCount += word.getSyllableCount();
			this.vocabulary.add(word.getWordContent());
			this.wordList.add(word);
			this.wordContentList.add(word.getWordContent());
			this.posList.add(word.getPOS());
			this.stemList.add(word.getStem());

			this.initNGramMap(new Unigram(word.getWordContent()),
					this.unigramMap);
			this.initNGramMap(new Unigram(word.getPOS()), this.posUnigramMap);
			this.initNGramMap(new Unigram(word.getStem()), this.stemUnigramMap);

			// Initialize the tagWordListMap.
			if (this.tagWordListMap.containsKey(tags[i])) {
				this.tagWordListMap.get(tags[i]).add(words[i]);
			} else {
				List<String> wordList = new ArrayList<String>();
				wordList.add(words[i]);
				this.tagWordListMap.put(tags[i], wordList);
			}
		}
		this.wordCount = this.wordList.size();
	}

	private void initNGramMap(Unigram key, Map<Unigram, Integer> gram) {
		if (gram.containsKey(key)) {
			gram.put(key, gram.get(key) + 1);
		} else {
			gram.put(key, 1);
		}
	}

	/**
	 * @return the wordCount
	 */
	public int getWordCount() {
		return wordCount;
	}

	/**
	 * @return the sentence
	 */
	public String getSenteceContent() {
		return senteceContent;
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

	/**
	 * @return the syllableCount
	 */
	public int getSyllableCount() {
		return syllableCount;
	}

	/**
	 * @return the letterCount
	 */
	public int getLetterCount() {
		return letterCount;
	}

	public Set<String> getVocabulary() {
		return Collections.unmodifiableSet(this.vocabulary);
	}

	public Map<String, List<String>> getTagWordListMap() {
		return Collections.unmodifiableMap(this.tagWordListMap);
	}

	public Map<Unigram, Integer> getUnigramMap() {
		return Collections.unmodifiableMap(this.unigramMap);
	}

	public Map<Unigram, Integer> getPOSUnigramMap() {
		return Collections.unmodifiableMap(this.posUnigramMap);
	}

	public Map<Unigram, Integer> getStemUnigramMap() {
		return Collections.unmodifiableMap(this.stemUnigramMap);
	}
}
