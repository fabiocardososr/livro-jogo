package livro.jogo.enums;

public enum ItensMapeamento {
    ANEL_DA_LUZ("itens/item_1.json",1),
    COROA_DE_OURO("itens/item_2.json",2),
    POCAO_ANTIVENENO("itens/item_3.json",3),
    FILTROS_NASAIS("itens/item_4.json",4),
    CHAVE_DE_PRATA("itens/item_5.json",5),
    CABECA_DE_MARTELO_DOS_ANOES("itens/item_6.json",6),
    AGUA_BENTA("itens/item_7.json",7),
    POCAO_CONTROLE_DAS_PLANTAS("itens/item_8.json",8),
    SINO_DE_METAL("itens/item_9.json",9),
    ESPADA_MAGNIFICA("itens/item_10.json",10),
    POEIRA_DA_LEVITACAO("itens/item_11.json",11),
    BRACADEIRA_DA_FORCA("itens/item_12.json",12),
    POCAO_DA_IMOBILIDADE("itens/item_13.json",13),
    BELADONA("itens/item_14.json",14),
    COLAR_OLHO_DE_AMBAR("itens/item_15.json",15),
    POCAO_CONTROLE_DOS_INSETOS("itens/item_16.json",16),
    CORDA_DE_ESCALADA("itens/item_17.json",17),
    ANEL_DE_OURO_COM_GRANDE_ESMERALDA("itens/item_18.json",18),
    CAPSULA_DE_FOGO("itens/item_19.json",19),
    COLEIRA_DE_COURO_COM_APLICACAO_EM_OURO("itens/item_20.json",20),
    FLAUTA_DO_SONO_DO_DRAGAO("itens/item_21.json",21),
    LUVA_DE_DESTREZA_DE_ARREMESSO("itens/item_22.json",22),
    FITA_DE_CABECA_DA_CONCENTRACAO("itens/item_23.json",23),
    GRANDE_ANEL_DE_OURO("itens/item_24.json",24),
    CABO_DO_MARTELO_DE_GUERRA_DOS_ANOES("itens/item_25.json",25),
    BRINCOS_DE_OURO("itens/item_26.json",26),
    POCAO_CURATIVA("itens/item_27.json",27),
    ESCUDO_DE_FERRO("itens/item_28.json",28),
    DARDO_FEITO_DE_PRATA("itens/item_29.json",29),
    LINGOTE_DE_OURO("itens/item_30.json",30),
    BOTAS_SALTADORAS("itens/item_31.json",31),
    REDE_DE_APRISIONAMENTO("itens/item_32.json",32),
    POCAO_DA_FORCA("itens/item_33.json",33),
    VARA_DE_ENCONTRAR_AGUA("itens/item_34.json",34),
    CABECA_DE_ALHO("itens/item_35.json",35),
    MEDALHAO_DE_OURO("itens/item_36.json",36),
    OURO("itens/item_37.json",37),
    PULSEIRA_DE_COBRE("itens/item_38.json",38),
    MANOPLA_DE_FERRO("itens/item_39.json",39),
    FACA_DE_ARREMESSO("itens/item_40.json",40),
    ANEL_DA_LENTIDAO("itens/item_41.json",41),
    MEDALHAO_DE_OURO_EM_UMA_FITA_DE_SEDA("itens/item_42.json",42),
    CAIXA_DE_PRATA("itens/item_43.json",43),
    DENTE_DE_DRAGAO("itens/item_44.json",44),
    POCAO_DE_HABILIDADE("itens/item_45.json", 45),
    POCAO_DE_ENERGIA("itens/item_46.json",46),
    POCAO_DA_FORTUNA("itens/item_47.json",47),
    APITO_DE_MADEIRA("itens/item_48.json",48),
    PROVISAO("itens/item_49.json",49),
    ESPADA("itens/item_50.json",50),
    ARMADURA_DE_COURO("itens/item_51.json",51),
    BISCOITO_CHEIO_DE_BICHOS("itens/item_52.json",52),
    COLAR_DE_CRANIO_DE_CAMUNDONGOS("itens/item_53.json",53),
    ELMO_DE_BRONZE("imagens/item_54.png",54),
    MAO_DE_ARGILA("itens/item_53.json",55),
    PEDACO_BICHO_ROCHEDO("itens/item_56.json",56),
    CENOURA("itens/item_57.json",57)

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
