package livro.jogo.executaveis;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.telas.desktop.TelaBolsa;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.ManipularDadosLivro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Testes {
    public static void main(String[] args) {
        //Carrega o livro
        CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

        var itensEquipados = recuperaItensIniciaisEquipados();
        var bolsa = adicionaItensIniciaisNaBolsa(47);

        Personagem personagem = new Personagem("Fábio",1,10,20,10,
                bolsa,itensEquipados,1);
        personagem.setQuantidadeOuro(15);
        ManipularDadosLivro.setPersonagem(personagem);
//        TelaSecoesBasica telaSecoesBasica = new TelaSecoesBasica( ManipularDadosLivro.getMapSecao().get(400),personagem, null );
//        telaSecoesBasica.setVisible(true);

//        TelaBolsa tela = new TelaBolsa();
//        tela.setVisible(true);
//        tela.setSize(200,100);
       //tela.setBackground(new Color(0,0,0,0));
//        tela.setLocationRelativeTo(null);
//        tela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

       // CarregarTelas.telaBolsa(1000,800);
        CarregarTelas.telaMensagem("Sua caminhada até a casa de Yaztromo leva um pouco mais da metade de um dia, e você chega à torre de pedra onde ele habita sujo e com fome. Como a torre fica afastada na periferia de Darkwood, a uns cinqüenta metros de distância da trilha que você estava seguindo, não é fácil encontrá-la. Finalmente, você chega junto à imensa porta de carvalho, de certa forma aliviado por descobrir que de fato a torre existe, e que Perna Grande não estava falando loucuras em seu delírio. Um grande sino de metal e um gongo pendem do arco de pedra. Ao tocar o sino, um calafrio percorre a sua espinha, e você se dá conta de que o som alto invade um silêncio profundo no qual você não tinha reparado antes. Não se ouvem ruídos de animais ou pássaros. Você espera ansiosamente junto à porta e ouve passadas lentas que descem escadas vindas da torre acima. Uma pequena portinhola de madeira se abre, e dois olhos aparecem, examinando você.\\n- Bem, quem é você? - pergunta uma voz aborrecida através do orifício.\\nVocê responde que é um aventureiro em busca do poderoso mago Yaztromo, tendo a intenção de adquirir alguns artigos mágicos dele para combater as criaturas da Floresta de Darkwood.\\n- Ah, bem, nesse caso, se você está interessado em comprar algumas mercadorias minhas, é melhor subir. Eu sou Yaztromo.\\nEle então se volta e sobe lentamente os degraus de pedra. Você:");

    }

    public static ArrayList<Item> simularPreenchimentoBolsa(){
        ObjectMapper objMapper = new ObjectMapper();
        ArrayList<Item> bolsa = new ArrayList<>();
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_46.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_47.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_46.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_47.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_46.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_47.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));

        return bolsa;
    }

    public static ArrayList<Item> recuperaItensIniciaisEquipados() {
        ObjectMapper objMapper = new ObjectMapper();
        var itensEquipados = new ArrayList<Item>();

        //Equipando uma espada(50)
        itensEquipados.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_50.json"));

        //Equipando uma armadura de couro(51)
        itensEquipados.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_51.json"));

        itensEquipados.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_49.json"));

        return itensEquipados;

    }

    private static ArrayList<Item> adicionaItensIniciaisNaBolsa(int pocaoEscolhida) {
        ObjectMapper objMapper = new ObjectMapper();
        var bolsa = new ArrayList<Item>();

        //Guardando na bolsa 10 provisões(refeições)(49)
        for (int i=0; i<10; i++)
            bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                    "livros/florestadadestruicao/itens/item_49.json"));

        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_"+pocaoEscolhida+".json"));

        return bolsa;
    }
}
