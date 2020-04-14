/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import interfaces.HistoricoOrientador;
import interfaces.Main;
import interfaces.TodosOrientadores;
import modelos.ModeloTabela;
import modelos.Orientador;
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
public class OrientadorDAO 
{
    
    public void getOrientador(String busca) 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        String situacao;
        
        String[] Colunas = new String[]{"ID","1","2","3","Orientador","Vigência","6","RP1","RF1","C1","RP2","RF2","C2","13","14","15","16","17","18","19","20","21","22","Situação","24","25","26","27","28"};
        
        try 
        {
            if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos")){
            query = "SELECT * FROM projeto_view WHERE nome_orientador like '%" + busca + "%' group by id_projeto order by nome_orientador asc";

        }else
        {
            query = "SELECT * FROM projeto_view WHERE vigencia_projeto = '" + String.valueOf(Main.jComboBoxVigencia.getSelectedItem()) + "' and nome_orientador like '%" + busca + "%' group by id_projeto order by nome_orientador asc";
        }
            
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
        catch (SQLException ex)
        {
           if(ex instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    //JOptionPane.showMessageDialog(null, ex);                
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
        Main.jTableMain.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 1 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 1 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMinWidth( 0 );        
        Main.jTableMain.getColumnModel().getColumn(4).setPreferredWidth(400);             
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
        Main.jTableMain.getColumnModel().getColumn(23).setPreferredWidth(45);
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
   
    public void getOrientadorMedio() 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        String situacao;
        
        String[] Colunas = new String[]{"ID","1","2","3","Orientador","Vigência","Projeto","RP1","RF1","C1","RP2","RF2","C2","13","14","15","16","17","18","19","20","21","22","Situação","24","25","26","27","28"};
        
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos"))
        {

            query = "SELECT * FROM projeto_view WHERE (modalidade_aluno1 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno1 = 'PIBIC Jr/CNPq' OR modalidade_aluno1 = 'PIBIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIBIC Jr/CNPq' OR modalidade_aluno2 = 'PIBIC Jr/IF Goiano') group by id_projeto order by nome_orientador asc";
        }else{
            query = "SELECT * FROM projeto_view WHERE vigencia_projeto = '" + Main.jComboBoxVigencia.getSelectedItem()+ "' and id_projeto \n" +
                    "IN (SELECT id_projeto from projeto_view where ( modalidade_aluno1 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno1 = 'PIBIC Jr/CNPq' OR modalidade_aluno1 = 'PIBIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIVIC Jr/IF Goiano' OR modalidade_aluno2 = 'PIBIC Jr/CNPq' OR modalidade_aluno2 = 'PIBIC Jr/IF Goiano')) group by id_projeto order by nome_orientador asc;";
            
        }
        try{
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
        catch (SQLException ex)
        {
           if(ex instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    //JOptionPane.showMessageDialog(null, ex);                
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
        Main.jTableMain.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 1 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 1 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMinWidth( 0 );        
        Main.jTableMain.getColumnModel().getColumn(4).setPreferredWidth(400);             
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
        Main.jTableMain.getColumnModel().getColumn(23).setPreferredWidth(45);
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
    public void getOrientadorSuperior() 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        String situacao;
        
        String[] Colunas = new String[]{"ID","1","2","3","Orientador","Vigência","Projeto","RP1","RF1","C1","RP2","RF2","C2","13","14","15","16","17","18","19","20","21","22","Situação","24","25","26","27","28"};
        
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos"))
        {
            query = "SELECT * FROM projeto_view WHERE (modalidade_aluno1 = 'PIBIC/IF Goiano' OR modalidade_aluno1 = 'PIBIC/CNPq' OR modalidade_aluno1 = 'PIVIC/IF Goiano' OR modalidade_aluno1 = 'PIBITI/CNPq' OR modalidade_aluno2 = 'PIBIC/IF Goiano' OR modalidade_aluno2 = 'PIBIC/CNPq' OR modalidade_aluno2 = 'PIVIC/IF Goiano' OR modalidade_aluno2 = 'PIBITI/CNPq') group by id_projeto order by nome_aluno asc";
        }else{
            query = "SELECT * FROM projeto_view WHERE vigencia_projeto = '"+ Main.jComboBoxVigencia.getSelectedItem() + "' and id_projeto\n" +
                    "IN (SELECT id_projeto from projeto_view where (modalidade_aluno1 = 'PIBIC/IF Goiano' OR modalidade_aluno1 = 'PIBIC/CNPq' OR modalidade_aluno1 = 'PIVIC/IF Goiano' OR modalidade_aluno1 = 'PIBITI/CNPq' OR modalidade_aluno2 = 'PIBIC/IF Goiano' OR modalidade_aluno2 = 'PIBIC/CNPq' OR modalidade_aluno2 = 'PIVIC/IF Goiano' OR modalidade_aluno2 = 'PIBITI/CNPq')) group by id_projeto order by nome_orientador asc";
        }
        try{
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
        catch (SQLException ex)
        {
           if(ex instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    //JOptionPane.showMessageDialog(null, ex);                
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
        Main.jTableMain.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 1 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 1 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMinWidth( 0 );        
        Main.jTableMain.getColumnModel().getColumn(4).setPreferredWidth(400);             
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
        Main.jTableMain.getColumnModel().getColumn(23).setPreferredWidth(45);
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
    
    public void getOrientadorFiltro( String modalidade ) 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        
        String[] Colunas = new String[]{"ID","1","2","3","Projetos por Orientadores","Vigência","Projeto","RP1","RF1","C1","RP2","RF2","C2","13","14","15","16","17","18","19","20","21","22","Situação","24","25","26","27","28"};
        
        if(String.valueOf(Main.jComboBoxVigencia.getSelectedItem()).equals("Todos"))
        {
            query = "SELECT * FROM projeto_view WHERE projeto_cancelado = 0 and (modalidade_aluno1 = '" + modalidade + "' OR modalidade_aluno2 = '" + modalidade + "') group by id_projeto order by nome_aluno asc";
        }else{
            query = "SELECT * FROM projeto_view WHERE vigencia_projeto = '"+ Main.jComboBoxVigencia.getSelectedItem() + "' and id_projeto\n" +
                    "IN (SELECT id_projeto from projeto_view where projeto_cancelado = 0 and (modalidade_aluno1 = '" + modalidade + "' OR modalidade_aluno2 = '" + modalidade + "')) group by id_projeto order by nome_orientador asc";
        }
        try{
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
       

        JTableHeader cabecalho = Main.jTableMain.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        Main.jTableMain.setModel(modelo);
        Main.jTableMain.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 1 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 1 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 2 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 2 ).setMinWidth( 0 );
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getColumnModel().getColumn( 3 ).setMinWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMaxWidth( 0 );  
        Main.jTableMain.getTableHeader().getColumnModel().getColumn( 3 ).setMinWidth( 0 );        
        Main.jTableMain.getColumnModel().getColumn(4).setPreferredWidth(744);             
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
    public void getHistoricoOrientador(String linhaOrientador) 
   {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        
        query = "SELECT * from projeto_view where orientador_id = " + linhaOrientador + " group by id_projeto order by vigencia_projeto desc";

      
        String[] Colunas = new String[]{"Nome","Aluno 1","Modalidade","Aluno 2","Modalidade","Vigência","Certificado","Projeto"};
        try 
        {
            
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                String m  = resultado.getString("modalidade_aluno2");
                String certificado;
                if(m.equals("(Nenhum)"))
                {
                    m = "";
                }        
                switch (resultado.getString("certificado_orientador")) {
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
                                      
                    resultado.getString("nome_orientador"),
                    resultado.getString("aluno1"),
                    resultado.getString("modalidade_aluno1"),
                    resultado.getString("aluno2"),
                    m,
                    resultado.getString("vigencia_projeto"),
                    certificado,
                    resultado.getString("nome_projeto"),                    
                    
                    
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
       

        JTableHeader cabecalho = HistoricoOrientador.jTableHistoricoOrientador.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        HistoricoOrientador.jTableHistoricoOrientador.setModel(modelo);
        HistoricoOrientador.jTableHistoricoOrientador.getColumnModel().getColumn(0).setPreferredWidth(180);
        HistoricoOrientador.jTableHistoricoOrientador.getColumnModel().getColumn(1).setPreferredWidth(180);
        HistoricoOrientador.jTableHistoricoOrientador.getColumnModel().getColumn(2).setPreferredWidth(85);
        HistoricoOrientador.jTableHistoricoOrientador.getColumnModel().getColumn(3).setPreferredWidth(180);
        HistoricoOrientador.jTableHistoricoOrientador.getColumnModel().getColumn(4).setPreferredWidth(85);
        HistoricoOrientador.jTableHistoricoOrientador.getColumnModel().getColumn(5).setPreferredWidth(25);
        HistoricoOrientador.jTableHistoricoOrientador.getColumnModel().getColumn(6).setPreferredWidth(35);        
        HistoricoOrientador.jTableHistoricoOrientador.getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        HistoricoOrientador.jTableHistoricoOrientador.getColumnModel().getColumn( 7 ).setMinWidth( 0 );  
        HistoricoOrientador.jTableHistoricoOrientador.getTableHeader().getColumnModel().getColumn( 7 ).setMaxWidth( 0 );  
        HistoricoOrientador.jTableHistoricoOrientador.getTableHeader().getColumnModel().getColumn( 7 ).setMinWidth( 0 );
       
        
        
        HistoricoOrientador.jTableHistoricoOrientador.getTableHeader().setReorderingAllowed(false);   
        HistoricoOrientador.jTableHistoricoOrientador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        HistoricoOrientador.jTableHistoricoOrientador.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    
    public void getTodosOrientadores() 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        
        String[] Colunas = new String[]{"ID","Nome","CPF","Cargo","Email","alteracao"};
        try 
        {
            instrucao = conexao.prepareStatement("select * from orientador where orientador_excluido = 0 order by nome_orientador");
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_orientador"),
                    resultado.getString("nome_orientador"),
                    resultado.getString("cpf_orientador"),                    
                    resultado.getString("titulacao_orientador"), 
                    resultado.getString("email_orientador"),
                    resultado.getString("alteracao_orientador")
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
       

        JTableHeader cabecalho = TodosOrientadores.jTableOrientadores.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        TodosOrientadores.jTableOrientadores.setModel(modelo);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(1).setPreferredWidth(260);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(2).setPreferredWidth(100);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(3).setPreferredWidth(60);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(4).setPreferredWidth(100);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 5 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 5 ).setMinWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 5 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 5 ).setMinWidth( 0 );
        
        TodosOrientadores.jTableOrientadores.getTableHeader().setReorderingAllowed(false);   
        TodosOrientadores.jTableOrientadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        TodosOrientadores.jTableOrientadores.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    
    public void getTodosOrientadoresBusca(String busca) 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        
        String[] Colunas = new String[]{"ID","Nome","CPF","Cargo","Email","alteracao"};
        try 
        {
            instrucao = conexao.prepareStatement("select * from orientador where nome_orientador like '%" + busca +"%' and orientador_excluido = 0 order by nome_orientador");
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_orientador"),
                    resultado.getString("nome_orientador"),
                    resultado.getString("cpf_orientador"),                    
                    resultado.getString("titulacao_orientador"), 
                    resultado.getString("email_orientador"),
                    resultado.getString("alteracao_orientador")  
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
       

        JTableHeader cabecalho = TodosOrientadores.jTableOrientadores.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        TodosOrientadores.jTableOrientadores.setModel(modelo);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(1).setPreferredWidth(260);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(2).setPreferredWidth(100);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(3).setPreferredWidth(60);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(4).setPreferredWidth(100);        
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 5 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 5 ).setMinWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 5 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 5 ).setMinWidth( 0 );
        
        TodosOrientadores.jTableOrientadores.getTableHeader().setReorderingAllowed(false);   
        TodosOrientadores.jTableOrientadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        TodosOrientadores.jTableOrientadores.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    public void getTodosOrientadoresExcluidos() 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        
        String[] Colunas = new String[]{"ID","Orientador Excluído","CPF","Cargo","Email","alteracao"};
        try 
        {
            instrucao = conexao.prepareStatement("select * from orientador where orientador_excluido = 1 order by nome_orientador");
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {
                dados.add(new Object[]
                {
                    resultado.getInt("id_orientador"),
                    resultado.getString("nome_orientador"),
                    resultado.getString("cpf_orientador"),                    
                    resultado.getString("titulacao_orientador"), 
                    resultado.getString("email_orientador"),
                    resultado.getString("alteracao_orientador")
                    
                    
                });

            } 
            while(resultado.next());                    
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
       

        JTableHeader cabecalho = TodosOrientadores.jTableOrientadores.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        TodosOrientadores.jTableOrientadores.setModel(modelo);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(1).setPreferredWidth(260);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(2).setPreferredWidth(100);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(3).setPreferredWidth(60);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn(4).setPreferredWidth(100);
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 5 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getColumnModel().getColumn( 5 ).setMinWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 5 ).setMaxWidth( 0 );  
        TodosOrientadores.jTableOrientadores.getTableHeader().getColumnModel().getColumn( 5 ).setMinWidth( 0 );
        
        TodosOrientadores.jTableOrientadores.getTableHeader().setReorderingAllowed(false);   
        TodosOrientadores.jTableOrientadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        TodosOrientadores.jTableOrientadores.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    
    
    public void inserirOrientador(Orientador orientador)
    {   
       try
       {
            instrucao = conexao.prepareStatement("INSERT INTO orientador (nome_orientador,cpf_orientador,email_orientador,titulacao_orientador,alteracao_orientador) VALUES (?,?,?,?,?)");//isso so funciona pra varchar
            instrucao.setNString(1, orientador.getNomeOrientador() );
            instrucao.setNString(2, orientador.getCpfOrientador());
            instrucao.setNString(3, orientador.getEmailOrientador() );
            instrucao.setNString(4, orientador.getTitulacaoOrientador());
            instrucao.setNString(5, orientador.getAtualizacao());
            instrucao.execute();            
            
            TodosOrientadores.labelMensagem.setVisible(true);
            TodosOrientadores.labelMensagem.setBackground(new Color(0,204,51));
            TodosOrientadores.labelMensagem.setText("Orientador inserido com sucesso");
            
        }catch( SQLException e )
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
            {
                TodosOrientadores.labelMensagem.setVisible(true);
                TodosOrientadores.labelMensagem.setBackground(new Color(230,0,0));
                TodosOrientadores.labelMensagem.setText("Já existente um orientador cadastrado com esse CPF");
                
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
    public void atualizarOrientador(Orientador orientador , String linhaOrientador)
    {   
        
        try
        {
            
           instrucao = conexao.prepareStatement("UPDATE orientador set nome_orientador = ?, cpf_orientador = ?, titulacao_orientador = ?, email_orientador = ?,alteracao_orientador = ? where id_orientador = " + linhaOrientador);//isso so funciona pra varchar
            
                        
            instrucao.setString   (1, orientador.getNomeOrientador());
            instrucao.setString   (2, orientador.getCpfOrientador() );
            instrucao.setString   (3, orientador.getTitulacaoOrientador());
            instrucao.setString   (4, orientador.getEmailOrientador());
            instrucao.setString   (5, orientador.getAtualizacao());
            instrucao.executeUpdate();
            
            TodosOrientadores.labelMensagem.setVisible(true);
            TodosOrientadores.labelMensagem.setBackground(new Color(0,204,51));
            TodosOrientadores.labelMensagem.setText("Orientador atualizado");  
           
        
        }catch( SQLException e )
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
            {
                TodosOrientadores.labelMensagem.setVisible(true);
                TodosOrientadores.labelMensagem.setBackground(new Color(230,0,0));
                TodosOrientadores.labelMensagem.setText("Já existente um orientador cadastrado com esse CPF");
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
    
    
    public void deletarOrientador(String linhaOrientador)
    {
        if(linhaOrientador.equals("0"))
        {
            TodosOrientadores.labelMensagem.setVisible(true);
            TodosOrientadores.labelMensagem.setBackground(new Color(255,102,0));
            TodosOrientadores.labelMensagem.setText("Selecione o orientador para removê-lo");
        }
        else
        {
            try
        {
            instrucao = conexao.prepareStatement("UPDATE orientador SET orientador_excluido = 1 WHERE id_orientador = " + linhaOrientador);
            instrucao.execute();
            
            TodosOrientadores.labelMensagem.setVisible(true);
            TodosOrientadores.labelMensagem.setBackground(new Color(0,204,51));
            TodosOrientadores.labelMensagem.setText("Orientador removido");
            
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
    
    public void recuperarOrientador(String linhaOrientador)
    {
       
        try
        {
            instrucao = conexao.prepareStatement( "UPDATE orientador SET orientador_excluido = 0 WHERE id_orientador = " + linhaOrientador );
            instrucao.execute();
            
            TodosOrientadores.labelMensagem.setVisible(true);
            TodosOrientadores.labelMensagem.setBackground(new Color(0,204,51));
            TodosOrientadores.labelMensagem.setText("Orientador recuperado");
            
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
    
    public void deletarPermanente(String linhaOrientador)
   {
       if(linhaOrientador.equals("0"))
        {
            TodosOrientadores.labelMensagem.setVisible(true);
            TodosOrientadores.labelMensagem.setBackground(new Color(255,102,0));
            TodosOrientadores.labelMensagem.setText("Selecione o orientador para deletá-lo");
        }
        else
        {
            try
            {
                instrucao = conexao.prepareStatement("DELETE FROM orientador WHERE id_orientador = " + linhaOrientador);
                instrucao.execute();
                
                
            TodosOrientadores.labelMensagem.setVisible(true);
            TodosOrientadores.labelMensagem.setBackground(new Color(0,204,51));
            TodosOrientadores.labelMensagem.setText("Orientador deletado permanentemente");
                
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
}
