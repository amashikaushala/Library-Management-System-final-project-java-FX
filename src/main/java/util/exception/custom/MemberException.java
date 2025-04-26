package util.exception.custom;

import util.exception.ServiceException;

public class MemberException extends ServiceException {
    public MemberException() {
    }

    public MemberException(String message) {

        super(message);
    }

    public MemberException(String message, Throwable cause) {

        super(message, cause);
    }
}
