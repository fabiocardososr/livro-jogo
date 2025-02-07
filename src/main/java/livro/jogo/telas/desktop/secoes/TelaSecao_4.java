package livro.jogo.telas.desktop.secoes;
import livro.jogo.acaosecoes.AcoesSecao_4;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.utils.DadosLivroCarregado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_4 extends TelaSecoesBasica {
    private JLabelOpcoesTelaSecao botaoOpcao1;
    private JLabelOpcoesTelaSecao botaoOpcao2;

    public TelaSecao_4(Secao secao) {
        super(secao, CarregarTelas.getReferenciaTelaPrincipal());
    }

    private void sair(){
        util.pararAudioMp3();
        if (referenciaTelaPrincipal != null)
            referenciaTelaPrincipal.setVisible(true);

        dispose();
    }

    private void carregaBotaoCasoMorto() {
        //Texto
        JLabel texto = new JLabel("<html><center>Você já estava fraco e esta flecha<br> "+
                "no ombro causou um enorme sangramento.<br>Você morreu! Fim do jogo</center></html>");
        texto.setBounds(305,635,360,80);
        texto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font(Font.SERIF,Font.BOLD,18));
        texto.setForeground(new Color(128,0,0));
        texto.setToolTipText("Clique aqui para começar sua jornada.");
        texto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sair();
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
        //texto.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Botão
        JLabelOpcoesTelaSecao botao = new JLabelOpcoesTelaSecao("",800, 220,
                ImagensDoLivroFlorestaDaDestruicao.FAIXA_OPCOES.getEnderecoImagem());
        botao.setBounds(82,560,800,230);
        //botaoComecarJornada.setBorder(BorderFactory.createLineBorder(Color.RED));

        botao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sair();
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

        add(texto);
        add(botao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        
        //Se false o personagem morreu
        if ( !AcoesSecao_4.perde4PontosDeEnergia() ){
            carregaBotaoCasoMorto();
        }
        else{
            opcao1(secao);
            opcao2(secao);
        }

        //Atualiza label que mostra os níveis de energia do personagem na tela
        lbEnergiaPersonagem.setText("Energia: "+
                String.valueOf(DadosLivroCarregado.getPersonagem().getEnergiaAtual())+ "/"+
                String.valueOf(DadosLivroCarregado.getPersonagem().getEnergiaMax()));
        repaint();
    }

    private void opcao1(Secao secao){
        String texto = "1"; //Número da opção que aparecerá para o usuário (label)
        int indiceOpcao = 0; //Para recuperar o texto da opção da seção (índice no array das próximas seções)

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

                    abrirProximaSecao( secao.getProximasSecoes().get(indiceOpcao).getCodProximaSecao() );
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
        String texto = "2";   //Número da opção que aparecerá para o usuário (label)
        int indiceOpcao = 1; //Para recuperar o texto da opção da seção (índice no array das próximas seções)

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

                    //Seção que esta opção vai direcionar
                    abrirProximaSecao(secao.getProximasSecoes().get(indiceOpcao).getCodProximaSecao());
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
        lbTexto.setBounds(170,647,550,60);
        lbTexto.setFont(new Font(Font.SERIF,Font.BOLD,25));
        lbTexto.setForeground(new Color(139,0,0));

        add(lbTexto);
        add(label);
        add(botaoOpcao2);
    }
}