/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import interfaces.RegistroDigital;
import modelos.ModeloTabela;
import modelos.Registro;
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
 * @author mayck
 */
public class RegistroDAO 
{
    public void getRegistro(String busca) 
   {
        ArrayList<Object> dados = new ArrayList<Object>();
        int nregistro;
        
        String[] Colunas = new String[]{"Nº Registro","Participante","Descrição","Registrado","Email","Data Envio","Situação","Nº Doc"};
        try 
        {
            
            instrucao = conexao.prepareStatement("Select * from registro where nome like '%" + busca + "%' order by nregistro desc");
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {   
                nregistro = resultado.getInt("nregistro");
                dados.add(new Object[]
                {                    
                    String.format("%06d", nregistro),
                    resultado.getString("nome"),
                    resultado.getString("descricao"),
                    resultado.getString("data_registro"),
                    resultado.getString("email"),                    
                    resultado.getString("data_entrega"),
                    resultado.getString("situacao"),                  
                    resultado.getString("n_documento"),                  
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
        JTableHeader cabecalho = RegistroDigital.jTableRegistroDigital.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        RegistroDigital.jTableRegistroDigital.setModel(modelo);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(0).setPreferredWidth(70);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(1).setPreferredWidth(200);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(2).setPreferredWidth(210);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(3).setPreferredWidth(70);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(4).setPreferredWidth(200);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(5).setPreferredWidth(50);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(6).setPreferredWidth(40);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(7).setPreferredWidth(20);
        
        RegistroDigital.jTableRegistroDigital.getTableHeader().setReorderingAllowed(false);   
        RegistroDigital.jTableRegistroDigital.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        RegistroDigital.jTableRegistroDigital.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
   }
    
    public void inserirRegistro(Registro r)
    {   
       try
       {
            instrucao = conexao.prepareStatement("INSERT INTO registro (nregistro,nome,descricao,data_registro,email,data_entrega,situacao,n_documento) VALUES (?,?,?,?,?,?,?,?)");//isso so funciona pra varchar
            instrucao.setString (1, r.getNregistro() );
            instrucao.setString (2, r.getNome());
            instrucao.setString (3, r.getDescricao());
            instrucao.setString (4, r.getData_Registro());
            instrucao.setString (5, r.getEmail());
            instrucao.setString (6, null);
            instrucao.setString (7, null);
            instrucao.setInt    (8, r.getNDocumento());
            instrucao.execute();
            
            RegistroDigital.labelMensagem.setVisible(true);
            RegistroDigital.labelMensagem.setText("Registro inserido com sucesso");
            RegistroDigital.labelMensagem.setBackground(new Color(0,204,51));
        }catch( SQLException e )
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
            {
                RegistroDigital.labelMensagem.setVisible(true);
                RegistroDigital.labelMensagem.setText("Já existe um registro com esse número");
                RegistroDigital.labelMensagem.setBackground(new Color(230,0,0));
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
    
    public void atualizarRegistro(Registro registro , String linhaRegistro)
    {        
            try
            {

               instrucao = conexao.prepareStatement("UPDATE registro set nregistro = ?, nome = ?, descricao = ?, data_registro = ?,email = ?, data_entrega = ?,situacao = ? where nregistro = " + linhaRegistro);//isso so funciona pra varchar


                instrucao.setString(1, registro.getNregistro());
                instrucao.setString(2, registro.getNome());
                instrucao.setString(3, registro.getDescricao() );
                instrucao.setString(4, registro.getData_Registro());
                instrucao.setString(5, registro.getEmail());
                instrucao.setString(6, registro.getData_entrega()); 
                if(registro.getSituacao())
                {
                instrucao.setString(7, "ENVIADO");                    
                }else{
                instrucao.setString(7, "");     
                }                
                instrucao.executeUpdate();
                
                instrucao.close();
                
                RegistroDigital.labelMensagem.setVisible(true);
                RegistroDigital.labelMensagem.setText("Registro atualizado");                 
                RegistroDigital.labelMensagem.setBackground(new Color(0,204,51));
            }catch( SQLException e )
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
            {
               RegistroDigital.labelMensagem.setVisible(true);
                RegistroDigital.labelMensagem.setText("Já existe um registro com esse número");
                RegistroDigital.labelMensagem.setBackground(new Color(230,0,0));
            }if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               JOptionPane.showMessageDialog(null, e);                
            } 
        }
    }
    
    public void deletarRegistro( String numeroRegistro)
    {
        try
        {
            instrucao = conexao.prepareStatement( "DELETE FROM registro WHERE nregistro = " + numeroRegistro );
            instrucao.execute();
            
            RegistroDigital.labelMensagem.setVisible(true);
            RegistroDigital.labelMensagem.setText("Registro removido");
            RegistroDigital.labelMensagem.setBackground(new Color(0,204,51));

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
    
    public void atualizaDpsEnvio(String nregistro, String dataEnvio, String situacao)
    {
        try
            {
                instrucao = conexao.prepareStatement("UPDATE registro set data_entrega = ?,situacao = ? where nregistro = " + nregistro);//isso so funciona pra varchar

                instrucao.setString(1, dataEnvio);
                instrucao.setString(2, situacao);                
                instrucao.executeUpdate();                
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
    }
}
