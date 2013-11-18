package edu.pku.ss.nlp.nlptoolkit.opennlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import edu.pku.ss.nlp.configuration.ConfigurationManager;
import edu.pku.ss.nlp.nlptoolkit.BaseChunker;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.util.InvalidFormatException;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class OpenNLPChunker implements BaseChunker {

	private ChunkerME chunker;

	public OpenNLPChunker() {
		InputStream modelIn = null;
		ChunkerModel model = null;
		try {
			modelIn = new FileInputStream(ConfigurationManager.getInstance().getOpennlpEnChunker());
			model = new ChunkerModel(modelIn);
			this.chunker = new ChunkerME(model);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	public String[] chunk(String[] sent, String[] pos) {
		// TODO Auto-generated method stub
		return this.chunker.chunk(sent, pos);
	}

}
