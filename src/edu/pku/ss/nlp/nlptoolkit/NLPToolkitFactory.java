package edu.pku.ss.nlp.nlptoolkit;

import edu.pku.ss.nlp.configuration.ConfigurationManager;

/**
 * This class is implemented following the Abstract Factory pattern to create
 * NLP toolkits from the configuration file.
 * 
 * @author 王志伟
 * @version 0.0.1
 * 
 */
public final class NLPToolkitFactory {

	/**
	 * The path for the class.
	 */
	private static final String path = ConfigurationManager.getInstance()
			.getNLPToolkit();

	public static BaseChunker createChunker() {
		String className = path + "Chunker";
		BaseChunker chunker = null;
		try {
			Class<?> cls = Class.forName(className);
			chunker = (BaseChunker) cls.getConstructor().newInstance();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return chunker;
	}

	public static BaseCoreferenceResolution createCoreferenceResolution() {
		String className = path + "CoreferenceResolution";
		BaseCoreferenceResolution cr = null;
		try {
			Class<?> cls = Class.forName(className);
			cr = (BaseCoreferenceResolution) cls.getConstructor().newInstance();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return cr;
	}

	public static BaseNER createNamedEntityExtractor() {
		String className = path + "NER";
		BaseNER nee = null;
		try {
			Class<?> cls = Class.forName(className);
			nee = (BaseNER) cls.getConstructor().newInstance();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return nee;
	}

	public static BaseParagraphSegmentor createParagraphSegmentor() {
		String className = path + "ParagraphSegmentor";
		BaseParagraphSegmentor ps = null;
		try {
			Class<?> cls = Class.forName(className);
			ps = (BaseParagraphSegmentor) cls.getConstructor().newInstance();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return ps;
	}

	public static BaseParser createParser() {
		String className = path + "Parser";
		BaseParser p = null;
		try {
			Class<?> cls = Class.forName(className);
			p = (BaseParser) cls.getConstructor().newInstance();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return p;
	}

	public static BasePOSTagger createPOSTagger() {
		String className = path + "POSTagger";
		BasePOSTagger t = null;
		try {
			Class<?> cls = Class.forName(className);
			t = (BasePOSTagger) cls.getConstructor().newInstance();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return t;
	}

	public static BaseSentenceSegmentor createSentenceSegmentor() {
		String className = path + "SentenceSegmentor";
		BaseSentenceSegmentor ss = null;
		try {
			Class<?> cls = Class.forName(className);
			ss = (BaseSentenceSegmentor) cls.getConstructor().newInstance();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return ss;
	}

	public static BaseTokenizer createTokenizer() {
		String className = path + "Tokenizer";
		BaseTokenizer t = null;
		try {
			Class<?> cls = Class.forName(className);
			t = (BaseTokenizer) cls.getConstructor().newInstance();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return t;
	}
}
