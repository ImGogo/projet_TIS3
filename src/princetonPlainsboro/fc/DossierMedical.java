package princetonPlainsboro.fc;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DossierMedical {

    private List<FicheDeSoins> fiches;     // contient des objets de classe 'FicheDeSoins'
    private List<Patient> patients;
    private List<Medecin> medecins;

    public DossierMedical() {
        fiches = new Vector<FicheDeSoins>();  // liste vide
        patients = new ArrayList<Patient>();
        medecins = new ArrayList<Medecin>();
    }

    public void ajouterFiche(FicheDeSoins fiche) {
        fiches.add(fiche);
    }
    
    public void ajouterPatient(Patient patient) {
        patients.add(patient);
    }
    
    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
    }
    
    public List<Patient> getPatients() {
        return this.patients;
    }
    
    public List<Medecin> getMedecins(){
        return this.medecins;
    }
    
    public Patient getPatient(String nom) {
        int i = 0;
        while( i < patients.size() && !patients.get(i).toString().equals(nom)) {
            i++;
        }
        return patients.get(i);
    }
    
    public Medecin getMedecin(String nom) {
        int i = 0;
        while( i < medecins.size() && !medecins.get(i).toString().equals(nom)) {
            i++;
        }
        return medecins.get(i);
    }
    
    public FicheDeSoins getFiche(String nom){
        int i = 0;
        String[] data = nom.split(" - ");
        while( i < fiches.size() && !fiches.get(i).getDate().toString().equals( data[1] ) && !fiches.get(i).getPatient().toString().equals( data[0] )) {
            i++;
        }
        return fiches.get(i);
    }
    
    public List<FicheDeSoins> getListeFiches(){
        return fiches;
    }
    
    public List<String> getPatientsNames() {
        List<String> liste = new ArrayList<>();
        
        for(Patient p: patients) {
            
            liste.add(p.toString());
//            System.out.println(p.toString());
        }
        
        return liste;
        
    }
    
    public List<String> getMedecinsNames() {
        List<String> liste = new ArrayList<>();
        
        for(Medecin m: medecins) {
            
            liste.add(m.toString());
        }
        
        return liste;
        
    }
    
    public List<String> getFichesNames() {
        List<String> liste = new ArrayList<>();
        
        for(FicheDeSoins f: fiches) {
            
            liste.add(f.toString());
        }
        
        return liste;
        
    }
    
    public boolean contientPatient(Patient patient) {
        return patients.contains(patient);
        
    }
    
    public boolean contientMedecin(Medecin medecin) {
        return medecins.contains(medecin);
    }

    public void afficher() {
        System.out.println("Dossier medical informatise :");
        System.out.println("-----------------------------");
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            f.afficher();
            // pour separer les fiches de soins :
            System.out.println("--------------------------------------");
        }
    }

    public double coutPatient(Patient p) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (p.equals(f.getPatient())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutMedecin(Medecin m) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutSpecialite(String specialite) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (specialite.equals(f.getMedecin().getSpecialite())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public void afficherListePatients(Medecin m) {
        System.out.println("> liste des patients du " + m.toString() + " :");
        Vector<Patient> liste = new Vector<Patient>();
        // 'liste' contient tous les patients deja affiches
        // --> ceci permet de ne pas reafficher un patient deja affiche
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                Patient p = f.getPatient();
                if (!liste.contains(p)) {
                    System.out.println(" - " + p.toString());
                    liste.add(p);
                }
            }
        }
    }

    public int nombreFichesIntervalle(Date d1, Date d2) {
        int n = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Date d = f.getDate();
            if (d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) {
                n++;
            }
        }
        return n;
    }

    public void trierDates() {
        Vector<FicheDeSoins> copieFiches = new Vector<FicheDeSoins>(fiches);

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }

    // tri generique :
    public void trier(ComparaisonFiches c) {
        Vector<FicheDeSoins> copieFiches = new Vector<FicheDeSoins>(fiches);

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (c.comparer(f2, f1) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }
}

