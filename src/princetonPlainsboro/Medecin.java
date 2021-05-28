package princetonPlainsboro;

class Medecin {
    private String nom;
    private String prenom;
    private String specialite;
    
    public Medecin(String nom, String prenom, String specialite) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        }
    
    public String getSpecialite() { return specialite; }
    
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


