/*Classe que disponibiliza vários métodos auxiliares*/
package livro.jogo.utils;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import livro.jogo.entidades.*;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;


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
            BufferedImage img = ImageIO.read(new File(enderecoImagem));
            Image imgDimensionada = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(imgDimensionada);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return imageIcon;
    }

    //Retorna diferença entre o valor máximo e o atual de ENERGIA do personagem
    public static int retornaDiferencaEntreEnergiaMaxEAtual(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        var indiceEnergiaAtual = personagem.getEnergiaAtual();
        var indiceEnergiaMax   = personagem.getEnergiaMax();

        return indiceEnergiaMax - indiceEnergiaAtual;
    }

    //Retorna diferença entre o valor máximo e o atual de HABILIDADE do personagem
    public static int retornaDiferencaEntreHabilidadeMaxEAtual(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        var indiceAtual = personagem.getHabilidadeAtual();
        var indiceMax   = personagem.getHabilidadeMax();

        return indiceMax - indiceAtual;
    }

    //Retorna diferença entre o valor máximo e o atual de SORTE do personagem
    public static int retornaDiferencaEntreSorteMaxEAtual(){
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        var indiceAtual = personagem.getSorteAtual();
        var indiceMax   = personagem.getSorteMax();

        return indiceMax - indiceAtual;
    }

    //    //Retorna a quantidade de provisões que estão na bolsa
    public static int quantidadeProvisoesRestantes(){
        int quantidadeProvisoes = 0;
        ArrayList<Item> itens = DadosLivroCarregado.getPersonagem().getBolsa();

        for (Item item : itens)
            if ( item.getIdItem() == ItensMapeamento.PROVISAO.getIdItem() )
                ++quantidadeProvisoes;

        return quantidadeProvisoes;
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
                InputStream inputstream = new FileInputStream(audio);
                player = new Player(inputstream);
                player.play();

                if (player.isComplete()){
                    label.setEnabled(true);
                    player.close();
                }


            } catch (FileNotFoundException | JavaLayerException e) {
                throw new RuntimeException(e);
            }
        }

        public void parar() {
            player.close();
        }
    }

    //Salvar o jogo em arquivo
    public static void salvarJogoEmArquivo(String nomeArq, SaveJogo save){
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("save/"+nomeArq+".sav")));
            objectOutputStream.writeObject(save);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    //lista todos os nomes arquivos dos jogos salvos
    public static ArrayList<String> listarJogosSalvos(){
        File file = new File("save/");
        File arquivos[] = file.listFiles();
        ArrayList<String> listaNomesArquivos = new ArrayList<>();

        for (int i=0; i < arquivos.length; i++){
            listaNomesArquivos.add(arquivos[i].getName());
        }

        return listaNomesArquivos;
    }

    //Verifica se o personagem possui determinado item na bolsa ou equipado
    public static boolean verificarExistenciaDeItemNaBolsaOuEquipado(int idItem){

        //Estando na bolsa
        for (Item item : DadosLivroCarregado.getBolsa()){
            if ( item.getIdItem() == ItensMapeamento.ANEL_DA_LUZ.getIdItem() ){
                return true;
            }
        }

        //Equipado
        for (Item item : DadosLivroCarregado.getItensEquipados()){
            if ( item.getIdItem() == ItensMapeamento.ANEL_DA_LUZ.getIdItem() ){
                return true;
            }
        }

        return false;
    }

    //Verifica se o personagem possui determinado item na bolsa
    public static boolean verificarExistenciaDeItemNaBolsa(int idItem){

        //Estando na bolsa
        for (Item item : DadosLivroCarregado.getBolsa()){
            if ( item.getIdItem() == ItensMapeamento.ANEL_DA_LUZ.getIdItem() ){
                return true;
            }
        }

        return false;
    }

    //Verifica se o personagem possui determinado item equipado
    public static boolean verificarExistenciaDeItemEquipado(int idItem){

        for (Item item : DadosLivroCarregado.getItensEquipados()){
            if ( item.getIdItem() == idItem ){
                return true;
            }
        }
        return false;
    }

    //Verifica se personagem vivo. True se Energia maior que "0"
    public static boolean retornaSePersonagemVivo(){

        return DadosLivroCarregado.getPersonagem().getEnergiaAtual() > 0;

    }

    //Verifica se inimigo vivo. True se Energia maior que "0"
    public static boolean retornaSeInimigoVivo(Inimigo inimigo){

        return inimigo.getEnergia() > 0;

    }

    //Personagem perda de energia. E retorna true se personagem continuar vivo
    public static boolean personagemPerdeEnergia(int valorEnergiaAPerder){
        var nivelDeEnergiaAtual = DadosLivroCarregado.getPersonagem().getEnergiaAtual();

        DadosLivroCarregado.getPersonagem().setEnergiaAtual(nivelDeEnergiaAtual - valorEnergiaAPerder);

       return retornaSePersonagemVivo();
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


    /*REGRA: Jogue dois dados. Se o número obtido for igual ou menor do que o seu índice de SORTE atual,
             você teve sorte, e o resultado lhe será favorável. Se o número obtido for maior do que o seu
             índice de SORTE atual, você não teve sorte.
    Na função: Retornando TRUE significa que foi bem sucedido no teste*/
    public static boolean testarSorte(){
        var personagem          = DadosLivroCarregado.getPersonagem();
        int indiceAtualSorte    = personagem.getSorteAtual();

        if (indiceAtualSorte <= 0)
            return false;

        //Simulando rolamento do(s) dado(s)
        var resultadoDoisDados  = rolarDados(6,2);

        //Independentemente de qualquer resultado a sorte é diminuida em 1.
        personagem.setSorteAtual(indiceAtualSorte - 1);

        if (resultadoDoisDados <= indiceAtualSorte)
            return true;
        else
            return false;
    }

    // Adiciona um delay (pausa a execução)
    public static void delayNaExecucao(int milisegundos){
        try {
            Thread.sleep(milisegundos); // Pausa a thread pelo tempo especificado
        } catch (InterruptedException e) {
            System.out.println("A thread foi interrompida!");
        }
    }

}
