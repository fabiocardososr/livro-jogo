package livro.jogo.enums;

public enum PocoesIniciais {
    POCAO_DE_HABILIDADE(45),
    POCAO_DE_ENERGIA(46),
    POCAO_DA_FORTUNA(47);

    private final int idItem;
    PocoesIniciais(int idItem) {
        this.idItem = idItem;
    }

    public int getIdItemPocao() {
        return this.idItem;
    }
}
