package livro.jogo.criarLivro.executaveis;

import com.fasterxml.jackson.core.JsonProcessingException;
import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.TelaPrincipal;

import javax.swing.*;

public class carregarLivros {

    public static void main(String[] args) throws JsonProcessingException {

        //Carrega o livro
        CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

        //Carregar Tela Principal
        TelaPrincipal tela = new TelaPrincipal();
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(1500, 800);
        tela.setLocationRelativeTo(null); //Centraliza a tela no centro
        tela.setVisible(true);

    }

}

