package learn.tassel.community.provider;

import com.alibaba.fastjson.JSON;
import learn.tassel.community.Dto.AccessTokenDTO;
import learn.tassel.community.Dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
// 把改类初始化为Spring的上下文
// 不需要实例化 IOC
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO token) {
        MediaType mediaType  = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(token));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            System.out.println("str: "+str);
            return  str;
        } catch (IOException e) {
            // 添加异常 alt + enter
        }
        return  null;
    }

    // 携带access_token 请求返回值。
    public GitHubUser getGitHubUser(String access_token){

        System.out.println(access_token);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+access_token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();//将返回的字符传封装成对象

            System.out.println(str);

            GitHubUser gitHubUser = JSON.parseObject(str, GitHubUser.class);
            return gitHubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
