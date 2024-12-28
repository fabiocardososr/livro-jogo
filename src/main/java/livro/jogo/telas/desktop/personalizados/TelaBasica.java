package livro.jogo.telas.desktop.personalizados;


import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.utils.ManipularDadosLivro;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TelaBasica extends JFrame {

    public TelaBasica(int largura, int altura)  {
        Container principal = getContentPane();
        setLayout(null);
        setResizable(false);
        principal.setBackground(Color.BLACK);
        setSize(largura,altura);
        setLocationRelativeTo(null);
        carregarIconTela();

    }

    private void carregarIconTela(){
        BufferedImage img;
        try {
            img = ImageIO.read(new File(ManipularDadosLivro.getLivro().getImagemCapa()));
            setIconImage(img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void mostrarDadosRolando(TelaBasica tela, int milisegundos, ImagensDoLivroFlorestaDaDestruicao enderecoImagem){

        JDialog telaDadosRolando = new JDialog(tela, "Rolando dados", true);
        telaDadosRolando.setUndecorated(true);  //Retirar barra de título
        telaDadosRolando.setBackground(Color.WHITE);
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
        telaDadosRolando.setLocationRelativeTo(tela);
        //telaDadosRolando.setType(Type.UTILITY); //Muda os botões da barra de tarefas superior, só deixa o "x"
        telaDadosRolando.setVisible(true);

    }
}
