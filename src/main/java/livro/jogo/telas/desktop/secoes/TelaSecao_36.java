package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_12;
import livro.jogo.acaosecoes.AcoesSecao_36;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.BotaoFaixaOpcoes;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_36 extends TelaSecoesBasica {
    private boolean conferido = false;
    private String itensRoubados;

    public TelaSecao_36(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        carregaListaDeItensNaBolsaQuePodemSerEntregues(80,550,420,250,2);
        opcao1(secao);
        labelNumOpcao1.setBounds(116,712, 50,50);
        botaoOpcao1.setBounds(120,720,40,50);
        lbTextoOpcao1.setBounds(170,707,700,60);

        acaoBotoes(secao);
        carregaBotaoDeConferenciaDaBolsa();
    }

    //O jogador vai clicar neste botão para conferir os itens roubados.
    //e neste momento escolherá os itens a serem roubados
    private void carregaBotaoDeConferenciaDaBolsa() {
        int largura = 550;
        int altura = 150;
        int eixoY = 570;

        //Texto botão repor habilidade
        JLabel textoBotaoConferencia = new JLabel("<html><center>Confira se algo foi roubado</center></html>");
        textoBotaoConferencia.setBounds(310,eixoY+45,250,60);
        textoBotaoConferencia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoConferencia.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoConferencia.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoConferencia.setFont(new Font(Font.SERIF,Font.BOLD,22));
        textoBotaoConferencia.setForeground(new Color(128,0,0));
        textoBotaoConferencia.setToolTipText("Conferir se algo foi roubado.");
        textoBotaoConferencia.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

               confiraSeAlgoFoiRoubado();
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
        //textoBotaoConferencia.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão de conferência
        JLabelOpcoesTelaSecao botaoConferencia = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoConferencia.setBounds(165,eixoY,largura,altura);
        botaoConferencia.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                confiraSeAlgoFoiRoubado();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoConferencia.setIcon(Util.dimensionarImagem(botaoConferencia.getWidth(),
                        botaoConferencia.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoConferencia.setIcon(Util.dimensionarImagem(botaoConferencia.getWidth(),
                        botaoConferencia.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoBotaoConferencia);
        add(botaoConferencia);
    }

    private void confiraSeAlgoFoiRoubado() {

        if ( conferido ){
            CarregarTelas.telaMensagem("Você já conferiu se foi roubado.\n\n"+
                    "O gatuno levou: "+itensRoubados);
            return;
        }

        //Fazer a conferência propriamente dita
        itensRoubados = AcoesSecao_36.ladraoRouba();
        conferido = true;
        atualizaIndicesNaTelaDoPersonagem();

        if ( itensRoubados.isEmpty() )
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nVocê teve muita sorte.\nNada foi roubado!\n\nMas você tem algo para ser roubado...?");
        else
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                ",\n\nMais cuidado em confiar em um estranho."+
                "\n\nRoubado(s): "+itensRoubados);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    if ( conferido )
                       abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nAntes de continuar, confira se algo foi roubado.");
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
