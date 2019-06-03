package shopping;

public class Konto {
	private String mName;
	private String mPin;
	private boolean mLogged;
	
	Konto(String name, String pin) {
		mName = name;
		mPin = pin;
	}
	
	public boolean login(String name, String pin) {
		if(
			!mLogged &&
			mName.equals(name) &&
			mPin.equals(pin)
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
