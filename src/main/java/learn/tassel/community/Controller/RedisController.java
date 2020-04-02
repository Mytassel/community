package learn.tassel.community.Controller;

import learn.tassel.community.Redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Slf4j
@RequestMapping("/redis")
@Controller
public class RedisController {

    private static int ExpireTime = 600;   // redis中存储的过期时间60s

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/set")
    public boolean redisset(String key, String value){
        System.out.println(value);
        return redisUtil.set(key,value);
    }

    @RequestMapping("/get")
    public Object redisget(String key){
        Object o = redisUtil.get(key);

        System.out.println("get key:"+o.toString());

        return o;
    }

}