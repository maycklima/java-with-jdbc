/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mayck
 */
public class DAO 
{
    public final String URL = "jdbc:mysql://localhost/siit"; //Caminho do banco de dados
    public final String USUARIO = "root"; //Usuario do banco de dados
    public final String SENHA = "cafe";  //Senha do banco de dados
    
    public static Connection conexao; 
    public static PreparedStatement instrucao; 
    public static ResultSet resultado;
    
    
    public boolean conectar()
    {
        try{
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            return true;
        } catch (SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }
}
