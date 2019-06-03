package autovermietung;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



/**
 * sotiert die verlauf liste mit dem namen aufsteigent
 * @author soren
 *
 */
class SortbyKundeName implements Comparator<Kunde> { 
    public int compare(Kunde a, Kunde b) { 
        return a.getName().compareTo(b.getName()); 
    } 
}

/**
 * sotiert die autoliste nach der popularitaet
 * @author soren
 *
 */
class SortbyAutoPopularitaet implements Comparator<Auto> {
    public int compare(Auto a, Auto b) { 
        return b.getPopularitaet() - a.getPopularitaet(); 
    } 
}


/**
 * Datum: 14.05.2019
 * @author soren
 *
 */
public class Menu {
	Vermietung vermietung;
	
	Menu() throws FileNotFoundException {
		vermietung = new Vermietung();
	}
	
	public void autoMieten() {
		
		if(!vermietung.istEinAutoVerfuegbar()) {
			Utilitys.zeigeDialog("Alle Autos vermietet.");
			return;
		}
		
		String dialog = "+++++++ Auto mieten +++++++" + "\n"
			+ "Kundenname: ";
		String kundenName = "";
		do {			
			kundenName = Utilitys.leseEingabeString(dialog);
		} while (kundenName.isEmpty() || kundenName == null);
        
        ArrayList<Auto> autos = vermietung.getAutos();
        // ausgabe alle autos die keinen kunden haben
        dialog = "[";
        for (int i = 0; i < autos.size(); i++) {
			Auto auto = autos.get(i);
			if(auto.getKunde() != null) {
				continue;
			}
			dialog += auto.getName();
			if(i + 1 < autos.size()) {
				dialog += ", ";
			}
		}
        dialog += "]" + "\n";
        boolean richtigeEingabe = true;
        do {
        	String msg = "";
        	if(!richtigeEingabe) {        		
        		msg = "Das angegebene Auto ist nicht verfügbar!";
        	}
        	msg = "Bitte geben Sie das Auto ein , welches gemietet werden soll: ";
        	String autoName = Utilitys.leseEingabeString(dialog + msg);
        	try {
        		richtigeEingabe = vermietung.vermieteAuto(autoName, kundenName);
        		if (richtigeEingabe) {       			
        			Utilitys.zeigeDialog("Das Auto wurde erfolgreich vermietet");
        		}
			} catch (InvalidParameterException e) {
				Utilitys.zeigeDialog(e.getMessage());
				richtigeEingabe = true;
			}
		} while (!richtigeEingabe);
	}
	
	public void autoZurueckgeben() {
		String dialog = "+++++++ Auto zurückgeben +++++++" + "\n"
				+ "Kundenname:";
		String kunde = Utilitys.leseEingabeString(dialog);
		
		if(vermietung.kundeHatEinAutoGemietet(kunde)) {
			Auto gemietetesAuto = vermietung.gebeAutoVonKunde(kunde);
			dialog = "Der Kunde " + kunde + 
				" hat derzeit das Auto " + gemietetesAuto.getName() + " gemietet" + "\n"
			+ "Soll es wieder abgegeben werden? (j/n) :";
			String eingabe = Utilitys.leseEingabeString(dialog);
			if(eingabe.equals("j")) {
				vermietung.zurueckgebenAuto(kunde);
			}
		} else {
			Utilitys.zeigeDialog(kunde + " hat kein Auto gemietet.");
		}
	}
	
	public void uebersichtZuVermieteneAutos() {
		String dialog = "+++++++ Derzeitvermietet : +++++++" + "\n";
		ArrayList<Auto> autos = vermietung.getAutos();
		for (Auto auto : autos) {
			if(auto.getKunde() != null) {
				dialog += 
					"Kunde " + auto.getKunde() + 
					" hat das Auto " + auto.getName() + " gemietet"
				+ "\n";
			}
		}
		Utilitys.zeigeDialog(dialog);
	}
	
	public void uebersichtUeberVermietungsVerlauf() {
		String dialog = "+++++++ Kundengeschichte +++++++" + "\n";
		ArrayList<Kunde> verlauf = new ArrayList<Kunde>(vermietung.vermietungsverlauf());
		Collections.sort(verlauf, new SortbyKundeName());
		
		for (Kunde kunde : verlauf) {
			String kundeName = kunde.getName();
			String autoHistory = kunde.getAutoHistoryString();
			dialog += kundeName + " : [" + autoHistory + "]"
			+ "\n";
		}
		Utilitys.zeigeDialog(dialog);
	}
	
	public void uebersichtPopularitaet() {
		String dialog = "+++++++ Autopopularität +++++++" + "\n";
		ArrayList<Auto> autos = new ArrayList<Auto>(vermietung.getAutos());
		Collections.sort(autos, new SortbyAutoPopularitaet());
		
		for (Auto auto : autos) {
			String autoName = auto.getName();
			int poplularitaet = auto.getPopularitaet();
			dialog += autoName + " : " + poplularitaet
			+ "\n";
		}
		Utilitys.zeigeDialog(dialog);
	}
}


