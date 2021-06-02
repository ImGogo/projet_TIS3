/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princetonPlainsboro.fc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;

/**
 *
 * @author Go
 */
public class FichePatientPrinter implements Printable, ActionListener{
    private FicheDeSoins fiche;
    int[] pageBreaks;
    String[] textLines;
    public FichePatientPrinter(FicheDeSoins fiche) {
        this.fiche = fiche;
    }
    public void setFiche(FicheDeSoins f){
        this.fiche = fiche;
    }
    
    public int print(Graphics g, PageFormat pf, int pageIndex) throws
                                                        PrinterException {
 
        FontMetrics metrics = g.getFontMetrics();
        int lineHeight = metrics.getHeight();
        
        
        String text = fiche.getStringToPrint();
        
        if (pageBreaks == null) {
            textLines = text.split("\n");
            int linesPerPage = (int)(pf.getImageableHeight()/lineHeight) - 2;
            int numBreaks = (textLines.length-1)/linesPerPage;
            pageBreaks = new int[numBreaks];
            for (int b=0; b<numBreaks; b++) {
                pageBreaks[b] = (b+1)*linesPerPage; 
            }
        }
        
        if (pageIndex > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
 
        int y = 20; 
        int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
        int end   = (pageIndex == pageBreaks.length)
                         ? textLines.length : pageBreaks[pageIndex];
        for (int line=start; line<end; line++) {
            y += lineHeight;
            g.drawString(textLines[line], 20, y);
        }
 
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
    
    public void printFiche(){
        PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                  job.print();
             } catch (PrinterException ex) {
              /* The job did not successfully complete */
             }
         }
    }
    public void actionPerformed(ActionEvent e) {
         PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                  job.print();
             } catch (PrinterException ex) {
              /* The job did not successfully complete */
             }
         }
    }
 
    public static void main(String args[]) {
  
//        UIManager.put("swing.boldMetal", Boolean.FALSE);
//        JFrame f = new JFrame("Hello World Printer");
//        f.addWindowListener(new WindowAdapter() {
//           public void windowClosing(WindowEvent e) {System.exit(0);}
//        });
//        JButton printButton = new JButton("Print Hello World");
//        printButton.addActionListener(new FichePatientPrinter());
//        f.add("Center", printButton);
//        f.pack();
//        f.setVisible(true);
    }
    
}
