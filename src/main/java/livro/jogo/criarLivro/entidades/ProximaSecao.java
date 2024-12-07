package livro.jogo.criarLivro.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProximaSecao {
    private int codProximaSecao;
    private String textoOpcao; //Aqui descreve a opção para ir a outra página

    public ProximaSecao() {
    }

    public int getCodProximaSecao() {
        return codProximaSecao;
    }

    public String getTextoOpcao() {
        return textoOpcao;
    }


    @Override
    public String toString() {
        return "\nProximaSecao{" +
                "codProximaSecao=" + codProximaSecao +
                ", textoOpcao='" + textoOpcao +'}';
    }
}
