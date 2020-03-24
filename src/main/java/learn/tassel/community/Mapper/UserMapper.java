package learn.tassel.community.Mapper;

import learn.tassel.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户信息
 * @Mapper 注解
 */
@Mapper
public interface UserMapper {

    //将用户信息插入表中
    @Insert("insert into user (name,account_id,token,gmt_creat,gmt_modified,avater_url) values (#{name},#{account_id},#{token},#{gmtCreat},#{gmtModifity},#{avaterUrl})")
    void insertUserInfo(User user);

    @Select("select * from user where token = #{token}")
    User findUserInfo(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User find2IdUserInfo(@Param("id") Integer id);
}
