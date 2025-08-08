package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_73;
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

public class TelaSecao_73 extends TelaSecoesBasica {
    private JLabel textoBotaoTeste;
    private boolean testeRealizado = false;

    public TelaSecao_73(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        labelNumOpcao1.setBounds(116,520, 50,50);
        botaoOpcao1.setBounds(120,528,40,50);
        lbTextoOpcao1.setBounds(170,515,700,60);


        labelNumOpcao2.setBounds(116,565, 50,50);
        botaoOpcao2.setBounds(120,573,40,50);
        lbTextoOpcao2.setBounds(170,560,700,60);

        carregaBotaoDeTeste();
    }

    private void carregaBotaoDeTeste() {
        int largura = 500;
        int altura = 100;
        int eixoY = 435;
        int eixoX = 190;

        //Texto botão repor habilidade
        textoBotaoTeste = new JLabel("<html><center>Realizar Teste</center></html>");
        textoBotaoTeste.setBounds(eixoX+125,eixoY+18,250,60);
        textoBotaoTeste.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoTeste.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoTeste.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoTeste.setFont(new Font(Font.SERIF,Font.BOLD,25));
        textoBotaoTeste.setForeground(new Color(128,0,0));
        textoBotaoTeste.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                fazerTeste();
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
        JLabelOpcoesTelaSecao botaoTesteSorte = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoTesteSorte.setBounds(eixoX,eixoY,largura,altura);
        botaoTesteSorte.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fazerTeste();
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

    private void fazerTeste() {

        if ( testeRealizado )
            return;

        if ( AcoesSecao_73.realizarTesteParaAbrirPorta() ){
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nVocê impõe toda sua força e violência contra a porta.\n\nA porta se abre!");
            botaoOpcao2.setEnabled(false);
        }else {
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                            ",\n\nVocê se choca contra a porta e é atirado de volta.\n\nA porta não abre!");
            botaoOpcao1.setEnabled(false);
        }

        textoBotaoTeste.setText("<html><center>Teste Realizado!</center></html>");
        testeRealizado = true;
        atualizaIndicesNaTelaDoPersonagem();
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1)

                    if ( !testeRealizado ){
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nVocê ainda não realizou o teste!");
                        return;
                    }

                if ( botaoOpcao1.isEnabled() )
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

        botaoOpcao2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao2)
                    if ( !testeRealizado ){
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nVocê ainda não realizou o teste!");
                        return;
                    }

                if ( botaoOpcao2.isEnabled() )
                    abrirProximaSecao( secao.getProximasSecoes().get(1).getCodProximaSecao() );
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
