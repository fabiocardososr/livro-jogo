package livro.jogo.telas.desktop.centralizacaotelas;

import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.SaveJogo;
import livro.jogo.entidades.Secao;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.swing.*;

//Classe responsável por carregar o jogo salvo propriamente dito
public class CarregarJogoSalvo {
    private SaveJogo save;

    public CarregarJogoSalvo(String nomeArquivo) {
        this.save = Util.carregarJogoEmArquivo(nomeArquivo);
        Personagem personagem = save.getPersonagem();
        Secao secao = save.getSecao();

        DadosLivroCarregado.setPersonagem(personagem);

        //Carregar tela
        new CarregarTelas().carregarSecaoSalva(secao, personagem);
    }
}
