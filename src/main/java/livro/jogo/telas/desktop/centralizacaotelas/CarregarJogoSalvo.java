package livro.jogo.telas.desktop.centralizacaotelas;

import livro.jogo.entidades.SaveJogo;
import livro.jogo.utils.Util;

import javax.swing.*;

//Classe responsável por carregar o jogo salvo propriamente dito
public class CarregarJogoSalvo {
    private SaveJogo save;

    public CarregarJogoSalvo(String nomeArquivo) {
        this.save = Util.carregarJogoEmArquivo(nomeArquivo);

        System.out.println(this.save.getPersonagem());

        //Tem que carregar todos os dados. Veja DadosLivroCarregado
        //CarregarTelas.carregarSecaoSalva(save.getSecao(), save.getPersonagem());
    }
}
