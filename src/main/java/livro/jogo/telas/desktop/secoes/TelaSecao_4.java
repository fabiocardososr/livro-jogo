package livro.jogo.telas.desktop.secoes;
import livro.jogo.acaosecoes.AcoesSecao_108;
import livro.jogo.acaosecoes.AcoesSecao_4;
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

public class TelaSecao_4 extends TelaSecoesBasica {

    public TelaSecao_4(Secao secao) {
        super(secao);
        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
    }

    private void sair(){
        util.pararAudioMp3();
        if (referenciaTelaPrincipal != null)
            referenciaTelaPrincipal.setVisible(true);

        dispose();
    }

    private void carregaBotaoCasoMorto() {
        //Texto
        JLabel texto = new JLabel("<html><center>Você já estava fraco e a flecha<br> "+
                "causou um enorme sangramento.<br>Você morreu!</center></html>");
        texto.setBounds(300,505,250,60);
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,16));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Clique aqui para começar sua jornada.");
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sair();
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
        //texto.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",600, 170,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(120,450,600,170);
        //botaoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.RED));

        botao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sair();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setIcon(Util.dimensionarImagem(botao.getWidth(),
                        botao.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setIcon(Util.dimensionarImagem(botao.getWidth(),
                        botao.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(texto);
        add(botao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

        //Se false o personagem morreu
        if ( !AcoesSecao_4.perde4PontosDeEnergia() ){
            labelNumOpcao1.setVisible(false);
            botaoOpcao1.setVisible(false);
            lbTextoOpcao1.setVisible(false);
            labelNumOpcao2.setVisible(false);
            botaoOpcao2.setVisible(false);
            lbTextoOpcao2.setVisible(false);
            carregaBotaoCasoMorto();
        }
        //Atualiza label que mostra os níveis de habilidade, sorte e energia do personagem na tela
        atualizaIndicesNaTelaDoPersonagem();
    }

    @Override
    protected void acaoBotoes(Secao secao) {


        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
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

                    //Seção que esta opção vai direcionar
                    abrirProximaSecao(secao.getProximasSecoes().get(1).getCodProximaSecao());
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