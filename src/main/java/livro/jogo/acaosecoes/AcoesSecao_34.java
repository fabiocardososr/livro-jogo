package livro.jogo.acaosecoes;

import livro.jogo.utils.UtilPersonagem;

public class AcoesSecao_34 {

    public static boolean recuperaHabilidadeAoNivelMaximo(){

        //Não faz nada já que o nível encontra-se no máximo
        if (UtilPersonagem.retornaDiferencaEntreHabilidadeMaxEAtual() == 0)
            return false;

        //Recupera ao máximo o índice
        UtilPersonagem.repoeHabilidadeAoNivelMaximo();

        return true;
    }

    public static boolean recuperaEnergiaAoNivelMaximo(){

        //Não faz nada já que o nível encontra-se no máximo
        if (UtilPersonagem.retornaDiferencaEntreEnergiaMaxEAtual() == 0)
            return false;

        //Recupera ao máximo o índice
        UtilPersonagem.repoeEnergiaAoNivelMaximo();

        return true;
    }

    public static boolean recuperaSorteAoNivelMaximo(){

        //Não faz nada já que o nível encontra-se no máximo
        if (UtilPersonagem.retornaDiferencaEntreSorteMaxEAtual() == 0)
            return false;

        //Recupera ao máximo o índice
        UtilPersonagem.repoeSorteAoNivelMaximo();

        return true;
    }

    //Verifica se o personagem está com todos os índices no máximo mesmo sem escolher uma opção.
    public static boolean verificaSeTodosIndicesCompletos(){
        if ( (UtilPersonagem.retornaDiferencaEntreSorteMaxEAtual() == 0) &&
             (UtilPersonagem.retornaDiferencaEntreEnergiaMaxEAtual() == 0) &&
             (UtilPersonagem.retornaDiferencaEntreHabilidadeMaxEAtual() == 0) )
            return true;

        return false;
    }

}
