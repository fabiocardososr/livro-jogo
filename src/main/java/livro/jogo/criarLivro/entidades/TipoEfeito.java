package livro.jogo.criarLivro.entidades;

public enum TipoEfeito {
    HABILIDADE(1), ENERGIA(2), SORTE(3),NENHUM(4);

    public final int tipoEfeito;
    TipoEfeito(int efeito) {
        tipoEfeito = efeito;
    }
}


