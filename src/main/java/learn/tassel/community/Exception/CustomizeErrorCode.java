package learn.tassel.community.Exception;

import javax.xml.transform.OutputKeys;

/**
 * 枚举
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001,"你找的问题不在了，请稍后再次尝试。"),
    NO_LOGIN(2002,"尊敬的用户您尚未登陆，请登陆操作"),
    OK_LOGIN(200,"操作成功"),
    NOT_FOUND_COMMENT(2003,"您回复的评论不存在。"),
    NOT_FOUND_QUESTION(2004,"您回复的问题不存在。");
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code,String message) {
        this.message = message;
        this.code = code;
    }
}
