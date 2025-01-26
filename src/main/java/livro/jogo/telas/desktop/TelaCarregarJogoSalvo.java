package livro.jogo.telas.desktop;

import livro.jogo.Personagens.CriacaoPersonagem;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarJogoSalvo;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.utils.Util;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;

public class TelaCarregarJogoSalvo extends JDialog {

    public TelaCarregarJogoSalvo(int largura, int altura) {
        setSize(largura,altura);
        setLayout(null);
//        setLocationRelativeTo(null);
//        setResizable(false);
//        setModal(true);
//        setLayout(null);
        //setUndecorated(true);
        //setBackground(new Color(0,0,0,0));

        carregaListaDeArquivos();
    }

    private void carregaListaDeArquivos() {
        ArrayList<String> arrayList = Util.listarJogosSalvos();
        String[] listaNomesArquivos = arrayList.toArray(new String[0]);

        JList<String> jListNomesArqs = new JList<String>(listaNomesArquivos);
        jListNomesArqs.setVisibleRowCount(10);
        JScrollPane scroll = new JScrollPane(jListNomesArqs);
        scroll.setBounds(10,10,150,200);
        add(scroll);

        jListNomesArqs.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                CarregarTelas.telaMensagem("Jogo carregado!");
                setVisible(false);
                new CarregarJogoSalvo(jListNomesArqs.getSelectedValue());
                dispose();
                //JOptionPane.showMessageDialog(null, jListNomesArqs.getSelectedValue());

            }
        });
    }
}
