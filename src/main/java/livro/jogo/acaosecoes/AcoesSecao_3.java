package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.Bolsa;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

public class AcoesSecao_3 {

    public static boolean opcao_1(){

        //Verifica se persongem possui o Anel de luz
        if ( !Bolsa.verificarExistenciaDeItemNaBolsaOuEquipado(ItensMapeamento.ANEL_DA_LUZ.getIdItem()) ) {
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ", você não possui o Anel da Luz.\n\nEscolha a 2ª opção.");
            return false;
        }

        //Se item anel de luz equipado libera o acesso a nova seção
        if ( Bolsa.verificarExistenciaDeItemEquipado(ItensMapeamento.ANEL_DA_LUZ.getIdItem()) ) {
            return true;
        }

        //Se possui o anel de luz mas ele encontra-se na bolsa, informa que ele deve ser equipado antes
        if ( Bolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.ANEL_DA_LUZ.getIdItem()) )
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ", você possui o item. Mas precisa equipá-lo.\n\nProcure-o na sua bolsa.");

        return false;
    }

}
