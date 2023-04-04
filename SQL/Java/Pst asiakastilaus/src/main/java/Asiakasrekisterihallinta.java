
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel; 
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*; 
import java.util.logging.Level; 
import java.util.logging.Logger;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
public class Asiakasrekisterihallinta extends javax.swing.JFrame {

    /**
     * Creates new form Asiakasrekisterihallinta
     */
     class Asiakas{
                                             // Asiakas-luokan atribuutit
         
    private int ASIAKASNUMERO;
    private String ASIAKKAAKSITULOPAIVA;
    private String YRITYS;
    private String ETUNIMI;
    private String SUKUNIMI;
    private String KATUOSOITE;
    private String POSTINUMERO;
    private String POSTITOIMIPAIKKA;
    private String PUHELIN;
    private String EMAIL;
  
                                                // Luokan muodostin
    
    public Asiakas(int id, String asiakkaaksitulopaiva ,String yritys ,String etunimi, String sukunimi, String katuosoite, String postinumero, String postitoimipaikka, String puhelin, String email) {
        this.ASIAKASNUMERO = id;
        this.ASIAKKAAKSITULOPAIVA = asiakkaaksitulopaiva;
        this.YRITYS = yritys;
        this.ETUNIMI = etunimi;
        this.SUKUNIMI = sukunimi;
        this.KATUOSOITE = katuosoite;
        this.POSTINUMERO = postinumero;
        this.POSTITOIMIPAIKKA = postitoimipaikka;
        this.PUHELIN = puhelin;
        this.EMAIL = email;
        
        
    }
    
    public int HaeAsiakasnumero() {
        return this.ASIAKASNUMERO;
    }
    public String HaeAsiakkaaksitulopaiva() {
        return this.ASIAKKAAKSITULOPAIVA;
    }
    public String HaeYritys() {
        return this.YRITYS;
    }
    public String HaeEtunimi() {
        return this.ETUNIMI;
    }
    public String HaeSukunimi() {
        return this.SUKUNIMI;
    }
    
