package edu.pku.ss.nlp.nlptoolkit.opennlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.pku.ss.nlp.configuration.ConfigurationManager;
import edu.pku.ss.nlp.nlptoolkit.BaseTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class OpenNLPTokenizer implements BaseTokenizer {

	private Tokenizer tokenizer;

	public OpenNLPTokenizer() {
		InputStream modelIn = null;
		try {
			modelIn = new FileInputStream(ConfigurationManager.getInstance()
					.getOpennlpEnToken());
			TokenizerModel model = new TokenizerModel(modelIn);
			this.tokenizer = new TokenizerME(model);
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
	public String[] tokenize(String input) {
		// TODO Auto-generated method stub
		return this.tokenizer.tokenize(input);
	}

}
