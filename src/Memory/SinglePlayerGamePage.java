package Memory;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SinglePlayerGamePage extends javax.swing.JFrame {

    Connection con = null;
    ResultSet rs = null;
    Statement pst = null;
    String s1;
    ImageIcon[] iconarray;//image ke icon e rupantor kore
    ArrayList<Integer> list1 = new ArrayList<Integer>();
    JButton[] buttonarray = new JButton[20];//button er array toiri korlam track rakhar jonno & pic assign er jonno
    Random rand1 = new Random();//randomly distribute korar jonno
    JButton button1;
    JButton button2;
    int counter = 1;
    Timer timer1;//timer dewa
    int points = 0;
    int points2;
    int movements = 0;
    boolean player1 = true;//first e player 1 true thakbe

    /**
     * Creates new form DoublePlayerGamePage
     */
    public SinglePlayerGamePage() throws MalformedURLException, SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Memory Game");
        con = Connect.ConnectDB();
        getData();
        jLabel1.setText(s1);
        //jLabel5.setText("Turn of Player 1");
        //setBounds(100, 100, 900, 580);  
        contentPane.setBackground(Color.WHITE);
        contentPane.add(jLabel1);
        contentPane.add(jLabel2);
        contentPane.add(jLabel3);
        contentPane.add(jLabel4);
        contentPane.add(jLabel6);
        contentPane.add(newGame);
        contentPane.add(Records);
        contentPane.add(exit1);
        contentPane.add(exit2);
        setContentPane(contentPane);//janina
        //contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.setBackground(Color.BLACK);
        contentPane.add(panel1);
        panel1.setLayout(new GridLayout(5, 4, 5, 5));
        URL[] immagini = new URL[11];
        immagini[0] = new URL("https://i.picsum.photos/id/824/150/200.jpg?hmac=xq8XmzYfZBNw_rxs5Lwt2UORxLK_DHYYAA0RKpCkrAc  ");
        immagini[1] = new URL("https://i.picsum.photos/id/375/150/200.jpg?hmac=daUd9bJ5h8qpXYnUIRd3pMW-gdqqPW38Dxs8pvNo6RQ");
        immagini[2] = new URL("https://i.picsum.photos/id/358/150/200.jpg?hmac=fy1-_YAvDB2ic0tiDfgBTnXcPvdLvXb06rVzrnddQZw");
        immagini[3] = new URL("https://i.picsum.photos/id/716/150/200.jpg?hmac=9yiY3dPcqd94mnkLBmENPDNRpQf5oE16MBdxqId1bqI");
        immagini[4] = new URL("https://i.picsum.photos/id/585/150/200.jpg?hmac=6OTf5JVWqUWFKjSHklnFkwDjYsRmlwz0Wtbrg1GOofM");
        immagini[5] = new URL("https://i.picsum.photos/id/641/150/200.jpg?hmac=oH6B7A03AYnWEApmjyZLScyutjar4LmvfqknreHPE90");
        immagini[10] = new URL("https://i.redd.it/6ihl9vurecz01.png");
        immagini[7] = new URL("https://i.picsum.photos/id/54/150/200.jpg?hmac=25X0RmwjgW0GCtWb6JF8LSnbDxM63nYTcKuW1JUdYWk");
        immagini[8] = new URL("https://i.picsum.photos/id/1073/150/200.jpg?hmac=KA0jeDBEJYlw-s1DtgmowRcxuNE8eRQ9JxoC7Yu7Xo4");
        immagini[9] = new URL("https://i.picsum.photos/id/487/150/200.jpg?hmac=6hTPmjO3nMARLbpxLBZYmufDExEnLG-Ean4GJHWLP9g");
        immagini[6] = new URL("https://i.picsum.photos/id/84/150/200.jpg?hmac=WW6epndHItnCRcBMCHMqqQwBLGjwsI6ZruWfq65xV0o");
        //https://i.picsum.photos/id/824/150/200.jpg?hmac=xq8XmzYfZBNw_rxs5Lwt2UORxLK_DHYYAA0RKpCkrAc  
        iconarray = new ImageIcon[11];
        // convert imagines in icons
        for (int i = 0; i <= 10; i++) {
            iconarray[i] = new ImageIcon(immagini[i]);
            // inizializzo list1
            list1.add(0);
        }

        // add 20 JButtons to panel 1 and set initial icon
        for (int i = 0; i < 20; i++) {
            
            panel1.add(new JButton(iconarray[10]));
            // insert JButtons in buttonarray
            buttonarray[i] = (JButton) panel1.getComponent(i);
            // add ImageButtonListener method to each JButton
            buttonarray[i].addActionListener(new ImageButtonListener());
            

        }

        // add a number between 0 and 9 for each JButton
        int y = 0;
        while (y < 20) {
            int x = rand1.nextInt(10);
            list1.set(x, list1.get(x).intValue()+1);
            if (list1.get(x) <= 2) {
                buttonarray[y].setName(Integer.toString(x));
                y++;
            }
        }

        timer1 = new Timer(750, new TimerListener());

    }

    public void getData() throws SQLException {

        try {
            pst = con.createStatement();
            rs = pst.executeQuery("select * from singleplayeruserentry");
            while (rs.next()) {

                s1 = rs.getString("Name");

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            pst.close();

        }

    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            button1.setIcon(iconarray[10]);
            button2.setIcon(iconarray[10]);
            timer1.stop();
            //active = true;
        }
    }

    class ImageButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // waiting for timer to pop, user clicks not accepted

            if (timer1.isRunning()) {
                return;
            }

            for (int i = 0; i < 20; i++) {
                if (e.getSource() == buttonarray[i]) {
                    int x = Integer.parseInt(buttonarray[i].getName());
                    buttonarray[i].setIcon(iconarray[x]);

                    // button1=  first clicked button
                    if (counter == 1) {
                        button1 = buttonarray[i];
                        counter++;
                    }
                    // button 2= second clicked button, check I didn't click same card twice
                    if (counter == 2 && buttonarray[i] != button1) {
                        button2 = buttonarray[i];
                        compareicons();
                    }
                }
            }
        }

        // check if icons match
        private void compareicons() {

            if (button1.getIcon() == button2.getIcon()) {

                button1.setEnabled(false);
                button2.setEnabled(false);
                movements++;
                if (movements > 15) {
                    jLabel6.setText("You Lose");
                    for(int i=0;i<20;i++){
                        buttonarray[i].setEnabled(false); ///shobgula button false kore dilam nayle harar porew button press kora jay
                    }
                    
                    
                    try {
                        pst = con.createStatement();

                        //String sql="insert into information (Name,Age,Department) values ('"+ Name +"','"+ age +"','"+dept+"')";
                        String sql = "insert into singleloser (Name) values ('" + s1 + "')";
                        pst.executeUpdate(sql);

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                }
                points++;
                jLabel3.setText("" + movements);
                if (points == 10 && movements <= 15) {
                    jLabel6.setText("You Win!!");
                    try {
                        pst = con.createStatement();

                        String sql = "insert into singlewinner (Name) values ('" + s1 + "')";
                        pst.executeUpdate(sql);

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            } //if cards are different, switch to other player
            else {

                movements++;

                if (movements > 15) {
                    //panel1.setEnabled(false);
                    for(int i=0;i<20;i++){
                        buttonarray[i].setEnabled(false);///shobgula button false kore dilam nayle harar porew button press kora jay
                    }
                    for(int i=0;i<20;i++){
                        
                    }
                    jLabel6.setText("You Lose");

                    try {
                        pst = con.createStatement();

                        //String sql="insert into information (Name,Age,Department) values ('"+ Name +"','"+ age +"','"+dept+"')";
                        String sql = "insert into singleloser (Name) values ('" + s1 + "')";
                        pst.executeUpdate(sql);

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                }
                jLabel3.setText("" + movements);
                timer1.start();
            }
            //reset counter

            counter = 1;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPane = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        newGame = new javax.swing.JButton();
        exit1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        exit2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Records = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Movements");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("0");

        newGame.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newGame.setText("NEW GAME");
        newGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameActionPerformed(evt);
            }
        });

        exit1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        exit1.setText("EXIT");
        exit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        exit2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        exit2.setText("Main Menu");
        exit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        Records.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Records.setText("Records");
        Records.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecordsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(Records, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addGap(51, 51, 51)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(57, 57, 57)))
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(exit2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(newGame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(exit1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(newGame)
                .addGap(39, 39, 39)
                .addComponent(exit2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Records))
                .addGap(37, 37, 37)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exit1)
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(contentPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameActionPerformed
        // TODO add your handling code here:
        this.dispose();

        singlePlayerUserEntry s1 = new singlePlayerUserEntry();
        s1.setVisible(true);
        //try {
        try {
            pst = con.createStatement();
            String sql = "TRUNCATE singleplayeruserentry";
            pst.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SinglePlayerGamePage.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        //} catch (SQLException ex) {
          //  Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        //}

        /*try {
            SinglePlayerGamePage n=new SinglePlayerGamePage();
            n.setVisible(true);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DoublePlayerGamePage.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }//GEN-LAST:event_newGameActionPerformed

    private void exit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        //try {
        try {
            pst = con.createStatement();
            String sql = "TRUNCATE singleplayeruserentry";
            pst.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SinglePlayerGamePage.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        //} catch (SQLException ex) {
          //  Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }//GEN-LAST:event_exit1ActionPerformed

    private void exit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit2ActionPerformed
        // TODO add your handling code here:
        MainMenuPage m = new MainMenuPage();
        m.setVisible(true);
        this.dispose();
        //try {
        try {
            pst = con.createStatement();
            String sql = "TRUNCATE singleplayeruserentry";
            pst.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SinglePlayerGamePage.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        //} catch (SQLException ex) {
          //  Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }//GEN-LAST:event_exit2ActionPerformed

    private void RecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecordsActionPerformed
        // TODO add your handling code here:
        SinglePlayerRecords s=new SinglePlayerRecords();
        s.setVisible(true);
        //this.hide();
    }//GEN-LAST:event_RecordsActionPerformed

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
            java.util.logging.Logger.getLogger(DoublePlayerGamePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoublePlayerGamePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoublePlayerGamePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoublePlayerGamePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    try {
                        new SinglePlayerGamePage().setVisible(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(SinglePlayerGamePage.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (MalformedURLException ex) {
                    Logger.getLogger(DoublePlayerGamePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Records;
    private javax.swing.JPanel contentPane;
    private javax.swing.JButton exit1;
    private javax.swing.JButton exit2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton newGame;
    private javax.swing.JPanel panel1;
    // End of variables declaration//GEN-END:variables
}
