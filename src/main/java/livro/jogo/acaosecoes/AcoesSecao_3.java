package livro.jogo.acaosecoes;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.EfeitoDeItens;
import livro.jogo.utils.Util;

import javax.swing.*;

public class AcoesSecao_3 {
    private final Secao secao;

    public AcoesSecao_3(Secao secao) {
        this.secao = secao;
    }

    public static boolean opcao_1(){

        //Verifica se persongem possui o Anel de luz
        if ( !Util.verificarExistenciaDeItemNaBolsaOuEquipado(ItensMapeamento.ANEL_DA_LUZ.getIdItem()) ) {
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ", você não possui o Anel da Luz.\n\nEscolha a 2ª opção.");
            return false;
        }

        //Se item anel de luz equipado libera o acesso a nova seção
        if ( Util.verificarExistenciaDeItemEquipado(ItensMapeamento.ANEL_DA_LUZ.getIdItem()) ) {
            return true;
        }

        //Se possui o anel de luz mas ele encontra-se na bolsa, informa que ele deve ser equipado antes
        if ( Util.verificarExistenciaDeItemNaBolsa(ItensMapeamento.ANEL_DA_LUZ.getIdItem()) )
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ", você possui o item. Mas precisa equipá-lo.\n\nProcure-o na bolsa.");

        return false;
    }

    public void opcao_2(){
        JOptionPane.showMessageDialog(null, "Opcao: "+secao.getProximasSecoes().get(1)+
                " - Descricao da opção: "+secao.getProximasSecoes().get(1).getTextoOpcao());
    }
}
