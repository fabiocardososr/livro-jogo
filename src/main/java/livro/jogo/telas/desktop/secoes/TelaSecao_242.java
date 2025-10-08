package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.BotaoFaixaOpcoes;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_242 extends TelaSecoesBasica {
    //private JLabelOpcoesTelaSecao botaoEscolhaItens;

    public TelaSecao_242(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        //carregaListaDeItensNaBolsaQuePodemSerEntregues(80,550,420,250,1);

        //posicionando as opção mais abaixo
        botaoOpcao1.setBounds(120,565,40,50);
        labelNumOpcao1.setBounds(116,557, 50,50);
        lbTextoOpcao1.setBounds(170,552,700,60);

        //carrega opção de escolher o item
        carregarListaItensParaDascarte();
    }

    private void carregarListaItensParaDascarte() {

        //Botão
        JLabelOpcoesTelaSecao botaoEscolhaItens = new JLabelOpcoesTelaSecao("",480, 140,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoEscolhaItens.setBounds(200,440,480,140);
        botaoEscolhaItens.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ( escolheuItensDaListaSuspensa ) {
                    CarregarTelas.telaMensagem("Você já descartou 1 item e adquiriu o lingote de ouro.");
                    return;
                }

                panelListaSuspensaItens.setVisible(true);
                panelListaItensEscolhidos.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoEscolhaItens.setIcon(Util.dimensionarImagem(botaoEscolhaItens.getWidth(),
                        botaoEscolhaItens.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoEscolhaItens.setIcon(Util.dimensionarImagem(botaoEscolhaItens.getWidth(),
                        botaoEscolhaItens.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        //Texto
        JLabel texto= new JLabel("<html><center>Descarte 1 item.<br>Ganhe o Lingote de Ouro</center></html>");
        texto.setBounds(326,477,220,60);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,18));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Substitua 1 item da bolsa pelo Lingote de Ouro.");
        //texto.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {



                if ( (escolheuItensDaListaSuspensa ) ||
                        (UtilBolsa.verificarExistenciaDeItemNaBolsa(ItensMapeamento.LINGOTE_DE_OURO.getIdItem())) ){
                    CarregarTelas.telaMensagem("Você já descartou 1 item e adquiriu o lingote de ouro.");
                    return;
                }

                panelListaSuspensaItens.setVisible(true);
                panelListaItensEscolhidos.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoEscolhaItens.setIcon(Util.dimensionarImagem(botaoEscolhaItens.getWidth(),
                        botaoEscolhaItens.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoEscolhaItens.setIcon(Util.dimensionarImagem(botaoEscolhaItens.getWidth(),
                        botaoEscolhaItens.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(texto);
        add(botaoEscolhaItens);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
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
