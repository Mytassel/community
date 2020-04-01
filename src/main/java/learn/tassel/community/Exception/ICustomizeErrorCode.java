package learn.tassel.community.Exception;

//自定义异常情况，返回信息
public interface ICustomizeErrorCode {
    String getMessage();
    Integer getCode();
}
