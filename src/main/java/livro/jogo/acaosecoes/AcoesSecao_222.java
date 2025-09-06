package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_222 {

    //Retorna true se false
    public static boolean rolarUmDadoDefineQuantoDeEnergiaPerdeERetornaSeVivo(){

        //Mostra animação
        Util.mostrarAnimacaoDadosRolando();

        //Simula a rolage 1 dados
        var resultadoDoisDado = Util.rolarDados(6,1);

        if ( !UtilPersonagem.personagemPerdeEnergia(resultadoDoisDado) )
        {
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nSeu corpo não resistiu ao gás tóxico. Você morreu!"+
                    "\n\nSua aventura acaba aqui!");
            return false;
        }

        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/azar.mp3", null);
        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                ",\n\nseu corpo resistiu ao gás venenoso. Mas perde "+ resultadoDoisDado +" de energia.");

        return true;
    }
}
