/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import interfaces.HistoricoAluno;
import interfaces.Main;
import interfaces.TodosAlunos;
import modelos.Aluno;
import modelos.ModeloTabela;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Mayck Lima
 */
public class AlunoDAO 
{
   public void getAluno(String busca) 
   {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        String situacao;
        
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos")){
            query = "SELECT * FROM projeto_view WHERE nome_aluno like '%" + busca + "%' order by nome_aluno asc";

        }else
        {
            query = "SELECT * FROM projeto_view WHERE vigencia_projeto = '" + String.valueOf(Main.jComboBoxVigencia.getSelectedItem()) + "' and nome_aluno like '%" + busca + "%' order by nome_aluno asc";
        }
        
        String[] Colunas = new String[]{"ID","Projetos por Alunos","2","3","Orientador","Vigência","6","RP1","RF1","C1","RP2","RF2","C2","13","14", "15","16", "17", "18", "19", "20", "21", "22","Situação","24","25","26","27","28"};
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
                dados.add(new Object[]
                {
                    
                    resultado.getInt("id_projeto"),
                    resultado.getString("nome_aluno"),
                    resultado.getString("aluno1"),
                    resultado.getString("aluno2"),                    
                    resultado.getString("nome_orientador"),
                    resultado.getString("vigencia_projeto"),
                    resultado.getString("nome_projeto"),                    
                    resultado.getString("r_parcial_aluno1"),
                    resultado.getString("r_final_aluno1"),
                    resultado.getString("ceit_aluno1"),
                    resultado.getString("r_parcial_aluno2"),
                    resultado.getString("r_final_aluno2"),
                    resultado.getString("ceit_aluno2"),
                    resultado.getString("modalidade_aluno1"),
                    resultado.getString("modalidade_aluno2"),
                    resultado.getString("aluno1_substituto"),
                    resultado.getString("aluno2_substituto"),
                    resultado.getString("id_aluno"),
                    resultado.getString("aluno1_id"),
                    resultado.getString("aluno2_id"),
                    resultado.getString("orientador_id"),                    
                    resultado.getString("data_cadastro_aluno1"),
                    resultado.getString("data_cadastro_aluno2"),
                    situacao,
                    resultado.getString("email_aluno"),
                    resultado.getString("email_orientador"),                          
                    resultado.getString("certificado_enviado1"),                          
                    resultado.getString("certificado_enviado2"),                          
                    resultado.getString("certificado_orientador")                          
                });

            } 
            while(resultado.next());
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e){
           if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
                //JOptionPane.showMessageDialog(null, e);                
            }   
        }
       

        JTableHeader cabecalho = Main.jTableMain.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        Main.jTableMain.setModel(modelo);
        Main.jTableMain.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn(1).setPreferredWidth(200);
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn(4).setPreferredWidth(200);
        Main.jTableMain.getColumnModel().getColumn(5).setPreferredWidth(30);
        Main.jTableMain.getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 6 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 6 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 7 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 7 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 8 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 8 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 9 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 9 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 10 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 10 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 10 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 10 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 11 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 11).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 11).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 11).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 12 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 12).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 12).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 12).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 13 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 13).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 13).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 13).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 14 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 14).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 14).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 14).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 15 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 15).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 15 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 15 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 16 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 16 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 16 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 16 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 17 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 17 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 17 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 17 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 18 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 18 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 18 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 18 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 19 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 19 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 19 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 19 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 20 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 20 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 20 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 20 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 21 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 21 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 21 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 21 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 22 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 22 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 22 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 22 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn(23).setPreferredWidth(30);
        Main.jTableMain.getColumnModel().getColumn( 24 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 24 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 24 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 24 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 25 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 25 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 25 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 25 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 26 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 26 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 26 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 26 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 27 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 27 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 27 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 27 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 28 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 28 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 28 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 28 ).setMinWidth( 0 );
        
        Main.jTableMain.getTableHeader().setReorderingAllowed(false);   
        Main.jTableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        Main.jTableMain.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
   
   public void getAlunoFiltro(String modalidade) 
   {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        
        if(Main.jComboBoxVigencia.getSelectedItem().equals("Todos"))
        {
            query = "SELECT * FROM projeto_view WHERE projeto_cancelado = 0 and (modalidade_aluno1 = '" + modalidade + "' OR modalidade_aluno2 = '" + modalidade + "') order by nome_aluno asc";
        }else{
            query = "SELECT * FROM projeto_view WHERE projeto_cancelado = 0 and vigencia_projeto = '" + Main.jComboBoxVigencia.getSelectedItem() + "' and id_projeto \n" +
                    " IN( SELECT id_projeto from projeto_view where modalidade_aluno1 = '" + modalidade + "' OR modalidade_aluno2 = '" + modalidade + "') order by nome_aluno asc;";
        }
        
        String[] Colunas = new String[]{"ID","Projetos por Alunos","2","3","Orientador","Vigência","6","RP1","RF1","C1","RP2","RF2","C2","13","14", "15","16", "17", "18", "19", "20", "21", "22","23","24","25","26","27","28"};
        try 
        {
            
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {   
                
                dados.add(new Object[]
                {
                    resultado.getInt("id_projeto"),
                    resultado.getString("nome_aluno"),
                    resultado.getString("aluno1"),
                    resultado.getString("aluno2"),                    
                    resultado.getString("nome_orientador"),
                    resultado.getString("vigencia_projeto"),
                    resultado.getString("nome_projeto"),                    
                    resultado.getString("r_parcial_aluno1"),
                    resultado.getString("r_final_aluno1"),
                    resultado.getString("ceit_aluno1"),
                    resultado.getString("r_parcial_aluno2"),
                    resultado.getString("r_final_aluno2"),
                    resultado.getString("ceit_aluno2"),
                    resultado.getString("modalidade_aluno1"),
                    resultado.getString("modalidade_aluno2"),
                    resultado.getString("aluno1_substituto"),
                    resultado.getString("aluno2_substituto"),
                    resultado.getString("id_aluno"),
                    resultado.getString("aluno1_id"),
                    resultado.getString("aluno2_id"),
                    resultado.getString("orientador_id"),                    
                    resultado.getString("data_cadastro_aluno1"),
                    resultado.getString("data_cadastro_aluno2"),
                    resultado.getString("projeto_cancelado"),
                    resultado.getString("email_aluno"),
                    resultado.getString("email_orientador"),                          
                    resultado.getString("certificado_enviado1"),                          
                    resultado.getString("certificado_enviado2"),                          
                    resultado.getString("certificado_orientador")                          
                });

            } 
            while(resultado.next());
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e){
           if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
                //JOptionPane.showMessageDialog(null, e);                
            }   
        }
       

        JTableHeader cabecalho = Main.jTableMain.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        Main.jTableMain.setModel(modelo);
        Main.jTableMain.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn(1).setPreferredWidth(450);
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn(4).setPreferredWidth(250);
        Main.jTableMain.getColumnModel().getColumn(5).setPreferredWidth(45);
        Main.jTableMain.getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 6 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 6 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 7 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 7 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 8 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 8 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 9 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 9 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 10 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 10 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 10 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 10 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 11 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 11).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 11).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 11).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 12 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 12).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 12).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 12).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 13 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 13).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 13).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 13).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 14 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 14).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 14).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 14).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 15 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 15).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 15 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 15 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 16 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 16 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 16 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 16 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 17 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 17 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 17 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 17 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 18 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 18 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 18 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 18 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 19 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 19 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 19 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 19 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 20 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 20 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 20 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 20 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 21 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 21 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 21 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 21 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 22 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 22 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 22 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 22 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 23 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 23 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 23 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 23 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 24 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 24 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 24 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 24 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 25 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 25 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 25 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 25 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 26 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 26 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 26 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 26 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 27 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 27 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 27 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 27 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 28 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 28 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 28 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 28 ).setMinWidth( 0 );
        
        Main.jTableMain.getTableHeader().setReorderingAllowed(false);   
        Main.jTableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        Main.jTableMain.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
   public void getAlunoMedio() 
   {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        String situacao;
        
        if(Main.jComboBoxVigencia.getSelectedItem().equals("Todos")){
            query = "SELECT * FROM projeto_view WHERE (modalidade_aluno1 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno1 = 'PIBIC Jr/CNPq' OR modalidade_aluno1 = 'PIBIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIBIC Jr/CNPq' OR modalidade_aluno2 = 'PIBIC Jr/IF Goiano') order by nome_aluno asc";
        }else{
             query = "SELECT * FROM projeto_view WHERE vigencia_projeto = '" + Main.jComboBoxVigencia.getSelectedItem() + "' and id_projeto\n" +
"	IN (SELECT id_projeto FROM projeto_view where modalidade_aluno1 = 'PIVIC Jr/IF Goiano' or modalidade_aluno1 = 'PIBIC Jr/CNPq' OR modalidade_aluno1 = 'PIBIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIBIC Jr/CNPq' OR modalidade_aluno2 = 'PIBIC Jr/IF Goiano') order by nome_aluno asc;";
                    
        }
        String[] Colunas = new String[]{"ID","Projetos por Alunos","2","3","Orientador","Vigência","6","RP1","RF1","C1","RP2","RF2","C2","13","14", "15","16", "17", "18", "19", "20", "21", "22","Situação","24","25","26","27","28"};
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
                dados.add(new Object[]
                {
                    
                    resultado.getInt("id_projeto"),
                    resultado.getString("nome_aluno"),
                    resultado.getString("aluno1"),
                    resultado.getString("aluno2"),                    
                    resultado.getString("nome_orientador"),
                    resultado.getString("vigencia_projeto"),
                    resultado.getString("nome_projeto"),                    
                    resultado.getString("r_parcial_aluno1"),
                    resultado.getString("r_final_aluno1"),
                    resultado.getString("ceit_aluno1"),
                    resultado.getString("r_parcial_aluno2"),
                    resultado.getString("r_final_aluno2"),
                    resultado.getString("ceit_aluno2"),
                    resultado.getString("modalidade_aluno1"),
                    resultado.getString("modalidade_aluno2"),
                    resultado.getString("aluno1_substituto"),
                    resultado.getString("aluno2_substituto"),
                    resultado.getString("id_aluno"),
                    resultado.getString("aluno1_id"),
                    resultado.getString("aluno2_id"),
                    resultado.getString("orientador_id"),                    
                    resultado.getString("data_cadastro_aluno1"),
                    resultado.getString("data_cadastro_aluno2"),
                    situacao,
                    resultado.getString("email_aluno"),
                    resultado.getString("email_orientador"),                          
                    resultado.getString("certificado_enviado1"),                          
                    resultado.getString("certificado_enviado2"),                          
                    resultado.getString("certificado_orientador")                          
                });

            } 
            while(resultado.next());
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e){
           if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
                //JOptionPane.showMessageDialog(null, e);                
            }   
        }
       

        JTableHeader cabecalho = Main.jTableMain.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        Main.jTableMain.setModel(modelo);
        Main.jTableMain.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn(1).setPreferredWidth(200);
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn(4).setPreferredWidth(200);
        Main.jTableMain.getColumnModel().getColumn(5).setPreferredWidth(30);
        Main.jTableMain.getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 6 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 6 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 7 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 7 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 8 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 8 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 9 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 9 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 10 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 10 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 10 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 10 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 11 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 11).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 11).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 11).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 12 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 12).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 12).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 12).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 13 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 13).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 13).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 13).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 14 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 14).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 14).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 14).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 15 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 15).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 15 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 15 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 16 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 16 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 16 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 16 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 17 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 17 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 17 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 17 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 18 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 18 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 18 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 18 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 19 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 19 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 19 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 19 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 20 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 20 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 20 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 20 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 21 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 21 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 21 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 21 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 22 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 22 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 22 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 22 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn(23).setPreferredWidth(30);
        Main.jTableMain.getColumnModel().getColumn( 24 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 24 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 24 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 24 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 25 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 25 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 25 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 25 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 26 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 26 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 26 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 26 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 27 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 27 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 27 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 27 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 28 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 28 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 28 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 28 ).setMinWidth( 0 );
        
        Main.jTableMain.getTableHeader().setReorderingAllowed(false);   
        Main.jTableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        Main.jTableMain.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }   
   public void getAlunoSuperior() 
   {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        String situacao;
        
        if(Main.jComboBoxVigencia.getSelectedItem().equals("Todos"))
        {
            query = "SELECT * FROM projeto_view WHERE (modalidade_aluno1 = 'PIBIC/IF Goiano' OR modalidade_aluno1 = 'PIBIC/CNPq' OR modalidade_aluno1 = 'PIVIC/IF Goiano' OR modalidade_aluno1 = 'PIBITI/CNPq' OR modalidade_aluno1 = 'PIBITI/IF Goiano' OR modalidade_aluno1 = 'PIVITI/IF Goiano' OR modalidade_aluno2 = 'PIBIC/IF Goiano' OR modalidade_aluno2 = 'PIBIC/CNPq' OR modalidade_aluno2 = 'PIVIC/IF Goiano' OR modalidade_aluno2 = 'PIBITI/CNPq' OR modalidade_aluno2 = 'PIBITI/IF Goiano' OR modalidade_aluno2 = 'PIVITI/IF Goiano') order by nome_aluno asc";
        }else{
            query = "SELECT * FROM projeto_view WHERE vigencia_projeto = '" +Main.jComboBoxVigencia.getSelectedItem() +"' and id_projeto \n" +
                    " IN( SELECT id_projeto from projeto_view where modalidade_aluno1 = 'PIBIC/IF Goiano' OR modalidade_aluno1 = 'PIBIC/CNPq' OR modalidade_aluno1 = 'PIVIC/IF Goiano' OR modalidade_aluno1 = 'PIBITI/CNPq' OR modalidade_aluno1 = 'PIBITI/IF Goiano' OR modalidade_aluno1 = 'PIVITI/IF Goiano' OR modalidade_aluno2 = 'PIBIC/IF Goiano' OR modalidade_aluno2 = 'PIBIC/CNPq' OR modalidade_aluno2 = 'PIVIC/IF Goiano' OR modalidade_aluno2 = 'PIBITI/CNPq' OR modalidade_aluno2 = 'PIBITI/IF Goiano' OR modalidade_aluno2 = 'PIVITI/IF Goiano') order by nome_aluno asc;";
        }
        
        String[] Colunas = new String[]{"ID","Projetos por Alunos","2","3","Orientador","Vigência","6","RP1","RF1","C1","RP2","RF2","C2","13","14", "15","16", "17", "18", "19", "20", "21", "22","Situação","24","25","26","27","28"};
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
                dados.add(new Object[]
                {
                    
                    resultado.getInt("id_projeto"),
                    resultado.getString("nome_aluno"),
                    resultado.getString("aluno1"),
                    resultado.getString("aluno2"),                    
                    resultado.getString("nome_orientador"),
                    resultado.getString("vigencia_projeto"),
                    resultado.getString("nome_projeto"),                    
                    resultado.getString("r_parcial_aluno1"),
                    resultado.getString("r_final_aluno1"),
                    resultado.getString("ceit_aluno1"),
                    resultado.getString("r_parcial_aluno2"),
                    resultado.getString("r_final_aluno2"),
                    resultado.getString("ceit_aluno2"),
                    resultado.getString("modalidade_aluno1"),
                    resultado.getString("modalidade_aluno2"),
                    resultado.getString("aluno1_substituto"),
                    resultado.getString("aluno2_substituto"),
                    resultado.getString("id_aluno"),
                    resultado.getString("aluno1_id"),
                    resultado.getString("aluno2_id"),
                    resultado.getString("orientador_id"),                    
                    resultado.getString("data_cadastro_aluno1"),
                    resultado.getString("data_cadastro_aluno2"),
                    situacao,
                    resultado.getString("email_aluno"),
                    resultado.getString("email_orientador"),                          
                    resultado.getString("certificado_enviado1"),                          
                    resultado.getString("certificado_enviado2"),                          
                    resultado.getString("certificado_orientador")                          
                });

            } 
            while(resultado.next());
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e){
           if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
                //JOptionPane.showMessageDialog(null, e);                
            }   
        }
       

        JTableHeader cabecalho = Main.jTableMain.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        Main.jTableMain.setModel(modelo);
        Main.jTableMain.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn(1).setPreferredWidth(200);
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn(4).setPreferredWidth(200);
        Main.jTableMain.getColumnModel().getColumn(5).setPreferredWidth(30);
        Main.jTableMain.getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 6 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 6 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 7 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 7 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 8 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 8 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 9 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 9 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 9 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 10 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 10 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 10 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 10 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 11 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 11).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 11).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 11).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 12 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 12).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 12).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 12).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 13 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 13).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 13).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 13).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 14 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 14).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 14).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 14).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 15 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 15).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 15 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 15 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 16 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 16 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 16 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 16 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 17 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 17 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 17 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 17 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 18 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 18 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 18 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 18 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 19 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 19 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 19 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 19 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 20 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 20 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 20 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 20 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 21 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 21 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 21 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 21 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 22 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 22 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 22 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 22 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn(23).setPreferredWidth(30);
        Main.jTableMain.getColumnModel().getColumn( 24 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 24 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 24 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 24 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 25 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 25 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 25 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 25 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 26 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 26 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 26 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 26 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 27 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 27 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 27 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 27 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 28 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 28 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 28 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 28 ).setMinWidth( 0 );
        
        Main.jTableMain.getTableHeader().setReorderingAllowed(false);   
        Main.jTableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        Main.jTableMain.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }   
   public void inserirAluno(Aluno a)
   {
       try
       {
            instrucao = conexao.prepareStatement("INSERT INTO aluno(nome_aluno,cpf_aluno,curso_aluno, email_aluno,banco_aluno, agencia_aluno, conta_aluno,alteracao_aluno) VALUES (?,?,?,?,?,?,?,?)");
            instrucao.setNString(1, a.getNome());
            instrucao.setNString(2, a.getCpf());
            instrucao.setNString(3, a.getCurso());
            instrucao.setNString(4, a.getEmail());
            instrucao.setNString(5, a.getBanco());
            instrucao.setNString(6, a.getAgencia());
            instrucao.setNString(7, a.getConta());
            instrucao.setNString(8, a.getAtualizacao());
            instrucao.executeUpdate();
            
            TodosAlunos.labelMensagem.setVisible(true);
            TodosAlunos.labelMensagem.setBackground(new Color(0,204,51));
            TodosAlunos.labelMensagem.setText("Aluno inserido com sucesso");
            
        }catch( SQLException e )
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
            {              
                TodosAlunos.labelMensagem.setVisible(true);
                TodosAlunos.labelMensagem.setBackground(new Color(230,0,0));
                TodosAlunos.labelMensagem.setText("Já existente um aluno cadastrado com esse CPF");
            }else if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
                JOptionPane.showMessageDialog(null, e);                
            }  
        }
   }
   public void deletarAluno(String linhaAluno)
   {
            try
            {
                instrucao = conexao.prepareStatement( "UPDATE aluno SET aluno_excluido = 1 WHERE id_aluno = " + linhaAluno );
                instrucao.execute();
                
                TodosAlunos.labelMensagem.setVisible(true);                
                TodosAlunos.labelMensagem.setBackground(new Color(0,204,51));
                TodosAlunos.labelMensagem.setText("Aluno removido");                
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
   
   public void deletarPermanente(String linhaAluno)
   {
       if(linhaAluno.equals("0"))
        {
            TodosAlunos.labelMensagem.setVisible(true);
            TodosAlunos.labelMensagem.setBackground(new Color(230,0,0));
            TodosAlunos.labelMensagem.setText("Escolha um aluno para deletar");
        }
        else
        {
            try
            {
                instrucao = conexao.prepareStatement( "DELETE FROM aluno WHERE id_aluno = " + linhaAluno );
                instrucao.execute();
                
                TodosAlunos.labelMensagem.setVisible(true);
                TodosAlunos.labelMensagem.setBackground(new Color(0,204,51));
                TodosAlunos.labelMensagem.setText("Aluno deletado permanentemente");
                
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
   public void atualizarAluno(Aluno aluno , String linhaAluno)
   {   
            try
            {

               instrucao = conexao.prepareStatement("UPDATE aluno set nome_aluno = ?, cpf_aluno = ?, curso_aluno = ?, email_aluno = ?, banco_aluno = ?, agencia_aluno = ? , conta_aluno = ?,alteracao_aluno = ?  where id_aluno = " + linhaAluno);//isso so funciona pra varchar


                instrucao.setString(1, aluno.getNome());
                instrucao.setString(2, aluno.getCpf() );
                instrucao.setString(3, aluno.getCurso() );
                instrucao.setString(4, aluno.getEmail());
                instrucao.setString(5, aluno.getBanco());
                instrucao.setString(6, aluno.getAgencia());
                instrucao.setString(7, aluno.getConta());
                instrucao.setString(8, aluno.getAtualizacao());
                
                instrucao.executeUpdate();                
                instrucao.close();

                TodosAlunos.labelMensagem.setVisible(true);
                TodosAlunos.labelMensagem.setBackground(new Color(0,204,51));
                TodosAlunos.labelMensagem.setText("Aluno atualizado");
                
            }catch( SQLException e )
            {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
            {
                TodosAlunos.labelMensagem.setVisible(true);
                TodosAlunos.labelMensagem.setBackground(new Color(230,0,0));
                TodosAlunos.labelMensagem.setText("Já existente um aluno cadastrado com esse CPF");
            }else{
                JOptionPane.showMessageDialog(null, e);                
            }
        }
    }
   public void getHistoricoAluno(String linhaAluno) 
   {    
        int aluno;  
        int aluno1;   
        int aluno2;
        String rparcial;
        String rfinal;
        String ceit;
        String certificado;
        String modalidade;
        
                
                 
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        
        query = "SELECT * FROM projeto_view WHERE id_aluno = '" + linhaAluno + "' order by vigencia_projeto desc";

      
        String[] Colunas = new String[]{"Nome","Modalidade","Orientador","Vigência", "R. Parcial", "R. Final", "CEICT","Certificado" ,"Projeto"};
        try 
        {
            
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();
            resultado.first();
            
               
            do 
            {
                aluno = resultado.getInt("id_aluno");  
                aluno1 = resultado.getInt("aluno1_id");   
                aluno2 = resultado.getInt("aluno2_id");
                
                
                 if(aluno == aluno1)
                 {  
                    switch (resultado.getString("r_parcial_aluno1")) {
                        case "1":
                            rparcial = "SIM";
                            break;
                        case "0":
                            rparcial = "NÃO";
                            break;
                        default:
                            rparcial = "ATRASADO";
                            break;
                    }
                     
                    switch (resultado.getString("r_final_aluno1")) {
                        case "1":
                            rfinal = "SIM";
                            break;
                        case "0":
                            rfinal = "NÃO";
                            break;
                        default:
                            rfinal = "ATRASADO";
                            break;
                    }
                    switch (resultado.getString("ceit_aluno1")) {
                        case "1":
                            ceit = "SIM";
                            break;
                        case "0":
                            ceit = "NÃO";
                            break;
                        default:
                            ceit = "ATRASADO";
                            break;
                    }
                     
                    switch (resultado.getString("certificado_enviado1")) {
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
                     
                     modalidade = resultado.getString("modalidade_aluno1");
                     dados.add(new Object[]
                    {
                    
                        resultado.getString("nome_aluno"),
                        modalidade,                
                        resultado.getString("nome_orientador"),                    
                        resultado.getString("vigencia_projeto"),
                        rparcial,
                        rfinal, 
                        ceit,
                        certificado,
                        resultado.getString("nome_projeto"),                        
                        
                    });
                }
                else if(aluno == aluno2)
                {
                    switch (resultado.getString("r_parcial_aluno2")) {
                        case "1":
                            rparcial = "SIM";
                            break;
                        case "0":
                            rparcial = "NÃO";
                            break;
                        default:
                            rparcial = "ATRASADO";
                            break;
                    }
                     
                    switch (resultado.getString("r_final_aluno2")) {
                        case "1":
                            rfinal = "SIM";
                            break;
                        case "0":
                            rfinal = "NÃO";
                            break;
                        default:
                            rfinal = "ATRASADO";
                            break;
                    }
                    switch (resultado.getString("ceit_aluno2")) {
                        case "1":
                            ceit = "SIM";
                            break;
                        case "0":
                            ceit = "NÃO";
                            break;
                        default:
                            ceit = "ATRASADO";
                            break;
                    }
                    switch (resultado.getString("certificado_enviado2")) 
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
                     
                     modalidade = resultado.getString("modalidade_aluno2");
                     dados.add(new Object[]
                    {
                        resultado.getString("nome_aluno"),
                        modalidade,                
                        resultado.getString("nome_orientador"),                    
                        resultado.getString("vigencia_projeto"),
                        rparcial,
                        rfinal, 
                        ceit,
                        certificado,
                        resultado.getString("nome_projeto"),
                    });
                }
  

            } 
            while(resultado.next()); 
            
            resultado.close();
            instrucao.close();
        } 
        catch(SQLException e)
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
       

        JTableHeader cabecalho = HistoricoAluno.jTableHistoricoAluno.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        HistoricoAluno.jTableHistoricoAluno.setModel(modelo);
        HistoricoAluno.jTableHistoricoAluno.getColumnModel().getColumn(0).setPreferredWidth(200);
        HistoricoAluno.jTableHistoricoAluno.getColumnModel().getColumn(1).setPreferredWidth(65);
        HistoricoAluno.jTableHistoricoAluno.getColumnModel().getColumn(2).setPreferredWidth(200);
        HistoricoAluno.jTableHistoricoAluno.getColumnModel().getColumn(3).setPreferredWidth(35);
        HistoricoAluno.jTableHistoricoAluno.getColumnModel().getColumn(4).setPreferredWidth(35);
        HistoricoAluno.jTableHistoricoAluno.getColumnModel().getColumn(5).setPreferredWidth(35);
        HistoricoAluno.jTableHistoricoAluno.getColumnModel().getColumn(6).setPreferredWidth(25);
        HistoricoAluno.jTableHistoricoAluno.getColumnModel().getColumn(7).setPreferredWidth(35);
        HistoricoAluno.jTableHistoricoAluno.getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        HistoricoAluno.jTableHistoricoAluno.getColumnModel().getColumn( 8 ).setMinWidth( 0 );  
        HistoricoAluno.jTableHistoricoAluno.getTableHeader().getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        HistoricoAluno.jTableHistoricoAluno.getTableHeader().getColumnModel().getColumn( 8 ).setMinWidth( 0 );
        
       
        
        
        HistoricoAluno.jTableHistoricoAluno.getTableHeader().setReorderingAllowed(false);   
        HistoricoAluno.jTableHistoricoAluno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        HistoricoAluno.jTableHistoricoAluno.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
   
   public void getTodosAlunos(String busca) 
   {
        ArrayList<Object> dados = new ArrayList<Object>();
        
        String[] Colunas = new String[]{"ID","Nome","CPF","Curso","Email","Banco","Agência","Conta","alteracao"};
        try 
        {
            instrucao = conexao.prepareStatement("SELECT * FROM aluno WHERE nome_aluno LIKE '%" + busca + "%' AND aluno_excluido = 0 order by nome_aluno");
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_aluno"),
                    resultado.getString("nome_aluno"),
                    resultado.getString("cpf_aluno"),
                    resultado.getString("curso_aluno"),                    
                    resultado.getString("email_aluno"),
                    resultado.getString("banco_aluno"),
                    resultado.getString("agencia_aluno"),
                    resultado.getString("conta_aluno"),
                    resultado.getString("alteracao_aluno"),
                    
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
       

        JTableHeader cabecalho = TodosAlunos.jTableAlunos.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        TodosAlunos.jTableAlunos.setModel(modelo);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodosAlunos.jTableAlunos.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        TodosAlunos.jTableAlunos.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodosAlunos.jTableAlunos.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(1).setPreferredWidth(200);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(2).setPreferredWidth(100);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(3).setPreferredWidth(150);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(4).setPreferredWidth(202);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(5).setPreferredWidth(65);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(6).setPreferredWidth(50);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(7).setPreferredWidth(30);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        TodosAlunos.jTableAlunos.getColumnModel().getColumn( 8 ).setMinWidth( 0 );  
        TodosAlunos.jTableAlunos.getTableHeader().getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        TodosAlunos.jTableAlunos.getTableHeader().getColumnModel().getColumn( 8 ).setMinWidth( 0 );
        
        
        
        TodosAlunos.jTableAlunos.getTableHeader().setReorderingAllowed(false);   
        TodosAlunos.jTableAlunos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        TodosAlunos.jTableAlunos.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public void getTodosAlunosExcluidos()
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        
        String[] Colunas = new String[]{"ID","Nome","CPF","Curso","Email","Banco","Agência","Conta","alteração"};
        try 
        {
            instrucao = conexao.prepareStatement("SELECT * FROM aluno WHERE aluno_excluido = 1 order by nome_aluno");              
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_aluno"),
                    resultado.getString("nome_aluno"),
                    resultado.getString("cpf_aluno"),
                    resultado.getString("curso_aluno"),                    
                    resultado.getString("email_aluno"),
                    resultado.getString("banco_aluno"),
                    resultado.getString("agencia_aluno"),
                    resultado.getString("conta_aluno"),
                    resultado.getString("alteracao_aluno"),
                    
                    
                });

            } 
            while(resultado.next());  
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException ex)
        {
           if(ex instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    //JOptionPane.showMessageDialog(null, e);                
                }  
        }
       

        JTableHeader cabecalho = TodosAlunos.jTableAlunos.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        TodosAlunos.jTableAlunos.setModel(modelo);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodosAlunos.jTableAlunos.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        TodosAlunos.jTableAlunos.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodosAlunos.jTableAlunos.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(1).setPreferredWidth(200);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(2).setPreferredWidth(100);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(3).setPreferredWidth(150);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(4).setPreferredWidth(202);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(5).setPreferredWidth(65);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(6).setPreferredWidth(50);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn(7).setPreferredWidth(30);
        TodosAlunos.jTableAlunos.getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        TodosAlunos.jTableAlunos.getColumnModel().getColumn( 8 ).setMinWidth( 0 );  
        TodosAlunos.jTableAlunos.getTableHeader().getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
        TodosAlunos.jTableAlunos.getTableHeader().getColumnModel().getColumn( 8 ).setMinWidth( 0 );
        
        TodosAlunos.jTableAlunos.getTableHeader().setReorderingAllowed(false);   
        TodosAlunos.jTableAlunos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        TodosAlunos.jTableAlunos.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    
    public void recuperarAluno(String linhaAluno)
    {
        try
        {
            instrucao = conexao.prepareStatement( "UPDATE aluno SET aluno_excluido = 0 WHERE id_aluno = " + linhaAluno );
            instrucao.execute();
            resultado.close();
            instrucao.close();
            
            TodosAlunos.labelMensagem.setVisible(true);
            TodosAlunos.labelMensagem.setText("Aluno recuperado"); 
            TodosAlunos.labelMensagem.setBackground(new Color(0,204,51)); 
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
    public String getCpfAluno(String id)
    {
      try
        {
            instrucao = conexao.prepareStatement("SELECT cpf_aluno FROM aluno WHERE id_aluno = " + id );
            resultado = instrucao.executeQuery();
            resultado.first();
            String cpf = resultado.getString("cpf_aluno");
            resultado.close();
            instrucao.close();
            return cpf; 
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
            return null;
        }    
    }
    public int getQntProjetos()
    {
        String query;   
        int qnt = 0;
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos") || String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("") ){
            query = "SELECT count(*) FROM projeto";
        }else
        {
            query = "SELECT count(*) FROM projeto WHERE vigencia_projeto = '" + Main.jComboBoxVigencia.getSelectedItem().toString() +"'";
        }
        
        try
        {

           instrucao = conexao.prepareStatement(query);//isso so funciona pra varchar
           resultado  = instrucao.executeQuery();

           if(resultado.next())// Next retorna 0 ou 1 como resultado da consuta
            {
                 qnt = resultado.getInt("count(*)");
            }else
           {
               JOptionPane.showMessageDialog(null, "Declaração não encontrada");
           }
        }
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, "1");                
                }  
            qnt = 0;
        }
     return qnt;   
    }
    
    public int getQntProjetosMedio()
    {
        String query;   
        int qnt = 0;
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos") || String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("") ){
            query = "SELECT COUNT(*) FROM projeto WHERE modalidade_aluno1 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno1 = 'PIBIC Jr/CNPq' OR modalidade_aluno1 = 'PIBIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIBIC Jr/CNPq' OR modalidade_aluno2 = 'PIBIC Jr/IF Goiano'";

        }else
        {
            query = "SELECT COUNT(*) FROM projeto WHERE vigencia_projeto = '" + Main.jComboBoxVigencia.getSelectedItem()+ "' and id_projeto \n" +
                    "IN (SELECT id_projeto from projeto where modalidade_aluno1 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno1 = 'PIBIC Jr/CNPq' OR modalidade_aluno1 = 'PIBIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIBIC Jr/CNPq' OR modalidade_aluno2 = 'PIBIC Jr/IF Goiano')";
        }
        
        try
        {

           instrucao = conexao.prepareStatement(query);//isso so funciona pra varchar
           resultado  = instrucao.executeQuery();

           if(resultado.next())// Next retorna 0 ou 1 como resultado da consuta
            {
                 qnt = resultado.getInt("count(*)");
            }else
           {
               JOptionPane.showMessageDialog(null, "Declaração não encontrada");
           }
        }
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, "2");                
                }  
            qnt = 0;
        }
     return qnt;   
    }
    public int getQntProjetosSuperior()
    {
        String query;   
        int qnt = 0;
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos") || String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("") ){
            query = "SELECT COUNT(*) FROM projeto WHERE modalidade_aluno1 = 'PIBIC/IF Goiano' OR modalidade_aluno1 = 'PIBIC/CNPq' OR modalidade_aluno1 = 'PIVIC/IF Goiano' OR modalidade_aluno1 = 'PIBITI/CNPq' OR modalidade_aluno2 = 'PIBIC/IF Goiano' OR modalidade_aluno2 = 'PIBIC/CNPq' OR modalidade_aluno2 = 'PIVIC/IF Goiano' OR modalidade_aluno2 = 'PIBITI/CNPq'";

        }else
        {
            query = "SELECT COUNT(*) FROM projeto WHERE vigencia_projeto = '"+ Main.jComboBoxVigencia.getSelectedItem() + "' and id_projeto\n" +
                    "IN (SELECT id_projeto from projeto where modalidade_aluno1 = 'PIBIC/IF Goiano' OR modalidade_aluno1 = 'PIBIC/CNPq' OR modalidade_aluno1 = 'PIVIC/IF Goiano' OR modalidade_aluno1 = 'PIBITI/CNPq' OR modalidade_aluno2 = 'PIBIC/IF Goiano' OR modalidade_aluno2 = 'PIBIC/CNPq' OR modalidade_aluno2 = 'PIVIC/IF Goiano' OR modalidade_aluno2 = 'PIBITI/CNPq')";
        }
        
        try
        {

           instrucao = conexao.prepareStatement(query);//isso so funciona pra varchar
           resultado  = instrucao.executeQuery();

           if(resultado.next())// Next retorna 0 ou 1 como resultado da consuta
            {
                 qnt = resultado.getInt("count(*)");
            }else
           {
               JOptionPane.showMessageDialog(null, "Declaração não encontrada");
           }
        }
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, "3");                
                }  
            qnt = 0;
        }
     return qnt;   
    }
    
    
    public int getQntAlunos()
    {
        String query;   
        int qnt = 0;
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos") || String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("") ){
            query = "SELECT count(*) FROM projeto_view";

        }else
        {
            query = "SELECT count(*) FROM projeto_view WHERE vigencia_projeto = '" + Main.jComboBoxVigencia.getSelectedItem().toString() +"'";
        }
        
        try
        {

           instrucao = conexao.prepareStatement(query);//isso so funciona pra varchar
           resultado  = instrucao.executeQuery();

           if(resultado.next())// Next retorna 0 ou 1 como resultado da consuta
            {
                 qnt = resultado.getInt("count(*)");
            }else
           {
               JOptionPane.showMessageDialog(null, "Declaração não encontrada");
           }
        }
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, "4");                
                }  
            qnt = 0;
        }
     return qnt;
    }
    
    public int getQntAlunosMedio()
    {
        String query;   
        int qnt = 0;
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos") || String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("") ){
            query = "SELECT count(*) FROM projeto_view WHERE modalidade_aluno1 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno1 = 'PIBIC Jr/CNPq' OR modalidade_aluno1 = 'PIBIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIBIC Jr/CNPq' OR modalidade_aluno2 = 'PIBIC Jr/IF Goiano' order by nome_aluno asc";

        }else
        {
            query = "SELECT count(*) FROM projeto_view WHERE vigencia_projeto = '" + Main.jComboBoxVigencia.getSelectedItem() + "' and id_projeto\n" +
"	IN (SELECT id_projeto FROM projeto_view where modalidade_aluno1 = 'PIVIC Jr/IF Goiano' or modalidade_aluno1 = 'PIBIC Jr/CNPq' OR modalidade_aluno1 = 'PIBIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIBIC Jr/CNPq' OR modalidade_aluno2 = 'PIBIC Jr/IF Goiano') order by nome_aluno asc;";
        }
        
        try
        {

           instrucao = conexao.prepareStatement(query);//isso so funciona pra varchar
           resultado  = instrucao.executeQuery();

           if(resultado.next())// Next retorna 0 ou 1 como resultado da consuta
            {
                 qnt = resultado.getInt("count(*)");
            }else
           {
               JOptionPane.showMessageDialog(null, "Declaração não encontrada");
           }
        }
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, "5");                
                }  
            qnt = 0;
        }
     return qnt;
    }
    public int getQntAlunosSuperior()
    {
        String query;   
        int qnt = 0;
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos") || String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("") ){
            query = "SELECT count(*) FROM projeto_view WHERE (modalidade_aluno1 = 'PIBIC/IF Goiano' OR modalidade_aluno1 = 'PIBIC/CNPq' OR modalidade_aluno1 = 'PIVIC/IF Goiano' OR modalidade_aluno1 = 'PIBITI/CNPq' OR modalidade_aluno2 = 'PIBIC/IF Goiano' OR modalidade_aluno2 = 'PIBIC/CNPq' OR modalidade_aluno2 = 'PIVIC/IF Goiano' OR modalidade_aluno2 = 'PIBITI/CNPq') order by nome_aluno asc";

        }else
        {
            query = "SELECT count(*) FROM projeto_view WHERE vigencia_projeto = '" +Main.jComboBoxVigencia.getSelectedItem() +"' and id_projeto \n" +
                    " IN( SELECT id_projeto from projeto_view where (modalidade_aluno1 = 'PIBIC/IF Goiano' OR modalidade_aluno1 = 'PIBIC/CNPq' OR modalidade_aluno1 = 'PIVIC/IF Goiano' OR modalidade_aluno1 = 'PIBITI/CNPq' OR modalidade_aluno2 = 'PIBIC/IF Goiano' OR modalidade_aluno2 = 'PIBIC/CNPq' OR modalidade_aluno2 = 'PIVIC/IF Goiano' OR modalidade_aluno2 = 'PIBITI/CNPq')) order by nome_aluno asc;";
        }
        
        try
        {

           instrucao = conexao.prepareStatement(query);//isso so funciona pra varchar
           resultado  = instrucao.executeQuery();

           if(resultado.next())// Next retorna 0 ou 1 como resultado da consuta
            {
                 qnt = resultado.getInt("count(*)");
            }else
           {
               JOptionPane.showMessageDialog(null, "Declaração não encontrada");
           }
        }
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, "6");                
                }  
            qnt = 0;
        }
     return qnt;
    }
     
    public int getQntOrientadores()
    {
        String query;   
        int qnt = 0;
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos") || String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("") )
        {
            query = "SELECT count(distinct orientador_id) as qnt FROM projeto_view";
        }else
        {
            query = "SELECT count(distinct orientador_id) as qnt FROM projeto_view WHERE vigencia_projeto = '" + Main.jComboBoxVigencia.getSelectedItem().toString() +"'";
        }
        
        try
        {

           instrucao = conexao.prepareStatement(query);//isso so funciona pra varchar
           resultado  = instrucao.executeQuery();

           if(resultado.next())// Next retorna 0 ou 1 como resultado da consuta
            {
                 qnt = resultado.getInt("qnt");
            }else
           {
               JOptionPane.showMessageDialog(null, "Declaração não encontrada");
           }
        }
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, "7");                
                }  
            qnt = 0;
        }
     return qnt;   
    }
    
    public int getQntOrientadoresMedio()
    {
        String query;   
        int qnt = 0;
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos") || String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("") ){
            query = "SELECT COUNT(distinct orientador_id) as qnt FROM projeto WHERE modalidade_aluno1 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno1 = 'PIBIC Jr/CNPq' OR modalidade_aluno1 = 'PIBIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIBIC Jr/CNPq' OR modalidade_aluno2 = 'PIBIC Jr/IF Goiano'";
        }else
        {
            query = "SELECT COUNT(distinct orientador_id) as qnt FROM projeto WHERE vigencia_projeto = '" + Main.jComboBoxVigencia.getSelectedItem()+ "' and id_projeto \n" +
                    "IN (SELECT id_projeto from projeto_view where modalidade_aluno1 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno1 = 'PIBIC Jr/CNPq' OR modalidade_aluno1 = 'PIBIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIBIC Jr/CNPq' OR modalidade_aluno2 = 'PIBIC Jr/IF Goiano')";
        }
        
        try
        {

           instrucao = conexao.prepareStatement(query);//isso so funciona pra varchar
           resultado  = instrucao.executeQuery();

           if(resultado.next())// Next retorna 0 ou 1 como resultado da consuta
            {
                 qnt = resultado.getInt("qnt");
            }else
           {
               JOptionPane.showMessageDialog(null, "Declaração não encontrada");
           }
        }
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, "8");                
                }  
            qnt = 0;
        }
     return qnt;   
    }
    
    public int getQntOrientadoresSuperior()
    {
        String query;   
        int qnt = 0;
        
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos") || String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("") ){
            query = "SELECT COUNT(distinct orientador_id) as qnt FROM projeto WHERE modalidade_aluno1 = 'PIBIC/IF Goiano' OR modalidade_aluno1 = 'PIBIC/CNPq' OR modalidade_aluno1 = 'PIVIC/IF Goiano' OR modalidade_aluno1 = 'PIBITI/CNPq' OR modalidade_aluno2 = 'PIBIC/IF Goiano' OR modalidade_aluno2 = 'PIBIC/CNPq' OR modalidade_aluno2 = 'PIVIC/IF Goiano' OR modalidade_aluno2 = 'PIBITI/CNPq'";

        }else
        {
            query = "SELECT COUNT(distinct orientador_id) as qnt FROM projeto WHERE vigencia_projeto = '"+ Main.jComboBoxVigencia.getSelectedItem() + "' and id_projeto\n" +
                    "IN (SELECT id_projeto from projeto_view where modalidade_aluno1 = 'PIBIC/IF Goiano' OR modalidade_aluno1 = 'PIBIC/CNPq' OR modalidade_aluno1 = 'PIVIC/IF Goiano' OR modalidade_aluno1 = 'PIBITI/CNPq' OR modalidade_aluno2 = 'PIBIC/IF Goiano' OR modalidade_aluno2 = 'PIBIC/CNPq' OR modalidade_aluno2 = 'PIVIC/IF Goiano' OR modalidade_aluno2 = 'PIBITI/CNPq')";
        }        
        try
        {

           instrucao = conexao.prepareStatement(query);//isso so funciona pra varchar
           resultado  = instrucao.executeQuery();

           if(resultado.next())// Next retorna 0 ou 1 como resultado da consuta
            {
                 qnt = resultado.getInt("qnt");
            }else
           {
               JOptionPane.showMessageDialog(null, "Declaração não encontrada");
           }
        }
        catch (SQLException e)
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, "9");                
                }  
            qnt = 0;
        }
     return qnt;   
    }
}
