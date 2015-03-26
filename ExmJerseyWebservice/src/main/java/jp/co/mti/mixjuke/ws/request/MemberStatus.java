/**
 * 
 */
package jp.co.mti.mixjuke.ws.request;

/**
 * @author ntnxuan
 * 
 */
public enum MemberStatus {
    CANCELED(0), FREE(1), PREMIUM(2), FREE_TRIAL(3);

    int status;

    private MemberStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static boolean isFreeMember(int status) {
        if (status == FREE.getStatus()) {
            return true;
        }
        return false;
    }

    public static MemberStatus fromShort(int status) {
        if (status == FREE.getStatus()) {
            return MemberStatus.FREE;
        } else if (status == FREE_TRIAL.getStatus()) {
            return MemberStatus.FREE_TRIAL;
        } else if (status == PREMIUM.getStatus()) {
            return MemberStatus.PREMIUM;
        } else if (status == CANCELED.getStatus()) {
            return MemberStatus.CANCELED;
        }
        return null;
    }
}
