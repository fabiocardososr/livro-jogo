package livro.jogo.telas.desktop.secoes;

import livro.jogo.entidades.Secao;
import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;
import livro.jogo.telas.desktop.CarregarTelas;
import livro.jogo.telas.desktop.personalizados.JLabelOpcoesTelaSecao;
import livro.jogo.telas.desktop.personalizados.TelaSecoesBasica;
import livro.jogo.telas.desktop.principal.TelaDeMensagensAoJogador;
import livro.jogo.utils.DadosLivroCarregado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaSecao_7 extends TelaSecoesBasica {

    public TelaSecao_7(Secao secao) {
        super(secao, CarregarTelas.getReferenciaTelaPrincipal());
    }

    @Override
    protected void carregarComponentesEspecificos(Secao secao) {
        opcao1(secao);
        //Redimensionando o botão da opção 1 (direcionando para a próxima seção)
        labelNumOpcao.setBounds(116,712, 50,50);
        lbTextoOpcao1.setBounds(170,707,700,60);
        botaoOpcao1.setBounds(120,720,40,50);

        acaoBotoes(secao);
        carregarBotoesBatalha();
    }

    @Override
    protected void acaoBotoes(Secao secao) {
        botaoOpcao1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == botaoOpcao1){
                    if ( isRespostaBatalha() )
                        abrirProximaSecao( secao.getProximasSecoes().getFirst().getCodProximaSecao() );
                    else
                        CarregarTelas.telaMensagem(DadosLivroCarregado.getPersonagem().getNome()+
                                ", você deve vencer os inimigos antes de continuar sua jornada.");
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

    private void carregarBotoesBatalha(){

        /*** Inimigo 1: ABELHAS ASSASSINAS: Habilidade 7; Energia: 3 ***/
        JLabelOpcoesTelaSecao btInimigo1 = new JLabelOpcoesTelaSecao("",120,110,
                ImagensDoLivroFlorestaDaDestruicao.BRASAO_BATALHA);
        btInimigo1.setBounds(100,560,120,110);
        btInimigo1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btInimigo1 .setToolTipText("Clique para escolher esta Opção.");
        btInimigo1.setToolTipText("Enxame de abelha 1 - Habilidade: 7 e Energia: 3");
        btInimigo1.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Texto da opção
        JLabel lbTextoInimigo1 = new JLabel("Enxame 1 -> H: 7 - E: 3 ");
        lbTextoInimigo1.setBounds(110,600,700,60);
        lbTextoInimigo1.setFont(new Font(Font.SERIF,Font.BOLD,22));
        lbTextoInimigo1.setForeground(new Color(139,0,0));

        add(btInimigo1);
        add(lbTextoInimigo1);

    }
}
