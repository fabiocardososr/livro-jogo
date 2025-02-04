package livro.jogo.utils;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ItensMapeamento;

import java.util.ArrayList;

public class EfeitoDeItens {

    //Aqui são codificados todos os efeitos dos itens
    //O retorno indica se consumiu/equipou
    public static boolean acoesDosItens(int idItem){

        return switch(idItem) {
            case 1  -> efeitoItem1();      //Anel de Luz(da luz)
            case 10 -> efeitoItem10();     //Espada magnífica
            case 45 -> efeitoItem45();     //Poção de Habilidade
            case 46 -> efeitoItem46();     //Poção de força
            case 47 -> efeitoItem47();     //Poção de Fortuna
            case 49 -> efeitoItem49();     //Provisões (comida)
            default -> false;
        };
    }

    //Equipar o Anel da Luz(colocar no dedo)
    private static boolean efeitoItem1() {

        //Se o anel já equipado, não faz nada e sai
        if (Util.verificarExistenciaDeItemEquipado(ItensMapeamento.ANEL_DA_LUZ.getIdItem()))
            return false;

        //Recupera o item anel da luz(código 1)
        Item item = DadosLivroCarregado.getMapItem().get( ItensMapeamento.ANEL_DA_LUZ.getIdItem() );

        //Adiciona aos equipados
        DadosLivroCarregado.getItensEquipados().add(item);

        //Remove da bolsa
        DadosLivroCarregado.removeItemBolsa(item);

        return true;
    }

    //Espada magnífica deve ser equipada (seção 70)
    private static boolean efeitoItem10() {
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        //Recupera o item
        Item item = DadosLivroCarregado.getMapItem().get(10);

        //desequipar a espada incial e incluir na bolsa
        Item espadaInicial = DadosLivroCarregado.getMapItem().get(50);
        DadosLivroCarregado.removeItemEquipado(espadaInicial);
        personagem.getBolsa().add(espadaInicial);

        //Equipa a espada magnífica(idItem 10)
        personagem.getItensEquipados().add(item);

        //Efeito mágico da espada(idItem=10): aumenta a habilidade atual do personagem em 2 pontos(seção 70)
        personagem.setHabilidadeAtual(personagem.getHabilidadeAtual() + 2);

        return true;
    }

    //Poção da fortuna(sorte)
    private static boolean efeitoItem47() {
        Personagem personagem = DadosLivroCarregado.getPersonagem();
        ArrayList<Item> itens = DadosLivroCarregado.getBolsa();

        //Se personagem com índice no máximo, não fazer nada
        if (Util.retornaDiferencaEntreSorteMaxEAtual() == 0){
            return false;
        }

        //Achar o item e removê-lo da bolsa
        for (Item item : itens)
            if ( item.getIdItem() == ItensMapeamento.POCAO_DA_FORTUNA.getIdItem() ) {
                itens.remove(item);
                break;
            }

        //Aumenta em 1 o nível inicial(máximo) de sorte
        personagem.setSorteMax( personagem.getSorteMax() + 1 );

        //Recupera índice ao valor máximo (valor inicial do momento da criação do personagem)
        personagem.setSorteAtual( personagem.getSorteMax() );

        return true;
    }

    //Poção de força(energia)
    private static boolean efeitoItem46() {
        Personagem personagem = DadosLivroCarregado.getPersonagem();
        ArrayList<Item> itens = DadosLivroCarregado.getBolsa();

        //Se personagem com índice no máximo, não fazer nada
        if (Util.retornaDiferencaEntreEnergiaMaxEAtual() == 0){
            return false;
        }

        //Achar o item e removê-lo da bolsa
        for (Item item : itens)
            if ( item.getIdItem() == ItensMapeamento.POCAO_DE_ENERGIA.getIdItem() ) {
                itens.remove(item);
                break;
            }

        //Recupera índice ao valor máximo (valor inicial do momento da criação do personagem)
        personagem.setEnergiaAtual( personagem.getEnergiaMax() );

        return true;
    }

    //Poção de Habilidade inicial
    private static boolean efeitoItem45(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();
        ArrayList<Item> itens = DadosLivroCarregado.getBolsa();

        //Se personagem com índice no máximo, não fazer nada
        if (Util.retornaDiferencaEntreHabilidadeMaxEAtual() == 0){
            return false;
        }

        //Achar o item e removê-lo da bolsa
        for (Item item : itens)
            if ( item.getIdItem() == ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem() ) {
                itens.remove(item);
                break;
            }

        //Recupera índice ao valor máximo (valor inicial do momento da criação do personagem)
        personagem.setHabilidadeAtual( personagem.getHabilidadeMax() );

        return true;
    }

    //Provisão
    private static boolean efeitoItem49(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();
        ArrayList<Item> itens = DadosLivroCarregado.getBolsa();

        //Se personagem com energia máxima, não fazer nada
        if (Util.retornaDiferencaEntreEnergiaMaxEAtual() == 0){
            return false;
        }

        //Achar um item do tipo provisão e removê-lo da bolsa
        for (Item item : itens)
            if ( item.getIdItem() == ItensMapeamento.PROVISAO.getIdItem() ) {
                itens.remove(item);
                break;
            }

        //Recuperando o índice atual e o índice máximo em que o personagem pode chegar
        var indiceAtual   = personagem.getEnergiaAtual();
        var indiceMaximo  = personagem.getEnergiaMax();

        //reposição de 4 pontos de energia do personagem
        var indiceAtualComReposicao = indiceAtual + 4;

        //Com a reposição ultrapassando o índice máximo, o índice atual recebe máximo
        if (indiceAtualComReposicao > indiceMaximo)
            indiceAtualComReposicao = indiceMaximo;

        //Atualizando índice
        personagem.setEnergiaAtual(indiceAtualComReposicao);

        return true;
    }




}
