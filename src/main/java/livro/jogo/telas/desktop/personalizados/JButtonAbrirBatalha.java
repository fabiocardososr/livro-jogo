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

         if ( inimigo.getEnergia() == 0 )
             btInimigo = new JLabelOpcoesTelaSecao("",100,80,
                     ImagensDoLivroFlorestaDaDestruicao.CAVEIRA);
         else
            btInimigo = new JLabelOpcoesTelaSecao("",100,80,
                ImagensDoLivroFlorestaDaDestruicao.BRASAO_BATALHA);

        btInimigo.setBounds(27,30,100,80);
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

        //Faixa onde fica o nome
        JLabelOpcoesTelaSecao faixaNomeInimigo = new JLabelOpcoesTelaSecao("",150,150,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_8);
        faixaNomeInimigo.setBounds(0,-15,150,80);

        //Nome do monstro ou tipo
        JLabel label = new JLabel(inimigo.getNome());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(new Color(139,0,0));
        label.setFont(new Font(Font.SERIF,Font.BOLD,16));
        label.setBounds(0,-5,150,50);
        //label.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Faixa onde fica as informações de habilidade
        JLabelOpcoesTelaSecao faixaHabilidadeInimigo = new JLabelOpcoesTelaSecao("",150,30,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        faixaHabilidadeInimigo.setBounds(5,105,150,30);
        faixaHabilidadeInimigo.setHorizontalAlignment(SwingConstants.CENTER);
        //faixaIndicesInimigo.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Info da habilidade
        JLabel labelInfoHabilidade = new JLabel("<html>Habilidade: <b>"+
                inimigo.getHabilidade()+"</b></html>");
        labelInfoHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoHabilidade.setForeground(new Color(139,0,0));
        labelInfoHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,16));
        labelInfoHabilidade.setBounds(2,104,150,30);

        //Faixa onde fica as informações de Energia(força)
        JLabelOpcoesTelaSecao faixaEnergiaInimigo = new JLabelOpcoesTelaSecao("",150,30,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        faixaEnergiaInimigo.setBounds(5,130,150,30);
        faixaEnergiaInimigo.setHorizontalAlignment(SwingConstants.CENTER);

        //Info da Energia
        JLabel labelInfoEnergia = new JLabel("<html>Energia: <b>"+
                inimigo.getEnergia()+"</b></html>");
        labelInfoEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfoEnergia.setForeground(new Color(139,0,0));
        labelInfoEnergia.setFont(new Font(Font.SERIF,Font.BOLD,16));
        labelInfoEnergia.setBounds(2,129,150,30);
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
