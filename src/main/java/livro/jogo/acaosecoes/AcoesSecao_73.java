package livro.jogo.acaosecoes;

import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

public class AcoesSecao_73 {


    /*
    REGRA:
    CONSEGUE ABRIR - Se o número obtido for igual ou menor do que tanto o seu índice de SORTE quanto o de HABILIDADE.
    NÃO ABRE A PORTA - Se o número obtido for maior do que qualquer um dos dois índices.* */
    public static boolean realizarTesteParaAbrirPorta(){
        int sorteAtualPersonagem = DadosLivroCarregado.getPersonagem().getSorteAtual();
        int habilidadeAtualPersonagem = DadosLivroCarregado.getPersonagem().getHabilidadeAtual();

        //Mostra animação
        Util.mostrarAnimacaoDadosRolando();

        //Simula a rolage 2 dados
        var resultadoDoisDados  = Util.rolarDados(6,2);

        //Faz a comparação seguindo as regras da seção
        if ( (resultadoDoisDados <= sorteAtualPersonagem) &&
                (resultadoDoisDados <= habilidadeAtualPersonagem)) {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
            return true;
        }
        else {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
            return false;
        }
    }

}
