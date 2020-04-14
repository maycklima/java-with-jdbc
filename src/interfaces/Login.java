package interfaces;

import db.DAO;
import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
//import modelos.LerArquivo;
import sql.UsuarioDAO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Login extends javax.swing.JFrame
{
  UsuarioDAO usuario = new UsuarioDAO();
  DAO dao = new DAO();
  boolean lembrar;
  
  public Login() 
  {
        initComponents();
        inicializar();        
  }
  
  public void inicializar(){
      if(dao.DAO())
        {              
            //lembrar();
            msgConexao.setText("Conexão com o servidor: OK");
            msgConexao.setBackground(new Color(153,153,153));
        }else{
            txtUsuario.setEnabled(false);
            txtSenha.setEnabled(false);
            btnFazerLogin.setEnabled(false);
            recuperar.setVisible(false);
            msgConexao.setBackground(new Color(230,0,0));
            msgConexao.setText("A conexão com o servidor falhou");         
        } 
  }
  public void fazerLogin() throws IOException
  {
      if(txtUsuario.getText().isEmpty())
            {
                msgConexao.setText("Digite seu usuário");
                msgConexao.setBackground(new Color(230,0,0));
            }
            else if (new String (txtSenha.getPassword()).equals(""))
            {
                msgConexao.setText("Digite sua senha");
                msgConexao.setBackground(new Color(230,0,0));
            }
            else
            {
                 UsuarioDAO u = new UsuarioDAO();
                 //lembrar = cbRelembrar.isSelected();
                 u.verificarLogin(txtUsuario.getText(), new String (txtSenha.getPassword()));
                 dispose();
            }
  }
  /*
  public void lembrar()
  {
      LerArquivo ler = new LerArquivo();
      try
        {
            
            instrucao = conexao.prepareStatement( "SELECT usuario FROM contas WHERE usuario = '" + ler.lerUsuario() + "' and lembrar = 1");
            resultado  = instrucao .executeQuery();
            
                if(resultado.next()) // Next retorna 0 ou 1 como resultado da consuta
                {
                     String usuarioLembrar = (resultado.getString("usuario"));
                     txtUsuario.setText(usuarioLembrar);

                }else
                {
                    txtUsuario.setText("");
                }
            
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
  }
  */
  
  public void abrirRecuperar()
  {
        JFrame mainFrame = new JFrame();
       RecuperarSenha rS = new RecuperarSenha(mainFrame, true);
       rS.setLocationRelativeTo(mainFrame);
       rS.setVisible(true);
  }
 
 @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFazerLogin = new javax.swing.JButton();
        nome = new javax.swing.JLabel();
        nome1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        SGPP = new javax.swing.JLabel();
        Login = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        msgConexao = new javax.swing.JLabel();
        recuperar = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(null);

        btnFazerLogin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnFazerLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/entrar.png"))); // NOI18N
        btnFazerLogin.setText("Entrar");
        btnFazerLogin.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFazerLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFazerLoginActionPerformed(evt);
            }
        });
        btnFazerLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnFazerLoginKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnFazerLoginKeyTyped(evt);
            }
        });
        getContentPane().add(btnFazerLogin);
        btnFazerLogin.setBounds(310, 260, 90, 30);

        nome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/userlogin.png"))); // NOI18N
        getContentPane().add(nome);
        nome.setBounds(150, 180, 30, 30);

        nome1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/passlogin.png"))); // NOI18N
        getContentPane().add(nome1);
        nome1.setBounds(150, 220, 30, 30);

        txtUsuario.setText("mayck");
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }
        });
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(180, 180, 220, 30);

        txtSenha.setText("0000");
        txtSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSenhaFocusGained(evt);
            }
        });
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSenhaKeyTyped(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(180, 220, 220, 30);

        SGPP.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        SGPP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SGPP.setText("SGPP");
        getContentPane().add(SGPP);
        SGPP.setBounds(60, 90, 480, 60);

        Login.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Login.setText("Login");
        getContentPane().add(Login);
        Login.setBounds(100, 120, 450, 40);

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon.png"))); // NOI18N
        getContentPane().add(icon);
        icon.setBounds(180, 30, 170, 130);

        msgConexao.setBackground(new java.awt.Color(153, 153, 153));
        msgConexao.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        msgConexao.setForeground(new java.awt.Color(255, 255, 255));
        msgConexao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msgConexao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        msgConexao.setOpaque(true);
        getContentPane().add(msgConexao);
        msgConexao.setBounds(-10, -10, 590, 40);

        recuperar.setText("Esqueci minha senha");
        recuperar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recuperarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                recuperarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                recuperarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                recuperarMousePressed(evt);
            }
        });
        getContentPane().add(recuperar);
        recuperar.setBounds(180, 250, 130, 14);

        bg.setBackground(new java.awt.Color(204, 204, 204));
        bg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bg.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(0, 0, 570, 370);

        setSize(new java.awt.Dimension(576, 391));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFazerLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFazerLoginActionPerformed
      try {
          fazerLogin();
      } catch (IOException ex) 
      {
          JOptionPane.showMessageDialog(null, ex);
      }
    }//GEN-LAST:event_btnFazerLoginActionPerformed
 
        
    private void btnFazerLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFazerLoginKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            try {
                fazerLogin();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnFazerLoginKeyPressed

    private void btnFazerLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFazerLoginKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            try {
                fazerLogin();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnFazerLoginKeyTyped

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            try {
                fazerLogin();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            try {
                fazerLogin();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed

    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained
        
    }//GEN-LAST:event_txtUsuarioFocusGained

    private void recuperarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recuperarMouseEntered
        recuperar.setText("<html><u>Esqueci minha senha</u>");        // TODO add your handling code here:
    }//GEN-LAST:event_recuperarMouseEntered

    private void recuperarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recuperarMouseExited
        recuperar.setText("Esqueci minha senha"); 
    }//GEN-LAST:event_recuperarMouseExited

    private void recuperarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recuperarMouseClicked
            abrirRecuperar();
    }//GEN-LAST:event_recuperarMouseClicked

    private void recuperarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recuperarMousePressed
            abrirRecuperar();
    }//GEN-LAST:event_recuperarMousePressed

    private void txtSenhaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            try {
                fazerLogin();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSenhaKeyTyped

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            try {
                fazerLogin();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed

    }//GEN-LAST:event_txtSenhaActionPerformed

    private void txtSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusGained

    }//GEN-LAST:event_txtSenhaFocusGained

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
                 //txtSenha.requestFocus();
                
                
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Login;
    private javax.swing.JLabel SGPP;
    private javax.swing.JLabel bg;
    private static javax.swing.JButton btnFazerLogin;
    private javax.swing.JLabel icon;
    public static javax.swing.JLabel msgConexao;
    private javax.swing.JLabel nome;
    private javax.swing.JLabel nome1;
    private javax.swing.JLabel recuperar;
    private static javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
