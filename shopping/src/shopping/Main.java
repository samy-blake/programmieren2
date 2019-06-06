package shopping;

import java.util.Scanner;

public class Main {
	private static Scanner mInput;
	private static Kasse mKasse;
	
	public static void main(String[] args) {
		boolean logged = true;
		do {			
			try {
				login();
				logged = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				logged = false;
			}
		} while (!logged);
		kunden();
		logout();
	}
	
	private static void logout() {
		mKasse.logoutFromCounter();
		System.out.println(String.format("Der Restbetrag in der Kasse ist %1$10.2f", mKasse.getMoney()));
	}
	
	private static void kunden() {
		System.out.println("Kunde 1:");
		ShoppingCart s1 = new ShoppingCart();
		s1.add(new CartItem("Erdbeerkram", 3, 2.19));
		s1.add(new Frucht("Apfel", 3, 0.49));
		s1.add(new Milchprodukte("Milch", 1, 0.69, 3.0));
		s1.add(new Suesswaren("Kekse", 4, 1.59, 150));
		s1.add(new CartItem("Hundefutter", 6, 3.20));
		s1.add(new Suesswaren("Chips", 4, 1.59, 150));
		System.out.println(s1.toString());
		shoppingCartAbkassieren(s1);
		
		System.out.println("Kunde 2:");
		ShoppingCart s2 = new ShoppingCart();
		s2.add(new Milchprodukte("kräuterquarl", 2, 0.69, 40.0));
		s2.add(new Frucht("Orangen", 4, 1.79));
		s2.add(new CartItem("Toilettenpapier", 1, 2.99));
		s2.add(new CartItem("Nutella", 1, 3.65));
		s2.add(new Suesswaren("Erdnussflips", 5, 0.59, 533));
		s2.add(new Frucht("Pflaumen", 3, 1.39));
		System.out.println(s2.toString());
		shoppingCartAbkassieren(s2);

		
		System.out.println("Kunde 3:");
		ShoppingCart s3 = new ShoppingCart();
		s3.add(new Suesswaren("Zartbitterschokolade", 2, 0.99, 592));
		s3.add(new Milchprodukte("Butter", 2, 1.09, 82.5));
		s3.add(new Suesswaren("Halorenkugeln", 2, 0.95, 427));
		s3.add(new CartItem("Backpapier", 1, 0.99));
		System.out.println(s3.toString());
		shoppingCartAbkassieren(s3);
	}
	
	private static void shoppingCartAbkassieren(ShoppingCart s) {
		double customerMoney = 0;
		double changeMoney = 0;
		do {
			System.out.println("erhaltener Betrag vom Kunden: ");
			customerMoney += mInput.nextDouble();
			try {
				changeMoney = mKasse.getChange(s.getTotalCost(), customerMoney);
			} catch (Exception e) {
				System.out.println(e.getMessage());	
			}
		} while (s.getTotalCost() > customerMoney);
		
		boolean correctChanger = true;
		do {			
			try {
				correctChanger = true;
				mKasse.takeMoney(changeMoney);
			} catch (Exception e) {
				System.out.println("Die Kasse enthält nicht genug Geld, um den Restbetrag zu begleichen.");
				System.out.println(e.getMessage());
				System.out.print("Wieviel Geld befindet sich jetzt in der Kasse: ");
				double money = mInput.nextDouble();
				mKasse.addMoney(money);
				correctChanger = false;
			}
		} while (!correctChanger);
		
		mKasse.addMoney(customerMoney);
		System.out.println(String.format("Restgeld: %1$10.2f", changeMoney));
		System.out.println("-------------------------------------------");
	}
	
	private static void login() throws Exception {
		mInput = new Scanner(System.in);
		LoginService.prepareLogins();
		String username;
		int pin;
		System.out.print("Name: ");
		username = mInput.nextLine();
		System.out.print("Pin (4-stellig): ");
		pin = mInput.nextInt();
		if(pin > 9999) {
			throw new Exception("Falsche eingabe");
		}
		
		mKasse = Kasse.loginToCounter(username, pin);
		if(mKasse == null) {
			throw new Exception("Falsche Eingabe!");
		}
		System.out.println("Willkommen " + username);
		System.out.print("Wieviel Geld befindet sich jetzt in der Kasse: ");
		double money = mInput.nextDouble();
		mKasse.addMoney(money);
	}
}
