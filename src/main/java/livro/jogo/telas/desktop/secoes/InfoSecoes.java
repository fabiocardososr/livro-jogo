package livro.jogo.telas.desktop.secoes;

import java.io.Serializable;

public class InfoSecoes implements Serializable {
    //Necessário porque as seções 201,293 e 248
    //disponibilizam a opção de abrir arca e caso a arca já tenha sido aberta anteriormente deve ser verificado
    private boolean abriuArcaSecao389;

    //Usado para salvar progresso do jogo
    public boolean getAbriuArcaSecao389(){
        return abriuArcaSecao389;
    }

    //Usado para salvar progresso do jogo
    public void setAbriuArcaSecao389(boolean abriuArcaSecao389){
        this.abriuArcaSecao389 = abriuArcaSecao389;
    }
}
