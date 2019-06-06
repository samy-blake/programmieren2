package shopping;

public class Milchprodukte extends CartItem {
	private double mFat;

	public Milchprodukte(String name, int quantity, double pricePerUnit, double fat) throws RuntimeException {
		super(name, quantity, pricePerUnit);
		mFat = fat;
	}

	public double getFat() {
		return mFat;
	}

	public void setFat(double fat) {
		mFat = fat;
	}
	
	@Override
	public String toString() {
		// %1$5s x %2$-30s %3$10.2f, %4$10.2f
		return String.format("%1$5s x %2$-30s %3$10.2f, %4$10.2f", mQuantity, mName + " ("+mFat+"%)", mPricePerUnit, mQuantity * mPricePerUnit);
	}

}
