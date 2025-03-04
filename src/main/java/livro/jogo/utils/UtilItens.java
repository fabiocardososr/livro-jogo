package livro.jogo.utils;

import livro.jogo.entidades.Item;
import livro.jogo.enums.TipoEfeito;

public class UtilItens {

    ///Verifica se item possui modificador do tipo habilidade
    public static int retornaModificadorQueInfluenciaNaRolagemDeDadosDaHabilidade(Item item){
        int modificador = 0;

        if (item.getTipoEfeito() != TipoEfeito.HABILIDADE)
            return modificador;

        ///Verificando modificador. Sendo temporário e quantidade de uso maior que 0 usa o modificador.
        ///Sendo permanente ignora o campo de quantidadede uso.
        if ( ( (item.getQuantidadeUso() > 0) || (item.getFlgUsoPermanente().equals("S")) ) &&
                (item.getFlgAfetaRolagemDados().equals("S")) ){
            modificador = item.getModificador();

            //Se item de uso temporário é necessário decrementar a quantidade de uso.
            if (item.getFlgUsoTemporario().equals("S"))
                item.setQuantidadeUso(item.getQuantidadeUso() - 1);
        }

        return modificador;
    }
}
