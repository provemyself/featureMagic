package edu.pku.ss.nlp.features;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import edu.pku.ss.nlp.configuration.ConfigurationManager;
import edu.pku.ss.nlp.corpus.Text;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public abstract class AbstractFeature implements BaseExtractor {

	protected Text text;

	/**
	 * The featureValueMap is a LinkedHashMap as the iteration of map is must be
	 * concerned. At the same time, we can get near-hashmap performance and
	 * insertion-order iteration by using LinkedHashMap.
	 */
	protected Map<String, Double> featureValueMap = new HashMap<String, Double>();

	public AbstractFeature(Text text) {
		this.text = text;
		this.initFeatureValueMap();
	}

	public Text getText() {
		return text;
	}

	protected abstract void initFeatureValueMap();

	/**
	 * Check that if the featureStr which is the description of some feature is
	 * required, if true,add the feature value;
	 * 
	 * @param featureStr
	 *            the string description of some feature.
	 * @param featureValue
	 *            the value of feature.
	 */
	public void checkAndAdd(String featureStr, Double featureValue) {
		if (ConfigurationManager.getInstance().isRequired(featureStr)) {
			this.featureValueMap.put(featureStr, featureValue);
		}
	}

	@Override
	abstract public String extractFeatureByString();

	@Override
	public Map<String, Double> extractFeatureByMap() {
		return Collections.unmodifiableMap(this.featureValueMap);
	}
}
