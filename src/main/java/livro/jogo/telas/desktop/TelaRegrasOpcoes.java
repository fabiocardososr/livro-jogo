package livro.jogo.telas.desktop;

import livro.jogo.entidades.Livro;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.utils.ManipularDadosLivro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaRegrasOpcoes extends TelaBasica {
    private JLabelOpcoesTelaSecao botaoCriarPersonagem;
    private JLabelOpcoesTelaSecao botaoRegraBatalha;
    private JLabelOpcoesTelaSecao botaoRegraSorte;
    private JLabelOpcoesTelaSecao botaoRegraReposicaoIndices;
    private JLabelOpcoesTelaSecao botaoRegraEquipamentos;
    private JLabelOpcoesTelaSecao botaoDicas;
    private JLabelOpcoesTelaSecao botaoSair;
    JLabelOpcoesTelaSecao labelFundo;


    public TelaRegrasOpcoes(int largura, int altura) {
        super(largura, altura);
        setTitle("Regras");
        getContentPane().setBackground(new Color(210,180,140));


        TelaRegrasOpcoesAcaoDosBotoes acao = new TelaRegrasOpcoesAcaoDosBotoes();
        configurarBotaoCriarPersonagem(acao);
        configurarBotaoRegraBatalha(acao);
        configurarBotaoRegraSorte(acao);
        configurarBotaoRegraReposicaoIndices(acao);
        configurarBotaoRegraEquipamentos(acao);
        configurarBotaoRegraDicas(acao);
        configurarBotaoSair(acao);
        configurarFundoTela(largura,altura);
    }

    private void configurarFundoTela(int largura, int altura) {
        labelFundo = new JLabelOpcoesTelaSecao("",largura+100,altura+50,
                ImagensDoLivroFlorestaDaDestruicao.FUNDO_REGRAS_OPCOES);
        labelFundo.setBounds(0,0,largura,altura);
        //labelFundo.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        labelFundo.setHorizontalAlignment(SwingConstants.CENTER);

        //FAIXA SUPERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaSuperiorEsquerda = new JLabelOpcoesTelaSecao(null,
                150, 100,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_ESQUERDA);
        labelFaixaSuperiorEsquerda.setBounds(-65,-35,150,100);

        //FAIXA SUPERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaSuperiorDireita = new JLabelOpcoesTelaSecao(null,
                150, 100,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_DIREITA);
        labelFaixaSuperiorDireita.setBounds(252,-45,150,100);

        //FAIXA INFERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaInferiorDireita = new JLabelOpcoesTelaSecao(null,
                150, 100,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_DIREITA);
        labelFaixaInferiorDireita.setBounds(265,677,150,100);

        //FAIXA INFERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaInferiorEsquerda = new JLabelOpcoesTelaSecao(null,
                150, 100,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_ESQUERDA);
        labelFaixaInferiorEsquerda.setBounds(-55,680,150,100);

        add(labelFaixaSuperiorEsquerda);
        add(labelFaixaSuperiorDireita);
        add(labelFaixaInferiorDireita);
        add(labelFaixaInferiorEsquerda);
        add(labelFundo);
    }

    private void configurarBotaoSair(TelaRegrasOpcoesAcaoDosBotoes acao) {
        JLabel labelSair = new JLabel("Sair");
        labelSair.setBounds(125,550,100,50);
        labelSair.setForeground(new Color(139,0,0));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoSair = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoSair.setBounds(65,535,220,90);
        botaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoSair.addMouseListener(acao);
        add(labelSair);
        add(botaoSair);
    }

    private void configurarBotaoRegraDicas(TelaRegrasOpcoesAcaoDosBotoes acao) {

        JLabel labelDicas = new JLabel("Dicas");
        labelDicas.setBounds(125,480,100,50);
        labelDicas.setForeground(new Color(139,0,0));
        labelDicas.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelDicas.setHorizontalAlignment(SwingConstants.CENTER);
        labelDicas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoDicas = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoDicas.setBounds(65,465,220,90);
        botaoDicas.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoDicas.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoDicas.addMouseListener(acao);
        add(labelDicas);
        add(botaoDicas);
    }

    private void configurarBotaoRegraEquipamentos(TelaRegrasOpcoesAcaoDosBotoes acao) {

        JLabel label= new JLabel("Equipamentos");
        label.setBounds(115,410,130,50);
        label.setForeground(new Color(139,0,0));
        label.setFont(new Font(Font.SERIF,Font.BOLD,20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoRegraEquipamentos = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoRegraEquipamentos.setBounds(65,395,220,90);
        botaoRegraEquipamentos.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoRegraEquipamentos.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoRegraEquipamentos.addMouseListener(acao);
        add(label);
        add(botaoRegraEquipamentos);
    }

    private void configurarBotaoRegraReposicaoIndices(TelaRegrasOpcoesAcaoDosBotoes acao) {

        JLabel label= new JLabel("Índices");
        label.setBounds(113,340,130,50);
        label.setForeground(new Color(139,0,0));
        label.setFont(new Font(Font.SERIF,Font.BOLD,25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoRegraReposicaoIndices = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoRegraReposicaoIndices.setBounds(65,325,220,90);
        botaoRegraReposicaoIndices.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoRegraReposicaoIndices.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoRegraReposicaoIndices.addMouseListener(acao);
        add(label);
        add(botaoRegraReposicaoIndices);
    }

    private void configurarBotaoRegraSorte(TelaRegrasOpcoesAcaoDosBotoes acao) {

        JLabel label= new JLabel("Sorte");
        label.setBounds(113,270,130,50);
        label.setForeground(new Color(139,0,0));
        label.setFont(new Font(Font.SERIF,Font.BOLD,25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoRegraSorte = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoRegraSorte.setBounds(65,255,220,90);
        botaoRegraSorte.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoRegraSorte.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoRegraSorte.addMouseListener(acao);
        add(label);
        add(botaoRegraSorte);
    }

    private void configurarBotaoRegraBatalha(TelaRegrasOpcoesAcaoDosBotoes acao) {

        JLabel label= new JLabel("Batalha");
        label.setBounds(113,200,130,50);
        label.setForeground(new Color(139,0,0));
        label.setFont(new Font(Font.SERIF,Font.BOLD,25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botaoRegraBatalha = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoRegraBatalha.setBounds(65,185,220,90);
        botaoRegraBatalha.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoRegraBatalha.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoRegraBatalha.addMouseListener(acao);
        add(label);
        add(botaoRegraBatalha);
    }

    private void configurarBotaoCriarPersonagem(TelaRegrasOpcoesAcaoDosBotoes acao){

        JLabel label= new JLabel("Personagem");
        label.setBounds(113,130,130,50);
        label.setForeground(new Color(139,0,0));
        label.setFont(new Font(Font.SERIF,Font.BOLD,25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botaoCriarPersonagem = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoCriarPersonagem.setBounds(65,115,220,90);
        botaoCriarPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoCriarPersonagem.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoCriarPersonagem.addMouseListener(acao);
        add(label);
        add(botaoCriarPersonagem);
    }

        private class TelaRegrasOpcoesAcaoDosBotoes implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoCriarPersonagem){
                Livro livro = ManipularDadosLivro.getLivro();
                String titulo = "Criar Personagem";

                TelaRegra telaRegras = new TelaRegra(1200,790,titulo,livro.getRegraCalcularIndicesIniciais());
                telaRegras.setTitle("Regra - Criar Personagem");
                telaRegras.setVisible(true);
            }

                if (e.getSource() == botaoSair){
                    setVisible(false);
                }

            if (e.getSource() == botaoRegraBatalha){
                Livro livro = ManipularDadosLivro.getLivro();
                String titulo = "Batalha";

                TelaRegra telaRegra = new TelaRegra(1200,790,titulo,livro.getRegraBatalha());
                telaRegra.setTitle("Regra - Batalha");
                telaRegra.setVisible(true);
            }

            if (e.getSource() == botaoRegraSorte){
                Livro livro = ManipularDadosLivro.getLivro();
                String titulo = "Sorte";

                TelaRegra telaRegra = new TelaRegra(1200,790,titulo,livro.getRegraUsoSorte());
                telaRegra.setTitle("Regra - Sorte");
                telaRegra.setVisible(true);
            }

            if (e.getSource() == botaoRegraReposicaoIndices){
                Livro livro = ManipularDadosLivro.getLivro();
                String titulo = "Repor HABILIDADE, SORTE e  ENERGIA";
                TelaRegra telaRegra = new TelaRegra(1200,790,titulo,livro.getRegraReposicaoIndices());
                telaRegra.setTitle("Regra - Repor HABILIDADE, SORTE e  ENERGIA");
                telaRegra.setVisible(true);
            }

            if (e.getSource() == botaoRegraEquipamentos){
                Livro livro = ManipularDadosLivro.getLivro();
                String titulo = "Equipamentos e Poções";
                TelaRegra telaRegras = new TelaRegra(1200,790,titulo,livro.getRegraEquipamentos());
                telaRegras.setTitle("Regra - Equipamentos e Poções");
                telaRegras.setVisible(true);
            }

            if (e.getSource() == botaoDicas){
                Livro livro = ManipularDadosLivro.getLivro();
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        } //FIM AcaoDosBotoes implements ActionListener

//    private class TelaRegrasOpcoesAcaoDosBotoes implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == botaoCriarPersonagem){
//                Livro livro = ManipularDadosLivro.getLivro();
//                String titulo = "Criar Personagem";
//
//                TelaRegra telaRegras = new TelaRegra(1200,790,titulo,livro.getRegraCalcularIndicesIniciais());
//                telaRegras.setTitle("Regra - Criar Personagem");
//                telaRegras.setVisible(true);
//            }
//
//            if (e.getSource() == botaoRegraBatalha){
//                Livro livro = ManipularDadosLivro.getLivro();
//                String titulo = "Batalha";
//
//                TelaRegra telaRegra = new TelaRegra(1200,790,titulo,livro.getRegraBatalha());
//                telaRegra.setTitle("Regra - Batalha");
//                telaRegra.setVisible(true);
//            }
//
//            if (e.getSource() == botaoRegraSorte){
//                Livro livro = ManipularDadosLivro.getLivro();
//                String titulo = "Sorte";
//
//                TelaRegra telaRegra = new TelaRegra(1200,790,titulo,livro.getRegraUsoSorte());
//                telaRegra.setTitle("Regra - Sorte");
//                telaRegra.setVisible(true);
//            }
//
//            if (e.getSource() == botaoRegraReposicaoIndices){
//                Livro livro = ManipularDadosLivro.getLivro();
//                String titulo = "Repor HABILIDADE, SORTE e  ENERGIA";
//                TelaRegra telaRegra = new TelaRegra(1200,790,titulo,livro.getRegraReposicaoIndices());
//                telaRegra.setTitle("Regra - Repor HABILIDADE, SORTE e  ENERGIA");
//                telaRegra.setVisible(true);
//            }
//
//            if (e.getSource() == botaoRegraEquipamentos){
//                Livro livro = ManipularDadosLivro.getLivro();
//                String titulo = "Equipamentos e Poções";
//                TelaRegra telaRegras = new TelaRegra(1200,790,titulo,livro.getRegraEquipamentos());
//                telaRegras.setTitle("Regra - Equipamentos e Poções");
//                telaRegras.setVisible(true);
//            }
//
//            if (e.getSource() == botaoDicas){
//                Livro livro = ManipularDadosLivro.getLivro();
//                String titulo = "Dicas";
//                TelaRegra telaRegras = new TelaRegra(1200,790,titulo,livro.getDicas());
//                telaRegras.setTitle("Dicas");
//                telaRegras.setVisible(true);
//            }
//
//        }
//    } //FIM AcaoDosBotoes implements ActionListener


}
