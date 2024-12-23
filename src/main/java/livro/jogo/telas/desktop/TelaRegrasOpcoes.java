package livro.jogo.telas.desktop;

import livro.jogo.entidades.Livro;
import livro.jogo.utils.ManipularDadosLivro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaRegrasOpcoes extends Tela{
    private JButton botaoCriarPersonagem;
    private JButton botaoRegraBatalha;
    private JButton botaoRegraSorte;
    private JButton botaoRegraReposicaoIndices;
    private JButton botaoRegraEquipamentos;
    private JButton botaoDicas;
    private final Livro livro;


    public TelaRegrasOpcoes(int largura, int altura, Livro livro) {
        super(largura, altura);
        setTitle("Regras");
        this.livro = livro;

        TelaRegrasOpcoesAcaoDosBotoes acao = new TelaRegrasOpcoesAcaoDosBotoes();
        configurarBotaoCriarPersonagem(acao);
        configurarBotaoRegraBatalha(acao);
        configurarBotaoRegraSorte(acao);
        configurarBotaoRegraReposicaoIndices(acao);
        configurarBotaoRegraEquipamentos(acao);
        configurarBotaoRegraDicas(acao);
    }

    private void configurarBotaoRegraDicas(TelaRegrasOpcoesAcaoDosBotoes acao) {
        botaoDicas = new JButton("Dicas");
        botaoDicas.setBackground(Color.BLACK);
        botaoDicas.setForeground(Color.WHITE);
        botaoDicas.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoDicas.setBounds(20,290,250,50);
        botaoDicas.setFocusable(false);
        botaoDicas.addActionListener(acao);
        add(botaoDicas);
    }

    private void configurarBotaoRegraEquipamentos(TelaRegrasOpcoesAcaoDosBotoes acao) {
        botaoRegraEquipamentos = new JButton("Equipamentos e Poções");
        botaoRegraEquipamentos.setBackground(Color.BLACK);
        botaoRegraEquipamentos.setForeground(Color.WHITE);
        botaoRegraEquipamentos.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRegraEquipamentos.setBounds(20,235,250,50);
        botaoRegraEquipamentos.setFocusable(false);
        botaoRegraEquipamentos.addActionListener(acao);
        add(botaoRegraEquipamentos);
    }

    private void configurarBotaoRegraReposicaoIndices(TelaRegrasOpcoesAcaoDosBotoes acao) {
        botaoRegraReposicaoIndices = new JButton("Repor Índices");
        botaoRegraReposicaoIndices.setBackground(Color.BLACK);
        botaoRegraReposicaoIndices.setForeground(Color.WHITE);
        botaoRegraReposicaoIndices.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRegraReposicaoIndices.setBounds(20,180,250,50);
        botaoRegraReposicaoIndices.setFocusable(false);
        botaoRegraReposicaoIndices.addActionListener(acao);
        add(botaoRegraReposicaoIndices);
    }

    private void configurarBotaoRegraSorte(TelaRegrasOpcoesAcaoDosBotoes acao) {
        botaoRegraSorte = new JButton("Sorte");
        botaoRegraSorte.setBackground(Color.BLACK);
        botaoRegraSorte.setForeground(Color.WHITE);
        botaoRegraSorte.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRegraSorte.setBounds(20,125,250,50);
        botaoRegraSorte.setFocusable(false);
        botaoRegraSorte.addActionListener(acao);
        add(botaoRegraSorte);
    }

    private void configurarBotaoRegraBatalha(TelaRegrasOpcoesAcaoDosBotoes acao) {
        botaoRegraBatalha = new JButton("Batalha");
        botaoRegraBatalha.setBackground(Color.BLACK);
        botaoRegraBatalha.setForeground(Color.WHITE);
        botaoRegraBatalha.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoRegraBatalha.setBounds(20,70,250,50);
        botaoRegraBatalha.setFocusable(false);
        botaoRegraBatalha.addActionListener(acao);
        add(botaoRegraBatalha);
    }

    private void configurarBotaoCriarPersonagem(TelaRegrasOpcoesAcaoDosBotoes acao){
        botaoCriarPersonagem = new JButton("Criar Personagem");
        botaoCriarPersonagem.setBackground(Color.BLACK);
        botaoCriarPersonagem.setForeground(Color.WHITE);
        botaoCriarPersonagem.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        botaoCriarPersonagem.setBounds(20,15,250,50);
        botaoCriarPersonagem.setFocusable(false);
        botaoCriarPersonagem.addActionListener(acao);
        add(botaoCriarPersonagem);
    }

    public Livro getLivro() {
        return livro;
    }

    private class TelaRegrasOpcoesAcaoDosBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botaoCriarPersonagem){
                Livro livro = ManipularDadosLivro.getLivro();
                String titulo = "Criar Personagem";

                TelaRegras telaRegras = new TelaRegras(1200,790,titulo,livro.getRegraCalcularIndicesIniciais());
                telaRegras.setTitle("Regra - Criar Personagem");
                telaRegras.setVisible(true);
            }

            if (e.getSource() == botaoRegraBatalha){
                Livro livro = ManipularDadosLivro.getLivro();
                String titulo = "Batalha";

                TelaRegras telaRegras = new TelaRegras(1200,790,titulo,livro.getRegraBatalha());
                telaRegras.setTitle("Regra - Batalha");
                telaRegras.setVisible(true);
            }

            if (e.getSource() == botaoRegraSorte){
                Livro livro = ManipularDadosLivro.getLivro();
                String titulo = "Sorte";

                TelaRegras telaRegras = new TelaRegras(1200,790,titulo,livro.getRegraUsoSorte());
                telaRegras.setTitle("Regra - Sorte");
                telaRegras.setVisible(true);
            }

            if (e.getSource() == botaoRegraReposicaoIndices){
                Livro livro = ManipularDadosLivro.getLivro();
                String titulo = "Repor HABILIDADE, SORTE e  ENERGIA";
                TelaRegras telaRegras = new TelaRegras(1200,790,titulo,livro.getRegraReposicaoIndices());
                telaRegras.setTitle("Regra - Repor HABILIDADE, SORTE e  ENERGIA");
                telaRegras.setVisible(true);
            }

            if (e.getSource() == botaoRegraEquipamentos){
                Livro livro = ManipularDadosLivro.getLivro();
                String titulo = "Equipamentos e Poções";
                TelaRegras telaRegras = new TelaRegras(1200,790,titulo,livro.getRegraEquipamentos());
                telaRegras.setTitle("Regra - Equipamentos e Poções");
                telaRegras.setVisible(true);
            }

            if (e.getSource() == botaoDicas){
                Livro livro = ManipularDadosLivro.getLivro();
                String titulo = "Dicas";
                TelaRegras telaRegras = new TelaRegras(1200,790,titulo,livro.getDicas());
                telaRegras.setTitle("Dicas");
                telaRegras.setVisible(true);
            }

        }
    } //FIM AcaoDosBotoes implements ActionListener
}
