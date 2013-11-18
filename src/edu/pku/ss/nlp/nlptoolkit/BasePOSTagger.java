package edu.pku.ss.nlp.nlptoolkit;

public interface BasePOSTagger {

	/**
	 * Tags the input string and returns the tagged version.
	 * 
	 * @param input
	 *            The untagged input String.
	 * @return A String of sentences with tags inserted in the form word/tag
	 */
	public String tagString(String input);

	/**
	 * 
	 * 
	 * @param sentence
	 *            An array of tokens which consist of the sentence. For example,
	 *            if the input is "My name is Nulooper.", the "sentence" should
	 *            be {"My", "name", "is", "Nulooper", "."}
	 * @return the array of tags which are the tag of args.
	 */
	public String[] tagTokenizedSentence(String[] sentence);
}
