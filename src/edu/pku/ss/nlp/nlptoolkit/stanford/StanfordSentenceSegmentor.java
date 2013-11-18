package edu.pku.ss.nlp.nlptoolkit.stanford;

import edu.pku.ss.nlp.nlptoolkit.BaseSentenceSegmentor;
import edu.pku.ss.nlp.nlptoolkit.opennlp.OpenNLPSentenceSegmentor;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class StanfordSentenceSegmentor implements BaseSentenceSegmentor {

	private BaseSentenceSegmentor segmentor;

	/**
	 * As the Stanford NLP toolkit does not provide a sentence segmentor, so
	 * this class uses OpenNLPSentenceSegmentor to segment input for
	 * consistency.
	 */
	public StanfordSentenceSegmentor() {
		this.segmentor = new OpenNLPSentenceSegmentor();
	}

	@Override
	public String[] segment(String input) {
		if (input == null || input.isEmpty()) {
			return null;
		}
		return this.segmentor.segment(input);
	}

}
