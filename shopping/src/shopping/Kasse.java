package shopping;

/**
 * 
 * @author soren
 *
 */
public class Kasse {
	public double getMoney() {
		return mMoney;
	}

	private double mMoney = 0.0;
	private Konto mKonto;
	
	private Kasse(String name, int pin) {
		mKonto = new Konto(name, pin);
	}
	
	public static Kasse loginToCounter(String name, int pin) {
		if(LoginService.login(name, pin)) {			
			return new Kasse(name, pin);
		} 
		return null;
	}
	
	public void addMoney(double amount) {
		mMoney += amount;
	}
	
	public double getChange(double toPay, double given) throws Exception {
		if(toPay > given) {
			throw new Exception(String.format("Das reicht nicht. Verlange weitere %1$10.2f", toPay - given));
		}
		double diff = given - toPay;
		return diff;
	}
	
	public void takeMoney(double amount) throws Exception {
		if((mMoney - amount) < 0) {
			throw new Exception(String.format("Es fehlen noch %1$10.2f", amount - mMoney));
		}
		mMoney -= amount;
	}
	
	public Konto getLoggedInEmployee()  {
		return mKonto;
	}
	
	public void logoutFromCounter() {
		mKonto.logout();
	}
}
