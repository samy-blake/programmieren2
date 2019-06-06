package shopping;

import java.util.ArrayList;

/**
 * kann nicht vererbt werden durch das wort "final"
 * @author soren
 *
 */
public final class LoginService {
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
		mEmployees.add(new Konto("sören", 1111));
	}
	
	public static boolean login(String name, int pin) {
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
