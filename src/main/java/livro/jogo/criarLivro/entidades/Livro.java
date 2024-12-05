package livro.jogo.criarLivro.entidades;

public class Livro {
    private int idLivro;
    private String nome;
    private String descricao;
    private int secaoInicial; //É a seção inicial
    private String regraCalcularIndicesIniciais;
    private String regraBatalha;
    private String regraUsoSorte;
    private String regraReposicaoIndices ;
    private String regraEquipamentos;
    private String dicas;
    private String historia;
    private String imagem; //Endereço de onde se encontra a imagem, se existir.

    public Livro() {
    }

    public Livro(Integer idLivro, String nome, String descricao, Integer secaoInicial,
                 String regraCalcularIndicesIniciais, String regraBatalha,
                 String regraUsoSorte, String regraReposicaoInidices, String regraEquipamentos,
                 String dicas, String historia, String caminhoImagem) {
        this.idLivro = idLivro;
        this.nome = nome;
        this.descricao = descricao;
        this.regraCalcularIndicesIniciais = regraCalcularIndicesIniciais;
        this.regraBatalha = regraBatalha;
        this.regraUsoSorte = regraUsoSorte;
        this.regraReposicaoIndices = regraReposicaoInidices;
        this.regraEquipamentos = regraEquipamentos;
        this.dicas = dicas;
        this.historia = historia;
        this.imagem = caminhoImagem;
        this.secaoInicial = secaoInicial;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getSecaoInicial() {
        return secaoInicial;
    }

    public String getRegraCalcularIndicesIniciais() {
        return regraCalcularIndicesIniciais;
    }

    public String getRegraBatalha() {
        return regraBatalha;
    }

    public String getRegraUsoSorte() {
        return regraUsoSorte;
    }

    public String getRegraReposicaoIndices() {
        return regraReposicaoIndices;
    }

    public String getRegraEquipamentos() {
        return regraEquipamentos;
    }

    public String getDicas() {
        return dicas;
    }

    public String getHistoria() {
        return historia;
    }

    public String getImagem() {
        return imagem;
    }
}
