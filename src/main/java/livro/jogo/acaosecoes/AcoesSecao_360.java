package livro.jogo.acaosecoes;

import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_360 {

    //Nesta seção moedas e itens são a mesma coisa.
    public static boolean verificaSePossuiItensOuMoedasParaPagar(){
        int qtdItensNaBolsa = UtilBolsa.quantidadeDeItensNaBolsa();
        int qtdMoedas = DadosLivroCarregado.getPersonagem().getQuantidadeOuro();
        int totalItens = qtdItensNaBolsa + qtdMoedas;

        //Possuindo 5 ou mais itens libera a opção pois é isso que os bandidos exigem de moedas ou item.
        if (totalItens >= 5)
            return true;
        else
            return false;
    }
}
