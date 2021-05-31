/*
 * LectureXMLDossier.java
 *
 * Created on 5 janvier 2006, 18:26
 *
 * Lecture d'un document XML et transformation en instances Java
 */
package princetonPlainsboro.fc;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.Vector;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Lecture d'un document XML et transformation en instances Java.
 *
 * @author promayon
 */
public class LectureXMLDossier {

    /// nom du document XML a analyser
    private String nomFichier;
    private final static String repBase = "src/donnees/";

    // 'nomFichier' est le nom d'un fichier XML se trouvant dans le repertoire 'repBase' a lire :
    public LectureXMLDossier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public DossierMedical getDossier(List<Patient> listePatient, List<Medecin> listeMedecin) {
        DossierMedical dossierCourant = null;
        Date date = null;
        Medecin medecinCourant = null;
        Patient patientCourant = null;
        List<Acte> actes = new Vector<Acte>();
        String donneesCourantes = "";
//        String idCourant = null;
        String idPatientCourant = null;
        String idMedecinCourant = null;
        String idFicheCourant = null;
        Code codeCourant = null;
        String observationCourante = null;
        TypeActe typeCourant = null;
        int coefCourant = 0;

        // analyser le fichier par StAX
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
                        if (parser.getLocalName().equals("Fiches")) {
                            dossierCourant = new DossierMedical(listePatient, listeMedecin);
                        }if (parser.getAttributeCount() > 0) {
                            if( parser.getLocalName().equals("patient") ) {
                                idPatientCourant = parser.getAttributeValue(0);
                            } else if( parser.getLocalName().equals("medecin")) {
                                idMedecinCourant = parser.getAttributeValue(0);
                            } else if( parser.getLocalName().equals("ficheDeSoins")) {
                                idFicheCourant = parser.getAttributeValue(0);
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (parser.getLocalName().equals("acte")) {
                            Acte acteCourant = new Acte(codeCourant, coefCourant, typeCourant, observationCourante);
                            actes.add(acteCourant);
//                            patientCourant.ajouterActe(acteCourant);
                        }
                        if (parser.getLocalName().equals("code")) {
                            codeCourant = getCode(donneesCourantes);
                            if (codeCourant == null) {
                                throw new XMLStreamException("Impossible de trouver le code d'acte = " + donneesCourantes);
                            }
                        }
                        if (parser.getLocalName().equals("coef")) {
                            coefCourant = Integer.parseInt(donneesCourantes);
                        }
                        if (parser.getLocalName().equals("type")) {
                            typeCourant = getTypeActe(donneesCourantes);
                            if (typeCourant == null) {
                                throw new XMLStreamException("Impossible de trouver le code du type = " + donneesCourantes);
                            }
                        }
                        if (parser.getLocalName().equals("observations")) {
                            observationCourante = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("date")) {
                            int annee = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-')));
                            int mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-')));
                            int jour = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.length()));

                            date = new Date(jour, mois, annee);
                        }
                        if (parser.getLocalName().equals("ficheDeSoins")) {
                            FicheDeSoins f = new FicheDeSoins(patientCourant, medecinCourant, date, idFicheCourant);
                            // ajout des actes
                            for (int i = 0; i < actes.size(); i++) {
                                Acte a = (Acte) actes.get(i);
                                f.ajouterActe(a);
                            }
                            // effacer tous les actes de la liste
                            actes.clear();
                            // ajouter la fiche de soin au dossiers
                            dossierCourant.ajouterFiche(f);
                            patientCourant.ajouterFiche(f);
                        }
                        if (parser.getLocalName().equals("medecin")) {
                            int i = 0;
                            
                            while (i < listeMedecin.size() && !listeMedecin.get(i).getId().equals(idMedecinCourant)) {
                                i++;
                            }
                            medecinCourant = listeMedecin.get(i);

                        }
                        if (parser.getLocalName().equals("patient")) {
                            int i = 0;
                            
                            while (i < listePatient.size() && !listePatient.get(i).getNumINSEE().equals(idPatientCourant)) {
                                i++;
                            }
                            patientCourant = listePatient.get(i);
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

        return dossierCourant;
    }

    private static Code getCode(String code) {
        if (code.equals("CS")) {
            return Code.CS;
        }
        if (code.equals("CSC")) {
            return Code.CSC;
        }
        if (code.equals("FP")) {
            return Code.FP;
        }
        if (code.equals("KC")) {
            return Code.KC;
        }
        if (code.equals("KE")) {
            return Code.KE;
        }
        if (code.equals("K")) {
            return Code.K;
        }
        if (code.equals("KFA")) {
            return Code.KFA;
        }
        if (code.equals("KFB")) {
            return Code.KFB;
        }
        if (code.equals("ORT")) {
            return Code.ORT;
        }
        if (code.equals("PRO")) {
            return Code.PRO;
        }
        // probleme : code inconnu
        return null;
    }
    
    private static TypeActe getTypeActe(String type) {
        if (type.equals("1")) {
            return TypeActe.DIAGNOSTIQUE;
        }
        if (type.equals("2")) {
            return TypeActe.THERAPEUTIQUE;
        }
        
        // probleme : code inconnu
        return null;
    }

    public static void main(String[] args) {
        LectureXMLMedecin lxm = new LectureXMLMedecin("Medecin.xml");
        LectureXMLPatient lxp = new LectureXMLPatient("Patient.xml");
        LectureXMLDossier lxd = new LectureXMLDossier("Fiches.xml");
        System.out.println(lxp.getPatients());
        DossierMedical dm=lxd.getDossier(lxp.getPatients(), lxm.getMedecins());
        for (FicheDeSoins f : dm.getListeFiches()) {
            f.afficher();
        }

    }
}
