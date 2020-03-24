package learn.tassel.community.Controller;

import learn.tassel.community.Mapper.QuestionMapper;
import learn.tassel.community.Mapper.UserMapper;
import learn.tassel.community.Model.Question;
import learn.tassel.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 发布页面
 */

@Controller
public class PublishController {


    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    //初始路径 get 请求（由发起按钮跳转到发布页面）
    @GetMapping("/publish")
    public String publish(){
        return "/publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title")String title,
                            @RequestParam("description")String description,
                            @RequestParam("tag")String tag,
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
        User user = null;
        Cookie[] cookies = request.getCookies();

        if(cookies != null && cookies.length !=0)
        for (int len=cookies.length,i=0;i<len; i++) {
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findUserInfo(token);
                //将用户信息返回
                question.setCreater(user.getId());
                break;
            }
        }
        if(user != null){
            questionMapper.insertQuestion(question);
            //成功，返回登陆页面
            return "redirect:/";
        }

        //失败，内容回填。并提示失败。
        model.addAttribute("fail","请登录之后发布消息");
        return "publish";
    }

}
