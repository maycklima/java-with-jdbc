/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import interfaces.Main;
import modelos.Usuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Karol
 */
public class CertificadoDAO 
{    
    
    public void geraCertificadoAluno(String linhaProjeto, String periodo, int registro) throws ParseException
    {
        Document document = new Document(PageSize.A4.rotate());        
        Font f = new  Font(Font.getFamily("Calibri"), 72, Font.NORMAL);
        Font f4 = new Font(Font.getFamily("Calibri"), 10, Font.BOLD);
            
        int row = Main.jTableMain.getSelectedRow();             
        String pastaProjeto  = Main.jTableMain.getValueAt(row, 0 ).toString();
        String nomeAluno     = Main.jTableMain.getValueAt(row, 1 ).toString();
        String emailAluno    = Main.jTableMain.getValueAt(row, 24).toString();
        String orientador    = Main.jTableMain.getValueAt(row, 4 ).toString();
        String projeto       = Main.jTableMain.getValueAt(row, 6 ).toString();
        String id_aluno      = Main.jTableMain.getValueAt(row, 17).toString();
        String id_aluno1     = Main.jTableMain.getValueAt(row, 18).toString();                   
        String vigencia      = Main.jTableMain.getValueAt(row, 5).toString();
        String modalidade;
        String coluna;
              
        
        if (id_aluno.equals(id_aluno1))
        {
            modalidade = Main.jTableMain.getValueAt(row, 13).toString();
            coluna = "certificado_enviado1";
        }
        else 
        {
            modalidade = Main.jTableMain.getValueAt(row, 14).toString();
            coluna = "certificado_enviado2";
        }
            try 
            {
                File diretorio = new File(Usuario.getSalvar_em() + "\\" + linhaProjeto);
                    //JOptionPane.showMessageDialog(null, linhaProjeto);
                if (!diretorio.exists())
                     diretorio.mkdirs();
                
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(  Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\" + registro + "-"+ nomeAluno + ".pdf"));
                document.open();
                Image img = Image.getInstance(Usuario.getSalvar_em() + "\\00 - modelo\\certificado.png");
                
                float[] columnWidths = {255};
                PdfPTable table = new PdfPTable(columnWidths);
                table.setWidthPercentage(98);
                
                Paragraph paragrafo99 = new Paragraph("Certificado gerado por", f);
                paragrafo99.setLeading(1f);
                
                Paragraph paragrafo1 = new Paragraph(" ", f);
                paragrafo1.setLeading(175f);
                
                PdfPCell cell = new PdfPCell();                
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setFixedHeight(332f);
                
                instrucao = conexao.prepareStatement("SELECT titulacao_orientador from orientador where nome_orientador like '" + orientador + "'");
                resultado = instrucao.executeQuery();
                resultado.first();
                String cargo = resultado.getString("titulacao_orientador");
                            
                String HTML = "<p class=\"texto\">Certificamos, para os devidos fins, que <b>" + nomeAluno + "</b> participou do Programa Institucional " + modalidade + ", vinculado(a) ao projeto de pesquisa intitulado <b><i>\"" + projeto + "\"</i></b>, sob a orientação do(a) " + cargo + " <b>" + orientador + "</b>, no período de " + periodo + ".<br></br><br></br></p><p class=\"data\"><b>" + getData()+ "</b></p>";
                String CSS = "p.texto{ font-family: Arial; line-height: 1.5; font-size: 24px; text-align: justify;}p.data{ font-family: Arial; line-height: 1.5; font-size: 24px; text-align:right;}";
            
                ElementList list = XMLWorkerHelper.parseToElementList(HTML,CSS);            
                list.forEach((element) -> 
                {
                    cell.addElement(element);
                });
                table.addCell(cell);
                
                
                PdfPCell cell2 = new PdfPCell(new Phrase("Certificado gerado pelo sistema SGPP - Registro nº " + String.format("%06d", registro),f4));
                cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell2.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell2);
                
                document.add(paragrafo1);
                document.add(table);
              
                PdfContentByte canvas = writer.getDirectContentUnder();
                img.scaleAbsolute(PageSize.A4.rotate());
                img.setAbsolutePosition(0,0);
                canvas.addImage(img);               
               
                Main.labelMensagem.setVisible(true);
                Main.labelMensagem.setBackground(new Color(0,204,51));
                Main.labelMensagem.setText("Certificado gerado");
                setRegistroDigital(nomeAluno, emailAluno, vigencia, registro);
                setCertificadoAluno(pastaProjeto, coluna);
                Desktop.getDesktop().open(new File(Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\" + registro + "-"+ nomeAluno + ".pdf"));
        } 
        catch (SQLException | FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        catch (IOException ex) 
        {
                Logger.getLogger(DeclaracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            document.close();
        }
    }
    
    public void geraCertificadoOrientador(String linhaProjeto, String periodo, int registro) throws ParseException
    {
        Document document = new Document(PageSize.A4.rotate());
        Font f = new  Font(Font.getFamily("Calibri"), 72, Font.NORMAL);
        Font f3 = new Font(Font.getFamily("Calibri"), 16, Font.BOLD);
        Font f4 = new Font(Font.getFamily("Calibri"), 10, Font.BOLD);
        
        int row = Main.jTableMain.getSelectedRow();  
        String pastaProjeto      = Main.jTableMain.getValueAt(row, 0).toString();
        String nomeAluno1        = Main.jTableMain.getValueAt(row, 2).toString();
        String orientador        = Main.jTableMain.getValueAt(row, 4).toString();
        String emailOrientador   = Main.jTableMain.getValueAt(row, 25).toString();
        String projeto           = Main.jTableMain.getValueAt(row,6).toString();
        String modalidade_aluno1 = Main.jTableMain.getValueAt(row, 13).toString();
        String modalidade_aluno2 = Main.jTableMain.getValueAt(row, 14).toString();
        String vigencia          = Main.jTableMain.getValueAt(row, 5).toString();
        String pro = vigencia;
        
        int um;
        int dois;
        int tres;
        um = pro.indexOf("2");
        dois = pro.lastIndexOf("-");
        tres = pro.indexOf("-2");
        String inicio = (pro.substring(um, dois)); 
        String fim = (pro.substring(tres + 1));
        periodo = "01/08/" + inicio + " a " + "31/07/" + fim;
        
        if (Main.jTableMain.getValueAt(row, 3) == null)
        {                        
            try 
            {
                File diretorio = new File(Usuario.getSalvar_em() + "\\" + linhaProjeto);
                    //JOptionPane.showMessageDialog(null, linhaProjeto);
                if (!diretorio.exists())
                     diretorio.mkdirs();
                
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(  Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\" + registro + "-"+ orientador + ".pdf"));
                document.open();
                Image img = Image.getInstance(Usuario.getSalvar_em() + "\\00 - modelo\\certificado.png");
                
                float[] columnWidths = {255};
                PdfPTable table = new PdfPTable(columnWidths);
                table.setWidthPercentage(98);
                 
                Paragraph paragrafo1 = new Paragraph(" ", f);
                paragrafo1.setLeading(175f);
                
                PdfPCell cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setFixedHeight(332f);
                
                instrucao = conexao.prepareStatement("SELECT titulacao_orientador from orientador where nome_orientador like '" + orientador + "'");
                resultado = instrucao.executeQuery();
                resultado.first();
                String cargo = resultado.getString("titulacao_orientador");

                            
                
                String HTML = "<p class=\"texto\">Certificamos, para os devidos fins, que o(a) " + cargo + " <b>" + orientador + "</b> orientou o(a) aluno(a) do Programa Institucional " + modalidade_aluno1 + ", <b>" + nomeAluno1 + "</b>, vinculado(a) ao projeto de pesquisa intitulado <b><i>\"" + projeto + "\"</i></b>, no período de " + periodo + ".<br></br><br></br></p><p class=\"data\"><b>" + getData()+ "</b></p>";
                String CSS = "p.texto{ font-family: Arial; line-height: 1.5; font-size: 24px; text-align: justify;}p.data{ font-family: Arial; line-height: 1.5; font-size: 24px; text-align:right;}";

                ElementList list = XMLWorkerHelper.parseToElementList(HTML,CSS);            
                list.forEach((element) -> 
                {
                    cell.addElement(element);
                });
                table.addCell(cell);
                
                PdfPCell cell3 = new PdfPCell(new Phrase("Certificado gerado pelo sistema SGPP - Registro nº "+ String.format("%06d", registro),f4));
                cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell3.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell3);
                
                document.add(paragrafo1);
                document.add(table);
              
                PdfContentByte canvas = writer.getDirectContentUnder();
                Image image = Image.getInstance(img);
                image.scaleAbsolute(PageSize.A4.rotate());
                image.setAbsolutePosition(0,0);
                canvas.addImage(image);                
               
                Main.labelMensagem.setVisible(true);
                Main.labelMensagem.setBackground(new Color(0,204,51));
                Main.labelMensagem.setText("Certificado gerado");
                setRegistroDigital(orientador, emailOrientador, vigencia, registro);
                setCertificadoOrientador(pastaProjeto);
                Desktop.getDesktop().open(new File(Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\" + registro + "-"+ orientador + ".pdf"));
            } 
            catch (SQLException | FileNotFoundException | DocumentException ex) {
                JOptionPane.showMessageDialog(null, "Erro:" + ex);
            }
            catch (IOException ex) {
                    Logger.getLogger(DeclaracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                document.close();
            }
        }
        else
        {
              try
              {
                
                File diretorio = new File(Usuario.getSalvar_em() + "\\" + linhaProjeto);
                    //JOptionPane.showMessageDialog(null, linhaProjeto);
                if (!diretorio.exists())
                     diretorio.mkdirs();
                
                String nomeAluno2 = Main.jTableMain.getValueAt(row, 3).toString();  
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(  Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\" + registro + "-"+ orientador + ".pdf"));
                document.open();
              Image img = Image.getInstance(Usuario.getSalvar_em() + "\\00 - modelo\\certificado.png");
                
                float[] columnWidths = {255};
                PdfPTable table = new PdfPTable(columnWidths);
                table.setWidthPercentage(98);
                Paragraph paragrafo1 = new Paragraph(" ", f);
                paragrafo1.setLeading(175f);
                
                PdfPCell cell = new PdfPCell();                
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setFixedHeight(332f);
                
                instrucao = conexao.prepareStatement("SELECT titulacao_orientador from orientador where nome_orientador like '" + orientador + "'");
                resultado = instrucao.executeQuery();
                resultado.first();
                String cargo = resultado.getString("titulacao_orientador");

                            
                
                String HTML = "<p class=\"texto\">Certificamos, para os devidos fins, que o(a) " + cargo + " <b>" + orientador + "</b> orientou os alunos do Programa Institucional " + modalidade_aluno1 + ", <b>" + nomeAluno1 + "</b> e " + modalidade_aluno2 + ", <b>" + nomeAluno2 + "</b> vinculados ao projeto de pesquisa intitulado <b><i>\"" + projeto + "\"</i></b>, no período de " + periodo + ".<br></br><br></br></p><p class=\"data\"><b>" + getData()+ "</b></p>";
                String CSS = "p.texto{ font-family: Arial; line-height: 1.5; font-size: 24px; text-align: justify;}p.data{ font-family: Arial; line-height: 1.5; font-size: 24px; text-align:right;}";
            
                ElementList list = XMLWorkerHelper.parseToElementList(HTML,CSS);            
                list.forEach((element) -> 
                {
                    cell.addElement(element);
                });
                table.addCell(cell);
                
                PdfPCell cell3 = new PdfPCell(new Phrase("Certificado gerado pelo sistema SGPP - Registro nº "+ String.format("%06d", registro),f4));
                cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell3.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell3);
                
                document.add(paragrafo1);
                document.add(table);
                
                PdfContentByte canvas = writer.getDirectContentUnder();
                Image image = Image.getInstance(img);
                image.scaleAbsolute(PageSize.A4.rotate());
                image.setAbsolutePosition(0,0);
                canvas.addImage(image);
                               
                Main.labelMensagem.setVisible(true);
                Main.labelMensagem.setBackground(new Color(0,204,51));
                Main.labelMensagem.setText("Certificado geradoo");
                setRegistroDigital(orientador, emailOrientador, vigencia, registro);
                setCertificadoOrientador(pastaProjeto);
                Desktop.getDesktop().open(new File(Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\" + registro + "-"+ orientador + ".pdf"));
            } 
            catch (SQLException | FileNotFoundException | DocumentException ex) 
                    {
                JOptionPane.showMessageDialog(null, "Erro:" + ex);
            }
            catch (IOException ex) 
            {
                    Logger.getLogger(DeclaracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                document.close();
            }
            
        }
    
    }    
    public void setOcorrenciasCertificado(String linhaProjeto, String nome, String email)
    {
        try{
            
            instrucao = conexao.prepareStatement("SELECT aluno1, aluno2, nome_orientador from projeto_view where id_projeto = " + linhaProjeto);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            if(nome.equals(resultado.getString("aluno1")))
            {
                instrucao = conexao.prepareStatement("UPDATE projeto SET certificado_enviado1 = 2 where id_projeto = " + linhaProjeto);//isso so funciona pra varchar
                instrucao.execute();
            }else if(nome.equals(resultado.getString("aluno2")))
            {
                instrucao = conexao.prepareStatement("UPDATE projeto SET certificado_enviado2 = 2 where id_projeto = " + linhaProjeto);//isso so funciona pra varchar
                instrucao.execute();                
            }else if(nome.equals(resultado.getString("nome_orientador")))
            {
                instrucao = conexao.prepareStatement("UPDATE projeto SET certificado_orientador = 2 where id_projeto = " + linhaProjeto);//isso so funciona pra varchar
                instrucao.execute();                
            } 
            
            Date data =  new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String data_atual = formato.format(data);
            
            instrucao = conexao.prepareStatement("INSERT INTO ocorrencias (projeto_id,tipo_ocorrencia,descricao_ocorrencia,data_ocorrencia) VALUES (?,?,?,?)");//isso so funciona pra varchar
            
            instrucao.setInt    (1, Integer.parseInt(linhaProjeto));
            instrucao.setString (2, "CERTIFICADO");
            instrucao.setString (3, nome);            
            instrucao.setString (4, data_atual);
            instrucao.execute();  
        
            }
            catch(SQLException e)
            {
                if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum projeto relacio" + e);                
                }  
            }
    }
    
    public int populaNRegistro()
    {
      try
        {
            instrucao = conexao.prepareStatement("SELECT max(nregistro) + 1  as nregistro from registro");
            resultado = instrucao.executeQuery();
            resultado.first();
            int nregistropopula = resultado.getInt("nregistro");                       
            return nregistropopula;           
            
        }
        catch( SQLException e )
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, e);                
                }  
            return 0;
        }
    }
    
    public void setRegistroDigital(String nome, String email, String vigencia, int nregistro)
    {
        try
       {
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
           Date date = new Date();
            
            instrucao = conexao.prepareStatement("INSERT INTO registro (nregistro,nome,descricao,data_registro,email,data_entrega,situacao,n_documento) VALUES (?,?,?,?,?,?,?,?)");//isso so funciona pra varchar
            instrucao.setInt    (1, nregistro);
            instrucao.setString (2, nome);
            instrucao.setString (3, "Certificado PIBIC " + vigencia);
            instrucao.setString (4, String.valueOf(dateFormat.format(date)));
            instrucao.setString (5, email);
            instrucao.setString (6, null);
            instrucao.setString (7, null);
            instrucao.setInt    (8, Integer.parseInt(Main.linhaProjeto));
            instrucao.execute();
            
            
            Main.labelMensagem.setText(Main.labelMensagem.getText() + " e inserido com sucesso no Registro Digital");
        }
        catch( MySQLIntegrityConstraintViolationException ex )
        {
             JOptionPane.showMessageDialog(null, "Número de registro já existente");

        }
        catch( SQLException e )
        {
             if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, e);                
                }  
        }
    }
    
    public void setCertificadoAluno(String idProjeto, String coluna)
    {
        try
       {            
            instrucao = conexao.prepareStatement("UPDATE projeto SET " + coluna + " = 1 where id_projeto = " + idProjeto);//isso so funciona pra varchar
            instrucao.execute();
            
            AlunoDAO a = new AlunoDAO();
            OrientadorDAO o = new OrientadorDAO();
            
            if(Main.JRbALuno.isSelected())
            {
                if(Main.jrbMedio.isSelected())
                 {
                    a.getAlunoMedio();
                 }else if(Main.jrbSuperior.isSelected())
                 {
                    a.getAlunoSuperior();
                 }else
                 {
                     a.getAluno("");
                 } 
            }else
            {
                 if(Main.jrbMedio.isSelected())
                 {
                    o.getOrientadorMedio();
                 }else if(Main.jrbSuperior.isSelected())
                 {
                    o.getOrientadorSuperior();
                 }else
                 {
                     o.getOrientador("");
                 } 
            }
            
        }
        catch( MySQLIntegrityConstraintViolationException ex )
        {
             JOptionPane.showMessageDialog(null, "Erro cerficado 1" + ex);

        }
        catch( SQLException e )
        {
             if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, e);                
                }  
        }
    }
    
    public void setCertificadoOrientador(String idProjeto)
    {
        try
       {            
            instrucao = conexao.prepareStatement("UPDATE projeto SET certificado_orientador = 1 where id_projeto = " + idProjeto);//isso so funciona pra varchar
            instrucao.execute();
            
            AlunoDAO a = new AlunoDAO();
            OrientadorDAO o = new OrientadorDAO();
            
            if(Main.JRbALuno.isSelected())
            {
                if(Main.jrbMedio.isSelected())
                 {
                    a.getAlunoMedio();
                 }else if(Main.jrbSuperior.isSelected())
                 {
                    a.getAlunoSuperior();
                 }else
                 {
                     a.getAluno("");
                 } 
            }else
            {
                 if(Main.jrbMedio.isSelected())
                 {
                    o.getOrientadorMedio();
                 }else if(Main.jrbSuperior.isSelected())
                 {
                    o.getOrientadorSuperior();
                 }else
                 {
                     o.getOrientador("");
                 } 
            }
        }
        catch( MySQLIntegrityConstraintViolationException ex )
        {
             JOptionPane.showMessageDialog(null, "Erro cerficado 1" + ex);

        }
        catch( SQLException e )
        {
             if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, e);                
                }  
        }
    }
    
    public String getData()
    {
        Date data =  new Date();
        SimpleDateFormat formatoDia = new SimpleDateFormat("dd");
        String dia = formatoDia.format(data);
        SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
        String mes = formatoMes.format(data);
        String mesString = mes;
        switch (mes) 
        {
            case "01":
                mesString = "janeiro";
                break;
            case "02":
                mesString = "fevereiro";
                break;
            case "03":
                mesString = "março";
                break;
            case "04":
                mesString = "abril";
                break;
            case "05":
                mesString = "maio";
                break;
            case "06":
                mesString = "junho";
                break;
            case "07":
                mesString = "julho";
                break;
            case "08":
                mesString = "agosto";
                break;
            case "09":
                mesString = "setembro";
                break;
            case "10":
                mesString = "outubro";
                break;
            case "11":
                mesString = "novembro";
                break;
            case "12":
                mesString = "dezembro";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Mês inválido");
                break;
        }
        SimpleDateFormat formatoAno = new SimpleDateFormat("yyyy");
        String ano = formatoAno.format(data);
        
        return "Urutaí, " + dia + " de " + mesString + " de " + ano + ".";
    }
}
    

