package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilItemEquipado;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_46 {

    public static void removerCenoura(){
        UtilItemEquipado.removerItem(ItensMapeamento.CENOURA.getIdItem());
        UtilBolsa.removerItem(ItensMapeamento.CENOURA.getIdItem());
    }

    public static void perde2Sorte(){
        UtilPersonagem.personagemPerdeSorte(2);
    }
}
