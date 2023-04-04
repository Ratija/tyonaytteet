
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author 22100
 */
public class Tilaustenhallinta extends javax.swing.JFrame {
    
     class Asiakas{
                                            // Asiakas-luokan atribuutit
    private int ASIAKASNUMERO;
    private String YRITYS;
                                            // Asiakas-luokan muodostin
        public Asiakas(int id, String yritys) {
        this.ASIAKASNUMERO = id;
        this.YRITYS = yritys;
    }
    public int HaeAsiakasnumero() {
        return this.ASIAKASNUMERO;
    }
    public String HaeYritys() {
        return this.YRITYS;
    }
    }                                       
                                            // Tilaus-luokan atribuutit
    class Tilaus {
    private int TUOTENUMERO;
    private String TUOTENIMI;
    private String AHINTA;
                                            // Tilaus-luokan muodostin
    public Tilaus(int tuotenumero, String tuotenimi, String ahinta) {
        this.TUOTENUMERO = tuotenumero;
        this.TUOTENIMI = tuotenimi;
        this.AHINTA = ahinta;
    }
    public int HaeTuotenumero() {
        return this.TUOTENUMERO;
    }
     public String HaeTuote() {
        return this.TUOTENIMI;
    }
    public String HaeHinta() {
        return this.AHINTA;
    }
    }                                   
                                            // Tilausnumero-luokan atribuutit
    class Tilausnumero {
        private int TILAUSNUMERO;
        private String ASIAKASNUMERO;
                                            // Tilausnumero-luokan muodostin
    public Tilausnumero(int tilausnumero, String asiakasnumero) {
        this.TILAUSNUMERO = tilausnumero;
        this.ASIAKASNUMERO = asiakasnumero;
    }
    public int HaeTilausnumero() {
        return this.TILAUSNUMERO;
    }
    public String HaeAsiakasnumero() {
        return this.ASIAKASNUMERO;
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
        String query = "SELECT ASIAKASNUMERO, YRITYS FROM ASIAKAS";
        Statement st;
        ResultSet rs;
        
        try {
            st = yhteys.createStatement();
            rs = st.executeQuery(query);
            Asiakas a;
                                            //Lisätään kaikki asiakkaat taulukkoon
            while(rs.next())
            {
                a = new Asiakas(rs.getInt("ASIAKASNUMERO"), rs.getString("YRITYS"));
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
        DefaultTableModel model = (DefaultTableModel)jtblTilaus.getModel();
                                            // Luodaan sarakeotsikot
        model.setColumnIdentifiers(new Object[]{"ASIAKASNUMERO", "YRITYS"});
        Object[] row = new Object[2];
                                            // Putsataan taulukko ennen täyttämistä
        for (int i = jtblTilaus.getRowCount() -1;i >= 0; i--) {
            model.removeRow(i);
        }
                                            // Täytetään taulukko uudella datalla
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).HaeAsiakasnumero();
            row[1] = list.get(i).HaeYritys();
            model.addRow(row);
        }
    }
                                            // Metodi, joka täyttää Tuote-arraylist objektin
    public ArrayList<Tilaus>HaeTuotetaulukko()
    {
    ArrayList<Tilaus> Tuotetaulukko = new ArrayList<Tilaus>();
        Connection yhteys = luoYhteys();
                                            // SQL kysely, jolla haetaan tarvittavat kentät
        String query = "SELECT TUOTENUMERO, TUOTENIMI, AHINTA FROM TUOTE";
        Statement st;
        ResultSet rs;
        
        try {
            st = yhteys.createStatement();
            rs = st.executeQuery(query);
            Tilaus a;
                                            //Lisätään kaikki tuotteet taulukkoon
            while(rs.next())
            {
                a = new Tilaus(rs.getInt("TUOTENUMERO"),rs.getString("TUOTENIMI"),rs.getString("AHINTA"));
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
        ArrayList<Tilaus> list = HaeTuotetaulukko();
        DefaultTableModel model = (DefaultTableModel)jtblTilaus.getModel();
                                            // Luodaan sarakeotsikot
        model.setColumnIdentifiers(new Object[]{"TUOTENIMI","AHINTA","TUOTENUMERO"});
        Object[] row = new Object[3];
                                            // Putsataan taulukko ennen täyttämistä
        for (int i = jtblTilaus.getRowCount() -1;i >= 0; i--) {
            model.removeRow(i);
        }
                                            // Täytetään taulukko uudella datalla
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).HaeTuote();
            row[1] = list.get(i).HaeHinta();
            row[2] = list.get(i).HaeTuotenumero();
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
            {                              //päivitetään jtblAsiakkaat taulukko
                DefaultTableModel model = (DefaultTableModel)jtblTilaus.getModel();
                model.setRowCount(0);      // Tyhjentää tilaus-tablen oletusrivit
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
        
    jtxtYritys.setText("Valitse");
    jtxtTuote.setText("Valitse");
    jtxtMaara.setText("");
    jtxtHinta.setText("");
    }                                      
                                            // Hakee suurimman arvon hinta-sarakkeesta
    public void HaeSuurin() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i =0;i<jtblTilaus.getRowCount();i++) {
            list.add(Integer.parseInt(jtblTilaus.getValueAt(i,0).toString()));
        }
        int suurin = Collections.max(list);
        jtxtTilausnumero.setText(Integer.toString(suurin));
    }
    
