package edu.pku.ss.nlp.features;

import java.util.List;

import edu.pku.ss.nlp.corpus.Text;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class Connectives extends AbstractFeature {

	@SuppressWarnings("unused")
	private final String[] causals = { "because", "so", "consequently", "thus",
			"hence", "as", "therefore", "accordingly", "since", "until",
			"whenever" };
	@SuppressWarnings("unused")
	private final String[] logicals = { "and", "or" };
	@SuppressWarnings("unused")
	private final String[] contrastives = { "although", "whereas",
			"nevertheless", "but", "however", "alternatively", "yet", "still",
			"instead", "whereas", "otherwise", "although" };
	@SuppressWarnings("unused")
	private final String[] temporals = { "first", "unitl", "initially",
			"firstly", "then", "after", "afterwards", "finally", "once",
			"secondly", "next", "subsequently", "meanwhile", "eventually" };
	@SuppressWarnings("unused")
	private final String[] additives = { "and", "moreover", "furthermore",
			"also", "too", "again" };
	@SuppressWarnings("unused")
	private final String[] positives = { "also", "moreover" };
	@SuppressWarnings("unused")
	private final String[] negatives = { "however", "but" };

	/**
	 * This is the incidence score of all connectives.
	 */
	private double connectivesIncidence;

	/**
	 * This is the incidence score of logic connectives.
	 */
	private double logicConnectivesIncidence;

	/**
	 * This is the incidence score of constrastive connectives.
	 */
	private double constrastiveConnectivesIncidence;

	/**
	 * This is the incidence score of temporal connectives.
	 */
	private double temporalConnectivesIncidence;

	/**
	 * This is the incidence score of additive connectives.
	 */
	private double additiveConnectivesIncidence;

	/**
	 * This is the incidence score of positive connectives.
	 */
	private double positiveConnectivesIncidence;

	/**
	 * This is the incidence score of negative connectives.
	 */
	private double negativeConnectivesIncidence;

	public Connectives(Text text) {
		super(text);
		this.init();
	}

	private void init() {
		List<String> strList = this.text.getListByPOS("CC");
		this.connectivesIncidence = FeatureAlgorithms.getIncidenceScore(
				strList.size(), this.text.getWordCount());
	}

	public double getConnectivesIncidence() {
		return connectivesIncidence;
	}

	public double getLogicConnectivesIncidence() {
		return logicConnectivesIncidence;
	}

	public double getConstrastiveConnectivesIncidence() {
		return constrastiveConnectivesIncidence;
	}

	public double getTemporalConnectivesIncidence() {
		return temporalConnectivesIncidence;
	}

	public double getAdditiveConnectivesIncidence() {
		return additiveConnectivesIncidence;
	}

	public double getPositiveConnectivesIncidence() {
		return positiveConnectivesIncidence;
	}

	public double getNegativeConnectivesIncidence() {
		return negativeConnectivesIncidence;
	}

	@Override
	protected void initFeatureValueMap() {
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public String extractFeatureByString() {
		// TODO Auto-generated method stub
		return null;
	}
}
