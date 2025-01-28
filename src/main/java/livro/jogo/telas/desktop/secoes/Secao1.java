package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

import javax.swing.*;

public class Secao1 extends TelaSecoesBasica {

    public Secao1(Secao secao, JFrame referenciaTelaPrincipal) {
        super(secao, referenciaTelaPrincipal);

        textoHistoria.setText( secao.getTexto() );

        //para posicionar a barra de rolagem no início.
        textoHistoria.setCaretPosition(0);

        carregarComponentesEspecificos();
    }

    @Override
    protected void carregarComponentesEspecificos() {

    }

}
