package livro.jogo.telas.desktop.centralizacaotelas;

import livro.jogo.entidades.Livro;
import livro.jogo.telas.desktop.TelaCriarPersonagem;
import livro.jogo.telas.desktop.TelaPrincipal;
import livro.jogo.telas.desktop.TelaRegra;
import livro.jogo.telas.desktop.TelaRegrasOpcoes;
import livro.jogo.telas.desktop.personalizados.TelaBasica;
import livro.jogo.utils.ManipularDadosLivro;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CarregarTelas {
    private TelaBasica telaPrincipal; //Guardar a referência da tela principal

    public CarregarTelas(TelaBasica telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    public CarregarTelas() {
    }

    public TelaBasica getTelaPrincipal() {
        return telaPrincipal;
    }

    public void carregarTela(TelasDisponiveisParaCarregamento tela, String titulo, String tituloTela, String texto){
        switch(tela) {
            case TelasDisponiveisParaCarregamento.TELA_PRINCIPAL:
                telaPrincipal();
                break;
            case TelasDisponiveisParaCarregamento.TELA_REGRAS_OPCOES:
                telaRegrasOpcoes();
                break;
            case TelasDisponiveisParaCarregamento.TELA_REGRAS:
               telaRegra(titulo, tituloTela, texto);
                break;
            case TelasDisponiveisParaCarregamento.TELA_CRIACAO_PERSONAGEM:
                telaCriacaoPersonagem();
                break;
            default:
                break;
        }

    }

    private void telaPrincipal(){
        TelaPrincipal tela = new TelaPrincipal(1430,800);
        this.telaPrincipal = tela;
        tela.setVisible(true);
    }

    private void telaRegrasOpcoes(){
        TelaRegrasOpcoes telaRegrasOpcoes = new TelaRegrasOpcoes(305,400);
        telaRegrasOpcoes.setVisible(true);
    }

    private void telaRegra(String titulo, String tituloTela, String texto){
        TelaRegra telaRegras = new TelaRegra(1200,790,titulo,texto);
        telaRegras.setTitle(tituloTela);
        telaRegras.setVisible(true);
    }

    private void telaCriacaoPersonagem(){
        TelaCriarPersonagem telaCriarPersonagem = new TelaCriarPersonagem(1165,830, telaPrincipal);

        telaCriarPersonagem.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                telaPrincipal.setVisible(true);
                //System.exit(0);
            }
        });

        telaCriarPersonagem.setVisible(true);
    }
}
