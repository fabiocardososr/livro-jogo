package livro.jogo.telas.desktop.principal;

import livro.jogo.entidades.Livro;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaRegrasOpcoes extends JDialog {
    private JLabel labelPersonagem;
    private JLabelOpcoesTelaSecao botaoCriarPersonagem;
    private JLabel labelRegraBatalha;
    private JLabelOpcoesTelaSecao botaoRegraBatalha;
    private JLabel labelRegraSorte;
    private JLabelOpcoesTelaSecao botaoRegraSorte;
    private JLabel labelRegraReposicaoIndices;
    private JLabelOpcoesTelaSecao botaoRegraReposicaoIndices;
    private JLabel labelRegraEquipamentos;
    private JLabelOpcoesTelaSecao botaoRegraEquipamentos;
    private JLabel labelDicas;
    private JLabelOpcoesTelaSecao botaoDicas;
    private JLabel labelSair;
    private JLabelOpcoesTelaSecao botaoSair;
    JLabelOpcoesTelaSecao labelFundo;


    public TelaRegrasOpcoes(int largura, int altura) {
        TelaRegrasOpcoesAcaoDosBotoes acao = new TelaRegrasOpcoesAcaoDosBotoes();

        //Configurando panel principal
        configurandoPanelPrincipal(largura, altura);

        //Configurando botões
        configurarBotaoCriarPersonagem(acao);
        configurarBotaoRegraBatalha(acao);
        configurarBotaoRegraSorte(acao);
        configurarBotaoRegraReposicaoIndices(acao);
        configurarBotaoRegraEquipamentos(acao);
        configurarBotaoRegraDicas(acao);
        configurarBotaoSair(acao);
        configurarFundoTela(largura,altura);
    }

    private void configurandoPanelPrincipal(int largura, int altura) {
        setSize(largura,altura);
        setModal(true);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setLocationRelativeTo(null);
        setTitle("Regras");
    }

    private void configurarFundoTela(int largura, int altura) {
        labelFundo = new JLabelOpcoesTelaSecao("",largura+100,altura+50,
                ImagensDoLivroFlorestaDaDestruicao.FUNDO_REGRAS_OPCOES);
        labelFundo.setBounds(0,0,largura,altura);
        //labelFundo.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        labelFundo.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelFundo);
    }

    private void configurarBotaoSair(TelaRegrasOpcoesAcaoDosBotoes acao) {
        labelSair = new JLabel("Sair");
        labelSair.setBounds(150,550,100,50);
        labelSair.setForeground(new Color(139,0,0));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelSair.addMouseListener(acao);
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoSair = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoSair.setBounds(90,535,220,90);
        botaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoSair.addMouseListener(acao);
        add(labelSair);
        add(botaoSair);
    }

    private void configurarBotaoRegraDicas(TelaRegrasOpcoesAcaoDosBotoes acao) {

        labelDicas = new JLabel("Dicas");
        labelDicas.setBounds(150,480,100,50);
        labelDicas.setForeground(new Color(139,0,0));
        labelDicas.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelDicas.setHorizontalAlignment(SwingConstants.CENTER);
        labelDicas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelDicas.addMouseListener(acao);
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoDicas = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoDicas.setBounds(90,465,220,90);
        botaoDicas.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoDicas.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoDicas.addMouseListener(acao);
        add(labelDicas);
        add(botaoDicas);
    }

    private void configurarBotaoRegraEquipamentos(TelaRegrasOpcoesAcaoDosBotoes acao) {

        labelRegraEquipamentos= new JLabel("Equipamentos");
        labelRegraEquipamentos.setBounds(140,410,130,50);
        labelRegraEquipamentos.setForeground(new Color(139,0,0));
        labelRegraEquipamentos.setFont(new Font(Font.SERIF,Font.BOLD,20));
        labelRegraEquipamentos.setHorizontalAlignment(SwingConstants.CENTER);
        labelRegraEquipamentos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelRegraEquipamentos.addMouseListener(acao);
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoRegraEquipamentos = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoRegraEquipamentos.setBounds(90,395,220,90);
        botaoRegraEquipamentos.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoRegraEquipamentos.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoRegraEquipamentos.addMouseListener(acao);
        add(labelRegraEquipamentos);
        add(botaoRegraEquipamentos);
    }

    private void configurarBotaoRegraReposicaoIndices(TelaRegrasOpcoesAcaoDosBotoes acao) {

        labelRegraReposicaoIndices= new JLabel("Índices");
        labelRegraReposicaoIndices.setBounds(138,340,130,50);
        labelRegraReposicaoIndices.setForeground(new Color(139,0,0));
        labelRegraReposicaoIndices.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelRegraReposicaoIndices.setHorizontalAlignment(SwingConstants.CENTER);
        labelRegraReposicaoIndices.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelRegraReposicaoIndices.addMouseListener(acao);
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoRegraReposicaoIndices = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoRegraReposicaoIndices.setBounds(90,325,220,90);
        botaoRegraReposicaoIndices.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoRegraReposicaoIndices.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoRegraReposicaoIndices.addMouseListener(acao);
        add(labelRegraReposicaoIndices);
        add(botaoRegraReposicaoIndices);
    }

    private void configurarBotaoRegraSorte(TelaRegrasOpcoesAcaoDosBotoes acao) {

        labelRegraSorte= new JLabel("Sorte");
        labelRegraSorte.setBounds(138,270,130,50);
        labelRegraSorte.setForeground(new Color(139,0,0));
        labelRegraSorte.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelRegraSorte.setHorizontalAlignment(SwingConstants.CENTER);
        labelRegraSorte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoRegraSorte = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoRegraSorte.setBounds(90,255,220,90);
        botaoRegraSorte.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoRegraSorte.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoRegraSorte.addMouseListener(acao);
        add(labelRegraSorte);
        add(botaoRegraSorte);
    }

    private void configurarBotaoRegraBatalha(TelaRegrasOpcoesAcaoDosBotoes acao) {

        labelRegraBatalha= new JLabel("Batalha");
        labelRegraBatalha.setBounds(138,200,130,50);
        labelRegraBatalha.setForeground(new Color(139,0,0));
        labelRegraBatalha.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelRegraBatalha.setHorizontalAlignment(SwingConstants.CENTER);
        labelRegraBatalha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelRegraBatalha.addMouseListener(acao);

        botaoRegraBatalha = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoRegraBatalha.setBounds(90,185,220,90);
        botaoRegraBatalha.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoRegraBatalha.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoRegraBatalha.addMouseListener(acao);
        add(labelRegraBatalha);
        add(botaoRegraBatalha);
    }

    private void configurarBotaoCriarPersonagem(TelaRegrasOpcoesAcaoDosBotoes acao){

        //Descrição do botão
        labelPersonagem= new JLabel("Personagem");
        labelPersonagem.setBounds(138,130,130,50);
        labelPersonagem.setForeground(new Color(139,0,0));
        labelPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,22));
        labelPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        labelPersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelPersonagem.addMouseListener(acao);

        //Botão que cria personagem
        botaoCriarPersonagem = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoCriarPersonagem.setBounds(90,115,220,90);
        botaoCriarPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoCriarPersonagem.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoCriarPersonagem.addMouseListener(acao);
        add(labelPersonagem);
        add(botaoCriarPersonagem);
    }

    private class TelaRegrasOpcoesAcaoDosBotoes implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {
                if ( (e.getSource() == botaoCriarPersonagem) || (e.getSource() == labelPersonagem) ){
                    Livro livro = DadosLivroCarregado.getLivro();
                    String titulo = "Personagem";

                    TelaRegra telaRegras = new TelaRegra(1200,790,titulo,livro.getRegraCalcularIndicesIniciais());
                    telaRegras.setTitle("Regra - Criar Personagem");
                    telaRegras.setVisible(true);
                }

                if ( (e.getSource() == botaoSair) || (e.getSource() == labelSair) ){
                    dispose();
                }

                if ( (e.getSource() == botaoRegraBatalha) || (e.getSource() == labelRegraBatalha) ){
                    Livro livro = DadosLivroCarregado.getLivro();
                    String titulo = "Batalha";

                    TelaRegra telaRegra = new TelaRegra(1200,790,titulo,livro.getRegraBatalha());
                    telaRegra.setTitle("Regra - Batalha");
                    telaRegra.setVisible(true);
                }

                if ( (e.getSource() == botaoRegraSorte) || (e.getSource() == labelRegraSorte) ){
                    Livro livro = DadosLivroCarregado.getLivro();
                    String titulo = "Sorte";

                    TelaRegra telaRegra = new TelaRegra(1200,790,titulo,livro.getRegraUsoSorte());
                    telaRegra.setTitle("Regra - Sorte");
                    telaRegra.setVisible(true);
                }

                if ( (e.getSource() == botaoRegraReposicaoIndices) || (e.getSource() == labelRegraReposicaoIndices) ){
                    Livro livro = DadosLivroCarregado.getLivro();
                    String titulo = "Índices";
                    TelaRegra telaRegra = new TelaRegra(1200,790,titulo,livro.getRegraReposicaoIndices());
                    telaRegra.setTitle("Regra - Repor HABILIDADE, SORTE e  ENERGIA");
                    telaRegra.setVisible(true);
                }

                if ( (e.getSource() == botaoRegraEquipamentos) || (e.getSource() == labelRegraEquipamentos) ){
                    Livro livro = DadosLivroCarregado.getLivro();
                    String titulo = "Equipamentos";
                    TelaRegra telaRegras = new TelaRegra(1200,790,titulo,livro.getRegraEquipamentos());
                    telaRegras.setTitle("Regra - Equipamentos e Poções");
                    telaRegras.setVisible(true);
                }

                if ( (e.getSource() == botaoDicas) || (e.getSource() == labelDicas) ){
                    Livro livro = DadosLivroCarregado.getLivro();
                    String titulo = "Dicas";
                    TelaRegra telaRegras = new TelaRegra(1200,790,titulo,livro.getDicas());
                    telaRegras.setTitle("Dicas");
                    telaRegras.setVisible(true);
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
                if ( (e.getSource() == botaoCriarPersonagem) || (e.getSource() == labelPersonagem) ){
                    botaoCriarPersonagem.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                            botaoCriarPersonagem.getWidth(), botaoCriarPersonagem.getHeight()).getImageIcon());
                    repaint();
                }

                if ( (e.getSource() == botaoRegraBatalha) || (e.getSource() == labelRegraBatalha) ){
                    botaoRegraBatalha.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                            botaoRegraBatalha.getWidth(), botaoRegraBatalha.getHeight()).getImageIcon());
                    repaint();
                }

                if ( (e.getSource() == botaoRegraSorte) || (e.getSource() == labelRegraSorte) ){
                    botaoRegraSorte.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                            botaoRegraSorte.getWidth(), botaoRegraSorte.getHeight()).getImageIcon());
                    repaint();
                }

                if ( (e.getSource() == botaoRegraReposicaoIndices) || (e.getSource() == labelRegraReposicaoIndices) ){
                    botaoRegraReposicaoIndices.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                            botaoRegraReposicaoIndices.getWidth(), botaoRegraReposicaoIndices.getHeight()).getImageIcon());
                    repaint();
                }

                if ( (e.getSource() == botaoRegraEquipamentos) || (e.getSource() == labelRegraEquipamentos) ){
                    botaoRegraEquipamentos.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                            botaoRegraEquipamentos.getWidth(), botaoRegraEquipamentos.getHeight()).getImageIcon());
                    repaint();
                }

                if ( (e.getSource() == botaoDicas) || (e.getSource() == labelDicas) ){
                    botaoDicas.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                            botaoDicas.getWidth(), botaoDicas.getHeight()).getImageIcon());
                    repaint();
                }

                if ( (e.getSource() == botaoSair) || (e.getSource() == labelSair) ){
                    botaoSair.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                            botaoSair.getWidth(), botaoSair.getHeight()).getImageIcon());
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if ( (e.getSource() == botaoCriarPersonagem) || (e.getSource() == labelPersonagem) ){
                    botaoCriarPersonagem.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                            botaoCriarPersonagem.getWidth(), botaoCriarPersonagem.getHeight()).getImageIcon());
                    repaint();
                }

                if ( (e.getSource() == botaoRegraBatalha) || (e.getSource() == labelRegraBatalha) ){
                    botaoRegraBatalha.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                            botaoRegraBatalha.getWidth(), botaoRegraBatalha.getHeight()).getImageIcon());
                    repaint();
                }

                if ( (e.getSource() == botaoRegraSorte) || (e.getSource() == labelRegraSorte) ){
                    botaoRegraSorte.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                            botaoRegraSorte.getWidth(), botaoRegraSorte.getHeight()).getImageIcon());
                    repaint();
                }
                if ( (e.getSource() == botaoRegraReposicaoIndices) || (e.getSource() == labelRegraReposicaoIndices) ){
                    botaoRegraReposicaoIndices.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                            botaoRegraReposicaoIndices.getWidth(), botaoRegraReposicaoIndices.getHeight()).getImageIcon());
                    repaint();
                }

                if ( (e.getSource() == botaoRegraEquipamentos) || (e.getSource() == labelRegraEquipamentos) ){
                    botaoRegraEquipamentos.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                            botaoRegraEquipamentos.getWidth(), botaoRegraEquipamentos.getHeight()).getImageIcon());
                    repaint();
                }

                if ( (e.getSource() == botaoDicas) || (e.getSource() == labelDicas) ){
                    botaoDicas.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                            botaoDicas.getWidth(), botaoDicas.getHeight()).getImageIcon());
                    repaint();
                }

                if ( (e.getSource() == botaoSair) || (e.getSource() == labelSair) ){
                    botaoSair.setIcon(new RedimensionarImagem(
                            ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                            botaoSair.getWidth(), botaoSair.getHeight()).getImageIcon());
                    repaint();
                }
            }
        } //FIM AcaoDosBotoes implements ActionListener

}
