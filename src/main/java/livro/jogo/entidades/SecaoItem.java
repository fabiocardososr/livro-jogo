package livro.jogo.entidades;

//Classe que guarda o idItem encontrado na secao
//Usado dessa forma para que a biblioteca JSckson serialize o json
//Pois o Jackson não serializa tipo Integer ou String
public class SecaoItem {
    private int idItem;

    public int getIdItem() {
        return idItem;
    }

    @Override
    public String toString() {
        return "SecaoItem{" +
                "idItem=" + idItem +
                '}';
    }
}