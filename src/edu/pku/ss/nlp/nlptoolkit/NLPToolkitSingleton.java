package edu.pku.ss.nlp.nlptoolkit;

/**
 * This class follows singleton pattern.
 * 
 * @author 王志伟
 * @version 0.0.1
 * 
 */
public class NLPToolkitSingleton {

	private static NLPToolkitSingleton instance = new NLPToolkitSingleton();

	private BaseChunker chunker;
	private BaseCoreferenceResolution coreferenceResolution;
	private BaseNER namedEntityExtractor;
	private BaseParagraphSegmentor paragraphSegmentor;
	private BaseParser parser;
	private BasePOSTagger tagger;
	private BaseSentenceSegmentor sentenceSegmentor;
	private BaseTokenizer tokenizer;

	/**
	 * Don't let anyone instantiate this class.
	 */
	private NLPToolkitSingleton() {
		this.chunker = NLPToolkitFactory.createChunker();
		this.coreferenceResolution = NLPToolkitFactory
				.createCoreferenceResolution();
		this.namedEntityExtractor = NLPToolkitFactory
				.createNamedEntityExtractor();
		this.paragraphSegmentor = NLPToolkitFactory.createParagraphSegmentor();
		this.parser = NLPToolkitFactory.createParser();
		this.tagger = NLPToolkitFactory.createPOSTagger();
		this.sentenceSegmentor = NLPToolkitFactory.createSentenceSegmentor();
		this.tokenizer = NLPToolkitFactory.createTokenizer();
	}

	public static NLPToolkitSingleton getInstance() {
		return NLPToolkitSingleton.instance;
	}

	public BaseChunker getChunker() {
		return chunker;
	}

	public BaseCoreferenceResolution getCoreferenceResolution() {
		return coreferenceResolution;
	}

	public BaseNER getNamedEntityExtractor() {
		return namedEntityExtractor;
	}

	public BaseParagraphSegmentor getParagraphSegmentor() {
		return paragraphSegmentor;
	}

	public BaseParser getParser() {
		return parser;
	}

	public BasePOSTagger getTagger() {
		return tagger;
	}

	public BaseSentenceSegmentor getSentenceSegmentor() {
		return sentenceSegmentor;
	}

	public BaseTokenizer getTokenizer() {
		return tokenizer;
	}
}
