package livro.jogo.executaveis;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;


import java.util.ArrayList;
import java.util.Random;

public class Testes {
    public static void main(String[] args) {

        //Carrega o livro
        CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

        var itensEquipados = recuperaItensIniciaisEquipados();
        var bolsa = adicionaItensNaBolsaParaTeste();

        Personagem personagem = new Personagem("Fábio",1,22,10,
                20,
                bolsa,itensEquipados,1);
        personagem.setAnotacoes("Existe um caminho seguro para atravessar a Floresta da Darkwood, e você precisará fazer várias tentativas até encontrá-lo. Tome nota e faça um mapa na medida em que vai explorando - este mapa será inestimável em aventuras futuras e permitirá que você progrida rapidamente através de áreas inexploradas.\n\nExiste um caminho seguro para atravessar a Floresta da Darkwood, e você precisará fazer várias tentativas até encontrá-lo. Tome nota e faça um mapa na medida em que vai explorando - este mapa será inestimável em aventuras futuras e permitirá que você progrida rapidamente através de áreas inexploradas.");
        personagem.setQuantidadeOuro(0);
        //personagem.setHabilidadeAtual(1);
        personagem.setEnergiaAtual(2);
        personagem.setSorteAtual(1);
        DadosLivroCarregado.setPersonagem(personagem);

        //TRABALHANDO NA SEÇÃO:
        Secao secao = DadosLivroCarregado.getLivro().getMapSecao().get( 95 );
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

//        //Equipando uma espada(50)
//        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_10.json"));
//
        //Equipando uma espada(50)
        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_50.json"));

        //Equipando uma armadura de couro(51)
        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_51.json"));


        return itensEquipados;

    }

    public static ArrayList<Item> adicionaItensNaBolsaParaTeste() {
        ObjectMapper objMapper = new ObjectMapper();
        var bolsa = new ArrayList<Item>();

        //Guardando na bolsa
//        for (int i=11; i<=15; i++) {
//            try {
//                bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                        "livros/florestadadestruicao/itens/item_" + i + ".json"));
//            } catch (Exception e) {
//                System.out.println("ÍNIDCE "+ i);
//                throw new RuntimeException(e);
//            }
//        }

        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
                "livros/florestadadestruicao/itens/item_14.json"));

        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
                "livros/florestadadestruicao/itens/item_56.json"));

        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
                "livros/florestadadestruicao/itens/item_8.json"));

        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
                "livros/florestadadestruicao/itens/item_16.json"));

        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
                "livros/florestadadestruicao/itens/item_11.json"));

//        System.out.println(bolsa.get(0).getIdItem() +" Nome: "+bolsa.get(0).getNome());
//        System.out.println(bolsa.get(1).getIdItem() +" Nome: "+bolsa.get(1).getNome());
//        System.out.println(bolsa.get(2).getIdItem() +" Nome: "+bolsa.get(2).getNome());
//        System.out.println(bolsa.get(3).getIdItem() +" Nome: "+bolsa.get(3).getNome());

       //Provisão
//        for (int i=1; i<=2; i++)
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//               "livros/florestadadestruicao/itens/item_49.json"));
//
//        //Braçadeira da força
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_12.json"));
//
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_8.json"));
//
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_6.json"));
//
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_55.json"));

        return bolsa;
    }
}
