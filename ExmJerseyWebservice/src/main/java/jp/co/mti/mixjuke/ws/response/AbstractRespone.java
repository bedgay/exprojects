package jp.co.mti.mixjuke.ws.response;

public abstract class AbstractRespone {

    protected int result;

    public AbstractRespone() {
        super();
    }

    public AbstractRespone(int result) {
        this.result = result;
    }

    public AbstractRespone(ResultCode result) {
        this.result = result.getResultCode();
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}