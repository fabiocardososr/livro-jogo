package livro.jogo.enums;

public enum ItensMapeamento {
    POCAO_DE_HABILIDADE(45),
    POCAO_DE_ENERGIA(46),
    POCAO_DA_FORTUNA(47),
    PROVISAO(49)
    ;


    private final int idItem;
    ItensMapeamento(int idItem) {
        this.idItem = idItem;
    }

    public int getIdItem() {
        return this.idItem;
    }
}
