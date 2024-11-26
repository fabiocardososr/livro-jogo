package livro.jogo.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

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

