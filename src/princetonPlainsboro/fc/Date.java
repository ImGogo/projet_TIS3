package princetonPlainsboro.fc;

public class Date implements Comparable {
    private int jour;
    private int mois;
    private int annee;
    private int heure;
    private int minute;
    
    public Date(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        }
    
    public Date(int jour, int mois, int annee, int heure, int minute) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure = heure;
        this.minute = minute;
        }

    public int getHeure() {
        return heure;
    }

    public int getMinute() {
        return minute;
    }
    
    public String toString() {
        return jour + "/" + mois + "/" + annee;
        }

    public int getJour() {
        return jour;
    }

    public int getMois() {
        return mois;
    }

    public int getAnnee() {
        return annee;
    }
    
     public String getFormattedDate(){
        return annee + "-" + mois + "-" + jour + ";" + heure + ":" + minute;
    }
     
    public String getDateToPrinter(){
        String sJour = Integer.toString(jour);
        String sMois = Integer.toString(mois);
        String sHeure = Integer.toString(heure);
        String sMinute = Integer.toString(minute);
        if(jour < 10){ sJour = "0" + sJour; }
        if(mois < 10){ sMois = "0" + sMois; }
        if(heure < 10){ sHeure = "0" + sHeure; }
        if(minute < 10){ sMinute = "0" + sMinute; }
        return sJour + "/" + sMois + "/" + annee + " à " + sHeure + ":" + sMinute;
    }
    
    public boolean equals(Object o) {
        if (o instanceof Date) {
            Date d = (Date)o;
            return (annee == d.annee) && (mois == d.mois) && (jour == d.jour);
            }
        else
            return false;
        }
    
    // precondition : 'o' est une instance de 'Date' :
    public int compareTo(Object o) {
        Date d = (Date)o;
        if (annee != d.annee)
            return annee - d.annee;
        // ici on a forcement annee == d.annee :
        if (mois != d.mois)
            return mois  - d.mois;
        // ici on a forcement annee == d.annee et mois == d.mois :
        return jour - d.jour;
        }
    
    }
