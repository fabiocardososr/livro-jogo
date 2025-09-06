//Classe responsável por ler os dados de uim arquivo
package livro.jogo.utils;

import java.io.*;

public class ManipularArquivos {

    public static StringBuilder lerTexto(String nomeArq) {
        StringBuilder texto = new StringBuilder();
        try {
            InputStream inputStream = ManipularArquivos.class.getClassLoader().getResourceAsStream(nomeArq);
            if (inputStream == null) {
                throw new IllegalArgumentException("Arquivo não encontrado: " + nomeArq);
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
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

