package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.BotaoFaixaOpcoes;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_12 extends TelaSecoesBasica {
    private boolean entregou10Moedas = false;  //Informa se o personagem entregou 10 moedas
    private TelaSecoesBasica tela = this; //Apenas para ter uma referência desta tela para passar como parâmetro
    private JLabelOpcoesTelaSecao botaoEscolhaItens; //Deixei no escopó global para que seja desabilitado caso escolha a opção de pagar com moedas

    public TelaSecao_12(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        carregaListaDeItensNaBolsaQuePodemSerEntregues(80,550,420,250,2);

        opcao1(secao);
        opcao2(secao);
        opcao3(secao);

        //posicionando as opção mais abaixo
        botaoOpcao1.setBounds(120,640,40,50);
        labelNumOpcao1.setBounds(116,632, 50,50);
        lbTextoOpcao1.setBounds(170,627,700,60);

        botaoOpcao2.setBounds(120,685,40,50);
        labelNumOpcao2.setBounds(116,677, 50,50);
        lbTextoOpcao2.setBounds(170,672,700,60);

        botaoOpcao3.setBounds(120,730,40,50);
        labelNumOpcao3.setBounds(116,722, 50,50);
        lbTextoOpcao3.setBounds(170,717,700,60);

        //Ação ao clicar
        acaoBotoes(secao);

        //Escolher moedas para dar ao Gnomo
        carregaBotaoOpcaoMoedas();

        //Escolher 2 itens para dar ao Gnomo
        carregarListaItensParaDarAoGnomo();
    }

    private void carregarListaItensParaDarAoGnomo() {

        //Botão

        botaoEscolhaItens = new BotaoFaixaOpcoes(450,560,340,80)
                .criarBotao();
        botaoEscolhaItens.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, entregou10Moedas);
                if ( entregou10Moedas ) {
                    CarregarTelas.telaMensagem("Você já pagou o Gnome com 10 moedas.");
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
        JLabel texto= new JLabel("<html><center>Escolha 2 itens</center></html>");
        texto.setBounds(555,585,130,25);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,20));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Escolha 2 itens para dar ao Gnomo.");
        //texto.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (entregou10Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou o Gnomo.");
                    return;
                }

                if ( escolheuItensDaListaSuspensa ) {
                    CarregarTelas.telaMensagem("Você já entregou 2 itens para o Gnomo.");
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
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",340, 80,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(90,560,340,80);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
     //   botao.setBorder(BorderFactory.createLineBorder(Color.RED));
        botao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ( escolheuItensDaListaSuspensa ){
                    CarregarTelas.telaMensagem("Você já entregou 2 itens para o Gnomo.\nNão existe necessidade de entregar 10 moedas.");
                    return;
                }


                if (entregou10Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou o Gnomo.");
                    return;
                }

                Personagem personagem = DadosLivroCarregado.getPersonagem();

                if (personagem.getQuantidadeOuro() >= 10){
                    CarregarTelas.telaMensagem("Você entrega 10 moedas para o Gnomo.");
                    personagem.setQuantidadeOuro(personagem.getQuantidadeOuro() - 10);
                    labelOuro.setText("Ouro: " + personagem.getQuantidadeOuro());
                    repaint();
                    entregou10Moedas = true;
                    //desabilito a ação de chamar a telade escolha de itens já que pagou com moeda
                    botaoEscolhaItens.setEnabled(false);
                }
                else{
                    CarregarTelas.telaMensagem("Você não tem 10 moedas para entregar ao Gnomo.");
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
        JLabel texto = new JLabel("<html><center>Pagar 10 moedas</center></html>");
        texto.setBounds(185,587,150,25);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,19));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Você entrega 10 moedas ao Gnomo.");
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //texto.setBorder(BorderFactory.createLineBorder(Color.RED));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (entregou10Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou o Gnomo.");
                    return;
                }

                Personagem personagem = DadosLivroCarregado.getPersonagem();

                if (personagem.getQuantidadeOuro() >= 10){
                    CarregarTelas.telaMensagem("Você entrega 10 moedas para o Gnomo.");
                    personagem.setQuantidadeOuro(personagem.getQuantidadeOuro() - 10);
                    labelOuro.setText("Ouro: " + personagem.getQuantidadeOuro());
                    repaint();
                    entregou10Moedas = true;
                }
                else{
                    CarregarTelas.telaMensagem("Você não tem 10 moedas para entregar ao Gnomo.");
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

                    if ( (entregou10Moedas) || (escolheuItensDaListaSuspensa) )
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem("Pague ao Gnomo 10 peças de ouro ou entregue 2 itens a sua escolha"+
                                " se deseja sua espada de volta e consequentemente continuar em sua jornada.");
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

        botaoOpcao2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getSource() == botaoOpcao2){
                    if ( (entregou10Moedas) || (escolheuItensDaListaSuspensa) )
                        abrirProximaSecao( secao.getProximasSecoes().get(1).getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem("Pague ao Gnomo 10 peças de ouro ou entregue 2 itens a sua escolha"+
                                " se deseja sua espada de volta e consequentemente continuar em sua jornada.");
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

        botaoOpcao3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao3){

                    if ( (DadosLivroCarregado.getBolsa().size() > 1) ||
                            (DadosLivroCarregado.getPersonagem().getQuantidadeOuro() > 9) ){
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ", existem recursos para serem dados ao Gnomo.\n\nVocê precisa recuperar sua espada para continuar a jornada.");
                        return;
                    }

                    if ( (!entregou10Moedas) && (!escolheuItensDaListaSuspensa) ) {
                        CarregarTelas.telaMensagem("Você não recupera sua espada.\n\nAndar na"+
                                " Floresta de Darkwood desarmado é suicídio. Volte por onde você veio.\n\nSua aventura acaba aqui.");
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

}
