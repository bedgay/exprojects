/**
 * 
 */
package jp.co.mti.mixjuke.ws.request;


/**
 * @author Xuan Nguyen
 * 
 */
public enum SongStatus {
    NEUTRAL(0), BAD(1), GOOD(2);

    int flag;

    private SongStatus(int code) {
        this.flag = code;
    }

    public int getSflag() {
        return flag;
    }

    public static boolean isValidFlag(int sflag) {
        if (sflag == NEUTRAL.getSflag() || sflag == BAD.getSflag()
                || sflag == GOOD.getSflag()) {
            return true;
        }
        return false;
    }

}
