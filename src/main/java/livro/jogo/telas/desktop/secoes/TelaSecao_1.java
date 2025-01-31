package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_1;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.centralizacaotelas.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_1 extends TelaSecoesBasica {
    private JLabelOpcoesTelaSecao botaoOpcao1;
    private JLabelOpcoesTelaSecao botaoOpcao2;
    private final AcoesSecao_1 acoesSecao1 = new AcoesSecao_1();


    public TelaSecao_1(Secao secao) {
        super(secao, CarregarTelas.getReferenciaTelaPrincipal());

        textoHistoria.setText( secao.getTexto() );

        //para posicionar a barra de rolagem no início.
        textoHistoria.setCaretPosition(0);

    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);
        opcao2(secao);
    }

    private void opcao1(Secao secao){
        String texto = "1";
        int indiceOpcao = 0; //Para recuperar o texto da opção da seção
        int indiceProximaSecao = 261; //Próxima seção escolhida


        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.SERIF,Font.BOLD,30));
        label.setBounds(116,592, 50,50);
        //label.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoOpcao1 = new JLabelOpcoesTelaSecao("",40,50,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1);
        botaoOpcao1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoOpcao1.setBounds(120,600,40,50);
        botaoOpcao1.setToolTipText("Clique para escolher esta Opção.");
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    acoesSecao1.opcao_1();

                    //Seção que esta opção vai direcionar
                    abrirProximaSecao(indiceProximaSecao);

                    //dispose();
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
        //botaoOpcao1.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Texto da opção
        JLabel lbTexto = new JLabel(secao.getProximasSecoes().get(indiceOpcao).getTextoOpcao());
        lbTexto.setBounds(170,587,450,60);
        lbTexto.setFont(new Font(Font.SERIF,Font.BOLD,25));
        lbTexto.setForeground(new Color(139,0,0));

        add(lbTexto);
        add(label);
        add(botaoOpcao1);
    }

    private void opcao2(Secao secao){
        String texto = "2";
        int indiceOpcao = 1; //Para recuperar o texto da opção da seção
        int indiceProximaSecao = 54;

        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.SERIF,Font.BOLD,30));
        label.setBounds(116,652, 50,50);
        //label.setBorder(BorderFactory.createLineBorder(Color.RED));

        botaoOpcao2 = new JLabelOpcoesTelaSecao("",40,50,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_VERTICAL_1);
        botaoOpcao2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoOpcao2.setBounds(120,660,40,50);
        botaoOpcao2.setToolTipText("Clique para escolher esta Opção.");
        botaoOpcao2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao2){
                    acoesSecao1.opcao_2();

                    //Seção que esta opção vai direcionar
                    Secao proximaSecao = DadosLivroCarregado.getMapSecao().get(indiceProximaSecao);

                    CarregarTelas.carregarSecao(proximaSecao);
                    dispose();
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

        //Texto da opção
        JLabel lbTexto = new JLabel(secao.getProximasSecoes().get(indiceOpcao).getTextoOpcao());
        lbTexto.setBounds(170,647,450,60);
        lbTexto.setFont(new Font(Font.SERIF,Font.BOLD,25));
        lbTexto.setForeground(new Color(139,0,0));

        add(lbTexto);
        add(label);
        add(botaoOpcao2);
    }


}
