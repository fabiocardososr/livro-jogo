package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_55;
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
import livro.jogo.utils.UtilPersonagem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TelaSecao_129 extends TelaSecoesBasica {
    private boolean entregouMoedas = false;  //Informa se o personagem entregou moedas
    private JLabelOpcoesTelaSecao botaoEscolhaItens; //Deixei no escopo global para que seja desabilitado caso escolha a opção de pagar com moedas

    public TelaSecao_129(Secao secao) {
        super(secao);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                UtilPersonagem.personagemPerdeSorte(2);
                atualizaIndicesNaTelaDoPersonagem();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                // Código a ser executado quando o diálogo está fechando
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // Código a ser executado quando o diálogo é fechado
            }

            @Override
            public void windowIconified(WindowEvent e) {
                // Código a ser executado quando o diálogo é minimizado
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // Código a ser executado quando o diálogo é restaurado
            }

            @Override
            public void windowActivated(WindowEvent e) {
                // Código a ser executado quando o diálogo é ativado
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // Código a ser executado quando o diálogo é desativado
            }
        });
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

        //Tela de escolha de item para ser descartado
        carregaListaDeItensNaBolsaQuePodemSerEntregues(80,550,420,250,1);

        opcao1(secao);
        lbTextoOpcao1.setFont(new Font(Font.SERIF,Font.BOLD,20));
        botaoOpcao1.setBounds(120,660,40,50);
        labelNumOpcao1.setBounds(116,652, 50,50);
        lbTextoOpcao1.setBounds(170,647,700,60);

        carregaBotaoOpcaoMoedas();

        carregarListaItensParaDar();

        acaoBotoes(secao);


    }

    private void carregarListaItensParaDar() {

        //Botão
        botaoEscolhaItens = new BotaoFaixaOpcoes(450,560,340,80)
                .criarBotao();
        botaoEscolhaItens.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (entregouMoedas) {
                    CarregarTelas.telaMensagem("Você já pagou a aposta a Quin.");
                    return;
                }

                if ( escolheuItensDaListaSuspensa ) {
                    CarregarTelas.telaMensagem("Você já entregou 1 item a Quin.");
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
        texto.setBounds(555,585,130,25);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,20));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Escolha 1 item para dar a Quin.");
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (entregouMoedas) {
                    CarregarTelas.telaMensagem("Você já pagou a aposta a Quin.");
                    return;
                }

                if ( escolheuItensDaListaSuspensa ) {
                    CarregarTelas.telaMensagem("Você já entregou 1 item a Quin.");
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

    private void carregaBotaoOpcaoMoedas() {

        //Botão
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",340, 80,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(90,560,340,80);
        botao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ( escolheuItensDaListaSuspensa ){
                    CarregarTelas.telaMensagem("Você já entregou 1 item para Quin.\nNão existe necessidade de entregar 10 moedas.");
                    return;
                }


                if (entregouMoedas) {
                    CarregarTelas.telaMensagem("Você já pagou a Quin.");
                    return;
                }

                Personagem personagem = DadosLivroCarregado.getPersonagem();

                if (personagem.getQuantidadeOuro() >= 10){
                    CarregarTelas.telaMensagem("Você paga sua aposta de 10 moedas de ouro para Quin.");
                    personagem.setQuantidadeOuro(personagem.getQuantidadeOuro() - 10);
                    entregouMoedas = true;
                }
                else{
                    if (personagem.getQuantidadeOuro() > 0) {
                        CarregarTelas.telaMensagem("Você não tem 10 moedas para entregar a Quin,"+
                                " mas ele aceita suas últimas "+personagem.getQuantidadeOuro()+ " peças de ouro.");
                        personagem.setQuantidadeOuro(0);
                        entregouMoedas = true;
                    }
                    else
                        CarregarTelas.telaMensagem("Você não tem nenhuma moeda para entregar a Quin. Precisa entregar algum item.");
                }

                atualizaIndicesNaTelaDoPersonagem();

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
        JLabel texto = new JLabel("<html><center>Pagar 10 moedas</center></html>");
        texto.setBounds(185,587,150,25);
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,19));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Você entrega 10 moedas a Quin.");
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ( escolheuItensDaListaSuspensa ){
                    CarregarTelas.telaMensagem("Você já entregou 1 item para Quin.\n\nNão existe necessidade de entregar moedas.");
                    return;
                }


                if (entregouMoedas) {
                    CarregarTelas.telaMensagem("Você já pagou a Quin.");
                    return;
                }

                Personagem personagem = DadosLivroCarregado.getPersonagem();

                if (personagem.getQuantidadeOuro() >= 10){
                    CarregarTelas.telaMensagem("Você paga sua aposta de 10 moedas de ouro para Quin.");
                    personagem.setQuantidadeOuro(personagem.getQuantidadeOuro() - 10);
                    entregouMoedas = true;
                }
                else{
                    if (personagem.getQuantidadeOuro() > 0) {
                        CarregarTelas.telaMensagem("Você não tem 10 moedas para entregar a Quin,"+
                                " mas ele aceita suas últimas "+personagem.getQuantidadeOuro()+ " peças de ouro.");
                        personagem.setQuantidadeOuro(0);
                        entregouMoedas = true;
                    }
                    else
                        CarregarTelas.telaMensagem("Você não tem nenhuma moeda para entregar a Quin. Precisa entregar algum item.");
                }

                atualizaIndicesNaTelaDoPersonagem();
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

        add(texto);
        add(botao);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( (entregouMoedas) || (escolheuItensDaListaSuspensa) )
                    abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                else
                    CarregarTelas.telaMensagem("Você não pagou a Quin a aposta de 10 peças de ouro ou 1 item a sua escolha.");
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
