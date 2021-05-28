package princetonPlainsboro;

import java.text.DecimalFormat;

class Acte {
    private Code code;
    private int coef;
    
    public Acte(Code code, int coef) {
        this.code = code;
        this.coef = coef;
        }
    public String[] toArray(){
        String[] table = {null, null, null, code.name(), new DecimalFormat("##.##").format( cout())};
        return table;
    }
    public String toString() {
        return code.toString() + ", coefficient : " + coef;
        }
    
    public double cout() {
        return code.calculerCout(coef);
        }
    }
