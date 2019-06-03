package autovermietung;

import java.security.InvalidParameterException;

/**
 * Datum: 14.05.2019
 * @author soren
 *
 */
public class Auto {
	private String mName;
	private String mKunde;
	private int mPopularitaet = 0;
	
	Auto(String name, String kunde, int popularitaet) throws InvalidParameterException {
		if(!Utilitys.isCorrectName(name)) {
			throw new InvalidParameterException(name + " beinhaltet verbotenes zeichen.");
		}
		mName = name;
		mKunde = kunde;
		mPopularitaet = popularitaet;
	}
	
	Auto(String fileString) {
		String[] parts = fileString.split(Utilitys.getListDelimiter());
		mName = parts[0];
		mKunde = parts[1].equals("null") ? null : parts[1];
		mPopularitaet = Integer.parseInt(parts[2]);
	}
			
	
	public String toString() {
		return getName() + Utilitys.getListDelimiter() + getKunde() + Utilitys.getListDelimiter() + getPopularitaet();
	}
	

	public String getName() {
		return mName;
	}

	public void setName(String name) throws InvalidParameterException {
		if(!Utilitys.isCorrectName(name)) {
			throw new InvalidParameterException(name + " beinhaltet verbotenes zeichen.");
		}
		mName = name;
	}

	public String getKunde() {
		return mKunde;
	}

	public void setKunde(String kunde) {
		mKunde = kunde;
	}
	public void setKunde() {
		mKunde = null;
	}

	public int getPopularitaet() {
		return mPopularitaet;
	}

	public void countPopularitaet() {
		mPopularitaet++;
	}
		
}
