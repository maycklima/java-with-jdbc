package interfaces;

import modelos.FixedLengthDocument;
import modelos.Orientador;
import sql.OrientadorDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon; 

public class CadastrarOrientador extends javax.swing.JDialog {

        OrientadorDAO o = new  OrientadorDAO();
        Orientador orientador = new Orientador();
    public CadastrarOrientador(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        labelMensagem.setVisible(false);
    }
    
    public void cadastrarOrientador()
    {
        if(txtNome.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Digite o nome do orientador");
        }else if(txtCpf.getText().equals("   .   .   -  "))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Digite o cpf do orientadir");
        }
        else if(txtEmail.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Digite o email do orientador");
        }
        else
        {   
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            Date date = new Date();
            orientador.setNomeOrientador(txtNome.getText());
            orientador.setCpfOrientador(txtCpf.getText());
            orientador.setTitulacaoOrientador(String.valueOf(jComboBoxTitulacao.getSelectedItem()));
            orientador.setEmailOrientador(txtEmail.getText());
            orientador.setAtualizacao(String.valueOf(dateFormat.format(date)));
            o.inserirOrientador(orientador);
            o.getTodosOrientadores();
            dispose();
                
        } 
    }
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbCriar = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        jComboBoxTitulacao = new javax.swing.JComboBox<>();
        txtEmail = new javax.swing.JTextField();
        Nome = new javax.swing.JLabel();
        Titulação = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        cpf = new javax.swing.JLabel();
        labelMensagem = new javax.swing.JLabel();
        cadastrarOrientador = new javax.swing.JLabel();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Orientador");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(null);

        jbCriar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbCriar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        jbCriar.setText("Cadastrar");
        jbCriar.setToolTipText("Clique para cadastrar o orientador");
        jbCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCriarActionPerformed(evt);
            }
        });
        getContentPane().add(jbCriar);
        jbCriar.setBounds(340, 310, 120, 30);

        txtNome.setDocument(new FixedLengthDocument(65));
        txtNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNomeFocusGained(evt);
            }
        });
        getContentPane().add(txtNome);
        txtNome.setBounds(160, 120, 300, 30);

        jComboBoxTitulacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Prof.", "Téc. Adm." }));
        jComboBoxTitulacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxTitulacaoMouseClicked(evt);
            }
        });
        jComboBoxTitulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTitulacaoActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxTitulacao);
        jComboBoxTitulacao.setBounds(160, 220, 100, 30);

        txtEmail.setDocument(new FixedLengthDocument(50));
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
        });
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });
        getContentPane().add(txtEmail);
        txtEmail.setBounds(160, 270, 300, 30);

        Nome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Nome.setText("Nome:");
        getContentPane().add(Nome);
        Nome.setBounds(110, 120, 50, 30);

        Titulação.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Titulação.setText("Cargo:");
        getContentPane().add(Titulação);
        Titulação.setBounds(110, 220, 50, 30);

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Orientador.png"))); // NOI18N
        getContentPane().add(Logo);
        Logo.setBounds(10, 10, 140, 130);

        Email.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Email.setText("Email:");
        getContentPane().add(Email);
        Email.setBounds(110, 270, 50, 30);

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setText("   .   .   -");
        txtCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCpfFocusGained(evt);
            }
        });
        txtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfActionPerformed(evt);
            }
        });
        txtCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCpfKeyTyped(evt);
            }
        });
        getContentPane().add(txtCpf);
        txtCpf.setBounds(160, 170, 300, 30);

        cpf.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cpf.setText("CPF: ");
        getContentPane().add(cpf);
        cpf.setBounds(120, 170, 40, 30);

        labelMensagem.setBackground(new java.awt.Color(255, 0, 0));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 580, 20);

        cadastrarOrientador.setBackground(new java.awt.Color(204, 204, 204));
        cadastrarOrientador.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        cadastrarOrientador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cadastrarOrientador.setText("Cadastrar orientador");
        cadastrarOrientador.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cadastrarOrientador.setOpaque(true);
        getContentPane().add(cadastrarOrientador);
        cadastrarOrientador.setBounds(-10, -10, 590, 80);

        BG.setBackground(new java.awt.Color(236, 236, 236));
        BG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BG.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        BG.setOpaque(true);
        getContentPane().add(BG);
        BG.setBounds(0, 70, 580, 300);

        setSize(new java.awt.Dimension(585, 394));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCriarActionPerformed
        cadastrarOrientador();
    }//GEN-LAST:event_jbCriarActionPerformed

    private void jComboBoxTitulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTitulacaoActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_jComboBoxTitulacaoActionPerformed

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyTyped

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed

    private void txtCpfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfKeyTyped

    private void txtNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtNomeFocusGained

    private void txtCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusGained
         labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtCpfFocusGained

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtEmailFocusGained

    private void jComboBoxTitulacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxTitulacaoMouseClicked
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_jComboBoxTitulacaoMouseClicked

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
            java.util.logging.Logger.getLogger(CadastrarOrientador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarOrientador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarOrientador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarOrientador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                CadastrarOrientador dialog = new CadastrarOrientador(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel BG;
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel Nome;
    private javax.swing.JLabel Titulação;
    private javax.swing.JLabel cadastrarOrientador;
    private javax.swing.JLabel cpf;
    private javax.swing.JComboBox<String> jComboBoxTitulacao;
    private javax.swing.JButton jbCriar;
    public static javax.swing.JLabel labelMensagem;
    public static javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
