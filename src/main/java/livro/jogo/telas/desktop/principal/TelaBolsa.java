package livro.jogo.telas.desktop.principal;

import livro.jogo.acaosecoes.AcoesSecao_16;
import livro.jogo.entidades.Item;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.ImagePanel;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.telas.desktop.secoes.TelaSecao_304;
import livro.jogo.utils.*;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TelaBolsa extends JDialog {

    //Objeto que guarda a imagem do item
    private HashMap<JLabelOpcoesTelaSecao, Item> mapItens = new HashMap<JLabelOpcoesTelaSecao, Item>();

    //Vai guardar o label("Equipado") quando equipado vou precisar destacar como texto no objeto da imagem que represanta o item
    //Futuramente, pense nisso. Agora equipa e fecha a tela sem precisar sair...
    //private HashMap<JLabelOpcoesTelaSecao,JLabel> mapLabel = new HashMap<JLabelOpcoesTelaSecao,JLabel>();

    private JLabelOpcoesTelaSecao botaoSair;
    private JLabel lbEnergiaPersonagem;
    private JLabel lbHabilidadePersonagem; //Vai ser atualizado com ações de itens a tela de secoes
    private JLabel lbSortePersonagem; //Vai ser atualizado com ações de itens a tela de secoes
    private JLabelOpcoesTelaSecao botaoProvisoes; //Vai ser atualizado com ações de itens a tela de secoes
    private JLabelOpcoesTelaSecao labelPocaoInicial; //Poção inicial
    private Container container;
    private Secao secao;
    private ImagePanel painelListaItens; //Painel onde irão ficar todos os itens na bolsa ou equipados. São as imagens dos itens. Os quais o jogador irá interagir
    private TelaBolsaListener acao = new TelaBolsaListener(); //Configurar ouvinte do click do mouse quando clicar nos itens
    private JTextPane descricaoItem; //Na tela suspensa de informação
    private JPanel panelInfoItem; //Representa a tela suspensa de informação do item
    private final int LARGURA_IMG = 220, ALTURA_IMG = 250; //Tamanho da imagem da tela suspensa de info dos itens
    private JLabel tituloTelaSuspensaInfo;
    private int largura, altura;


    public TelaBolsa(Container container,int largura, int altura, JLabel lbEnergiaPersonagem,
                     JLabel lbHabilidadePersonagem, JLabel lbSortePersonagem,
                     JLabelOpcoesTelaSecao botaoProvisoes, JLabelOpcoesTelaSecao labelPocaoInicial, Secao secao) {
        this.lbEnergiaPersonagem = lbEnergiaPersonagem;
        this.botaoProvisoes = botaoProvisoes;
        this.labelPocaoInicial = labelPocaoInicial;
        this.lbSortePersonagem = lbSortePersonagem;
        this.lbHabilidadePersonagem = lbHabilidadePersonagem;
        this.container = container;
        this.secao = secao;
        this.largura = largura;
        this.altura = altura;
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setSize(largura,altura);
        setLayout(null);
        setModal(true);
        setLocationRelativeTo(null);
        carregarTela();
    }

    public void carregarTela(){

        //fundo
        ImagePanel painelImgFundoBolsa = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FUNDO_BOLSA);
        painelImgFundoBolsa.setBackground(new Color(210,180,140));
        painelImgFundoBolsa.setForeground(new Color(139,0,0));
        painelImgFundoBolsa.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        painelImgFundoBolsa.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ///Painel onde irão ficar todos os itens na bolsa ou equipados.
        ///São as imagens dos itens. Os quais o jogador irá interagir
        painelListaItens = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.FUNDO_BOLSA_LISTA);
        painelListaItens.setBackground(new Color(210,180,140));
        painelListaItens.setForeground(new Color(139,0,0));
        painelListaItens.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        painelListaItens.setCursor(null);
        painelListaItens.setLayout(null);
        painelListaItens.setBounds(220,80,largura-450, altura-160);
        painelImgFundoBolsa.setBounds(0,0,largura,altura);
        painelImgFundoBolsa.add(painelListaItens);
        //painelListaItens.setBorder(BorderFactory.createLineBorder(Color.RED));


        //Botão sair
        JLabel labelSair = new JLabel("Sair");
        labelSair.setBounds(565,500,100,50);
        labelSair.setForeground(new Color(139,0,0));
        labelSair.setFont(new Font(Font.SERIF,Font.BOLD,25));
        labelSair.setHorizontalAlignment(SwingConstants.CENTER);
        labelSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //labelSair.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoSair = new JLabelOpcoesTelaSecao(null,
                220, 90,ImagensDoLivroFlorestaDaDestruicao.FAIXA_3);
        botaoSair.setBounds(505,485,220,90);
        botaoSair.setHorizontalAlignment(SwingConstants.CENTER);
        //botaoSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        botaoSair.addMouseListener(acao);

        //Carregar tela de informações dos itens
        telaInfoItem();

        //adicionar na tela os componentes
        add(labelSair);
        add(botaoSair);
        add(painelListaItens);
        add(painelImgFundoBolsa);

        //Recupera todos os itens da bolsa ou equipados e os mostra.
        atualizarBolsa();
    }

    /// Usado quando incluir clicar em um item para equipar ou consumir.
    ///O primeiro coloca a tarja "Equipado" o outro some.
    private void atualizarBolsa(){
        JLabelOpcoesTelaSecao imgItem;

        //Retira todas as imagens do painel
        painelListaItens.removeAll();

        //Apaga todos os itens do hashMap
        for (Map.Entry<JLabelOpcoesTelaSecao, Item> map : mapItens.entrySet()) {
            imgItem = map.getKey();
            imgItem = null;
        }
        //Limpa o hashmap
        mapItens.clear();

        //Recria a bolsa
        carregaItensNaBolsaEEquipados(acao, painelListaItens);

        //Esconde a tela de informação do item suspensa
        panelInfoItem.setVisible(false);

        repaint();
    }

    private void carregaItensNaBolsaEEquipados(TelaBolsaListener acao, ImagePanel painelListaItens) {
        //Carregar itens da bolsa e os equipados
        ArrayList<Item> bolsa = DadosLivroCarregado.getBolsa();
        ArrayList<Item> itensEquipados = DadosLivroCarregado.getItensEquipados();

        var x = 60; //Posição da esquerda para a direita
        var y = 50;  //Posição de cima para baixo
        var largura = 40;
        var altura = 40;
        var contNumerodeItensPorLinha = 0; //Vai auxiliar nos itens por linha, no caso estipulei 12 itens por linha

        //Para itens na bolsa (Não equipados)
        for (Item item: bolsa) {

            if (contNumerodeItensPorLinha == 11){
                contNumerodeItensPorLinha = 0;
                y = y + 50; //Posiciona os próximos itens logo abaixo
                x = 60; //Volta para o início da (esquerda para a direita)
            }

            //Inserindo os itens na tela
            JLabelOpcoesTelaSecao imgItem;

            //Se cabeça ou cabo do machado do anões, aumentar a altura e largura
            if ( item.getIdItem() == 25 ) {
                imgItem = new JLabelOpcoesTelaSecao("", largura, altura + 15,
                        item.getEnderecoImagem());
                imgItem.setBounds(x,y,largura,altura+15);

             //Sendo a seção 192 a espada equipada transforma-se em cenoura
            }
            else {
                imgItem = new JLabelOpcoesTelaSecao("", largura, altura,
                        item.getEnderecoImagem());
                imgItem.setBounds(x,y,largura,altura);
            }

            imgItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            imgItem.addMouseListener(acao);

            //Coloco o objeto no map para posterior consulta ao clicar nele
            mapItens.put(imgItem, item);

            painelListaItens.add(imgItem);
            ++contNumerodeItensPorLinha;
            //imgInterrogacao.setBorder(BorderFactory.createLineBorder(Color.RED));

            //Caminhando da esquerda para a direita
            x = x + 60;
        }

        for (Item item: itensEquipados) {

            if (item.getEnderecoImagem().isEmpty())
                continue;

            if (contNumerodeItensPorLinha == 11){
                contNumerodeItensPorLinha = 0;
                y = y + 50; //Posiciona os próximos itens logo abaixo
                x = 60; //Volta para o início da (esquerda para a direita)
            }

            if (secao != null) {

                ///Seção 192 - a espada se transforma em uma cenoura.
                if ((secao.getCodSecaoLivro() == 192) &&
                        ((item.getIdItem() == 10) || (item.getIdItem() == 50)))
                    item = DadosLivroCarregado.recuperaItemDoJson(ItensMapeamento.CENOURA.getEnderecoJson());

                ///Seção 45 - O personagem joga a cenoura no gnomo, então ela não está na bolsa
                if ((secao.getCodSecaoLivro() == 46) &&
                        ((item.getIdItem() == 10) || (item.getIdItem() == 50)))
                    continue;
            }
            //Criar imagem
            JLabelOpcoesTelaSecao imgItem = new JLabelOpcoesTelaSecao("", largura, altura,
                        ImagensDoLivroFlorestaDaDestruicao.CENOURA.getEnderecoImagem());
            imgItem.setBounds(x,y,largura,altura);
            imgItem = new JLabelOpcoesTelaSecao("", largura, altura, item.getEnderecoImagem());
            imgItem.setBounds(x, y, largura, altura);
            imgItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            imgItem.addMouseListener(acao);

            //Rótulo indicando que este item está equipado
            JLabel rotuloEquipado = new JLabel("Equipado");
            rotuloEquipado.setForeground(new Color(220,220,220));
            rotuloEquipado.setFont(new Font(Font.SERIF,Font.PLAIN,14));
            rotuloEquipado.setBounds(x+5,y+40,75,18);

            //Incluir item no hashmap para que possa ser identificado quando clicado na imagem
            mapItens.put(imgItem, item);

            //Incluir no panel
            painelListaItens.add(rotuloEquipado);
            painelListaItens.add(imgItem);

            //Incrementado contador de modo a que só fiquem 5 itens por linha
            ++contNumerodeItensPorLinha;

            //Caminhando da esquerda para a direita
            x = x + 60;
        }
    }

    //Carrega tela suspensa com informação do item
    private void telaInfoItem(){

        panelInfoItem = new JPanel();
        panelInfoItem.setLayout(null);
        panelInfoItem.setBackground(new Color(0,0,0,0));
        panelInfoItem.setBounds(0,0,LARGURA_IMG, ALTURA_IMG);
        panelInfoItem.setVisible(false);

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
        add(panelInfoItem);
    }

    private void configuraTelaSuspensaInfoItem(JLabelOpcoesTelaSecao imgLabel) {

        Item item = mapItens.get(imgLabel);
        panelInfoItem.setBounds(imgLabel.getX()+240,imgLabel.getY(),LARGURA_IMG, ALTURA_IMG);
        panelInfoItem.setVisible(true);
        descricaoItem.setText( item.getDescricao() );
        tituloTelaSuspensaInfo.setText( item.getNome() );

    }

    //Quando clicado no botão de poção inicial, muda a imagem para uma garrafa vazia
    private void mudarBotaoPocaoInicialParaVazio(){
        labelPocaoInicial.setIcon(Util.dimensionarImagem(50,55,
                ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_VAZIA.getEnderecoImagem()));
        labelPocaoInicial.setText("");
        labelPocaoInicial.setHorizontalAlignment(SwingConstants.CENTER);
        labelPocaoInicial.setBounds(1270,260,150,100);
        labelPocaoInicial.addMouseListener(null);
        labelPocaoInicial.setToolTipText("Poção consumida");
    }

    //FUTURAMENTE MELHORAR ESTA FUNÇÃO, POIS PODERIA ATUALIZAR OS ÍNDICE COM APENAS UMA FUNÇÃO CHAMADA
    private void atualizarCamposTelaSecao(JLabelOpcoesTelaSecao imgLabel, Item item,
                                          boolean consumiuItem) {

        ///Poção Antiveneno(3)
        if (item.getIdItem() == ItensMapeamento.POCAO_ANTIVENENO.getIdItem()){


            //Cada seção que a poção antiveneno é necessária
            if (secao != null) {
                switch (secao.getCodSecaoLivro()) {
                    case 16 -> consumiuItem = AcoesSecao_16.isConsumiuPocaoAntiveneno();
                }
            }

            if ( !consumiuItem )
                CarregarTelas.telaMensagem("Não é necessário tomar a Poção Antiveneno.");

        }

        ///Poção de Habilidade(45)
        if (item.getIdItem() == ItensMapeamento.POCAO_DE_HABILIDADE.getIdItem()) {

            //Retornando falso o personagem está com índice completo e portanto não consume o item
            if ( !consumiuItem ){
                CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()
                        .toUpperCase()+", seu índice de habilidade está completo.\n\nNão existe necessidade de tomar a poção.");
                return;
            }

            //Muda garrafa para vazia
            mudarBotaoPocaoInicialParaVazio();

            //Muda informação do índice do personagem
            lbHabilidadePersonagem.setText("Habilidade: "+
                    String.valueOf(DadosLivroCarregado.getPersonagem().getHabilidadeAtual())+ "/"+
                    String.valueOf(DadosLivroCarregado.getPersonagem().getHabilidadeMax()));

            //Destrói o objeto
            imgLabel.setVisible(false);

            //Mensagem
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome().toUpperCase() +
                    ", você toma a poção e se sente bem.\n\nSeu índice de habilidade" +
                    " encontra-se no nível máximo.");
        }

        ///Poção da Força(energia - 46)
        if (item.getIdItem() == ItensMapeamento.POCAO_DE_ENERGIA.getIdItem()) {

            //Retornando falso o personagem está com índice completo e portanto não consume o item
            if ( !consumiuItem ){
                CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()
                        .toUpperCase()+", seu índice de energia está completo.\n\nNão existe necessidade de tomar a poção.");
                return;
            }

            //Muda garrafa para vazia
            mudarBotaoPocaoInicialParaVazio();

            //Muda informação do índice do personagem
            lbEnergiaPersonagem.setText("Energia: "+
                    String.valueOf(DadosLivroCarregado.getPersonagem().getEnergiaAtual())+ "/"+
                    String.valueOf(DadosLivroCarregado.getPersonagem().getEnergiaMax()));

            //Destrói o objeto
            imgLabel.setVisible(false);

            //Mensagem
            CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome().toUpperCase() +
                    ", você toma a poção e se sente bem.\n\nSeu índice de energia" +
                    " encontra-se no nível máximo.");
        }

        ///Poção da Fortuna(47)
        if (item.getIdItem() == ItensMapeamento.POCAO_DA_FORTUNA.getIdItem()) {

            //Retornando falso o personagem está com índice completo e portanto não consume o item
            if ( !consumiuItem ){
               CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()
                  .toUpperCase()+", seu índice de sorte está completo.\n\nNão existe necessidade de tomar a poção.");
               return;
            }

            //Muda garrafa para vazia
            mudarBotaoPocaoInicialParaVazio();

            //Atualiza informação do índice do personagem
            lbSortePersonagem.setText("Sorte: "+
                    String.valueOf(DadosLivroCarregado.getPersonagem().getSorteAtual())+ "/"+
                    String.valueOf(DadosLivroCarregado.getPersonagem().getSorteMax()));

           //Destrói o objeto
           imgLabel.setVisible(false);

           //Mensagem
           CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome().toUpperCase() +
                        ", você toma a poção e se sente bem.\n\nSeu índice de sorte" +
                        " encontra-se no nível máximo. Além do incremento de 1 ponto no seu nível.");
        }

        ///PROVISÃO(49)
        if (item.getIdItem() == ItensMapeamento.PROVISAO.getIdItem()) {

            //Sendo a seção 304 tem que registrar quantas provisões comeram.
            //pois somente passa para outra seção se consumir 5 provisões
            if (secao.getCodSecaoLivro() == 304) {
                TelaSecao_304.setQuantProvisoesComidas(TelaSecao_304.getQuantProvisoesComidas() + 1);
                TelaSecao_304.getLbFaixaInfoQtdFaltamComer().setText("<html><center>Comer<br>"+
                        String.valueOf(5 - TelaSecao_304.getQuantProvisoesComidas())+"<br>provisões(ão)</center></html>");
                repaint();
            }

            lbEnergiaPersonagem.setText("Energia: " +
                    String.valueOf(DadosLivroCarregado.getPersonagem().getEnergiaAtual()) + "/" +
                    String.valueOf(DadosLivroCarregado.getPersonagem().getEnergiaMax()));

            botaoProvisoes.setText("<html>Provisões:" + UtilItens.quantidadeProvisoesRestantes() + "</html>");

            if ( consumiuItem ) {
                //Destrói o objeto
                imgLabel.setVisible(false);

                //Mensagem
                CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome().toUpperCase() +
                        ", você recuperou 4 pontos de energia ao comer uma Provisão(refeição).");
            }
            else
                //Testa se personagem encontra-se com energia cheia e o avisa.
                if (UtilPersonagem.retornaDiferencaEntreEnergiaMaxEAtual() == 0){
                    CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()
                            .toUpperCase()+", sua energia está completa."+
                            "\n\nNão existe necessidade de se alimentar.");
                    return;
                }
        }

        if ( (item.getIdItem() == ItensMapeamento.ESPADA.getIdItem()) ||
                (item.getIdItem() == ItensMapeamento.ESPADA_MAGNIFICA.getIdItem()))
            lbHabilidadePersonagem.setText("Habilidade: " +
                    String.valueOf(DadosLivroCarregado.getPersonagem().getHabilidadeAtual()) + "/" +
                    String.valueOf(DadosLivroCarregado.getPersonagem().getHabilidadeMax()));


        if ( item.getIdItem() == ItensMapeamento.POCAO_DA_FORCA.getIdItem() ){
            lbEnergiaPersonagem.setText("Energia: " +
                    String.valueOf(DadosLivroCarregado.getPersonagem().getEnergiaAtual()) + "/" +
                    String.valueOf(DadosLivroCarregado.getPersonagem().getEnergiaMax()));
        }

        //Após a interação com o item, atualiza a tela
        atualizarBolsa();
    }

    private class TelaBolsaListener implements MouseListener {
        private boolean consumiuItem;

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == botaoSair){
                dispose();
                return;
            }

            //Recupero o item através do índice do Map que é o objeto do tipo JLabelOpcoesTelaSecao
            JLabelOpcoesTelaSecao imgLabel = (JLabelOpcoesTelaSecao) e.getSource();
            Item item = mapItens.get(imgLabel);

            //executa o efeito e remove da bolsa.
            consumiuItem = new EfeitoDeItens(secao).acoesDosItens(item.getIdItem());

            //Faz a atualização dos campos da tela de secao ou retorna alguma mensagem
            atualizarCamposTelaSecao(imgLabel, item, consumiuItem);
        }



        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == botaoSair) {
                botaoSair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3_SELECIONADA.getEnderecoImagem(),
                        botaoSair.getWidth(), botaoSair.getHeight()).getImageIcon());
                return;
            }

            //Recupero o item através do índice do Map que é o objeto do tipo JLabelOpcoesTelaSecao
            JLabelOpcoesTelaSecao imgLabel = (JLabelOpcoesTelaSecao) e.getSource();

            //Configura tela suspensa de informação de itens
            configuraTelaSuspensaInfoItem(imgLabel);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == botaoSair) {
                botaoSair.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.FAIXA_3.getEnderecoImagem(),
                        botaoSair.getWidth(), botaoSair.getHeight()).getImageIcon());
                return;
            }

            //Esconde a tela suspensa de info.
            panelInfoItem.setVisible(false);
        }
    }
}
