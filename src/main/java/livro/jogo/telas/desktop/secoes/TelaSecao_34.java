package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_34;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_34 extends TelaSecoesBasica {
    private boolean escolheuDesejo = false;  //Somente é possível escolher um desejo e essta variável faz o controle

    public TelaSecao_34(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        labelNumOpcao1.setBounds(116,562, 50,50);
        botaoOpcao1.setBounds(120,570,40,50);
        lbTextoOpcao1.setBounds(170,557,700,60);


        carregaBotoesDeGanhoHabilidadeEnergiaOuSorte();
    }

    private void carregaBotoesDeGanhoHabilidadeEnergiaOuSorte() {
        int largura = 200;
        int altura = 80;
        int eixoY = 480;

        //Texto botão repor habilidade
        JLabel textoBotaoHabilidade = new JLabel("<html><center>Habilidade</center></html>");
        textoBotaoHabilidade.setBounds(125,eixoY+28,140,20);
        textoBotaoHabilidade.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoHabilidade.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,16));
        textoBotaoHabilidade.setForeground(new Color(128,0,0));
        textoBotaoHabilidade.setToolTipText("Restabeleça sua Habilidade ao nível inicial.");
        textoBotaoHabilidade.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                clicarEscolhaHabilidade();
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
                clicarEscolhaHabilidade();
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

        //Texto botão repor Energia
        JLabel textoBotaoEnergia = new JLabel("<html><center>Energia</center></html>");
        textoBotaoEnergia.setBounds(355,eixoY+23,140,30);
        textoBotaoEnergia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoEnergia.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoEnergia.setFont(new Font(Font.SERIF,Font.BOLD,16));
        textoBotaoEnergia.setForeground(new Color(128,0,0));
        textoBotaoEnergia.setToolTipText("Restabeleça sua energia ao nível inicial.");
        textoBotaoEnergia.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicarEscolhaEnergia();
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
        //textoBotaoEnergia.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão repor Energia
        JLabelOpcoesTelaSecao botaoReporEnergia = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoReporEnergia.setBounds(325,eixoY,largura,altura);
        botaoReporEnergia.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicarEscolhaEnergia();
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

        //Texto botão repor Sorte
        JLabel textoBotaoSorte = new JLabel("<html><center>Sorte</center></html>");
        textoBotaoSorte.setBounds(585,eixoY+23,140,30);
        textoBotaoSorte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoSorte.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoSorte.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoSorte.setFont(new Font(Font.SERIF,Font.BOLD,16));
        textoBotaoSorte.setForeground(new Color(128,0,0));
        textoBotaoSorte.setToolTipText("Restabeleça sua sorte ao nível inicial.");
        textoBotaoSorte.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicarEscolhaSorte();
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

        //Botão repor Energia
        JLabelOpcoesTelaSecao botaoReporSorte = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoReporSorte.setBounds(555,eixoY,largura,altura);
        botaoReporSorte.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicarEscolhaSorte();
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

        add(textoBotaoSorte);
        add(textoBotaoEnergia);
        add(textoBotaoHabilidade);
        add(botaoReporHabilidade);
        add(botaoReporEnergia);
        add(botaoReporSorte);
    }

    private void clicarEscolhaHabilidade() {
        if ( escolheuDesejo ) {
            CarregarTelas.telaMensagem("\nVocê já fez o seu desejo.\n\nSiga para o norte!");
            return;
        }


        if ( AcoesSecao_34.recuperaHabilidadeAoNivelMaximo() ) {
            escolheuDesejo = true;
            atualizaIndicesNaTelaDoPersonagem();
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\nSeu índice de habilidade foi restaurado!");
        }
        else
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\nSeu nível de habilidade já encontra-se no máximo. "+
                    "\n\nVocê não pode escolher esta opção!");
    }

    private void clicarEscolhaEnergia() {
        if ( escolheuDesejo ) {
            CarregarTelas.telaMensagem("\nVocê já fez o seu desejo.\n\nSiga para o norte!");
            return;
        }


        if ( AcoesSecao_34.recuperaEnergiaAoNivelMaximo() ) {
            escolheuDesejo = true;
            atualizaIndicesNaTelaDoPersonagem();
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\nSeu índice de energia foi restaurado!");
        }
        else
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\nSeu nível de energia já encontra-se no máximo. "+
                    "\n\nVocê não pode escolher esta opção!");
    }

    private void clicarEscolhaSorte() {
        if ( escolheuDesejo ) {
            CarregarTelas.telaMensagem("\nVocê já fez o seu desejo.\n\nSiga para o norte!");
            return;
        }


        if ( AcoesSecao_34.recuperaSorteAoNivelMaximo() ) {
            escolheuDesejo = true;
            atualizaIndicesNaTelaDoPersonagem();
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\nSeu índice de sorte foi restaurado!");
        }
        else
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\nSeu nível de sorte já encontra-se no máximo. "+
                    "\n\nVocê não pode escolher esta opção!");
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    if ( (AcoesSecao_34.verificaSeTodosIndicesCompletos()) || ( escolheuDesejo ) )
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                }

                if ( !escolheuDesejo )
                    CarregarTelas.telaMensagem("Você precisa fazer sua escolha.\n\nFaça seu desejo!");
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
