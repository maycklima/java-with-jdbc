/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package interfaces;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Cabessa
 */
public class DefinirVigencia extends javax.swing.JDialog {

    /**
     * Creates new form AdicionarCategoria
     */
    public DefinirVigencia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtVigenciaAtual = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Definir Vigência");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Em que vigência estamos?");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 20, 200, 20);

        jButton1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jButton1.setText("Definir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(120, 80, 90, 30);

        try {
            txtVigenciaAtual.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtVigenciaAtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVigenciaAtualActionPerformed(evt);
            }
        });
        txtVigenciaAtual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVigenciaAtualKeyTyped(evt);
            }
        });
        getContentPane().add(txtVigenciaAtual);
        txtVigenciaAtual.setBounds(20, 40, 190, 30);

        jLabel2.setBackground(new java.awt.Color(236, 236, 236));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 230, 130);

        setBounds(0, 0, 234, 156);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try
        {
            
           instrucao = conexao.prepareStatement("UPDATE vigencia SET vigencia_atual = ? where id_vigencia = 1");//isso so funciona pra varchar
            
                        
            instrucao.setString (1, txtVigenciaAtual.getText());
            instrucao.executeUpdate();
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setText("Vigência atual definida");
            Main.jComboBoxVigencia.setSelectedItem(txtVigenciaAtual.getText());
            dispose();
        
        }
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
                JOptionPane.showMessageDialog(null, e);                
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try 
        {
            instrucao = conexao.prepareStatement("SELECT vigencia_atual FROM vigencia where id_vigencia = 1");
            resultado = instrucao.executeQuery();            
            if(resultado.next())
            {
               
            txtVigenciaAtual.setText(resultado.getString("vigencia_atual"));
            }
                
        } catch (SQLException e) 
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
                JOptionPane.showMessageDialog(null, e);                
            }
         }
              
    }//GEN-LAST:event_formWindowOpened

    private void txtVigenciaAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVigenciaAtualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVigenciaAtualActionPerformed

    private void txtVigenciaAtualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVigenciaAtualKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVigenciaAtualKeyTyped

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
            java.util.logging.Logger.getLogger(DefinirVigencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DefinirVigencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DefinirVigencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DefinirVigencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DefinirVigencia dialog = new DefinirVigencia(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JFormattedTextField txtVigenciaAtual;
    // End of variables declaration//GEN-END:variables
}
