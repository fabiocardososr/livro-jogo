package livro.jogo.enums;

public enum ItensJson {
    POCAO_HABILIDADE("livros/florestadadestruicao/itens/item_45.json"),
    POCAO_DE_FORCA("livros/florestadadestruicao/itens/item_46.json"),
    POCAO_DA_FORTUNA("livros/florestadadestruicao/itens/item_47.json"),
    PROVISAO("livros/florestadadestruicao/itens/item_49.json"),
    ESPADA("livros/florestadadestruicao/itens/item_50.json"),
    ARMADURA_DE_COURO("livros/florestadadestruicao/itens/item_51.json"),
    ANEL_DA_LUZ("livros/florestadadestruicao/itens/item_1.json"),
    COROA_DE_OURO("livros/florestadadestruicao/itens/item_2.json"),
    POCAO_ANTIVENENO("livros/florestadadestruicao/itens/item_3.json"),
    FILTROS_NASAIS("livros/florestadadestruicao/itens/item_4.json"),
    CHAVE_DE_PRATA("livros/florestadadestruicao/itens/item_5.json"),
    CABECA_DE_MARTELO_DOS_ANOES("livros/florestadadestruicao/itens/item_6.json"),
    AGUA_BENTA("livros/florestadadestruicao/itens/item_7.json"),
    POCAO_CONTROLE_DAS_PLANTAS("livros/florestadadestruicao/itens/item_8.json"),
    SINO_DE_METAL("livros/florestadadestruicao/itens/item_9.json"),
    ESPADA_MAGNIFICA("livros/florestadadestruicao/itens/item_10.json"),
    POEIRA_DA_LEVITACAO("livros/florestadadestruicao/itens/item_11.json"),
    BRACADEIRA_DA_FORCA("livros/florestadadestruicao/itens/item_12.json"),
    POCAO_DA_IMOBILIDADE("livros/florestadadestruicao/itens/item_13.json"),
    BELADONA("livros/florestadadestruicao/itens/item_14.json"),
    COLAR_OLHO_DE_AMBAR("livros/florestadadestruicao/itens/item_15.json"),
    POCAO_CONTROLE_DOS_INSETOS("livros/florestadadestruicao/itens/item_16.json"),
    CORDA_DE_ESCALADA("livros/florestadadestruicao/itens/item_17.json"),
    ANEL_DE_OURO_COM_GRANDE_ESMERALDA("livros/florestadadestruicao/itens/item_18.json"),
    CAPSULA_DE_FOGO("livros/florestadadestruicao/itens/item_19.json"),
    COLEIRA_DE_COURO_COM_APLICACAO_EM_OURO("livros/florestadadestruicao/itens/item_20.json"),
    FLAUTA_DO_SONO_DO_DRAGAO("livros/florestadadestruicao/itens/item_21.json"),
    LUVA_DE_DESTREZA_DE_ARREMESSO("livros/florestadadestruicao/itens/item_22.json"),
    FITA_DE_CABECA_DA_CONCENTRACAO("livros/florestadadestruicao/itens/item_23.json"),
    GRANDE_ANEL_DE_OURO("livros/florestadadestruicao/itens/item_24.json"),
    CABO_DO_MARTELO_DE_GUERRA_DOS_ANOES("livros/florestadadestruicao/itens/item_25.json"),
    BRINCOS_DE_OURO("livros/florestadadestruicao/itens/item_26.json"),
    POCAO_CURATIVA("livros/florestadadestruicao/itens/item_27.json"),
    ESCUDO_DE_FERRO("livros/florestadadestruicao/itens/item_28.json"),



    ;
    private String enderecoJson;
    ItensJson(String enderecoJson) {
        this.enderecoJson = enderecoJson;
    }

    public String getEnderecoJson() {
        return enderecoJson;
    }
}
