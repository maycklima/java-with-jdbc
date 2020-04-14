/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

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
public class DesistenciasAluno extends javax.swing.JDialog {
    ProjetoDAO projeto = new ProjetoDAO();
    int linhaSelecionada = 0;
    String pastaProjeto = "0";
    String destino = "";
    /**
     * Creates new form DesistenciaAluno
     */
    public DesistenciasAluno(java.awt.Frame parent, boolean modal) 
    {
        super(parent, modal);
        initComponents();
        populaComboBoxVigencia();
        jComboBoxVigenciaDesistencia.setSelectedItem(Main.jComboBoxVigencia.getSelectedItem());
        projeto.getDesistencias();
        labelMensagem.setVisible(false);        
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
                jComboBoxVigenciaDesistencia.addItem(db.DAO.resultado.getString("vigencia_projeto")); 
                
            }
            
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
         }
               
    }
    public void selecionaProjeto()
    {
        linhaSelecionada = jTableDesistencia.getSelectedRow();
        pastaProjeto = (String.valueOf(jTableDesistencia.getValueAt(linhaSelecionada, 6)));
        labelMensagem.setVisible(false);
    }
    
    public void abrirPasta()
    {             
        
        if(pastaProjeto.equals("0"))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Selecione uma linha");
        }else{         

            try{

                String caminho = ("explorer " + Usuario.getSalvar_em()+"\\" + pastaProjeto);
                //JOptionPane.showMessageDialog(null, caminho);
                Runtime.getRuntime().exec(caminho);
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    public void exportar()
    {
        if(jTableDesistencia.getRowCount() == 0)
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
            tb.add(jTableDesistencia);
            nome.add("Desistências");
            String file = chooser.getSelectedFile().toString().concat(".xls");
            
            try {
                ExportarExcel e = new ExportarExcel(new File(file),tb,nome);
                if(e.exportar())
                {
                    labelMensagem.setVisible(true);
                    labelMensagem.setText("Os dados foram exportados");
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
            Files.copy(Paths.get(caminho), Paths.get(diretorio + "/" + "Desistência - " +  String.valueOf(jTableDesistencia.getValueAt(linhaSelecionada, 1)) +".pdf"));
            if(caminho.equals("")){
                
                 JOptionPane.showMessageDialog(null, "Erro ao anexar o arquivo");
            }else
            {
                labelMensagem.setVisible(true);
                labelMensagem.setText("O arquivo foi anexado");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abrirPastaPopup = new javax.swing.JPopupMenu();
        addAnexoMenu = new javax.swing.JMenuItem();
        abrirPasta = new javax.swing.JMenuItem();
        maisDetalhes = new javax.swing.JMenuItem();
        jComboBoxVigenciaDesistencia = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDesistencia = new javax.swing.JTable();
        exportar = new javax.swing.JButton();
        labelMensagem = new javax.swing.JLabel();
        labelDesistencias = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        addAnexoMenu.setText("Adicionar anexo....");
        addAnexoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAnexoMenuActionPerformed(evt);
            }
        });
        abrirPastaPopup.add(addAnexoMenu);

        abrirPasta.setText("Abrir pasta");
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
        setTitle("Desistências");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(null);

        jComboBoxVigenciaDesistencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        jComboBoxVigenciaDesistencia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxVigenciaDesistenciaItemStateChanged(evt);
            }
        });
        jComboBoxVigenciaDesistencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxVigenciaDesistenciaMouseClicked(evt);
            }
        });
        getContentPane().add(jComboBoxVigenciaDesistencia);
        jComboBoxVigenciaDesistencia.setBounds(10, 80, 100, 30);

        jTableDesistencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableDesistencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDesistenciaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableDesistenciaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableDesistenciaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDesistencia);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 1090, 370);

        exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excel.png"))); // NOI18N
        exportar.setText("Exportar");
        exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarActionPerformed(evt);
            }
        });
        getContentPane().add(exportar);
        exportar.setBounds(1000, 80, 100, 30);

        labelMensagem.setBackground(new java.awt.Color(0, 204, 51));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 1120, 20);

        labelDesistencias.setBackground(new java.awt.Color(204, 204, 204));
        labelDesistencias.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        labelDesistencias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDesistencias.setText("Desistências");
        labelDesistencias.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelDesistencias.setOpaque(true);
        getContentPane().add(labelDesistencias);
        labelDesistencias.setBounds(-10, -10, 1130, 80);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(0, 70, 1130, 430);

        setBounds(0, 0, 1115, 523);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxVigenciaDesistenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxVigenciaDesistenciaMouseClicked
        
    }//GEN-LAST:event_jComboBoxVigenciaDesistenciaMouseClicked

    private void jComboBoxVigenciaDesistenciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxVigenciaDesistenciaItemStateChanged
        projeto.getDesistencias();
    }//GEN-LAST:event_jComboBoxVigenciaDesistenciaItemStateChanged

    private void jTableDesistenciaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDesistenciaMouseReleased
        if(jTableDesistencia.getSelectedRow() >= 0)
        {
            abrirPasta.setEnabled(true);
            addAnexoMenu.setEnabled(true);
            if(evt.isPopupTrigger())
            {
                abrirPastaPopup.show(jTableDesistencia, evt.getX(), evt.getY());
            }
        }else
        {
        } 
        
    }//GEN-LAST:event_jTableDesistenciaMouseReleased

    private void abrirPastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirPastaActionPerformed
        abrirPasta();
    }//GEN-LAST:event_abrirPastaActionPerformed

    private void jTableDesistenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDesistenciaMouseClicked
        if(jTableDesistencia.getSelectedRow() >= 0)
        {
           selecionaProjeto();
        }else
        {
        }      
        
    }//GEN-LAST:event_jTableDesistenciaMouseClicked

    private void jTableDesistenciaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDesistenciaMousePressed
         if(jTableDesistencia.getSelectedRow() >= 0)
        {
           selecionaProjeto();
        }else
        {
        } 
    }//GEN-LAST:event_jTableDesistenciaMousePressed

    private void exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarActionPerformed
        exportar();        // TODO add your handling code here:
    }//GEN-LAST:event_exportarActionPerformed

    private void addAnexoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAnexoMenuActionPerformed
            addAnexo();
    }//GEN-LAST:event_addAnexoMenuActionPerformed

    private void maisDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maisDetalhesActionPerformed
        JFrame mainFrame = new JFrame();
        MaisDetalhesProjeto detalhes = new MaisDetalhesProjeto(mainFrame, true, String.valueOf(jTableDesistencia.getValueAt(linhaSelecionada, 7)));
        detalhes.setLocationRelativeTo(mainFrame);
        detalhes.setVisible(true);
    }//GEN-LAST:event_maisDetalhesActionPerformed

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
            java.util.logging.Logger.getLogger(DesistenciasAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DesistenciasAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DesistenciasAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DesistenciasAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DesistenciasAluno dialog = new DesistenciasAluno(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem addAnexoMenu;
    private javax.swing.JLabel bg;
    private javax.swing.JButton exportar;
    public static javax.swing.JComboBox<String> jComboBoxVigenciaDesistencia;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableDesistencia;
    private javax.swing.JLabel labelDesistencias;
    public static javax.swing.JLabel labelMensagem;
    private javax.swing.JMenuItem maisDetalhes;
    // End of variables declaration//GEN-END:variables
}
