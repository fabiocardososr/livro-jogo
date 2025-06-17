package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.BotaoFaixaOpcoes;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.PanelCircular;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.ListItem;
import livro.jogo.telas.desktop.personalizados.util.ListaDeItensComImagem;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilBolsa;
import livro.jogo.utils.UtilItens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_279 extends TelaSecoesBasica {
    private int qtdPagar5itens = 5;  //5 itens e vai decrementando a medida que vai sendo pago(removido da bolsa)
    private JLabelOpcoesTelaSecao botaoEscolhaItens; //Deixei no escopo global para que seja desabilitado caso escolha a opção de pagar com moedas
    private DefaultListModel<ListItem> listaItensParaEscolha; //Vai servir para a tela de escolha de itens
    private Item itemEscolhido;


    public TelaSecao_279(Secao secao) {
        super(secao);
    }

    //Limpa a lista de itens selecionados
    private void limparPanelEscolhaItensASeremDescartados(){

        //Limpar o hashmap coim os itens selecionados
        itemEscolhido = null;

        //Removendo todos os componentes menos o fundo (imagem) circular
        Component[] components = panelListaItensEscolhidos.getComponents();
        for (Component component : components) {

            if (component.getName() == null)
                continue;

            if ( component.getName().equals("REMOVER") ) {
                panelListaItensEscolhidos.remove(component);
            }
        }
        repaint();
    }

    private void confirmarEscolhaItens() {

        if (qtdPagar5itens == 0){
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nA mulher já foi paga.");
        }

        //Remover item da bolsa
        UtilBolsa.removerItem(itemEscolhido.getIdItem());

        //Decrementa a variável que indica quantos itens faltam entregar (conta ouro como um item)
        --qtdPagar5itens;

        escolheuItensDaListaSuspensa = true;
        panelListaSuspensaItens.setVisible(false);
        panelListaItensEscolhidos.setVisible(false);


    }

    private void incluirItemEscolhido(JLabelOpcoesTelaSecao imagemItem, JList<ListItem> jListItem) {
        int posicaoX = 85;
        int posicaoY = 75;

        //Excluir seleção anterior
        limparPanelEscolhaItensASeremDescartados();

        //Recupera as informações do item
        itemEscolhido = UtilItens.retornaItem(jListItem.getSelectedValue().getIdItem());

        //Para ajustar largura da imagem. Vai ser necessário para alguns itens (padrão é 60)
        int largura = ajusteLargura(jListItem.getSelectedValue().getIdItem());


        //Cria o componente e incluir no panel
        imagemItem = new JLabelOpcoesTelaSecao(null,
                largura,70,itemEscolhido.getEnderecoImagem());
        imagemItem.setBounds(posicaoX,posicaoY,largura,70);
        imagemItem.setName("REMOVER"); //caso precise resetar a escolha. Entrão esse marcador indica que pode remover do panel
        imagemItem.setHorizontalAlignment(SwingConstants.CENTER);
        panelListaItensEscolhidos.add(imagemItem);

        repaint();
    }

    protected void carregaListaDeItensNaBolsaQuePodemSerEntregues(int posicaoX, int posicaoY,
                                                                  int largura, int altura) {


        /* CRIAÇÃO DOS COMPONENTES VISUAIS */

        //Painel que incorporará a lista de itens da bolsa
        panelListaSuspensaItens = new JPanel();
        panelListaSuspensaItens.setLayout(null);
        panelListaSuspensaItens.setBounds(posicaoX,posicaoY,largura,altura);
        panelListaSuspensaItens.setBackground(new Color(0,0,0,0));

        //Painel que mostrará os itens escolhidos
        panelListaItensEscolhidos = new PanelCircular();
        panelListaItensEscolhidos.setLayout(null);
        panelListaItensEscolhidos.setBounds(posicaoX+410,posicaoY+7,250,230);
        panelListaItensEscolhidos.setBackground(new Color(0,0,0, 210));
        //panelListaItensEscolhidos.setBackground(Color.DARK_GRAY);


        //Fundo painel suspenso escolha (panelListaItensEscolhidos)
        JLabelOpcoesTelaSecao fundoPanelEscolha = new JLabelOpcoesTelaSecao(null,
                273, 243,ImagensDoLivroFlorestaDaDestruicao.MOLDURA_CIRCULAR);
        fundoPanelEscolha.setHorizontalAlignment(SwingConstants.CENTER);
        fundoPanelEscolha.setVerticalAlignment(SwingConstants.CENTER);
        fundoPanelEscolha.setBounds(0,-2,250,230);


        //Fundo painel suspenso principal (panelListaSuspensaItens)
        JLabelOpcoesTelaSecao fundoPanel = new JLabelOpcoesTelaSecao(null,
                largura, altura,ImagensDoLivroFlorestaDaDestruicao.MOLDURA_16);
        fundoPanel.setBounds(0,0,largura,altura);


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
                //Se já escolhidos o limite de itens
                if  (qtdPagar5itens == 0) {
                    CarregarTelas.telaMensagem("A mulher foi paga");
                    return;
                }

                incluirItemEscolhido(imagemItemEscolhido1, jListItem);

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
                panelListaSuspensaItens.setVisible(false);
                panelListaItensEscolhidos.setVisible(false);
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
        JLabel lbBotaoLimpar = new JLabel("Resetar");
        lbBotaoLimpar.setFont(new Font(Font.SERIF,Font.BOLD,14));
        lbBotaoLimpar.setForeground(new Color(139,0,0));
        lbBotaoLimpar.setHorizontalAlignment(SwingConstants.CENTER);
        lbBotaoLimpar.setBounds(295,212,50,20);
        lbBotaoLimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Adicionando componentes no panel que mostra os itens a serem escolhidos
        panelListaSuspensaItens.add(lbBotaoLimpar);
        panelListaSuspensaItens.add(botaoResetar);
        panelListaSuspensaItens.add(lbBotaoConfirmar);
        panelListaSuspensaItens.add(botaoConfirmar);
        panelListaSuspensaItens.add(lbBotaoSair);
        panelListaSuspensaItens.add(botaoSair);
        panelListaItensEscolhidos.add(fundoPanelEscolha);
        panelListaSuspensaItens.add(scrollListaSuspensaDeItens);
        panelListaSuspensaItens.add(fundoPanel);

        add(panelListaItensEscolhidos);
        add(panelListaSuspensaItens);


        //Carrega, mas deixa invisível, pois nos testes demorou a ser carregada quando clicado no botão
        panelListaSuspensaItens.setVisible(false);
        panelListaItensEscolhidos.setVisible(false);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        carregaListaDeItensNaBolsaQuePodemSerEntregues(80,550,420,250);

        opcao1(secao);

        //posicionando as opção mais abaixo
        botaoOpcao1.setBounds(120,640,40,50);
        labelNumOpcao1.setBounds(116,632, 50,50);
        lbTextoOpcao1.setBounds(170,627,700,60);


        //Ação ao clicar
        acaoBotoes(secao);

        //Escolher moedas para dar ao Gnomo
        //carregaBotaoOpcaoMoedas();

        //Escolher 2 itens para dar ao Gnomo
        carregarListaItensParaDarAoGnomo();
    }

    private void carregarListaItensParaDarAoGnomo() {

        //Botão
        botaoEscolhaItens = new BotaoFaixaOpcoes(450,560,340,80)
                .criarBotao();
        botaoEscolhaItens.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirInventario();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoEscolhaItens.setIcon(Util.dimensionarImagem(botaoEscolhaItens.getWidth(),
                        botaoEscolhaItens.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoEscolhaItens.setIcon(Util.dimensionarImagem(botaoEscolhaItens.getWidth(),
                        botaoEscolhaItens.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        //Texto
        JLabel texto= new JLabel("<html><center>Escolha itens</center></html>");
        texto.setBounds(555,585,130,25);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,20));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Escolha itens para dar a mulher.");
        //texto.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirInventario();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoEscolhaItens.setIcon(Util.dimensionarImagem(botaoEscolhaItens.getWidth(),
                        botaoEscolhaItens.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoEscolhaItens.setIcon(Util.dimensionarImagem(botaoEscolhaItens.getWidth(),
                        botaoEscolhaItens.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //textoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(texto);
        add(botaoEscolhaItens);
    }

    private void abrirInventario() {
        if (qtdPagar5itens == 0) {
            CarregarTelas.telaMensagem("Você já pagou.");
            return;
        }

        panelListaSuspensaItens.setVisible(true);
        panelListaItensEscolhidos.setVisible(true);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getSource() == botaoOpcao1){

                    if ( qtdPagar5itens == 5)
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem("AQUI MENSAGEM QUANDO NÃO PAGAR O QUE A MULHER QUER");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    botaoOpcao1.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1_SELECIONADO.getEnderecoImagem(),
                            botaoOpcao1.getWidth(), botaoOpcao1.getHeight()).getImageIcon());
                    repaint();
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    botaoOpcao1.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1.getEnderecoImagem(),
                            botaoOpcao1.getWidth(), botaoOpcao1.getHeight()).getImageIcon());
                    repaint();
                }
            }
        });
    }
}
