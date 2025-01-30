package livro.jogo.acaosecoes;

import livro.jogo.entidades.ProximaSecao;
import livro.jogo.entidades.Secao;
import livro.jogo.utils.DadosLivroCarregado;

import javax.swing.*;

public class AcoesSecao_1 {
    private final Secao secao = DadosLivroCarregado.getMapSecao().get(1);
    private final ProximaSecao opcao_1 = secao.getProximasSecoes().get(0);
    private final ProximaSecao opcao_2 = secao.getProximasSecoes().get(1);

    public void opcao_1(){
        JOptionPane.showMessageDialog(null, "Opcao: "+opcao_1.getCodProximaSecao()+" - Descricao da opção: "+opcao_1.getTextoOpcao());
    }

    public void opcao_2(){
        JOptionPane.showMessageDialog(null, "Opcao: "+opcao_2.getCodProximaSecao()+" - Descricao da opção: "+opcao_2.getTextoOpcao());
    }
}
