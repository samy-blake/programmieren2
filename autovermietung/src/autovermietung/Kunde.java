package autovermietung;

import java.util.ArrayList;

/**
 * Datum: 14.05.2019
 * @author soren
 *
 */
public class Kunde {	
	private String mName;
	private ArrayList<String> mAutoHistory = new ArrayList<String>();
	
	Kunde(String fileString) {
		String[] parts = fileString.split(Utilitys.getListDelimiter());
		mName = parts[0];
		String autoHistory = parts[1];
		for (String auto : autoHistory.split(Utilitys.getParamsDelimiter())) {
			mAutoHistory.add(auto);
		}
	}
	
	Kunde(String name, String auto) {
		mName = name;
		mAutoHistory.add(auto);
	}
	

	public String toString() {
		return getName() + Utilitys.getListDelimiter() + getAutoHistoryString();
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String name) {
		mName = name;
	}
	
	public ArrayList<String> getAutoHistory() {
		return mAutoHistory;
	}
	
	public String getAutoHistoryString() {
		String result = "";

		for (int i = 0; i < mAutoHistory.size(); i++) {
			String autoString = mAutoHistory.get(i);
			result += autoString;
			
			if(i+1 < mAutoHistory.size()) {				
				result += Utilitys.getParamsDelimiter();
			}
		}

		return result;
	}
	
	public void addAutoToHistory(String autoString) {
		mAutoHistory.add(autoString);
	}
	
	
	
}
