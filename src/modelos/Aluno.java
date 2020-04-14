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
public class Aluno 
{
    int id;
    private String nome;
    private String cpf;
    private String curso;
    private String atualizacao;
    private String email;
    private String banco;
    private String agencia;
    private String conta;
    

    
    public Aluno( ) 
    {   
        
    }

    public String getAtualizacao()
    {
        return atualizacao;
    }

    public void setAtualizacao(String atualizacao)
    {
        this.atualizacao = atualizacao;
    }

    
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }
        
        
    
}
