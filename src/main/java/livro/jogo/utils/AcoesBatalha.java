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
    private final Util util = new Util();

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
                    TelaBasica.mostrarDadosRolando(4000, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);


                    if (resultadoSorte) {
                        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
                        CarregarTelas.telaMensagem("Sucesso no teste de sorte. Seu dano será reduzido em 1 ponto.");
                    }
                    else {
                        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
                        CarregarTelas.telaMensagem("Fracasso no teste de sorte. Você é azarado! \n\nNo momento da fuga você escorregou e caiu de mau jeito.\nSeu dano será aumentado em mais 1 ponto.");
                    }
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

        //Info do que está acontecendo. Aparecerá na tela para o jogador
        //JLabel painelInfo = telaBatalha.getLabelPainelMensagens();
        ResultadoBatalha resultadoTurnoBatalha;

        /*Em caso de teste de sorte quando o personagem está morto com 2 de dano,
         mas pode recuperar um para se salvar aparecerá a tela perguntando se quer usar a sorte não precisando
        /clicar no botão e esta variável vai impedir de disponibilizar novamente o teste*/
        boolean sorteJaUsada    = false;


        telaBatalha.getLabelMostradorResultDadosInimigo().setText("0");
        telaBatalha.getLabelMostradorResultDadosPersonagem().setText("0");
        mensagemComDelay(3000,"<html><center>Iniciando turno <br>"+telaBatalha.getQuantidadeRodadas()
                +"</center></html>");

        ///Calculando ataque do inimigo: Ataque é o resultado de 2 dados somado a sua habilidade
        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/suspense.mp3", null);
        mensagemComDelay(2000,"<html><center>Calculando ataque do inimigo...</center></html>");
        TelaBasica.mostrarDadosRolando(4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
        var resultadoDadosInimigo = Util.rolarDados(6,2);
        var forcaDeAtaqueInimigo  = resultadoDadosInimigo + inimigo.getHabilidade();


        ///Mostrar resultado no painel (escudo) do lado direito (inimigo)
        telaBatalha.getLabelMostradorResultDadosInimigo().setText(Integer.toString(forcaDeAtaqueInimigo));
        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/calculo_ataque.mp3", null);
        mensagemComDelay(4000,"<html><center>Ataque do inimigo calculado!</center></html>");


        ///Calculando ataque do personagem: Ataque é o resultado de 2 dados somado a sua habilidade
        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/suspense.mp3", null);
        mensagemComDelay(2000,"<html><center>Calculando seu ataque...</center></html>");
        TelaBasica.mostrarDadosRolando(4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
        var resultadoDadosPersonagem = Util.rolarDados(6,2);
        var forcaDeAtaquePersonagem  = UtilPersonagem.calcularForcaDeAtaqueDoPersonagem();


        ///Mostrar resultado no painel (escudo) do lado esquerdo (personagem)
        telaBatalha.getLabelMostradorResultDadosPersonagem().setText(Integer.toString(forcaDeAtaquePersonagem));
        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/calculo_ataque.mp3", null);
        mensagemComDelay(4000,"<html><center>Seu ataque foi calculado!</center></html>");



        /**************** COMPARANDO ATAQUES ****************/

        ///PERSONAGEM GANHA TURNO DE BATALHA
        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/resultado_turno_batalha.mp3", null);
        if (forcaDeAtaquePersonagem > forcaDeAtaqueInimigo){
            resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_GANHOU_TURNO;

            inimigoVivo = Util.inimigoPerdeEnergia(2,inimigo);
            mensagemComDelay(4000,"<html><center>"+ personagem.getNome()+
                    "<br>acerta o inimigo</center></html>");

            if ( !inimigoVivo ){
                resultadoTurnoBatalha = ResultadoBatalha.INIMIGO_MORTO;
                util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/vitoria.mp3", null);
                mensagemComDelay(3000,"<html><center>Inimigo derrotado!</center></html>");
                removerComponentesPanelETrocarImagem( telaSecao, telaBatalha.getPanelBotao() );

            }
        }
        /// INIMIGO GANHA TURNO DE BATALHA
        else if (forcaDeAtaqueInimigo > forcaDeAtaquePersonagem) {
            resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_PERDEU_TURNO;

            util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/dor.mp3", null);
            mensagemComDelay(4000,"<html><center>Inimigo o atinge</center></html>");
            personagemVivo = Util.personagemPerdeEnergia(2);


            /*Se após dedução da energia do personagem o índice chegar a zero(NÃO abaixo de zero), existe
              a possibilidade de testar sorte e recupera 1 ponto e com isso evitar a morte do personagem ou
              perde +1 de dano em caso de azar*/
            if ( (!personagemVivo) && (personagem.getEnergiaAtual() == 0) && (personagem.getSorteAtual() > 0) ){

                //Perguntar se o jogador quer Testar a Sorte para diminuir o dano
                CarregarTelas.telaMensagem("Deseja tentar a sorte para diminuir o " +
                        "dano?\n\nO dano causado será fatal caso não teste a sorte e tenha sucesso.", telaBatalha);

                if (TelaBatalha.isRespostaTelaConfirmacao()) {
                    boolean resultadoSorte = Util.testarSorte();
                    sorteJaUsada = true;

                    TelaBasica.mostrarDadosRolando(4000, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);

                    if (resultadoSorte) {
                        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
                        CarregarTelas.telaMensagem("Sucesso no teste de sorte. Seu dano será reduzido em 1 ponto.");
                        personagem.setEnergiaAtual(personagem.getEnergiaAtual() + 1);
                        if (personagem.getEnergiaAtual() > 0)
                            personagemVivo = true;
                    }
                    else {
                        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
                        CarregarTelas.telaMensagem("Fracasso no teste de sorte. Você é azarado! \n\nSeu dano será aumentado em mais 1 ponto.");
                        personagemVivo = Util.personagemPerdeEnergia(1);
                    }
                }
            }

            //Personagem morto
            if ( !personagemVivo )
             {
                 util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/perdeu.mp3", null);
                resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_MORTO;
                telaBatalha.atualizarIndicesPersonagemInimigo();
                mensagemComDelay(3000,"<html><center>"+personagem.getNome()+",<br>sua energia<br>chegou a 0.</center></html>");
            }
        }
        ///EMPATE
        else {
            util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/empate.mp3", null);
            mensagemComDelay(4000,"<html><center>Empate!<br>Ambos desviam do ataque</center></html>");
            //No empate ambos escapam do ataque um do outro
            resultadoTurnoBatalha = ResultadoBatalha.EMPATE_TURNO;
        }


        //Atualiza os índices na tela de batalha
        telaBatalha.atualizarIndicesPersonagemInimigo();

        //liberar uso da sorte
        if (  (inimigoVivo) && (personagemVivo) && (personagem.getSorteAtual() > 0) &&
                (resultadoTurnoBatalha != ResultadoBatalha.EMPATE_TURNO) && ( !sorteJaUsada ) ) {
            telaBatalha.podeUsarASorte();
            mensagemComDelay(1000, "<html><center>Você pode testar a sorte<center><html>");
        }

        return resultadoTurnoBatalha;
    }

    public ResultadoBatalha testarSorte(ResultadoBatalha resultadoBatalha){
        ResultadoBatalha resultadoTesteSorte = null;


        /**** QUANDO O PERSONAGEM GANHAR O TURNO DE BATALHA ****/

        //Regra: sucesso -> Causa 2 a mais de dano; Fracasso -> Devolve 1 de energia para a criatura
        if ( resultadoBatalha == ResultadoBatalha.PERSONAGEM_GANHOU_TURNO ){
            //util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/rolar_dados.mp3", null);
            mensagemComDelay(2000, "<html><center>Teste de sorte...<center><html>");
            TelaBasica.mostrarDadosRolando(4000, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
            var teveSorte = Util.testarSorte();
            mensagemComDelay(4000, "<html><center>Teste realizado!<center><html>");

            if ( teveSorte ){
                util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
                mensagemComDelay(3000, "<html><center>SORTE!<br>O inimigo sofre<br>+2 de dano<center><html>");
                var inimigoVivo = Util.inimigoPerdeEnergia(2,inimigo);

                if ( inimigoVivo )
                    resultadoTesteSorte = ResultadoBatalha.INIMIGO_VIVO;
                else {
                    util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/vitoria.mp3", null);
                    removerComponentesPanelETrocarImagem( telaSecao, telaBatalha.getPanelBotao() );
                    telaBatalha.atualizarIndicesPersonagemInimigo();
                    return ResultadoBatalha.INIMIGO_MORTO;
                }
            }
            else{
                //Se falhar no teste de sorte, devolve 1 ponto de energia para a criatura
                util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
                mensagemComDelay(4000, "<html><center>AZAR!<br>inimigo recupera<br>1 ponto<center><html>");
                inimigo.setEnergia(inimigo.getEnergia() + 1);
                telaBatalha.atualizarIndicesPersonagemInimigo();
            }
        }


        /**** QUANDO O PERSONAGEM PERDER O TURNO DE BATALHA ****/

        /* REGRA: Se a criatura acabou de ferir você
            - SORTE -> reponha 1 ponto de ENERGIA.
            - AZAR  -> Subtraia 1 ponto a mais de ENERGIA.*/
        if ( resultadoBatalha == ResultadoBatalha.PERSONAGEM_PERDEU_TURNO ){
            //util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/rolar_dados.mp3", null);
            mensagemComDelay(2000, "<html><center>Teste de sorte...<center><html>");
            TelaBasica.mostrarDadosRolando(4000, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
            var teveSorte = Util.testarSorte();
            mensagemComDelay(4000, "<html><center>Teste realizado!<center><html>");

            if ( teveSorte ){
                util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
                mensagemComDelay(4000, "<html><center>SORTE!<br>Recupera 1 ponto<center><html>");
                personagem.setEnergiaAtual(personagem.getEnergiaAtual() + 1);
                telaBatalha.atualizarIndicesPersonagemInimigo();
            }
            else{
                util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
                mensagemComDelay(4000, "<html><center>AZAR!<br>+1 ponto de dano<center><html>");
                var personagemVivo = Util.personagemPerdeEnergia(1);
                telaBatalha.atualizarIndicesPersonagemInimigo();

                if ( !personagemVivo )
                {
                    telaBatalha.atualizarIndicesPersonagemInimigo();
                    mensagemComDelay(4000,"<html><center>"+personagem.getNome()+",<br>sua energia<br>chegou a 0.</center></html>");
                    return ResultadoBatalha.PERSONAGEM_MORTO;
                }
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

        //Atualiza a tela de seção que chamou a tela de batalha atualizando a tela com informações
        panel.removeAll();
        panel.add(btInimigo);
        telaSecao.atualizaIndicesNaTelaDoPersonagem();
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
