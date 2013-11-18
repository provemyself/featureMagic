package edu.pku.ss.nlp.configuration;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

/**
 * This is a singleton class which is used to manage the configuration. During
 * the running of this software, there is only a ConfigurationManager object.
 * This class implements following the singleton pattern.
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class ConfigurationManager {

	private Configuration config;

	private static ConfigurationManager manager = new ConfigurationManager();

	private static final String CONFIG_PATH = "config/config.json";

	private Map<String, Boolean> requirementMap;

	private ConfigurationManager() {
		Gson gson = new Gson();
		String configContent = null;
		try {
			configContent = this.readConfigFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.config = gson.fromJson(configContent, Configuration.class);
		this.requirementMap = this.config.createRequirementMap();
	}

	public static ConfigurationManager getInstance() {
		return manager;
	}

	/**
	 * Reads the content of the configuration file into string. The file is
	 * always closed.
	 * 
	 * @return the file contents.
	 * @throws IOException
	 *             in case of an I/O error
	 */
	private String readConfigFile() throws IOException {
		File file = new File(ConfigurationManager.CONFIG_PATH);
		return FileUtils.readFileToString(file);
	}

	public Configuration getConfiguration() {
		return this.config;
	}

	public String getOpennlpEnChunker() {
		return this.config.opennlpEnChunker;
	}

	public String getOpennlpEnNerDate() {
		return this.config.opennlpEnNerDate;
	}

	public String getOpennlpEnDerLocation() {
		return this.config.openEnDerLocation;
	}

	public String getOpennlpEnNerMoney() {
		return this.config.openEnNerMoney;
	}

	public String getOpennlpEnNerOrganization() {
		return this.config.opennlpEnNerOrganization;
	}

	public String getOpennlpEnNerPercentage() {
		return this.config.opennlpEnNerPercentage;
	}

	public String getOpennlpEnNerPerson() {
		return this.config.opennlpEnNerPerson;
	}

	public String getOpennlpEnNerTime() {
		return this.config.opennlpEnNerTime;
	}

	public String getOpennlpEnParserChunking() {
		return this.config.opennlpEnParserChunking;
	}

	public String getOpennlpEnPosMaxent() {
		return this.config.opennlpEnPosMaxent;
	}

	public String getOpennlpEnPosPerceptron() {
		return this.config.opennlpEnPosPerceptron;
	}

	public String getOpennlpEnSent() {
		return this.config.opennlpEnSent;
	}

	public String getOpennlpEnToken() {
		return this.config.opennlpEnToken;
	}

	public String getNLPToolkit() {
		return this.config.NLPToolkit;
	}

	public String getStanfordEnTagger() {
		return this.config.stanfordEnTagger;
	}

	public String getStanfordEnNER() {
		return this.config.stanfordEnNER;
	}

	/**
	 * 
	 * @param feature
	 *            one feature of an document and its type is String.
	 * @return if the argument is in the configuration file then return its
	 *         value, if not returns false.
	 */
	public boolean isRequired(String feature) {
		return this.requirementMap.containsKey(feature) ? this.requirementMap
				.get(feature) : false;
	}

	public Map<String, Boolean> getRequirementMap() {
		return Collections.unmodifiableMap(this.requirementMap);
	}
}
