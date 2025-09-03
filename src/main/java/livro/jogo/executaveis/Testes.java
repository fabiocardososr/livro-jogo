package livro.jogo.executaveis;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;


import java.util.ArrayList;

public class Testes {
    public static void main(String[] args) {

        //Carrega o livro
        CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

        var itensEquipados = recuperaItensIniciaisEquipados();
        var bolsa = adicionaItensNaBolsaParaTeste();

        Personagem personagem = new Personagem("Fábio",1,22,40,
                30, bolsa,itensEquipados,1);
        personagem.setAnotacoes("Existe um caminho seguro para atravessar a Floresta da Darkwood, e você precisará fazer várias tentativas até encontrá-lo. Tome nota e faça um mapa na medida em que vai explorando - este mapa será inestimável em aventuras futuras e permitirá que você progrida rapidamente através de áreas inexploradas.\n\nExiste um caminho seguro para atravessar a Floresta da Darkwood, e você precisará fazer várias tentativas até encontrá-lo. Tome nota e faça um mapa na medida em que vai explorando - este mapa será inestimável em aventuras futuras e permitirá que você progrida rapidamente através de áreas inexploradas.");
        personagem.setQuantidadeOuro(10);
        personagem.setHabilidadeAtual(0);
        personagem.setEnergiaAtual(10);
        personagem.setSorteAtual(2);
        DadosLivroCarregado.setPersonagem(personagem);

        //personagem.setMaldicaoLobisomem(false);

        Secao secao = DadosLivroCarregado.getLivro().getMapSecao().get(227);
        CarregarTelas.carregarSecao(secao);


//        System.out.println("Tamanho da bolsa: "+bolsa.size());
//       for (int i=0; i<100; i++)
//            System.out.println( 1 + new Random().nextInt(2 ) );

       // AcoesSecao_36.ladraoRouba();

//        UtilItens.retornaModificadorQueInfluenciaNaRolagemDeDadosDaHabilidade(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_54.json"));

//        if ( AcoesSecao_28.bracadeiraDaForcaEquipada() )
//            System.out.println("ENTROU");
//        else
//            System.out.println("não ENTROU");

        //AcoesSecao_12.recuperaEspadaDoGnomo();

       // int forca = UtilItens.retornaModificadorQueInfluenciaNaRolagemDeDadosDaHabilidade(itensEquipados.getFirst());
//        int forca2 = UtilItens.retornaModificadorQueInfluenciaNaRolagemDeDadosDaHabilidade(itensEquipados.getFirst());

        //TelaBasica.mostrarDadosRolando(4000, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);

        //Tela batalha
//        Inimigo inimigo = new Inimigo(1,"Enxame 1",7,3,
//                "livros/florestadadestruicao/imagens/inimigos/enxame.png");
//        TelaBatalha telaBatalha = new TelaBatalha(inimigo, null, true);
//        telaBatalha.setVisible(true);

    }

    public static ArrayList<Item> recuperaItensIniciaisEquipados() {
        //ObjectMapper objMapper = new ObjectMapper();
        var itensEquipados = new ArrayList<Item>();

        //Braçadeira da força
//        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_12.json"));

       //for (int i=1; i<=58; i++)
      // itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_"+i+".json"));
//
        //Equipando uma espada magnífica(10)
       // itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_10.json"));
//
//        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_38.json"));
//
//
       // itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_57.json"));
//
//        //Elmo
//        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_54.json"));

        //Colar olho de Âmbar
       // itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_15.json"));

       // itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_39.json"));

        //itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_28.json"));




        return itensEquipados;

    }

    public static ArrayList<Item> adicionaItensNaBolsaParaTeste() {
        ObjectMapper objMapper = new ObjectMapper();
        var bolsa = new ArrayList<Item>();


//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_1.json"));
//
//
//
        for (int i=1; i<=9; i++)
            bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
              "livros/florestadadestruicao/itens/item_"+i+".json"));

        //for (int i=1; i<=57; i++)
            bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
                    "livros/florestadadestruicao/itens/item_13.json"));
//
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_57.json"));
////
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//               "livros/florestadadestruicao/itens/item_5.json"));
//////
//       bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//               "livros/florestadadestruicao/itens/item_10.json"));
////
//       bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//               "livros/florestadadestruicao/itens/item_16.json"));
//
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_25.json"));

        return bolsa;
    }
}
