package interfaces;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import modelos.Aluno;
import modelos.FixedLengthDocument;
import sql.AlunoDAO;
import java.awt.Color;
import java.awt.event.KeyEvent; 
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CadastrarAluno extends javax.swing.JDialog {


    public CadastrarAluno(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        populaComboBoxCurso();
    }
    public static void populaComboBoxCurso()
    {
        String sql = "SELECT nome_curso FROM cursos order by nome_curso asc";
        try 
        {
            db.DAO.instrucao = db.DAO.conexao.prepareStatement(sql);
            db.DAO.resultado = db.DAO.instrucao.executeQuery();
            
            while (db.DAO.resultado.next())
            {
                JComboBoxCurso.addItem(db.DAO.resultado.getString("nome_curso"));  
                
            }
            
        } catch (SQLException e) 
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente");
                System.exit(0);
            }else
            {
                JOptionPane.showMessageDialog(null, e);                
            }
         }
               
    }
    public void cadastroAluno()
    {
        if(txtNome.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Digite o nome do aluno");
            labelMensagem.setBackground(new Color(255,0,0));
        } else if(txtCpf.getText().equals("   .   .   -  "))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Digite o cpf do aluno");
            labelMensagem.setBackground(new Color(255,0,0));
        } else if(txtEmail.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Digite o email do aluno");
            labelMensagem.setBackground(new Color(255,0,0));
        } 
        else
        {
            Aluno a = new Aluno();
            AlunoDAO aD = new AlunoDAO();
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            Date date = new Date();
            
            a.setNome(txtNome.getText());
            a.setCpf(txtCpf.getText());
            a.setCurso(String.valueOf(JComboBoxCurso.getSelectedItem()));
            a.setEmail(txtEmail.getText());
            a.setBanco(txtBanco.getText());
            a.setAgencia(txtAgencia.getText());
            a.setConta(txtConta.getText());
            a.setAtualizacao(String.valueOf(dateFormat.format(date)));
            aD.inserirAluno(a);
            aD.getTodosAlunos("");
            dispose();
        }
       
    }


  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbCriar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        JComboBoxCurso = new javax.swing.JComboBox<>();
        dadosBancarios = new javax.swing.JLabel();
        agencia = new javax.swing.JLabel();
        txtAgencia = new javax.swing.JTextField();
        conta = new javax.swing.JLabel();
        txtConta = new javax.swing.JTextField();
        banco = new javax.swing.JLabel();
        nome = new javax.swing.JLabel();
        curso = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        cpf = new javax.swing.JLabel();
        img = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        txtBanco = new javax.swing.JTextField();
        addCurso = new javax.swing.JButton();
        removeCurso = new javax.swing.JButton();
        labelMensagem = new javax.swing.JLabel();
        cadastrarAluno = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Aluno");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
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
        jbCriar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        jbCriar.setText("Cadastrar");
        jbCriar.setToolTipText("Clique para cadastrar o aluno");
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
        jbCriar.setBounds(310, 470, 120, 30);

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(130, 320, 310, 10);

        txtNome.setDocument(new FixedLengthDocument(65));
        txtNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNomeFocusGained(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomeKeyTyped(evt);
            }
        });
        getContentPane().add(txtNome);
        txtNome.setBounds(130, 90, 300, 30);

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
        txtEmail.setBounds(130, 280, 300, 30);

        JComboBoxCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JComboBoxCursoMouseClicked(evt);
            }
        });
        JComboBoxCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBoxCursoActionPerformed(evt);
            }
        });
        getContentPane().add(JComboBoxCurso);
        JComboBoxCurso.setBounds(130, 210, 300, 30);

        dadosBancarios.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dadosBancarios.setText("Dados Bancários (Opcional)");
        getContentPane().add(dadosBancarios);
        dadosBancarios.setBounds(140, 320, 270, 30);

        agencia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        agencia.setText("Agência:");
        getContentPane().add(agencia);
        agencia.setBounds(170, 390, 60, 30);

        txtAgencia.setDocument(new FixedLengthDocument(10));
        txtAgencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAgenciaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgenciaKeyTyped(evt);
            }
        });
        getContentPane().add(txtAgencia);
        txtAgencia.setBounds(230, 390, 200, 30);

        conta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        conta.setText("Conta:");
        getContentPane().add(conta);
        conta.setBounds(180, 430, 50, 30);

        txtConta.setDocument(new FixedLengthDocument(20));
        txtConta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContaKeyTyped(evt);
            }
        });
        getContentPane().add(txtConta);
        txtConta.setBounds(230, 430, 200, 30);

        banco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        banco.setText("Banco:");
        getContentPane().add(banco);
        banco.setBounds(180, 350, 50, 30);

        nome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome.setText("*Nome");
        getContentPane().add(nome);
        nome.setBounds(130, 70, 50, 20);

        curso.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        curso.setText("*Curso:");
        getContentPane().add(curso);
        curso.setBounds(130, 190, 50, 20);

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCpf.setText("   .   .   -");
        txtCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCpfFocusGained(evt);
            }
        });
        txtCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCpfKeyPressed(evt);
            }
        });
        getContentPane().add(txtCpf);
        txtCpf.setBounds(130, 150, 300, 30);

        cpf.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cpf.setText("*CPF: ");
        getContentPane().add(cpf);
        cpf.setBounds(130, 130, 50, 20);

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Aluno.png"))); // NOI18N
        getContentPane().add(img);
        img.setBounds(0, 0, 140, 130);

        email.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        email.setText("*Email:");
        getContentPane().add(email);
        email.setBounds(130, 260, 50, 20);

        txtBanco.setDocument(new FixedLengthDocument(25));
        txtBanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBancoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBancoKeyTyped(evt);
            }
        });
        getContentPane().add(txtBanco);
        txtBanco.setBounds(230, 350, 200, 30);

        addCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        addCurso.setToolTipText("Clique para adicionar um novo curso à lista");
        addCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCursoActionPerformed(evt);
            }
        });
        getContentPane().add(addCurso);
        addCurso.setBounds(320, 240, 50, 30);

        removeCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete_projeto.png"))); // NOI18N
        removeCurso.setToolTipText("Clique para remover esse curso da lista");
        removeCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCursoActionPerformed(evt);
            }
        });
        getContentPane().add(removeCurso);
        removeCurso.setBounds(380, 240, 50, 30);

        labelMensagem.setBackground(new java.awt.Color(255, 0, 0));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 550, 20);

        cadastrarAluno.setBackground(new java.awt.Color(204, 204, 204));
        cadastrarAluno.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        cadastrarAluno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cadastrarAluno.setText("Cadastrar aluno");
        cadastrarAluno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cadastrarAluno.setOpaque(true);
        getContentPane().add(cadastrarAluno);
        cadastrarAluno.setBounds(-10, -10, 560, 70);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bg.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(0, 60, 550, 460);

        setSize(new java.awt.Dimension(550, 546));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCriarActionPerformed
        cadastroAluno();
    }//GEN-LAST:event_jbCriarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
       
    }//GEN-LAST:event_formKeyTyped

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_txtNomeKeyPressed

    private void txtNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_txtNomeKeyTyped

    private void txtCpfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_txtCpfKeyPressed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_txtEmailKeyTyped

    private void txtBancoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBancoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_txtBancoKeyPressed

    private void txtBancoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBancoKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_txtBancoKeyTyped

    private void txtAgenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgenciaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_txtAgenciaKeyPressed

    private void txtAgenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgenciaKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_txtAgenciaKeyTyped

    private void txtContaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_txtContaKeyPressed

    private void txtContaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContaKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_txtContaKeyTyped

    private void jbCriarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbCriarKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_jbCriarKeyPressed

    private void jbCriarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbCriarKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cadastroAluno();
        }
    }//GEN-LAST:event_jbCriarKeyTyped

    private void addCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCursoActionPerformed
        JFrame mainFrame = new JFrame();
        CadastrarCurso CadastroCurso = new CadastrarCurso(mainFrame, true);
        CadastroCurso.setLocationRelativeTo(mainFrame);
        CadastroCurso.setVisible(true);
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_addCursoActionPerformed

    private void removeCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCursoActionPerformed
            int resposta = JOptionPane.showConfirmDialog(null, "Pode haver outros alunos cadastrados neste curso. Deseja removê-lo assim mesmo?","Remover curso", JOptionPane.YES_NO_OPTION);

            if(resposta == 0)
            {
                try
                {
                    instrucao = conexao.prepareStatement("DELETE FROM cursos WHERE nome_curso = '" + JComboBoxCurso.getSelectedItem() + "'");
                    instrucao.execute();
                    JComboBoxCurso.removeAllItems();
                    populaComboBoxCurso();
                    
                    labelMensagem.setVisible(true);
                    labelMensagem.setText("Curso removido");
                    labelMensagem.setBackground(new Color(0,204,51));

                }
                catch( SQLException e )
                {
                    if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                    {              
                        JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente");
                        System.exit(0);
                    }else
                    {
                        JOptionPane.showMessageDialog(null, e);                
                    }
                }
            }else if(resposta == 1 )
            {

            }
            else
            {

            }
            labelMensagem.setVisible(false);
    }//GEN-LAST:event_removeCursoActionPerformed

    private void txtNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtNomeFocusGained

    private void txtCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusGained
         labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtCpfFocusGained

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtEmailFocusGained

    private void JComboBoxCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboBoxCursoActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_JComboBoxCursoActionPerformed

    private void JComboBoxCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboBoxCursoMouseClicked
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_JComboBoxCursoMouseClicked

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
            java.util.logging.Logger.getLogger(CadastrarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                CadastrarAluno dialog = new CadastrarAluno(new javax.swing.JFrame(), true);
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
    public static javax.swing.JComboBox<String> JComboBoxCurso;
    private javax.swing.JButton addCurso;
    private javax.swing.JLabel agencia;
    private javax.swing.JLabel banco;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel cadastrarAluno;
    private javax.swing.JLabel conta;
    private javax.swing.JLabel cpf;
    private javax.swing.JLabel curso;
    private javax.swing.JLabel dadosBancarios;
    private javax.swing.JLabel email;
    private javax.swing.JLabel img;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbCriar;
    public static javax.swing.JLabel labelMensagem;
    private javax.swing.JLabel nome;
    private javax.swing.JButton removeCurso;
    private javax.swing.JTextField txtAgencia;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtConta;
    public static javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
