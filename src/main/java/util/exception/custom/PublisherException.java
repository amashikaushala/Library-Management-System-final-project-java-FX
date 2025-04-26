package util.exception.custom;

import util.exception.ServiceException;

public class PublisherException extends ServiceException {
    public PublisherException() {
    }

    public PublisherException(String message, Throwable cause) {
        super(message, cause);
    }

    public PublisherException(String message) {
        super(message);
    }
}
