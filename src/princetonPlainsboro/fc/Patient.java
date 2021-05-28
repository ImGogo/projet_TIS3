package princetonPlainsboro.fc;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String nom;
    private String prenom;
    private List<Acte> listeActes;
    
    public Patient(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.listeActes = new ArrayList<>();
        }
    
    public String toString() {
        return prenom + " " + nom;
    }
    public String getNom(){
        return this.nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public void setPrenom(String nom){
        this.prenom = prenom;
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

