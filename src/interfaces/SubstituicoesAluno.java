/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Color;
import modelos.Usuario;
import sql.ExportarExcel;
import sql.ProjetoDAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Mayck Lima
 */
public class SubstituicoesAluno extends javax.swing.JDialog {

    /**
     * Creates new form DesistenciaAluno
     */
    ProjetoDAO projeto = new ProjetoDAO();
    int linhaSelecionada;
    String pastaProjeto = "0";
    String destino = "";
    
    public SubstituicoesAluno(java.awt.Frame parent, boolean modal) 
    {
        super(parent, modal);        
   
        initComponents();
        populaComboBoxVigencia();
        jComboBoxVigenciaSubstituicao.setSelectedItem(Main.jComboBoxVigencia.getSelectedItem());
        projeto.getSubstituicoes("");

    }    
    public static void populaComboBoxVigencia()
    {   
        String sql = "SELECT vigencia_projeto FROM projeto GROUP BY vigencia_projeto order by vigencia_projeto asc;";
        try 
        {
            db.DAO.instrucao = db.DAO.conexao.prepareStatement(sql);
            db.DAO.resultado = db.DAO.instrucao.executeQuery();
            
            while (db.DAO.resultado.next())
            {
                jComboBoxVigenciaSubstituicao.addItem(db.DAO.resultado.getString("vigencia_projeto")); 
                
            }
            
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
         }
               
    }
    
