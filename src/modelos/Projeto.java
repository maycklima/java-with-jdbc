package modelos;
 
/**
 *
 * @author Mayck Lima
 */
public class Projeto 
{
    private int idProjeto;
    private String nomeProjeto;
    private String vigenciaProjeto;
    private int aluno1;
    private int aluno2;
    private static String aluno1nome;
    private static String aluno2nome;
    private int alunoAntigo;
    private int alunoNovo;
    private String alunoDesistente;
    private String data_desistencia;
    private String data_substituicao1;
    private String data_substituicao2;
    private int orientador;
    private String nomeOrientador;
    private String modalidadeAluno1;
    private String modalidadeAluno2;
    private int rParcial1;
    private int rFinal1;
    private int ceit1;
    private int rParcial2;
    private int rFinal2;
    private int ceit2;
    private boolean aluno1_substituto;
    private boolean aluno2_substituto;
    private static String data_cadastro_projeto;
    private static String data_cadastro_aluno1;
    private static String data_cadastro_aluno2;
    private boolean alteracao_titulo;
    private int certificado1;
    private int certificado2;
    private int certificadoOrientador;
    
    public Projeto() {
        
    }  

    public int getCertificado1() {
        return certificado1;
    }

    public void setCertificado1(int certificado1) {
        this.certificado1 = certificado1;
    }

    public int getCertificado2() {
        return certificado2;
    }

    public void setCertificado2(int certificado2) {
        this.certificado2 = certificado2;
    }

    public int getCertificadoOrientador() {
        return certificadoOrientador;
    }

    public void setCertificadoOrientador(int certificadoOrientador) {
        this.certificadoOrientador = certificadoOrientador;
    }
    
    public boolean isAlteracao_titulo() {
        return alteracao_titulo;
    }

    public void setAlteracao_titulo(boolean alteracao_titulo) {
        this.alteracao_titulo = alteracao_titulo;
    }
    
    

    public static String getAluno1nome() {
        return aluno1nome;
    }

    public static void setAluno1nome(String aluno1nome) {
        Projeto.aluno1nome = aluno1nome;
    }

    public static String getAluno2nome() {
        return aluno2nome;
    }

    public static void setAluno2nome(String aluno2nome) {
        Projeto.aluno2nome = aluno2nome;
    } 
    
    public static String getData_cadastro_projeto() {
        return data_cadastro_projeto;
    }

    public void setData_cadastro_projeto(String data_cadastro_projeto) {
        Projeto.data_cadastro_projeto = data_cadastro_projeto;
    }
    
    
    public static String getData_cadastro_aluno1() {
        return data_cadastro_aluno1;
    }

    public void setData_cadastro_aluno1(String data_cadastro) {
        Projeto.data_cadastro_aluno1 = data_cadastro;
    }

    public String getData_substituicao2() {
        return data_substituicao2;
    }

    public void setData_substituicao2(String data_substituicao2) {
        this.data_substituicao2 = data_substituicao2;
    }
    

    public static String getData_cadastro_aluno2() {
        return data_cadastro_aluno2;
    }

    public void setData_cadastro_aluno2(String data_cadastro_aluno2) {
        Projeto.data_cadastro_aluno2 = data_cadastro_aluno2;
    }
    
    
    
    public boolean isAluno1_substituto() {
        return aluno1_substituto;
    }

    public void setAluno1_substituto(boolean aluno1_substituto) {
        this.aluno1_substituto = aluno1_substituto;
    }

    public boolean isAluno2_substituto() {
        return aluno2_substituto;
    }

    public void setAluno2_substituto(boolean aluno2_substituto) {
        this.aluno2_substituto = aluno2_substituto;
    }

    
    
    
    public String getData_substituicao1() {
        return data_substituicao1;
    }

    public void setData_substituicao1(String data_substituicao1) {
        this.data_substituicao1 = data_substituicao1;
    }
    private String modalidadeDesistente;

    public String getData_desistencia() {
        return data_desistencia;
    }

    public void setData_desistencia(String data_desistencia) {
        this.data_desistencia = data_desistencia;
    }
    
    public String getModalidadeAluno1() {
        return modalidadeAluno1;
    }

    public void setModalidadeAluno1(String modalidadeAluno1) {
        this.modalidadeAluno1 = modalidadeAluno1;
    }

    public String getModalidadeAluno2() {
        return modalidadeAluno2;
    }

    public void setModalidadeAluno2(String modalidadeAluno2) {
        this.modalidadeAluno2 = modalidadeAluno2;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getVigenciaProjeto() {
        return vigenciaProjeto;
    }

    public void setVigenciaProjeto(String vigenciaProjeto) {
        this.vigenciaProjeto = vigenciaProjeto;
    }

    public  int getAluno1() {
        return aluno1;
    }

    public void setAluno1(int aluno1) {
        this.aluno1 = aluno1;
    }

    public int getAluno2() {
        return aluno2;
    }

    public void setAluno2(int aluno2) {
        this.aluno2 = aluno2;
    }
    
     public int getAlunoAntigo() {
        return alunoAntigo;
    }

    public void setAlunoAntigo(int alunoAntigo) {
        this.alunoAntigo = alunoAntigo;
    }

    public int getAlunoNovo() {
        return alunoNovo;
    }

    public void setAlunoNovo(int alunoNovo) {
        this.alunoNovo = alunoNovo;
    }

    public int getOrientador() {
        return orientador;
    }

    public void setOrientador(int orientador) {
        this.orientador = orientador;
    }

    public int getrParcial1() {
        return rParcial1;
    }

    public void setrParcial1(int rParcial1) {
        this.rParcial1 = rParcial1;
    }

    public int getrFinal1() {
        return rFinal1;
    }

    public void setrFinal1(int rFinal1) {
        this.rFinal1 = rFinal1;
    }

    public int getCeit1() {
        return ceit1;
    }

    public void setCeit1(int ceit1) {
        this.ceit1 = ceit1;
    }

    public int getrParcial2() {
        return rParcial2;
    }

    public void setrParcial2(int rParcial2) {
        this.rParcial2 = rParcial2;
    }

    public int getrFinal2() {
        return rFinal2;
    }

    public void setrFinal2(int rFinal2) {
        this.rFinal2 = rFinal2;
    }

    public int getCeit2() {
        return ceit2;
    }

    public void setCeit2(int ceit2) {
        this.ceit2 = ceit2;
    }
    
    
    
    public String getAlunoDesistente() {
        return alunoDesistente;
    }

    public void setAlunoDesistente(String alunoDesistente) {
        this.alunoDesistente = alunoDesistente;
    }

    public String getNomeOrientador() {
        return nomeOrientador;
    }

    public void setNomeOrientador(String nomeOrientador) {
        this.nomeOrientador = nomeOrientador;
    }

    public String getModalidadeDesistente() {
        return modalidadeDesistente;
    }

    public void setModalidadeDesistente(String modalidadeDesistente) {
        this.modalidadeDesistente = modalidadeDesistente;
    }
    
    
}
