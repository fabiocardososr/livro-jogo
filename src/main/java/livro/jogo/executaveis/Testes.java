package livro.jogo.executaveis;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Inimigo;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.principal.TelaBatalha;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

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
        DadosLivroCarregado.setPersonagem(personagem);

//        System.out.println("ìndice personagem: "+ personagem.getSorteAtual());
//        System.out.println("Resultado teste sorte: "+ Util.testarSorte());
//        System.out.println("ìndice personagem APÓS TESTE: "+ personagem.getSorteAtual());

        //Util.testarSorte();

        //TRABALHANDO NA SEÇÃO:
        Secao secao = DadosLivroCarregado.getLivro().getMapSecao().get( 10 );
        CarregarTelas.carregarSecao(secao);

        //TelaBasica.mostrarDadosRolando(4000, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);

        //Tela batalha
//        Inimigo inimigo = new Inimigo(1,"Enxame 1",7,3,
//                "livros/florestadadestruicao/imagens/inimigos/enxame.png");
//        TelaBatalha telaBatalha = new TelaBatalha(inimigo, null, true);
//        telaBatalha.setVisible(true);

    }

    public static ArrayList<Item> recuperaItensIniciaisEquipados() {
        ObjectMapper objMapper = new ObjectMapper();
        var itensEquipados = new ArrayList<Item>();

        //Anel de ouro
//        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
//                "livros/florestadadestruicao/itens/item_1.json"));

        //Equipando uma espada(50)
        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
                "livros/florestadadestruicao/itens/item_50.json"));

        //Equipando uma armadura de couro(51)
        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
                "livros/florestadadestruicao/itens/item_51.json"));


        return itensEquipados;

    }

    private static ArrayList<Item> adicionaItensNaBolsaParaTeste() {
        ObjectMapper objMapper = new ObjectMapper();
        var bolsa = new ArrayList<Item>();

        //Guardando na bolsa 10 provisões(refeições)(49)
        for (int i=1; i<=5; i++)
            bolsa.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
                    "livros/florestadadestruicao/itens/item_"+i+".json"));

//        for (int i=1; i<=5; i++)
        //Poção de força(energia)
       bolsa.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
               "livros/florestadadestruicao/itens/item_46.json"));

       //Provisão
        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
                "livros/florestadadestruicao/itens/item_49.json"));

        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
                "livros/florestadadestruicao/itens/item_49.json"));



        return bolsa;
    }
}
