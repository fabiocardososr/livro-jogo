package livro.jogo.acaosecoes;

import livro.jogo.entidades.Secao;
import livro.jogo.utils.EfeitoDeItens;

public class AcoesSecao_70 {

    public static void equiparEspadaMagnifica(Secao secao){
        new EfeitoDeItens(secao).acoesDosItens(10);
    }
}
