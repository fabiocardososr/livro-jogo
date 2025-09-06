package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.*;

public class AcoesSecao_22 {

    public static void perde2DeHabilidade(){
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/azar.mp3", null);
        UtilPersonagem.personagemPerdeHabilidade(2);
    }

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

        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                ",\n\nseu corpo resistiu ao gás venenoso. Mas perde "+ resultadoDoisDado +" de energia.");

        return true;
    }

    public static void ganhaCaixaDePrata(){
        //new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);

        //Se já existir não deixe incluir (só por precaução, acredito que no livro não tem como se repetir)
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.CAIXA_DE_PRATA.getIdItem()) )
            UtilBolsa.incluirItem(UtilItens.retornaItem(ItensMapeamento.CAIXA_DE_PRATA.getIdItem()));
    }
}
