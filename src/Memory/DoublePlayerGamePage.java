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
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public final class DoublePlayerGamePage extends javax.swing.JFrame {

    Connection con = null;
    ResultSet rs = null;
    Statement pst = null;
    String s1, s2;

    ImageIcon[] iconarray;//image ke icon e rupantor kore
    ArrayList<Integer> list1 = new ArrayList<>();
    JButton[] buttonarray = new JButton[20];//button er array toiri korlam track rakhar jonno & pic assign er jonno
    Random rand1 = new Random();//randomly distribute korar jonno
    JButton button1;
    JButton button2;
    int counter = 1;
    Timer timer1;//timer dewa
    int points1;
    int points2;
    int movements1 = 0;
    int movements2 = 0;
    boolean player1 = true;//first e player 1 true thakbe

    /**
     * Creates new form NewJFrame
     *
     * @throws java.net.MalformedURLException
     * @throws java.sql.SQLException
     */
    public DoublePlayerGamePage() throws MalformedURLException, SQLException {
        initComponents();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        con = Connect.ConnectDB();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Memory Game");
        getData();
        jLabel5.setText("Turn of  " + s1);
        //setBounds(100, 100, 900, 580);  
        jLabel1.setText(s1);
        jLabel2.setText(s2);

        contentPane.setBackground(Color.WHITE);
        contentPane.add(jLabel1);
        contentPane.add(jLabel2);
        contentPane.add(jLabel3);
        contentPane.add(jLabel4);
        contentPane.add(jLabel5);
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
        immagini[0] = new URL(" https://i.picsum.photos/id/824/150/200.jpg?hmac=xq8XmzYfZBNw_rxs5Lwt2UORxLK_DHYYAA0RKpCkrAc  ");
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
            list1.set(x, list1.get(x).intValue() + 1);
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
            rs = pst.executeQuery("select * from doubleplayeruserentry");
            while (rs.next()) {
                //System.out.println(rs.getString("Player1")+" "+rs.getString("Player2"));
                s1 = rs.getString("Player1");
                s2 = rs.getString("Player2");

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            pst.close();
            //con.close();
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

                //add up points to player who found two matching icons
                if (player1 == true) {
                    jLabel5.setText("Turn of " + s2);
                    points1++;
                    movements1++;
                    if (points1 + points2 == 10) {
                        if (points1 > points2) {

                            jLabel5.setText("Winner Is " + s1);
                            try {
                            pst = con.createStatement();

                            //String sql1="insert into information (Name,Age,Department) values ('"+ Name +"','"+ age +"','"+dept+"')";
                            String sql = "insert into doubleplayernormal (Winner,Loser) values ('" + s1 + "','"+s2+"')";
                            pst.executeUpdate(sql);

                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }

                        } else if (points1 < points2) {
                             
                            jLabel5.setText("Winner Is " + s2);
                            try {
                            pst = con.createStatement();

                            //String sql1="insert into information (Name,Age,Department) values ('"+ Name +"','"+ age +"','"+dept+"')";
                            String sql = "insert into doubleplayernormal (Loser,Winner) values ('" + s1 + "','"+s2+"')";
                            pst.executeUpdate(sql);

                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }

                        } else if (points1 == points2) {
                             jLabel5.setText("Draw");
                            try {
                            pst = con.createStatement();

                            //String sql1="insert into information (Name,Age,Department) values ('"+ Name +"','"+ age +"','"+dept+"')";
                            String sql = "insert into doubleplayerdraw (Player1,Player2) values ('" + s1 + "','"+s2+"')";
                            pst.executeUpdate(sql);

                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }
                            /*if (movements1 == movements2) {

                                jLabel5.setText("DRAW\n");

                            } else if (movements1 > movements2) {

                                jLabel5.setText("Winner Is " + s2);

                            } else if (movements1 < movements2) {

                                jLabel5.setText("Winner Is " + s1);

                            }*/

                        }
                        //jLabel5.setText("Game Over\n");
                    }
                    jLabel3.setText(Integer.toString(points1));
                    player1 = false;
                } else {
                    jLabel5.setText("Turn of " + s1);
                    points2++;
                    movements2++;
                    if (points1 + points2 == 10) {
                        if (points1 > points2) {

                            //jLabel5.setText("Winner Is " + s1);
                            jLabel5.setText("Winner Is " + s1);
                            try {
                            pst = con.createStatement();

                            //String sql1="insert into information (Name,Age,Department) values ('"+ Name +"','"+ age +"','"+dept+"')";
                            String sql = "insert into doubleplayernormal (Winner,Loser) values ('" + s1 + "','"+s2+"')";
                            pst.executeUpdate(sql);

                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }

                        } else if (points1 < points2) {

                            //jLabel5.setText("Winner Is " + s2);
                             jLabel5.setText("Winner Is " + s2);
                            try {
                            pst = con.createStatement();

                            //String sql1="insert into information (Name,Age,Department) values ('"+ Name +"','"+ age +"','"+dept+"')";
                            String sql = "insert into doubleplayernormal (Loser,Winner) values ('" + s1 + "','"+s2+"')";
                            pst.executeUpdate(sql);

                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }

                        } else if (points1 == points2) {
                            jLabel5.setText("Draw");
                             try {
                            pst = con.createStatement();

                            //String sql1="insert into information (Name,Age,Department) values ('"+ Name +"','"+ age +"','"+dept+"')";
                            String sql = "insert into doubleplayerdraw (Player1,Player2) values ('" + s1 + "','"+s2+"')";
                            pst.executeUpdate(sql);

                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }
                            /*if (movements1 == movements2) {

                                jLabel5.setText("DRAW\n");

                            } else if (movements1 > movements2) {

                                jLabel5.setText("Winner Is " + s2);

                            } else if (movements1 < movements2) {

                                jLabel5.setText("Winner Is " + s1);

                            }*/

                        }
                        //jLabel5.setText("Game Over\n");
                    }
                    /*if (points1 + points2 == 10) {
                        jLabel5.setText("Game Over\n");
                    }*/
                    
                    jLabel4.setText(Integer.toString(points2));
                    player1 = true;                 
                }
            } //if cards are different, switch to other player
            else {

                if (player1 == true) {
                    jLabel5.setText("Turn of " + s2);
                    movements1++;
                    player1 = false;
                } else {
                    jLabel5.setText("Turn of " + s1);
                    movements2++;
                    player1 = true;
                }
                timer1.start();
            }
            counter = 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPane = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        newGame = new javax.swing.JButton();
        exit1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        exit2 = new javax.swing.JButton();
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("0");

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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        exit2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        exit2.setText("Main Menu");
        exit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit2ActionPerformed(evt);
            }
        });

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
                .addGap(128, 128, 128)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(exit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Records, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(newGame)
                .addGap(38, 38, 38)
                .addComponent(exit2)
                .addGap(36, 36, 36)
                .addComponent(Records)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(exit1)
                .addGap(58, 58, 58))
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(contentPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameActionPerformed

        this.dispose();

        singlePlayerUserEntry s1 = new singlePlayerUserEntry();
        s1.setVisible(true);
        //try {
        try {
            pst = con.createStatement();
            String sql = "TRUNCATE doubleplayeruserentry";
            pst.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DoublePlayerGamePage.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        //} catch (SQLException ex) {
            //Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        //}


    }//GEN-LAST:event_newGameActionPerformed

    private void exit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        //try {
        try {
            pst = con.createStatement();
            String sql = "TRUNCATE doubleplayeruserentry";
            pst.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DoublePlayerGamePage.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = "TRUNCATE doubleplayeruserentry";
            pst.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DoublePlayerGamePage.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        //} catch (SQLException ex) {
          //  Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }//GEN-LAST:event_exit2ActionPerformed

    private void RecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecordsActionPerformed
        // TODO add your handling code here:
        DoublePlayerRecords d=new DoublePlayerRecords();
        d.setVisible(true);
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
                        new DoublePlayerGamePage().setVisible(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(DoublePlayerGamePage.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton newGame;
    private javax.swing.JPanel panel1;
    // End of variables declaration//GEN-END:variables
}
