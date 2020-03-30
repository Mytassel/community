package learn.tassel.community.Exception;

/**
 * 枚举
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("你找的问题不在了，请稍后再次尝试。");

    @Override
    public String getMessage() {
        return message;
    }
    private String message;

    CustomizeErrorCode(String message){
        this.message = message;
    }
}
