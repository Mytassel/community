package learn.tassel.community.Mapper;

import learn.tassel.community.Model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,creater,tag,gmt_create,gmt_modifity) values (#{title},#{description},#{creater},#{tag},#{gmtCreate},#{gmtModifity})")
    void insertQuestion(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creater = #{userId}")
    Integer count2UserId(@Param("userId") Integer userId);

    @Select("select * from question where creater = #{userId} limit #{offset},#{size}")
    List<Question> list2UserId(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select * from question where id = #{id}")
    Question findQuestionById(@Param("id") Integer id);

    @Update("update question set title=#{title},description=#{description},tag=#{tag},gmt_modifity=#{gmtModifity} where id = #{id}")
    void updateQuestion(Question question);
}
