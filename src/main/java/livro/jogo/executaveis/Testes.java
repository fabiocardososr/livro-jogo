package livro.jogo.executaveis;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;

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
        personagem.setEnergiaAtual(10);
        personagem.setSorteAtual(5);
        personagem.setHabilidadeAtual(5);
        DadosLivroCarregado.setPersonagem(personagem);

        //LEMBRE-SE QUE ESTÁ DANDO PAU NA HORA DE FECHAR PORQUE NAO TEM REFERENCIA PARA A TELA PRINCIPAL
        TelaSecoesBasica telaSecoesBasica = new TelaSecoesBasica( DadosLivroCarregado.getMapSecao().get(400),personagem, null );
        telaSecoesBasica.setVisible(true);

//        TelaBolsa tela = new TelaBolsa();
//        tela.setVisible(true);
//        tela.setSize(200,100);
       //tela.setBackground(new Color(0,0,0,0));
//        tela.setLocationRelativeTo(null);
//        tela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

       // CarregarTelas.telaBolsa(1000,800);
       // CarregarTelas.telaMensagem("Sua caminhada até a casa de Yaztromo leva um pouco mais da metade de um dia");

    }

    public static ArrayList<Item> simularPreenchimentoBolsa(){
        ObjectMapper objMapper = new ObjectMapper();
        ArrayList<Item> bolsa = new ArrayList<>();
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_46.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_47.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_46.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_47.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_46.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_47.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));

        return bolsa;
    }

    public static ArrayList<Item> recuperaItensIniciaisEquipados() {
        ObjectMapper objMapper = new ObjectMapper();
        var itensEquipados = new ArrayList<Item>();

        //Equipando uma espada(50)
        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_50.json"));

        //Equipando uma armadura de couro(51)
        itensEquipados.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_51.json"));


        return itensEquipados;

    }

    private static ArrayList<Item> adicionaItensIniciaisNaBolsa(int pocaoEscolhida) {
        ObjectMapper objMapper = new ObjectMapper();
        var bolsa = new ArrayList<Item>();

        //Guardando na bolsa 10 provisões(refeições)(49)
        for (int i=0; i<10; i++)
            bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                    "livros/florestadadestruicao/itens/item_49.json"));

        bolsa.add(DadosLivroCarregado.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_"+pocaoEscolhida+".json"));

        return bolsa;
    }
}
