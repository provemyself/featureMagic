package edu.pku.ss.nlp.features;

import java.util.HashMap;
import java.util.Map;

public final class ConcreteFeature {

	public Map<String, Double> featureMap = new HashMap<String, Double>();

	private static final String keyValueSeperator = "@@@";
	private static final String entrySeperator = "\t";

	public String score;

	public ConcreteFeature(Map<String, Double> featureMap, String score) {
		this.featureMap = featureMap;
		this.score = score;
	}

	public ConcreteFeature(Map<String, Double> featureMap) {
		this.featureMap = featureMap;
	}

	/**
	 * This function returns the whole features of an essay.
	 * 
	 * @return a map of features.
	 */
	public Map<String, Double> getPredictionFeatureMap() {
		return this.featureMap;
	}

	public Map<String, Double> getTrainFeatureMap() {
		this.featureMap.put("score", Double.parseDouble(this.score));
		return this.featureMap;
	}

	public String getComplexTrainFeatureString() {
		StringBuilder sb = this.createBasicComplexStringBuilder();
		sb.append("score").append(keyValueSeperator).append(this.score);
		return sb.toString();
	}

	public String getSimpleTrainFeatureString() {
		StringBuilder sb = this.createBasicSimpleStringBuilder();
		sb.append(this.score);
		return sb.toString();
	}

	public String getComplexPredictionFeatureString() {
		return this.createBasicComplexStringBuilder().toString();
	}

	public String getSimplePrefictionFeatureString() {
		return this.createBasicSimpleStringBuilder().toString();
	}

	private StringBuilder createBasicComplexStringBuilder() {
		StringBuilder sb = new StringBuilder();
		for (String key : featureMap.keySet()) {
			sb.append(key).append(keyValueSeperator)
					.append(featureMap.get(key)).append(entrySeperator);
		}
		return sb;
	}

	private StringBuilder createBasicSimpleStringBuilder() {
		StringBuilder sb = new StringBuilder();
		for (String key : featureMap.keySet()) {
			sb.append(featureMap.get(key)).append(entrySeperator);
		}
		return sb;
	}
}
