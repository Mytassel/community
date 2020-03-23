package learn.tassel.community.Mapper;

import learn.tassel.community.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,creater,tag,gmt_create,gmt_modifity) values (#{title},#{description},#{creater},#{tag},#{gmtCreate},#{gmtModifity})")
    void insertQuestion(Question question);
}