                                               // Metodi, joka täyttää tilausnumero-arraylist objektin
    public ArrayList<Tilausnumero>HaeTilausnumero()
    {
    ArrayList<Tilausnumero> Tilausnumerotaulukko = new ArrayList<Tilausnumero>();
        Connection yhteys = luoYhteys();
                                            // SQL kysely, jolla haetaan tarvittavat kentät
        String query =  "SELECT TILAUSNUMERO, ASIAKASNUMERO FROM TILAUS";
        Statement st;
        ResultSet rs;
        
        try {
            st = yhteys.createStatement();
            rs = st.executeQuery(query);
            Tilausnumero a;
                                            //Lisätään kaikki numerot taulukkoon
            while(rs.next())
            {
                a = new Tilausnumero(rs.getInt("TILAUSNUMERO"), rs.getString("ASIAKASNUMERO"));
                    Tilausnumerotaulukko.add(a);
                    
                    int i = jtblTilaus.getSelectedRow();
                    TableModel model = jtblTilaus.getModel();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
            return Tilausnumerotaulukko;
    }
    public void NaytaTilausnumero()
    {
        ArrayList<Tilausnumero> list = HaeTilausnumero();
        DefaultTableModel model = (DefaultTableModel)jtblTilaus.getModel();
                                            // Luodaan sarakeotsikot
        model.setColumnIdentifiers(new Object[]{"TILAUSNUMERO", "ASIAKASNUMERO"});
        Object[] row = new Object[2];
                                            // Putsataan taulukko ennen täyttämistä
        for (int i = jtblTilaus.getRowCount() -1;i >= 0; i--) {
            model.removeRow(i);
        }
                                            // Täytetään taulukko uudella datalla
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).HaeTilausnumero();
            row[1] = list.get(i).HaeAsiakasnumero();
            model.addRow(row);
        }
    }
   
