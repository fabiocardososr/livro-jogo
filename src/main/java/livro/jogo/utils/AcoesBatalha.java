package livro.jogo.utils;

import livro.jogo.entidades.Inimigo;
import livro.jogo.entidades.Personagem;

import javax.swing.*;

public class AcoesBatalha {
    private final Personagem personagem = DadosLivroCarregado.getPersonagem();
    private final Inimigo inimigo;

    public AcoesBatalha(Inimigo inimigo) {
        this.inimigo = inimigo;
    }

    //Retornando TRUE significa que mesmo perdendo energia ainda está VIVO
    //Na fuga se perde 2 de energia, mas caso tente a sorte e seja bem sucedido, vc perde apenas 1.
    public boolean fuga(boolean usarSorte){
        int quantidadeEnergiaPerdida = 2;

        if ( usarSorte ){
            //aqui se faz o teste da sorte caso seja
            JOptionPane.showMessageDialog(null,"VAI USAR A SORTE! COLOQUE A FUNÇÃO AQUI");
            quantidadeEnergiaPerdida = quantidadeEnergiaPerdida - 1;
        }


        System.out.println("\n\nSorte: "+usarSorte+" quantid: "+quantidadeEnergiaPerdida);

        return Util.perdeEnergia( quantidadeEnergiaPerdida );
    }
}
