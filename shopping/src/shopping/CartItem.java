package shopping;


/**
 * Cart Item class
 * @author soren
 * @date 3.6.19
 */
public class CartItem {
	protected String mName;
	protected int mQuantity;
	protected double mPricePerUnit;
	
	public CartItem(String name, int quantity, double pricePerUnit) throws RuntimeException {
		mName = name;
		setQuantity(quantity);
		setPricePerUnit(pricePerUnit);
	}
	
	
	public double getCost() {
		return mQuantity * mPricePerUnit;
	}
	
	public void setQuantity(int quantity) throws RuntimeException {
		if(quantity >= 1) {
			mQuantity = quantity;
		} else {
			throw new RuntimeException();
		}
	}
	
	public void setName(String name) {
		mName = name;
	}
	
	public String getName() {
		return mName;
	}
	
	public void setPricePerUnit(double pricePerUnit) throws RuntimeException {
		if(pricePerUnit >= 0) {
			mPricePerUnit = pricePerUnit;
		} else {
			throw new RuntimeException();
		}
	}
	
	public String toString() {
		return String.format("%1$5s x %2$-30s %3$10.2f, %4$10.2f", mQuantity, mName, mPricePerUnit, mQuantity * mPricePerUnit);
	}
}
