package livro.jogo.telas.desktop;

import com.fasterxml.jackson.databind.ObjectMapper;
import livro.jogo.entidades.Item;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.ImagePanel;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.utils.AcoesDasSecaoEEfeitoDeItens;
import livro.jogo.utils.ManipularDadosLivro;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public class TelaBolsa extends JDialog {
    private HashMap<JLabelOpcoesTelaSecao, Item> mapItens = new HashMap<JLabelOpcoesTelaSecao, Item>();

    public TelaBolsa(int largura, int altura) {
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setSize(largura,altura);
        setLayout(null);
        setModal(true);
        setLocationRelativeTo(null);
        carregarTela();
    }

    public void carregarTela(){

        TelaBolsaListener acao = new TelaBolsaListener();


        //fundo
        ImagePanel painelImgFundoBolsa = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FUNDO_BOLSA);
        painelImgFundoBolsa.setBackground(new Color(210,180,140));
        painelImgFundoBolsa.setForeground(new Color(139,0,0));
        painelImgFundoBolsa.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        painelImgFundoBolsa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //painelImgBolsa.setBorder(BorderFactory.createLineBorder(Color.RED));
        //painelImgBolsa.setToolTipText("Carrega todos os itens que não estejam equipados");


        ImagePanel painelListaItens = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FUNDO_BOLSA_LISTA);
        painelListaItens.setBackground(new Color(210,180,140));
        painelListaItens.setForeground(new Color(139,0,0));
        painelListaItens.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        painelListaItens.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painelListaItens.setLayout(null);


        //Simulo a bolsa acrescentando itens
        //DEPOIS APAGUE
        ObjectMapper objMapper = new ObjectMapper();
        ArrayList<Item> bolsa = new ArrayList<>();
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_49.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_45.json"));

        var x = 100;
        var y = 10;
        var largura = 40;
        var altura = 100;
        for (Item item: bolsa) {
            JLabelOpcoesTelaSecao imgInterrogacao = new JLabelOpcoesTelaSecao(null, 40, 40,
                    item.getEnderecoImagem());
            imgInterrogacao.setBounds(x,y,largura,altura);
            mapItens.put(imgInterrogacao, item);
            imgInterrogacao.addMouseListener(acao);
            painelListaItens.add(imgInterrogacao);

            //Caminhando da esquerda para a direita
            x = x + 80;

            //aqui vai ter que contar 6 itens e após isso incrementa "y" de modo a ir para a próxima
            //linha
        }

        painelListaItens.setBounds(185,115,625, 570);
        painelImgFundoBolsa.setBounds(0,0,1000,800);
        painelListaItens.setBorder(BorderFactory.createLineBorder(Color.RED));
        painelImgFundoBolsa.add(painelListaItens);

        add(painelListaItens);
        add(painelImgFundoBolsa);


    }

    private class TelaBolsaListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabelOpcoesTelaSecao imgLabel = (JLabelOpcoesTelaSecao) e.getSource();
            Item item = mapItens.get(imgLabel);

            //Executa o efeito do item quando clicado na imagem do item que consta na bolsa
            AcoesDasSecaoEEfeitoDeItens.acoesDosItens(item);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
