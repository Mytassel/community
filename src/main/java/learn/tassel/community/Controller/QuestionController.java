package learn.tassel.community.Controller;

import learn.tassel.community.Dto.QuestionDTO;
import learn.tassel.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String profile(@PathVariable(name = "id") Long id,
                          Model model){
        //通过id 获取问题内容
        QuestionDTO questionDTO = questionService.findQuestionById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }

}
