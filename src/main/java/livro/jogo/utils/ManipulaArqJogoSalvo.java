package livro.jogo.utils;
import livro.jogo.entidades.SaveJogo;
import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.secoes.InfoSecoes;

//Classe responsável por carregar o jogo salvo propriamente dito
public class ManipulaArqJogoSalvo {
    private SaveJogo save;

    public ManipulaArqJogoSalvo() {

    }

    public void carregaJogo(String nomeArquivo){
        this.save = Util.carregarJogoEmArquivo(nomeArquivo);
        Secao secao = save.getSecao();

        //Informa que a seção foi carregada a partir de um arquivo save
        secao.setSecaoCarregadaDeArquivoSave(true);

        InfoSecoes infoSecoes = save.getInfoSecoes();

        DadosLivroCarregado.setPersonagem(save.getPersonagem());

        DadosLivroCarregado.setInfoSecoes(infoSecoes);

        //Carregar tela
        CarregarTelas.carregarSecao(secao);
    }

    public void apagaJogo(String nomeArquivo){
        Util.apagaJogoSalvo(nomeArquivo);
    }
}
