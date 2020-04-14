/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Mayck Lima
 */
public class Orientador 
{
    private int idOrientador;
    private String nomeOrientador;
    private String cpfOrientador;
    private String emailOrientador;
    private String titulacaoOrientador;
    private String atualizacao;

     public Orientador()
    {
       
    }

    public String getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(String atualizacao) {
        this.atualizacao = atualizacao;
    }
     
    
    public String getCpfOrientador() {
        return cpfOrientador;
    }

    public void setCpfOrientador(String cpfOrientador) {
        this.cpfOrientador = cpfOrientador;
    }
    public int getIdOrientador() {
        return idOrientador;
    }

    public void setIdOrientador(int idOrientador) {
        this.idOrientador = idOrientador;
    }

    public String getNomeOrientador() {
        return nomeOrientador;
    }

    public void setNomeOrientador(String nomeOrientador) {
        this.nomeOrientador = nomeOrientador;
    }

    public String getEmailOrientador() {
        return emailOrientador;
    }

    public void setEmailOrientador(String emailOrientador) {
        this.emailOrientador = emailOrientador;
    }
    
    public String getTitulacaoOrientador() {
        return titulacaoOrientador;
    }

    public void setTitulacaoOrientador(String titulacaoOrientador) {
        this.titulacaoOrientador = titulacaoOrientador;
    }
    
    
}
