package livro.jogo.enums;

public enum ImagensDoLivroFlorestaDaDestruicao {
    ARMADURA_DE_COURO_INICIAL("livros/florestadadestruicao/imagens/armadura_de_couro.png"),
    PROVISOES("livros/florestadadestruicao/imagens/provisao.png"),
    PERGAMINHO_FUNDO_CAD_PERSONAGEM("livros/florestadadestruicao/imagens/pergaminho_painel_cadPersonagem.png"),
    PERGAMINHO_FAIXA("livros/florestadadestruicao/imagens/pergaminho_inferior_secao.png"),
    PERGAMINHO_ABERTO("livros/florestadadestruicao/imagens/pergaminho_aberto.png"),
    BOLSA("livros/florestadadestruicao/imagens/bolsa.png"),
    ESPADA_INICIAL("livros/florestadadestruicao/imagens/espada_inicial.png"),
    PERGAMINHO_FUNDO_CAD_PERSONAGEM_INDICES("livros/florestadadestruicao/imagens/pergaminho.png"),
    GIF_ROLANDO_DADOS("livros/florestadadestruicao/imagens/rolando_dados.gif"),
    IMAGEM_DEFAULT_FLORESTA("livros/florestadadestruicao/imagens/floresta.jpg"),
    POCAO_DE_HABILIDADE("livros/florestadadestruicao/imagens/pocao_de_habilidade.png"),
    POCAO_DE_ENERGIA("livros/florestadadestruicao/imagens/pocao_de_energia.png"),
    POCAO_DE_SORTE("livros/florestadadestruicao/imagens/pocao_de_sorte.png"),
    MAPA_DA_FLORESTA("livros/florestadadestruicao/imagens/mapainicial.png"),
    BARBARO("livros/florestadadestruicao/imagens/barbaro.png"),
    BARBARA("livros/florestadadestruicao/imagens/barbara.png"),
    MOLDURA("livros/florestadadestruicao/imagens/moldura.png")
    ;

    private final String enderecoImagem;
    ImagensDoLivroFlorestaDaDestruicao(String endereco) {
        enderecoImagem = endereco;
    }

    public String getEnderecoImagem() {
        return enderecoImagem;
    }
}
