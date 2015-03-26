package jp.co.mti.mixjuke.web.external.response;


public class InquiryMemberResponse {

    private String acctid;
    private Result result;

    /**
     * @return the acctid
     */
    public String getAcctid() {
        return acctid;
    }

    /**
     * @param acctid
     *            the acctid to set
     */
    public void setAcctid(String acctid) {
        this.acctid = acctid;
    }

    /**
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

}
