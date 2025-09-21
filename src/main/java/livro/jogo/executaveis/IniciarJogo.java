package livro.jogo.executaveis;

import livro.jogo.criarLivro.cadastro.CarregarLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.enums.TelasDisponiveisParaCarregamento;

import javax.swing.*;
import java.awt.*;

public class  IniciarJogo {

    public static void main(String[] args) {

        //Carrega o livro
        CarregarLivroFlorestaDaDestruicao livroFlorestaDaDestruicao = new CarregarLivroFlorestaDaDestruicao();
        livroFlorestaDaDestruicao.carregarLivroFlorestaDestruicao();

        //Config cor de toda mensagem via tooltrip, ou seja, imformações quando se colocar o cursor em cima
        UIManager.put("ToolTip.background", new Color(244,164,96));
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Arial", Font.ITALIC, 12));


        //Carregar Tela Principal
        new CarregarTelas().carregarTela(TelasDisponiveisParaCarregamento.TELA_PRINCIPAL,"","","");
    }
}

