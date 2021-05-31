package princetonPlainsboro.fc;

// Cette enumeration fait intervenir des valeurs qui possedent des
// attributs ('libelle' et 'cout') qui sont initialises par un appel du
// constructeur (arguments entre parentheses apres le nom de chaque valeur).
// Par exemple, la valeur Code.FP a un attribut 'libelle' contenant la chaine
// de caracteres "forfait pediatrique" et un attribut 'cout' ayant la valeur 5.0

public enum TypeActe {
    // valeurs de l'ï¿½num :
    DIAGNOSTIQUE("Diagnostique"),
    THERAPEUTIQUE("Thérapeutique");
                             
    // attributs de l'ï¿½num :
    private String libelle;
    
    // constructeur :
    private TypeActe(String libelle) {
        this.libelle = libelle;
        }
    
    
    // mï¿½thodes :
    public String toString() {
        return libelle;
        }
    
    }
