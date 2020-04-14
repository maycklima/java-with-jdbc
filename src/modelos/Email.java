/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author mayck
 */
public class Email 
{
    static String id_email;
    static String email;
    static String senha;

    public static String getId_email() {
        return id_email;
    }

    public static void setId_email(String id_email) {
        Email.id_email = id_email;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Email.email = email;
    }

    public static String getSenha() {
        return senha;
    }

    public static void setSenha(String senha) {
        Email.senha = senha;
    }
    
    
}
