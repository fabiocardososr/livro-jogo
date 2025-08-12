package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_182;
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

public class TelaSecao_182 extends TelaSecoesBasica {
    boolean testouHabilidade = false;
    JLabel textoBotaoTesteHabilidade;

    public TelaSecao_182(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        labelNumOpcao1.setBounds(116,520, 50,50);
        botaoOpcao1.setBounds(120,528,40,50);
        lbTextoOpcao1.setBounds(170,515,700,60);


        labelNumOpcao2.setBounds(116,565, 50,50);
        botaoOpcao2.setBounds(120,573,40,50);
        lbTextoOpcao2.setBounds(170,560,700,60);

        carregaBotaoTestarHabilidade();
    }

    private void testarHabilidade(){

        if ( testouHabilidade )
            return;

        if ( AcoesSecao_182.testarHabilidadeRemoverEspada() ){
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
            CarregarTelas.telaMensagem("Resultado dos dados: "+AcoesSecao_182.getResultado2Dados()+
                            "\n\nHabilidade: "+DadosLivroCarregado.getPersonagem().getHabilidadeAtual()+
                    "\n\nSUCESSO!");
            botaoOpcao2.setEnabled(false);
        }else {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
            CarregarTelas.telaMensagem("Resultado dos dados: "+AcoesSecao_182.getResultado2Dados()+
                    "\n\nHabilidade: "+DadosLivroCarregado.getPersonagem().getHabilidadeAtual()+
                    "\n\nFALHA!");
            botaoOpcao1.setEnabled(false);
        }

        textoBotaoTesteHabilidade.setText("<html><center>Teste Realizado!</center></html>");
        testouHabilidade = true;
    }

    private void carregaBotaoTestarHabilidade() {
        int largura = 500;
        int altura = 100;
        int eixoY = 435;
        int eixoX = 190;

        //Texto botão repor habilidade
        textoBotaoTesteHabilidade = new JLabel("<html><center>Realizar Teste</center></html>");
        textoBotaoTesteHabilidade.setBounds(eixoX+125,eixoY+18,250,60);
        textoBotaoTesteHabilidade.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textoBotaoTesteHabilidade.setHorizontalAlignment(SwingConstants.CENTER);
        textoBotaoTesteHabilidade.setVerticalAlignment(SwingConstants.CENTER);
        textoBotaoTesteHabilidade.setFont(new Font(Font.SERIF,Font.BOLD,25));
        textoBotaoTesteHabilidade.setForeground(new Color(128,0,0));
        textoBotaoTesteHabilidade.setToolTipText("SUCESSO: igual ou menor que a Habilidade" +
                " - FALHA: maior que a Habilidade");
        textoBotaoTesteHabilidade.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                testarHabilidade();
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
        JLabelOpcoesTelaSecao botaoTeste = new JLabelOpcoesTelaSecao("",largura, altura,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoTeste.setBounds(eixoX,eixoY,largura,altura);
        botaoTeste.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                testarHabilidade();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoTeste.setIcon(Util.dimensionarImagem(botaoTeste.getWidth(),
                        botaoTeste.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoTeste.setIcon(Util.dimensionarImagem(botaoTeste.getWidth(),
                        botaoTeste.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        add(textoBotaoTesteHabilidade);
        add(botaoTeste);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1) {

                    if ( !testouHabilidade ){
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nVocê ainda não fez o Teste!");
                        return;
                    }

                    if ( botaoOpcao1.isEnabled() )
                        abrirProximaSecao(secao.getProximasSecoes().getFirst().getCodProximaSecao());
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
                if (e.getSource() == botaoOpcao2) {

                    if ( !testouHabilidade ){
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nVocê ainda não fez o Teste!");
                        return;
                    }

                    if ( botaoOpcao2.isEnabled() ){
                        abrirProximaSecao(secao.getProximasSecoes().get(1).getCodProximaSecao());
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
