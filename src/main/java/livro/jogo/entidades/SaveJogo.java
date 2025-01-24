package livro.jogo.entidades;

import java.io.Serializable;

public class SaveJogo implements Serializable {
    private final Personagem personagem;
    private final Secao secao;

    public SaveJogo(Personagem personagem, Secao secao) {
        this.personagem = personagem;
        this.secao = secao;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public Secao getSecao() {
        return secao;
    }
}
