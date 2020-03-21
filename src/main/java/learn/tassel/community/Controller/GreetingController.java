package learn.tassel.community.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    //初始路径
    @GetMapping("/")
    public String index(){
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
