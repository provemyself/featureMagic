package edu.pku.ss.nlp.features;

import java.util.Map;

import edu.pku.ss.nlp.configuration.ConfigurationManager;
import edu.pku.ss.nlp.corpus.Text;

public class Fundation extends AbstractFeature implements BaseExtractor {

	public Fundation(Text text) {
		super(text);
	}

	@Override
	protected void initFeatureValueMap() {
		if (ConfigurationManager.getInstance().isRequired("unigram")) {
			this.addFeatureMap(super.text.getUnigram(), "unigram");
		}
		if (ConfigurationManager.getInstance().isRequired("bigram")) {
			this.addFeatureMap(super.text.getBigram(), "bigram");
		}
		if (ConfigurationManager.getInstance().isRequired("trigram")) {
			this.addFeatureMap(super.text.getTrigram(), "trigram");
		}
		if (ConfigurationManager.getInstance().isRequired("posUnigram")) {
			this.addFeatureMap(super.text.getPosUnigram(), "posUnigram");
		}
		if (ConfigurationManager.getInstance().isRequired("posBigram")) {
			this.addFeatureMap(super.text.getPosBigram(), "posBigram");
		}
		if (ConfigurationManager.getInstance().isRequired("posTrigram")) {
			this.addFeatureMap(super.text.getPosTrigram(), "posTrigram");
		}
		if (ConfigurationManager.getInstance().isRequired("stemUnigram")) {
			this.addFeatureMap(super.text.getStemUnigram(), "stemUnigram");
		}
		if (ConfigurationManager.getInstance().isRequired("stemBigram")) {
			this.addFeatureMap(super.text.getStemBigram(), "stemBigram");
		}
		if (ConfigurationManager.getInstance().isRequired("stemTrigram")) {
			this.addFeatureMap(super.text.getStemTrigram(), "stemTrigram");
		}
	}

	private <T> void addFeatureMap(Map<T, Integer> gramMap, String n) {
		for (T key : gramMap.keySet()) {
			this.featureValueMap.put(n + "_" + key.toString(), gramMap.get(key)
					.doubleValue());
		}
	}

	@Override
	public String extractFeatureByString() {
		// TODO Auto-generated method stub
		return null;
	}
}
