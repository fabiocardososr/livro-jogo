package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_84;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JButtonAbrirBatalha;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_290 extends TelaSecoesBasica {
    TelaSecoesBasica estaTela = this;

    public TelaSecao_290(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

        //Redimensionando o botão da opção 1
        labelNumOpcao1.setBounds(306,465, 50,50);
        lbTextoOpcao1.setBounds(360,460,700,60);
        lbTextoOpcao1.setFont(new Font(Font.SERIF,Font.BOLD,16));
        botaoOpcao1.setBounds(310,473,40,50);
        botaoOpcao1.setToolTipText("Somente após vencer todos os inimigos você pode escolher esta opção.");


        labelNumOpcao2.setBounds(306,515, 50,50);
        lbTextoOpcao2.setBounds(360,510,700,60);
        botaoOpcao2.setBounds(310,523,40,50);
        lbTextoOpcao2.setFont(new Font(Font.SERIF,Font.BOLD,16));
        botaoOpcao2.setToolTipText("Escolha esta opção sendo covarde. Corra antes de enfrentar quaisquer inimigos.");

        //Inimigos
        configurandoBotoesBatalha(secao);
    }

    private void configurandoBotoesBatalha(Secao secao) {

        JButtonAbrirBatalha.carregarBotoesBatalha(this, secao.getInimigos().get(0),
                100,450,150,165);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    if ( Util.isVenceuTodosInimigos(secao) )
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nvocê deve vencer o inimigo antes de continuar sua jornada.");
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

        botaoOpcao2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao2){

                    if ( Util.isVenceuTodosInimigos(secao) )
                        return;

                    if ( AcoesSecao_84.fuga(estaTela) ) {
                        atualizaIndicesNaTelaDoPersonagem();
                        abrirProximaSecao(secao.getProximasSecoes().get(1).getCodProximaSecao());
                    }

                    //Após a fuga e perda de energia, verifica se o personagem está vivo.
                    if ( !UtilPersonagem.retornaSePersonagemVivo() )
                        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
                    if (referenciaTelaPrincipal != null)
                        referenciaTelaPrincipal.setVisible(true);
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
                if (e.getSource() == botaoOpcao2){
                    botaoOpcao2.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1_SELECIONADO.getEnderecoImagem(),
                            botaoOpcao2.getWidth(), botaoOpcao2.getHeight()).getImageIcon());
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == botaoOpcao2){
                    botaoOpcao2.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1.getEnderecoImagem(),
                            botaoOpcao2.getWidth(), botaoOpcao2.getHeight()).getImageIcon());
                    repaint();
                }
            }
        });
    }
}
