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
        painelListaItens.setCursor(null);
        painelListaItens.setLayout(null);


        //Simulo a bolsa acrescentando itens
        //DEPOIS APAGUE
        ObjectMapper objMapper = new ObjectMapper();
        ArrayList<Item> bolsa = new ArrayList<>();
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_49.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_46.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_47.json"));
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
                "livros/florestadadestruicao/itens/item_49.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_46.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_47.json"));
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
                "livros/florestadadestruicao/itens/item_49.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_46.json"));
        bolsa.add(ManipularDadosLivro.recuperaItemDoJsonEGuardaNaBolsa(objMapper,
                "livros/florestadadestruicao/itens/item_47.json"));
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



        var x = 90; //Posição da esquerda para a direita
        var y = 30;  //Posição de cima para baixo
        var largura = 50;
        var altura = 50;
        var contNumerodeItensPorLinha = 0; //Vai auxiliar nos itens por linha, no caso estipulei 5 itens por linha
        for (Item item: bolsa) {

            if (contNumerodeItensPorLinha == 5){
                contNumerodeItensPorLinha = 0;
                y = y + 60; //Posiciona os próximos itens logo abaixo
                x = 90; //Volta para o início da (esquerda para a direita)
            }

            //Criando a imagem com os
            JLabelOpcoesTelaSecao imgInterrogacao = new JLabelOpcoesTelaSecao("", largura, altura,
                    item.getEnderecoImagem());
            imgInterrogacao.setBounds(x,y,largura,altura);
            imgInterrogacao.setCursor(new Cursor(Cursor.HAND_CURSOR));
            imgInterrogacao.setToolTipText(item.getNome().toUpperCase()+" - " + item.getDescricao());
            imgInterrogacao.addMouseListener(acao);
            mapItens.put(imgInterrogacao, item);
            painelListaItens.add(imgInterrogacao);
            ++contNumerodeItensPorLinha;
            //imgInterrogacao.setBorder(BorderFactory.createLineBorder(Color.RED));


            //Caminhando da esquerda para a direita
            x = x + 100;

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
            AcoesDasSecaoEEfeitoDeItens acaoItem = new AcoesDasSecaoEEfeitoDeItens();
            acaoItem.acoesDosItens(item);
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
