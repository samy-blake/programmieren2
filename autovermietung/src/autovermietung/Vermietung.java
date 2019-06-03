package autovermietung;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Datum: 14.05.2019
 * @author soren
 *
 */
public class Vermietung {
	private String mAutoDateiName = "autovermietung.txt";
	private String mVerlaufDateiName = "verlauf.txt";
	
	ArrayList<Auto> mAutos = new ArrayList<>();
	ArrayList<Kunde> mVerlauf = new ArrayList<>();
	private File mAutoDatei;
	private File mVerlaufDatei;
	
	Vermietung() throws FileNotFoundException {
		leseAlleDateien();
	}
	
	/**
	 * schreibt arraylist inhalt in die datei
	 */
	private void schreibeAutoDatei() {
		String inhalt = "";
		for (Auto auto : mAutos) {
			inhalt += auto.toString() + "\n";
		}
		schreibeDatei(mAutoDatei, inhalt);
	}
	
	/**
	 * schreibt arraylist verlauf in die datei
	 */
	private void schreibeVerlaufDatei() {
		String inhalt = "";
		for (Kunde kunde : mVerlauf) {
			inhalt += kunde.toString() + "\n";
		}
		schreibeDatei(mVerlaufDatei, inhalt);
	}
	
	/*
	 * schreibt inhalt in eine datei
	 */
	private void schreibeDatei(File datei, String inhalt) {
		Writer writer;
		try {
			writer = new FileWriter(datei);
			writer.write(inhalt);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * erzeugt default daten für auto vermietungs datei
	 * @param datei
	 */
	private void setzeDefault() {
		mAutos.add(new Auto("VW", null, 0));
		mAutos.add(new Auto("BMW", null, 0));
		mAutos.add(new Auto("Scoda", null, 0));
		mAutos.add(new Auto("Porsche", null, 0));
		mAutos.add(new Auto("Mini", null, 0));
	}
	
	
	/**
	 * erstellt datei
	 * @param datei
	 */
	private void erstelleDatei(File datei, Boolean setDefault) {
		try {
			if(datei.createNewFile()) {
				// neue datei
				if(setDefault) {
					setzeDefault();
					schreibeAutoDatei();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ließt die autodatei aus und fügt den inhalt in die auto arraylist
	 * @throws FileNotFoundException
	 */
	private void leseDateiAuto() throws FileNotFoundException {
		Scanner input = new Scanner(mAutoDatei);
		mAutos = new ArrayList<Auto>();
		while (input.hasNextLine()) {
			String line = input.nextLine();
			
			// erzeuge auto object und füge es der Liste hinzu
			mAutos.add(new Auto(line));
		}
		input.close();
	}

	/**
	 * ließt die verlauf datei und fügt den inhalt in die verlauf arraylist
	 * @throws FileNotFoundException
	 */
	private void leseDateiVerlauf() throws FileNotFoundException {
		Scanner input = new Scanner(mVerlaufDatei);
		mVerlauf = new ArrayList<Kunde>();
		while (input.hasNextLine()) {
			String line = input.nextLine();
			
			// erzeuge auto object und füge es der Liste hinzu
			mVerlauf.add(new Kunde(line));
		}
		input.close();
	}
	
	/**
	 * erstellt datei falls nicht vorhanden, und ließt jedes auto ein 
	 */
	private void leseAlleDateien() throws FileNotFoundException {
		mAutoDatei = new File(mAutoDateiName);
		mVerlaufDatei = new File(mVerlaufDateiName);
		
		erstelleDatei(mAutoDatei, true);
		erstelleDatei(mVerlaufDatei, false);
		
		leseDateiAuto();
		leseDateiVerlauf();
	}

	/**
	 * setzt den kundennamen in das autoobject wenn es das auto gibt, 
	 * und der kunde kein anderes auto vermietet hat
	 * @param autoName
	 * @param kunde
	 * @return
	 * @throws InvalidParameterException
	 */
	public boolean vermieteAuto(String autoName, String kunde) throws InvalidParameterException {
		if(kundeHatEinAutoGemietet(kunde)) {
			throw new InvalidParameterException("Kunde hat bereits ein Auto");
		}
		
		if(!Utilitys.isCorrectName(kunde)) {
			throw new InvalidParameterException("Kundename beinhaltet ein verbotenes Zeichen");
		}
		
		boolean gefunden = false;
		for (int i = 0; i < mAutos.size(); i++) {
			Auto auto = mAutos.get(i);
			if(auto.getName().equals(autoName)) {
				auto.setKunde(kunde);
				auto.countPopularitaet();
				gefunden = true;
				addAutoToVerlauf(kunde, auto);
			}
		}
		schreibeAutoDatei();
		schreibeVerlaufDatei();
		return gefunden;
	}
	
	/**
	 * fügt das auto zu dem kunden in die verlauf list
	 * @param kundeName
	 * @param auto
	 */
	private void addAutoToVerlauf(String kundeName, Auto auto) {
		boolean kundegefunden = false;
		for (Kunde kunde : mVerlauf) {
			if(kunde.getName().equals(kundeName)) {
				kunde.addAutoToHistory(auto.getName());
				kundegefunden = true;
			}
		}
		
		if(!kundegefunden) {
			mVerlauf.add(
				new Kunde(kundeName, auto.getName())
			);
		}
	}
	
	/**
	 * wenn der kunde ein auto hat, wird es zurück gegeben
	 * @param kunde
	 * @return
	 * @throws InvalidParameterException
	 */
	public boolean zurueckgebenAuto(String kunde) throws InvalidParameterException {
		if(!kundeHatEinAutoGemietet(kunde)) {
			throw new InvalidParameterException("Kunde hat kein Auto in Vermietung");
		}
		boolean gefunden = false;
		for (int i = 0; i < mAutos.size(); i++) {
			Auto auto = mAutos.get(i);
			if(
				auto.getKunde() != null &&
				auto.getKunde().equals(kunde)
			) {
				auto.setKunde();
				gefunden = true;
			}
		}
		schreibeAutoDatei();
		return gefunden;
	}
	
	/**
	 * prüft ob der kunde ein auto gemietet hat
	 * @param kunde
	 * @return
	 */
	public boolean kundeHatEinAutoGemietet(String kunde) {
		for (Auto auto : mAutos) {
			if(
				auto.getKunde() != null &&
				auto.getKunde().equals(kunde)
			) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * lieftert das auto vom kunden zurück
	 * @param kunde
	 * @return
	 */
	public Auto gebeAutoVonKunde(String kunde) {
		for (Auto auto : mAutos) {
			if(
				auto.getKunde() != null && 
				auto.getKunde().equals(kunde)
			) {
				return auto;
			}
		}
		return null;
	}
	
	public ArrayList<Auto> getAutos() {
		return mAutos;
	}
	
	public ArrayList<Kunde> vermietungsverlauf() {		
		return mVerlauf;
	}
	
	/**
	 * prüft ob ein auto verfügbar ist
	 * @return
	 */
	public boolean istEinAutoVerfuegbar() {
		int counter = 1;
		for (Auto auto : mAutos) {
			if(
				auto.getKunde() != null
			) {
				counter++;
			}
		}
		return counter != mAutos.size();
	}
}
