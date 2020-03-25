package learn.tassel.community.Controller;

import learn.tassel.community.Dto.PageQuestionDTO;
import learn.tassel.community.Mapper.UserMapper;
import learn.tassel.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired(required=false)
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    //初始路径
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){

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
