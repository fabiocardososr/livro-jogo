package livro.jogo.executaveis;

import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.TelasDisponiveisParaCarregamento;
import livro.jogo.telas.desktop.CarregarTelas;


import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TelaSplash extends JFrame {
    JProgressBar progressBar;
    JPanel panel;
    int progress = 0;

    public TelaSplash() {
        setTitle("Floresta de Darkwood");
        setSize(800, 600); // Ajuste conforme necessário
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Remove bordas da janela

        // Painel com imagem de fundo
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //TelaSplash.class.getClassLoader().getResourceAsStream(audio);
                ImageIcon background = new ImageIcon(getClass().getClassLoader().getResource(escolheImagemParaFundoDaTelaDeSplash()));
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(new BorderLayout());

        add(panel);

        barraDeProgresso();

        // Timer para trocar de tela após 5 segundos
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                dispose(); // Fecha a splash

            }
        }, 5000); // 5000 ms = 5 segundos
    }

    private String escolheImagemParaFundoDaTelaDeSplash(){

        //Escolhe imagem de fundo da tela de splash
        var telaSplash = new Random().nextInt(3);
        var stringImg = "";

        switch (telaSplash){
            case 0: stringImg = ImagensDoLivroFlorestaDaDestruicao.TELA_SPLASH_0.getEnderecoImagem();
                break;
            case 1: stringImg = ImagensDoLivroFlorestaDaDestruicao.TELA_SPLASH_1.getEnderecoImagem();
                break;
            case 2: stringImg = ImagensDoLivroFlorestaDaDestruicao.TELA_SPLASH_2.getEnderecoImagem();
                break;
        }

        return stringImg;
    }

    private void carregaJogo(){
        //Carrega o livro
        CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

        //Carregar Tela Principal
        new CarregarTelas().carregarTela(TelasDisponiveisParaCarregamento.TELA_PRINCIPAL,"","","");
    }

    private void barraDeProgresso() {

        // Barra de progresso
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(false);
       // progressBar.setPreferredSize(new Dimension(getWidth(), 20)); // altura de 5 pixels

        progressBar.setForeground(new Color(255, 255, 255, 100)); // branco translúcido
        progressBar.setBackground(new Color(0, 0, 0, 50)); // fundo escuro translúcido

        panel.add(progressBar);

        panel.setLayout(null);
        progressBar.setBounds(20, getHeight() - 30, getWidth() - 40, 7); // margem de 20px nas laterais

        add(panel);

        // Timer para atualizar a barra de progresso
        Timer progressTimer = new Timer();
        progressTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                progress += 2; // Incrementa 2% a cada 100ms
                progressBar.setValue(progress);
                if (progress >= 100) {
                    progressTimer.cancel();
                    dispose(); // Fecha a splash
                    new MainMenu(); // Abre o menu principal
                }
            }
        }, 0, 100); // Atualiza a cada 100ms
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            //Carrega o livro
            CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
            livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

            TelaSplash splash = new TelaSplash();
            splash.setVisible(true);
        });
    }
}

// Tela principal do menu
class MainMenu extends JFrame {
    public MainMenu() {

        //Carregar Tela Principal
        new CarregarTelas().carregarTela(TelasDisponiveisParaCarregamento.TELA_PRINCIPAL,"","","");
    }
}
