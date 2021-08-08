
package test.javidesoft.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when an object can't be found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8065930713576933330L;

    /**
     * The constructor.
     *
     * @param message the message
     */
    public NotFoundException(final String message) {
        super(message);
    }

    /**
     * The constructor.
     *
     * @param message the message
     * @param cause   The origin error
     */
    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
