package edu.pku.ss.nlp.lm;

public class Trigram extends Bigram {

	public String third;

	public Trigram(String first, String second, String third) {
		super(first, second);
		this.third = third;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((third == null) ? 0 : third.hashCode());
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
		Trigram other = (Trigram) obj;
		if (third == null) {
			if (other.third != null)
				return false;
		} else if (!third.equals(other.third))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.first + " " + this.second + " " + this.third;
	}
}
