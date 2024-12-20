//Classe responsável por manter os dados do livro após carregamento do Json via classe ManipularArquivos
package livro.jogo.utils;

import livro.jogo.criarLivro.entidades.Livro;
import livro.jogo.criarLivro.entidades.Secao;

import java.util.HashMap;

public class ManipularDados {
    private static Livro livro;


    public static Livro getLivro() {
        return livro;
    }

    public static void setLivro(Livro livro) {
        ManipularDados.livro = livro;
    }

    public static HashMap<Integer, Secao> getMapSecao(){
        return livro.getMapSecao();
    }

    public static void imprimirInfoSecoes() {

        //Dados do livro
        System.out.print("\n\nIdLivro: "+ livro.getIdLivro());
        System.out.println("\nNome: "+ livro.getNome());
        System.out.println("Descrição: "+ livro.getDescricao());
        System.out.println("\n======================================\n\n");

        for (Integer key : livro.getMapSecao().keySet()) {

            //Capturamos o valor a partir da chave
            Secao secao = (Secao) livro.getMapSecao()                                                                .get(key);
            System.out.println("\n\nCod. Seção: "+ secao.getCodSecaoLivro());
            System.out.println("Desc. Seção: "+ secao.getTexto());
            System.out.println("Imagem da Seção: "+ secao.getEnderecoImagem());
            System.out.println("Proximas seções: "+ secao.getProximasSecoes().size());
            System.out.println("Proximas seções: "+ secao.getProximasSecoes());
            System.out.println("Itens: "+ secao.getItens().toString());
            System.out.println("Inimigos: "+ secao.getInimigos().toString());
        }
    }
}
