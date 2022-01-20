package Memory;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenuPage extends javax.swing.JFrame {

    Connection con = null;
    ResultSet rs = null;
    Statement pst = null;

    public MainMenuPage() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Memory Game");
        con = Connect.ConnectDB();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Memory/ACETYLCHOLINE - Copy.jpg"))); // NOI18N

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 2, 36)); // NOI18N
        jButton1.setText("Single Player");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 2, 36)); // NOI18N
        jButton2.setText("MultiPlayer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 2, 36)); // NOI18N
        jButton3.setText("EXIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 2, 36)); // NOI18N
        jLabel2.setText("Main Menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            
            /*try {
            NewJFrame s = new NewJFrame();
            s.setVisible(true);
            this.dispose();
            } catch (MalformedURLException ex) {
            Logger.getLogger(MainMenuPage.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            // try {
            pst = con.createStatement();
            String sql = "TRUNCATE singleplayeruserentry";
            String sql1 = "TRUNCATE doubleplayeruserentry";
            pst.executeUpdate(sql);
            pst.executeUpdate(sql1);
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuPage.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            //con.close();
            //this.dispose();
        //} catch (SQLException ex) {
            //Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        //}
        DoublePlayerInstructionPage d = new DoublePlayerInstructionPage();
        d.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            
            // TODO add your handling code here:
            
            /*try {
            NewJFrame_1 s = new NewJFrame_1();
            
            } catch (MalformedURLException ex) {
            Logger.getLogger(MainMenuPage.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            // try {
            pst = con.createStatement();
            String sql = "TRUNCATE singleplayeruserentry";
            String sql1 = "TRUNCATE doubleplayeruserentry";
            pst.executeUpdate(sql);
            pst.executeUpdate(sql1);
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuPage.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            //con.close();
            //this.dispose();
        //} catch (SQLException ex) {
            //Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
       // }
        InstructionPageSinglePlayer x = new InstructionPageSinglePlayer();
        x.setVisible(true);

        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            // try {
            pst = con.createStatement();
            String sql = "TRUNCATE singleplayeruserentry";
            String sql1 = "TRUNCATE doubleplayeruserentry";
            pst.executeUpdate(sql);
            pst.executeUpdate(sql1);
            pst.close();
            con.close();
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuPage.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        //} catch (SQLException ex) {
            //Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenuPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
