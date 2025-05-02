package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_184 {

    public static void entregaSinoAoFrade(){
        UtilBolsa.removerItem(ItensMapeamento.SINO_DE_METAL.getIdItem());
    }

    public static void recupera4PontosDeEnergia(){

        UtilPersonagem.recuperaEnergia(4);
    }
}
