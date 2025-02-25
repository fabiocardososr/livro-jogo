package livro.jogo.telas.desktop.principal;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.ImagePanel;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.utils.EfeitoDeItens;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

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

    public TelaBolsa(Container container,int largura, int altura, JLabel lbEnergiaPersonagem,
                     JLabel lbHabilidadePersonagem, JLabel lbSortePersonagem,
                     JLabelOpcoesTelaSecao botaoProvisoes, JLabelOpcoesTelaSecao labelPocaoInicial) {
        this.lbEnergiaPersonagem = lbEnergiaPersonagem;
        this.botaoProvisoes = botaoProvisoes;
        this.labelPocaoInicial = labelPocaoInicial;
        this.lbSortePersonagem = lbSortePersonagem;
        this.lbHabilidadePersonagem = lbHabilidadePersonagem;
        this.container = container;
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
        ArrayList<Item> bolsa = DadosLivroCarregado.getBolsa();
        ArrayList<Item> itensEquipados = DadosLivroCarregado.getItensEquipados();

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
            JLabelOpcoesTelaSecao imgItem;

            //Se cabeça ou cabo do machado do anões, aumentar a altura e largura
            if ( item.getIdItem() == 25 ) {
                imgItem = new JLabelOpcoesTelaSecao("", largura, altura + 15,
                        item.getEnderecoImagem());
                imgItem.setBounds(x,y,largura,altura+15);
            }
            else {
                imgItem = new JLabelOpcoesTelaSecao("", largura, altura,
                        item.getEnderecoImagem());
                imgItem.setBounds(x,y,largura,altura);
            }

            imgItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            imgItem.setToolTipText(item.getNome().toUpperCase()+" - " + item.getDescricao());
            imgItem.addMouseListener(acao);

            //Coloco o objeto no map para posterior consulta ao clicar nele
            mapItens.put(imgItem, item);

            painelListaItens.add(imgItem);
            ++contNumerodeItensPorLinha;
            //imgInterrogacao.setBorder(BorderFactory.createLineBorder(Color.RED));

            //Caminhando da esquerda para a direita
            x = x + 100;
        }

        for (Item item: itensEquipados) {

            if (item.getEnderecoImagem().isEmpty())
                continue;

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
            rotuloEquipado.setBounds(x+5,y+40,75,18);

            //Incluir item no hashmap para que possa ser identificado quando clicado na imagem
            mapItens.put(imgItem, item);
           // mapLabel.put(imgItem, rotuloEquipado);

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
            consumiuItem = EfeitoDeItens.acoesDosItens(item.getIdItem());

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

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    //Quando clicado no botão de poção inicial, muda a imagem para uma garrafa vazia
    private void mudarBotaoPocaoInicialParaVazio(){
        labelPocaoInicial.setIcon(Util.dimensionarImagem(50,55,
                ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_VAZIA.getEnderecoImagem()));
        labelPocaoInicial.setText("");
        labelPocaoInicial.setHorizontalAlignment(SwingConstants.CENTER);
        labelPocaoInicial.setBounds(1270,265,150,100);
        labelPocaoInicial.addMouseListener(null);
        labelPocaoInicial.setToolTipText("Poção consumida");
    }

    private void atualizarCamposTelaSecao(JLabelOpcoesTelaSecao imgLabel, Item item,
                                          boolean consumiuItem) {
        Personagem personagem = DadosLivroCarregado.getPersonagem();

        /* Anel da Luz(1) */
        if (item.getIdItem() == ItensMapeamento.ANEL_DA_LUZ.getIdItem()){

            if ( consumiuItem )
                CarregarTelas.telaMensagem("Você coloca o Anel de luz no seu dedo indicador.");
            else
                CarregarTelas.telaMensagem("Item já equipado!");

            dispose();
        }

        /*Coroa de ouro(2)*/
        if (item.getIdItem() == ItensMapeamento.COROA_DE_OURO.getIdItem()){

            if ( consumiuItem )
                CarregarTelas.telaMensagem("Você coloca o Anel de luz no seu dedo indicador.");
            else
                CarregarTelas.telaMensagem("Item já equipado!");

            dispose();
        }

        /*Poção Antiveneno(3)*/
        if (item.getIdItem() == ItensMapeamento.POCAO_ANTIVENENO.getIdItem()){

            if ( consumiuItem )
                CarregarTelas.telaMensagem("Você coloca o Anel de luz no seu dedo indicador.");
            else
                CarregarTelas.telaMensagem("Item já equipado!");

            dispose();
        }

        /*Filtros Nasais(4)*/
        if (item.getIdItem() == ItensMapeamento.FILTROS_NASAIS.getIdItem()){

            if ( consumiuItem )
                CarregarTelas.telaMensagem("Você coloca o Anel de luz no seu dedo indicador.");
            else
                CarregarTelas.telaMensagem("Item já equipado!");

            dispose();
        }

        /*Chave de prata(5)*/
        if (item.getIdItem() == ItensMapeamento.CHAVE_DE_PRATA.getIdItem()){

            if ( consumiuItem )
                CarregarTelas.telaMensagem("Você coloca o Anel de luz no seu dedo indicador.");
            else
                CarregarTelas.telaMensagem("Item já equipado!");

            dispose();
        }

        /*Chave de prata(5)*/
        if (item.getIdItem() == ItensMapeamento.CHAVE_DE_PRATA.getIdItem()){

            if ( consumiuItem )
                CarregarTelas.telaMensagem("Você coloca o Anel de luz no seu dedo indicador.");
            else
                CarregarTelas.telaMensagem("Item já equipado!");

            dispose();
        }

        /*Chave de prata(6)*/
        if (item.getIdItem() == ItensMapeamento.CABECA_DE_MARTELO_DOS_ANOES.getIdItem()){

            if ( consumiuItem )
                CarregarTelas.telaMensagem("Você coloca o Anel de luz no seu dedo indicador.");
            else
                CarregarTelas.telaMensagem("Item já equipado!");

            dispose();
        }

        /*Chave de prata(7)*/
        if (item.getIdItem() == ItensMapeamento.AGUA_BENTA.getIdItem()){

            if ( consumiuItem )
                CarregarTelas.telaMensagem("Você coloca o Anel de luz no seu dedo indicador.");
            else
                CarregarTelas.telaMensagem("Item já equipado!");

            dispose();
        }

        /*Chave de prata(8)*/
        if (item.getIdItem() == ItensMapeamento.POCAO_CONTROLE_DAS_PLANTAS.getIdItem()){

            if ( consumiuItem )
                CarregarTelas.telaMensagem("Você coloca o Anel de luz no seu dedo indicador.");
            else
                CarregarTelas.telaMensagem("Item já equipado!");

            dispose();
        }

        /*Chave de prata(9)*/
        if (item.getIdItem() == ItensMapeamento.SINO_DE_METAL.getIdItem()){

            if ( consumiuItem )
                CarregarTelas.telaMensagem("Você coloca o Anel de luz no seu dedo indicador.");
            else
                CarregarTelas.telaMensagem("Item já equipado!");

            dispose();
        }

        /* Espada magnífica(10) */
        if (item.getIdItem() == ItensMapeamento.ESPADA_MAGNIFICA.getIdItem()){
            /*
            Lembre que será um botão fora da bolsa, provavelmente um botão na parte de opções,
            tipo: "Receba a espada", e então não tem necessidade de precisar clicar na espada
            dentro da bolsa. Na bolsa já mostrará "Equipado'.
            Mas não tem a opção de clicar na espada inicial para voltar (também não faz sentido voltar...)
            * */

            //Muda informação do índice do personagem
            lbHabilidadePersonagem.setText("Habilidade: "+
                    String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                    String.valueOf(personagem.getHabilidadeMax()));
        }

        /* Poeira da Levitação(11) */
        if (item.getIdItem() == ItensMapeamento.POEIRA_DA_LEVITACAO.getIdItem()){
            /*
            Lembre que será um botão fora da bolsa, provavelmente um botão na parte de opções,
            tipo: "Receba a espada", e então não tem necessidade de precisar clicar na espada
            dentro da bolsa. Na bolsa já mostrará "Equipado'.
            Mas não tem a opção de clicar na espada inicial para voltar (também não faz sentido voltar...)
            * */

            //Muda informação do índice do personagem
            lbHabilidadePersonagem.setText("Habilidade: "+
                    String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                    String.valueOf(personagem.getHabilidadeMax()));
        }

        /* Poeira da Levitação(12) */
        if (item.getIdItem() == ItensMapeamento.BRACADEIRA_DA_FORCA.getIdItem()){
            /*
            Lembre que será um botão fora da bolsa, provavelmente um botão na parte de opções,
            tipo: "Receba a espada", e então não tem necessidade de precisar clicar na espada
            dentro da bolsa. Na bolsa já mostrará "Equipado'.
            Mas não tem a opção de clicar na espada inicial para voltar (também não faz sentido voltar...)
            * */

            //Muda informação do índice do personagem
            lbHabilidadePersonagem.setText("Habilidade: "+
                    String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                    String.valueOf(personagem.getHabilidadeMax()));
        }

        /* Poção da Imobilidade(13) */
        if (item.getIdItem() == ItensMapeamento.POCAO_DA_IMOBILIDADE.getIdItem()){
            /*
            Lembre que será um botão fora da bolsa, provavelmente um botão na parte de opções,
            tipo: "Receba a espada", e então não tem necessidade de precisar clicar na espada
            dentro da bolsa. Na bolsa já mostrará "Equipado'.
            Mas não tem a opção de clicar na espada inicial para voltar (também não faz sentido voltar...)
            * */

            //Muda informação do índice do personagem
            lbHabilidadePersonagem.setText("Habilidade: "+
                    String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                    String.valueOf(personagem.getHabilidadeMax()));
        }

        /* Poção da Imobilidade(14) */
        if (item.getIdItem() == ItensMapeamento.BELADONA.getIdItem()){
            /*
            Lembre que será um botão fora da bolsa, provavelmente um botão na parte de opções,
            tipo: "Receba a espada", e então não tem necessidade de precisar clicar na espada
            dentro da bolsa. Na bolsa já mostrará "Equipado'.
            Mas não tem a opção de clicar na espada inicial para voltar (também não faz sentido voltar...)
            * */

            //Muda informação do índice do personagem
            lbHabilidadePersonagem.setText("Habilidade: "+
                    String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                    String.valueOf(personagem.getHabilidadeMax()));
        }

        /* Colar Olho de Âmbar(15) */
        if (item.getIdItem() == ItensMapeamento.BELADONA.getIdItem()){
            /*
            Lembre que será um botão fora da bolsa, provavelmente um botão na parte de opções,
            tipo: "Receba a espada", e então não tem necessidade de precisar clicar na espada
            dentro da bolsa. Na bolsa já mostrará "Equipado'.
            Mas não tem a opção de clicar na espada inicial para voltar (também não faz sentido voltar...)
            * */

            //Muda informação do índice do personagem
            lbHabilidadePersonagem.setText("Habilidade: "+
                    String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                    String.valueOf(personagem.getHabilidadeMax()));
        }






        /* Poção de Habilidade(45)  */
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
                    String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                    String.valueOf(personagem.getHabilidadeMax()));

            //Destrói o objeto
            imgLabel.setVisible(false);

            //Mensagem
            CarregarTelas.telaMensagem(personagem.getNome().toUpperCase() +
                    ", você toma a poção e se sente bem.\n\nSeu índice de habilidade" +
                    " encontra-se no nível máximo.");
        }

        /* Poção da Força(energia - 46)  */
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
                    String.valueOf(personagem.getEnergiaAtual())+ "/"+
                    String.valueOf(personagem.getEnergiaMax()));

            //Destrói o objeto
            imgLabel.setVisible(false);

            //Mensagem
            CarregarTelas.telaMensagem(personagem.getNome().toUpperCase() +
                    ", você toma a poção e se sente bem.\n\nSeu índice de energia" +
                    " encontra-se no nível máximo.");
        }

        /* Poção da Fortuna(47)  */
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
                    String.valueOf(personagem.getSorteAtual())+ "/"+
                    String.valueOf(personagem.getSorteMax()));

           //Destrói o objeto
           imgLabel.setVisible(false);

           //Mensagem
           CarregarTelas.telaMensagem(personagem.getNome().toUpperCase() +
                        ", você toma a poção e se sente bem.\n\nSeu índice de sorte" +
                        " encontra-se no nível máximo. Além do incremento de 1 ponto no seu nível.");
        }

        /* PROVISÃO(49) */
        if (item.getIdItem() == ItensMapeamento.PROVISAO.getIdItem()) {

            lbEnergiaPersonagem.setText("Energia: " +
                    String.valueOf(DadosLivroCarregado.getPersonagem().getEnergiaAtual()) + "/" +
                    String.valueOf(DadosLivroCarregado.getPersonagem().getEnergiaMax()));

            botaoProvisoes.setText("<html>Provisões:" + Util.quantidadeProvisoesRestantes() + "</html>");

            if ( consumiuItem ) {
                //Destrói o objeto
                imgLabel.setVisible(false);

                //Mensagem
                CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome().toUpperCase() +
                        ", você recuperou 4 pontos de energia ao comer uma Provisão(refeição).");
            }
            else
                //Testa se personagem encontra-se com energia cheia e o avisa.
                if (Util.retornaDiferencaEntreEnergiaMaxEAtual() == 0){
                    CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()
                            .toUpperCase()+", sua energia está completa."+
                            "\n\nNão existe necessidade de se alimentar.");
                    return;
                }
        }

        //atualiza a tela de secao que chama este tela
        //estava ficando estranha a tela em alguns pontos ao clicar nos itens. fundo com imagens sem sentido
        container.repaint();
    }


}
