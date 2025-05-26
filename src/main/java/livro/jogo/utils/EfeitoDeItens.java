package livro.jogo.utils;

import livro.jogo.acaosecoes.AcoesSecao_16;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ItensMapeamento;

public class EfeitoDeItens {
    private Secao secao;

    public EfeitoDeItens(Secao secao) {
        this.secao = secao;
    }

    //Aqui são codificados todos os efeitos dos itens
    //O retorno indica se consumiu/equipou
    public boolean acoesDosItens(int idItem){

        return switch(idItem) {
            case 1  -> efeitoItem_1();      //Anel de Luz(da luz)
            case 3  -> efeitoItem_3();      //Poção Antiveneno
            case 12 -> efeitoItem_12();     //Braçadeira da Força
            case 10 -> efeitoItem_10();     //Espada magnífica
            case 28 -> efeitoItem_28();     //Escudo de ferro(imperador)
            case 45 -> efeitoItem_45();     //Poção de Habilidade
            case 46 -> efeitoItem_46();     //Poção de força
            case 47 -> efeitoItem_47();     //Poção de Fortuna
            case 49 -> efeitoItem_49();     //Provisões (comida)
            case 50 -> efeitoItem_50();     //Espada comum (inicial)
            case 54 -> efeitoItem_54();     //Elmo

            default -> false;
        };
    }

    /// REGRA DO ESCUDO: Se o resultado for 4, 5 ou 6, os danos causados a você serão reduzidos em 1 ponto
    private boolean efeitoItem_28() {
        var resultadoDado = Util.rolarDados(6,1);

        if (resultadoDado >= 4)
            return true;
        else
            return false;
    }

    private boolean efeitoItem_54() {

        ///TESTAR AO CLICAR NO ITEM NA BOLSA E VÊ SE EQUIPA
        Item item = DadosLivroCarregado.getMapItem().get( ItensMapeamento.ELMO_DE_BRONZE.getIdItem() );
        UtilPersonagem.equiparDesequiparItem(item);

        return false;
    }

    private boolean efeitoItem_12() {

        ///TESTAR AO CLICAR NO ITEM NA BOLSA E VÊ SE EQUIPA
        Item item = DadosLivroCarregado.getMapItem().get( ItensMapeamento.BRACADEIRA_DA_FORCA.getIdItem() );
        UtilPersonagem.equiparDesequiparItem(item);

        return false;
    }

    //apenas informa que a Poção Antiveneno foi consumida
    private boolean efeitoItem_3() {
        boolean retorno = false;

        //Cada seção que a poção antiveneno é necessária
        switch (secao.getCodSecaoLivro()){

            case 16 -> retorno = AcoesSecao_16.consumiuPocaoAntiveneno();  //seta variável e remove item

        }
        return retorno;
    }

    ///Equipar o Anel da Luz(colocar no dedo)
    private boolean efeitoItem_1() {
        //Recupera o item anel da luz(código 1)
        Item item = DadosLivroCarregado.getMapItem().get( ItensMapeamento.ANEL_DA_LUZ.getIdItem() );

        //Se o anel já equipado, coloca-o na bolsa novamente
        if (UtilBolsa.verificarExistenciaDeItemEquipado(ItensMapeamento.ANEL_DA_LUZ.getIdItem())) {
            DadosLivroCarregado.removeItemEquipado(item);
            DadosLivroCarregado.getBolsa().add(item);
            return false;
        }

        //Adiciona aos equipados
        DadosLivroCarregado.getItensEquipados().add(item);

        //Remove da bolsa
        DadosLivroCarregado.removeItemBolsa(item);

        return true;
    }

