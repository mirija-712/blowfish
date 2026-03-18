/**********************************************************************************
 * API: APIFileCipher - Suppose que la clé blowfish a déjà été généré
 * Description:	Utilise ApiBlowfish pour le chiffrement et déchiffrement de fichier
 * Auteur:		Didier Samfat
 * Date:		28 Mar 2021
 * Version:		1.0
 *********************************************************************************/

package myBlowfish;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;


public class ApiFileCipher {

/**
 * Méthode qui lit un fichier est retourne ce qu'il a lu	
 * @param nomFichier: le fichier à lire
 * @return : la chaîne lue
 */
	
static String read(String nomFichier) {
	
	try {
		  // Lit le fichier clair		
		  File inFile = new File(nomFichier);
		  FileInputStream inputStream = new FileInputStream(inFile);
		  
		  byte[] inBytes = new byte[(int) inFile.length()];
		  inputStream.read(inBytes); // lit le fichier clair
		  inputStream.close();
		  
		  //return Base64.getEncoder().encodeToString(inBytes); 
		  
		  return new String(inBytes);
		  
	    }
	    catch (FileNotFoundException e) {}
	    catch (IOException e) {}
		return null;
}

/**
 * Méthode qui chiffre un fichier donné avec une clé blowfish
 * Créer asussi un nouveau fichier au format nomFichier.cryp 
 * @param nomFichier : fichier qui doit être chiffré
 * @param clef : doit être généré au prálable
 * @return : le texte chifré encode en Base64
 * @throws Exception
 */
	
static String encrypt(String nomFichier, Key clef) throws Exception{
		
		try {
			  // Lit le fichier clair		
			  File inFile = new File(nomFichier);
			  FileInputStream inputStream = new FileInputStream(inFile);
			  byte[] inBytes = new byte[(int) inFile.length()];
			  inputStream.read(inBytes); // lit le fichier clair
			  
			  // creer fichier.txt.cryp
			  String fichierCrypte = nomFichier + ".cryp";  
			  File outFile = new File(fichierCrypte); // creer le fichier de sortie crypté
			  FileOutputStream outputStream = new FileOutputStream(outFile);
			 
			  byte[] texteChiffre = ApiBlowfish.encryptInByte(inBytes, clef); 
			  outputStream.write(texteChiffre); // on sauvegarde

			  inputStream.close();
			  outputStream.close();
				
			  // encodage pour lisibilité du texte à l'écran
			  return Base64.getEncoder().encodeToString(texteChiffre); 
			  
		    }
		    catch (FileNotFoundException e) {}
		    catch (IOException e) {}
			return null;
		
	}
	
	
static String decrypt(String nomFichier, Key clef) throws Exception{
	
	try {
		 		
		  File inFile = new File(nomFichier);
		  		  
		  FileInputStream inputStream = new FileInputStream(inFile);
			
		  byte[] inBytes = new byte[(int) inFile.length()];
		  inputStream.read(inBytes);

		  byte[] texteDechiffre = ApiBlowfish.decryptInByte(inBytes, clef);
		  
		  String chaine = new String(texteDechiffre);
		  
		  inputStream.close();
		  
		  return chaine;
		  
	    }
	    catch (FileNotFoundException e) {}
	    catch (IOException e) {}
		return null;
}


}
