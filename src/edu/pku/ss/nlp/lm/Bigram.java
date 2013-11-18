package edu.pku.ss.nlp.lm;

/**
 * This class represents the bigram of N-gram model. It has overrides the
 * hashCode() and equals(), as its object will be the key of map.
 * 
 * @author 王志伟
 * @version 0.0.1
 */
public class Bigram extends Unigram {
	public String second;

	public Bigram(String first, String second) {
		super(first);
		this.second = second;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bigram other = (Bigram) obj;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.first + " " + this.second;
	}

}
