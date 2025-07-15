package livro.jogo.entidades;

import livro.jogo.telas.desktop.secoes.InfoSecoes;
import livro.jogo.utils.DadosLivroCarregado;

import java.io.Serializable;

public class SaveJogo implements Serializable {
    private final Personagem personagem;
    private final Secao secao;
    private final InfoSecoes infoSecoes;

    public SaveJogo(Personagem personagem, Secao secao) {
        this.personagem = personagem;
        this.secao = secao;

        //Guardar informações que precisam ser vistas em outras seções
        this.infoSecoes = DadosLivroCarregado.getInfoSecoes();
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public Secao getSecao() {
        return secao;
    }

    public InfoSecoes getInfoSecoes() {
        return infoSecoes;
    }
}
