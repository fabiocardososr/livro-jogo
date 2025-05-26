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
    private int milisegundos = 4000; //Tempo do delay mostrando os passos do turno de batalha

    ///Apenas para quando quiser usar a função acaoAoClicarNoBotaoFuga()
    public AcoesBatalha() {
        this.inimigo = null;
        this.telaBatalha = null;
        this.telaSecao = null;
    }

    public AcoesBatalha(Inimigo inimigo, TelaBatalha telaBatalha, TelaSecoesBasica telaSecao) {
        this.inimigo = inimigo;
        this.telaBatalha = telaBatalha;
        this.telaSecao = telaSecao;
    }

    //Aumenta ou diminue a velocidade
    public boolean velocidade2x() {

        if (milisegundos == 4000) {
            this.milisegundos = 1000; //Aumenta a velocidade
            return true;
        }
        else { //Volta para a velocidade normal
            this.milisegundos = 4000;
            return false;
        }
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
        return UtilPersonagem.personagemPerdeEnergia( quantidadeEnergiaPerdida );
    }

    //Quando clica no botão da fuga na TELA DE BATALHA
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
                    resultadoSorte = UtilPersonagem.testarSorte();
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
                new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
                CarregarTelas.telaMensagem("Você foi ferido gravemente, sua energia chegou a zero.\n\n"+
                        "Sua aventura acaba aqui.");
                telaPai.dispose();
            }

            if (telaBatalha != null)
                telaBatalha.dispose();
        }
    }

    //Quando utilizado por outra tela que não a tela de batalha (exemplo, seção 29,43,84)
    public boolean clicarNaOpcaoFuga(TelaSecoesBasica telaPai){
        boolean vaiTestarSorte = false; //Se na fuga foi feito o teste de sorte. O default é não fazer
        boolean resultadoSorte = false; //Resultado do teste da sorte, caso feito.

        CarregarTelas.telaMensagem("Deseja abandonar a luta?"+
                "\n\n"+ DadosLivroCarregado.getPersonagem().getNome() +", você perderá 2 pontos de energia."+
                "\nEsse é o preço de sua covardia.", telaPai);

        if ( telaPai.isRespostaTelaMensagem() ) {

            //Se tiver índice de sorte para usar
            if ( DadosLivroCarregado.getPersonagem().getSorteAtual() > 0) {

                //Perguntar se o jogador quer Testar a Sorte para diminuir o dano
                CarregarTelas.telaMensagem("Deseja tentar a sorte para diminuir o " +
                        "dano na fuga?\n\nObs.: Todo teste de sorte decrementa em 1 seu " +
                        "índice de Sorte independentemente de sucesso ou não.", telaPai);

                if ( telaPai.isRespostaTelaMensagem() ) {
                    vaiTestarSorte = true;
                    resultadoSorte = UtilPersonagem.testarSorte();
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
                util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
                CarregarTelas.telaMensagem("Você foi ferido gravemente, sua energia chegou a zero.\n\n"+
                        "Sua aventura acaba aqui.");
                telaPai.dispose();
                return false;
            }
        }
        else //Se na tela de confirmação desistiu de fugir
            return false;

        return true;
    }

    //Rodada de luta entre o personagem e o inimigo
    public ResultadoBatalha turnoDeBatalha() {
        boolean inimigoVivo     = true;
        boolean personagemVivo  = true;
        int desvantagem = 0; //Algumas seções desvantagem no ataque (seção 49,71).

        //Info do que está acontecendo. Aparecerá na tela para o jogador
        ResultadoBatalha resultadoTurnoBatalha;

        /*Em caso de teste de sorte quando o personagem está morto com 2 de dano,
         mas pode recuperar um para se salvar aparecerá a tela perguntando se quer usar a sorte não precisando
        /clicar no botão e esta variável vai impedir de disponibilizar novamente o teste*/
        boolean sorteJaUsada    = false;


        telaBatalha.getLabelMostradorResultDadosInimigo().setText("0");
        telaBatalha.getLabelMostradorResultDadosPersonagem().setText("0");
        mensagemComDelay(milisegundos,"<html><center>Iniciando turno <br>"+telaBatalha.getQuantidadeRodadas()
                +"</center></html>");

        ///Calculando ataque do inimigo: Ataque é o resultado de 2 dados somado a sua habilidade
        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/suspense.mp3", null);
        animacaoRolagemDados("Calculando ataque do inimigo...");
        var resultadoDadosInimigo = Util.rolarDados(6,2);
        var forcaDeAtaqueInimigo  = resultadoDadosInimigo + inimigo.getHabilidade();


        ///Mostrar resultado no painel (escudo) do lado direito (inimigo)
        telaBatalha.getLabelMostradorResultDadosInimigo().setText(Integer.toString(forcaDeAtaqueInimigo));
        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/calculo_ataque.mp3", null);
        mensagemComDelay(milisegundos,"<html><center>Ataque do inimigo calculado!</center></html>");


        ///Calculando ataque do personagem: Ataque é o resultado de 2 dados somado a sua habilidade
        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/suspense.mp3", null);
        animacaoRolagemDados("Calculando seu ataque...");

        //Verifica se existe alguma desvantagem no ataque do personagem em alguma seção
        switch ( telaSecao.getSecao().getCodSecaoLivro() ){
            case 49  -> desvantagem = desvantagem3pontosNoAtque();  //Na seção 49 o personagem tem desvantagem na luta de -3 pontos de ataque
            case 71  -> desvantagem = desvantagem3pontosNoAtque();  //Na seção 71 o personagem tem desvantagem na luta de -3 pontos de ataque
            case 165 -> desvantagem = desvantagem3pontosNoAtque();  //Na seção 165 o personagem tem desvantagem na luta de -3 pontos de ataque
            case 193 -> desvantagem = desvantagem3pontosNoAtque();  //Na seção 193 o personagem tem desvantagem na luta de -3 pontos de ataque
        }
        var forcaDeAtaquePersonagem  = UtilPersonagem.calcularForcaDeAtaqueDoPersonagem(desvantagem);


        ///Mostrar resultado no painel (escudo) do lado esquerdo (personagem)
        telaBatalha.getLabelMostradorResultDadosPersonagem().setText(Integer.toString(forcaDeAtaquePersonagem));
        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/calculo_ataque.mp3", null);
        mensagemComDelay(milisegundos,"<html><center>Seu ataque foi calculado!</center></html>");



        /**************** COMPARANDO ATAQUES ****************/

        ///PERSONAGEM GANHA TURNO DE BATALHA
        util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/resultado_turno_batalha.mp3", null);
        if (forcaDeAtaquePersonagem > forcaDeAtaqueInimigo){
            resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_GANHOU_TURNO;

            inimigoVivo = Util.inimigoPerdeEnergia(2,inimigo);
            mensagemComDelay(milisegundos,"<html><center>"+ personagem.getNome()+
                    "<br>acerta o inimigo</center></html>");

            if ( !inimigoVivo ){
                resultadoTurnoBatalha = ResultadoBatalha.INIMIGO_MORTO;
                util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/vitoria.mp3", null);
                mensagemComDelay(milisegundos,"<html><center>Inimigo derrotado!</center></html>");
                removerComponentesPanelETrocarImagem( telaSecao, telaBatalha.getPanelBotao() );

            }
        }


        /// INIMIGO GANHA TURNO DE BATALHA
        else if (forcaDeAtaqueInimigo > forcaDeAtaquePersonagem) {
            resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_PERDEU_TURNO;

            util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/dor.mp3", null);
            mensagemComDelay(milisegundos,"<html><center>Inimigo o atinge</center></html>");

            //Regra é perder 2 pontos de energia por turno de batalha não vencido.
            personagemVivo = UtilPersonagem.personagemPerdeEnergia(2);

            /*Se após dedução da energia do personagem o índice chegar a zero(NÃO abaixo de zero), existe
              a possibilidade de testar sorte e recupera 1 ponto e com isso evitar a morte do personagem ou
              perde +1 de dano em caso de azar*/
            if ( (!personagemVivo) && (personagem.getEnergiaAtual() == 0) && (personagem.getSorteAtual() > 0) ){

                //Perguntar se o jogador quer Testar a Sorte para diminuir o dano
                CarregarTelas.telaMensagem("Deseja tentar a sorte para diminuir o " +
                        "dano?\n\nO dano causado será fatal caso não teste a sorte e tenha sucesso.", telaBatalha);

                if (TelaBatalha.isRespostaTelaConfirmacao()) {
                    boolean resultadoSorte = UtilPersonagem.testarSorte();
                    sorteJaUsada = true;

                    TelaBasica.mostrarDadosRolando(milisegundos, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);

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
                        personagemVivo = UtilPersonagem.personagemPerdeEnergia(1);
                    }
                }
            }

            //Personagem morto
            if ( !personagemVivo )
             {
                 util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/perdeu.mp3", null);
                resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_MORTO;
                telaBatalha.atualizarIndicesPersonagemInimigo();
                mensagemComDelay(milisegundos,"<html><center>"+personagem.getNome()+",<br>sua energia<br>chegou a 0.</center></html>");
            }
        }
        ///EMPATE
        else {
            util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/empate.mp3", null);
            mensagemComDelay(milisegundos,"<html><center>Empate!<br>Ambos desviam do ataque</center></html>");
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

    //Seção 49,71,165,193 a força de ataque é reduzida em 3 pontos
    private int desvantagem3pontosNoAtque(){
        mensagemComDelay(milisegundos,"<html><center>-3 pontos na sua Força de  Ataque</center></html>");
        return 3;
    }

    //Pode ser usado por outras telas que não sejam a tela de batalha
    public void animacaoRolagemDados(String mensagem){
        mensagemComDelay(2000,"<html><center>"+mensagem+"</center></html>");
        TelaBasica.mostrarDadosRolando(milisegundos,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
    }

    public ResultadoBatalha testarSorte(ResultadoBatalha resultadoBatalha){
        ResultadoBatalha resultadoTesteSorte = null;


        /**** QUANDO O PERSONAGEM GANHAR O TURNO DE BATALHA ****/

        //Regra: sucesso -> Causa 2 a mais de dano; Fracasso -> Devolve 1 de energia para a criatura
        if ( resultadoBatalha == ResultadoBatalha.PERSONAGEM_GANHOU_TURNO ){
            //util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/rolar_dados.mp3", null);
            mensagemComDelay(2000, "<html><center>Teste de sorte...<center><html>");
            TelaBasica.mostrarDadosRolando(milisegundos, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
            var teveSorte = UtilPersonagem.testarSorte();
            mensagemComDelay(milisegundos, "<html><center>Teste realizado!<center><html>");

            if ( teveSorte ){
                util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
                mensagemComDelay(milisegundos, "<html><center>SORTE!<br>O inimigo sofre<br>+2 de dano<center><html>");
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
                mensagemComDelay(milisegundos, "<html><center>AZAR!<br>inimigo recupera<br>1 ponto<center><html>");
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
            TelaBasica.mostrarDadosRolando(milisegundos, ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
            var teveSorte = UtilPersonagem.testarSorte();
            mensagemComDelay(milisegundos, "<html><center>Teste realizado!<center><html>");

            if ( teveSorte ){
                util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
                mensagemComDelay(milisegundos, "<html><center>SORTE!<br>Recupera 1 ponto<center><html>");
                personagem.setEnergiaAtual(personagem.getEnergiaAtual() + 1);
                telaBatalha.atualizarIndicesPersonagemInimigo();
            }
            else{
                util.reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/azar.mp3", null);
                mensagemComDelay(milisegundos, "<html><center>AZAR!<br>+1 ponto de dano<center><html>");
                var personagemVivo = UtilPersonagem.personagemPerdeEnergia(1);
                telaBatalha.atualizarIndicesPersonagemInimigo();

                if ( !personagemVivo )
                {
                    telaBatalha.atualizarIndicesPersonagemInimigo();
                    mensagemComDelay(milisegundos,"<html><center>"+personagem.getNome()+",<br>sua energia<br>chegou a 0.</center></html>");
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

    /// REGRA DO ESCUDO: Se o resultado for 4, 5 ou 6, os danos causados a você serão reduzidos em 1 ponto
    public boolean testarDefesaEscudoDeFerroItem28() {
        TelaBasica.mostrarDadosRolando(4000,ImagensDoLivroFlorestaDaDestruicao.GIF_ROLANDO_DADOS);
        return new EfeitoDeItens(null).acoesDosItens(28);
    }
}
