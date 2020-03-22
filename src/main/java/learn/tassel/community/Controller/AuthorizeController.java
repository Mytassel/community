package learn.tassel.community.Controller;

import learn.tassel.community.Dto.AccessTokenDTO;
import learn.tassel.community.Dto.GitHubUser;
import learn.tassel.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//Controller 路由Api的承载者
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

//    用于接受github账号登陆验证
    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state){

        //引入jar 或依赖 alt+回车
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("1ceec699e187a3eed6fa");
        accessTokenDTO.setClient_secret("e2ce8560494a08d15c6d92a08cdd7672be31c960");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:9090/callback");
        accessTokenDTO.setState(state);
        //得到方法返回值 ctrl+ alt + v
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        //access_token=3186b52e8aa93ad31f6a5389ecc9db47522b295d&scope=user&token_type=bearer

        //获取token; access_token
        String access_token = accessToken.split("&")[0].split("=")[1];
        GitHubUser user = gitHubProvider.getGitHubUser(access_token);
        System.out.println(user.getName());
        return "index";
    }
}
