package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.*;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.enums.ItensMapeamento;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.UtilBolsa;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_3 extends TelaSecoesBasica {
    private TelaSecoesBasica tela = this;

    public TelaSecao_3(Secao secao) {
        super(secao);

    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {

    }

    @Override
    protected void acaoBotoes(Secao secao) {

            botaoOpcao1.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getSource() == botaoOpcao1){

                    if ( AcoesSecao_3.opcao_1() )
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
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

            botaoOpcao2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao2){

                    //Se personagem tem o anel de luz, mas não quer seguir sem colocar o anel no dedo
                    AcoesSecao_3.opcao_2_possui_item(tela);
                    if ( ( isRespostaTelaMensagem() ) ||
                         (!UtilBolsa.verificarExistenciaDeItemNaBolsaOuEquipado(ItensMapeamento.ANEL_DA_LUZ.getIdItem())) )
                        abrirProximaSecao(secao.getProximasSecoes().get(1).getCodProximaSecao());
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
    }

}
