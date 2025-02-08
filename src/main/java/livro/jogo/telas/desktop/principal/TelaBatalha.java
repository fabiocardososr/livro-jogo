package livro.jogo.telas.desktop.principal;

import livro.jogo.entidades.Inimigo;

import javax.swing.*;

public class TelaBatalha extends JDialog {
    private final Inimigo inimigo;

    public TelaBatalha(Inimigo inimigo) {
        this.inimigo = inimigo;
        setSize(700,500);
        setTitle("Batalha");
        setModal(true);
        //setUndecorated(true);
        setLocationRelativeTo(null);

        System.out.println(this.inimigo);
    }
}
