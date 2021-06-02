/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princetonPlainsboro.fc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import static princetonPlainsboro.fc.Scripts.generateSalt;
import static princetonPlainsboro.fc.Scripts.hashPassword;
import static princetonPlainsboro.fc.Scripts.verifyPassword;

/**
 *
 * @author Benhadj
 */
public class LectureXMLPersonnel {
    /// nom du document XML a analyser
//    private String nomFichier;
    private final static String repBase = "src/donnees/";
//    private String id;
//    private String password;
    
    // 'nomFichier' est le nom d'un fichier XML se trouvant dans le repertoire 'repBase' a lire :
//    public LectureXMLPersonnel(String nomFichier, String id, String password) {
//        this.nomFichier = nomFichier;
//        
//    }
    
    public static boolean isCorrect(String nomFichier, String id, String password) {
        String currentId = null;
        String currentPassword = null;
        String donneesCourantes = null;
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(repBase + nomFichier);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);
            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getAttributeCount() > 0) {
                            currentId = parser.getAttributeValue(0);
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (parser.getLocalName().equals("password")) {
                            currentPassword = donneesCourantes;
                            if( verifyPassword( password, currentPassword, Salt.SALT) && currentId.equals(id) )
                                return true;
                        }
                        break;
                        
                    case XMLStreamConstants.CHARACTERS:
                        donneesCourantes = parser.getText();
                        break;
                } // end switch
            } // end while
            parser.close();
        } catch (XMLStreamException ex) {
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }
       
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println( LectureXMLPersonnel.isCorrect("PersonnelMedical.xml", "2", "tati") );
        
        
    }
}
