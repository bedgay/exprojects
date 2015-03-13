/**
 * 
 */
package jp.co.mti.mixjuke.ws.exception;

/**
 * @author Xuan Nguyen
 * 
 */
public class REException extends AbstractWebApplicationException {

    private static final long serialVersionUID = 890868140540159887L;

    /**
     * Throw exception from RE.
     * 
     * @param code
     * @param description
     */
    public REException(int code, String description) {
        super(code, description);
    }
}
