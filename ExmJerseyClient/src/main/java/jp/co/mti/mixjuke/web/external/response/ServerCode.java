/**
 * 
 */
package jp.co.mti.mixjuke.web.external.response;

/**
 * @author Xuan Nguyen
 *
 */
public enum ServerCode {
    IF5001_NORMAL("I000", "ServerCode.IF5001_Normal"),
    IF5001_REGISTER_DB_FAIL("E000", "ServerCode.IF5001_RegisterDBFail"),
    IF5001_REGISTER_DB_FAIL_1("E000", "ServerCode.IF5001_RegisterDBFai_1"),
    IF5001_SITEID_ERROR("E001", "ServerCode.IF5001_SiteIDError"),
    IF5001_TRANSACTIONID_ERROR("E001", "ServerCode.IF5001_TransactionIDError"),
    IF5001_IFIDS_ERROR("E001", "ServerCode.IF5001_IFIDsError"),
    IF5001_SITEID_FORMAT_ERROR("E002", "ServerCode.IF5001_SiteIDFormatError"),
    IF5001_TRANSACTIONID_FORMAT_ERROR("E002", "ServerCode.IF5001_TransactionFormatIDError"),
    IF5001_IFIDS_FORMAT_ERROR("E002", "ServerCode.IF5001_IFIDsFormatError"),
    IF5001_EXPIRE_FORMAT_ERROR("E002", "ServerCode.IF5001_ExpireFormatError"),
    IF5001_EXPIRE_EXCEED_MAX("E003", "ServerCode.IF5001_ExpireExceedMax"),
    IF5001_IFIDS_SAME_ID("E003", "ServerCode.IF5001_IFIDsSameID"),
    IF5001_MASTER_DATA_BUG("E008", "ServerCode.IF5001_MasterDataBug"),
    
    IF5002_NORMAL("I000", "ServerCode.IF5002_Normal"),
    IF5002_DELETING_DB_FAIL("E000", "ServerCode.IF5002_DBFail"),
    IF5002_DELETING_DB_FAIL_1("E000", "ServerCode.IF5002_DBFail_1"),
    IF5002_TOKEN_ERROR("E001", "ServerCode.IF5002_TokenError"),
    IF5002_TOKEN_FORMAT_ERROR("E001", "ServerCode.IF5002_TokenFormatError"),
    
    IF5003_NORMAL("I000", "ServerCode.IF5003_Normal"),
    IF5003_REGISTER_SESSION_FAIL("E000", "ServerCode.IF5003_RegisterSessionFail"),
    IF5003_ACCOUNT_LOCKED("E000", "ServerCode.IF5003_AccountLocked"),
    IF5003_ACCTID_ERROR("E001", "ServerCode.IF5003_AccountError"),
    IF5003_PASSWORD_ERROR("E001", "ServerCode.IF5003_PasswordError"),
    IF5003_ACCTID_FORMAT_ERROR("E001", "ServerCode.IF5003_AccountFormatError"),
    IF5003_CANNOT_GET_DATA_FROM_ARGUMENT("E002", "ServerCode.IF5003_CannotGetDataFromArgument"),
    IF5003_ACCOUNT_LOCKED_SUCCESS_IN_SUBSEQUENT("E002", "ServerCode.IF5003_AccountLockedSuccessInSubsequent"),
    
