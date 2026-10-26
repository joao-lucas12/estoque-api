package estoque_api.exceptions;

public class NomeProdutoDuplicadoException extends RuntimeException {

    public NomeProdutoDuplicadoException(String mensagem) {
        super(mensagem);
    }

}
