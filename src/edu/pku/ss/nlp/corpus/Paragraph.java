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
public class Paragraph {

	/**
	 * the total number of sentence in the paragraph.
	 */
	private int sentenceCount;

	/**
	 * the total number of word in the paragraph.
	 */
	private int wordCount;

	/**
	 * Each Paragraph consists of several sentences.
	 */
	private List<Sentence> sentenceList = new ArrayList<Sentence>();

	/**
	 * a paragraph of the text.
	 */
	private String paragraphContent;

	/**
	 * the total number of word syllable in the paragraph.
	 */
	private int syllableCount;

	/**
	 * the total number of word letter in the paragraph.
	 */
	private int letterCount;

	/**
	 * the key is the tag of word, and the value is the list of words that
	 * belong to the tag.
	 */
	private Map<String, List<String>> tagWordListMap = new HashMap<String, List<String>>();

	private Set<String> vocabulary = new HashSet<String>();
	private List<Word> wordList = new ArrayList<Word>();
	private List<String> wordContentList = new ArrayList<String>();
	private List<String> posList = new ArrayList<String>();
	private List<String> stemList = new ArrayList<String>();

	private Map<Unigram, Integer> unigramMap = new HashMap<Unigram, Integer>();
	private Map<Unigram, Integer> posUnigramMap = new HashMap<Unigram, Integer>();
	private Map<Unigram, Integer> stemUnigramMap = new HashMap<Unigram, Integer>();

	public Paragraph(String paragraph) {
		this.paragraphContent = paragraph;
		this.initialize();
	}

	private void initialize() {
		String[] sentences = NLPToolkitSingleton.getInstance()
				.getSentenceSegmentor().segment(this.paragraphContent);

		Sentence sent = null;
		for (String sentence : sentences) {
			sent = new Sentence(sentence);

			this.wordCount += sent.getWordCount();
			this.syllableCount += sent.getSyllableCount();
			this.letterCount += sent.getLetterCount();
			this.sentenceList.add(sent);
			this.tagWordListMap.putAll(sent.getTagWordListMap());
			this.vocabulary.addAll(sent.getVocabulary());
			this.wordList.addAll(sent.getWordList());
			this.wordContentList.addAll(sent.getWordContentList());
			this.posList.addAll(sent.getPOSList());
			this.stemList.addAll(sent.getStemList());

			this.unigramMap.putAll(sent.getUnigramMap());
			this.posUnigramMap.putAll(sent.getPOSUnigramMap());
			this.stemUnigramMap.putAll(sent.getStemUnigramMap());
		}
		this.sentenceCount = sentenceList.size();
	}

	/**
	 * @return the sentenceCount
	 */
	public int getSentenceCount() {
		return sentenceCount;
	}

	/**
	 * @return the wordCount
	 */
	public int getWordCount() {
		return wordCount;
	}

	/**
	 * @return the paragraph
	 */
	public String getParagraphContent() {
		return paragraphContent;
	}

	public List<Sentence> getSentenceList() {
		return Collections.unmodifiableList(sentenceList);
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

	public Map<String, List<String>> getTagWordListMap() {
		return Collections.unmodifiableMap(this.tagWordListMap);
	}

	public Set<String> getVocabulary() {
		return Collections.unmodifiableSet(this.vocabulary);
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
