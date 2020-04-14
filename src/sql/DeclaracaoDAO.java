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
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class DeclaracaoDAO 
{
    public void geraDeclaracaoAluno(String linhaProjeto) throws ParseException
    {
        AlunoDAO ad = new AlunoDAO();
        Document document = new Document(PageSize.A4);
        Font f = new Font(Font.getFamily("Arial"), 22, Font.BOLD);
        Font f2 = new Font(Font.getFamily("Arial"), 12, Font.BOLD);
        
        int row = Main.jTableMain.getSelectedRow();         
        String pastaProjeto = Main.jTableMain.getValueAt(row, 0 ).toString();
        String nomeAluno    = Main.jTableMain.getValueAt(row, 1 ).toString();
        String orientador   = Main.jTableMain.getValueAt(row, 4 ).toString();
        String projeto      = Main.jTableMain.getValueAt(row, 6 ).toString();
        String id_aluno     = Main.jTableMain.getValueAt(row, 17).toString();
        String id_aluno1    = Main.jTableMain.getValueAt(row, 18).toString();            
        String vigencia     = Main.jTableMain.getValueAt(row, 5 ).toString();        
        String id_aluno2;
        String modalidade;
        String cpf;            
        String pro = vigencia;        
        int um;
        int dois;        
        int tres; 
        
        if (id_aluno.equals(id_aluno1))
        {
            cpf = ad.getCpfAluno(id_aluno1);
            modalidade = Main.jTableMain.getValueAt(row, 13).toString();
        }
        else 
        {   
            id_aluno2 = Main.jTableMain.getValueAt(row, 19).toString();
            cpf = ad.getCpfAluno(id_aluno2);
            modalidade = Main.jTableMain.getValueAt(row, 14).toString();
        }
        um = pro.indexOf("2");
        dois = pro.lastIndexOf("-");
        tres = pro.indexOf("-2");
        String inicio = (pro.substring(um, dois)); 
        String fim = (pro.substring(tres + 1));        
        
        try 
        {   
            File diretorio = new File(Usuario.getSalvar_em() + "\\" + linhaProjeto);
                //JOptionPane.showMessageDialog(null, linhaProjeto);
            if (!diretorio.exists())
                 diretorio.mkdirs();

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream( Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\Declaração - "+ nomeAluno + ".pdf"));
            document.open();
            Image img = Image.getInstance(Usuario.getSalvar_em() + "\\00 - modelo\\declaracao.png");
            
            Paragraph paragrafo1 = new Paragraph("DECLARAÇÃO", f);
            paragrafo1.setLeading(165f);

            Paragraph paragrafo2 = new Paragraph();
            paragrafo2.setSpacingBefore(50f);
            paragrafo2.setSpacingAfter(40f);
            
            instrucao = conexao.prepareStatement("SELECT titulacao_orientador from orientador where nome_orientador like '" + orientador + "'");
            resultado = instrucao.executeQuery();
            resultado.first();
            String cargo = resultado.getString("titulacao_orientador");
                    
            
            String HTML = "<p>Declaro, para os devidos fins, que o(a) acadêmico(a) <b><i>" + nomeAluno + "</i></b>, portador(a) do CPF: " + cpf + ", participa do Programa de Iniciação Científica, " + modalidade + ", desenvolvendo o projeto <b><i>\"" + projeto + "\"</i></b>, sob orientação do(a) "+ cargo +" <b><i>" + orientador + "</i></b>, com início das atividades datada em agosto de " + inicio + " e término previsto para julho de " + fim + ".</p>";
            String CSS = "p{ font-family: Arial; line-height: 1.5; font-size: 18px; text-align: justify; text-indent: 60px;}";
            
            
            ElementList list = XMLWorkerHelper.parseToElementList(HTML, CSS);            
            list.forEach((element) -> 
            {
                paragrafo2.add(element);
            });            

            Paragraph paragrafo3 = new Paragraph(getData(), f2);            
            paragrafo1.setAlignment(Element.ALIGN_CENTER);
            paragrafo3.setAlignment(Element.ALIGN_RIGHT);

            document.add(paragrafo1);
            document.add(paragrafo2);
            document.add(paragrafo3);
            
            PdfContentByte canvas = writer.getDirectContentUnder();
            img.scaleAbsolute(PageSize.A4);
            img.setAbsolutePosition(0,0);
            canvas.addImage(img);
                
            setOcorrencias(linhaProjeto, nomeAluno + " (Aluno)");
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setBackground(new Color(0,204,51));
            Main.labelMensagem.setText("Declaração emitida com sucesso!");
            Main.labelMensagem.setBackground(new Color(0,204,51));
            Desktop.getDesktop().open(new File(Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\Declaração - " + nomeAluno + ".pdf"));
        } 
        catch (FileNotFoundException | DocumentException | SQLException ex) {
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
    
    public void geraDeclaracaoAlunoValor(String linhaProjeto)
    {
        AlunoDAO ad = new AlunoDAO();
        Document document = new Document();
        
        Font f = new Font(Font.getFamily("Arial"), 22, Font.BOLD);
        Font f2 = new Font(Font.getFamily("Arial"), 12, Font.BOLD);
    
        document.setPageSize(PageSize.A4);
        int row = Main.jTableMain.getSelectedRow();
        
        String pastaProjeto = Main.jTableMain.getValueAt(row, 0 ).toString();
        String nomeAluno    = Main.jTableMain.getValueAt(row, 1 ).toString();
        String orientador   = Main.jTableMain.getValueAt(row, 4 ).toString();
        String projeto      = Main.jTableMain.getValueAt(row, 6 ).toString();
        String id_aluno     = Main.jTableMain.getValueAt(row, 17).toString();
        String id_aluno1    = Main.jTableMain.getValueAt(row, 18).toString();
        String vigencia     = Main.jTableMain.getValueAt(row, 5 ).toString();
        String id_aluno2;            
        String modalidade;
        String cpf;
        String valor;        
        String pro = vigencia;
        
        int um;
        int dois;
        int tres;
        um = pro.indexOf("2");
        dois = pro.lastIndexOf("-");
        tres = pro.indexOf("-2");
        String inicio = (pro.substring(um, dois));   
        String fim = (pro.substring(tres + 1));
        
        if (id_aluno.equals(id_aluno1))
        {
            modalidade = Main.jTableMain.getValueAt(row, 13).toString();
            cpf = ad.getCpfAluno(id_aluno1);
            if(modalidade.equals("PIBIC/CNPq") || modalidade.equals("PIBIC/IF Goiano") || modalidade.equals("PIVIC/IF Goiano") || modalidade.equals("PIBITI/CNPq"))
            {
                valor = JOptionPane.showInputDialog("Digite o valor da bolsa para o aluno do Ensino Superior");
            }else 
            {
                valor = JOptionPane.showInputDialog("Digite o valor da bolsa para o aluno do Ensino Médio");
            }
            if(valor == null) return;
        }
        else 
        {
            id_aluno2    = Main.jTableMain.getValueAt(row, 19).toString();
            modalidade = Main.jTableMain.getValueAt(row, 14).toString();
            cpf = ad.getCpfAluno(id_aluno2);
            if(modalidade.equals("PIBIC/CNPq") || modalidade.equals("PIBIC/IF Goiano") || modalidade.equals("PIVIC/IF Goiano") || modalidade.equals("PIBITI/CNPq"))
            {
                valor = JOptionPane.showInputDialog("Digite o valor da bolsa para o aluno do Ensino Superior");            
            }else 
            {
                valor = JOptionPane.showInputDialog("Digite o valor da bolsa para o aluno do Ensino Médio");
            }
            
            if(valor == null) return;            
        }
        try             
        {
            File diretorio = new File(Usuario.getSalvar_em() + "\\" + linhaProjeto);
                //JOptionPane.showMessageDialog(null, linhaProjeto);
            if (!diretorio.exists())
                 diretorio.mkdirs();

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream( Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\Declaração com valor - "+ nomeAluno + ".pdf"));
            document.open();
            Image img = Image.getInstance(Usuario.getSalvar_em() + "\\00 - modelo\\declaracao.png");
            img.setAlignment(Element.ALIGN_CENTER);                

            Paragraph paragrafo1 = new Paragraph("DECLARAÇÃO", f);
            paragrafo1.setLeading(150f);
            
            Paragraph paragrafo2 = new Paragraph();
            paragrafo2.setSpacingBefore(50f);
            paragrafo2.setSpacingAfter(40f);
               
            instrucao = conexao.prepareStatement("SELECT titulacao_orientador from orientador where nome_orientador like '" + orientador + "'");
            resultado = instrucao.executeQuery();
            resultado.first();
            String cargo = resultado.getString("titulacao_orientador");
            
            String HTML = "<p>Declaro, para os devidos fins, que o(a) acadêmico(a) <b><i>" + nomeAluno + "</i></b>, portador(a) do CPF: " + cpf + ", participa do Programa de Iniciação Científica, " + modalidade + ", na condição de bolsista, recebendo mensalmente o valor de R$"+ valor +", desenvolvendo o projeto <b><i>\"" + projeto + "\"</i></b>, sob orientação do(a) "+ cargo + " <b><i>" + orientador + "</i></b>, com início das atividades datada em agosto de " + inicio + " e término previsto para julho de " + fim + ".</p>";
            String CSS = "p{ font-family: Arial; line-height: 1.5; font-size: 18px; text-align: justify; text-indent: 60px;}";
            
            
            ElementList list = XMLWorkerHelper.parseToElementList(HTML, CSS);            
            list.forEach((element) -> 
            {
                paragrafo2.add(element);
            });
            
            Paragraph paragrafo3 = new Paragraph(getData(), f2);
            
            paragrafo1.setAlignment(Element.ALIGN_CENTER);
            paragrafo3.setAlignment(Element.ALIGN_RIGHT);

            document.add(paragrafo1);
            document.add(paragrafo2);
            document.add(paragrafo3);
                
                PdfContentByte canvas = writer.getDirectContentUnder();
                img.scaleAbsolute(PageSize.A4);
                img.setAbsolutePosition(0,0);
                canvas.addImage(img);
                
                setOcorrencias(linhaProjeto, nomeAluno + " (Aluno)");
                Main.labelMensagem.setVisible(true);
                Main.labelMensagem.setBackground(new Color(0,204,51));
                Main.labelMensagem.setText("Declaração emitida com sucesso!");
                Main.labelMensagem.setBackground(new Color(0,204,51));
                Desktop.getDesktop().open(new File(Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\Declaração com valor - " + nomeAluno + ".pdf"));
        } 
        catch (FileNotFoundException | SQLException |DocumentException ex) {
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
    public void geraDeclaracaoOrientador(String linhaProjeto)
    {
        Document document = new Document();
        document.setPageSize(PageSize.A4);
        Font f = new Font(Font.getFamily("Arial"), 20, Font.BOLD);
        Font f2 = new Font(Font.getFamily("Arial"), 12, Font.BOLD);    
        int row = Main.jTableMain.getSelectedRow(); 
        
        String nomeOrientador    = Main.jTableMain.getValueAt(row, 4).toString(); 
        String projeto           = Main.jTableMain.getValueAt(row, 6).toString();
        String aluno1            = Main.jTableMain.getValueAt(row, 2).toString();
        String modalidade_aluno1 = Main.jTableMain.getValueAt(row, 13).toString();
        String pastaProjeto      = Main.jTableMain.getValueAt(row, 0).toString();
        String vigencia          = Main.jTableMain.getValueAt(row, 5).toString();
        String aluno2 ;
        String modalidade_aluno2;
        String pro = vigencia;        
        int um;
        int dois;      
        int tres;
        
        um = pro.indexOf("2");
        dois = pro.lastIndexOf("-");
        tres = pro.indexOf("-2");
        String inicio = (pro.substring(um, dois));
        String fim    = (pro.substring(tres + 1));
        
        
       if (Main.jTableMain.getValueAt(row, 3) == null)
       {
            try 
            {
            File diretorio = new File(Usuario.getSalvar_em() + "\\" + linhaProjeto);
                //JOptionPane.showMessageDialog(null, linhaProjeto);
            if (!diretorio.exists())
                 diretorio.mkdirs();

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream( Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\Declaração - "+ nomeOrientador + " - " + aluno1 + ".pdf"));
            document.open();
            Image img = Image.getInstance(Usuario.getSalvar_em() + "\\00 - modelo\\declaracao.png");
            img.setAlignment(Element.ALIGN_CENTER);                
               
            Paragraph paragrafo1 = new Paragraph("DECLARAÇÃO", f);
            paragrafo1.setLeading(165f);

            Paragraph paragrafo3 = new Paragraph(getData(), f2);
            
            instrucao = conexao.prepareStatement("SELECT titulacao_orientador from orientador where nome_orientador like '" + nomeOrientador + "'");
            resultado = instrucao.executeQuery();
            resultado.first();
            String cargo = resultado.getString("titulacao_orientador");
            
            String HTML = "<p>Declaro, para os devidos fins, que o(a) "+ cargo + " <b><i>" + nomeOrientador + "</i></b>, participa do Programa Institucional de Iniciação Científica do IF Goiano - Câmpus Urutaí, por meio da coordenação do projeto de pesquisa intitulado <b><i>\"" + projeto + "\"</i></b>, desenvolvido com a participação do(a) acadêmico(a) <i><b>" + aluno1 + "</b></i> - (" + modalidade_aluno1 + "), com início das atividades datada em agosto de " + inicio + " e término previsto para julho de " + fim + ".</p>";
            String CSS = "p{ font-family: Arial; line-height: 1.5; font-size: 18px; text-align: justify; text-indent: 60px;}";
            
            Paragraph paragrafo2 = new Paragraph();
            paragrafo2.setSpacingBefore(50f);
            paragrafo2.setSpacingAfter(40f);
            
            ElementList list = XMLWorkerHelper.parseToElementList(HTML,CSS);
            
            list.forEach((element) -> 
            {
                paragrafo2.add(element);
            });
            
            paragrafo1.setAlignment(Element.ALIGN_CENTER);
            paragrafo3.setAlignment(Element.ALIGN_RIGHT);

            document.add(paragrafo1);
            document.add(paragrafo2);
            document.add(paragrafo3);
            
            PdfContentByte canvas = writer.getDirectContentUnder();
            img.scaleAbsolute(PageSize.A4);
            img.setAbsolutePosition(0,0);
            canvas.addImage(img);
            
            img.scaleAbsolute(PageSize.A4);
            img.setAbsolutePosition(0,0);
            canvas.addImage(img);

            setOcorrencias(linhaProjeto, nomeOrientador + " (Orientador)");
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setBackground(new Color(0,204,51));
            Main.labelMensagem.setText("Declaração emitida com sucesso!");
            Main.labelMensagem.setBackground(new Color(0,204,51));
            Desktop.getDesktop().open(new File(Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\Declaração - " + nomeOrientador + " - " + aluno1 + ".pdf"));

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
            aluno2 = Main.jTableMain.getValueAt(row, 3).toString();
            modalidade_aluno2 = Main.jTableMain.getValueAt(row, 14).toString();
            
            try 
            {
                File diretorio = new File(Usuario.getSalvar_em() + "\\" + linhaProjeto);
                    //JOptionPane.showMessageDialog(null, linhaProjeto);
                if (!diretorio.exists())
                     diretorio.mkdirs();
                
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream( Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\Declaração - " + nomeOrientador + " - " + aluno1 + " e " + aluno2 + ".pdf"));
                document.open();                
                Image img = Image.getInstance(Usuario.getSalvar_em() + "\\00 - modelo\\declaracao.png");
                img.setAlignment(Element.ALIGN_CENTER);
                
                Paragraph paragrafo1 = new Paragraph("DECLARAÇÃO", f);
                paragrafo1.setLeading(150f);
                
                Paragraph paragrafo2 = new Paragraph();
                paragrafo2.setSpacingBefore(50f);
                paragrafo2.setSpacingAfter(40f);

                Paragraph paragrafo3 = new Paragraph(getData(), f2);
                instrucao = conexao.prepareStatement("SELECT titulacao_orientador from orientador where nome_orientador like '" + nomeOrientador + "'");
                resultado = instrucao.executeQuery();
                resultado.first();
                String cargo = resultado.getString("titulacao_orientador");
            
                String HTML = "<p>Declaro, para os devidos fins, que o(a) "+ cargo + " <b><i>" + nomeOrientador + "</i></b>, participa do Programa Institucional de Iniciação Científica do IF Goiano - Câmpus Urutaí, por meio da coordenação do projeto de pesquisa intitulado <b><i>\"" + projeto + "\"</i></b>, desenvolvido com a participação dos acadêmicos <i><b>" + aluno1 + "</b></i> - (" + modalidade_aluno1 + ") e <i><b>" + aluno2 + "</b></i> - (" + modalidade_aluno2 + "), com início das atividades datada em agosto de " + inicio + " e término previsto para julho de " + fim + ".</p>";
                String CSS = "p{ font-family: Arial; line-height: 1.5; font-size: 18px; text-align: justify; text-indent: 60px;}";
            

                ElementList list = XMLWorkerHelper.parseToElementList(HTML, CSS);            
                list.forEach((element) -> {
                    paragrafo2.add(element);
                });
                
                paragrafo1.setAlignment(Element.ALIGN_CENTER);
                paragrafo3.setAlignment(Element.ALIGN_RIGHT);

                document.add(paragrafo1);
                document.add(paragrafo2);
                document.add(paragrafo3);          
                
                PdfContentByte canvas = writer.getDirectContentUnder();
                img.scaleAbsolute(PageSize.A4);
                img.setAbsolutePosition(0,0);
                canvas.addImage(img);
                
                setOcorrencias(linhaProjeto, nomeOrientador + " (Orientador)");
                Main.labelMensagem.setVisible(true);
                Main.labelMensagem.setBackground(new Color(0,204,51));
                Main.labelMensagem.setText("Declaração emitida com sucesso!");
                Main.labelMensagem.setBackground(new Color(0,204,51));
                Desktop.getDesktop().open(new File(Usuario.getSalvar_em() + "\\" + pastaProjeto + "\\Declaração - " + nomeOrientador + " - " + aluno1 + " e " + aluno2 + ".pdf"));
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
    
    public void setOcorrencias(String linhaProjeto, String nome)
    {
        try
        {
            Date data =  new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String data_atual = formato.format(data);
            
            instrucao = conexao.prepareStatement("INSERT INTO ocorrencias (projeto_id,tipo_ocorrencia,descricao_ocorrencia,data_ocorrencia) VALUES (?,?,?,?)");//isso so funciona pra varchar
            
            instrucao.setInt    (1, Integer.parseInt(linhaProjeto));
            instrucao.setString (2, "DECLARAÇÃO");
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
                    JOptionPane.showMessageDialog(null, e);                
                }  
        }
    }
}
