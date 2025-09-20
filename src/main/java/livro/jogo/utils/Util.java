/*Classe que disponibiliza vários métodos auxiliares*/
package livro.jogo.utils;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import livro.jogo.entidades.*;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class Util {
    private static Player player; //Reproduzir mp3
    private Audio audio; //Thread que executa o áudio sem travar a tela (mp3)

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
        Personagem personagem = DadosLivroCarregado.getPersonagem();
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
            InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(enderecoImagem);
            if (inputStream == null) {
                throw new RuntimeException("Imagem não encontrada: " + enderecoImagem);
            }

            BufferedImage img = ImageIO.read(inputStream);

            if (img == null) {
                System.err.println("Falha ao decodificar imagem: " + enderecoImagem);
            }

            Image imgDimensionada = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(imgDimensionada);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return imageIcon;
    }

    public void reproduzirAudioMp3(String nomeAudio, JLabelOpcoesTelaSecao label){
        audio = new Audio(nomeAudio, label);
        audio.start();
    }

    public void pararAudioMp3(){
        if (player != null)
            player.close();

        if (audio != null)
            audio.interrupt();
    }

    //Para tratar a execução do áudio sem travar a tela
    private static class Audio extends Thread {
        private final String audio;

        //Não encontrei outro jeito para habilitar o botão novamente quando acabar o áudio
        //Por isso tive que passar a referência do componente para este método.
        private final JLabelOpcoesTelaSecao label;

        public Audio(String audio, JLabelOpcoesTelaSecao label) {
            this.audio = audio;
            this.label = label;
        }

        public void run() {
            try {
                InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(audio);
                player = new Player(inputStream);
                player.play();

                if (player.isComplete()){
                    if (label != null)
                       label.setEnabled(true);

                    player.close();
                }


            } catch (JavaLayerException e) {
                throw new RuntimeException(e);
            }
        }

        public void parar() {
            player.close();
        }
    }

    //Salvar o jogo em arquivo
    public static void salvarJogoEmArquivo(String nomeArq, SaveJogo save) {
        ObjectOutputStream objectOutputStream = null;
        try {
            // Garante que o diretório "save" exista
            File diretorio = new File("save");
            if (!diretorio.exists()) {
                diretorio.mkdirs(); // Cria o diretório e subdiretórios se necessário
            }

            // Cria o arquivo dentro do diretório
            File arquivo = new File(diretorio, nomeArq + ".sav");
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(arquivo)));
            objectOutputStream.writeObject(save);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //Carrega dados do jogo salvo
    public static SaveJogo carregarJogoEmArquivo(String nomeArq){
        ObjectInputStream objectIn = null;
        SaveJogo carregaSave = null;

        try {
            objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("save/"+nomeArq)));
            carregaSave = (SaveJogo) objectIn.readObject();
            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return carregaSave;
    }

    public static boolean apagaJogoSalvo(String nomeArq){

        File arquivo = new File("save/" + nomeArq);
        if (arquivo.exists()) {
            return arquivo.delete(); // retorna true se o arquivo foi excluído com sucesso
        } else {
            System.out.println("Arquivo não encontrado: " + nomeArq);
            return false;
        }
    }

    //lista todos os nomes arquivos dos jogos salvos
    public static ArrayList<String> listarJogosSalvos(){
        File file = new File("save/");

        // Verifica se a pasta existe; se não, cria
        if (!file.exists()) {
            boolean criada = file.mkdirs(); // cria diretórios, se necessário
            if (!criada) {
                System.err.println("Não foi possível criar a pasta 'save/'.");
                return new ArrayList<>(); // retorna lista vazia em caso de erro
            }
        }

        File arquivos[] = file.listFiles();
        ArrayList<String> listaNomesArquivos = new ArrayList<>();

        for (int i=0; i < arquivos.length; i++){
            listaNomesArquivos.add(arquivos[i].getName());
        }

        return listaNomesArquivos;
    }

    //Verifica se inimigo vivo. True se Energia maior que "0"
    public static boolean retornaSeInimigoVivo(Inimigo inimigo){

        return inimigo.getEnergia() > 0;

    }

    //Inimigo perda de energia. E retorna true se inimigo continuar vivo
    public static boolean inimigoPerdeEnergia(int valorEnergiaAPerder, Inimigo inimigo){
        var nivelDeEnergiaAtual = inimigo.getEnergia();

        inimigo.setEnergia(nivelDeEnergiaAtual - valorEnergiaAPerder);

        return retornaSeInimigoVivo(inimigo);
    }

    //Usado na tela de seção para verificar se todos os inimigos foram derrotados.
    //Geralmente esta função vai ser usada para liberar próxima seção
    public static boolean isVenceuTodosInimigos(Secao secao) {

        for (int i=0; i<secao.getInimigos().size(); i++) {
            if (secao.getInimigos().get(i).getEnergia() > 0)
                return false;
        }
        return true;
    }

    //Animação de dados rolando
    public static void mostrarAnimacaoDadosRolando(){
        TelaBasica.mostrarDadosRolando(4000, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
    }

    //Testar sorte. Faz o teste propriamente dito e cria a animação e feito sonoro.
    public static boolean testarSorte(){
        boolean sorte;

        //Animação de dados rolando
        Util.mostrarAnimacaoDadosRolando();

        sorte = UtilPersonagem.testarSorte();

        if ( sorte )
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/sorte.mp3", null);
        else
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/azar.mp3", null);

        return sorte;
    }

}
