package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_106 {

    public static void recebe2PontosDeSorteEAnelDeOuroComGrandeEsmeralda(){
        UtilBolsa.incluirItem(ItensMapeamento.ANEL_DE_OURO_COM_GRANDE_ESMERALDA);
        UtilPersonagem.recuperaSorte(2);
    }
}
