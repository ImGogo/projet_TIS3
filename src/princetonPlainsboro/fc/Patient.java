package princetonPlainsboro.fc;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String nom;
    private String prenom;
    private String numINSEE;
    private String adresse;
    private List<FicheDeSoins> listeFiches;
    
    public Patient(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        listeFiches = new ArrayList<>();
    }
    
      public Patient(String nom, String prenom,String adresse,String insee) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse=adresse;
        this.numINSEE=insee;
        this.listeFiches = new ArrayList<>();
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
    
    public void ajouterFiche(FicheDeSoins f){
        this.listeFiches.add(f);
    }
    
    public List<Acte> getlisteActes(){
        List<Acte> l = new ArrayList<>();
        for(FicheDeSoins f : listeFiches){
            for(Acte a : f.getActes() ) {
                l.add(a);
            }
        }
        return l;
    }
    
    public List<FicheDeSoins> getListeFiches() {
        return this.listeFiches;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumINSEE(String numINSEE) {
        this.numINSEE = numINSEE;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

//    public void setListeActes(List<Acte> listeActes) {
//        this.listeActes = listeActes;
//    }
        
    
   
    
    public boolean equals(Object o) {
        if (o instanceof Patient) {
            Patient p = (Patient)o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        }
        else
            return false;
        }    
    }

