package shopping;

public class Suesswaren extends CartItem {
	private int mKilocalorien;
	
	public double getKilocalorien() {
		return mKilocalorien;
	}

	public void setKilocalorien(int kilocalorien) {
		mKilocalorien = kilocalorien;
	}

	public Suesswaren(String name, int quantity, double pricePerUnit, int kilocalorien) throws RuntimeException {
		super(name, quantity, pricePerUnit);
		mKilocalorien = kilocalorien;
	}
	

	@Override
	public String toString() {
		return String.format("%1$5s x %2$-30s %3$10.2f, %4$10.2f", mQuantity, mName + " ("+mKilocalorien+"kcal)", mPricePerUnit, mQuantity * mPricePerUnit);
	}

}
