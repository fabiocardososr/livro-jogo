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
import livro.jogo.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_279 extends TelaSecoesBasica {
    private int qtdPagar5itens;  //5 itens e vai decrementando a medida que vai sendo pago(removido da bolsa)
    private JLabelOpcoesTelaSecao botaoEscolhaItens; //Deixei no escopo global para que seja desabilitado caso escolha a opção de pagar com moedas
    private JList<ListItem> jListItem;
    private DefaultListModel<ListItem> listaItensParaEscolha; //Vai servir para a tela de escolha de itens
    private Item itemEscolhido;
    private JLabel lbFaixaPanelSuspensoInfoQtdItensFaltam;
    private JLabel lbFaixaInfoQtdItensFaltam;
    private JLabelOpcoesTelaSecao botaoDarMoedas;


    public TelaSecao_279(Secao secao) {
        super(secao);

    }

    //Atualiza infos que mostra quanto falta de itens ou moedas a serem pagas
    private void atualizaInfoPagamento(){
        lbFaixaPanelSuspensoInfoQtdItensFaltam.setText("Falta pagar: "+qtdPagar5itens );
        lbFaixaInfoQtdItensFaltam.setText("<html><center>Pagar<br>"+qtdPagar5itens+" item(ns)</center></html>");

        repaint();
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

    //Ao clicar no botão quando escolhido um item
    private void confirmarEscolhaItens() {

        if ( (itemEscolhido == null) || (jListItem.getSelectedIndex() == -1) )
            return;

        if (qtdPagar5itens == 0){
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                    ",\n\nA mulher já foi paga.");
            fecharTelaListaItens();
            return;
        }

        //Remover item da bolsa
        UtilBolsa.removerItem(itemEscolhido.getIdItem());

        //Decrementa a variável que indica quantos itens faltam entregar (conta ouro como um item)
        --qtdPagar5itens;

        // Para remover o item selecionado
        int selectedIndex = jListItem.getSelectedIndex();
        if (selectedIndex != -1) {
            listaItensParaEscolha.remove(selectedIndex);
        }

        //Limpa panel de visualização do item
        limparPanelEscolhaItensASeremDescartados();

        //atualiza informação sobre quanto falta pagar
        atualizaInfoPagamento();
    }

    private void fecharTelaListaItens(){
        panelListaSuspensaItens.setVisible(false);
        panelListaItensEscolhidos.setVisible(false);
    }

    private void incluirItemEscolhido(JLabelOpcoesTelaSecao imagemItem) {
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
        imagemItem.setName("REMOVER"); //caso precise resetar a escolha. Então esse marcador indica que pode remover do panel
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
        jListItem = new JList<>(listaItensParaEscolha);
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
                    fecharTelaListaItens();
                    return;
                }

                incluirItemEscolhido(imagemItemEscolhido1);
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
        botaoConfirmar.setBounds(100,195,100,60);
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

        //label do botaoConfirmar
        JLabel lbBotaoConfirmar = new JLabel("Confirmar");
        lbBotaoConfirmar.setFont(new Font(Font.SERIF,Font.BOLD,12));
        lbBotaoConfirmar.setForeground(new Color(139,0,0));
        lbBotaoConfirmar.setHorizontalAlignment(SwingConstants.CENTER);
        lbBotaoConfirmar.setBounds(122,212,55,20);
        lbBotaoConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //lbBotaoConfirmar.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão sair sem fazer nada
        JLabelOpcoesTelaSecao botaoSair = new JLabelOpcoesTelaSecao(null,
                100,60,ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoSair.setBounds(228,195,100,60);
        botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSair.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limparPanelEscolhaItensASeremDescartados();
                fecharTelaListaItens();
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

        //label do botãoSair
        JLabel lbBotaoSair = new JLabel("Sair");
        lbBotaoSair.setFont(new Font(Font.SERIF,Font.BOLD,16));
        lbBotaoSair.setForeground(new Color(139,0,0));
        lbBotaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        lbBotaoSair.setBounds(253,212,50,20);
        lbBotaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //lbBotaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        //Faixa de informação de quantos itens faltam para pagar
        JLabelOpcoesTelaSecao faixaInfoQtdItensFaltam = new JLabelOpcoesTelaSecao(null,
                200,80,ImagensDoLivroFlorestaDaDestruicao.FAIXA_8_REDUZIDO);
        faixaInfoQtdItensFaltam.setBounds(25,-10,200,80);
        faixaInfoQtdItensFaltam.setCursor(null);

        //label da faixa de informação de quantos itens faltam para pagar
        lbFaixaPanelSuspensoInfoQtdItensFaltam = new JLabel("Falta pagar: "+String.valueOf(qtdPagar5itens) );
        lbFaixaPanelSuspensoInfoQtdItensFaltam.setFont(new Font(Font.SERIF,Font.BOLD,16));
        lbFaixaPanelSuspensoInfoQtdItensFaltam.setForeground(new Color(139,0,0));
        lbFaixaPanelSuspensoInfoQtdItensFaltam.setHorizontalAlignment(SwingConstants.CENTER);
        lbFaixaPanelSuspensoInfoQtdItensFaltam.setBounds(51,10,150,25);
        lbFaixaPanelSuspensoInfoQtdItensFaltam.setCursor(null);
        //lbfaixaInfoQtdItensFaltam.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Adicionando componentes no panel que mostra os itens a serem escolhidos
        panelListaItensEscolhidos.add(lbFaixaPanelSuspensoInfoQtdItensFaltam);
        panelListaItensEscolhidos.add(faixaInfoQtdItensFaltam);
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
        fecharTelaListaItens();
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        //estou inicializando aqui pois no construtor essa função
        //está sendo chamada antes e vem zerado quando tento mostrá-la no label
        this.qtdPagar5itens = 5;

        carregaListaDeItensNaBolsaQuePodemSerEntregues(80,550,420,250);

        opcao1(secao);

        //posicionando as opção mais abaixo
        botaoOpcao1.setBounds(120,710,40,50);
        labelNumOpcao1.setBounds(116,702, 50,50);
        lbTextoOpcao1.setBounds(170,697,700,60);


        //Ação ao clicar
        acaoBotoes(secao);

        //Escolher moedas para dar ao Gnomo
        //carregaBotaoOpcaoMoedas();

        //Carrega faixa de informações de quantos itens faltam
        carregaFaixaDeInformacaoDeQuantitativoDeitensFaltantes();

        //Carrega botão que chama a lista de itens
        carregarBotaoQueChamaListaItens();

        //Carrega botão de pagar moeda de ouro
        carregarBotaoPagarMoedas();
    }

    private void carregaFaixaDeInformacaoDeQuantitativoDeitensFaltantes() {

        JLabelOpcoesTelaSecao faixaInfoQtdItensFaltam = new JLabelOpcoesTelaSecao(null,
                200,120,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        faixaInfoQtdItensFaltam.setBounds(340,560,200,120);
        faixaInfoQtdItensFaltam.setCursor(null);
        //faixaInfoQtdItensFaltam.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        lbFaixaInfoQtdItensFaltam = new JLabel("<html><center>Pagar<br>"+
                                                String.valueOf(qtdPagar5itens)+" item(ns)</center></html>");
        lbFaixaInfoQtdItensFaltam.setFont(new Font(Font.SERIF,Font.BOLD,22));
        lbFaixaInfoQtdItensFaltam.setForeground(new Color(139,0,0));
        lbFaixaInfoQtdItensFaltam.setHorizontalAlignment(SwingConstants.CENTER);
        lbFaixaInfoQtdItensFaltam.setBounds(365,580,150,80);
        lbFaixaInfoQtdItensFaltam.setCursor(null);
        //lbFaixaInfoQtdItensFaltam.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(lbFaixaInfoQtdItensFaltam);
        add(faixaInfoQtdItensFaltam);

    }

    private void carregarBotaoPagarMoedas() {

        //Botão
        botaoDarMoedas = new JLabelOpcoesTelaSecao("",250, 80,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoDarMoedas.setBounds(100, 581,250,80);
        botaoDarMoedas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pagarMoeda();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoDarMoedas.setIcon(Util.dimensionarImagem(botaoDarMoedas.getWidth(),
                        botaoDarMoedas.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoDarMoedas.setIcon(Util.dimensionarImagem(botaoDarMoedas.getWidth(),
                        botaoDarMoedas.getHeight(), ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });

        //Texto
        JLabel texto= new JLabel("<html><center>+1 Moeda</center></html>");
        texto.setBounds(180,606,90,25);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,18));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Pague 1 moeda para a mulher");
        //texto.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pagarMoeda();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoDarMoedas.setIcon(Util.dimensionarImagem(botaoDarMoedas.getWidth(),
                        botaoDarMoedas.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES_SELECIONADA.getEnderecoImagem()));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoDarMoedas.setIcon(Util.dimensionarImagem(botaoDarMoedas.getWidth(),
                        botaoDarMoedas.getHeight(),
                        ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem()));
                repaint();
            }
        });
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //textoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(texto);
        add(botaoDarMoedas);
    }

    private void pagarMoeda() {

        if (qtdPagar5itens == 0) {
            CarregarTelas.telaMensagem("Você já pagou.");
            return;
        }

        if (DadosLivroCarregado.getPersonagem().getQuantidadeOuro() == 0){
           CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                   ",\n\nVocê não possui peças de ouro para pagar.");
           return;
        }

        new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/moedas.mp3", null);
       UtilPersonagem.reduzirValorOuro(1);
        --qtdPagar5itens;
       atualizaInfoPagamento();
       atualizaIndicesNaTelaDoPersonagem();
    }

    private void carregarBotaoQueChamaListaItens() {

        //Botão
        botaoEscolhaItens = new JLabelOpcoesTelaSecao("",250, 80,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botaoEscolhaItens.setBounds(530, 581,250,80);

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
        JLabel texto= new JLabel("<html><center>Itens</center></html>");
        texto.setBounds(605,606,90,25);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,18));
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

                    if ( qtdPagar5itens == 0)
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ",\n\nVocê precisa pagar tudo o que a mulher "+
                                "bandida exigiu antes de continuar sua jornada.");
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
