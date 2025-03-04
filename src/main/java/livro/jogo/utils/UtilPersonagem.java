package livro.jogo.utils;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;

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

    //Personagem perda de energia. E retorna true se personagem continuar vivo
    public static boolean personagemPerdeEnergia(int valorEnergiaAPerder){
        var nivelDeEnergiaAtual = DadosLivroCarregado.getPersonagem().getEnergiaAtual();

        DadosLivroCarregado.getPersonagem().setEnergiaAtual(nivelDeEnergiaAtual - valorEnergiaAPerder);

        return retornaSePersonagemVivo();
    }

    //Verifica se personagem vivo. True se Energia maior que "0"
    public static boolean retornaSePersonagemVivo(){

        return DadosLivroCarregado.getPersonagem().getEnergiaAtual() > 0;

    }

    //Retorna diferença entre o valor máximo e o atual de ENERGIA do personagem
    public static int retornaDiferencaEntreEnergiaMaxEAtual(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        var indiceEnergiaAtual = personagem.getEnergiaAtual();
        var indiceEnergiaMax   = personagem.getEnergiaMax();

        return indiceEnergiaMax - indiceEnergiaAtual;
    }

    //Retorna diferença entre o valor máximo e o atual de HABILIDADE do personagem
    public static int retornaDiferencaEntreHabilidadeMaxEAtual(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        var indiceAtual = personagem.getHabilidadeAtual();
        var indiceMax   = personagem.getHabilidadeMax();

        return indiceMax - indiceAtual;
    }

    //Retorna diferença entre o valor máximo e o atual de SORTE do personagem
    public static int retornaDiferencaEntreSorteMaxEAtual(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        var indiceAtual = personagem.getSorteAtual();
        var indiceMax   = personagem.getSorteMax();

        return indiceMax - indiceAtual;
    }
}