    public void abrirPasta()
    {               
        if(pastaProjeto.equals("0"))
        {
            JOptionPane.showMessageDialog(null, "Selecione uma linha!");
        }else{         

            try{

                String caminho = ("explorer " + Usuario.getSalvar_em() +  "\\" + pastaProjeto);
                //JOptionPane.showMessageDialog(null, caminho);
                Runtime.getRuntime().exec(caminho);
            }catch(IOException ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    public void selecionaProjeto()
    {
        linhaSelecionada = jTableSubstituicoes.getSelectedRow();
        pastaProjeto = (String.valueOf(jTableSubstituicoes.getValueAt(linhaSelecionada, 6)));
    }
    
    public void exportar()
    {
        if(jTableSubstituicoes.getRowCount() == 0)
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
            tb.add(jTableSubstituicoes);
            nome.add("Substituições");
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
    
    public void addAnexo()
    {
        
        JFileChooser abrir = new JFileChooser();
        String caminho ;
        
        abrir.setDialogTitle("Procurar arquivo...");
        abrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        FileNameExtensionFilter ff = new FileNameExtensionFilter("Arquivo","pdf");
        
        abrir.setFileFilter(ff);
        int retorno = abrir.showOpenDialog(null); 
        
    if (retorno == JFileChooser.APPROVE_OPTION)  
    {
          caminho = abrir.getSelectedFile().getAbsolutePath();
       
       File diretorio = new File(Usuario.getSalvar_em() + "\\" + pastaProjeto);
       
        
       if (!diretorio.exists())
            diretorio.mkdirs();
       try 
       {
            Files.copy(Paths.get(caminho), Paths.get(diretorio + "\\" + "Substituição de " + String.valueOf(jTableSubstituicoes.getValueAt(linhaSelecionada, 1)) + " por " + String.valueOf(jTableSubstituicoes.getValueAt(linhaSelecionada, 2)) +".pdf"));
            if(caminho.equals("")){
                
                 JOptionPane.showMessageDialog(null, "Erro ao anexar o arquivo");
            }else{
                 JOptionPane.showMessageDialog(null, "O arquivo foi anexado");
            }
        } catch (FileAlreadyExistsException ex) {
            JOptionPane.showMessageDialog(null, "Já existe um arquivo com esse nome");
        } catch (IOException ex) {
            Logger.getLogger(SubstituirAluno.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }else
    {
        caminho = "";
    }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abrirPastaPopup = new javax.swing.JPopupMenu();
        addAnexo = new javax.swing.JMenuItem();
        abrirPasta = new javax.swing.JMenuItem();
        maisDetalhes = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSubstituicoes = new javax.swing.JTable();
        jComboBoxVigenciaSubstituicao = new javax.swing.JComboBox<>();
        exportar = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        labelSubstituicoes = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        addAnexo.setText("Adicionar anexo....");
        addAnexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAnexoActionPerformed(evt);
            }
        });
        abrirPastaPopup.add(addAnexo);

        abrirPasta.setText("Abir pasta");
        abrirPasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirPastaActionPerformed(evt);
            }
        });
        abrirPastaPopup.add(abrirPasta);

        maisDetalhes.setText("Mais detalhes");
        maisDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maisDetalhesActionPerformed(evt);
            }
        });
        abrirPastaPopup.add(maisDetalhes);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Substituições");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(null);

        jTableSubstituicoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableSubstituicoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSubstituicoesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableSubstituicoesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableSubstituicoesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableSubstituicoes);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 1170, 410);

        jComboBoxVigenciaSubstituicao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        jComboBoxVigenciaSubstituicao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxVigenciaSubstituicaoItemStateChanged(evt);
            }
        });
        jComboBoxVigenciaSubstituicao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxVigenciaSubstituicaoMouseClicked(evt);
            }
        });
        getContentPane().add(jComboBoxVigenciaSubstituicao);
        jComboBoxVigenciaSubstituicao.setBounds(10, 80, 100, 30);

        exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excel.png"))); // NOI18N
        exportar.setText("Exportar");
        exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarActionPerformed(evt);
            }
        });
        getContentPane().add(exportar);
        exportar.setBounds(1080, 80, 100, 30);

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
        txtPesquisar.setBounds(760, 40, 350, 30);

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
        btnPesquisar.setBounds(1110, 33, 60, 40);

        labelSubstituicoes.setBackground(new java.awt.Color(204, 204, 204));
        labelSubstituicoes.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        labelSubstituicoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSubstituicoes.setText("Substituições");
        labelSubstituicoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelSubstituicoes.setOpaque(true);
        getContentPane().add(labelSubstituicoes);
        labelSubstituicoes.setBounds(-10, -10, 1200, 90);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(0, 70, 1340, 470);

        setBounds(0, 0, 1194, 562);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxVigenciaSubstituicaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxVigenciaSubstituicaoItemStateChanged
        projeto.getSubstituicoes("");
    }//GEN-LAST:event_jComboBoxVigenciaSubstituicaoItemStateChanged

    private void jComboBoxVigenciaSubstituicaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxVigenciaSubstituicaoMouseClicked

    }//GEN-LAST:event_jComboBoxVigenciaSubstituicaoMouseClicked

    private void jTableSubstituicoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSubstituicoesMouseClicked
        if(jTableSubstituicoes.getSelectedRow() >= 0)
            {
                selecionaProjeto();                
            }else
            {
                JOptionPane.showMessageDialog(null, "Selecione uma linha");
            }  
        
    }//GEN-LAST:event_jTableSubstituicoesMouseClicked

    private void jTableSubstituicoesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSubstituicoesMouseReleased
        if(jTableSubstituicoes.getSelectedRow() >= 0)
            {
                        abrirPasta.setEnabled(true);
                        addAnexo.setEnabled(true);
                        if(evt.isPopupTrigger())
                        {
                            abrirPastaPopup.show(jTableSubstituicoes, evt.getX(), evt.getY());
                        }
                                
            }else
            {
                JOptionPane.showMessageDialog(null, "Selecione uma linha");
            }
        
    }//GEN-LAST:event_jTableSubstituicoesMouseReleased

    private void abrirPastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirPastaActionPerformed
        abrirPasta();
    }//GEN-LAST:event_abrirPastaActionPerformed

    private void jTableSubstituicoesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSubstituicoesMousePressed
            if(jTableSubstituicoes.getSelectedRow() >= 0)
            {
                selecionaProjeto();                
            }else
            {
                JOptionPane.showMessageDialog(null, "Selecione uma linha");
            }
    }//GEN-LAST:event_jTableSubstituicoesMousePressed

    private void exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarActionPerformed
        exportar();        // TODO add your handling code here:
    }//GEN-LAST:event_exportarActionPerformed

    private void addAnexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAnexoActionPerformed
        addAnexo();
    }//GEN-LAST:event_addAnexoActionPerformed

    private void maisDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maisDetalhesActionPerformed
       JFrame mainFrame = new JFrame();
        MaisDetalhesProjeto detalhes = new MaisDetalhesProjeto(mainFrame, true, String.valueOf(jTableSubstituicoes.getValueAt(linhaSelecionada, 7)));
        detalhes.setLocationRelativeTo(mainFrame);
        detalhes.setVisible(true);
    }//GEN-LAST:event_maisDetalhesActionPerformed

    private void txtPesquisarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisarFocusGained
        txtPesquisar.setText("");
        txtPesquisar.setForeground(Color.black);
    }//GEN-LAST:event_txtPesquisarFocusGained

    private void txtPesquisarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisarFocusLost
        txtPesquisar.setText("Pesquisar...");
        txtPesquisar.setForeground(Color.GRAY);
    }//GEN-LAST:event_txtPesquisarFocusLost

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
        projeto.getSubstituicoes(txtPesquisar.getText());
        linhaSelecionada = 0;
    }//GEN-LAST:event_txtPesquisarKeyTyped

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
       projeto.getSubstituicoes(txtPesquisar.getText());
       linhaSelecionada = 0;
    }//GEN-LAST:event_btnPesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(SubstituicoesAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubstituicoesAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubstituicoesAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubstituicoesAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                SubstituicoesAluno dialog = new SubstituicoesAluno(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem abrirPasta;
    private javax.swing.JPopupMenu abrirPastaPopup;
    private javax.swing.JMenuItem addAnexo;
    private javax.swing.JLabel bg;
    private static javax.swing.JButton btnPesquisar;
    private javax.swing.JButton exportar;
    public static javax.swing.JComboBox<String> jComboBoxVigenciaSubstituicao;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableSubstituicoes;
    private javax.swing.JLabel labelSubstituicoes;
    private javax.swing.JMenuItem maisDetalhes;
    private static javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
