package interfaces;

import static interfaces.Main.JRbALuno;
import static interfaces.Main.jRbOrientador;
import static interfaces.Main.jTableMain;
import static interfaces.Main.jrbMedio;
import static interfaces.Main.jrbSuperior;
import static interfaces.Main.linha_selecionada;
import modelos.FixedLengthDocument;
import modelos.Projeto;
import modelos.Usuario;
import sql.AlunoDAO;
import sql.OrientadorDAO;
import sql.ProjetoDAO;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AlterarProjeto extends javax.swing.JDialog {

    JFileChooser abrir = new JFileChooser();
    String caminho;
    String idProjeto;
    public AlterarProjeto(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        labelMensagem.setVisible(false);
    }
    
    public void atualizarProjeto()
    {          
        if(txtFile.isVisible() && txtFile.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setBackground(new Color(230,0,0));
            labelMensagem.setText("Selecione o arquivo da alteração");
        }else if(txtProjeto.getText().isEmpty())
        {
            labelMensagem.setVisible(true);
            labelMensagem.setBackground(new Color(230,0,0));
            labelMensagem.setText("Insira um nome para o projeto");
        }else 
        {        
                      
            ProjetoDAO pro = new ProjetoDAO();
            AlunoDAO aluno = new AlunoDAO();
            OrientadorDAO o = new OrientadorDAO();

            Projeto p = new  Projeto();                                
            p.setNomeProjeto(txtProjeto.getText());
            p.setModalidadeAluno1(String.valueOf(JComboBoxModalidadeAluno1.getSelectedItem()));
            p.setModalidadeAluno2(String.valueOf(JComboBoxModalidadeAluno2.getSelectedItem()));
            p.setNomeOrientador(txtOrientador.getText());
            
            if(aluno1_parcial_atrasado.isSelected() && aluno1_parcial.isSelected())
            {
                p.setrParcial1(2);
            }
            else if(aluno1_parcial.isSelected())
            {
              p.setrParcial1(1);
            }else
            {
              p.setrParcial1(0);
            }
            
            if(aluno1_final_atrasado.isSelected() && aluno1_final.isSelected())
            {
                p.setrFinal1(2);
            }
            else if(aluno1_final.isSelected())
            {
              p.setrFinal1(1);
            }else
            {
              p.setrFinal1(0);
            }

            if(aluno1_ceit_atrasado.isSelected() && aluno1_ceit.isSelected())
            {
                p.setCeit1(2);
            }
            else if(aluno1_ceit.isSelected())
            {
              p.setCeit1(1);
            }else
            {
              p.setCeit1(0);
            }
            
            if(aluno2_parcial_atrasado.isSelected() && aluno2_parcial.isSelected())
            {
                p.setrParcial2(2);
            }
            else if(aluno2_parcial.isSelected())
            {
              p.setrParcial2(1);
            }else
            {
              p.setrParcial2(0);
            }
            
            if(aluno2_final_atrasado.isSelected() && aluno2_final.isSelected())
            {
                p.setrFinal2(2);
            }
            else if(aluno2_final.isSelected())
            {
              p.setrFinal2(1);
            }else
            {
              p.setrFinal2(0);
            }

            if(aluno2_ceit_atrasado.isSelected() && aluno2_ceit.isSelected())
            {
                p.setCeit2(2);
            }
            else if(aluno2_ceit.isSelected())
            {
              p.setCeit2(1);
            }else
            {
              p.setCeit2(0);
            }
            
            if(cbAlterarTitulo.isSelected())
            {
                p.setAlteracao_titulo(true);
                enviarPDF();
            }else{
                p.setAlteracao_titulo(false);
            }
            
            if(certificadoEnviado1.isSelected() && certificadoGerado1.isSelected())
            {
                p.setCertificado1(2);
            }else if(certificadoGerado1.isSelected())
            {
                p.setCertificado1(1);
            }
            else
            {
              p.setCertificado1(0);
            }
            
            if(certificadoEnviado2.isSelected() && certificadoGerado2.isSelected())
            {
                p.setCertificado2(2);
            }else if(certificadoGerado2.isSelected())
            {
                p.setCertificado2(1);
            }
            else
            {
              p.setCertificado2(0);
            }
            
            if(certificadoOrientadorEnviado.isSelected() && certificadoOrientadorGerado.isSelected() )
            {
                p.setCertificadoOrientador(2);
            }else if(certificadoOrientadorGerado.isSelected())
            {
                p.setCertificadoOrientador(1);
            }
            else
            {
              p.setCertificadoOrientador(0);
            }
            
            pro.atualizarProjeto(p, Main.linhaProjeto);;
            Main.linhaProjeto = "0";
        
        if(jRbOrientador.isSelected())
        {
            if(jrbSuperior.isSelected())
            {
                o.getOrientadorSuperior();
            }else if(jrbMedio.isSelected())
            {
                o.getOrientadorMedio();
            }else{
                o.getOrientador("");
            }
        }
        else if(JRbALuno.isSelected())
        {
          if(jrbSuperior.isSelected())
            {
                aluno.getAlunoSuperior();
            }else if(jrbMedio.isSelected())
            {
                aluno.getAlunoMedio();
            }else{
                aluno.getAluno("");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Escolha entre aluno e orientador pra fazer a busca!");
        }
        Main.limparProjeto();
        dispose();
        } 
    }
    
    public void enviarPDF()
    {       
       File diretorio = new File(Usuario.getSalvar_em() + "/" + idProjeto);
       
        
       if (!diretorio.exists())
            diretorio.mkdirs();
 
        
       try {
            // Move o arquivo para o novo diretorio
            
            
            //JOptionPane.showMessageDialog(null, diretorio);
            Files.copy(Paths.get(caminho), Paths.get(diretorio + "/" + "Alteração do título.pdf"));
        } catch (FileAlreadyExistsException ex) 
        {
            JOptionPane.showMessageDialog(null, "Já existe um arquivo com esse nome");
        } catch (IOException ex) {
            Logger.getLogger(SubstituirAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void verificaTitulo()
    {
        if(cbAlterarTitulo.isSelected())
        {
            btnArquivo.setVisible(true);
            labelArquivis.setVisible(true);
            imagemArquivo.setVisible(true);
            txtFile.setVisible(true);
        }else{
            btnArquivo.setVisible(false);
            labelArquivis.setVisible(false);
            imagemArquivo.setVisible(false);
            txtFile.setVisible(false);
        }
            
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aluno1_parcial_atrasado = new javax.swing.JCheckBox();
        aluno1_ceit_atrasado = new javax.swing.JCheckBox();
        aluno1_final_atrasado = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        nome = new javax.swing.JLabel();
        img = new javax.swing.JLabel();
        nome1 = new javax.swing.JLabel();
        nome2 = new javax.swing.JLabel();
        nome3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtProjeto = new javax.swing.JTextArea();
        JComboBoxModalidadeAluno1 = new javax.swing.JComboBox<>();
        JComboBoxModalidadeAluno2 = new javax.swing.JComboBox<>();
        labelAluno2 = new javax.swing.JLabel();
        labelModalidade2 = new javax.swing.JLabel();
        jbSalvar = new javax.swing.JButton();
        nome6 = new javax.swing.JLabel();
        txtAluno1 = new javax.swing.JTextField();
        txtAluno2 = new javax.swing.JTextField();
        txtOrientador = new javax.swing.JTextField();
        aluno2_ceit_atrasado = new javax.swing.JCheckBox();
        aluno2_final_atrasado = new javax.swing.JCheckBox();
        aluno2_parcial_atrasado = new javax.swing.JCheckBox();
        relatorio1 = new javax.swing.JLabel();
        aluno1_parcial = new javax.swing.JCheckBox();
        aluno1_final = new javax.swing.JCheckBox();
        aluno1_ceit = new javax.swing.JCheckBox();
        TxtVigencia = new javax.swing.JTextField();
        aluno2_ceit = new javax.swing.JCheckBox();
        aluno2_final = new javax.swing.JCheckBox();
        aluno2_parcial = new javax.swing.JCheckBox();
        relatorio2 = new javax.swing.JLabel();
        adicionarAluno = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        cbAlterarTitulo = new javax.swing.JCheckBox();
        imagemArquivo = new javax.swing.JLabel();
        txtFile = new javax.swing.JTextField();
        labelMensagem = new javax.swing.JLabel();
        labelArquivis = new javax.swing.JLabel();
        alterarProjeto = new javax.swing.JLabel();
        btnArquivo = new javax.swing.JButton();
        relatorio3 = new javax.swing.JLabel();
        certificadoGerado1 = new javax.swing.JCheckBox();
        certificadoOrientadorEnviado = new javax.swing.JCheckBox();
        certificadoGerado2 = new javax.swing.JCheckBox();
        certificadoOrientadorGerado = new javax.swing.JCheckBox();
        certificadoEnviado1 = new javax.swing.JCheckBox();
        certificadoEnviado2 = new javax.swing.JCheckBox();
        certificado2 = new javax.swing.JLabel();
        relatorio5 = new javax.swing.JLabel();
        jRbalterarOrientador = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Projeto");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        aluno1_parcial_atrasado.setText("Atrasado");
        aluno1_parcial_atrasado.setFocusable(false);
        aluno1_parcial_atrasado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno1_parcial_atrasadoActionPerformed(evt);
            }
        });
        getContentPane().add(aluno1_parcial_atrasado);
        aluno1_parcial_atrasado.setBounds(580, 170, 80, 23);

        aluno1_ceit_atrasado.setText("Atrasado");
        aluno1_ceit_atrasado.setFocusable(false);
        aluno1_ceit_atrasado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno1_ceit_atrasadoActionPerformed(evt);
            }
        });
        getContentPane().add(aluno1_ceit_atrasado);
        aluno1_ceit_atrasado.setBounds(580, 210, 80, 23);

        aluno1_final_atrasado.setText("Atrasado");
        aluno1_final_atrasado.setFocusable(false);
        aluno1_final_atrasado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno1_final_atrasadoActionPerformed(evt);
            }
        });
        getContentPane().add(aluno1_final_atrasado);
        aluno1_final_atrasado.setBounds(580, 190, 80, 23);

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(50, 150, 590, 10);

        nome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome.setText("Aluno 1");
        getContentPane().add(nome);
        nome.setBounds(50, 170, 140, 20);

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Projeto.png"))); // NOI18N
        getContentPane().add(img);
        img.setBounds(520, 10, 140, 130);

        nome1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome1.setText("Título do projeto");
        getContentPane().add(nome1);
        nome1.setBounds(50, 440, 170, 20);

        nome2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome2.setText("Modalidade");
        getContentPane().add(nome2);
        nome2.setBounds(360, 170, 140, 20);

        nome3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome3.setText("Vigência");
        getContentPane().add(nome3);
        nome3.setBounds(50, 90, 180, 20);

        txtProjeto.setColumns(20);
        txtProjeto.setDocument(new FixedLengthDocument(255));
        txtProjeto.setLineWrap(true);
        txtProjeto.setRows(5);
        txtProjeto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProjetoFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(txtProjeto);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 460, 580, 90);

        JComboBoxModalidadeAluno1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PIBIC/IF Goiano", "PIBIC/CNPq", "PIVIC/IF Goiano", "PIBITI/IF Goiano", "PIBITI/CNPq", "PIVITI/IF Goiano", "PIBIC Jr/IF Goiano", "PIBIC Jr/CNPq", "PIVIC Jr/IF Goiano" }));
        JComboBoxModalidadeAluno1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JComboBoxModalidadeAluno1MouseClicked(evt);
            }
        });
        JComboBoxModalidadeAluno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBoxModalidadeAluno1ActionPerformed(evt);
            }
        });
        getContentPane().add(JComboBoxModalidadeAluno1);
        JComboBoxModalidadeAluno1.setBounds(360, 190, 140, 30);

        JComboBoxModalidadeAluno2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "(Nenhum)", "PIBIC/IF Goiano", "PIBIC/CNPq", "PIVIC/IF Goiano", "PIBITI/IF Goiano", "PIBITI/CNPq", "PIVITI/IF Goiano", "PIBIC Jr/IF Goiano", "PIBIC Jr/CNPq", "PIVIC Jr/IF Goiano" }));
        JComboBoxModalidadeAluno2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JComboBoxModalidadeAluno2MouseClicked(evt);
            }
        });
        JComboBoxModalidadeAluno2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBoxModalidadeAluno2ActionPerformed(evt);
            }
        });
        getContentPane().add(JComboBoxModalidadeAluno2);
        JComboBoxModalidadeAluno2.setBounds(360, 300, 140, 30);

        labelAluno2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelAluno2.setText("Aluno 2");
        getContentPane().add(labelAluno2);
        labelAluno2.setBounds(50, 280, 140, 20);

        labelModalidade2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelModalidade2.setText("Modalidade");
        getContentPane().add(labelModalidade2);
        labelModalidade2.setBounds(360, 280, 140, 20);

        jbSalvar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jbSalvar.setText("Salvar");
        jbSalvar.setToolTipText("Clique para salvar suas alterações");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(jbSalvar);
        jbSalvar.setBounds(510, 570, 120, 30);

        nome6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome6.setText("Orientador");
        getContentPane().add(nome6);
        nome6.setBounds(50, 380, 180, 20);

        txtAluno1.setEditable(false);
        txtAluno1.setBackground(new java.awt.Color(204, 204, 204));
        txtAluno1.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtAluno1.setDoubleBuffered(true);
        txtAluno1.setFocusable(false);
        getContentPane().add(txtAluno1);
        txtAluno1.setBounds(50, 190, 300, 30);

        txtAluno2.setEditable(false);
        txtAluno2.setBackground(new java.awt.Color(204, 204, 204));
        txtAluno2.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtAluno2.setDoubleBuffered(true);
        txtAluno2.setFocusable(false);
        getContentPane().add(txtAluno2);
        txtAluno2.setBounds(50, 300, 300, 30);

        txtOrientador.setEditable(false);
        txtOrientador.setBackground(new java.awt.Color(204, 204, 204));
        txtOrientador.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtOrientador.setDoubleBuffered(true);
        txtOrientador.setFocusable(false);
        getContentPane().add(txtOrientador);
        txtOrientador.setBounds(50, 400, 300, 30);

        aluno2_ceit_atrasado.setText("Atrasado");
        aluno2_ceit_atrasado.setFocusable(false);
        aluno2_ceit_atrasado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno2_ceit_atrasadoActionPerformed(evt);
            }
        });
        getContentPane().add(aluno2_ceit_atrasado);
        aluno2_ceit_atrasado.setBounds(580, 330, 80, 23);

        aluno2_final_atrasado.setText("Atrasado");
        aluno2_final_atrasado.setFocusable(false);
        aluno2_final_atrasado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno2_final_atrasadoActionPerformed(evt);
            }
        });
        getContentPane().add(aluno2_final_atrasado);
        aluno2_final_atrasado.setBounds(580, 310, 80, 23);

        aluno2_parcial_atrasado.setText("Atrasado");
        aluno2_parcial_atrasado.setFocusable(false);
        aluno2_parcial_atrasado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno2_parcial_atrasadoActionPerformed(evt);
            }
        });
        getContentPane().add(aluno2_parcial_atrasado);
        aluno2_parcial_atrasado.setBounds(580, 290, 80, 23);

        relatorio1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        relatorio1.setText("Relatórios");
        relatorio1.setFocusable(false);
        getContentPane().add(relatorio1);
        relatorio1.setBounds(520, 150, 140, 30);

        aluno1_parcial.setText("Parcial");
        aluno1_parcial.setFocusable(false);
        aluno1_parcial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno1_parcialActionPerformed(evt);
            }
        });
        getContentPane().add(aluno1_parcial);
        aluno1_parcial.setBounds(520, 170, 60, 23);

        aluno1_final.setText("Final");
        aluno1_final.setFocusable(false);
        aluno1_final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno1_finalActionPerformed(evt);
            }
        });
        getContentPane().add(aluno1_final);
        aluno1_final.setBounds(520, 190, 60, 23);

        aluno1_ceit.setText("CEICT");
        aluno1_ceit.setFocusable(false);
        aluno1_ceit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno1_ceitActionPerformed(evt);
            }
        });
        getContentPane().add(aluno1_ceit);
        aluno1_ceit.setBounds(520, 210, 60, 23);

        TxtVigencia.setEditable(false);
        TxtVigencia.setBackground(new java.awt.Color(204, 204, 204));
        TxtVigencia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TxtVigencia.setFocusable(false);
        TxtVigencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtVigenciaActionPerformed(evt);
            }
        });
        TxtVigencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtVigenciaKeyTyped(evt);
            }
        });
        getContentPane().add(TxtVigencia);
        TxtVigencia.setBounds(50, 110, 100, 30);

        aluno2_ceit.setText("CEICT");
        aluno2_ceit.setFocusable(false);
        aluno2_ceit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno2_ceitActionPerformed(evt);
            }
        });
        getContentPane().add(aluno2_ceit);
        aluno2_ceit.setBounds(520, 330, 60, 23);

        aluno2_final.setText("Final");
        aluno2_final.setFocusable(false);
        aluno2_final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno2_finalActionPerformed(evt);
            }
        });
        getContentPane().add(aluno2_final);
        aluno2_final.setBounds(520, 310, 60, 23);

        aluno2_parcial.setText("Parcial");
        aluno2_parcial.setFocusable(false);
        aluno2_parcial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluno2_parcialActionPerformed(evt);
            }
        });
        getContentPane().add(aluno2_parcial);
        aluno2_parcial.setBounds(520, 290, 60, 23);

        relatorio2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        relatorio2.setText("Relatórios");
        relatorio2.setFocusable(false);
        getContentPane().add(relatorio2);
        relatorio2.setBounds(520, 270, 130, 30);

        adicionarAluno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        adicionarAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar.png"))); // NOI18N
        adicionarAluno.setText("Adicionar aluno");
        adicionarAluno.setToolTipText("Clique para adicionar um novo aluno ao projeto");
        adicionarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarAlunoActionPerformed(evt);
            }
        });
        getContentPane().add(adicionarAluno);
        adicionarAluno.setBounds(50, 300, 150, 30);

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(40, 380, 470, 10);

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(50, 270, 590, 20);

        cbAlterarTitulo.setText("O título foi alterado");
        cbAlterarTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAlterarTituloActionPerformed(evt);
            }
        });
        getContentPane().add(cbAlterarTitulo);
        cbAlterarTitulo.setBounds(160, 440, 130, 23);
        getContentPane().add(imagemArquivo);
        imagemArquivo.setBounds(0, 540, 50, 40);

        txtFile.setEditable(false);
        getContentPane().add(txtFile);
        txtFile.setBounds(50, 570, 290, 30);

        labelMensagem.setBackground(new java.awt.Color(255, 102, 0));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 690, 20);

        labelArquivis.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelArquivis.setText("Documento da alteração do título");
        getContentPane().add(labelArquivis);
        labelArquivis.setBounds(50, 550, 290, 20);

        alterarProjeto.setBackground(new java.awt.Color(204, 204, 204));
        alterarProjeto.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        alterarProjeto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alterarProjeto.setText("Editar projeto");
        alterarProjeto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        alterarProjeto.setOpaque(true);
        getContentPane().add(alterarProjeto);
        alterarProjeto.setBounds(-10, -10, 700, 80);

        btnArquivo.setText("Escolher Arquivo...");
        btnArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArquivoActionPerformed(evt);
            }
        });
        getContentPane().add(btnArquivo);
        btnArquivo.setBounds(340, 570, 130, 30);

        relatorio3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        relatorio3.setText("Certificado");
        relatorio3.setFocusable(false);
        getContentPane().add(relatorio3);
        relatorio3.setBounds(520, 230, 80, 20);

        certificadoGerado1.setText("Gerado");
        certificadoGerado1.setFocusable(false);
        certificadoGerado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                certificadoGerado1ActionPerformed(evt);
            }
        });
        getContentPane().add(certificadoGerado1);
        certificadoGerado1.setBounds(520, 250, 70, 20);

        certificadoOrientadorEnviado.setText("Enviado");
        certificadoOrientadorEnviado.setFocusable(false);
        certificadoOrientadorEnviado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                certificadoOrientadorEnviadoActionPerformed(evt);
            }
        });
        getContentPane().add(certificadoOrientadorEnviado);
        certificadoOrientadorEnviado.setBounds(420, 400, 70, 30);

        certificadoGerado2.setText("Gerado");
        certificadoGerado2.setFocusable(false);
        certificadoGerado2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                certificadoGerado2ActionPerformed(evt);
            }
        });
        getContentPane().add(certificadoGerado2);
        certificadoGerado2.setBounds(520, 370, 70, 20);

        certificadoOrientadorGerado.setText("Gerado");
        certificadoOrientadorGerado.setFocusable(false);
        certificadoOrientadorGerado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                certificadoOrientadorGeradoActionPerformed(evt);
            }
        });
        getContentPane().add(certificadoOrientadorGerado);
        certificadoOrientadorGerado.setBounds(350, 400, 70, 30);

        certificadoEnviado1.setText("Enviado");
        certificadoEnviado1.setFocusable(false);
        certificadoEnviado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                certificadoEnviado1ActionPerformed(evt);
            }
        });
        getContentPane().add(certificadoEnviado1);
        certificadoEnviado1.setBounds(590, 250, 70, 20);

        certificadoEnviado2.setText("Enviado");
        certificadoEnviado2.setFocusable(false);
        certificadoEnviado2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                certificadoEnviado2ActionPerformed(evt);
            }
        });
        getContentPane().add(certificadoEnviado2);
        certificadoEnviado2.setBounds(590, 370, 70, 20);

        certificado2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        certificado2.setText("Certificado");
        certificado2.setFocusable(false);
        getContentPane().add(certificado2);
        certificado2.setBounds(520, 350, 80, 20);

        relatorio5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        relatorio5.setText("Certificado");
        relatorio5.setFocusable(false);
        getContentPane().add(relatorio5);
        relatorio5.setBounds(350, 380, 68, 30);

        jRbalterarOrientador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        jRbalterarOrientador.setToolTipText("Alterar orientador");
        jRbalterarOrientador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRbalterarOrientadorActionPerformed(evt);
            }
        });
        getContentPane().add(jRbalterarOrientador);
        jRbalterarOrientador.setBounds(0, 400, 50, 33);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bg.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(-10, 70, 690, 550);

        setSize(new java.awt.Dimension(686, 646));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        atualizarProjeto();
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Main.linha_selecionada = jTableMain.getSelectedRow();
        txtAluno1.setText(String.valueOf(jTableMain.getValueAt(Main.linha_selecionada, 2)));
        TxtVigencia.setText(String.valueOf(jTableMain.getValueAt(Main.linha_selecionada, 5)));
        idProjeto = jTableMain.getValueAt(Main.linha_selecionada, 0).toString();
        labelArquivis.setVisible(false);
        txtFile.setVisible(false);
        btnArquivo.setVisible(false);
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 3)).equals("null"))
        {
            txtAluno2.setText("(Nenhum)");
            txtAluno2.setVisible(false);
            JComboBoxModalidadeAluno2.setVisible(false);
            labelModalidade2.setVisible(false);
            relatorio2.setVisible(false);
            aluno2_parcial.setVisible(false);
            aluno2_final.setVisible(false);
            aluno2_ceit.setVisible(false);
            aluno2_parcial_atrasado.setVisible(false);
            aluno2_final_atrasado.setVisible(false);
            aluno2_ceit_atrasado.setVisible(false);
            certificadoGerado2.setVisible(false);
            certificado2.setVisible(false);
            certificadoEnviado2.setVisible(false);
        }else{
            
            txtAluno2.setText(String.valueOf(jTableMain.getValueAt(linha_selecionada, 3)));
            JComboBoxModalidadeAluno2.removeItem("(Nenhum)");
            adicionarAluno.setVisible(true);
            
        }
        
        txtOrientador.setText(String.valueOf(jTableMain.getValueAt(Main.linha_selecionada, 4)));
        txtProjeto.setText(String.valueOf(jTableMain.getValueAt(Main.linha_selecionada, 6)));
        
        if(jTableMain.getValueAt(linha_selecionada, 26).toString().equals("1"))
        {
          certificadoGerado1.setSelected(true);
        }else if(jTableMain.getValueAt(linha_selecionada, 26).toString().equals("0")){
          certificadoGerado1.setSelected(false);;
        }else{
            certificadoGerado1.setSelected(true);
            certificadoGerado1.setEnabled(false);
            certificadoEnviado1.setSelected(true);
        }
        
        if(jTableMain.getValueAt(linha_selecionada, 27).toString().equals("1"))
        {
          certificadoGerado2.setSelected(true);
        }else if(jTableMain.getValueAt(linha_selecionada, 27).toString().equals("0")){
            certificadoGerado2.setSelected(false);
        }else{
            certificadoGerado2.setSelected(true);
            certificadoGerado2.setEnabled(false);
            certificadoEnviado2.setSelected(true);
        }
        
        if(jTableMain.getValueAt(linha_selecionada, 28).toString().equals("1"))
        {
          certificadoOrientadorGerado.setSelected(true);
        }else if(jTableMain.getValueAt(linha_selecionada, 28).toString().equals("0")){
            certificadoOrientadorGerado.setSelected(false);
        }else{
            certificadoOrientadorGerado.setSelected(true);
            certificadoOrientadorGerado.setEnabled(false);
            certificadoOrientadorEnviado.setSelected(true);
        }
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 7)).equals("1"))
        {
          aluno1_parcial.setSelected(true);
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 7)).equals("0"))
        {
          aluno1_parcial.setSelected(false);
        }else{
            aluno1_parcial.setSelected(true);
            aluno1_parcial.setEnabled(false);
            aluno1_parcial_atrasado.setSelected(true);
        }
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 8)).equals("1"))
        {
          aluno1_final.setSelected(true);
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 8)).equals("0")){
          aluno1_final.setSelected(false);
        }
        else{
            aluno1_final.setSelected(true);
            aluno1_final.setEnabled(false);
            aluno1_final_atrasado.setSelected(true);
        }
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 9)).equals("1"))
        {
          aluno1_ceit.setSelected(true);
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 9)).equals("0")){
          aluno1_ceit.setSelected(false);
        }
        else{
            aluno1_ceit.setSelected(true);
            aluno1_ceit.setEnabled(false);
            aluno1_ceit_atrasado.setSelected(true);
        }
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 10)).equals("1"))
        {
          aluno2_parcial.setSelected(true);
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 10)).equals("0")){
          aluno2_parcial.setSelected(false);
        }else{
            aluno2_parcial.setSelected(true);
            aluno2_parcial.setEnabled(false);
            aluno2_parcial_atrasado.setSelected(true);
        }
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 11)).equals("1"))
        {
          aluno2_final.setSelected(true);
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 11)).equals("0")){
          aluno2_final.setSelected(false);
        }
        else{
            aluno2_final.setSelected(true);
            aluno2_final.setEnabled(false);
            aluno2_final_atrasado.setSelected(true);
        }
        
        if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 12)).equals("1"))
        {
          aluno2_ceit.setSelected(true);
        }else if(String.valueOf(jTableMain.getValueAt(linha_selecionada, 12)).equals("0")){
          aluno2_ceit.setSelected(false);
        }
        else{
            aluno2_ceit.setSelected(true);
            aluno2_ceit.setEnabled(false);
            aluno2_ceit_atrasado.setSelected(true);
        }
        
        switch (String.valueOf(jTableMain.getValueAt(linha_selecionada, 13))) {
            case "(Nenhum)":
                JComboBoxModalidadeAluno1.setSelectedItem("(Nenhum)");
                break;
            case "PIBIC/CNPq":
                JComboBoxModalidadeAluno1.setSelectedItem("PIBIC/CNPq");
                break;
            case "PIBITI/CNPq":
                JComboBoxModalidadeAluno1.setSelectedItem("PIBITI/CNPq");
                break;
            case "PIBITI/IF Goiano":
                JComboBoxModalidadeAluno1.setSelectedItem("PIBITI/IF Goiano");
                break;
            case "PIVITI/IF Goiano":
                JComboBoxModalidadeAluno1.setSelectedItem("PIVITI/IF Goiano");
                break;    
            case "PIBIC/IF Goiano":
                JComboBoxModalidadeAluno1.setSelectedItem("PIBIC/IF Goiano");
                break;
            case "PIVIC/IF Goiano":
                JComboBoxModalidadeAluno1.setSelectedItem("PIVIC/IF Goiano");
                break;
            case "PIBIC Jr/CNPq":
                JComboBoxModalidadeAluno1.setSelectedItem("PIBIC Jr/CNPq");
                break;
            case "PIBIC Jr/IF Goiano":
                JComboBoxModalidadeAluno1.setSelectedItem("PIBIC Jr/IF Goiano");
                break;
            case "PIVIC Jr/IF Goiano":
                JComboBoxModalidadeAluno1.setSelectedItem("PIVIC Jr/IF Goiano");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Eta!!!!!!!!!!!!!!!!!");
                break;
        }
                
        switch (String.valueOf(jTableMain.getValueAt(linha_selecionada, 14))) {
            case "(Nenhum)":
                JComboBoxModalidadeAluno2.setSelectedItem("(Nenhum)");
                JComboBoxModalidadeAluno2.setEnabled(false);
                break;
            case "PIBIC/CNPq":
                JComboBoxModalidadeAluno2.setSelectedItem("PIBIC/CNPq");
                break;
            case "PIBITI/CNPq":
                JComboBoxModalidadeAluno2.setSelectedItem("PIBITI/CNPq");
                break;
            case "PIBITI/IF Goiano":
                JComboBoxModalidadeAluno2.setSelectedItem("PIBITI/IF Goiano");
                break;
            case "PIVITI/IF Goiano":
                JComboBoxModalidadeAluno2.setSelectedItem("PIVITI/IF Goiano");
                break;        
            case "PIBIC/IF Goiano":
                JComboBoxModalidadeAluno2.setSelectedItem("PIBIC/IF Goiano");
                break;
            case "PIVIC/IF Goiano":
                JComboBoxModalidadeAluno2.setSelectedItem("PIVIC/IF Goiano");
                break;
            case "PIBIC Jr/CNPq":
                JComboBoxModalidadeAluno2.setSelectedItem("PIBIC Jr/CNPq");
                break;
            case "PIBIC Jr/IF Goiano":
                JComboBoxModalidadeAluno2.setSelectedItem("PIBIC Jr/IF Goiano");
                break;
            case "PIVIC Jr/IF Goiano":
                JComboBoxModalidadeAluno2.setSelectedItem("PIVIC Jr/IF Goiano");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Eta!!!!!!!!!!!!!!!!!");
                break; 
        }
        
           
    }//GEN-LAST:event_formWindowOpened

    private void TxtVigenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtVigenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtVigenciaActionPerformed

    private void TxtVigenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtVigenciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtVigenciaKeyTyped

    private void aluno1_ceitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno1_ceitActionPerformed
            labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno1_ceitActionPerformed

    private void aluno1_finalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno1_finalActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno1_finalActionPerformed

    private void aluno2_ceitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno2_ceitActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno2_ceitActionPerformed

    private void aluno2_finalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno2_finalActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno2_finalActionPerformed

    private void cbAlterarTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlterarTituloActionPerformed
            verificaTitulo();
    }//GEN-LAST:event_cbAlterarTituloActionPerformed

    private void txtProjetoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProjetoFocusGained
        cbAlterarTitulo.setSelected(true);
        btnArquivo.setVisible(true);
        labelArquivis.setVisible(true);
        imagemArquivo.setVisible(true);
        txtFile.setVisible(true);
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtProjetoFocusGained

    private void aluno1_final_atrasadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno1_final_atrasadoActionPerformed
        if(aluno1_final_atrasado.isSelected())    {
           aluno1_final.setSelected(true); 
           aluno1_final.setEnabled(false); 
        }else
        {
            aluno1_final.setSelected(false);
            aluno1_final.setEnabled(true);
        }  
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno1_final_atrasadoActionPerformed

    private void aluno2_ceit_atrasadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno2_ceit_atrasadoActionPerformed
        if(aluno2_ceit_atrasado.isSelected())
        {
           aluno2_ceit.setSelected(true); 
           aluno2_ceit.setEnabled(false); 
        }else
        {
            aluno2_ceit.setSelected(false);
            aluno2_ceit.setEnabled(true);
        }  
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno2_ceit_atrasadoActionPerformed

    private void aluno2_final_atrasadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno2_final_atrasadoActionPerformed
        if(aluno2_final_atrasado.isSelected())    {
           aluno2_final.setSelected(true); 
           aluno2_final.setEnabled(false); 
        }else
        {
            aluno2_final.setSelected(false);
            aluno2_final.setEnabled(true);
        }  
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno2_final_atrasadoActionPerformed

    private void aluno1_parcial_atrasadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno1_parcial_atrasadoActionPerformed
        if(aluno1_parcial_atrasado.isSelected())
        {
           aluno1_parcial.setSelected(true); 
           aluno1_parcial.setEnabled(false); 
        }else
        {
            aluno1_parcial.setSelected(false);
            aluno1_parcial.setEnabled(true);
        }        
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno1_parcial_atrasadoActionPerformed

    private void aluno1_ceit_atrasadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno1_ceit_atrasadoActionPerformed
        if(aluno1_ceit_atrasado.isSelected())
        {
           aluno1_ceit.setSelected(true); 
           aluno1_ceit.setEnabled(false); 
        }else
        {
            aluno1_ceit.setSelected(false);
            aluno1_ceit.setEnabled(true);
        }  
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno1_ceit_atrasadoActionPerformed

    private void aluno2_parcial_atrasadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno2_parcial_atrasadoActionPerformed
        if(aluno2_parcial_atrasado.isSelected())    {
           aluno2_parcial.setSelected(true); 
           aluno2_parcial.setEnabled(false); 
        }else
        {
            aluno2_parcial.setSelected(false);
            aluno2_parcial.setEnabled(true);
        }  
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno2_parcial_atrasadoActionPerformed

    private void aluno1_parcialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno1_parcialActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno1_parcialActionPerformed

    private void adicionarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarAlunoActionPerformed
        JFrame mainFrame = new JFrame();
        NovoAluno nAluno = new NovoAluno(mainFrame, true);
        nAluno.setLocationRelativeTo(mainFrame);
        nAluno.setVisible(true);        
        dispose();
    }//GEN-LAST:event_adicionarAlunoActionPerformed

    private void btnArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArquivoActionPerformed
        labelMensagem.setVisible(false);
        
        URL pdfIcon;

        abrir.setDialogTitle("Procurar arquivo...");
        abrir.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter ff = new FileNameExtensionFilter("Arquivo","pdf");

        abrir.setFileFilter(ff);
        int retorno = abrir.showOpenDialog(null);

        if (retorno == JFileChooser.APPROVE_OPTION)
        {
            caminho = abrir.getSelectedFile().getAbsolutePath();
            txtFile.setText(caminho);
            try{
                pdfIcon = getClass().getResource("/imagens/iconPDF.png");
                imagemArquivo.setIcon(new ImageIcon(pdfIcon));
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_btnArquivoActionPerformed

    private void aluno2_parcialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluno2_parcialActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_aluno2_parcialActionPerformed

    private void certificadoGerado2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_certificadoGerado2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_certificadoGerado2ActionPerformed

    private void certificadoGerado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_certificadoGerado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_certificadoGerado1ActionPerformed

    private void certificadoOrientadorGeradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_certificadoOrientadorGeradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_certificadoOrientadorGeradoActionPerformed

    private void JComboBoxModalidadeAluno2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboBoxModalidadeAluno2ActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_JComboBoxModalidadeAluno2ActionPerformed

    private void JComboBoxModalidadeAluno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboBoxModalidadeAluno1ActionPerformed
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_JComboBoxModalidadeAluno1ActionPerformed

    private void JComboBoxModalidadeAluno1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboBoxModalidadeAluno1MouseClicked
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_JComboBoxModalidadeAluno1MouseClicked

    private void JComboBoxModalidadeAluno2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboBoxModalidadeAluno2MouseClicked
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_JComboBoxModalidadeAluno2MouseClicked

    private void certificadoEnviado2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_certificadoEnviado2ActionPerformed
        if(certificadoEnviado2.isSelected())
        {
           certificadoGerado2.setSelected(true); 
           certificadoGerado2.setEnabled(false); 
        }else
        {
            certificadoGerado2.setSelected(false);
            certificadoGerado2.setEnabled(true);
        }  
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_certificadoEnviado2ActionPerformed

    private void certificadoEnviado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_certificadoEnviado1ActionPerformed
        if(certificadoEnviado1.isSelected())
        {
           certificadoGerado1.setSelected(true); 
           certificadoGerado1.setEnabled(false); 
        }else
        {
            certificadoGerado1.setSelected(false);
            certificadoGerado1.setEnabled(true);
        }  
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_certificadoEnviado1ActionPerformed

    private void certificadoOrientadorEnviadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_certificadoOrientadorEnviadoActionPerformed
        if(certificadoOrientadorEnviado.isSelected())
        {
           certificadoOrientadorGerado.setSelected(true); 
           certificadoOrientadorGerado.setEnabled(false); 
        }else
        {
            certificadoOrientadorGerado.setSelected(false);
            certificadoOrientadorGerado.setEnabled(true);
        }  
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_certificadoOrientadorEnviadoActionPerformed

    private void jRbalterarOrientadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRbalterarOrientadorActionPerformed
        JFrame mainFrame = new JFrame();
        SubstituirOrientador nOrientador = new SubstituirOrientador(mainFrame, true);
        nOrientador.setLocationRelativeTo(mainFrame);
        nOrientador.setVisible(true);
        
        dispose();     
    }//GEN-LAST:event_jRbalterarOrientadorActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AlterarProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AlterarProjeto dialog = new AlterarProjeto(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JComboBoxModalidadeAluno1;
    private javax.swing.JComboBox<String> JComboBoxModalidadeAluno2;
    public static javax.swing.JTextField TxtVigencia;
    private javax.swing.JButton adicionarAluno;
    private javax.swing.JLabel alterarProjeto;
    private javax.swing.JCheckBox aluno1_ceit;
    private javax.swing.JCheckBox aluno1_ceit_atrasado;
    private javax.swing.JCheckBox aluno1_final;
    private javax.swing.JCheckBox aluno1_final_atrasado;
    private javax.swing.JCheckBox aluno1_parcial;
    private javax.swing.JCheckBox aluno1_parcial_atrasado;
    private javax.swing.JCheckBox aluno2_ceit;
    private javax.swing.JCheckBox aluno2_ceit_atrasado;
    private javax.swing.JCheckBox aluno2_final;
    private javax.swing.JCheckBox aluno2_final_atrasado;
    private javax.swing.JCheckBox aluno2_parcial;
    private javax.swing.JCheckBox aluno2_parcial_atrasado;
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnArquivo;
    private javax.swing.JCheckBox cbAlterarTitulo;
    private javax.swing.JLabel certificado2;
    private javax.swing.JCheckBox certificadoEnviado1;
    private javax.swing.JCheckBox certificadoEnviado2;
    private javax.swing.JCheckBox certificadoGerado1;
    private javax.swing.JCheckBox certificadoGerado2;
    private javax.swing.JCheckBox certificadoOrientadorEnviado;
    private javax.swing.JCheckBox certificadoOrientadorGerado;
    private javax.swing.JLabel imagemArquivo;
    private javax.swing.JLabel img;
    private javax.swing.JButton jRbalterarOrientador;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JLabel labelAluno2;
    private javax.swing.JLabel labelArquivis;
    public static javax.swing.JLabel labelMensagem;
    private javax.swing.JLabel labelModalidade2;
    private javax.swing.JLabel nome;
    private javax.swing.JLabel nome1;
    private javax.swing.JLabel nome2;
    private javax.swing.JLabel nome3;
    private javax.swing.JLabel nome6;
    private javax.swing.JLabel relatorio1;
    private javax.swing.JLabel relatorio2;
    private javax.swing.JLabel relatorio3;
    private javax.swing.JLabel relatorio5;
    private javax.swing.JTextField txtAluno1;
    private javax.swing.JTextField txtAluno2;
    private javax.swing.JTextField txtFile;
    private javax.swing.JTextField txtOrientador;
    private javax.swing.JTextArea txtProjeto;
    // End of variables declaration//GEN-END:variables
}
