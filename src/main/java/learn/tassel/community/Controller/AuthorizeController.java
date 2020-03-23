package learn.tassel.community.Controller;

import learn.tassel.community.Dto.AccessTokenDTO;
import learn.tassel.community.Dto.GitHubUser;
import learn.tassel.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
//Controller 路由Api的承载者
public class AuthorizeController {

    //装配类
    @Autowired
    private GitHubProvider gitHubProvider;

    //装配配置文件内容值
    @Value("${github.client.id}")
    private String ClientID;

    @Value("${github.client.secret}")
    private String ClientSecret;

    @Value("${github.redirect.uri}")
    private String RedirectUri;

//    用于接受github账号登陆验证
    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state, HttpServletRequest request){

        //引入jar 或依赖 alt+回车
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(ClientID);
        accessTokenDTO.setClient_secret(ClientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(RedirectUri);
        accessTokenDTO.setState(state);
        //得到方法返回值 ctrl+ alt + v
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        //access_token=3186b52e8aa93ad31f6a5389ecc9db47522b295d&scope=user&token_type=bearer

        //获取token; access_token
        String access_token = accessToken.split("&")[0].split("=")[1];
        GitHubUser user = gitHubProvider.getGitHubUser(access_token);

        //手写session
        if(user != null){
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            return "redirect:/";
        }

    }
}
