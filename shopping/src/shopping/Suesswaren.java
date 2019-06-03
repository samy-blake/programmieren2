package shopping;

public class Suesswaren extends CartItem {
	private double mKilocalorien;
	
	public double getKilocalorien() {
		return mKilocalorien;
	}

	public void setKilocalorien(double kilocalorien) {
		mKilocalorien = kilocalorien;
	}

	public Suesswaren(String name, int quantity, double pricePerUnit) throws RuntimeException {
		super(name, quantity, pricePerUnit);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public String toString() {
		return String.format("%s x %s (%s kilocal) %s, %s", mQuantity, mName, mKilocalorien, mPricePerUnit, mQuantity * mPricePerUnit);
	}

}
