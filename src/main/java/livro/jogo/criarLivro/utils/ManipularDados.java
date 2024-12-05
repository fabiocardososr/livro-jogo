//Classe responsável por manter os dados do livro após carregamento do Json via classe ManipularArquivos
package livro.jogo.criarLivro.utils;

import livro.jogo.criarLivro.entidades.Livro;
import livro.jogo.criarLivro.entidades.Secao;

import java.util.HashMap;

public class ManipularDados {
    private Livro livro;
    private static final HashMap<Integer, Secao> mapSecao = new HashMap<Integer, Secao>();

    public ManipularDados(Livro livro) {
        this.livro = livro;

    }

    public Livro getLivro() {
        return livro;
    }

    public static HashMap<Integer, Secao> getMapSecao(){
        return mapSecao;
    }
}
