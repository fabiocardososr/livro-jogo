package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;

public class AcoesSecao_170 {

    public static boolean verificaSePossuiColarOlhoDeAmbar(){

        return UtilBolsa.verificarExistenciaDeItemEquipado(ItensMapeamento.COLAR_OLHO_DE_AMBAR.getIdItem());
    }
}
