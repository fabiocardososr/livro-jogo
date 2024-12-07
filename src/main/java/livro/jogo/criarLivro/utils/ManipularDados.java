//Classe responsável por manter os dados do livro após carregamento do Json via classe ManipularArquivos
package livro.jogo.criarLivro.utils;

import livro.jogo.criarLivro.entidades.Livro;
import livro.jogo.criarLivro.entidades.Secao;

import java.util.HashMap;

public class ManipularDados {
    private static Livro livro;
    private static final HashMap<Integer, Secao> mapSecao = new HashMap<Integer, Secao>();

    public ManipularDados(Livro livro) {
        this.livro = livro;

    }

    public static Livro getLivro() {
        return livro;
    }

    public static void setLivro(Livro livro) {
        ManipularDados.livro = livro;
    }

    public static HashMap<Integer, Secao> getMapSecao(){
        return mapSecao;
    }

    public static void imprimirInfoSecoes() {

        for (Integer key : mapSecao.keySet()) {

            //Capturamos o valor a partir da chave
            Secao secao = (Secao) mapSecao.get(key);
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
