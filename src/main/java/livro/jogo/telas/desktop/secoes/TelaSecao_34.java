package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_34 extends TelaSecoesBasica {
    public TelaSecao_34(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        //opcao1(secao);
        //acaoBotoes(secao);

        carregaBotoesDeGanhoHabilidadeEnergiaOuSorte();
    }

    private void carregaBotoesDeGanhoHabilidadeEnergiaOuSorte() {
        int largura = 260;
        int altura = 80;
        int eixoY = 600;

        //Texto botão repor habilidade
        JLabel textoBotaoHabilidade = new JLabel("<html><center>Habilidade</center></html>");
        textoBotaoHabilidade.setBounds(155,eixoY+28,140,20);
        textoBotaoHabilidade.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoHabilidade.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,20));
        textoBotaoHabilidade.setForeground(new Color(128,0,0));
        textoBotaoHabilidade.setToolTipText("Restabeleça sua Habilidade ao nível inicial.");
        textoBotaoHabilidade.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               // UtilPersonagem.
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
        //textoBotaoHabilidade.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão repor Habilidade
        JLabelOpcoesTelaSecao botaoReporHabilidade = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoReporHabilidade.setBounds(95,eixoY,largura,altura);
        botaoReporHabilidade.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoReporHabilidade.setIcon(Util.dimensionarImagem(botaoReporHabilidade.getWidth(),
                        botaoReporHabilidade.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoReporHabilidade.setIcon(Util.dimensionarImagem(botaoReporHabilidade.getWidth(),
                        botaoReporHabilidade.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        //Botão repor Energia
        JLabelOpcoesTelaSecao botaoReporEnergia = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoReporEnergia.setBounds(355,eixoY,largura,altura);
        botaoReporEnergia.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoReporEnergia.setIcon(Util.dimensionarImagem(botaoReporEnergia.getWidth(),
                        botaoReporEnergia.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoReporEnergia.setIcon(Util.dimensionarImagem(botaoReporEnergia.getWidth(),
                        botaoReporEnergia.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        //Botão repor Energia
        JLabelOpcoesTelaSecao botaoReporSorte = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoReporSorte.setBounds(615,eixoY,largura,altura);
        botaoReporSorte.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoReporSorte.setIcon(Util.dimensionarImagem(botaoReporSorte.getWidth(),
                        botaoReporSorte.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoReporSorte.setIcon(Util.dimensionarImagem(botaoReporSorte.getWidth(),
                        botaoReporSorte.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoBotaoHabilidade);
        add(botaoReporHabilidade);
        add(botaoReporEnergia);
        add(botaoReporSorte);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    botaoOpcao1.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1_SELECIONADO.getEnderecoImagem(),
                            botaoOpcao1.getWidth(), botaoOpcao1.getHeight()).getImageIcon());
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    botaoOpcao1.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1.getEnderecoImagem(),
                            botaoOpcao1.getWidth(), botaoOpcao1.getHeight()).getImageIcon());
                    repaint();
                }
            }
        });
    }
}
