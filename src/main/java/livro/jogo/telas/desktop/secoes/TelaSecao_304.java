package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;
import livro.jogo.utils.Util;
import livro.jogo.utils.UtilItens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_304 extends TelaSecoesBasica {

    public TelaSecao_304(Secao secao) {
        super(secao);
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        carregaFaixaDeInformacaoQtdFaltamComer();
    }

    private void carregaFaixaDeInformacaoQtdFaltamComer() {

        JLabelOpcoesTelaSecao faixaInfo = new JLabelOpcoesTelaSecao(null,
                200,160,ImagensDoLivroFlorestaDaDestruicao.FAIXA_INDICE_TELA_SECAO);
        faixaInfo.setBounds(505,445,200,160);
        faixaInfo.setCursor(null);
        //faixaInfoQtdItensFaltam.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        lbFaixaInfoQtdFaltamComer = new JLabel("<html><center>Comer<br>"+
                String.valueOf(5 - getQuantProvisoesComidas())+"<br>provisões(ão)</center></html>");
        lbFaixaInfoQtdFaltamComer.setFont(new Font(Font.SERIF,Font.BOLD,20));
        lbFaixaInfoQtdFaltamComer.setForeground(new Color(139,0,0));
        lbFaixaInfoQtdFaltamComer.setHorizontalAlignment(SwingConstants.CENTER);
        lbFaixaInfoQtdFaltamComer.setBounds(530,475,150,100);
        lbFaixaInfoQtdFaltamComer.setCursor(null);
        //lbFaixaInfoQtdFaltamComer.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        add(lbFaixaInfoQtdFaltamComer);
        add(faixaInfo);

    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ( getQuantProvisoesComidas() >= 5)
                    abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );

                if ( (UtilItens.quantidadeProvisoesRestantes() > 0) && (getQuantProvisoesComidas() < 5)) {
                    CarregarTelas.telaMensagem("Necessário comer " + String.valueOf(5 - getQuantProvisoesComidas()) +
                            " provisões(ão)");
                    return;
                }

                if ( (UtilItens.quantidadeProvisoesRestantes() == 0) && (getQuantProvisoesComidas() < 5)) {
                    new Util().reproduzirAudioMp3("audio/efeitos_sonoros/risada_sinistra_fim_de_jogo.mp3", null);
                    CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                            ",\n\nVocê não possui provisões suficientes para se restabelecer.\n\nSua aventura acaba aqui.");
                    personagemVivo(false);
                    return;
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
