package livro.jogo.utils;

import livro.jogo.entidades.Inimigo;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ResultadoBatalha;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.principal.TelaBatalha;

import javax.swing.*;

public class AcoesBatalha {
    private final Personagem personagem = DadosLivroCarregado.getPersonagem();
    private final Inimigo inimigo;
    private final TelaBatalha telaBatalha; //Necessário para interagir enviando mensagens para o painel (label)
    private final TelaSecoesBasica telaSecao;

    public AcoesBatalha(Inimigo inimigo, TelaBatalha telaBatalha, TelaSecoesBasica telaSecao) {
        this.inimigo = inimigo;
        this.telaBatalha = telaBatalha;
        this.telaSecao = telaSecao;
    }

    //Retornando TRUE significa que mesmo perdendo energia ainda está VIVO
    //Na fuga se perde 2 de energia
    //testouSorte indica se jogador escolheu testar sorte.
    //Se testou a sorte e resultado TRUE diminui em 1 o dano. FALSE eleve em mais 1.
    public boolean fuga(boolean testouSorte, boolean resultadoSorte){
        int quantidadeEnergiaPerdida = 2;

        if ( testouSorte ) {
            if (resultadoSorte) {
                quantidadeEnergiaPerdida = quantidadeEnergiaPerdida - 1;
            }
            else{
                quantidadeEnergiaPerdida = quantidadeEnergiaPerdida + 1;
            }
        }

        //A função retorna se personagem ainda está vivo
        return Util.personagemPerdeEnergia( quantidadeEnergiaPerdida );
    }

    //Quando clica no botão da fuga
    public void acaoAoClicarNoBotaoFuga(TelaSecoesBasica telaPai){
        boolean vaiTestarSorte = false; //Se na fuga foi feito o teste de sorte. O default é não fazer
        boolean resultadoSorte = false; //Resultado do teste da sorte, caso feito.

        CarregarTelas.telaMensagem("Deseja abandonar a luta?"+
                "\n\n"+personagem.getNome() +", você perderá 2 pontos de energia."+
                "\nEsse é o preço de sua covardia.", telaBatalha);

        if ( TelaBatalha.isRespostaTelaConfirmacao() ) {

            //Se tiver índice de sorte para usar
            if (personagem.getSorteAtual() > 0) {

                //Perguntar se o jogador quer Testar a Sorte para diminuir o dano
                CarregarTelas.telaMensagem("Deseja tentar a sorte para diminuir o " +
                        "dano na fuga?\n\nObs.: Todo teste de sorte decrementa em 1 seu " +
                        "índice de Sorte independentemente de sucesso ou não.", telaBatalha);

                if (TelaBatalha.isRespostaTelaConfirmacao()) {
                    vaiTestarSorte = true;
                    resultadoSorte = Util.testarSorte();
                    TelaBasica.mostrarDadosRolando(4000, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS,
                            300, 300);

                    if (resultadoSorte)
                        CarregarTelas.telaMensagem("Sucesso no teste de sorte. Seu dano será reduzido em 1 ponto.");
                    else
                        CarregarTelas.telaMensagem("Fracasso no teste de sorte. Você é azarado! \n\nNo momento da fuga você escorregou e caiu de mau jeito.\nSeu dano será aumentado em mais 1 ponto.");
                }
            }

            //Passando resultadoSorte TRUE diminui 1 ponto de dano
            var estaVivo = fuga(vaiTestarSorte, resultadoSorte);

            //Após o decremento da energia se estiver vivo, atualiza os índices da tela de seção
            if ( estaVivo ) {
                if (telaPai != null){
                    telaPai.atualizaIndicesNaTelaDoPersonagem();
                    telaPai.setVisible(true);
                    telaPai.repaint();
                }
            }
            else{
                CarregarTelas.telaMensagem("Você foi ferido gravemente, sua energia chegou a zero.\n\n"+
                        "Sua aventura acaba aqui.");
                telaPai.dispose();
            }
            telaBatalha.dispose();
        }
    }

