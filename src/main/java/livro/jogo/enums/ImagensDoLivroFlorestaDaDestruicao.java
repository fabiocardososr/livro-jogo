package livro.jogo.enums;

public enum ImagensDoLivroFlorestaDaDestruicao {

    /*** IMAGENS DE ITENS ***/
    COROA_DE_OURO("livros/florestadadestruicao/imagens/itens/item_2.png"),
    FILTROS_NASAIS("livros/florestadadestruicao/imagens/itens/item_4.png"),
    CHAVE_DE_PRATA("livros/florestadadestruicao/imagens/itens/item_5.png"),
    POCAO_DE_HABILIDADE("livros/florestadadestruicao/imagens/itens/item_45.png"),
    POCAO_DE_ENERGIA("livros/florestadadestruicao/imagens/itens/item_46.png"),
    POCAO_DE_SORTE("livros/florestadadestruicao/imagens/itens/item_47.png"),
    PROVISOES("livros/florestadadestruicao/imagens/itens/item_49.png"),
    ESPADA_INICIAL("livros/florestadadestruicao/imagens/itens/item_50.png"),
    ARMADURA_DE_COURO_INICIAL("livros/florestadadestruicao/imagens/itens/item_51.png"),


    /***DEMAIS IMAGENS***/
    PERGAMINHO_FUNDO_CAD_PERSONAGEM("livros/florestadadestruicao/imagens/pergaminho_painel_cadPersonagem.png"),
    PERGAMINHO_FAIXA("livros/florestadadestruicao/imagens/pergaminho_inferior_secao.png"),
    PERGAMINHO_ABERTO("livros/florestadadestruicao/imagens/pergaminho_aberto7.png"),
    FAIXA_INDICE_TELA_SECAO("livros/florestadadestruicao/imagens/faixa9.png"),
    FAIXA_NOME_PERSONAGEM_TELA_SECAO("livros/florestadadestruicao/imagens/faixa2.png"),
    FAIXA_8("livros/florestadadestruicao/imagens/faixa_8.png"),
    FAIXA_10("livros/florestadadestruicao/imagens/faixa_10.png"),
    FAIXA_2("livros/florestadadestruicao/imagens/faixa2.png"),
    FAIXA_OPCOES("livros/florestadadestruicao/imagens/faixa_opcoes.png"),
    FAIXA_3("livros/florestadadestruicao/imagens/faixa3.png"), //Aqui usei na tela de regras. Diminui os espaços em branco da imagem e nãol quis alterar o arquivo "faixa.png"
    FAIXA_VERTICAL_1("livros/florestadadestruicao/imagens/faixa_vertical_1.png"),
    BOLSA("livros/florestadadestruicao/imagens/bolsa.png"),
    GIF_ROLANDO_DADOS("livros/florestadadestruicao/imagens/rolando_dados.gif"),
    POCAO_DE_VAZIA("livros/florestadadestruicao/imagens/itens/pocao_vazia.png"),
    MAPA_DA_FLORESTA("livros/florestadadestruicao/imagens/mapainicial.png"),
    BARBARO("livros/florestadadestruicao/imagens/barbaro.png"),
    BARBARA("livros/florestadadestruicao/imagens/barbara.png"),
    MOLDURA("livros/florestadadestruicao/imagens/moldura1.png"),
    MOLDURA_ANOTACOES("livros/florestadadestruicao/imagens/moldura_anotacoes.png"),
    MOLDURA_2("livros/florestadadestruicao/imagens/moldura_2.png"),
    MOLDURA_4("livros/florestadadestruicao/imagens/moldura_4.png"),
    MOLDURA_5("livros/florestadadestruicao/imagens/moldura_5.png"),
    MOLDURA_6("livros/florestadadestruicao/imagens/moldura_6.png"),
    MOLDURA_TELA_PRINCIPAL("livros/florestadadestruicao/imagens/moldura_tela_principal.png"),
    BUSSOLA("livros/florestadadestruicao/imagens/bussola.png"),
    ANOTACOES("livros/florestadadestruicao/imagens/anotacoes.png"),
    FAIXA("livros/florestadadestruicao/imagens/faixa.png"),
    FAIXA_SUPERIOR_ESQUERDA("livros/florestadadestruicao/imagens/faixa_superior_esquerda.png"),
    FAIXA_SUPERIOR_DIREITA("livros/florestadadestruicao/imagens/faixa_superior_direita.png"),
    FAIXA_INFERIOR_ESQUERDA("livros/florestadadestruicao/imagens/faixa_inferior_esquerda.png"),
    FAIXA_INFERIOR_DIREITA("livros/florestadadestruicao/imagens/faixa_inferior_direita.png"),
    PORTA_SAIR("livros/florestadadestruicao/imagens/porta_sair.png"),
    SINAL_SOMA("livros/florestadadestruicao/imagens/sinal_soma.png"),
    SINAL_MENOS("livros/florestadadestruicao/imagens/sinal_menos.png"),
    FUNDO_REGRAS_OPCOES("livros/florestadadestruicao/imagens/fundo_regra_opcoes.png"),
    SETA_APONTA_DIREITA("livros/florestadadestruicao/imagens/dedo_apontando_direita.png"),
    DOIS_DADOS("livros/florestadadestruicao/imagens/dois_dados.png"),
    UM_DADOS("livros/florestadadestruicao/imagens/um_dado.png"),
    INTERROGACAO("livros/florestadadestruicao/imagens/interrogacao.png"),
    FUNDO_BOLSA("livros/florestadadestruicao/imagens/fundo_bolsa.png"),
    FUNDO_BOLSA_LISTA("livros/florestadadestruicao/imagens/fundo_bolsa_lista.png"),
    SIMBOLO_VOZ("livros/florestadadestruicao/imagens/voz.png"),
    SIMBOLO_VOZ_PARAR("livros/florestadadestruicao/imagens/parar_voz.png")
    ;

    private final String enderecoImagem;
    ImagensDoLivroFlorestaDaDestruicao(String endereco) {
        enderecoImagem = endereco;
    }

    public String getEnderecoImagem() {
        return enderecoImagem;
    }
}
