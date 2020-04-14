package interfaces;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import static interfaces.TodosAlunos.jTableAlunos;
import modelos.Aluno;
import modelos.FixedLengthDocument;
import sql.AlunoDAO;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AlterarAluno extends javax.swing.JDialog {


    public AlterarAluno(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        populaComboBoxCurso();
        labelMensagem.setVisible(false);
                
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
                BoxCurso.addItem(db.DAO.resultado.getString("nome_curso"));  
                
            }
            
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
         }
               
    }
    
    public void alterarAluno()
    {
        if(txtNome.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Insira o nome do aluno");
        } else if(txtCpf.getText().equals("   .   .   -  "))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Insira o cpf do aluno");
        }  else if(txtEmail.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Insira o email do aluno");
        }
        else 
        {            
            AlunoDAO ad = new  AlunoDAO();        
            Aluno a = new Aluno();
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            Date date = new Date();
        
            a.setNome(txtNome.getText());
            a.setCpf(txtCpf.getText());
            a.setCurso(String.valueOf(BoxCurso.getSelectedItem()));
            a.setEmail(txtEmail.getText());
            a.setBanco(txtBanco.getText());
            a.setAgencia(txtAgencia.getText());
            a.setConta(txtConta.getText());
            a.setAtualizacao(String.valueOf(dateFormat.format(date)));
        
        
            ad.atualizarAluno(a, TodosAlunos.linhaAluno);
            ad.getTodosAlunos("");            
            dispose();
        } 
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        img = new javax.swing.JLabel();
        jbAlterarAluno = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
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
        email = new javax.swing.JLabel();
        BoxCurso = new javax.swing.JComboBox<>();
        txtBanco = new javax.swing.JTextField();
        labelMensagem = new javax.swing.JLabel();
        alterarAluno = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar aluno");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Aluno.png"))); // NOI18N
        getContentPane().add(img);
        img.setBounds(10, 10, 140, 130);

        jbAlterarAluno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbAlterarAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jbAlterarAluno.setText("Salvar");
        jbAlterarAluno.setToolTipText("Clique para salvar suas alterações");
        jbAlterarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAlterarAlunoActionPerformed(evt);
            }
        });
        getContentPane().add(jbAlterarAluno);
        jbAlterarAluno.setBounds(330, 450, 120, 30);

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(150, 300, 370, 10);

        txtNome.setDocument(new FixedLengthDocument(65));
        txtNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNomeFocusGained(evt);
            }
        });
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
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
        txtNome.setBounds(150, 90, 300, 30);

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
        txtEmail.setBounds(150, 260, 300, 30);

        dadosBancarios.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dadosBancarios.setText("Dados Bancários (Opcional)");
        getContentPane().add(dadosBancarios);
        dadosBancarios.setBounds(160, 300, 250, 30);

        agencia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        agencia.setText("Agência:");
        getContentPane().add(agencia);
        agencia.setBounds(190, 370, 60, 30);

        txtAgencia.setDocument(new FixedLengthDocument(10));
        txtAgencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgenciaActionPerformed(evt);
            }
        });
        txtAgencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgenciaKeyTyped(evt);
            }
        });
        getContentPane().add(txtAgencia);
        txtAgencia.setBounds(250, 370, 200, 30);

        conta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        conta.setText("Conta:");
        getContentPane().add(conta);
        conta.setBounds(200, 410, 50, 30);

        txtConta.setDocument(new FixedLengthDocument(20));
        txtConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContaActionPerformed(evt);
            }
        });
        txtConta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContaKeyTyped(evt);
            }
        });
        getContentPane().add(txtConta);
        txtConta.setBounds(250, 410, 200, 30);

        banco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        banco.setText("Banco:");
        getContentPane().add(banco);
        banco.setBounds(200, 330, 50, 30);

        nome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome.setText("*Nome");
        getContentPane().add(nome);
        nome.setBounds(150, 70, 50, 20);

        curso.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        curso.setText("*Curso:");
        getContentPane().add(curso);
        curso.setBounds(150, 180, 50, 20);

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
        txtCpf.setBounds(150, 150, 300, 30);

        cpf.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cpf.setText("*CPF: ");
        getContentPane().add(cpf);
        cpf.setBounds(150, 130, 50, 20);

        email.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        email.setText("*Email:");
        getContentPane().add(email);
        email.setBounds(150, 240, 50, 20);

        BoxCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BoxCursoMouseClicked(evt);
            }
        });
        BoxCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxCursoActionPerformed(evt);
            }
        });
        getContentPane().add(BoxCurso);
        BoxCurso.setBounds(150, 200, 300, 30);

        txtBanco.setDocument(new FixedLengthDocument(25));
        txtBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBancoActionPerformed(evt);
            }
        });
        txtBanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBancoKeyTyped(evt);
            }
        });
        getContentPane().add(txtBanco);
        txtBanco.setBounds(250, 330, 200, 30);

        labelMensagem.setBackground(new java.awt.Color(255, 0, 0));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 550, 20);

        alterarAluno.setBackground(new java.awt.Color(204, 204, 204));
        alterarAluno.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        alterarAluno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alterarAluno.setText("Editar aluno");
        alterarAluno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        alterarAluno.setOpaque(true);
        getContentPane().add(alterarAluno);
        alterarAluno.setBounds(-10, -10, 560, 70);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(0, 60, 550, 450);

        setSize(new java.awt.Dimension(550, 526));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbAlterarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAlterarAlunoActionPerformed
        alterarAluno();
    }//GEN-LAST:event_jbAlterarAlunoActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyTyped

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeKeyTyped

    private void txtAgenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgenciaActionPerformed

    private void txtAgenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgenciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgenciaKeyTyped

    private void txtContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContaActionPerformed

    private void txtContaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContaKeyTyped

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed

    private void txtCpfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
            TodosAlunos.linha_selecionada = jTableAlunos.getSelectedRow();
          
            txtNome.setText(String.valueOf(jTableAlunos.getValueAt(TodosAlunos.linha_selecionada, 1)));
            txtCpf.setText(String.valueOf(jTableAlunos.getValueAt(TodosAlunos.linha_selecionada, 2)));
            String cursoAluno = String.valueOf(jTableAlunos.getValueAt(TodosAlunos.linha_selecionada, 3));
            txtEmail.setText(String.valueOf(jTableAlunos.getValueAt(TodosAlunos.linha_selecionada, 4)));
            txtBanco.setText(String.valueOf(jTableAlunos.getValueAt(TodosAlunos.linha_selecionada, 5)));
            txtAgencia.setText(String.valueOf(jTableAlunos.getValueAt(TodosAlunos.linha_selecionada, 6)));
            txtConta.setText(String.valueOf(jTableAlunos.getValueAt(TodosAlunos.linha_selecionada, 7)));  
            
         
            
            try 
            {       
                instrucao = conexao.prepareStatement("SELECT nome_curso from cursos");
                resultado = instrucao.executeQuery();
                resultado.first();
                
                do 
                {   
                        if(cursoAluno.equals(resultado.getString("nome_curso")))
                        {
                            BoxCurso.setSelectedItem(resultado.getString("nome_curso"));
                        } 
                } 
                while(resultado.next());
                

                resultado.close();
                instrucao.close();
            } 
        catch (SQLException ex)
        {
            if(ex instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente");
                System.exit(0);
            }else
            {
                JOptionPane.showMessageDialog(null, ex);                
            }
        }
            
        
                       
                                   
        
        
    }//GEN-LAST:event_formWindowOpened

    private void txtBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBancoActionPerformed

    private void txtBancoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBancoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBancoKeyTyped

    private void txtNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeFocusGained
            labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtNomeFocusGained

    private void txtCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusGained
            labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtCpfFocusGained

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
            labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtEmailFocusGained

    private void BoxCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxCursoActionPerformed
            labelMensagem.setVisible(false);
    }//GEN-LAST:event_BoxCursoActionPerformed

    private void BoxCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoxCursoMouseClicked
            labelMensagem.setVisible(false);
    }//GEN-LAST:event_BoxCursoMouseClicked

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeKeyPressed

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
            java.util.logging.Logger.getLogger(AlterarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AlterarAluno dialog = new AlterarAluno(new javax.swing.JFrame(), true);
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
    private static javax.swing.JComboBox<String> BoxCurso;
    private javax.swing.JLabel agencia;
    private javax.swing.JLabel alterarAluno;
    private javax.swing.JLabel banco;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel conta;
    private javax.swing.JLabel cpf;
    private javax.swing.JLabel curso;
    private javax.swing.JLabel dadosBancarios;
    private javax.swing.JLabel email;
    private javax.swing.JLabel img;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbAlterarAluno;
    public static javax.swing.JLabel labelMensagem;
    private javax.swing.JLabel nome;
    private javax.swing.JTextField txtAgencia;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtConta;
    public static javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
