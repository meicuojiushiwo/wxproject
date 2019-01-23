package com.nbh.wxprojectcore.exception;

public class AuthorityException extends BaseException {

    public AuthorityException() {
    }

    public AuthorityException(String message) {
        super(message);
    }

    public AuthorityException(String code, String message) {
        super(code, message);
    }
}
