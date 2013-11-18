package edu.pku.ss.nlp.lm;

public class Unigram extends AbstractGram {

	public String first;

	public Unigram(String first) {
		super();
		this.first = first;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unigram other = (Unigram) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.first;
	}
}
