package shopping;

import java.util.ArrayList;

/**
 * shopping cart class
 * @author soren
 * @date 3.6.19
 */
public class ShoppingCart {
	private ArrayList<CartItem> mCartItemList = new ArrayList<CartItem>();
	
	/**
	 * add item to list
	 * @param item
	 */
	public void add(CartItem item) {
		mCartItemList.add(item);		
	}
	
	public double getTotalCost() {
		return 0.0;
	}
	

	public String toString() {
		String result = "";
		for (int i = 0; i < mCartItemList.size(); i++) {
			CartItem item = mCartItemList.get(i);
			result += String.format("%s \n", item);
		}
		return result;
	}
	
}
