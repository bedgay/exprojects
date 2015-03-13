/**
 * 
 */
package jp.co.mti.mixjuke.base;

/**
 * @author Xuan Nguyen
 *
 */
public class MixjukeRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -4409415420712721819L;

    /**
     * @param message
     */
    public MixjukeRuntimeException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public MixjukeRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public MixjukeRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
