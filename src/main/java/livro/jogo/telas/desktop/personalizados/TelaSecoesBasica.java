package livro.jogo.telas.desktop.personalizados;

import livro.jogo.acaosecoes.AcoesSecao_12;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.entidades.SaveJogo;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.util.ListItem;
import livro.jogo.telas.desktop.personalizados.util.ListaDeItensComImagem;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.util.HashMap;

public abstract class TelaSecoesBasica extends JDialog {
    private final Secao secao;
    private final Personagem personagem;
    private final TelaSecoesBasicaAcaoDosLabels acaoLabels = new TelaSecoesBasicaAcaoDosLabels();
    private Item pocaoInicial; //É a poção escolhida na criação do personagem
    private String enderecoImagem = DadosLivroCarregado.getLivro().getImagemComplementar();
    protected JFrame referenciaTelaPrincipal;
    private JLabelOpcoesTelaSecao labelMapaBotao;
    private JLabelOpcoesTelaSecao labelBolsa;
    private JLabelOpcoesTelaSecao labelPocaoInicial;
    private JLabelOpcoesTelaSecao labelProvisoes;
    private JLabelOpcoesTelaSecao labelAnotacoes;
    private JLabel labelSalvar;
    private JDialog dialogImagemMapa;
    private JDialog dialogImSecaoAmpliar;
    protected JLabel labelImagemSecao;
    private JLabelOpcoesTelaSecao labelSair;
    private JLabelOpcoesTelaSecao labelAumentaTexto;
    private JLabelOpcoesTelaSecao labelDiminuiTexto;
    private JLabelOpcoesTelaSecao labelVoz;
    private JLabelOpcoesTelaSecao labelVozParar;
    protected JTextPane textoHistoria;
    protected JLabel lbEnergiaPersonagem;         //Informa o índice de energia atual e máxima
    protected JLabel lbHabilidadePersonagem;      //Informa o índice de habilidade atual e máxima
    protected JLabel lbSortePersonagem;           //Informa o índice de sorte atual e máxima
    private int tamanhoTexto = 20; //tamanho default para o texto da seção. Pode ser ajustado
    private String enderecoAudioHistoriaInicial; //Se é a histório inicial. Carrega áudio da história inicial
    protected final Util util = new Util(); //Usado para a a narração (play /stop)
    protected final TelaSecoesBasica thisDialog = this; //Referencia esta tela para passar para a tela de mensaagem quando precisar fechar(em conjunto com respostaTelaMensagem)
    private static boolean respostaTelaMensagem = false; //Setado quando chamada a tela de confirmação e não é para fechar a tela
    protected JLabel labelOuro;
    protected JLabelOpcoesTelaSecao botaoOpcao1; //Primeira Opção da seção
    protected JLabelOpcoesTelaSecao botaoOpcao2; //Segunda Opção da seção
    protected JLabelOpcoesTelaSecao botaoOpcao3; //Terceira Opção da seção
    protected JLabelOpcoesTelaSecao botaoOpcao4; //Quarta Opção da seção
    protected JLabel lbTextoOpcao1;
    protected JLabel lbTextoOpcao2;
    protected JLabel lbTextoOpcao3;
    protected JLabel lbTextoOpcao4;
    protected JLabel labelNumOpcao1;
    protected JLabel labelNumOpcao2;
    protected JLabel labelNumOpcao3;
    protected JLabel labelNumOpcao4;
    protected final HashMap<String, Integer> listaNomeEIdDosItens = UtilBolsa.listarNomesItensNaBolsa(); //(Chave=nome do item; Valor = idItem)
    protected JPanel panelListaSuspensaItens; //Lista suspensa de itens da bolsa. Usada, por exmeplo, na seção 12
    protected JPanel panelListaItensEscolhidos; //Lista suspensa de itens escolhidos da bolsa. Usada, por exmeplo, na seção 12
    //Objeto que guarda a imagem do item
    protected HashMap<JLabelOpcoesTelaSecao, Item> mapItens = new HashMap<JLabelOpcoesTelaSecao, Item>();
    private JLabelOpcoesTelaSecao labelFundoMapa;
    private JLabelOpcoesTelaSecao labelFundoPocaoInicial;

    //Referente as seções (exemplo da seção 12) que precisa escolher 2 itens para descartar
    protected JLabelOpcoesTelaSecao imagemItemEscolhido1; //
    private JLabelOpcoesTelaSecao imagemItemEscolhido2; //Referente as seções (exemplo da seção 12) que precisa escolher 2 itens para descartar
    private int limiteDeEscolhaDeItens; //Limite de seleções no módulo de itens (usado, por exemplo, seção 12)
    private DefaultListModel<ListItem> listaItensParaEscolha; //Vai servir para a tela de escolha de itens
    protected boolean escolheuItensDaListaSuspensa;    //Informa se escolheu entregar 2 itens ao Gnomo
    private boolean pocaoInicialConsumido; //Informa se a poção inicial foi tomada
    private JLabelOpcoesTelaSecao botaoLabelFundoProvisoes;
    private JLabelOpcoesTelaSecao botaoLabelFundoAnotacoes;
    private JLabelOpcoesTelaSecao botaoLabelFundoSalvar;


