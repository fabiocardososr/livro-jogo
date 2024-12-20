package livro.jogo.entidades;

public class Item {
    private int idItem;
    private TipoEfeito tipoEfeito;                  //indica onde o artefato vai atuar se na HABILIDADE, ENERGIA OU SORTE
    private  String descricao;
    private  int valorCusto;                         //Como se trata de moedas de ouro pode ficar com tipo inteiro
    private  int modificador;                        //Altera o valor dos efeitos. Seja ele bom ou ruim, temporário ou permanente (ver os flags). Portanto pode ser um valor negativo também (se efeito ruim)
    private  int quantidadeUsoTemporario;            //Este campo é setado caso o flgUsoTemporario = "S". Pois este campo indica quantas vezes o efeito será ativado, já que é temporário.
    private  String flgUsoTemporario;                //Diz se o item tem efeito temporário, seja uma arma ou poção. Exemplo somar mais 1 nas rolagem de dados por duas batalhas.
    private  String flgUsoUnico;                    //Indica que uma vez usado tem que ser descartado
    private  String flgUsoPermanente;               //Indica se o efeito do item afeta permanentemente o personagem enquanto usar o item. Exemplo, um item que causa perda de -2 dois pontos nas rolagem de ataques.
    private  String flgAfetaRolagemDados;           //Indica que o efeito deste item afeta a rolagem de dados tanto para o bem(flgEfeitoPositivo) ou para o mal(flgEfeitoNegativo) decrementando/somando o que consta no campo modificador.
    private  String flgAfetaNivelAtualPersonagem;   //Diz se o efeito do item afeta os níveis atuais de HABILIDADE, ENERGIA ou SORTE. A depender do campo "idEfeito".
    private  String flgAfetaNivelMaxPersonagem;      //Diz se o efeito do item afeta os níveis MAX (aumentando o valor que foi definido na rolagem de dados na criação do personagem) de HABILIDADE, ENERGIA ou SORTE. A depender do campo "idEfeito".

    public Item() {
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

    @Override
    public String toString() {
        return "Item{" +
                "idItem=" + idItem +
                ", tipoEfeito=" + tipoEfeito +
                ", descricao='" + descricao + '\'' +
                ", valorCusto=" + valorCusto +
                ", modificador=" + modificador +
                ", quantidadeUsoTemporario=" + quantidadeUsoTemporario +
                ", flgUsoTemporario='" + flgUsoTemporario + '\'' +
                ", flgUsoUnico='" + flgUsoUnico + '\'' +
                ", flgUsoPermanente='" + flgUsoPermanente + '\'' +
                ", flgAfetaRolagemDados='" + flgAfetaRolagemDados + '\'' +
                ", flgAfetaNivelAtualPersonagem='" + flgAfetaNivelAtualPersonagem + '\'' +
                ", flgAfetaNivelMaxPersonagem='" + flgAfetaNivelMaxPersonagem + '\'' +
                '}';
    }
}
