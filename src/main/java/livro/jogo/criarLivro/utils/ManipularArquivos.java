//Classe responsável por ler os dados de uim arquivo
package livro.jogo.criarLivro.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ManipularArquivos {

    public static StringBuilder lerTexto(String nomeArq) {
        StringBuilder texto = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(nomeArq));
            String line = bufferedReader.readLine();
            texto = new StringBuilder();

            while(line != null) {
                texto.append(line+"\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("\n"+e.getMessage()+"\n");
        }
        return texto;
    }

}

