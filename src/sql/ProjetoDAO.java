package sql;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import interfaces.DesistenciasAluno;
import interfaces.HistoricoCertificado;
import interfaces.HistoricoDeclaracao;
import interfaces.Main;
import interfaces.OcorrenciasProjeto;
import interfaces.ProjetosCancelados;
import interfaces.RelatoriosAlunos;
import interfaces.RelatoriosOrientadores;
import interfaces.SubstituicoesAluno;
import interfaces.TodasOcorrencias;
import modelos.ModeloTabela;
import modelos.Projeto;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Mayck Lima
 */
public class ProjetoDAO 
{   
    public void inserirProjeto(Projeto p)
    {   
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
       Date date = new Date(); 
       try
       {
            instrucao = conexao.prepareStatement("INSERT INTO projeto (nome_projeto,vigencia_projeto,aluno1_id,aluno2_id,orientador_id,modalidade_aluno1,modalidade_aluno2,data_cadastro_projeto,data_cadastro_aluno1,data_cadastro_aluno2) VALUES (?,?,?,?,?,?,?,?,?,?)");//isso so funciona pra varchar
            instrucao.setString (1, p.getNomeProjeto() );
            instrucao.setString (2, p.getVigenciaProjeto() );
            instrucao.setInt    (3, p.getAluno1() );
            
            if(p.getAluno2() == 0)
            {
            instrucao.setString(4, null );
            }else{
                instrucao.setInt(4, p.getAluno2() );
            }
            
            instrucao.setInt    (5,  p.getOrientador() );
            instrucao.setString (6,  p.getModalidadeAluno1() );
            instrucao.setString (7,  p.getModalidadeAluno2() );
            instrucao.setString (8,  String.valueOf(dateFormat.format(date)));
            instrucao.setString (9,  String.valueOf(dateFormat.format(date)));
            instrucao.setString (10, String.valueOf(dateFormat.format(date)));
            instrucao.execute();
            
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setText("Projeto inserido com sucesso");
            Main.labelMensagem.setBackground(new Color(0,204,51));
            
        }
        catch( SQLException e )
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
    
    public void atualizarProjeto(Projeto projeto , String linhaProjeto)
    {   
        
        try
        {
            
           instrucao = conexao.prepareStatement("UPDATE projeto set nome_projeto = ?, modalidade_aluno1 = ? , modalidade_aluno2 = ?, r_parcial_aluno1 = ?, r_final_aluno1 = ?, ceit_aluno1 = ?,r_parcial_aluno2 = ?, r_final_aluno2 = ?, ceit_aluno2 = ?,certificado_enviado1 = ?, certificado_enviado2 = ?, certificado_orientador = ?  where id_projeto = " + linhaProjeto);//isso so funciona pra varchar
            
                        
            instrucao.setString   (1, projeto.getNomeProjeto());
            instrucao.setString   (2, projeto.getModalidadeAluno1());
            instrucao.setString   (3, projeto.getModalidadeAluno2());
            instrucao.setInt      (4, projeto.getrParcial1());
            instrucao.setInt      (5, projeto.getrFinal1());
            instrucao.setInt      (6, projeto.getCeit1());
            instrucao.setInt      (7, projeto.getrParcial2());
            instrucao.setInt      (8, projeto.getrFinal2());
            instrucao.setInt      (9, projeto.getCeit2());
            instrucao.setInt      (10, projeto.getCertificado1());
            instrucao.setInt      (11, projeto.getCertificado2());
            instrucao.setInt      (12, projeto.getCertificadoOrientador());
            instrucao.executeUpdate();
            
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setText("Projeto atualizado");
            Main.labelMensagem.setBackground(new Color(0,204,51));
        
        }
        catch (SQLException e)
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
        
        if(projeto.isAlteracao_titulo())
        {           
            try 
            {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
                Date date = new Date(); 
                instrucao = conexao.prepareStatement("INSERT INTO ocorrencias (projeto_id,tipo_ocorrencia,descricao_ocorrencia,data_ocorrencia) VALUES (?,?,?,?)");//isso so funciona pra varchar

                instrucao.setInt    (1, Integer.parseInt(linhaProjeto));
                instrucao.setString (2, "TÍTULO");
                instrucao.setString (3, projeto.getNomeOrientador() + " alterou o título do seu projeto.");
                instrucao.setString (4, String.valueOf(dateFormat.format(date)));
                instrucao.execute();     

            }catch (NumberFormatException | SQLException e) 
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
    
    public void atualizarProjetoSegundoAluno(Projeto projeto, String linhaProjeto)
    {   
        
        try
        {
            
           instrucao = conexao.prepareStatement("UPDATE projeto set aluno2_id = ?, modalidade_aluno2 = ? where id_projeto = " + linhaProjeto);//isso so funciona pra varchar
            
                        
            instrucao.setInt    (1, projeto.getAluno2());
            instrucao.setString (2, projeto.getModalidadeAluno2());
            instrucao.executeUpdate();
           
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setText("Novo aluno inserido com sucesso");
            Main.labelMensagem.setBackground(new Color(0,204,51));
        
        }
        catch (SQLException e)
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
    
    public void atualizarOrientador(Projeto projeto, String linhaProjeto)
    {   
        
        try
        {
            
           instrucao = conexao.prepareStatement("UPDATE projeto set orientador_id = ? where id_projeto = " + linhaProjeto);//isso so funciona pra varchar
            
                        
            instrucao.setInt (1, projeto.getOrientador());
            instrucao.executeUpdate();
           
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setText("Orientador alterado com sucesso");
            Main.labelMensagem.setBackground(new Color(0,204,51));
        
        }
        catch (SQLException e)
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
    public void deletarProjeto(String linhaProjeto)
    {
        
        try
        {
            instrucao = conexao.prepareStatement( "DELETE FROM projeto WHERE id_projeto = " + linhaProjeto );
            instrucao.execute();
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setText("Projeto deletado");
            Main.labelMensagem.setBackground(new Color(0,204,51));            
        }
        catch( SQLException e )
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
    
    public void deletarOcorrencia(String linhaOcorrencia)
    {        
        try
        {
            instrucao = conexao.prepareStatement( "DELETE FROM ocorrencias WHERE id_ocorrencia = " + linhaOcorrencia );
            instrucao.execute();            
        }
        catch( SQLException e )
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
    
   public void assinalarDesistencia(int linhaProjeto, Projeto projeto)
   {
        try
        {
         instrucao = conexao.prepareStatement("UPDATE projeto SET aluno1_id = ?, modalidade_aluno1 = ?, aluno2_id = ?, modalidade_aluno2 = ?,r_parcial_aluno1 = ?,r_final_aluno1 = ?,ceit_aluno1 = ?, r_parcial_aluno2 = ?,r_final_aluno2 = ?,ceit_aluno2 = ?,aluno1_substituto = ?, aluno2_substituto = ?, certificado_enviado1 = ?, certificado_enviado2 = ? WHERE id_projeto = " + linhaProjeto);
         instrucao.setString(1, String.valueOf(projeto.getAluno1()));
         instrucao.setString(2, String.valueOf(projeto.getModalidadeAluno1()));        
         
         if(projeto.getAluno2() == 0)
         {
            instrucao.setString(3, null);
            instrucao.setBoolean(12, false);            
         }
         else
         {
            instrucao.setString(3, String.valueOf(projeto.getAluno2()));
            instrucao.setBoolean(11,projeto.isAluno1_substituto());
            instrucao.setBoolean(12,projeto.isAluno2_substituto());            
         }
         
         instrucao.setString(4,String.valueOf(projeto.getModalidadeAluno2()));
         instrucao.setInt(5,projeto.getrParcial1());
         instrucao.setInt(6,projeto.getrFinal1());
         instrucao.setInt(7,projeto.getCeit1());
         instrucao.setInt(8,projeto.getrParcial2());
         instrucao.setInt(9,projeto.getrFinal2());
         instrucao.setInt(10,projeto.getCeit2());
         instrucao.setBoolean(11,projeto.isAluno1_substituto());
         instrucao.setInt(13,projeto.getCertificado1());
         instrucao.setInt(14,projeto.getCertificado2());
         instrucao.execute();
          
         // Inserção em Desistências
         
         instrucao = conexao.prepareStatement("INSERT INTO desistencias (aluno_desistente, modalidade, nome_orientador, nome_projeto, vigencia_projeto,projeto_id, data_desistencia,n_documento) VALUES (?,?,?,?,?,?,?,?)");//isso so funciona pra varchar
         instrucao.setString (1, projeto.getAlunoDesistente());
         instrucao.setString (2, projeto.getModalidadeDesistente() ); 
         instrucao.setString (3, projeto.getNomeOrientador() );
         instrucao.setString (4, projeto.getNomeProjeto());
         instrucao.setString (5, projeto.getVigenciaProjeto() );
         instrucao.setInt    (6, linhaProjeto );
         instrucao.setString (7, projeto.getData_desistencia() );
         instrucao.setInt    (8, linhaProjeto );
         instrucao.execute();
         
         Main.labelMensagem.setVisible(true);
         Main.labelMensagem.setText("Desistência registrada");
         Main.labelMensagem.setBackground(new Color(0,204,51));
         
         instrucao = conexao.prepareStatement("INSERT INTO ocorrencias (projeto_id,tipo_ocorrencia,descricao_ocorrencia,data_ocorrencia) VALUES (?,?,?,?)");//isso so funciona pra varchar
            
            instrucao.setInt    (1, linhaProjeto);
            instrucao.setString (2, "DESISTÊNCIA");
            instrucao.setString (3, projeto.getAlunoDesistente() + " desistiu do projeto.");
            instrucao.setString (4, projeto.getData_desistencia());
            instrucao.execute();
        }
        catch( SQLException e )
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
   public void getDesistencias() 
   { 
      ArrayList<Object> dados = new ArrayList<Object>();
      String query;
       
      if(String.valueOf(DesistenciasAluno.jComboBoxVigenciaDesistencia.getSelectedItem()).equals("Todos"))
      {
        query = "SELECT * FROM desistencias";
      }
      else
      {
         query = "SELECT * FROM desistencias WHERE vigencia_projeto = '" + String.valueOf(DesistenciasAluno.jComboBoxVigenciaDesistencia.getSelectedItem()) + "'";
      }
        
        String[] Colunas = new String[]{"ID","Alunos Desistentes","Modalidade","Orientador","Vigência", "Data", "Nº Doc.","Projeto"};
        try 
        {
            
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_desistencia"),
                    resultado.getString("aluno_desistente"),
                    resultado.getString("modalidade"),
                    resultado.getString("nome_orientador"),                    
                    resultado.getString("vigencia_projeto"),
                    resultado.getString("data_desistencia"),
                    resultado.getString("n_documento"),
                    resultado.getString("nome_projeto")
                });

            } 
            while(resultado.next());                    
        } 
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                   //JOptionPane.showMessageDialog(null, e);                
                }  
        }
       

        JTableHeader cabecalho = DesistenciasAluno.jTableDesistencia.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        DesistenciasAluno.jTableDesistencia.setModel(modelo);
        DesistenciasAluno.jTableDesistencia.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        DesistenciasAluno.jTableDesistencia.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        DesistenciasAluno.jTableDesistencia.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        DesistenciasAluno.jTableDesistencia.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        DesistenciasAluno.jTableDesistencia.getColumnModel().getColumn(1).setPreferredWidth(280);
        DesistenciasAluno.jTableDesistencia.getColumnModel().getColumn(2).setPreferredWidth(150);
        DesistenciasAluno.jTableDesistencia.getColumnModel().getColumn(3).setPreferredWidth(250);
        DesistenciasAluno.jTableDesistencia.getColumnModel().getColumn(4).setPreferredWidth(95);
        DesistenciasAluno.jTableDesistencia.getColumnModel().getColumn(5).setPreferredWidth(95);
        DesistenciasAluno.jTableDesistencia.getColumnModel().getColumn(6).setPreferredWidth(85);
        DesistenciasAluno.jTableDesistencia.getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        DesistenciasAluno.jTableDesistencia.getColumnModel().getColumn( 7 ).setMinWidth( 0 );  
        DesistenciasAluno.jTableDesistencia.getTableHeader().getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        DesistenciasAluno.jTableDesistencia.getTableHeader().getColumnModel().getColumn( 7 ).setMinWidth( 0 ); 
        
        DesistenciasAluno.jTableDesistencia.getTableHeader().setReorderingAllowed(false);   
        DesistenciasAluno.jTableDesistencia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        DesistenciasAluno.jTableDesistencia.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
   }
           
    public void substituirAluno(Projeto projeto , String linhaProjeto, String nomeAlunoAntigo, String nomeAlunoNovo, String orientador, String dataSubstituicao)
    {           
        try
        {
            
            instrucao = conexao.prepareStatement("UPDATE projeto set aluno1_id = ?, aluno2_id = ?, aluno1_substituto = ?, aluno2_substituto = ?,data_cadastro_aluno1 = ?,data_cadastro_aluno2 = ? where id_projeto = " + linhaProjeto);//isso so funciona pra varchar
            instrucao.setInt   (1, projeto.getAluno1());
            if(projeto.getAluno2() == 0)
            {
                instrucao.setString   (2, null);
            }else{
                instrucao.setInt   (2, projeto.getAluno2());
            }         
            instrucao.setBoolean   (3, projeto.isAluno1_substituto());
            instrucao.setBoolean   (4, projeto.isAluno2_substituto());
            instrucao.setString    (5, projeto.getData_substituicao1());
            instrucao.setString    (6, projeto.getData_substituicao2());
            instrucao.executeUpdate();   
            
            instrucao = conexao.prepareStatement("INSERT INTO substituicoes (aluno_antigo,aluno_novo,nome_orientador,vigencia_projeto,nome_projeto,projeto_id,data_substituicao ,n_documento) VALUES (?,?,?,?,?,?,?,?)");//isso so funciona pra varchar
            instrucao.setString (1, nomeAlunoAntigo);
            instrucao.setString (2, nomeAlunoNovo);
            instrucao.setString (3, orientador);            
            instrucao.setString (4, projeto.getVigenciaProjeto());
            instrucao.setString (5, projeto.getNomeProjeto());
            instrucao.setInt    (6, Integer.parseInt(linhaProjeto));
            instrucao.setString (7, dataSubstituicao);
            instrucao.setInt    (8, Integer.parseInt(linhaProjeto));
            instrucao.execute();          
            
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setText("Aluno substituído");
            Main.labelMensagem.setBackground(new Color(0,204,51));
            
            
            instrucao = conexao.prepareStatement("INSERT INTO ocorrencias (projeto_id,tipo_ocorrencia,descricao_ocorrencia,data_ocorrencia) VALUES (?,?,?,?)");//isso so funciona pra varchar
            
            instrucao.setInt    (1, Integer.parseInt(linhaProjeto));
            instrucao.setString (2, "SUBSTITUIÇÃO");
            instrucao.setString (3, nomeAlunoAntigo + " foi substituído(a) por " + nomeAlunoNovo + ".");
            instrucao.setString (4, dataSubstituicao);
            instrucao.execute();
        }
        catch (SQLException e)
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
    
    public void getSubstituicoes(String busca) 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        
        String[] Colunas = new String[]{"ID","Aluno Substituído","Aluno Substituto","Orientador","Vigência","Data","Nº Doc","Projeto"};
        try 
        {     
        if(String.valueOf(SubstituicoesAluno.jComboBoxVigenciaSubstituicao.getSelectedItem()).equals("Todos"))
        {
          query = "SELECT * FROM substituicoes WHERE (aluno_antigo LIKE '%" + busca + "%' OR  aluno_novo LIKE '%" + busca + "%') order by id_substituicao desc";
        }
        else
        {
           query = "SELECT * FROM substituicoes WHERE (aluno_antigo LIKE '%" + busca + "%' OR  aluno_novo LIKE '%" + busca + "%') AND vigencia_projeto = '" + String.valueOf(SubstituicoesAluno.jComboBoxVigenciaSubstituicao.getSelectedItem()) + "' order by id_substituicao desc";
        }
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_substituicao"),
                    resultado.getString("aluno_antigo"),
                    resultado.getString("aluno_novo"),
                    resultado.getString("nome_orientador"),                    
                    resultado.getString("vigencia_projeto"),
                    resultado.getString("data_substituicao"),
                    resultado.getString("n_documento"),
                    resultado.getString("nome_projeto")
                    
                });

            } 
            while(resultado.next());                    
        } 
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 

        }       

