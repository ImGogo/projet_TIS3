package princetonPlainsboro.fc;

// Cette enumeration fait intervenir des valeurs qui possedent des
// attributs ('libelle' et 'cout') qui sont initialises par un appel du
// constructeur (arguments entre parentheses apres le nom de chaque valeur).
// Par exemple, la valeur Code.FP a un attribut 'libelle' contenant la chaine
// de caracteres "forfait pediatrique" et un attribut 'cout' ayant la valeur 5.0

public enum TypeActe {
    // valeurs de l'�num :
    DIAGNOSTIQUE("Diagnostique"),
    THERAPEUTIQUE("Th�rapeutique");
                             
    // attributs de l'�num :
    private String libelle;
    
    // constructeur :
    private TypeActe(String libelle) {
        this.libelle = libelle;
        }
    
    
    // m�thodes :
    public String toString() {
        return libelle;
        }
    
    }
