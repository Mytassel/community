package learn.tassel.community.Mapper;

import learn.tassel.community.Model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(parent_id,commentator,content,type,gmt_create,gmt_modified,like_count) values (#{parentId},#{commentator},#{content},#{type},#{gmtCreate},#{gmtModifity},#{likeCount})")
    void insertComment(Comment comment);

    @Select("select * from comment where parent_id=#{parentId}")
    Comment volidateComment(@Param("parentId") Long parentId);
}
