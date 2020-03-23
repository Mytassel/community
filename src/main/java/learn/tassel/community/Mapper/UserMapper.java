package learn.tassel.community.Mapper;

import learn.tassel.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息
 * @Mapper 注解
 */
@Mapper
public interface UserMapper {

    //将用户信息插入表中
    @Insert("insert into user (name,account_id,token,gmt_creat,gmt_modified) values (#{name},#{account_id},#{token},#{gmtCreat},#{gmtModifity})")
    void insertUserInfo(User user);

}
