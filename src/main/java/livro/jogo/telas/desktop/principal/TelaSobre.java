package livro.jogo.telas.desktop.principal;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

public class TelaSobre extends JWindow {
    public TelaSobre() {
        setSize(900, 540);
        setLocationRelativeTo(null);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader()
                .getResource(ImagensDoLivroFlorestaDaDestruicao.TELA_SOBRE.getEnderecoImagem()));
        Image bgImage = bgIcon.getImage();

        JPanel painelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setOpaque(false);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(32, 0, 32, 0));

        JLabel titulo = new JLabel("Floresta de Darkwood");
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        titulo.setForeground(new Color(255, 202, 40));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);

        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel criador = new JLabel("Criador: Fábio Cardoso Santa Rosa.");
        criador.setFont(new Font("Serif", Font.BOLD, 22));
        criador.setForeground(new Color(255, 248, 210, 190));
        criador.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(criador);

        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 16)));

        JTextArea info = new JTextArea(
                "Este jogo é totalmente gratuito e criado por um fã dos livros-jogos clássicos!\n"
                        + "Inspirado por 'A Floresta da Destruição', do mestre Ian Livingstone e publicado pela editora Jambô, e cenários sombrios de RPG."
        );
        info.setFont(new Font("Serif", Font.BOLD, 16));
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setEditable(false);
        info.setOpaque(false);
        info.setForeground(new Color(255, 235, 190, 250));
        info.setMaximumSize(new Dimension(650, 60));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(info);

        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 18)));

        JLabel destaqueEditora = new JLabel("★ Compre os livros no site da Editora Jambô ★");
        destaqueEditora.setFont(new Font("Serif", Font.BOLD, 26));
        destaqueEditora.setForeground(new Color(255, 202, 40));
        destaqueEditora.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(destaqueEditora);

        // ======= BOTÕES COM ESTILO ESTRITO PARA NÃO HERDAR VISUAL =======

        JButton btnJambo = new JButton("Site da Jambô Editora");
        btnJambo.setBackground(new Color(50, 50, 50, 100));  // escuro translúcido
        btnJambo.setForeground(new Color(255, 202, 40));     // dourado
        btnJambo.setFont(new Font("Serif", Font.BOLD, 16));
        btnJambo.setFocusPainted(false);
        btnJambo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnJambo.setOpaque(true);
        btnJambo.setContentAreaFilled(true);
        btnJambo.setBorder(BorderFactory.createLineBorder(new Color(255, 202, 40, 80), 1, true)); // dourado translúcido
        btnJambo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnJambo.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://jamboeditora.com.br/"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível abrir o site.");
            }
        });
        painelPrincipal.add(btnJambo);

        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 14)));

        JLabel destaqueAutor = new JLabel("★ Autor: Ian Livingstone ★");
        destaqueAutor.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));
        destaqueAutor.setForeground(new Color(235, 232, 200, 210));
        destaqueAutor.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(destaqueAutor);

        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 9)));

        painelPrincipal.add(Box.createVerticalGlue());

        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setBackground(new Color(80, 16, 16, 90));
        btnFechar.setForeground(new Color(235, 232, 200, 210));
        btnFechar.setFont(new Font("Serif", Font.BOLD, 14));
        btnFechar.setFocusPainted(false);
        btnFechar.setOpaque(true);
        btnFechar.setContentAreaFilled(true);
        btnFechar.setBorder(BorderFactory.createLineBorder(new Color(255, 202, 40, 60), 1, true));
        btnFechar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFechar.addActionListener(e -> dispose());
        painelPrincipal.add(btnFechar);

        setContentPane(painelPrincipal);
    }

    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaSobre janela = new TelaSobre();
            janela.setVisible(true);
        });
    }*/
}
