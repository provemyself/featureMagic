package edu.pku.ss.nlp.nlptoolkit.opennlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import edu.pku.ss.nlp.configuration.ConfigurationManager;
import edu.pku.ss.nlp.nlptoolkit.BasePOSTagger;
import edu.pku.ss.nlp.nlptoolkit.stanford.StanfordPOSTagger;

/*
 * 
 */
public class OpenNLPPOSTagger implements BasePOSTagger {

	private POSTaggerME tagger;

	private StanfordPOSTagger stanfordTagger;

	public OpenNLPPOSTagger() {
		InputStream modelIn = null;
		try {
			modelIn = new FileInputStream(ConfigurationManager.getInstance()
					.getOpennlpEnPosMaxent());
			POSModel model = new POSModel(modelIn);
			this.tagger = new POSTaggerME(model);

			this.stanfordTagger = new StanfordPOSTagger();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public String tagString(String input) {
		return this.stanfordTagger.tagString(input);
	}

	@Override
	public String[] tagTokenizedSentence(String[] sentence) {
		return this.tagger.tag(sentence);
	}
}
