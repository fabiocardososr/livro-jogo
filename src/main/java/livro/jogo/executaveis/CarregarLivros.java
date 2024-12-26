package livro.jogo.executaveis;

import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
import livro.jogo.telas.desktop.centralizacaotelas.TelasDisponiveisParaCarregamento;

public class CarregarLivros {

    public static void main(String[] args) {

        //Carrega o livro
        CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

        //Carregar Tela Principal
        new CarregarTelas().carregarTela(TelasDisponiveisParaCarregamento.TELA_PRINCIPAL,"","","");
    }
}