        JTableHeader cabecalho = SubstituicoesAluno.jTableSubstituicoes.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        SubstituicoesAluno.jTableSubstituicoes.setModel(modelo);
        SubstituicoesAluno.jTableSubstituicoes.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        SubstituicoesAluno.jTableSubstituicoes.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        SubstituicoesAluno.jTableSubstituicoes.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        SubstituicoesAluno.jTableSubstituicoes.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        
        SubstituicoesAluno.jTableSubstituicoes.getColumnModel().getColumn(1).setPreferredWidth(300);
        SubstituicoesAluno.jTableSubstituicoes.getColumnModel().getColumn(2).setPreferredWidth(300);
        SubstituicoesAluno.jTableSubstituicoes.getColumnModel().getColumn(3).setPreferredWidth(260);
        SubstituicoesAluno.jTableSubstituicoes.getColumnModel().getColumn(4).setPreferredWidth(100);
        SubstituicoesAluno.jTableSubstituicoes.getColumnModel().getColumn(5).setPreferredWidth(100);
        SubstituicoesAluno.jTableSubstituicoes.getColumnModel().getColumn(6).setPreferredWidth(110);
        SubstituicoesAluno.jTableSubstituicoes.getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        SubstituicoesAluno.jTableSubstituicoes.getColumnModel().getColumn( 7 ).setMinWidth( 0 );  
        SubstituicoesAluno.jTableSubstituicoes.getTableHeader().getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        SubstituicoesAluno.jTableSubstituicoes.getTableHeader().getColumnModel().getColumn( 7 ).setMinWidth( 0 );
        
