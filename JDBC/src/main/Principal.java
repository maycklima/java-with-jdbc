/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JOptionPane;

import db.DAO;
import model.Usuario;
import sql.UsuarioDAO;

/**
 *
 * @author mayck
 */
public class Principal 
{
    public static void main(String[] args)
    {
        DAO dao = new DAO();
        Usuario u = new Usuario();
        UsuarioDAO uD = new UsuarioDAO();
        
        //System.out.println(dao.conectar());
        JOptionPane.showMessageDialog(null, dao.conectar());
        
        //u.setNome("Junio");
        //u.setSobrenome("César");        
        //uD.cadastrarUsuario(u); 
    }
}
