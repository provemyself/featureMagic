package edu.pku.ss.nlp.nlptoolkit.nulooper;

import edu.pku.ss.nlp.nlptoolkit.Constants;
import edu.pku.ss.nlp.nlptoolkit.BaseParagraphSegmentor;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class NulooperParagraphSegmentor implements BaseParagraphSegmentor {

	public NulooperParagraphSegmentor() {

	}

	@Override
	public String[] segment(String input) {
		return input.split(Constants.LINE_SEPARATOR_UNIX + "|"
				+ Constants.LINE_SEPARATOR_WINDOWS);
	}
}
