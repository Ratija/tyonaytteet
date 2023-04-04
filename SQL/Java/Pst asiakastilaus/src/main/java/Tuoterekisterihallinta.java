
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
public class Tuoterekisterihallinta extends javax.swing.JFrame {

    /**
     * Creates new form Tuoterekisterihallinta
     */
     class Tuote{
                                             // Tuote-luokan atribuutit
         
    private int TUOTENUMERO;
    private String TUOTENIMI;
    private String SELITE;
    private String AHINTA;
    
                                                // Luokan muodostin
    
    public Tuote(int id, String tuotenimi ,String selite ,String ahinta) {
        this.TUOTENUMERO = id;
        this.TUOTENIMI = tuotenimi;
        this.SELITE = selite;
        this.AHINTA = ahinta;
    }
    
    public int HaeTuotenumero() {
        return this.TUOTENUMERO;
    }
    public String HaeTuotenimi() {
        return this.TUOTENIMI;
    }
    public String HaeSelite() {
        return this.SELITE;
    }
    public String HaeAhinta() {
        return this.AHINTA;
    }
    }
     
     
   
                                            // Tietokantayhteyden luomisen metodi
    public Connection luoYhteys() {
        Connection cn=null;
        
                                          //  palvelin osoite, tietokannan nimi ja kayttäjätunnus & salasana
    try {
        cn = DriverManager.
                getConnection("jdbc:mariadb://" + "localhost/" +"ASIAKASTILAUS", "kehittaja", "Koira123!");
        return cn;
    } catch (SQLException e) {
        System.out.println("Yhteyden luominen epäonnistui!:\n" + e.getMessage());
        e.printStackTrace();
            return null;
    }
        
       
    }
     
