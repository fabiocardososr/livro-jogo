package livro.jogo.telas.desktop;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Tela extends JFrame {

    public Tela(int largura, int altura)  {
        Container principal = getContentPane();
        setLayout(null);
        setResizable(false);
        principal.setBackground(Color.BLACK);
        setSize(largura,altura);
        setLocationRelativeTo(null);
    }


    public static void mostrarDadosRolando(Tela tela, int milisegundos, String enderecoImagem){

        JDialog telaDadosRolando = new JDialog(tela, "Rolando dados", true);
        telaDadosRolando.setBackground(Color.WHITE);

        ImageIcon imageIcon = new ImageIcon(enderecoImagem);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(2,2,200,200);
        //telaDadosRolando.setIconImage();
        telaDadosRolando.add(label);
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
