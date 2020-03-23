package learn.tassel.community.Controller;

import learn.tassel.community.Mapper.UserMapper;
import learn.tassel.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    //初始路径
    @GetMapping("/")
    public String index(HttpServletRequest request){
        //通过request获取 验证cookie
        Cookie[] cookies = request.getCookies();
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
