package livro.jogo.acaosecoes;

import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_13 {

    //Nesta seção o persongagem perde 3 pontos de sorte
    public static void perde3PontosDeSorte(){

        UtilPersonagem.personagemPerdeSorte(3);
        System.out.println(DadosLivroCarregado.getPersonagem().getSorteAtual());

    }
}
