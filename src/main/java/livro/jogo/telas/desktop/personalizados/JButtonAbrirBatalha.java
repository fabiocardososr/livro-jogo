package livro.jogo.telas.desktop.personalizados;

import livro.jogo.entidades.Inimigo;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JButtonAbrirBatalha {

    public static void carregarBotoesBatalha(TelaSecoesBasica tela, Inimigo inimigo, int x, int y, int largura, int altura){

        JPanel panel = new JPanel(null);
        panel.setBounds(x,y,largura,altura);
        panel.setBackground(new Color(0,0,0,0));

        /*** Inimigo 1: ABELHAS ASSASSINAS: Habilidade 7; Energia: 3 ***/
        JLabelOpcoesTelaSecao btInimigo = new JLabelOpcoesTelaSecao("",100,80,
                ImagensDoLivroFlorestaDaDestruicao.BRASAO_BATALHA);
        btInimigo.setBounds(27,30,100,80);
        btInimigo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btInimigo.setToolTipText("Clique para lutar com este inimigo.");
        btInimigo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CarregarTelas.telaBatalha(inimigo, tela);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //Faixa onde fica o nome
        JLabelOpcoesTelaSecao faixaNomeInimigo = new JLabelOpcoesTelaSecao("",150,150,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_8);
        faixaNomeInimigo.setBounds(0,-15,150,80);
        //faixaNomeInimigo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //faixaNomeInimigo.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Nome do monstro ou tipo
        JLabel label = new JLabel(inimigo.getNome());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(new Color(139,0,0));
        label.setFont(new Font(Font.SERIF,Font.BOLD,18));
        label.setBounds(0,-7,150,50);
        //label.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Faixa onde fica as informações de habilidade
        JLabelOpcoesTelaSecao faixaHabilidadeInimigo = new JLabelOpcoesTelaSecao("",140,30,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        faixaHabilidadeInimigo.setBounds(5,105,140,30);
        //faixaHabilidadeInimigo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        faixaHabilidadeInimigo.setHorizontalAlignment(SwingConstants.CENTER);
        //faixaIndicesInimigo.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Info da habilidade
        JLabel labelInfoHabilidade = new JLabel("<html>Habilidade: <b>"+
                inimigo.getHabilidade()+"</b></html>");
        labelInfoHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoHabilidade.setForeground(new Color(139,0,0));
        labelInfoHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,16));
        labelInfoHabilidade.setBounds(2,104,150,30);
        //labelInfoHabilidade.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Faixa onde fica as informações de Energia(força)
        JLabelOpcoesTelaSecao faixaEnergiaInimigo = new JLabelOpcoesTelaSecao("",140,30,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        faixaEnergiaInimigo.setBounds(5,130,140,30);
        //faixaEnergiaInimigo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        faixaEnergiaInimigo.setHorizontalAlignment(SwingConstants.CENTER);
        //faixaIndicesInimigo.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Info da Energia
        JLabel labelInfoEnergia = new JLabel("<html>Energia: <b>"+
                inimigo.getEnergia()+"</b></html>");
        labelInfoEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoEnergia.setForeground(new Color(139,0,0));
        labelInfoEnergia.setFont(new Font(Font.SERIF,Font.BOLD,16));
        labelInfoEnergia.setBounds(2,129,150,30);
        //labelInfoHabilidade.setBorder(BorderFactory.createLineBorder(Color.RED));

        panel.add(labelInfoEnergia);
        panel.add(faixaEnergiaInimigo);
        panel.add(labelInfoHabilidade);
        panel.add(faixaHabilidadeInimigo);
        panel.add(label);
        panel.add(faixaNomeInimigo);
        panel.add(btInimigo);

        tela.add(panel);
    }
}
