package learn.tassel.community.Controller;

import learn.tassel.community.Dto.PageQuestionDTO;
import learn.tassel.community.Mapper.UserMapper;
import learn.tassel.community.Model.User;
import learn.tassel.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired(required=false)
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    //初始路径
    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        //通过request获取 验证cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length !=0)
        for (int len=cookies.length,i=0;i<len; i++) {
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findUserInfo(token);
                //将用户信息返回
                request.getSession().setAttribute("user",user);
                break;
            }
        }
        PageQuestionDTO  questionDTO = questionService.list(page, size);
        model.addAttribute("PageQuestion",questionDTO);
        return "index";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/test")
    public String webChar(@RequestParam(name="test",required = false)String  name, Model model){
        model.addAttribute("test",name);
        //请求转发
        return "test";
    }
}
