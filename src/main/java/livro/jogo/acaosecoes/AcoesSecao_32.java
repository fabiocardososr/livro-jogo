package livro.jogo.acaosecoes;

import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_32 {

    ///Não há necessidade de verificar a disponibilidade dos recursos.
    ///Essa validação já vai ser feita na seção 346 que chama esta 32
    public static void entrega10PecasDeOuroEPerde1PontoDeSorte(){
        UtilPersonagem.reduzirValorOuro(10);
        UtilPersonagem.personagemPerdeSorte(1);
    }

}
