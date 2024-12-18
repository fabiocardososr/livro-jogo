package livro.jogo.criarLivro.entidades;

import java.util.HashMap;

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
    private String imagemCapa; //Endereço de onde se encontra a imagem da capa, se existir.
    private String imagemMapa; //Endereço de onde se encontra a imagem do mapa, se existir.
    private final HashMap<Integer, Secao> mapSecao = new HashMap<Integer, Secao>();

    public HashMap<Integer, Secao> getMapSecao() {
        return mapSecao;
    }

    public String getImagemMapa() {
        return imagemMapa;
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

    public String getImagemCapa() {
        return imagemCapa;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "idLivro=" + idLivro +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", secaoInicial=" + secaoInicial +
                ", regraCalcularIndicesIniciais='" + regraCalcularIndicesIniciais + '\'' +
                ", regraBatalha='" + regraBatalha + '\'' +
                ", regraUsoSorte='" + regraUsoSorte + '\'' +
                ", regraReposicaoIndices='" + regraReposicaoIndices + '\'' +
                ", regraEquipamentos='" + regraEquipamentos + '\'' +
                ", dicas='" + dicas + '\'' +
                ", historia='" + historia + '\'' +
                ", imagemCapa='" + imagemCapa + '\'' +
                ", imagemMapa='" + imagemMapa + '\'' +
                '}';
    }
}
