/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princetonPlainsboro;

/**
 *
 * @author Go
 */
public class Scripts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String s = "Jean bonno - 07/08/2019";
        
        for(String f: s.split(" - ")){
            System.out.println(f);
        }
    }
    
}
