package princetonPlainsboro.fc;

public class Medecin {
    private String id;
    private String nom;
    private String prenom;
    private String specialite;
    private String telephone;
    
    public Medecin(String nom, String prenom, String specialite,String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.telephone=telephone;
        }
    
    public Medecin(String nom, String prenom, String specialite,String telephone, String id) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.telephone = telephone;
        this.id = id;
        }
    
    public String getSpecialite() { return specialite; }
    
    public String getTelephone(){return telephone;}
    
    public String getId() { return id; }
    
    public String toString() {
        return "Dr " + prenom + " " + nom + ", " + specialite;
        }
    
    public boolean equals(Object o) {
        if (o instanceof Medecin) {
            Medecin p = (Medecin)o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
            }
        else
            return false;
        }    

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    
    
}


