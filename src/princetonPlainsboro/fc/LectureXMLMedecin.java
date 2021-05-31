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

/**
 *
 * @author Benhadj
 */
public class LectureXMLMedecin {
    /// nom du document XML a analyser
    private String nomFichier;
    private final static String repBase = "src/donnees/";
    
    // 'nomFichier' est le nom d'un fichier XML se trouvant dans le repertoire 'repBase' a lire :
    public LectureXMLMedecin(String nomFichier) {
        this.nomFichier = nomFichier;
    }
    
    public List<Medecin> getMedecins() {
        Patient medecinCourant = null;
        String donneesCourantes = "";
        String nomCourant = "";
        String prenomCourant = "";
        String telephoneCourant = "";
        String specialiteCourant="";
        String idCourant = "";
        List<Medecin> listeMedecin=null;       
        
        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(repBase + nomFichier);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);
            medecinCourant = new Patient();
            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("medecins")) {
                            listeMedecin=new ArrayList<>();
                        }
                        if (parser.getAttributeCount() > 0) {
                            idCourant = parser.getAttributeValue(0);
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                     
                                            
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("medecin")) {
                            listeMedecin.add(new Medecin(nomCourant, prenomCourant,specialiteCourant,telephoneCourant,idCourant));
                        
                        }
                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("telephone")) {
                            telephoneCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("specialite")) {
                            specialiteCourant = donneesCourantes;
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
       
        return listeMedecin;
    }
    
    public static void main(String[] args) {
        LectureXMLMedecin lxm=new LectureXMLMedecin("Medecin.xml");
        
        for (Medecin m:lxm.getMedecins()){
            System.out.println(m.toStringComplet());
        }
        
    }
}
