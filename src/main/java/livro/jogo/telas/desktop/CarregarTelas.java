package livro.jogo.telas.desktop;

import livro.jogo.entidades.Inimigo;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.TelasDisponiveisParaCarregamento;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.principal.*;
import livro.jogo.telas.desktop.secoes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CarregarTelas {
    private static TelaBasica telaPrincipal; //Guardar a referência da tela principal
    private static boolean resultadoTelaDeConfirmacao = false;

    public static boolean isResultadoTelaDeConfirmacao() {
        return resultadoTelaDeConfirmacao;
    }

    public CarregarTelas(TelaBasica telaPai) {
        this.telaPrincipal = telaPai;
    }

    public CarregarTelas() {
    }

    public static TelaBasica getReferenciaTelaPrincipal() {
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
            case TelasDisponiveisParaCarregamento.TELA_CARREGAR_JOGO:
                telaCarregarJogo();
                break;
            default:
                break;
        }

    }

    private void telaCarregarJogo() {
        TelaCarregarJogoSalvo tela = new TelaCarregarJogoSalvo(250,300);
        tela.setVisible(true);
    }

    private static void telaPrincipal(){
        TelaPrincipal tela = new TelaPrincipal(1430,720);
        telaPrincipal = tela;
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

    public static void telaSecaoHistoriaInicial(Secao secao){
        SecaoHistoriaInicial telaSecoesBasica = new SecaoHistoriaInicial(secao, getReferenciaTelaPrincipal());
        telaSecoesBasica.setVisible(true);
    }

    public static void telaBolsa(Container container, int largura, int altura, JLabel lbEnergiaPersonagem,
                                 JLabel lbHabilidadePersonagem, JLabel lbSortePersonagem,
                                 JLabelOpcoesTelaSecao botaoProvisoes, JLabelOpcoesTelaSecao labelPocaoInicial){
        TelaBolsa telaBolsa = new TelaBolsa(container,largura,altura, lbEnergiaPersonagem,lbHabilidadePersonagem,
                lbSortePersonagem,botaoProvisoes, labelPocaoInicial);

        telaBolsa.setVisible(true);
    }

    public static void telaMensagem(String texto){
        TelaDeMensagensAoJogador tela = new TelaDeMensagensAoJogador(texto);
        tela.setVisible(true);
    }

    public static void telaBatalha(Inimigo inimigo, TelaSecoesBasica tela){
        //tela.setVisible(false);
        TelaBatalha telaBatalha = new TelaBatalha(inimigo, tela, false);
        telaBatalha.setVisible(true);
    }

    //Esta tela é a de confirmação para FECHAR TELA. Parâmetro "true" significa que é tela de confirmação se quer fechar a tela
    public static void telaMensagem(String texto, TelaSecoesBasica dialog){
        TelaDeMensagensAoJogador tela = new TelaDeMensagensAoJogador(texto, dialog);
        tela.setLocationRelativeTo(null);
        tela.setResizable(false);
        tela.setUndecorated(true);
        tela.setModal(true);
        tela.setVisible(true);
    }

    public static void telaMensagem(String texto, TelaBatalha dialog){
        TelaDeMensagensAoJogador tela = new TelaDeMensagensAoJogador(texto, dialog);
        tela.setLocationRelativeTo(null);
        tela.setResizable(false);
        tela.setUndecorated(true);
        tela.setModal(true);
        tela.setVisible(true);
    }

    public static void telaAnotacoes(Personagem personagem){
        TelaAnotacoes tela = new TelaAnotacoes(personagem);
        tela.setVisible(true);

    }

    public static void carregarSecao(Secao secao){

        if (secao == null) {
            telaSecaoHistoriaInicial(null);
            return;
        }

        //Aqui vai ser chamado as seções
        switch ( secao.getCodSecaoLivro() ){
            case 1   -> new TelaSecao_1(secao).setVisible(true);
            case 2   -> new TelaSecao_2(secao).setVisible(true);  //Termina o jogo
            case 3   -> new TelaSecao_3(secao).setVisible(true);
            case 4   -> new TelaSecao_4(secao).setVisible(true);
            case 5   -> new TelaSecao_5(secao).setVisible(true);
            case 6   -> new TelaSecao_6(secao).setVisible(true);
            case 7   -> new TelaSecao_7(secao).setVisible(true);
            case 49  -> new TelaSecao_49(secao).setVisible(true); //chamado pela seção 4 (INCOMPLETO)
            case 54  -> new TelaSecao_54(secao).setVisible(true); //chamado pela seção 1 (INCOMPLETO)
            case 93  -> new TelaSecao_93(secao).setVisible(true); //chamado pela seção 4 (INCOMPLETO)
            case 120 -> new TelaSecao_120(secao).setVisible(true);//chamado pela seção 3 (INCOMPLETO)
            case 148 -> new TelaSecao_148(secao).setVisible(true);//chamado pela seção 6 (INCOMPLETO)
            case 249 -> new TelaSecao_249(secao).setVisible(true);//chamado pela seção 5 (INCOMPLETO)
            case 261 -> new TelaSecao_261(secao).setVisible(true);//chamado pela seção 1 (INCOMPLETO)
            case 322 -> new TelaSecao_322(secao).setVisible(true);//chamado pela seção 3 (INCOMPLETO)
            case 333 -> new TelaSecao_333(secao).setVisible(true);//chamado pela seção 5 (INCOMPLETO) Termina o jogo


        }
    }
}
