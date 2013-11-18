package edu.pku.ss.nlp.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a class for the configuration of this software. Its fields includes
 * two parts, one is about the information of the referenced third-part
 * softwares, and the other is for choosing which features are required.
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class Configuration {

	// setting for OpenNLP Model
	public String opennlpEnChunker;
	public String opennlpEnNerDate;
	public String openEnDerLocation;
	public String openEnNerMoney;
	public String opennlpEnNerOrganization;
	public String opennlpEnNerPercentage;
	public String opennlpEnNerPerson;
	public String opennlpEnNerTime;
	public String opennlpEnParserChunking;
	public String opennlpEnPosMaxent;
	public String opennlpEnPosPerceptron;
	public String opennlpEnSent;
	public String opennlpEnToken;

	// setting for Stanford Model
	public String stanfordEnTagger;
	public String stanfordEnNER;

	/**
	 * Setting for NLP toolkit. Its value has four values. They are
	 * "nlp.ss.pku.nlptoolkit.lingpipe.LingPipe",
	 * "nlp.ss.pku.nlptoolkit.nulooper.Nulooper",
	 * "nlp.ss.pku.nlptoolkit.opennlp.OpenNLP",
	 * "nlp.ss.pku.nlptoolkit.stanford.Stanford".
	 */
	public String NLPToolkit;

	// Configuration for selected features to extarct.
	// features for "Descrptive".
	public boolean paragraphCount = true;
	public boolean sentenceCount = true;
	public boolean wordCount = true;
	public boolean paragraphMeanLength = true;
	public boolean paragraphLengthDeviation = true;
	public boolean sentenceMeanLength = true;
	public boolean sentenceLengthDeviation = true;
	public boolean wordSyllableMeanLength = true;
	public boolean wordSyllableDeviation = true;
	public boolean wordLetterMeanLength = true;
	public boolean wordLetterDeviation = true;

	// features for "Readability".
	public boolean fleschReadingEase = true;
	public boolean fleschKincaidGradeLevel = true;

	// features for "WordInformation".
	public boolean nounIncidence = true;
	public boolean verbIncidence = true;
	public boolean adjectiveIncidence = true;
	public boolean adverbIncidence = true;
	public boolean personalPronounIncidence = true;
	public boolean firstPersonSingularPronounIncidence = true;
	public boolean firstPersonPluralPronounIncidence = true;
	public boolean secondPersonPronounIncidence = true;
	public boolean thirdPersonSingularPronounIncidence = true;
	public boolean thirdPersonPluralPronounIncidence = true;

	// features for "lightSIDE".
	public boolean unigram = true;
	public boolean bigram = true;
	public boolean trigram = true;
	public boolean posUnigram = true;
	public boolean posBigram = true;
	public boolean posTrigram = true;
	public boolean stemUnigram = true;
	public boolean stemBigram = true;
	public boolean stemTrigram = true;

	// features for "Lexical Diversity"
	public boolean contentWordsTTR = true;
	public boolean allWordsTTR = true;
	public boolean allWordsMTLD = true;
	public boolean allWordsVOC = true;// wait to compute.

	/**
	 * Dont't let anyone instantiate this class without any parameter.
	 */
	@SuppressWarnings("unused")
	private Configuration() {

	}

	public Configuration(String opennlpEnChunker, String opennlpEnNerDate,
			String openEnDerLocation, String openEnNerMoney,
			String opennlpEnNerOrganization, String opennlpEnNerPercentage,
			String opennlpEnNerPerson, String opennlpEnNerTime,
			String opennlpEnParserChunking, String opennlpEnPosMaxent,
			String opennlpEnPosPerceptron, String opennlpEnSent,
			String opennlpEnToken, String stanfordEnTagger,
			String stanfordEnNER, String nLPToolkit, boolean paragraphCount,
			boolean sentenceCount, boolean wordCount,
			boolean paragraphMeanLength, boolean paragraphLengthDeviation,
			boolean sentenceMeanLength, boolean sentenceLengthDeviation,
			boolean wordSyllableMeanLength, boolean wordSyllableDeviation,
			boolean wordLetterMeanLength, boolean wordLetterDeviation,
			boolean fleschReadingEase, boolean fleschKincaidGradeLevel,
			boolean nounIncidence, boolean verbIncidence,
			boolean adjectiveIncidence, boolean adverbIncidence,
			boolean personalPronounIncidence,
			boolean firstPersonSingularPronounIncidence,
			boolean firstPersonPluralPronounIncidence,
			boolean secondPersonPronounIncidence,
			boolean thirdPersonSingularPronounIncidence,
			boolean thirdPersonPluralPronounIncidence, boolean unigram,
			boolean bigram, boolean trigram, boolean posUnigram,
			boolean posBigram, boolean posTrigram, boolean stemUnigram,
			boolean stemBigram, boolean stemTrigram, boolean contentWordsTTR,
			boolean allWordsTTR, boolean allWordsMTLD, boolean allWordsVOC) {
		super();
		this.opennlpEnChunker = opennlpEnChunker;
		this.opennlpEnNerDate = opennlpEnNerDate;
		this.openEnDerLocation = openEnDerLocation;
		this.openEnNerMoney = openEnNerMoney;
		this.opennlpEnNerOrganization = opennlpEnNerOrganization;
		this.opennlpEnNerPercentage = opennlpEnNerPercentage;
		this.opennlpEnNerPerson = opennlpEnNerPerson;
		this.opennlpEnNerTime = opennlpEnNerTime;
		this.opennlpEnParserChunking = opennlpEnParserChunking;
		this.opennlpEnPosMaxent = opennlpEnPosMaxent;
		this.opennlpEnPosPerceptron = opennlpEnPosPerceptron;
		this.opennlpEnSent = opennlpEnSent;
		this.opennlpEnToken = opennlpEnToken;
		this.stanfordEnTagger = stanfordEnTagger;
		this.stanfordEnNER = stanfordEnNER;
		NLPToolkit = nLPToolkit;
		this.paragraphCount = paragraphCount;
		this.sentenceCount = sentenceCount;
		this.wordCount = wordCount;
		this.paragraphMeanLength = paragraphMeanLength;
		this.paragraphLengthDeviation = paragraphLengthDeviation;
		this.sentenceMeanLength = sentenceMeanLength;
		this.sentenceLengthDeviation = sentenceLengthDeviation;
		this.wordSyllableMeanLength = wordSyllableMeanLength;
		this.wordSyllableDeviation = wordSyllableDeviation;
		this.wordLetterMeanLength = wordLetterMeanLength;
		this.wordLetterDeviation = wordLetterDeviation;
		this.fleschReadingEase = fleschReadingEase;
		this.fleschKincaidGradeLevel = fleschKincaidGradeLevel;
		this.nounIncidence = nounIncidence;
		this.verbIncidence = verbIncidence;
		this.adjectiveIncidence = adjectiveIncidence;
		this.adverbIncidence = adverbIncidence;
		this.personalPronounIncidence = personalPronounIncidence;
		this.firstPersonSingularPronounIncidence = firstPersonSingularPronounIncidence;
		this.firstPersonPluralPronounIncidence = firstPersonPluralPronounIncidence;
		this.secondPersonPronounIncidence = secondPersonPronounIncidence;
		this.thirdPersonSingularPronounIncidence = thirdPersonSingularPronounIncidence;
		this.thirdPersonPluralPronounIncidence = thirdPersonPluralPronounIncidence;
		this.unigram = unigram;
		this.bigram = bigram;
		this.trigram = trigram;
		this.posUnigram = posUnigram;
		this.posBigram = posBigram;
		this.posTrigram = posTrigram;
		this.stemUnigram = stemUnigram;
		this.stemBigram = stemBigram;
		this.stemTrigram = stemTrigram;
		this.contentWordsTTR = contentWordsTTR;
		this.allWordsTTR = allWordsTTR;
		this.allWordsMTLD = allWordsMTLD;
		this.allWordsVOC = allWordsVOC;
	}

	public Map<String, Boolean> createRequirementMap() {
		Map<String, Boolean> requirementMap = new HashMap<String, Boolean>();
		requirementMap.put("paragraphCount", this.paragraphCount);
		requirementMap.put("sentenceCount", this.sentenceCount);
		requirementMap.put("wordCount", this.wordCount);
		requirementMap.put("paragraphMeanLength", this.paragraphMeanLength);
		requirementMap.put("paragraphLengthDeviation",
				this.paragraphLengthDeviation);
		requirementMap.put("sentenceMeanLength", this.sentenceMeanLength);
		requirementMap.put("sentenceLengthDeviation",
				this.sentenceLengthDeviation);
		requirementMap.put("wordSyllableMeanLength",
				this.wordSyllableMeanLength);
		requirementMap.put("wordSyllableDeviation", this.wordSyllableDeviation);
		requirementMap.put("wordLetterMeanLength", this.wordLetterMeanLength);
		requirementMap.put("wordLetterDeviation", this.wordLetterDeviation);
		requirementMap.put("fleschReadingEase", this.fleschReadingEase);
		requirementMap.put("fleschKincaidGradeLevel",
				this.fleschKincaidGradeLevel);
		requirementMap.put("nounIncidence", this.nounIncidence);
		requirementMap.put("verbIncidence", this.verbIncidence);
		requirementMap.put("adjectiveIncidence", this.adjectiveIncidence);
		requirementMap.put("adverbIncidence", this.adverbIncidence);
		requirementMap.put("personalPronounIncidence",
				this.personalPronounIncidence);
		requirementMap.put("firstPersonSingularPronounIncidence",
				this.firstPersonSingularPronounIncidence);
		requirementMap.put("firstPersonPluralPronounIncidence",
				this.firstPersonPluralPronounIncidence);
		requirementMap.put("secondPersonPronounIncidence",
				this.secondPersonPronounIncidence);
		requirementMap.put("thirdPersonSingularPronounIncidence",
				this.thirdPersonSingularPronounIncidence);
		requirementMap.put("thirdPersonPluralPronounIncidence",
				this.thirdPersonPluralPronounIncidence);
		requirementMap.put("unigram", this.unigram);
		requirementMap.put("bigram", this.bigram);
		requirementMap.put("trigram", this.trigram);
		requirementMap.put("posUnigram", this.posUnigram);
		requirementMap.put("posBigram", this.posBigram);
		requirementMap.put("posTrigram", this.posTrigram);
		requirementMap.put("stemUnigram", this.stemUnigram);
		requirementMap.put("stemBigram", this.stemBigram);
		requirementMap.put("stemTrigram", this.stemTrigram);
		requirementMap.put("contentWordsTTR", this.contentWordsTTR);
		requirementMap.put("allWordsTTR", this.allWordsTTR);
		requirementMap.put("allWordsMTLD", this.allWordsMTLD);
		requirementMap.put("allWordsVOC", this.allWordsVOC);
		return Collections.unmodifiableMap(requirementMap);
	}
}
