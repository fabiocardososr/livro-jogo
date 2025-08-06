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

public class TelaSecao_12 extends TelaSecoesBasica {
    private boolean entregou10Moedas = false;  //Informa se o personagem entregou 10 moedas
    private JLabelOpcoesTelaSecao botaoEscolhaItens; //Deixei no escopo global para que seja desabilitado caso escolha a opção de pagar com moedas

    public TelaSecao_12(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

        //posicionando as opção mais abaixo
        botaoOpcao1.setBounds(120,440,40,50);
        labelNumOpcao1.setBounds(116,432, 50,50);
        lbTextoOpcao1.setBounds(170,427,700,60);

        botaoOpcao2.setBounds(120,485,40,50);
        labelNumOpcao2.setBounds(116,477, 50,50);
        lbTextoOpcao2.setBounds(170,472,700,60);

        botaoOpcao3.setBounds(120,530,40,50);
        labelNumOpcao3.setBounds(116,522, 50,50);
        lbTextoOpcao3.setBounds(170,517,700,60);

        //Escolher moedas para dar ao Gnomo
        carregaBotaoOpcaoMoedas();

        //Escolher 2 itens para dar ao Gnomo
        carregarListaItensParaDarAoGnomo();
    }

    private void carregarListaItensParaDarAoGnomo() {
        //Botão
        botaoEscolhaItens = new JLabelOpcoesTelaSecao("",285, 50,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoEscolhaItens.setBounds(410,570,285,50);
        botaoEscolhaItens.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (entregou10Moedas) {
                    CarregarTelas.telaMensagem("Você já pagou o Gnomo.");
                    return;
                }

                if ( escolheuItensDaListaSuspensa ) {
                    CarregarTelas.telaMensagem("Você já entregou 2 itens ao Gnomo.");
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
        //texto.setBounds(555,585,130,14);
        texto.setBounds(495,583,120,20);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,14));
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
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",285, 50,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(130,570,285,50);
        //botao.setBorder(BorderFactory.createLineBorder(Color.BLUE));
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
                    AcoesSecao_12.recuperaEspadaDoGnomo();
                    botaoOpcao3.setEnabled(false);
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
        texto.setBounds(200,583,150,20);
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,14));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Você entrega 10 moedas ao Gnomo.");
        //texto.setBorder(BorderFactory.createLineBorder(Color.RED));
        texto.addMouseListener(new MouseListener() {
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
                    CarregarTelas.telaMensagem("Você entrega 10 moedas para o Gnomo.\n\nSua espada é devolvida!");
                    personagem.setQuantidadeOuro(personagem.getQuantidadeOuro() - 10);
                    labelOuro.setText("Ouro: " + personagem.getQuantidadeOuro());
                    repaint();
                    entregou10Moedas = true;
                    AcoesSecao_12.recuperaEspadaDoGnomo();
                    botaoOpcao3.setEnabled(false);
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

        botaoOpcao3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao3){

                    //Se já entregou itens ao Gnomo, não é possível escolher esta opção
                    if ( (entregou10Moedas) || (escolheuItensDaListaSuspensa) ) {
                        return;
                    }

                    if ( (DadosLivroCarregado.getBolsa().size() > 1) ||
                            (DadosLivroCarregado.getPersonagem().getQuantidadeOuro() > 9) ){
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n existem recursos para serem dados ao Gnomo. Escolha uma das opções."+
                                "\n\nVocê precisa recuperar sua espada para continuar a jornada.");
                        return;
                    }

                    if ( (!entregou10Moedas) && (!escolheuItensDaListaSuspensa) ) {
                        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
                        CarregarTelas.telaMensagem("Você não recupera sua espada.\n\nAndar na"+
                                " Floresta de Darkwood desarmado é suicídio. Volte por onde você veio."+
                                "\n\nSua aventura acaba aqui.");
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
                if (e.getSource() == botaoOpcao3){
                    botaoOpcao3.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1_SELECIONADO.getEnderecoImagem(),
                            botaoOpcao3.getWidth(), botaoOpcao3.getHeight()).getImageIcon());
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == botaoOpcao3){
                    botaoOpcao3.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1.getEnderecoImagem(),
                            botaoOpcao3.getWidth(), botaoOpcao3.getHeight()).getImageIcon());
                    repaint();
                }
            }
        });
    }

}
