package edu.pku.ss.nlp.nlptoolkit;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MRCDictParser {
	private static MRCDictParser instance = new MRCDictParser();

	private Map<String, Attribute> wordAttributeMap = new HashMap<String, Attribute>();
	private int size;

	private MRCDictParser() {
		this.size = 0;
		try (BufferedReader reader = Files.newBufferedReader(
				Paths.get("resources/dicts/mrc", "mrc2.dct"),
				Charset.forName("UTF-8"))) {
			String line = null;
			Attribute attribute = null;
			while ((line = reader.readLine()) != null) {
				this.size++;
				attribute = new Attribute(line);
				this.wordAttributeMap.put(attribute.word, attribute);
			}
			reader.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public static MRCDictParser getInstance() {
		return instance;
	}

	public Attribute getAttribute(String key) {
		Attribute attr = new Attribute();
		if (null == key) {
			return attr;
		}

		String validKey = key.toUpperCase();
		if (this.wordAttributeMap.containsKey(validKey)) {
			attr = this.wordAttributeMap.get(validKey);
		}

		return attr;
	}

	public Set<String> getWordsOfDict() {
		return Collections.unmodifiableSet(this.wordAttributeMap.keySet());
	}

	public int size() {
		return this.size;
	}
}
