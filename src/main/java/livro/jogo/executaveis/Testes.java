package livro.jogo.executaveis;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.SaveJogo;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.TelasDisponiveisParaCarregamento;
import livro.jogo.telas.desktop.TelaBolsa;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Testes {
    public static void main(String[] args) {

        //Carrega o livro
        CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

        var itensEquipados = recuperaItensIniciaisEquipados();
        var bolsa = adicionaItensNaBolsaParaTeste();

        Personagem personagem = new Personagem("Fábio",1,10,200,10,
                bolsa,itensEquipados,1);
        personagem.setQuantidadeOuro(15);
        personagem.setHabilidadeAtual(5);
        personagem.setEnergiaAtual(10);
        personagem.setAnotacoes("Existe um caminho seguro para atravessar a Floresta da Darkwood, e você precisará fazer várias tentativas até encontrá-lo. Tome nota e faça um mapa na medida em que vai explorando - este mapa será inestimável em aventuras futuras e permitirá que você progrida rapidamente através de áreas inexploradas.\n\nExiste um caminho seguro para atravessar a Floresta da Darkwood, e você precisará fazer várias tentativas até encontrá-lo. Tome nota e faça um mapa na medida em que vai explorando - este mapa será inestimável em aventuras futuras e permitirá que você progrida rapidamente através de áreas inexploradas.");
        DadosLivroCarregado.setPersonagem(personagem);

        //Util.salvarJogoEmArquivo(personagem.getNome(),new SaveJogo(personagem,null));

//        SaveJogo save = Util.carregarJogoEmArquivo("Fábio.sav");
//        Personagem personagem1 = save.getPersonagem();
//        System.out.println("\n\nDEU CERTO: "+personagem1.getNome()+"\n\n");

//        personagem.setHabilidadeAtual(5);

        /* TESTAR SEÇÕES INDIVIDUAIS */
        Secao secao = DadosLivroCarregado.getLivro().getMapSecao().get(1);
        //CarregarTelas.carregarSecao(secao);
        CarregarTelas.telaSecaoHistoriaInicial(secao);

        //new CarregarTelas().carregarTela(TelasDisponiveisParaCarregamento.TELA_CARREGAR_JOGO,"","","");

      // CarregarTelas.telaBolsa(1000,800,null,
         //      null,null,null,null);
       //CarregarTelas.telaMensagem("Sua caminhada até a casa de Yaztromo leva um pouco mais da metade de um dia");

    }

    public static ArrayList<Item> recuperaItensIniciaisEquipados() {
        ObjectMapper objMapper = new ObjectMapper();
        var itensEquipados = new ArrayList<Item>();

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
        for (int i=1; i<=45; i++)
            bolsa.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
                    "livros/florestadadestruicao/itens/item_"+i+".json"));

        for (int i=1; i<=5; i++)
        bolsa.add(DadosLivroCarregado.recuperaItemDoJson(objMapper,
                "livros/florestadadestruicao/itens/item_49.json"));



        return bolsa;
    }
}
