package autovermietung;

import javax.swing.JOptionPane;

/**
 * Datum: 14.05.2019
 * @author soren
 *
 */
public class Utilitys {
	private static String mListDelimiter = ";";
	private static String mParamsDelimiter = ",";
	
	
	public static String getListDelimiter() {
		return mListDelimiter;
	}

	public static String getParamsDelimiter() {
		return mParamsDelimiter;
	}

	public static String leseEingabeString(String dialogZeile) {
		String eingabe = JOptionPane.showInputDialog(dialogZeile);
		return eingabe;
	}
	
	public static void zeigeDialog(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static int leseEingabeInt(String dialogZeile) {
		String eingabe = JOptionPane.showInputDialog(dialogZeile);
		int intEingabe = Integer.parseInt(eingabe);
		return intEingabe;
	}
	
	public static boolean isCorrectName(String name) {
		return !name.contains(mListDelimiter) && !name.contains(mParamsDelimiter);
	}
}
