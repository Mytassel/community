package learn.tassel.community.Dto;

import lombok.Data;

/**
 * Alt + Ins 组合键生产get + set
 *
 * DTO 是关联服务的实体
 */
@Data
public class AccessTokenDTO {
    private  String client_id;
    private  String client_secret;
    private  String code;
    private  String redirect_uri;
    private  String state;
}
