package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//Classe responsável por fazer a conexão com o banco
public class DAO 
{    
    private final String URL     = "jdbc:mysql://192.168.200.36/ifgoiano_dev-pesquisa";// private = modificador de acesso, que so pode ser usado dentro da mesma classe
    //private final String URL     = "jdbc:mysql://localhost/ifgoiano_dev-pesquisa";// private = modificador de acesso, que so pode ser usado dentro da mesma classe
    private final String USUARIO = "pibicurt";// final nao pode ser modificado
    //private final String USUARIO = "root";// final nao pode ser modificado
    private final String SENHA   = "gyhagasa9";
    //private final String SENHA   = "cafe";
    public static Connection conexao;//abre a conexao  | protected = modificador de acesso, posso usar dentro do pacote inteiro
    public static PreparedStatement instrucao;//executa a instrucao
    public static ResultSet resultado;//pega os resultados de preparedStatement
    
    // static =  nao precisa de construir um objeto pra aceesar ele e todos os objetos da classe usa o mesmo valor
    // se uma variavel for estatica todas os objetos que usa a mesma variavel tera o mesmo valor.
    //construtor =  contruir um objeto e instancia a classe
    
    public boolean DAO()
    {
        try
        {
            conexao = DriverManager.getConnection( URL, USUARIO, SENHA );// cria a conexao com o Banco
            return true;
        }
        catch( SQLException sql )
        {
            //JOptionPane.showMessageDialog(null, sql);
            return false;
        }
    }
}
