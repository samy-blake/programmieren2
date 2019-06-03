package autovermietung;

import java.io.FileNotFoundException;

/**
 * Datum: 14.05.2019
 * @author soren
 *
 */
public class Interface {
	private static Menu menu;
	public static void main(String[] args) {
		try {
			menu = new Menu();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
		int eingabe = 0;
		do {
			eingabe = 0;
			String dialog = ""
			+ "#########################################################" + "\n"
			+ "Willkommen zur Autovermietung" + "\n"
			+ "0 : Beenden" + "\n"
			+ "1 : Auto mieten" + "\n"
			+ "2 : Auto zurückgeben" + "\n"
			+ "3 : Übersicht zu vermieteten Autos" + "\n"
			+ "4 : Übersicht über Vermietungsverlauf aller Kunden" + "\n"
			+ "5 : Übersicht zur Popularität der Autos" + "\n"
			+ "Bitte geben Sie die Zahl zur Option Ihrer Wahl ein: ";
            
            try {
            	eingabe = Utilitys.leseEingabeInt(dialog);
			} catch (Exception e) {
				Utilitys.zeigeDialog("Falsche Eingabe!");
				eingabe = -1;
			}
            wahleMenu(eingabe);
		} while (eingabe != 0);

	}
	
	
	private static void wahleMenu(int eingabe) {
		switch (eingabe) {
		case 0:
			Utilitys.zeigeDialog("Wird beendet.");
			break;
		case 1:
			menu.autoMieten();
			break;
		case 2:
			menu.autoZurueckgeben();
			break;
		case 3:
			menu.uebersichtZuVermieteneAutos();
			break;
		case 4:
			menu.uebersichtUeberVermietungsVerlauf();
			break;
		case 5:
			menu.uebersichtPopularitaet();
			break;
		default:
			System.out.println("Eingabe " + eingabe + " gibt es nicht.");
			break;
		}
	}
}
