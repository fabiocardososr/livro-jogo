/*Classe que disponibiliza vários métodos auxiliares*/
package livro.jogo.utils;

import java.util.Random;


public class Util {

    //Simula a rolagem de dados, passando o tipo de dado(numeroDeFaces) e a quantidade de dados que serão rolados.
    public static int rolarDados(int numeroDeFaces, int quantidadeDeDados){
        int somaValorDados = 0;

        for (int i=0; i<quantidadeDeDados; i++ )
            somaValorDados =somaValorDados + (1 + new Random().nextInt(numeroDeFaces));

        return somaValorDados;
    }

}
