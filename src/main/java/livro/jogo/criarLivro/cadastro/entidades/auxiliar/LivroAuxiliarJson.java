package livro.jogo.criarLivro.cadastro.entidades.auxiliar;

//Classe usada para receber os dados via JSON do arquivo .liv
public class LivroAuxiliarJson {
    private final int idLivro;
    private final int secaoInicial;
    private final String descricaoLivro;
    private final String regraCalculoIndicesIniciais;
    private final String regraBatalha;
    private final String regraUsoSorte;
    private final String regraReposicaoIndice;
    private final String regraEquipamentos;
    private final String dica;
    private final String historia;

    public LivroAuxiliarJson(int idLivro, int secaoInicial, String descricaoLivro, String regraCalculoIndicesIniciais,
                             String regraBatalha, String regraUsoSorte, String regraReposicaoIndice,
                             String regraEquipamentos, String dica, String historia) {
        this.idLivro = idLivro;
        this.secaoInicial = secaoInicial;
        this.descricaoLivro = descricaoLivro;
        this.regraCalculoIndicesIniciais = regraCalculoIndicesIniciais;
        this.regraBatalha = regraBatalha;
        this.regraUsoSorte = regraUsoSorte;
        this.regraReposicaoIndice = regraReposicaoIndice;
        this.regraEquipamentos = regraEquipamentos;
        this.dica = dica;
        this.historia = historia;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public int getSecaoInicial() {
        return secaoInicial;
    }

    public String getDescricaoLivro() {
        return descricaoLivro;
    }

    public String getRegraCalculoIndicesIniciais() {
        return regraCalculoIndicesIniciais;
    }

    public String getRegraBatalha() {
        return regraBatalha;
    }

    public String getRegraUsoSorte() {
        return regraUsoSorte;
    }

    public String getRegraReposicaoIndice() {
        return regraReposicaoIndice;
    }

    public String getRegraEquipamentos() {
        return regraEquipamentos;
    }

    public String getDica() {
        return dica;
    }

    public String getHistoria() {
        return historia;
    }

    @Override
    public String toString() {
        return "LivroAuxiliarJson{" +
                "idLivro=" + idLivro +
                ", secaoInicial=" + secaoInicial +
                ", descricaoLivro='" + descricaoLivro + '\'' +
                ", regraCalculoIndicesIniciais='" + regraCalculoIndicesIniciais + '\'' +
                ", regraBatalha='" + regraBatalha + '\'' +
                ", regraUsoSorte='" + regraUsoSorte + '\'' +
                ", regraReposicaoIndice='" + regraReposicaoIndice + '\'' +
                ", regraEquipamentos='" + regraEquipamentos + '\'' +
                ", dica='" + dica + '\'' +
                ", historia='" + historia + '\'' +
                '}';
    }
}
