package livro.jogo.executaveis;

import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.TelaPrincipal;
import livro.jogo.telas.desktop.TelaRegra;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.ManipularDadosLivro;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;

public class Testes {
    public static void main(String[] args) {
        //Carrega o livro
        CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

        TelaSecoesBasica telaSecoesBasica = new TelaSecoesBasica( ManipularDadosLivro.getMapSecao().get(2) );
        telaSecoesBasica.setVisible(true);

        //Não copiar quando finalizar
        telaSecoesBasica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
