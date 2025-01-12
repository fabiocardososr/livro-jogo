package livro.jogo.telas.desktop;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
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
    private HashMap<JLabelOpcoesTelaSecao, Item> mapItens = new HashMap<JLabelOpcoesTelaSecao, Item>();
    private JLabelOpcoesTelaSecao botaoSair;
    private JLabel lbEnergiaPersonagem;
    private JLabel lbHabilidadePersonagem; //Vai ser atualizado com ações de itens a tela de secoes
    private JLabel lbSortePersonagem; //Vai ser atualizado com ações de itens a tela de secoes
    private JLabelOpcoesTelaSecao botaoProvisoes; //Vai ser atualizado com ações de itens a tela de secoes
    private JLabelOpcoesTelaSecao labelPocaoInicial; //Poção inicial
    private final EfeitoDeItens efeitoDeItens = new EfeitoDeItens();

    public TelaBolsa(int largura, int altura, JLabel lbEnergiaPersonagem,
                     JLabel lbHabilidadePersonagem, JLabel lbSortePersonagem,
                     JLabelOpcoesTelaSecao botaoProvisoes, JLabelOpcoesTelaSecao labelPocaoInicial) {
        this.lbEnergiaPersonagem = lbEnergiaPersonagem;
        this.botaoProvisoes = botaoProvisoes;
        this.labelPocaoInicial = labelPocaoInicial;
        this.lbSortePersonagem = lbSortePersonagem;
        this.lbHabilidadePersonagem = lbHabilidadePersonagem;
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

            //Coloco o objeto no map para posterior consulta ao clicar nele
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
        private boolean consumiuItem;

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == botaoSair){
                setVisible(false);
                return;
            }

            //Recupero o item através do índice do Map que é o objeto do tipo JLabelOpcoesTelaSecao
            JLabelOpcoesTelaSecao imgLabel = (JLabelOpcoesTelaSecao) e.getSource();
            Item item = mapItens.get(imgLabel);

            //executa o efeito e remove da bolsa.
            consumiuItem = efeitoDeItens.acoesDosItens(item.getIdItem());

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
    }


}
