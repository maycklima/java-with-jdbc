/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author mayck
 */
public class ExportarExcel 
{
    private File file;
    private List<JTable> tabela;
    private List<String> nome_files;

    public ExportarExcel(File file, List<JTable> tabela, List<String> nome_files) throws Exception {
        this.file = file;
        this.tabela = tabela;
        this.nome_files = nome_files;
        if(nome_files.size()!=tabela.size())
        {
              throw new Exception("Error");
        }
    }
    
    public boolean exportar()
    {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            WritableWorkbook w = Workbook.createWorkbook(out);
            for (int index = 0; index < tabela.size(); index++){
                JTable table = tabela.get(index);
                WritableSheet s = w.createSheet(nome_files.get(index), 0);
                for (int i = 0; i < table.getColumnCount(); i++) 
                {   
                    for (int j = 0; j < table.getRowCount(); j++) 
                    {
                     Object object = table.getValueAt(j, i);
                     s.addCell(new Label(i,j,String.valueOf(object)));
                    }
                    
                }
                
            }
            w.write();
            w.close();
            return true;
        }catch (IOException | WriteException e)
        {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        
    }
}
