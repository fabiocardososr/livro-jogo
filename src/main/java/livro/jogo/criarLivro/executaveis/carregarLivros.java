package livro.jogo.criarLivro.executaveis;

import com.fasterxml.jackson.core.JsonProcessingException;
import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;

public class carregarLivros {

    public static void main(String[] args) throws JsonProcessingException {

        /* ** Inicializar classes que farão a inserção na base de dados usando as classes DAO ** */
        CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

    }

}

