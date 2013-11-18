package edu.pku.ss.nlp.nlptoolkit.stanford;

import edu.pku.ss.nlp.configuration.ConfigurationManager;
import edu.pku.ss.nlp.nlptoolkit.BasePOSTagger;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class StanfordPOSTagger implements BasePOSTagger {

	private MaxentTagger tagger;

	public StanfordPOSTagger() {
		this.tagger = new MaxentTagger(ConfigurationManager.getInstance()
				.getStanfordEnTagger());
	}

	@Override
	public String tagString(String input) {
		return this.tagger.tagString(input);
	}

	@Override
	public String[] tagTokenizedSentence(String[] sentence) {
		StringBuilder sb = new StringBuilder();
		int j = 0;
		while (true) {
			sb.append(sentence[j]);
			if (++j >= sentence.length) {
				break;
			} else {
				sb.append(" ");
			}
		}

		String taggedResult = this.tagString(sb.toString());

		String[] tokensWithTag = taggedResult.split(" ");
		String[] taggs = new String[tokensWithTag.length];

		String tmp;
		int index;
		for (int i = 0; i < tokensWithTag.length; ++i) {
			tmp = tokensWithTag[i];
			index = tmp.indexOf("_");
			taggs[i] = tmp.substring(index + 1);
		}
		return taggs;
	}
}
