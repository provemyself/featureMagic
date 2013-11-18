package edu.pku.ss.nlp.corpus;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class StopWord {

	private static StopWord instance = new StopWord();

	private List<String> punctuationList;
	private List<String> functionList;

	private Set<String> punctuationSet;
	private Set<String> functionSet;

	private StopWord() {
		try {
			this.functionList = FileUtils.readLines(new File(
					"stopwords/function.stp"));
			this.punctuationList = FileUtils.readLines(new File(
					"stopwords/punctuation.stp"));

			this.functionSet = new HashSet<String>(this.functionList);
			this.punctuationSet = new HashSet<String>(this.punctuationList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static StopWord getInstance() {
		return instance;
	}

	public List<String> getFunctionList() {
		return this.functionList;
	}

	public List<String> getPunctuationList() {
		return this.punctuationList;
	}

	public Set<String> getFunctionSet() {
		return this.functionSet;
	}

	public Set<String> getPunctuationSet() {
		return this.punctuationSet;
	}
}
