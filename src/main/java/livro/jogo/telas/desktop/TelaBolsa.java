package livro.jogo.telas.desktop;

import livro.jogo.entidades.Item;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.executaveis.Testes;
import livro.jogo.telas.desktop.personalizados.ImagePanel;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.utils.EfeitoDeItens;
import livro.jogo.utils.ManipularDadosLivro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public class TelaBolsa extends JDialog {
    private HashMap<JLabelOpcoesTelaSecao, Item> mapItens = new HashMap<JLabelOpcoesTelaSecao, Item>();
    private JLabelOpcoesTelaSecao botaoSair;

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

        //Configurar ouvinte do click do mouse quando clicar nos itens
        TelaBolsaListener acao = new TelaBolsaListener();

        //Carregar itens da bolsa e os equipados
        ArrayList<Item> bolsa = ManipularDadosLivro.getBolsa();
        ArrayList<Item> itensEquipados = ManipularDadosLivro.getItensEquipados();

        //fundo
        ImagePanel painelImgFundoBolsa = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FUNDO_BOLSA);
        painelImgFundoBolsa.setBackground(new Color(210,180,140));
        painelImgFundoBolsa.setForeground(new Color(139,0,0));
        painelImgFundoBolsa.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        painelImgFundoBolsa.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImagePanel painelListaItens = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FUNDO_BOLSA_LISTA);
        painelListaItens.setBackground(new Color(210,180,140));
        painelListaItens.setForeground(new Color(139,0,0));
        painelListaItens.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        painelListaItens.setCursor(null);
        painelListaItens.setLayout(null);

        /**** DEPOIS APAGUE CARREGANDO ITENS NA BOLSA E EQUIPADOS PARA SIMULAR ****/
//        ArrayList<Item> bolsa = Testes.simularPreenchimentoBolsa();
//        var itensEquipados = Testes.recuperaItensIniciaisEquipados();
        /**** DEPOIS APAGUE CARREGANDO ITENS NA BOLSA E EQUIPADOS PARA SIMULAR ****/

        var x = 90; //Posição da esquerda para a direita
        var y = 30;  //Posição de cima para baixo
        var largura = 50;
        var altura = 50;
        var contNumerodeItensPorLinha = 0; //Vai auxiliar nos itens por linha, no caso estipulei 5 itens por linha

        //Para itens na bolsa (Não equipados)
        for (Item item: bolsa) {

            if (contNumerodeItensPorLinha == 5){
                contNumerodeItensPorLinha = 0;
                y = y + 60; //Posiciona os próximos itens logo abaixo
                x = 90; //Volta para o início da (esquerda para a direita)
            }

            //Inserindo os itens na tela
            JLabelOpcoesTelaSecao imgItem = new JLabelOpcoesTelaSecao("", largura, altura,
                    item.getEnderecoImagem());
            imgItem.setBounds(x,y,largura,altura);
            imgItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            imgItem.setToolTipText(item.getNome().toUpperCase()+" - " + item.getDescricao());
            imgItem.addMouseListener(acao);
            mapItens.put(imgItem, item);
            painelListaItens.add(imgItem);
            ++contNumerodeItensPorLinha;
            //imgInterrogacao.setBorder(BorderFactory.createLineBorder(Color.RED));

            //Caminhando da esquerda para a direita
            x = x + 100;
        }

        for (Item item: itensEquipados) {

            if (contNumerodeItensPorLinha == 5){
                contNumerodeItensPorLinha = 0;
                y = y + 60; //Posiciona os próximos itens logo abaixo
                x = 90; //Volta para o início da (esquerda para a direita)
            }

            //Criando a imagem
            JLabelOpcoesTelaSecao imgItem = new JLabelOpcoesTelaSecao("", largura, altura,
                    item.getEnderecoImagem());
            imgItem.setBounds(x,y,largura,altura);
            imgItem.setCursor(new Cursor(Cursor.HAND_CURSOR));

            if (item.getDescricao().isEmpty())
                imgItem.setToolTipText(item.getNome().toUpperCase());
            else
               imgItem.setToolTipText(item.getNome().toUpperCase()+" - " + item.getDescricao());

            imgItem.addMouseListener(acao);

            //Rótulo indicando que este item está equipado
            JLabel rotuloEquipado = new JLabel("Equipado");
            rotuloEquipado.setForeground(new Color(220,220,220));
            rotuloEquipado.setFont(new Font(Font.SERIF,Font.PLAIN,18));
            //rotuloEquipado.setOpaque(true); //sem chamar este método não pinta o fundo
            //rotuloEquipado.setBackground(Color.black);
            rotuloEquipado.setBounds(x+5,y+40,75,18);

            //Incluir item no hashmap para que possa ser identificado quando clicado na imagem
            mapItens.put(imgItem, item);

            //Incluir no panel
            painelListaItens.add(rotuloEquipado);
            painelListaItens.add(imgItem);

            //Incrementado contador de modo a que só fiquem 5 itens por linha
            ++contNumerodeItensPorLinha;

            //Caminhando da esquerda para a direita
            x = x + 100;
        }

        painelListaItens.setBounds(185,115,625, 570);
        painelImgFundoBolsa.setBounds(0,0,1000,800);
        painelImgFundoBolsa.add(painelListaItens);
        //painelListaItens.setBorder(BorderFactory.createLineBorder(Color.RED));


        //Botão sair
        JLabel labelSair = new JLabel("Sair");
        labelSair.setBounds(465,700,100,50);
        labelSair.setForeground(new Color(139,0,0));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoSair = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoSair.setBounds(405,685,220,90);
        botaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoSair.addMouseListener(acao);
        add(labelSair);
        add(botaoSair);

        add(painelListaItens);
        add(painelImgFundoBolsa);


    }

    private class TelaBolsaListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == botaoSair){
                setVisible(false);
                return;
            }

            JLabelOpcoesTelaSecao imgLabel = (JLabelOpcoesTelaSecao) e.getSource();
            Item item = mapItens.get(imgLabel);

            //Executa o efeito do item quando clicado na imagem do item que consta na bolsa
            EfeitoDeItens acaoItem = new EfeitoDeItens(ManipularDadosLivro.getPersonagem());
            acaoItem.acoesDosItens(item.getIdItem());
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
