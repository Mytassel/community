package learn.tassel.community.Service;

import learn.tassel.community.Dto.PageQuestionDTO;
import learn.tassel.community.Dto.QuestionDTO;
import learn.tassel.community.Exception.CustomizeErrorCode;
import learn.tassel.community.Exception.CustomizeException;
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
        QuestionDTOMerge(list,QuestionList);
        pageQuestionDTO.setQuestion(QuestionList);
        pageQuestionDTO.setPageData(page,size,countNum);
        return pageQuestionDTO;
    }

    public PageQuestionDTO list2UserId(Integer userID, Integer page, Integer size) {
        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
        List<QuestionDTO> QuestionList = new ArrayList<QuestionDTO>();
        Integer offset = (page-1)*size;
        Integer countNum = questionMapper.count2UserId(userID);

        List<Question> list = questionMapper.list2UserId(userID,offset,size);
        QuestionDTOMerge(list,QuestionList);
        pageQuestionDTO.setQuestion(QuestionList);
        pageQuestionDTO.setPageData(page,size,countNum);
        return pageQuestionDTO;
    }

    public QuestionDTO findQuestionById(Integer id) {
        Question question = questionMapper.findQuestionById(id);
        //抛出异常
        if(question ==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        Integer creater = question.getCreater();
        User id2UserInfo = userMapper.find2IdUserInfo(creater);
        questionDTO.setUser(id2UserInfo);
        return questionDTO;
    }

    public void insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }

    public void insertOrUpdateQuestion(Question question) {
        Integer id = question.getId();
        if(id == null){
            questionMapper.insertQuestion(question);
        }else{
            questionMapper.updateQuestion(question);
        }

    }

    /**
     * 返回问题列表信息 重构
     * @param list
     * @param QuestionList
     */
    private void QuestionDTOMerge(List<Question> list,List<QuestionDTO> QuestionList){
        if(list.size()>0)
            for (Question question:list) {
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question,questionDTO);
                Integer creater = question.getCreater();
                User id2UserInfo = userMapper.find2IdUserInfo(creater);
                questionDTO.setUser(id2UserInfo);
                QuestionList.add(questionDTO);
            }
    }
}
