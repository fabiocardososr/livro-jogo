package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_12;
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

public class TelaSecao_14 extends TelaSecoesBasica {
    private boolean entregou3Moedas = false;  //Informa se o personagem entregou 3 moedas
    private JLabelOpcoesTelaSecao botaoEscolhaItens; //Deixei no escopo global para que seja desabilitado caso escolha a opção de pagar com moedas

    public TelaSecao_14(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

        //Opção 1
        labelNumOpcao1.setBounds(86,437, 50,50);
        botaoOpcao1.setBounds(90,445,40,50);
        lbTextoOpcao1.setBounds(140,432,700,60);

        //Opção 2
        labelNumOpcao2.setBounds(86,482, 50,50);
        botaoOpcao2.setBounds(90,490,40,50);
        lbTextoOpcao2.setBounds(140,477,700,60);

        carregaBotaoOpcaoMoedas();

        carregarListaItensParaDar();
    }

    private void carregarListaItensParaDar() {

        JLabelOpcoesTelaSecao botaoEscolhaItens = new JLabelOpcoesTelaSecao("",300, 80,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoEscolhaItens.setBounds(410,530,300,80);

        botaoEscolhaItens.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (entregou3Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou o caçador.");
                    return;
                }

                if ( escolheuItensDaListaSuspensa ) {
                    CarregarTelas.telaMensagem("Você já entregou 1 item ao caçador.");
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
        JLabel texto= new JLabel("<html><center>Escolha 1 item</center></html>");
        texto.setBounds(495,555,130,25);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,16));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Escolha 1 item para dar ao caçador.");
        //texto.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (entregou3Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou o caçador.");
                    return;
                }

                if ( escolheuItensDaListaSuspensa ) {
                    CarregarTelas.telaMensagem("Você já entregou 1 item para o caçador.");
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
        //textoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(texto);
        add(botaoEscolhaItens);
    }

    private void carregaBotaoOpcaoMoedas() {

        //Botão
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",300, 80,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(100,530,300,80);

        //botao.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ( escolheuItensDaListaSuspensa ){
                    CarregarTelas.telaMensagem("Você já entregou 1 item para o caçador.\nNão existe necessidade de entregar 3 moedas.");
                    return;
                }


                if (entregou3Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou o caçador.");
                    return;
                }

                Personagem personagem = DadosLivroCarregado.getPersonagem();

                if (personagem.getQuantidadeOuro() >= 3){
                    new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/moedas.mp3", null);
                    CarregarTelas.telaMensagem("Você entrega 3 moedas para o caçador.");
                    personagem.setQuantidadeOuro(personagem.getQuantidadeOuro() - 3);
                    labelOuro.setText("Ouro: " + personagem.getQuantidadeOuro());
                    repaint();
                    entregou3Moedas = true;
                    botaoOpcao2.setEnabled(false);
                }
                else{
                    CarregarTelas.telaMensagem("Você não tem 3 moedas para entregar ao caçador.");
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //Texto
        JLabel texto = new JLabel("<html><center>Pagar 3 moedas</center></html>");
        texto.setBounds(175,555,150,25);
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,16));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Você entrega 3 moedas ao caçador.");
        //texto.setBorder(BorderFactory.createLineBorder(Color.RED));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ( escolheuItensDaListaSuspensa ){
                    CarregarTelas.telaMensagem("Você já entregou 1 item para o Caçador.\nNão existe necessidade de entregar 3 moedas.");
                    return;
                }


                if (entregou3Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou o Caçador.");
                    return;
                }

                Personagem personagem = DadosLivroCarregado.getPersonagem();

                if (personagem.getQuantidadeOuro() >= 3){
                    new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/moedas.mp3", null);
                    CarregarTelas.telaMensagem("Você entrega 3 moedas para o Caçador.");
                    personagem.setQuantidadeOuro(personagem.getQuantidadeOuro() - 3);
                    labelOuro.setText("Ouro: " + personagem.getQuantidadeOuro());
                    repaint();
                    entregou3Moedas = true;
                    botaoOpcao2.setEnabled(false);
                }
                else{
                    CarregarTelas.telaMensagem("Você não tem 3 moedas para entregar ao caçador.");
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
                botao.setIcon(Util.dimensionarImagem(botao.getWidth(),botao.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setIcon(Util.dimensionarImagem(botao.getWidth(),botao.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });
        //textoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(texto);
        add(botao);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getSource() == botaoOpcao1){

                    if ( (entregou3Moedas) || (escolheuItensDaListaSuspensa) )
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem("Você não pagou ao caçador 3 peças de ouro ou 1 item a sua escolha.");
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

                    //Se já entregou itens ao caçador, não é possível escolher esta opção
                    if ( (entregou3Moedas) || (escolheuItensDaListaSuspensa) ) {
                        return;
                    }

                    if ( (DadosLivroCarregado.getBolsa().size() > 1) ||
                            (DadosLivroCarregado.getPersonagem().getQuantidadeOuro() >= 3) ){
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n existem recursos para serem dados ao caçador. Escolha uma das opções.");
                        return;
                    }

                    if ( (!entregou3Moedas) && (!escolheuItensDaListaSuspensa) ) {
                        CarregarTelas.telaMensagem("Ele o encara e diz: "+
                                "\n\"Você estragou a armadilha e não tem como pagar uma compensação, suba sozinho.\""+
                                "\n\nA morte o espera. Sua aventura acaba aqui!");
                        dispose();
                    }

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
