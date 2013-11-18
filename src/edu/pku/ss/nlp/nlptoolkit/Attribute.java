package edu.pku.ss.nlp.nlptoolkit;

public class Attribute {
	/* number of letters */
	public int nlet;
	
	/* number of phomemes */
	public int nphon;
	
	/* number of syllables in word */
	public int nsyl;
	
	/* k & f frequency */
	public int kffreq;
	
	/* no of k&f categories */
	public int kfncats;
	
	/* no of k&f samples */
	public int kfnsamp;
	
	/* Thorndyke-Lorge freq */
	public int t_l;
	
	/* Brown's Spoken frequency */
	public int brownf;

	/* Familiarity. min:100;max:657;mean:488;deviation:99 */
	public int fam;

	/* Concreteness. min: 158; max:670; mean:438 */
	public int conc;

	/* Imagery. min:129;max:670;mean:438; */
	public int imag;

	/* mean Colerado meaningfulness. min:127;max:667;mean:415; */
	public int meanc;

	/* mean pavio meaningfulness. min:192;max:922;mean:600; */
	public int meanp;

	/* mean pavio meaningfulness. min:125;max:697;mean:405; */
	public int aoa;

	public String tq2; /* type */
	public String wtype; /* part of speech */
	public String pdwtype; /* PD part of speech */
	public String alphsyl; /* Alphasyllable */
	public String status; /* Status */
	public String varp; /* Varient phoneme */
	public String cap;
	public String irreg; /* irregular plural */

	public String word;
	public String phonetic;
	public String editedPhonetic;
	public String stress;

	public Attribute() {
		tq2 = "";
		wtype = "";
		pdwtype = "";
		alphsyl = "";
		status = "";
		varp = "";
		cap = "";
		irreg = "";
		word = "";
		phonetic = "";
		editedPhonetic = "";
		stress = "";
	}

	public Attribute(String line) {
		nlet = Integer.parseInt(line.substring(0, 2));
		nphon = Integer.parseInt(line.substring(2, 4));
		nsyl = Integer.parseInt(line.substring(4, 5));
		kffreq = Integer.parseInt(line.substring(5, 11));
		kfncats = Integer.parseInt(line.substring(11, 13));
		kfnsamp = Integer.parseInt(line.substring(13, 15));
		t_l = Integer.parseInt(line.substring(15, 21));
		brownf = Integer.parseInt(line.substring(21, 25));
		fam = Integer.parseInt(line.substring(25, 28));
		conc = Integer.parseInt(line.substring(28, 31));
		imag = Integer.parseInt(line.substring(31, 34));
		meanc = Integer.parseInt(line.substring(34, 37));
		meanp = Integer.parseInt(line.substring(37, 40));
		aoa = Integer.parseInt(line.substring(40, 43));
		tq2 = line.substring(43, 44);
		wtype = line.substring(44, 45);
		pdwtype = line.substring(45, 46);
		alphsyl = line.substring(46, 47);
		status = line.substring(47, 48);
		varp = line.substring(48, 49);
		cap = line.substring(49, 50);
		irreg = line.substring(50, 51);

		int w = 51;
		StringBuilder wordSB = new StringBuilder();
		while (line.charAt(w) != '|') {
			wordSB.append(line.charAt(w));
			++w;
		}
		this.word = wordSB.toString();

		++w;
		StringBuilder phoneticSB = new StringBuilder();
		while (line.charAt(w) != '|') {
			phoneticSB.append(line.charAt(w));
			++w;
		}
		this.phonetic = phoneticSB.toString();

		++w;
		StringBuilder editedPhoneticSB = new StringBuilder();
		while (line.charAt(w) != '|') {
			editedPhoneticSB.append(line.charAt(w));
			++w;
		}
		this.editedPhonetic = editedPhoneticSB.toString();

		++w;
		this.stress = line.substring(w, line.length() - 1);
	}
}
