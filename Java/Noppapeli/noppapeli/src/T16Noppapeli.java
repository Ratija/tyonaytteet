
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
public class T16Noppapeli extends javax.swing.JFrame {

    /**
     * Creates new form Noppapeli
     */
    public T16Noppapeli() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnHeita = new javax.swing.JButton();
        jBtnLopeta = new javax.swing.JButton();
        jLblTulos = new javax.swing.JLabel();
        jLblPelaaja = new javax.swing.JLabel();
        jLblKone = new javax.swing.JLabel();
        jTxtfPelaaja = new javax.swing.JTextField();
        jTxtfKone = new javax.swing.JTextField();
        jTxtfHeittoPelaaja = new javax.swing.JTextField();
        jTxtfHeittoKone = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtArTulos = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLblNopat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBtnHeita.setText("Heitä noppaa");
        jBtnHeita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnHeitaMouseClicked(evt);
            }
        });
        jBtnHeita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHeitaActionPerformed(evt);
            }
        });

        jBtnLopeta.setText("Skippaa vuoro / Lopeta");
        jBtnLopeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnLopetaMouseClicked(evt);
            }
        });
        jBtnLopeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLopetaActionPerformed(evt);
            }
        });

        jLblTulos.setText("Voitetut kierrokset:");

        jLblPelaaja.setText("Pelaaja");

        jLblKone.setText("Kone");

        jTxtfPelaaja.setText("0");
        jTxtfPelaaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtfPelaajaActionPerformed(evt);
            }
        });

        jTxtfKone.setText("0");

        jTxtfHeittoPelaaja.setText("0");
        jTxtfHeittoPelaaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtfHeittoPelaajaActionPerformed(evt);
            }
        });

        jTxtfHeittoKone.setText("0");

        jLabel1.setText("Pelaajan summa:");

        jLabel2.setText("Koneen summa:");

        jTxtArTulos.setColumns(20);
        jTxtArTulos.setRows(5);
        jScrollPane1.setViewportView(jTxtArTulos);

        jLabel3.setText("Halutessasi voit skipata vuorosi, ja myös vastustajasi voi tehdä niin.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(jLblPelaaja, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTxtfPelaaja, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119)))
                        .addComponent(jBtnLopeta, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLblTulos, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jTxtfKone, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jLblKone, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(85, 85, 85)
                        .addComponent(jLblNopat, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtnHeita)
                                .addGap(18, 18, 18)))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtfHeittoKone, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtfHeittoPelaaja, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnHeita)
                    .addComponent(jBtnLopeta))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtfHeittoKone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtfHeittoPelaaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblPelaaja)
                            .addComponent(jLblKone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblTulos)
                            .addComponent(jTxtfPelaaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtfKone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLblNopat, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnHeitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHeitaActionPerformed
        // TODO add your handling code here:
       
        Noppa kone1 = new Noppa(6);
        Noppa kone2 = new Noppa(6);
        Noppa pelaaja1 = new Noppa(6);
        Noppa pelaaja2 = new Noppa(6);

        int koneSumma = Integer.parseInt(jTxtfHeittoKone.getText());
        int pelaajaSumma = Integer.parseInt(jTxtfHeittoPelaaja.getText());

        String tulos = null;

        pelaaja1.heita();
        pelaaja2.heita();
        pelaajaSumma += pelaaja1.getArvo() + pelaaja2.getArvo();
        
         
         
        if ((koneSumma < 17) && (koneSumma < pelaajaSumma)) {

            kone1.heita();
            kone2.heita();
            koneSumma += kone1.getArvo() + kone2.getArvo();
           
        }
        if (koneSumma > pelaajaSumma) {

            tulos = (" ");
            jTxtArTulos.append(tulos);

        }

        String appendText = ("Koneella on " + koneSumma + " pistettä " + "\n" + "Pelaajalla on " + pelaajaSumma + " pistettä" + "\n" + "\n");
        jTxtArTulos.append(appendText);
        jTxtfHeittoPelaaja.setText(String.valueOf(pelaajaSumma));
        jTxtfHeittoKone.setText(String.valueOf(koneSumma));

        if ((koneSumma > 21) && (pelaajaSumma > 21)) {
            tulos = ("Molemmat hävisivät.\n");

            jTxtArTulos.append(tulos);

        } else if ((koneSumma > 21) && (pelaajaSumma <= 21)) {
            tulos = ("Pelaaja voitti!!\n");

            jTxtArTulos.append(tulos);

        } else if ((koneSumma <= 21) && (pelaajaSumma > 21)) {
            tulos = ("Kone voitti!!\n");

            jTxtArTulos.append(tulos);

        } else if (koneSumma == 21 && pelaajaSumma < 21) {
            tulos = ("Kone voitti!!\n");

            jTxtArTulos.append(tulos);

        } else if (koneSumma < 21 && pelaajaSumma == 21) {
            tulos = ("Pelaaja voitti!!\n");

            jTxtArTulos.append(tulos);

        } else if (koneSumma == 21 && pelaajaSumma == 21) {
            tulos = ("Tasapeli\n");

            jTxtArTulos.append(tulos);

            if (pelaajaSumma == 21) {
                tulos = ("Pelaaja voitti!!\n");

                jTxtArTulos.append(tulos);

            }

            if (koneSumma == 21) {
                tulos = ("Kone voitti!!\n");

                jTxtArTulos.append(tulos);

            } else {
                tulos = (" ");
                jTxtArTulos.append(tulos);

            }
        }

    }//GEN-LAST:event_jBtnHeitaActionPerformed

    private void jTxtfPelaajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtfPelaajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtfPelaajaActionPerformed

    private void jTxtfHeittoPelaajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtfHeittoPelaajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtfHeittoPelaajaActionPerformed

    private void jBtnLopetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLopetaActionPerformed
        // TODO add your handling code here:
        Noppa kone1 = new Noppa(6);
        Noppa kone2 = new Noppa(6);
        int pelaajatulos = Integer.parseInt(jTxtfPelaaja.getText());
        int konetulos = Integer.parseInt(jTxtfKone.getText());
        int koneSumma = Integer.parseInt(jTxtfHeittoKone.getText());
        int pelaajaSumma = Integer.parseInt(jTxtfHeittoPelaaja.getText());
        String tulos;

        if ((koneSumma < pelaajaSumma) && (pelaajaSumma < 21)) {
            kone1.heita();
            kone2.heita();
            koneSumma += kone1.getArvo() + kone2.getArvo();

        }

        String appendText = ("Koneella on " + koneSumma + " pistettä " + "\n" + "Pelaajalla on " + pelaajaSumma + " pistettä" + "\n" + "\n");
        jTxtArTulos.append(appendText);
        jTxtfHeittoPelaaja.setText(String.valueOf(pelaajaSumma));
        jTxtfHeittoKone.setText(String.valueOf(koneSumma));

        if ((koneSumma > 21) && (pelaajaSumma > 21)) {
            tulos = ("Molemmat hävisivät.\n");
            jTxtArTulos.append(tulos);
            pelaajaSumma = 0;
            koneSumma = 0;
            jTxtfHeittoKone.setText(String.valueOf(koneSumma));
            jTxtfHeittoPelaaja.setText(String.valueOf(pelaajaSumma));

        } else if ((koneSumma > 21) && (pelaajaSumma <= 21)) {
            tulos = ("Pelaaja voitti!!\n");
            pelaajatulos += 1;
            pelaajaSumma = 0;
            koneSumma = 0;
            jTxtfHeittoPelaaja.setText(String.valueOf(pelaajaSumma));
            jTxtfHeittoKone.setText(String.valueOf(koneSumma));
            jTxtArTulos.append(tulos);
            jTxtfPelaaja.setText(String.valueOf(pelaajatulos));

        } else if ((koneSumma <= 21) && (pelaajaSumma > 21)) {
            tulos = ("Kone voitti!!\n");
            konetulos += 1;
            pelaajaSumma = 0;
            koneSumma = 0;
            jTxtfHeittoKone.setText(String.valueOf(koneSumma));
            jTxtfHeittoPelaaja.setText(String.valueOf(pelaajaSumma));
            jTxtArTulos.append(tulos);
            jTxtfKone.setText(String.valueOf(konetulos));

        } else if ((koneSumma <= 21) && (pelaajaSumma < koneSumma)) {
            tulos = ("Kone voitti!!\n");
            konetulos += 1;
            pelaajaSumma = 0;
            koneSumma = 0;
            jTxtfHeittoPelaaja.setText(String.valueOf(pelaajaSumma));
            jTxtfHeittoKone.setText(String.valueOf(koneSumma));
            jTxtArTulos.append(tulos);
            jTxtfKone.setText(String.valueOf(konetulos));

        } else if (koneSumma == pelaajaSumma && pelaajaSumma < 21) {
            tulos = ("Tasapeli\n");

            pelaajaSumma = 0;
            koneSumma = 0;
            jTxtfHeittoKone.setText(String.valueOf(koneSumma));
            jTxtfHeittoPelaaja.setText(String.valueOf(pelaajaSumma));
            jTxtArTulos.append(tulos);

        } else if (pelaajaSumma == 21) {
            tulos = ("Pelaaja voitti!!\n");

            pelaajatulos += 1;
            pelaajaSumma = 0;
            koneSumma = 0;
            jTxtfHeittoKone.setText(String.valueOf(koneSumma));
            jTxtfHeittoPelaaja.setText(String.valueOf(pelaajaSumma));
            jTxtArTulos.append(tulos);
            jTxtfPelaaja.setText(String.valueOf(pelaajatulos));

        } else if (koneSumma == 21) {
            tulos = ("Kone voitti!!\n");
            konetulos += 1;
            pelaajaSumma = 0;
            koneSumma = 0;
            jTxtfHeittoKone.setText(String.valueOf(koneSumma));
            jTxtfHeittoPelaaja.setText(String.valueOf(pelaajaSumma));
            jTxtArTulos.append(tulos);
            jTxtfKone.setText(String.valueOf(konetulos));

        } else {
            tulos = (" ");
            jTxtArTulos.append(tulos);

        }


    }//GEN-LAST:event_jBtnLopetaActionPerformed

    private void jBtnHeitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHeitaMouseClicked
        // TODO add your handling code here:
        jLblNopat.setVisible(true);
         jLblNopat.setIcon(new ImageIcon("roll-dice.gif"));
    }//GEN-LAST:event_jBtnHeitaMouseClicked

    private void jBtnLopetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLopetaMouseClicked
        // TODO add your handling code here:
        jLblNopat.setVisible(false);
    }//GEN-LAST:event_jBtnLopetaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(T16Noppapeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(T16Noppapeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(T16Noppapeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(T16Noppapeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new T16Noppapeli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnHeita;
    private javax.swing.JButton jBtnLopeta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLblKone;
    private javax.swing.JLabel jLblNopat;
    private javax.swing.JLabel jLblPelaaja;
    private javax.swing.JLabel jLblTulos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTxtArTulos;
    private javax.swing.JTextField jTxtfHeittoKone;
    private javax.swing.JTextField jTxtfHeittoPelaaja;
    private javax.swing.JTextField jTxtfKone;
    private javax.swing.JTextField jTxtfPelaaja;
    // End of variables declaration//GEN-END:variables
}