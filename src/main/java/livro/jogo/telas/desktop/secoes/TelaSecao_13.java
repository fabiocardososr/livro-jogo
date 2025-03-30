package livro.jogo.telas.desktop.secoes;

import livro.jogo.acaosecoes.AcoesSecao_13;
import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.personalizados.util.RedimensionarImagem;
import livro.jogo.utils.DadosLivroCarregado;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_13 extends TelaSecoesBasica {

    public TelaSecao_13(Secao secao) {
        super(secao);

        //Já na entrada da tela perde 3 pontos de sorte
        AcoesSecao_13.perde3PontosDeSorte();

        //Atualiza label que mostra os níveis de habilidade, sorte e energia do personagem na tela
        atualizaIndicesNaTelaDoPersonagem();


        //Aviso sobre a perda de 3 pontos de sorte
        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                ",\n\nvocê perde 3 pontos de Sorte.");
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);

        labelNumOpcao1.setBounds(116,622, 50,50);
        botaoOpcao1.setBounds(120,630,40,50);
        lbTextoOpcao1.setBounds(170,617,700,60);

        acaoBotoes(secao);
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
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
