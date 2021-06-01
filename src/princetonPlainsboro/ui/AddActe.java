/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princetonPlainsboro.ui;

import java.util.Collections;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import princetonPlainsboro.fc.Acte;
import princetonPlainsboro.fc.Code;
import princetonPlainsboro.fc.Date;
import princetonPlainsboro.fc.DossierMedical;
import princetonPlainsboro.fc.FicheDeSoins;
import princetonPlainsboro.fc.Medecin;
import princetonPlainsboro.fc.Patient;
import princetonPlainsboro.fc.TypeActe;

/**
 *
 * @author Go
 */
public class AddActe extends javax.swing.JFrame {
    
    private static final int STARTING_YEAR = 2000;
    MainWindow mainWindow;
    Boolean isValid;
    FicheDeSoins fiche;
    /**
     * Creates new form AddPatient
     */
    public AddActe(MainWindow mainWindow, FicheDeSoins fiche) {
        initComponents();
        
        this.mainWindow = mainWindow;
        this.fiche = fiche;
        initFields();
        isValid = false;
    }
    public final void initFields() {
        this.currentPatientLbl.setText( fiche.getPatient().toString() );
        Vector<Code> codes = new Vector<>();
        Collections.addAll(codes, Code.values());
        
        DefaultComboBoxModel<Code> modelPatient = new DefaultComboBoxModel<>( codes );
        this.acteBox.setModel(modelPatient);
        
        setDescLabelsVisible(false);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typeBtnGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0));
        acteBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        currentPatientLbl = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        diagnostiqueBtn = new javax.swing.JRadioButton();
        therapeutiqueBtn = new javax.swing.JRadioButton();
        coefTxtF = new javax.swing.JTextField();
        totalDescLbl = new javax.swing.JLabel();
        totalLbl = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(365, 350));

        jLabel1.setText("Patient : ");

        jLabel2.setText("Acte :");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("Ajout d'un acte m�dical");

        addBtn.setText("Ajouter");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        acteBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acteBoxActionPerformed(evt);
            }
        });

        jLabel6.setText("Type :");

        currentPatientLbl.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jLabel7.setText("Coefficient :");

        typeBtnGroup.add(diagnostiqueBtn);
        diagnostiqueBtn.setSelected(true);
        diagnostiqueBtn.setText("Diagnostique");

        typeBtnGroup.add(therapeutiqueBtn);
        therapeutiqueBtn.setText("Th�rapeutique");

        coefTxtF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                coefTxtFKeyReleased(evt);
            }
        });

        totalDescLbl.setText("Co�t total:");

        totalLbl.setForeground(new java.awt.Color(255, 0, 51));

        jLabel9.setText("Observations :");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(diagnostiqueBtn)
                        .addGap(26, 26, 26)
                        .addComponent(therapeutiqueBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(acteBox, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentPatientLbl)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(coefTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(totalDescLbl)
                                        .addGap(19, 19, 19)
                                        .addComponent(totalLbl)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(0, 108, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(currentPatientLbl))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acteBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(diagnostiqueBtn)
                            .addComponent(therapeutiqueBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(coefTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalDescLbl)
                            .addComponent(totalLbl))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed

//        String newId = Integer.toString( Integer.parseInt( dm.getFiches().get( dm.getFiches().size() - 1).getId() ) + 1 );
        
        int selectedType;
        if( this.diagnostiqueBtn.isSelected() ) selectedType = 1;
        else selectedType = 2;
            
//        Acte tmp;
        if( isValid ) {
            fiche.ajouterActe(new Acte(
                    (Code) this.acteBox.getSelectedItem(),
                    Integer.parseInt( this.coefTxtF.getText()),
                    TypeActe.getTypeFromInt( selectedType ),
                    this.jTextArea1.getText()
            ));
            
                System.out.println(fiche.actesToStringList());
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erreurs dans un ou plusieurs champs");
        }
        this.dispose();
    }//GEN-LAST:event_addBtnActionPerformed

    private void coefTxtFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coefTxtFKeyReleased
        JTextField textField = (JTextField) evt.getSource();
        String validString = "^([0-9])+$";
        if(!textField.getText().matches(validString)){
           this.setDescLabelsVisible(false);
           this.totalLbl.setText("Format attendu : 12345");
           isValid = false;
        } else {
            this.setDescLabelsVisible(true);
            updateCoutTotal();
            isValid = true;
        }
    }//GEN-LAST:event_coefTxtFKeyReleased

    private void acteBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acteBoxActionPerformed
        updateCoutTotal();
    }//GEN-LAST:event_acteBoxActionPerformed
    
    private void updateCoutTotal() {
        if( !this.coefTxtF.getText().equals("")) {
            double cout = ((Code) this.acteBox.getSelectedItem()).calculerCout( Integer.parseInt( this.coefTxtF.getText()));
            this.totalLbl.setText( Double.toString(cout) + "�");
        }
        
    }
    private void setDescLabelsVisible(Boolean bool) {
        this.totalDescLbl.setVisible(bool);
    }   
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() { public void run() {
//                new AddPatient().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Code> acteBox;
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField coefTxtF;
    private javax.swing.JLabel currentPatientLbl;
    private javax.swing.JRadioButton diagnostiqueBtn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JRadioButton therapeutiqueBtn;
    private javax.swing.JLabel totalDescLbl;
    private javax.swing.JLabel totalLbl;
    private javax.swing.ButtonGroup typeBtnGroup;
    // End of variables declaration//GEN-END:variables
}