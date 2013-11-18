package edu.pku.ss.nlp.nlptoolkit.opennlp;

import java.io.FileInputStream;
import java.io.InputStream;

import edu.pku.ss.nlp.configuration.ConfigurationManager;
import edu.pku.ss.nlp.nlptoolkit.BaseSentenceSegmentor;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class OpenNLPSentenceSegmentor implements BaseSentenceSegmentor {

	private SentenceDetectorME sentenceDetector;

	public OpenNLPSentenceSegmentor() {
		try {
			InputStream modelIn = new FileInputStream(ConfigurationManager
					.getInstance().getOpennlpEnSent());
			SentenceModel model = new SentenceModel(modelIn);
			this.sentenceDetector = new SentenceDetectorME(model);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] segment(String input) {
		return this.sentenceDetector.sentDetect(input);
	}

}
