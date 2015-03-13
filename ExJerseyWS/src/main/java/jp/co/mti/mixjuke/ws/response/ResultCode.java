package jp.co.mti.mixjuke.ws.response;

public enum ResultCode {
    NORMAL(0, "ResultCode.Normal"), // Normal
    NOT_LOGIN(-1, "ResultCode.NotLogin"), // Not logged in
    NOT_A_MEMBER(-2, "ResultCode.NotAMember"), // Not a member
    CAN_FOUND_SPEC_RESOURCE(-3, "ResultCode.NotFoundResource"), // Can not find specified resource
    ERROR_IN_PARAMETER(-4, "ResultCode.ErrorInParam"), // Error in parameter (NULL or wrong)
    ALREADY_DEVICE(-5, "ResultCode.AlreadyDevice"), // Already another device registered
    NO_NEW_DATA(-6, "ResultCode.NoNewData"), // No new data
    ERROR_FROM_RE(-7, "ResultCode.ErrorFromRE"), // -7 : Undefined
    UN_DEFINED(-8, "ResultCode.Undefined"), // -8 : Undefined
    UN_DEFINED2(-8, "ResultCode.Undefined2"), // -9 : Undefined
    UN_DEFINED3(-9, "ResultCode.Undefined3"), // -10 : Undefined
    UN_DEFINED4(-11, "ResultCode.Undefined4"), // -11 : Undefined
    RESOURCE_NOT_FOUND(-88, "ResultCode.ResourceNotFound"), // Can not find the resource or wrong url
    SYSTEM_ERROR(-99, "ResultCode.SystemError"); // System error
    
    private int resutlCode;
    private String description;

    private ResultCode(int s, String des) {
        resutlCode = s;
        description = des;
    }

    public int getResultCode() {
        return resutlCode;
    }

    public String getDescription() {
        return description;
    }
}
