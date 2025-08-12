package livro.jogo.telas.desktop.secoes;

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
import livro.jogo.utils.UtilBolsa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_218 extends TelaSecoesBasica {
    private boolean entregou5Moedas = false;
    private JLabelOpcoesTelaSecao botaoEscolhaItens; //Deixei no escopo global para que seja desabilitado caso escolha a opção de pagar com moedas

    public TelaSecao_218(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

        //lbTextoOpcao1.setFont(new Font(Font.SERIF,Font.BOLD,20));
        botaoOpcao1.setBounds(120,575,40,50);
        labelNumOpcao1.setBounds(116,567, 50,50);
        lbTextoOpcao1.setBounds(170,562,700,60);

        carregaBotaoOpcaoMoedas();

        carregarListaItensParaDar();

        carregaBotaoNaoTemComoPagar();
    }

    private void carregaBotaoNaoTemComoPagar() {

        //Botão
        JLabelOpcoesTelaSecao botaoNaoTemComoPagar = new BotaoFaixaOpcoes(272,510,340,80)
                .criarBotao();
        botaoNaoTemComoPagar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               cliqueBotaoNaoTemComoPagar();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoNaoTemComoPagar.setIcon(Util.dimensionarImagem(botaoNaoTemComoPagar.getWidth(),
                        botaoNaoTemComoPagar.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoNaoTemComoPagar.setIcon(Util.dimensionarImagem(botaoNaoTemComoPagar.getWidth(),
                        botaoNaoTemComoPagar.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        //Texto
        JLabel texto= new JLabel("<html><center>Sem recursos</center></html>");
        texto.setBounds(375,535,130,25);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,20));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Não possui recursos para pagar ao garotinho.");
        //texto.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                cliqueBotaoNaoTemComoPagar();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoNaoTemComoPagar.setIcon(Util.dimensionarImagem(botaoNaoTemComoPagar.getWidth(),
                        botaoNaoTemComoPagar.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoNaoTemComoPagar.setIcon(Util.dimensionarImagem(botaoNaoTemComoPagar.getWidth(),
                        botaoNaoTemComoPagar.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //textoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(texto);
        add(botaoNaoTemComoPagar);
    }

    private void cliqueBotaoNaoTemComoPagar() {
        if ( (!entregou5Moedas) &&
                (!escolheuItensDaListaSuspensa) &&
                (DadosLivroCarregado.getPersonagem().getQuantidadeOuro() < 5) &&
                (UtilBolsa.retornaListaDeBensNaBolsa().length == 0) ){
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
            CarregarTelas.telaMensagem("Você não tem como pagar e o garotinho o deixa pendurado."+
                    "\n\nA morte o espera.\nSua aventura acaba aqui!");
            dispose();
        }

        if ( (entregou5Moedas) || (escolheuItensDaListaSuspensa) ){
            CarregarTelas.telaMensagem("Você já pagou ao garotinho.");
            return;
        }

        if  ( (DadosLivroCarregado.getPersonagem().getQuantidadeOuro() >= 5) ||
                (UtilBolsa.retornaListaDeBensNaBolsa().length > 0) ){
            CarregarTelas.telaMensagem("Você possui recursos para pagar ao garotinho.");
        }
    }

    private void carregarListaItensParaDar() {

        //Botão
        botaoEscolhaItens = new BotaoFaixaOpcoes(430,440,340,80)
                .criarBotao();
        botaoEscolhaItens.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (entregou5Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou ao garotinho.");
                    return;
                }

                if ( escolheuItensDaListaSuspensa ) {
                    CarregarTelas.telaMensagem("Você já entregou 1 item ao garotinho.");
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
        texto.setBounds(535,467,130,25);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,20));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Escolha 1 item para dar ao garotinho.");
        //texto.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (entregou5Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou ao garotinho.");
                    return;
                }

                if ( escolheuItensDaListaSuspensa ) {
                    CarregarTelas.telaMensagem("Você já entregou 1 item para ao garotinho.");
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

    private void clicarBotaoMoeda(){
        if ( escolheuItensDaListaSuspensa ){
            CarregarTelas.telaMensagem("Você já entregou 1 item ao garotinho.\nNão existe necessidade de entregar 5 moedas.");
            return;
        }


        if (entregou5Moedas) {
            CarregarTelas.telaMensagem("Você já pagou o garotinho.");
            return;
        }

        Personagem personagem = DadosLivroCarregado.getPersonagem();

        if (personagem.getQuantidadeOuro() >= 5){
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/moedas.mp3", null);
            CarregarTelas.telaMensagem("Você entrega 5 moedas para o garotinho.");
            personagem.setQuantidadeOuro(personagem.getQuantidadeOuro() - 5);
            atualizaIndicesNaTelaDoPersonagem();
            entregou5Moedas = true;
        }
        else{
            CarregarTelas.telaMensagem("Você não tem 5 moedas para entregar ao garotinho.");
        }
    }

    private void carregaBotaoOpcaoMoedas() {

        //Botão
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",340, 80,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(90,440,340,80);
        //botao.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicarBotaoMoeda();
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
        JLabel texto = new JLabel("<html><center>Pagar 5 moedas</center></html>");
        texto.setBounds(185,467,150,25);
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,19));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Você entrega 5 moedas ao garotinho.");
        //texto.setBorder(BorderFactory.createLineBorder(Color.RED));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicarBotaoMoeda();
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

                    if ( (entregou5Moedas) || (escolheuItensDaListaSuspensa) )
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem("Você não pagou ao garotinho 5 peças de ouro ou 1 item a sua escolha.");
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
