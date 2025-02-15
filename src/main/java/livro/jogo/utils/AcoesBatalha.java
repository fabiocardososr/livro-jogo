package livro.jogo.utils;

import livro.jogo.entidades.Inimigo;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ResultadoBatalha;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.principal.TelaBatalha;

import javax.swing.*;

public class AcoesBatalha {
    private final Personagem personagem = DadosLivroCarregado.getPersonagem();
    private final Inimigo inimigo;
    private final TelaBatalha telaBatalha; //Necessário para interagir enviando mensagens para o painel (label)

    public AcoesBatalha(Inimigo inimigo, TelaBatalha telaBatalha) {
        this.inimigo = inimigo;
        this.telaBatalha = telaBatalha;
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

        if ( telaBatalha.isRespostaTelaConfirmacao() ) {

            //Se tiver índice de sorte para usar
            if (personagem.getSorteAtual() > 0) {

                //Perguntar se o jogador quer Testar a Sorte para diminuir o dano
                CarregarTelas.telaMensagem("Deseja tentar a sorte para diminuir o " +
                        "dano na fuga?\n\nObs.: Todo teste de sorte decrementa em 1 seu " +
                        "índice de Sorte independentemente de sucesso ou não.", telaBatalha);

                if (telaBatalha.isRespostaTelaConfirmacao()) {
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
    public ResultadoBatalha turnoDeBatalha(){
        boolean inimigoVivo = true;
        boolean personagemVivo = true;

        //Info do que está acontecendo. Aparecerá na tela para o jogador
        //JLabel painelInfo = telaBatalha.getLabelPainelMensagens();
        ResultadoBatalha resultadoTurnoBatalha;

        /// Colocar delay na execução
        //Util.delayNaExecucao(4000);

        //Ataque do inimigo: Ataque é o resultado de 2 dados somado a sua habilidade
        var resultadoDadosInimigo = Util.rolarDados(6,2);
        var forcaDeAtaqueInimigo  = resultadoDadosInimigo + inimigo.getHabilidade();

        //Ataque do personagem: Ataque é o resultado de 2 dados somado a sua habilidade
        var resultadoDadosPersonagem = Util.rolarDados(6,2);
        var forcaDeAtaquePersonagem  = resultadoDadosPersonagem + personagem.getHabilidadeAtual();

        //Comparando forças de ataque
        if (forcaDeAtaquePersonagem > forcaDeAtaqueInimigo){
            inimigoVivo = Util.inimigoPerdeEnergia(2,inimigo);

            if (inimigoVivo)
                resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_GANHOU_TURNO;
            else
                resultadoTurnoBatalha = ResultadoBatalha.INIMIGO_MORTO;

        } else if (forcaDeAtaqueInimigo > forcaDeAtaquePersonagem) {
            personagemVivo = Util.personagemPerdeEnergia(2);

            if ( personagemVivo )
                resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_PERDEU_TURNO;
            else
                resultadoTurnoBatalha = ResultadoBatalha.PERSONAGEM_MORTO;

        } else {
            //No empate ambos escapam do ataque um do outro
            resultadoTurnoBatalha = ResultadoBatalha.EMPATE_TURNO;
        }

        //liberar uso da sorte
        if (  (inimigoVivo) && (personagemVivo) )
            telaBatalha.podeUsarASorte();

        //Atualiza os índices na tela de batalha
        telaBatalha.atualizarIndicesPersonagemInimigo();





        return resultadoTurnoBatalha;
    }
}
