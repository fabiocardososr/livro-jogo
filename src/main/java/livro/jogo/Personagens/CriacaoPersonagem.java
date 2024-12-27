package livro.jogo.Personagens;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ItensIniciaisJson;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.ManipularDadosLivro;

import javax.swing.*;
import java.util.ArrayList;

public class CriacaoPersonagem {

    public CriacaoPersonagem(String nome, int idLivro, int habilidadeInicial, int energiaInicial,
                             int sorteInicial, int pocaoEscolhida, int genero) {

        var itensEquipados = adicionaItensIniciaisEquipados();
        var bolsa = adicionaItensIniciaisNaBolsa(pocaoEscolhida);
        Personagem personagem = new Personagem(nome.toUpperCase(),idLivro,habilidadeInicial,energiaInicial,sorteInicial, bolsa, itensEquipados,genero);
        ManipularDadosLivro.setPersonagem(personagem);
        JOptionPane.showMessageDialog(null,personagem.toString());
    }

    private ArrayList<Item> adicionaItensIniciaisNaBolsa(int pocaoEscolhida) {
        ObjectMapper objMapper = new ObjectMapper();
        var bolsa = new ArrayList<Item>();


        //Guardando na bolsa 10 provisões(refeições)(49)
        for (int i=0; i<10; i++)
            bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                    ItensIniciaisJson.PROVISAO.getEnderecoJson()));

        if (ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem() == pocaoEscolhida)
           bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                   ItensIniciaisJson.POCAO_HABILIDADE.getEnderecoJson()));

        if (ItensMapeamento.POCAO_DE_ENERGIA.getIdItem() == pocaoEscolhida)
            bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                    ItensIniciaisJson.POCAO_DE_FORCA.getEnderecoJson()));

        if (ItensMapeamento.POCAO_DA_FORTUNA.getIdItem() == pocaoEscolhida)
            bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                    ItensIniciaisJson.POCAO_DA_FORTUNA.getEnderecoJson()));

        return bolsa;
    }

    private ArrayList<Item> adicionaItensIniciaisEquipados() {
        ObjectMapper objMapper = new ObjectMapper();
        var itensEquipados = new ArrayList<Item>();

        //Equipando uma espada(50)
        itensEquipados.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                ItensIniciaisJson.ESPADA.getEnderecoJson()));

        //Equipando uma armadura de couro(51)
        itensEquipados.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                ItensIniciaisJson.ARMADURA_DE_COURO.getEnderecoJson()));

        return itensEquipados;

    }
}
