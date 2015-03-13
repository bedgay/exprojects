/**
 * 
 */
package jp.co.mti.external.re.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author Xuan Nguyen
 *
 */
public class AbstractREResponse{

    protected int result;
    @JsonSerialize(include = Inclusion.NON_NULL)
    protected String error;
    int total;
    /**
     * @return the result
     */
    public int getResult() {
        return result;
    }
    /**
     * @param result the result to set
     */
    public void setResult(int result) {
        this.result = result;
    }
    /**
     * @return the error
     */
    public String getError() {
        return error;
    }
    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }
    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    public String getErrorDetail(){
        return (this.result +": "+ this.error);
    }
}
