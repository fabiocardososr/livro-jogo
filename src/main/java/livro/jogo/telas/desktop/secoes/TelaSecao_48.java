package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_44;
import livro.jogo.acaosecoes.AcoesSecao_48;
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

public class TelaSecao_48 extends TelaSecoesBasica {
    private int resultadoDaRolagemDoDado = 0; //Maior que zero significa que o dado foi rolado

    public TelaSecao_48(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

        labelNumOpcao1.setBounds(116,562, 50,50);
        botaoOpcao1.setBounds(120,570,40,50);
        lbTextoOpcao1.setBounds(170,557,700,60);

        carregaBotaoRolagemDado();
    }

    private void carregaBotaoRolagemDado() {
        int largura = 530;
        int altura = 130;
        int eixoY = 440;

        //Texto botão repor habilidade
        JLabel textoBotaoRolagemDado = new JLabel("<html><center>Rolar o dado</center></html>");
        textoBotaoRolagemDado.setBounds(285,eixoY+32,250,60);
        textoBotaoRolagemDado.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoRolagemDado.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoRolagemDado.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoRolagemDado.setFont(new Font(Font.SERIF,Font.BOLD,30));
        textoBotaoRolagemDado.setForeground(new Color(128,0,0));
        textoBotaoRolagemDado.setToolTipText("O dado rolado será um D6. Definirá quantos pedaços de vidro o atingiu.");
        textoBotaoRolagemDado.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calculaDanos();
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
        JLabelOpcoesTelaSecao botaoRolagemDado = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoRolagemDado.setBounds(145,eixoY,largura,altura);
        botaoRolagemDado.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calculaDanos();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoRolagemDado.setIcon(Util.dimensionarImagem(botaoRolagemDado.getWidth(),
                        botaoRolagemDado.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoRolagemDado.setIcon(Util.dimensionarImagem(botaoRolagemDado.getWidth(),
                        botaoRolagemDado.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoBotaoRolagemDado);
        add(botaoRolagemDado);
    }

    private void calculaDanos(){

        //Verifica se a rolagem já foi feita não permitindo novo clique.
        if ( resultadoDaRolagemDoDado > 0 ) {
            CarregarTelas.telaMensagem("O dano já foi calculado anteriormente.\n\n" +
                    "Você perdeu " + resultadoDaRolagemDoDado+" pontos de energia.\n\nSiga seu caminho.");
            return;
        }

        //Calcula o dano
        resultadoDaRolagemDoDado = AcoesSecao_48.rolarDadoDeterminandoQuantoDeDano();

        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);

        //Informa o resultado da rolagem de dados, consequentemente o dano causado
        if (resultadoDaRolagemDoDado == 1) {
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome() +
                    ",\n\nUm estilhaço o acertou!\n\n" +
                    "Você perde " + resultadoDaRolagemDoDado + " de energia");
        }
        else
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nVários estilhaços o acertaram!\n\n"+
                    "Você perde "+resultadoDaRolagemDoDado+" de energia");


        //Verifica se o personagem continua vivo
        if ( !AcoesSecao_48.perdePontosDeEnergiaBaseadoNoResultadoDoDado(resultadoDaRolagemDoDado) ){
            if (referenciaTelaPrincipal != null)
                referenciaTelaPrincipal.setVisible(true);

            dispose();
        }

        //Atualiza os índices do personagem
        atualizaIndicesNaTelaDoPersonagem();
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){

                    if (resultadoDaRolagemDoDado > 0)
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nRole o dado para definir seu dano."+
                                "\n\nApós sofrer o dano e estando vivo, pode seguir em sua jornada.");
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
