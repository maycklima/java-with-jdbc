package interfaces;

import static interfaces.RegistroDigital.jTableRegistroDigital;
import static interfaces.RegistroDigital.linhaRegistro;
import modelos.FixedLengthDocument;
import modelos.Registro;
import sql.RegistroDAO;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class AlterarRegistro extends javax.swing.JDialog {
    
    

    public AlterarRegistro(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        inicializar();
    }
    
    public void inicializar()
    {
        if(jbAutomatico.isSelected()){
            txtRegistro.setEnabled(false);
        }else{
            txtRegistro.setEnabled(true);
        }
        labelMensagem.setVisible(false);
    }
    public void atualizar()
    {
        if(txtRegistro.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("insira o número do registro");
        } else if(txtDescricao.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("insira a descrição do registro");
        } else if(txtNomeParticipante.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("insira o nome do participante");
        } else if(txtDataRegistro.getText().equals("  /  /    "))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("insira a data de registro");
        } else if(txtEmail.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("insira um email para o registro");
        }
        else
        {   
            Registro r = new Registro();
            RegistroDAO rD = new RegistroDAO();
            
            r.setNregistro(txtRegistro.getText());
            r.setNome(txtNomeParticipante.getText());
            r.setDescricao(txtDescricao.getText());
            if(txtDataRegistro.getText().equals("  /  /    "))
            {
                r.setData_Registro("");
            }else{
            r.setData_Registro(txtDataRegistro.getText());                
            }            
            r.setEmail(txtEmail.getText());
            if(txtDataEntrega.getText().equals("  /  /    "))
            {
                r.setData_entrega("");
            }else{
            r.setData_entrega(txtDataEntrega.getText());                
            }    
            r.setSituacao(jcbEnviado.isSelected());
            rD.atualizarRegistro(r, RegistroDigital.linhaRegistro);
            linhaRegistro = "0";
            rD.getRegistro("");
            dispose();
        }
       
    }


  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nregistro = new javax.swing.ButtonGroup();
        jbCriar = new javax.swing.JButton();
        txtRegistro = new javax.swing.JTextField();
        nRegistro = new javax.swing.JLabel();
        dataRegistro = new javax.swing.JLabel();
        txtNomeParticipante = new javax.swing.JTextField();
        nomeParticipante = new javax.swing.JLabel();
        descricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        jbAutomatico = new javax.swing.JRadioButton();
        jbManual = new javax.swing.JRadioButton();
        txtEmail = new javax.swing.JTextField();
        email = new javax.swing.JLabel();
        dataEntrega = new javax.swing.JLabel();
        dataEntrega1 = new javax.swing.JLabel();
        labelMensagem = new javax.swing.JLabel();
        jcbEnviado = new javax.swing.JCheckBox();
        txtDataRegistro = new javax.swing.JFormattedTextField();
        cadastrarAluno = new javax.swing.JLabel();
        txtDataEntrega = new javax.swing.JFormattedTextField();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar registro");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        getContentPane().setLayout(null);

        jbCriar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbCriar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jbCriar.setText("Salvar");
        jbCriar.setToolTipText("Clique para salvar as alterações");
        jbCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCriarActionPerformed(evt);
            }
        });
        jbCriar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbCriarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jbCriarKeyTyped(evt);
            }
        });
        getContentPane().add(jbCriar);
        jbCriar.setBounds(300, 460, 120, 30);

        txtRegistro.setDocument(new FixedLengthDocument(6));
        txtRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRegistroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRegistroKeyTyped(evt);
            }
        });
        getContentPane().add(txtRegistro);
        txtRegistro.setBounds(70, 110, 130, 30);

        nRegistro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nRegistro.setText("Nº Registro");
        getContentPane().add(nRegistro);
        nRegistro.setBounds(70, 70, 110, 20);

        dataRegistro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dataRegistro.setText("Data Registro");
        getContentPane().add(dataRegistro);
        dataRegistro.setBounds(70, 260, 100, 20);

        txtNomeParticipante.setDocument(new FixedLengthDocument(65));
        txtNomeParticipante.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNomeParticipanteFocusGained(evt);
            }
        });
        getContentPane().add(txtNomeParticipante);
        txtNomeParticipante.setBounds(70, 160, 320, 30);

        nomeParticipante.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nomeParticipante.setText("Nome Participante");
        getContentPane().add(nomeParticipante);
        nomeParticipante.setBounds(70, 140, 160, 20);

        descricao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        descricao.setText("Descrição");
        getContentPane().add(descricao);
        descricao.setBounds(70, 200, 130, 20);

        txtDescricao.setDocument(new FixedLengthDocument(255));
        txtDescricao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescricaoFocusGained(evt);
            }
        });
        getContentPane().add(txtDescricao);
        txtDescricao.setBounds(70, 220, 320, 30);

        nregistro.add(jbAutomatico);
        jbAutomatico.setSelected(true);
        jbAutomatico.setText("Automático");
        jbAutomatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAutomaticoActionPerformed(evt);
            }
        });
        getContentPane().add(jbAutomatico);
        jbAutomatico.setBounds(70, 90, 90, 23);

        nregistro.add(jbManual);
        jbManual.setText("Manual");
        jbManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbManualActionPerformed(evt);
            }
        });
        getContentPane().add(jbManual);
        jbManual.setBounds(160, 90, 90, 20);

        txtEmail.setDocument(new FixedLengthDocument(50));
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });
        getContentPane().add(txtEmail);
        txtEmail.setBounds(70, 340, 320, 30);

        email.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        email.setText("Email:");
        getContentPane().add(email);
        email.setBounds(70, 320, 60, 20);

        dataEntrega.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dataEntrega.setText("Data Entrega");
        getContentPane().add(dataEntrega);
        dataEntrega.setBounds(70, 380, 120, 20);

        dataEntrega1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dataEntrega1.setText("Situação");
        getContentPane().add(dataEntrega1);
        dataEntrega1.setBounds(70, 440, 80, 20);

        labelMensagem.setBackground(new java.awt.Color(255, 0, 0));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 440, 20);

        jcbEnviado.setText("Enviado");
        jcbEnviado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbEnviadoActionPerformed(evt);
            }
        });
        getContentPane().add(jcbEnviado);
        jcbEnviado.setBounds(70, 460, 81, 23);

        try {
            txtDataRegistro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataRegistro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataRegistroFocusGained(evt);
            }
        });
        txtDataRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDataRegistroKeyPressed(evt);
            }
        });
        getContentPane().add(txtDataRegistro);
        txtDataRegistro.setBounds(70, 280, 80, 30);

        cadastrarAluno.setBackground(new java.awt.Color(204, 204, 204));
        cadastrarAluno.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        cadastrarAluno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cadastrarAluno.setText("Editar registro");
        cadastrarAluno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cadastrarAluno.setOpaque(true);
        getContentPane().add(cadastrarAluno);
        cadastrarAluno.setBounds(-10, -10, 460, 70);

        try {
            txtDataEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataEntrega.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataEntrega.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataEntregaFocusGained(evt);
            }
        });
        txtDataEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataEntregaActionPerformed(evt);
            }
        });
        txtDataEntrega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDataEntregaKeyPressed(evt);
            }
        });
        getContentPane().add(txtDataEntrega);
        txtDataEntrega.setBounds(70, 400, 80, 30);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bg.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(0, 60, 470, 450);

        setSize(new java.awt.Dimension(449, 534));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCriarActionPerformed
        atualizar();
    }//GEN-LAST:event_jbCriarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
       
    }//GEN-LAST:event_formKeyTyped

    private void txtRegistroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRegistroKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            atualizar();
        }
    }//GEN-LAST:event_txtRegistroKeyPressed

    private void txtRegistroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRegistroKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            atualizar();
        }
    }//GEN-LAST:event_txtRegistroKeyTyped

    private void jbCriarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbCriarKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            atualizar();
        }
    }//GEN-LAST:event_jbCriarKeyPressed

    private void jbCriarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbCriarKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            atualizar();
        }
    }//GEN-LAST:event_jbCriarKeyTyped

    private void jbAutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAutomaticoActionPerformed
        txtRegistro.setEnabled(false);
    }//GEN-LAST:event_jbAutomaticoActionPerformed

    private void jbManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbManualActionPerformed
        txtRegistro.setEnabled(true);
    }//GEN-LAST:event_jbManualActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
            RegistroDigital.linha_selecionada = jTableRegistroDigital.getSelectedRow();
          
            txtRegistro.setText(String.valueOf(jTableRegistroDigital.getValueAt(RegistroDigital.linha_selecionada, 0)));
            txtNomeParticipante.setText(String.valueOf(jTableRegistroDigital.getValueAt(RegistroDigital.linha_selecionada, 1)));
            txtDescricao.setText(String.valueOf(jTableRegistroDigital.getValueAt(RegistroDigital.linha_selecionada, 2)));
            txtDataRegistro.setText(String.valueOf(jTableRegistroDigital.getValueAt(RegistroDigital.linha_selecionada, 3)));
            txtEmail.setText(String.valueOf(jTableRegistroDigital.getValueAt(RegistroDigital.linha_selecionada, 4)));
            
            if(String.valueOf(jTableRegistroDigital.getValueAt(RegistroDigital.linha_selecionada, 5)).equals("null"))
            {
                
            }else{            
            txtDataEntrega.setText(String.valueOf(jTableRegistroDigital.getValueAt(RegistroDigital.linha_selecionada, 5)));
            }
            
            if(String.valueOf(jTableRegistroDigital.getValueAt(RegistroDigital.linha_selecionada, 6)).equals("ENVIADO"))
            {
             jcbEnviado.setSelected(true);
            }else if(String.valueOf(jTableRegistroDigital.getValueAt(RegistroDigital.linha_selecionada, 6)).equals("null")){
             jcbEnviado.setSelected(false);
            }
    }//GEN-LAST:event_formWindowOpened

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyTyped

    private void txtDataRegistroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataRegistroKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataRegistroKeyPressed

    private void txtNomeParticipanteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeParticipanteFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtNomeParticipanteFocusGained

    private void txtDescricaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescricaoFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtDescricaoFocusGained

    private void txtDataRegistroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataRegistroFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtDataRegistroFocusGained

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtEmailFocusGained

    private void jcbEnviadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbEnviadoActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_jcbEnviadoActionPerformed

    private void txtDataEntregaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataEntregaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataEntregaKeyPressed

    private void txtDataEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataEntregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataEntregaActionPerformed

    private void txtDataEntregaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataEntregaFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtDataEntregaFocusGained

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
            java.util.logging.Logger.getLogger(AlterarRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                AlterarRegistro dialog = new AlterarRegistro(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel cadastrarAluno;
    private javax.swing.JLabel dataEntrega;
    private javax.swing.JLabel dataEntrega1;
    private javax.swing.JLabel dataRegistro;
    private javax.swing.JLabel descricao;
    private javax.swing.JLabel email;
    private javax.swing.JRadioButton jbAutomatico;
    private javax.swing.JButton jbCriar;
    private javax.swing.JRadioButton jbManual;
    private javax.swing.JCheckBox jcbEnviado;
    public static javax.swing.JLabel labelMensagem;
    private javax.swing.JLabel nRegistro;
    private javax.swing.JLabel nomeParticipante;
    private javax.swing.ButtonGroup nregistro;
    public static javax.swing.JFormattedTextField txtDataEntrega;
    public static javax.swing.JFormattedTextField txtDataRegistro;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNomeParticipante;
    private javax.swing.JTextField txtRegistro;
    // End of variables declaration//GEN-END:variables
}
