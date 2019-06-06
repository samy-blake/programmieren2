package shopping;

public class Konto {
	private String mName;
	private int mPin;
	private boolean mLogged;
	
	Konto(String name, int pin) {
		mName = name;
		mPin = pin;
	}
	
	public boolean login(String name, int pin) {
		if(
			!mLogged &&
			mName.equals(name) &&
			mPin == pin
		) {
			mLogged = true;
			return true;
		} else {
			return false;
		}
			
	}
	
	public void logout() {
		mLogged = false;
	}
	
	public boolean getLogged() {
		return mLogged;
	}

	public String getName() {
		return mName;
	}
}
