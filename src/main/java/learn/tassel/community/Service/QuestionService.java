package learn.tassel.community.Service;

import learn.tassel.community.Dto.PageQuestionDTO;
import learn.tassel.community.Dto.QuestionDTO;
import learn.tassel.community.Mapper.QuestionMapper;
import learn.tassel.community.Mapper.UserMapper;
import learn.tassel.community.Model.Question;
import learn.tassel.community.Model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    //查询出所有的问题
    public PageQuestionDTO list(Integer page, Integer size){

        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();

        List<QuestionDTO> QuestionList = new ArrayList<QuestionDTO>();

        Integer offset = (page-1)*size;

        Integer countNum = questionMapper.count();

        List<Question> list = questionMapper.list(offset,size);
        if(list.size()>0)
            for (Question question:list) {
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question,questionDTO);
                Integer creater = question.getCreater();
                User id2UserInfo = userMapper.find2IdUserInfo(creater);
                questionDTO.setUser(id2UserInfo);
                QuestionList.add(questionDTO);
            }
        pageQuestionDTO.setQuestion(QuestionList);
        pageQuestionDTO.setPageData(page,size,countNum);
        return pageQuestionDTO;
    }

}
