package livro.jogo.telas.desktop.secoes;

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

public class TelaSecao_309 extends TelaSecoesBasica {
    boolean testouSorte = false;
    int quantidadeDeTestesdeSorte = 2;  //Será decrementado para cada teste
    JLabel textoBotaoTesteSorte;

    public TelaSecao_309(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);
        labelNumOpcao1.setBounds(116,662, 50,50);
        botaoOpcao1.setBounds(120,670,40,50);
        lbTextoOpcao1.setBounds(170,657,700,60);

        opcao2(secao);
        labelNumOpcao2.setBounds(116,712, 50,50);
        botaoOpcao2.setBounds(120,720,40,50);
        lbTextoOpcao2.setBounds(170,707,700,60);

        acaoBotoes(secao);

        carregaBotaoTestarSorte();
    }

    private void carregaBotaoTestarSorte() {
        int largura = 500;
        int altura = 100;
        int eixoY = 570;
        int eixoX = 190;

        //Texto botão repor habilidade
        textoBotaoTesteSorte = new JLabel("<html><center>1º Teste de Sorte</center></html>");
        textoBotaoTesteSorte.setBounds(eixoX+125,eixoY+18,250,60);
        textoBotaoTesteSorte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoTesteSorte.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoTesteSorte.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoTesteSorte.setFont(new Font(Font.SERIF,Font.BOLD,25));
        textoBotaoTesteSorte.setForeground(new Color(128,0,0));
        textoBotaoTesteSorte.setToolTipText("REGRA: São jogados dois dados. Número obtido" +
                " igual ou menor ao seu índice de SORTE, você teve sorte. Qualquer que seja o resultado, SEMPRE é diminuído em 1 seu índice.");
        textoBotaoTesteSorte.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                testarSorte();
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
                testarSorte();
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

        add(textoBotaoTesteSorte);
        add(botaoTesteSorte);
    }

    private void testarSorte(){

        if ( testouSorte )
            return;

        //Decrementar o contador de testes
        --quantidadeDeTestesdeSorte;

        if ( Util.testarSorte() ){
            CarregarTelas.telaMensagem("Parabéns!\n\nVocê foi bem sucedido no teste de sorte.");
            if (quantidadeDeTestesdeSorte == 0)
                botaoOpcao2.setEnabled(false);
        }else {
            CarregarTelas.telaMensagem("Azarado!\n\nVocê foi malsucedido no teste de sorte. Você foi atingido.");
            quantidadeDeTestesdeSorte = 0;
            botaoOpcao1.setEnabled(false);
        }

        textoBotaoTesteSorte.setText("<html><center>2º Teste de Sorte</center></html>");

        if (quantidadeDeTestesdeSorte == 0) {
            textoBotaoTesteSorte.setText("<html><center>Teste Realizado!</center></html>");
            testouSorte = true;
        }

        atualizaIndicesNaTelaDoPersonagem();
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){

                    if ( !testouSorte ){
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nVocê ainda não fez o teste de sorte!");
                        return;
                    }

                    if ( botaoOpcao1.isEnabled() )
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

        botaoOpcao2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao2){

                    if ( !testouSorte ){
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nVocê ainda não fez o teste de sorte!");
                        return;
                    }


                    if ( botaoOpcao2.isEnabled() )
                        abrirProximaSecao( secao.getProximasSecoes().get(1).getCodProximaSecao() );
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
