package edu.pku.ss.nlp.nlptoolkit.stanford;

import edu.pku.ss.nlp.nlptoolkit.BaseParagraphSegmentor;
import edu.pku.ss.nlp.nlptoolkit.nulooper.NulooperParagraphSegmentor;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class StanfordParagraphSegmentor implements BaseParagraphSegmentor {

	private BaseParagraphSegmentor segmentor;

	/**
	 * As the Stanford NLP toolkit does not provide a paragraph segmentor,so
	 * this class uses the NulooperParagraphSegmentor to segment input for
	 * consistency.
	 */
	public StanfordParagraphSegmentor() {
		this.segmentor = new NulooperParagraphSegmentor();
	}

	@Override
	public String[] segment(String input) {
		if (input == null || input.isEmpty()) {
			return null;
		}
		return this.segmentor.segment(input);
	}

}
