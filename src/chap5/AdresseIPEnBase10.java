package chap5;

public class AdresseIPEnBase10 {

	public static final int OCTET_LENGTH = 8; // Nombre de bits contenu dans un octet.
	public static final int IP_OCTET_NUMBER = 4; // Nombre d'octet contenu dans une ip.
	public static final double POWER_INVERT = Math.pow(2, OCTET_LENGTH-1);
	/**
	 * Convertir un octet binaire en octet décimale.
	 * @param octetBin une chaine de  caractère représentant un octet binaire.
	 * @returnl l'octet binaire convertit en octet décimale.
	 */


	private static int convertBit(char bit) {
		return bit - '0';
	}


	public static int convertirOctet(String octetBin) {
		if(octetBin.length() != OCTET_LENGTH) throw new IllegalArgumentException("l'octet doit être de longueur 8");

		int octetDec = 0;
		for(int i=0; i<OCTET_LENGTH; i++) {
			char octetAtIndex = octetBin.charAt(i);  // '1'; 
			if(octetAtIndex != '0' && octetAtIndex != '1') throw new IllegalArgumentException("l'octet en binaire ne doit contenir que des 0-1");



			octetDec += convertBit(octetAtIndex) * POWER_INVERT / Math.pow(2, i);
		}

		return octetDec;
	}
	

	/**
	 * Sépare une chaine de caractère représentant une ip binaire par octet.
	 * @param ip est une chaine de caractère de format "00000000.11111111.00000000.11111111".
	 * @return une table String[] contenant par objet un octet.
	 */
	public static String[] convertirIpToOctet(String ip) {
		String[] octets = ip.split("\\.");
		if(octets.length != IP_OCTET_NUMBER) throw new IllegalArgumentException("L'ip doit contenir 4 Octets");

		return octets;
	}
	public static void main(String[] main) {
		// Variable pour l'entrée
		final String IP = "11000000.10101000.00000001.00000101";
		String[] octets = convertirIpToOctet(IP);
		
		String toPrint = "";	
		for(int i=0; i<octets.length; i++) {
			if(i!=0) {
				toPrint += ".";
			}
			toPrint += convertirOctet(octets[i]);
		}

		IO.println(String.format("mon ip : %s", toPrint));
	}
	
}