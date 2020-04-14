/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
 
public class LerArquivo 
{ 
    String linha = "";
  public String lerUsuario()
  {  
    try
    {
        String nome = System.getProperty("user.home");
        String arquivo = "/Documents/SGPP/usuario.txt";
 
      FileReader arq = new FileReader(nome+arquivo);
      BufferedReader lerArq = new BufferedReader(arq);
 
       linha = lerArq.readLine(); // lê a primeira linha
            // a variável "linha" recebe o valor "null" quando o processo
            // de repetição atingir o final do arquivo texto
        for(int i=1;i<1;i++){ 
        linha = lerArq.readLine(); // lê da segunda até a última linha      
        }       
 
      arq.close();
    } catch (IOException e)
    {
        JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo: %s.\n" + e.getMessage() + " - " + e);
    }
 
    return linha;
  }
  public void gravarUsuario(String usuario) throws IOException
  {
 
    String nome = System.getProperty("user.home");
    String arquivo = "/Documents/SGPP/usuario.txt";
 
    FileWriter arq = new FileWriter(nome+arquivo);
    PrintWriter gravarArq = new PrintWriter(arq);
 
    for (int i=0;i<1; i++) {
      gravarArq.printf(usuario);
    }
 
    arq.close();
 
  }
}


