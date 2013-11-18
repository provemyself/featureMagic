package edu.pku.ss.nlp.nlptoolkit;

import java.io.File;
import java.util.List;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import edu.mit.jwi.morph.WordnetStemmer;

public class WordnetParser {
	private static WordnetParser instance = new WordnetParser();

	public static final String dictPath = "resources/dicts/wordnet";

	private IDictionary dict;
	private WordnetStemmer stemmer;

	private WordnetParser() {
		try {
			File wordnetDir = new File(dictPath);
			this.dict = new Dictionary(wordnetDir);
			this.stemmer = new WordnetStemmer(dict);
			this.dict.open();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static WordnetParser getInstance() {
		return instance;
	}

	public List<String> stem(String word, POS pos) {
		return this.stemmer.findStems(word, pos);
	}

	/**
	 * Returns the number of word senses. If Wordnet does not have the given
	 * word, then returns 1.
	 * 
	 * @param word
	 * @param pos
	 *            part of speech.
	 * @return
	 */
	public int getPolysemys(String word, POS pos) {
		int polysemys = 1;// default value is 1.
		IIndexWord idxWord = dict.getIndexWord(word, pos);
		if (null != idxWord) {
			List<IWordID> wordIDs = idxWord.getWordIDs();
			polysemys = wordIDs.size();
		}
		return polysemys;
	}

	/**
	 * wait to implement.
	 * 
	 * @param word
	 * @param pos
	 * @return
	 */
	public int getHypernyms(String word, POS pos) {
		IIndexWord idxWord = dict.getIndexWord(word, pos);
		List<IWordID> wordIDs = idxWord.getWordIDs();
		System.out.println("List<IWordID>: " + wordIDs.size());

		int hypernymNumber = 0;
		IWord iword;
		ISynset synset;
		List<ISynsetID> hypernyms;
		iword = dict.getWord(wordIDs.get(0));
		synset = iword.getSynset();
		hypernyms = synset.getRelatedSynsets(Pointer.HYPERNYM);

		List<IWord> words;
		for (ISynsetID sid : hypernyms) {
			words = dict.getSynset(sid).getWords();
			System.out.print(sid + "{ ");
			for (IWord w : words) {
				System.out.print(w.getLemma() + ", ");

			}
			System.out.println("}");
		}

		System.out.println(hypernyms.size());
		hypernymNumber += hypernyms.size();
		return hypernymNumber;
	}
}
