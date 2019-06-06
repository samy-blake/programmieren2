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
	
	/**
	 * liefert die gesamt kosten alle caritems zurueck
	 * @return double
	 */
	public double getTotalCost() {
		double totalCost = 0;
		for (int i = 0; i < mCartItemList.size(); i++) {
			CartItem item = mCartItemList.get(i);
			totalCost += item.getCost();
		}
		return totalCost;
	}
	

	public String toString() {
		String result = "";
		for (int i = 0; i < mCartItemList.size(); i++) {
			CartItem item = mCartItemList.get(i);
			result += String.format("%s \n", item.toString());
		}
		result += String.format("%1$51s %2$10.2f", "Summe: ", getTotalCost());
		return result;
	}
	
}
