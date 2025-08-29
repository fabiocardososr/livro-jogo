package livro.jogo.telas.desktop.personalizados;

import livro.jogo.entidades.Inimigo;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JButtonAbrirBatalha {
    private final static int LARGURA_IMG = 150, ALTURA_IMG = 180; //Tamanho da imagem da tela suspensa de info dos itens
    private static JPanel panelImagemInimigo; //Representa a tela suspensa de informação do item
    private static Secao secao;

    public static JPanel carregarBotoesBatalha(TelaSecoesBasica tela, Inimigo inimigo, int x, int y, int largura,
                                               int altura){

        JPanel panel = new JPanel(null);
        panel.setBounds(x,y,largura,altura);
        panel.setBackground(new Color(0,0,0,0));
        secao = tela.getSecao();
        JLabelOpcoesTelaSecao btInimigo;
        //panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

         if ( inimigo.getEnergia() == 0 )
             btInimigo = new JLabelOpcoesTelaSecao("",65,45,
                     ImagensDoLivroFlorestaDaDestruicao.CAVEIRA);
         else
            btInimigo = new JLabelOpcoesTelaSecao("",65,45,
                ImagensDoLivroFlorestaDaDestruicao.BRASAO_BATALHA);

         //Botão que abre a tela de batalha
         btInimigo.setBounds(35,30,65,45);
         btInimigo.setCursor(new Cursor(Cursor.HAND_CURSOR));
         btInimigo.setToolTipText("Clique para lutar com este inimigo.");
         btInimigo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (inimigo.getEnergia() > 0)
                    CarregarTelas.telaBatalha(inimigo, tela, panel);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                if (inimigo.getEnergia() <= 0)
                    return;

                if (e.getSource() == btInimigo){
                    btInimigo.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.BRASAO_BATALHA_SELECIONADO.getEnderecoImagem(),
                            btInimigo.getWidth(), btInimigo.getHeight()).getImageIcon());

                    tela.labelImagemSecao.setIcon(new RedimensionarImagem(inimigo.getEnderecoImagem(),
                            tela.labelImagemSecao.getWidth(),
                            tela.labelImagemSecao.getHeight()).getImageIcon());

                    tela.repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                String enderecoImagemPainel;

                if (inimigo.getEnergia() <= 0)
                    return;

                if (e.getSource() == btInimigo){
                    btInimigo.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.BRASAO_BATALHA.getEnderecoImagem(),
                            btInimigo.getWidth(), btInimigo.getHeight()).getImageIcon());

                    if ( (secao.getEnderecoImagem() != null)  && (!secao.getEnderecoImagem().isEmpty()) )
                        enderecoImagemPainel = secao.getEnderecoImagem();
                    else
                        enderecoImagemPainel = DadosLivroCarregado.getLivro().getImagemComplementar();

                    tela.labelImagemSecao.setIcon(new RedimensionarImagem(enderecoImagemPainel,
                            tela.labelImagemSecao.getWidth(),
                            tela.labelImagemSecao.getHeight()).getImageIcon());

                    tela.repaint();
                }
            }
        });

        //Faixa onde fica o nome da criatura
        JLabelOpcoesTelaSecao faixaNomeInimigo = new JLabelOpcoesTelaSecao("",100,40,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_8_REDUZIDO);
        faixaNomeInimigo.setBounds(17,0,100,40);
        //faixaNomeInimigo.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Nome da criatura
        JLabel label = new JLabel(inimigo.getNome());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(new Color(139,0,0));
        label.setFont(new Font(Font.SERIF,Font.BOLD,11));
        label.setBounds(13,-10,110,50);
        //label.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Faixa onde fica as informações de habilidade
        JLabelOpcoesTelaSecao faixaHabilidadeInimigo = new JLabelOpcoesTelaSecao("",100,25,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        faixaHabilidadeInimigo.setBounds(17,73,100,25);
        faixaHabilidadeInimigo.setHorizontalAlignment(SwingConstants.CENTER);
        //faixaIndicesInimigo.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Info da habilidade
        JLabel labelInfoHabilidade = new JLabel("<html>Habilidade <b>"+
                inimigo.getHabilidade()+"</b></html>");
        labelInfoHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoHabilidade.setForeground(new Color(139,0,0));
        labelInfoHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,11));
        labelInfoHabilidade.setBounds(-8,70,150,30);
        labelInfoHabilidade.setToolTipText("Habilidade da criatura: "+inimigo.getHabilidade());

        //Faixa onde fica as informações de Energia(força)
        JLabelOpcoesTelaSecao faixaEnergiaInimigo = new JLabelOpcoesTelaSecao("",100,25,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        faixaEnergiaInimigo.setBounds(17,95,100,25);
        faixaEnergiaInimigo.setHorizontalAlignment(SwingConstants.CENTER);

        //Info da Energia
        JLabel labelInfoEnergia = new JLabel("<html>Energia: <b>"+
                inimigo.getEnergia()+"</b></html>");
        labelInfoEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoEnergia.setForeground(new Color(139,0,0));
        labelInfoEnergia.setFont(new Font(Font.SERIF,Font.BOLD,11));
        labelInfoEnergia.setBounds(-8,92,150,30);
        labelInfoEnergia.setToolTipText("Energia da criatura: "+inimigo.getEnergia());
        //labelInfoHabilidade.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Ao carregar jogo, se salvou com algum inimigo morto é preciso carregar apenas com a imagem da caveira
        if (inimigo.getEnergia() > 0) {
            panel.add(labelInfoEnergia);
            panel.add(faixaEnergiaInimigo);
            panel.add(labelInfoHabilidade);
            panel.add(faixaHabilidadeInimigo);
            panel.add(label);
            panel.add(faixaNomeInimigo);
        }
        panel.add(btInimigo);

        tela.add(panel);

        //Retorna o painel onde se encontra o botão do inimigo.
        //Caso ele morra vou precisar alterar a imagem
        return panel;
    }
}
