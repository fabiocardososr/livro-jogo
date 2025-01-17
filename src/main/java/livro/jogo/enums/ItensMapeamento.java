package livro.jogo.enums;

public enum ItensMapeamento {
    ANEL_DA_LUZ("livros/florestadadestruicao/itens/item_1.json",1),
    COROA_DE_OURO("livros/florestadadestruicao/itens/item_2.json",2),
    POCAO_ANTIVENENO("livros/florestadadestruicao/itens/item_3.json",3),
    FILTROS_NASAIS("livros/florestadadestruicao/itens/item_4.json",4),
    CHAVE_DE_PRATA("livros/florestadadestruicao/itens/item_5.json",5),
    CABECA_DE_MARTELO_DOS_ANOES("livros/florestadadestruicao/itens/item_6.json",6),
    AGUA_BENTA("livros/florestadadestruicao/itens/item_7.json",7),
    POCAO_CONTROLE_DAS_PLANTAS("livros/florestadadestruicao/itens/item_8.json",8),
    SINO_DE_METAL("livros/florestadadestruicao/itens/item_9.json",9),
    ESPADA_MAGNIFICA("livros/florestadadestruicao/itens/item_10.json",10),
    POEIRA_DA_LEVITACAO("livros/florestadadestruicao/itens/item_11.json",11),
    BRACADEIRA_DA_FORCA("livros/florestadadestruicao/itens/item_12.json",12),
    POCAO_DA_IMOBILIDADE("livros/florestadadestruicao/itens/item_13.json",13),
    BELADONA("livros/florestadadestruicao/itens/item_14.json",14),
    COLAR_OLHO_DE_AMBAR("livros/florestadadestruicao/itens/item_15.json",15),
    POCAO_CONTROLE_DOS_INSETOS("livros/florestadadestruicao/itens/item_16.json",16),
    CORDA_DE_ESCALADA("livros/florestadadestruicao/itens/item_17.json",17),
    ANEL_DE_OURO_COM_GRANDE_ESMERALDA("livros/florestadadestruicao/itens/item_18.json",18),
    CAPSULA_DE_FOGO("livros/florestadadestruicao/itens/item_19.json",19),
    COLEIRA_DE_COURO_COM_APLICACAO_EM_OURO("livros/florestadadestruicao/itens/item_20.json",20),
    FLAUTA_DO_SONO_DO_DRAGAO("livros/florestadadestruicao/itens/item_21.json",21),
    LUVA_DE_DESTREZA_DE_ARREMESSO("livros/florestadadestruicao/itens/item_22.json",22),
    FITA_DE_CABECA_DA_CONCENTRACAO("livros/florestadadestruicao/itens/item_23.json",23),
    GRANDE_ANEL_DE_OURO("livros/florestadadestruicao/itens/item_24.json",24),
    CABO_DO_MARTELO_DE_GUERRA_DOS_ANOES("livros/florestadadestruicao/itens/item_25.json",25),
    BRINCOS_DE_OURO("livros/florestadadestruicao/itens/item_26.json",26),
    POCAO_CURATIVA("livros/florestadadestruicao/itens/item_27.json",27),
    ESCUDO_DE_FERRO("livros/florestadadestruicao/itens/item_28.json",28),
    DARDO_FEITO_DE_PRATA("livros/florestadadestruicao/itens/item_29.json",29),
    LINGOTE_DE_OURO("livros/florestadadestruicao/itens/item_30.json",31),
    BOTAS_SALTADORAS("livros/florestadadestruicao/itens/item_31.json",31),
    REDE_DE_APRISIONAMENTO("livros/florestadadestruicao/itens/item_32.json",32),
    POCAO_DA_FORCA("livros/florestadadestruicao/itens/item_33.json",33),
    VARA_DE_ENCONTRAR_AGUA("livros/florestadadestruicao/itens/item_34.json",34),
    CABECA_DE_ALHO("livros/florestadadestruicao/itens/item_35.json",35),
    MEDALHAO_DE_OURO("livros/florestadadestruicao/itens/item_36.json",36),
    OURO("livros/florestadadestruicao/itens/item_37.json",37),
    PULSEIRA_DE_COBRE("livros/florestadadestruicao/itens/item_38.json",38),
    MANOPLA_DE_FERRO("livros/florestadadestruicao/itens/item_39.json",39),
    FACA_DE_ARREMESSO("livros/florestadadestruicao/itens/item_40.json",40),
    ANEL_DE_OURO("livros/florestadadestruicao/itens/item_41.json",41),
    MEDALHAO_DE_OURO_EM_UMA_FITA_DE_SEDA("livros/florestadadestruicao/itens/item_42.json",42),
    CAIXA_DE_PRATA("livros/florestadadestruicao/itens/item_43.json",43),
    DENTE_DE_DRAGAO("livros/florestadadestruicao/itens/item_44.json",44),
    POCAO_DE_HABILIDADE("livros/florestadadestruicao/itens/item_45.json", 45),
    POCAO_DE_ENERGIA("livros/florestadadestruicao/itens/item_46.json",46),
    POCAO_DA_FORTUNA("livros/florestadadestruicao/itens/item_47.json",47),
    APITO_DE_MADEIRA("livros/florestadadestruicao/itens/item_48.json",48),
    PROVISAO("livros/florestadadestruicao/itens/item_49.json",49),
    ESPADA("livros/florestadadestruicao/itens/item_50.json",50),
    ARMADURA_DE_COURO("livros/florestadadestruicao/itens/item_51.json",51),
    BISCOITO_CHEIO_DE_BICHOS("livros/florestadadestruicao/itens/item_52.json",52),
    COLAR_DE_CRANIO_DE_CAMUNDONGOS("livros/florestadadestruicao/itens/item_52.json",53)

    ;
    private String enderecoJson;
    private int idItem;

    ItensMapeamento(String enderecoJson, int idItem) {
        this.enderecoJson = enderecoJson;
        this.idItem = idItem;
    }

    public String getEnderecoJson() {
        return enderecoJson;
    }

    public int getIdItem(){
        return idItem;
    }

}
