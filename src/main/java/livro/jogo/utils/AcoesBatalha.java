package livro.jogo.utils;

import livro.jogo.entidades.Inimigo;
import livro.jogo.entidades.Personagem;
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
        return Util.perdeEnergia( quantidadeEnergiaPerdida );
    }
}
