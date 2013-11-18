/**
 * 
 */
package edu.pku.ss.nlp.features;

import edu.pku.ss.nlp.corpus.Text;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class Descriptive extends AbstractFeature implements BaseExtractor {

	public Descriptive(Text text) {
		super(text);
	}

	@Override
	protected void initFeatureValueMap() {
		this.checkAndAdd("paragraphCount",
				(double) this.text.getParagraphCount());
		this.checkAndAdd("sentenceCount", (double) this.text.getSentenceCount());
		this.checkAndAdd("wordCount", (double) this.text.getWordCount());
		this.checkAndAdd("paragraphMeanLength",
				this.text.getParagraphMeanLength());
		this.checkAndAdd("paragraphLengthDeviation",
				this.text.getParagraphLengthDeviation());
		this.checkAndAdd("sentenceMeanLength",
				this.text.getSentenceMeanLength());
		this.checkAndAdd("sentenceLengthDeviation",
				this.text.getSentenceLengthDeviation());
		this.checkAndAdd("wordSyllableMeanLength",
				this.text.getWordSyllableMeanLength());
		this.checkAndAdd("wordSyllableDeviation",
				this.text.getWordSyllableDeviation());
		this.checkAndAdd("wordLetterMeanLength",
				this.text.getWordLetterMeanLength());
		this.checkAndAdd("wordLetterDeviation",
				this.text.getWordLetterDeviation());
	}

	@Override
	public String extractFeatureByString() {
		return null;
	}
}