        SubstituicoesAluno.jTableSubstituicoes.getTableHeader().setReorderingAllowed(false);   
        SubstituicoesAluno.jTableSubstituicoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        SubstituicoesAluno.jTableSubstituicoes.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public void getCancelados() 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        String situacao;
        String modalidade1;
        String modalidade2;
        
        
      if(String.valueOf(ProjetosCancelados.jComboBoxVigenciaCancelado.getSelectedItem()).equals("Todos"))
      {
        query = "SELECT * FROM projeto_view WHERE projeto_cancelado = 1";
      }
      else
      {
         query = "SELECT * FROM projeto_view WHERE projeto_cancelado = 1 and vigencia_projeto = '" + ProjetosCancelados.jComboBoxVigenciaCancelado.getSelectedItem() + "'";
      }
        String[] Colunas = new String[]{"ID","Aluno 1","Modalidade","Aluno 2","Modalidade","Orientador","Vigência","Situação","Projeto"};
        try 
        {
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                
                if(resultado.getString("projeto_cancelado").equals("1"))
                {
                    situacao = "CANCELADO";
                }else{
                    situacao = "ATIVO";
                }
                if(resultado.getString("modalidade_aluno1").equals("(Nenhum)"))
                {
                    modalidade1 = "";
                }else{
                    modalidade1 = resultado.getString("modalidade_aluno1");
                }
                
                if(resultado.getString("modalidade_aluno2").equals("(Nenhum)"))
                {
                    modalidade2 = "";
                }else{
                    modalidade2 = resultado.getString("modalidade_aluno2");
                }
                dados.add(new Object[]
                {
                    resultado.getInt("id_projeto"),
                    resultado.getString("aluno1"),                    
                    modalidade1,
                    resultado.getString("aluno2"),
                    modalidade2,
                    resultado.getString("nome_orientador"),
                    resultado.getString("vigencia_projeto"),
                    situacao,
                    resultado.getString("nome_projeto")                  
                });

            } 
            while(resultado.next());                    
        } 
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 

        }       

        JTableHeader cabecalho = ProjetosCancelados.jTableCancelados.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        ProjetosCancelados.jTableCancelados.setModel(modelo);
        ProjetosCancelados.jTableCancelados.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        ProjetosCancelados.jTableCancelados.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        ProjetosCancelados.jTableCancelados.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        ProjetosCancelados.jTableCancelados.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        ProjetosCancelados.jTableCancelados.getColumnModel().getColumn(1).setPreferredWidth(150);
        ProjetosCancelados.jTableCancelados.getColumnModel().getColumn(2).setPreferredWidth(80);
        ProjetosCancelados.jTableCancelados.getColumnModel().getColumn(3).setPreferredWidth(150);
        ProjetosCancelados.jTableCancelados.getColumnModel().getColumn(4).setPreferredWidth(80);
        ProjetosCancelados.jTableCancelados.getColumnModel().getColumn(5).setPreferredWidth(150);
        ProjetosCancelados.jTableCancelados.getColumnModel().getColumn(6).setPreferredWidth(50);
        ProjetosCancelados.jTableCancelados.getColumnModel().getColumn(7).setPreferredWidth(80);
        ProjetosCancelados.jTableCancelados.getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        ProjetosCancelados.jTableCancelados.getColumnModel().getColumn( 8 ).setMinWidth( 0 );  
        ProjetosCancelados.jTableCancelados.getTableHeader().getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        ProjetosCancelados.jTableCancelados.getTableHeader().getColumnModel().getColumn( 8 ).setMinWidth( 0 );
        
        ProjetosCancelados.jTableCancelados.getTableHeader().setReorderingAllowed(false);   
        ProjetosCancelados.jTableCancelados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        ProjetosCancelados.jTableCancelados.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public void getOcorrencias(String linhaProjeto) 
    { 
      ArrayList<Object> dados = new ArrayList<Object>();       
      
        
        String[] Colunas = new String[]{"ID","Tipo","Ocorrência","Data","Nº Doc"};
        try 
        {
            
            instrucao = conexao.prepareStatement("SELECT * FROM ocorrencias where projeto_id = " + linhaProjeto + " order by id_ocorrencia desc");
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_ocorrencia"),
                    resultado.getString("tipo_ocorrencia"),
                    resultado.getString("descricao_ocorrencia"),
                    resultado.getString("data_ocorrencia"),
                    resultado.getString("projeto_id"),
                });

            } 
            while(resultado.next());                    
        } 
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 
        }
       

        JTableHeader cabecalho = OcorrenciasProjeto.jTableHistorico.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        OcorrenciasProjeto.jTableHistorico.setModel(modelo);
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        OcorrenciasProjeto.jTableHistorico.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        OcorrenciasProjeto.jTableHistorico.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn(1).setPreferredWidth(75);
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn(2).setPreferredWidth(510);
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn(3).setPreferredWidth(50);        
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn(4).setPreferredWidth(30);
        
       

        
        OcorrenciasProjeto.jTableHistorico.getTableHeader().setReorderingAllowed(false);   
        OcorrenciasProjeto.jTableHistorico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        OcorrenciasProjeto.jTableHistorico.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
   }
    
    public void getTodasOcorrencias(String busca) 
    { 
      ArrayList<Object> dados = new ArrayList<Object>();       
      
        
        String[] Colunas = new String[]{"ID","Tipo","Ocorrência","Data","Nº Doc"};
        try 
        {
            
            instrucao = conexao.prepareStatement("SELECT * FROM ocorrencias where descricao_ocorrencia like '%" + busca + "%' order by id_ocorrencia desc");
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_ocorrencia"),
                    resultado.getString("tipo_ocorrencia"),
                    resultado.getString("descricao_ocorrencia"),
                    resultado.getString("data_ocorrencia"),
                    resultado.getString("projeto_id"),
                });

            } 
            while(resultado.next());                    
        } 
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 
        }
       

        JTableHeader cabecalho = TodasOcorrencias.jTableTodasOcorrencias.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        TodasOcorrencias.jTableTodasOcorrencias.setModel(modelo);
        TodasOcorrencias.jTableTodasOcorrencias.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodasOcorrencias.jTableTodasOcorrencias.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        TodasOcorrencias.jTableTodasOcorrencias.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodasOcorrencias.jTableTodasOcorrencias.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        TodasOcorrencias.jTableTodasOcorrencias.getColumnModel().getColumn(1).setPreferredWidth(75);
        TodasOcorrencias.jTableTodasOcorrencias.getColumnModel().getColumn(2).setPreferredWidth(510);
        TodasOcorrencias.jTableTodasOcorrencias.getColumnModel().getColumn(3).setPreferredWidth(50);        
        TodasOcorrencias.jTableTodasOcorrencias.getColumnModel().getColumn(4).setPreferredWidth(30);
        
       

        
        TodasOcorrencias.jTableTodasOcorrencias.getTableHeader().setReorderingAllowed(false);   
        TodasOcorrencias.jTableTodasOcorrencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        TodasOcorrencias.jTableTodasOcorrencias.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.RIGHT);
   }
    /*
    public void getTodasOcorrencias(String vigencia) 
    { 
      ArrayList<Object> dados = new ArrayList<Object>();       
        String query;
        if(vigencia.equals("Todos")){
            query = "SELECT * FROM ocorrencias";

        }else
        {
            query = "SELECT * FROM ocorrencias WHERE vigencia_projeto = '" + vigencia + "'";
        }
        String[] Colunas = new String[]{"ID","Tipo","Ocorrência","Data","Nº Doc"};
        try 
        {
            
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_ocorrencia"),
                    resultado.getString("tipo_ocorrencia"),
                    resultado.getString("descricao_ocorrencia"),
                    resultado.getString("data_ocorrencia"),
                    resultado.getString("projeto_id"),
                });

            } 
            while(resultado.next());                    
        } 
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 
        }
       

        JTableHeader cabecalho = OcorrenciasProjeto.jTableHistorico.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        OcorrenciasProjeto.jTableHistorico.setModel(modelo);
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        OcorrenciasProjeto.jTableHistorico.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        OcorrenciasProjeto.jTableHistorico.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn(1).setPreferredWidth(75);
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn(2).setPreferredWidth(510);
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn(3).setPreferredWidth(50);        
        OcorrenciasProjeto.jTableHistorico.getColumnModel().getColumn(4).setPreferredWidth(30);
        
       

        
        OcorrenciasProjeto.jTableHistorico.getTableHeader().setReorderingAllowed(false);   
        OcorrenciasProjeto.jTableHistorico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        OcorrenciasProjeto.jTableHistorico.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
   }
    */
    public void getDeclaracoes() 
    { 
        ArrayList<Object> dados = new ArrayList<Object>();   
        
        String[] Colunas = new String[]{"ID","Tipo","Ocorrência","Vigência","Data","Nº Doc"};
        String query;
        
        if(String.valueOf(HistoricoDeclaracao.jComboBoxVigenciaDeclaracoes.getSelectedItem()).equals("Todos")){
            query = "SELECT *, (select vigencia_projeto from projeto where id_projeto = projeto_id) as vigencia FROM ocorrencias WHERE tipo_ocorrencia = 'DECLARAÇÃO' order by id_ocorrencia desc";

        }else
        {
            query = "SELECT *, (select vigencia_projeto from projeto where id_projeto = projeto_id) as vigencia FROM ocorrencias WHERE tipo_ocorrencia = 'DECLARAÇÃO' and projeto_id in (select id_projeto from projeto where vigencia_projeto = '" + HistoricoDeclaracao.jComboBoxVigenciaDeclaracoes.getSelectedItem().toString() +"') order by id_ocorrencia desc" ;
        }
        
        try 
        {
            
            instrucao = conexao.prepareStatement(query);//isso so funciona pra varchar
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_ocorrencia"),
                    resultado.getString("tipo_ocorrencia"),
                    resultado.getString("descricao_ocorrencia"),
                    resultado.getString("vigencia"),
                    resultado.getString("data_ocorrencia"),
                    resultado.getString("projeto_id")
                });

            } 
            while(resultado.next());                    
        } 
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 
        }
       

        JTableHeader cabecalho = HistoricoDeclaracao.jTableHistoricoDeclaracoes.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.setModel(modelo);
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.getColumnModel().getColumn(1).setPreferredWidth(75);
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.getColumnModel().getColumn(2).setPreferredWidth(450);
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.getColumnModel().getColumn(3).setPreferredWidth(50);
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.getColumnModel().getColumn(4).setPreferredWidth(50);
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.getColumnModel().getColumn(5).setPreferredWidth(50);
        
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.getTableHeader().setReorderingAllowed(false);   
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        HistoricoDeclaracao.jTableHistoricoDeclaracoes.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
   }
    
    public void getRelatorioAluno(String busca) 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;  
        String rparcial;
        String rfinal;
        String ceit;
        String certificado;
        String cancelado;
        
        String[] Colunas = new String[]{"Aluno","Vigência","Orientador","Modalidade","R. Parcial","R. Final","CEICT","Certificado","Situação","Projeto"};
        try 
        {
            if(String.valueOf(RelatoriosAlunos.jComboBoxVigenciaRelarorio.getSelectedItem()).equals("Todos"))
        {
          query = "SELECT * from relatorios where nomeAluno like '%" + busca +"%' order by nomeAluno";
        }
        else
        {
           query = "SELECT * from relatorios where nomeAluno like '%" + busca +"%' and vigencia_projeto = '" + String.valueOf(RelatoriosAlunos.jComboBoxVigenciaRelarorio.getSelectedItem()) + "' order by nomeAluno";
        }
            
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {   
                     if(resultado.getString("r_parcial_aluno1").equals("1"))
                     {
                        rparcial = "SIM";
                     }else if(resultado.getString("r_parcial_aluno1").equals("0"))
                     {
                        rparcial = "NÃO";
                     }else{
                         rparcial = "ATRASADO";
                     }
                     
                     if(resultado.getString("r_final_aluno1").equals("1"))
                     {
                        rfinal = "SIM";
                     }else if(resultado.getString("r_final_aluno1").equals("0"))
                     {
                        rfinal = "NÃO";
                     }else{
                        rfinal = "ATRASADO";
                     }
                     if(resultado.getString("ceit_aluno1").equals("1"))
                     {
                        ceit = "SIM";
                     }else if(resultado.getString("ceit_aluno1").equals("0"))
                     {
                        ceit = "NÃO";
                     }else{
                       ceit = "ATRASADO";
                     }
                     
                     if(resultado.getString("projeto_cancelado").equals("1"))
                     {
                        cancelado = "CANCELADO";
                     }else{
                        cancelado = "ATIVO";
                     }
                     
                     switch (resultado.getString("certificado_enviado1")) 
                    {
                        case "2":
                            certificado = "ENVIADO";
                            break;
                        case "1":
                            certificado = "GERADO";
                            break;
                        default:
                            certificado = "NÃO";
                            break;
                    }
                    dados.add(new Object[]
                    {
                        resultado.getString("nomeAluno"),
                        resultado.getString("vigencia_projeto"),
                        resultado.getString("nomeOrientador"),
                        resultado.getString("modalidade_aluno1"),
                        rparcial,
                        rfinal,                    
                        ceit,
                        certificado,
                        cancelado,
                        resultado.getString("nome_projeto")
                    });

            } 
            while(resultado.next());
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e)
        {
           if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 
        }
       

        JTableHeader cabecalho = RelatoriosAlunos.jTableRelatorios.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        RelatoriosAlunos.jTableRelatorios.setModel(modelo);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(0).setPreferredWidth(350);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(1).setPreferredWidth(91);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(2).setPreferredWidth(350);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(3).setPreferredWidth(150);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(4).setPreferredWidth(100);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(5).setPreferredWidth(100);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(6).setPreferredWidth(100);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(7).setPreferredWidth(110);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(8).setPreferredWidth(100);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn( 9 ).setMinWidth( 0 );  
        RelatoriosAlunos.jTableRelatorios.getTableHeader().getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        RelatoriosAlunos.jTableRelatorios.getTableHeader().getColumnModel().getColumn( 9 ).setMinWidth( 0 );
        
        RelatoriosAlunos.jTableRelatorios.getTableHeader().setReorderingAllowed(false);   
        RelatoriosAlunos.jTableRelatorios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        RelatoriosAlunos.jTableRelatorios.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    public void getRelatorioAlunoPorOrientador(String busca) 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;  
        String rparcial;
        String rfinal;
        String ceit;
        String certificado;
        String cancelado;
        
        String[] Colunas = new String[]{"Orientador","Vigência","Aluno","Modalidade","R. Parcial","R. Final","CEICT","Certificado","Situação","Projeto"};
        try 
        {
            if(String.valueOf(RelatoriosAlunos.jComboBoxVigenciaRelarorio.getSelectedItem()).equals("Todos"))
        {
          query = "SELECT * from relatorios where nomeAluno like '%" + busca +"%' order by nomeOrientador";
        }
        else
        {
           query = "SELECT * from relatorios where nomeAluno like '%" + busca +"%' and vigencia_projeto = '" + String.valueOf(RelatoriosAlunos.jComboBoxVigenciaRelarorio.getSelectedItem()) + "' order by nomeOrientador";
        }
            
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {   
                     if(resultado.getString("r_parcial_aluno1").equals("1"))
                     {
                        rparcial = "SIM";
                     }else if(resultado.getString("r_parcial_aluno1").equals("0"))
                     {
                        rparcial = "NÃO";
                     }else{
                         rparcial = "ATRASADO";
                     }
                     
                     if(resultado.getString("r_final_aluno1").equals("1"))
                     {
                        rfinal = "SIM";
                     }else if(resultado.getString("r_final_aluno1").equals("0"))
                     {
                        rfinal = "NÃO";
                     }else{
                        rfinal = "ATRASADO";
                     }
                     if(resultado.getString("ceit_aluno1").equals("1"))
                     {
                        ceit = "SIM";
                     }else if(resultado.getString("ceit_aluno1").equals("0"))
                     {
                        ceit = "NÃO";
                     }else{
                       ceit = "ATRASADO";
                     }
                     
                     if(resultado.getString("projeto_cancelado").equals("1"))
                     {
                        cancelado = "CANCELADO";
                     }else{
                        cancelado = "ATIVO";
                     }
                     
                     switch (resultado.getString("certificado_enviado1")) 
                    {
                        case "2":
                            certificado = "ENVIADO";
                            break;
                        case "1":
                            certificado = "GERADO";
                            break;
                        default:
                            certificado = "NÃO";
                            break;
                    }
                    dados.add(new Object[]
                    {
                        resultado.getString("nomeOrientador"),
                        resultado.getString("vigencia_projeto"),
                        resultado.getString("nomeAluno"),
                        resultado.getString("modalidade_aluno1"),
                        rparcial,
                        rfinal,                    
                        ceit,
                        certificado,
                        cancelado,
                        resultado.getString("nome_projeto")
                    });

            } 
            while(resultado.next());
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e)
        {
           if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 
        }
       

        JTableHeader cabecalho = RelatoriosAlunos.jTableRelatorios.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        RelatoriosAlunos.jTableRelatorios.setModel(modelo);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(0).setPreferredWidth(350);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(1).setPreferredWidth(91);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(2).setPreferredWidth(350);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(3).setPreferredWidth(150);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(4).setPreferredWidth(100);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(5).setPreferredWidth(100);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(6).setPreferredWidth(100);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(7).setPreferredWidth(110);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn(8).setPreferredWidth(100);
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        RelatoriosAlunos.jTableRelatorios.getColumnModel().getColumn( 9 ).setMinWidth( 0 );  
        RelatoriosAlunos.jTableRelatorios.getTableHeader().getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        RelatoriosAlunos.jTableRelatorios.getTableHeader().getColumnModel().getColumn( 9 ).setMinWidth( 0 );
        
        RelatoriosAlunos.jTableRelatorios.getTableHeader().setReorderingAllowed(false);   
        RelatoriosAlunos.jTableRelatorios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        RelatoriosAlunos.jTableRelatorios.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    public void getRelatorioOrientador(String busca) 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;  
        String certificado;
        String cancelado;
        
        String[] Colunas = new String[]{"Orientador","Vigência","Aluno 1","Aluno 2","Certificado","Situação","Projeto"};
        try 
        {
            if(String.valueOf(RelatoriosOrientadores.jComboBoxVigenciaRelarorioOrientador.getSelectedItem()).equals("Todos"))
        {
          query = "select distinct(nome_orientador) nome_orientador,vigencia_projeto,aluno1,aluno2,certificado_orientador,projeto_cancelado,nome_projeto from projeto_view where nome_orientador like '%" + busca +"%' order by nome_orientador";
        }
        else
        {
           query = "select distinct(nome_orientador) nome_orientador,vigencia_projeto,aluno1,aluno2,certificado_orientador,projeto_cancelado,nome_projeto from projeto_view where nome_orientador like'%" + busca +"%' and vigencia_projeto = '" + String.valueOf(RelatoriosOrientadores.jComboBoxVigenciaRelarorioOrientador.getSelectedItem()) + "' order by nome_orientador";
        }
            
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {   
                     
                    switch (resultado.getString("certificado_orientador")) 
                    {
                        case "2":
                            certificado = "ENVIADO";
                            break;
                        case "1":
                            certificado = "GERADO";
                            break;
                        default:
                            certificado = "NÃO";
                            break;
                    }
                     if(resultado.getString("projeto_cancelado").equals("1"))
                     {
                        cancelado = "CANCELADO";
                     }else{
                        cancelado = "ATIVO";
                     }
                     
                    
                    dados.add(new Object[]
                    {
                        resultado.getString("nome_orientador"),
                        resultado.getString("vigencia_projeto"),
                        resultado.getString("aluno1"),
                        resultado.getString("aluno2"),
                        certificado,
                        cancelado,
                        resultado.getString("nome_projeto")
                    });

            } 
            while(resultado.next());
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e)
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
       

        JTableHeader cabecalho = RelatoriosOrientadores.jTableRelatoriosOrientador.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        RelatoriosOrientadores.jTableRelatoriosOrientador.setModel(modelo);
        RelatoriosOrientadores.jTableRelatoriosOrientador.getColumnModel().getColumn(0).setPreferredWidth(200);
        RelatoriosOrientadores.jTableRelatoriosOrientador.getColumnModel().getColumn(1).setPreferredWidth(20);
        RelatoriosOrientadores.jTableRelatoriosOrientador.getColumnModel().getColumn(2).setPreferredWidth(200);
        RelatoriosOrientadores.jTableRelatoriosOrientador.getColumnModel().getColumn(3).setPreferredWidth(200);
        RelatoriosOrientadores.jTableRelatoriosOrientador.getColumnModel().getColumn(4).setPreferredWidth(50);
        RelatoriosOrientadores.jTableRelatoriosOrientador.getColumnModel().getColumn(5).setPreferredWidth(100);
        RelatoriosOrientadores.jTableRelatoriosOrientador.getColumnModel().getColumn( 6).setMaxWidth( 0 );  
        RelatoriosOrientadores.jTableRelatoriosOrientador.getColumnModel().getColumn( 6 ).setMinWidth( 0 );  
        RelatoriosOrientadores.jTableRelatoriosOrientador.getTableHeader().getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
        RelatoriosOrientadores.jTableRelatoriosOrientador.getTableHeader().getColumnModel().getColumn( 6 ).setMinWidth( 0 );
        
        RelatoriosOrientadores.jTableRelatoriosOrientador.getTableHeader().setReorderingAllowed(false);   
        RelatoriosOrientadores.jTableRelatoriosOrientador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        RelatoriosOrientadores.jTableRelatoriosOrientador.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    
    public void getDetalhes(String linhaProjeto)
    {
        Projeto projeto = new Projeto();
        try{
            
            instrucao = conexao.prepareStatement("SELECT data_cadastro_projeto, (SELECT nome_aluno from aluno where id_aluno = aluno1_id) as aluno1, (SELECT nome_aluno from aluno where id_aluno = aluno2_id) as aluno2,data_cadastro_aluno1,data_cadastro_aluno2 from projeto WHERE id_projeto = " + linhaProjeto);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            
            projeto.setData_cadastro_projeto(resultado.getString("data_cadastro_projeto"));
            projeto.setAluno1nome(resultado.getString("aluno1"));
            projeto.setAluno2nome(resultado.getString("aluno2"));
            projeto.setData_cadastro_aluno1(resultado.getString("data_cadastro_aluno1"));
            projeto.setData_cadastro_aluno2(resultado.getString("data_cadastro_aluno2"));
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e)
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
    
    public void cancelarProjeto(String lprojeto, String data,String nomeOrientador)
    {        
       if(lprojeto.equals("0"))
        {
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setBackground(new Color(230,0,0));
            Main.labelMensagem.setText("Escolha um aluno para deletar");
        }
        else
        {
            try
            {
                instrucao = conexao.prepareStatement( "UPDATE projeto SET projeto_cancelado = 1 WHERE id_projeto = " + lprojeto );
                instrucao.execute();
                
                Main.labelMensagem.setVisible(true);
                Main.labelMensagem.setBackground(new Color(0,204,51));
                Main.labelMensagem.setText("Projeto cancelado");
                
                instrucao = conexao.prepareStatement("INSERT INTO ocorrencias (projeto_id,tipo_ocorrencia,descricao_ocorrencia,data_ocorrencia) VALUES (?,?,?,?)");//isso so funciona pra varchar
                instrucao.setInt    (1, Integer.parseInt(lprojeto));
                instrucao.setString (2, "CANCELAMENTO");
                instrucao.setString (3, "Um projeto do orientador "+ nomeOrientador +" foi cancelado.");
                instrucao.setString (4, data);
                instrucao.execute();     
                }
            catch( SQLException e )
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
    public void recuperarProjeto(String linhaProjeto,String nomeOrientador)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        Date date = new Date();
        
        if(linhaProjeto.equals("0"))
        {
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setBackground(new Color(230,0,0));
            Main.labelMensagem.setText("Escolha um aluno para recuperar");
        }
        else
        {
            try
            {
                instrucao = conexao.prepareStatement( "UPDATE projeto SET projeto_cancelado = 0 WHERE id_projeto = " + linhaProjeto );
                instrucao.execute();
                
                Main.labelMensagem.setVisible(true);
                Main.labelMensagem.setBackground(new Color(0,204,51));
                Main.labelMensagem.setText("Projeto recuperado");
                
                instrucao = conexao.prepareStatement("INSERT INTO ocorrencias (projeto_id,tipo_ocorrencia,descricao_ocorrencia,data_ocorrencia) VALUES (?,?,?,?)");//isso so funciona pra varchar
                instrucao.setInt    (1, Integer.parseInt(linhaProjeto));
                instrucao.setString (2, "REATIVAMENTO");
                instrucao.setString (3, "Um projeto do orientador "+ nomeOrientador +" foi reativado.");
                instrucao.setString (4, String.valueOf(dateFormat.format(date)));
                instrucao.execute();
                instrucao.close();
                
                
            }
            catch( SQLException e )
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
    
    public void getCertificados(String busca) 
    { 
        ArrayList<Object> dados = new ArrayList<Object>();   
        
        String[] Colunas = new String[]{"ID","Tipo","Ocorrência","Vigência","Data","Nº Doc"};
        String query;
        
        if(String.valueOf(HistoricoCertificado.jComboBoxVigenciaCertificado.getSelectedItem()).equals("Todos"))
        {
            query = "SELECT *, (select vigencia_projeto from projeto where id_projeto = projeto_id) as vigencia FROM ocorrencias WHERE (tipo_ocorrencia = 'CONFECÇÃO' or tipo_ocorrencia = 'CERTIFICADO') AND descricao_ocorrencia LIKE '%" + busca + "%' order by id_ocorrencia desc";

        }else
        {
            query = "SELECT *, (select vigencia_projeto from projeto where id_projeto = projeto_id) as vigencia FROM ocorrencias WHERE ((tipo_ocorrencia = 'CONFECÇÃO' or tipo_ocorrencia = 'CERTIFICADO') and projeto_id in (select id_projeto from projeto where vigencia_projeto = '" + HistoricoCertificado.jComboBoxVigenciaCertificado.getSelectedItem().toString() +"')) AND descricao_ocorrencia LIKE '%" + busca + "%' order by id_ocorrencia desc" ;
        }        
        try 
        {
            
            instrucao = conexao.prepareStatement(query);//isso so funciona pra varchar
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_ocorrencia"),
                    resultado.getString("tipo_ocorrencia"),
                    resultado.getString("descricao_ocorrencia"),
                    resultado.getString("vigencia"),
                    resultado.getString("data_ocorrencia"),
                    resultado.getString("projeto_id")
                });

            } 
            while(resultado.next());                    
        } 
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 
        }
       

        JTableHeader cabecalho = HistoricoCertificado.jTableHistoricoCertificado.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        HistoricoCertificado.jTableHistoricoCertificado.setModel(modelo);
        HistoricoCertificado.jTableHistoricoCertificado.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        HistoricoCertificado.jTableHistoricoCertificado.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        HistoricoCertificado.jTableHistoricoCertificado.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        HistoricoCertificado.jTableHistoricoCertificado.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        HistoricoCertificado.jTableHistoricoCertificado.getColumnModel().getColumn(1).setPreferredWidth(65);
        HistoricoCertificado.jTableHistoricoCertificado.getColumnModel().getColumn(2).setPreferredWidth(660);
        HistoricoCertificado.jTableHistoricoCertificado.getColumnModel().getColumn(3).setPreferredWidth(50);
        HistoricoCertificado.jTableHistoricoCertificado.getColumnModel().getColumn(4).setPreferredWidth(50);
        HistoricoCertificado.jTableHistoricoCertificado.getColumnModel().getColumn(5).setPreferredWidth(35);
        
        HistoricoCertificado.jTableHistoricoCertificado.getTableHeader().setReorderingAllowed(false);   
        HistoricoCertificado.jTableHistoricoCertificado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        HistoricoCertificado.jTableHistoricoCertificado.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
   }
}
