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

    ;
    private String enderecoJson;
    ItensJson(String enderecoJson) {
        this.enderecoJson = enderecoJson;
    }

    public String getEnderecoJson() {
        return enderecoJson;
    }
}
