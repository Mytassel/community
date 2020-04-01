package learn.tassel.community.Controller;

import learn.tassel.community.Dto.QuestionDTO;
import learn.tassel.community.Mapper.QuestionMapper;
import learn.tassel.community.Model.Question;
import learn.tassel.community.Model.User;
import learn.tassel.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 发布页面
 */

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    //初始路径 get 请求（由发起按钮跳转到发布页面）
    @GetMapping("/publish")
    public String publish(){
        return "/publish";
    }

    @GetMapping("/publish/{id}")
    public String editPublish(@PathVariable(name = "id")Long id,
                              Model model){
        QuestionDTO question = questionService.findQuestionById(id);

        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",id);

        return "/publish";
    }


    @PostMapping("/publish")
    public String doPublish(@RequestParam("title")String title,
                            @RequestParam("description")String description,
                            @RequestParam("tag")String tag,
                            @RequestParam("id") Long id,
                            HttpServletRequest request,
                            Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        Question question = new Question();
        question.setTitle(title);
        question.setTag(tag);
        question.setDescription(description);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModifity(question.getGmtCreate());
        //获取用户id
        User user = (User)request.getSession().getAttribute("user");
        question.setId(id);
        question.setCreater(user.getId());
        if(user != null){
            questionService.insertOrUpdateQuestion(question);
            //成功，返回登陆页面
            return "redirect:/";
        }
        //失败，内容回填。并提示失败。
        model.addAttribute("fail","请登录之后发布消息");
        return "publish";
    }

}
