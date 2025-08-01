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
        TelaPrincipal tela = new TelaPrincipal(1275,622);
        telaPrincipal = tela;
        tela.setVisible(true);
    }

    private void telaRegrasOpcoes(){
        TelaRegrasOpcoes telaRegrasOpcoes = new TelaRegrasOpcoes(400,730);
        telaRegrasOpcoes.setVisible(true);
    }

    private void telaRegra(String titulo, String tituloTela, String texto){
        TelaRegra telaRegras = new TelaRegra(1200,790,titulo,texto);
        telaRegras.setTitle(tituloTela);
        telaRegras.setVisible(true);
    }

    private void telaCriacaoPersonagem(){
        TelaCriarPersonagem telaCriarPersonagem = new TelaCriarPersonagem(1150,622, telaPrincipal);

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
        ///Resolvi nao chamar a seção 2 a partir da 227. Pois uniformizei todos os inimigos retirando qualquer ataque especial
        ///e o GHOUL paraliza, e ao paralizar ou matar chama a seção 2 sendo que a tela de batalha, ao zerar a energia,
        /// já faz isso
        switch ( secao.getCodSecaoLivro() ){
            case 1   -> new TelaSecao_1(secao).setVisible(true); //chamado por 98
            //case 2   -> new TelaSecao_2(secao).setVisible(true); //Chamado por 227 - Termina o jogo
            case 3   -> new TelaSecao_3(secao).setVisible(true); //Chamado por 247
            case 4   -> new TelaSecao_4(secao).setVisible(true); //perde energia (chamado por 81)
            case 5   -> new TelaSecao_5(secao).setVisible(true); //Chamado por 152
            case 6   -> new TelaSecao_6(secao).setVisible(true); //Chamado por 187
            case 7   -> new TelaSecao_7(secao).setVisible(true);  //Tem 3 inimigos (chamado por 92)
            case 8   -> new TelaSecao_8(secao).setVisible(true); //Chamado por 160,343
            case 9   -> new TelaSecao_9(secao).setVisible(true);  //Tem 1 inimigos (chamado por 85)
            case 10  -> new TelaSecao_10(secao).setVisible(true); //chamado por 137
            case 11  -> new TelaSecao_11(secao).setVisible(true); //Chamado por 342
            case 12  -> new TelaSecao_12(secao).setVisible(true); //Chamado pelas seções: 46,192 - Remoção de itens.
            case 13  -> new TelaSecao_13(secao).setVisible(true); //Chamado por 82 - perde 3 pontos de sorte
            case 14  -> new TelaSecao_14(secao).setVisible(true); //Chamado por 319 - Escolher itens para remover ou dar ouro
            case 15  -> new TelaSecao_15(secao).setVisible(true); //Tem 1 inimigo - chamado por 125
            case 16  -> new TelaSecao_16(secao).setVisible(true); //Chamado seção 69,282 (Pergunta se tem Poção Antiveneno)
            case 17  -> new TelaSecao_17(secao).setVisible(true); //Jogar uma moeda no poço - chamado por 233
            case 18  -> new TelaSecao_18(secao).setVisible(true); //Chamado pelo 19 (pode morrer, perda de energia)
            case 19  -> new TelaSecao_19(secao).setVisible(true); //Chamado por 259 - perde energia
            case 20  -> new TelaSecao_20(secao).setVisible(true); //Chamado por 148
            case 21  -> new TelaSecao_21(secao).setVisible(true); //Chamado por 336
            case 22  -> new TelaSecao_22(secao).setVisible(true); //chamado por 126 - perde habilidade e energia de acordo com rolagem de dado
            case 23  -> new TelaSecao_23(secao).setVisible(true); //chamado pela seção 7
            case 24  -> new TelaSecao_24(secao).setVisible(true); //Chamado por 197
            case 25  -> new TelaSecao_25(secao).setVisible(true); //chamado por 74, 101,140,196,267,287
            case 26  -> new TelaSecao_26(secao).setVisible(true); //Chamado pela seção 47,353
            case 27  -> new TelaSecao_27(secao).setVisible(true); //Chamado por seção 123
            case 28  -> new TelaSecao_28(secao).setVisible(true); //Chamado por 209 - pergunta se possui braçadeira da Força
            case 29  -> new TelaSecao_29(secao).setVisible(true); //Chamado por 226 - 2 inimigos ORCs - fuga
            case 30  -> new TelaSecao_30(secao).setVisible(true); //Chamado por 295 - perda de energia
            case 31  -> new TelaSecao_31(secao).setVisible(true); //chamado por 110,257
            case 32  -> new TelaSecao_32(secao).setVisible(true); //Chamado por 346 - perda de sorte, perde moedas e perde 2 itens
            case 33  -> new TelaSecao_33(secao).setVisible(true); //Chamado por 243
            case 34  -> new TelaSecao_34(secao).setVisible(true); //chamado por 356 - Ganho de Habilidade, Energia ou Sorte.
            case 35  -> new TelaSecao_35(secao).setVisible(true); //Perde energia.
            case 36  -> new TelaSecao_36(secao).setVisible(true); //Chamado por 344 - Perde itens ou moedas.
            case 37  -> new TelaSecao_37(secao).setVisible(true); //Chamado por 239
            case 38  -> new TelaSecao_38(secao).setVisible(true); //chamado por 147
            case 39  -> new TelaSecao_39(secao).setVisible(true); //Chamado por 196 - perde a Rede de Aprisionamento(32)
            case 40  -> new TelaSecao_40(secao).setVisible(true); //Chamado por 40
            case 41  -> new TelaSecao_41(secao).setVisible(true); //Chamado por 81,317
            case 42  -> new TelaSecao_42(secao).setVisible(true); //Chamado por 315 - Perde sorte automaticamente
            case 43  -> new TelaSecao_43(secao).setVisible(true); //Chamado por 210,348 - 2 Inimigos e permite fuga
            case 44  -> new TelaSecao_44(secao).setVisible(true); //Perde energia. Chamado por 60
            case 45  -> new TelaSecao_45(secao).setVisible(true); //Chamado por 229 - Perde energia
            case 46  -> new TelaSecao_46(secao).setVisible(true); //Chamado por 192 - Perde sorte
            case 47  -> new TelaSecao_47(secao).setVisible(true); //Chamado por 158
            case 48  -> new TelaSecao_48(secao).setVisible(true); //Chamado por 372
            case 49  -> new TelaSecao_49(secao).setVisible(true); //chamado pela seção 4 (tem 2 inimigo Gremlin)
            case 50  -> new TelaSecao_50(secao).setVisible(true); //chamado pela seção 43
            case 51  -> new TelaSecao_51(secao).setVisible(true); //Chamado por 334
            case 52  -> new TelaSecao_52(secao).setVisible(true); //chamado pela seção 28
            case 53  -> new TelaSecao_53(secao).setVisible(true); //Chamado por 197 - Perde ouro
            case 54  -> new TelaSecao_54(secao).setVisible(true); //chamado pela seção 1
            case 55  -> new TelaSecao_55(secao).setVisible(true); //chamado por 137 - Equipa luvas de arremesso automaticamente
            case 56  -> new TelaSecao_56(secao).setVisible(true);//chamado pela seção 25
            case 57  -> new TelaSecao_57(secao).setVisible(true);//Teste de Sorte (chamado por 103)
            case 58  -> new TelaSecao_58(secao).setVisible(true);//chamado 173 - Ganha 25 ouro e a Cabeça do Martelo dos Anões(6)
            case 59  -> new TelaSecao_59(secao).setVisible(true); //Chamado por 68,141,340,363
            case 60  -> new TelaSecao_60(secao).setVisible(true); //Chamado por 367 - Testar sorte
            case 61  -> new TelaSecao_61(secao).setVisible(true); //chamado por 93,121
            case 62  -> new TelaSecao_62(secao).setVisible(true); //Chamado por 96,276
            case 63  -> new TelaSecao_63(secao).setVisible(true); //Chamado por 251
            case 64  -> new TelaSecao_64(secao).setVisible(true); //Chamado por 138 - Consome Poção Controle das Plantas(8)
            case 65  -> new TelaSecao_65(secao).setVisible(true); //Chamado por 299,339
            case 66  -> new TelaSecao_66(secao).setVisible(true);//Chamado por 394
            case 67  -> new TelaSecao_67(secao).setVisible(true); //chamado pelas seções: 12 e 271,297,378
            case 68  -> new TelaSecao_68(secao).setVisible(true); //Chamado por 363 - Toma poção e recupera 3 pontos de energia
            case 69  -> new TelaSecao_69(secao).setVisible(true); //chamada por 136,214,304
            case 70  -> new TelaSecao_70(secao).setVisible(true); //chamado por 182 - Equipar Espada magnífica
            case 71  -> new TelaSecao_71(secao).setVisible(true); //Chamado por 151 - 1 inimigo Gremlin
            case 72  -> new TelaSecao_72(secao).setVisible(true); //Chamado por 252
            case 73  -> new TelaSecao_73(secao).setVisible(true); //Chamado por 379 - Realizar teste rolando dados para vê se abre porta
            case 74  -> new TelaSecao_74(secao).setVisible(true); //Chamado por 376 - Adquire moedas e sino.
            case 75  -> new TelaSecao_75(secao).setVisible(true); //Chamado por 299,339
            case 76  -> new TelaSecao_76(secao).setVisible(true); //Chamado por 289
            case 77  -> new TelaSecao_77(secao).setVisible(true); //Chamado por 77
            case 78  -> new TelaSecao_78(secao).setVisible(true); //chamado pelas seções: 52 (inclui Poeira da levitação(11))
            case 79  -> new TelaSecao_79(secao).setVisible(true); //Chamado por 325 - 3 inimigos morcegos vampiros
            case 80  -> new TelaSecao_80(secao).setVisible(true); //Chamado por 143
            case 81  -> new TelaSecao_81(secao).setVisible(true); //Teste de sorte. Chamado por 121,284
            case 82  -> new TelaSecao_82(secao).setVisible(true); //Chamado por 250 - Pergunta se tem poção de Imobilidade
            case 83  -> new TelaSecao_83(secao).setVisible(true); // chamado por 155 - Perde energia; uso de Beladona (pegue esta como exemplo)
            case 84  -> new TelaSecao_84(secao).setVisible(true); //chamado por 288 -Inimigo Bicho Rochedo
            case 85  -> new TelaSecao_85(secao).setVisible(true); //chamado por 169,313,338
            case 86  -> new TelaSecao_86(secao).setVisible(true); //Chamado por 86
            case 87  -> new TelaSecao_87(secao).setVisible(true); //Chamado por 198
            case 88  -> new TelaSecao_88(secao).setVisible(true); //Chamado por 201,249,293,389
            case 89  -> new TelaSecao_89(secao).setVisible(true); //chamado pela seção 17,172
            case 90  -> new TelaSecao_90(secao).setVisible(true); //Testar Sorte - chamado pela seção 87,119
            case 91  -> new TelaSecao_91(secao).setVisible(true);//chamado pela seção 26 - Recupera sorte
            case 92  -> new TelaSecao_92(secao).setVisible(true);//chamado pela seção 24,53,77,205,377 (pergunta sobre Poção Controle de insetos(16))
            case 93  -> new TelaSecao_93(secao).setVisible(true); //chamado pela seção 4,371
            case 94  -> new TelaSecao_94(secao).setVisible(true); //Chamado por 120,322
            case 95  -> new TelaSecao_95(secao).setVisible(true); //Chamado por 292 - Pergunta sobre a Poeira da Levitação(11)
            case 96  -> new TelaSecao_96(secao).setVisible(true); //Chamado por 396 - Inimigos 3 cães-de-caça e um caçador mascarado
            case 97  -> new TelaSecao_97(secao).setVisible(true); //Chamado por 148,382
            case 98  -> new TelaSecao_98(secao).setVisible(true); //Chamado por 381 - Testar sorte: falhando no teste, morre!
            case 99  -> new TelaSecao_99(secao).setVisible(true); //Chamado por 208,323
            case 100 -> new TelaSecao_100(secao).setVisible(true);//chamado pela seção 92
            case 101 -> new TelaSecao_101(secao).setVisible(true);//Chamado por 310,321 - ganha 5 moedas e sino
            case 102 -> new TelaSecao_102(secao).setVisible(true); //Chamado por 323,349
            case 103 -> new TelaSecao_103(secao).setVisible(true); //Chamado por 112,332
            case 104 -> new TelaSecao_104(secao).setVisible(true); //Chamado por 360 ( 3 bandidos e 1 bandida)
            case 105 -> new TelaSecao_105(secao).setVisible(true); //chamado pela seção 102,180
            case 106 -> new TelaSecao_106(secao).setVisible(true); //Chamado por 175,294 - Anel de ouro com uma grande esmeralda(18) e 2 pontos de sorte
            case 107 -> new TelaSecao_107(secao).setVisible(true); //chamado pela seção 88,212 (inimigo Demônio de fogo)
            case 108 -> new TelaSecao_108(secao).setVisible(true); //Chamado por 336 - perde energia
            case 109 -> new TelaSecao_109(secao).setVisible(true); //Chamado por 156,207,274,302
            case 110 -> new TelaSecao_110(secao).setVisible(true); //Chamado por 193
            case 111 -> new TelaSecao_111(secao).setVisible(true); //Chamado por 346
            case 112 -> new TelaSecao_112(secao).setVisible(true); //chamado pela seção 58,73,200,292,306,312,327,351,368,379
            case 113 -> new TelaSecao_113(secao).setVisible(true); //chamado pela seção 67,334
            case 114 -> new TelaSecao_114(secao).setVisible(true); //Chamado por 277 - Verifica a existência de Cápsula de Fogo
            case 115 -> new TelaSecao_115(secao).setVisible(true); //Chamado por 179,220,275
            case 116 -> new TelaSecao_116(secao).setVisible(true); //Chamado por 330 - Coleira de couro com aplicações em ouro(20).
            case 117 -> new TelaSecao_117(secao).setVisible(true); //Chamado por 168 - 1 Inimigo Goblin
            case 118 -> new TelaSecao_118(secao).setVisible(true); //chamado pela seção 97,328 (inimigo javali selvagem)
            case 119 -> new TelaSecao_119(secao).setVisible(true); //Chamado por 268
            case 120 -> new TelaSecao_120(secao).setVisible(true);//chamado pela seção 3
            case 121 -> new TelaSecao_121(secao).setVisible(true); //Chamado por 242,270
            case 122 -> new TelaSecao_122(secao).setVisible(true); //Chamado por 256
            case 123 -> new TelaSecao_123(secao).setVisible(true);//chamado pela seção 114 (1 inimigo e tem fuga)
            case 124 -> new TelaSecao_124(secao).setVisible(true);//chamado pela seção 109,331
            case 125 -> new TelaSecao_125(secao).setVisible(true); //Chamado por 157
            case 126 -> new TelaSecao_126(secao).setVisible(true); //Chamado por 263 - Verifica a existência de Filtros Nasais
            case 127 -> new TelaSecao_127(secao).setVisible(true); //Chamado por 366 - Paga 3 peças de ouro ao Centauro
            case 128 -> new TelaSecao_128(secao).setVisible(true); //Chamado por 384 - inimigo bárbaro
            case 129 -> new TelaSecao_129(secao).setVisible(true); //Chamado por 266 - perda de sorte, escolher perder 10 moedas ou 1 item
            case 130 -> new TelaSecao_130(secao).setVisible(true); //Chamado por 149
            case 131 -> new TelaSecao_131(secao).setVisible(true); //Chamado por 186
            case 132 -> new TelaSecao_132(secao).setVisible(true);//chamado pela seção 35 (verifica se tem Flauta do Sono do Dragão )
            case 133 -> new TelaSecao_133(secao).setVisible(true); //Chamado por 305,374 - Coloca anel no dedo(Anel da Lentidão)
            case 134 -> new TelaSecao_134(secao).setVisible(true); //Chamado por 199 - Perde 1 ponto de sorte
            case 135 -> new TelaSecao_135(secao).setVisible(true);//chamado pela seção 122,225
            case 136 -> new TelaSecao_136(secao).setVisible(true);//chamado pela seção 94
            case 137 -> new TelaSecao_137(secao).setVisible(true); //Chamado por 230
            case 138 -> new TelaSecao_138(secao).setVisible(true);//chamado pela seção 72,300
            case 139 -> new TelaSecao_139(secao).setVisible(true);//chamado pela seção 83
            case 140 -> new TelaSecao_140(secao).setVisible(true);//chamado pela seção 298
            case 141 -> new TelaSecao_141(secao).setVisible(true);//Chamado por 340 - Perde sorte
            case 142 -> new TelaSecao_142(secao).setVisible(true);//chamado pela seção 64,159
            case 143 -> new TelaSecao_143(secao).setVisible(true);//chamado pela seção 69 (perde energia)
            case 144 -> new TelaSecao_144(secao).setVisible(true);//chamado pela seção 94,164,247,380
            case 145 -> new TelaSecao_145(secao).setVisible(true); //Chamado por 291,326
            case 146 -> new TelaSecao_146(secao).setVisible(true);//chamado pela seção 84 (adquirir item pedra)
            case 147 -> new TelaSecao_147(secao).setVisible(true); //Chamado por 289
            case 148 -> new TelaSecao_148(secao).setVisible(true);//chamado pela seção 6,308,373
            case 149 -> new TelaSecao_149(secao).setVisible(true);//chamado pela seção 13,113,185,235,324,341
            case 150 -> new TelaSecao_150(secao).setVisible(true);//chamado pela seção 32,111,223,236,375
            case 151 -> new TelaSecao_151(secao).setVisible(true);//chamado pela seção 61,135
            case 152 -> new TelaSecao_152(secao).setVisible(true);//chamado pela seção 107
            case 153 -> new TelaSecao_153(secao).setVisible(true);//chamado pela seção 130 (inimigo Mulher-gato TEM FUGA)
            case 154 -> new TelaSecao_154(secao).setVisible(true);//Chamado por 395
            case 155 -> new TelaSecao_155(secao).setVisible(true); //Verifica se tem Beladona(14)
            case 156 -> new TelaSecao_156(secao).setVisible(true); //Chamado por 195,352 - Perde energia
            case 157 -> new TelaSecao_157(secao).setVisible(true); //Chamado por 301,392
            case 158 -> new TelaSecao_158(secao).setVisible(true); //Chamado por 342
            case 159 -> new TelaSecao_159(secao).setVisible(true);//chamado pela seção 138 (perde energia)
            case 160 -> new TelaSecao_160(secao).setVisible(true); //Chamado por 177 - pagar moeda ao corvo, opção 1
            case 161 -> new TelaSecao_161(secao).setVisible(true); //Chamado por 324
            case 162 -> new TelaSecao_162(secao).setVisible(true); //Chamado por 181
            case 163 -> new TelaSecao_163(secao).setVisible(true);//chamado pela seção 56,245,281,397
            case 164 -> new TelaSecao_164(secao).setVisible(true); //Chamado por 249
            case 165 -> new TelaSecao_165(secao).setVisible(true);//chamado pela seção 45,229 (inimigo Gremlin. Desvantagem -3 pontos)
            case 166 -> new TelaSecao_166(secao).setVisible(true);//Chamado por 243 - Perdeu moeda
            case 167 -> new TelaSecao_167(secao).setVisible(true);//chamado pela seção 132 (inimigo dragão)
            case 168 -> new TelaSecao_168(secao).setVisible(true);//chamado pela seção 55,385
            case 169 -> new TelaSecao_169(secao).setVisible(true); //Chamado por 240 - perde habilidade e Energia de acordo com rolagem de dados
            case 170 -> new TelaSecao_170(secao).setVisible(true); //Chamado por 236
            case 171 -> new TelaSecao_171(secao).setVisible(true);//chamado pela seção 59,150
            case 172 -> new TelaSecao_172(secao).setVisible(true); //Chamado por 359
            case 173 -> new TelaSecao_173(secao).setVisible(true);//chamado pela seção 95 (Remove Poeira da Levitação(11)) questiona se tem água benta
            case 174 -> new TelaSecao_174(secao).setVisible(true); //chamado por 118 - grande anel de ouro (24) e ganha sorte
            case 175 -> new TelaSecao_175(secao).setVisible(true); //Chamado por 294
            case 176 -> new TelaSecao_176(secao).setVisible(true);//chamado pela seção 9 - Cabo do martelo de guerra dos anões(25)
            case 177 -> new TelaSecao_177(secao).setVisible(true); //Chamado por 261
            case 178 -> new TelaSecao_178(secao).setVisible(true);//chamado pela seção 63,142,260,366
            case 179 -> new TelaSecao_179(secao).setVisible(true);//Chamado por 275 - Equipa Elmo(54)
            case 180 -> new TelaSecao_180(secao).setVisible(true);//chamado pela seção 124,329
            case 181 -> new TelaSecao_181(secao).setVisible(true);//Chamado por 335 - inimigo homem-peixe
            case 182 -> new TelaSecao_182(secao).setVisible(true);//Chamado por 255 - Testar habilidade jogando 2 dados
            case 183 -> new TelaSecao_183(secao).setVisible(true);//chamado pela seção 60
            case 184 -> new TelaSecao_184(secao).setVisible(true);//Chamado por 191 - Remove sino da bolsa e recupera 4 pontos de energia
            case 185 -> new TelaSecao_185(secao).setVisible(true);//chamado pela seção 161
            case 186 -> new TelaSecao_186(secao).setVisible(true);//Chamado por 394 - Inimigo Enguia
            case 187 -> new TelaSecao_187(secao).setVisible(true);//chamado pela seção 36,206,253
            case 188 -> new TelaSecao_188(secao).setVisible(true); //chamado pela seção 43,50,210,348
            case 189 -> new TelaSecao_189(secao).setVisible(true); //Chamado por 264
            case 190 -> new TelaSecao_190(secao).setVisible(true); //chamado pela seção 171,390
            case 191 -> new TelaSecao_191(secao).setVisible(true); //Chamado por 369 - Verifica sino de metal
            case 192 -> new TelaSecao_192(secao).setVisible(true); //Chamado por 307
            case 193 -> new TelaSecao_193(secao).setVisible(true); //Chamado por 398 - Inimigo Gremlin
            case 194 -> new TelaSecao_194(secao).setVisible(true); //Ganha Beladona
            case 195 -> new TelaSecao_195(secao).setVisible(true); //Chamado por 274
            case 196 -> new TelaSecao_196(secao).setVisible(true); //Chamado por 267 - Verifica se tem rede de aprisionamento
            case 197 -> new TelaSecao_197(secao).setVisible(true); //Chamado por 309 - Verifica se tem poção Antiveneno
            case 198 -> new TelaSecao_198(secao).setVisible(true);//chamado pela seção 18, 139,244,316
            case 199 -> new TelaSecao_199(secao).setVisible(true);//chamado pela seção 51,221
            case 200 -> new TelaSecao_200(secao).setVisible(true); //Usar chave. (remover chave de prata da bolsa(5))
            case 201 -> new TelaSecao_201(secao).setVisible(true); //Chamado por 215
            case 202 -> new TelaSecao_202(secao).setVisible(true);//chamado pela seção 153
            case 203 -> new TelaSecao_203(secao).setVisible(true); //Chamado por 187
            case 204 -> new TelaSecao_204(secao).setVisible(true); //Chamado por 246,311 - Verifica se possui todas as partes do martelo
            case 205 -> new TelaSecao_205(secao).setVisible(true); //Chamdo por 377 - ganha moedas
            case 206 -> new TelaSecao_206(secao).setVisible(true);//chamado pela seção 76
            case 207 -> new TelaSecao_207(secao).setVisible(true); //Chamado por 352
            case 208 -> new TelaSecao_208(secao).setVisible(true);//chamado pela seção 62,86,194
            case 209 -> new TelaSecao_209(secao).setVisible(true);//chamado pela seção 99
            case 210 -> new TelaSecao_210(secao).setVisible(true);//chamado pela seção 90
            case 211 -> new TelaSecao_211(secao).setVisible(true);//chamado pela seção 16 - Consome Poção antiveneno
            case 212 -> new TelaSecao_212(secao).setVisible(true);//chamado pela seção 88 - Inimigos e Fuga
            case 213 -> new TelaSecao_213(secao).setVisible(true);//chamado pela seção 143
            case 214 -> new TelaSecao_214(secao).setVisible(true); //Chmado por 237 - Consome Poção Curativa(27)
            case 215 -> new TelaSecao_215(secao).setVisible(true); //Chamado por 293,389
            case 216 -> new TelaSecao_216(secao).setVisible(true);//chamado pela seção 119
            case 217 -> new TelaSecao_217(secao).setVisible(true);//chamado pela seção 15
            case 218 -> new TelaSecao_218(secao).setVisible(true);//chamado por 358 - Escolher item para remover ou dar ouro
            case 219 -> new TelaSecao_219(secao).setVisible(true); //chamado por 241 - Ganha dardo de prata e sorte
            case 220 -> new TelaSecao_220(secao).setVisible(true);//chamado pela seção 26,38,47,147,353,391
            case 221 -> new TelaSecao_221(secao).setVisible(true);//chamado pela seção 188,238
            case 222 -> new TelaSecao_222(secao).setVisible(true);//Chamado por 367
            case 223 -> new TelaSecao_223(secao).setVisible(true); //Chamado por 170
            case 224 -> new TelaSecao_224(secao).setVisible(true); //Chamado por 231
            case 225 -> new TelaSecao_225(secao).setVisible(true); //chamado pela seção 30,295
            case 226 -> new TelaSecao_226(secao).setVisible(true); //chamado pelas seções 21,36,108,239
            case 227 -> new TelaSecao_227(secao).setVisible(true); //chamado pelas seções 173
            case 228 -> new TelaSecao_228(secao).setVisible(true); //Chamado por 319 - Equipar botas saltadoras(31)
            case 229 -> new TelaSecao_229(secao).setVisible(true); //Chamado por 270 - Testar sorte
            case 230 -> new TelaSecao_230(secao).setVisible(true); //Chamado por 337
            case 231 -> new TelaSecao_231(secao).setVisible(true); //chamado pela seção 34,154,318,356,370
            case 232 -> new TelaSecao_232(secao).setVisible(true); //chamado pela seção 117 - Cabo do martelo dos anões e ganha sorte
            case 233 -> new TelaSecao_233(secao).setVisible(true); //chamado pela seção 56,245
            case 234 -> new TelaSecao_234(secao).setVisible(true); //chamado pela seção 123,277
            case 235 -> new TelaSecao_235(secao).setVisible(true); //chamado pela seção 82
            case 236 -> new TelaSecao_236(secao).setVisible(true); //Chamado por 375
            case 237 -> new TelaSecao_237(secao).setVisible(true); //Chamado por 380 - Perde Sorte e Verifica se possui Poção curativa(27)
            case 238 -> new TelaSecao_238(secao).setVisible(true); //chamado pela seção 17,89,233,362
            case 239 -> new TelaSecao_239(secao).setVisible(true); //chamado pela seção 41,160,343
            case 240 -> new TelaSecao_240(secao).setVisible(true); //Chamado por 313 - Verifica Filtros Nasais
            case 241 -> new TelaSecao_241(secao).setVisible(true); //Chamado por 254 - Inimigo Urso
            case 242 -> new TelaSecao_242(secao).setVisible(true); //chamado pela seção 165 - Escolhe item para descartar e ganhar um lingote de ouro
            case 243 -> new TelaSecao_243(secao).setVisible(true); //chamado pela seção 191 - Pergunta se quer doar 1 moeda ao frade
            case 244 -> new TelaSecao_244(secao).setVisible(true); //Chamado 259
            case 245 -> new TelaSecao_245(secao).setVisible(true);//chamado pela seção 84,146,288
            case 246 -> new TelaSecao_246(secao).setVisible(true); //Chamado por 279
            case 247 -> new TelaSecao_247(secao).setVisible(true); //Chamado por 303
            case 248 -> new TelaSecao_248(secao).setVisible(true);//chamado pela seção 215
            case 249 -> new TelaSecao_249(secao).setVisible(true);//chamado pela seção 5, 152
            case 250 -> new TelaSecao_250(secao).setVisible(true);//chamado por 185,324
            case 251 -> new TelaSecao_251(secao).setVisible(true);//chamado pela seção 142 - Realizar teste se vc foi atingido por flecha
            case 252 -> new TelaSecao_252(secao).setVisible(true); //Chamado por 329
            case 253 -> new TelaSecao_253(secao).setVisible(true);//chamado pela seção 206
            case 254 -> new TelaSecao_254(secao).setVisible(true);//chamado pela seção 29,226,383
            case 255 -> new TelaSecao_255(secao).setVisible(true);//chamado pela seção 14,228
            case 256 -> new TelaSecao_256(secao).setVisible(true); //chamado pela seção 17,89,172
            case 257 -> new TelaSecao_257(secao).setVisible(true); //chamado pela seção 110
            case 258 -> new TelaSecao_258(secao).setVisible(true); //chamado pela seção 132
            case 259 -> new TelaSecao_259(secao).setVisible(true); //chamado pela seção 155 perde 3 pontos de energia e testa sorte, sendo azarado perde mais 2 pontos de energia
            case 260 -> new TelaSecao_260(secao).setVisible(true); //chamado pela seção 251
            case 261 -> new TelaSecao_261(secao).setVisible(true);//chamado pela seção 1,54 - Loja de itens de Yaztromo
            case 262 -> new TelaSecao_262(secao).setVisible(true);//chamado pela seção 217 - Ganha poção de habilidade com Armas, tem efeito temporário
            case 263 -> new TelaSecao_263(secao).setVisible(true);//chamado pela seção 232
            case 264 -> new TelaSecao_264(secao).setVisible(true);//chamado pela seção 69
            case 265 -> new TelaSecao_265(secao).setVisible(true);//chamado pela seção 190
            case 266 -> new TelaSecao_266(secao).setVisible(true);//chamado pela seção 28
            case 267 -> new TelaSecao_267(secao).setVisible(true);//chamado pela seção 140
            case 268 -> new TelaSecao_268(secao).setVisible(true); //Chamado por 325
            case 269 -> new TelaSecao_269(secao).setVisible(true);//chamado pela seção 69,189
            case 270 -> new TelaSecao_270(secao).setVisible(true);//chamado pela seção 93,284
            case 271 -> new TelaSecao_271(secao).setVisible(true); //chamado pela seção 12,307 (Escolher itens para remover)
            case 272 -> new TelaSecao_272(secao).setVisible(true);//chamado pela seção 128
            case 273 -> new TelaSecao_273(secao).setVisible(true);//chamado pela seção 71 - pegar medalhão
            case 274 -> new TelaSecao_274(secao).setVisible(true);//chamado pela seção 40,218
            case 275 -> new TelaSecao_275(secao).setVisible(true);//chamado pela seção 220 - Perde energia
            case 276 -> new TelaSecao_276(secao).setVisible(true);
            case 277 -> new TelaSecao_277(secao).setVisible(true);//chamado pela seção 20,115
            case 278 -> new TelaSecao_278(secao).setVisible(true);//chamado pela seção 198,216 - Testa sorte em queda
            //ATENÇÃO NESSA TELA 279 QUANDO FOR AJUSTAR O TAMANHO DAS TELAS
            case 279 -> new TelaSecao_279(secao).setVisible(true);//chamado por 360 - (criei nova mecânica de remoção de itens aqui)Remove peças de ouro ou itens. Máximo de 5 entre ouro e itens
            case 280 -> new TelaSecao_280(secao).setVisible(true);//Chamado por 390
            case 281 -> new TelaSecao_281(secao).setVisible(true);//chamado pela seção 39,89,172,359,362
            case 282 -> new TelaSecao_282(secao).setVisible(true);//chamado por 264
            case 283 -> new TelaSecao_283(secao).setVisible(true);//chamado por 134,199
            case 284 -> new TelaSecao_284(secao).setVisible(true);//chamado pela seção 135,296
            case 285 -> new TelaSecao_285(secao).setVisible(true);//chamado pela seção 145,162
            case 286 -> new TelaSecao_286(secao).setVisible(true); //Chamado por 187
            case 287 -> new TelaSecao_287(secao).setVisible(true);//chamado pela seção 39
            case 288 -> new TelaSecao_288(secao).setVisible(true); //Chamado por 294,314,372
            case 289 -> new TelaSecao_289(secao).setVisible(true);//chamado pela seção 177
            case 290 -> new TelaSecao_290(secao).setVisible(true);//chamado pela seção 10,230
            case 291 -> new TelaSecao_291(secao).setVisible(true);//chamado pela seção 208,323,349
            case 292 -> new TelaSecao_292(secao).setVisible(true); //Chamado por 351
            case 293 -> new TelaSecao_293(secao).setVisible(true);//chamado pela seção 44,80,183,189,211,222,269,282,345
            case 294 -> new TelaSecao_294(secao).setVisible(true);//Chamado por 314
            case 295 -> new TelaSecao_295(secao).setVisible(true);
            case 296 -> new TelaSecao_296(secao).setVisible(true);//chamado pela seção 151,273
            case 297 -> new TelaSecao_297(secao).setVisible(true);//chamado pela seção 271
            case 298 -> new TelaSecao_298(secao).setVisible(true);//chamado pela seção 127,178
            case 299 -> new TelaSecao_299(secao).setVisible(true);//chamado pela seção 92 - Perde Todas as provisões
            case 300 -> new TelaSecao_300(secao).setVisible(true);//chamado pela seção 219,254
            case 301 -> new TelaSecao_301(secao).setVisible(true); //Chamado por 317
            case 302 -> new TelaSecao_302(secao).setVisible(true);//chamado pela secao 207
            case 303 -> new TelaSecao_303(secao).setVisible(true);//chamado pela seção 134,199,283
            case 304 -> new TelaSecao_304(secao).setVisible(true);//chamado pela seção 237
            case 305 -> new TelaSecao_305(secao).setVisible(true);//chamado pela seção 167,258
            case 306 -> new TelaSecao_306(secao).setVisible(true);//chamado pela seção 149,213,280,357
            case 307 -> new TelaSecao_307(secao).setVisible(true);//Chamado por 378
            case 308 -> new TelaSecao_308(secao).setVisible(true);//Chamado por 373 - Efeito de troca de sorte com habilidade
            case 309 -> new TelaSecao_309(secao).setVisible(true);//chamado pela seção 109,252,331
            case 310 -> new TelaSecao_310(secao).setVisible(true); //Chamado por 376
            case 311 -> new TelaSecao_311(secao).setVisible(true);//chamado pela seção 104
            case 312 -> new TelaSecao_312(secao).setVisible(true);//chamado pela seção 227 - 25 moedas e cabeça de martelo letra G
            case 313 -> new TelaSecao_313(secao).setVisible(true);//chamado pela seção 55,385
            case 314 -> new TelaSecao_314(secao).setVisible(true);//chamado pela seção 116
            case 315 -> new TelaSecao_315(secao).setVisible(true);//chamado pela seção 38
            case 316 -> new TelaSecao_316(secao).setVisible(true);
            case 317 -> new TelaSecao_317(secao).setVisible(true);//chamado pela seção 8 - Inimigo e fuga
            case 318 -> new TelaSecao_318(secao).setVisible(true);//chamado pela seção 190
            case 319 -> new TelaSecao_319(secao).setVisible(true);//chamado pela seção 278 - verifica se possui botas saltadoras
            case 320 -> new TelaSecao_320(secao).setVisible(true);//Chamado por 398
            case 321 -> new TelaSecao_321(secao).setVisible(true);//chamado pela seção 212
            case 322 -> new TelaSecao_322(secao).setVisible(true);//chamado pela seção 3
            case 323 -> new TelaSecao_323(secao).setVisible(true);//chamado pela seção 174
            case 324 -> new TelaSecao_324(secao).setVisible(true);//chamado pela seção 113
            case 325 -> new TelaSecao_325(secao).setVisible(true);//chamado pela seção 66,131
            case 326 -> new TelaSecao_326(secao).setVisible(true); //Chamado por 335
            case 327 -> new TelaSecao_327(secao).setVisible(true);//chamado pela seção 73
            case 328 -> new TelaSecao_328(secao).setVisible(true);//chamado pela seção 97 (perde energia e perde provisão)
            case 329 -> new TelaSecao_329(secao).setVisible(true);//chamado pela seção 27
            case 330 -> new TelaSecao_330(secao).setVisible(true);//chamado pela seção 65,75 - 3 inimigos lobos
            case 331 -> new TelaSecao_331(secao).setVisible(true);//chamado pela seção 300
            case 332 -> new TelaSecao_332(secao).setVisible(true);//chamado pela seção 224,231
            case 333 -> new TelaSecao_333(secao).setVisible(true);//chamado pela seção 5,152 - Termina o jogo
            case 334 -> new TelaSecao_334(secao).setVisible(true);//chamado pela seção 70,182,255
            case 335 -> new TelaSecao_335(secao).setVisible(true);//chamado pela seção 291
            case 336 -> new TelaSecao_336(secao).setVisible(true);//chamado pela seção 37
            case 337 -> new TelaSecao_337(secao).setVisible(true);//chamado pela seção 125, 157,217,262
            case 338 -> new TelaSecao_338(secao).setVisible(true);//chamado pela seção 240
            case 339 -> new TelaSecao_339(secao).setVisible(true);//chamado pela seção 23,100
            case 340 -> new TelaSecao_340(secao).setVisible(true); //Chamado por 355
            case 341 -> new TelaSecao_341(secao).setVisible(true);//chamado pela seção 161 - Ganha moedas e itens
            case 342 -> new TelaSecao_342(secao).setVisible(true);//chamado pela seção 42,315 - possui Fita de Cabeça da Concentração(23)
            case 343 -> new TelaSecao_343(secao).setVisible(true);//chamado pela seção 160
            case 344 -> new TelaSecao_344(secao).setVisible(true);//chamado pela seção 253
            case 345 -> new TelaSecao_345(secao).setVisible(true);//chamado pela seção 16
            case 346 -> new TelaSecao_346(secao).setVisible(true);//chamado pela seção 170
            case 347 -> new TelaSecao_347(secao).setVisible(true);//chamado pela seção 141,340
            case 348 -> new TelaSecao_348(secao).setVisible(true);//chamado pela seção 90
            case 349 -> new TelaSecao_349(secao).setVisible(true);//chamado pela seção 78,99,129,209,354
            case 350 -> new TelaSecao_350(secao).setVisible(true);//chamado pela seção 114 - Consome Cápsula de Fogo
            case 351 -> new TelaSecao_351(secao).setVisible(true); //Chamado por 200,327 - Remove cápsulas de fogo
            case 352 -> new TelaSecao_352(secao).setVisible(true); //Chamado por 195 - 1 inimigo e fuga
            case 353 -> new TelaSecao_353(secao).setVisible(true);//chamado pela seção 11, 158 - perde todas as provisões
            case 354 -> new TelaSecao_354(secao).setVisible(true);//chamado pela seção 266 - Ganha poeira da levitação
            case 355 -> new TelaSecao_355(secao).setVisible(true);//chamado por 130,153,202
            case 356 -> new TelaSecao_356(secao).setVisible(true);//chamado por 265
            case 357 -> new TelaSecao_357(secao).setVisible(true);//chamado pela seção 150
            case 358 -> new TelaSecao_358(secao).setVisible(true);//chamado pela seção 22,55,85,168,169,176,230,232,263,290,313,337,338,365,385 - Testar Sorte
            case 359 -> new TelaSecao_359(secao).setVisible(true);//chamado pela seção 188
            case 360 -> new TelaSecao_360(secao).setVisible(true); //chamado pela seção 103,133,305,374 (4 homens e 1 mulher)
            case 361 -> new TelaSecao_361(secao).setVisible(true); //chamado pela seção 180
            case 362 -> new TelaSecao_362(secao).setVisible(true); //chamado pela seção 31,122,225,320
            case 363 -> new TelaSecao_363(secao).setVisible(true); //chamado por 347
            case 364 -> new TelaSecao_364(secao).setVisible(true);//Chamado por 396
            case 365 -> new TelaSecao_365(secao).setVisible(true); //chamado pela seção 126 - remove o filtro nasal e ganha caixa de prata
            case 366 -> new TelaSecao_366(secao).setVisible(true); //chamado pela seção 142
            case 367 -> new TelaSecao_367(secao).setVisible(true);//chamado pela seção 264
            case 368 -> new TelaSecao_368(secao).setVisible(true); //chamado pela seção 95
            case 369 -> new TelaSecao_369(secao).setVisible(true); //chamado pela seção 25,393
            case 370 -> new TelaSecao_370(secao).setVisible(true);//Chamado por 395
            case 371 -> new TelaSecao_371(secao).setVisible(true); //chamado pela seção 49 - ganha moeda e mão de argila(55)
            case 372 -> new TelaSecao_372(secao).setVisible(true); //chamado pela seção 175 - teste de habilidade
            case 373 -> new TelaSecao_373(secao).setVisible(true); //chamado pela seção 203,286
            case 374 -> new TelaSecao_374(secao).setVisible(true); //chamado pela seção 133,305 - Ganha manopla de Habilidade(39) usei nova função ded incluir item, pois estava criando outro objeto item sendo que ja tenho em DadosLivroCarregado
            case 375 -> new TelaSecao_375(secao).setVisible(true); //chamado pela seção 163
            case 376 -> new TelaSecao_376(secao).setVisible(true); //chamado pela seção 196 - Testar sorte
            case 377 -> new TelaSecao_377(secao).setVisible(true);//chamado pela seção 24,77 - 2 Inimigo e fuga
            case 378 -> new TelaSecao_378(secao).setVisible(true);//chamado pela seção 221
            case 379 -> new TelaSecao_379(secao).setVisible(true);//Chamado por 391
            case 380 -> new TelaSecao_380(secao).setVisible(true);//chamado pela seção 120,322
            case 381 -> new TelaSecao_381(secao).setVisible(true);//chamado pela seção 115
            case 382 -> new TelaSecao_382(secao).setVisible(true);//chamado pela seção 204,234
            case 383 -> new TelaSecao_383(secao).setVisible(true);//chamado pela seção 29 - Ganha moedas e apito de madeira e energia
            case 384 -> new TelaSecao_384(secao).setVisible(true);//chamado pela seção 105
            case 385 -> new TelaSecao_385(secao).setVisible(true);//chamado pela seção 290
            case 386 -> new TelaSecao_386(secao).setVisible(true);//chamado pela seção 79
            case 387 -> new TelaSecao_387(secao).setVisible(true);//chamado pela seção 143
            case 388 -> new TelaSecao_388(secao).setVisible(true);//chamado 285
            case 389 -> new TelaSecao_389(secao).setVisible(true);//chamado pela seção 201,248,293 - ganha moeda e sorte
            case 390 -> new TelaSecao_390(secao).setVisible(true);//chamado pela seção 33,166,184,369
            case 391 -> new TelaSecao_391(secao).setVisible(true);//chamado pela seção 306 - verifica se possui chave de prata
            case 392 -> new TelaSecao_392(secao).setVisible(true);//chamado pela seção 8
            case 393 -> new TelaSecao_393(secao).setVisible(true);//chamado pela seção 245, 281,397
            case 394 -> new TelaSecao_394(secao).setVisible(true);//chamado pela seção 105,272,384
            case 395 -> new TelaSecao_395(secao).setVisible(true);//chamado pela seção 356 - Testar sorte
            case 396 -> new TelaSecao_396(secao).setVisible(true); //Chamado por 361
            case 397 -> new TelaSecao_397(secao).setVisible(true);//chamado pela seção 51
            case 398 -> new TelaSecao_398(secao).setVisible(true);//chamado pela seção 61,296
            case 399 -> new TelaSecao_399(secao).setVisible(true);//chamado pela seção 54 - Termina o jogo
            case 400 -> new TelaSecao_400(secao).setVisible(true);//chamado pela seção 204 - VENCEDOR!
        }
    }
}
