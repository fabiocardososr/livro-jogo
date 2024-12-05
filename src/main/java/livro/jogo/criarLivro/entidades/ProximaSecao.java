package livro.jogo.criarLivro.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProximaSecao {
    private int codProximaSecao;
    private String textoOpcao; //Aqui descreve a opção para ir a outra página
    private int secaoAtual;  //Refere-se a seção em que estas opções estão disponíveis

    public ProximaSecao() {
    }

    public int getCodProximaSecao() {
        return codProximaSecao;
    }

    public String getTextoOpcao() {
        return textoOpcao;
    }

    public int getSecaoAtual() {
        return secaoAtual;
    }

    @Override
    public String toString() {
        return "ProximaSecao{" +
                "codProximaSecao=" + codProximaSecao +
                ", textoOpcao='" + textoOpcao + '\'' +
                ", secaoAtual=" + secaoAtual +
                '}';
    }
}
