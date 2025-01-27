package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.interfaces.SecaoInterface;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

import javax.swing.*;

public class Secao1 extends TelaSecoesBasica implements SecaoInterface {

    public Secao1(Secao secao, JFrame referenciaTelaPrincipal) {
        super(secao, referenciaTelaPrincipal);

        textoHistoria.setText( secao.getTexto() );
        carregarComponentesEspecificos();
    }


    @Override
    public void carregarComponentesEspecificos() {


        //Necessário chamar depois porque o botão acima ficaria por baixo e com isso não aparecendo.
        carregaPainelInferior();
    }
}
