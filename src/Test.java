import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import edu.pku.ss.nlp.features.FeatureFacade;

public class Test {

	public static void main(String[] args) throws IOException {
		String docStr = FileUtils.readFileToString(new File("1_1_8"));
		Map<String, Double> featureMap = FeatureFacade
				.extractFeatureMapForPrediction(docStr);
		for (String key : featureMap.keySet()) {
			System.out.println(key + "\t" + featureMap.get(key));
		}
	}
}
