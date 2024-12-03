package livro.jogo.criarLivro.cadastro.executaveis;

import com.fasterxml.jackson.core.JsonProcessingException;
import livro.jogo.criarLivro.cadastro.CadastrarLivroFlorestaDaDestruicao;

public class carregarLivros {

    public static void main(String[] args) throws JsonProcessingException {

        /* ** Inicializar classes que farão a inserção na base de dados usando as classes DAO ** */
        CadastrarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CadastrarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();
    }

}

