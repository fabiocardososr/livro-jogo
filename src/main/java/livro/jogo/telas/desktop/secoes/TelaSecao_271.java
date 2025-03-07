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

public class TelaSecao_271 extends TelaSecoesBasica {
    private boolean entregou5Moedas = false;  //Informa se o personagem entregou 10 moedas
    private JLabelOpcoesTelaSecao botaoEscolhaItens; //Deixei no escopo global para que seja desabilitado caso escolha a opção de pagar com moedas

    public TelaSecao_271(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        carregaListaDeItensNaBolsaQuePodemSerEntregues(80,550,420,250,1);

        opcao1(secao);
        opcao2(secao);

        //posicionando as opção mais abaixo
        botaoOpcao1.setBounds(120,655,40,50);
        labelNumOpcao1.setBounds(116,647, 50,50);
        lbTextoOpcao1.setBounds(170,642,700,60);

        botaoOpcao2.setBounds(120,705,40,50);
        labelNumOpcao2.setBounds(116,697, 50,50);
        lbTextoOpcao2.setBounds(170,692,700,60);

        //Ação ao clicar
        acaoBotoes(secao);

        //Carrega opção de dar as moedas
        carregaBotaoOpcaoMoedas();

        //carrega opção de escolher o item
        carregarListaItensParaDarAoGnomo();

    }

    private void carregaBotaoOpcaoMoedas() {

        //Botão
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",340, 80,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(90,560,340,80);
        //botao.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ( escolheuItensDaListaSuspensa ){
                    CarregarTelas.telaMensagem("Você já entregou 1 item para o Gnomo.\nNão existe necessidade de entregar 5 moedas.");
                    return;
                }


                if (entregou5Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou o Gnomo.");
                    return;
                }

                Personagem personagem = DadosLivroCarregado.getPersonagem();

                if (personagem.getQuantidadeOuro() >= 5){
                    CarregarTelas.telaMensagem("Você pagou 5 moedas ao ganancioso Gnomo.");
                    personagem.setQuantidadeOuro(personagem.getQuantidadeOuro() - 5);
                    labelOuro.setText("Ouro: " + personagem.getQuantidadeOuro());
                    entregou5Moedas = true;
                    repaint();
                }
                else{
                    CarregarTelas.telaMensagem("Você não tem 5 moedas para entregar ao Gnomo.");
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
        JLabel texto = new JLabel("<html><center>Pagar 5 moedas</center></html>");
        texto.setBounds(185,587,150,25);
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,19));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Pague 5 moedas ao Gnomo.");
        //texto.setBorder(BorderFactory.createLineBorder(Color.RED));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ( escolheuItensDaListaSuspensa ){
                    CarregarTelas.telaMensagem("Você já entregou 1 item para o Gnomo.\nNão existe necessidade de entregar 5 moedas.");
                    return;
                }


                if (entregou5Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou o Gnomo.");
                    return;
                }

                Personagem personagem = DadosLivroCarregado.getPersonagem();

                if (personagem.getQuantidadeOuro() >= 5){
                    CarregarTelas.telaMensagem("Você pagou 5 moedas ao ganancioso Gnomo.");
                    personagem.setQuantidadeOuro(personagem.getQuantidadeOuro() - 5);
                    labelOuro.setText("Ouro: " + personagem.getQuantidadeOuro());
                    entregou5Moedas = true;
                    repaint();
                }
                else{
                    CarregarTelas.telaMensagem("Você não tem 5 moedas para entregar ao Gnomo.");
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

    private void carregarListaItensParaDarAoGnomo() {

        //Botão
        botaoEscolhaItens = new BotaoFaixaOpcoes(450,560,340,80)
                .criarBotao();
        botaoEscolhaItens.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (entregou5Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou o Gnomo.");
                    return;
                }

                if ( escolheuItensDaListaSuspensa ) {
                    CarregarTelas.telaMensagem("Você já entregou 1 item ao Gnomo.");
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
        texto.setToolTipText("Escolha 1 item para dar ao Gnomo.");
        //texto.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (entregou5Moedas) {
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

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ( e.getSource() == botaoOpcao1 ){

                    if ( (entregou5Moedas) || (escolheuItensDaListaSuspensa) )
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n você ainda não pagou ao Gnomo 5 peças de ouro ou entregou 1 item a sua escolha.");
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

                    //Se não paguei nada, libera a opção 2 sem questionar
                    if ( (!entregou5Moedas) && (!escolheuItensDaListaSuspensa) )
                        abrirProximaSecao( secao.getProximasSecoes().get(1).getCodProximaSecao() );
                    else {
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                                ",\nVocê já pagou pela informação.\n\n"+
                                "Mesmo assim deseja continuar pelo oeste sem ouvir a informação?", thisDialog);

                        if (isRespostaTelaMensagem())
                            abrirProximaSecao( secao.getProximasSecoes().get(1).getCodProximaSecao() );
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
