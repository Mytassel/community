package learn.tassel.community.Service;

import learn.tassel.community.Enums.CommentTypeEnum;
import learn.tassel.community.Exception.CustomizeErrorCode;
import learn.tassel.community.Exception.CustomizeException;
import learn.tassel.community.Mapper.CommentMapper;
import learn.tassel.community.Mapper.QuestionMapper;
import learn.tassel.community.Model.Comment;
import learn.tassel.community.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Autowired(required = false)
    private QuestionMapper questionMapper;

//   增加事物处理
    @Transactional
    public void insertComment(Comment comment) {
        //如果存在
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        //类型判断
        if(comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw  new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.volidateComment(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.NOT_FOUND_COMMENT);
            }
            commentMapper.insertComment(comment);
        }else{
            //回复问题
            //findQuestionById
            Question question = questionMapper.findQuestionById(comment.getParentId());
            if(question == null){
                throw new CustomizeException(CustomizeErrorCode.NOT_FOUND_QUESTION);
            }
            commentMapper.insertComment(comment);
            //回复数据，也增加阅读量。
            //点击问题列表时，已经有了阅读数增加了。
//            questionMapper.incView(comment.getParentId());
        }
    }
}
