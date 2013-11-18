package edu.pku.ss.nlp.nlptoolkit.nulooper;

import edu.pku.ss.nlp.nlptoolkit.BaseSentenceSegmentor;
import edu.pku.ss.nlp.nlptoolkit.opennlp.OpenNLPSentenceSegmentor;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class NulooperSentenceSegmentor implements BaseSentenceSegmentor {

	private BaseSentenceSegmentor segmentor;

	/**
	 * As the Nulooper NLP toolkit does not provide a sentence segmentor,so this
	 * class uses OpenNLPSentenceSegmentor to segment input for consistency.
	 */
	public NulooperSentenceSegmentor() {
		this.segmentor = new OpenNLPSentenceSegmentor();
	}

	@Override
	public String[] segment(String input) {
		// TODO Auto-generated method stub
		return this.segmentor.segment(input);
	}
}