    /**
     * Creates new form Tilaustenhallinta
     */
    public Tilaustenhallinta() {
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

        jlblTilaustenhallinta = new javax.swing.JLabel();
        jlblAsiakasnumero = new javax.swing.JLabel();
        jlblYritys = new javax.swing.JLabel();
        jlblTuote = new javax.swing.JLabel();
        jlblMaara = new javax.swing.JLabel();
        jlblHinta = new javax.swing.JLabel();
        jtxtAsiakasnumero = new javax.swing.JTextField();
        jtxtYritys = new javax.swing.JTextField();
        jtxtTuote = new javax.swing.JTextField();
        jtxtMaara = new javax.swing.JTextField();
        jtxtHinta = new javax.swing.JTextField();
        jbtnLisaa = new javax.swing.JButton();
        jbtnPoista = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblTilaus = new javax.swing.JTable();
        jtxtNaytatuotelista = new javax.swing.JTextField();
        jbtnTilaa = new javax.swing.JButton();
        jtxtYhteensa = new javax.swing.JTextField();
        jtxtNaytaYrityslista = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblTuotteet = new javax.swing.JTable();
        jlblTilausnumero = new javax.swing.JLabel();
        jtxtTilausnumero = new javax.swing.JTextField();
        jtxtTuotenumero = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlblTilaustenhallinta.setText("Tilaustenhallinta");

        jlblAsiakasnumero.setText("Asiakasnumero:");

        jlblYritys.setText("Yritys:");

        jlblTuote.setText("Tuote:");

        jlblMaara.setText("Määrä (kpl):");

        jlblHinta.setText("Hinta:");

        jtxtAsiakasnumero.setEditable(false);
        jtxtAsiakasnumero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtAsiakasnumeroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtxtAsiakasnumeroMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtxtAsiakasnumeroMousePressed(evt);
            }
        });

        jtxtYritys.setEditable(false);
        jtxtYritys.setText("Valitse");
        jtxtYritys.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtYritysMouseClicked(evt);
            }
        });

        jtxtTuote.setEditable(false);
        jtxtTuote.setText("Valitse");
        jtxtTuote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtTuoteMouseClicked(evt);
            }
        });
        jtxtTuote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtTuoteActionPerformed(evt);
            }
        });

        jbtnLisaa.setText("Lisää");
        jbtnLisaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLisaaActionPerformed(evt);
            }
        });

        jbtnPoista.setText("Poista");
        jbtnPoista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPoistaActionPerformed(evt);
            }
        });

        jtblTilaus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jtblTilaus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblTilausMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblTilaus);

        jtxtNaytatuotelista.setEditable(false);
        jtxtNaytatuotelista.setText("NÄYTÄ TUOTELISTA");
        jtxtNaytatuotelista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtxtNaytatuotelistaMouseEntered(evt);
            }
        });
        jtxtNaytatuotelista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNaytatuotelistaActionPerformed(evt);
            }
        });

        jbtnTilaa.setText("Tilaa");
        jbtnTilaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTilaaActionPerformed(evt);
            }
        });

        jtxtYhteensa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtYhteensaActionPerformed(evt);
            }
        });

        jtxtNaytaYrityslista.setEditable(false);
        jtxtNaytaYrityslista.setText("NÄYTÄ YRITYSLISTA");
        jtxtNaytaYrityslista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtxtNaytaYrityslistaMouseEntered(evt);
            }
        });

        jtblTuotteet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jtblTuotteet.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jtblTuotteetInputMethodTextChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jtblTuotteet);

        jlblTilausnumero.setText("Tilausnumero:");

        jtxtTilausnumero.setEditable(false);

        jtxtTuotenumero.setEditable(false);
        jtxtTuotenumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtTuotenumeroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlblTilaustenhallinta, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtYhteensa, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlblAsiakasnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jlblHinta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlblMaara, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlblYritys, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlblTuote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(10, 10, 10)
                                                .addComponent(jtxtTuotenumero, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(8, 8, 8)
                                            .addComponent(jbtnLisaa))))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jbtnPoista))
                                    .addComponent(jtxtYritys, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtAsiakasnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtNaytaYrityslista, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtNaytatuotelista, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtTuote, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtMaara, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jtxtHinta, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbtnTilaa)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblTilausnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtTilausnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(349, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblTilaustenhallinta)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtxtNaytaYrityslista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblAsiakasnumero)
                            .addComponent(jtxtAsiakasnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtYritys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblYritys))
                        .addGap(18, 18, 18)
                        .addComponent(jtxtNaytatuotelista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblTuote)
                                .addComponent(jtxtTuotenumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtxtTuote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlblMaara)
                            .addComponent(jtxtMaara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblHinta)
                            .addComponent(jtxtHinta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnLisaa)
                            .addComponent(jbtnPoista))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtYhteensa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnTilaa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblTilausnumero)
                            .addComponent(jtxtTilausnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(176, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtAsiakasnumeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtAsiakasnumeroMouseClicked
        // TODO add your handling code here:
        
  
              
        
       
    }//GEN-LAST:event_jtxtAsiakasnumeroMouseClicked

    private void jtblTilausMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTilausMouseClicked
        // TODO add your handling code here:
    
       
       
        
    }//GEN-LAST:event_jtblTilausMouseClicked

    private void jtxtAsiakasnumeroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtAsiakasnumeroMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtxtAsiakasnumeroMouseEntered

    private void jtxtAsiakasnumeroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtAsiakasnumeroMousePressed
        // TODO add your handling code here:
      
        
    }//GEN-LAST:event_jtxtAsiakasnumeroMousePressed

    private void jtxtYritysMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtYritysMouseClicked
        // TODO add your handling code here:
                                                         // Haetaan käyttöliittymästä klikattu rivinumero
        int i = jtblTilaus.getSelectedRow();
        TableModel model = jtblTilaus.getModel();
                                                        //Asetetaan käyttöliittymään tiedot
        jtxtAsiakasnumero.setText((model.getValueAt(i,0).toString()));
                                                        // Estetään Asiakasnumeron muokkaaminen
        jtxtAsiakasnumero.setEnabled(false);
         
        jtxtYritys.setText((model.getValueAt(i,1).toString()));
                                                        // Valinnan jälkeen poistaa loput näkyvistä
    DefaultTableModel dm = (DefaultTableModel)jtblTilaus.getModel();
    while(dm.getRowCount() > 0)
    {
    dm.removeRow(0);
    }  
    }//GEN-LAST:event_jtxtYritysMouseClicked

    private void jtxtTuoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtTuoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtTuoteActionPerformed

    private void jtxtNaytatuotelistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNaytatuotelistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNaytatuotelistaActionPerformed

    private void jtxtNaytatuotelistaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtNaytatuotelistaMouseEntered
        // TODO add your handling code here:
        Naytatuotteet();
    }//GEN-LAST:event_jtxtNaytatuotelistaMouseEntered

    private void jtxtTuoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtTuoteMouseClicked
        // TODO add your handling code here:
                                                    // Haetaan käyttöliittymästä klikattu rivinumero
        int i = jtblTilaus.getSelectedRow();
        TableModel model = jtblTilaus.getModel();
                                                    // Asetetaan käyttöliittymään tiedot
        jtxtTuote.setText((model.getValueAt(i,0).toString()));
        jtxtHinta.setText((model.getValueAt(i,1).toString()));
        jtxtTuotenumero.setText((model.getValueAt(i,2).toString()));
    }//GEN-LAST:event_jtxtTuoteMouseClicked

    private void jbtnLisaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLisaaActionPerformed
        // TODO add your handling code here:
                       
        DefaultTableModel model = (DefaultTableModel)jtblTuotteet.getModel();
                                                // Luodaan sarakeotsikot
        model.setColumnIdentifiers(new Object[]{"TUOTENIMI","AHINTA","MÄÄRÄ","NRO"});
                                                // Tietojen vienti tauluun
        String nextRowId = Integer.toString(model.getRowCount());
        model.addRow(new Object[]  { 
              jtxtTuote.getText(), 
              jtxtHinta.getText(), 
              jtxtMaara.getText(),
              jtxtTuotenumero.getText(),
              nextRowId});
                                               // Hinnan asetus textfieldiin
        int yhteensa = 0;
        for (int i = 0; i < jtblTuotteet.getRowCount(); i++){
            int hinta = Integer.parseInt(jtblTuotteet.getValueAt(i, 1)+"");
            int maara = Integer.parseInt(jtblTuotteet.getValueAt(i, 2)+"");
            yhteensa += hinta * maara;
        }
        jtxtYhteensa.setText("Yhteensä: " + String.valueOf(yhteensa) + " €"); 
                                                // Tuotteen lisäyksen jälkeen tyhjentää valinnat
        jtxtTuote.setText("Valitse");
        jtxtMaara.setText("");
        jtxtHinta.setText("");
             
    }//GEN-LAST:event_jbtnLisaaActionPerformed

    private void jtxtYhteensaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtYhteensaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtYhteensaActionPerformed

    private void jtxtNaytaYrityslistaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtNaytaYrityslistaMouseEntered
        // TODO add your handling code here:
        Naytaasiakkaat();
    }//GEN-LAST:event_jtxtNaytaYrityslistaMouseEntered

    private void jtblTuotteetInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jtblTuotteetInputMethodTextChanged
        // TODO add your handling code here:
  
    
    }//GEN-LAST:event_jtblTuotteetInputMethodTextChanged

    private void jbtnPoistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPoistaActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jtblTuotteet.getModel();
                                        // Tarkistaa rivin olemassaolon
            if(jtblTuotteet.getSelectedRow() != -1) {
                                         // Poistaa valitun rivin taululta
               model.removeRow(jtblTuotteet.getSelectedRow());
               JOptionPane.showMessageDialog(null, "Tuote poistettu ostoslistalta!");
            }
                     // Lopullisen hinnan päivitys
         int yhteensa = 0;
        for (int i = 0; i < jtblTuotteet.getRowCount(); i++){
            int hinta = Integer.parseInt(jtblTuotteet.getValueAt(i, 1)+"");
            int maara = Integer.parseInt(jtblTuotteet.getValueAt(i, 2)+"");
            yhteensa += hinta * maara;
        }
        jtxtYhteensa.setText("Yhteensä: " + String.valueOf(yhteensa) + " €"); 
        
    }//GEN-LAST:event_jbtnPoistaActionPerformed

    private void jbtnTilaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTilaaActionPerformed
        // TODO add your handling code here:
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date tamapaiva = new Date();
            var toimituspaiva = new Date();
            toimituspaiva.setDate(toimituspaiva.getDate() + 7); // Toimitus 7 vrk tilauksesta
             var erapaiva = new Date();
            erapaiva.setDate(erapaiva.getDate() + 14);          // Maksun eräpäivä 14 vrk tilauksesta
                                     
            Connection cn = luoYhteys();
            
            String query = "INSERT INTO TILAUS(TILAUSNUMERO, ASIAKASNUMERO, TILAUSPAIVA, TOIMITUSPAIVA, ERAPAIVA, MAKSUTAPA, LISATIETOJA) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = cn.prepareStatement(query);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, jtxtAsiakasnumero.getText());
            preparedStatement.setString(3, dateFormat.format(tamapaiva));
            preparedStatement.setString(4, dateFormat.format(toimituspaiva));
            preparedStatement.setString(5,  dateFormat.format(erapaiva));
            preparedStatement.setString(6, "1");
            preparedStatement.setString(7, "");
            
            preparedStatement.executeUpdate();
                   
        } catch (SQLException ex) {
            Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        NaytaTilausnumero();                                // Näyttää varmistuksena kaikki tilausnumerot taulussa, asiakasnumeron kanssa
        HaeSuurin();                                        // Hakee ja asettaa viimeisimmän tilausnumeron tekstikenttään
               
        try {

            int rivit = jtblTuotteet.getRowCount();
            Connection cn = luoYhteys();
    
            String query = "INSERT INTO TILAUSRIVI(TILAUSRIVINUMERO, TILAUSNUMERO, TUOTENUMERO, MAARA, AHINTA) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = cn.prepareStatement(query);
                                                            // Syöttää silmukalla kaikki tilaukset kerralla tietokantaan
            for (int rivi = 0; rivi < rivit; rivi++) {
            String tilausnumero = (String) jtxtTilausnumero.getText();
            String ahinta = (String) jtblTuotteet.getValueAt(rivi, 1);
            String maara = (String) jtblTuotteet.getValueAt(rivi, 2);
            String tuotenumero = (String) jtblTuotteet.getValueAt(rivi, 3);
        
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, tilausnumero);
            preparedStatement.setString(3, tuotenumero);
            preparedStatement.setString(4, maara);
            preparedStatement.setString(5, ahinta);
               
            preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.execute(query);
        
        } catch (SQLException ex) {
            Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }//GEN-LAST:event_jbtnTilaaActionPerformed

    private void jtxtTuotenumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtTuotenumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtTuotenumeroActionPerformed

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
            java.util.logging.Logger.getLogger(Tilaustenhallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tilaustenhallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tilaustenhallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tilaustenhallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tilaustenhallinta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnLisaa;
    private javax.swing.JButton jbtnPoista;
    private javax.swing.JButton jbtnTilaa;
    private javax.swing.JLabel jlblAsiakasnumero;
    private javax.swing.JLabel jlblHinta;
    private javax.swing.JLabel jlblMaara;
    private javax.swing.JLabel jlblTilausnumero;
    private javax.swing.JLabel jlblTilaustenhallinta;
    private javax.swing.JLabel jlblTuote;
    private javax.swing.JLabel jlblYritys;
    private javax.swing.JTable jtblTilaus;
    private javax.swing.JTable jtblTuotteet;
    private javax.swing.JTextField jtxtAsiakasnumero;
    private javax.swing.JTextField jtxtHinta;
    private javax.swing.JTextField jtxtMaara;
    private javax.swing.JTextField jtxtNaytaYrityslista;
    private javax.swing.JTextField jtxtNaytatuotelista;
    private javax.swing.JTextField jtxtTilausnumero;
    private javax.swing.JTextField jtxtTuote;
    private javax.swing.JTextField jtxtTuotenumero;
    private javax.swing.JTextField jtxtYhteensa;
    private javax.swing.JTextField jtxtYritys;
    // End of variables declaration//GEN-END:variables
}
