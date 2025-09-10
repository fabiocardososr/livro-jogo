package livro.jogo.telas.desktop.principal;

import livro.jogo.acaosecoes.AcoesLojaDoMago;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.PanelCircular;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.ListItem;
import livro.jogo.telas.desktop.personalizados.util.ListaDeItensComImagem;
import livro.jogo.utils.*;

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
    private JPanel panelItemAserCompradoCusto;
    private DefaultListModel<ListItem> listaItensParaEscolha;
    private HashMap<JLabelOpcoesTelaSecao, Item> mapItens = new HashMap<JLabelOpcoesTelaSecao, Item>();
    private Container telaPrincipalLista;
    private JPanel panelInfoItem; //Representa a tela suspensa de informação do item
    private JTextPane descricaoItem; //Na tela suspensa de informação
    private JLabel tituloTelaSuspensaInfo;
    private JList<ListItem> jListItem;
    private Item itemSelecionado;
    private JLabel lbCusto;
    private TelaSecoesBasica telaSecao;
    private JLabel lbBotaoConfirmar;  //Usado para informar quando o item não pode ser adquirido uma segunda vez

    public LojaDoMago(int posicaoX, int posicaoY,
                      int largura, int altura, TelaSecoesBasica telaSecao) {


        this.telaSecao = telaSecao;


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
        fundoPanel.setCursor(null);
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
        fundoPanelEscolha.setCursor(null);
        //fundoPanelEscolha.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        ///Botão de confirmação
        JLabelOpcoesTelaSecao botaoConfirmar = new JLabelOpcoesTelaSecao(null,
                100,60,ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoConfirmar.setBounds(posicaoX+75,posicaoY+175,100,60);
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
        lbBotaoConfirmar = new JLabel("Comprar");
        lbBotaoConfirmar.setFont(new Font(Font.SERIF,Font.BOLD,14));
        lbBotaoConfirmar.setForeground(new Color(139,0,0));
        lbBotaoConfirmar.setHorizontalAlignment(SwingConstants.CENTER);
        lbBotaoConfirmar.setBounds(posicaoX+93,posicaoY+191,65,20);
        lbBotaoConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelItemAserComprado.add(lbBotaoConfirmar);
        panelItemAserComprado.add(botaoConfirmar);
        panelItemAserComprado.add(fundoPanelEscolha);
        /*** FIM: tela arredondda para visualizar item para compra ***/


        /*** Tela arredonda para visualizar VALOR EM OURO ***/

        //Painel que mostrará o item a ser comprado comprados
        panelItemAserCompradoCusto = new PanelCircular();
        panelItemAserCompradoCusto.setLayout(null);
        panelItemAserCompradoCusto.setBounds(posicaoX+750,posicaoY+10,100,80);
        panelItemAserCompradoCusto.setBackground(new Color(0,0,0, 190));
        //panelListaItensEscolhidos.setBackground(Color.DARK_GRAY);


        //Fundo painel suspenso escolha
        JLabelOpcoesTelaSecao fundoPanelEscolhaCusto = new JLabelOpcoesTelaSecao(null,
                113, 83, ImagensDoLivroFlorestaDaDestruicao.MOLDURA_CIRCULAR);
        fundoPanelEscolhaCusto.setHorizontalAlignment(SwingConstants.CENTER);
        fundoPanelEscolhaCusto.setVerticalAlignment(SwingConstants.CENTER);
        fundoPanelEscolhaCusto.setBounds(0,-2,100,80);
        //fundoPanelEscolhaCusto.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Titulo da tela de valor do item
        JLabelOpcoesTelaSecao tituloTelaCusto = new JLabelOpcoesTelaSecao(null,
                80, 30, ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        tituloTelaCusto.setHorizontalAlignment(SwingConstants.CENTER);
        tituloTelaCusto.setVerticalAlignment(SwingConstants.BOTTOM);
        tituloTelaCusto.setBounds(8,posicaoY-23,80,50);
        //tituloTelaCusto.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Título
        JLabel lbTituloCusto = new JLabel("Ouro");
        lbTituloCusto.setFont(new Font(Font.SERIF,Font.BOLD,14));
        lbTituloCusto.setForeground(new Color(139,0,0));
        lbTituloCusto.setHorizontalAlignment(SwingConstants.CENTER);
        lbTituloCusto.setBounds(9,0,80,20);
        //lbTituloCusto.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        lbCusto = new JLabel("");
        lbCusto.setFont(new Font(Font.SERIF,Font.BOLD,40));
        lbCusto.setForeground(Color.white);
        lbCusto.setHorizontalAlignment(SwingConstants.CENTER);
        lbCusto.setBounds(posicaoX+25,posicaoY+20,50,45);
        //lbCusto.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        panelItemAserCompradoCusto.add(lbTituloCusto);
        panelItemAserCompradoCusto.add(tituloTelaCusto);
        panelItemAserCompradoCusto.add(lbCusto);
        panelItemAserCompradoCusto.add(fundoPanelEscolhaCusto);

        /*** FIM: Tela arredonda para visualizar VALOR EM OURO ***/


        ///Botão sair sem fazer nada
        JLabelOpcoesTelaSecao botaoSair = new JLabelOpcoesTelaSecao(null,
                100,60,ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoSair.setBounds(posicaoX+254,posicaoY+470,100,60);
        botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSair.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                removerItemSelecionado();
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

        panelListaItensAVenda.add(lbBotaoSair);
        panelListaItensAVenda.add(botaoSair);
        panelListaItensAVenda.add( criarListaItens(posicaoX, posicaoY, largura, altura) );
        panelListaItensAVenda.add(fundoPanel);

        tela.add(panelItemAserComprado);
        tela.add(panelItemAserCompradoCusto);
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
        jListItem.setCellRenderer(new ListaDeItensComImagem());
        jListItem.setVisibleRowCount(5);
        jListItem.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jListItem.setForeground(Color.WHITE);
        jListItem.setBackground(new Color(0,0,0,0));
        jListItem.setOpaque(false);
        JScrollPane scrollListaSuspensaDeItens = new JScrollPane(jListItem);
        scrollListaSuspensaDeItens.setBounds(posicaoX+95,posicaoY+143,largura-579,altura-187);
        scrollListaSuspensaDeItens.setOpaque(false);
        scrollListaSuspensaDeItens.getViewport().setOpaque(false);
        //scrollListaSuspensaDeItens.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //desabilitar teclas UP e Down
        InputMap inputMap = jListItem.getInputMap(JComponent.WHEN_FOCUSED);
        inputMap.put(KeyStroke.getKeyStroke("UP"), "none");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "none");
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
        Item item = UtilItens.retornaItem( jListItem.getSelectedValue().getIdItem() );

        //Para ajustar largura da imagem. Vai ser necessário para alguns itens (padrão é 90)
        int largura = ajusteLargura(jListItem.getSelectedValue().getIdItem());

        //Cria o componente e incluir no panel
        JLabelOpcoesTelaSecao imagemItem = new JLabelOpcoesTelaSecao(null,
                largura,90,item.getEnderecoImagem());
        imagemItem.setBounds(posicaoX,posicaoY,largura,90);
        imagemItem.setName("REMOVER"); //caso precise resetar a escolha. esse marcador indica que pode remover do panel
        imagemItem.setHorizontalAlignment(SwingConstants.CENTER);
        panelItemAserComprado.add(imagemItem);
        repaint();

        return item;
    }

    private void selecionarItem(){

        //Remover item anterior da tela
        removerItemSelecionado();

        //Recuperando o objeto Item
        itemSelecionado = itemSelecionadoParaCompra();

        //Preenchendo informações do item
        descricaoItem.setText( itemSelecionado.getDescricao() );
        tituloTelaSuspensaInfo.setText( itemSelecionado.getNome() );

        //Se já comprou, não pode adquirir outro
        if ( UtilBolsa.verificarExistenciaDeItemNaBolsa(itemSelecionado.getIdItem()) ) {
            lbCusto.setText("0");
            lbBotaoConfirmar.setFont(new Font(Font.SERIF,Font.BOLD,12));
            lbBotaoConfirmar.setText("Comprado!");
        }
        else {
            lbCusto.setText(String.valueOf(itemSelecionado.getValorCusto()));
            lbBotaoConfirmar.setFont(new Font(Font.SERIF,Font.BOLD,14));
            lbBotaoConfirmar.setText("Comprar");
        }
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

            //Removendo a imagem do artefato
            Component[] components = panelItemAserComprado.getComponents();
            for (Component component : components) {

                if (component.getName() == null)
                    continue;

                if ( component.getName().equals("REMOVER") ) {
                    panelItemAserComprado.remove(component);
                }
            }

            //Apagar campos de informações do artefato (custo e descrição)
            descricaoItem.setText("");
            tituloTelaSuspensaInfo.setText("");
            lbCusto.setText("");
            itemSelecionado = null;

            repaint();
    }

    private void confirmarEscolhaItens() {
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        if (itemSelecionado == null)
            return;

        //Verifica se possui ouro suficiente para a compra
        if ( itemSelecionado.getValorCusto() > personagem.getQuantidadeOuro() ){
            new Util().reproduzirAudioMp3("audio/efeitos_sonoros/azar.mp3", null);
            CarregarTelas.telaMensagem(personagem.getNome()+
                    ",\n\nVocê não possui ouro suficiente para comprar o(a) "+itemSelecionado.getNome()+".");
            return;
        }


        if ( UtilBolsa.verificarExistenciaDeItemNaBolsa(itemSelecionado.getIdItem()) ){
            CarregarTelas.telaMensagem(personagem.getNome()+
                    ",\n\nSomente é possivel adquirir um artefato. Você já comprou o(a) "+itemSelecionado.getNome()+".");
            return;
        }

        //Efeito sonoro de moedas
        new Util().reproduzirAudioMp3("audio/efeitos_sonoros/moedas.mp3", null);

        //Decrementar valor em ouro
        UtilPersonagem.reduzirValorOuro( itemSelecionado.getValorCusto() );

        //Atualiza tela
        telaSecao.atualizaIndicesNaTelaDoPersonagem();

        //Inclui na bolsa
        UtilBolsa.incluirItem( itemSelecionado );

        //Aviso que adquiriu o item
//        CarregarTelas.telaMensagem(personagem.getNome()+
//                ",\n\nVocê comprou o(a) "+itemSelecionado.getNome()+".");

        //Apagar informações do artefato
        removerItemSelecionado();
    }
}
