package learn.tassel.community.Mapper;

import learn.tassel.community.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,creater,tag,gmt_create,gmt_modifity) values (#{title},#{description},#{creater},#{tag},#{gmtCreate},#{gmtModifity})")
    void insertQuestion(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();
}
