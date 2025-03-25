package livro.jogo.utils;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ItensMapeamento;

import java.util.ArrayList;

public class UtilPersonagem {
    private static final Personagem personagem = DadosLivroCarregado.getPersonagem();

    //Calcula força de ataque do personagem
    //desvantagem é usada por exemplo na seção 49 quando o personagem encontra-se lutando agachado (-3 pontos de ataque)
    public static int calcularForcaDeAtaqueDoPersonagem(int desvantagem){
        ArrayList<Item> itensEquipados = DadosLivroCarregado.getItensEquipados();
        int modificador = 0;  //Será somado (ou diminuído) a força de ataque, caso o item tenha algum modificador

        //Para cada item EQUIPADO verifica se existe modificador que afeta no cáclulo do ataque
        for (Item item : itensEquipados){

            //Se existe algum modificador que influencia(soma) ao resultado
            modificador = UtilItens.retornaModificadorQueInfluenciaNaRolagemDeDadosDaHabilidade(item);
        }

        //Rolagem de dados
        var resultadoDadosPersonagem = Util.rolarDados(6,2);

        //Força de ataque
        return resultadoDadosPersonagem + modificador + personagem.getHabilidadeAtual() - desvantagem;
    }

    //Calcula força de ataque menos alguma desvantagem
    // Exemplo na seção 49 o personagem está agachado e seu
    //ataque é prejudicado e com isso dá menos 3 pontos de força de ataque

    //Personagem perda de energia. E retorna true se personagem continuar vivo
    public static boolean personagemPerdeEnergia(int valorEnergiaAPerder){
        var nivelDeEnergiaAtual = personagem.getEnergiaAtual();

        DadosLivroCarregado.getPersonagem().setEnergiaAtual(nivelDeEnergiaAtual - valorEnergiaAPerder);

        return retornaSePersonagemVivo();
    }

    //Personagem perda de energia. E retorna true se personagem continuar vivo
    public static void personagemPerdeSorte(int valorSorteAPerder){
        var nivelDeSorteAtual = personagem.getSorteAtual();

        var novoNivelDeSorte = nivelDeSorteAtual - valorSorteAPerder;

        if (novoNivelDeSorte < 0)
            novoNivelDeSorte = 0;

        DadosLivroCarregado.getPersonagem().setSorteAtual(novoNivelDeSorte);
    }

    //Verifica se personagem vivo. True se Energia maior que "0"
    public static boolean retornaSePersonagemVivo(){

        return DadosLivroCarregado.getPersonagem().getEnergiaAtual() > 0;

    }

    //Retorna diferença entre o valor máximo e o atual de ENERGIA do personagem
    public static int retornaDiferencaEntreEnergiaMaxEAtual(){

        var indiceEnergiaAtual = personagem.getEnergiaAtual();
        var indiceEnergiaMax   = personagem.getEnergiaMax();

        return indiceEnergiaMax - indiceEnergiaAtual;
    }

    //Retorna diferença entre o valor máximo e o atual de HABILIDADE do personagem
    public static int retornaDiferencaEntreHabilidadeMaxEAtual(){

        var indiceAtual = personagem.getHabilidadeAtual();
        var indiceMax   = personagem.getHabilidadeMax();

        return indiceMax - indiceAtual;
    }

    //Retorna diferença entre o valor máximo e o atual de SORTE do personagem
    public static int retornaDiferencaEntreSorteMaxEAtual(){

        var indiceAtual = personagem.getSorteAtual();
        var indiceMax   = personagem.getSorteMax();

        return indiceMax - indiceAtual;
    }

    ///Dedução de outro
    public static boolean reduzirValorOuro(int valorASerReduzido){
        if (valorASerReduzido > personagem.getQuantidadeOuro())
            return false;

        personagem.setQuantidadeOuro(personagem.getQuantidadeOuro() - valorASerReduzido);

        return true;
    }

    ///Equipa ou coloca-o na bolsa novamente.
    ///Depende de onde ele esteja atualmente. Se na bolsa equipa-o, se equipado volta para a bolsa
    public static void equiparDesequiparItem(Item item){

        //Se o item já equipado, coloca-o na bolsa novamente
        if ( UtilBolsa.verificarExistenciaDeItemEquipado( item.getIdItem()) ) {
            DadosLivroCarregado.removeItemEquipado(item);
            DadosLivroCarregado.getBolsa().add(item);
            return;
        }

        //Adiciona aos equipados
        DadosLivroCarregado.getItensEquipados().add(item);

        //Remove da bolsa
        DadosLivroCarregado.removeItemBolsa(item);


//        DadosLivroCarregado.removeItemBolsa(item);
//        DadosLivroCarregado.getItensEquipados().add(item);
    }

    //Repõe o índice de habilidade ao valor inicial (valor máximo no momento da criação do personagem)
    public static void repoeHabilidadeAoNivelMaximo(){
        personagem.setHabilidadeAtual(personagem.getHabilidadeMax());
    }

    //Repõe o índice de energia ao valor inicial (valor máximo no momento da criação do personagem)
    public static void repoeEnergiaAoNivelMaximo(){
        personagem.setEnergiaAtual(personagem.getEnergiaMax());
    }

    //Repõe o índice de sorte ao valor inicial (valor máximo no momento da criação do personagem)
    public static void repoeSorteAoNivelMaximo(){
        personagem.setSorteAtual(personagem.getSorteMax());
    }
}