    ///Espada magnífica deve ser equipada e desequipar a espada comum.
    ///E acrescentar +2 no índice de habilidade (seção 70)
    private boolean efeitoItem_10() {
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        //Recupera o item
        Item espadaMagnifica = DadosLivroCarregado.getMapItem().get(ItensMapeamento.ESPADA_MAGNIFICA.getIdItem());

        if ( UtilBolsa.verificarExistenciaDeItemEquipado( espadaMagnifica.getIdItem()) )
            return false;

        //desequipar a espada incial e incluir na bolsa
        Item espadaInicial = DadosLivroCarregado.getMapItem().get(ItensMapeamento.ESPADA.getIdItem());
        DadosLivroCarregado.removeItemEquipado(espadaInicial);
        personagem.getBolsa().add(espadaInicial);

        //Equipa a espada magnífica(idItem 10)
        personagem.getItensEquipados().add(espadaMagnifica);
        UtilBolsa.removerItem(ItensMapeamento.ESPADA_MAGNIFICA.getIdItem());

        //Efeito mágico da espada(idItem=10): aumenta a habilidade atual do personagem em 2 pontos(seção 70)
        personagem.setHabilidadeAtual(personagem.getHabilidadeAtual() + 2);

        return true;
    }

    private boolean efeitoItem_50() {
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        //Recupera o item espada comum
        Item item = DadosLivroCarregado.getMapItem().get(ItensMapeamento.ESPADA.getIdItem());

        if ( UtilBolsa.verificarExistenciaDeItemEquipado( item.getIdItem()) )
            return false;


        //desequipar a espada magnífica e incluir na bolsa
        Item espadaMagnifica = DadosLivroCarregado.getMapItem().get(ItensMapeamento.ESPADA_MAGNIFICA.getIdItem());
        DadosLivroCarregado.removeItemEquipado(espadaMagnifica);
        personagem.getBolsa().add(espadaMagnifica);

        //Equipa a espada comum(idItem 50)
        personagem.getItensEquipados().add(item);
        UtilBolsa.removerItem(ItensMapeamento.ESPADA.getIdItem());

        //Efeito mágico da espada(idItem=10): aumenta a habilidade atual do personagem em 2 pontos(seção 70)
        personagem.setHabilidadeAtual(personagem.getHabilidadeAtual() - 2);

        return true;
    }

    ///Poção da fortuna(sorte)
    private boolean efeitoItem_47() {
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        //Se personagem com índice no máximo, não fazer nada
        if (UtilPersonagem.retornaDiferencaEntreSorteMaxEAtual() == 0){
            return false;
        }

        //Achar o item e removê-lo da bolsa
        UtilBolsa.removerItem(ItensMapeamento.POCAO_DA_FORTUNA.getIdItem());

        //Aumenta em 1 o nível inicial(máximo) de sorte
        personagem.setSorteMax( personagem.getSorteMax() + 1 );

        //Recupera índice ao valor máximo (valor inicial do momento da criação do personagem)
        personagem.setSorteAtual( personagem.getSorteMax() );

        return true;
    }

    ///Poção de força(energia)
    private boolean efeitoItem_46() {
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        //Se personagem com índice no máximo, não fazer nada
        if (UtilPersonagem.retornaDiferencaEntreEnergiaMaxEAtual() == 0){
            return false;
        }

        //Achar o item e removê-lo da bolsa
        UtilBolsa.removerItem(ItensMapeamento.POCAO_DE_ENERGIA.getIdItem());

        //Recupera índice ao valor máximo (valor inicial do momento da criação do personagem)
        personagem.setEnergiaAtual( personagem.getEnergiaMax() );

        return true;
    }

    ///Poção de Habilidade inicial
    private boolean efeitoItem_45(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        //Se personagem com índice no máximo, não fazer nada
        if (UtilPersonagem.retornaDiferencaEntreHabilidadeMaxEAtual() == 0){
            return false;
        }

        //Achar o item e removê-lo da bolsa
        UtilBolsa.removerItem(ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem());

        //Recupera índice ao valor máximo (valor inicial do momento da criação do personagem)
        personagem.setHabilidadeAtual( personagem.getHabilidadeMax() );

        return true;
    }

    ///Provisão
    private boolean efeitoItem_49(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        //Se personagem com energia máxima, não fazer nada
        if (UtilPersonagem.retornaDiferencaEntreEnergiaMaxEAtual() == 0){
            return false;
        }

        //Achar um item do tipo provisão e removê-lo da bolsa
        UtilBolsa.removerItem(ItensMapeamento.PROVISAO.getIdItem());

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
