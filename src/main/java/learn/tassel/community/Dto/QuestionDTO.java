package learn.tassel.community.Dto;

import learn.tassel.community.Model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;//发布信息id
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModifity;
    private Integer creater;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
