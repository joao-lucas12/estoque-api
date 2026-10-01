package estoque_api.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException{

    public ProdutoNaoEncontradoException (String mensagem){
        super(mensagem);
    }
}
