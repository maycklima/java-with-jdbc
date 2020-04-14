package interfaces;
import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import modelos.Projeto;
import modelos.Usuario;
import sql.AlunoDAO;
import sql.DeclaracaoDAO;
import sql.ExportarExcel;
import sql.OrientadorDAO;
import sql.ProjetoDAO;
import sql.UsuarioDAO;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class Main extends javax.swing.JFrame 
{
    
    public static String linhaProjeto = "0";
    static int linha_selecionada = 0;
    
    AlunoDAO aluno = new AlunoDAO();
    ProjetoDAO projeto = new ProjetoDAO();    
    OrientadorDAO orientador = new OrientadorDAO();
    JFileChooser abrir = new JFileChooser();
    String caminho ;
    String destino = "";
    String nomeAluno;
    String nomeOrientador ;
    String filtroModalidade;
    String projetoCancelado;
    
    public Main() 
    {        
        initComponents();
        inicializar();
    };
    
    public void assinalarDesistencia()
    {
      if(linhaProjeto.equals("0"))
      {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione um aluno para substituir");
          labelMensagem.setBackground(new Color(255,102,0));
      }
      else
      {
        JFrame mainFrame = new JFrame();
        DesistirAluno alunoDesistir = new DesistirAluno(mainFrame, true);
        alunoDesistir.setLocationRelativeTo(mainFrame);
        alunoDesistir.setVisible(true);
      }  
    }
    public void inicializar()
    {
        labelMensagem.setVisible(false);
        btnFilttro.setVisible(false);
        populaComboBoxVigencia();
        selecionaVigencia();
        aluno.getAluno("");
        btnCancelar.setVisible(false);
        btnAtivar.setVisible(false);
        Configurar.setVisible(false);
    }  
    
    public void sair()
    {
        int resposta  = JOptionPane.showConfirmDialog(null , "Deseja realmente sair?" , "Logout", JOptionPane.YES_NO_OPTION);
        
        if(resposta == 0)
        {
            System.exit(0);
        }
       else{
            
           }
    }
    
    public void abrirOrientador()
    {
        labelMensagem.setVisible(false);
        JFrame mainFrame = new JFrame();
        TodosOrientadores orientadores = new TodosOrientadores(mainFrame, true);
        orientadores.setLocationRelativeTo(mainFrame);
        orientadores.setVisible(true);
    }
    public void abrirAluno()
    {
       labelMensagem.setVisible(false);
      JFrame mainFrame = new JFrame();
      TodosAlunos alunos = new TodosAlunos(mainFrame, true);
      alunos.setLocationRelativeTo(mainFrame);
      alunos.setVisible(true);
    }
    public void abrirRegistro(){
       labelMensagem.setVisible(false);
       JFrame mainFrame = new JFrame();
       RegistroDigital rd = new RegistroDigital(mainFrame, true);
       rd.setLocationRelativeTo(mainFrame);
       rd.setVisible(true);
    }
    public static void populaComboBoxVigencia()
    {   
        String sql = "SELECT vigencia_projeto FROM projeto GROUP BY vigencia_projeto order by vigencia_projeto asc;";
        try 
        {
            instrucao = conexao.prepareStatement(sql);
            resultado = instrucao.executeQuery();
            
            while (resultado.next())
            {
                jComboBoxVigencia.addItem(db.DAO.resultado.getString("vigencia_projeto")); 
                
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
    
    public void abrirRelatoriosAlunos()
    { 
      labelMensagem.setVisible(false);
      JFrame mainFrame = new JFrame();
      RelatoriosAlunos relatorios = new RelatoriosAlunos(mainFrame, true);
      relatorios.setLocationRelativeTo(mainFrame);
      relatorios.setVisible(true);
    }
    public void abrirRelatoriosOrientadores()
    { 
      labelMensagem.setVisible(false);
      JFrame mainFrame = new JFrame();
      RelatoriosOrientadores relatoriosOrientador = new RelatoriosOrientadores(mainFrame, true);
      relatoriosOrientador.setLocationRelativeTo(mainFrame);
      relatoriosOrientador.setVisible(true);
    }
    
    public void selecionaVigencia()
    {      
        String vigencia = "";
        try 
        {
            instrucao = conexao.prepareStatement("select max(vigencia_projeto) as vigencia from projeto_view;");
            resultado = instrucao.executeQuery();            
            if(resultado.next())
            {
               
                vigencia = resultado.getString("vigencia");
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
        
        
         jComboBoxVigencia.setSelectedItem(vigencia);
    }
        
     public void pesquisar()
     {
         
        if(jRbOrientador.isSelected())
        {
            orientador.getOrientador(txtPesquisar.getText());
        }
        else if(JRbALuno.isSelected())
        {
          aluno.getAluno(txtPesquisar.getText());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Escolha entre aluno e orientador pra fazer a busca!");
        }
         
     }
     
    
    public void mostrarProjeto()
    {   
        tampao.setVisible(false);
        btnEditar.setEnabled(true);
        btnDeletar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnAtivar.setEnabled(true);
        linha_selecionada = jTableMain.getSelectedRow();
        linhaProjeto      = (String.valueOf(jTableMain.getValueAt(linha_selecionada, 0)));
        //JOptionPane.showMessageDialog(null, "Selecionou linha: " + linhaProjeto);
        nomeAluno         = (String.valueOf(jTableMain.getValueAt(linha_selecionada, 1)));
        nomeOrientador    = (String.valueOf(jTableMain.getValueAt(linha_selecionada, 4)));
        String id_aluno   = Main.jTableMain.getValueAt(linha_selecionada, 17).toString();
        String id_aluno1  = Main.jTableMain.getValueAt(linha_selecionada, 18).toString();
        String modalidade;
        labelMensagem.setVisible(false);
        
        if(Main.jTableMain.getValueAt(linha_selecionada, 23).toString().equals("CANCELADO"))
        {
            btnCancelar.setVisible(false);
            menuCancelarProjeto.setEnabled(false);
            btnAtivar.setVisible(true);
            labelProjetoCancelado.setForeground(new Color(230,0,0));
            labelProjetoCancelado.setText("- Projeto cancelado");
        }else{
            btnCancelar.setVisible(true);
            menuCancelarProjeto.setEnabled(true);
            btnAtivar.setVisible(false);
            labelProjetoCancelado.setText("");
        }
        
        /*if(situacaoMain.equals("1"))
        {
            btnCancelar.setVisible(false);
        }else{
            btnCancelar.setVisible(true);
        }*/
        
        
        if (id_aluno.equals(id_aluno1))
        {
            modalidade = Main.jTableMain.getValueAt(linha_selecionada, 13).toString();
            if(modalidade.equals("PIVIC/IF Goiano") || modalidade.equals("PIVIC Jr/IF Goiano"))
            {
                menuEmitirDeclaracaoValor.setEnabled(false);
            }else 
            {
                menuEmitirDeclaracaoValor.setEnabled(true);                
            }
        }else 
        {
            modalidade = Main.jTableMain.getValueAt(linha_selecionada, 14).toString();
            if(modalidade.equals("PIVIC/IF Goiano") || modalidade.equals("PIVIC Jr/IF Goiano"))
            {
                menuEmitirDeclaracaoValor.setEnabled(false);          
            }else 
            {
                
                    menuEmitirDeclaracaoValor.setEnabled(true);
            }        
        }
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 15)).equals("1"))
        {
          aluno1Substituto.setForeground(new Color(230,0,0));
          aluno1Substituto.setText(" - Substituto*");
        }
        else
        {
            aluno1Substituto.setForeground(new Color(230,0,0));
            aluno1Substituto.setText("");
        }
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 16)).equals("1"))
        {
          aluno2Substituto.setForeground(new Color(230,0,0));
          aluno2Substituto.setText(" - Substituto*");
        }
        else
        {
            aluno2Substituto.setForeground(new Color(230,0,0));
            aluno2Substituto.setText("");
        }
                
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 3)).equals("null"))
        {
            txtAluno2.setText("(Nenhum)");
            relatorios2.setVisible(false);
            rParcial2.setVisible(false);
            rFinal2.setVisible(false);
            rCEIT2.setVisible(false);
            jLabelParcial2.setVisible(false);
            jLabelFinal2.setVisible(false);
            jLabelCeit2.setVisible(false);
            certificado2.setVisible(false);
            certificadoo2.setVisible(false);
        }
        else
        {
            txtAluno2.setText(String.valueOf(jTableMain.getValueAt(linha_selecionada, 3)));
            relatorios2.setVisible(true);
            rParcial2.setVisible(true);
            rFinal2.setVisible(true);
            rCEIT2.setVisible(true);
            jLabelParcial2.setVisible(true);
            jLabelFinal2.setVisible(true);
            jLabelCeit2.setVisible(true);            
            certificado2.setVisible(true);
            certificadoo2.setVisible(true);
        }
        txtOrientador.setText(nomeOrientador);
        txtVigencia.setText(String.valueOf(jTableMain.getValueAt(linha_selecionada, 5)));
        txtProjeto.setText(String.valueOf(jTableMain.getValueAt(linha_selecionada, 6)));
        txtAluno1.setText(String.valueOf(jTableMain.getValueAt(linha_selecionada, 2)));
        txtModalidadeAluno1.setText(String.valueOf(jTableMain.getValueAt(linha_selecionada, 13)));
        txtModalidadeAluno2.setText(String.valueOf(jTableMain.getValueAt(linha_selecionada, 14)));
        
        
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 7)).equals("1"))
        {
          rParcial1.setText(" OK");
          rParcial1.setForeground(new Color(0,204,51));
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 7)).equals("0")){
          rParcial1.setText(" -");
          rParcial1.setForeground(Color.black);
        }else{  
            rParcial1.setText(" OK - Atrasado");
            rParcial1.setForeground(new Color(230,0,0));
        }
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 8)).equals("1"))
        {
          rFinal1.setText(" OK");
          rFinal1.setForeground(new Color(0,204,51));
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 8)).equals("0")){
          rFinal1.setText(" -");
          rFinal1.setForeground(Color.black);
        }else{
          rFinal1.setText(" OK - Atrasado");
          rFinal1.setForeground(new Color(230,0,0));
          
        }
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 9)).equals("1"))
        {
          rCEIT1.setText(" OK");
          rCEIT1.setForeground(new Color(0,204,51));
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 9)).equals("0")){
          rCEIT1.setText(" -");
          rCEIT1.setForeground(Color.black);
        }else{
            rCEIT1.setText(" OK - Atrasado");
            rCEIT1.setForeground(new Color(230,0,0));
        }
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 10)).equals("1"))
        {
          rParcial2.setText(" OK");
          rParcial2.setForeground(new Color(0,204,51));
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 10)).equals("0")){
          rParcial2.setText(" -");
          rParcial2.setForeground(Color.black);
        }else{
        rParcial2.setText(" OK - Atrasado");
        rParcial2.setForeground(new Color(230,0,0));
        }        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 11)).equals("1"))
        {
          rFinal2.setText(" OK");
          rFinal2.setForeground(new Color(0,204,51));
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 11)).equals("0")){
          rFinal2.setText(" -");
          rFinal2.setForeground(Color.black);
        }else{
            rFinal2.setText(" OK - Atrasado");
            rFinal2.setForeground(new Color(230,0,0));
        }
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 12)).equals("1"))
        {
          rCEIT2.setText(" OK");
          rCEIT2.setForeground(new Color(0,204,51));
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 12)).equals("0")){
          rCEIT2.setText(" -");
          rCEIT2.setForeground(Color.black);
        }else{
            rCEIT2.setText(" OK - Atrasado");
            rCEIT2.setForeground(new Color(230,0,0));
        }
        
        switch (String.valueOf(jTableMain.getValueAt(linha_selecionada, 26))) {
            case "2":
                certificadoo1.setText(" Enviado");
                certificadoo1.setForeground(new Color(0,204,51));
                break;
            case "1":
                certificadoo1.setText(" Gerado");
                certificadoo1.setForeground(new Color(255,153,0));
                break;
            default:
                certificadoo1.setText(" - ");
                certificadoo1.setForeground(Color.black);
                break;
        }
        
        switch (String.valueOf(jTableMain.getValueAt(linha_selecionada, 27))) 
        {
            case "2":
                certificadoo2.setText(" Enviado");
                certificadoo2.setForeground(new Color(0,204,51));
                break;
            case "1":
                certificadoo2.setText(" Gerado");
                certificadoo2.setForeground(new Color(255,153,0));
                break;
            default:
                certificadoo2.setText(" - ");
                certificadoo2.setForeground(Color.black);
                break;
        }
        
        switch (String.valueOf(jTableMain.getValueAt(linha_selecionada, 28)))
        {
            case "2":
                certificadoOrientador.setText(" Enviado");
                certificadoOrientador.setForeground(new Color(0,204,51));
                break;
            case "1":
                certificadoOrientador.setText(" Gerado");
                certificadoOrientador.setForeground(new Color(255,153,0));
                break;
            default:
                certificadoOrientador.setText(" - ");
                certificadoOrientador.setForeground(Color.black);
                break;
        }
        
        //JOptionPane.showMessageDialog(null, String.valueOf(jTableMain.getValueAt(linha_selecionada, 0)) + "" + String.valueOf(jTableMain.getValueAt(linha_selecionada, 1))+ "" + String.valueOf(jTableMain.getValueAt(linha_selecionada, 2))+ "" + String.valueOf(jTableMain.getValueAt(linha_selecionada, 3))+ "" + String.valueOf(jTableMain.getValueAt(linha_selecionada, 4))+ "" + String.valueOf(jTableMain.getValueAt(linha_selecionada, 5)) + "" + String.valueOf(jTableMain.getValueAt(linha_selecionada, 12)) + "" + String.valueOf(jTableMain.getValueAt(linha_selecionada, 13))+ String.valueOf(jTableMain.getValueAt(linha_selecionada, 14))+ String.valueOf(jTableMain.getValueAt(linha_selecionada, 15))); 
    }
    
    public void desistirAluno()
    {
        Projeto p = new Projeto();
        String modalidade_aluno1,  modalidade_aluno2;
      
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        Date date = new Date();
        
        int aluno1, aluno2, id_alunoDesistente, linha;

        linha = Integer.parseInt((String.valueOf(jTableMain.getValueAt(linha_selecionada, 0))));
        id_alunoDesistente = Integer.parseInt(String.valueOf(jTableMain.getValueAt(linha_selecionada, 17)));
        aluno1 = Integer.parseInt(String.valueOf(jTableMain.getValueAt(linha_selecionada, 18)));

        modalidade_aluno1 = String.valueOf(jTableMain.getValueAt(linha_selecionada,13)); 
        modalidade_aluno2 = String.valueOf(jTableMain.getValueAt(linha_selecionada,14));



        p.setAlunoDesistente(String.valueOf(jTableMain.getValueAt(linha_selecionada, 1)));
        p.setVigenciaProjeto(String.valueOf(jTableMain.getValueAt(linha_selecionada, 5)));
        p.setNomeOrientador(String.valueOf(jTableMain.getValueAt(linha_selecionada, 4)));
        p.setNomeProjeto(String.valueOf(jTableMain.getValueAt(linha_selecionada, 6)));
        p.setData_desistencia(String.valueOf(dateFormat.format(date)));


        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 19)).equals("null"))
        {
            JOptionPane.showMessageDialog(null, "O projeto não pode ficar sem nenhum aluno!");
        }else{

        aluno2 = Integer.parseInt(String.valueOf(jTableMain.getValueAt(linha_selecionada, 19)));

        if(id_alunoDesistente == aluno1)
        {
            p.setModalidadeDesistente(modalidade_aluno1);
            p.setAluno1(aluno2);
           if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 10)).equals("1"))
           {
                p.setrParcial1(1);
           }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 10)).equals("2"))
           {
                p.setrParcial1(2);
           }else
           {
            p.setrParcial1(0);
           }
           
           if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 11)).equals("1"))
           {
                p.setrFinal1(1);
           }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 11)).equals("2"))
           {
                p.setrFinal1(2);
           }else
           {
               p.setrFinal1(0);
           }
           if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 12)).equals("1"))
           {
                p.setCeit1(1);
           }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 12)).equals("2"))
           {
                p.setCeit1(2);
           }else
           {
                p.setCeit1(0);
           }
           if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 16)).equals("1"))
           {
                p.setAluno1_substituto(true);
           }else
           {
                p.setAluno1_substituto(false);
           }
           
            switch (String.valueOf(jTableMain.getValueAt(linha_selecionada, 27)))
            {
                case "2":
                    p.setCertificado1(2);
                    break;
                case "1":
                    p.setCertificado1(1);
                    break;
                default:
                    p.setCertificado1(0);
                    break;
            }
           
            p.setModalidadeAluno1(modalidade_aluno2);
            p.setAluno2(0);
            p.setAluno2_substituto(false);
            p.setrParcial2(0);
            p.setrFinal2(0);
            p.setCeit2(0);
            p.setCertificado2(0);
            p.setModalidadeAluno2("(Nenhum)");

            }
        else if(id_alunoDesistente == aluno2)
        {
           p.setModalidadeDesistente(modalidade_aluno2);

           p.setAluno1(aluno1);
           p.setModalidadeAluno1(modalidade_aluno1);
           p.setAluno2(0);
           p.setAluno2_substituto(false);
           
           if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 7)).equals("1"))
           {
                p.setrParcial1(1);
           }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 7)).equals("2"))
           {
                p.setrParcial1(2);
           }else
           {
            p.setrParcial1(0);
           }
           if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 8)).equals("1"))
           {
                p.setrFinal1(1);
           }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 8)).equals("2"))
           {
                p.setrFinal1(2);
           }else
           {
            p.setrFinal1(0);
           }
           if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 9)).equals("1"))
           {
                p.setCeit1(1);
           }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 9)).equals("2"))
           {
                p.setCeit1(2);
           }else
           {
            p.setCeit1(0);
           }

           if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 15)).equals("1"))
           {
                p.setAluno1_substituto(true);
           }else
           {
                p.setAluno1_substituto(false);
           }
           
           if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 26)).equals("2"))
           {
               p.setCertificado1(2);
           }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 26)).equals("1"))
           {
                p.setCertificado1(1);
           }else
           {
                p.setCertificado1(0);
           }

           p.setrParcial2(0);
           p.setrFinal2(0);
           p.setCeit2(0);
           p.setAluno2(0);
           p.setCertificado2(0);
           p.setModalidadeAluno2("(Nenhum)");

        }

        projeto.assinalarDesistencia(linha, p);
        aluno.getAluno("");
        }
    }
        
    public void cancelaProjeto()
    {
      if(linhaProjeto.equals("0"))
        {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione um projeto para cancelar");
          labelMensagem.setBackground(new Color(255,102,0));
        }else
        {
            JFrame mainFrame = new JFrame();
            CancelarProjeto projetoCancela = new CancelarProjeto(mainFrame, true);
            projetoCancela.setLocationRelativeTo(mainFrame);
            projetoCancela.setVisible(true);
        }     
    }
    
    public void deletarProjeto()
    {
        if(linhaProjeto.equals("0"))
        {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione um projeto para deletar");
          labelMensagem.setBackground(new Color(255,102,0));
        }
        else
        {
            int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse projeto permanentemente?");
            if(resposta == 0)
            {
                if(jRbOrientador.isSelected())
                {
                        projeto.deletarProjeto(linhaProjeto);     
                        linhaProjeto = "0"; 
                        orientador.getOrientador("");
                        String vig = String.valueOf(jComboBoxVigencia.getSelectedItem());
                        jComboBoxVigencia.removeAllItems();
                        jComboBoxVigencia.addItem("Todos");
                        populaComboBoxVigencia();
                        jComboBoxVigencia.setSelectedItem(vig);
                }else if(JRbALuno.isSelected())
                {
                        projeto.deletarProjeto(linhaProjeto);     
                        linhaProjeto = "0"; 
                        aluno.getAluno("");
                        String vig = String.valueOf(jComboBoxVigencia.getSelectedItem());
                        jComboBoxVigencia.removeAllItems();
                        jComboBoxVigencia.addItem("Todos");
                        populaComboBoxVigencia();
                        jComboBoxVigencia.setSelectedItem(vig);
                        
                }else{
                    JOptionPane.showMessageDialog(null, "Escolha entre aluno e orientador pra fazer a busca!");
                }
            
                
            }else if(resposta == 1)
            {
                
            }
            else{
                
            }
        }
        
        
    }
    public static void limparProjeto()
    {     
        if(JRbALuno.isSelected())
        {
            JRbALuno.getAction();
        }else{
            jRbOrientador.getAction();
        }
        tampao.setVisible(true);
        btnEditar.setEnabled(false);
        btnDeletar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnAtivar.setEnabled(false);
    }
    
    public void emitirDeclaracao() throws ParseException
    {
       {
            if(JRbALuno.isSelected())
            {
             DeclaracaoDAO declaracao = new DeclaracaoDAO();
             declaracao.geraDeclaracaoAluno(linhaProjeto);
            }
            else
            {
                DeclaracaoDAO declaracao = new DeclaracaoDAO();
                declaracao.geraDeclaracaoOrientador(linhaProjeto);
            }
       }
    }
    public void emitirDeclaracaoValor() throws ParseException
    {
        String arq = Usuario.getSalvar_em() + "\\00 - modelo\\declaracao.png";
        if(!new File(arq).exists())
        {
            JOptionPane.showMessageDialog(null, "Primeiro defina uma imagem de modelo para a declaracao!");

        }else
        {       
            if(JRbALuno.isSelected())
            {
             DeclaracaoDAO declaracao = new DeclaracaoDAO();
             declaracao.geraDeclaracaoAlunoValor(linhaProjeto);
            }
            else
            {
            DeclaracaoDAO declaracao = new DeclaracaoDAO();
            declaracao.geraDeclaracaoOrientador(linhaProjeto);
            }
        }
       
    }
      public void editarProjeto(){
      
            JFrame mainFrame = new JFrame();
            AlterarProjeto projetoAlterar = new AlterarProjeto(mainFrame, true);
            projetoAlterar.setLocationRelativeTo(mainFrame);
            projetoAlterar.setVisible(true);
      
    } 
    public void substituirAluno()
    {
      if(linhaProjeto.equals("0"))
      {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione um aluno para substituir");
          labelMensagem.setBackground(new Color(255,102,0));
      }
      else
      {
        JFrame mainFrame = new JFrame();
        SubstituirAluno alunoSubstituir = new SubstituirAluno(mainFrame, true);
        alunoSubstituir.setLocationRelativeTo(mainFrame);
        alunoSubstituir.setVisible(true);
      }
    }
    
    public void exportar()
    {
        if(jTableMain.getRowCount() == 0)
        {
            JOptionPane.showMessageDialog(null, "Não há dados na tabela para exportar.","Mayck Lima",JOptionPane.INFORMATION_MESSAGE);
        }else
        {
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
            tb.add(jTableMain);
            nome.add("Principal");
            String file = chooser.getSelectedFile().toString().concat(".xls");
            
            try {
                ExportarExcel e = new ExportarExcel(new File(file),tb,nome);
                if(e.exportar())
                {
                    JOptionPane.showMessageDialog(null, "Os dados foram exportados");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }     
      }
    }
    
    public void gerarCertificado()
    {
      if(linhaProjeto.equals("0"))
      {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione um aluno ou orientador para emitir o certificado");
          labelMensagem.setBackground(new Color(255,102,0));
          
      }else
      {
        String arq = Usuario.getSalvar_em() + "\\00 - modelo\\certificado.png";
        if(!new File(arq).exists())
        {
            JOptionPane.showMessageDialog(null, "Primeiro defina uma imagem de modelo para certificado!");

        }else
        {
            if(jRbOrientador.isSelected())
            {
                try 
                {
                    JFrame mainFrame = new JFrame();
                    GerarCertificadoOrientador certificado = new GerarCertificadoOrientador(mainFrame, true);
                    certificado.setLocationRelativeTo(mainFrame);
                    certificado.setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(JRbALuno.isSelected())
            {            
                try 
                {
                    JFrame mainFrame = new JFrame();
                    GerarCertificadoAluno certificado = new GerarCertificadoAluno(mainFrame, true);
                    certificado.setLocationRelativeTo(mainFrame);
                    certificado.setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 

            else
            {
                JOptionPane.showMessageDialog(null, "Escolha entre aluno e orientador para gerar o certificado");
            }
          }
        }
        
    }
    
    public void emitirDeclaracaoOriginal()
    {
      int resposta;
      int resposta2 = 1;
      if(linhaProjeto.equals("0"))
      {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione um aluno ou orientador para emitir a declaração");
          labelMensagem.setBackground(new Color(255,102,0));
      }else
      {
          String arq = Usuario.getSalvar_em() + "\\00 - modelo\\declaracao.png";
        if(!new File(arq).exists())
        {
            JOptionPane.showMessageDialog(null, "Primeiro defina uma imagem de modelo para a declaracao!");

        }else{
            if(Main.jTableMain.getValueAt(linha_selecionada, 23).toString().equals("CANCELADO"))
        {
            resposta2 = JOptionPane.showConfirmDialog(null, "Este projeto está cancelado, deseja emitir uma declaração mesmo assim?", "Declaração aluno",JOptionPane.YES_NO_OPTION);
        
            if(resposta2 == 0)
            {
                if(JRbALuno.isSelected())
                {
                resposta = JOptionPane.showConfirmDialog(null, "Deseja emitir declaração para " + nomeAluno + "?", "Declaração aluno",JOptionPane.YES_NO_OPTION);          
                }else
                {
                resposta = JOptionPane.showConfirmDialog(null, "Deseja emitir declaração para " + nomeOrientador + "?", "Declaração orientador", JOptionPane.YES_NO_OPTION);
                }            

                    if(resposta == 0) 
                    {                   
                        try {
                                emitirDeclaracao();
                            } catch (ParseException ex) 
                            {
                                System.err.print(ex);
                            }

                    }
            }else{
                
            }
            
        }else
        {
            if(JRbALuno.isSelected())
            {
            resposta = JOptionPane.showConfirmDialog(null, "Deseja emitir declaração para " + nomeAluno + "?", "Declaração aluno",JOptionPane.YES_NO_OPTION);          
            }else
            {
            resposta = JOptionPane.showConfirmDialog(null, "Deseja emitir declaração para " + nomeOrientador + "?", "Declaração orientador", JOptionPane.YES_NO_OPTION);
            } 
                if(resposta == 0) 
                {                   
                    try 
                    {
                        emitirDeclaracao();
                    } catch (ParseException ex) 
                    {
                        System.err.print(ex);
                    }
                }
        }     
        }
           
      }
    }
    
    public void verOcorrencias()
    {
        if(linhaProjeto.equals("0"))
      {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione um projeto para ver as ocorrências nele");
          labelMensagem.setBackground(new Color(255,102,0));
      }else{
        JFrame mainFrame = new JFrame();
        OcorrenciasProjeto ocorrencias = new OcorrenciasProjeto(mainFrame, true);
        ocorrencias.setLocationRelativeTo(mainFrame);
        ocorrencias.setVisible(true);
      }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbAlunoOrientador = new javax.swing.ButtonGroup();
        jbMedioSuperior = new javax.swing.ButtonGroup();
        jbAtivoCancelados = new javax.swing.ButtonGroup();
        jbModalidade = new javax.swing.ButtonGroup();
        detalhes = new javax.swing.JPopupMenu();
        menuEditar = new javax.swing.JMenuItem();
        menuEmitirDeclaracao = new javax.swing.JMenuItem();
        menuEmitirDeclaracaoValor = new javax.swing.JMenuItem();
        menuSubstituir = new javax.swing.JMenuItem();
        menuGerarCertificado = new javax.swing.JMenuItem();
        menuCancelarProjeto = new javax.swing.JMenuItem();
        menuVerOcorrencias = new javax.swing.JMenuItem();
        menuVerdetalhes = new javax.swing.JMenuItem();
        Tabela = new javax.swing.JScrollPane();
        jTableMain = new javax.swing.JTable();
        jrbMedio = new javax.swing.JRadioButton();
        jrbSuperior = new javax.swing.JRadioButton();
        separadorAlunos = new javax.swing.JSeparator();
        tampao = new javax.swing.JLabel();
        jRbOrientador = new javax.swing.JRadioButton();
        JRbALuno = new javax.swing.JRadioButton();
        txtPesquisar = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jComboBoxVigencia = new javax.swing.JComboBox<>();
        Vigencia = new javax.swing.JLabel();
        btnDesistencia = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        labelMensagem = new javax.swing.JLabel();
        exportar = new javax.swing.JButton();
        Detalhes = new javax.swing.JLabel();
        btnSubstituir = new javax.swing.JButton();
        aluno1Substituto = new javax.swing.JLabel();
        aluno2Substituto = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        Aluno1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        Aluno2 = new javax.swing.JLabel();
        labelModalidadeAluno2 = new javax.swing.JLabel();
        txtModalidadeAluno2 = new javax.swing.JTextField();
        txtModalidadeAluno1 = new javax.swing.JTextField();
        MLabelodalidadeAluno1 = new javax.swing.JLabel();
        relatorios1 = new javax.swing.JLabel();
        jLabelParcial1 = new javax.swing.JLabel();
        jLabelFinal1 = new javax.swing.JLabel();
        certificado1 = new javax.swing.JLabel();
        jLabelCeit1 = new javax.swing.JLabel();
        rParcial1 = new javax.swing.JLabel();
        rFinal1 = new javax.swing.JLabel();
        rCEIT1 = new javax.swing.JLabel();
        certificadoo1 = new javax.swing.JLabel();
        relatorios2 = new javax.swing.JLabel();
        jLabelParcial2 = new javax.swing.JLabel();
        jLabelFinal2 = new javax.swing.JLabel();
        jLabelCeit2 = new javax.swing.JLabel();
        certificado2 = new javax.swing.JLabel();
        certificadoo2 = new javax.swing.JLabel();
        rCEIT2 = new javax.swing.JLabel();
        rFinal2 = new javax.swing.JLabel();
        rParcial2 = new javax.swing.JLabel();
        Vigencia1 = new javax.swing.JLabel();
        Orientador = new javax.swing.JLabel();
        txtOrientador = new javax.swing.JTextField();
        txtVigencia = new javax.swing.JTextField();
        certificado3 = new javax.swing.JLabel();
        certificadoOrientador = new javax.swing.JLabel();
        Projeto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtProjeto = new javax.swing.JTextArea();
        btnEditar = new javax.swing.JButton();
        btnHistorico = new javax.swing.JButton();
        btnEmitirDeclaracao = new javax.swing.JButton();
        btnGerarCertificado = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        txtAluno1 = new javax.swing.JTextField();
        txtAluno2 = new javax.swing.JTextField();
        btnFilttro = new javax.swing.JButton();
        btnAtivar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        labelProjetoCancelado = new javax.swing.JLabel();
        timeLine = new javax.swing.JButton();
        btnModeloCert = new javax.swing.JButton();
        btnModeloDecl = new javax.swing.JButton();
        bg = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        Opcoes = new javax.swing.JMenu();
        addUsuario = new javax.swing.JMenuItem();
        editarUsuario = new javax.swing.JMenuItem();
        excluirUsuario = new javax.swing.JMenuItem();
        sair = new javax.swing.JMenuItem();
        Alunos = new javax.swing.JMenu();
        Orientadores = new javax.swing.JMenu();
        Ocorrencias = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        Relatorios = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        Registro = new javax.swing.JMenu();
        Configurar = new javax.swing.JMenu();
        salvarEm = new javax.swing.JMenuItem();
        menuSobre = new javax.swing.JMenu();

        menuEditar.setText("Editar projeto");
        menuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditarActionPerformed(evt);
            }
        });
        detalhes.add(menuEditar);

        menuEmitirDeclaracao.setText("Emitir declaração");
        menuEmitirDeclaracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEmitirDeclaracaoActionPerformed(evt);
            }
        });
        detalhes.add(menuEmitirDeclaracao);

        menuEmitirDeclaracaoValor.setText("Emitir declaração com valor");
        menuEmitirDeclaracaoValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEmitirDeclaracaoValorActionPerformed(evt);
            }
        });
        detalhes.add(menuEmitirDeclaracaoValor);

        menuSubstituir.setText("Substituir aluno");
        menuSubstituir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSubstituirActionPerformed(evt);
            }
        });
        detalhes.add(menuSubstituir);

        menuGerarCertificado.setText("Gerar certificado");
        menuGerarCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGerarCertificadoActionPerformed(evt);
            }
        });
        detalhes.add(menuGerarCertificado);

        menuCancelarProjeto.setText("Cancelar projeto");
        menuCancelarProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCancelarProjetoActionPerformed(evt);
            }
        });
        detalhes.add(menuCancelarProjeto);

        menuVerOcorrencias.setText("Ocorrências do projeto");
        menuVerOcorrencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVerOcorrenciasActionPerformed(evt);
            }
        });
        detalhes.add(menuVerOcorrencias);

        menuVerdetalhes.setText("Mais detalhes");
        menuVerdetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVerdetalhesActionPerformed(evt);
            }
        });
        detalhes.add(menuVerdetalhes);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SGPP - Sistema de Gerenciamento de Projetos de Pesquisa");
        setBackground(new java.awt.Color(255, 51, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jTableMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableMain.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTableMainMouseMoved(evt);
            }
        });
        jTableMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMainMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableMainMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableMainMouseReleased(evt);
            }
        });
        jTableMain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableMainKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableMainKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTableMainKeyTyped(evt);
            }
        });
        Tabela.setViewportView(jTableMain);

        getContentPane().add(Tabela);
        Tabela.setBounds(20, 140, 730, 480);

        jbMedioSuperior.add(jrbMedio);
        jrbMedio.setText("Ensino Médio");
        jrbMedio.setToolTipText("Clique para ver os projetos do ensino médio");
        jrbMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMedioActionPerformed(evt);
            }
        });
        getContentPane().add(jrbMedio);
        jrbMedio.setBounds(20, 120, 120, 23);

        jbMedioSuperior.add(jrbSuperior);
        jrbSuperior.setText("Ensino Superior");
        jrbSuperior.setToolTipText("Clique para ver os projetos do ensino superior");
        jrbSuperior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbSuperiorActionPerformed(evt);
            }
        });
        getContentPane().add(jrbSuperior);
        jrbSuperior.setBounds(20, 100, 120, 20);

        separadorAlunos.setToolTipText("Clique para ver os alunos  do ensino superior");
        getContentPane().add(separadorAlunos);
        separadorAlunos.setBounds(20, 100, 190, 10);

        tampao.setBackground(new java.awt.Color(236, 236, 236));
        tampao.setOpaque(true);
        getContentPane().add(tampao);
        tampao.setBounds(760, 110, 610, 490);

        jbAlunoOrientador.add(jRbOrientador);
        jRbOrientador.setText("Orientadores");
        jRbOrientador.setToolTipText("Clique para ver os orientadores que tenham projeto");
        jRbOrientador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRbOrientadorFocusGained(evt);
            }
        });
        jRbOrientador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRbOrientadorActionPerformed(evt);
            }
        });
        getContentPane().add(jRbOrientador);
        jRbOrientador.setBounds(100, 80, 110, 23);

        jbAlunoOrientador.add(JRbALuno);
        JRbALuno.setSelected(true);
        JRbALuno.setText("Alunos");
        JRbALuno.setToolTipText("Clique para ver os alunos que tenham projeto");
        JRbALuno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JRbALunoFocusGained(evt);
            }
        });
        JRbALuno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRbALunoActionPerformed(evt);
            }
        });
        getContentPane().add(JRbALuno);
        JRbALuno.setBounds(20, 80, 80, 23);

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
        txtPesquisar.setBounds(350, 110, 340, 30);

        btnPesquisar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisa.png"))); // NOI18N
        btnPesquisar.setToolTipText("Pesquisar um aluno ou orientador");
        btnPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar);
        btnPesquisar.setBounds(690, 100, 60, 40);

        jComboBoxVigencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        jComboBoxVigencia.setToolTipText("Selecione a vigência para ver os projetos");
        jComboBoxVigencia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxVigenciaItemStateChanged(evt);
            }
        });
        jComboBoxVigencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxVigenciaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBoxVigenciaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jComboBoxVigenciaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBoxVigenciaMousePressed(evt);
            }
        });
        jComboBoxVigencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVigenciaActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxVigencia);
        jComboBoxVigencia.setBounds(20, 50, 100, 30);

        Vigencia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Vigencia.setText("Vigência");
        Vigencia.setFocusable(false);
        getContentPane().add(Vigencia);
        Vigencia.setBounds(20, 30, 100, 20);

        btnDesistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/desistencia32.png"))); // NOI18N
        btnDesistencia.setText("Desistência");
        btnDesistencia.setToolTipText("Clique se um aluno deseja desistir do projeto.");
        btnDesistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesistenciaActionPerformed(evt);
            }
        });
        getContentPane().add(btnDesistencia);
        btnDesistencia.setBounds(910, 560, 130, 35);

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        btnCadastrar.setText("Novo Projeto");
        btnCadastrar.setToolTipText("Clique para adicionar um projeto");
        btnCadastrar.setOpaque(false);
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrar);
        btnCadastrar.setBounds(620, 620, 130, 30);

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete_projeto.png"))); // NOI18N
        btnDeletar.setText("Deletar Projeto");
        btnDeletar.setToolTipText("Clique para deletar o projeto");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });
        getContentPane().add(btnDeletar);
        btnDeletar.setBounds(480, 620, 140, 30);

        labelMensagem.setBackground(new java.awt.Color(0, 204, 51));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 1620, 30);

        exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excel.png"))); // NOI18N
        exportar.setText("Exportar");
        exportar.setToolTipText("Exportar tabela para excel");
        exportar.setFocusable(false);
        exportar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarActionPerformed(evt);
            }
        });
        getContentPane().add(exportar);
        exportar.setBounds(250, 110, 100, 30);

        Detalhes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Detalhes.setText("Detalhes do projeto");
        getContentPane().add(Detalhes);
        Detalhes.setBounds(760, 70, 160, 30);

        btnSubstituir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/change_user-32.png"))); // NOI18N
        btnSubstituir.setText("Substituir Aluno");
        btnSubstituir.setToolTipText("Cliique para substituir um aluno");
        btnSubstituir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubstituirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubstituir);
        btnSubstituir.setBounds(760, 560, 150, 35);

        aluno1Substituto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        getContentPane().add(aluno1Substituto);
        aluno1Substituto.setBounds(810, 120, 110, 20);

        aluno2Substituto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        getContentPane().add(aluno2Substituto);
        aluno2Substituto.setBounds(810, 240, 110, 20);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(750, 100, 590, 10);

        Aluno1.setBackground(new java.awt.Color(204, 204, 204));
        Aluno1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Aluno1.setText("Aluno 1");
        Aluno1.setFocusable(false);
        getContentPane().add(Aluno1);
        Aluno1.setBounds(760, 120, 50, 20);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(750, 110, 10, 540);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(760, 210, 570, 20);

        Aluno2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Aluno2.setText("Aluno 2");
        Aluno2.setFocusable(false);
        getContentPane().add(Aluno2);
        Aluno2.setBounds(760, 240, 70, 20);

        labelModalidadeAluno2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelModalidadeAluno2.setText("Modalidade");
        labelModalidadeAluno2.setFocusable(false);
        getContentPane().add(labelModalidadeAluno2);
        labelModalidadeAluno2.setBounds(1080, 240, 90, 20);

        txtModalidadeAluno2.setEditable(false);
        txtModalidadeAluno2.setBackground(new java.awt.Color(204, 204, 204));
        txtModalidadeAluno2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtModalidadeAluno2.setFocusable(false);
        txtModalidadeAluno2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModalidadeAluno2ActionPerformed(evt);
            }
        });
        txtModalidadeAluno2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModalidadeAluno2KeyTyped(evt);
            }
        });
        getContentPane().add(txtModalidadeAluno2);
        txtModalidadeAluno2.setBounds(1080, 260, 130, 30);

        txtModalidadeAluno1.setEditable(false);
        txtModalidadeAluno1.setBackground(new java.awt.Color(204, 204, 204));
        txtModalidadeAluno1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtModalidadeAluno1.setFocusable(false);
        txtModalidadeAluno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModalidadeAluno1ActionPerformed(evt);
            }
        });
        txtModalidadeAluno1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModalidadeAluno1KeyTyped(evt);
            }
        });
        getContentPane().add(txtModalidadeAluno1);
        txtModalidadeAluno1.setBounds(1080, 140, 130, 30);

        MLabelodalidadeAluno1.setBackground(new java.awt.Color(204, 204, 204));
        MLabelodalidadeAluno1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        MLabelodalidadeAluno1.setText("Modalidade");
        MLabelodalidadeAluno1.setFocusable(false);
        getContentPane().add(MLabelodalidadeAluno1);
        MLabelodalidadeAluno1.setBounds(1080, 120, 100, 20);

        relatorios1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        relatorios1.setText("Relatórios");
        relatorios1.setFocusable(false);
        getContentPane().add(relatorios1);
        relatorios1.setBounds(1220, 110, 140, 20);

        jLabelParcial1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelParcial1.setText("Parcial:");
        getContentPane().add(jLabelParcial1);
        jLabelParcial1.setBounds(1220, 130, 40, 20);

        jLabelFinal1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelFinal1.setText("Final:");
        getContentPane().add(jLabelFinal1);
        jLabelFinal1.setBounds(1220, 150, 30, 20);

        certificado1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        certificado1.setText("Certificado:");
        getContentPane().add(certificado1);
        certificado1.setBounds(1220, 190, 90, 20);

        jLabelCeit1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelCeit1.setText("CEICT:");
        getContentPane().add(jLabelCeit1);
        jLabelCeit1.setBounds(1220, 170, 40, 20);

        rParcial1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(rParcial1);
        rParcial1.setBounds(1260, 130, 110, 20);

        rFinal1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(rFinal1);
        rFinal1.setBounds(1250, 150, 110, 20);

        rCEIT1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(rCEIT1);
        rCEIT1.setBounds(1260, 170, 110, 20);

        certificadoo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(certificadoo1);
        certificadoo1.setBounds(1280, 190, 110, 20);

        relatorios2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        relatorios2.setText("Relatórios");
        relatorios2.setFocusable(false);
        getContentPane().add(relatorios2);
        relatorios2.setBounds(1220, 220, 120, 20);

        jLabelParcial2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelParcial2.setText("Parcial:");
        getContentPane().add(jLabelParcial2);
        jLabelParcial2.setBounds(1220, 240, 40, 20);

        jLabelFinal2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelFinal2.setText("Final:");
        getContentPane().add(jLabelFinal2);
        jLabelFinal2.setBounds(1220, 260, 30, 20);

        jLabelCeit2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelCeit2.setText("CEICT:");
        getContentPane().add(jLabelCeit2);
        jLabelCeit2.setBounds(1220, 280, 40, 20);

        certificado2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        certificado2.setText("Certificado:");
        getContentPane().add(certificado2);
        certificado2.setBounds(1220, 300, 90, 20);

        certificadoo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(certificadoo2);
        certificadoo2.setBounds(1280, 300, 110, 20);

        rCEIT2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(rCEIT2);
        rCEIT2.setBounds(1260, 280, 100, 20);

        rFinal2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(rFinal2);
        rFinal2.setBounds(1250, 260, 100, 20);

        rParcial2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(rParcial2);
        rParcial2.setBounds(1260, 240, 100, 20);

        Vigencia1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Vigencia1.setText("Vigência");
        Vigencia1.setFocusable(false);
        getContentPane().add(Vigencia1);
        Vigencia1.setBounds(1080, 330, 80, 20);

        Orientador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Orientador.setText("Orientador(a)");
        Orientador.setFocusable(false);
        getContentPane().add(Orientador);
        Orientador.setBounds(760, 330, 100, 20);

        txtOrientador.setEditable(false);
        txtOrientador.setBackground(new java.awt.Color(204, 204, 204));
        txtOrientador.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtOrientador.setFocusable(false);
        txtOrientador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrientadorActionPerformed(evt);
            }
        });
        txtOrientador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOrientadorKeyTyped(evt);
            }
        });
        getContentPane().add(txtOrientador);
        txtOrientador.setBounds(760, 350, 310, 30);

        txtVigencia.setEditable(false);
        txtVigencia.setBackground(new java.awt.Color(204, 204, 204));
        txtVigencia.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtVigencia.setFocusable(false);
        txtVigencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVigenciaActionPerformed(evt);
            }
        });
        txtVigencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVigenciaKeyTyped(evt);
            }
        });
        getContentPane().add(txtVigencia);
        txtVigencia.setBounds(1080, 350, 80, 30);

        certificado3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        certificado3.setText("Certificado:");
        getContentPane().add(certificado3);
        certificado3.setBounds(1170, 360, 90, 20);

        certificadoOrientador.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(certificadoOrientador);
        certificadoOrientador.setBounds(1230, 360, 110, 20);

        Projeto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Projeto.setText("Projeto");
        Projeto.setFocusable(false);
        getContentPane().add(Projeto);
        Projeto.setBounds(760, 390, 100, 20);

        txtProjeto.setEditable(false);
        txtProjeto.setBackground(new java.awt.Color(204, 204, 204));
        txtProjeto.setColumns(20);
        txtProjeto.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtProjeto.setLineWrap(true);
        txtProjeto.setRows(5);
        txtProjeto.setFocusable(false);
        jScrollPane2.setViewportView(txtProjeto);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(760, 410, 580, 100);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        btnEditar.setText("Editar Projeto");
        btnEditar.setToolTipText("Clique editar o projeto");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(760, 520, 130, 35);

        btnHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/historicu.png"))); // NOI18N
        btnHistorico.setText("Ocorrências");
        btnHistorico.setToolTipText("Clique para ver os ocorrências no projeto");
        btnHistorico.setPreferredSize(new java.awt.Dimension(127, 33));
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });
        getContentPane().add(btnHistorico);
        btnHistorico.setBounds(890, 520, 130, 35);

        btnEmitirDeclaracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/declaracao.png"))); // NOI18N
        btnEmitirDeclaracao.setText("Emitir Declaração");
        btnEmitirDeclaracao.setToolTipText("Clique para emitir uma declaração");
        btnEmitirDeclaracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmitirDeclaracaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnEmitirDeclaracao);
        btnEmitirDeclaracao.setBounds(1020, 520, 160, 35);

        btnGerarCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/certificado2.png"))); // NOI18N
        btnGerarCertificado.setText("Gerar Certificado");
        btnGerarCertificado.setToolTipText("Clique para gerar o certificado");
        btnGerarCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarCertificadoActionPerformed(evt);
            }
        });
        getContentPane().add(btnGerarCertificado);
        btnGerarCertificado.setBounds(1180, 520, 160, 35);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(760, 320, 570, 10);

        txtAluno1.setEditable(false);
        txtAluno1.setBackground(new java.awt.Color(204, 204, 204));
        txtAluno1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtAluno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAluno1ActionPerformed(evt);
            }
        });
        getContentPane().add(txtAluno1);
        txtAluno1.setBounds(760, 140, 310, 30);

        txtAluno2.setEditable(false);
        txtAluno2.setBackground(new java.awt.Color(204, 204, 204));
        txtAluno2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        getContentPane().add(txtAluno2);
        txtAluno2.setBounds(760, 260, 310, 30);

        btnFilttro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/filtro.png"))); // NOI18N
        btnFilttro.setToolTipText("Filtro avançado");
        btnFilttro.setOpaque(false);
        btnFilttro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilttroActionPerformed(evt);
            }
        });
        getContentPane().add(btnFilttro);
        btnFilttro.setBounds(200, 110, 50, 30);

        btnAtivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/accepted.png"))); // NOI18N
        btnAtivar.setText("Ativar Projeto");
        btnAtivar.setToolTipText("Clique para ativar o projeto");
        btnAtivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtivarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAtivar);
        btnAtivar.setBounds(340, 620, 130, 30);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelaProjeto.png"))); // NOI18N
        btnCancelar.setText("Cancelar Projeto");
        btnCancelar.setToolTipText("Clique para cancelar o projeto");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(330, 620, 150, 30);

        labelProjetoCancelado.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelProjetoCancelado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        getContentPane().add(labelProjetoCancelado);
        labelProjetoCancelado.setBounds(920, 70, 230, 30);

        timeLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relogioPequeno.png"))); // NOI18N
        timeLine.setText("Atividades recentes");
        timeLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeLineActionPerformed(evt);
            }
        });
        getContentPane().add(timeLine);
        timeLine.setBounds(660, 30, 200, 40);

        btnModeloCert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/certificado2.png"))); // NOI18N
        btnModeloCert.setText("Modelo Certificado");
        btnModeloCert.setToolTipText("Clique para alterar o modelo do certificado");
        btnModeloCert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModeloCertActionPerformed(evt);
            }
        });
        getContentPane().add(btnModeloCert);
        btnModeloCert.setBounds(20, 620, 170, 30);

        btnModeloDecl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/declaracao.png"))); // NOI18N
        btnModeloDecl.setText("Modelo Declaração");
        btnModeloDecl.setToolTipText("Clique para alterar o modelo da declaração");
        btnModeloDecl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModeloDeclActionPerformed(evt);
            }
        });
        getContentPane().add(btnModeloDecl);
        btnModeloDecl.setBounds(20, 650, 170, 30);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        bg.setOpaque(true);
        bg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bgMouseExited(evt);
            }
        });
        getContentPane().add(bg);
        bg.setBounds(-180, -70, 2230, 1290);

        jMenuBar.setBackground(new java.awt.Color(255, 51, 51));
        jMenuBar.setForeground(new java.awt.Color(255, 153, 102));

        Opcoes.setText("Opções");
        Opcoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OpcoesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OpcoesMouseExited(evt);
            }
        });

        addUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionarUsuario.png"))); // NOI18N
        addUsuario.setText("Novo Usuário");
        addUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUsuarioActionPerformed(evt);
            }
        });
        Opcoes.add(addUsuario);

        editarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterarUser.png"))); // NOI18N
        editarUsuario.setText("Editar Conta");
        editarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarUsuarioActionPerformed(evt);
            }
        });
        Opcoes.add(editarUsuario);

        excluirUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/deleteUser.png"))); // NOI18N
        excluirUsuario.setText("Excluir Conta");
        excluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirUsuarioActionPerformed(evt);
            }
        });
        Opcoes.add(excluirUsuario);

        sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sair.png"))); // NOI18N
        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });
        Opcoes.add(sair);

        jMenuBar.add(Opcoes);

        Alunos.setText("Alunos");
        Alunos.setToolTipText("Ver todos os alunos cadastrados");
        Alunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AlunosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AlunosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AlunosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AlunosMousePressed(evt);
            }
        });
        Alunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlunosActionPerformed(evt);
            }
        });
        jMenuBar.add(Alunos);

        Orientadores.setText("Orientadores");
        Orientadores.setToolTipText("Ver todos os orientadores cadastrados");
        Orientadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OrientadoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OrientadoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OrientadoresMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OrientadoresMousePressed(evt);
            }
        });
        Orientadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrientadoresActionPerformed(evt);
            }
        });
        jMenuBar.add(Orientadores);

        Ocorrencias.setText("Ocorrências");
        Ocorrencias.setToolTipText("Ver todas as ocorrências registradas");
        Ocorrencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OcorrenciasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OcorrenciasMouseExited(evt);
            }
        });

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/change_user-32.png"))); // NOI18N
        jMenuItem1.setText("Substituições");
        jMenuItem1.setToolTipText("Clique para ver as substituições ocorridas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Ocorrencias.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/desistencia32.png"))); // NOI18N
        jMenuItem2.setText("Desistências");
        jMenuItem2.setToolTipText("Clique para ver as desistências ocorridas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Ocorrencias.add(jMenuItem2);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelaProjeto.png"))); // NOI18N
        jMenuItem4.setText("Cancelados");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        Ocorrencias.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/declaracao.png"))); // NOI18N
        jMenuItem5.setText("Declarações");
        jMenuItem5.setToolTipText("Clique para ver as declarações emitidas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        Ocorrencias.add(jMenuItem5);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/certificado2.png"))); // NOI18N
        jMenuItem3.setText("Certificados");
        jMenuItem3.setToolTipText("Clique para ver os certificados gerados");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        Ocorrencias.add(jMenuItem3);

        jMenuBar.add(Ocorrencias);

        Relatorios.setText("Relátórios/Certificados");
        Relatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RelatoriosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RelatoriosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RelatoriosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RelatoriosMousePressed(evt);
            }
        });
        Relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RelatoriosActionPerformed(evt);
            }
        });

        jMenuItem6.setText("Alunos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        Relatorios.add(jMenuItem6);

        jMenuItem7.setText("Orientadores");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        Relatorios.add(jMenuItem7);

        jMenuBar.add(Relatorios);

        Registro.setBorder(null);
        Registro.setText("Protocolo");
        Registro.setToolTipText("Controle de Registo Digital");
        Registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegistroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RegistroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RegistroMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RegistroMousePressed(evt);
            }
        });
        Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistroActionPerformed(evt);
            }
        });
        jMenuBar.add(Registro);

        Configurar.setText("Configurar");
        Configurar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ConfigurarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ConfigurarMouseExited(evt);
            }
        });

        salvarEm.setText("Salvar em...");
        salvarEm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salvarEmMouseClicked(evt);
            }
        });
        salvarEm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarEmActionPerformed(evt);
            }
        });
        Configurar.add(salvarEm);

        jMenuBar.add(Configurar);

        menuSobre.setText("Sobre");
        menuSobre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSobreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuSobreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuSobreMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuSobreMousePressed(evt);
            }
        });
        jMenuBar.add(menuSobre);

        setJMenuBar(jMenuBar);

        setSize(new java.awt.Dimension(2050, 826));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtOrientadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrientadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrientadorActionPerformed

    private void txtOrientadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrientadorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrientadorKeyTyped

    private void txtVigenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVigenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVigenciaActionPerformed

    private void txtVigenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVigenciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVigenciaKeyTyped

    private void editarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarUsuarioActionPerformed
        JFrame mainFrame = new JFrame();
        AlterarUsuario usuario = new AlterarUsuario(mainFrame, true);
        usuario.setLocationRelativeTo(mainFrame);
        usuario.setVisible(true);
    }//GEN-LAST:event_editarUsuarioActionPerformed

    private void excluirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirUsuarioActionPerformed
       int resposta  = JOptionPane.showConfirmDialog(null , "Você perderá acesso ao programa com a atual conta, deseja realmente excluí-la?", "Excluir conta de usuário", JOptionPane.YES_NO_OPTION);
        
        if(resposta == 0)
        {
            Login l = new Login();
            UsuarioDAO usuario = new  UsuarioDAO();
            usuario.deletarUsuario();
            dispose();
            l.setVisible(true);
        }else if(resposta == 1)
        {
            JOptionPane.showMessageDialog(null, "Nenhuma alteração foi feita.");
        }
        else if(resposta == 2)
        {
            
        }
    }//GEN-LAST:event_excluirUsuarioActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
       sair();   
    }//GEN-LAST:event_sairActionPerformed

    private void jRbOrientadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRbOrientadorActionPerformed
        
        orientador.getOrientador("");
        txtPesquisar.setEnabled(true);
        btnPesquisar.setEnabled(true);
        btnFilttro.setEnabled(true);
        
         jbMedioSuperior.clearSelection();
         btnDesistencia.setEnabled(false);
         btnSubstituir.setEnabled(false);
         linhaProjeto = "0";
         limparProjeto();
         labelMensagem.setVisible(false);
    }//GEN-LAST:event_jRbOrientadorActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
      deletarProjeto();    
        
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed

    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void txtPesquisarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisarFocusGained
        txtPesquisar.setText("");
        txtPesquisar.setForeground(Color.black);
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtPesquisarFocusGained

    private void txtPesquisarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisarFocusLost
        txtPesquisar.setText("Pesquisar...");
        txtPesquisar.setForeground(Color.GRAY);
    }//GEN-LAST:event_txtPesquisarFocusLost

    private void JRbALunoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JRbALunoFocusGained
        
    }//GEN-LAST:event_JRbALunoFocusGained

    private void jRbOrientadorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRbOrientadorFocusGained
         
    }//GEN-LAST:event_jRbOrientadorFocusGained

    private void JRbALunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRbALunoActionPerformed
    
    aluno.getAluno("");
    txtPesquisar.setEnabled(true);
    btnPesquisar.setEnabled(true);
    btnFilttro.setEnabled(true);
    menuSubstituir.setEnabled(true);
    btnDesistencia.setEnabled(true);
    btnSubstituir.setEnabled(true);
    
    linhaProjeto = "0";
    limparProjeto();
    jbMedioSuperior.clearSelection();
    labelMensagem.setVisible(false);
    }//GEN-LAST:event_JRbALunoActionPerformed

    private void jTableMainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) 
        {
            mostrarProjeto();
        }else if(evt.getKeyCode() == KeyEvent.VK_DELETE)
        {
            deletarProjeto();
        }
    }//GEN-LAST:event_jTableMainKeyPressed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
      if(linhaProjeto.equals("0"))
      {
          labelMensagem.setVisible(true);
          labelMensagem.setText("Selecione um projeto para alterar");
          labelMensagem.setBackground(new Color(255,102,0));
      }
      else
      {
        editarProjeto();
      }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFrame mainFrame = new JFrame();
        SubstituicoesAluno substituicao = new SubstituicoesAluno(mainFrame, true);
        substituicao.setLocationRelativeTo(mainFrame);
        substituicao.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnDesistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesistenciaActionPerformed
       btnFilttro.setEnabled(true);
        assinalarDesistencia();
    }//GEN-LAST:event_btnDesistenciaActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JFrame mainFrame = new JFrame();
        DesistenciasAluno desistencia = new DesistenciasAluno(mainFrame, true);
        desistencia.setLocationRelativeTo(mainFrame);
        desistencia.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnSubstituirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubstituirActionPerformed
       btnFilttro.setEnabled(true);
        substituirAluno();        
    }//GEN-LAST:event_btnSubstituirActionPerformed

    private void jComboBoxVigenciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxVigenciaItemStateChanged
        if(jRbOrientador.isSelected())
        {
           orientador.getOrientador("");     
           jbMedioSuperior.clearSelection();           
        }
        else if(JRbALuno.isSelected())
        {
            aluno.getAluno("");
            jbMedioSuperior.clearSelection();            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Escolha entre aluno e orientador pra fazer a busca!");
        }        
        btnFilttro.setEnabled(false);
        btnFilttro.setEnabled(true);
        limparProjeto();
        linhaProjeto = "0";
    }//GEN-LAST:event_jComboBoxVigenciaItemStateChanged

    private void jComboBoxVigenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxVigenciaMouseClicked
     
    }//GEN-LAST:event_jComboBoxVigenciaMouseClicked

    private void btnEmitirDeclaracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmitirDeclaracaoActionPerformed
      emitirDeclaracaoOriginal();
            
    }//GEN-LAST:event_btnEmitirDeclaracaoActionPerformed

    private void OrientadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrientadoresMouseClicked
        abrirOrientador();
    }//GEN-LAST:event_OrientadoresMouseClicked

    private void AlunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlunosMouseClicked
      abrirAluno();
    }//GEN-LAST:event_AlunosMouseClicked

    private void AlunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlunosActionPerformed
      abrirAluno();
    }//GEN-LAST:event_AlunosActionPerformed

    private void AlunosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlunosMousePressed
      abrirAluno();
    }//GEN-LAST:event_AlunosMousePressed

    private void OrientadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrientadoresActionPerformed
       abrirOrientador();
    }//GEN-LAST:event_OrientadoresActionPerformed

    private void OrientadoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrientadoresMousePressed
        abrirOrientador();
    }//GEN-LAST:event_OrientadoresMousePressed

    private void txtModalidadeAluno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModalidadeAluno1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModalidadeAluno1ActionPerformed

    private void txtModalidadeAluno1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalidadeAluno1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModalidadeAluno1KeyTyped

    private void txtModalidadeAluno2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModalidadeAluno2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModalidadeAluno2ActionPerformed

    private void txtModalidadeAluno2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalidadeAluno2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModalidadeAluno2KeyTyped

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed
        //pesquisar();
    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        //pesquisar();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void txtPesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyTyped
       pesquisar();
    }//GEN-LAST:event_txtPesquisarKeyTyped

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        pesquisar();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jTableMainKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMainKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) 
        {
            mostrarProjeto();
        }
    }//GEN-LAST:event_jTableMainKeyReleased

    private void jTableMainKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMainKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) 
        {
            mostrarProjeto();
        }
    }//GEN-LAST:event_jTableMainKeyTyped

    private void jComboBoxVigenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVigenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVigenciaActionPerformed

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoActionPerformed
      verOcorrencias();
    }//GEN-LAST:event_btnHistoricoActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        JFrame mainFrame = new JFrame();
        CadastrarProjeto projetoCadastra = new CadastrarProjeto(mainFrame, true);
        projetoCadastra.setLocationRelativeTo(mainFrame);
        projetoCadastra.setVisible(true);

    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    
    }//GEN-LAST:event_formWindowOpened

    private void addUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUsuarioActionPerformed
       JFrame mainFrame = new JFrame();
       CadastrarUsuario aU = new CadastrarUsuario(mainFrame, true);
       aU.setLocationRelativeTo(mainFrame);
       aU.setVisible(true);
    }//GEN-LAST:event_addUsuarioActionPerformed

    private void RelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RelatoriosActionPerformed
      
    }//GEN-LAST:event_RelatoriosActionPerformed

    private void RelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RelatoriosMouseClicked
      
    }//GEN-LAST:event_RelatoriosMouseClicked

    private void RelatoriosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RelatoriosMousePressed
     
    }//GEN-LAST:event_RelatoriosMousePressed

    private void jTableMainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMainMouseReleased
                if(jTableMain.getSelectedRow() >= 0)
                {
                    if(evt.isPopupTrigger())
                    {                       
                        if(jRbOrientador.isSelected())
                        {
                            detalhes.show(jTableMain, evt.getX(), evt.getY());
                            menuEmitirDeclaracaoValor.setEnabled(false);
                            menuSubstituir.setEnabled(false);
                        }else
                        {
                            detalhes.show(jTableMain, evt.getX(), evt.getY());
                        }                              
                    }  
                }else{
                    //JOptionPane.showMessageDialog(null, "Selecione uma linha");
                }
    }//GEN-LAST:event_jTableMainMouseReleased

    private void menuVerdetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVerdetalhesActionPerformed
        projeto.getDetalhes(linhaProjeto);
        JFrame mainFrame = new JFrame();            
        DetalhesProjeto detalhesprojet = new DetalhesProjeto(mainFrame, true);
        detalhesprojet.setLocationRelativeTo(mainFrame);
        detalhesprojet.setVisible(true);
    }//GEN-LAST:event_menuVerdetalhesActionPerformed

    private void menuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditarActionPerformed
        editarProjeto();
    }//GEN-LAST:event_menuEditarActionPerformed

    private void menuEmitirDeclaracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEmitirDeclaracaoActionPerformed
        emitirDeclaracaoOriginal();
    }//GEN-LAST:event_menuEmitirDeclaracaoActionPerformed

    private void menuSubstituirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSubstituirActionPerformed
        substituirAluno();
    }//GEN-LAST:event_menuSubstituirActionPerformed

    private void jrbMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMedioActionPerformed
        if(JRbALuno.isSelected())
        {
            aluno.getAlunoMedio();
            txtPesquisar.setEnabled(false);
            btnPesquisar.setEnabled(false);
        }else
        {
            orientador.getOrientadorMedio();
            txtPesquisar.setEnabled(false);
            btnPesquisar.setEnabled(false);
        }
        btnFilttro.setEnabled(false);
        tampao.setVisible(true);
    }//GEN-LAST:event_jrbMedioActionPerformed

    private void jrbSuperiorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbSuperiorActionPerformed
        if(JRbALuno.isSelected())
        {
            aluno.getAlunoSuperior();
            txtPesquisar.setEnabled(false);
            btnPesquisar.setEnabled(false);
        }else
        {
            orientador.getOrientadorSuperior();
            txtPesquisar.setEnabled(false);
            btnPesquisar.setEnabled(false);
        }
        btnFilttro.setEnabled(false);
        tampao.setVisible(true);
    }//GEN-LAST:event_jrbSuperiorActionPerformed

    private void menuEmitirDeclaracaoValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEmitirDeclaracaoValorActionPerformed
         
        try {
            emitirDeclaracaoValor();
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuEmitirDeclaracaoValorActionPerformed

    private void menuCancelarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCancelarProjetoActionPerformed
        cancelaProjeto();
    }//GEN-LAST:event_menuCancelarProjetoActionPerformed

    private void jTableMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMainMouseClicked
        if(jTableMain.getSelectedRow() >= 0)
        {
            mostrarProjeto();
            
        }else{
            //JOptionPane.showMessageDialog(null, "Selecione uma linha");
        }
        
    }//GEN-LAST:event_jTableMainMouseClicked

    private void jTableMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMainMousePressed
       if(jTableMain.getSelectedRow() >= 0)
        {
            mostrarProjeto();
        }else{
            //JOptionPane.showMessageDialog(null, "Selecione uma linha");
            }
    }//GEN-LAST:event_jTableMainMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       int resposta  = JOptionPane.showConfirmDialog(null , "Deseja realmente fechar o programa?" , "Saindo do sistema",JOptionPane.YES_NO_OPTION);
        
        if(resposta == 0)
        {
          System.exit(0);            
        }
       else
        {
            
        }
        
       
        
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarActionPerformed
        exportar();        // TODO add your handling code here:
    }//GEN-LAST:event_exportarActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        JFrame mainFrame = new JFrame();
        HistoricoDeclaracao rela = new HistoricoDeclaracao(mainFrame, true);
        rela.setLocationRelativeTo(mainFrame);
        rela.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistroActionPerformed
       abrirRegistro();
    }//GEN-LAST:event_RegistroActionPerformed

    private void RegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistroMouseClicked
         abrirRegistro();
    }//GEN-LAST:event_RegistroMouseClicked

    private void RegistroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistroMousePressed
         abrirRegistro();
    }//GEN-LAST:event_RegistroMousePressed

    private void AlunosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlunosMouseEntered
        Alunos.setText("<html><u><b>Alunos</b></u>");
    }//GEN-LAST:event_AlunosMouseEntered

    private void AlunosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlunosMouseExited
        Alunos.setText("Alunos");
    }//GEN-LAST:event_AlunosMouseExited

    private void OrientadoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrientadoresMouseEntered
        Orientadores.setText("<html><u><b>Orientadores</b></u>");
    }//GEN-LAST:event_OrientadoresMouseEntered

    private void OrientadoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrientadoresMouseExited
        Orientadores.setText("Orientadores");
    }//GEN-LAST:event_OrientadoresMouseExited

    private void OcorrenciasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OcorrenciasMouseEntered
        Ocorrencias.setText("<html><u><b>Ocorrências</b></u>");
    }//GEN-LAST:event_OcorrenciasMouseEntered

    private void OcorrenciasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OcorrenciasMouseExited
        Ocorrencias.setText("Ocorrências");
    }//GEN-LAST:event_OcorrenciasMouseExited

    private void RelatoriosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RelatoriosMouseEntered
            Relatorios.setText("<html><u><b>Relatórios/Certificados</b></u>");
    }//GEN-LAST:event_RelatoriosMouseEntered

    private void RelatoriosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RelatoriosMouseExited
        Relatorios.setText("Relatórios/Certificados");
    }//GEN-LAST:event_RelatoriosMouseExited

    private void RegistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistroMouseEntered
        Registro.setText("<html><u><b>Protocolo</b></u>");
    }//GEN-LAST:event_RegistroMouseEntered

    private void RegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistroMouseExited
        Registro.setText("Protocolo");
    }//GEN-LAST:event_RegistroMouseExited

    private void OpcoesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpcoesMouseEntered
        Opcoes.setText("<html><u><b>Opções</b></u>");
    }//GEN-LAST:event_OpcoesMouseEntered

    private void OpcoesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpcoesMouseExited
        Opcoes.setText("Opções");
    }//GEN-LAST:event_OpcoesMouseExited

    private void menuGerarCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGerarCertificadoActionPerformed
        gerarCertificado();
        
    }//GEN-LAST:event_menuGerarCertificadoActionPerformed

    private void btnGerarCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarCertificadoActionPerformed
      gerarCertificado();
    }//GEN-LAST:event_btnGerarCertificadoActionPerformed

    private void jComboBoxVigenciaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxVigenciaMousePressed
        
    }//GEN-LAST:event_jComboBoxVigenciaMousePressed

    private void jComboBoxVigenciaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxVigenciaMouseEntered
    
    }//GEN-LAST:event_jComboBoxVigenciaMouseEntered

    private void jComboBoxVigenciaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxVigenciaMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVigenciaMouseExited

    private void jTableMainMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMainMouseMoved
      
    }//GEN-LAST:event_jTableMainMouseMoved

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JFrame mainFrame = new JFrame();
        HistoricoCertificado rela = new HistoricoCertificado(mainFrame, true);
        rela.setLocationRelativeTo(mainFrame);
        rela.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void bgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMouseExited
       
    }//GEN-LAST:event_bgMouseExited

    private void bgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMouseEntered
      
    }//GEN-LAST:event_bgMouseEntered

    private void menuVerOcorrenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVerOcorrenciasActionPerformed
        verOcorrencias();        // TODO add your handling code here:
    }//GEN-LAST:event_menuVerOcorrenciasActionPerformed

    private void txtAluno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAluno1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAluno1ActionPerformed

    private void btnFilttroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilttroActionPerformed
     
    }//GEN-LAST:event_btnFilttroActionPerformed

    private void bgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMouseClicked
    }//GEN-LAST:event_bgMouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
           abrirRelatoriosAlunos();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
         abrirRelatoriosOrientadores();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelaProjeto();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAtivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtivarActionPerformed
        int resposta  = JOptionPane.showConfirmDialog(null , "Deseja reativar esse projeto?" , "Reativar projeto", JOptionPane.YES_NO_OPTION);
        if(resposta == 0)
        {
            projeto.recuperarProjeto(linhaProjeto,nomeOrientador);
        } 
        if(jRbOrientador.isSelected())
        {
            if(jrbSuperior.isSelected())
            {
                orientador.getOrientadorSuperior();
            }else if(jrbMedio.isSelected())
            {
                orientador.getOrientadorMedio();
            }else{
                orientador.getOrientador("");
            }
        }
        else if(JRbALuno.isSelected())
        {
          if(jrbSuperior.isSelected())
            {
                aluno.getAlunoSuperior();
            }else if(jrbMedio.isSelected())
            {
                aluno.getAlunoMedio();
            }else{
                aluno.getAluno("");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Escolha entre aluno e orientador pra fazer a busca!");
        }
    }//GEN-LAST:event_btnAtivarActionPerformed

    private void timeLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeLineActionPerformed
            JFrame mainFrame = new JFrame();
            TodasOcorrencias ocorrencias = new TodasOcorrencias(mainFrame, true);
            ocorrencias.setLocationRelativeTo(mainFrame);
            ocorrencias.setVisible(true);
    }//GEN-LAST:event_timeLineActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JFrame mainFrame = new JFrame();
        ProjetosCancelados cancelados = new ProjetosCancelados(mainFrame, true);
        cancelados.setLocationRelativeTo(mainFrame);
        cancelados.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void ConfigurarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfigurarMouseExited
        Configurar.setText("Configurar");
    }//GEN-LAST:event_ConfigurarMouseExited

    private void ConfigurarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfigurarMouseEntered
        Configurar.setText("<html><u><b>Configurar</b></u>");
    }//GEN-LAST:event_ConfigurarMouseEntered

    private void salvarEmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarEmActionPerformed
        JFrame mainFrame = new JFrame();
        SalvarEm salvar = new SalvarEm(mainFrame, true);
        salvar.setLocationRelativeTo(mainFrame);
        salvar.setVisible(true);
    }//GEN-LAST:event_salvarEmActionPerformed

    private void salvarEmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salvarEmMouseClicked

    }//GEN-LAST:event_salvarEmMouseClicked

    private void btnModeloCertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModeloCertActionPerformed
       try 
        {
            JFrame mainFrame = new JFrame();
            ModeloCertificado modelo = new ModeloCertificado(mainFrame, true);
            modelo.setLocationRelativeTo(mainFrame);
            modelo.setVisible(true);
        } catch (HeadlessException e) 
        {
            System.err.print( e);
        }
    }//GEN-LAST:event_btnModeloCertActionPerformed

    private void btnModeloDeclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModeloDeclActionPerformed
        try 
        {
            JFrame mainFrame = new JFrame();
            ModeloDeclaracao modelo = new ModeloDeclaracao(mainFrame, true);
            modelo.setLocationRelativeTo(mainFrame);
            modelo.setVisible(true);
        } catch (HeadlessException e) 
        {
            System.err.print( e);
        }
    }//GEN-LAST:event_btnModeloDeclActionPerformed

    private void menuSobreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSobreMouseClicked
            JFrame mainFrame = new JFrame();
            Sobre sobre = new Sobre(mainFrame, true);
            sobre.setLocationRelativeTo(mainFrame);
            sobre.setVisible(true);
    }//GEN-LAST:event_menuSobreMouseClicked

    private void menuSobreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSobreMousePressed
            JFrame mainFrame = new JFrame();
            Sobre sobre = new Sobre(mainFrame, true);
            sobre.setLocationRelativeTo(mainFrame);
            sobre.setVisible(true);
    }//GEN-LAST:event_menuSobreMousePressed

    private void menuSobreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSobreMouseEntered
         menuSobre.setText("<html><u><b>Sobre</b></u>");
    }//GEN-LAST:event_menuSobreMouseEntered

    private void menuSobreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSobreMouseExited
          menuSobre.setText("Sobre");
    }//GEN-LAST:event_menuSobreMouseExited
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel Aluno1;
    public static javax.swing.JLabel Aluno2;
    private javax.swing.JMenu Alunos;
    private javax.swing.JMenu Configurar;
    private javax.swing.JLabel Detalhes;
    public static javax.swing.JRadioButton JRbALuno;
    private static javax.swing.JLabel MLabelodalidadeAluno1;
    private javax.swing.JMenu Ocorrencias;
    private javax.swing.JMenu Opcoes;
    private javax.swing.JLabel Orientador;
    private javax.swing.JMenu Orientadores;
    private javax.swing.JLabel Projeto;
    private javax.swing.JMenu Registro;
    private javax.swing.JMenu Relatorios;
    private javax.swing.JScrollPane Tabela;
    private javax.swing.JLabel Vigencia;
    private javax.swing.JLabel Vigencia1;
    private javax.swing.JMenuItem addUsuario;
    private static javax.swing.JLabel aluno1Substituto;
    private static javax.swing.JLabel aluno2Substituto;
    private javax.swing.JLabel bg;
    public static javax.swing.JButton btnAtivar;
    public static javax.swing.JButton btnCadastrar;
    public static javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnDeletar;
    public static javax.swing.JButton btnDesistencia;
    public static javax.swing.JButton btnEditar;
    public static javax.swing.JButton btnEmitirDeclaracao;
    private javax.swing.JButton btnFilttro;
    public static javax.swing.JButton btnGerarCertificado;
    public static javax.swing.JButton btnHistorico;
    public static javax.swing.JButton btnModeloCert;
    public static javax.swing.JButton btnModeloDecl;
    private static javax.swing.JButton btnPesquisar;
    public static javax.swing.JButton btnSubstituir;
    private javax.swing.JLabel certificado1;
    private javax.swing.JLabel certificado2;
    private javax.swing.JLabel certificado3;
    private static javax.swing.JLabel certificadoOrientador;
    private static javax.swing.JLabel certificadoo1;
    private static javax.swing.JLabel certificadoo2;
    private javax.swing.JPopupMenu detalhes;
    private javax.swing.JMenuItem editarUsuario;
    private javax.swing.JMenuItem excluirUsuario;
    private javax.swing.JButton exportar;
    public static javax.swing.JComboBox<String> jComboBoxVigencia;
    private javax.swing.JLabel jLabelCeit1;
    private javax.swing.JLabel jLabelCeit2;
    private javax.swing.JLabel jLabelFinal1;
    private javax.swing.JLabel jLabelFinal2;
    private javax.swing.JLabel jLabelParcial1;
    private javax.swing.JLabel jLabelParcial2;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    public static javax.swing.JRadioButton jRbOrientador;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    public static javax.swing.JTable jTableMain;
    private javax.swing.ButtonGroup jbAlunoOrientador;
    private javax.swing.ButtonGroup jbAtivoCancelados;
    public static javax.swing.ButtonGroup jbMedioSuperior;
    private javax.swing.ButtonGroup jbModalidade;
    public static javax.swing.JRadioButton jrbMedio;
    public static javax.swing.JRadioButton jrbSuperior;
    public static javax.swing.JLabel labelMensagem;
    private javax.swing.JLabel labelModalidadeAluno2;
    private static javax.swing.JLabel labelProjetoCancelado;
    private javax.swing.JMenuItem menuCancelarProjeto;
    private javax.swing.JMenuItem menuEditar;
    private javax.swing.JMenuItem menuEmitirDeclaracao;
    private javax.swing.JMenuItem menuEmitirDeclaracaoValor;
    private javax.swing.JMenuItem menuGerarCertificado;
    private javax.swing.JMenu menuSobre;
    private javax.swing.JMenuItem menuSubstituir;
    private javax.swing.JMenuItem menuVerOcorrencias;
    private javax.swing.JMenuItem menuVerdetalhes;
    private static javax.swing.JLabel rCEIT1;
    private static javax.swing.JLabel rCEIT2;
    private static javax.swing.JLabel rFinal1;
    private static javax.swing.JLabel rFinal2;
    private static javax.swing.JLabel rParcial1;
    private static javax.swing.JLabel rParcial2;
    private javax.swing.JLabel relatorios1;
    private javax.swing.JLabel relatorios2;
    private javax.swing.JMenuItem sair;
    private javax.swing.JMenuItem salvarEm;
    private javax.swing.JSeparator separadorAlunos;
    public static javax.swing.JLabel tampao;
    private javax.swing.JButton timeLine;
    private static javax.swing.JTextField txtAluno1;
    private static javax.swing.JTextField txtAluno2;
    private static javax.swing.JTextField txtModalidadeAluno1;
    private static javax.swing.JTextField txtModalidadeAluno2;
    private static javax.swing.JTextField txtOrientador;
    private static javax.swing.JTextField txtPesquisar;
    private static javax.swing.JTextArea txtProjeto;
    public static javax.swing.JTextField txtVigencia;
    // End of variables declaration//GEN-END:variables
}
