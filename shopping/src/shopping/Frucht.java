package shopping;

public class Frucht extends CartItem {
	public static int anzahl;
	public static int maxAnzahl = 10;
	
	
	public Frucht(String name, int quantity, double pricePerUnit) throws RuntimeException {

		super(name, quantity, pricePerUnit);
		anzahl++;
		if(anzahl > maxAnzahl) {
			throw new RuntimeException("Alle Früchte wurden bereits verkauft");
		}
	}
	
	
}
