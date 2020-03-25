package learn.tassel.community.Controller;

import learn.tassel.community.Dto.PageQuestionDTO;
import learn.tassel.community.Model.User;
import learn.tassel.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

//我的问题

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request, Model model,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size){

        User user = (User)request.getSession().getAttribute("user");
        //如果用户为空，直接跳转到主页。
        if(user == null){
            return "redirect:/";
        }
        if("problem".equals(action)){
            model.addAttribute("selectTagName","我的问题");
            model.addAttribute("selectTag","problem");
        }else if("reply".equals(action)){
            model.addAttribute("selectTagName","我的回复");
            model.addAttribute("selectTag","reply");
        }

        PageQuestionDTO questionDTO = questionService.list2UserId(user.getId(),page, size);
        model.addAttribute("PageQuestion",questionDTO);

        return "profile";
    }

}
