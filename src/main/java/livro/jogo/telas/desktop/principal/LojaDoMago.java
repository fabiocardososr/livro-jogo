package livro.jogo.telas.desktop.principal;

import livro.jogo.acaosecoes.AcoesLojaDoMago;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.PanelCircular;
import livro.jogo.telas.desktop.personalizados.util.ListItem;
import livro.jogo.telas.desktop.personalizados.util.ListaDeItensComImagem;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilItens;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class LojaDoMago extends JDialog {
    private JPanel panelListaItensAVenda;
    private JPanel panelItemAserComprado;
    private DefaultListModel<ListItem> listaItensParaEscolha;
    private HashMap<JLabelOpcoesTelaSecao, Item> mapItens = new HashMap<JLabelOpcoesTelaSecao, Item>();
    private Secao secao;
    private Container telaPrincipalLista;
    private JPanel panelInfoItem; //Representa a tela suspensa de informação do item
    private JTextPane descricaoItem; //Na tela suspensa de informação
    private JLabel tituloTelaSuspensaInfo;
    private JList<ListItem> jListItem;

    public LojaDoMago(int posicaoX, int posicaoY,
                      int largura, int altura, Secao secao) {


        this.secao = secao;

        /* CRIAÇÃO DOS COMPONENTES VISUAIS */

        //Painel geral
        Container tela = getContentPane();
        setSize(largura-80,altura+100);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setLayout(null);
        setModal(true);
        setLocationRelativeTo(null);


        /**** Painel que incorporará a lista de itens da bolsa ****/
        int larguraListaItensAVenda = largura-400;
        panelListaItensAVenda = new JPanel();
        panelListaItensAVenda.setLayout(null);
        panelListaItensAVenda.setBounds(posicaoX,posicaoY,larguraListaItensAVenda,altura+100);
        panelListaItensAVenda.setBackground(new Color(0,0,0,0));

        //Fundo painel suspenso principal (panelListaSuspensaItens)
        JLabelOpcoesTelaSecao fundoPanel = new JLabelOpcoesTelaSecao(null,
                larguraListaItensAVenda, altura,ImagensDoLivroFlorestaDaDestruicao.MOLDURA_16);
        fundoPanel.setBounds(0,50,larguraListaItensAVenda,altura);
        //fundoPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        /**** FIM: Painel que incorporará a lista de itens da bolsa ****/


        /*** Tela arredondda para visualizar item para compra ***/

        //Painel que mostrará o item a ser comprado comprados
        panelItemAserComprado = new PanelCircular();
        panelItemAserComprado.setLayout(null);
        panelItemAserComprado.setBounds(posicaoX+590,posicaoY+57,250,230);
        panelItemAserComprado.setBackground(new Color(0,0,0, 190));
        //panelListaItensEscolhidos.setBackground(Color.DARK_GRAY);


        //Fundo painel suspenso escolha
        JLabelOpcoesTelaSecao fundoPanelEscolha = new JLabelOpcoesTelaSecao(null,
                273, 243, ImagensDoLivroFlorestaDaDestruicao.MOLDURA_CIRCULAR);
        fundoPanelEscolha.setHorizontalAlignment(SwingConstants.CENTER);
        fundoPanelEscolha.setVerticalAlignment(SwingConstants.CENTER);
        fundoPanelEscolha.setBounds(0,-2,250,230);
        //fundoPanelEscolha.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        panelItemAserComprado.add(fundoPanelEscolha);
        /*** FIM: tela arredondda para visualizar item para compra ***/


        ///Botão de confirmação
        JLabelOpcoesTelaSecao botaoConfirmar = new JLabelOpcoesTelaSecao(null,
                100,60,ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoConfirmar.setBounds(posicaoX+120,posicaoY+470,100,60);
        botaoConfirmar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                confirmarEscolhaItens();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoConfirmar.setIcon(Util.dimensionarImagem(botaoConfirmar.getWidth(),
                        botaoConfirmar.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoConfirmar.setIcon(Util.dimensionarImagem(botaoConfirmar.getWidth(),
                        botaoConfirmar.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem()));
                repaint();
            }
        });

        ///label do botaoConfirmar
        JLabel lbBotaoConfirmar = new JLabel("OK");
        lbBotaoConfirmar.setFont(new Font(Font.SERIF,Font.BOLD,14));
        lbBotaoConfirmar.setForeground(new Color(139,0,0));
        lbBotaoConfirmar.setHorizontalAlignment(SwingConstants.CENTER);
        lbBotaoConfirmar.setBounds(posicaoX+145,posicaoY+486,50,20);
        lbBotaoConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));


        ///Botão sair sem fazer nada
        JLabelOpcoesTelaSecao botaoSair = new JLabelOpcoesTelaSecao(null,
                100,60,ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoSair.setBounds(posicaoX+254,posicaoY+470,100,60);
        botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSair.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limparPanelEscolhaItensASeremDescartados();
                setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoSair.setIcon(Util.dimensionarImagem(botaoSair.getWidth(),botaoSair.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoSair.setIcon(Util.dimensionarImagem(botaoSair.getWidth(),botaoSair.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem()));
                repaint();
            }
        });

        ///label do botãoSair
        JLabel lbBotaoSair = new JLabel("Sair");
        lbBotaoSair.setFont(new Font(Font.SERIF,Font.BOLD,16));
        lbBotaoSair.setForeground(new Color(139,0,0));
        lbBotaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        lbBotaoSair.setBounds(posicaoX+280,posicaoY+487,50,20);
        lbBotaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //lbBotaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão limpar (resetar)
        JLabelOpcoesTelaSecao botaoResetar = new JLabelOpcoesTelaSecao(null,
                100,60,ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoResetar.setBounds(posicaoX+388,posicaoY+470,100,60);
        botaoResetar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limparPanelEscolhaItensASeremDescartados();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoResetar.setIcon(Util.dimensionarImagem(botaoResetar.getWidth(),
                        botaoResetar.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoResetar.setIcon(Util.dimensionarImagem(botaoResetar.getWidth(),
                        botaoResetar.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem()));
                repaint();
            }
        });

        //label do resetar
        JLabel lbBotaoLimpar = new JLabel("Limpar");
        lbBotaoLimpar.setFont(new Font(Font.SERIF,Font.BOLD,14));
        lbBotaoLimpar.setForeground(new Color(139,0,0));
        lbBotaoLimpar.setHorizontalAlignment(SwingConstants.CENTER);
        lbBotaoLimpar.setBounds(posicaoX+415,posicaoY+487,50,20);
        lbBotaoLimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Adicionando componentes no panel que mostra os itens a serem escolhidos
        panelListaItensAVenda.add(lbBotaoLimpar);
        //panelListaItensAVenda.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        panelListaItensAVenda.add(botaoResetar);
        panelListaItensAVenda.add(lbBotaoConfirmar);
        panelListaItensAVenda.add(botaoConfirmar);
        panelListaItensAVenda.add(lbBotaoSair);
        panelListaItensAVenda.add(botaoSair);
        panelListaItensAVenda.add( criarListaItens(posicaoX, posicaoY, largura, altura) );
        panelListaItensAVenda.add(fundoPanel);

        tela.add(panelItemAserComprado);
        tela.add(panelListaItensAVenda);

        //Tela de info do item selecionado
        tela.add( telaInfoItem(posicaoX,posicaoY+50) );
    }

    private JScrollPane criarListaItens(int posicaoX, int posicaoY, int largura, int altura){

        //Cria um listModel para que possa iterar diretamente com o JList.
        //Atualizando ele, automaticamente o JList muda
        listaItensParaEscolha = new DefaultListModel<>();
        //Carregando os dados
        for (ListItem listItem : AcoesLojaDoMago.retornaListaDeItensAVenda())
            listaItensParaEscolha.addElement(listItem);

        //Criando o JList de itens na bolsa
        jListItem = new JList<>(listaItensParaEscolha);
        //jListItem.setFont(new Font(Font.DIALOG,Font.BOLD,30));
        jListItem.setCellRenderer(new ListaDeItensComImagem());
        jListItem.setVisibleRowCount(5);
        jListItem.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jListItem.setForeground(Color.WHITE);
        jListItem.setBackground(new Color(0,0,0,0));
        jListItem.setOpaque(false);
        JScrollPane scrollListaSuspensaDeItens = new JScrollPane(jListItem);
        //scrollListaSuspensaDeItens.setFont(new Font(Font.DIALOG,Font.BOLD,30));
        scrollListaSuspensaDeItens.setBounds(posicaoX+95,posicaoY+143,largura-579,altura-187);
        scrollListaSuspensaDeItens.setOpaque(false);
        scrollListaSuspensaDeItens.getViewport().setOpaque(false);
        //scrollListaSuspensaDeItens.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        //jListItem.setFont(new Font(Font.DIALOG,Font.BOLD,30));
        jListItem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selecionarItem();
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
        });

        return scrollListaSuspensaDeItens;
    }

    private JPanel telaInfoItem(int posicaoX, int posicaoY){
        final int LARGURA_IMG = 220, ALTURA_IMG = 250; //Tamanho da imagem da tela suspensa de info dos itens

        panelInfoItem = new JPanel();
        panelInfoItem.setLayout(null);
        panelInfoItem.setBackground(new Color(0,0,0,0));
        panelInfoItem.setBounds(posicaoX+607,posicaoY+220,LARGURA_IMG, ALTURA_IMG);
        //panelInfoItem.setVisible(false);

        //Fundo do painel
        JLabelOpcoesTelaSecao fundoPanel = new JLabelOpcoesTelaSecao(null,
                LARGURA_IMG, ALTURA_IMG,ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_1);
        fundoPanel.setHorizontalAlignment(SwingConstants.CENTER);
        fundoPanel.setVerticalAlignment(SwingConstants.CENTER);
        fundoPanel.setBounds(0,0, LARGURA_IMG, ALTURA_IMG);
        //fundoPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

        descricaoItem = new JTextPane();
        descricaoItem.setForeground(new Color(139,0,0));
        descricaoItem.setBackground(new Color(0,0,0,0));
        descricaoItem.setFocusable(false);
        descricaoItem.setEditable(false);
        descricaoItem.setFont(new Font(Font.DIALOG,Font.BOLD,12));
        descricaoItem.setBounds(46,96, LARGURA_IMG -95, ALTURA_IMG -140);
        StyledDocument textoLivroStyle = descricaoItem.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_CENTER);
        textoLivroStyle.setParagraphAttributes(0, textoLivroStyle.getLength(), configTexto, false);
        //descricaoItem.setBorder(BorderFactory.createLineBorder(Color.RED));


        //Criar fundo do título (nome do item e embaixo a descrição dele)
        JLabelOpcoesTelaSecao titulo = new JLabelOpcoesTelaSecao("",120,40,
                ImagensDoLivroFlorestaDaDestruicao.PLACA_1);
        titulo.setBounds(48,52,120,40);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setVerticalAlignment(SwingConstants.CENTER);
        //titulo.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Criando texto do título
        tituloTelaSuspensaInfo = new JLabel("");
        tituloTelaSuspensaInfo.setBounds(54,60,110,25);
        tituloTelaSuspensaInfo.setForeground(new Color(0,0,0));
        tituloTelaSuspensaInfo.setHorizontalAlignment(SwingConstants.CENTER);
        tituloTelaSuspensaInfo.setVerticalAlignment(SwingConstants.CENTER);
        tituloTelaSuspensaInfo.setFont(new Font(Font.DIALOG,Font.BOLD,10));
        //tituloTelaSuspensaInfo.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        panelInfoItem.add(tituloTelaSuspensaInfo);
        panelInfoItem.add(titulo);
        panelInfoItem.add(descricaoItem);
        panelInfoItem.add(fundoPanel);

        return panelInfoItem;
    }

    //Inclui a imagem no panel de escolha suspensa e guarda em um hashmap
    private Item itemSelecionadoParaCompra() {

        int posicaoX = 75;
        int posicaoY = 60;

        //Recupera as informações do item
        Item item = UtilItens.retornaItem(jListItem.getSelectedValue().getIdItem());

        //Se item já incluído não faça nada
        for (JLabelOpcoesTelaSecao key : mapItens.keySet()) {
            if (mapItens.get(key).getIdItem() == item.getIdItem())
                return null;
        }

        //Para ajustar largura da imagem. Vai ser necessário para alguns itens (padrão é 90)
        int largura = ajusteLargura(jListItem.getSelectedValue().getIdItem());


        //Cria o componente e incluir no panel
        JLabelOpcoesTelaSecao imagemItem = new JLabelOpcoesTelaSecao(null,
                largura,90,item.getEnderecoImagem());
        imagemItem.setBounds(posicaoX,posicaoY,largura,90);
        imagemItem.setName("REMOVER"); //caso precise resetar a escolha. esse marcador indica que pode remover do panel
        imagemItem.setHorizontalAlignment(SwingConstants.CENTER);
        panelItemAserComprado.add(imagemItem);
        //imagemItem.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Guarda a referência do botão(item) para ser usado em seguida
        mapItens.put(imagemItem,item);
        repaint();

        return item;
    }

    private void selecionarItem(){
        Item item = itemSelecionadoParaCompra();

        if (item == null)
            return;

        descricaoItem.setText( item.getDescricao() );
        tituloTelaSuspensaInfo.setText( item.getNome() );
    }

    private int ajusteLargura(int idItem) {
        int larguraPadrao = 90;

        switch (idItem){
            case  5: return larguraPadrao - 10; //Chave de prata
            case 10: return larguraPadrao + 10; //Espada Magnífica
            case 16: return larguraPadrao - 10; //Poção Controle dos Insetos
            case 25: return larguraPadrao - 20; //Cabo do Martelo de Guerra dos Anões
        }

        return larguraPadrao;
    }

    private void removerItemSelecionado(){

            //Limpar o hashmap coim os itens selecionados
            //mapItens.clear();

            //Removendo todos os componentes menos o fundo (imagem) circular
            Component[] components = panelItemAserComprado.getComponents();
            for (Component component : components) {

                if (component.getName() == null)
                    continue;

                if ( component.getName().equals("REMOVER") ) {
                    panelItemAserComprado.remove(component);
                }
            }
            repaint();
    }

    private void limparPanelEscolhaItensASeremDescartados(){

        //Limpar o hashmap coim os itens selecionados
        mapItens.clear();

        //Removendo todos os componentes menos o fundo (imagem) circular
        Component[] components = panelItemAserComprado.getComponents();
        for (Component component : components) {

            if (component.getName() == null)
                continue;

            if ( component.getName().equals("REMOVER") ) {
                panelItemAserComprado.remove(component);
            }
        }
        repaint();
    }

    private void confirmarEscolhaItens() {
        /*
        if ( escolheuItensDaListaSuspensa )
            return;

        if (mapItens.size() < limiteDeEscolhaDeItens) {
            CarregarTelas.telaMensagem(personagem.getNome()+
                    ",\nVocê precisa escolher "+limiteDeEscolhaDeItens+" itens.");
            return;
        }

        for (JLabelOpcoesTelaSecao key : mapItens.keySet()) {
            UtilBolsa.removerItem(mapItens.get(key).getIdItem());
        }

        escolheuItensDaListaSuspensa = true;
        panelListaSuspensaItens.setVisible(false);
        panelListaItensEscolhidos.setVisible(false);

        //Trata determinadas ações quando usa a telinha de escolha de itens
        acoesEspecificasDasSecoes();
        */
    }
}