    IF5006_NORMAL("I000", "ServerCode.IF5006_Normal"),
    IF5006_ACCTID_ERROR("E001", "ServerCode.IF5006_AccountError"),
    IF5006_PASSWORD_ERROR("E001", "ServerCode.IF5006_PasswordError"),
    IF5006_ACCTID_FORMAT_ERROR("E002", "ServerCode.IF5006_AccountFormatError"),
    IF5006_PASSWORD_FORMAT_ERROR("E002", "ServerCode.IF5006_PasswordFormatError"),
    IF5006_BIRTHDAY_FORMAT_ERROR("E002", "ServerCode.IF5006_BirthdayFormatError"),
    IF5006_TELNO_FORMAT_ERROR("E002", "ServerCode.IF5006_TelnoFormatError"),
    IF5006_MAILADDR_REGISTERED_ALREADY("E009", "ServerCode.IF5006_MailAddrRegisteresAlready"),
    IF5006_REGISTER_SESSION_FAIL("E000", "ServerCode.IF5006_RegisterSessionFail"),
    IF5006_ACCOUNT_REGISTERED_ALREADY("E005", "ServerCode.IF5006_AccountRegisteresAlready"),
    IF5006_MAILADDR_FORMAT_ERROR("E002", "ServerCode.IF5006_MailAddrFormatError"),
    IF5006_SITEID_ERROR("E001", "ServerCode.IF5006_SiteIDError"),
    IF5006_TRANSACTIONID_ERROR("E001", "ServerCode.IF5006_TransactionIDError"),
    IF5006_TOKEN_ERROR("E001", "ServerCode.IF5006_TokenError"),
    IF5006_SITEID_FORMAT_ERROR("E002", "ServerCode.IF5006_SiteIDFormatError"),
    IF5006_TRANSACTIONID_FORMAT_ERROR("E002", "ServerCode.IF5006_TransactionFormatIDError"),
    IF5006_TOKEN_FORMAT_ERROR("E002", "ServerCode.IF5006_TokenFormatIDError"),
    IF5006_SITEID_DIFFERENT_IF5001("E003", "ServerCode.IF5006_SiteIDDifferentIF5001"),
    IF5006_TRANSACTIONID_DIFFERENT_IF5001("E003", "ServerCode.IF5006_TransactionIDDifferentIF5001"),
    IF5006_TOKEN_TARGER_INVALID("E008", "ServerCode.IF5006_TokenTargetInvalid"),
    IF5006_TOKEN_EXPIRE("E024", "ServerCode.IF5006_TokenExpire"),
    IF5006_TOKEN_TARGET_USED_ALREADY("E031", "ServerCode.IF5006_TokenTargetUsedAlready"),
    
    IF5008_NORMAL("I000", "ServerCode.IF5008_Normal"),
    IF5008_CANNOT_GET_RESOURCEID_FROM_ARGUMENT("E008", "ServerCode.IF5008_CannotGetResourceIDFromArgument"),
    IF5008_TARGET_RESOURCE_IS_BEING_USED("E011", "ServerCode.IF5008_TargetResourceIsBeingUsed"),
    IF5008_TARGET_IS_NOT_FREE_RESOURCE("E031", "ServerCode.IF5008_TargetIsNotFreeResource"),
    IF5008_CANNOT_GET_MUID_FROM_ARGUMENT("E008", "ServerCode.IF5008_CannotGetMuidFromArgument"),
    IF5008_SITE_CALLBACK_ERROR("E016", "ServerCode.IF5008_SiteCallBackError"),
    IF5008_RESOURCE_EXCEPT_MONTHLY_FREE_CHARGING("E019", "ServerCode.IF5008_ResouceExceptMonthlyFreeCharge"),
    IF5008_MUID_ERROR("E001", "ServerCode.IF5008_MuidError"),
    IF5008_MUID_FORMAT_ERROR("E002", "ServerCode.IF5008_MuidFormatError"),
    IF5008_RID_ERROR("E001", "ServerCode.IF5008_RidError"),
    IF5008_RID_FORMAT_ERROR("E002", "ServerCode.IF5008_RidFormatError"),
    IF5008_SITEID_ERROR("E001", "ServerCode.IF5008_SiteIDError"),
    IF5008_TRANSACTIONID_ERROR("E001", "ServerCode.IF5008_TransactionIDError"),
    IF5008_TOKEN_ERROR("E001", "ServerCode.IF5008_TokenError"),
    IF5008_SITEID_FORMAT_ERROR("E002", "ServerCode.IF5008_SiteIDFormatError"),
    IF5008_TRANSACTIONID_FORMAT_ERROR("E002", "ServerCode.IF5008_TransactionFormatIDError"),
    IF5008_TOKEN_FORMAT_ERROR("E002", "ServerCode.IF5008_TokenFormatIDError"),
    IF5008_SITEID_DIFFERENT_IF5001("E003", "ServerCode.IF5008_SiteIDDifferentIF5001"),
    IF5008_TRANSACTIONID_DIFFERENT_IF5001("E003", "ServerCode.IF5008_TransactionIDDifferentIF5001"),
    IF5008_TOKEN_TARGER_INVALID("E008", "ServerCode.IF5008_TokenTargetInvalid"),
    IF5008_TOKEN_EXPIRE("E024", "ServerCode.IF5008_TokenExpire"),
    IF5008_TOKEN_TARGET_USED_ALREADY("E031", "ServerCode.IF5008_TokenTargetUsedAlready");

    private String code;
    private String description;

    private ServerCode(String code, String des) {
        this.code = code;
        description = des;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
