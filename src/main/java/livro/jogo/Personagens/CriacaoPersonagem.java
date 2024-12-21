package livro.jogo.Personagens;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.TipoEfeito;
import livro.jogo.utils.ManipularDados;

import java.util.ArrayList;

public class CriacaoPersonagem {
    private String nome;
    private int idLivro;
    private int habilidadeInicial;
    private int energiaInicial;
    private int sorteInicial;
    private final ArrayList<Item> bolsa = new ArrayList<>();
    private final ArrayList<Item> itensEquipados = new ArrayList<>();

    public CriacaoPersonagem(String nome, int idLivro, int habilidadeInicial, int energiaInicial,
                             int sorteInicial, Item pocaoEscolhida) {
        this.nome = nome;
        this.idLivro = idLivro;
        this.habilidadeInicial = habilidadeInicial;
        this.energiaInicial = energiaInicial;
        this.sorteInicial = sorteInicial;
        bolsa.add(pocaoEscolhida);
    }

    public Personagem criarPersonagem(){

        //Itens iniciais
        Item espada = new Item(50, TipoEfeito.NENHUM,"Espada",0,0,0,"N","N","N","N","N","N");
        Item armaduraDeCouro = new Item(51, TipoEfeito.NENHUM,"Armadura de Couro",0,0,0,"N","N","N","N","N","N");
        itensEquipados.add(espada);
        itensEquipados.add(armaduraDeCouro);

        Personagem personagem = new Personagem(nome,idLivro,habilidadeInicial,energiaInicial,sorteInicial, bolsa, itensEquipados);
        ManipularDados.setPersonagem(personagem);

        return personagem;
    }
}
