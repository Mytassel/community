package learn.tassel.community.Dto;

import learn.tassel.community.Exception.CustomizeErrorCode;
import lombok.Data;

@Data
public class ResultDTO {

    private Integer code;
    private String message;

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    //生成返回错误信息
    public static ResultDTO errorOf(CustomizeErrorCode customizeErrorCode){
        ResultDTO resultDto = new ResultDTO(customizeErrorCode.getCode(),customizeErrorCode.getMessage());
        return resultDto;
    }
    public static ResultDTO okOf(CustomizeErrorCode customizeErrorCode){
        ResultDTO resultDto = new ResultDTO(customizeErrorCode.getCode(),customizeErrorCode.getMessage());
        return resultDto;
    }

}
