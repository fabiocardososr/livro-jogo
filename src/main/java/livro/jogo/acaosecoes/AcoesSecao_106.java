package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_106 {

    public static void recebe2PontosDeSorteEAnelDeOuroComGrandeEsmeralda(){
        if (!UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.ANEL_DE_OURO_COM_GRANDE_ESMERALDA.getIdItem())) {
            UtilBolsa.incluirItem(DadosLivroCarregado.getMapItem().get(ItensMapeamento.ANEL_DE_OURO_COM_GRANDE_ESMERALDA.getIdItem()));
        }
        UtilPersonagem.recuperaSorte(2);
    }
}
