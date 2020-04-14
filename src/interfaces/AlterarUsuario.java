/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import modelos.FixedLengthDocument;
import modelos.Usuario;
import sql.UsuarioDAO;
import javax.swing.ImageIcon;

/**
 *
 * @author Lucas
 */
public class AlterarUsuario extends javax.swing.JDialog {

    /**
     * Creates new form AlterarUsuario
     */
    public AlterarUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        labelMensagem.setVisible(false);
    }

    public void AlterarUsuario()
    {
        if(txtUsuario.getText().isEmpty() || txtCpf.getText().isEmpty() || txtCpf.getText().equals("   .   .   -  ") || new String (txtSenhaUsuario.getPassword()).equals(""))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Preencha todos os campos");
        }else
        {
            UsuarioDAO usuario = new  UsuarioDAO();
            Usuario u = new Usuario ();
            u.setUsuario(txtUsuario.getText());
            u.setCpf(txtCpf.getText());
            u.setSenha(new String (txtSenhaUsuario.getPassword()));
            usuario.atualizarUsuario( u );
            dispose();
        } 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        labelMensagem = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtSenhaUsuario = new javax.swing.JPasswordField();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar usuário");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Usuário: ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(170, 90, 60, 30);

        txtUsuario.setDocument(new FixedLengthDocument(45));
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(230, 90, 220, 30);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("CPF: ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(190, 140, 40, 30);

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCpfFocusGained(evt);
            }
        });
        getContentPane().add(txtCpf);
        txtCpf.setBounds(230, 140, 220, 30);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Senha:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(180, 190, 50, 30);

        btnSalvar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setToolTipText("Clique para salvar as alterações");
        btnSalvar.setMaximumSize(new java.awt.Dimension(83, 30));
        btnSalvar.setMinimumSize(new java.awt.Dimension(83, 30));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(340, 230, 100, 30);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/AlterarUserGrande.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(40, 0, 100, 100);

        labelMensagem.setBackground(new java.awt.Color(255, 0, 0));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 640, 20);

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Editar usuário");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-10, -10, 650, 70);
        getContentPane().add(txtSenhaUsuario);
        txtSenhaUsuario.setBounds(230, 190, 220, 30);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(0, 60, 630, 260);

        setSize(new java.awt.Dimension(634, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        AlterarUsuario();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        UsuarioDAO usuariodao = new UsuarioDAO();
        usuariodao.getRecuperar();
        txtUsuario.setText(Usuario.getUsuario());
        txtCpf.setText(Usuario.getCpf());
        txtSenhaUsuario.setText(Usuario.getSenha());
    }//GEN-LAST:event_formWindowOpened

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        String caracteres="0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-";
        if(!caracteres.contains(evt.getKeyChar()+""))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtUsuarioFocusGained

    private void txtCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtCpfFocusGained

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
            java.util.logging.Logger.getLogger(AlterarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AlterarUsuario dialog = new AlterarUsuario(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel labelMensagem;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JPasswordField txtSenhaUsuario;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
