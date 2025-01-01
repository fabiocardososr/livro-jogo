package livro.jogo.enums;

public enum ImagensDoLivroFlorestaDaDestruicao {
    ARMADURA_DE_COURO_INICIAL("livros/florestadadestruicao/imagens/armadura_de_couro.png"),
    PROVISOES("livros/florestadadestruicao/imagens/provisao.png"),
    PERGAMINHO_FUNDO_CAD_PERSONAGEM("livros/florestadadestruicao/imagens/pergaminho_painel_cadPersonagem.png"),
    PERGAMINHO_FAIXA("livros/florestadadestruicao/imagens/pergaminho_inferior_secao.png"),
    PERGAMINHO_ABERTO("livros/florestadadestruicao/imagens/pergaminho_aberto7.png"),
    FAIXA_INDICE_TELA_SECAO("livros/florestadadestruicao/imagens/faixa9.png"),
    FAIXA_NOME_PERSONAGEM_TELA_SECAO("livros/florestadadestruicao/imagens/faixa2.png"),
    BOLSA("livros/florestadadestruicao/imagens/bolsa.png"),
    ESPADA_INICIAL("livros/florestadadestruicao/imagens/espada_inicial.png"),
    PERGAMINHO_FUNDO_CAD_PERSONAGEM_INDICES("livros/florestadadestruicao/imagens/pergaminho.png"),
    GIF_ROLANDO_DADOS("livros/florestadadestruicao/imagens/rolando_dados.gif"),
    //IMAGEM_DEFAULT_FLORESTA("livros/florestadadestruicao/imagens/floresta.jpg"),
    POCAO_DE_HABILIDADE("livros/florestadadestruicao/imagens/pocao_de_habilidade.png"),
    POCAO_DE_ENERGIA("livros/florestadadestruicao/imagens/pocao_de_energia.png"),
    POCAO_DE_SORTE("livros/florestadadestruicao/imagens/pocao_de_sorte.png"),
    POCAO_DE_VAZIA("livros/florestadadestruicao/imagens/pocao_vazia.png"),
    MAPA_DA_FLORESTA("livros/florestadadestruicao/imagens/mapainicial.png"),
    BARBARO("livros/florestadadestruicao/imagens/barbaro.png"),
    BARBARA("livros/florestadadestruicao/imagens/barbara.png"),
    MOLDURA("livros/florestadadestruicao/imagens/moldura1.png"),
    MOLDURA_2("livros/florestadadestruicao/imagens/moldura_2.png"),
    MOLDURA_3("livros/florestadadestruicao/imagens/moldura_3.png"),
    MOLDURA_TELA_PRINCIPAL("livros/florestadadestruicao/imagens/moldura_tela_principal.png"),
    BUSSOLA("livros/florestadadestruicao/imagens/bussola.png"),
    ANOTACOES("livros/florestadadestruicao/imagens/anotacoes.png"),
    FAIXA("livros/florestadadestruicao/imagens/faixa.png"),
    FAIXA_2("livros/florestadadestruicao/imagens/faixa2.png"),
    FAIXA_3("livros/florestadadestruicao/imagens/faixa3.png"), //Aqui usei na tela de regras. Diminui os espaços em branco da imagem e nãol quis alterar o arquivo "faixa.png"
    FAIXA_SUPERIOR_ESQUERDA("livros/florestadadestruicao/imagens/faixa_superior_esquerda.png"),
    FAIXA_SUPERIOR_DIREITA("livros/florestadadestruicao/imagens/faixa_superior_direita.png"),
    FAIXA_INFERIOR_ESQUERDA("livros/florestadadestruicao/imagens/faixa_inferior_esquerda.png"),
    FAIXA_INFERIOR_DIREITA("livros/florestadadestruicao/imagens/faixa_inferior_direita.png"),
    PORTA_SAIR("livros/florestadadestruicao/imagens/porta_sair.png"),
    SINAL_SOMA("livros/florestadadestruicao/imagens/sinal_soma.png"),
    SINAL_MENOS("livros/florestadadestruicao/imagens/sinal_menos.png"),
    FUNDO_REGRAS_OPCOES("livros/florestadadestruicao/imagens/fundo_regra_opcoes.png"),
    SETA_APONTA_DIREITA("livros/florestadadestruicao/imagens/dedo_apontando_direita.png")
    ;

    private final String enderecoImagem;
    ImagensDoLivroFlorestaDaDestruicao(String endereco) {
        enderecoImagem = endereco;
    }

    public String getEnderecoImagem() {
        return enderecoImagem;
    }
}