    public TelaSecoesBasica(Secao secao) {
        setSize(1500,800);
        this.secao = secao;
        this.personagem = DadosLivroCarregado.getPersonagem();
        this.referenciaTelaPrincipal = CarregarTelas.getReferenciaTelaPrincipal();

        //Caso necessite alterar layout da tela para uma especifica. Necessário o Container
        Container tela = getContentPane();
        tela.setBackground(new Color(210,180,140));
        setLayout(null);

        //sendo secao = null significa que é a tela de história inicial do jogo ainda não é uma seção
        if ( (secao != null) && (secao.getEnderecoImagem() != null) ) {
            if ( !secao.getEnderecoImagem().isEmpty() )
                this.enderecoImagem = secao.getEnderecoImagem();

            setTitle("Seção - " + secao.getCodSecaoLivro());
        }

        //Seta o endereço da narração da história inicial
        if (secao == null){
            setTitle("Livro - " + DadosLivroCarregado.getLivro().getNome());
            enderecoAudioHistoriaInicial = DadosLivroCarregado.getLivro().getEnderecoAudio();
        }

        setType(Window.Type.UTILITY);

        setLocationRelativeTo(null);
        setModal(true);
        setUndecorated(true);

        //Carregar campo que receberá o texto da história
        carregarTextoHistoria();

        carregaImgSecao();
        carregaPainelPersonagem();
        carregarPainelDireito();
        carregarFaixasDasExtremidades();
        carregarComponentesEspecificos(secao);
        carregaPainelInferior();

        //para o áudio caso esteja sendo reproduzido
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent  e) {
                util.pararAudioMp3();
                if (referenciaTelaPrincipal != null)
                    referenciaTelaPrincipal.setVisible(true);
            }
        });
    }

    public Secao getSecao(){
        return this.secao;
    }

    //Limpa a lista de itens selecionados
    private void limparPanelEscolhaItensASeremDescartados(){

        //Limpar o hashmap com os itens selecionados
        mapItens.clear();

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

    //variável limiteDeEscolhaDeItens indica o limite de seleções que podem ser feitas
    //Exemplo é a seção 12 que podem ser escolhidos 2 itens para entregar ao Gnomo
    protected void carregaListaDeItensNaBolsaQuePodemSerEntregues(int posicaoX, int posicaoY,
                                                                  int largura, int altura,
                                                                  int limiteDeEscolhaDeItens) {
        //Pode limitar o número de itens selecionados
        this.limiteDeEscolhaDeItens = limiteDeEscolhaDeItens;

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
                //Se já escolhidos a limite de itens
                if (mapItens.size() == limiteDeEscolhaDeItens){
                    if (limiteDeEscolhaDeItens > 1)
                        CarregarTelas.telaMensagem("Os "+limiteDeEscolhaDeItens+
                            " itens já foram escolhidos.\n\n"+
                            "Se precisar, resete e faça novas escolhas.");
                    else
                        CarregarTelas.telaMensagem(limiteDeEscolhaDeItens+
                                " item já foi escolhido.\n\n"+
                                "Se precisar, resete e faça nova escolha.");

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

    private void confirmarEscolhaItens() {

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

    }

    //Em caso do personagem morto, fecha a tela de seção e abre a principal
    protected void personagemVivo(boolean vivo){

        if ( !vivo ) {
            if (referenciaTelaPrincipal != null)
                referenciaTelaPrincipal.setVisible(true);

            dispose();
        }
    }

    private void acoesEspecificasDasSecoes(){

        //Na secao 12 quando é removido os itens é devolvida a espada
        if (secao.getCodSecaoLivro() == 12) {
            AcoesSecao_12.recuperaEspadaDoGnomo();
            CarregarTelas.telaMensagem("Dívida paga.\n\nSua espada é devolvida!");
            botaoOpcao3.setEnabled(false);
        }

        if (secao.getCodSecaoLivro() == 14) {
            botaoOpcao2.setEnabled(false);
        }

        //Seção 271 é necessário pagar para obter informações do Gnomo
        if (secao.getCodSecaoLivro() == 271) {
            CarregarTelas.telaMensagem("Pagamento efetuado ao ganancioso Gnomo.");
        }

        //Ao descartar um item, ganha-se o lingote de ouro.
        if (secao.getCodSecaoLivro() == 242){
            new Util().reproduzirAudioMp3("livros/florestadadestruicao/audio/efeitos_sonoros/sorte.mp3", null);
            UtilBolsa.incluirItem(ItensMapeamento.LINGOTE_DE_OURO);
        }
    }

    //Inclui a imagem no panel de escolha suspensa e guarda em um hashmap
    private void incluirItemEscolhido(JLabelOpcoesTelaSecao imagemItem, JList<ListItem> jListItem,
                                      int espacoEntreItens) {
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
    }

    protected int ajusteLargura(int idItem) {
        int larguraPadrao = 70;

        switch (idItem){
            case  5: return larguraPadrao - 10; //Chave de prata
            case 10: return larguraPadrao + 10; //Espada Magnífica
            case 16: return larguraPadrao - 10; //Poção Controle dos Insetos
            case 25: return larguraPadrao - 20; //Cabo do Martelo de Guerra dos Anões
        }

        return larguraPadrao;
    }

    private void carregarFaixasDasExtremidades() {
        //FAIXA SUPERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaSuperiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_ESQUERDA);
        labelFaixaSuperiorEsquerda.setBounds(-115,-100,300,250);

        //FAIXA SUPERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaSuperiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_SUPERIOR_DIREITA);
        labelFaixaSuperiorDireita.setBounds(1310,-108,300,250);

        //FAIXA INFERIOR DIREITA
        JLabelOpcoesTelaSecao labelFaixaInferiorDireita = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_DIREITA);
        labelFaixaInferiorDireita.setBounds(1325,650,300,250);

        //FAIXA INFERIOR ESQUERDA
        JLabelOpcoesTelaSecao labelFaixaInferiorEsquerda = new JLabelOpcoesTelaSecao(null,
                300, 250,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INFERIOR_ESQUERDA);
        labelFaixaInferiorEsquerda.setBounds(-110,675,300,250);


        add(labelFaixaInferiorDireita);
        add(labelFaixaSuperiorDireita);
        add(labelFaixaSuperiorEsquerda);
        add(labelFaixaInferiorEsquerda);
    }

    protected abstract void carregarComponentesEspecificos(Secao secao);

    protected abstract void acaoBotoes(Secao secao);

    private void carregarTextoHistoria() {

        //Moldura que engloba o texto da seção
        JLabelOpcoesTelaSecao labelMolduraTexto = new JLabelOpcoesTelaSecao(null,900,600,
                ImagensDoLivroFlorestaDaDestruicao.MOLDURA_2);
        labelMolduraTexto.setCursor(null);
        //labelMolduraTexto.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Texto do livro
        textoHistoria = new JTextPane();
        textoHistoria.setBackground(new Color(210,180,140));
        textoHistoria.setFocusable(false);
        textoHistoria.setFont(new Font(Font.DIALOG,Font.BOLD,tamanhoTexto));

        StyledDocument textoLivroStyle = textoHistoria.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setForeground(configTexto,Color.BLACK);
        textoLivroStyle.setParagraphAttributes(0, textoLivroStyle.getLength(), configTexto, false);
        textoHistoria.setEditable(false);
        JScrollPane scrollTextoHistoria = new JScrollPane(textoHistoria);
        scrollTextoHistoria.setFocusable(true);
        scrollTextoHistoria.setBorder(null);

        //Carregando texto no componente
        if (secao != null)
            textoHistoria.setText( secao.getTexto() );
        else
            textoHistoria.setText( DadosLivroCarregado.getLivro().getHistoria() );

        //para posicionar a barra de rolagem no início.
        textoHistoria.setCaretPosition(0);

        //Aumenta texto
        labelAumentaTexto = new JLabelOpcoesTelaSecao(null, 40,40,
                ImagensDoLivroFlorestaDaDestruicao.SINAL_SOMA);
        labelAumentaTexto.setHorizontalAlignment(SwingConstants.CENTER);
        labelAumentaTexto.setToolTipText("Aumentar texto");
        labelAumentaTexto.addMouseListener(acaoLabels);
        //labelAumentaTexto.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Diminuir texto
        labelDiminuiTexto = new JLabelOpcoesTelaSecao(null, 30,30,
                ImagensDoLivroFlorestaDaDestruicao.SINAL_MENOS);
        labelDiminuiTexto.setHorizontalAlignment(SwingConstants.CENTER);
        labelDiminuiTexto.setToolTipText("Diminuir texto");
        labelDiminuiTexto.addMouseListener(acaoLabels);
        //labelDiminuiTexto.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Botão para narrar
        labelVoz = new JLabelOpcoesTelaSecao(null,45,45,
                ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ);
        labelVoz.addMouseListener(acaoLabels);
        labelVoz.setToolTipText("Narrador");

        //Botão para parar a narração
        labelVozParar = new JLabelOpcoesTelaSecao(null,45,45,
                ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ_PARAR);
        labelVozParar.addMouseListener(acaoLabels);
        labelVozParar.setToolTipText("Parar a narração");

        //posicionamento na tela
        scrollTextoHistoria.setBounds(115, 118, 650, 350);
        labelMolduraTexto.setBounds(-10,-40,950,650);
        labelAumentaTexto.setBounds(80,260,30,30);
        labelDiminuiTexto.setBounds(777,260,20,30);

        labelVoz.setBounds(315,510,45,45);
        labelVozParar.setBounds(520,510,45,45);

        //Adicionando a tela
        add(labelVoz);
        add(labelVozParar);
        add(labelAumentaTexto);
        add(labelDiminuiTexto);
        add(scrollTextoHistoria);
        add(labelMolduraTexto);

    }

    private void carregaPainelPersonagem() {
        //Dados do Personagem
        ImagePanel painelPersonagem;
        painelPersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MOLDURA);
        painelPersonagem.setLayout(null);

        //Nome do personagem
        JLabel lbNomePersonagem = new JLabel(personagem.getNome());
        lbNomePersonagem.setFont(new Font(Font.SERIF,Font.BOLD,25));
        lbNomePersonagem.setForeground(new Color(139,0,0));
        lbNomePersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        //lbNomePersonagem.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Faixa pergaminho no personagem
        ImagePanel imgPainelNomePersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FAIXA_NOME_PERSONAGEM_TELA_SECAO);
        imgPainelNomePersonagem.setLayout(null);
        ImagePanel imgPainelHabilidadePersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        imgPainelHabilidadePersonagem.setLayout(null);
        ImagePanel imgPainelEnergiaPersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        imgPainelEnergiaPersonagem.setLayout(null);
        ImagePanel imgPainelSortePersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        imgPainelSortePersonagem.setLayout(null);
        //imgPainelHabilidadePersonagem.setBorder(BorderFactory.createLineBorder(Color.RED));


        //Habilidade
        lbHabilidadePersonagem = new JLabel("Habilidade: "+
                String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                String.valueOf(personagem.getHabilidadeMax()));
        lbHabilidadePersonagem.setFont(new Font(Font.SERIF,Font.BOLD,16));
        lbHabilidadePersonagem.setForeground(new Color(139,0,0));
        lbHabilidadePersonagem.setHorizontalAlignment(SwingConstants.LEFT);
        lbHabilidadePersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbHabilidadePersonagem.setToolTipText("<html>Reflete a sua capacidade como espadachim e domínio geral das técnicas de luta.<br>Índice: (Atual/Máximo)</html>");

        //Energia
        lbEnergiaPersonagem = new JLabel("Energia: "+
                String.valueOf(personagem.getEnergiaAtual())+ "/"+
                String.valueOf(personagem.getEnergiaMax()));
        lbEnergiaPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,16));
        lbEnergiaPersonagem.setForeground(new Color(139,0,0));
        lbEnergiaPersonagem.setHorizontalAlignment(SwingConstants.LEFT);
        lbEnergiaPersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbEnergiaPersonagem.setToolTipText("<html>Reflete sua constituição física global, sua determinação de sobreviver, força de vontade e aptidão geral.<br>Índice: (Atual/Máximo)</html>");

        //Sorte
        lbSortePersonagem = new JLabel("Sorte: "+
                String.valueOf(personagem.getSorteAtual())+ "/"+
                String.valueOf(personagem.getSorteMax()));
        lbSortePersonagem.setFont(new Font(Font.SERIF,Font.BOLD,16));
        lbSortePersonagem.setForeground(new Color(139,0,0));
        lbSortePersonagem.setHorizontalAlignment(SwingConstants.LEFT);
        lbSortePersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbSortePersonagem.setToolTipText("<html>Seu índice SORTE indica o quanto você é, naturalmente, uma pessoa de sorte.<br>Índice: (Atual/Máximo)</html>");

        //Aqui define a imagem do Bárbaro(ou Bárara) do personagem
        //1 = Homem; 2 = Mulher
        String enderecoImgPersonagem;
        String toolTip;
        if (personagem.getGenero() == 1) {
            enderecoImgPersonagem = ImagensDoLivroFlorestaDaDestruicao.BARBARO.getEnderecoImagem();
            toolTip = "Este é você!";
        }
        else {
            enderecoImgPersonagem = ImagensDoLivroFlorestaDaDestruicao.BARBARA.getEnderecoImagem();
            toolTip = "Esta é você!";
        }

        ImagePanel imgPersonagem = new ImagePanel(enderecoImgPersonagem);
        imgPersonagem.setLayout(null);
        imgPersonagem.setToolTipText(toolTip);
        imgPersonagem.setCursor(null);
        //imgPersonagem.setBorder(BorderFactory.createLineBorder(Color.RED));


        //Bolsa
        labelBolsa = new JLabelOpcoesTelaSecao(null,ImagensDoLivroFlorestaDaDestruicao.BOLSA);
        labelBolsa.setToolTipText("Acesse aqui sua mochila.");
        labelBolsa.addMouseListener(acaoLabels);
        //labelBolsa.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelBolsa.setBounds(930,600,100,90);
        labelBolsa.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.BOLSA.getEnderecoImagem(),
                labelBolsa.getWidth(), labelBolsa.getHeight()).getImageIcon());


        //Posiciona
        imgPersonagem.setBounds(1080,500,205,260);
        lbSortePersonagem.setBounds(967,555,140,50);
        lbEnergiaPersonagem.setBounds(960,520,140,50);
        lbHabilidadePersonagem.setBounds(950,485,140,50);
        imgPainelNomePersonagem.setBounds(895,370,300,150);
        imgPainelHabilidadePersonagem.setBounds(910,490,200,40);
        imgPainelEnergiaPersonagem.setBounds(910,525,200,40);
        imgPainelSortePersonagem.setBounds(910,560,200,40);
        lbNomePersonagem.setBounds(900,415,300,50);
        painelPersonagem.setBounds(875, 367, 340, 375);

        //Adiciona a tela
        add(lbSortePersonagem);
        add(lbEnergiaPersonagem);
        add(lbHabilidadePersonagem);
        add(lbNomePersonagem);
        add(labelBolsa);
        add(imgPainelNomePersonagem);
        add(imgPainelSortePersonagem);
        add(imgPainelEnergiaPersonagem);
        add(imgPainelHabilidadePersonagem);
        add(imgPersonagem);
        add(painelPersonagem);
    }

    private void carregaImgSecao() {

        //Carrega imagem
        labelImagemSecao = new JLabel();
        labelImagemSecao.setHorizontalAlignment(SwingConstants.CENTER);
        ImagePanel imgMolduraParaImgSecao = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MOLDURA);
        imgMolduraParaImgSecao.setLayout(null);

        //posicionamento
        imgMolduraParaImgSecao.setBounds(875,2,340,375);
        labelImagemSecao.setBounds(915, 45, 261, 289);


        labelImagemSecao.setIcon(new RedimensionarImagem(enderecoImagem, labelImagemSecao.getWidth(),
                labelImagemSecao.getHeight()).getImageIcon());

        labelImagemSecao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelImagemSecao.setToolTipText("Ampliar");
        labelImagemSecao.addMouseListener(acaoLabels);

        //Configura clique na imagem para ampliar em uma nova tela
        JLabel labelImagemTempoaria = new JLabel();
        labelImagemTempoaria.setBounds(0, 0, 650,715);


        labelImagemTempoaria.setIcon(new RedimensionarImagem(enderecoImagem, labelImagemTempoaria.getWidth(),
                    labelImagemTempoaria.getHeight()).getImageIcon());

        labelImagemTempoaria.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dialogImSecaoAmpliar = carregaImagemEmUmaTela();
        dialogImSecaoAmpliar.add(labelImagemTempoaria);
        dialogImSecaoAmpliar.addMouseListener(acaoLabels);

        //Adiciona componentes na tela atual
        add(labelImagemSecao);
        add(imgMolduraParaImgSecao);
    }

    protected void carregaPainelInferior() {
        ImagePanel imgPainelInferior = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_ABERTO);
        imgPainelInferior.setLayout(null);
        imgPainelInferior.setBounds(1,505,900,290);
        //imgPainelInferior.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(imgPainelInferior);
    }

    private void carregarPainelDireito() {

        //Imagem de fundo (um pergaminho)
        ImagePanel imgPainelDireito = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_FAIXA);
        imgPainelDireito.setLayout(null);
        imgPainelDireito.setBounds(1200,2,280,770);

        //Quadra onde mostra a imagem padrão de uma floresta
        ImageIcon imageIcon = new ImageIcon(ImagensDoLivroFlorestaDaDestruicao.MAPA_DA_FLORESTA.getEnderecoImagem());
        JLabel labelImgMapa = new JLabel(imageIcon);
        labelImgMapa.setBounds(2,2,200,200);
        labelImgMapa.setBackground(new Color(210,180,140));
        dialogImagemMapa = carregaImagemEmUmaTela();
        dialogImagemMapa.add(labelImgMapa);
        dialogImagemMapa.addMouseListener(new TelaSecoesBasicaAcaoDosLabels());

        //Configura imagem do botão do Mapa
        configuraBotaoMapa();

        //Configura botão da poção inicial
        configuraBotaoPocaoInicial();

        //Provisões
        carregaBotaoDeProvisoes();

        //Anotações
        carregarBotaoAnotacoes();

        //Salvar
        carregarBotaoSalvar();

        //Ouro
        labelOuro = new JLabel("Ouro: " + personagem.getQuantidadeOuro());
        labelOuro.setFont(new Font(Font.SERIF,Font.BOLD,19));
        labelOuro.setForeground(new Color(139,0,0));
        labelOuro.setBounds(1302,72,85,55);
        JLabelOpcoesTelaSecao labelFundoOuro = new JLabelOpcoesTelaSecao(null,160,130,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelFundoOuro.setBounds(1260,40,160,130);
        labelFundoOuro.setHorizontalAlignment(SwingConstants.CENTER);


        labelSair = new JLabelOpcoesTelaSecao(null,130,110,
                ImagensDoLivroFlorestaDaDestruicao.PORTA_SAIR);
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.addMouseListener(acaoLabels);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelSair.setToolTipText("Sair");
        // labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelSair.setBounds(1272,690,130,110);


        //Adiciona a tela
        add(labelOuro);
        add(labelFundoOuro);
        add(labelSair);
        add(imgPainelDireito);
    }

    private void carregarBotaoSalvar() {
        //Salvar
        labelSalvar = new JLabel("Salvar");
        labelSalvar.setFont(new Font(Font.SERIF,Font.BOLD,20));
        labelSalvar.setForeground(new Color(139,0,0));
        labelSalvar.addMouseListener(acaoLabels);
        labelSalvar.setHorizontalAlignment(SwingConstants.CENTER);
        labelSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelSalvar.setBounds(1290,600,100,50);

        //Botão salvar
        botaoLabelFundoSalvar = new JLabelOpcoesTelaSecao(null,170,130,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoLabelFundoSalvar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoLabelFundoSalvar.setBounds(1255,565,170,130);
        botaoLabelFundoSalvar.addMouseListener(acaoLabels);

        add(labelSalvar);
        add(botaoLabelFundoSalvar);
    }

    //Carregar botão de anotações
    private void carregarBotaoAnotacoes() {
        labelAnotacoes = new JLabelOpcoesTelaSecao("Anotações",30,35,
                ImagensDoLivroFlorestaDaDestruicao.ANOTACOES);
        labelAnotacoes.setFont(new Font(Font.SERIF,Font.BOLD,20));
        labelAnotacoes.setBounds(1275,470,250,100);
        labelAnotacoes.addMouseListener(acaoLabels);

        //Botão anotações
        botaoLabelFundoAnotacoes = new JLabelOpcoesTelaSecao(null,220,130,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoLabelFundoAnotacoes.setBounds(1230,460,220,130);
        botaoLabelFundoAnotacoes.addMouseListener(acaoLabels);
        add(labelAnotacoes);
        add(botaoLabelFundoAnotacoes);
    }

    private void carregaBotaoDeProvisoes() {

        var textoProvisoes = "<html>Provisões: " + UtilItens.quantidadeProvisoesRestantes() + "</html>";
        labelProvisoes = new JLabelOpcoesTelaSecao(textoProvisoes,40,45,
                ImagensDoLivroFlorestaDaDestruicao.PROVISOES);
        labelProvisoes.setFont(new Font(Font.SERIF,Font.BOLD,18));
        labelProvisoes.setBounds(1265, 387,150,55);
        labelProvisoes.addMouseListener(acaoLabels);

        //Botão faixa
        botaoLabelFundoProvisoes = new JLabelOpcoesTelaSecao(null,220,130,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        botaoLabelFundoProvisoes.setBounds(1231,355,220,130);
        botaoLabelFundoProvisoes.addMouseListener(acaoLabels);

        add(labelProvisoes);
        add(botaoLabelFundoProvisoes);
    }

    //Configura poção inicial
    private void configuraBotaoPocaoInicial() {

        //Configura a poção inicial (na condição sendo '0' é porque já foi usada)
        pocaoInicial = Util.retornaPocaoInicialDaBolsa();

        if (pocaoInicial == null)
            configuraPocaoVaziaQuandoPocaoInicialConsumida();

        if ( (pocaoInicial != null) && (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem()) ) {
            labelPocaoInicial = new JLabelOpcoesTelaSecao("Habilidade", 30, 35, ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_HABILIDADE);
            labelPocaoInicial.setToolTipText("Repõe os pontos de HABILIDADE");
        }

        if ( (pocaoInicial != null) && (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DE_ENERGIA.getIdItem()) ) {
            labelPocaoInicial = new JLabelOpcoesTelaSecao("Força", 40, 45,
                    ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_ENERGIA);
            labelPocaoInicial.setToolTipText("Repõe os pontos de ENERGIA");
        }
        if ( (pocaoInicial != null) && (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DA_FORTUNA.getIdItem()) ) {
            labelPocaoInicial = new JLabelOpcoesTelaSecao("Fortuna", 30, 35,
                    ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_SORTE);
            labelPocaoInicial.setToolTipText("Repõe os pontos de SORTE e acrescenta 1 à SORTE Inicial");
        }

        if (pocaoInicial != null) {
            labelPocaoInicial.addMouseListener(acaoLabels);
            labelPocaoInicial.setFont(new Font(Font.SERIF,Font.BOLD,18));
            labelPocaoInicial.setBounds(1285,260,150,100);
        }

        //Botão faixa
        labelFundoPocaoInicial = new JLabelOpcoesTelaSecao(null,220,130,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelFundoPocaoInicial.setBounds(1230,250,220,130);
        labelFundoPocaoInicial.addMouseListener(acaoLabels);

        if (pocaoInicial != null)
            add(labelPocaoInicial);

        add(labelFundoPocaoInicial);
    }

    //Configura botão do mapa
    private void configuraBotaoMapa() {
        labelMapaBotao = new JLabelOpcoesTelaSecao("Mapa",50,55,
                ImagensDoLivroFlorestaDaDestruicao.BUSSOLA);
        labelMapaBotao.setBounds(1285,180,120,55);
        labelMapaBotao.setFont(new Font(Font.SERIF,Font.BOLD,19));

        //Botãso faixa
        labelFundoMapa = new JLabelOpcoesTelaSecao(null,180,130,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA);
        labelFundoMapa.setBounds(1250,145,180,130);

        //labelMapaBotao.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Significa que é a tela inicial e o personagem ainda não adquiriu o mapa
        if (secao == null)
            labelMapaBotao.setEnabled(false);
        else {
            labelMapaBotao.addMouseListener(acaoLabels);
            labelFundoMapa.addMouseListener(acaoLabels);
        }

        add(labelMapaBotao);
        add(labelFundoMapa);
    }

    //Substitui a imagem da poção inicial por um recipiente vazio
    private void configuraPocaoVaziaQuandoPocaoInicialConsumida(){
        String complementoTexto = ""; //Complemento do texto quando escolhida a poção da fortuna (sorte)
        boolean consumido = false;

        if (pocaoInicial == null) {
            labelPocaoInicial = new JLabelOpcoesTelaSecao("", 50, 55,
                    ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_VAZIA);
            labelPocaoInicial.setText("");
            labelPocaoInicial.setHorizontalAlignment(SwingConstants.CENTER);
            labelPocaoInicial.setBounds(1270,257,150,100);
            labelPocaoInicial.addMouseListener(null);
            labelPocaoInicial.setToolTipText("Poção consumida");
            add(labelPocaoInicial);
            return;
        }

        if (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DA_FORTUNA.getIdItem()){

            complementoTexto = " Além do incremento de 1 ponto no seu nível.";
            atualizaIndicesNaTelaDoPersonagem();
            consumido = true;

        }

        if (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DE_ENERGIA.getIdItem()){
            atualizaIndicesNaTelaDoPersonagem();
            consumido = true;
        }

        if (pocaoInicial.getIdItem() == ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem()){
            atualizaIndicesNaTelaDoPersonagem();
            consumido = true;
        }

        //Muda para recipiente vazio
        labelPocaoInicial.setIcon(Util.dimensionarImagem(50,55,
                ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_VAZIA.getEnderecoImagem()));
        labelPocaoInicial.setText("");
        labelPocaoInicial.setHorizontalAlignment(SwingConstants.CENTER);
        labelPocaoInicial.setBounds(1270,257,150,100);
        labelPocaoInicial.addMouseListener(null);
        labelPocaoInicial.setToolTipText("Poção consumida");


        CarregarTelas.telaMensagem(personagem.getNome().toUpperCase() +
                ",\nvocê toma a poção e se sente bem.\n\nSeu índice de " +
                pocaoInicial.getTipoEfeito().name().toLowerCase()
                + " encontra-se no nível máximo."+complementoTexto);
    }

    public void atualizaIndicesNaTelaDoPersonagem(){

        lbSortePersonagem.setText("Sorte: "+
                String.valueOf(personagem.getSorteAtual())+ "/"+
                String.valueOf(personagem.getSorteMax()));

        lbEnergiaPersonagem.setText("Energia: "+
                String.valueOf(personagem.getEnergiaAtual())+ "/"+
                String.valueOf(personagem.getEnergiaMax()));

        lbHabilidadePersonagem.setText("Habilidade: "+
                String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                String.valueOf(personagem.getHabilidadeMax()));

        labelOuro.setText("Ouro: " + personagem.getQuantidadeOuro());

        labelProvisoes.setText("<html>Provisões: " + UtilItens.quantidadeProvisoesRestantes() + "</html>");

        repaint();
    }

    //Carrega uma tela de diálogo com uma imagem
    private JDialog carregaImagemEmUmaTela(){
        JDialog dialogImagem = new JDialog(this,"",false);
        dialogImagem.setSize(new Dimension(650,715));
        dialogImagem.setLocationRelativeTo(this); //Centralizar baseado na tela que a chama
        dialogImagem.setResizable(false);
        dialogImagem.getContentPane().setBackground(new Color(210,180,140));
        dialogImagem.setUndecorated(true); //retira a barra de ferramentas.
        dialogImagem.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return dialogImagem;
    }

    public boolean isRespostaTelaMensagem() {
        return respostaTelaMensagem;
    }

    public void setRespostaTelaMensagem(boolean respostaTelaMensagem) {
        this.respostaTelaMensagem = respostaTelaMensagem;
    }

    //Chamado para abrir a próxima seção que foi escolhida como opção
    protected void abrirProximaSecao(int codSecao){
        util.pararAudioMp3();
        CarregarTelas.carregarSecao(DadosLivroCarregado.getLivro().getMapSecao().get(codSecao));
        this.dispose();
    }

    protected void opcao1(Secao secao){
        String texto = "1";            //Número da opção que aparecerá para o usuário (label)
        int indiceOpcao = 0;           //Para recuperar o texto da opção da seção (índice no array das próximas seções)

        labelNumOpcao1 = new JLabel(texto);
        labelNumOpcao1.setForeground(Color.WHITE);
        labelNumOpcao1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelNumOpcao1.setHorizontalAlignment(SwingConstants.CENTER);
        labelNumOpcao1.setFont(new Font(Font.SERIF,Font.BOLD,30));

        //label.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoOpcao1 = new JLabelOpcoesTelaSecao("",40,50,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1);
        botaoOpcao1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botaoOpcao1.setToolTipText("Clique para escolher esta opção.");
        //botaoOpcao1.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Texto da opção
        lbTextoOpcao1 = new JLabel(secao.getProximasSecoes().get(indiceOpcao).getTextoOpcao());
        lbTextoOpcao1.setFont(new Font(Font.SERIF,Font.BOLD,22));
        lbTextoOpcao1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbTextoOpcao1.setForeground(new Color(139,0,0));

        labelNumOpcao1.setBounds(116,592, 50,50);
        botaoOpcao1.setBounds(120,600,40,50);
        lbTextoOpcao1.setBounds(170,587,700,60);

        add(lbTextoOpcao1);
        add(labelNumOpcao1);
        add(botaoOpcao1);
    }

    protected void opcao2(Secao secao){
        String texto = "2";             //Número da opção que aparecerá para o usuário (label)
        int indiceOpcao = 1;            //Para recuperar o texto da opção da seção (índice no array das próximas seções)

        labelNumOpcao2 = new JLabel(texto);
        labelNumOpcao2.setForeground(Color.WHITE);
        labelNumOpcao2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelNumOpcao2.setHorizontalAlignment(SwingConstants.CENTER);
        labelNumOpcao2.setFont(new Font(Font.SERIF,Font.BOLD,30));
        //label.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoOpcao2 = new JLabelOpcoesTelaSecao("",40,50,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1);
        botaoOpcao2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoOpcao2.setToolTipText("Clique para escolher esta opção.");

        //Texto da opção
        lbTextoOpcao2 = new JLabel(secao.getProximasSecoes().get(indiceOpcao).getTextoOpcao());
        lbTextoOpcao2.setFont(new Font(Font.SERIF,Font.BOLD,22));
        lbTextoOpcao2.setForeground(new Color(139,0,0));
        //lbTexto.setBorder(BorderFactory.createLineBorder(Color.RED));

        labelNumOpcao2.setBounds(116,652, 50,50);
        botaoOpcao2.setBounds(120,660,40,50);
        lbTextoOpcao2.setBounds(170,647,700,60);

        add(lbTextoOpcao2);
        add(labelNumOpcao2);
        add(botaoOpcao2);
    }

    protected void opcao3(Secao secao){
        String texto = "3";             //Número da opção que aparecerá para o usuário (label)
        int indiceOpcao = 2;            //Para recuperar o texto da opção da seção (índice no array das próximas seções)

        labelNumOpcao3 = new JLabel(texto);
        labelNumOpcao3.setForeground(Color.WHITE);
        labelNumOpcao3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelNumOpcao3.setHorizontalAlignment(SwingConstants.CENTER);
        labelNumOpcao3.setFont(new Font(Font.SERIF,Font.BOLD,30));
        //label.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoOpcao3 = new JLabelOpcoesTelaSecao("",40,50,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1);
        botaoOpcao3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoOpcao3.setToolTipText("Clique para escolher esta opção.");

        //Texto da opção
        lbTextoOpcao3 = new JLabel(secao.getProximasSecoes().get(indiceOpcao).getTextoOpcao());
        lbTextoOpcao3.setFont(new Font(Font.SERIF,Font.BOLD,22));
        lbTextoOpcao3.setForeground(new Color(139,0,0));
        //lbTexto.setBorder(BorderFactory.createLineBorder(Color.RED));

        labelNumOpcao3.setBounds(116,712, 50,50);
        botaoOpcao3.setBounds(120,720,40,50);
        lbTextoOpcao3.setBounds(170,707,700,60);


        add(lbTextoOpcao3);
        add(labelNumOpcao3);
        add(botaoOpcao3);
    }

    protected void opcao4(Secao secao){
        String texto = "4";             //Número da opção que aparecerá para o usuário (label)
        int indiceOpcao = 3;            //Para recuperar o texto da opção da seção (índice no array das próximas seções)

        labelNumOpcao4 = new JLabel(texto);
        labelNumOpcao4.setForeground(Color.WHITE);
        labelNumOpcao4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelNumOpcao4.setHorizontalAlignment(SwingConstants.CENTER);
        labelNumOpcao4.setFont(new Font(Font.SERIF,Font.BOLD,30));
        //label.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoOpcao4 = new JLabelOpcoesTelaSecao("",40,50,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1);
        botaoOpcao4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoOpcao4.setToolTipText("Clique para escolher esta opção.");

        //Texto da opção
        lbTextoOpcao4 = new JLabel(secao.getProximasSecoes().get(indiceOpcao).getTextoOpcao());
        lbTextoOpcao4.setBounds(170,707,700,60);
        lbTextoOpcao4.setFont(new Font(Font.SERIF,Font.BOLD,22));
        lbTextoOpcao4.setForeground(new Color(139,0,0));
        //lbTexto.setBorder(BorderFactory.createLineBorder(Color.RED));

        labelNumOpcao4.setBounds(116,772, 50,50);
        botaoOpcao4.setBounds(120,780,40,50);
        lbTextoOpcao4.setBounds(170,767,700,60);

        add(lbTextoOpcao4);
        add(labelNumOpcao4);
        add(botaoOpcao4);
    }

    private class TelaSecoesBasicaAcaoDosLabels implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == labelVoz){

                if (secao.getEnderecoAudio() == null)
                    return;

                //Não deixar ativar o som mais de uma vez
                if ( !labelVoz.isEnabled() )
                    return;

                if ( ( (enderecoAudioHistoriaInicial == null) ||
                        (enderecoAudioHistoriaInicial.isEmpty()) ) && (secao != null) )
                    util.reproduzirAudioMp3(secao.getEnderecoAudio(), labelVoz);
                else
                    util.reproduzirAudioMp3(enderecoAudioHistoriaInicial, labelVoz);

                labelVoz.setEnabled(false);
            }

            if (e.getSource() == labelVozParar){
                util.pararAudioMp3();
                labelVoz.setEnabled(true);
            }

            if (e.getSource() == labelAumentaTexto){
                ++tamanhoTexto;
                textoHistoria.setFont(new Font(Font.DIALOG,Font.BOLD,tamanhoTexto));
            }

            if (e.getSource() == labelDiminuiTexto){
                --tamanhoTexto;
                textoHistoria.setFont(new Font(Font.DIALOG,Font.BOLD,tamanhoTexto));
            }

            if (e.getSource() == labelSair){

                CarregarTelas.telaMensagem("Deseja realmente sair?", thisDialog);
                if (isRespostaTelaMensagem()) {
                    util.pararAudioMp3();
                    if (referenciaTelaPrincipal != null)
                        referenciaTelaPrincipal.setVisible(true);

                    dispose();
                }
            }

            if ( (e.getSource() == labelMapaBotao) || (e.getSource() == labelFundoMapa) ){
                dialogImagemMapa.setVisible(true);
            }

            if (e.getSource() ==  labelImagemSecao){
                dialogImSecaoAmpliar.setVisible(true);
            }

            if (e.getSource() == labelBolsa){
                if (DadosLivroCarregado.getPersonagem().getEnergiaAtual() > 0)
                    CarregarTelas.telaBolsa(getContentPane(),1150,800, lbEnergiaPersonagem, lbHabilidadePersonagem,
                        lbSortePersonagem, labelProvisoes, labelPocaoInicial,secao);
            }

            if ( (e.getSource() == labelPocaoInicial) || (e.getSource() == labelFundoPocaoInicial) ){

                if (DadosLivroCarregado.getPersonagem().getEnergiaAtual() <= 0)
                    return;


                //Caso consumida via bolsa essa variável fica ativa no botão. Então verifica se ainda contém na bolsa.
                pocaoInicial = Util.retornaPocaoInicialDaBolsa();

                if ( ( pocaoInicial == null ) || ( pocaoInicialConsumido ) ){
                    CarregarTelas.telaMensagem(personagem.getNome().toUpperCase() +
                            ",\nvocê já tomou a poção especial.");
                    return;
                }

                //Questionar se de fato quer usar a poção
                CarregarTelas.telaMensagem("Deseja realmente tomar a poção?",thisDialog);
                if ( !respostaTelaMensagem )
                    return;

                pocaoInicialConsumido = new EfeitoDeItens(secao).acoesDosItens(pocaoInicial.getIdItem());

                if ( pocaoInicialConsumido ) {
                    configuraPocaoVaziaQuandoPocaoInicialConsumida();
                    pocaoInicial = null;
                }
                else
                    CarregarTelas.telaMensagem(personagem.getNome().toUpperCase()+
                            ", seu índice de "+pocaoInicial.getTipoEfeito().name().toLowerCase()+
                            " encontra-se no nível máximo."+
                            "\n\nNão existe necessidade de tomar a poção.");
            }

            if ( (e.getSource() ==  labelProvisoes) || ((e.getSource() ==  botaoLabelFundoProvisoes)) ){

                if (DadosLivroCarregado.getPersonagem().getEnergiaAtual() <= 0)
                    return;

                //Testa se personagem encontra-se com energia cheia e o avisa.
                if (UtilPersonagem.retornaDiferencaEntreEnergiaMaxEAtual() == 0){
                    CarregarTelas.telaMensagem(personagem.getNome().toUpperCase()+", sua energia está completa."+
                            "\n\nNão existe necessidade de se alimentar.");
                    return;
                }

                //Questionar se de fato quer usar a poção
                CarregarTelas.telaMensagem("Você realmente quer comer uma provisão?",thisDialog);
                if ( !respostaTelaMensagem )
                    return;

                //Testa se ainda existem provisões para comer
                if (UtilItens.quantidadeProvisoesRestantes() > 0) {

                    //Aqui trata a ação de comer a provisão
                    //Item provisão
                    if  (new EfeitoDeItens(secao).acoesDosItens(ItensMapeamento.PROVISAO.getIdItem()) ) {
                        lbEnergiaPersonagem.setText("Energia: " +
                                String.valueOf(personagem.getEnergiaAtual()) + "/" +
                                String.valueOf(personagem.getEnergiaMax()));
                        labelProvisoes.setText("<html>Provisões: " + UtilItens.quantidadeProvisoesRestantes() + "</html>");
                        CarregarTelas.telaMensagem(personagem.getNome() + ", você recuperou 4 pontos de energia ao comer uma provisão(refeição).");
                    }
                }
                else
                    CarregarTelas.telaMensagem("Você não tem mais provisões para comer.");
            }

            if ( (e.getSource() ==  labelAnotacoes) || (e.getSource() ==  botaoLabelFundoAnotacoes) ){
                CarregarTelas.telaAnotacoes(personagem);
            }

            if ( (e.getSource() ==  labelSalvar) || (e.getSource() == botaoLabelFundoSalvar) ){
                CarregarTelas.telaMensagem("Deseja salvar o andamento do jogo?",thisDialog);

                //Se resposta da tela telaMensagem positiva, salva o jogo
                if (isRespostaTelaMensagem())
                    Util.salvarJogoEmArquivo(personagem.getNome(),new SaveJogo(personagem,secao));
            }

            if (e.getSource() ==  dialogImagemMapa){
                dialogImagemMapa.setVisible(false);
                dialogImagemMapa.dispose();
            }

            if (e.getSource() ==  dialogImSecaoAmpliar){
                dialogImSecaoAmpliar.setVisible(false);
                dialogImSecaoAmpliar.dispose();
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
            if (e.getSource() == labelVoz){
                labelVoz.setIcon(Util.dimensionarImagem(labelVoz.getWidth(),
                        labelVoz.getHeight(), ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ_SELECIONADO.getEnderecoImagem()));
                repaint();
            }

            if (e.getSource() == labelVozParar){
                labelVozParar.setIcon(Util.dimensionarImagem(labelVozParar.getWidth(),
                        labelVozParar.getHeight(), ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ_PARAR_SELECIONADO.getEnderecoImagem()));
                repaint();
            }

            if (e.getSource() == labelSair){
                labelSair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.PORTA_SAIR_SELECIONADA.getEnderecoImagem(),
                        labelSair.getWidth(), labelSair.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == labelBolsa){
                labelBolsa.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.BOLSA_SELECIONADA.getEnderecoImagem(),
                        labelBolsa.getWidth(), labelBolsa.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() == labelMapaBotao) || (e.getSource() == labelFundoMapa) ){
                labelFundoMapa.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        labelFundoMapa.getWidth(), labelFundoMapa.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() == labelPocaoInicial) || (e.getSource() == labelFundoPocaoInicial) ){
                labelFundoPocaoInicial.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        labelFundoPocaoInicial.getWidth(), labelFundoPocaoInicial.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() ==  labelProvisoes) || ((e.getSource() ==  botaoLabelFundoProvisoes)) ){
                botaoLabelFundoProvisoes.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        botaoLabelFundoProvisoes.getWidth(), botaoLabelFundoProvisoes.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() ==  labelAnotacoes) || (e.getSource() ==  botaoLabelFundoAnotacoes) ){
                botaoLabelFundoAnotacoes.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        botaoLabelFundoAnotacoes.getWidth(), botaoLabelFundoAnotacoes.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() ==  labelSalvar) || (e.getSource() == botaoLabelFundoSalvar) ){
                botaoLabelFundoSalvar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_SELECIONADA.getEnderecoImagem(),
                        botaoLabelFundoSalvar.getWidth(), botaoLabelFundoSalvar.getHeight()).getImageIcon());
                repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == labelVoz){
                labelVoz.setIcon(Util.dimensionarImagem(labelVoz.getWidth(),
                        labelVoz.getHeight(), ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ.getEnderecoImagem()));
                repaint();
            }

            if (e.getSource() == labelVozParar){
                labelVozParar.setIcon(Util.dimensionarImagem(labelVozParar.getWidth(),
                        labelVozParar.getHeight(), ImagensDoLivroFlorestaDaDestruicao.SIMBOLO_VOZ_PARAR.getEnderecoImagem()));
                repaint();
            }

            if (e.getSource() == labelSair){
                labelSair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.PORTA_SAIR.getEnderecoImagem(),
                        labelSair.getWidth(), labelSair.getHeight()).getImageIcon());
                repaint();
            }

            if (e.getSource() == labelBolsa){
                labelBolsa.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.BOLSA.getEnderecoImagem(),
                        labelBolsa.getWidth(), labelBolsa.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() == labelMapaBotao) || (e.getSource() == labelFundoMapa) ){
                labelFundoMapa.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        labelFundoMapa.getWidth(), labelFundoMapa.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() == labelPocaoInicial) || (e.getSource() == labelFundoPocaoInicial) ){
                labelFundoPocaoInicial.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        labelFundoPocaoInicial.getWidth(), labelFundoPocaoInicial.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() ==  labelProvisoes) || ((e.getSource() ==  botaoLabelFundoProvisoes)) ){
                botaoLabelFundoProvisoes.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        botaoLabelFundoProvisoes.getWidth(), botaoLabelFundoProvisoes.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() ==  labelAnotacoes) || (e.getSource() ==  botaoLabelFundoAnotacoes) ){
                botaoLabelFundoAnotacoes.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        botaoLabelFundoAnotacoes.getWidth(), botaoLabelFundoAnotacoes.getHeight()).getImageIcon());
                repaint();
            }

            if ( (e.getSource() ==  labelSalvar) || (e.getSource() == botaoLabelFundoSalvar) ){
                botaoLabelFundoSalvar.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA.getEnderecoImagem(),
                        botaoLabelFundoSalvar.getWidth(), botaoLabelFundoSalvar.getHeight()).getImageIcon());
                repaint();
            }

        }
    }

}