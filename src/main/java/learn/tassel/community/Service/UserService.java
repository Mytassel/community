package learn.tassel.community.Service;

import learn.tassel.community.Mapper.UserMapper;
import learn.tassel.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    public void createOrUpdateUserInfo(User user){
        //从数据库中查找有挂用户的accont_id
        Integer user1 = userMapper.volidateUser(user.getAccount_id());
        if(user1 == 0){
            //执行插入操作
            userMapper.insertUserInfo(user);
        }else{
            //更新用户信息
            userMapper.updateUserInfo(user);
        }
    }

}
