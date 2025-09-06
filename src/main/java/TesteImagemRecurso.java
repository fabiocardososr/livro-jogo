

import livro.jogo.utils.ManipularArquivos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TesteImagemRecurso {

    public static void main(String[] args) {
        //String caminhoImagem = "/livro/jogo/ImagensSons/itens/item_1.png"; // ajuste conforme o nome real

//        try {
//            // Tenta obter a URL do recurso
//            URL url = TesteImagemRecurso.class.getResource(caminhoImagem);
//            System.out.println("URL da imagem: " + url);
//
//            // Tenta obter o InputStream do recurso
//            InputStream input = TesteImagemRecurso.class.getResourceAsStream(caminhoImagem);
//            if (input == null) {
//                throw new RuntimeException("Imagem não encontrada no classpath: " + caminhoImagem);
//            }
//
//            // Lê e exibe a imagem em uma janela simples
//            BufferedImage imagem = ImageIO.read(input);
//            ImageIcon icon = new ImageIcon(imagem);
//
//            JFrame frame = new JFrame("Teste de Imagem");
//            JLabel label = new JLabel(icon);
//            frame.getContentPane().add(label);
//            frame.pack();
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setVisible(true);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        try {
            InputStream inputStream = ManipularArquivos.class.getClassLoader()
                    .getResourceAsStream("imagens/itens/item_1.png");

            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                System.out.println("Arquivos na pasta livro:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            } else {
                System.out.println("Pasta 'livro' não encontrada no resources");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
