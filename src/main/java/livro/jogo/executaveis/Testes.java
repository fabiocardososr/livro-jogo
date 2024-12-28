package livro.jogo.executaveis;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.Personagens.CriacaoPersonagem;
import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.telas.desktop.TelaPrincipal;
import livro.jogo.telas.desktop.TelaRegra;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.ManipularDadosLivro;
import livro.jogo.utils.Util;

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
                bolsa,itensEquipados,2);
        personagem.setQuantidadeOuro(15);
        ManipularDadosLivro.setPersonagem(personagem);
        TelaSecoesBasica telaSecoesBasica = new TelaSecoesBasica( ManipularDadosLivro.getMapSecao().get(1),personagem );
        telaSecoesBasica.setVisible(true);

        //Não copiar quando finalizar
        telaSecoesBasica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private static ArrayList<Item> recuperaItensIniciaisEquipados() {
        ObjectMapper objMapper = new ObjectMapper();
        var itensEquipados = new ArrayList<Item>();

        //Equipando uma espada(50)
        itensEquipados.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itensIniciais/item_50.json"));

        //Equipando uma armadura de couro(51)
        itensEquipados.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itensIniciais/item_51.json"));

        return itensEquipados;

    }

    private static ArrayList<Item> adicionaItensIniciaisNaBolsa(int pocaoEscolhida) {
        ObjectMapper objMapper = new ObjectMapper();
        var bolsa = new ArrayList<Item>();

        //Guardando na bolsa 10 provisões(refeições)(49)
        for (int i=0; i<10; i++)
            bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                    "livros/florestadadestruicao/itensIniciais/item_49.json"));

        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itensIniciais/item_"+pocaoEscolhida+".json"));

        return bolsa;
    }
}
