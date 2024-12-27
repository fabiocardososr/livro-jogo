package livro.jogo.enums;

public enum ItensIniciaisJson {
    POCAO_HABILIDADE("livros/florestadadestruicao/itensIniciais/item_45.json"),
    POCAO_DE_FORCA("livros/florestadadestruicao/itensIniciais/item_46.json"),
    POCAO_DA_FORTUNA("livros/florestadadestruicao/itensIniciais/item_47.json"),
    PROVISAO("livros/florestadadestruicao/itensIniciais/item_49.json"),
    ESPADA("livros/florestadadestruicao/itensIniciais/item_50.json"),
    ARMADURA_DE_COURO("livros/florestadadestruicao/itensIniciais/item_51.json")
    ;
    private String enderecoJson;
    ItensIniciaisJson(String enderecoJson) {
        this.enderecoJson = enderecoJson;
    }

    public String getEnderecoJson() {
        return enderecoJson;
    }
}
