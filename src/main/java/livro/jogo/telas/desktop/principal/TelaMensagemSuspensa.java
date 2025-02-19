package livro.jogo.telas.desktop.principal;

import javax.swing.JDialog;

public class TelaMensagemSuspensa extends JDialog {
    private String titulo;
    private String texto;

    public TelaMensagemSuspensa(String titulo, String texto) {
        super();
        this.titulo = titulo;
        this.texto = texto;
        setLayout(null);

       // setSize(80,80);

    }
}
