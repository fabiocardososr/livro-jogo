package livro.jogo.executaveis;

import livro.jogo.cadastros.CadastrarLivroFlorestaDaDestruicao;

public class carregarLivros {

    public static void main(String[] args) {

        /* ** Inicializar classes que farão a inserção na base de dados usando as classes DAO ** */
        CadastrarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CadastrarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

    }

}
