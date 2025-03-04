package livro.jogo.utils;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.TipoEfeito;

import java.util.ArrayList;

public class UtilPersonagem {

    //Calcula força de ataque do personagem
    public static int calcularForcaDeAtaqueDoPersonagem(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();
        ArrayList<Item> itensEquipados = DadosLivroCarregado.getItensEquipados();
        int modificador = 0;  //Será somado (ou diminuído) a força de ataque, caso o item tenha algum modificador

        //Para cada item verifica se existe modificador que afeta no cáclulo do ataque
        for (Item item : itensEquipados){

            //Se existe algum modificador que influencia(soma) ao resultado
            modificador = UtilItens.retornaModificadorQueInfluenciaNaRolagemDeDadosDaHabilidade(item);
        }

        //Rolagem de dados
        var resultadoDadosPersonagem = Util.rolarDados(6,2);

        //Força de ataque
        return resultadoDadosPersonagem + modificador + personagem.getHabilidadeAtual();
    }
}
