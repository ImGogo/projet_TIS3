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
public class LectureXMLPatient {
    /// nom du document XML a analyser
    private String nomFichier;
    private final static String repBase = "src/donnees/";
    
    // 'nomFichier' est le nom d'un fichier XML se trouvant dans le repertoire 'repBase' a lire :
    public LectureXMLPatient(String nomFichier) {
        this.nomFichier = nomFichier;
    }
    
    public List<Patient> getPatients() {
        Patient patientCourant = null;
        String donneesCourantes = "";
        String nomCourant = "";
        String prenomCourant = "";
        String adresseCourante = "";
        String inseeCourant="";
        String idCourant = "";
        List<Patient> listePatient=null;       
        
        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(repBase + nomFichier);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);
            patientCourant = new Patient();
            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("patients")) {
                            listePatient=new ArrayList<>();
                        }
                        if (parser.getAttributeCount() > 0) {
                            idCourant = parser.getAttributeValue(0);
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                     
                                            
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("patient")) {
                            listePatient.add(new Patient(nomCourant, prenomCourant,adresseCourante,inseeCourant));
                        
                        }
                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("adresse")) {
                            adresseCourante = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("insee")) {
                            inseeCourant = donneesCourantes;
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
       
        return listePatient;
    }
    
    public static void main(String[] args) {
        LectureXMLPatient lxp=new LectureXMLPatient("Patient.xml");
        
        for (Patient p:lxp.getPatients()){
            System.out.println(p.toStringComplet());
        }
        
    }
}
