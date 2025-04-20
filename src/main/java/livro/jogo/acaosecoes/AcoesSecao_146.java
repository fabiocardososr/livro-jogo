package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_146 {

    public static boolean pegarPedra(){

        //Verifica se já foi adicionado e não deixa adicionar novamente
        if (UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.PEDACO_BICHO_ROCHEDO.getIdItem()))
            return true;

        var adicionado = UtilBolsa.incluirItem(ItensMapeamento.PEDACO_BICHO_ROCHEDO);

        if (adicionado)
            CarregarTelas.telaMensagem("O pedaço de rocha do Bicho Rochedo foi adicionado a sua bolsa.");

        return adicionado;

    }
}
