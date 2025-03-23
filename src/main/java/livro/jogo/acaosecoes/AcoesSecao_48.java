package livro.jogo.acaosecoes;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_48 {

    //O resultado da rolagem de dado vai definir quanto de dano o personagem irá tomar
    public static int rolarDadoDeterminandoQuantoDeDano(){

        //Animação dos dados rolando
        TelaBasica.mostrarDadosRolando(4000, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);

        return Util.rolarDados(6,1);
    }

    public static boolean perdePontosDeEnergiaBaseadoNoResultadoDoDado(int resultadoDaRolagemDoDado) {

        if ( !UtilPersonagem.personagemPerdeEnergia(resultadoDaRolagemDoDado) )
        {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);

            if (resultadoDaRolagemDoDado == 1)
                CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nO estilhaço penetrou em órgãos vitais.\nO dano foi mortal."+
                    "\n\nSua aventura acaba aqui!");
            else
                CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                        ",\n\nOs estilhaços penetraram em órgãos vitais.\nO dano foi mortal."+
                        "\n\nSua aventura acaba aqui!");

            return false;
        }

        return true;
    }
}