                                            // Metodi, joka täyttää Tuote-arraylist objektin
    public ArrayList<Tuote>HaeTuotetaulukko()
    {
    ArrayList<Tuote> Tuotetaulukko = new ArrayList<Tuote>();
    
        Connection yhteys = luoYhteys();
                                            // SQL kysely, jolla haetaan tarvittavat kentät
        String query = "SELECT TUOTENUMERO, TUOTENIMI, SELITE, AHINTA FROM TUOTE";
        Statement st;
        ResultSet rs;
        
        try {
            st = yhteys.createStatement();
            rs = st.executeQuery(query);
            
            Tuote a;
                                            //Lisätään kaikki asiakkaat taulukkoon
            while(rs.next())
            {
                
                a = new Tuote(rs.getInt("TUOTENUMERO"),rs.getString("TUOTENIMI"),rs.getString("SELITE"),rs.getString("AHINTA"));
                    Tuotetaulukko.add(a);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
            return Tuotetaulukko;
    }
            
    public void Naytatuotteet()
    {
        ArrayList<Tuote> list = HaeTuotetaulukko();
        DefaultTableModel model = (DefaultTableModel)jtblTuotteet.getModel();
        
                                            // Luodaan sarakeotsikot
        model.setColumnIdentifiers(new Object[]{"TUOTENUMERO", "TUOTENIMI", "SELITE" ,"AHINTA"});
        Object[] row = new Object[4];
        
                                            // Putsataan taulukko ennen täyttämistä
        for (int i = jtblTuotteet.getRowCount() -1;i >= 0; i--) {
            model.removeRow(i);
        }
                                            // Täytetään taulukko uudella datalla
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).HaeTuotenumero();
            row[1] = list.get(i).HaeTuotenimi();
            row[2] = list.get(i).HaeSelite();
            row[3] = list.get(i).HaeAhinta();
            model.addRow(row);
        }
    }
                                            // SQL-Kyselyt
    public void suoritaSQLKysely(String query, String message)
    {
        Connection yhteys = luoYhteys();
        Statement st;
        try {
            st = yhteys.createStatement();
            if((st.executeUpdate(query)) == 1)
            {
                                            //päivitetään jtbTuotteet taulukko
                DefaultTableModel model = (DefaultTableModel)jtblTuotteet.getModel();
                model.setRowCount(0);
                Naytatuotteet();
                
                JOptionPane.showMessageDialog(null, "Data "+message+" onnistuneesti");
            } else {
                JOptionPane.showMessageDialog(null, "Data ei "+message);
            }
                                            // Suljetaan tietokantayhteys
            yhteys.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }                                       // Tyhjentää tekstikentät
    public void tyhjenna() {
    jtxtTuotenumero.setText("");
                                                // Estetään Tuotenumeron muokkaaminen
        jtxtTuotenumero.setEnabled(false);
        
        jtxtTuotenimi.setText("");
        jtxtSelite.setText("");
        jtxtAhinta.setText("");
    }
            
    public Tuoterekisterihallinta() {
        initComponents();
        Naytatuotteet();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblTuoterekisteri = new javax.swing.JLabel();
        jlblTuotenumero = new javax.swing.JLabel();
        jlblSelite = new javax.swing.JLabel();
        jlblAhinta = new javax.swing.JLabel();
        jtxtTuotenumero = new javax.swing.JTextField();
        jtxtSelite = new javax.swing.JTextField();
        jtxtAhinta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblTuotteet = new javax.swing.JTable();
        jbtnUusi = new javax.swing.JButton();
        jbtnPaivita = new javax.swing.JButton();
        jbtnPoista = new javax.swing.JButton();
        jlblTuotenimi = new javax.swing.JLabel();
        jtxtTuotenimi = new javax.swing.JTextField();
        jlblPakollisetKentat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlblTuoterekisteri.setText("Tuoterekisteri");

        jlblTuotenumero.setText("Tuotenumero:");

        jlblSelite.setText("Selite: *");

        jlblAhinta.setText("A-hinta: *");

        jtxtTuotenumero.setEditable(false);
        jtxtTuotenumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtTuotenumeroActionPerformed(evt);
            }
        });

        jtxtSelite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtSeliteActionPerformed(evt);
            }
        });
        jtxtSelite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtSeliteKeyPressed(evt);
            }
        });

        jtxtAhinta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtAhintaKeyPressed(evt);
            }
        });

        jtblTuotteet.setAutoCreateRowSorter(true);
        jtblTuotteet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        jtblTuotteet.setShowGrid(true);
        jtblTuotteet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblTuotteetMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblTuotteet);

        jbtnUusi.setText("Uusi");
        jbtnUusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUusiActionPerformed(evt);
            }
        });

        jbtnPaivita.setText("Päivitä");
        jbtnPaivita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPaivitaActionPerformed(evt);
            }
        });

        jbtnPoista.setText("Poista");
        jbtnPoista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPoistaActionPerformed(evt);
            }
        });

        jlblTuotenimi.setText("Tuotenimi: *");

        jtxtTuotenimi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtTuotenimiKeyPressed(evt);
            }
        });

        jlblPakollisetKentat.setText("Pakolliset kentät *");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jlblTuoterekisteri, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlblSelite)
                                    .addComponent(jlblTuotenumero, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                    .addComponent(jlblAhinta, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
                            .addComponent(jlblTuotenimi, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtTuotenumero, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtSelite, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(jtxtAhinta, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(jtxtTuotenimi)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtnUusi)
                                .addGap(21, 21, 21)
                                .addComponent(jbtnPaivita)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnPoista))
                            .addComponent(jlblPakollisetKentat, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblTuoterekisteri)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtTuotenumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblTuotenumero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblTuotenimi)
                            .addComponent(jtxtTuotenimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlblSelite)
                            .addComponent(jtxtSelite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblAhinta)
                            .addComponent(jtxtAhinta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jlblPakollisetKentat)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnUusi)
                            .addComponent(jbtnPaivita)
                            .addComponent(jbtnPoista))))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtTuotenumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtTuotenumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtTuotenumeroActionPerformed

    private void jtblTuotteetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTuotteetMouseClicked
        // TODO add your handling code here:
                                                // Haetaan käyttöliittymästä klikattu rivinumero
        int i = jtblTuotteet.getSelectedRow();
        TableModel model = jtblTuotteet.getModel();
        
                                                //Asetetaan käyttöliittymään tiedot
        jtxtTuotenumero.setText((model.getValueAt(i,0).toString()));
                                                // Estetään Tuotenumeron muokkaaminen
        jtxtTuotenumero.setEnabled(false);
        
        jtxtTuotenimi.setText((model.getValueAt(i,1).toString()));
        jtxtSelite.setText((model.getValueAt(i,2).toString()));
        jtxtAhinta.setText((model.getValueAt(i,3).toString()));
       
        
        
        
        
    }//GEN-LAST:event_jtblTuotteetMouseClicked

    private void jbtnUusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUusiActionPerformed
        // TODO add your handling code here:
              
                                                // Kenttien tarkistus tyhjien varalta
        if (jtxtSelite.getText().isEmpty()||jtxtAhinta.getText().isEmpty() ||jtxtTuotenimi.getText().isEmpty()) {
                                                                                                                                          
        JOptionPane.showMessageDialog(null,"Tarkista pakolliset kentät!");
        return;
        
        } else {
                                                // Uuden lisääminen varmistus-dialogi  
        int response = JOptionPane.showConfirmDialog(this, "Haluatko lisätä uuden tuotteen?", "Uusi tuote", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            switch (response) {
                case JOptionPane.YES_OPTION:
                    break;
                case JOptionPane.NO_OPTION:
                    return; 
        }
        }
                                                // Muodostetaan INSERT lause, huomaa + ja ' merkkien käyttäminen
                                                
        String query = "INSERT INTO `tuote` (`TUOTENIMI`, `SELITE`, `AHINTA`)";
        query = query + " VALUES('" +jtxtTuotenimi.getText()+"','"+jtxtSelite.getText()+"','"+jtxtAhinta.getText()+"')";
        
        suoritaSQLKysely(query, "lisätty");
        
        tyhjenna();
               
    }//GEN-LAST:event_jbtnUusiActionPerformed

    private void jbtnPaivitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPaivitaActionPerformed
        // TODO add your handling code here:
   
                                                // Kenttien varmistus tyhjien varalta
         if (jtxtTuotenimi.getText().isEmpty()||jtxtSelite.getText().isEmpty()||jtxtAhinta.getText().isEmpty()) {
                                                                                                                                          
        JOptionPane.showMessageDialog(null,"Tarkista pakolliset kentät!");
        return;
        
        } else {
                                                 // Päivittämisen varmistus-dialogi
        
        int response = JOptionPane.showConfirmDialog(this, "Haluatko päivittää tuotteen tiedot?", "Tuotteen päivitys", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            switch (response) {
                case JOptionPane.YES_OPTION:
                    break;
                case JOptionPane.NO_OPTION:
                    return; 
        }
        }
        String query = "UPDATE TUOTE SET  TUOTENIMI='"+jtxtTuotenimi.getText()+"', SELITE='"+jtxtSelite.getText()+"', AHINTA='"+jtxtAhinta.getText()+"' WHERE TUOTENUMERO = "+jtxtTuotenumero.getText(); 
               
        JOptionPane.showMessageDialog(null, query);
        //jtxtSQL.setText(query);
        suoritaSQLKysely(query, "Päivitetty");
        
        tyhjenna();
        
    }//GEN-LAST:event_jbtnPaivitaActionPerformed

    private void jbtnPoistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPoistaActionPerformed
        // TODO add your handling code here:
                                                // Poistamisen varmistus-dialogi
        int response = JOptionPane.showConfirmDialog(this, "Haluatko poistaa tuotteen?", "Tuotteen poisto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            switch (response) {
                case JOptionPane.YES_OPTION:
                    break;
                case JOptionPane.NO_OPTION:
                    return; 
        }
            
        String query = "DELETE FROM TUOTE WHERE TUOTENUMERO="+jtxtTuotenumero.getText();
        suoritaSQLKysely(query, "Poistettu");
           
        tyhjenna(); 
    }//GEN-LAST:event_jbtnPoistaActionPerformed

    private void jtxtSeliteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtSeliteActionPerformed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jtxtSeliteActionPerformed

    private void jtxtSeliteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSeliteKeyPressed
        // TODO add your handling code here:
                                                // Sallittujen merkkien asettaminen
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c)|| Character.isDigit(c) || Character.isWhitespace(c) || c==KeyEvent.VK_MINUS || Character.isISOControl(c)) {
            // Iso-kontrolli delete/backspace nappuloille
            // Sallitaan  kirjaimet, numerot, välilyönti, väliviiva ja em iso-kontrollit
            jtxtSelite.setEditable(true);
        } else
        {
            jtxtSelite.setEditable(false);
        }
        
    }//GEN-LAST:event_jtxtSeliteKeyPressed

    private void jtxtAhintaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtAhintaKeyPressed
        // TODO add your handling code here:
                                                // Sallittujen merkkien asettaminen
        
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c) || Character.isISOControl(c)) {
            // Iso-kontrolli delete/backspace nappuloille
            // Sallitaan  numerot, välilyönti, ja em iso-kontrollit
            jtxtAhinta.setEditable(true);
        } else
        {
            jtxtAhinta.setEditable(false);
        }
    }//GEN-LAST:event_jtxtAhintaKeyPressed

    private void jtxtTuotenimiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtTuotenimiKeyPressed
        // TODO add your handling code here:
                                                 // Sallittujen merkkien asettaminen
        
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c)||Character.isWhitespace(c)|| c==KeyEvent.VK_MINUS || Character.isISOControl(c)) {
            // Iso-kontrolli delete/backspace nappuloille
            // Sallitaan  kirjaimet, välilyönti, väliviiva ja em iso-kontrollit
            jtxtTuotenimi.setEditable(true);
        } else
        {
            jtxtTuotenimi.setEditable(false);
        }
    }//GEN-LAST:event_jtxtTuotenimiKeyPressed

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
            java.util.logging.Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asiakasrekisterihallinta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnPaivita;
    private javax.swing.JButton jbtnPoista;
    private javax.swing.JButton jbtnUusi;
    private javax.swing.JLabel jlblAhinta;
    private javax.swing.JLabel jlblPakollisetKentat;
    private javax.swing.JLabel jlblSelite;
    private javax.swing.JLabel jlblTuotenimi;
    private javax.swing.JLabel jlblTuotenumero;
    private javax.swing.JLabel jlblTuoterekisteri;
    private javax.swing.JTable jtblTuotteet;
    private javax.swing.JTextField jtxtAhinta;
    private javax.swing.JTextField jtxtSelite;
    private javax.swing.JTextField jtxtTuotenimi;
    private javax.swing.JTextField jtxtTuotenumero;
    // End of variables declaration//GEN-END:variables
}