    public String HaeKatuosoite() {
        return this.KATUOSOITE;
    }
    public String HaePostinumero() {
        return this.POSTINUMERO;
    }
    public String HaePostitoimipaikka() {
        return this.POSTITOIMIPAIKKA;
    }
    public String HaePuhelin() {
        return this.PUHELIN;
    }
     public String HaeEmail() {
        return this.EMAIL;
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
     
                                            // Metodi, joka täyttää Asiakas-arraylist objektin
    public ArrayList<Asiakas>HaeAsiakastaulukko()
    {
    ArrayList<Asiakas> Asiakastaulukko = new ArrayList<Asiakas>();
    
        Connection yhteys = luoYhteys();
                                            // SQL kysely, jolla haetaan tarvittavat kentät
        String query = "SELECT ASIAKASNUMERO, ASIAKKAAKSITULOPAIVA, YRITYS, ETUNIMI, SUKUNIMI, KATUOSOITE, POSTINUMERO, POSTITOIMIPAIKKA, PUHELIN, EMAIL FROM ASIAKAS";
        Statement st;
        ResultSet rs;
        
        try {
            st = yhteys.createStatement();
            rs = st.executeQuery(query);
            
            Asiakas a;
                                            //Lisätään kaikki asiakkaat taulukkoon
            while(rs.next())
            {
                
                a = new Asiakas(rs.getInt("ASIAKASNUMERO"),rs.getString("ASIAKKAAKSITULOPAIVA"),rs.getString("YRITYS"),rs.getString("ETUNIMI"),rs.getString("SUKUNIMI"),rs.getString("KATUOSOITE"),rs.getString("POSTINUMERO"),rs.getString("POSTITOIMIPAIKKA"),rs.getString("PUHELIN"),rs.getString("EMAIL"));
                    Asiakastaulukko.add(a);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
            return Asiakastaulukko;
    }
            
    public void Naytaasiakkaat()
    {
        ArrayList<Asiakas> list = HaeAsiakastaulukko();
        DefaultTableModel model = (DefaultTableModel)jtblAsiakkaat.getModel();
                                            // Luodaan sarakeotsikot
        
        model.setColumnIdentifiers(new Object[]{"ASIAKASNUMERO", "ASIAKKAAKSITULOPAIVA", "YRITYS" ,"ETUNIMI", "SUKUNIMI", "KATUOSOITE", "POSTINUMERO", "POSTITOIMIPAIKKA", "PUHELIN", "EMAIL"});
        Object[] row = new Object[10];
        
                                            // Putsataan taulukko ennen täyttämistä
        for (int i = jtblAsiakkaat.getRowCount() -1;i >= 0; i--) {
            model.removeRow(i);
        }
                                            // Täytetään taulukko uudella datalla
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).HaeAsiakasnumero();
            row[1] = list.get(i).HaeAsiakkaaksitulopaiva();
            row[2] = list.get(i).HaeYritys();
            row[3] = list.get(i).HaeEtunimi();
            row[4] = list.get(i).HaeSukunimi();
            row[5] = list.get(i).HaeKatuosoite();
            row[6] = list.get(i).HaePostinumero();
            row[7] = list.get(i).HaePostitoimipaikka();
            row[8] = list.get(i).HaePuhelin();
            row[9] = list.get(i).HaeEmail();
            
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
                                            //päivitetään jtblAsiakkaat taulukko
                DefaultTableModel model = (DefaultTableModel)jtblAsiakkaat.getModel();
                model.setRowCount(0);
                Naytaasiakkaat();
                
                JOptionPane.showMessageDialog(null, "Data "+message+" onnistuneesti");
            } else {
                JOptionPane.showMessageDialog(null, "Data ei "+message);
            }
                                            // Suljetaan tietokantayhteys
            yhteys.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
                                        // Tekstikenttien tyhjentämisen metodi
    public void tyhjenna() {
     
    jtxtAsiakasnumero.setText("");
                                            // Estetään Asiakasnumeron muokkaaminen
    jtxtAsiakasnumero.setEnabled(false);
        
    jtxtAsiakkaaksitulopaiva.setText("");
                                            // Estetään asiakkaaksitulopäivän muuttaminen
    jtxtAsiakkaaksitulopaiva.setEnabled(false);
        
    jtxtYritys.setText("");
    jtxtEtunimi.setText("");
    jtxtSukunimi.setText("");
    jtxtKatuosoite.setText("");
    jtxtPostinumero.setText("");
    jtxtPostitoimipaikka.setText("");
    jtxtPuhelin.setText("");
    jtxtEmail.setText("");
    } 
            
    public Asiakasrekisterihallinta() {
        initComponents();
        Naytaasiakkaat();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblAsiakasrekisteri = new javax.swing.JLabel();
        jlblAsiakasnumero = new javax.swing.JLabel();
        jlblEtunimi = new javax.swing.JLabel();
        jlblSukunimi = new javax.swing.JLabel();
        jlblYritys = new javax.swing.JLabel();
        jtxtAsiakasnumero = new javax.swing.JTextField();
        jtxtEtunimi = new javax.swing.JTextField();
        jtxtSukunimi = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblAsiakkaat = new javax.swing.JTable();
        jbtnUusi = new javax.swing.JButton();
        jbtnPaivita = new javax.swing.JButton();
        jbtnPoista = new javax.swing.JButton();
        jlblKatuosoite = new javax.swing.JLabel();
        jlblPostinumero = new javax.swing.JLabel();
        jlblPostitoimipaikka = new javax.swing.JLabel();
        jlblPuhelin = new javax.swing.JLabel();
        jlblEmail = new javax.swing.JLabel();
        jtxtKatuosoite = new javax.swing.JTextField();
        jtxtPostinumero = new javax.swing.JTextField();
        jtxtPostitoimipaikka = new javax.swing.JTextField();
        jtxtPuhelin = new javax.swing.JTextField();
        jtxtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtxtAsiakkaaksitulopaiva = new javax.swing.JTextField();
        jtxtYritys = new javax.swing.JTextField();
        jlblPakollisetKentat = new javax.swing.JLabel();
        jtxtSQL = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlblAsiakasrekisteri.setText("Asiakasrekisteri");

        jlblAsiakasnumero.setText("Asiakasnumero:");

        jlblEtunimi.setText("Etunimi: *");

        jlblSukunimi.setText("Sukunimi: *");

        jlblYritys.setText("Yritys: *");

        jtxtAsiakasnumero.setEditable(false);
        jtxtAsiakasnumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAsiakasnumeroActionPerformed(evt);
            }
        });

