package livro.jogo.criarLivro.cadastro.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {
    @Id //Infoma que o atributo "id" é a chave primaria que vem da tabela
    private int idLivro;
    private int secaoInicial; //É a seção inicial
    private String nome;
    private String descricao;
    private String regraCalcularIndicesIniciais;
    private String regraBatalha;
    private String regraUsoSorte;
    private String regraReposicaoIndices ;
    private String regraEquipamentos;
    private String dicas;
    private String historia;
    private String imagem; //Endereço de onde se encontra a imagem, se existir.

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

    public int getSecaoInicial() {
        return secaoInicial;
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

    public String getRegraCalcularIndicesIniciais() {
        return regraCalcularIndicesIniciais;
    }

    public String getRegraLuta() {
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

    @Override
    public String toString() {
        return "Livro{" +
                "idLivro=" + idLivro +
                ", secaoInicial=" + secaoInicial +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", regraCalcularIndicesIniciais='" + regraCalcularIndicesIniciais + '\'' +
                ", regraBatalha='" + regraBatalha + '\'' +
                ", regraUsoSorte='" + regraUsoSorte + '\'' +
                ", regraReposicaoIndices='" + regraReposicaoIndices + '\'' +
                ", regraEquipamentos='" + regraEquipamentos + '\'' +
                ", dicas='" + dicas + '\'' +
                ", historia='" + historia + '\'' +
                ", imagem='" + imagem + '\'' +
                '}';
    }
}
