package interfaces;

import modelos.FixedLengthDocument;
import modelos.Usuario;
import sql.UsuarioDAO;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
 
public class CadastrarUsuario extends javax.swing.JDialog {
    
    String caminho_assinatura = "";
    JFileChooser abrir = new JFileChooser();

    public CadastrarUsuario(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        labelMensagem.setVisible(false);
    }
    public void cadastrarUsuario()
    {
         if(txtUsuario.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Insira o nome do usuário");   
        }
        else if(txtCpf.getText().isEmpty() || txtCpf.getText().equals("   .   .   -  "))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Insira o cpf do usuário");
        }
        else if(new String (txtSenha.getPassword()).equals(""))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Insira a senha do usuário");
        }
        else if(new String (txtConfirmacao.getPassword()).equals(""))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Insira a confirmação da senha");
        }  
        else if(!new String (txtConfirmacao.getPassword()).equals(new String (txtSenha.getPassword())))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("As senha não coincidem");
        }else
        {
            UsuarioDAO usuario = new  UsuarioDAO();
            Usuario u = new Usuario ();
            u.setUsuario(txtUsuario.getText());
            u.setCpf(txtCpf.getText());
            u.setSenha(new String (txtSenha.getPassword()));
            usuario.inserirUsuario( u );
            dispose();    
        }     
    }

  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonFazerrLoguin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtConfirmacao = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        labelMensagem = new javax.swing.JLabel();
        novoUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo usuário");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(null);

        jButtonFazerrLoguin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonFazerrLoguin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        jButtonFazerrLoguin.setText("Criar");
        jButtonFazerrLoguin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFazerrLoguinActionPerformed(evt);
            }
        });
        jButtonFazerrLoguin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonFazerrLoguinKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButtonFazerrLoguinKeyTyped(evt);
            }
        });
        getContentPane().add(jButtonFazerrLoguin);
        jButtonFazerrLoguin.setBounds(430, 290, 90, 30);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Usuário:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(130, 90, 60, 30);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Senha:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(140, 190, 50, 30);

        txtSenha.setDocument(new FixedLengthDocument(45));
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
        txtSenha.setBounds(190, 190, 200, 30);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("CPF: ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(150, 140, 40, 30);

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
        txtCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCpfKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCpfKeyTyped(evt);
            }
        });
        getContentPane().add(txtCpf);
        txtCpf.setBounds(190, 140, 200, 30);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/newAccountGrante.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(40, 10, 90, 90);

        txtConfirmacao.setDocument(new FixedLengthDocument(45));
        txtConfirmacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtConfirmacaoFocusGained(evt);
            }
        });
        txtConfirmacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmacaoActionPerformed(evt);
            }
        });
        txtConfirmacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConfirmacaoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConfirmacaoKeyTyped(evt);
            }
        });
        getContentPane().add(txtConfirmacao);
        txtConfirmacao.setBounds(190, 230, 200, 30);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Confirmação:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(100, 230, 90, 30);

        txtUsuario.setDocument(new FixedLengthDocument(45));
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
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
        txtUsuario.setBounds(190, 90, 200, 30);

        labelMensagem.setBackground(new java.awt.Color(255, 0, 0));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 550, 20);

        novoUsuario.setBackground(new java.awt.Color(204, 204, 204));
        novoUsuario.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        novoUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        novoUsuario.setText("Novo usuário");
        novoUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        novoUsuario.setOpaque(true);
        getContentPane().add(novoUsuario);
        novoUsuario.setBounds(-10, -10, 560, 80);

        setSize(new java.awt.Dimension(549, 373));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFazerrLoguinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFazerrLoguinActionPerformed
        cadastrarUsuario();
    }//GEN-LAST:event_jButtonFazerrLoguinActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void txtCpfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyTyped
         if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastrarUsuario();
        }
    }//GEN-LAST:event_txtCpfKeyTyped

    private void txtConfirmacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmacaoActionPerformed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastrarUsuario();
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastrarUsuario();
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtCpfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastrarUsuario();
        }
    }//GEN-LAST:event_txtCpfKeyPressed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastrarUsuario();
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtSenhaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastrarUsuario();
        }
    }//GEN-LAST:event_txtSenhaKeyTyped

    private void txtConfirmacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmacaoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastrarUsuario();
        }
    }//GEN-LAST:event_txtConfirmacaoKeyPressed

    private void txtConfirmacaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmacaoKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastrarUsuario();
        }
    }//GEN-LAST:event_txtConfirmacaoKeyTyped

    private void jButtonFazerrLoguinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonFazerrLoguinKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastrarUsuario();
        }
    }//GEN-LAST:event_jButtonFazerrLoguinKeyPressed

    private void jButtonFazerrLoguinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonFazerrLoguinKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastrarUsuario();
        }
    }//GEN-LAST:event_jButtonFazerrLoguinKeyTyped

    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtUsuarioFocusGained

    private void txtCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtCpfFocusGained

    private void txtSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtSenhaFocusGained

    private void txtConfirmacaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConfirmacaoFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtConfirmacaoFocusGained

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
            java.util.logging.Logger.getLogger(CadastrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastrarUsuario dialog = new CadastrarUsuario(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonFazerrLoguin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel labelMensagem;
    private javax.swing.JLabel novoUsuario;
    private javax.swing.JPasswordField txtConfirmacao;
    public static javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
