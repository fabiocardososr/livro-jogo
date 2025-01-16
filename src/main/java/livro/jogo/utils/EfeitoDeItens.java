package livro.jogo.utils;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;

import java.util.ArrayList;

public class EfeitoDeItens {
    private Personagem personagem = DadosLivroCarregado.getPersonagem();
    private ArrayList<Item> itens = personagem.getBolsa();

    //Aqui são codificados todos os efeitos dos itens
    //O retorno indica se consumiu/equipou
    public boolean acoesDosItens(int idItem){

        return switch(idItem) {
            case 45 -> efeitoItem45();             //Poção de Habilidade
            case 46 -> efeitoItem46();             //Poção de força
            case 47 -> efeitoItem47();             //Poção de Fortuna
            case 49 -> efeitoItem49Provisao();     //Provisões (comida)
            default -> false;
        };
    }

    //Poção da fortuna(sorte)
    private boolean efeitoItem47() {

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
    private boolean efeitoItem46() {

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
    private boolean efeitoItem45(){

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
    private boolean efeitoItem49Provisao(){

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
