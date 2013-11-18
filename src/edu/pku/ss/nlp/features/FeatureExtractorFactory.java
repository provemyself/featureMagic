package edu.pku.ss.nlp.features;

import edu.pku.ss.nlp.corpus.Text;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class FeatureExtractorFactory {

	/**
	 * 
	 * @param type
	 *            the requirement type
	 * @return the feature instance according to the argument.
	 */
	public static BaseExtractor createFeatureExtractor(Text text, String type) {

		BaseExtractor extractor = null;

		switch (type) {
		case BaseExtractor.NULOOPER_CONNECTIVES:
			extractor = new Connectives(text);
			break;
		case BaseExtractor.NULOOPER_DESCRIPTIVE:
			extractor = new Descriptive(text);
			break;
		case BaseExtractor.NULOOPER_READABILITY:
			extractor = new Readability(text);
			break;
		case BaseExtractor.NULOOPER_LEXICAL_DIVERSITY:
			extractor = new LexicalDiversity(text);
			break;
		case BaseExtractor.NULOOPER_FUNDATION:
			extractor = new Fundation(text);
			break;
		case BaseExtractor.NULOOPER_LSA:
			extractor = new LSA(text);
			break;
		case BaseExtractor.NULOOPER_REFERENTIAL_COHESION:
			extractor = new ReferentialCohesion(text);
			break;
		case BaseExtractor.NULOOPER_SITUATION_MODEL:
			extractor = new SituationModel(text);
			break;
		case BaseExtractor.NULOOPER_SYNTACTIC_COMPLEXITY:
			extractor = new SyntacticComplexity(text);
			break;
		case BaseExtractor.NULOOPER_SYNTACTIC_PATTERN_DENSITY:
			extractor = new SyntacticPatternDensity(text);
			break;
		case BaseExtractor.NULOOPER_WORD_INFORMATION:
			extractor = new WordInformation(text);
			break;
		default:
			break;
		}

		return extractor;
	}
}
