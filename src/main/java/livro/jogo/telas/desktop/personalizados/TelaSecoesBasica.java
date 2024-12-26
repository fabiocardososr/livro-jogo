package livro.jogo.telas.desktop.personalizados;

import livro.jogo.entidades.Personagem;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.entidades.Secao;
import livro.jogo.utils.ManipularDadosLivro;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.File;

public class TelaSecoesBasica extends TelaBasica{
    private final Secao secao;
    private final Personagem personagem;
    private String enderecoImagemDefault = ManipularDadosLivro.getLivro().getImagemComplementar();

    public TelaSecoesBasica(Secao secao, Personagem personagem) {
        super(1500,800); //Tamanho comum para todas as telas de seções

        //Caso necessite alterar layout da tela para uma especifica. Necessário o Container
        Container tela = getContentPane();
        tela.setBackground(new Color(210,180,140));

        this.secao = secao;
        this.personagem = personagem;

        if (secao.getEnderecoImagem() != null)
            this.enderecoImagemDefault = secao.getEnderecoImagem();

        setTitle("Seção - "+secao.getCodSecaoLivro());
        setType(Window.Type.UTILITY);

        //Carregar campo que receberá o texto da história
        carregarTextoHistoria();
        carregaImgSecaoEPersonagem();
        carregarPainelDireito();
        carregaPainelInferior();
    }

    private void carregaImgSecaoEPersonagem() {
        //Carrega imagem
        JLabel labelImagemSecao = new JLabel();
        //ImageIcon img = new ImageIcon(new File(ImagensDoLivroFlorestaDaDestruicao.IMAGEM_DEFAULT_FLORESTA).toString());
        //labelImgCapaLivro.setIcon( new ImageIcon(livro.getImagemCapa()));
        //ImageIcon img = new//VEJA SE REDIMENSIONA A IMAGEM
        labelImagemSecao.setIcon(new ImageIcon(enderecoImagemDefault));
        labelImagemSecao.setHorizontalAlignment(SwingConstants.CENTER);

        //labelImagemSecao.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        ImagePanel imgMoldura = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MOLDURA);



        //Dados do Personagem
        ImagePanel painelPersonagem;
        //if (personagem.getGenero() == 1)
        painelPersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.MOLDURA);
//        else
//            painelPersonagem = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.BARBARA);


        //posicionamento
        imgMoldura.setBounds(875,2,340,375);
        labelImagemSecao.setBounds(915, 45, 262, 289);

        //imagemSecao.setBounds(945, 85, 200, 200);
        painelPersonagem.setBounds(887, 367, 310, 340);

        add(labelImagemSecao);
        add(imgMoldura);
        add(painelPersonagem);
       // add(imagemSecao);
    }

    private void carregaPainelInferior() {
        ImagePanel imgPainelInferior = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_ABERTO);
        imgPainelInferior.setBounds(5,465,900,295);
        add(imgPainelInferior);
    }

    private void carregarPainelDireito() {
        ImagePanel imgPainelDireito = new ImagePanel(ImagensDoLivroFlorestaDaDestruicao.PERGAMINHO_FAIXA);
        imgPainelDireito.setBounds(1200,2,280,770);
        add(imgPainelDireito);
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
        textoHistoria.setCaretPosition(0); //para posicionar a a barra de rolagem no início.
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


}
