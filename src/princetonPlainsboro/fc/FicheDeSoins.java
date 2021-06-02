package princetonPlainsboro.fc;

import java.util.ArrayList;
import java.util.Vector;

public class FicheDeSoins {
    private Patient patient;
    private Medecin medecin;
    private Date date;
    private String id;
    private Vector<Acte> actes;       // contient des objets de classe 'Acte'
    
    public FicheDeSoins(Patient patient, Medecin medecin, Date date, String id) {
        this.patient = patient;
        this.medecin = medecin;
        this.date = date;
        this.id = id;
        actes = new Vector<Acte>();   // liste vide
        }
    
    public FicheDeSoins(Patient patient, Medecin medecin, Date date, String id, java.util.List<Acte> actes) {
        this.patient = patient;
        this.medecin = medecin;
        this.date = date;
        this.id = id;
        actes = new Vector<Acte>(actes);   // liste vide
        }

    public String getId() {
        return id;
    }
    
    public Patient getPatient() { return patient; }
    public Medecin getMedecin() { return medecin; }
    public Date    getDate()    { return date; }
    
    public void ajouterActe(Acte acte) {
        actes.add(acte);
        }
    
    public void ajouterActe(Code code, int coefficient) {
        Acte acte = new Acte(code, coefficient);
        actes.add(acte);
        }
    
    public void afficher() {
        System.out.println("Fiche de soins du " + date.toString());
        System.out.println("- medecin : " + medecin.toString());
        System.out.println("- patient : " + patient.toString());
        System.out.println("- actes medicaux :");
        for (int i=0; i<actes.size(); i++) {
            Acte a = actes.get(i);
            System.out.println("    > " + a.toString());
            }
        }
    
    public String getStringToPrint(){
        String s = "Fiche de soins du " + date.toString() +"\n";
        s += "- medecin : " + medecin.toString() + "\n";
        s += "- patient : " + patient.toString() + "\n";
        s += "- actes medicaux :\n";
        for (int i=0; i<actes.size(); i++) {
            Acte a = actes.get(i);
            s += "    > " + a.toString() + "\n";
            }
        return s;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public double coutTotal() {
        double total = 0;
        for (int i=0; i<actes.size(); i++) {
            Acte a = actes.get(i);
            total += a.cout();
            }
        return total;
        }
    
    public String toString(){
        return patient.getNom() + " " + patient.getPrenom() + " - " + date.toString();
    }
    
    public ArrayList<String> actesToStringList() {
        
        ArrayList<String> stringA = new ArrayList<>();
       
        for(Acte a: actes){
            stringA.add(a.toString());
        }
        return stringA;
    }
    
    public Vector<Acte> getActes(){
        return this.actes;
    }
    
    
}

