/*Classe que disponibiliza vários métodos auxiliares*/
package livro.jogo.utils;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ItensMapeamento;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Util {

    //Simula a rolagem de dados, passando o tipo de dado(numeroDeFaces) e a quantidade de dados que serão rolados.
    public static int rolarDados(int numeroDeFaces, int quantidadeDeDados){
        int somaValorDados = 0;

        for (int i=0; i<quantidadeDeDados; i++ )
            somaValorDados =somaValorDados + (1 + new Random().nextInt(numeroDeFaces));

        return somaValorDados;
    }

    public static int obterIndiceHabilidadeOuSorteInicial(){
        return (6 + rolarDados(6,1));
    }

    public static int obterIndiceEnergiaInicial(){
        return (12 + rolarDados(6,2));
    }

    //Retorna a poção inicial escolhida e caso já usada, retorna 0.
    //45 - Poção de Habilidade
    //46 - Poção de Força (Poção da Força)
    //47 - Poção da Fortuna
    public static Item retornaPocaoInicialDaBolsa(){
        Personagem personagem = ManipularDadosLivro.getPersonagem();
        ArrayList<Item> itens = personagem.getBolsa();

        for (Item item : itens){
            if ( (item.getIdItem() == ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem()) ||
                    (item.getIdItem() == ItensMapeamento.POCAO_DE_ENERGIA.getIdItem()) ||
                    (item.getIdItem() == ItensMapeamento.POCAO_DA_FORTUNA.getIdItem()) ){
                    return item;
            }
        }
       return null;
    }

    //Caso a imagem seja maior que o label (por exemplo) redimensionar de modo caber no componente
    public static ImageIcon dimensionarImagem(int largura, int altura, String enderecoImagem){
        ImageIcon imageIcon;
        try {
            BufferedImage img = ImageIO.read(new File(enderecoImagem));
            Image imgDimensionada = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(imgDimensionada);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return imageIcon;
    }

}
