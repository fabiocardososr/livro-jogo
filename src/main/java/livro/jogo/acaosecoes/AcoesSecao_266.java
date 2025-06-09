package livro.jogo.acaosecoes;

import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

public class AcoesSecao_266 {
    private static int resultado2Dados = 0;

    /// Regra:
    ///SUCESSO -> Se resultado dos 2 dados igual ou menor que o índice de habilidade
    ///FALHA   -> Se o resultado dos dados maior que o índice de habilidade
    public static boolean testarHabilidadeRemoverEspada(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        //Rolagem de 2 dados

        TelaBasica.mostrarDadosRolando(3000, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
        resultado2Dados = Util.rolarDados(6,2);

        //Comparar com a habilidade do personagem
        if ( resultado2Dados <= personagem.getHabilidadeAtual() )
            return true;
        else
            return false;
    }

    public static int getResultado2Dados(){
        return resultado2Dados;
    }
}
