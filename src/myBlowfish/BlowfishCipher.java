/********************************************************************
 * Programme: 	BlowfishCipher
 * Description:	Mets en practique les ApiBlowfish et ApiFileCipher
 * Auteur:		Didier Samfat
 * Date:		27 Mar 2021
 * Version:		1.0
 ********************************************************************/

package myBlowfish;


import java.security.Key;


public class BlowfishCipher {
	
	static final String FICHIER_CLAIR = "./DojoKun.txt";
	static final String FICHIER_CRYPTE = "./DojoKun.txt.cryp";


	public static void main(String[] args) throws Exception {
		
		Key maClef = ApiBlowfish.generateKey(); // génére la clé Blowfish
		
		System.out.println("********* Chiffrement de TEXTE *********\n");
		
		String textClair = "LA VIE EST BELLE !!! ";
		System.out.printf("Le texte clair est: %s \n", textClair);
		
		String textChiffre = ApiBlowfish.encryptInString(textClair, maClef);
		System.out.printf("\nLe texte chiffré est:  %s\n", textChiffre);
	
		String textDechiffre = ApiBlowfish.decryptInString(textChiffre, maClef);
		System.out.printf("\nLe texte déchiffré est à nouveau: %s \n", textDechiffre);
				
		
		System.out.println("\n\n********* Chiffrement de FICHIER *********\n");
		textClair = ApiFileCipher.read(FICHIER_CLAIR);
		System.out.printf("Le Fichier %s eb clair est : \n\n%s \n", FICHIER_CLAIR, textClair);
		
		textChiffre = ApiFileCipher.encrypt(FICHIER_CLAIR, maClef);
		System.out.printf("\nLe Fichier %s chiffré est: \n\n %s \n", FICHIER_CRYPTE, textChiffre);
		
		textDechiffre = ApiFileCipher.decrypt(FICHIER_CRYPTE, maClef);				
		System.out.printf("\nLe Fichier déchiffré est à nouveau: \n\n%s \n", textDechiffre);
	}
}
