package edu.pku.ss.nlp.features;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import edu.pku.ss.nlp.corpus.Text;

/**
 * The implementation of this class follows the facade pattern. This class is
 * used to provide a simple interface of all the features. Then clients can get
 * any kind of features they want just through this class.
 * 
 * @author 王志伟
 * 
 */
public class FeatureFacade {

	/**
	 * 
	 * @param docPath
	 *            the path for all the documents that will be extracted features
	 *            from.
	 * @param encoding
	 *            the encoding of all the documents.
	 * @return a list of map whose key is the feature's name and value is the
	 *         feature's value.
	 * @throws IOException
	 *             if the docPath is not available, this function will throw
	 *             IOException.
	 */
	public static List<Map<String, Double>> extractFeatureMapForTrain(
			String docPath, Charset encoding) throws IOException {
		String score = null;
		String docStr = null;
		List<String> scoreList = new ArrayList<String>();
		List<String> docStrList = new ArrayList<String>();

		File dir = new File(docPath);
		for (File file : dir.listFiles()) {
			docStr = FileUtils.readFileToString(file, encoding);
			docStrList.add(docStr);

			score = file.getName().substring(
					file.getName().lastIndexOf("_") + 1);
			scoreList.add(score);
		}

		return extractFeatureMapForTrain(docStrList, scoreList);
	}

	/**
	 * 
	 * @param docStr
	 *            the string of a document.
	 * @param score
	 *            the score of a document.
	 * @return
	 */
	public static Map<String, Double> extractFeatureMapForTrain(String docStr,
			String score) {
		return doExtractObjectForTrain(docStr, score).getTrainFeatureMap();
	}

	public static List<Map<String, Double>> extractFeatureMapForTrain(
			List<String> docStrList, List<String> scoreList) {
		List<Map<String, Double>> featureMapList = new ArrayList<Map<String, Double>>();
		for (int i = 0; i < docStrList.size(); ++i) {
			featureMapList.add(extractFeatureMapForTrain(docStrList.get(i),
					scoreList.get(i)));
		}
		return Collections.unmodifiableList(featureMapList);
	}

	public static List<Map<String, Double>> extractFeatureMapForPrediction(
			String docPath, Charset encoding) throws IOException {
		File dir = new File(docPath);
		List<String> docStrList = new ArrayList<String>();
		for (File f : dir.listFiles()) {
			docStrList.add(FileUtils.readFileToString(f, encoding));
		}
		return extractFeatureMapForPrediction(docStrList);
	}

	public static Map<String, Double> extractFeatureMapForPrediction(
			String docStr) {
		return doExtractObjectForPrediction(docStr).getPredictionFeatureMap();
	}

	public static List<Map<String, Double>> extractFeatureMapForPrediction(
			List<String> docStrList) {
		List<Map<String, Double>> mapList = new ArrayList<Map<String, Double>>();
		for (String docStr : docStrList) {
			mapList.add(extractFeatureMapForPrediction(docStr));
		}
		return Collections.unmodifiableList(mapList);
	}

	public static String extractFeatureStringForPrediction(String docStr,
			boolean isSimpleFormat) {
		String fs = null;
		if (isSimpleFormat) {
			fs = doExtractObjectForPrediction(docStr)
					.getSimplePrefictionFeatureString();
		} else {
			fs = doExtractObjectForPrediction(docStr)
					.getComplexPredictionFeatureString();
		}
		return fs;
	}

	public static List<String> extractFeatureStringForPrediction(
			List<String> docStrList, boolean isSimpleFormat) {
		List<String> fl = new ArrayList<String>();
		for (String docStr : docStrList) {
			fl.add(extractFeatureStringForPrediction(docStr, isSimpleFormat));
		}
		return Collections.unmodifiableList(fl);
	}

	public static List<String> extractFeatureStringForTrain(String docPath,
			boolean isSimpleFormat) throws IOException {
		return extractFeatureStringForTrain(docPath, isSimpleFormat,
				Charset.forName("UTF-8"));
	}

	public static List<String> extractFeatureStringForTrain(String docPath,
			boolean isSimpleFormat, Charset encoding) throws IOException {
		String score = null;
		String docStr = null;
		List<String> scoreList = new ArrayList<String>();
		List<String> docStrList = new ArrayList<String>();

		File dir = new File(docPath);
		for (File file : dir.listFiles()) {
			docStr = FileUtils.readFileToString(file, encoding);
			docStrList.add(docStr);

			score = file.getName().substring(
					file.getName().lastIndexOf("_") + 1);
			scoreList.add(score);
		}
		return extractFeatureStringForTrain(docStrList, isSimpleFormat,
				scoreList);
	}

	public static String extractFeatureStringForTrain(String docStr,
			boolean isSimpleFormat, String score) {
		String fs = null;
		if (isSimpleFormat) {
			fs = doExtractObjectForTrain(docStr, score)
					.getSimpleTrainFeatureString();
		} else {
			fs = doExtractObjectForTrain(docStr, score)
					.getComplexTrainFeatureString();
		}
		return fs;
	}

	public static List<String> extractFeatureStringForTrain(
			List<String> docStrList, boolean isSimpleFormat,
			List<String> scoreList) {
		List<String> featureStrList = new ArrayList<String>();
		int size = docStrList.size();
		for (int i = 0; i < size; ++i) {
			featureStrList.add(extractFeatureStringForTrain(docStrList.get(i),
					isSimpleFormat, scoreList.get(i)));
		}
		return Collections.unmodifiableList(featureStrList);
	}

	private static ConcreteFeature doExtractObjectForPrediction(String docStr) {

		Map<String, Double> featureMap = new HashMap<String, Double>();
		Text text = new Text(docStr);

		List<BaseExtractor> extractors = createExtractorList(text);
		for (BaseExtractor e : extractors) {
			featureMap.putAll(e.extractFeatureByMap());
		}

		return new ConcreteFeature(featureMap);
	}

	private static ConcreteFeature doExtractObjectForTrain(String docStr,
			String score) {
		Map<String, Double> featureMap = new HashMap<String, Double>();
		Text text = new Text(docStr);

		List<BaseExtractor> extractors = createExtractorList(text);
		for (BaseExtractor e : extractors) {
			featureMap.putAll(e.extractFeatureByMap());
		}

		return new ConcreteFeature(featureMap, score);
	}

	private static List<BaseExtractor> createExtractorList(Text text) {
		List<BaseExtractor> extractors = new ArrayList<BaseExtractor>();
		extractors.add(FeatureExtractorFactory.createFeatureExtractor(text,
				BaseExtractor.NULOOPER_DESCRIPTIVE));
		extractors.add(FeatureExtractorFactory.createFeatureExtractor(text,
				BaseExtractor.NULOOPER_READABILITY));
		extractors.add(FeatureExtractorFactory.createFeatureExtractor(text,
				BaseExtractor.NULOOPER_WORD_INFORMATION));
		extractors.add(FeatureExtractorFactory.createFeatureExtractor(text,
				BaseExtractor.NULOOPER_FUNDATION));
		// added todady
		extractors.add(FeatureExtractorFactory.createFeatureExtractor(text,
				BaseExtractor.NULOOPER_LEXICAL_DIVERSITY));
		return Collections.unmodifiableList(extractors);
	}
}
