package livro.jogo.telas.desktop.personalizados;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;

import javax.swing.*;
import java.awt.*;

public class BotaoFaixa{
    private final String texto;
    private final ImagensDoLivroFlorestaDaDestruicao enderecoImagem;

    public BotaoFaixa(String texto, ImagensDoLivroFlorestaDaDestruicao enderecoImagem) {
        this.texto = texto;
        this.enderecoImagem = enderecoImagem;
    }

    public  JLabelOpcoesTelaSecao criarBotao(Container container, int imgPanelX, int imgPanelY, int imgPanelWidth, int imgPanelHeight,
                           int labelBotaoX, int labelBotaoY, int labelBotaoWidth, int labelBotaoHeight){
        ImagePanel imgPainel = new ImagePanel(enderecoImagem.getEnderecoImagem());
        JLabelOpcoesTelaSecao label = new JLabelOpcoesTelaSecao(this.texto);
        label.setForeground(new Color(139,0,0));
        label.setFont(new Font(Font.SERIF,Font.BOLD,30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        //label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //imgPainel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //imgPainel.setBorder(BorderFactory.createLineBorder(Color.RED));
        //label.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        imgPainel.setBounds(imgPanelX, imgPanelY, imgPanelWidth, imgPanelHeight);
        label.setBounds(labelBotaoX, labelBotaoY,labelBotaoWidth, labelBotaoHeight);
        container.add(label);
        container.add(imgPainel);

        return label;
    }




}
