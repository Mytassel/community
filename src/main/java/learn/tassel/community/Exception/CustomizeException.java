package learn.tassel.community.Exception;

/**
 * 继承运行异常
 */
public class CustomizeException extends RuntimeException {
    private String message;
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
