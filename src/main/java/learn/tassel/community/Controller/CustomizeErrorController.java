package learn.tassel.community.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义 错误
 *
 * 获取 4XX 和 5XX 错误
 */

@Controller
@RequestMapping("/error")
public class CustomizeErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, Model model){
        HttpStatus status = getStatus(request);
        if(status.is4xxClientError()){
            model.addAttribute("message","400是前台错误");
        }else if(status.is5xxServerError()){
            model.addAttribute("message","500是后台错误");
        }
        return new ModelAndView("error");
    }

    public HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try{
            return  HttpStatus.valueOf(statusCode);
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}
