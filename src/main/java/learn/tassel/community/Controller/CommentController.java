package learn.tassel.community.Controller;

import learn.tassel.community.Dto.CommentDTO;
import learn.tassel.community.Dto.ResultDTO;
import learn.tassel.community.Exception.CustomizeErrorCode;
import learn.tassel.community.Mapper.CommentMapper;
import learn.tassel.community.Model.Comment;
import learn.tassel.community.Model.User;
import learn.tassel.community.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

//    ResponseBody 返回的是数据，不是地址内容
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request){

        //判断是否登陆，回馈用户信息
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setCommentator(user.getId());
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModifity(comment.getGmtCreate());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        commentService.insertComment(comment);
        return ResultDTO.okOf(CustomizeErrorCode.OK_LOGIN);
    }

}
