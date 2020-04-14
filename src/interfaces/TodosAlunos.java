/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import modelos.Usuario;
import sql.AlunoDAO;
import sql.ExportarExcel;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Mayck Lima
 */
public class TodosAlunos extends javax.swing.JDialog {
    static int linha_selecionada = 0;
    static String linhaAluno = "0";
    AlunoDAO aluno = new AlunoDAO();
    public TodosAlunos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializar();
        
    }
    
    public void inicializar()
    {
        labelMensagem.setVisible(false);
        btnRecuperar.setVisible(false);
        btnRemoverPermanente.setVisible(false);
        recuperarAluno.setEnabled(false);        
        DeltarPermanente.setEnabled(false);
        aluno.getTodosAlunos("");
        existentes.setVisible(false);
        desabilitaTudo();        
    }
    
    public void habilitaTudo()
    {
        btnEditar.setEnabled(true);
        btnRemover.setEnabled(true);
        btnRemoverPermanente.setEnabled(true);
        btnRecuperar.setEnabled(true);
        btnHistorico.setEnabled(true);
    }
    public void desabilitaTudo()
    {
        btnEditar.setEnabled(false);
        btnRemover.setEnabled(false);
        btnRemoverPermanente.setEnabled(false);
        btnRecuperar.setEnabled(false);
        btnHistorico.setEnabled(false);
    }
     public void pesquisar()
     {
            aluno.getTodosAlunos(txtPesquisar.getText());
     }
     
     public void exportar()
        {
        if(jTableAlunos.getRowCount() == 0)
        {
            JOptionPane.showMessageDialog(null, "Não há dados na tabela para exportar.","Mayck Lima",JOptionPane.INFORMATION_MESSAGE);
        }else{
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de excel","xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar arquivo");
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            List<JTable> tb = new ArrayList<>();
            List<String> nome = new ArrayList<>();
            tb.add(jTableAlunos);
            nome.add("Alunos");
            String file = chooser.getSelectedFile().toString().concat(".xls");
            
            try {
                ExportarExcel e = new ExportarExcel(new File(file),tb,nome);
                if(e.exportar()){
                    JOptionPane.showMessageDialog(null, "Os dados foram exportados");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        }
            
    }
     
     public void remover(){
         if(linhaAluno.equals("0"))
      {          
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione o aluno para remove-lo");
          labelMensagem.setBackground(new Color(255,102,0));
      }else
      {
            int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse aluno?", "Remover aluno", JOptionPane.YES_NO_OPTION);

             switch (resposta) {
                 case 0:
                     aluno.deletarAluno(linhaAluno);
                     linhaAluno = "0";
                     aluno.getTodosAlunos("");
                     break;
                 case 1:
                     break;
                 default:
                     break;
             }
      }
     }
     
     public void abrirExistentes()
     {
         desabilitaTudo();
        existentes.setVisible(false);
        excluidos.setVisible(true);
        btnRecuperar.setVisible(false);
        btnRemoverPermanente.setVisible(false);
        btnRemover.setVisible(true);
        btnAdicionar.setVisible(true);
        btnEditar.setVisible(true);
        btnHistorico.setVisible(true);
        txtPesquisar.setEnabled(true);
        btnPesquisar.setEnabled(true); 
        pEditar.setEnabled(true);
        DeletarSO.setEnabled(true);            
        recuperarAluno.setEnabled(false);
        DeltarPermanente.setEnabled(false);
        
        labelMensagem.setVisible(false);
        labelAluno.setVerticalAlignment(JLabel.CENTER);
        aluno.getTodosAlunos("");
        linhaAluno = "0";
     }
     
     public void abrirExcluidos()
     {
        desabilitaTudo();
        excluidos.setVisible(false);
        existentes.setVisible(true);
        btnRemoverPermanente.setVisible(true);
        btnRecuperar.setVisible(true);
        btnRemover.setVisible(false);
        btnAdicionar.setVisible(false);
        btnEditar.setVisible(false);
        btnHistorico.setVisible(false);
        txtPesquisar.setEnabled(false);
        btnPesquisar.setEnabled(false); 
        pEditar.setEnabled(false);
        DeletarSO.setEnabled(false);            
        recuperarAluno.setEnabled(true);
        DeltarPermanente.setEnabled(true);
        
        labelMensagem.setVisible(false);
        labelAluno.setVerticalAlignment(JLabel.CENTER);
        aluno.getTodosAlunosExcluidos();
        linhaAluno = "0";
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PopupDeletar = new javax.swing.JPopupMenu();
        pEditar = new javax.swing.JMenuItem();
        Historico = new javax.swing.JMenuItem();
        recuperarAluno = new javax.swing.JMenuItem();
        DeletarSO = new javax.swing.JMenuItem();
        DeltarPermanente = new javax.swing.JMenuItem();
        detalhesAluno = new javax.swing.JMenuItem();
        txtPesquisar = new javax.swing.JTextField();
        btnHistorico = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlunos = new javax.swing.JTable();
        btnRecuperar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        labelMensagem = new javax.swing.JLabel();
        labelAluno = new javax.swing.JLabel();
        btnRemoverPermanente = new javax.swing.JButton();
        bg = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        existentes = new javax.swing.JMenu();
        excluidos = new javax.swing.JMenu();

        pEditar.setText("Editar aluno");
        pEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pEditarActionPerformed(evt);
            }
        });
        PopupDeletar.add(pEditar);

        Historico.setText("Ver projetos anteriores");
        Historico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistoricoActionPerformed(evt);
            }
        });
        PopupDeletar.add(Historico);

        recuperarAluno.setText("Recuperar aluno");
        recuperarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recuperarAlunoActionPerformed(evt);
            }
        });
        PopupDeletar.add(recuperarAluno);

        DeletarSO.setText("Remover aluno");
        DeletarSO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletarSOActionPerformed(evt);
            }
        });
        PopupDeletar.add(DeletarSO);

        DeltarPermanente.setText("Deletar permanentemente");
        DeltarPermanente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeltarPermanenteActionPerformed(evt);
            }
        });
        PopupDeletar.add(DeltarPermanente);

        detalhesAluno.setText("Mais detalhes");
        detalhesAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalhesAlunoActionPerformed(evt);
            }
        });
        PopupDeletar.add(detalhesAluno);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Todos os Alunos");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(null);

        txtPesquisar.setForeground(new java.awt.Color(153, 153, 153));
        txtPesquisar.setText("Pesquisar...");
        txtPesquisar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesquisarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesquisarFocusLost(evt);
            }
        });
        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyTyped(evt);
            }
        });
        getContentPane().add(txtPesquisar);
        txtPesquisar.setBounds(930, 40, 350, 30);

        btnHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/historicu.png"))); // NOI18N
        btnHistorico.setText("Projetos anteriores");
        btnHistorico.setToolTipText("Clique para ver os projetos anteriores de um aluno");
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });
        getContentPane().add(btnHistorico);
        btnHistorico.setBounds(830, 550, 170, 30);

        btnPesquisar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisa.png"))); // NOI18N
        btnPesquisar.setToolTipText("Clique para pesquisar por um aluno");
        btnPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar);
        btnPesquisar.setBounds(1280, 30, 60, 40);

        jTableAlunos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAlunosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableAlunosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTableAlunosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableAlunosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableAlunosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAlunos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 1330, 430);

        btnRecuperar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/recovery.png"))); // NOI18N
        btnRecuperar.setText("Recuperar aluno");
        btnRecuperar.setToolTipText("Clique para recuperar um aluno removido");
        btnRecuperar.setOpaque(false);
        btnRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRecuperar);
        btnRecuperar.setBounds(1010, 550, 150, 30);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        btnAdicionar.setText("Cadastrar aluno");
        btnAdicionar.setToolTipText("Clique para cadastrar um aluno");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(380, 550, 150, 30);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        btnEditar.setText("Editar aluno");
        btnEditar.setToolTipText("Clique para editar um aluno");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(530, 550, 140, 30);

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete_projeto.png"))); // NOI18N
        btnRemover.setText("Remover aluno");
        btnRemover.setToolTipText("Clique para remover um aluno");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemover);
        btnRemover.setBounds(670, 550, 140, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excel.png"))); // NOI18N
        jButton1.setText("Exportar");
        jButton1.setToolTipText("Exportar tabela de alunos para o excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1240, 80, 100, 30);

        labelMensagem.setBackground(new java.awt.Color(0, 204, 51));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 1360, 20);

        labelAluno.setBackground(new java.awt.Color(210, 210, 210));
        labelAluno.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        labelAluno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAluno.setText("Todos os alunos");
        labelAluno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelAluno.setOpaque(true);
        getContentPane().add(labelAluno);
        labelAluno.setBounds(-10, -10, 1370, 90);

        btnRemoverPermanente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete_projeto.png"))); // NOI18N
        btnRemoverPermanente.setText("Remover permanente");
        btnRemoverPermanente.setToolTipText("Clique para remover um aluno");
        btnRemoverPermanente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverPermanenteActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemoverPermanente);
        btnRemoverPermanente.setBounds(1160, 550, 180, 30);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(0, 70, 1360, 540);

        existentes.setText("Existentes");
        existentes.setToolTipText("Clique para exibir os alunos existentes");
        existentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                existentesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                existentesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                existentesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                existentesMousePressed(evt);
            }
        });
        existentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existentesActionPerformed(evt);
            }
        });
        jMenuBar1.add(existentes);

        excluidos.setText("Removidos");
        excluidos.setToolTipText("Clique para exibir os alunos removidos");
        excluidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                excluidosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                excluidosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                excluidosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                excluidosMousePressed(evt);
            }
        });
        excluidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluidosActionPerformed(evt);
            }
        });
        jMenuBar1.add(excluidos);

        setJMenuBar(jMenuBar1);

        setBounds(0, 0, 1359, 653);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableAlunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlunosMouseClicked
        if(jTableAlunos.getSelectedRow() >= 0)
            {
                habilitaTudo();
                linha_selecionada = jTableAlunos.getSelectedRow();
                linhaAluno = (String.valueOf(jTableAlunos.getValueAt(linha_selecionada, 0))); 
                labelMensagem.setVisible(false);
            }else
            {
                
            }  
    }//GEN-LAST:event_jTableAlunosMouseClicked

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        JFrame mainFrame = new JFrame();
        CadastrarAluno cadastroAluno = new CadastrarAluno(mainFrame, true);
        cadastroAluno.setLocationRelativeTo(mainFrame);
        cadastroAluno.setVisible(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
      desabilitaTudo();
      if(linhaAluno.equals("0"))
      {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione o aluno para editá-lo");
          labelMensagem.setBackground(new Color(255,102,0));
      }else{
                    
            JFrame mainFrame = new JFrame();
            AlterarAluno alterarAluno = new AlterarAluno(mainFrame, true);
            alterarAluno.setLocationRelativeTo(mainFrame);
            alterarAluno.setVisible(true);
      }
        aluno.getTodosAlunos("");
        btnRecuperar.setVisible(false);
        btnRemover.setVisible(true);
        btnAdicionar.setVisible(true);
        btnEditar.setVisible(true);
        btnHistorico.setVisible(true);
        txtPesquisar.setEnabled(true);
        btnPesquisar.setEnabled(true);
        linhaAluno = "0";
            
      
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        desabilitaTudo();
        remover();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void jTableAlunosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlunosMousePressed
        if(jTableAlunos.getSelectedRow() >= 0)
        {
            habilitaTudo();
            linha_selecionada = jTableAlunos.getSelectedRow();
            linhaAluno = (String.valueOf(jTableAlunos.getValueAt(linha_selecionada, 0)));
            labelMensagem.setVisible(false);
        }else
        {
            //JOptionPane.showMessageDialog(null, "Selecione uma linha");
        }
    }//GEN-LAST:event_jTableAlunosMousePressed

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
         

    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed
            //aluno.getTodosAlunos(txtPesquisar.getText());
            //linhaAluno = "0";

    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
           // aluno.getTodosAlunos(txtPesquisar.getText());
           // linhaAluno = "0";

    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void txtPesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyTyped
           aluno.getTodosAlunos(txtPesquisar.getText());
           linhaAluno = "0";
    }//GEN-LAST:event_txtPesquisarKeyTyped

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
           aluno.getTodosAlunos(txtPesquisar.getText());
           linhaAluno = "0";
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jTableAlunosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlunosMouseEntered
        
    }//GEN-LAST:event_jTableAlunosMouseEntered

    private void jTableAlunosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlunosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableAlunosMouseExited

    private void btnRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarActionPerformed
        desabilitaTudo();
        if(linhaAluno.equals("0"))
        {            
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione o aluno para recupera-lo");
          labelMensagem.setBackground(new Color(255,102,0));
        }else{
            aluno.recuperarAluno(linhaAluno);
            aluno.getTodosAlunosExcluidos();
        }
            linhaAluno = "0";
    }//GEN-LAST:event_btnRecuperarActionPerformed

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoActionPerformed
        desabilitaTudo();
        if(linhaAluno.equals("0"))
        {            
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione o aluno para ver o histórico");
          labelMensagem.setBackground(new Color(255,102,0));
        }
        else
        {
            JFrame mainFrame = new JFrame();
            HistoricoAluno hA = new HistoricoAluno(mainFrame, true);
            hA.setLocationRelativeTo(mainFrame);
            hA.setVisible(true);
        }
    }//GEN-LAST:event_btnHistoricoActionPerformed

    private void jTableAlunosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlunosMouseReleased
            if(jTableAlunos.getSelectedRow() >= 0)
            {
                if(evt.isPopupTrigger())
                {
                   PopupDeletar.show(jTableAlunos, evt.getX(), evt.getY());
                }
            }else
            {
                
            }        
                               
                
    }//GEN-LAST:event_jTableAlunosMouseReleased

    private void DeltarPermanenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeltarPermanenteActionPerformed
        int resposta  = JOptionPane.showConfirmDialog(null , "Os projetos desse aluno também serão excluídos, deseja realmente excluí-lo?", "Excluir permanentemente", JOptionPane.YES_NO_OPTION);
        
        if(resposta == 0)
        {
           aluno.deletarPermanente(linhaAluno);
           aluno.getTodosAlunosExcluidos();
           linhaAluno = "0";
        }else if(resposta == 1)
        {
        }
        else if(resposta == 2)
        {
            
        }
        
        
    }//GEN-LAST:event_DeltarPermanenteActionPerformed

    private void pEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pEditarActionPerformed
        if(linhaAluno.equals("0"))
      {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione uma linha");
          labelMensagem.setBackground(new Color(255,102,0));
      }else{
                    
            JFrame mainFrame = new JFrame();
            AlterarAluno aluno = new AlterarAluno(mainFrame, true);
            aluno.setLocationRelativeTo(mainFrame);
            aluno.setVisible(true);
      }
        aluno.getTodosAlunos("");
        btnRecuperar.setVisible(false);
        btnRemover.setVisible(true);
        btnAdicionar.setVisible(true);
        btnEditar.setVisible(true);
        btnHistorico.setVisible(true);
        txtPesquisar.setEnabled(true);
        btnPesquisar.setEnabled(true);
        linhaAluno = "0";
        
    }//GEN-LAST:event_pEditarActionPerformed

    private void HistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistoricoActionPerformed
        if(linhaAluno.equals("0"))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Selecione uma linha");
            labelMensagem.setBackground(new Color(255,102,0));
        }
        else
        {
            JFrame mainFrame = new JFrame();
            HistoricoAluno hA = new HistoricoAluno(mainFrame, true);
            hA.setLocationRelativeTo(mainFrame);
            hA.setVisible(true);
        }
    }//GEN-LAST:event_HistoricoActionPerformed

    private void detalhesAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detalhesAlunoActionPerformed
        JFrame mainFrame = new JFrame();
        DetalhesAluno dAluno = new DetalhesAluno(mainFrame, true);
        dAluno.setLocationRelativeTo(mainFrame);
        dAluno.setVisible(true);
    }//GEN-LAST:event_detalhesAlunoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        exportar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void DeletarSOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletarSOActionPerformed
        remover();        // TODO add your handling code here:
    }//GEN-LAST:event_DeletarSOActionPerformed

    private void recuperarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recuperarAlunoActionPerformed
         if(linhaAluno.equals("0"))
         {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Selecione uma linha");
            labelMensagem.setBackground(new Color(255,102,0));             
        }else{
            aluno.recuperarAluno(linhaAluno);
            aluno.getTodosAlunosExcluidos();
        }
            linhaAluno = "0";
    }//GEN-LAST:event_recuperarAlunoActionPerformed

    private void existentesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_existentesMouseEntered
        existentes.setText("<html><u><b>Existentes</b></u>");
    }//GEN-LAST:event_existentesMouseEntered

    private void existentesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_existentesMouseExited
        existentes.setText("Existentes");
    }//GEN-LAST:event_existentesMouseExited

    private void excluidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluidosActionPerformed
        abrirExcluidos();
    }//GEN-LAST:event_excluidosActionPerformed

    private void existentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_existentesMouseClicked
        abrirExistentes();
    }//GEN-LAST:event_existentesMouseClicked

    private void existentesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_existentesMousePressed
          abrirExistentes();
    }//GEN-LAST:event_existentesMousePressed

    private void excluidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluidosMouseClicked
        abrirExcluidos();
    }//GEN-LAST:event_excluidosMouseClicked

    private void excluidosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluidosMousePressed
        abrirExcluidos();
    }//GEN-LAST:event_excluidosMousePressed

    private void excluidosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluidosMouseEntered
        excluidos.setText("<html><u><b>Removidos</b></u>");
    }//GEN-LAST:event_excluidosMouseEntered

    private void excluidosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluidosMouseExited
        excluidos.setText("Removidos");
    }//GEN-LAST:event_excluidosMouseExited

    private void existentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existentesActionPerformed
        abrirExistentes();
    }//GEN-LAST:event_existentesActionPerformed

    private void txtPesquisarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisarFocusGained
        txtPesquisar.setText("");
        txtPesquisar.setForeground(Color.black);
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtPesquisarFocusGained

    private void txtPesquisarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisarFocusLost
         txtPesquisar.setText("Pesquisar...");
         txtPesquisar.setForeground(Color.GRAY);
    }//GEN-LAST:event_txtPesquisarFocusLost

    private void btnRemoverPermanenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverPermanenteActionPerformed
        desabilitaTudo();
        
        int resposta  = JOptionPane.showConfirmDialog(null , "Os projetos desse aluno também serão excluídos, deseja realmente excluí-lo?", "Excluir permanentemente", JOptionPane.YES_NO_OPTION);
        
        switch (resposta) {
            case 0:
                aluno.deletarPermanente(linhaAluno);
                aluno.getTodosAlunosExcluidos();
                linhaAluno = "0";
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnRemoverPermanenteActionPerformed
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
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
            java.util.logging.Logger.getLogger(TodosAlunos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TodosAlunos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TodosAlunos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TodosAlunos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TodosAlunos dialog = new TodosAlunos(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem DeletarSO;
    private javax.swing.JMenuItem DeltarPermanente;
    private javax.swing.JMenuItem Historico;
    private javax.swing.JPopupMenu PopupDeletar;
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnHistorico;
    private static javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnRemoverPermanente;
    private javax.swing.JMenuItem detalhesAluno;
    private javax.swing.JMenu excluidos;
    private javax.swing.JMenu existentes;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableAlunos;
    public static javax.swing.JLabel labelAluno;
    public static javax.swing.JLabel labelMensagem;
    private javax.swing.JMenuItem pEditar;
    private javax.swing.JMenuItem recuperarAluno;
    private static javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
