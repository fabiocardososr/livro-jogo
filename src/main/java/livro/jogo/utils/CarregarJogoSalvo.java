package livro.jogo.utils;

import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.SaveJogo;
import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.secoes.InfoSecoes;

//Classe responsável por carregar o jogo salvo propriamente dito
public class CarregarJogoSalvo {
    private SaveJogo save;

    public CarregarJogoSalvo(String nomeArquivo) {
        this.save = Util.carregarJogoEmArquivo(nomeArquivo);
        Personagem personagem = save.getPersonagem();
        Secao secao = save.getSecao();
        InfoSecoes infoSecoes = save.getInfoSecoes();

        DadosLivroCarregado.setPersonagem(personagem);

        DadosLivroCarregado.setInfoSecoes(infoSecoes);

        //Carregar tela
        CarregarTelas.carregarSecao(secao);
    }
}
