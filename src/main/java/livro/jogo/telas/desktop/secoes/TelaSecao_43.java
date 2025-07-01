package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_29;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JButtonAbrirBatalha;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilPersonagem;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_43 extends TelaSecoesBasica {
    private TelaSecoesBasica estaTela = this;

    public TelaSecao_43(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);
        labelNumOpcao1.setBounds(446,592, 50,50);
        botaoOpcao1.setBounds(450,600,40,50);
        lbTextoOpcao1.setBounds(500,587,700,60);

        opcao2(secao);
        labelNumOpcao2.setBounds(446,652, 50,50);
        botaoOpcao2.setBounds(450,660,40,50);
        botaoOpcao2.setToolTipText("Fuja do enfrentamento! Mas sofrerá 2 de dano com a possibilidade de usar a sorte para diminuir.");
        lbTextoOpcao2.setBounds(500,647,700,60);
        lbTextoOpcao2.setToolTipText("Fuja do enfrentamento! Mas sofrerá 2 de dano com a possibilidade de usar a sorte para diminuir.");

        acaoBotoes(secao);

        configurandoBotoesBatalha(secao);
    }

    private void configurandoBotoesBatalha(Secao secao) {

        JButtonAbrirBatalha.carregarBotoesBatalha(this, secao.getInimigos().get(0),
                100,570,150,165);

        JButtonAbrirBatalha.carregarBotoesBatalha(this, secao.getInimigos().get(1),
                280,570,150,165);

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
                                ",\nvocê deve vencer os inimigos antes de continuar sua jornada.");
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

                    if ( AcoesSecao_29.fuga(estaTela) ) {
                        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/correndo.mp3", null);
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