        jtxtEtunimi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtEtunimiActionPerformed(evt);
            }
        });
        jtxtEtunimi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtEtunimiKeyPressed(evt);
            }
        });

        jtxtSukunimi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtSukunimiKeyPressed(evt);
            }
        });

        jtblAsiakkaat.setAutoCreateRowSorter(true);
        jtblAsiakkaat.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblAsiakkaat.setShowGrid(true);
        jtblAsiakkaat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblAsiakkaatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblAsiakkaat);

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

        jlblKatuosoite.setText("Katuosoite: *");

        jlblPostinumero.setText("Postinumero: *");

        jlblPostitoimipaikka.setText("Postitoimipaikka: *");

        jlblPuhelin.setText("Puhelin: *");

        jlblEmail.setText("Email: *");

        jtxtPostinumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtPostinumeroKeyPressed(evt);
            }
        });

        jtxtPostitoimipaikka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtPostitoimipaikkaKeyPressed(evt);
            }
        });

        jtxtPuhelin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtPuhelinKeyPressed(evt);
            }
        });

        jLabel1.setText("    Asiakkaaksitulopäivä: ");

        jtxtAsiakkaaksitulopaiva.setEditable(false);

        jlblPakollisetKentat.setText("Pakolliset kentät *");

        jtxtSQL.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jlblAsiakasrekisteri, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlblEtunimi)
                            .addComponent(jlblAsiakasnumero, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(jlblKatuosoite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblPostinumero, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblPuhelin, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblYritys, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblSukunimi, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbtnUusi)
                            .addComponent(jlblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jbtnPaivita))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblPostitoimipaikka, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblPakollisetKentat, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtAsiakasnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtEtunimi, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(jtxtSukunimi, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jbtnPoista))
                            .addComponent(jtxtKatuosoite)
                            .addComponent(jtxtPostinumero)
                            .addComponent(jtxtPostitoimipaikka)
                            .addComponent(jtxtPuhelin)
                            .addComponent(jtxtEmail)
                            .addComponent(jtxtAsiakkaaksitulopaiva)
                            .addComponent(jtxtYritys))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtxtSQL, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblAsiakasrekisteri)
                    .addComponent(jtxtSQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtAsiakasnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblAsiakasnumero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtxtAsiakkaaksitulopaiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlblEtunimi)
                            .addComponent(jtxtEtunimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblSukunimi)
                            .addComponent(jtxtSukunimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblYritys)
                            .addComponent(jtxtYritys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblKatuosoite)
                            .addComponent(jtxtKatuosoite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblPostinumero)
                            .addComponent(jtxtPostinumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblPostitoimipaikka)
                            .addComponent(jtxtPostitoimipaikka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblPuhelin)
                            .addComponent(jtxtPuhelin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblEmail)
                            .addComponent(jtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(jlblPakollisetKentat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnUusi)
                            .addComponent(jbtnPaivita)
                            .addComponent(jbtnPoista))))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtAsiakasnumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAsiakasnumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAsiakasnumeroActionPerformed

    private void jtblAsiakkaatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblAsiakkaatMouseClicked
        // TODO add your handling code here:
                                                // Haetaan käyttöliittymästä klikattu rivinumero
        int i = jtblAsiakkaat.getSelectedRow();
        TableModel model = jtblAsiakkaat.getModel();
        
                                                //Asetetaan käyttöliittymään tiedot
        jtxtAsiakasnumero.setText((model.getValueAt(i,0).toString()));
        
                                                // Estetään Asiakasnumeron muokkaaminen
        jtxtAsiakasnumero.setEnabled(false);
        
        jtxtAsiakkaaksitulopaiva.setText((model.getValueAt(i,1).toString()));
        
                                                // Estetään asiakkaaksitulopäivän muuttaminen
        jtxtAsiakkaaksitulopaiva.setEnabled(false);
        
        jtxtYritys.setText((model.getValueAt(i,2).toString()));
        jtxtEtunimi.setText((model.getValueAt(i,3).toString()));
        jtxtSukunimi.setText((model.getValueAt(i,4).toString()));
        jtxtKatuosoite.setText((model.getValueAt(i,5).toString()));
        jtxtPostinumero.setText((model.getValueAt(i,6).toString()));
        jtxtPostitoimipaikka.setText((model.getValueAt(i,7).toString()));
        jtxtPuhelin.setText((model.getValueAt(i,8).toString()));
        jtxtEmail.setText((model.getValueAt(i,9).toString()));
        
        
        
        
    }//GEN-LAST:event_jtblAsiakkaatMouseClicked

    private void jbtnUusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUusiActionPerformed
        // TODO add your handling code here:
        
        /*                                        
        if (jtxtAsiakasnumero.getText() != null) {       // Jos asiakasnumerossa on jotain, tyhjentää kaikki kentät ettei voi tallentaa dublikaattia
            tyhjenna();
        }   
        */
                                                   // Kenttien tarkistus tyhjien varalta
        if (jtxtYritys.getText().isEmpty()||jtxtEtunimi.getText().isEmpty()||jtxtSukunimi.getText().isEmpty()||jtxtKatuosoite.getText().isEmpty()||jtxtPostinumero.getText().isEmpty()||jtxtPostitoimipaikka.getText().isEmpty()||jtxtPuhelin.getText().isEmpty()||jtxtEmail.getText().isEmpty()) {
                                                                                                                                          
        JOptionPane.showMessageDialog(null,"Tarkista pakolliset kentät!");
        return;
        
        } else {
        }
                                                // Uuden lisääminen varmistus-dialogi  
        int response = JOptionPane.showConfirmDialog(this, "Haluatko lisätä uuden asiakkaan?", "Uusi asiakas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            switch (response) {
                case JOptionPane.YES_OPTION:
                    break;
                case JOptionPane.NO_OPTION:
                    return; 
        }
        
        
        
                                                 // Muotoillaan päivämäärä muotoon yyyy-MM-dd
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
                                                // Haetaan järjestelmän päiväys
        //Date tamapaiva = new Date();
        /*
         // Muodostetaan INSERT lause, huomaa + ja ' merkkien käyttäminen
        String query = "INSERT INTO `asiakas` (`ASIAKKAAKSITULOPAIVA`, `YRITYS`, `ETUNIMI`, `SUKUNIMI`, `KATUOSOITE`, `POSTINUMERO`, `POSTITOIMIPAIKKA`, `PUHELIN`, `EMAIL`)";
        query = query + " VALUES('" + dateFormat.format(tamapaiva)+"','"+jtxtYritys.getText()+"','"+jtxtEtunimi.getText()+"','"+jtxtSukunimi.getText()+"','"+jtxtKatuosoite.getText() + "','"+jtxtPostinumero.getText()+"','"+jtxtPostitoimipaikka.getText()+"','"+jtxtPuhelin.getText()+"','"+jtxtEmail.getText()+"')";
        // JOptionPane.showMessageDialog(null, query);
        // luomisvaiheessa SQL merkkijonoa kannattaa testata ja kehittää phpMyAdmin-ohjelmassa, välituloksia, joita voi kopioida, kannattaa tulostaa sovelluksen käyttöliittymän tai Debug-ikkunaan
        //jtxtSQL.setText(query);
        suoritaSQLKysely(query, "lisätty");
        */
        
       try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date tamapaiva = new Date();
            Connection cn = luoYhteys();
            
            String query = "INSERT INTO ASIAKAS(ASIAKKAAKSITULOPAIVA, YRITYS, ETUNIMI, SUKUNIMI, KATUOSOITE, POSTINUMERO, POSTITOIMIPAIKKA, PUHELIN, EMAIL) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = cn.prepareStatement(query);
            preparedStatement.setString(1, dateFormat.format(tamapaiva));
            preparedStatement.setString(2, jtxtYritys.getText());
            preparedStatement.setString(3, jtxtEtunimi.getText());
            preparedStatement.setString(4, jtxtSukunimi.getText());
            preparedStatement.setString(5, jtxtKatuosoite.getText());
            preparedStatement.setString(6, jtxtPostinumero.getText());
            preparedStatement.setString(7, jtxtPostitoimipaikka.getText());
            preparedStatement.setString(8, jtxtPuhelin.getText());
            preparedStatement.setString(9, jtxtEmail.getText());
            
            jtxtSQL.setText(query);
            preparedStatement.executeUpdate();
            Naytaasiakkaat();
        } catch (SQLException ex) {
            Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(Level.SEVERE, null, ex);
        }
        tyhjenna();
        
    }//GEN-LAST:event_jbtnUusiActionPerformed

    private void jbtnPaivitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPaivitaActionPerformed
        // TODO add your handling code here:
   
                                                // Kenttien varmistus tyhjien varalta
         if (jtxtYritys.getText().isEmpty()||jtxtEtunimi.getText().isEmpty()||jtxtSukunimi.getText().isEmpty()||jtxtKatuosoite.getText().isEmpty()||jtxtPostinumero.getText().isEmpty()||jtxtPostitoimipaikka.getText().isEmpty()||jtxtPuhelin.getText().isEmpty()||jtxtEmail.getText().isEmpty()) {
                                                                                                                                          
        JOptionPane.showMessageDialog(null,"Tarkista pakolliset kentät!");
        return;
        
        } else {
                                                 // Päivittämisen varmistus-dialogi
        }
        int response = JOptionPane.showConfirmDialog(this, "Haluatko päivittää asiakkaan tiedot?", "Asiakkaan päivitys", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            switch (response) {
                case JOptionPane.YES_OPTION:
                    break;
                case JOptionPane.NO_OPTION:
                    return; 
        }
        // Tavallinen Statement-lauseke
       // String query = "UPDATE ASIAKAS SET  KATUOSOITE='"+jtxtKatuosoite.getText()+"', POSTINUMERO='"+jtxtPostinumero.getText()+"', POSTITOIMIPAIKKA='"+jtxtPostitoimipaikka.getText()+"', PUHELIN='"+jtxtPuhelin.getText()+"', EMAIL='"+jtxtEmail.getText()+"', ETUNIMI='"+jtxtEtunimi.getText()+"', SUKUNIMI='"+jtxtSukunimi.getText()+"', YRITYS='"+jtxtYritys.getText()+"' WHERE ASIAKASNUMERO = "+jtxtAsiakasnumero.getText(); 
               
       // JOptionPane.showMessageDialog(null, query);
        //jtxtSQL.setText(query);
        //suoritaSQLKysely(query, "Päivitetty");
        
        try {
            Connection cn = luoYhteys();
            
            String query = "UPDATE ASIAKAS SET ETUNIMI=?, SUKUNIMI=?, YRITYS=?, KATUOSOITE=?, POSTINUMERO=?, POSTITOIMIPAIKKA=?, PUHELIN=?, EMAIL=? WHERE ASIAKASNUMERO=?";
            PreparedStatement preparedStatement = cn.prepareStatement(query);
            preparedStatement.setString(1, jtxtEtunimi.getText());
            preparedStatement.setString(2, jtxtSukunimi.getText());
            preparedStatement.setString(3, jtxtYritys.getText());
            preparedStatement.setString(4, jtxtKatuosoite.getText());
            preparedStatement.setString(5, jtxtPostinumero.getText());
            preparedStatement.setString(6, jtxtPostitoimipaikka.getText());
            preparedStatement.setString(7, jtxtPuhelin.getText());
            preparedStatement.setString(8, jtxtEmail.getText());
            preparedStatement.setInt(9, Integer.parseInt(jtxtAsiakasnumero.getText()));
            jtxtSQL.setText(query);
            preparedStatement.executeUpdate();
            Naytaasiakkaat();
        } catch (SQLException ex) {
            Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(Level.SEVERE, null, ex);
        }
        tyhjenna();
    }//GEN-LAST:event_jbtnPaivitaActionPerformed

    private void jbtnPoistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPoistaActionPerformed
        // TODO add your handling code here:
                                                // Poistamisen varmistus-dialogi
        int response = JOptionPane.showConfirmDialog(this, "Haluatko poistaa asiakkaan?", "Asiakkaan poisto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            switch (response) {
                case JOptionPane.YES_OPTION:
                    break;
                case JOptionPane.NO_OPTION:
                    return; 
        }
            // Tavallinen Statement-lauseke
       // String query = "DELETE FROM ASIAKAS WHERE ASIAKASNUMERO="+jtxtAsiakasnumero.getText();
        // JOptionPane.showMessageDialog(null, query);
        //jtxtSQL.setText(query);
        //suoritaSQLKysely(query, "Poistettu");
        
        try {
            Connection cn = luoYhteys();
            
            String query = "DELETE FROM ASIAKAS WHERE ASIAKASNUMERO=?";
            PreparedStatement preparedStatement = cn.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(jtxtAsiakasnumero.getText()));
            jtxtSQL.setText(query);
            preparedStatement.executeUpdate();
            Naytaasiakkaat();
        } catch (SQLException ex) {
            Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          
       
         
    }//GEN-LAST:event_jbtnPoistaActionPerformed

    private void jtxtEtunimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtEtunimiActionPerformed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jtxtEtunimiActionPerformed

    private void jtxtEtunimiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtEtunimiKeyPressed
        // TODO add your handling code here:
                                                // Sallittujen merkkien asettaminen
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c)|| Character.isWhitespace(c)|| c==KeyEvent.VK_MINUS || Character.isISOControl(c)) {
            // Iso-kontrolli delete/backspace nappuloille
            // Sallitaan  kirjaimet, välilyönti, väliviiva ja em iso-kontrollit
            jtxtEtunimi.setEditable(true);
        } else
        {
            jtxtEtunimi.setEditable(false);
        }
        
    }//GEN-LAST:event_jtxtEtunimiKeyPressed

    private void jtxtSukunimiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSukunimiKeyPressed
        // TODO add your handling code here:
                                                // Sallittujen merkkien asettaminen
        
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c)||Character.isWhitespace(c)|| c==KeyEvent.VK_MINUS || Character.isISOControl(c)) {
            // Iso-kontrolli delete/backspace nappuloille
            // Sallitaan  kirjaimet, välilyönti, väliviiva ja em iso-kontrollit
            jtxtSukunimi.setEditable(true);
        } else
        {
            jtxtSukunimi.setEditable(false);
        }
    }//GEN-LAST:event_jtxtSukunimiKeyPressed

    private void jtxtPostitoimipaikkaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtPostitoimipaikkaKeyPressed
        // TODO add your handling code here:
                                                // Sallittujen merkkien asettaminen
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c)||Character.isWhitespace(c) || Character.isISOControl(c)) {
            // Iso-kontrolli delete/backspace nappuloille
            // Sallitaan  kirjaimet, välilyönti, ja em iso-kontrollit
            jtxtPostitoimipaikka.setEditable(true);
        } else
        {
            jtxtPostitoimipaikka.setEditable(false);
        }
    }//GEN-LAST:event_jtxtPostitoimipaikkaKeyPressed

    private void jtxtPostinumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtPostinumeroKeyPressed
        // TODO add your handling code here:
                                                // Sallittujen merkkien asettaminen
         char c = evt.getKeyChar();
        
        if(Character.isDigit(c) || Character.isISOControl(c)) {
            // Iso-kontrolli delete/backspace nappuloille
            // Sallitaan  kirjaimet ja em iso-kontrollit
            jtxtPostinumero.setEditable(true);
        } else
        {
            jtxtPostinumero.setEditable(false);
        }
    }//GEN-LAST:event_jtxtPostinumeroKeyPressed

    private void jtxtPuhelinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtPuhelinKeyPressed
        // TODO add your handling code here:
                                                // Sallittujen merkkien asettaminen
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c)||Character.isWhitespace(c) || Character.isISOControl(c)) {
            // Iso-kontrolli delete/backspace nappuloille
            // Sallitaan  kirjaimet, välilyönti, ja em iso-kontrollit
            jtxtPuhelin.setEditable(true);
        } else
        {
            jtxtPuhelin.setEditable(false);
        }
    }//GEN-LAST:event_jtxtPuhelinKeyPressed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnPaivita;
    private javax.swing.JButton jbtnPoista;
    private javax.swing.JButton jbtnUusi;
    private javax.swing.JLabel jlblAsiakasnumero;
    private javax.swing.JLabel jlblAsiakasrekisteri;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblEtunimi;
    private javax.swing.JLabel jlblKatuosoite;
    private javax.swing.JLabel jlblPakollisetKentat;
    private javax.swing.JLabel jlblPostinumero;
    private javax.swing.JLabel jlblPostitoimipaikka;
    private javax.swing.JLabel jlblPuhelin;
    private javax.swing.JLabel jlblSukunimi;
    private javax.swing.JLabel jlblYritys;
    private javax.swing.JTable jtblAsiakkaat;
    private javax.swing.JTextField jtxtAsiakasnumero;
    private javax.swing.JTextField jtxtAsiakkaaksitulopaiva;
    private javax.swing.JTextField jtxtEmail;
    private javax.swing.JTextField jtxtEtunimi;
    private javax.swing.JTextField jtxtKatuosoite;
    private javax.swing.JTextField jtxtPostinumero;
    private javax.swing.JTextField jtxtPostitoimipaikka;
    private javax.swing.JTextField jtxtPuhelin;
    private javax.swing.JTextField jtxtSQL;
    private javax.swing.JTextField jtxtSukunimi;
    private javax.swing.JTextField jtxtYritys;
    // End of variables declaration//GEN-END:variables
}
