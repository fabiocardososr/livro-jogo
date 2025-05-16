package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_222;
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

public class TelaSecao_222 extends TelaSecoesBasica {
    JLabel textoBotaoTeste;
    boolean testeRealizado = false;

    public TelaSecao_222(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);
        labelNumOpcao1.setBounds(116,692, 50,50);
        botaoOpcao1.setBounds(120,700,40,50);
        lbTextoOpcao1.setBounds(170,687,700,60);

        acaoBotoes(secao);

        carregaBotaoDeTeste();
    }

    private void rolarDadoCalcularDano() {

        if ( testeRealizado )
            return;

        //Se personagem morto, fecha a tela de seção e abre a principal
        personagemVivo( AcoesSecao_222.rolarUmDadoDefineQuantoDeEnergiaPerdeERetornaSeVivo() );

        //Atualiza label que mostra os níveis de habilidade, sorte e energia do personagem na tela
        atualizaIndicesNaTelaDoPersonagem();

        textoBotaoTeste.setText("<html><center>Dano calculado!</center></html>");
        testeRealizado = true;
        atualizaIndicesNaTelaDoPersonagem();
    }

    private void carregaBotaoDeTeste() {
        int largura = 500;
        int altura = 100;
        int eixoY = 570;
        int eixoX = 190;

        //Texto botão repor habilidade
        textoBotaoTeste = new JLabel("<html><center>Calcular dano</center></html>");
        textoBotaoTeste.setBounds(eixoX+125,eixoY+18,250,60);
        textoBotaoTeste.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoTeste.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoTeste.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoTeste.setFont(new Font(Font.SERIF,Font.BOLD,25));
        textoBotaoTeste.setForeground(new Color(128,0,0));
        textoBotaoTeste.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                rolarDadoCalcularDano();
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

        //Botão de conferência
        JLabelOpcoesTelaSecao botaoTesteSorte = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoTesteSorte.setBounds(eixoX,eixoY,largura,altura);
        botaoTesteSorte.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rolarDadoCalcularDano();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoTesteSorte.setIcon(Util.dimensionarImagem(botaoTesteSorte.getWidth(),
                        botaoTesteSorte.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoTesteSorte.setIcon(Util.dimensionarImagem(botaoTesteSorte.getWidth(),
                        botaoTesteSorte.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoBotaoTeste);
        add(botaoTesteSorte);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){

                    if ( testeRealizado )
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nvocê precisa calcular o seu dano antes de seguir seu caminho.");
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
