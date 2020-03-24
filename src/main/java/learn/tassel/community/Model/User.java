package learn.tassel.community.Model;

import lombok.Data;

@Data
public class User {
    private  Integer id;
    private String name;
    private String account_id;
    private String token;
    private Long gmtCreat;
    private Long gmtModifity;
    private String avaterUrl;
}
