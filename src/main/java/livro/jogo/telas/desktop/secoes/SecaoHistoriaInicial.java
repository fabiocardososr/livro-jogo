package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

import javax.swing.*;

public class SecaoHistoriaInicial extends TelaSecoesBasica {
    public SecaoHistoriaInicial(Secao secao, Personagem personagem, JFrame referenciaTelaPrincipal) {
        super(secao, personagem, referenciaTelaPrincipal);
    }
}