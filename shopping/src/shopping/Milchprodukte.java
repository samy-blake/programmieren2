package shopping;

public class Milchprodukte extends CartItem {
	private double mFat;

	public Milchprodukte(String name, int quantity, double pricePerUnit) throws RuntimeException {
		super(name, quantity, pricePerUnit);
	}

	public double getFat() {
		return mFat;
	}

	public void setFat(double fat) {
		mFat = fat;
	}
	
	@Override
	public String toString() {
		return String.format("%s x %s (%s % Fett) %s, %s", mQuantity, mName, mFat, mPricePerUnit, mQuantity * mPricePerUnit);
	}

}
