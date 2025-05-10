package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_328 {

    //Retorna se está vivo
    public static boolean diminuiUmaProvisaoEPerdeQuatroPontosDeEnergia(){

        //Remove uma provisão da bolsa
        UtilBolsa.removerItem(ItensMapeamento.PROVISAO.getIdItem());

        //Perder 4 pontos de energia
        var vivo = UtilPersonagem.personagemPerdeEnergia(4);

        if ( !vivo ) {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ",\n\nVocê sentou em uma Cadeira de Sugamento da Vida, apesar da porção de suas Provisões comida,"+
                    " não foi suficiente para mantê-lo vivo.");
        }

        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
        return vivo;
    }
}
