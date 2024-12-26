package livro.jogo.executaveis;

import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Personagem;
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
        Personagem personagem = new Personagem("Fábio",1,10,20,10,
                null,null,2);
        TelaSecoesBasica telaSecoesBasica = new TelaSecoesBasica( ManipularDadosLivro.getMapSecao().get(2),personagem );
        telaSecoesBasica.setVisible(true);

        //Não copiar quando finalizar
        telaSecoesBasica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
