package interfaces;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import static interfaces.Main.jComboBoxVigencia;
import modelos.FixedLengthDocument;
import modelos.Projeto;
import sql.AlunoDAO;
import sql.OrientadorDAO;
import sql.ProjetoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CadastrarProjeto extends javax.swing.JDialog {

    int novaVigencia = 0;     
    int idAluno1;
    int idAluno2;
    int idOrientadorCadastro;
    static ArrayList<Integer> idAlunos1 = new ArrayList<Integer>();
    static ArrayList<Integer> idAlunos2 = new ArrayList<Integer>();
    static ArrayList<Integer> idOrientador = new ArrayList<Integer>();
    
    public CadastrarProjeto(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        populaComboBoxAluno();
        populaComboBoxOrientador();
        
        if(jComboBoxVigencia.getSelectedItem() == null || jComboBoxVigencia.getSelectedItem().equals("Todos"))
        {
            txtVigencia.setText("");
            novaVigencia = 1;
            
        }else{
             txtVigencia.setText(String.valueOf(jComboBoxVigencia.getSelectedItem()));
             txtVigencia.setEnabled(false);
        }
        labelMensagem.setVisible(false);
    }
    
    public static void populaComboBoxAluno()
    {   String sql;
        idAlunos1.clear();
        idAlunos2.clear();
        idOrientador.clear();
        
       sql = "SELECT * FROM aluno where aluno_excluido = 0 order by nome_aluno asc";
        try 
        {
            instrucao = conexao.prepareStatement(sql);
            resultado = instrucao.executeQuery();
            
            while (resultado.next())
            {
                idAlunos1.add(Integer.parseInt(resultado.getString("id_aluno"))); 
                jComboBoxAluno1.addItem(resultado.getString("nome_aluno") + "  -  " + resultado.getString("curso_aluno")); 
                
                idAlunos2.add(Integer.parseInt(resultado.getString("id_aluno")));
                jComboBoxAluno2.addItem(resultado.getString("nome_aluno")  + "  -  " + resultado.getString("curso_aluno")); 
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
               
    }
    public static void populaComboBoxOrientador()
    {
        String sql = "SELECT * FROM orientador where orientador_excluido = 0 order by nome_orientador asc";
        try 
        {
            instrucao = conexao.prepareStatement(sql);
            resultado = instrucao.executeQuery();
            
            while (db.DAO.resultado.next())
            {
                idOrientador.add(Integer.parseInt(resultado.getString("id_orientador"))); 
                jComboBoxOrientador.addItem(resultado.getString("nome_orientador"));                
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
               
    }
    
    public void cadastrarProjeto()
    {  
        
        if(txtVigencia.getText().equals("    -    "))
        {            
            labelMensagem.setVisible(true);
            labelMensagem.setText("Insira a vigência");
        }else if(txtProjeto.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Insira o nome do projeto");
        }else if(!jComboBoxAluno2.getSelectedItem().equals("(Nenhum)") && JComboBoxModalidadeAluno2.getSelectedItem().equals("(Nenhum)")){
            
            labelMensagem.setVisible(true);
            labelMensagem.setText("Selecione uma modalidade pro aluno 2");
        }else if(String.valueOf(jComboBoxAluno1.getSelectedItem()).equals("null")){
            labelMensagem.setVisible(true);
            labelMensagem.setText("Selecione o aluno 1");
        }else if(jComboBoxAluno2.getSelectedItem().equals("(Nenhum)") && !JComboBoxModalidadeAluno2.getSelectedItem().equals("(Nenhum)")){
            
            labelMensagem.setVisible(true);
            labelMensagem.setText("Selecione um aluno aluno para a modalidade 2");
        }else        
        {
            
        try 
        {   
            if(novaVigencia == 1)
            {
                instrucao = conexao.prepareStatement( "SELECT * FROM projeto WHERE vigencia_projeto = '" + txtVigencia.getText() + "'");
                resultado = instrucao .executeQuery();
            
                if(resultado.next()) // Next retorna 0 ou 1 como resultado da consuta
                {
                    labelMensagem.setVisible(true);
                    labelMensagem.setText("Vigência já existente. Selecione-a na tela principal");
                }else
                {
                        idAluno1 = idAlunos1.get(jComboBoxAluno1.getSelectedIndex());
                        
                        if(String.valueOf(jComboBoxAluno2.getSelectedItem()).equals("(Nenhum)"))
                        {
                            idAluno2 = 0;
                        }else
                        {
                        
                        idAluno2 = idAlunos2.get(jComboBoxAluno2.getSelectedIndex()-1);
                        }


                    idOrientadorCadastro = idOrientador.get(jComboBoxOrientador.getSelectedIndex());
                            
                    ProjetoDAO pro = new ProjetoDAO();
                    Projeto p = new  Projeto();

                    p.setNomeProjeto(txtProjeto.getText());
                    p.setVigenciaProjeto(txtVigencia.getText());
                    p.setAluno1(idAluno1);
                    p.setAluno2(idAluno2);
                    p.setOrientador(idOrientadorCadastro);
                    p.setModalidadeAluno1(String.valueOf(JComboBoxModalidadeAluno1.getSelectedItem()));
                    p.setModalidadeAluno2(String.valueOf(JComboBoxModalidadeAluno2.getSelectedItem()));

                    pro.inserirProjeto(p);

                    jComboBoxVigencia.removeAllItems();
                    jComboBoxVigencia.addItem("Todos");
                    Main.populaComboBoxVigencia();
                    jComboBoxVigencia.setSelectedItem(txtVigencia.getText());

                    dispose();
                    AlunoDAO a = new AlunoDAO();
                    OrientadorDAO o = new OrientadorDAO();

                    if(Main.jRbOrientador.isSelected())
                    {
                        o.getOrientador("");                        
                    }
                    else if(Main.JRbALuno.isSelected())
                    {
                       a.getAluno("");
                       Main.jbMedioSuperior.clearSelection();
                    }
                    Main.limparProjeto();
                }
                

            }else 
            {
                
                
                idAluno1 = idAlunos1.get(jComboBoxAluno1.getSelectedIndex());
            
            
                if(String.valueOf(jComboBoxAluno2.getSelectedItem()).equals("(Nenhum)"))
                {
                    idAluno2 = 0;
                }else
                {
                idAluno2 = idAlunos2.get(jComboBoxAluno2.getSelectedIndex()-1);
                }
                idOrientadorCadastro = idOrientador.get(jComboBoxOrientador.getSelectedIndex());
            
                ProjetoDAO pro = new ProjetoDAO();        
        
                    Projeto p = new  Projeto();

                    p.setNomeProjeto(txtProjeto.getText());
                    p.setVigenciaProjeto(txtVigencia.getText());
                    p.setAluno1(idAluno1);
                    p.setAluno2(idAluno2);
                    p.setOrientador(idOrientadorCadastro);
                    p.setModalidadeAluno1(String.valueOf(JComboBoxModalidadeAluno1.getSelectedItem()));
                    p.setModalidadeAluno2(String.valueOf(JComboBoxModalidadeAluno2.getSelectedItem()));

                    pro.inserirProjeto(p);

                    jComboBoxVigencia.removeAllItems();
                    jComboBoxVigencia.addItem("Todos");
                    Main.populaComboBoxVigencia();
                    
                    
                    Main.Aluno1.setText("Aluno 1");
                    Main.Aluno2.setText("Aluno 2");
                    jComboBoxVigencia.setSelectedItem(txtVigencia.getText());

                    dispose();
                    AlunoDAO a = new AlunoDAO();
                    OrientadorDAO o = new OrientadorDAO();

                    if(Main.jRbOrientador.isSelected())
                    {
                        o.getOrientador("");
                        Main.jbMedioSuperior.clearSelection();
                    }
                    else if(Main.JRbALuno.isSelected())
                    {
                      a.getAluno("");
                      Main.jbMedioSuperior.clearSelection();
                    }
                    
            }
        Main.limparProjeto();
        }catch (SQLException e)
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
    }


  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nome = new javax.swing.JLabel();
        img = new javax.swing.JLabel();
        nome1 = new javax.swing.JLabel();
        jComboBoxAluno1 = new javax.swing.JComboBox<>();
        jComboBoxOrientador = new javax.swing.JComboBox<>();
        nome2 = new javax.swing.JLabel();
        jComboBoxAluno2 = new javax.swing.JComboBox<>();
        nome3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtProjeto = new javax.swing.JTextArea();
        JComboBoxModalidadeAluno1 = new javax.swing.JComboBox<>();
        JComboBoxModalidadeAluno2 = new javax.swing.JComboBox<>();
        nome4 = new javax.swing.JLabel();
        nome5 = new javax.swing.JLabel();
        jbCriar = new javax.swing.JButton();
        txtVigencia = new javax.swing.JFormattedTextField();
        nome6 = new javax.swing.JLabel();
        labelMensagem = new javax.swing.JLabel();
        cadastrarAluno = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Projeto");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(null);

        nome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome.setText("Selecione o aluno 1");
        getContentPane().add(nome);
        nome.setBounds(40, 190, 140, 20);

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Projeto.png"))); // NOI18N
        getContentPane().add(img);
        img.setBounds(10, 0, 140, 130);

        nome1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome1.setText("Digite o título do projeto");
        getContentPane().add(nome1);
        nome1.setBounds(40, 370, 180, 20);

        jComboBoxAluno1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxAluno1ItemStateChanged(evt);
            }
        });
        jComboBoxAluno1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxAluno1MouseClicked(evt);
            }
        });
        jComboBoxAluno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAluno1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxAluno1);
        jComboBoxAluno1.setBounds(40, 210, 380, 30);

        jComboBoxOrientador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxOrientadorItemStateChanged(evt);
            }
        });
        jComboBoxOrientador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxOrientadorMouseClicked(evt);
            }
        });
        jComboBoxOrientador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOrientadorActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxOrientador);
        jComboBoxOrientador.setBounds(40, 330, 310, 30);

        nome2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome2.setText("Modalidade");
        getContentPane().add(nome2);
        nome2.setBounds(430, 190, 140, 20);

        jComboBoxAluno2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "(Nenhum)" }));
        jComboBoxAluno2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxAluno2ItemStateChanged(evt);
            }
        });
        jComboBoxAluno2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxAluno2MouseClicked(evt);
            }
        });
        jComboBoxAluno2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAluno2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxAluno2);
        jComboBoxAluno2.setBounds(40, 270, 380, 30);

        nome3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome3.setText("Vigência");
        getContentPane().add(nome3);
        nome3.setBounds(40, 130, 180, 20);

        txtProjeto.setColumns(20);
        txtProjeto.setDocument(new FixedLengthDocument(255));
        txtProjeto.setLineWrap(true);
        txtProjeto.setRows(5);
        jScrollPane1.setViewportView(txtProjeto);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 390, 530, 90);

        JComboBoxModalidadeAluno1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PIBIC/CNPq", "PIBITI/CNPq", "PIBIC Jr/CNPq", "PIBIC/IF Goiano", "PIVIC/IF Goiano", "PIBITI/IF Goiano", "PIVITI/IF Goiano", "PIBIC Jr/IF Goiano", "PIVIC Jr/IF Goiano" }));
        JComboBoxModalidadeAluno1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JComboBoxModalidadeAluno1MouseClicked(evt);
            }
        });
        JComboBoxModalidadeAluno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBoxModalidadeAluno1ActionPerformed(evt);
            }
        });
        getContentPane().add(JComboBoxModalidadeAluno1);
        JComboBoxModalidadeAluno1.setBounds(430, 210, 140, 30);

        JComboBoxModalidadeAluno2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "(Nenhum)", "PIBIC/IF Goiano", "PIBIC/CNPq", "PIVIC/IF Goiano", "PIBITI/IF Goiano", "PIBITI/CNPq", "PIVITI/IF Goiano", "PIBIC Jr/IF Goiano", "PIBIC Jr/CNPq", "PIVIC Jr/IF Goiano" }));
        JComboBoxModalidadeAluno2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JComboBoxModalidadeAluno2MouseClicked(evt);
            }
        });
        JComboBoxModalidadeAluno2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBoxModalidadeAluno2ActionPerformed(evt);
            }
        });
        getContentPane().add(JComboBoxModalidadeAluno2);
        JComboBoxModalidadeAluno2.setBounds(430, 270, 140, 30);

        nome4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome4.setText("Selecione o aluno 2");
        getContentPane().add(nome4);
        nome4.setBounds(40, 250, 140, 20);

        nome5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome5.setText("Modalidade");
        getContentPane().add(nome5);
        nome5.setBounds(430, 250, 140, 20);

        jbCriar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbCriar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        jbCriar.setText("Cadastrar Projeto");
        jbCriar.setToolTipText("Clique para cancelar o projeto");
        jbCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCriarActionPerformed(evt);
            }
        });
        getContentPane().add(jbCriar);
        jbCriar.setBounds(410, 490, 160, 30);

        try {
            txtVigencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtVigencia);
        txtVigencia.setBounds(40, 150, 90, 30);

        nome6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome6.setText("Selecione o orientador");
        getContentPane().add(nome6);
        nome6.setBounds(40, 310, 180, 20);

        labelMensagem.setBackground(new java.awt.Color(255, 0, 0));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 600, 20);

        cadastrarAluno.setBackground(new java.awt.Color(204, 204, 204));
        cadastrarAluno.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        cadastrarAluno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cadastrarAluno.setText("Cadastrar projeto");
        cadastrarAluno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cadastrarAluno.setOpaque(true);
        getContentPane().add(cadastrarAluno);
        cadastrarAluno.setBounds(-10, -10, 610, 80);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bg.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(-10, 60, 610, 480);

        setSize(new java.awt.Dimension(602, 569));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCriarActionPerformed
        cadastrarProjeto();
    }//GEN-LAST:event_jbCriarActionPerformed

    private void jComboBoxAluno1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxAluno1MouseClicked
        labelMensagem.setVisible(false);   
    }//GEN-LAST:event_jComboBoxAluno1MouseClicked

    private void jComboBoxAluno1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxAluno1MousePressed
         
    }//GEN-LAST:event_jComboBoxAluno1MousePressed

    private void txtVigenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVigenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVigenciaActionPerformed

    private void txtVigenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVigenciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVigenciaKeyTyped

    private void jComboBoxOrientadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOrientadorActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_jComboBoxOrientadorActionPerformed

    private void jComboBoxAluno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAluno1ActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_jComboBoxAluno1ActionPerformed

    private void jComboBoxAluno2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAluno2ActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_jComboBoxAluno2ActionPerformed

    private void JComboBoxModalidadeAluno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboBoxModalidadeAluno1ActionPerformed
       labelMensagem.setVisible(false);
    }//GEN-LAST:event_JComboBoxModalidadeAluno1ActionPerformed

    private void JComboBoxModalidadeAluno2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboBoxModalidadeAluno2ActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_JComboBoxModalidadeAluno2ActionPerformed

    private void jComboBoxAluno2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxAluno2MouseClicked
        labelMensagem.setVisible(false);        
    }//GEN-LAST:event_jComboBoxAluno2MouseClicked

    private void jComboBoxOrientadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxOrientadorMouseClicked
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_jComboBoxOrientadorMouseClicked

    private void JComboBoxModalidadeAluno1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboBoxModalidadeAluno1MouseClicked
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_JComboBoxModalidadeAluno1MouseClicked

    private void JComboBoxModalidadeAluno2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboBoxModalidadeAluno2MouseClicked
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_JComboBoxModalidadeAluno2MouseClicked

    private void jComboBoxAluno1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxAluno1ItemStateChanged
        idAluno1 = idAlunos1.get(jComboBoxAluno1.getSelectedIndex());
    }//GEN-LAST:event_jComboBoxAluno1ItemStateChanged

    private void jComboBoxAluno2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxAluno2ItemStateChanged
        try{
            idAluno2 = idAlunos2.get(jComboBoxAluno2.getSelectedIndex()-1);
        }catch(Exception e)
        {
            idAluno2 = 0;
        }
    }//GEN-LAST:event_jComboBoxAluno2ItemStateChanged

    private void jComboBoxOrientadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxOrientadorItemStateChanged
        idOrientadorCadastro = idOrientador.get(jComboBoxOrientador.getSelectedIndex());
    }//GEN-LAST:event_jComboBoxOrientadorItemStateChanged

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
            java.util.logging.Logger.getLogger(CadastrarProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                CadastrarProjeto dialog = new CadastrarProjeto(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> JComboBoxModalidadeAluno1;
    private javax.swing.JComboBox<String> JComboBoxModalidadeAluno2;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel cadastrarAluno;
    private javax.swing.JLabel img;
    private static javax.swing.JComboBox<String> jComboBoxAluno1;
    private static javax.swing.JComboBox<String> jComboBoxAluno2;
    private static javax.swing.JComboBox<String> jComboBoxOrientador;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbCriar;
    public static javax.swing.JLabel labelMensagem;
    private javax.swing.JLabel nome;
    private javax.swing.JLabel nome1;
    private javax.swing.JLabel nome2;
    private javax.swing.JLabel nome3;
    private javax.swing.JLabel nome4;
    private javax.swing.JLabel nome5;
    private javax.swing.JLabel nome6;
    private javax.swing.JTextArea txtProjeto;
    private javax.swing.JFormattedTextField txtVigencia;
    // End of variables declaration//GEN-END:variables
}
