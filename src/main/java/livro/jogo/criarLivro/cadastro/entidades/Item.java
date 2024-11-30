package livro.jogo.criarLivro.cadastro.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idItem;

    @ManyToOne
    @JoinColumn(name = "idTipoEfeito")
    private TipoEfeito tipoEfeito;                  //indica onde o artefato vai atuar se na HABILIDADE, ENERGIA OU SORTE

    private final String descricao;
    private final int valorCusto;                         //Como se trata de moedas de ouro pode ficar com tipo inteiro
    private final int modificador;                        //Altera o valor dos efeitos. Seja ele bom ou ruim, temporário ou permanente (ver os flags). Portanto pode ser um valor negativo também (se efeito ruim)
    private final int quantidadeUsoTemporario;            //Este campo é setado caso o flgUsoTemporario = "S". Pois este campo indica quantas vezes o efeito será ativado, já que é temporário.
    private final String flgUsoTemporario;                //Diz se o item tem efeito temporário, seja uma arma ou poção. Exemplo somar mais 1 nas rolagem de dados por duas batalhas.
    private final String flgUsoUnico;                    //Indica que uma vez usado tem que ser descartado
    private final String flgUsoPermanente;               //Indica se o efeito do item afeta permanentemente o personagem enquanto usar o item. Exemplo, um item que causa perda de -2 dois pontos nas rolagem de ataques.
    private final String flgAfetaRolagemDados;           //Indica que o efeito deste item afeta a rolagem de dados tanto para o bem(flgEfeitoPositivo) ou para o mal(flgEfeitoNegativo) decrementando/somando o que consta no campo modificador.
    private final String flgAfetaNivelAtualPersonagem;   //Diz se o efeito do item afeta os níveis atuais de HABILIDADE, ENERGIA ou SORTE. A depender do campo "idEfeito".
    private final String flgAfetaNivelMaxPersonagem;      //Diz se o efeito do item afeta os níveis MAX (aumentando o valor que foi definido na rolagem de dados na criação do personagem) de HABILIDADE, ENERGIA ou SORTE. A depender do campo "idEfeito".

    public Item( TipoEfeito tipoEfeito, String descricao, int valorCusto, int modificador, int quantidadeUsoTemporario,
                 String flgUsoTemporario, String flgUsoUnico, String flgUsoPermanente, String flgAfetaRolagemDados,
                 String flgAfetaNivelAtualPersonagem, String flgAfetaNivelMaxPersonagem) {
        this.tipoEfeito = tipoEfeito;
        this.descricao = descricao;
        this.valorCusto = valorCusto;
        this.modificador = modificador;
        this.quantidadeUsoTemporario = quantidadeUsoTemporario;
        this.flgUsoTemporario = flgUsoTemporario;
        this.flgUsoUnico = flgUsoUnico;
        this.flgUsoPermanente = flgUsoPermanente;
        this.flgAfetaRolagemDados = flgAfetaRolagemDados;
        this.flgAfetaNivelAtualPersonagem = flgAfetaNivelAtualPersonagem;
        this.flgAfetaNivelMaxPersonagem = flgAfetaNivelMaxPersonagem;
    }

    public int getIdItem() {
        return idItem;
    }

    public TipoEfeito getTipoEfeito() {
        return tipoEfeito;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValorCusto() {
        return valorCusto;
    }

    public int getModificador() {
        return modificador;
    }

    public int getQuantidadeUsoTemporario() {
        return quantidadeUsoTemporario;
    }

    public String getFlgUsoTemporario() {
        return flgUsoTemporario;
    }

    public String getFlgUsoUnico() {
        return flgUsoUnico;
    }

    public String getFlgUsoPermanente() {
        return flgUsoPermanente;
    }

    public String getFlgAfetaRolagemDados() {
        return flgAfetaRolagemDados;
    }

    public String getFlgAfetaNivelAtualPersonagem() {
        return flgAfetaNivelAtualPersonagem;
    }

    public String getFlgAfetaNivelMaxPersonagem() {
        return flgAfetaNivelMaxPersonagem;
    }
}
