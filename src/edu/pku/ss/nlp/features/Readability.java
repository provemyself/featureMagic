package edu.pku.ss.nlp.features;

import edu.pku.ss.nlp.corpus.Text;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class Readability extends AbstractFeature implements BaseExtractor {

	public Readability(Text text) {
		super(text);
	}

	@Override
	protected void initFeatureValueMap() {
		this.checkAndAdd("fleschReadingEase", this.text.getFleschReadingEase());
		this.checkAndAdd("fleschKincaidGradeLevel",
				this.text.getFleschKincaidGradeLevel());
	}

	@Override
	public String extractFeatureByString() {
		// TODO Auto-generated method stub
		return null;
	}
}
