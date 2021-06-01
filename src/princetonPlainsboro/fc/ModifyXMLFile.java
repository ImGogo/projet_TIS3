/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princetonPlainsboro.fc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Benhadj
 */
//fonctions avec en parametre Id et le medecin, ou fiches de soins ou patients 
public class ModifyXMLFile {

    public static void modifyMedecin(String id, Medecin med) {
        try {
            String specialite = med.getSpecialite();
            String prenom = med.getPrenom();
            String nom = med.getNom();
            String telephone = med.getTelephone();

            String filePath = "C:\\Users\\Benhadj\\Documents\\NetBeansProjects\\projet_TIS3(2)\\src\\donnees\\";
            FileInputStream inputFileMedecin = new FileInputStream(filePath + "Medecins.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            InputSource isMedecin = new InputSource(inputFileMedecin);
            Document docMedecin = docBuilder.parse(isMedecin);

            NodeList medecin = docMedecin.getElementsByTagName("medecin");

            int k = 0;
            Node medecins = medecin.item(k);
            NodeList childNodesMedecin = medecins.getChildNodes();
            while (k < medecin.getLength() && !id.equals(medecins.getAttributes().getNamedItem("id").getTextContent().trim())) {
                k++;
                medecins = medecin.item(k);
                childNodesMedecin = medecins.getChildNodes();
            }

            for (int j = 0; j < childNodesMedecin.getLength(); j++) {
                Node item = childNodesMedecin.item(j);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    if ("prenom".equalsIgnoreCase(item.getNodeName())) {
                        item.setTextContent(prenom);
                    }
                    if ("nom".equalsIgnoreCase(item.getNodeName())) {
                        item.setTextContent(nom);
                    }
                    if ("specialite".equalsIgnoreCase(item.getNodeName())) {
                        item.setTextContent(specialite);
                    }
                    if ("telephone".equalsIgnoreCase(item.getNodeName())) {
                        item.setTextContent(telephone);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addMedecin(Medecin med) {
        try {
            String filePath = "C:\\Users\\Benhadj\\Documents\\NetBeansProjects\\projet_TIS3(2)\\src\\donnees\\";
            FileInputStream inputFileMedecin = new FileInputStream(filePath + "Medecins.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            InputSource isMedecin = new InputSource(inputFileMedecin);
            Document docMedecin = docBuilder.parse(isMedecin);

            NodeList root = docMedecin.getElementsByTagName("medecins");
            NodeList medecins = docMedecin.getElementsByTagName("medecin");
            Node medecin = medecins.item(0);

            Element newMedecin = docMedecin.createElement("medecin");
            Element name = docMedecin.createElement("nom");
            Element surname = docMedecin.createElement("prenom");
            Element specialite = docMedecin.createElement("specialite");
            Element telephone = docMedecin.createElement("telephone");

            name.appendChild(docMedecin.createTextNode(med.getNom()));
            surname.appendChild(docMedecin.createTextNode(med.getPrenom()));
            specialite.appendChild(docMedecin.createTextNode(med.getSpecialite()));
            telephone.appendChild(docMedecin.createTextNode(med.getTelephone()));

            newMedecin.appendChild(name);
            newMedecin.appendChild(surname);
            newMedecin.appendChild(specialite);
            newMedecin.appendChild(telephone);

            int newId = medecins.getLength() + 1;
            String newIdMed = Integer.toString(newId);
            newMedecin.setAttribute("id", newIdMed);
//            newMedecin.getAttributes().getNamedItem("id").setTextContent(newIdMed);

            root.item(0).appendChild(newMedecin);

            // write the content on console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(docMedecin);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult("Medecins2.xml");
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPatient(Patient pat) {
        try {
            String filePath = "C:\\Users\\Benhadj\\Documents\\NetBeansProjects\\projet_TIS3(2)\\src\\donnees\\";
            FileInputStream inputFilePatient = new FileInputStream(filePath + "Patients.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            InputSource isPatient = new InputSource(inputFilePatient);
            Document docPatient = docBuilder.parse(isPatient);

            NodeList root = docPatient.getElementsByTagName("patients");
            NodeList patients = docPatient.getElementsByTagName("patient");
            Node patient = patients.item(0);

            Element newPatient = docPatient.createElement("patient");
            Element name = docPatient.createElement("nom");
            Element surname = docPatient.createElement("prenom");
            Element adress = docPatient.createElement("adresse");
            Element insee = docPatient.createElement("insee");

            name.appendChild(docPatient.createTextNode(pat.getNom()));
            surname.appendChild(docPatient.createTextNode(pat.getPrenom()));
            adress.appendChild(docPatient.createTextNode(pat.getAdresse()));
            insee.appendChild(docPatient.createTextNode(pat.getNumINSEE()));

            newPatient.appendChild(name);
            newPatient.appendChild(surname);
            newPatient.appendChild(adress);
            newPatient.appendChild(insee);

            newPatient.setAttribute("id", pat.getNumINSEE());
//            newMedecin.getAttributes().getNamedItem("id").setTextContent(newIdMed);

            root.item(0).appendChild(newPatient);

            // write the content on console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(docPatient);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult("Patients2.xml");
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifyPatient(String id, Patient pat) {
        try {
            String adress = pat.getAdresse();
            String numINSEE = pat.getNumINSEE();
            String prenom = pat.getPrenom();
            String nom = pat.getNom();

            String filePath = "C:\\Users\\Benhadj\\Documents\\NetBeansProjects\\projet_TIS3(2)\\src\\donnees\\";
            FileInputStream inputFilePatient = new FileInputStream(filePath + "Patients.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            InputSource isPatient = new InputSource(inputFilePatient);
            Document docPatient = docBuilder.parse(isPatient);

            NodeList patient = docPatient.getElementsByTagName("Patient");

            int k = 0;
            Node patients = patient.item(k);
            NodeList childNodesPatient = patients.getChildNodes();
            while (k < patient.getLength() && !id.equals(patients.getAttributes().getNamedItem("id").getTextContent().trim())) {
                k++;
                patients = patient.item(k);
                childNodesPatient = patients.getChildNodes();
            }

            for (int j = 0; j < childNodesPatient.getLength(); j++) {
                Node item = childNodesPatient.item(j);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    if ("prenom".equalsIgnoreCase(item.getNodeName())) {
                        item.setTextContent(prenom);
                    }
                    if ("nom".equalsIgnoreCase(item.getNodeName())) {
                        item.setTextContent(nom);
                    }
                    if ("adresse".equalsIgnoreCase(item.getNodeName())) {
                        item.setTextContent(adress);
                    }
                    if ("insee".equalsIgnoreCase(item.getNodeName())) {
                        item.setTextContent(numINSEE);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addFiches(FicheDeSoins f) {
        try {
            String filePath = "C:\\Users\\Benhadj\\Documents\\NetBeansProjects\\projet_TIS3(2)\\src\\donnees\\";
            FileInputStream inputFileFiche = new FileInputStream(filePath + "Fiches.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            InputSource isFiche = new InputSource(inputFileFiche);
            Document docFiche = docBuilder.parse(isFiche);

            NodeList root = docFiche.getElementsByTagName("Fiches");
            NodeList fiches = docFiche.getElementsByTagName("ficheDeSoins");
            Node fiche = fiches.item(0);

            Element newFiche = docFiche.createElement("fiche");
            Element date = docFiche.createElement("date");
            Element medecin = docFiche.createElement("medecin");
            Element patient = docFiche.createElement("patient");

            Date dateFiche = f.getDate();
//            String strDate = dateFormat.format(dateFiche);
            String strDate = dateFiche.getFormattedDate();
            Vector<Acte> listeActes = f.getActes();

            date.appendChild(docFiche.createTextNode(strDate));
            medecin.setAttribute("id", f.getMedecin().getId());
            patient.setAttribute("id", f.getPatient().getNumINSEE());

            newFiche.appendChild(date);
            newFiche.appendChild(medecin);
            newFiche.appendChild(patient);

            for (int i = 0; i < listeActes.size(); i++) {
                Element acte = docFiche.createElement("acte");
                Element code = docFiche.createElement("code");
                Element coef = docFiche.createElement("coef");
                Element observation = docFiche.createElement("observation");
                Element nomActe = docFiche.createElement("nomActe");
                nomActe.appendChild(docFiche.createTextNode(listeActes.get(i).getNomActe()));
                code.appendChild(docFiche.createTextNode(listeActes.get(i).toStringCode()));
                int coefActe = listeActes.get(i).getCoef();
                String coefStr = Integer.toString(coefActe);
                coef.appendChild(docFiche.createTextNode(coefStr));
                observation.appendChild(docFiche.createTextNode(listeActes.get(i).getObservation()));
                acte.appendChild(nomActe);
                acte.appendChild(code);
                acte.appendChild(coef);
                acte.appendChild(observation);
                newFiche.appendChild(acte);
            }

            int newId = fiches.getLength() + 1;
            String newIdFiche = Integer.toString(newId);
            newFiche.setAttribute("id", newIdFiche);

//            newMedecin.getAttributes().getNamedItem("id").setTextContent(newIdMed);
            root.item(0).appendChild(newFiche);

            // write the content on console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(docFiche);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult("Fiche2.xml");
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifyFiche(String id, FicheDeSoins f) {
        try {
            Date date = f.getDate();
            Medecin med = f.getMedecin();
            Patient pat = f.getPatient();

            //System.out.println("listeActe debut: " + listeActe.size());
            String filePath = "C:\\Users\\Benhadj\\Documents\\NetBeansProjects\\projet_TIS3(2)\\src\\donnees\\";
            FileInputStream inputFileFiche = new FileInputStream(filePath + "Fiches.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            InputSource isFiche = new InputSource(inputFileFiche);
            Document docFiche = docBuilder.parse(isFiche);

            NodeList fiche = docFiche.getElementsByTagName("ficheDeSoins");

            int k = 0;
            System.out.println("fiche.length= " + fiche.getLength());
            Node fiches = fiche.item(k);
            NodeList childNodesFiche = fiches.getChildNodes();
            System.out.println("childNodesFiche= " + childNodesFiche.getLength());
            while (k < fiche.getLength() && !id.equals(fiches.getAttributes().getNamedItem("id").getTextContent().trim())) {
                //System.out.println("id= " + fiches.getAttributes().getNamedItem("id").getTextContent().trim());
                k++;
                fiches = fiche.item(k);
                childNodesFiche = fiches.getChildNodes();
            }

            for (int j = 0; j < childNodesFiche.getLength(); j++) {
                Node item = childNodesFiche.item(j);
                //System.out.println("CNF.item(j)= " + item);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    if ("medecin".equalsIgnoreCase(item.getNodeName())) {
                        System.out.println("OK: " + med.getId());
                        NamedNodeMap attribute = item.getAttributes();
                        Node nodeAttr = attribute.getNamedItem("id");
                        nodeAttr.setTextContent(med.getId());

                    }
                    if ("patient".equalsIgnoreCase(item.getNodeName())) {
                        item.setNodeValue(pat.getNumINSEE());
                        NamedNodeMap attribute = item.getAttributes();
                        Node nodeAttr = attribute.getNamedItem("id");
                        nodeAttr.setTextContent(pat.getNumINSEE());
                    }
                }

            }

            // write the content on console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(docFiche);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult("Fiche2.xml");
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void modifyFicheActe(String id, Acte a) {
        try {
            String nom = a.getNomActe();
            System.out.println("NOM: " + nom);
            String code = a.toStringCode();
            int coef = a.getCoef();
            String coefStr = Integer.toString(coef);
            String observation = a.getObservation();
            String nomActeCourant = null;

            String filePath = "C:\\Users\\Benhadj\\Documents\\NetBeansProjects\\projet_TIS3(2)\\src\\donnees\\";
            FileInputStream inputFileFiche = new FileInputStream(filePath + "Fiches.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            InputSource isFiche = new InputSource(inputFileFiche);
            Document docFiche = docBuilder.parse(isFiche);

            NodeList fiche = docFiche.getElementsByTagName("ficheDeSoins");

            int k = 0;
            System.out.println("fiche.length= " + fiche.getLength());
            Node fiches = fiche.item(k);
            NodeList childNodesFiche = fiches.getChildNodes();
            System.out.println("childNodesFiche= " + childNodesFiche.getLength());
            while (k < fiche.getLength() && !id.equals(fiches.getAttributes().getNamedItem("id").getTextContent().trim())) {
                System.out.println("id= " + fiches.getAttributes().getNamedItem("id").getTextContent().trim());
                k++;
                fiches = fiche.item(k);
                childNodesFiche = fiches.getChildNodes();
            }
            System.out.println("k= " + k);

            for (int j = 0; j < childNodesFiche.getLength(); j++) {
                Node item = childNodesFiche.item(j);
                System.out.println("CNF.item(j)= " + item);
                System.out.println("j= " + j);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    if ("acte".equalsIgnoreCase(item.getNodeName())) {
                        NodeList childNodesActe = item.getChildNodes();

                        for (int l = 0; l < childNodesActe.getLength(); l++) {
                            System.out.println("l= " + l);
                            Node itemActe = childNodesActe.item(l);

                            if (itemActe.getNodeType() == Node.ELEMENT_NODE) {

                                if ("nomActe".equalsIgnoreCase(itemActe.getNodeName()) && itemActe.getTextContent().equals(nom)) {
                                    nomActeCourant = itemActe.getTextContent();
                                    itemActe.setTextContent(nom);

                                } else if ("nomActe".equalsIgnoreCase(itemActe.getNodeName())) {
                                    nomActeCourant = itemActe.getTextContent();
                                }
                                if ("code".equalsIgnoreCase(itemActe.getNodeName()) && itemActe.getTextContent().equals(code) && nomActeCourant.equals(nom)) {
                                    itemActe.setTextContent(code);

                                }
                                if ("coef".equalsIgnoreCase(itemActe.getNodeName()) && nomActeCourant.equals(nom)) {
                                    itemActe.setTextContent(coefStr);

                                }
                                if ("observation".equalsIgnoreCase(itemActe.getNodeName()) && nomActeCourant.equals(nom)) {
                                    itemActe.setTextContent(observation);
                                }

                            }

                        }
                    }
                }
            }

            // write the content on console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(docFiche);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult("Fiche2.xml");
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addFicheActe(String id, Acte a) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
        try {
            String nom = a.getNomActe();
            System.out.println("NOM: " + nom);
            String code = a.toStringCode();
            int coef = a.getCoef();
            String coefStr = Integer.toString(coef);
            String observation = a.getObservation();
            String nomActeCourant = null;

            String filePath = "C:\\Users\\Benhadj\\Documents\\NetBeansProjects\\projet_TIS3(2)\\src\\donnees\\";
            FileInputStream inputFileFiche = new FileInputStream(filePath + "Fiches.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            InputSource isFiche = new InputSource(inputFileFiche);
            Document docFiche = docBuilder.parse(isFiche);

            NodeList fiche = docFiche.getElementsByTagName("ficheDeSoins");

            int k = 0;
            System.out.println("fiche.length= " + fiche.getLength());
            Node fiches = fiche.item(k);
            NodeList childNodesFiche = fiches.getChildNodes();
            System.out.println("childNodesFiche= " + childNodesFiche.getLength());
            while (k < fiche.getLength() && !id.equals(fiches.getAttributes().getNamedItem("id").getTextContent().trim())) {
                System.out.println("id= " + fiches.getAttributes().getNamedItem("id").getTextContent().trim());
                k++;
                fiches = fiche.item(k);
                childNodesFiche = fiches.getChildNodes();
            }

            Element acte = docFiche.createElement("acte");
            Element codeAdd = docFiche.createElement("code");
            Element coefAdd = docFiche.createElement("coef");
            Element observationAdd = docFiche.createElement("observation");
            Element nomActeAdd = docFiche.createElement("nomActe");
            nomActeAdd.appendChild(docFiche.createTextNode(nom));
            codeAdd.appendChild(docFiche.createTextNode(code));
            coefAdd.appendChild(docFiche.createTextNode(coefStr));
            observationAdd.appendChild(docFiche.createTextNode(observation));
            acte.appendChild(nomActeAdd);
            acte.appendChild(codeAdd);
            acte.appendChild(coefAdd);
            acte.appendChild(observationAdd);
            fiches.appendChild(acte);
            
            // write the content on console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(docFiche);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult("Fiche2.xml");
            transformer.transform(source, consoleResult);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, IOException, SAXException, TransformerException {

        Medecin med = new Medecin("BHS", "Meissa", "Oncologue", "0498758496", "4");
        //addMedecin(med);

        Patient pat = new Patient("BHS", "Meissa", "14 avenue Z", "125");
        //addPatient(pat);

        Code code1 = Code.CS;
        Code code2 = Code.CSC;

        Acte acte1 = new Acte(code1, 1);
        Acte acte2 = new Acte(code2, 1);

        Date date = new Date(15, 11, 2022);

        FicheDeSoins fs = new FicheDeSoins(pat, med, date);
        fs.ajouterActe(acte1);
        fs.ajouterActe(acte2);

        addFiches(fs);

        Medecin medModif = new Medecin("Bono", "Gilbert", "ORL", "0606060606", "2");
        Acte acte3 = new Acte(code1, 10000, "consultation au cabinet", "ZZZZ");

        FicheDeSoins fsModif = new FicheDeSoins(pat, medModif, date);
        addFicheActe("1", acte3);

        //modifyFiche("1", fsModif);
    }

}
