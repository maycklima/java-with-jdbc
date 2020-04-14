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

public class Registro 
{
    private String nregistro;
    private String nome;
    private String descricao;
    private String data_registro;
    private String email;
    private String data_entrega;
    private Boolean situacao;
    private int NDocumento;

    public String getNregistro() {
        return nregistro;
    }

    public void setNregistro(String nregistro) {
        this.nregistro = nregistro;
    }

    public int getNDocumento() {
        return NDocumento;
    }

    public void setNDocumento(int NDocumento) {
        this.NDocumento = NDocumento;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    

    public String getData_Registro() {
        return data_registro;
    }

    public void setData_Registro(String data) {
        this.data_registro = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(String data_entrega) {
        this.data_entrega = data_entrega;
    }

    public String getData_registro() {
        return data_registro;
    }

    public void setData_registro(String data_registro) {
        this.data_registro = data_registro;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }
    
    
    
    
}
