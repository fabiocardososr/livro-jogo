package livro.jogo.telas.desktop.principal;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.PanelCircular;
import livro.jogo.telas.desktop.personalizados.util.ListItem;
import livro.jogo.telas.desktop.personalizados.util.ListaDeItensComImagem;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class LojaDoMago extends JDialog {
    private JPanel panelListaItensAVenda;
    private JPanel panelListaItensComprados;
    private DefaultListModel<ListItem> listaItensParaEscolha;
    private HashMap<JLabelOpcoesTelaSecao, Item> mapItens = new HashMap<JLabelOpcoesTelaSecao, Item>();
    private Secao secao;

    public LojaDoMago(int posicaoX, int posicaoY,
                      int largura, int altura, Secao secao) {


            this.secao = secao;

        /* CRIAÇÃO DOS COMPONENTES VISUAIS */

            //Painel geral
            setSize(largura,altura);
            Container tela = getContentPane();
            setUndecorated(true);
            setBackground(new Color(0,0,0,50));
            setSize(largura,altura);
            setLayout(null);
            setModal(true);
            setLocationRelativeTo(null);


            /**** Painel que incorporará a lista de itens da bolsa ****/
            int larguraListaItensAVenda = largura-400;
            panelListaItensAVenda = new JPanel();
            panelListaItensAVenda.setLayout(null);
            panelListaItensAVenda.setBounds(posicaoX,posicaoY,larguraListaItensAVenda,altura);
            panelListaItensAVenda.setBackground(new Color(0,0,0,0));

            //Fundo painel suspenso principal (panelListaSuspensaItens)
            JLabelOpcoesTelaSecao fundoPanel = new JLabelOpcoesTelaSecao(null,
                    larguraListaItensAVenda, altura,ImagensDoLivroFlorestaDaDestruicao.MOLDURA_16);
            fundoPanel.setBounds(0,0,larguraListaItensAVenda,altura);
        /**** FIM: Painel que incorporará a lista de itens da bolsa ****/

            //Painel que mostrará os itens comprados
            panelListaItensComprados = new PanelCircular();
            panelListaItensComprados.setLayout(null);
            panelListaItensComprados.setBounds(posicaoX+410,posicaoY+7,250,230);
            panelListaItensComprados.setBackground(new Color(0,0,0, 210));
            //panelListaItensEscolhidos.setBackground(Color.DARK_GRAY);


            //Fundo painel suspenso escolha (panelListaItensComprados)
            JLabelOpcoesTelaSecao fundoPanelEscolha = new JLabelOpcoesTelaSecao(null,
                    273, 243, ImagensDoLivroFlorestaDaDestruicao.MOLDURA_CIRCULAR);
            fundoPanelEscolha.setHorizontalAlignment(SwingConstants.CENTER);
            fundoPanelEscolha.setVerticalAlignment(SwingConstants.CENTER);
            fundoPanelEscolha.setBounds(0,-2,250,230);
            //fundoPanelEscolha.setBorder(BorderFactory.createLineBorder(Color.BLUE));


            //Cria um listModel para que possa iterar diretamente com o JList.
            //Atualizando ele, automaticamente o JList muda
            listaItensParaEscolha = new DefaultListModel<>();
            for (ListItem listItem : UtilBolsa.retornaListaDeBensNaBolsa())
                listaItensParaEscolha.addElement(listItem);

            //Criando o JList de itens na bolsa
            JList<ListItem> jListItem = new JList<>(listaItensParaEscolha);
            jListItem.setCellRenderer(new ListaDeItensComImagem());
            jListItem.setVisibleRowCount(5);
            jListItem.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            jListItem.setForeground(Color.WHITE);
            jListItem.setBackground(new Color(0,0,0,0));
            jListItem.setOpaque(false);
            JScrollPane scrollListaSuspensaDeItens = new JScrollPane(jListItem);
            scrollListaSuspensaDeItens.setBounds(68,47,292,altura-95);
            scrollListaSuspensaDeItens.setOpaque(false);
            scrollListaSuspensaDeItens.getViewport().setOpaque(false);
            //scrollListaSuspensaDeItens.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            jListItem.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cliqueEmItemDaLista();
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

            //Botão de confirmação
            JLabelOpcoesTelaSecao botaoConfirmar = new JLabelOpcoesTelaSecao(null,
                    100,60,ImagensDoLivroFlorestaDaDestruicao.FAIXA);
            botaoConfirmar.setBounds(50,195,100,60);
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

            //Botão sair sem fazer nada
            JLabelOpcoesTelaSecao botaoSair = new JLabelOpcoesTelaSecao(null,
                    100,60,ImagensDoLivroFlorestaDaDestruicao.FAIXA);
            botaoSair.setBounds(160,195,100,60);
            botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
            botaoSair.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    limparPanelEscolhaItensASeremDescartados();
                    panelListaItensAVenda.setVisible(false);
                    panelListaItensComprados.setVisible(false);
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
            //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));

            //Botão limpar (resetar)
            JLabelOpcoesTelaSecao botaoResetar = new JLabelOpcoesTelaSecao(null,
                    100,60,ImagensDoLivroFlorestaDaDestruicao.FAIXA);
            botaoResetar.setBounds(270,195,100,60);
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

            //label do botãoSair
            JLabel lbBotaoSair = new JLabel("Sair");
            lbBotaoSair.setFont(new Font(Font.SERIF,Font.BOLD,16));
            lbBotaoSair.setForeground(new Color(139,0,0));
            lbBotaoSair.setHorizontalAlignment(SwingConstants.CENTER);
            lbBotaoSair.setBounds(185,212,50,20);
            lbBotaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
            //lbBotaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));


            //label do botaoConfirmar
            JLabel lbBotaoConfirmar = new JLabel("OK");
            lbBotaoConfirmar.setFont(new Font(Font.SERIF,Font.BOLD,14));
            lbBotaoConfirmar.setForeground(new Color(139,0,0));
            lbBotaoConfirmar.setHorizontalAlignment(SwingConstants.CENTER);
            lbBotaoConfirmar.setBounds(75,212,50,20);
            lbBotaoConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));

            //label do resetar
            JLabel lbBotaoLimpar = new JLabel("Limpar");
            lbBotaoLimpar.setFont(new Font(Font.SERIF,Font.BOLD,14));
            lbBotaoLimpar.setForeground(new Color(139,0,0));
            lbBotaoLimpar.setHorizontalAlignment(SwingConstants.CENTER);
            lbBotaoLimpar.setBounds(295,212,50,20);
            lbBotaoLimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));

            //Adicionando componentes no panel que mostra os itens a serem escolhidos
            panelListaItensAVenda.add(lbBotaoLimpar);
            panelListaItensAVenda.add(botaoResetar);
            panelListaItensAVenda.add(lbBotaoConfirmar);
            panelListaItensAVenda.add(botaoConfirmar);
            panelListaItensAVenda.add(lbBotaoSair);
            panelListaItensAVenda.add(botaoSair);
            panelListaItensComprados.add(fundoPanelEscolha);
            panelListaItensAVenda.add(scrollListaSuspensaDeItens);
            panelListaItensAVenda.add(fundoPanel);

            tela.add(panelListaItensComprados);
            tela.add(panelListaItensAVenda);


            //Carrega, mas deixa invisível, pois nos testes demorou a ser carregada quando clicado no botão
            //panelListaItensAVenda.setVisible(false);
            //panelListaItensComprados.setVisible(false);
    }


    public JPanel getPanelListaItensAVenda() {
        return panelListaItensAVenda;
    }

    public JPanel getPanelListaItensComprados() {
        return panelListaItensComprados;
    }

    //Inclui a imagem no panel de escolha suspensa e guarda em um hashmap
    private void incluirItemEscolhido(JLabelOpcoesTelaSecao imagemItem, JList<ListItem> jListItem,
                                      int espacoEntreItens) {

        /*
        int posicaoX = 25 + espacoEntreItens;
        int posicaoY = 80;

        //Recupera as informações do item
        Item item = UtilItens.retornaItem(jListItem.getSelectedValue().getIdItem());

        //Se item já incluído não faça nada
        for (JLabelOpcoesTelaSecao key : mapItens.keySet()) {
            if (mapItens.get(key).getIdItem() == item.getIdItem())
                return;
        }

        //Para ajustar largura da imagem. Vai ser necessário para alguns itens (padrão é 60)
        int largura = ajusteLargura(jListItem.getSelectedValue().getIdItem());


        //Cria o componente e incluir no panel
        imagemItem = new JLabelOpcoesTelaSecao(null,
                largura,70,item.getEnderecoImagem());
        imagemItem.setBounds(posicaoX+espacoEntreItens,posicaoY,largura,70);
        imagemItem.setName("REMOVER"); //caso precise resetar a escolha. Entrão esse marcador indica que pode remover do panel
        imagemItem.setHorizontalAlignment(SwingConstants.CENTER);
        panelListaItensEscolhidos.add(imagemItem);
        //imagemItem.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Guarda a referência do botão(item) para ser usado em seguida
        mapItens.put(imagemItem,item);
        repaint();
        */
    }

    private void cliqueEmItemDaLista() {

        /*
        //Se já escolhidos 2 itens
        if (mapItens.size() == limiteDeEscolhaDeItens){
            if (limiteDeEscolhaDeItens > 1)
                CarregarTelas.telaMensagem("Os "+limiteDeEscolhaDeItens+
                        " itens já foram escolhidos.\n\n"+
                        "Se precisar, remova-os e faça novas escolhas.");
            else
                CarregarTelas.telaMensagem(limiteDeEscolhaDeItens+
                        " item já foi escolhido.\n\n"+
                        "Se precisar, remova-o e faça nova escolha.");

            return;
        }

        if ( mapItens.isEmpty() ){

            /// trata o posicionamento de acordo com a quantidade de itens a serem descartados
            /// Exemplo, na seção 14,271,129,218,242 é apenas 1 item então posiciona mais ou menos no centro da tela
            /// os demais posiciona o primeiro item à esquerda
            ///e depois o espaçamento de 50 entre eles
            switch ( secao.getCodSecaoLivro() ) {
                case 14, 271,129,218,242 -> incluirItemEscolhido(imagemItemEscolhido1, jListItem,32);
                default  -> incluirItemEscolhido(imagemItemEscolhido1, jListItem,10);
            }
        }else {
            incluirItemEscolhido(imagemItemEscolhido2, jListItem,50);
        }
        */
    }

    private void limparPanelEscolhaItensASeremDescartados(){

        //Limpar o hashmap coim os itens selecionados
        mapItens.clear();

        //Removendo todos os componentes menos o fundo (imagem) circular
        Component[] components = panelListaItensComprados.getComponents();
        for (Component component : components) {

            if (component.getName() == null)
                continue;

            if ( component.getName().equals("REMOVER") ) {
                panelListaItensComprados.remove(component);
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
