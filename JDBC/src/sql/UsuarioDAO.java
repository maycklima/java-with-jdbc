/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author mayck
 */
public class UsuarioDAO 
{
    public void cadastrarUsuario(Usuario u)
    {
        String query = "INSERT INTO usuario(nome,sobrenome) VALUES (?,?)";
        
        try 
        {
            instrucao = conexao.prepareStatement(query);
            instrucao.setString(1, u.getNome());
            instrucao.setString(2, u.getSobrenome());
            instrucao.execute();
        } catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
