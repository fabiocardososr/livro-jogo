package livro.jogo.telas.desktop;

import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.TelasDisponiveisParaCarregamento;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
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

    //Esta tela é a de confirmação para FECHAR TELA. Parâmetro "true" significa que é tela de confirmação se quer fechar a tela
    public static void telaMensagem(String texto, TelaSecoesBasica dialog){
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
            case 1      -> secao_1(secao);
            case 2      -> secao_2(secao);     //Termina o jogo
            case 3      -> secao_3(secao);
            case 4      -> secao_4(secao);
            case 54     -> secao_54(secao);    //chamado pela seção 1 (INCOMPLETO)
            case 120    -> secao_120(secao);   //chamado pela seção 3 (INCOMPLETO)
            case 261    -> secao_261(secao);   //chamado pela seção 1 (INCOMPLETO)
            case 322    -> secao_322(secao);   //chamado pela seção 3 (INCOMPLETO)

        }
    }

    /*************** MÉTODOS DE INICIALIZAÇÃO DAS SEÇÕES ***************/

    private static void secao_1(Secao secao){
        new TelaSecao_1(secao).setVisible(true);
    }

    private static void secao_2(Secao secao){
        new TelaSecao_2(secao).setVisible(true);
    }

    private static void secao_3(Secao secao) {
        new TelaSecao_3(secao).setVisible(true);
    }

    private static void secao_4(Secao secao) {
        new TelaSecao_4(secao).setVisible(true);
    }

    private static void secao_54(Secao secao) {
        new TelaSecao_54(secao).setVisible(true);
    }

    private static void secao_120(Secao secao) {
        new TelaSecao_120(secao).setVisible(true);
    }

    private static void secao_261(Secao secao) {
        new TelaSecao_261(secao).setVisible(true);
    }

    private static void secao_322(Secao secao) {
        new TelaSecao_322(secao).setVisible(true);
    }

}
