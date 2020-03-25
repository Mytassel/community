package learn.tassel.community.Interceptor;

import learn.tassel.community.Mapper.UserMapper;
import learn.tassel.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Ctr + o 重写该类方法
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired(required = false)
    private UserMapper userMapper;

    //请求前拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拦截Cookie
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

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
