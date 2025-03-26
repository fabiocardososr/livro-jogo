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
        TelaRegrasOpcoes telaRegrasOpcoes = new TelaRegrasOpcoes(400,740);
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
        SecaoHistoriaInicial telaSecoesBasica = new SecaoHistoriaInicial(secao);
        telaSecoesBasica.setVisible(true);
    }

    public static void telaBolsa(Container container, int largura, int altura, JLabel lbEnergiaPersonagem,
                                 JLabel lbHabilidadePersonagem, JLabel lbSortePersonagem,
                                 JLabelOpcoesTelaSecao botaoProvisoes, JLabelOpcoesTelaSecao labelPocaoInicial, Secao secao){
        TelaBolsa telaBolsa = new TelaBolsa(container,largura,altura, lbEnergiaPersonagem,lbHabilidadePersonagem,
                lbSortePersonagem,botaoProvisoes, labelPocaoInicial,secao);

        telaBolsa.setVisible(true);
    }

    public static void telaMensagem(String texto){
        TelaDeMensagensAoJogador tela = new TelaDeMensagensAoJogador(texto);
        tela.setVisible(true);
    }

    public static void telaBatalha(Inimigo inimigo, TelaSecoesBasica tela, JPanel panelBotao){
        //tela.setVisible(false);
        TelaBatalha telaBatalha = new TelaBatalha(inimigo, tela, panelBotao);
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
            case 2   -> new TelaSecao_2(secao).setVisible(true); //Termina o jogo
            case 3   -> new TelaSecao_3(secao).setVisible(true);
            case 4   -> new TelaSecao_4(secao).setVisible(true); //perde energia
            case 5   -> new TelaSecao_5(secao).setVisible(true);
            case 6   -> new TelaSecao_6(secao).setVisible(true);
            case 7   -> new TelaSecao_7(secao).setVisible(true);  //Tem inimigos
            case 8   -> new TelaSecao_8(secao).setVisible(true);
            case 9   -> new TelaSecao_9(secao).setVisible(true);  //Tem inimigos
            case 10  -> new TelaSecao_10(secao).setVisible(true);
            case 11  -> new TelaSecao_11(secao).setVisible(true);
            case 12  -> new TelaSecao_12(secao).setVisible(true); //Remoção de itens. Chamado pelas seções: 46
            case 13  -> new TelaSecao_13(secao).setVisible(true);
            case 14  -> new TelaSecao_14(secao).setVisible(true); //Escolher itens para remover ou dar ouro
            case 15  -> new TelaSecao_15(secao).setVisible(true); //Tem inimigo
            case 16  -> new TelaSecao_16(secao).setVisible(true); //Pergunta se tem Poção Antiveneno
            case 17  -> new TelaSecao_17(secao).setVisible(true); //Jogar uma moeda no poço
            case 18  -> new TelaSecao_18(secao).setVisible(true); //Chamado pelo 19 (pode morrer, perda de energia)
            case 19  -> new TelaSecao_19(secao).setVisible(true);
            case 20  -> new TelaSecao_20(secao).setVisible(true);
            case 21  -> new TelaSecao_21(secao).setVisible(true);
            case 22  -> new TelaSecao_22(secao).setVisible(true);
            case 23  -> new TelaSecao_23(secao).setVisible(true); //chamado pela seção 7
            case 24  -> new TelaSecao_24(secao).setVisible(true);
            case 25  -> new TelaSecao_25(secao).setVisible(true);
            case 26  -> new TelaSecao_26(secao).setVisible(true); //Chamado pela seção 47
            case 27  -> new TelaSecao_27(secao).setVisible(true);
            case 28  -> new TelaSecao_28(secao).setVisible(true); //pergunta se possui braçadeira da Força
            case 29  -> new TelaSecao_29(secao).setVisible(true); //inimigos ORCs
            case 30  -> new TelaSecao_30(secao).setVisible(true); //perda de energia
            case 31  -> new TelaSecao_31(secao).setVisible(true);
            case 32  -> new TelaSecao_32(secao).setVisible(true); //perda de sorte, perde moedas e perde 2 itens
            case 33  -> new TelaSecao_33(secao).setVisible(true);
            case 34  -> new TelaSecao_34(secao).setVisible(true); //Ganho de Habilidade, Energia ou Sorte.
            case 35  -> new TelaSecao_35(secao).setVisible(true); //Perde energia.
            case 36  -> new TelaSecao_36(secao).setVisible(true); //Perde itens ou moedas.
            case 37  -> new TelaSecao_37(secao).setVisible(true);
            case 38  -> new TelaSecao_38(secao).setVisible(true);
            case 39  -> new TelaSecao_39(secao).setVisible(true);
            case 40  -> new TelaSecao_40(secao).setVisible(true);
            case 41  -> new TelaSecao_41(secao).setVisible(true);
            case 42  -> new TelaSecao_42(secao).setVisible(true); //Perde sorte automaticamente
            case 43  -> new TelaSecao_43(secao).setVisible(true); //Inimigos e permite fuga
            case 44  -> new TelaSecao_44(secao).setVisible(true); //Perde energia
            case 45  -> new TelaSecao_45(secao).setVisible(true); //Perde energia
            case 46  -> new TelaSecao_46(secao).setVisible(true); //Perde sorte
            case 47  -> new TelaSecao_47(secao).setVisible(true);
            case 48  -> new TelaSecao_48(secao).setVisible(true);
            case 49  -> new TelaSecao_49(secao).setVisible(true); //chamado pela seção 4
            case 50  -> new TelaSecao_50(secao).setVisible(true); //chamado pela seção 43
            case 51  -> new TelaSecao_51(secao).setVisible(true);
            case 52  -> new TelaSecao_52(secao).setVisible(true); //chamado pela seção 28
            case 53  -> new TelaSecao_53(secao).setVisible(true); //Perde ouro
            case 54  -> new TelaSecao_54(secao).setVisible(true); //chamado pela seção 1
            case 271 -> new TelaSecao_271(secao).setVisible(true); //chamado pela seção 12 (Escolher itens para remover)


            /************* TELAS DE SEÇÕES CHAMADAS PELA TELAS ACIMA, MAS INCOMPLETAS **************/
            case 56  -> new TelaSecao_56(secao).setVisible(true);//chamado pela seção 25
            case 67  -> new TelaSecao_67(secao).setVisible(true); //chamado pelas seções: 12 e 271
            case 78  -> new TelaSecao_78(secao).setVisible(true); //chamado pelas seções: 52
            case 89  -> new TelaSecao_89(secao).setVisible(true); //chamado pela seção 17
            case 91  -> new TelaSecao_91(secao).setVisible(true);//chamado pela seção 26
            case 92  -> new TelaSecao_92(secao).setVisible(true);//chamado pela seção 24,53
            case 93  -> new TelaSecao_93(secao).setVisible(true); //chamado pela seção 4
            case 120 -> new TelaSecao_120(secao).setVisible(true);//chamado pela seção 3
            case 132 -> new TelaSecao_132(secao).setVisible(true);//chamado pela seção 35
            case 148 -> new TelaSecao_148(secao).setVisible(true);//chamado pela seção 6
            case 149 -> new TelaSecao_149(secao).setVisible(true);//chamado pela seção 13
            case 150 -> new TelaSecao_150(secao).setVisible(true);//chamado pela seção 32
            case 165 -> new TelaSecao_165(secao).setVisible(true);//chamado pela seção 45
            case 176 -> new TelaSecao_176(secao).setVisible(true);//chamado pela seção 9
            case 187 -> new TelaSecao_187(secao).setVisible(true);//chamado pela seção 36
            case 188 -> new TelaSecao_188(secao).setVisible(true); //chamado pela seção 43,50
            case 198 -> new TelaSecao_198(secao).setVisible(true);//chamado pela seção 18
            case 199 -> new TelaSecao_199(secao).setVisible(true);//chamado pela seção 51
            case 211 -> new TelaSecao_211(secao).setVisible(true);//chamado pela seção 16
            case 217 -> new TelaSecao_217(secao).setVisible(true);//chamado pela seção 15
            case 220 -> new TelaSecao_220(secao).setVisible(true);//chamado pela seção 26,38,47
            case 225 -> new TelaSecao_225(secao).setVisible(true); //chamado pela seção 30
            case 226 -> new TelaSecao_226(secao).setVisible(true); //chamado pelas seções 21,36
            case 231 -> new TelaSecao_238(secao).setVisible(true); //chamado pela seção 34
            case 238 -> new TelaSecao_238(secao).setVisible(true); //chamado pela seção 17
            case 239 -> new TelaSecao_239(secao).setVisible(true); //chamado pela seção 41
            case 249 -> new TelaSecao_249(secao).setVisible(true);//chamado pela seção 5
            case 254 -> new TelaSecao_254(secao).setVisible(true);//chamado pela seção 29
            case 255 -> new TelaSecao_255(secao).setVisible(true);//chamado pela seção 14
            case 256 -> new TelaSecao_256(secao).setVisible(true); //chamado pela seção 17
            case 261 -> new TelaSecao_261(secao).setVisible(true);//chamado pela seção 1,54
            case 266 -> new TelaSecao_266(secao).setVisible(true);//chamado pela seção 28
            case 274 -> new TelaSecao_274(secao).setVisible(true);//chamado pela seção 40
            case 277 -> new TelaSecao_277(secao).setVisible(true);//chamado pela seção 20
            case 287 -> new TelaSecao_287(secao).setVisible(true);//chamado pela seção 39
            case 288 -> new TelaSecao_288(secao).setVisible(true);//chamado pela seção 48
            case 290 -> new TelaSecao_290(secao).setVisible(true);//chamado pela seção 10
            case 293 -> new TelaSecao_293(secao).setVisible(true);//chamado pela seção 44
            case 297 -> new TelaSecao_297(secao).setVisible(true);//chamado pela seção 271
            case 315 -> new TelaSecao_317(secao).setVisible(true);//chamado pela seção 38
            case 317 -> new TelaSecao_317(secao).setVisible(true);//chamado pela seção 8
            case 322 -> new TelaSecao_322(secao).setVisible(true);//chamado pela seção 3
            case 329 -> new TelaSecao_329(secao).setVisible(true);//chamado pela seção 27
            case 333 -> new TelaSecao_333(secao).setVisible(true);//chamado pela seção 5 Termina o jogo
            case 336 -> new TelaSecao_336(secao).setVisible(true);//chamado pela seção 37
            case 339 -> new TelaSecao_339(secao).setVisible(true);//chamado pela seção 23
            case 342 -> new TelaSecao_342(secao).setVisible(true);//chamado pela seção 42
            case 345 -> new TelaSecao_345(secao).setVisible(true);//chamado pela seção 16
            case 353 -> new TelaSecao_353(secao).setVisible(true);//chamado pela seção 11 Termina o jogo
            case 358 -> new TelaSecao_358(secao).setVisible(true);//chamado pela seção 22
            case 362 -> new TelaSecao_362(secao).setVisible(true); //chamado pela seção 31
            case 369 -> new TelaSecao_369(secao).setVisible(true); //chamado pela seção 25
            case 371 -> new TelaSecao_371(secao).setVisible(true); //chamado pela seção 49
            case 377 -> new TelaSecao_377(secao).setVisible(true);//chamado pela seção 24
            case 383 -> new TelaSecao_383(secao).setVisible(true);//chamado pela seção 29
            case 390 -> new TelaSecao_390(secao).setVisible(true);//chamado pela seção 33
            case 392 -> new TelaSecao_392(secao).setVisible(true);//chamado pela seção 8
            case 397 -> new TelaSecao_397(secao).setVisible(true);//chamado pela seção 51
            case 399 -> new TelaSecao_399(secao).setVisible(true);//chamado pela seção 54
        }
    }
}
