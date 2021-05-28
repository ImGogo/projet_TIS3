/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princetonPlainsboro.fc;

/**
 *
 * @author Go
 */
public class Scripts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LectureXML test = new LectureXML("Medecin.xml");
        DossierMedical dm = test.getDossier();
        for(Medecin m: dm.getMedecins()) {
            System.out.println(m.getId());
        }
        
    }
    
}
