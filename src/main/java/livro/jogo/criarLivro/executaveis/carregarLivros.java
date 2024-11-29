package livro.jogo.criarLivro.executaveis;

import livro.jogo.criarLivro.cadastro.CadastrarLivroFlorestaDaDestruicao;

public class carregarLivros {

    public static void main(String[] args) {

        /* ** Inicializar classes que farão a inserção na base de dados usando as classes DAO ** */
        CadastrarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CadastrarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

    }

}

