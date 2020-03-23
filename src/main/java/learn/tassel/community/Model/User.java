package learn.tassel.community.Model;


public class User {

    private  Integer id;
    private String name;
    private String account_id;
    private String token;
    private Long gmtCreat;
    private Long gmtModifity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGmtCreat() {
        return gmtCreat;
    }

    public void setGmtCreat(Long gmtCreat) {
        this.gmtCreat = gmtCreat;
    }

    public Long getGmtModifity() {
        return gmtModifity;
    }

    public void setGmtModifity(Long gmtModifity) {
        this.gmtModifity = gmtModifity;
    }
}
