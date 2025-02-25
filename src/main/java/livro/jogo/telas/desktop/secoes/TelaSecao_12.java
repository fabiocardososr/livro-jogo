package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_12 extends TelaSecoesBasica {
    private boolean entregou10Moedas = false;  //Informa se o personagem entregou 10 moedas
    private boolean entregar2Itens = false;    //Informa se escolheu entregar 2 itens ao Gnomo

    public TelaSecao_12(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        carregaListaDeItensNaBolsaQuePodemSerEntregues(78,600,240,100);

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

        //opção da punição
        carregaBotaoOpcaoMoedas();
    }

    private void carregaBotaoOpcaoMoedas() {

        //Botão
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",400, 80,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(100,560,400,80);
        //botaoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.RED));
        botao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //Texto
        JLabel textoComecarJornada = new JLabel("<html><center>Pagar 10 moedas</center></html>");
        textoComecarJornada.setBounds(120,570,360,60);
        textoComecarJornada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoComecarJornada.setHorizontalAlignment(SwingConstants.CENTER);
        textoComecarJornada.setFont(new Font(Font.SERIF,Font.BOLD,20));
        textoComecarJornada.setForeground(new Color(128,0,0));
        textoComecarJornada.setToolTipText("Clique aqui para começar sua jornada.");
        textoComecarJornada.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //textoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        add(textoComecarJornada);
        add(botao);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getSource() == botaoOpcao1){

                    if ( (entregou10Moedas) || (entregar2Itens) )
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
                    if ( (entregou10Moedas) || (entregar2Itens) )
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

                    if ( (!entregou10Moedas) && (!entregar2Itens) ) {
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
