/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import modelos.Usuario;
import sql.ExportarExcel;
import sql.OrientadorDAO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
public class TodosOrientadores extends javax.swing.JDialog {

    
   static int linha_selecionada = 0;
   static String linhaOrientador = "0";
   OrientadorDAO orientador = new OrientadorDAO();
   
    public TodosOrientadores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializar();
        
    }
    
    public void inicializar()
    {
        existentes.setVisible(false);
        btnRecuperar.setVisible(false);
         btnRemoverPermanente.setVisible(false);
        deletaPermanente.setEnabled(false); 
        recuperarOrientador.setEnabled(false);
        orientador.getTodosOrientadores();
        labelMensagem.setVisible(false);
        
    }
    public void deletarOrientador()
    {
        if(linhaOrientador.equals("0"))
        {
            labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione o orientador para removê-lo");
          labelMensagem.setBackground(new Color(255,102,0));
        }else
        {
            int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este orientador?","Remover orientador",JOptionPane.YES_NO_OPTION);
            
            if(resposta == 0)
            {
                     
                orientador.deletarOrientador(linhaOrientador);     
                linhaOrientador = "0"; 
                orientador.getTodosOrientadores();
            }else if(resposta ==1)
            {
            
            }else
            {
            
            }
        }
    }
    
    public void editarOrientador(){
         if(linhaOrientador.equals("0"))
         {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione o orientador para editá-lo");
          labelMensagem.setBackground(new Color(255,102,0));
        }else{
            JFrame mainFrame = new JFrame();
            AlterarOrientador alterarOrientador = new AlterarOrientador(mainFrame, true);
            alterarOrientador.setLocationRelativeTo(mainFrame);
            alterarOrientador.setVisible(true);   
        }
         
         orientador.getTodosOrientadores();
         
        btnRecuperar.setVisible(false);
        btnRemover.setVisible(true);
        btnAdicionar.setVisible(true);
        btnEditar.setVisible(true);
        btnHistorico.setVisible(true);
        txtPesquisar.setEnabled(true);
        btnPesquisar.setEnabled(true);
        linhaOrientador = "0";
    }
    
    public void historicoOrientador()
    {
        if(linhaOrientador.equals("0"))
        {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione o orientador para ver o histórico");
          labelMensagem.setBackground(new Color(255,102,0));
        }else{
        JFrame mainFrame = new JFrame();
        HistoricoOrientador hO = new HistoricoOrientador(mainFrame, true);
        hO.setLocationRelativeTo(mainFrame);
        hO.setVisible(true);
        }
    }
    
    public void exportar()
    {
        if(jTableOrientadores.getRowCount() == 0)
        {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Não há dados para importar");
          labelMensagem.setBackground(new Color(230,0,0));
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
            tb.add(jTableOrientadores);
            nome.add("Orientadores");
            String file = chooser.getSelectedFile().toString().concat(".xls");
            
            try {
                ExportarExcel e = new ExportarExcel(new File(file),tb,nome);
                if(e.exportar())
                {                    
                    labelMensagem.setVisible(true);
                    labelMensagem.setText("Os dados foram exportados com sucesso!");
                    labelMensagem.setBackground(new Color(0,204,51));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        }
    }
    
    public void deletaOrientadorPermanente()
    {
        int resposta  = JOptionPane.showConfirmDialog(null , "Todos os projetos desse orientador também serão excluídos, deseja realmente exclui-lo permanentemente?");
        
       switch (resposta) 
       {
           case 0:
               orientador.deletarPermanente(linhaOrientador);
               orientador.getTodosOrientadoresExcluidos();
               linhaOrientador = "0";
               break;
           case 1:
                    labelMensagem.setVisible(true);
                    labelMensagem.setText("Nenhuma alteração foi feita");
                    labelMensagem.setBackground(new Color(230,0,0));
               break;
           case 2:
               break;
           default:
               break;
       }
    }
    
    public void verExistentes()
    {
        existentes.setVisible(false);
        removidos.setVisible(true);
        btnRecuperar.setVisible(false);
        btnRecuperar.setEnabled(false);
        btnRemoverPermanente.setVisible(false);
        recuperarOrientador.setEnabled(false);
        btnRemover.setVisible(true);
        btnAdicionar.setVisible(true);
        btnEditar.setVisible(true);
        btnHistorico.setVisible(true);
        txtPesquisar.setEnabled(true);
        btnPesquisar.setEnabled(true);
        pEditar.setEnabled(true);
        deletarSo.setEnabled(true);
        deletaPermanente.setEnabled(false);
        
        orientador.getTodosOrientadores();
        linhaOrientador = "0";
    }
    
    public void verRemovidos()
    {
        existentes.setVisible(true);
        removidos.setVisible(false);
        btnRecuperar.setVisible(true);
        btnRecuperar.setEnabled(true);
        btnRemoverPermanente.setVisible(true);
        btnRemover.setVisible(false);
        btnAdicionar.setVisible(false);
        btnEditar.setVisible(false);
        btnHistorico.setVisible(false);
        txtPesquisar.setEnabled(false);
        btnPesquisar.setEnabled(false);
        deletaPermanente.setEnabled(true);
        recuperarOrientador.setEnabled(true);
        deletarSo.setEnabled(false);
        pEditar.setEnabled(false);
        orientador.getTodosOrientadoresExcluidos();
        linhaOrientador = "0";
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        pEditar = new javax.swing.JMenuItem();
        pHistorico = new javax.swing.JMenuItem();
        recuperarOrientador = new javax.swing.JMenuItem();
        deletarSo = new javax.swing.JMenuItem();
        deletaPermanente = new javax.swing.JMenuItem();
        detalhes = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOrientadores = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnRecuperar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnHistorico = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        labelMensagem = new javax.swing.JLabel();
        TodosOrientadores = new javax.swing.JLabel();
        btnRemoverPermanente = new javax.swing.JButton();
        bg = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        existentes = new javax.swing.JMenu();
        removidos = new javax.swing.JMenu();

        pEditar.setText("Editar orientador");
        pEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pEditarActionPerformed(evt);
            }
        });
        Popup.add(pEditar);

        pHistorico.setText("Ver projetos anteriores");
        pHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pHistoricoActionPerformed(evt);
            }
        });
        Popup.add(pHistorico);

        recuperarOrientador.setText("Recuperar orientador");
        recuperarOrientador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recuperarOrientadorActionPerformed(evt);
            }
        });
        Popup.add(recuperarOrientador);

        deletarSo.setText("Remover orientador");
        deletarSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarSoActionPerformed(evt);
            }
        });
        Popup.add(deletarSo);

        deletaPermanente.setText("Deletar permanentemente");
        deletaPermanente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletaPermanenteActionPerformed(evt);
            }
        });
        Popup.add(deletaPermanente);

        detalhes.setText("Mais detalhes");
        detalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalhesActionPerformed(evt);
            }
        });
        Popup.add(detalhes);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Todos os Orientadores");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(null);

        jTableOrientadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableOrientadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableOrientadoresMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableOrientadoresMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableOrientadoresMouseReleased(evt);
            }
        });
        jTableOrientadores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableOrientadoresKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableOrientadoresKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTableOrientadoresKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTableOrientadores);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 1320, 430);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        btnAdicionar.setText("Cadastrar orientador");
        btnAdicionar.setToolTipText("Clique para cadastrar um orientador");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(310, 550, 170, 30);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        btnEditar.setText("Editar orientador");
        btnEditar.setToolTipText("Clique para editar um orientador");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(480, 550, 150, 30);

        btnRecuperar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/recovery.png"))); // NOI18N
        btnRecuperar.setText("Recuperar orientador");
        btnRecuperar.setToolTipText("Clique para recuperar um orientador removido");
        btnRecuperar.setOpaque(false);
        btnRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRecuperar);
        btnRecuperar.setBounds(970, 550, 180, 30);

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete_projeto.png"))); // NOI18N
        btnRemover.setText("Remover orientador");
        btnRemover.setToolTipText("Clique para remover um orientador");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemover);
        btnRemover.setBounds(630, 550, 170, 30);

        btnHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/historicu.png"))); // NOI18N
        btnHistorico.setText("Projetos anteriores");
        btnHistorico.setToolTipText("Clique para ver os projetos anteriores de um orientador");
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });
        getContentPane().add(btnHistorico);
        btnHistorico.setBounds(800, 550, 170, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excel.png"))); // NOI18N
        jButton1.setText("Exportar");
        jButton1.setToolTipText("Exportar tabela de orientadores para o excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1230, 80, 100, 30);

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
        txtPesquisar.setBounds(930, 40, 340, 30);

        btnPesquisar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisa.png"))); // NOI18N
        btnPesquisar.setToolTipText("Clique para pesquisar por um orientador");
        btnPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar);
        btnPesquisar.setBounds(1270, 37, 60, 35);

        labelMensagem.setBackground(new java.awt.Color(0, 204, 51));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 1360, 20);

        TodosOrientadores.setBackground(new java.awt.Color(210, 210, 210));
        TodosOrientadores.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        TodosOrientadores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TodosOrientadores.setText("Todos os orientadores");
        TodosOrientadores.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TodosOrientadores.setOpaque(true);
        getContentPane().add(TodosOrientadores);
        TodosOrientadores.setBounds(-10, -10, 1360, 90);

        btnRemoverPermanente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete_projeto.png"))); // NOI18N
        btnRemoverPermanente.setText("Remover permanente");
        btnRemoverPermanente.setToolTipText("Clique para remover um aluno");
        btnRemoverPermanente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverPermanenteActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemoverPermanente);
        btnRemoverPermanente.setBounds(1150, 550, 180, 30);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(0, 70, 1350, 540);

        existentes.setText("Existentes");
        existentes.setToolTipText("Clique para exibir os orientadores existentes e os removidos");
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

        removidos.setText("Removidos");
        removidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removidosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removidosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removidosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                removidosMousePressed(evt);
            }
        });
        removidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removidosActionPerformed(evt);
            }
        });
        jMenuBar1.add(removidos);

        setJMenuBar(jMenuBar1);

        setBounds(0, 0, 1349, 653);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        JFrame mainFrame = new JFrame();
        CadastrarOrientador CadastrarOrientador = new CadastrarOrientador(mainFrame, true);
        CadastrarOrientador.setLocationRelativeTo(mainFrame);
        CadastrarOrientador.setVisible(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       editarOrientador();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarActionPerformed
        if(linhaOrientador.equals("0"))
        {
            labelMensagem.setVisible(true);
            labelMensagem.setText("Selecione o orientador para recuperá-lo");
            labelMensagem.setBackground(new Color(255,102,0));
        }
        else{     
        orientador.recuperarOrientador(linhaOrientador);     
        linhaOrientador = "0"; 
        orientador.getTodosOrientadoresExcluidos();
        }
    }//GEN-LAST:event_btnRecuperarActionPerformed

    private void jTableOrientadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOrientadoresMouseClicked
            if(jTableOrientadores.getSelectedRow() >= 0)
            {
                linha_selecionada = jTableOrientadores.getSelectedRow();
                linhaOrientador = (String.valueOf(jTableOrientadores.getValueAt(linha_selecionada, 0)));                
                labelMensagem.setVisible(false);
               // JOptionPane.showMessageDialog(null, "Linha selecionada: "+linha_selecionada + " ID Orientador:" + linhaOrientador);
            }else
            {
            }  
    }//GEN-LAST:event_jTableOrientadoresMouseClicked

    private void jTableOrientadoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOrientadoresMousePressed
            if(jTableOrientadores.getSelectedRow() >= 0)
            {
                linha_selecionada = jTableOrientadores.getSelectedRow();
                linhaOrientador = (String.valueOf(jTableOrientadores.getValueAt(linha_selecionada, 0)));
                labelMensagem.setVisible(false);
                //JOptionPane.showMessageDialog(null, "Linha selecionada: "+linha_selecionada + " ID Orientador:" + linhaOrientador);

            }else
            {
            }  
    }//GEN-LAST:event_jTableOrientadoresMousePressed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        deletarOrientador();            
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed

    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed
       // orientador.getTodosOrientadoresBusca(txtPesquisar.getText());
        //linhaOrientador = "0";
    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
       // orientador.getTodosOrientadoresBusca(txtPesquisar.getText());
       // linhaOrientador = "0";

    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void txtPesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyTyped
        orientador.getTodosOrientadoresBusca(txtPesquisar.getText());
        linhaOrientador = "0";

    }//GEN-LAST:event_txtPesquisarKeyTyped

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        orientador.getTodosOrientadoresBusca(txtPesquisar.getText());
        linhaOrientador = "0";
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jTableOrientadoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableOrientadoresKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) 
        {
            linha_selecionada = jTableOrientadores.getSelectedRow();
            linhaOrientador = (String.valueOf(jTableOrientadores.getValueAt(linha_selecionada, 0)));
        }
        if(evt.getKeyCode() == KeyEvent.VK_DELETE)
        {
            deletarOrientador();
        }
    }//GEN-LAST:event_jTableOrientadoresKeyPressed

    private void jTableOrientadoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableOrientadoresKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) 
        {
            linha_selecionada = jTableOrientadores.getSelectedRow();
            linhaOrientador = (String.valueOf(jTableOrientadores.getValueAt(linha_selecionada, 0)));
        }
        if(evt.getKeyCode() == KeyEvent.VK_DELETE)
        {
            deletarOrientador();
        }
    }//GEN-LAST:event_jTableOrientadoresKeyReleased

    private void jTableOrientadoresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableOrientadoresKeyTyped
         if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) 
        {
            linha_selecionada = jTableOrientadores.getSelectedRow();
            linhaOrientador = (String.valueOf(jTableOrientadores.getValueAt(linha_selecionada, 0)));
        }
         if(evt.getKeyCode() == KeyEvent.VK_DELETE)
        {
            deletarOrientador();
        }
    }//GEN-LAST:event_jTableOrientadoresKeyTyped

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoActionPerformed
        historicoOrientador();
    }//GEN-LAST:event_btnHistoricoActionPerformed

    private void pEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pEditarActionPerformed
        editarOrientador();
    }//GEN-LAST:event_pEditarActionPerformed

    private void pHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pHistoricoActionPerformed
        historicoOrientador();
    }//GEN-LAST:event_pHistoricoActionPerformed

    private void jTableOrientadoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOrientadoresMouseReleased
            if(jTableOrientadores.getSelectedRow() >= 0)
            {
            if(evt.isPopupTrigger())
                {
                  Popup.show(jTableOrientadores, evt.getX(), evt.getY());
                }               
            }else
            {
            }     
        
    }//GEN-LAST:event_jTableOrientadoresMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        exportar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void deletarSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarSoActionPerformed
        deletarOrientador();
    }//GEN-LAST:event_deletarSoActionPerformed

    private void deletaPermanenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletaPermanenteActionPerformed
        deletaOrientadorPermanente();        // TODO add your handling code here:
    }//GEN-LAST:event_deletaPermanenteActionPerformed

    private void recuperarOrientadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recuperarOrientadorActionPerformed
        if(linhaOrientador.equals("0")){
            labelMensagem.setVisible(true);
            labelMensagem.setText("Selecione o orientador para recuperá-lo");
            labelMensagem.setBackground(new Color(255,102,0));
        }
        else{     
        orientador.recuperarOrientador(linhaOrientador);     
        linhaOrientador = "0"; 
        orientador.getTodosOrientadoresExcluidos();
        }
    }//GEN-LAST:event_recuperarOrientadorActionPerformed

    private void detalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detalhesActionPerformed
        JFrame mainFrame = new JFrame();
        DetalhesOrientador dOrientador = new DetalhesOrientador(mainFrame, true);
        dOrientador.setLocationRelativeTo(mainFrame);
        dOrientador.setVisible(true);
    }//GEN-LAST:event_detalhesActionPerformed

    private void existentesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_existentesMouseEntered
        existentes.setText("<html><u><b>Existentes</b></u>");
    }//GEN-LAST:event_existentesMouseEntered

    private void existentesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_existentesMouseExited
        existentes.setText("Existentes");
    }//GEN-LAST:event_existentesMouseExited

    private void txtPesquisarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisarFocusGained
        txtPesquisar.setText("");
        txtPesquisar.setForeground(Color.black);
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtPesquisarFocusGained

    private void txtPesquisarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisarFocusLost
        txtPesquisar.setText("Pesquisar...");
        txtPesquisar.setForeground(Color.GRAY);
    }//GEN-LAST:event_txtPesquisarFocusLost

    private void existentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existentesActionPerformed
        verExistentes();
    }//GEN-LAST:event_existentesActionPerformed

    private void existentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_existentesMouseClicked
        verExistentes();
    }//GEN-LAST:event_existentesMouseClicked

    private void existentesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_existentesMousePressed
        verExistentes();
    }//GEN-LAST:event_existentesMousePressed

    private void removidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removidosActionPerformed
        verRemovidos();
    }//GEN-LAST:event_removidosActionPerformed

    private void removidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removidosMouseClicked
        verRemovidos();
    }//GEN-LAST:event_removidosMouseClicked

    private void removidosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removidosMousePressed
        verRemovidos();
    }//GEN-LAST:event_removidosMousePressed

    private void removidosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removidosMouseEntered
        removidos.setText("<html><u><b>Removidos</b></u>");
    }//GEN-LAST:event_removidosMouseEntered

    private void removidosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removidosMouseExited
         removidos.setText("Removidos");
    }//GEN-LAST:event_removidosMouseExited

    private void btnRemoverPermanenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverPermanenteActionPerformed
        deletaOrientadorPermanente();  
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
            java.util.logging.Logger.getLogger(TodosOrientadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TodosOrientadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TodosOrientadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TodosOrientadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                TodosOrientadores dialog = new TodosOrientadores(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                
                dialog.setVisible(true);
                btnPesquisar.requestFocus();
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu Popup;
    private static javax.swing.JLabel TodosOrientadores;
    private javax.swing.JLabel bg;
    private static javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnHistorico;
    private static javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnRemoverPermanente;
    private javax.swing.JMenuItem deletaPermanente;
    private javax.swing.JMenuItem deletarSo;
    private javax.swing.JMenuItem detalhes;
    private static javax.swing.JMenu existentes;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableOrientadores;
    public static javax.swing.JLabel labelMensagem;
    private javax.swing.JMenuItem pEditar;
    private javax.swing.JMenuItem pHistorico;
    private javax.swing.JMenuItem recuperarOrientador;
    private javax.swing.JMenu removidos;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
