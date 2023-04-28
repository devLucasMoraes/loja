package br.com.alura.loja.exeptions;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String msg) {
        super(msg);
    }
}
