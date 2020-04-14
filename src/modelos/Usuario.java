/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Karol
 */
public class Usuario {
    static int id;
    static String usuario;
    static String senha;
    static String cpf;
    static String confirmacao;
    static String salvar_em;
    static String vigencia;

    public static String getVigencia() {
        return vigencia;
    }

    public static void setVigencia(String vigencia) {
        Usuario.vigencia = vigencia;
    }
    public static String getSalvar_em() {
        return salvar_em;
    }

    public static void setSalvar_em(String salvar_em) {
        Usuario.salvar_em = salvar_em;
    }
    
    
    public static String getConfirmacao() {
        return confirmacao;
    }

    public static void setConfirmacao(String confirmacao) {
        Usuario.confirmacao = confirmacao;
    }

    public static  int getId() {
        return id;
    }

    public static void setId(int id) {
        Usuario.id = id;
    }    
    
    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        
        Usuario.usuario = usuario;
    }

    public static String getSenha() {
        return senha;
    }

    public static void setSenha(String senha) {
        Usuario.senha = senha;
    }

    public static String getCpf() {
        return cpf;
    }

    public static void setCpf(String cpf) {
        Usuario.cpf = cpf;
    }
}
