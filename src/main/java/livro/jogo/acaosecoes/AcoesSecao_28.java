package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_28 {

    public static boolean possuiBracadeira(){
        return UtilBolsa.verificarExistenciaDeItemNaBolsaOuEquipado(ItensMapeamento.BRACADEIRA_DA_FORCA.getIdItem());
    }

    //Verifica a existência da Braçadeira da Força(12)
    public static boolean bracadeiraDaForcaEstaNaBolsa(){
        return UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.BRACADEIRA_DA_FORCA.getIdItem());
    }


    public static boolean bracadeiraDaForcaEquipada() {

        //Verifica se a braçadeira foi equipada e libera a próxima seção
        return UtilBolsa.verificarExistenciaDeItemEquipado(ItensMapeamento.BRACADEIRA_DA_FORCA.getIdItem());
    }
}
