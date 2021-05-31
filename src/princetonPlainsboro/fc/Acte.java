package princetonPlainsboro.fc;

import java.text.DecimalFormat;

public class Acte {
    private Code code;
    private int coef;
    private TypeActe type;
    private String observations;
    
    public Acte(Code code, int coef) {
        this.code = code;
        this.coef = coef;
        }
    
    public Acte(Code code, int coef, TypeActe type, String observations) {
        this.code= code;
        this.coef = coef;
        this.type = type;
        this.observations = observations;
    }

    public int getCoef() {
        return coef;
    }

    public TypeActe getType() {
        return type;
    }

    public String getObservations() {
        return observations;
    }
    public String toString() {
        return code.getLibelle();
        }
    
    public double cout() {
        return code.calculerCout(coef);
    }
    
    public Code getCode(){
        return this.code;
    }
    
    public String getNomActe(){
        return this.code.getLibelle();
    }
}
