package princetonPlainsboro.fc;

import java.util.ArrayList;
import java.util.List;

class Patient {
    private String nom;
    private String prenom;
    private String numINSEE;
    private String adresse;
    private List<Acte> listeActes;
    
    public Patient(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.listeActes = new ArrayList<>();
    }
    
      public Patient(String nom, String prenom,String adresse,String insee) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse=adresse;
        this.numINSEE=insee;
        this.listeActes = new ArrayList<>();
    }
    public Patient(){
        this.nom=null;
        this.prenom=null;
    }
    
    public String toString() {
        return prenom + " " + nom;
    }
    
    public String toStringComplet() {
        return prenom + " " + nom+ " "+adresse+" "+numINSEE;
    }
    
    public String getNom(){
        return this.nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    
    public String getAdresse(){
        return this.adresse;
    }
    
    public String getNumINSEE(){
        return this.numINSEE;
    }
    
    public void ajouterActe(Acte a){
        this.listeActes.add(a);
    }
    
    public List<Acte> getlisteActes(){
        return this.listeActes;
    }
    
    public boolean equals(Object o) {
        if (o instanceof Patient) {
            Patient p = (Patient)o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        }
        else
            return false;
        }    
    }

