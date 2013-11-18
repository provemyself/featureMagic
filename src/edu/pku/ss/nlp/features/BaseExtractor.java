package edu.pku.ss.nlp.features;

import java.util.Map;

/**
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public interface BaseExtractor {

	public String extractFeatureByString();

	public Map<String, Double> extractFeatureByMap();

	public static final String NULOOPER_CONNECTIVES = "connectives";
	public static final String NULOOPER_DESCRIPTIVE = "descriptive";
	public static final String NULOOPER_FUNDATION = "fundation";
	public static final String NULOOPER_LEXICAL_DIVERSITY = "lexical_diversity";
	public static final String NULOOPER_LSA = "lsa";
	public static final String NULOOPER_READABILITY = "readability";
	public static final String NULOOPER_REFERENTIAL_COHESION = "referential_cohesion";
	public static final String NULOOPER_SITUATION_MODEL = "situation_model";
	public static final String NULOOPER_SYNTACTIC_COMPLEXITY = "syntactic_complexity";
	public static final String NULOOPER_SYNTACTIC_PATTERN_DENSITY = "syntatic_pattern_density";
	public static final String NULOOPER_TEXT_EASABILITY_SCORES = "text_easability_scores";
	public static final String NULOOPER_WORD_INFORMATION = "word_information";
}
