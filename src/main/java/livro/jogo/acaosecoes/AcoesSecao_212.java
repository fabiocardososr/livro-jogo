package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.AcoesBatalha;

public class AcoesSecao_212 {
    private static boolean fugiu;

    /// Executar ação de fuga quando escolhida a opção
    public static boolean fuga(TelaSecoesBasica tela){
        return  new AcoesBatalha().clicarNaOpcaoFuga(tela);
    }
}
