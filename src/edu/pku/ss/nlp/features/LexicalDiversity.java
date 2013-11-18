package edu.pku.ss.nlp.features;

import edu.pku.ss.nlp.corpus.Text;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class LexicalDiversity extends AbstractFeature implements BaseExtractor {

	public LexicalDiversity(Text text) {
		super(text);
	}

	@Override
	protected void initFeatureValueMap() {
		this.checkAndAdd("contentWordsTTR", this.text.getContentWordsTTR());
		this.checkAndAdd("allWordsTTR", this.text.getAllWordsTTR());
		this.checkAndAdd("allWordsMTLD", this.text.getAllWordsMTLD());
		this.checkAndAdd("allWordsVOC", this.text.getAllWordsVOC());
	}

	@Override
	public String extractFeatureByString() {
		// TODO Auto-generated method stub
		return null;
	}
}
