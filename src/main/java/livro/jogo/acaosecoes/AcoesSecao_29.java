package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.AcoesBatalha;

public class AcoesSecao_29 {
    private static boolean fugiu;

    ///Informa se o personagem fugiu da luta. Pode ser quando estiver na batalha
    /// ou clicar na opção de fuga da tela de seção
    public static boolean isFugiu() {
        return fugiu;
    }

    /// Ao fugir seta a variável para que a tela da batalha
    /// saiba de uma fuga da batalha da seção 29
    public static void setFugiu(boolean fugiu) {
        AcoesSecao_29.fugiu = fugiu;
    }

    /// Executar ação de fuga quando escolhida a opção
    public static boolean fuga(TelaSecoesBasica tela){
       return  new AcoesBatalha().clicarNaOpcaoFuga(tela);
    }
}
