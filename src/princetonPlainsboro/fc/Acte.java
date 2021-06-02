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
    
    public String getTypeIntString(){
        if(type == TypeActe.DIAGNOSTIQUE)
            return "1";
        else
            return "2";
    }

    public String getObservations() {
        return observations;
    }
    public String toString() {
        return code.getLibelle();
        }
    public String toStringCode() {
        if (this.code==Code.CS){
            return "CS";
        }
        if (this.code==Code.CSC){
            return "CSC";
        }if (this.code==Code.FP){
            return "FP";
        }if (this.code==Code.KC){
            return "KC";
        }if (this.code==Code.KE){
            return "KE";
        }if (this.code==Code.K){
            return "K";
        }if (this.code==Code.KFA){
            return "KFA";
        }if (this.code==Code.KFB){
            return "KFB";
        }if (this.code==Code.ORT){
            return "ORT";
        }if (this.code==Code.ORT){
            return "ORT";
        }
        return null;
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
