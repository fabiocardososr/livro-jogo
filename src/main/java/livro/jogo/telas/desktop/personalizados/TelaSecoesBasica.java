package livro.jogo.telas.desktop.personalizados;

import livro.jogo.entidades.Item;
import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.PocoesIniciais;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.AcoesComunsTelaSecao;
import livro.jogo.utils.ManipularDadosLivro;
import livro.jogo.utils.Util;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecoesBasica extends TelaBasica{
    private final Secao secao;
    private final Personagem personagem;
    private final TelaSecoesBasicaAcaoDosLabels acaoLabels = new TelaSecoesBasicaAcaoDosLabels();
    private Item pocaoInicial;
    private String enderecoImagem = ManipularDadosLivro.getLivro().getImagemComplementar();

    private JLabelOpcoesTelaSecao labelMapa;
    private JLabelOpcoesTelaSecao labelBolsa;
    private JLabelOpcoesTelaSecao labelPocaoInicial;
    private AcoesComunsTelaSecao acoesComunsTelaSecao;


    public TelaSecoesBasica(Secao secao, Personagem personagem) {
        super(1500,800); //Tamanho comum para todas as telas de seções

        //Caso necessite alterar layout da tela para uma especifica. Necessário o Container
        Container tela = getContentPane();
        tela.setBackground(new Color(210,180,140));

        this.secao = secao;
        this.personagem = personagem;
        acoesComunsTelaSecao = new AcoesComunsTelaSecao(personagem);

        if (secao.getEnderecoImagem() != null)
            this.enderecoImagem = secao.getEnderecoImagem();

        setTitle("Seção - "+secao.getCodSecaoLivro());
        setType(Window.Type.UTILITY);

        //Carregar campo que receberá o texto da história
        carregarTextoHistoria();
        carregaImgSecao();
        carregaPainelPersonagem();
        carregarPainelDireito();
        carregaPainelInferior();
    }

    private void carregarTextoHistoria() {

        //Texto do livro
        JTextPane textoHistoria = new JTextPane();
        textoHistoria.setBackground(Color.BLACK);
        StyledDocument textoCapaLivroStyle = textoHistoria.getStyledDocument();
        SimpleAttributeSet configTexto = new SimpleAttributeSet();
        StyleConstants.setAlignment(configTexto,StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(configTexto,20);
        StyleConstants.setForeground(configTexto,Color.WHITE);
        textoCapaLivroStyle.setParagraphAttributes(0, textoCapaLivroStyle.getLength(), configTexto, false);
        textoHistoria.setEditable(false);
        textoHistoria.setCaretPosition(0); //para posicionar a barra de rolagem no início.
        JScrollPane scrollTextoHistoria = new JScrollPane(textoHistoria);
        scrollTextoHistoria.setFocusable(true);
        scrollTextoHistoria.setBorder(null);

        //Carregando texto no componente
        textoHistoria.setText( secao.getTexto() );

        //posicionamento na tela
        scrollTextoHistoria.setBounds(15, 15, 870, 450);

        //Adicionando a tela
        add(scrollTextoHistoria);
    }

    private void carregaPainelPersonagem() {
        //Dados do Personagem
        ImagePanel painelPersonagem;
        painelPersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MOLDURA);

        //Nome do personagem
        JLabel lbNomePersonagem = new JLabel(personagem.getNome());
        lbNomePersonagem.setFont(new Font(Font.SERIF,Font.BOLD,30));
        lbNomePersonagem.setForeground(new Color(139,0,0));
        lbNomePersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        //lbNomePersonagem.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Habilidade
        JLabel lbHabilidadePersonagem = new JLabel("Habilidade: "+
                String.valueOf(personagem.getHabilidadeAtual())+ "/"+
                String.valueOf(personagem.getHabilidadeMax()));
        lbHabilidadePersonagem.setFont(new Font(Font.SERIF,Font.BOLD,20));
        lbHabilidadePersonagem.setForeground(new Color(139,0,0));
        lbHabilidadePersonagem.setHorizontalAlignment(SwingConstants.LEFT);
        lbHabilidadePersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbHabilidadePersonagem.setToolTipText("<html>Reflete a sua capacidade como espadachim e domínio geral das técnicas de luta.<br>Índice: (Atual/Máximo)</html>");

        //Energia
        JLabel lbEnergiaPersonagem = new JLabel("Energia: "+
                String.valueOf(personagem.getEnergiaAtual())+ "/"+
                String.valueOf(personagem.getEnergiaMax()));
        lbEnergiaPersonagem.setFont(new Font(Font.SERIF,Font.BOLD,20));
        lbEnergiaPersonagem.setForeground(new Color(139,0,0));
        lbEnergiaPersonagem.setHorizontalAlignment(SwingConstants.LEFT);
        lbEnergiaPersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbEnergiaPersonagem.setToolTipText("<html>Reflete sua constituição física global, sua determinação de sobreviver, força de vontade e aptidão geral.<br>Índice: (Atual/Máximo)</html>");

        //Sorte
        JLabel lbSortePersonagem = new JLabel("Sorte: "+
                String.valueOf(personagem.getSorteAtual())+ "/"+
                String.valueOf(personagem.getSorteMax()));
        lbSortePersonagem.setFont(new Font(Font.SERIF,Font.BOLD,20));
        lbSortePersonagem.setForeground(new Color(139,0,0));
        lbSortePersonagem.setHorizontalAlignment(SwingConstants.LEFT);
        lbSortePersonagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbSortePersonagem.setToolTipText("<html>Seu índice SORTE indica o quanto você é, naturalmente, uma pessoa de sorte.<br>Índice: (Atual/Máximo)</html>");

        //Aqui define a imagem do Bárbaro(ou Bárara) do personagem
        //1 = Homem; 2 = Mulher
        String enderecoImgPersonagem;
        if (personagem.getGenero() == 1)
            enderecoImgPersonagem = ImagensDoLivroFlorestaDaDestruicao.BARBARO.getEnderecoImagem();
        else
            enderecoImgPersonagem = ImagensDoLivroFlorestaDaDestruicao.BARBARA.getEnderecoImagem();
        ImagePanel imgPersonagem = new ImagePanel(enderecoImgPersonagem);
        //imgPersonagem.setBorder(BorderFactory.createLineBorder(Color.RED));


        //Bolsa
        labelBolsa = new JLabelOpcoesTelaSecao(null,ImagensDoLivroFlorestaDaDestruicao.BOLSA);
        labelBolsa.setToolTipText("Acesse aqui sua mochila.");
        labelBolsa.addMouseListener(acaoLabels);
        //labelBolsa.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelBolsa.setBounds(930,550,150,140);
        labelBolsa.setIcon(new RedimensionarImagem(ImagensDoLivroFlorestaDaDestruicao.BOLSA.getEnderecoImagem(),
                labelBolsa.getWidth(), labelBolsa.getHeight()).getImageIcon());


        //Posiciona
        imgPersonagem.setBounds(1080,500,205,260);
        lbSortePersonagem.setBounds(920,510,300,50);
        lbEnergiaPersonagem.setBounds(920,480,300,50);
        lbHabilidadePersonagem.setBounds(920,450,300,50);
        lbNomePersonagem.setBounds(895,400,300,50);
        painelPersonagem.setBounds(875, 367, 340, 375);

        //Adiciona a tela
        add(lbSortePersonagem);
        add(lbEnergiaPersonagem);
        add(lbHabilidadePersonagem);
        add(lbNomePersonagem);
        add(labelBolsa);
        add(imgPersonagem);
        add(painelPersonagem);
    }

    private void carregaImgSecao() {

        //Carrega imagem
        JLabel labelImagemSecao = new JLabel();
        labelImagemSecao.setHorizontalAlignment(SwingConstants.CENTER);
        ImagePanel imgMolduraParaImgSecao = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MOLDURA);

        //posicionamento
        imgMolduraParaImgSecao.setBounds(875,2,340,375);
        labelImagemSecao.setBounds(915, 45, 261, 289);
        labelImagemSecao.setIcon(new RedimensionarImagem(enderecoImagem, labelImagemSecao.getWidth(),
                labelImagemSecao.getHeight()).getImageIcon());

        add(labelImagemSecao);
        add(imgMolduraParaImgSecao);
    }

    private void carregaPainelInferior() {
        ImagePanel imgPainelInferior = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_ABERTO);
        imgPainelInferior.setBounds(5,465,900,295);
        add(imgPainelInferior);
    }

    private void carregarPainelDireito() {
        ImagePanel imgPainelDireito = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_FAIXA);


        //Configura labelMapa
        labelMapa = new JLabelOpcoesTelaSecao("Mapa",80,85,ImagensDoLivroFlorestaDaDestruicao.BUSSOLA);
        labelMapa.addMouseListener(acaoLabels);
        //labelMapa.setToolTipText("Acesso ao mapa.");



        //Configura a poção inicial (na condição sendo '0' é porque já foi usada)
        pocaoInicial = Util.retornaPocaoInicialDaBolsa();

        if (pocaoInicial.getIdItem() == PocoesIniciais.POCAO_DE_HABILIDADE.getIdItemPocao())
            labelPocaoInicial = new JLabelOpcoesTelaSecao("Habilidade",50,55,ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_HABILIDADE);

        if (pocaoInicial.getIdItem() == PocoesIniciais.POCAO_DE_ENERGIA.getIdItemPocao())
            labelPocaoInicial = new JLabelOpcoesTelaSecao("Energia",50,55,ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_ENERGIA);

        if (pocaoInicial.getIdItem() == PocoesIniciais.POCAO_DA_FORTUNA.getIdItemPocao())
            labelPocaoInicial = new JLabelOpcoesTelaSecao("Fortuna",50,55,ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_SORTE);

        if ( pocaoInicial.getQuantidadeUso() == 0 ){
            labelPocaoInicial.setIcon(Util.dimensionarImagem(70,75, ImagensDoLivroFlorestaDaDestruicao.POCAO_DE_VAZIA.getEnderecoImagem()));
            labelPocaoInicial.setText("Vazia");
        }
        labelPocaoInicial.addMouseListener(acaoLabels);


        //Posicionamento
        labelPocaoInicial.setBounds(1263,200,250,100);
        labelMapa.setBounds(1250,100,150,100);
        imgPainelDireito.setBounds(1200,2,280,770);

        //Adiciona a tela
        add(labelMapa);
        add(labelPocaoInicial);
        add(imgPainelDireito);
    }

    private class TelaSecoesBasicaAcaoDosLabels implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == labelMapa){
                JOptionPane.showMessageDialog(null,"Clicado no Mapa");
            }

            if (e.getSource() == labelBolsa){
                JOptionPane.showMessageDialog(null,"Clicado na Bolsa");
            }

            if (e.getSource() == labelPocaoInicial){
                if (pocaoInicial.getQuantidadeUso() == 0)
                    JOptionPane.showMessageDialog(null,"Você já tomou a "+ pocaoInicial.getDescricao());
                else
                    JOptionPane.showMessageDialog(null,"Clicado na Poção: "+ pocaoInicial.getDescricao());
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
    }

}
