package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_266;
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

public class TelaSecao_266 extends TelaSecoesBasica {
    boolean testouHabilidade = false;
    JLabel textoBotaoTesteHabilidade;
    private int contadorDeSucessos = 0;
    private int qtdTestes = 3;

    public TelaSecao_266(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);
        labelNumOpcao1.setBounds(116,662, 50,50);
        botaoOpcao1.setBounds(120,670,40,50);
        botaoOpcao1.setToolTipText("Resultado do teste: SUCESSO - igual ou menor que a Habilidade");
        lbTextoOpcao1.setBounds(170,657,700,60);

        opcao2(secao);
        labelNumOpcao2.setBounds(116,712, 50,50);
        botaoOpcao2.setBounds(120,720,40,50);
        botaoOpcao2.setToolTipText("Resultado do teste: FALHA - maior que a Habilidade");
        lbTextoOpcao2.setBounds(170,707,700,60);

        acaoBotoes(secao);
        carregaBotaoTestarHabilidade();
    }

    private void testarHabilidade(){

        if ( testouHabilidade )
            return;

        if (AcoesSecao_266.testarHabilidadeRemoverEspada() ){

            //incrementa sucessos
            ++contadorDeSucessos;

            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
            CarregarTelas.telaMensagem("Resultado dos dados: "+AcoesSecao_266.getResultado2Dados()+
                    "\n\nHabilidade: "+DadosLivroCarregado.getPersonagem().getHabilidadeAtual()+
                    "\n\n"+contadorDeSucessos+" SUCESSO(S)!");

            //Após 3 tentativas de sucesso, libera o botão 1 de vitorioso.
            if (contadorDeSucessos == 3) {
                new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/vitoria.mp3", null);
                botaoOpcao2.setEnabled(false);
            }


        }else {
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
            CarregarTelas.telaMensagem("Resultado dos dados: "+AcoesSecao_266.getResultado2Dados()+
                    "\n\nHabilidade: "+DadosLivroCarregado.getPersonagem().getHabilidadeAtual()+
                    "\n\nFALHA!");
            botaoOpcao1.setEnabled(false);

            //se teve qualquer falha acaba
            testouHabilidade = true;
            textoBotaoTesteHabilidade.setText("<html><center>Você Perdeu!</center></html>");
        }

        if (contadorDeSucessos == 3) {
            textoBotaoTesteHabilidade.setText("<html><center>Teste Realizado!</center></html>");
            testouHabilidade = true;
        }
    }

    private void carregaBotaoTestarHabilidade() {
        int largura = 500;
        int altura = 100;
        int eixoY = 570;
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
                        var faltam = qtdTestes-contadorDeSucessos;
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nVocê ainda não realizou o desafio da luta de braço.\n\nFalta(m) realizar "+
                                faltam +" testes.");
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
                        var faltam = qtdTestes-contadorDeSucessos;
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nVocê ainda não realizou o desafio da luta de braço.\n\nFalta(m) realizar "+
                                faltam +" testes.");
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
