package livro.jogo.executaveis;

import com.fasterxml.jackson.core.JsonProcessingException;
import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.TelaPrincipal;

import javax.swing.*;

public class CarregarLivros {

    public static void main(String[] args) throws JsonProcessingException {

        //Carrega o livro
        CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

        //Carregar Tela Principal
        TelaPrincipal tela = new TelaPrincipal(1430,800);
        tela.setVisible(true);

    }

}

