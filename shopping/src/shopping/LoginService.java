package shopping;

import java.util.ArrayList;

public class LoginService {
	private static ArrayList<Konto> mEmployees = new ArrayList<Konto>();
	
	private static Konto getKonto(String name) {
		for (int i = 0; i < mEmployees.size(); i++) {
			Konto konto = mEmployees.get(i);
			if(konto.getName().equals(name)) {
				return konto;
			}
		}
		return null;
	}
	
	
	public static void prepareLogins() {
		mEmployees.add(new Konto("sören", "balke"));
	}
	
	public static boolean login(String name, String pin) {
		Konto konto = getKonto(name);
		if(konto == null) {
			return false;
		}
		return konto.login(name, pin);
	}
	
	public static boolean isWorking(String name) {
		Konto konto = getKonto(name);
		if(konto == null) {
			return false;
		}
		return konto.getLogged();
	}
	
	
	public static void logout(String name) {
		Konto konto = getKonto(name);
		if(konto != null) {
			konto.logout();
		}
	}
}
