package livro.jogo.acaosecoes;

import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_251 {

    /*
    * REGRA: Ao rolar 2 dados,
    * SUCESSO: Resultado dos dados menor ou igual ao seu índice de HABILIDADE, você consegue se esquivar da flecha
    * FALHA: Resultado dos dados maior ao seu índice de HABILIDADE, você NÃO consegue se esquivar da flecha
    * */
    public static boolean realizarTeste(){
        int habilidadeAtualPersonagem = DadosLivroCarregado.getPersonagem().getHabilidadeAtual();

        //Mostra animação
        Util.mostrarAnimacaoDadosRolando();

        //Simula a rolage 2 dados
        var resultadoDoisDados  = Util.rolarDados(6,2);
System.out.println(resultadoDoisDados);
        //Faz a comparação seguindo as regras da seção
        if ( resultadoDoisDados <= habilidadeAtualPersonagem ) {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
            return true;
        }
        else {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
            return false;
        }
    }

    //Perde energia e retorna TRUE se o personagem ainda vivo
    public static boolean perde4PontosDeEnergia(){

        if ( !UtilPersonagem.personagemPerdeEnergia(4) )
        {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\nApesar da flecha se alojar no ombro, o ferimento foi bastante grave pois você já estava ferido."+
                    "\n\nVocê morreu!");
            return false;
        }

        return true;
    }
}
