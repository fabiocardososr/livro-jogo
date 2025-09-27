package livro.jogo.Personagens;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.utils.DadosLivroCarregado;

import java.util.ArrayList;

public class CriacaoPersonagem {
    private String nome;
    private int idLivro;
    private int habilidadeInicial;
    private int energiaInicial;
    private int sorteInicial;
    private int pocaoEscolhida;
    private int genero;

    public CriacaoPersonagem(String nome, int idLivro, int habilidadeInicial, int energiaInicial, int sorteInicial, int pocaoEscolhida, int genero) {
        this.nome = nome;
        this.idLivro = idLivro;
        this.habilidadeInicial = habilidadeInicial;
        this.energiaInicial = energiaInicial;
        this.sorteInicial = sorteInicial;
        this.pocaoEscolhida = pocaoEscolhida;
        this.genero = genero;
    }

    public void criar(){
        var itensEquipados = adicionaItensIniciaisEquipados();
        var bolsa = adicionaItensIniciaisNaBolsa(pocaoEscolhida);
        Personagem personagem = new Personagem(nome.toUpperCase(),idLivro,habilidadeInicial,energiaInicial,sorteInicial, bolsa, itensEquipados,genero);
        DadosLivroCarregado.setPersonagem(personagem);
    }

    private ArrayList<Item> adicionaItensIniciaisNaBolsa(int pocaoEscolhida) {
        ObjectMapper objMapper = new ObjectMapper();
        var bolsa = new ArrayList<Item>();

        //Guardando na bolsa 10 provisões(refeições)(49)
        for (int i=0; i<10; i++)
            bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
                    ItensMapeamento.PROVISAO.getEnderecoJson()));

        if (ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem() == pocaoEscolhida)
           bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
                   ItensMapeamento.POCAO_DE_HABILIDADE.getEnderecoJson()));

        if (ItensMapeamento.POCAO_DE_ENERGIA.getIdItem() == pocaoEscolhida)
            bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
                    ItensMapeamento.POCAO_DE_ENERGIA.getEnderecoJson()));

        if (ItensMapeamento.POCAO_DA_FORTUNA.getIdItem() == pocaoEscolhida)
            bolsa.add(DadosLivroCarregado.recuperaItemDoJson(
                    ItensMapeamento.POCAO_DA_FORTUNA.getEnderecoJson()));

        return bolsa;
    }

    private ArrayList<Item> adicionaItensIniciaisEquipados() {
        ObjectMapper objMapper = new ObjectMapper();
        var itensEquipados = new ArrayList<Item>();

        //Equipando uma espada(50)
        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson(
                ItensMapeamento.ESPADA.getEnderecoJson()));

        //Equipando uma armadura de couro(51)
        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJson(
                ItensMapeamento.ARMADURA_DE_COURO.getEnderecoJson()));

        return itensEquipados;

    }
}
