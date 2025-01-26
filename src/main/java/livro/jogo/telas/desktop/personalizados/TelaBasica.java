package livro.jogo.telas.desktop.personalizados;


import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.utils.DadosLivroCarregado;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TelaBasica extends JFrame {
    private static boolean respostaTelaMensagem = false; //Setado quando chamada a tela de confirmação e não é para fechar a tela

    public TelaBasica(int largura, int altura)  {
        Container principal = getContentPane();
        setLayout(null);
        setResizable(false);
        setUndecorated(true);
        principal.setBackground(Color.BLACK);
        setSize(largura,altura);
        setLocationRelativeTo(null);
        carregarIconTela();

    }

    public boolean isRespostaTelaMensagem() {
        return respostaTelaMensagem;
    }

    public void setRespostaTelaMensagem(boolean respostaTelaMensagem) {
        TelaBasica.respostaTelaMensagem = respostaTelaMensagem;
    }

    private void carregarIconTela(){
        BufferedImage img;
        try {
            img = ImageIO.read(new File(DadosLivroCarregado.getLivro().getImagemCapa()));
            setIconImage(img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void mostrarDadosRolando(int milisegundos, ImagensDoLivroFlorestaDaDestruicao enderecoImagem){

        JDialog telaDadosRolando = new JDialog();
        telaDadosRolando.setTitle("Rolando Dados");
        telaDadosRolando.setModal(true);
        telaDadosRolando.setUndecorated(true);  //Retirar barra de título
        telaDadosRolando.setBackground(Color.WHITE);
        telaDadosRolando.setLocationRelativeTo(null);
        telaDadosRolando.getContentPane().setBackground(new Color(210,180,140));

        ImageIcon imageIcon = new ImageIcon(enderecoImagem.getEnderecoImagem());
        JLabel label = new JLabel(imageIcon);
        label.setBounds(2,2,200,200);
        telaDadosRolando.add(label);

        //Configura a tela para que feche automaticamente em alguns milisegundos
        Timer timer = new Timer(milisegundos, e -> {
            telaDadosRolando.setVisible(false);
            telaDadosRolando.dispose();
        });
        timer.setRepeats(false);
        timer.start();
        telaDadosRolando.setSize(400,350);
        telaDadosRolando.setResizable(false);
        telaDadosRolando.setLocationRelativeTo(null);
        //telaDadosRolando.setType(Type.UTILITY); //Muda os botões da barra de tarefas superior, só deixa o "x"
        telaDadosRolando.setVisible(true);

    }
}
