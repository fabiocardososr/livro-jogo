package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_5 extends TelaSecoesBasica {
    private JLabelOpcoesTelaSecao botaoOpcao1;
    private JLabelOpcoesTelaSecao botaoOpcao2;


    public TelaSecao_5(Secao secao) {
        super(secao, CarregarTelas.getReferenciaTelaPrincipal());
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        //opcao1(secao);
        //opcao2(secao);
    }
}
