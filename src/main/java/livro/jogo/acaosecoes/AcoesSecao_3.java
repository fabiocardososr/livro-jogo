package livro.jogo.acaosecoes;

import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.DadosLivroCarregado;

public class AcoesSecao_3 {

    public static boolean opcao_1(){

        //Verifica se persongem possui o Anel de luz
        if ( !UtilBolsa.verificarExistenciaDeItemNaBolsaOuEquipado(ItensMapeamento.ANEL_DA_LUZ.getIdItem()) ) {
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ", você não possui o Anel da Luz.\n\nEscolha a 2ª opção.");
            return false;
        }

        //Se item anel de luz equipado libera o acesso a nova seção
        if ( UtilBolsa.verificarExistenciaDeItemEquipado(ItensMapeamento.ANEL_DA_LUZ.getIdItem()) ) {
            return true;
        }

        //Se possui o anel de luz mas ele encontra-se na bolsa, informa que ele deve ser equipado antes
        if ( UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.ANEL_DA_LUZ.getIdItem()) )
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ",\nvocê possui o item. Mas precisa equipá-lo.\n\nProcure-o na sua bolsa e coloque-o no dedo.");

        return false;
    }

    /// Neste caso o item existe, mas o jogador clicou nesta opção
    public static void opcao_2_possui_item(TelaSecoesBasica tela){

        if ( UtilBolsa.verificarExistenciaDeItemEquipado(ItensMapeamento.ANEL_DA_LUZ.getIdItem()) ) {
            return;
        }

        //Se personagem tem o anel de luz mas não quer seguir sem colocar o nael no dedo
        if ( UtilBolsa.verificarExistenciaDeItemNaBolsaOuEquipado(ItensMapeamento.ANEL_DA_LUZ.getIdItem()) ) {
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ",\nverifique em sua bolsa a existência do Anel de Luz e coloque-o em seu dedo."+
                    "\n\nCaso possuindo, mesmo assim, deseja escolher esta opção?",tela);
        }
    }

}
