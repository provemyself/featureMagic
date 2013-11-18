package edu.pku.ss.nlp.nlptoolkit.stanford;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.pku.ss.nlp.nlptoolkit.BaseTokenizer;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class StanfordTokenizer implements BaseTokenizer {

	public StanfordTokenizer() {
	}

	@Override
	public String[] tokenize(String input) {
		if (input == null || input.isEmpty()) {
			return null;
		}

		PTBTokenizer<CoreLabel> ptbt = new PTBTokenizer<CoreLabel>(
				new StringReader(input), new CoreLabelTokenFactory(), "");
		List<String> tokens = new ArrayList<String>();
		for (CoreLabel label; ptbt.hasNext();) {
			label = (CoreLabel) ptbt.next();
			tokens.add(label.toString());
		}
		String[] returnTokens = new String[tokens.size()];
		tokens.toArray(returnTokens);
		return returnTokens;
	}
}
