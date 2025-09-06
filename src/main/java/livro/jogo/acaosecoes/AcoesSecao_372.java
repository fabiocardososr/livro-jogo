package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_372 {

    public static boolean perdeEnergia(){

        //Perder 1 pontos de energia
        var vivo = UtilPersonagem.personagemPerdeEnergia(1);

        if ( !vivo ) {
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ",\n\nVocê deu um mal jeito nas costas e isso foi fatal.\nVocê morreu!");
        }
        else {
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ",\n\nA arca é muito pesada e pelo esforço você perde 1 ponto de energia!");
        }

        return vivo;
    }

    public static boolean realizarTesteParaLevantarAArca(){
        int habilidadeAtualPersonagem = DadosLivroCarregado.getPersonagem().getHabilidadeAtual();

        //Mostra animação
        Util.mostrarAnimacaoDadosRolando();

        //Simula a rolage 2 dados
        var resultadoDoisDados  = Util.rolarDados(6,2);

        //Faz a comparação seguindo as regras da seção
        if ( resultadoDoisDados <= habilidadeAtualPersonagem ) {
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
            return true;
        }
        else {
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/azar.mp3", null);
            return false;
        }
    }
}
