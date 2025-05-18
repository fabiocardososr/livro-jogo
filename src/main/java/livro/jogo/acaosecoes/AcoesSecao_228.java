package livro.jogo.acaosecoes;

import livro.jogo.entidades.Secao;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.EfeitoDeItens;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_228 {

    public static void equiparBotasSaltadoras(){
        UtilBolsa.removerItem(31);
        UtilPersonagem.equiparDesequiparItem(DadosLivroCarregado.getMapItem().get(31));
    }
}
