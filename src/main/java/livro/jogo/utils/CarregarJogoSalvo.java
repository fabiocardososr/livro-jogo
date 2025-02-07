package livro.jogo.utils;

import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.SaveJogo;
import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.CarregarTelas;

//Classe responsável por carregar o jogo salvo propriamente dito
public class CarregarJogoSalvo {
    private SaveJogo save;

    public CarregarJogoSalvo(String nomeArquivo) {
        this.save = Util.carregarJogoEmArquivo(nomeArquivo);
        Personagem personagem = save.getPersonagem();
        Secao secao = save.getSecao();

        DadosLivroCarregado.setPersonagem(personagem);

        //Carregar tela
        CarregarTelas.carregarSecao(secao);
    }
}
