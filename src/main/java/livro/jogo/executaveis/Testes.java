package livro.jogo.executaveis;

import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.TelaPrincipal;
import livro.jogo.utils.Util;

public class Testes {
    public static void main(String[] args) {

        System.out.println("-----");
        for (int i=0; i<20; i++)
            System.out.print("Habilidade: "+ Util.obterIndiceHabilidadeOuSorteInicial()+", ");

        System.out.println("-----");
        for (int i=0; i<20; i++)
            System.out.print("Energia: "+ Util.obterIndiceEnergiaInicial()+", ");

        System.out.println("-----");
        for (int i=0; i<20; i++)
            System.out.print("Sorte: "+ Util.obterIndiceHabilidadeOuSorteInicial()+", ");

    }
}
