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

        Personagem personagem = new Personagem("Fábio",1,12,22,
                10,
                bolsa,itensEquipados,1);
        personagem.setAnotacoes("Existe um caminho seguro para atravessar a Floresta da Darkwood, e você precisará fazer várias tentativas até encontrá-lo. Tome nota e faça um mapa na medida em que vai explorando - este mapa será inestimável em aventuras futuras e permitirá que você progrida rapidamente através de áreas inexploradas.\n\nExiste um caminho seguro para atravessar a Floresta da Darkwood, e você precisará fazer várias tentativas até encontrá-lo. Tome nota e faça um mapa na medida em que vai explorando - este mapa será inestimável em aventuras futuras e permitirá que você progrida rapidamente através de áreas inexploradas.");
        personagem.setQuantidadeOuro(20);
        personagem.setHabilidadeAtual(9);
        personagem.setEnergiaAtual(9);
        DadosLivroCarregado.setPersonagem(personagem);

//        System.out.println("ìndice personagem: "+ personagem.getSorteAtual());
//        System.out.println("Resultado teste sorte: "+ Util.testarSorte());
//        System.out.println("ìndice personagem APÓS TESTE: "+ personagem.getSorteAtual());

        //Util.testarSorte();

        //TRABALHANDO NA SEÇÃO:
        Secao secao = DadosLivroCarregado.getLivro().getMapSecao().get( 13 );
        CarregarTelas.carregarSecao(secao);

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

        //Anel de ouro
//        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
//                "livros/florestadadestruicao/itens/item_1.json"));

        //Equipando uma espada(50)
        //itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("livros/florestadadestruicao/itens/item_10.json"));

        //Equipando uma armadura de couro(51)
//        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
//                "livros/florestadadestruicao/itens/item_51.json"));


        return itensEquipados;

    }

    private static ArrayList<Item> adicionaItensNaBolsaParaTeste() {
        ObjectMapper objMapper = new ObjectMapper();
        var bolsa = new ArrayList<Item>();

        //Guardando na bolsa 10 provisões(refeições)(49)
        for (int i=1; i<=53; i++) {
            bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
                    "livros/florestadadestruicao/itens/item_" + i + ".json"));
            //System.out.println(bolsa.get(i-1).getIdItem() +" Nome: "+bolsa.get(i-1).getNome());
        }


//
//       //Cabeça de martelo
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_50.json"));
//
       //Provisão

//        //Provisão
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_50.json"));
//        //Provisão
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_49.json"));
//        //Provisão
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_49.json"));
//        //Provisão
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_49.json"));
//        //Provisão
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_49.json"));
//        //Provisão
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_49.json"));
//        //Provisão
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_49.json"));
//        //Provisão
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_49.json"));
//        //Provisão
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_49.json"));
//        //Provisão
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_49.json"));
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
//                "livros/florestadadestruicao/itens/item_49.json"));
//
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
//                "livros/florestadadestruicao/itens/item_49.json"));
//
//        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
//                "livros/florestadadestruicao/itens/item_25.json"));



        return bolsa;
    }
}
