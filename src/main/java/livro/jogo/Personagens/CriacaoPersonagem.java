package livro.jogo.Personagens;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.utils.ManipularDados;

import java.util.ArrayList;

public class CriacaoPersonagem {

    public CriacaoPersonagem(String nome, int idLivro, int habilidadeInicial, int energiaInicial,
                             int sorteInicial, int pocaoEscolhida) {

        var itensEquipados = recuperaItensIniciaisEquipados();
        var bolsa = recuperaItensIniciaisNaBolsa(pocaoEscolhida);
        Personagem personagem = new Personagem(nome,idLivro,habilidadeInicial,energiaInicial,sorteInicial, bolsa, itensEquipados);
        ManipularDados.setPersonagem(personagem);
    }

    private ArrayList<Item> recuperaItensIniciaisNaBolsa(int pocaoEscolhida) {
        ObjectMapper objMapper = new ObjectMapper();
        var bolsa = new ArrayList<Item>();

        //Guardando na bolsa 10 provisões(refeições)(49)
        for (int i=0; i<10; i++)
            bolsa.add(ManipularDados.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                    "livros/florestadadestruicao/itensIniciais/item_49.json"));

        bolsa.add(ManipularDados.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itensIniciais/item_"+pocaoEscolhida+".json"));

        return bolsa;
    }

    private ArrayList<Item> recuperaItensIniciaisEquipados() {
        ObjectMapper objMapper = new ObjectMapper();
        var itensEquipados = new ArrayList<Item>();

        //Equipando uma espada(50)
        itensEquipados.add(ManipularDados.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itensIniciais/item_50.json"));

        //Equipando uma armadura de couro(51)
        itensEquipados.add(ManipularDados.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itensIniciais/item_51.json"));

        return itensEquipados;

    }
}
