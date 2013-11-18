package edu.pku.ss.nlp.nlptoolkit.lingpipe;

import edu.pku.ss.nlp.nlptoolkit.BaseParagraphSegmentor;
import edu.pku.ss.nlp.nlptoolkit.nulooper.NulooperParagraphSegmentor;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class LingPipeParagraphSegmentor implements BaseParagraphSegmentor {

	private BaseParagraphSegmentor segmentor;

	/**
	 * As the LingPipe toolkit does not provide a Paragraph segmentor, so this
	 * class use the NulooperParagraphSegmentor to segment input for
	 * consistency.
	 */
	public LingPipeParagraphSegmentor() {
		this.segmentor = new NulooperParagraphSegmentor();
	}

	@Override
	public String[] segment(String input) {
		// TODO Auto-generated method stub
		return this.segmentor.segment(input);
	}

}