    //Rodada de luta entre o personagem e o inimigo
    public ResultadoBatalha turnoDeBatalha() {
        boolean inimigoVivo     = true;
        boolean personagemVivo  = true;
        telaBatalha.getLabelMostradorResultDadosInimigo().setText("0");
        telaBatalha.getLabelMostradorResultDadosPersonagem().setText("0");
        mensagemComDelay(5000,"<html><center>Iniciando turno <br>"+telaBatalha.getQuantidadeRodadas()
                +"</center></html>");

        //Info do que está acontecendo. Aparecerá na tela para o jogador
        //JLabel painelInfo = telaBatalha.getLabelPainelMensagens();
        ResultadoBatalha resultadoTurnoBatalha;

        //Calculando ataque do inimigo: Ataque é o resultado de 2 dados somado a sua habilidade
        mensagemComDelay(4000,"<html><center>Calculando ataque do inimigo...</center></html>");
        var resultadoDadosInimigo = Util.rolarDados(6,2);
        var forcaDeAtaqueInimigo  = resultadoDadosInimigo + inimigo.getHabilidade();

        //Mostrar resultado no painel (escudo) do lado direito (inimigo)
        telaBatalha.getLabelMostradorResultDadosInimigo().setText(Integer.toString(forcaDeAtaqueInimigo));
        mensagemComDelay(4000,"<html><center>Ataque do inimigo calculado!</center></html>");


        //Calculando ataque do personagem: Ataque é o resultado de 2 dados somado a sua habilidade
        mensagemComDelay(4000,"<html><center>Calculando seu ataque...</center></html>");
        var resultadoDadosPersonagem = Util.rolarDados(6,2);
        var forcaDeAtaquePersonagem  = resultadoDadosPersonagem + personagem.getHabilidadeAtual();

        telaBatalha.getLabelMostradorResultDadosPersonagem().setText(Integer.toString(forcaDeAtaquePersonagem));
        mensagemComDelay(4000,"<html><center>Seu ataque foi calculado!</center></html>");

        //Comparando forças de ataque
        if (forcaDeAtaquePersonagem > forcaDeAtaqueInimigo){
            inimigoVivo = Util.inimigoPerdeEnergia(2,inimigo);
            mensagemComDelay(4000,"<html><center>"+ personagem.getNome()+"<br>acerta o inimigo</center></html>");
            if (inimigoVivo)
                resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_GANHOU_TURNO;
            else {
                resultadoTurnoBatalha = ResultadoBatalha.INIMIGO_MORTO;
                mensagemComDelay(4000,"<html><center>Inimigo derrotado!</center></html>");
                removerComponentesPanelETrocarImagem( telaSecao, telaBatalha.getPanelBotao() );

            }

        } else if (forcaDeAtaqueInimigo > forcaDeAtaquePersonagem) {
            mensagemComDelay(4000,"<html><center>Inimigo o atinge</center></html>");
            personagemVivo = Util.personagemPerdeEnergia(2);

            if ( personagemVivo )
                resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_PERDEU_TURNO;
            else {
                resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_MORTO;
                mensagemComDelay(4000,"<html><center>"+personagem.getNome()+",<br>sua energia<br>chegou a 0.</center></html>");
            }

        } else {
            mensagemComDelay(4000,"<html><center>Empate!<br>Ambos desviam do ataque</center></html>");
            //No empate ambos escapam do ataque um do outro
            resultadoTurnoBatalha = ResultadoBatalha.EMPATE_TURNO;
        }

        //Atualiza os índices na tela de batalha
        telaBatalha.atualizarIndicesPersonagemInimigo();

        //liberar uso da sorte
        if (  (inimigoVivo) && (personagemVivo) )
            telaBatalha.podeUsarASorte();

        return resultadoTurnoBatalha;
    }

    public ResultadoBatalha testarSorte(ResultadoBatalha resultadoBatalha){
        ResultadoBatalha resultadoTesteSorte = null;

        //TESTE: DEPOIS APAGUE
//        resultadoBatalha = ResultadoBatalha.PERSONAGEM_GANHOU_TURNO;
//        System.out.println("ANTES: "+inimigo);


        /**** QUANDO O PERSONAGEM GANHAR O TURNO DE BATALHA ****/

        //Regra: sucesso -> Causa 2 a mais de dano; Fracasso -> Devolve 1 de energia para a criatura
        if ( resultadoBatalha == ResultadoBatalha.PERSONAGEM_GANHOU_TURNO ){
            mensagemComDelay(4000, "<html><center>Teste de sorte...<center><html>");
            var teveSorte = Util.testarSorte();
            mensagemComDelay(4000, "<html><center>Teste realizado!<center><html>");

            if ( teveSorte ){
                mensagemComDelay(4000, "<html><center>SORTE!<br>O inimigo sofre<br>+2 de dano<center><html>");
                var inimigoVivo = Util.inimigoPerdeEnergia(2,inimigo);
                if ( inimigoVivo )
                    resultadoTesteSorte = ResultadoBatalha.INIMIGO_VIVO;
                else {
                    removerComponentesPanelETrocarImagem( telaSecao, telaBatalha.getPanelBotao() );
                    resultadoTesteSorte = ResultadoBatalha.INIMIGO_MORTO;
                }
            }
            else{
                //Se falhar no teste de sorte, devolve 1 ponto de energia para a criatura
                mensagemComDelay(4000, "<html><center>AZAR!<br>inimigo recupera<br>1 ponto<center><html>");
                inimigo.setEnergia(inimigo.getEnergia() + 1);
            }
        }


        /**** QUANDO O PERSONAGEM PERDER O TURNO DE BATALHA ****/

        /* REGRA: Se a criatura acabou de ferir você
            - SORTE -> reponha 1 ponto de ENERGIA.
            - AZAR  -> Subtraia 1 ponto a mais de ENERGIA.*/
        if ( resultadoBatalha == ResultadoBatalha.PERSONAGEM_PERDEU_TURNO ){
            mensagemComDelay(4000, "<html><center>Teste de sorte...<center><html>");
            var teveSorte = Util.testarSorte();
            mensagemComDelay(4000, "<html><center>Teste realizado!<center><html>");

            if ( teveSorte ){
                mensagemComDelay(4000, "<html><center><center><html>");

                ///CONTINUAR DAQUI. FAZER A LÓGICA QUANDO O PERSONAGEM PERDER O TURNO DE BATALHA

            }
            else{

            }
        }


        //Atualiza os índices na tela de batalha
        telaBatalha.atualizarIndicesPersonagemInimigo();
        return resultadoTesteSorte;
    }

    private void removerComponentesPanelETrocarImagem(TelaSecoesBasica telaSecao, JPanel panel) {

        JLabelOpcoesTelaSecao btInimigo = (JLabelOpcoesTelaSecao) panel.getComponent(6);
        btInimigo.setIcon(Util.dimensionarImagem(btInimigo.getWidth(), btInimigo.getHeight(),
                ImagensDoLivroFlorestaDaDestruicao.CAVEIRA.getEnderecoImagem()));
        btInimigo.setCursor(null);
        btInimigo.setToolTipText("Inimigo morto");

        panel.removeAll();
        panel.add(btInimigo);
        telaSecao.repaint();
    }

    private void mensagemComDelay(int milisegundos, String msg) {
        telaBatalha.getLabelPainelMensagens().setText(msg);
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
