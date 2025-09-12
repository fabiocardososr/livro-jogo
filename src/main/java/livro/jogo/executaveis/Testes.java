package livro.jogo.executaveis;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.utils.DadosLivroCarregado;


import java.util.ArrayList;
import java.util.Scanner;

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
        personagem.setHabilidadeAtual(5);
        personagem.setEnergiaAtual(10);
        personagem.setSorteAtual(2);
        DadosLivroCarregado.setPersonagem(personagem);

        //personagem.setMaldicaoLobisomem(false);

//        System.out.println(DadosLivroCarregado.getMapItem());
//        System.out.println();
//        System.out.println(DadosLivroCarregado.getBolsa());
//        System.out.println();
//        System.out.println(DadosLivroCarregado.getItensEquipados());

        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        do {
            System.out.print("Digite a seção: ");
            String input = scanner.nextLine().trim();

            if (!input.equalsIgnoreCase("null") && !input.isEmpty()) {
                try {
                    numero = Integer.parseInt(input);
                    if (numero == 0){ continue; }
                    Secao secao = DadosLivroCarregado.getLivro().getMapSecao().get(numero);
                    CarregarTelas.carregarSecao(secao);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Usando null.");
                }
            }
        }while(numero != 0);
    }

    public static ArrayList<Item> recuperaItensIniciaisEquipados() {
        //ObjectMapper objMapper = new ObjectMapper();
        var itensEquipados = new ArrayList<Item>();

        //Braçadeira da força
//        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson(
//                "itens/item_12.json"));

       for (int i=21; i<=57; i++)
           itensEquipados.add(DadosLivroCarregado.getMapItem().get(i));
//
        //Equipando uma espada magnífica(10)
        itensEquipados.add(DadosLivroCarregado.getMapItem().get(1));
//
//        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("itens/item_38.json"));
//
//
       // itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("itens/item_57.json"));
//
//        //Elmo
//        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("itens/item_54.json"));

        //Colar olho de Âmbar
       // itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("itens/item_15.json"));

       // itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("itens/item_39.json"));

        //itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson("itens/item_28.json"));




        return itensEquipados;

    }

    public static ArrayList<Item> adicionaItensNaBolsaParaTeste() {
        ObjectMapper objMapper = new ObjectMapper();
        var bolsa = new ArrayList<Item>();

        for (int i=1; i<=20; i++)
           bolsa.add(DadosLivroCarregado.getMapItem().get(i));
        //bolsa.add(DadosLivroCarregado.getMapItem().get(45));


        return bolsa;
    }
}
