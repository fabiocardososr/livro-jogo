package livro.jogo.telas.desktop.centralizacaotelas;

import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.TelasDisponiveisParaCarregamento;
import livro.jogo.telas.desktop.*;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.secoes.SecaoHistoriaInicial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CarregarTelas {
    private TelaBasica telaPrincipal; //Guardar a referência da tela principal

    public CarregarTelas(TelaBasica telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    public CarregarTelas() {
    }

    public TelaBasica getTelaPrincipal() {
        return telaPrincipal;
    }

    public void carregarTela(TelasDisponiveisParaCarregamento tela, String titulo, String tituloTela, String texto){
        switch(tela) {
            case TelasDisponiveisParaCarregamento.TELA_PRINCIPAL:
                telaPrincipal();
                break;
            case TelasDisponiveisParaCarregamento.TELA_REGRAS_OPCOES:
                telaRegrasOpcoes();
                break;
            case TelasDisponiveisParaCarregamento.TELA_REGRAS:
               telaRegra(titulo, tituloTela, texto);
                break;
            case TelasDisponiveisParaCarregamento.TELA_CRIACAO_PERSONAGEM:
                telaCriacaoPersonagem();
                break;
            default:
                break;
        }

    }

    private void telaPrincipal(){
        TelaPrincipal tela = new TelaPrincipal(1430,720);
        this.telaPrincipal = tela;
        tela.setVisible(true);
    }

    private void telaRegrasOpcoes(){
        TelaRegrasOpcoes telaRegrasOpcoes = new TelaRegrasOpcoes(350,740);
        telaRegrasOpcoes.setVisible(true);
    }

    private void telaRegra(String titulo, String tituloTela, String texto){
        TelaRegra telaRegras = new TelaRegra(1200,790,titulo,texto);
        telaRegras.setTitle(tituloTela);
        telaRegras.setVisible(true);
    }

    private void telaCriacaoPersonagem(){
        TelaCriarPersonagem telaCriarPersonagem = new TelaCriarPersonagem(1150,820, telaPrincipal);

        //Quando fechar este tela, deve voltar para a principal
        telaCriarPersonagem.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                telaPrincipal.setVisible(true);
                //System.exit(0);
            }
        });
        telaCriarPersonagem.setVisible(true);
    }

    public static void telaSecaoHistoriaInicial(Secao secao, Personagem personagem, JFrame telaPrincipal){
        SecaoHistoriaInicial telaSecoesBasica = new SecaoHistoriaInicial(secao, personagem, telaPrincipal);
        telaSecoesBasica.setVisible(true);
    }

    public static void telaBolsa(int largura, int altura){
        TelaBolsa telaBolsa = new TelaBolsa(largura,altura);

        telaBolsa.setVisible(true);
    }
}
