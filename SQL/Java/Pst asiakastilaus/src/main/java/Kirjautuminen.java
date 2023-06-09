
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
public class Kirjautuminen extends javax.swing.JFrame {

    /**
     * Creates new form Kirjautuminen
     */
    public Kirjautuminen() {
        initComponents();
    }
    public Connection luoYhteys()
    {
        Connection cn=null;
    try {
        cn = DriverManager.
                getConnection("jdbc:mariadb://" + "localhost/" + "ASIAKASTILAUS" + "?socketTimeout=2000", "kehittaja", "Koira123!");
        return cn;
    } catch (SQLException e) {
        System.out.println("Yhteyden luominen epäonnistui!:\n" +e.getMessage());
        e.printStackTrace();
            return null;
    }
   }
    public void sulje() {
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtTunnus = new javax.swing.JTextField();
        jbtnKirjaudu = new javax.swing.JButton();
        jchkSalasana = new javax.swing.JCheckBox();
        jbtnTestaa = new javax.swing.JButton();
        jpswSalasana = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tervetuloa Asiakastilaus-tietojärjestelmään.");

        jLabel2.setText("Tunnus:");

        jLabel3.setText("Salasana:");

        jbtnKirjaudu.setText("Kirjaudu");
        jbtnKirjaudu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnKirjauduActionPerformed(evt);
            }
        });

        jchkSalasana.setText("Näytä salasana");
        jchkSalasana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchkSalasanaActionPerformed(evt);
            }
        });

        jbtnTestaa.setText("Testaa yhteys");
        jbtnTestaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTestaaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jbtnKirjaudu))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtxtTunnus, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                    .addComponent(jpswSalasana))
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtnTestaa)
                                    .addComponent(jchkSalasana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtTunnus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jchkSalasana)
                    .addComponent(jpswSalasana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jbtnKirjaudu)
                .addGap(62, 62, 62)
                .addComponent(jbtnTestaa)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnKirjauduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnKirjauduActionPerformed
        // TODO add your handling code here:
        //Käyttöliittymästä haettu tunnus ja salasana
        String tunnus = jtxtTunnus.getText();
        String oikeasalasanatietokannassa="";
        //Miksi salasanojen käsittely Char taulukkoina eikä String-muuttujina?
        char annettusalasana[]= jpswSalasana.getPassword();
        char [] oikeasalasanataulukkona=null;
        //Muodostetaan tietokantaan yhteys
        Connection cn = luoYhteys();
        //Kahdessa seuraavassa rivissä piilee heikkoja lenkkejä, mitä ja missä kohti?
        String sqlKysely = "SELECT TUNNUS, DES_DECRYPT(SALASANA,'salainenavain') AS SALASANA FROM KAYTTAJA WHERE TUNNUS='"+tunnus+"'";
        // Tätä seuraavaa riviä ei saa näyttää kenttänä ohjelmassa asiakkaalle / ulkopuoliselle taholle.
        //jtxtSQL.setText(sqlKysely);
        try {
            PreparedStatement stm = cn.prepareStatement(sqlKysely);
            ResultSet tulos = stm.executeQuery();
            //Jos tulos.next, tunnus annettu oikein
            if(tulos.next()) {
                //Selvitetään ja heataan resultset-objektista salasana ja trimmataan lopuksi
                oikeasalasanatietokannassa = tulos.getString("SALASANA").trim();
                //Muunnetaan string muotoinen salasana taulukoksi
                oikeasalasanataulukkona = oikeasalasanatietokannassa.toCharArray();
                //Jos tallennettu salasana ja annettu salasana täsmää...
                if(Arrays.equals(annettusalasana, oikeasalasanataulukkona)) {
                    //Välitetään käyttäjätunnus GUI-luokan muodostimelle ja tuodaan lomake esille
                    GUI g = new GUI(tunnus);
                        g.setVisible(true);
                        //Suljetaan kirjautumislomake
                        sulje();
                } else {
                    // Miksi ei pidä kertoa kumpi on ollut väärin: tunnus vai salasana?
                    JOptionPane.showMessageDialog(this,"Tunnus tai salasana on väärin. Yritä uudelleen.");
                    jtxtTunnus.requestFocus();
                    return;
                }
                }
            } catch (SQLException ex) {
            Logger.getLogger(Kirjautuminen.class.getName()).log(Level.SEVERE,null, ex);           
        }
        //Tyhjennetään tietoturvasyistä salasanojen käyttöön liittyvät muuttujat täyttämällä taulukko nollamerkeillä ja ""
        Arrays.fill(oikeasalasanataulukkona, '0');
        oikeasalasanatietokannassa="";
    }//GEN-LAST:event_jbtnKirjauduActionPerformed

    private void jchkSalasanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchkSalasanaActionPerformed
        // TODO add your handling code here:
        // Salasanan näkyminen / piilottaminen
        if(jchkSalasana.isSelected()) {
            jpswSalasana.setEchoChar((char)0);
            jchkSalasana.setSelected(true);
        } else {
            jpswSalasana.setEchoChar('+');
            jchkSalasana.setSelected(false);
        }
    }//GEN-LAST:event_jchkSalasanaActionPerformed

    private void jbtnTestaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTestaaActionPerformed
        // TODO add your handling code here:
        
        // Yhteyden testaaminen
        try { 
            Class.forName("org.mariadb.jdbc.Driver"); 
        } catch (ClassNotFoundException e) { 
            System.out.println("Missä on MariaDB JDBC ajuri? Oletko ladannut mariadb connectorin osoitteesta:https://mariadb.com/downloads/#connectors ja lisännyt sen Netbeansissä Asiakasrekisteri-Libraries-Add JAR/Folder kohdassa? "); 
        e.printStackTrace(); 
        return; 
        } 
        System.out.println("Mariadb JDBC Driver rekisteröity!"); 
        Connection connection = null; 
        
        try { 
            connection = DriverManager. 
                    getConnection("jdbc:mariadb://" + "localhost/" +
                            "ASIAKASTILAUS", "kehittaja", "Koira123!"); 
        } catch (SQLException e) { 
        System.out.println("Yhteyden luominen epäonnistui!:\n" + e.getMessage());
        }
        
        if (connection != null) { 
            System.out.println("Hienoa ja onnittelut! Sait luotua yhteyden tietokantaasi. Voit aloittaa käyttöliittymän koodaamisen!"); 
        } else { 
        System.out.println("Pahus, tarkista vielä, että kaikki tarvittava on tehty ja virheitä ei ole!"); 
        } 
         
    }//GEN-LAST:event_jbtnTestaaActionPerformed

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
            java.util.logging.Logger.getLogger(Kirjautuminen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kirjautuminen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kirjautuminen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kirjautuminen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kirjautuminen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbtnKirjaudu;
    private javax.swing.JButton jbtnTestaa;
    private javax.swing.JCheckBox jchkSalasana;
    private javax.swing.JPasswordField jpswSalasana;
    private javax.swing.JTextField jtxtTunnus;
    // End of variables declaration//GEN-END:variables
}
